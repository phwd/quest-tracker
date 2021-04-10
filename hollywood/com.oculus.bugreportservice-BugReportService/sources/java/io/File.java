package java.io;

import java.net.URI;
import java.net.URISyntaxException;
import sun.misc.Unsafe;

public class File implements Serializable, Comparable {
    private static final long PATH_OFFSET;
    private static final long PREFIX_LENGTH_OFFSET;
    private static final Unsafe UNSAFE;
    private static final FileSystem fs = DefaultFileSystem.getFileSystem();
    public static final String pathSeparator = ("" + pathSeparatorChar);
    public static final char pathSeparatorChar = fs.getPathSeparator();
    public static final String separator = ("" + separatorChar);
    public static final char separatorChar = fs.getSeparator();
    private static final long serialVersionUID = 301077366599181567L;
    private final String path;
    private final transient int prefixLength;
    private transient PathStatus status = null;

    /* access modifiers changed from: private */
    public enum PathStatus {
        INVALID,
        CHECKED
    }

    static {
        try {
            Unsafe unsafe = Unsafe.getUnsafe();
            PATH_OFFSET = unsafe.objectFieldOffset(File.class.getDeclaredField("path"));
            PREFIX_LENGTH_OFFSET = unsafe.objectFieldOffset(File.class.getDeclaredField("prefixLength"));
            UNSAFE = unsafe;
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isInvalid() {
        PathStatus pathStatus;
        if (this.status == null) {
            if (this.path.indexOf(0) < 0) {
                pathStatus = PathStatus.CHECKED;
            } else {
                pathStatus = PathStatus.INVALID;
            }
            this.status = pathStatus;
        }
        if (this.status == PathStatus.INVALID) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getPrefixLength() {
        return this.prefixLength;
    }

    private File(String str, int i) {
        this.path = str;
        this.prefixLength = i;
    }

    private File(String str, File file) {
        this.path = fs.resolve(file.path, str);
        this.prefixLength = file.prefixLength;
    }

    public File(String str) {
        if (str != null) {
            fs.normalize(str);
            this.path = str;
            this.prefixLength = fs.prefixLength(this.path);
            return;
        }
        throw new NullPointerException();
    }

    public File(String str, String str2) {
        if (str2 != null) {
            if (str == null || str.isEmpty()) {
                fs.normalize(str2);
                this.path = str2;
            } else {
                FileSystem fileSystem = fs;
                fileSystem.normalize(str);
                fs.normalize(str2);
                this.path = fileSystem.resolve(str, str2);
            }
            this.prefixLength = fs.prefixLength(this.path);
            return;
        }
        throw new NullPointerException();
    }

    public File(File file, String str) {
        if (str != null) {
            if (file == null) {
                fs.normalize(str);
                this.path = str;
            } else if (file.path.equals("")) {
                FileSystem fileSystem = fs;
                String defaultParent = fileSystem.getDefaultParent();
                fs.normalize(str);
                this.path = fileSystem.resolve(defaultParent, str);
            } else {
                FileSystem fileSystem2 = fs;
                String str2 = file.path;
                fileSystem2.normalize(str);
                this.path = fileSystem2.resolve(str2, str);
            }
            this.prefixLength = fs.prefixLength(this.path);
            return;
        }
        throw new NullPointerException();
    }

    public File(URI uri) {
        if (!uri.isAbsolute()) {
            throw new IllegalArgumentException("URI is not absolute");
        } else if (!uri.isOpaque()) {
            String scheme = uri.getScheme();
            if (scheme == null || !scheme.equalsIgnoreCase("file")) {
                throw new IllegalArgumentException("URI scheme is not \"file\"");
            } else if (uri.getAuthority() != null) {
                throw new IllegalArgumentException("URI has an authority component");
            } else if (uri.getFragment() != null) {
                throw new IllegalArgumentException("URI has a fragment component");
            } else if (uri.getQuery() == null) {
                String path2 = uri.getPath();
                if (!path2.equals("")) {
                    String fromURIPath = fs.fromURIPath(path2);
                    char c = separatorChar;
                    fromURIPath = c != '/' ? fromURIPath.replace('/', c) : fromURIPath;
                    fs.normalize(fromURIPath);
                    this.path = fromURIPath;
                    this.prefixLength = fs.prefixLength(this.path);
                    return;
                }
                throw new IllegalArgumentException("URI path component is empty");
            } else {
                throw new IllegalArgumentException("URI has a query component");
            }
        } else {
            throw new IllegalArgumentException("URI is not hierarchical");
        }
    }

    public String getName() {
        int lastIndexOf = this.path.lastIndexOf(separatorChar);
        int i = this.prefixLength;
        if (lastIndexOf < i) {
            return this.path.substring(i);
        }
        return this.path.substring(lastIndexOf + 1);
    }

    public String getParent() {
        int i;
        int lastIndexOf = this.path.lastIndexOf(separatorChar);
        int i2 = this.prefixLength;
        if (lastIndexOf >= i2) {
            return this.path.substring(0, lastIndexOf);
        }
        if (i2 <= 0 || this.path.length() <= (i = this.prefixLength)) {
            return null;
        }
        return this.path.substring(0, i);
    }

    public File getParentFile() {
        String parent = getParent();
        if (parent == null) {
            return null;
        }
        return new File(parent, this.prefixLength);
    }

    public String getPath() {
        return this.path;
    }

    public String getAbsolutePath() {
        return fs.resolve(this);
    }

    public File getAbsoluteFile() {
        String absolutePath = getAbsolutePath();
        return new File(absolutePath, fs.prefixLength(absolutePath));
    }

    public String getCanonicalPath() {
        if (!isInvalid()) {
            FileSystem fileSystem = fs;
            return fileSystem.canonicalize(fileSystem.resolve(this));
        }
        throw new IOException("Invalid file path");
    }

    public File getCanonicalFile() {
        String canonicalPath = getCanonicalPath();
        return new File(canonicalPath, fs.prefixLength(canonicalPath));
    }

    private static String slashify(String str, boolean z) {
        char c = separatorChar;
        if (c != '/') {
            str = str.replace(c, '/');
        }
        if (!str.startsWith("/")) {
            str = "/" + str;
        }
        if (str.endsWith("/") || !z) {
            return str;
        }
        return str + "/";
    }

    public URI toURI() {
        try {
            File absoluteFile = getAbsoluteFile();
            String slashify = slashify(absoluteFile.getPath(), absoluteFile.isDirectory());
            if (slashify.startsWith("//")) {
                slashify = "//" + slashify;
            }
            return new URI("file", null, slashify, null);
        } catch (URISyntaxException e) {
            throw new Error(e);
        }
    }

    public boolean canRead() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (isInvalid()) {
            return false;
        } else {
            return fs.checkAccess(this, 4);
        }
    }

    public boolean canWrite() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkWrite(this.path);
            throw null;
        } else if (isInvalid()) {
            return false;
        } else {
            return fs.checkAccess(this, 2);
        }
    }

