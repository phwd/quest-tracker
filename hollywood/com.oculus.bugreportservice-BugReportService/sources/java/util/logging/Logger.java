package java.util.logging;

import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.LogManager;
import sun.reflect.Reflection;

public class Logger {
    private static final LoggerBundle NO_RESOURCE_BUNDLE = new LoggerBundle(null, null);
    private static final LoggerBundle SYSTEM_BUNDLE = new LoggerBundle("sun.util.logging.resources.logging", null);
    private static final Handler[] emptyHandlers = new Handler[0];
    public static final Logger global = new Logger("global");
    private static final int offValue = Level.OFF.intValue();
    private static final Object treeLock = new Object();
    private boolean anonymous;
    private WeakReference callersClassLoaderRef;
    private ResourceBundle catalog;
    private Locale catalogLocale;
    private String catalogName;
    private volatile Filter filter;
    private final CopyOnWriteArrayList handlers = new CopyOnWriteArrayList();
    private final boolean isSystemLogger;
    private ArrayList kids;
    private volatile Level levelObject;
    private volatile int levelValue;
    private volatile LoggerBundle loggerBundle = NO_RESOURCE_BUNDLE;
    private volatile LogManager manager;
    private String name;
    private volatile Logger parent;
    private volatile boolean useParentHandlers = true;

    /* access modifiers changed from: private */
    public static final class LoggerBundle {
        final String resourceBundleName;
        final ResourceBundle userBundle;

        private LoggerBundle(String str, ResourceBundle resourceBundle) {
            this.resourceBundleName = str;
            this.userBundle = resourceBundle;
        }

        /* access modifiers changed from: package-private */
        public boolean isSystemBundle() {
            return "sun.util.logging.resources.logging".equals(this.resourceBundleName);
        }

        static LoggerBundle get(String str, ResourceBundle resourceBundle) {
            if (str == null && resourceBundle == null) {
                return Logger.NO_RESOURCE_BUNDLE;
            }
            if (!"sun.util.logging.resources.logging".equals(str) || resourceBundle != null) {
                return new LoggerBundle(str, resourceBundle);
            }
            return Logger.SYSTEM_BUNDLE;
        }
    }

    Logger(String str, String str2, Class cls, LogManager logManager, boolean z) {
        this.manager = logManager;
        this.isSystemLogger = z;
        setupResourceInfo(str2, cls);
        this.name = str;
        this.levelValue = Level.INFO.intValue();
    }

    private void setCallersClassLoaderRef(Class cls) {
        ClassLoader classLoader = cls != null ? cls.getClassLoader() : null;
        if (classLoader != null) {
            this.callersClassLoaderRef = new WeakReference(classLoader);
        }
    }

    private ClassLoader getCallersClassLoader() {
        WeakReference weakReference = this.callersClassLoaderRef;
        if (weakReference != null) {
            return (ClassLoader) weakReference.get();
        }
        return null;
    }

