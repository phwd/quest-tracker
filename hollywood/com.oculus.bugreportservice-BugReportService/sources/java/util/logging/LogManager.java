package java.util.logging;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.WeakHashMap;
import sun.util.logging.PlatformLogger;

public class LogManager {
    private static final Level defaultLevel = Level.INFO;
    private static LoggingMXBean loggingMXBean = null;
    private static final LogManager manager = ((LogManager) AccessController.doPrivileged(new PrivilegedAction() {
        /* class java.util.logging.LogManager.AnonymousClass1 */

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
        /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
        @Override // java.security.PrivilegedAction
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.logging.LogManager run() {
            /*
                r5 = this;
                r5 = 0
                java.lang.String r0 = "java.util.logging.manager"
                java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ Exception -> 0x0017 }
                if (r0 == 0) goto L_0x0037
                java.lang.Class r1 = java.util.logging.LogManager.access$100(r0)     // Catch:{ Exception -> 0x0015 }
                java.lang.Object r1 = r1.newInstance()     // Catch:{ Exception -> 0x0015 }
                java.util.logging.LogManager r1 = (java.util.logging.LogManager) r1     // Catch:{ Exception -> 0x0015 }
                r5 = r1
                goto L_0x0037
            L_0x0015:
                r1 = move-exception
                goto L_0x0019
            L_0x0017:
                r1 = move-exception
                r0 = r5
            L_0x0019:
                java.io.PrintStream r2 = java.lang.System.err
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Could not load Logmanager \""
                r3.append(r4)
                r3.append(r0)
                java.lang.String r0 = "\""
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2.println(r0)
                r1.printStackTrace()
            L_0x0037:
                if (r5 != 0) goto L_0x003e
                java.util.logging.LogManager r5 = new java.util.logging.LogManager
                r5.<init>()
            L_0x003e:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.logging.LogManager.AnonymousClass1.run():java.util.logging.LogManager");
        }
    }));
    private WeakHashMap contextsMap;
    private final Permission controlPermission;
    private boolean deathImminent;
    private volatile boolean initializationDone;
    private boolean initializedCalled;
    private boolean initializedGlobalHandlers;
    private final Map listenerMap;
    private final ReferenceQueue loggerRefQueue;
    private volatile Properties props;
    private volatile boolean readPrimordialConfiguration;
    private volatile Logger rootLogger;
    private final LoggerContext systemContext;
    private final LoggerContext userContext;

    private class Cleaner extends Thread {
        private Cleaner() {
            setContextClassLoader(null);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogManager unused = LogManager.manager;
            synchronized (LogManager.this) {
                LogManager.this.deathImminent = true;
                LogManager.this.initializedGlobalHandlers = true;
            }
            LogManager.this.reset();
        }
    }

    protected LogManager() {
        this(checkSubclassPermissions());
    }

    private LogManager(Void r3) {
        this.props = new Properties();
        this.listenerMap = new HashMap();
        this.systemContext = new SystemLoggerContext();
        this.userContext = new LoggerContext();
        this.initializedGlobalHandlers = true;
        this.initializedCalled = false;
        this.initializationDone = false;
        this.contextsMap = null;
        this.loggerRefQueue = new ReferenceQueue();
        this.controlPermission = new LoggingPermission("control", null);
        try {
            Runtime.getRuntime().addShutdownHook(new Cleaner());
        } catch (IllegalStateException unused) {
        }
    }

    private static Void checkSubclassPermissions() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            return null;
        }
        securityManager.checkPermission(new RuntimePermission("shutdownHooks"));
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final void ensureLogManagerInitialized() {
        if (!this.initializationDone && this == manager) {
            synchronized (this) {
                if (!(this.initializedCalled)) {
                    if (!this.initializationDone) {
                        this.initializedCalled = true;
                        try {
                            AccessController.doPrivileged(new PrivilegedAction() {
                                /* class java.util.logging.LogManager.AnonymousClass2 */

                                @Override // java.security.PrivilegedAction
                                public Object run() {
                                    this.readPrimordialConfiguration();
                                    LogManager logManager = this;
                                    Objects.requireNonNull(logManager);
                                    logManager.rootLogger = new RootLogger();
                                    LogManager logManager2 = this;
                                    logManager2.addLogger(logManager2.rootLogger);
                                    if (!this.rootLogger.isLevelInitialized()) {
                                        this.rootLogger.setLevel(LogManager.defaultLevel);
                                    }
                                    this.addLogger(Logger.global);
                                    return null;
                                }
                            });
                        } finally {
                            this.initializationDone = true;
                        }
                    }
                }
            }
        }
    }

    public static LogManager getLogManager() {
        LogManager logManager = manager;
        if (logManager != null) {
            logManager.ensureLogManagerInitialized();
        }
        return manager;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void readPrimordialConfiguration() {
        if (!this.readPrimordialConfiguration) {
            synchronized (this) {
                if (!this.readPrimordialConfiguration) {
                    if (System.out != null) {
                        this.readPrimordialConfiguration = true;
                        try {
                            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                                /* class java.util.logging.LogManager.AnonymousClass3 */

                                @Override // java.security.PrivilegedExceptionAction
                                public Void run() {
                                    LogManager.this.readConfiguration();
                                    PlatformLogger.redirectPlatformLoggers();
                                    return null;
                                }
                            });
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
    }

    private LoggerContext getUserContext() {
        return this.userContext;
    }

    /* access modifiers changed from: package-private */
    public final LoggerContext getSystemContext() {
        return this.systemContext;
    }

    private List contexts() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSystemContext());
        arrayList.add(getUserContext());
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Logger demandLogger(String str, String str2, Class cls) {
        Logger logger = getLogger(str);
        if (logger == null) {
            Logger logger2 = new Logger(str, str2, cls, this, false);
            while (!addLogger(logger2)) {
                logger = getLogger(str);
                if (logger != null) {
                }
            }
            return logger2;
        }
        return logger;
    }

    /* access modifiers changed from: package-private */
    public Logger demandSystemLogger(String str, String str2) {
        final Logger logger;
        final Logger demandLogger = getSystemContext().demandLogger(str, str2);
        do {
            if (addLogger(demandLogger)) {
                logger = demandLogger;
                continue;
            } else {
                logger = getLogger(str);
                continue;
            }
        } while (logger == null);
        if (logger != demandLogger && demandLogger.accessCheckedHandlers().length == 0) {
            AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.logging.LogManager.AnonymousClass4 */

                @Override // java.security.PrivilegedAction
                public Void run() {
                    for (Handler handler : logger.accessCheckedHandlers()) {
                        demandLogger.addHandler(handler);
                    }
                    return null;
                }
            });
        }
        return demandLogger;
    }

    /* access modifiers changed from: private */
    public static Class getClassInstance(String str) {
        try {
            return ClassLoader.getSystemClassLoader().loadClass(str);
        } catch (ClassNotFoundException unused) {
            return Thread.currentThread().getContextClassLoader().loadClass(str);
        }
    }

    /* access modifiers changed from: package-private */
    public class LoggerContext {
        private final Hashtable namedLoggers;
        private final LogNode root;

        private LoggerContext() {
            this.namedLoggers = new Hashtable();
            this.root = new LogNode(null, this);
        }

        /* access modifiers changed from: package-private */
        public final boolean requiresDefaultLoggers() {
            boolean z = getOwner() == LogManager.manager;
            if (z) {
                getOwner().ensureLogManagerInitialized();
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public final LogManager getOwner() {
            return LogManager.this;
        }

        /* access modifiers changed from: package-private */
        public final Logger getRootLogger() {
            return getOwner().rootLogger;
        }

        /* access modifiers changed from: package-private */
        public final Logger getGlobalLogger() {
            return Logger.global;
        }

        /* access modifiers changed from: package-private */
        public Logger demandLogger(String str, String str2) {
            return getOwner().demandLogger(str, str2, null);
        }

        private void ensureInitialized() {
            if (requiresDefaultLoggers()) {
                ensureDefaultLogger(getRootLogger());
                ensureDefaultLogger(getGlobalLogger());
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized Logger findLogger(String str) {
            ensureInitialized();
            LoggerWeakRef loggerWeakRef = (LoggerWeakRef) this.namedLoggers.get(str);
            if (loggerWeakRef == null) {
                return null;
            }
            Logger logger = (Logger) loggerWeakRef.get();
            if (logger == null) {
                loggerWeakRef.dispose();
            }
            return logger;
        }

        private void ensureAllDefaultLoggers(Logger logger) {
            if (requiresDefaultLoggers()) {
                String name = logger.getName();
                if (!name.isEmpty()) {
                    ensureDefaultLogger(getRootLogger());
                    if (!"global".equals(name)) {
                        ensureDefaultLogger(getGlobalLogger());
                    }
                }
            }
        }

        private void ensureDefaultLogger(Logger logger) {
            if (requiresDefaultLoggers() && logger != null) {
                if ((logger == Logger.global || logger == LogManager.this.rootLogger) && !this.namedLoggers.containsKey(logger.getName())) {
                    addLocalLogger(logger, false);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean addLocalLogger(Logger logger) {
            return addLocalLogger(logger, requiresDefaultLoggers());
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean addLocalLogger(Logger logger, boolean z) {
            if (z) {
                ensureAllDefaultLoggers(logger);
            }
            String name = logger.getName();
            if (name != null) {
                LoggerWeakRef loggerWeakRef = (LoggerWeakRef) this.namedLoggers.get(name);
                if (loggerWeakRef != null) {
                    if (loggerWeakRef.get() != null) {
                        return false;
                    }
                    loggerWeakRef.dispose();
                }
                LogManager owner = getOwner();
                logger.setLogManager(owner);
                Objects.requireNonNull(owner);
                LoggerWeakRef loggerWeakRef2 = new LoggerWeakRef(logger);
                this.namedLoggers.put(name, loggerWeakRef2);
                Logger logger2 = null;
                Level levelProperty = owner.getLevelProperty(name + ".level", null);
                if (levelProperty != null && !logger.isLevelInitialized()) {
                    LogManager.doSetLevel(logger, levelProperty);
                }
                processParentHandlers(logger, name);
                LogNode node = getNode(name);
                node.loggerRef = loggerWeakRef2;
                for (LogNode logNode = node.parent; logNode != null; logNode = logNode.parent) {
                    LoggerWeakRef loggerWeakRef3 = logNode.loggerRef;
                    if (!(loggerWeakRef3 == null || (logger2 = (Logger) loggerWeakRef3.get()) == null)) {
                        break;
                    }
                }
                if (logger2 != null) {
                    LogManager.doSetParent(logger, logger2);
                }
                node.walkAndSetParent(logger);
                loggerWeakRef2.setNode(node);
                return true;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public synchronized void removeLoggerRef(String str, LoggerWeakRef loggerWeakRef) {
            this.namedLoggers.remove(str, loggerWeakRef);
        }

        /* access modifiers changed from: package-private */
        public synchronized Enumeration getLoggerNames() {
            ensureInitialized();
            return this.namedLoggers.keys();
        }

        private void processParentHandlers(final Logger logger, final String str) {
            final LogManager owner = getOwner();
            AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.logging.LogManager.LoggerContext.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Void run() {
                    if (logger == owner.rootLogger) {
                        return null;
                    }
                    LogManager logManager = owner;
                    if (logManager.getBooleanProperty(str + ".useParentHandlers", true)) {
                        return null;
                    }
                    logger.setUseParentHandlers(false);
                    return null;
                }
            });
            int i = 1;
            while (true) {
                int indexOf = str.indexOf(".", i);
                if (indexOf >= 0) {
                    String substring = str.substring(0, indexOf);
                    if (owner.getProperty(substring + ".level") == null) {
                        if (owner.getProperty(substring + ".handlers") == null) {
                            i = indexOf + 1;
                        }
                    }
                    demandLogger(substring, null);
                    i = indexOf + 1;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public LogNode getNode(String str) {
            String str2;
            if (str == null || str.equals("")) {
                return this.root;
            }
            LogNode logNode = this.root;
            while (str.length() > 0) {
                int indexOf = str.indexOf(".");
                if (indexOf > 0) {
                    String substring = str.substring(0, indexOf);
                    str2 = str.substring(indexOf + 1);
                    str = substring;
                } else {
                    str2 = "";
                }
                if (logNode.children == null) {
                    logNode.children = new HashMap();
                }
                LogNode logNode2 = (LogNode) logNode.children.get(str);
                if (logNode2 == null) {
                    logNode2 = new LogNode(logNode, this);
                    logNode.children.put(str, logNode2);
                }
                logNode = logNode2;
                str = str2;
            }
            return logNode;
        }
    }

    final class SystemLoggerContext extends LoggerContext {
        SystemLoggerContext() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.logging.LogManager.LoggerContext
        public Logger demandLogger(String str, String str2) {
            Logger findLogger = findLogger(str);
            if (findLogger == null) {
                Logger logger = new Logger(str, str2, null, getOwner(), true);
                do {
                    if (addLocalLogger(logger)) {
                        findLogger = logger;
                        continue;
                    } else {
                        findLogger = findLogger(str);
                        continue;
                    }
                } while (findLogger == null);
            }
            return findLogger;
        }
    }

    private void loadLoggerHandlers(final Logger logger, String str, final String str2) {
        AccessController.doPrivileged(new PrivilegedAction() {
            /* class java.util.logging.LogManager.AnonymousClass5 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                String[] parseClassNames = LogManager.this.parseClassNames(str2);
                for (String str : parseClassNames) {
                    try {
                        Handler handler = (Handler) LogManager.getClassInstance(str).newInstance();
                        String property = LogManager.this.getProperty(str + ".level");
                        if (property != null) {
                            Level findLevel = Level.findLevel(property);
                            if (findLevel != null) {
                                handler.setLevel(findLevel);
                            } else {
                                System.err.println("Can't set level for " + str);
                            }
                        }
                        logger.addHandler(handler);
                    } catch (Exception e) {
                        System.err.println("Can't load log handler \"" + str + "\"");
                        System.err.println("" + e);
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public final class LoggerWeakRef extends WeakReference {
        private boolean disposed = false;
        private String name;
        private LogNode node;
        private WeakReference parentRef;

        LoggerWeakRef(Logger logger) {
            super(logger, LogManager.this.loggerRefQueue);
            this.name = logger.getName();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
            r2 = r0.context;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            r0.context.removeLoggerRef(r5.name, r5);
            r5.name = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
            if (r0.loggerRef != r5) goto L_0x0022;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
            r0.loggerRef = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
            r5.node = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
            r0 = r5.parentRef;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x002b, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x002d, code lost:
            r0 = (java.util.logging.Logger) r0.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0033, code lost:
            if (r0 == null) goto L_0x0038;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0035, code lost:
            r0.removeChildLogger(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0038, code lost:
            r5.parentRef = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
            r0 = r5.node;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
            if (r0 == null) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dispose() {
            /*
                r5 = this;
                monitor-enter(r5)
                boolean r0 = r5.disposed     // Catch:{ all -> 0x003b }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r5)     // Catch:{ all -> 0x003b }
                return
            L_0x0007:
                r0 = 1
                r5.disposed = r0     // Catch:{ all -> 0x003b }
                monitor-exit(r5)     // Catch:{ all -> 0x003b }
                java.util.logging.LogManager$LogNode r0 = r5.node
                r1 = 0
                if (r0 == 0) goto L_0x0029
                java.util.logging.LogManager$LoggerContext r2 = r0.context
                monitor-enter(r2)
                java.util.logging.LogManager$LoggerContext r3 = r0.context     // Catch:{ all -> 0x0026 }
                java.lang.String r4 = r5.name     // Catch:{ all -> 0x0026 }
                r3.removeLoggerRef(r4, r5)     // Catch:{ all -> 0x0026 }
                r5.name = r1     // Catch:{ all -> 0x0026 }
                java.util.logging.LogManager$LoggerWeakRef r3 = r0.loggerRef     // Catch:{ all -> 0x0026 }
                if (r3 != r5) goto L_0x0022
                r0.loggerRef = r1     // Catch:{ all -> 0x0026 }
            L_0x0022:
                r5.node = r1     // Catch:{ all -> 0x0026 }
                monitor-exit(r2)     // Catch:{ all -> 0x0026 }
                goto L_0x0029
            L_0x0026:
                r5 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0026 }
                throw r5
            L_0x0029:
                java.lang.ref.WeakReference r0 = r5.parentRef
                if (r0 == 0) goto L_0x003a
                java.lang.Object r0 = r0.get()
                java.util.logging.Logger r0 = (java.util.logging.Logger) r0
                if (r0 == 0) goto L_0x0038
                r0.removeChildLogger(r5)
            L_0x0038:
                r5.parentRef = r1
            L_0x003a:
                return
            L_0x003b:
                r0 = move-exception
                monitor-exit(r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.logging.LogManager.LoggerWeakRef.dispose():void");
        }

        /* access modifiers changed from: package-private */
        public void setNode(LogNode logNode) {
            this.node = logNode;
        }

        /* access modifiers changed from: package-private */
        public void setParentRef(WeakReference weakReference) {
            this.parentRef = weakReference;
        }
    }

    /* access modifiers changed from: package-private */
    public final void drainLoggerRefQueueBounded() {
        LoggerWeakRef loggerWeakRef;
        for (int i = 0; i < 400 && (r1 = this.loggerRefQueue) != null && (loggerWeakRef = (LoggerWeakRef) r1.poll()) != null; i++) {
            loggerWeakRef.dispose();
        }
    }

    public boolean addLogger(Logger logger) {
        String name = logger.getName();
        if (name != null) {
            drainLoggerRefQueueBounded();
            if (!getUserContext().addLocalLogger(logger)) {
                return false;
            }
            loadLoggerHandlers(logger, name, name + ".handlers");
            return true;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public static void doSetLevel(final Logger logger, final Level level) {
        if (System.getSecurityManager() == null) {
            logger.setLevel(level);
        } else {
            AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.logging.LogManager.AnonymousClass6 */

                @Override // java.security.PrivilegedAction
                public Object run() {
                    Logger.this.setLevel(level);
                    return null;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void doSetParent(final Logger logger, final Logger logger2) {
        if (System.getSecurityManager() == null) {
            logger.setParent(logger2);
        } else {
            AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.logging.LogManager.AnonymousClass7 */

                @Override // java.security.PrivilegedAction
                public Object run() {
                    Logger.this.setParent(logger2);
                    return null;
                }
            });
        }
    }

    public Logger getLogger(String str) {
        return getUserContext().findLogger(str);
    }

    public void readConfiguration() {
        InputStream inputStream;
        checkPermission();
        String property = System.getProperty("java.util.logging.config.class");
        if (property != null) {
            try {
                getClassInstance(property).newInstance();
                return;
            } catch (Exception e) {
                PrintStream printStream = System.err;
                printStream.println("Logging configuration class \"" + property + "\" failed");
                PrintStream printStream2 = System.err;
                printStream2.println("" + e);
            }
        }
        String property2 = System.getProperty("java.util.logging.config.file");
        if (property2 == null) {
            String property3 = System.getProperty("java.home");
            if (property3 != null) {
                property2 = new File(new File(property3, "lib"), "logging.properties").getCanonicalPath();
            } else {
                throw new Error("Can't find java.home ??");
            }
        }
        try {
            inputStream = new FileInputStream(property2);
        } catch (Exception e2) {
            inputStream = LogManager.class.getResourceAsStream("logging.properties");
            if (inputStream == null) {
                throw e2;
            }
        }
        try {
            readConfiguration(new BufferedInputStream(inputStream));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void reset() {
        checkPermission();
        synchronized (this) {
            this.props = new Properties();
            this.initializedGlobalHandlers = true;
        }
        for (LoggerContext loggerContext : contexts()) {
            Enumeration loggerNames = loggerContext.getLoggerNames();
            while (loggerNames.hasMoreElements()) {
                Logger findLogger = loggerContext.findLogger((String) loggerNames.nextElement());
                if (findLogger != null) {
                    resetLogger(findLogger);
                }
            }
        }
    }

    private void resetLogger(Logger logger) {
        Handler[] handlers = logger.getHandlers();
        for (Handler handler : handlers) {
            logger.removeHandler(handler);
            try {
                handler.close();
            } catch (Exception unused) {
            }
        }
        String name = logger.getName();
        if (name == null || !name.equals("")) {
            logger.setLevel(null);
        } else {
            logger.setLevel(defaultLevel);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String[] parseClassNames(String str) {
        String property = getProperty(str);
        int i = 0;
        if (property == null) {
            return new String[0];
        }
        String trim = property.trim();
        ArrayList arrayList = new ArrayList();
        while (i < trim.length()) {
            int i2 = i;
            while (i2 < trim.length() && !Character.isWhitespace(trim.charAt(i2)) && trim.charAt(i2) != ',') {
                i2++;
            }
            String substring = trim.substring(i, i2);
            int i3 = i2 + 1;
            String trim2 = substring.trim();
            if (trim2.length() != 0) {
                arrayList.add(trim2);
            }
            i = i3;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void readConfiguration(InputStream inputStream) {
        HashMap hashMap;
        checkPermission();
        reset();
        this.props.load(inputStream);
        String[] parseClassNames = parseClassNames("config");
        for (String str : parseClassNames) {
            try {
                getClassInstance(str).newInstance();
            } catch (Exception e) {
                System.err.println("Can't load config class \"" + str + "\"");
                System.err.println("" + e);
            }
        }
        setLevelsOnExistingLoggers();
        synchronized (this.listenerMap) {
            hashMap = !this.listenerMap.isEmpty() ? new HashMap(this.listenerMap) : null;
        }
        if (hashMap != null) {
            Object newPropertyChangeEvent = Beans.newPropertyChangeEvent(LogManager.class, null, null, null);
            for (Map.Entry entry : hashMap.entrySet()) {
                Object key = entry.getKey();
                int intValue = ((Integer) entry.getValue()).intValue();
                for (int i = 0; i < intValue; i++) {
                    Beans.invokePropertyChange(key, newPropertyChangeEvent);
                }
            }
        }
        synchronized (this) {
            this.initializedGlobalHandlers = false;
        }
    }

    public String getProperty(String str) {
        return this.props.getProperty(str);
    }

    /* access modifiers changed from: package-private */
    public boolean getBooleanProperty(String str, boolean z) {
        String property = getProperty(str);
        if (property == null) {
            return z;
        }
        String lowerCase = property.toLowerCase();
        if (lowerCase.equals("true") || lowerCase.equals("1")) {
            return true;
        }
        if (lowerCase.equals("false") || lowerCase.equals("0")) {
            return false;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public Level getLevelProperty(String str, Level level) {
        String property = getProperty(str);
        if (property == null) {
            return level;
        }
        Level findLevel = Level.findLevel(property.trim());
        return findLevel != null ? findLevel : level;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void initializeGlobalHandlers() {
        if (!this.initializedGlobalHandlers) {
            this.initializedGlobalHandlers = true;
            if (!this.deathImminent) {
                loadLoggerHandlers(this.rootLogger, null, "handlers");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkPermission() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(this.controlPermission);
            throw null;
        }
    }

    /* access modifiers changed from: private */
    public static class LogNode {
        HashMap children;
        final LoggerContext context;
        LoggerWeakRef loggerRef;
        LogNode parent;

        LogNode(LogNode logNode, LoggerContext loggerContext) {
            this.parent = logNode;
            this.context = loggerContext;
        }

        /* access modifiers changed from: package-private */
        public void walkAndSetParent(Logger logger) {
            Logger logger2;
            HashMap hashMap = this.children;
            if (hashMap != null) {
                for (LogNode logNode : hashMap.values()) {
                    LoggerWeakRef loggerWeakRef = logNode.loggerRef;
                    if (loggerWeakRef == null) {
                        logger2 = null;
                    } else {
                        logger2 = (Logger) loggerWeakRef.get();
                    }
                    if (logger2 == null) {
                        logNode.walkAndSetParent(logger);
                    } else {
                        LogManager.doSetParent(logger2, logger);
                    }
                }
            }
        }
    }

    private final class RootLogger extends Logger {
        private RootLogger() {
            super("", null, null, LogManager.this, true);
        }

        @Override // java.util.logging.Logger
        public void log(LogRecord logRecord) {
            LogManager.this.initializeGlobalHandlers();
            super.log(logRecord);
        }

        @Override // java.util.logging.Logger
        public void addHandler(Handler handler) {
            LogManager.this.initializeGlobalHandlers();
            super.addHandler(handler);
        }

        @Override // java.util.logging.Logger
        public void removeHandler(Handler handler) {
            LogManager.this.initializeGlobalHandlers();
            super.removeHandler(handler);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.logging.Logger
        public Handler[] accessCheckedHandlers() {
            LogManager.this.initializeGlobalHandlers();
            return super.accessCheckedHandlers();
        }
    }

    private synchronized void setLevelsOnExistingLoggers() {
        Enumeration propertyNames = this.props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            if (str.endsWith(".level")) {
                String substring = str.substring(0, str.length() - 6);
                Level levelProperty = getLevelProperty(str, null);
                if (levelProperty == null) {
                    PrintStream printStream = System.err;
                    printStream.println("Bad level value for property: " + str);
                } else {
                    for (LoggerContext loggerContext : contexts()) {
                        Logger findLogger = loggerContext.findLogger(substring);
                        if (findLogger != null) {
                            findLogger.setLevel(levelProperty);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class Beans {
        private static final Class propertyChangeEventClass = getClass("java.beans.PropertyChangeEvent");
        private static final Class propertyChangeListenerClass = getClass("java.beans.PropertyChangeListener");
        private static final Method propertyChangeMethod = getMethod(propertyChangeListenerClass, "propertyChange", propertyChangeEventClass);
        private static final Constructor propertyEventCtor = getConstructor(propertyChangeEventClass, Object.class, String.class, Object.class, Object.class);

        private Beans() {
        }

        private static Class getClass(String str) {
            try {
                return Class.forName(str, true, Beans.class.getClassLoader());
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }

        private static Constructor getConstructor(Class cls, Class... clsArr) {
            if (cls == null) {
                return null;
            }
            try {
                return cls.getDeclaredConstructor(clsArr);
            } catch (NoSuchMethodException e) {
                throw new AssertionError(e);
            }
        }

        private static Method getMethod(Class cls, String str, Class... clsArr) {
            if (cls == null) {
                return null;
            }
            try {
                return cls.getMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                throw new AssertionError(e);
            }
        }

        static Object newPropertyChangeEvent(Object obj, String str, Object obj2, Object obj3) {
            try {
                return propertyEventCtor.newInstance(obj, str, obj2, obj3);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                Throwable cause = e2.getCause();
                if (cause instanceof Error) {
                    throw ((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                } else {
                    throw new AssertionError(e2);
                }
            }
        }

        static void invokePropertyChange(Object obj, Object obj2) {
            try {
                propertyChangeMethod.invoke(obj, obj2);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                Throwable cause = e2.getCause();
                if (cause instanceof Error) {
                    throw ((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                } else {
                    throw new AssertionError(e2);
                }
            }
        }
    }
}
