package sun.nio.fs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Properties;

/* access modifiers changed from: package-private */
public abstract class UnixFileStore extends FileStore {
    private static final Object loadLock = new Object();
    private static volatile Properties props;
    private final long dev;
    private final UnixMountEntry entry;
    private final UnixPath file;

    /* access modifiers changed from: package-private */
    public enum FeatureStatus {
        PRESENT,
        NOT_PRESENT,
        UNKNOWN
    }

    /* access modifiers changed from: package-private */
    public abstract UnixMountEntry findMountEntry() throws IOException;

    private static long devFor(UnixPath file2) throws IOException {
        try {
            return UnixFileAttributes.get(file2, true).dev();
        } catch (UnixException x) {
            x.rethrowAsIOException(file2);
            return 0;
        }
    }

    UnixFileStore(UnixPath file2) throws IOException {
        this.file = file2;
        this.dev = devFor(file2);
        this.entry = findMountEntry();
    }

    UnixFileStore(UnixFileSystem fs, UnixMountEntry entry2) throws IOException {
        this.file = new UnixPath(fs, entry2.dir());
        this.dev = entry2.dev() == 0 ? devFor(this.file) : entry2.dev();
        this.entry = entry2;
    }

    /* access modifiers changed from: package-private */
    public UnixPath file() {
        return this.file;
    }

    /* access modifiers changed from: package-private */
    public long dev() {
        return this.dev;
    }

    /* access modifiers changed from: package-private */
    public UnixMountEntry entry() {
        return this.entry;
    }

    @Override // java.nio.file.FileStore
    public String name() {
        return this.entry.name();
    }

    @Override // java.nio.file.FileStore
    public String type() {
        return this.entry.fstype();
    }

    @Override // java.nio.file.FileStore
    public boolean isReadOnly() {
        return this.entry.isReadOnly();
    }

    private UnixFileStoreAttributes readAttributes() throws IOException {
        try {
            return UnixFileStoreAttributes.get(this.file);
        } catch (UnixException x) {
            x.rethrowAsIOException(this.file);
            return null;
        }
    }

    @Override // java.nio.file.FileStore
    public long getTotalSpace() throws IOException {
        UnixFileStoreAttributes attrs = readAttributes();
        return attrs.blockSize() * attrs.totalBlocks();
    }

    @Override // java.nio.file.FileStore
    public long getUsableSpace() throws IOException {
        UnixFileStoreAttributes attrs = readAttributes();
        return attrs.blockSize() * attrs.availableBlocks();
    }

    @Override // java.nio.file.FileStore
    public long getUnallocatedSpace() throws IOException {
        UnixFileStoreAttributes attrs = readAttributes();
        return attrs.blockSize() * attrs.freeBlocks();
    }

    @Override // java.nio.file.FileStore
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> view) {
        if (view != null) {
            return (V) null;
        }
        throw new NullPointerException();
    }

    @Override // java.nio.file.FileStore
    public Object getAttribute(String attribute) throws IOException {
        if (attribute.equals("totalSpace")) {
            return Long.valueOf(getTotalSpace());
        }
        if (attribute.equals("usableSpace")) {
            return Long.valueOf(getUsableSpace());
        }
        if (attribute.equals("unallocatedSpace")) {
            return Long.valueOf(getUnallocatedSpace());
        }
        throw new UnsupportedOperationException("'" + attribute + "' not recognized");
    }

    @Override // java.nio.file.FileStore
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        if (type == null) {
            throw new NullPointerException();
        } else if (type == BasicFileAttributeView.class) {
            return true;
        } else {
            if (type != PosixFileAttributeView.class && type != FileOwnerAttributeView.class) {
                return false;
            }
            if (checkIfFeaturePresent("posix") != FeatureStatus.NOT_PRESENT) {
                return true;
            }
            return false;
        }
    }

    @Override // java.nio.file.FileStore
    public boolean supportsFileAttributeView(String name) {
        if (name.equals("basic") || name.equals("unix")) {
            return true;
        }
        if (name.equals("posix")) {
            return supportsFileAttributeView(PosixFileAttributeView.class);
        }
        if (name.equals("owner")) {
            return supportsFileAttributeView(FileOwnerAttributeView.class);
        }
        return false;
    }

    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }
        if (!(ob instanceof UnixFileStore)) {
            return false;
        }
        UnixFileStore other = (UnixFileStore) ob;
        if (this.dev != other.dev || !Arrays.equals(this.entry.dir(), other.entry.dir()) || !this.entry.name().equals(other.entry.name())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.dev;
        return ((int) (j ^ (j >>> 32))) ^ Arrays.hashCode(this.entry.dir());
    }

    public String toString() {
        return Util.toString(this.entry.dir()) + " (" + this.entry.name() + ")";
    }

    /* access modifiers changed from: package-private */
    public FeatureStatus checkIfFeaturePresent(String feature) {
        if (props == null) {
            synchronized (loadLock) {
                if (props == null) {
                    props = (Properties) AccessController.doPrivileged(new PrivilegedAction<Properties>() {
                        /* class sun.nio.fs.UnixFileStore.AnonymousClass1 */

                        @Override // java.security.PrivilegedAction
                        public Properties run() {
                            return UnixFileStore.loadProperties();
                        }
                    });
                }
            }
        }
        String value = props.getProperty(type());
        if (value != null) {
            for (String s : value.split("\\s")) {
                String s2 = s.trim().toLowerCase();
                if (s2.equals(feature)) {
                    return FeatureStatus.PRESENT;
                }
                if (s2.startsWith("no") && s2.substring(2).equals(feature)) {
                    return FeatureStatus.NOT_PRESENT;
                }
            }
        }
        return FeatureStatus.UNKNOWN;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        if (r2 != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Properties loadProperties() {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "java.home"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            r1.append(r2)
            java.lang.String r2 = "/lib/fstypes.properties"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            java.lang.String[] r3 = new java.lang.String[r2]
            java.nio.file.Path r3 = java.nio.file.Paths.get(r1, r3)
            java.nio.file.OpenOption[] r2 = new java.nio.file.OpenOption[r2]     // Catch:{ IOException -> 0x0046 }
            java.nio.channels.SeekableByteChannel r2 = java.nio.file.Files.newByteChannel(r3, r2)     // Catch:{ IOException -> 0x0046 }
            java.lang.String r4 = "UTF-8"
            java.io.Reader r4 = java.nio.channels.Channels.newReader(r2, r4)     // Catch:{ all -> 0x0038 }
            r0.load(r4)     // Catch:{ all -> 0x0038 }
            if (r2 == 0) goto L_0x0037
            r2.close()
        L_0x0037:
            goto L_0x0047
        L_0x0038:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003a }
        L_0x003a:
            r5 = move-exception
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ all -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r6 = move-exception
            r4.addSuppressed(r6)
        L_0x0045:
            throw r5
        L_0x0046:
            r2 = move-exception
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.UnixFileStore.loadProperties():java.util.Properties");
    }
}
