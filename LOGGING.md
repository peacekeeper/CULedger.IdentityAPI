#Logging/Analytics
The CULedger Identity API utilizes the SLF4J logging facade wrapping the log4j libraries.
Logs are currently written to the console and also a log file which can be found in the root of the project as log4j.log.  In the Docker environment, it is located at `/opt/culedger-identityapi`.

Settings to control what is logged can be found at `src/main/resources/log4j.properties` and are currently defined as: 

```java
log4j.rootLogger=DEBUG, STDOUT, FILE

log4j.appender.STDOUT=org.apache.log4j.ConsoleAppender
log4j.appender.STDOUT.layout=org.apache.log4j.PatternLayout
log4j.appender.STDOUT.layout.ConversionPattern=%d{HH:mm:ss,SSS} - %X{mdcData} %5p [%c] - %m%n

log4j.appender.FILE=org.apache.log4j.FileAppender
log4j.appender.FILE.File=log4j.log
log4j.appender.FILE.layout=org.apache.log4j.PatternLayout
log4j.appender.FILE.layout.ConversionPattern=%d{HH:mm:ss,SSS} - %X{mdcData} %5p [%c] - %m%n

log4j.logger.org.apache=INFO, STDOUT, FILE
log4j.logger.org.eclipse=INFO, STDOUT, FILE
log4j.logger.org.mortbay=INFO, STDOUT, FILE
log4j.logger.org.springframework=INFO, STDOUT, FILE
log4j.logger.springfox=INFO, STDOUT, FILE
log4j.logger.io.swagger=DEBUG, STDOUT, FILE
```

One improvement to the logging set up that I usually implement on projects is that of adding context to the log so that sets of entries can be grouped together to provide a better understanding of what is happening when problems arise.  This is especially helpful when debugging servers that handle multiple requests.  SLF4J supports this context this it's MDC feature.  I have added the necessary configuration to enable this for our logs.  

One draw back to using this feature is that it is thread based so that when servers implement thread pooling to support callbacks or other features, it may not provide the data we're looking for or it may simply provide the wrong context in the event of a recycled thread.

So we should consider this addition in light of these limitations and proceed accordingly.
