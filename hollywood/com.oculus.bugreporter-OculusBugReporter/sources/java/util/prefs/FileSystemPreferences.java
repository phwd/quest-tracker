package java.util.prefs;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import sun.util.logging.PlatformLogger;

public class FileSystemPreferences extends AbstractPreferences {
    private static final int EACCES = 13;
    private static final int EAGAIN = 11;
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final int ERROR_CODE = 1;
    private static int INIT_SLEEP_TIME = 50;
    private static final int LOCK_HANDLE = 0;
    private static int MAX_ATTEMPTS = 5;
    private static final int USER_READ_WRITE = 384;
    private static final int USER_RWX = 448;
    private static final int USER_RWX_ALL_RX = 493;
    private static final int USER_RW_ALL_READ = 420;
    private static boolean isSystemRootModified = false;
    private static boolean isSystemRootWritable;
    private static boolean isUserRootModified = false;
    private static boolean isUserRootWritable;
    static File systemLockFile;
    static Preferences systemRoot;
    private static File systemRootDir;
    private static int systemRootLockHandle = 0;
    private static File systemRootModFile;
    private static long systemRootModTime;
    static File userLockFile;
    static Preferences userRoot = null;
    private static File userRootDir;
    private static int userRootLockHandle = 0;
    private static File userRootModFile;
    private static long userRootModTime;
    final List<Change> changeLog = new ArrayList();
    private final File dir;
    private final boolean isUserNode;
    private long lastSyncTime = 0;
    NodeCreate nodeCreate = null;
    private Map<String, String> prefsCache = null;
    private final File prefsFile;
    private final File tmpFile;

    /* access modifiers changed from: private */
    public static native int chmod(String str, int i);

    private static native int[] lockFile0(String str, int i, boolean z);

    private static native int unlockFile0(int i);

