package java.lang;

import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

/* access modifiers changed from: package-private */
/* compiled from: ClassLoader */
public class BootClassLoader extends ClassLoader {
    private static BootClassLoader instance;

    public static synchronized BootClassLoader getInstance() {
        BootClassLoader bootClassLoader;
        synchronized (BootClassLoader.class) {
            if (instance == null) {
                instance = new BootClassLoader();
            }
            bootClassLoader = instance;
        }
        return bootClassLoader;
    }

    public BootClassLoader() {
        super(null);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class findClass(String str) {
        return Class.classForName(str, false, null);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public URL findResource(String str) {
        return VMClassLoader.getResource(str);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Enumeration findResources(String str) {
        return Collections.enumeration(VMClassLoader.getResources(str));
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Package getPackage(String str) {
        Package r0;
        if (str == null || str.isEmpty()) {
            return null;
        }
        synchronized (this) {
            r0 = super.getPackage(str);
            if (r0 == null) {
                r0 = definePackage(str, "Unknown", "0.0", "Unknown", "Unknown", "0.0", "Unknown", null);
            }
        }
        return r0;
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        return findResource(str);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class loadClass(String str, boolean z) {
        Class findLoadedClass = findLoadedClass(str);
        return findLoadedClass == null ? findClass(str) : findLoadedClass;
    }

    @Override // java.lang.ClassLoader
    public Enumeration getResources(String str) {
        return findResources(str);
    }
}