    public boolean exists() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (isInvalid()) {
            return false;
        } else {
            return fs.checkAccess(this, 8);
        }
    }

    public boolean isDirectory() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (!isInvalid() && (fs.getBooleanAttributes(this) & 4) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFile() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (!isInvalid() && (fs.getBooleanAttributes(this) & 2) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public long lastModified() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (isInvalid()) {
            return 0;
        } else {
            return fs.getLastModifiedTime(this);
        }
    }

    public long length() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (isInvalid()) {
            return 0;
        } else {
            return fs.getLength(this);
        }
    }

    public boolean createNewFile() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkWrite(this.path);
            throw null;
        } else if (!isInvalid()) {
            return fs.createFileExclusively(this.path);
        } else {
            throw new IOException("Invalid file path");
        }
    }

    public boolean delete() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkDelete(this.path);
            throw null;
        } else if (isInvalid()) {
            return false;
        } else {
            return fs.delete(this);
        }
    }

    public void deleteOnExit() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkDelete(this.path);
            throw null;
        } else if (!isInvalid()) {
            DeleteOnExitHook.add(this.path);
        }
    }

    public String[] list() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkRead(this.path);
            throw null;
        } else if (isInvalid()) {
            return null;
        } else {
            return fs.list(this);
        }
    }

    public File[] listFiles() {
        String[] list = list();
        if (list == null) {
            return null;
        }
        int length = list.length;
        File[] fileArr = new File[length];
        for (int i = 0; i < length; i++) {
            fileArr[i] = new File(list[i], this);
        }
        return fileArr;
    }

    public boolean mkdir() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkWrite(this.path);
            throw null;
        } else if (isInvalid()) {
            return false;
        } else {
            return fs.createDirectory(this);
        }
    }

    public boolean mkdirs() {
        if (exists()) {
            return false;
        }
        if (mkdir()) {
            return true;
        }
        try {
            File canonicalFile = getCanonicalFile();
            File parentFile = canonicalFile.getParentFile();
            if (parentFile == null) {
                return false;
            }
            if ((parentFile.mkdirs() || parentFile.exists()) && canonicalFile.mkdir()) {
                return true;
            }
            return false;
        } catch (IOException unused) {
            return false;
        }
    }

    public int compareTo(File file) {
        return fs.compare(this, file);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof File) || compareTo((File) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return fs.hashCode(this);
    }

    public String toString() {
        return getPath();
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private synchronized void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }
}