    /* access modifiers changed from: private */
    public static PlatformLogger getLogger() {
        return PlatformLogger.getLogger("java.util.prefs");
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            /* class java.util.prefs.FileSystemPreferences.AnonymousClass3 */

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                FileSystemPreferences.syncWorld();
            }
        });
    }

    static synchronized Preferences getUserRoot() {
        Preferences preferences;
        synchronized (FileSystemPreferences.class) {
            if (userRoot == null) {
                setupUserRoot();
                userRoot = new FileSystemPreferences(true);
            }
            preferences = userRoot;
        }
        return preferences;
    }

    private static void setupUserRoot() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class java.util.prefs.FileSystemPreferences.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                File unused = FileSystemPreferences.userRootDir = new File(System.getProperty("java.util.prefs.userRoot", System.getProperty("user.home")), ".java/.userPrefs");
                if (!FileSystemPreferences.userRootDir.exists()) {
                    if (FileSystemPreferences.userRootDir.mkdirs()) {
                        try {
                            FileSystemPreferences.chmod(FileSystemPreferences.userRootDir.getCanonicalPath(), FileSystemPreferences.USER_RWX);
                        } catch (IOException e) {
                            FileSystemPreferences.getLogger().warning("Could not change permissions on userRoot directory. ");
                        }
                        FileSystemPreferences.getLogger().info("Created user preferences directory.");
                    } else {
                        FileSystemPreferences.getLogger().warning("Couldn't create user preferences directory. User preferences are unusable.");
                    }
                }
                boolean unused2 = FileSystemPreferences.isUserRootWritable = FileSystemPreferences.userRootDir.canWrite();
                String USER_NAME = System.getProperty("user.name");
                File file = FileSystemPreferences.userRootDir;
                FileSystemPreferences.userLockFile = new File(file, ".user.lock." + USER_NAME);
                File file2 = FileSystemPreferences.userRootDir;
                File unused3 = FileSystemPreferences.userRootModFile = new File(file2, ".userRootModFile." + USER_NAME);
                if (!FileSystemPreferences.userRootModFile.exists()) {
                    try {
                        FileSystemPreferences.userRootModFile.createNewFile();
                        int result = FileSystemPreferences.chmod(FileSystemPreferences.userRootModFile.getCanonicalPath(), 384);
                        if (result != 0) {
                            PlatformLogger logger = FileSystemPreferences.getLogger();
                            logger.warning("Problem creating userRoot mod file. Chmod failed on " + FileSystemPreferences.userRootModFile.getCanonicalPath() + " Unix error code " + result);
                        }
                    } catch (IOException e2) {
                        FileSystemPreferences.getLogger().warning(e2.toString());
                    }
                }
                long unused4 = FileSystemPreferences.userRootModTime = FileSystemPreferences.userRootModFile.lastModified();
                return null;
            }
        });
    }

    static synchronized Preferences getSystemRoot() {
        Preferences preferences;
        synchronized (FileSystemPreferences.class) {
            if (systemRoot == null) {
                setupSystemRoot();
                systemRoot = new FileSystemPreferences(false);
            }
            preferences = systemRoot;
        }
        return preferences;
    }

    private static void setupSystemRoot() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class java.util.prefs.FileSystemPreferences.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                File unused = FileSystemPreferences.systemRootDir = new File(System.getProperty("java.util.prefs.systemRoot", "/etc/.java"), ".systemPrefs");
                if (!FileSystemPreferences.systemRootDir.exists()) {
                    File unused2 = FileSystemPreferences.systemRootDir = new File(System.getProperty("java.home"), ".systemPrefs");
                    if (!FileSystemPreferences.systemRootDir.exists()) {
                        if (FileSystemPreferences.systemRootDir.mkdirs()) {
                            FileSystemPreferences.getLogger().info("Created system preferences directory in java.home.");
                            try {
                                FileSystemPreferences.chmod(FileSystemPreferences.systemRootDir.getCanonicalPath(), FileSystemPreferences.USER_RWX_ALL_RX);
                            } catch (IOException e) {
                            }
                        } else {
                            FileSystemPreferences.getLogger().warning("Could not create system preferences directory. System preferences are unusable.");
                        }
                    }
                }
                boolean unused3 = FileSystemPreferences.isSystemRootWritable = FileSystemPreferences.systemRootDir.canWrite();
                FileSystemPreferences.systemLockFile = new File(FileSystemPreferences.systemRootDir, ".system.lock");
                File unused4 = FileSystemPreferences.systemRootModFile = new File(FileSystemPreferences.systemRootDir, ".systemRootModFile");
                if (!FileSystemPreferences.systemRootModFile.exists() && FileSystemPreferences.isSystemRootWritable) {
                    try {
                        FileSystemPreferences.systemRootModFile.createNewFile();
                        int result = FileSystemPreferences.chmod(FileSystemPreferences.systemRootModFile.getCanonicalPath(), FileSystemPreferences.USER_RW_ALL_READ);
                        if (result != 0) {
                            PlatformLogger logger = FileSystemPreferences.getLogger();
                            logger.warning("Chmod failed on " + FileSystemPreferences.systemRootModFile.getCanonicalPath() + " Unix error code " + result);
                        }
                    } catch (IOException e2) {
                        FileSystemPreferences.getLogger().warning(e2.toString());
                    }
                }
                long unused5 = FileSystemPreferences.systemRootModTime = FileSystemPreferences.systemRootModFile.lastModified();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public abstract class Change {
        /* access modifiers changed from: package-private */
        public abstract void replay();

        private Change() {
        }
    }

    private class Put extends Change {
        String key;
        String value;

        Put(String key2, String value2) {
            super();
            this.key = key2;
            this.value = value2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.prefs.FileSystemPreferences.Change
        public void replay() {
            FileSystemPreferences.this.prefsCache.put(this.key, this.value);
        }
    }

    private class Remove extends Change {
        String key;

        Remove(String key2) {
            super();
            this.key = key2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.prefs.FileSystemPreferences.Change
        public void replay() {
            FileSystemPreferences.this.prefsCache.remove(this.key);
        }
    }

    /* access modifiers changed from: private */
    public class NodeCreate extends Change {
        private NodeCreate() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.prefs.FileSystemPreferences.Change
        public void replay() {
        }
    }

    private void replayChanges() {
        int n = this.changeLog.size();
        for (int i = 0; i < n; i++) {
            this.changeLog.get(i).replay();
        }
    }

    /* access modifiers changed from: private */
    public static void syncWorld() {
        Preferences userRt;
        Preferences systemRt;
        synchronized (FileSystemPreferences.class) {
            userRt = userRoot;
            systemRt = systemRoot;
        }
        if (userRt != null) {
            try {
                userRt.flush();
            } catch (BackingStoreException e) {
                PlatformLogger logger = getLogger();
                logger.warning("Couldn't flush user prefs: " + ((Object) e));
            }
        }
        if (systemRt != null) {
            try {
                systemRt.flush();
            } catch (BackingStoreException e2) {
                PlatformLogger logger2 = getLogger();
                logger2.warning("Couldn't flush system prefs: " + ((Object) e2));
            }
        }
    }

    private FileSystemPreferences(boolean user) {
        super(null, "");
        this.isUserNode = user;
        this.dir = user ? userRootDir : systemRootDir;
        this.prefsFile = new File(this.dir, "prefs.xml");
        this.tmpFile = new File(this.dir, "prefs.tmp");
    }

    public FileSystemPreferences(String path, File lockFile, boolean isUserNode2) {
        super(null, "");
        this.isUserNode = isUserNode2;
        this.dir = new File(path);
        this.prefsFile = new File(this.dir, "prefs.xml");
        this.tmpFile = new File(this.dir, "prefs.tmp");
        this.newNode = !this.dir.exists();
        if (this.newNode) {
            this.prefsCache = new TreeMap();
            this.nodeCreate = new NodeCreate();
            this.changeLog.add(this.nodeCreate);
        }
        if (isUserNode2) {
            userLockFile = lockFile;
            File parentFile = lockFile.getParentFile();
            userRootModFile = new File(parentFile, lockFile.getName() + ".rootmod");
            return;
        }
        systemLockFile = lockFile;
        File parentFile2 = lockFile.getParentFile();
        systemRootModFile = new File(parentFile2, lockFile.getName() + ".rootmod");
    }

    private FileSystemPreferences(FileSystemPreferences parent, String name) {
        super(parent, name);
        this.isUserNode = parent.isUserNode;
        this.dir = new File(parent.dir, dirName(name));
        this.prefsFile = new File(this.dir, "prefs.xml");
        this.tmpFile = new File(this.dir, "prefs.tmp");
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class java.util.prefs.FileSystemPreferences.AnonymousClass4 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                FileSystemPreferences fileSystemPreferences = FileSystemPreferences.this;
                fileSystemPreferences.newNode = !fileSystemPreferences.dir.exists();
                return null;
            }
        });
        if (this.newNode) {
            this.prefsCache = new TreeMap();
            this.nodeCreate = new NodeCreate();
            this.changeLog.add(this.nodeCreate);
        }
    }

    @Override // java.util.prefs.AbstractPreferences, java.util.prefs.Preferences
    public boolean isUserNode() {
        return this.isUserNode;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public void putSpi(String key, String value) {
        initCacheIfNecessary();
        this.changeLog.add(new Put(key, value));
        this.prefsCache.put(key, value);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public String getSpi(String key) {
        initCacheIfNecessary();
        return this.prefsCache.get(key);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public void removeSpi(String key) {
        initCacheIfNecessary();
        this.changeLog.add(new Remove(key));
        this.prefsCache.remove(key);
    }

    private void initCacheIfNecessary() {
        if (this.prefsCache == null) {
            try {
                loadCache();
            } catch (Exception e) {
                this.prefsCache = new TreeMap();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        $closeResource(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadCache() throws java.util.prefs.BackingStoreException {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.prefs.FileSystemPreferences.loadCache():void");
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
                x0.addSuppressed(th);
            }
        } else {
            x1.close();
        }
    }

    private void writeBackCache() throws BackingStoreException {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                /* class java.util.prefs.FileSystemPreferences.AnonymousClass5 */

                /* JADX WARNING: Code restructure failed: missing block: B:19:0x008f, code lost:
                    r2 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
                    r0.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:22:0x0094, code lost:
                    r3 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
                    r1.addSuppressed(r3);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x0098, code lost:
                    throw r2;
                 */
                @Override // java.security.PrivilegedExceptionAction
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Void run() throws java.util.prefs.BackingStoreException {
                    /*
                    // Method dump skipped, instructions count: 168
                    */
                    throw new UnsupportedOperationException("Method not decompiled: java.util.prefs.FileSystemPreferences.AnonymousClass5.run():java.lang.Void");
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((BackingStoreException) e.getException());
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public String[] keysSpi() {
        initCacheIfNecessary();
        return (String[]) this.prefsCache.keySet().toArray(new String[this.prefsCache.size()]);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public String[] childrenNamesSpi() {
        return (String[]) AccessController.doPrivileged(new PrivilegedAction<String[]>() {
            /* class java.util.prefs.FileSystemPreferences.AnonymousClass6 */

            @Override // java.security.PrivilegedAction
            public String[] run() {
                List<String> result = new ArrayList<>();
                File[] dirContents = FileSystemPreferences.this.dir.listFiles();
                if (dirContents != null) {
                    for (int i = 0; i < dirContents.length; i++) {
                        if (dirContents[i].isDirectory()) {
                            result.add(FileSystemPreferences.nodeName(dirContents[i].getName()));
                        }
                    }
                }
                return (String[]) result.toArray(FileSystemPreferences.EMPTY_STRING_ARRAY);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public AbstractPreferences childSpi(String name) {
        return new FileSystemPreferences(this, name);
    }

    @Override // java.util.prefs.AbstractPreferences, java.util.prefs.Preferences
    public void removeNode() throws BackingStoreException {
        synchronized ((isUserNode() ? userLockFile : systemLockFile)) {
            if (lockFile(false)) {
                try {
                    super.removeNode();
                } finally {
                    unlockFile();
                }
            } else {
                throw new BackingStoreException("Couldn't get file lock.");
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public void removeNodeSpi() throws BackingStoreException {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                /* class java.util.prefs.FileSystemPreferences.AnonymousClass7 */

                @Override // java.security.PrivilegedExceptionAction
                public Void run() throws BackingStoreException {
                    if (FileSystemPreferences.this.changeLog.contains(FileSystemPreferences.this.nodeCreate)) {
                        FileSystemPreferences.this.changeLog.remove(FileSystemPreferences.this.nodeCreate);
                        FileSystemPreferences.this.nodeCreate = null;
                        return null;
                    } else if (!FileSystemPreferences.this.dir.exists()) {
                        return null;
                    } else {
                        FileSystemPreferences.this.prefsFile.delete();
                        FileSystemPreferences.this.tmpFile.delete();
                        File[] junk = FileSystemPreferences.this.dir.listFiles();
                        if (junk.length != 0) {
                            FileSystemPreferences.getLogger().warning("Found extraneous files when removing node: " + ((Object) Arrays.asList(junk)));
                            for (File file : junk) {
                                file.delete();
                            }
                        }
                        if (FileSystemPreferences.this.dir.delete()) {
                            return null;
                        }
                        throw new BackingStoreException("Couldn't delete dir: " + ((Object) FileSystemPreferences.this.dir));
                    }
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((BackingStoreException) e.getException());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004c, code lost:
        r3 = th;
     */
    @Override // java.util.prefs.AbstractPreferences, java.util.prefs.Preferences
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void sync() throws java.util.prefs.BackingStoreException {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.isUserNode()     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0009
            r1 = 0
            goto L_0x000d
        L_0x0009:
            boolean r1 = java.util.prefs.FileSystemPreferences.isSystemRootWritable     // Catch:{ all -> 0x004e }
            r1 = r1 ^ 1
        L_0x000d:
            boolean r2 = r5.isUserNode()     // Catch:{ all -> 0x004e }
            if (r2 == 0) goto L_0x0016
            java.io.File r2 = java.util.prefs.FileSystemPreferences.userLockFile     // Catch:{ all -> 0x004e }
            goto L_0x0018
        L_0x0016:
            java.io.File r2 = java.util.prefs.FileSystemPreferences.systemLockFile     // Catch:{ all -> 0x004e }
        L_0x0018:
            monitor-enter(r2)     // Catch:{ all -> 0x004e }
            boolean r3 = r5.lockFile(r1)     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x0041
            java.util.prefs.FileSystemPreferences$8 r3 = new java.util.prefs.FileSystemPreferences$8     // Catch:{ all -> 0x0049 }
            r3.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.Object r3 = java.security.AccessController.doPrivileged(r3)     // Catch:{ all -> 0x0049 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0049 }
            super.sync()     // Catch:{ all -> 0x003c }
            java.util.prefs.FileSystemPreferences$9 r4 = new java.util.prefs.FileSystemPreferences$9     // Catch:{ all -> 0x003c }
            r4.<init>(r3)     // Catch:{ all -> 0x003c }
            java.security.AccessController.doPrivileged(r4)     // Catch:{ all -> 0x003c }
            r5.unlockFile()
            monitor-exit(r2)
            monitor-exit(r5)
            return
        L_0x003c:
            r4 = move-exception
            r5.unlockFile()
            throw r4
        L_0x0041:
            java.util.prefs.BackingStoreException r3 = new java.util.prefs.BackingStoreException
            java.lang.String r4 = "Couldn't get file lock."
            r3.<init>(r4)
            throw r3
        L_0x0049:
            r3 = move-exception
        L_0x004a:
            monitor-exit(r2)     // Catch:{ all -> 0x004c }
            throw r3
        L_0x004c:
            r3 = move-exception
            goto L_0x004a
        L_0x004e:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.prefs.FileSystemPreferences.sync():void");
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public void syncSpi() throws BackingStoreException {
        syncSpiPrivileged();
    }

    private void syncSpiPrivileged() throws BackingStoreException {
        if (isRemoved()) {
            throw new IllegalStateException("Node has been removed");
        } else if (this.prefsCache != null) {
            if (!isUserNode() ? isSystemRootModified : isUserRootModified) {
                long lastModifiedTime = this.prefsFile.lastModified();
                if (lastModifiedTime != this.lastSyncTime) {
                    loadCache();
                    replayChanges();
                    this.lastSyncTime = lastModifiedTime;
                }
            } else if (this.lastSyncTime != 0 && !this.dir.exists()) {
                this.prefsCache = new TreeMap();
                replayChanges();
            }
            if (!this.changeLog.isEmpty()) {
                writeBackCache();
                long lastModifiedTime2 = this.prefsFile.lastModified();
                if (this.lastSyncTime <= lastModifiedTime2) {
                    this.lastSyncTime = 1000 + lastModifiedTime2;
                    this.prefsFile.setLastModified(this.lastSyncTime);
                }
                this.changeLog.clear();
            }
        }
    }

    @Override // java.util.prefs.AbstractPreferences, java.util.prefs.Preferences
    public void flush() throws BackingStoreException {
        if (!isRemoved()) {
            sync();
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.util.prefs.AbstractPreferences
    public void flushSpi() throws BackingStoreException {
    }

    private static boolean isDirChar(char ch) {
        return (ch <= 31 || ch >= 127 || ch == '/' || ch == '.' || ch == '_') ? false : true;
    }

    private static String dirName(String nodeName) {
        int n = nodeName.length();
        for (int i = 0; i < n; i++) {
            if (!isDirChar(nodeName.charAt(i))) {
                return "_" + Base64.byteArrayToAltBase64(byteArray(nodeName));
            }
        }
        return nodeName;
    }

    private static byte[] byteArray(String s) {
        int len = s.length();
        byte[] result = new byte[(len * 2)];
        int j = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int j2 = j + 1;
            result[j] = (byte) (c >> '\b');
            j = j2 + 1;
            result[j2] = (byte) c;
        }
        return result;
    }

    /* access modifiers changed from: private */
    public static String nodeName(String dirName) {
        if (dirName.charAt(0) != '_') {
            return dirName;
        }
        byte[] a = Base64.altBase64ToByteArray(dirName.substring(1));
        StringBuffer result = new StringBuffer(a.length / 2);
        int highByte = 0;
        while (highByte < a.length) {
            int i = highByte + 1;
            result.append((char) (((a[highByte] & 255) << 8) | (a[i] & 255)));
            highByte = i + 1;
        }
        return result.toString();
    }

    private boolean lockFile(boolean shared) throws SecurityException {
        boolean usernode = isUserNode();
        int errorCode = 0;
        File lockFile = usernode ? userLockFile : systemLockFile;
        long sleepTime = (long) INIT_SLEEP_TIME;
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                int[] result = lockFile0(lockFile.getCanonicalPath(), usernode ? 384 : USER_RW_ALL_READ, shared);
                errorCode = result[1];
                if (result[0] != 0) {
                    if (usernode) {
                        userRootLockHandle = result[0];
                    } else {
                        systemRootLockHandle = result[0];
                    }
                    return true;
                }
            } catch (IOException e) {
            }
            try {
                Thread.sleep(sleepTime);
                sleepTime *= 2;
            } catch (InterruptedException e2) {
                checkLockFile0ErrorCode(errorCode);
                return false;
            }
        }
        checkLockFile0ErrorCode(errorCode);
        return false;
    }

    private void checkLockFile0ErrorCode(int errorCode) throws SecurityException {
        String str = "System prefs.";
        if (errorCode == 13) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not lock ");
            if (isUserNode()) {
                str = "User prefs.";
            }
            sb.append(str);
            sb.append(" Lock file access denied.");
            throw new SecurityException(sb.toString());
        } else if (errorCode != 11) {
            PlatformLogger logger = getLogger();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not lock ");
            if (isUserNode()) {
                str = "User prefs. ";
            }
            sb2.append(str);
            sb2.append(" Unix error code ");
            sb2.append(errorCode);
            sb2.append(".");
            logger.warning(sb2.toString());
        }
    }

    private void unlockFile() {
        boolean usernode = isUserNode();
        if (usernode) {
            File file = userLockFile;
        } else {
            File file2 = systemLockFile;
        }
        int lockHandle = usernode ? userRootLockHandle : systemRootLockHandle;
        String str = "user";
        if (lockHandle == 0) {
            PlatformLogger logger = getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Unlock: zero lockHandle for ");
            if (!usernode) {
                str = "system";
            }
            sb.append(str);
            sb.append(" preferences.)");
            logger.warning(sb.toString());
            return;
        }
        int result = unlockFile0(lockHandle);
        if (result != 0) {
            PlatformLogger logger2 = getLogger();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not drop file-lock on ");
            if (!isUserNode()) {
                str = "system";
            }
            sb2.append(str);
            sb2.append(" preferences. Unix error code ");
            sb2.append(result);
            sb2.append(".");
            logger2.warning(sb2.toString());
            if (result == 13) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Could not unlock");
                sb3.append(isUserNode() ? "User prefs." : "System prefs.");
                sb3.append(" Lock file access denied.");
                throw new SecurityException(sb3.toString());
            }
        }
        if (isUserNode()) {
            userRootLockHandle = 0;
        } else {
            systemRootLockHandle = 0;
        }
    }
}
