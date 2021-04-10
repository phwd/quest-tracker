package java.lang;

import dalvik.system.PathClassLoader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import sun.misc.CompoundEnumeration;

public abstract class ClassLoader {
    private final HashMap packages;
    private final ClassLoader parent;
    public final Map proxyCache;

    /* access modifiers changed from: private */
    public static class SystemClassLoader {
        public static ClassLoader loader = ClassLoader.createSystemClassLoader();
    }

    private static Void checkCreateClassLoader() {
        return null;
    }

    private Class findBootstrapClassOrNull(String str) {
        return null;
    }

    private static URL getBootstrapResource(String str) {
        return null;
    }

    private static Enumeration getBootstrapResources(String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public URL findResource(String str) {
        return null;
    }

    /* access modifiers changed from: private */
    public static ClassLoader createSystemClassLoader() {
        return new PathClassLoader(System.getProperty("java.class.path", "."), System.getProperty("java.library.path", ""), BootClassLoader.getInstance());
    }

    private ClassLoader(Void r1, ClassLoader classLoader) {
        this.proxyCache = new HashMap();
        this.packages = new HashMap();
        this.parent = classLoader;
    }

    protected ClassLoader(ClassLoader classLoader) {
        this(checkCreateClassLoader(), classLoader);
    }

    protected ClassLoader() {
        this(checkCreateClassLoader(), getSystemClassLoader());
    }

    public Class loadClass(String str) {
        return loadClass(str, false);
    }

    /* access modifiers changed from: protected */
    public Class loadClass(String str, boolean z) {
        Class findLoadedClass = findLoadedClass(str);
        if (findLoadedClass != null) {
            return findLoadedClass;
        }
        try {
            if (this.parent != null) {
                findLoadedClass = this.parent.loadClass(str, false);
            } else {
                findLoadedClass = findBootstrapClassOrNull(str);
            }
        } catch (ClassNotFoundException unused) {
        }
        return findLoadedClass == null ? findClass(str) : findLoadedClass;
    }

    /* access modifiers changed from: protected */
    public Class findClass(String str) {
        throw new ClassNotFoundException(str);
    }

    /* access modifiers changed from: protected */
    public final Class findLoadedClass(String str) {
        if (this == BootClassLoader.getInstance()) {
            this = null;
        }
        return VMClassLoader.findLoadedClass(this, str);
    }

    public URL getResource(String str) {
        URL url;
        ClassLoader classLoader = this.parent;
        if (classLoader != null) {
            url = classLoader.getResource(str);
        } else {
            url = getBootstrapResource(str);
        }
        return url == null ? findResource(str) : url;
    }

    public Enumeration getResources(String str) {
        Enumeration[] enumerationArr = new Enumeration[2];
        ClassLoader classLoader = this.parent;
        if (classLoader != null) {
            enumerationArr[0] = classLoader.getResources(str);
        } else {
            enumerationArr[0] = getBootstrapResources(str);
        }
        enumerationArr[1] = findResources(str);
        return new CompoundEnumeration(enumerationArr);
    }

    /* access modifiers changed from: protected */
    public Enumeration findResources(String str) {
        return Collections.emptyEnumeration();
    }

    public static URL getSystemResource(String str) {
        ClassLoader systemClassLoader = getSystemClassLoader();
        if (systemClassLoader == null) {
            return getBootstrapResource(str);
        }
        return systemClassLoader.getResource(str);
    }

    public static Enumeration getSystemResources(String str) {
        ClassLoader systemClassLoader = getSystemClassLoader();
        if (systemClassLoader == null) {
            return getBootstrapResources(str);
        }
        return systemClassLoader.getResources(str);
    }

    public InputStream getResourceAsStream(String str) {
        URL resource = getResource(str);
        if (resource == null) {
            return null;
        }
        try {
            return resource.openStream();
        } catch (IOException unused) {
            return null;
        }
    }

    public static InputStream getSystemResourceAsStream(String str) {
        URL systemResource = getSystemResource(str);
        if (systemResource == null) {
            return null;
        }
        try {
            return systemResource.openStream();
        } catch (IOException unused) {
            return null;
        }
    }

    public final ClassLoader getParent() {
        return this.parent;
    }

    public static ClassLoader getSystemClassLoader() {
        return SystemClassLoader.loader;
    }

    static ClassLoader getClassLoader(Class cls) {
        if (cls == null) {
            return null;
        }
        return cls.getClassLoader();
    }

    /* access modifiers changed from: protected */
    public Package definePackage(String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
        Package r13;
        synchronized (this.packages) {
            if (((Package) this.packages.get(str)) == null) {
                r13 = new Package(str, str2, str3, str4, str5, str6, str7, url, this);
                this.packages.put(str, r13);
            } else {
                throw new IllegalArgumentException(str);
            }
        }
        return r13;
    }

    /* access modifiers changed from: protected */
    public Package getPackage(String str) {
        Package r1;
        synchronized (this.packages) {
            r1 = (Package) this.packages.get(str);
        }
        return r1;
    }
}
