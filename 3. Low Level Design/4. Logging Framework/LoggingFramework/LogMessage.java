package LoggingFramework;

public class LogMessage {
    private final LogLevel logLevel;
    private final String message;
    private final long timeStamp;

    public LogMessage(LogLevel logLevel, String message) {
        this.logLevel = logLevel;
        this.message = message;
        this.timeStamp = System.currentTimeMillis();
    }

    // Getters
    public LogLevel getLogLevel() { return logLevel; }
    public String getMessage() { return message; }
    public long getTimeStamp() { return timeStamp; }

    @Override
    public String toString() {
        return "[" + logLevel + "] " + timeStamp + " - " + message;
    }
}
