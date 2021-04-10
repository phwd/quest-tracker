package java.io;

import android.system.ErrnoException;
import android.system.OsConstants;
import dalvik.system.BlockGuard;
import java.security.AccessController;
import libcore.io.Libcore;
import sun.security.action.GetPropertyAction;

/* access modifiers changed from: package-private */
public class UnixFileSystem extends FileSystem {
    private ExpiringCache cache = new ExpiringCache();
    private final char colon = ((String) AccessController.doPrivileged(new GetPropertyAction("path.separator"))).charAt(0);
    private final String javaHome = ((String) AccessController.doPrivileged(new GetPropertyAction("java.home")));
    private ExpiringCache javaHomePrefixCache = new ExpiringCache();
    private final char slash = ((String) AccessController.doPrivileged(new GetPropertyAction("file.separator"))).charAt(0);

    private native String canonicalize0(String str) throws IOException;

    private native boolean createDirectory0(File file);

    private native boolean createFileExclusively0(String str) throws IOException;

    private native int getBooleanAttributes0(String str);

    private native long getLastModifiedTime0(File file);

    private native long getSpace0(File file, int i);

    private static native void initIDs();

    private native String[] list0(File file);

    private native boolean setLastModifiedTime0(File file, long j);

    private native boolean setPermission0(File file, int i, boolean z, boolean z2);

    private native boolean setReadOnly0(File file);

    @Override // java.io.FileSystem
    public char getSeparator() {
        return this.slash;
    }

    @Override // java.io.FileSystem
    public char getPathSeparator() {
        return this.colon;
    }

    @Override // java.io.FileSystem
    public String normalize(String pathname) {
        int n = pathname.length();
        char[] normalized = pathname.toCharArray();
        int index = 0;
        char prevChar = 0;
        for (int i = 0; i < n; i++) {
            char current = normalized[i];
            if (current != '/' || prevChar != '/') {
                normalized[index] = current;
                index++;
            }
            prevChar = current;
        }
        if (prevChar == '/' && n > 1) {
            index--;
        }
        return index != n ? new String(normalized, 0, index) : pathname;
    }

    @Override // java.io.FileSystem
    public int prefixLength(String pathname) {
        if (pathname.length() != 0 && pathname.charAt(0) == '/') {
            return 1;
        }
        return 0;
    }

    @Override // java.io.FileSystem
    public String resolve(String parent, String child) {
        if (child.isEmpty() || child.equals("/")) {
            return parent;
        }
        if (child.charAt(0) == '/') {
            if (parent.equals("/")) {
                return child;
            }
            return parent + child;
        } else if (parent.equals("/")) {
            return parent + child;
        } else {
            return parent + '/' + child;
        }
    }

    @Override // java.io.FileSystem
    public String getDefaultParent() {
        return "/";
    }

    @Override // java.io.FileSystem
    public String fromURIPath(String path) {
        if (!path.endsWith("/") || path.length() <= 1) {
            return path;
        }
        return path.substring(0, path.length() - 1);
    }

    @Override // java.io.FileSystem
    public boolean isAbsolute(File f) {
        return f.getPrefixLength() != 0;
    }

    @Override // java.io.FileSystem
    public String resolve(File f) {
        if (isAbsolute(f)) {
            return f.getPath();
        }
        return resolve(System.getProperty("user.dir"), f.getPath());
    }

    @Override // java.io.FileSystem
    public String canonicalize(String path) throws IOException {
        String resDir;
        String resDir2;
        if (!useCanonCaches) {
            return canonicalize0(path);
        }
        String res = this.cache.get(path);
        if (res == null) {
            String dir = null;
            if (!(!useCanonPrefixCache || (dir = parentOrNull(path)) == null || (resDir2 = this.javaHomePrefixCache.get(dir)) == null)) {
                String filename = path.substring(dir.length() + 1);
                res = resDir2 + this.slash + filename;
                this.cache.put(dir + this.slash + filename, res);
            }
            if (res == null) {
                BlockGuard.getThreadPolicy().onReadFromDisk();
                BlockGuard.getVmPolicy().onPathAccess(path);
                res = canonicalize0(path);
                this.cache.put(path, res);
                if (useCanonPrefixCache && dir != null && dir.startsWith(this.javaHome) && (resDir = parentOrNull(res)) != null && resDir.equals(dir)) {
                    File f = new File(res);
                    if (f.exists() && !f.isDirectory()) {
                        this.javaHomePrefixCache.put(dir, resDir);
                    }
                }
            }
        }
        return res;
    }

