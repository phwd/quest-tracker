package java.util.logging;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/* access modifiers changed from: package-private */
public class Logging implements LoggingMXBean {
    private static String EMPTY_STRING = "";
    private static LogManager logManager = LogManager.getLogManager();

    Logging() {
    }

    @Override // java.util.logging.LoggingMXBean
    public List<String> getLoggerNames() {
        Enumeration<String> loggers = logManager.getLoggerNames();
        ArrayList<String> array = new ArrayList<>();
        while (loggers.hasMoreElements()) {
            array.add(loggers.nextElement());
        }
        return array;
    }

    @Override // java.util.logging.LoggingMXBean
    public String getLoggerLevel(String loggerName) {
        Logger l = logManager.getLogger(loggerName);
        if (l == null) {
            return null;
        }
        Level level = l.getLevel();
        if (level == null) {
            return EMPTY_STRING;
        }
        return level.getLevelName();
    }

    @Override // java.util.logging.LoggingMXBean
    public void setLoggerLevel(String loggerName, String levelName) {
        if (loggerName != null) {
            Logger logger = logManager.getLogger(loggerName);
            if (logger != null) {
                Level level = null;
                if (levelName == null || (level = Level.findLevel(levelName)) != null) {
                    logger.setLevel(level);
                    return;
                }
                throw new IllegalArgumentException("Unknown level \"" + levelName + "\"");
            }
            throw new IllegalArgumentException("Logger " + loggerName + "does not exist");
        }
        throw new NullPointerException("loggerName is null");
    }

    @Override // java.util.logging.LoggingMXBean
    public String getParentLoggerName(String loggerName) {
        Logger l = logManager.getLogger(loggerName);
        if (l == null) {
            return null;
        }
        Logger p = l.getParent();
        if (p == null) {
            return EMPTY_STRING;
        }
        return p.getName();
    }
}
