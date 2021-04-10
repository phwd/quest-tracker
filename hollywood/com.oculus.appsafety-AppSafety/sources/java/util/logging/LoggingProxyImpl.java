package java.util.logging;

import java.util.List;
import sun.util.logging.LoggingProxy;

class LoggingProxyImpl implements LoggingProxy {
    static final LoggingProxy INSTANCE = new LoggingProxyImpl();

    private LoggingProxyImpl() {
    }

    @Override // sun.util.logging.LoggingProxy
    public Object getLogger(String name) {
        return Logger.getPlatformLogger(name);
    }

    @Override // sun.util.logging.LoggingProxy
    public Object getLevel(Object logger) {
        return ((Logger) logger).getLevel();
    }

    @Override // sun.util.logging.LoggingProxy
    public void setLevel(Object logger, Object newLevel) {
        ((Logger) logger).setLevel((Level) newLevel);
    }

    @Override // sun.util.logging.LoggingProxy
    public boolean isLoggable(Object logger, Object level) {
        return ((Logger) logger).isLoggable((Level) level);
    }

    @Override // sun.util.logging.LoggingProxy
    public void log(Object logger, Object level, String msg) {
        ((Logger) logger).log((Level) level, msg);
    }

    @Override // sun.util.logging.LoggingProxy
    public void log(Object logger, Object level, String msg, Throwable t) {
        ((Logger) logger).log((Level) level, msg, t);
    }

    @Override // sun.util.logging.LoggingProxy
    public void log(Object logger, Object level, String msg, Object... params) {
        ((Logger) logger).log((Level) level, msg, params);
    }

    @Override // sun.util.logging.LoggingProxy
    public List<String> getLoggerNames() {
        return LogManager.getLoggingMXBean().getLoggerNames();
    }

    @Override // sun.util.logging.LoggingProxy
    public String getLoggerLevel(String loggerName) {
        return LogManager.getLoggingMXBean().getLoggerLevel(loggerName);
    }

    @Override // sun.util.logging.LoggingProxy
    public void setLoggerLevel(String loggerName, String levelName) {
        LogManager.getLoggingMXBean().setLoggerLevel(loggerName, levelName);
    }

    @Override // sun.util.logging.LoggingProxy
    public String getParentLoggerName(String loggerName) {
        return LogManager.getLoggingMXBean().getParentLoggerName(loggerName);
    }

    @Override // sun.util.logging.LoggingProxy
    public Object parseLevel(String levelName) {
        Level level = Level.findLevel(levelName);
        if (level != null) {
            return level;
        }
        throw new IllegalArgumentException("Unknown level \"" + levelName + "\"");
    }

    @Override // sun.util.logging.LoggingProxy
    public String getLevelName(Object level) {
        return ((Level) level).getLevelName();
    }

    @Override // sun.util.logging.LoggingProxy
    public int getLevelValue(Object level) {
        return ((Level) level).intValue();
    }

    @Override // sun.util.logging.LoggingProxy
    public String getProperty(String key) {
        return LogManager.getLogManager().getProperty(key);
    }
}
