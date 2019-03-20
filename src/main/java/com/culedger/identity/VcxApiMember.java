package com.culedger.identity;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.connection.ConnectionApi;
import com.evernym.sdk.vcx.issuer.IssuerApi;
import com.evernym.sdk.vcx.proof.GetProofResult;
import com.evernym.sdk.vcx.proof.ProofApi;

import io.swagger.model.CULedgerMember;
import io.swagger.model.CULedgerOnboardingData;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

public class VcxApiMember extends VcxApi {

	private static final Logger logger = LoggerFactory.getLogger(VcxApiMember.class);

	public static ResponseEntity<CULedgerOnboardingData> memberOnBoard(CULedgerOnboardingData cuLedgerOnboardingData, String memberId, String prefer) {

		Supplier<ResponseEntity<CULedgerOnboardingData>> supplier = () -> {

			try {

				// create connection

				Integer connectionHandle = createConnection(memberId, cuLedgerOnboardingData);

				// write to member DID mapper

				VcxApi.memberDidMapper.add(memberId, connectionHandle);
				if (logger.isInfoEnabled()) logger.info("Added member ID " + memberId + " with connection handle " + connectionHandle);

				// create credential

				createCredential(memberId, connectionHandle);

				// done

				CULedgerOnboardingData cuLedgerOnboardingDataResponse = cuLedgerOnboardingData;
				return new ResponseEntity<CULedgerOnboardingData>(cuLedgerOnboardingDataResponse, HttpStatus.OK);
			} catch (Exception ex) {

				if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
				return new ResponseEntity<CULedgerOnboardingData>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};

		if ("respond-async".equals(prefer)) {

			String jobId = VcxApiPoll.submit(supplier);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(VcxConfiguration.getApplicationProperty("server.contextPath") + "/" + jobId));
			ResponseEntity<CULedgerOnboardingData> response = new ResponseEntity<CULedgerOnboardingData>(headers, HttpStatus.ACCEPTED);
			return response;
		} else {

			try { return supplier.get(); } catch (Exception ex) { return new ResponseEntity<CULedgerOnboardingData>(HttpStatus.INTERNAL_SERVER_ERROR); }
		}
	}

