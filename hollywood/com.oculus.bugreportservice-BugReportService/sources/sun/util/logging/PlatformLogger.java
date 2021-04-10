package sun.util.logging;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlatformLogger {
    private static final Level DEFAULT_LEVEL = Level.INFO;
    private static Map loggers = new HashMap();
    private static boolean loggingEnabled = ((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
        /* class sun.util.logging.PlatformLogger.AnonymousClass1 */

        @Override // java.security.PrivilegedAction
        public Boolean run() {
            return Boolean.valueOf((System.getProperty("java.util.logging.config.class") == null && System.getProperty("java.util.logging.config.file") == null) ? false : true);
        }
    })).booleanValue();
    private volatile JavaLoggerProxy javaLoggerProxy;
    private volatile LoggerProxy loggerProxy;

    public enum Level {
        ALL,
        FINEST,
        FINER,
        FINE,
        CONFIG,
        INFO,
        WARNING,
        SEVERE,
        OFF;
        
        private static final int[] LEVEL_VALUES = {Integer.MIN_VALUE, 300, 400, 500, 700, 800, 900, 1000, Integer.MAX_VALUE};
        Object javaLevel;

        public int intValue() {
            return LEVEL_VALUES[ordinal()];
        }
    }

    public static synchronized PlatformLogger getLogger(String str) {
        PlatformLogger platformLogger;
        synchronized (PlatformLogger.class) {
            platformLogger = null;
            WeakReference weakReference = (WeakReference) loggers.get(str);
            if (weakReference != null) {
                platformLogger = (PlatformLogger) weakReference.get();
            }
            if (platformLogger == null) {
                platformLogger = new PlatformLogger(str);
                loggers.put(str, new WeakReference(platformLogger));
            }
        }
        return platformLogger;
    }

    public static synchronized void redirectPlatformLoggers() {
        synchronized (PlatformLogger.class) {
            if (!loggingEnabled) {
                if (LoggingSupport.isAvailable()) {
                    loggingEnabled = true;
                    for (Map.Entry entry : loggers.entrySet()) {
                        PlatformLogger platformLogger = (PlatformLogger) ((WeakReference) entry.getValue()).get();
                        if (platformLogger != null) {
                            platformLogger.redirectToJavaLoggerProxy();
                        }
                    }
                }
            }
        }
    }

    private void redirectToJavaLoggerProxy() {
        LoggerProxy loggerProxy2 = this.loggerProxy;
        DefaultLoggerProxy.class.cast(loggerProxy2);
        DefaultLoggerProxy defaultLoggerProxy = (DefaultLoggerProxy) loggerProxy2;
        JavaLoggerProxy javaLoggerProxy2 = new JavaLoggerProxy(defaultLoggerProxy.name, defaultLoggerProxy.level);
        this.javaLoggerProxy = javaLoggerProxy2;
        this.loggerProxy = javaLoggerProxy2;
    }

    private PlatformLogger(String str) {
        if (loggingEnabled) {
            JavaLoggerProxy javaLoggerProxy2 = new JavaLoggerProxy(str);
            this.javaLoggerProxy = javaLoggerProxy2;
            this.loggerProxy = javaLoggerProxy2;
            return;
        }
        this.loggerProxy = new DefaultLoggerProxy(str);
    }

    public boolean isLoggable(Level level) {
        if (level != null) {
            JavaLoggerProxy javaLoggerProxy2 = this.javaLoggerProxy;
            return javaLoggerProxy2 != null ? javaLoggerProxy2.isLoggable(level) : this.loggerProxy.isLoggable(level);
        }
        throw new NullPointerException();
    }

    public void finest(String str) {
        this.loggerProxy.doLog(Level.FINEST, str);
    }

    /* access modifiers changed from: private */
    public static abstract class LoggerProxy {
        final String name;

        /* access modifiers changed from: package-private */
        public abstract void doLog(Level level, String str);

        /* access modifiers changed from: package-private */
        public abstract boolean isLoggable(Level level);

        protected LoggerProxy(String str) {
            this.name = str;
        }
    }

    /* access modifiers changed from: private */
    public static final class DefaultLoggerProxy extends LoggerProxy {
        private static final String formatString = LoggingSupport.getSimpleFormat(false);
        private Date date = new Date();
        volatile Level effectiveLevel = deriveEffectiveLevel(null);
        volatile Level level = null;

        private static PrintStream outputStream() {
            return System.err;
        }

        DefaultLoggerProxy(String str) {
            super(str);
        }

        /* access modifiers changed from: package-private */
        @Override // sun.util.logging.PlatformLogger.LoggerProxy
        public void doLog(Level level2, String str) {
            if (isLoggable(level2)) {
                outputStream().print(format(level2, str, null));
            }
        }

        /* access modifiers changed from: package-private */
        @Override // sun.util.logging.PlatformLogger.LoggerProxy
        public boolean isLoggable(Level level2) {
            Level level3 = this.effectiveLevel;
            return level2.intValue() >= level3.intValue() && level3 != Level.OFF;
        }

        private Level deriveEffectiveLevel(Level level2) {
            return level2 == null ? PlatformLogger.DEFAULT_LEVEL : level2;
        }

        private synchronized String format(Level level2, String str, Throwable th) {
            String str2;
            this.date.setTime(System.currentTimeMillis());
            str2 = "";
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                printWriter.println();
                th.printStackTrace(printWriter);
                printWriter.close();
                str2 = stringWriter.toString();
            }
            return String.format(formatString, this.date, getCallerInfo(), this.name, level2.name(), str, str2);
        }

        private String getCallerInfo() {
            String str;
            String str2;
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            int length = stackTrace.length;
            boolean z = true;
            int i = 0;
            while (true) {
                str = null;
                if (i >= length) {
                    str2 = null;
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                String className = stackTraceElement.getClassName();
                if (z) {
                    if (className.equals("sun.util.logging.PlatformLogger")) {
                        z = false;
                    }
                } else if (!className.equals("sun.util.logging.PlatformLogger")) {
                    str2 = stackTraceElement.getMethodName();
                    str = className;
                    break;
                }
                i++;
            }
            if (str == null) {
                return this.name;
            }
            return str + " " + str2;
        }
    }

    /* access modifiers changed from: private */
    public static final class JavaLoggerProxy extends LoggerProxy {
        private final Object javaLogger;

        static {
            Level[] values = Level.values();
            for (Level level : values) {
                level.javaLevel = LoggingSupport.parseLevel(level.name());
            }
        }

        JavaLoggerProxy(String str) {
            this(str, null);
        }

        JavaLoggerProxy(String str, Level level) {
            super(str);
            this.javaLogger = LoggingSupport.getLogger(str);
            if (level != null) {
                LoggingSupport.setLevel(this.javaLogger, level.javaLevel);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // sun.util.logging.PlatformLogger.LoggerProxy
        public void doLog(Level level, String str) {
            LoggingSupport.log(this.javaLogger, level.javaLevel, str);
        }

        /* access modifiers changed from: package-private */
        @Override // sun.util.logging.PlatformLogger.LoggerProxy
        public boolean isLoggable(Level level) {
            return LoggingSupport.isLoggable(this.javaLogger, level.javaLevel);
        }
    }
}
