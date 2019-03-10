package com.culedger.identity;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VcxApiPoll extends VcxApi {

	private static final Logger logger = LoggerFactory.getLogger(VcxApiPoll.class);

	private static ConcurrentHashMap<String, ResponseEntity<?>> responses = new ConcurrentHashMap<String, ResponseEntity<?>> ();
	private static ExecutorService executor = Executors.newCachedThreadPool();

	public static ResponseEntity<?> poll(String jobId) {

		ResponseEntity<?> responseEntity = responses.get(jobId);

		logger.info("Get response for " + jobId + ": " + responseEntity);

		if (responseEntity == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else if (HttpStatus.NO_CONTENT.equals(responseEntity.getStatusCode())) {

			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {

			responses.remove(jobId);
			return responseEntity;
		}
	}

	static <T extends ResponseEntity<?>> String submit(Supplier<T> supplier) {

		final String jobId = UUID.randomUUID().toString();
		CompletableFuture<T> future = CompletableFuture.supplyAsync(supplier, executor);

		responses.put(jobId, new ResponseEntity<Void>(HttpStatus.NO_CONTENT));

		future.thenRun(() ->  {

			try {

				logger.info("Add response for " + jobId);
				responses.put(jobId, future.get());
			} catch (Exception ex) {

				if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
				throw new RuntimeException(ex.getMessage(), ex);
			}
		});

		return jobId;
	}
}
