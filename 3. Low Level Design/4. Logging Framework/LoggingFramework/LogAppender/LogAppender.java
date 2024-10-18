package LoggingFramework.LogAppender;

import LoggingFramework.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
