package com.facebook.secure.logger;

public class LoggingConfiguration {
    public static final LoggingLevel DEFAULT_LOGGING_LEVEL = LoggingLevel.ALL;
    private LoggingLevel mLoggingLevel;

    public enum LoggingLevel {
        OFF(0),
        FAIL_OPEN(1),
        FAIL_CLOSE(2),
        INFO(3),
        ALL(4);
        
        private final int level;

        private LoggingLevel(int level2) {
            this.level = level2;
        }
    }

    public static LoggingConfiguration getInstance(long value) {
        if (value < 0 || value >= ((long) LoggingLevel.values().length)) {
            return new LoggingConfiguration();
        }
        return new LoggingConfiguration(LoggingLevel.values()[(int) value]);
    }

    public LoggingConfiguration() {
        this.mLoggingLevel = DEFAULT_LOGGING_LEVEL;
    }

    public LoggingConfiguration(LoggingLevel loggingLevel) {
        this.mLoggingLevel = loggingLevel;
    }

    public synchronized LoggingLevel getLoggingLevel() {
        return this.mLoggingLevel;
    }

    public synchronized void setLoggingLevel(LoggingLevel loggingLevel) {
        this.mLoggingLevel = loggingLevel;
    }

    public synchronized boolean canLog(LoggingLevel requestLoggingLevel) {
        return this.mLoggingLevel != LoggingLevel.OFF && this.mLoggingLevel.level >= requestLoggingLevel.level;
    }
}
