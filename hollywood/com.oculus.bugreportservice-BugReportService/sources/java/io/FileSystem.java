package java.io;

/* access modifiers changed from: package-private */
public abstract class FileSystem {
    static boolean useCanonCaches = getBooleanProperty("sun.io.useCanonCaches", useCanonCaches);
    static boolean useCanonPrefixCache = getBooleanProperty("sun.io.useCanonPrefixCache", useCanonPrefixCache);

    public abstract String canonicalize(String str);

    public abstract boolean checkAccess(File file, int i);

    public abstract int compare(File file, File file2);

    public abstract boolean createDirectory(File file);

    public abstract boolean createFileExclusively(String str);

    public abstract boolean delete(File file);

    public abstract String fromURIPath(String str);

    public abstract int getBooleanAttributes(File file);

    public abstract String getDefaultParent();

    public abstract long getLastModifiedTime(File file);

    public abstract long getLength(File file);

    public abstract char getPathSeparator();

    public abstract char getSeparator();

    public abstract int hashCode(File file);

    public abstract String[] list(File file);

    public abstract String normalize(String str);

    public abstract int prefixLength(String str);

    public abstract String resolve(File file);

    public abstract String resolve(String str, String str2);

    FileSystem() {
    }

    private static boolean getBooleanProperty(String str, boolean z) {
        String property = System.getProperty(str);
        if (property == null) {
            return z;
        }
        return property.equalsIgnoreCase("true");
    }
}