    static String parentOrNull(String path) {
        if (path == null) {
            return null;
        }
        char sep = File.separatorChar;
        int last = path.length() - 1;
        int adjacentDots = 0;
        int nonDotCount = 0;
        for (int idx = last; idx > 0; idx--) {
            char c = path.charAt(idx);
            if (c == '.') {
                adjacentDots++;
                if (adjacentDots >= 2) {
                    return null;
                }
            } else if (c != sep) {
                nonDotCount++;
                adjacentDots = 0;
            } else if ((adjacentDots == 1 && nonDotCount == 0) || idx == 0 || idx >= last - 1 || path.charAt(idx - 1) == sep) {
                return null;
            } else {
                return path.substring(0, idx);
            }
        }
        return null;
    }

    @Override // java.io.FileSystem
    public int getBooleanAttributes(File f) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        int rv = getBooleanAttributes0(f.getPath());
        String name = f.getName();
        int i = 0;
        if (name.length() > 0 && name.charAt(0) == '.') {
            i = 8;
        }
        return i | rv;
    }

    @Override // java.io.FileSystem
    public boolean checkAccess(File f, int access) {
        int mode;
        if (access == 1) {
            mode = OsConstants.X_OK;
        } else if (access == 2) {
            mode = OsConstants.W_OK;
        } else if (access == 4) {
            mode = OsConstants.R_OK;
        } else if (access == 8) {
            mode = OsConstants.F_OK;
        } else {
            throw new IllegalArgumentException("Bad access mode: " + access);
        }
        try {
            return Libcore.os.access(f.getPath(), mode);
        } catch (ErrnoException e) {
            return false;
        }
    }

    @Override // java.io.FileSystem
    public long getLastModifiedTime(File f) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return getLastModifiedTime0(f);
    }

    @Override // java.io.FileSystem
    public long getLength(File f) {
        try {
            return Libcore.os.stat(f.getPath()).st_size;
        } catch (ErrnoException e) {
            return 0;
        }
    }

    @Override // java.io.FileSystem
    public boolean setPermission(File f, int access, boolean enable, boolean owneronly) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return setPermission0(f, access, enable, owneronly);
    }

    @Override // java.io.FileSystem
    public boolean createFileExclusively(String path) throws IOException {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(path);
        return createFileExclusively0(path);
    }

    @Override // java.io.FileSystem
    public boolean delete(File f) {
        this.cache.clear();
        this.javaHomePrefixCache.clear();
        try {
            Libcore.os.remove(f.getPath());
            return true;
        } catch (ErrnoException e) {
            return false;
        }
    }

    @Override // java.io.FileSystem
    public String[] list(File f) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return list0(f);
    }

    @Override // java.io.FileSystem
    public boolean createDirectory(File f) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return createDirectory0(f);
    }

    @Override // java.io.FileSystem
    public boolean rename(File f1, File f2) {
        this.cache.clear();
        this.javaHomePrefixCache.clear();
        try {
            Libcore.os.rename(f1.getPath(), f2.getPath());
            return true;
        } catch (ErrnoException e) {
            return false;
        }
    }

    @Override // java.io.FileSystem
    public boolean setLastModifiedTime(File f, long time) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return setLastModifiedTime0(f, time);
    }

    @Override // java.io.FileSystem
    public boolean setReadOnly(File f) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return setReadOnly0(f);
    }

    @Override // java.io.FileSystem
    public File[] listRoots() {
        try {
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                security.checkRead("/");
            }
            return new File[]{new File("/")};
        } catch (SecurityException e) {
            return new File[0];
        }
    }

    @Override // java.io.FileSystem
    public long getSpace(File f, int t) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(f.getPath());
        return getSpace0(f, t);
    }

    @Override // java.io.FileSystem
    public int compare(File f1, File f2) {
        return f1.getPath().compareTo(f2.getPath());
    }

    @Override // java.io.FileSystem
    public int hashCode(File f) {
        return f.getPath().hashCode() ^ 1234321;
    }

    static {
        initIDs();
    }
}
