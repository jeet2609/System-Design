package LoggingFramework;

import LoggingFramework.LogAppender.ConsoleAppender;

public class Logger {
    private static final Logger instance = new Logger();
    private LoggerConfig loggerConfig;

    private Logger() {
        loggerConfig = new LoggerConfig(LogLevel.INFO, new ConsoleAppender());
    }

    public static Logger getInstance() {
        return instance;
    }

    public void setConfig(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    private synchronized void log(LogLevel logLevel, String message) {
        if(logLevel.ordinal() >= loggerConfig.getLogLevel().ordinal()) {
            LogMessage logMessage = new LogMessage(logLevel, message);
            loggerConfig.getLogAppender().append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}
