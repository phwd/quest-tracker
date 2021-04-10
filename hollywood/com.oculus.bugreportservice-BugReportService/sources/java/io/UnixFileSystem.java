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

    private native String canonicalize0(String str);

    private native boolean createDirectory0(File file);

    private native boolean createFileExclusively0(String str);

    private native int getBooleanAttributes0(String str);

    private native long getLastModifiedTime0(File file);

    private static native void initIDs();

    private native String[] list0(File file);

    @Override // java.io.FileSystem
    public String getDefaultParent() {
        return "/";
    }

    @Override // java.io.FileSystem
    public char getSeparator() {
        return this.slash;
    }

    @Override // java.io.FileSystem
    public char getPathSeparator() {
        return this.colon;
    }

    @Override // java.io.FileSystem
    public String normalize(String str) {
        int length = str.length();
        char[] charArray = str.toCharArray();
        int i = 0;
        char c = 0;
        int i2 = 0;
        while (i < length) {
            char c2 = charArray[i];
            if (c2 != '/' || c != '/') {
                charArray[i2] = c2;
                i2++;
            }
            i++;
            c = c2;
        }
        if (c == '/' && length > 1) {
            i2--;
        }
        if (i2 == length) {
            return str;
        }
        new String(charArray, 0, i2);
        throw null;
    }

    @Override // java.io.FileSystem
    public int prefixLength(String str) {
        if (str.length() != 0 && str.charAt(0) == '/') {
            return 1;
        }
        return 0;
    }

    @Override // java.io.FileSystem
    public String resolve(String str, String str2) {
        if (str2.isEmpty() || str2.equals("/")) {
            return str;
        }
        if (str2.charAt(0) == '/') {
            if (str.equals("/")) {
                return str2;
            }
            return str + str2;
        } else if (str.equals("/")) {
            return str + str2;
        } else {
            return str + '/' + str2;
        }
    }

    @Override // java.io.FileSystem
    public String fromURIPath(String str) {
        return (!str.endsWith("/") || str.length() <= 1) ? str : str.substring(0, str.length() - 1);
    }

    public boolean isAbsolute(File file) {
        return file.getPrefixLength() != 0;
    }

    @Override // java.io.FileSystem
    public String resolve(File file) {
        if (isAbsolute(file)) {
            return file.getPath();
        }
        return resolve(System.getProperty("user.dir"), file.getPath());
    }

    @Override // java.io.FileSystem
    public String canonicalize(String str) {
        String parentOrNull;
        String str2;
        if (!FileSystem.useCanonCaches) {
            return canonicalize0(str);
        }
        String str3 = this.cache.get(str);
        if (str3 == null) {
            String str4 = null;
            if (!(!FileSystem.useCanonPrefixCache || (str4 = parentOrNull(str)) == null || (str2 = this.javaHomePrefixCache.get(str4)) == null)) {
                String substring = str.substring(str4.length() + 1);
                String str5 = str2 + this.slash + substring;
                this.cache.put(str4 + this.slash + substring, str5);
                str3 = str5;
            }
            if (str3 == null) {
                BlockGuard.getThreadPolicy().onReadFromDisk();
                BlockGuard.getVmPolicy().onPathAccess(str);
                str3 = canonicalize0(str);
                this.cache.put(str, str3);
                if (FileSystem.useCanonPrefixCache && str4 != null && str4.startsWith(this.javaHome) && (parentOrNull = parentOrNull(str3)) != null && parentOrNull.equals(str4)) {
                    File file = new File(str3);
                    if (file.exists() && !file.isDirectory()) {
                        this.javaHomePrefixCache.put(str4, parentOrNull);
                    }
                }
            }
        }
        return str3;
    }

    static String parentOrNull(String str) {
        if (str == null) {
            return null;
        }
        char c = File.separatorChar;
        int length = str.length() - 1;
        int i = 0;
        int i2 = 0;
        for (int i3 = length; i3 > 0; i3--) {
            char charAt = str.charAt(i3);
            if (charAt == '.') {
                i++;
                if (i >= 2) {
                    return null;
                }
            } else if (charAt != c) {
                i2++;
                i = 0;
            } else if ((i == 1 && i2 == 0) || i3 == 0 || i3 >= length - 1 || str.charAt(i3 - 1) == c) {
                return null;
            } else {
                return str.substring(0, i3);
            }
        }
        return null;
    }

    @Override // java.io.FileSystem
    public int getBooleanAttributes(File file) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(file.getPath());
        int booleanAttributes0 = getBooleanAttributes0(file.getPath());
        String name = file.getName();
        int i = 0;
        if (name.length() > 0 && name.charAt(0) == '.') {
            i = 8;
        }
        return booleanAttributes0 | i;
    }

    @Override // java.io.FileSystem
    public boolean checkAccess(File file, int i) {
        int i2;
        if (i == 1) {
            i2 = OsConstants.X_OK;
        } else if (i == 2) {
            i2 = OsConstants.W_OK;
        } else if (i == 4) {
            i2 = OsConstants.R_OK;
        } else if (i == 8) {
            i2 = OsConstants.F_OK;
        } else {
            throw new IllegalArgumentException("Bad access mode: " + i);
        }
        try {
            return Libcore.os.access(file.getPath(), i2);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // java.io.FileSystem
    public long getLastModifiedTime(File file) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(file.getPath());
        return getLastModifiedTime0(file);
    }

    @Override // java.io.FileSystem
    public long getLength(File file) {
        try {
            return Libcore.os.stat(file.getPath()).st_size;
        } catch (ErrnoException unused) {
            return 0;
        }
    }

    @Override // java.io.FileSystem
    public boolean createFileExclusively(String str) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(str);
        return createFileExclusively0(str);
    }

    @Override // java.io.FileSystem
    public boolean delete(File file) {
        this.cache.clear();
        this.javaHomePrefixCache.clear();
        try {
            Libcore.os.remove(file.getPath());
            return true;
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // java.io.FileSystem
    public String[] list(File file) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        BlockGuard.getVmPolicy().onPathAccess(file.getPath());
        return list0(file);
    }

    @Override // java.io.FileSystem
    public boolean createDirectory(File file) {
        BlockGuard.getThreadPolicy().onWriteToDisk();
        BlockGuard.getVmPolicy().onPathAccess(file.getPath());
        return createDirectory0(file);
    }

    @Override // java.io.FileSystem
    public int compare(File file, File file2) {
        return file.getPath().compareTo(file2.getPath());
    }

    @Override // java.io.FileSystem
    public int hashCode(File file) {
        return file.getPath().hashCode() ^ 1234321;
    }

    static {
        initIDs();
    }
}
