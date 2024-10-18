package LoggingFramework;

import LoggingFramework.LogAppender.FileAppender;

public class LoggingFrameworkDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        // Logging with default configuration
        logger.info("This is information message");

        // Multi Threading
        Thread thread1 = new Thread(() -> logger.error("Thread 1: Error message"));
        Thread thread2 = new Thread(() -> logger.info("Thread 2: Info message"));
        thread2.start();
        thread1.start();

        // Changing log level and appender
        LoggerConfig config = new LoggerConfig(LogLevel.DEBUG, new FileAppender("app.log"));
        logger.setConfig(config);

        logger.debug("This is a debug message");
        logger.info("This is a info message");
    }
}
