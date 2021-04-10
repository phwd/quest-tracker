package com.facebook.secure.logger;

public class LoggingConfiguration {
    public static final LoggingLevel DEFAULT_LOGGING_LEVEL = LoggingLevel.ALL;
    private LoggingLevel mLoggingLevel = DEFAULT_LOGGING_LEVEL;

    public enum LoggingLevel {
        OFF(0),
        FAIL_OPEN(1),
        FAIL_CLOSE(2),
        INFO(3),
        ALL(4);
        
        private final int level;

        private LoggingLevel(int i) {
            this.level = i;
        }
    }
}