	public static ResponseEntity<String> memberConnect(CULedgerOnboardingData cuLedgerOnboardingData, String memberId, String prefer) {

		Supplier<ResponseEntity<String>> supplier = () -> {

			try {

				// create connection

				Integer connectionHandle = createConnection(memberId, cuLedgerOnboardingData);

				// write to member DID mapper

				VcxApi.memberDidMapper.add(memberId, connectionHandle);
				if (logger.isInfoEnabled()) logger.info("Added member ID " + memberId + " with connection handle " + connectionHandle);

				// done

				return new ResponseEntity<String>("connected", HttpStatus.OK);
			} catch (Exception ex) {

				if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};

		if ("respond-async".equals(prefer)) {

			String jobId = VcxApiPoll.submit(supplier);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(VcxConfiguration.getApplicationProperty("server.contextPath") + "/" + jobId));
			ResponseEntity<String> response = new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
			return response;
		} else {

			try { return supplier.get(); } catch (Exception ex) { return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); }
		}
	}

	public static ResponseEntity<String> memberSendCredential(String memberId, String prefer) {

		Supplier<ResponseEntity<String>> supplier = () -> {

			try {

				// read from member DID mapper

				Integer connectionHandle = VcxApi.memberDidMapper.getAsConnectionHandle(memberId);
				if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " got connection handle " + connectionHandle);

				if (connectionHandle == null) return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

				// create credential

				createCredential(memberId, connectionHandle.intValue());

				// done

				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (Exception ex) {

				if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};

		if ("respond-async".equals(prefer)) {

			String jobId = VcxApiPoll.submit(supplier);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(VcxConfiguration.getApplicationProperty("server.contextPath") + "/" + jobId));
			ResponseEntity<String> response = new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
			return response;
		} else {

			try { return supplier.get(); } catch (Exception ex) { return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); }
		}
	}

	public static ResponseEntity<CULedgerMember> memberAuthenticate(String memberId, String prefer) {

		Supplier<ResponseEntity<CULedgerMember>> supplier = () -> {

			try {

				// read from member DID mapper

				Integer connectionHandle = VcxApi.memberDidMapper.getAsConnectionHandle(memberId);
				if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " got connection handle " + connectionHandle);

				if (connectionHandle == null) return new ResponseEntity<CULedgerMember>(HttpStatus.NOT_FOUND);

				// create proof

				String sourceId = memberId;
				String requestedAttrs = requestedAttrs();
				String requestedPredicates = "";
				String proofName = "mycuidproof";
				Integer proofHandle = ProofApi.proofCreate(sourceId, requestedAttrs, requestedPredicates, proofName).get();

				if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " and requested attrs " + requestedAttrs + " and requested predicates " + requestedPredicates + " got proof handle " + proofHandle);

				// send proof request

				Integer proofSendRequestResult = ProofApi.proofSendRequest(proofHandle, connectionHandle).get();
				if (logger.isInfoEnabled()) logger.info("For proof handle " + proofHandle + " and connection handle " + connectionHandle + " got proof send request result " + proofSendRequestResult);

				// update state

				Integer proofUpdateStateResult = ProofApi.proofUpdateState(proofHandle).get();
				if (logger.isInfoEnabled()) logger.info("For proof handle " + proofHandle + " got proof update state result " + proofUpdateStateResult);

				// wait

				long waitProofRequest = System.currentTimeMillis();
				while (true) {

					long timeout = VcxConfiguration.VCX_TIMEOUT_PROOFREQUEST - (System.currentTimeMillis() - waitProofRequest) / 1000;
					if (timeout <= 0) throw new InterruptedException("Timeout while waiting for proof.");

					Integer proofWaitGetStateResult = ProofApi.proofGetState(proofHandle).get();
					if (logger.isInfoEnabled()) logger.info("WAIT: For proof handle " + connectionHandle + " got state result " + proofWaitGetStateResult);

					if (proofWaitGetStateResult.intValue() != VcxApi.VCX_OFFERSENT) break;
					Thread.sleep(500);

					Integer proofWaitUpdateStateResult = ProofApi.proofUpdateState(proofHandle).get();
					if (logger.isInfoEnabled()) logger.info("WAIT: For proof handle " + proofHandle + " got proof update state result " + proofWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");
					Thread.sleep(500);
				}

				// get proof

				GetProofResult getProofResult = ProofApi.getProof(proofHandle, connectionHandle).get();
				if (logger.isInfoEnabled()) logger.info("For proof handle " + proofHandle + " and connection handle " + connectionHandle + " got proof result " + getProofResult + " with response data " + getProofResult.getResponse_data() + " and proof state " + getProofResult.getProof_state());

				if (getProofResult.getProof_state() != VcxApi.PROOFSTATE_VERIFIED) {

					return new ResponseEntity<CULedgerMember>(HttpStatus.UNAUTHORIZED);
				}

				// done

				CULedgerMember cuLedgerMember = new CULedgerMember();
				cuLedgerMember.setMemberId(memberId);
				return new ResponseEntity<CULedgerMember>(cuLedgerMember, HttpStatus.OK);
			} catch (Exception ex) {

				if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
				return new ResponseEntity<CULedgerMember>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};

		if ("respond-async".equals(prefer)) {

			String jobId = VcxApiPoll.submit(supplier);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(VcxConfiguration.getApplicationProperty("server.contextPath") + "/" + jobId));
			ResponseEntity<CULedgerMember> response = new ResponseEntity<CULedgerMember>(headers, HttpStatus.ACCEPTED);
			return response;
		} else {

			try { return supplier.get(); } catch (Exception ex) { return new ResponseEntity<CULedgerMember>(HttpStatus.INTERNAL_SERVER_ERROR); }
		}
	}

	/*
	 * Helper methods
	 */

	private static Integer createConnection(String sourceId, CULedgerOnboardingData cuLedgerOnboardingData) throws InterruptedException, ExecutionException, VcxException, ParseException {

		// create connection

		Integer connectionHandle = ConnectionApi.vcxConnectionCreate(sourceId).get();
		if (connectionHandle == null) throw new NullPointerException();

		if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " created connection handle " + connectionHandle);

		// connect

		String phoneNumber = cuLedgerOnboardingData.getPhoneNumber();
		JSONObject jsonConnectionType = new JSONObject();
		jsonConnectionType.put("connection_type", "SMS");
		jsonConnectionType.put("phone", phoneNumber);
		String connectionType = jsonConnectionType.toJSONString();
		String connectionDetails = ConnectionApi.vcxConnectionConnect(connectionHandle, connectionType).get();

		if (logger.isInfoEnabled()) logger.info("For connection handle " + connectionHandle + " and connection type " + connectionType + " created connection details " + connectionDetails);

		// update connection

		Integer connectionUpdateStateResult = ConnectionApi.vcxConnectionUpdateState(connectionHandle).get();
		if (logger.isInfoEnabled()) logger.info("For connection handle " + connectionHandle + " got update state result " + connectionUpdateStateResult);

		// invite details

		String connectionInviteDetails = ConnectionApi.connectionInviteDetails(connectionHandle, 0).get();
		if (logger.isInfoEnabled()) logger.info("For connection handle " + connectionHandle + " got invite details " + connectionInviteDetails);

		JSONObject jsonConnectionInviteDetails = (JSONObject) jsonParser.parse(connectionInviteDetails);
		JSONObject jsonSenderDetail = jsonConnectionInviteDetails == null ? null : (JSONObject) jsonConnectionInviteDetails.get("senderDetail");
		JSONObject jsonAagentKeyDlgProof = jsonSenderDetail == null ? null : (JSONObject) jsonSenderDetail.get("agentKeyDlgProof");
		String agentDID = jsonAagentKeyDlgProof == null ? null : (String) jsonAagentKeyDlgProof.get("agentDID");

		if (logger.isInfoEnabled()) logger.info("For connection invite details " + connectionInviteDetails + " got agent DID " + agentDID);

		// wait

		long waitConnectionInvite = System.currentTimeMillis();
		while (true) {

			long timeout = VcxConfiguration.VCX_TIMEOUT_CONNECTIONINVITE - (System.currentTimeMillis() - waitConnectionInvite) / 1000;
			if (timeout <= 0) throw new InterruptedException("Timeout while waiting for connection.");

			Integer connectionGetStateResult = ConnectionApi.connectionGetState(connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("WAIT: For connection handle " + connectionHandle + " got state result " + connectionGetStateResult);

			if (connectionGetStateResult.intValue() != VcxApi.VCX_OFFERSENT) break;
			Thread.sleep(500);

			Integer connectionWaitUpdateStateResult = ConnectionApi.vcxConnectionUpdateState(connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("WAIT: For connection handle " + connectionHandle + " got update state result " + connectionWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");
			Thread.sleep(500);
		}

		// done

		return connectionHandle;
	}

	private static void createCredential(String sourceId, Integer connectionHandle) throws InterruptedException, ExecutionException, VcxException {

		// create credential

		String credentialData = credentialData(VcxConfiguration.VCX_CREDENTIAL_VALUE);
		String credentialName = VcxConfiguration.VCX_CREDENTIAL_NAME;
		Integer credentialHandle = IssuerApi.issuerCreateCredential(sourceId, VcxInit.credDefId, null, credentialData, credentialName, "0").get();

		if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " and credential def ID " + VcxInit.credDefId + " and credential data " + credentialData + " got credential handle " + credentialHandle);

		// send credential offer

		Integer issuerSendCredentialOfferResult = IssuerApi.issuerSendcredentialOffer(credentialHandle, connectionHandle).get();
		if (logger.isInfoEnabled()) logger.info("For credential handle (offer) " + credentialHandle + " and connection handle " + connectionHandle + " got issuer send credential offer result " + issuerSendCredentialOfferResult);

		// update state

		Integer credentialUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
		if (logger.isInfoEnabled()) logger.info("For credential handle (offer) " + credentialHandle + " got credential update state result " + credentialUpdateStateResult);

		// wait

		long waitCredentialOffer = System.currentTimeMillis();
		while (true) {

			long timeout = VcxConfiguration.VCX_TIMEOUT_CREDENTIALOFFER - (System.currentTimeMillis() - waitCredentialOffer) / 1000;
			if (timeout <= 0) throw new InterruptedException("Timeout while waiting for credential offer.");

			Integer credentialWaitGetStateResult = IssuerApi.issuerCredntialGetState(credentialHandle).get();
			if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle (offer) " + connectionHandle + " got state result " + credentialWaitGetStateResult);

			if (credentialWaitGetStateResult.intValue() != VcxApi.VCX_OFFERSENT) break;
			Thread.sleep(500);

			Integer credentialWaitUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
			if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle (offer) " + credentialHandle + " got credential update state result " + credentialWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");
			Thread.sleep(500);
		}

		// send credential

		Integer issuerSendCredentialResult = IssuerApi.issuerSendCredential(credentialHandle, connectionHandle).get();
		if (logger.isInfoEnabled()) logger.info("For credential handle (send) " + credentialHandle + " and connection handle " + connectionHandle + " got issuer send credential result " + issuerSendCredentialResult);

		// update state

		Integer credentialUpdateStateResult2 = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
		if (logger.isInfoEnabled()) logger.info("For credential handle (send) " + credentialHandle + " got credential update state result " + credentialUpdateStateResult2);

		// wait

		long waitCredentialSend = System.currentTimeMillis();
		while (true) {

			long timeout = VcxConfiguration.VCX_TIMEOUT_CREDENTIALSEND - (System.currentTimeMillis() - waitCredentialSend) / 1000;
			if (timeout <= 0) throw new InterruptedException("Timeout while waiting for credential send.");

			Integer credentialWaitGetStateResult = IssuerApi.issuerCredntialGetState(credentialHandle).get();
			if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle (send) " + credentialHandle + " got state result " + credentialWaitGetStateResult);

			if (credentialWaitGetStateResult.intValue() != VcxApi.VCX_REQUESTRECEIVED) break;
			Thread.sleep(500);

			Integer credentialWaitUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
			if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle (send) " + credentialHandle + " got credential update state result " + credentialWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");
			Thread.sleep(500);
		}
	}

	private static String credentialData(String cuName) {

		String credentialData =
				"{'cu':['" + cuName + "']}";

		return credentialData.replace("'", "\"");
	}

	private static String requestedAttrs() {

		String requestedAttrs = 
				"[{'name':'cu'}]";

		return requestedAttrs.replace("'", "\"");
	}
}