    private Logger(String str) {
        this.name = str;
        this.isSystemLogger = true;
        this.levelValue = Level.INFO.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setLogManager(LogManager logManager) {
        this.manager = logManager;
    }

    private void checkPermission() {
        if (!this.anonymous) {
            if (this.manager == null) {
                this.manager = LogManager.getLogManager();
            }
            this.manager.checkPermission();
        }
    }

    /* access modifiers changed from: private */
    public static class SystemLoggerHelper {
        static boolean disableCallerCheck = getBooleanProperty("sun.util.logging.disableCallerCheck");

        private static boolean getBooleanProperty(final String str) {
            return Boolean.valueOf((String) AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.logging.Logger.SystemLoggerHelper.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public String run() {
                    return System.getProperty(String.this);
                }
            })).booleanValue();
        }
    }

    private static Logger demandLogger(String str, String str2, Class cls) {
        LogManager logManager = LogManager.getLogManager();
        if (System.getSecurityManager() == null || SystemLoggerHelper.disableCallerCheck || cls.getClassLoader() != null) {
            return logManager.demandLogger(str, str2, cls);
        }
        return logManager.demandSystemLogger(str, str2);
    }

    public static Logger getLogger(String str) {
        return demandLogger(str, null, Reflection.getCallerClass());
    }

    public ResourceBundle getResourceBundle() {
        return findResourceBundle(getResourceBundleName(), true);
    }

    public String getResourceBundleName() {
        return this.loggerBundle.resourceBundleName;
    }

    public void log(LogRecord logRecord) {
        Handler[] handlerArr;
        boolean z;
        if (isLoggable(logRecord.getLevel())) {
            Filter filter2 = this.filter;
            if (filter2 == null || filter2.isLoggable(logRecord)) {
                Logger logger = this;
                while (logger != null) {
                    if (this.isSystemLogger) {
                        handlerArr = logger.accessCheckedHandlers();
                    } else {
                        handlerArr = logger.getHandlers();
                    }
                    for (Handler handler : handlerArr) {
                        handler.publish(logRecord);
                    }
                    if (this.isSystemLogger) {
                        z = logger.useParentHandlers;
                    } else {
                        z = logger.getUseParentHandlers();
                    }
                    if (z) {
                        logger = this.isSystemLogger ? logger.parent : logger.getParent();
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void doLog(LogRecord logRecord) {
        logRecord.setLoggerName(this.name);
        LoggerBundle effectiveLoggerBundle = getEffectiveLoggerBundle();
        ResourceBundle resourceBundle = effectiveLoggerBundle.userBundle;
        String str = effectiveLoggerBundle.resourceBundleName;
        if (!(str == null || resourceBundle == null)) {
            logRecord.setResourceBundleName(str);
            logRecord.setResourceBundle(resourceBundle);
        }
        log(logRecord);
    }

    public void log(Level level, String str) {
        if (isLoggable(level)) {
            doLog(new LogRecord(level, str));
        }
    }

    public void log(Level level, String str, Throwable th) {
        if (isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, str);
            logRecord.setThrown(th);
            doLog(logRecord);
        }
    }

    public void fine(String str) {
        log(Level.FINE, str);
    }

    public void setLevel(Level level) {
        checkPermission();
        synchronized (treeLock) {
            this.levelObject = level;
            updateEffectiveLevel();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isLevelInitialized() {
        return this.levelObject != null;
    }

    public boolean isLoggable(Level level) {
        return level.intValue() >= this.levelValue && this.levelValue != offValue;
    }

    public String getName() {
        return this.name;
    }

    public void addHandler(Handler handler) {
        handler.getClass();
        checkPermission();
        this.handlers.add(handler);
    }

    public void removeHandler(Handler handler) {
        checkPermission();
        if (handler != null) {
            this.handlers.remove(handler);
        }
    }

    public Handler[] getHandlers() {
        return accessCheckedHandlers();
    }

    /* access modifiers changed from: package-private */
    public Handler[] accessCheckedHandlers() {
        return (Handler[]) this.handlers.toArray(emptyHandlers);
    }

    public void setUseParentHandlers(boolean z) {
        checkPermission();
        this.useParentHandlers = z;
    }

    public boolean getUseParentHandlers() {
        return this.useParentHandlers;
    }

    private static ResourceBundle findSystemResourceBundle(final Locale locale) {
        return (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction() {
            /* class java.util.logging.Logger.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public ResourceBundle run() {
                try {
                    return ResourceBundle.getBundle("sun.util.logging.resources.logging", Locale.this, ClassLoader.getSystemClassLoader());
                } catch (MissingResourceException e) {
                    throw new InternalError(e.toString());
                }
            }
        });
    }

    private synchronized ResourceBundle findResourceBundle(String str, boolean z) {
        if (str == null) {
            return null;
        }
        Locale locale = Locale.getDefault();
        LoggerBundle loggerBundle2 = this.loggerBundle;
        if (loggerBundle2.userBundle != null && str.equals(loggerBundle2.resourceBundleName)) {
            return loggerBundle2.userBundle;
        } else if (this.catalog != null && locale.equals(this.catalogLocale) && str.equals(this.catalogName)) {
            return this.catalog;
        } else if (str.equals("sun.util.logging.resources.logging")) {
            this.catalog = findSystemResourceBundle(locale);
            this.catalogName = str;
            this.catalogLocale = locale;
            return this.catalog;
        } else {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = ClassLoader.getSystemClassLoader();
            }
            try {
                this.catalog = ResourceBundle.getBundle(str, locale, contextClassLoader);
                this.catalogName = str;
                this.catalogLocale = locale;
                return this.catalog;
            } catch (MissingResourceException unused) {
                if (!z) {
                    return null;
                }
                ClassLoader callersClassLoader = getCallersClassLoader();
                if (callersClassLoader == null || callersClassLoader == contextClassLoader) {
                    return null;
                }
                try {
                    this.catalog = ResourceBundle.getBundle(str, locale, callersClassLoader);
                    this.catalogName = str;
                    this.catalogLocale = locale;
                    return this.catalog;
                } catch (MissingResourceException unused2) {
                    return null;
                }
            }
        }
    }

    private synchronized void setupResourceInfo(String str, Class cls) {
        LoggerBundle loggerBundle2 = this.loggerBundle;
        if (loggerBundle2.resourceBundleName != null) {
            if (!loggerBundle2.resourceBundleName.equals(str)) {
                throw new IllegalArgumentException(loggerBundle2.resourceBundleName + " != " + str);
            }
        } else if (str != null) {
            setCallersClassLoaderRef(cls);
            if (this.isSystemLogger && getCallersClassLoader() != null) {
                checkPermission();
            }
            if (findResourceBundle(str, true) != null) {
                this.loggerBundle = LoggerBundle.get(str, null);
                return;
            }
            this.callersClassLoaderRef = null;
            throw new MissingResourceException("Can't find " + str + " bundle", str, "");
        }
    }

    public Logger getParent() {
        return this.parent;
    }

    public void setParent(Logger logger) {
        if (logger != null) {
            if (this.manager == null) {
                this.manager = LogManager.getLogManager();
            }
            this.manager.checkPermission();
            doSetParent(logger);
            return;
        }
        throw new NullPointerException();
    }

    private void doSetParent(Logger logger) {
        synchronized (treeLock) {
            LogManager.LoggerWeakRef loggerWeakRef = null;
            if (this.parent != null) {
                Iterator it = this.parent.kids.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    LogManager.LoggerWeakRef loggerWeakRef2 = (LogManager.LoggerWeakRef) it.next();
                    if (((Logger) loggerWeakRef2.get()) == this) {
                        it.remove();
                        loggerWeakRef = loggerWeakRef2;
                        break;
                    }
                }
            }
            this.parent = logger;
            if (this.parent.kids == null) {
                this.parent.kids = new ArrayList(2);
            }
            if (loggerWeakRef == null) {
                LogManager logManager = this.manager;
                Objects.requireNonNull(logManager);
                loggerWeakRef = new LogManager.LoggerWeakRef(this);
            }
            loggerWeakRef.setParentRef(new WeakReference(this.parent));
            this.parent.kids.add(loggerWeakRef);
            updateEffectiveLevel();
        }
    }

    /* access modifiers changed from: package-private */
    public final void removeChildLogger(LogManager.LoggerWeakRef loggerWeakRef) {
        synchronized (treeLock) {
            Iterator it = this.kids.iterator();
            while (it.hasNext()) {
                if (((LogManager.LoggerWeakRef) it.next()) == loggerWeakRef) {
                    it.remove();
                    return;
                }
            }
        }
    }

    private void updateEffectiveLevel() {
        int i;
        if (this.levelObject != null) {
            i = this.levelObject.intValue();
        } else if (this.parent != null) {
            i = this.parent.levelValue;
        } else {
            i = Level.INFO.intValue();
        }
        if (this.levelValue != i) {
            this.levelValue = i;
            if (this.kids != null) {
                for (int i2 = 0; i2 < this.kids.size(); i2++) {
                    Logger logger = (Logger) ((LogManager.LoggerWeakRef) this.kids.get(i2)).get();
                    if (logger != null) {
                        logger.updateEffectiveLevel();
                    }
                }
            }
        }
    }

    private LoggerBundle getEffectiveLoggerBundle() {
        String str;
        LoggerBundle loggerBundle2 = this.loggerBundle;
        if (loggerBundle2.isSystemBundle()) {
            return SYSTEM_BUNDLE;
        }
        ResourceBundle resourceBundle = getResourceBundle();
        if (resourceBundle != null && resourceBundle == loggerBundle2.userBundle) {
            return loggerBundle2;
        }
        if (resourceBundle != null) {
            return LoggerBundle.get(getResourceBundleName(), resourceBundle);
        }
        Logger logger = this.parent;
        while (logger != null) {
            LoggerBundle loggerBundle3 = logger.loggerBundle;
            if (loggerBundle3.isSystemBundle()) {
                return SYSTEM_BUNDLE;
            }
            if (loggerBundle3.userBundle != null) {
                return loggerBundle3;
            }
            if (this.isSystemLogger) {
                str = logger.isSystemLogger ? loggerBundle3.resourceBundleName : null;
            } else {
                str = logger.getResourceBundleName();
            }
            if (str != null) {
                return LoggerBundle.get(str, findResourceBundle(str, true));
            }
            logger = this.isSystemLogger ? logger.parent : logger.getParent();
        }
        return NO_RESOURCE_BUNDLE;
    }
}
