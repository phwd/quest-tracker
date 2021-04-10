package java.lang;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

/* access modifiers changed from: package-private */
/* compiled from: ClassLoader */
public class BootClassLoader extends ClassLoader {
    private static BootClassLoader instance;

    @FindBugsSuppressWarnings({"DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED"})
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
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return Class.classForName(name, false, null);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public URL findResource(String name) {
        return VMClassLoader.getResource(name);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Enumeration<URL> findResources(String resName) throws IOException {
        return Collections.enumeration(VMClassLoader.getResources(resName));
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Package getPackage(String name) {
        Package pack;
        if (name == null || name.isEmpty()) {
            return null;
        }
        synchronized (this) {
            pack = super.getPackage(name);
            if (pack == null) {
                pack = definePackage(name, "Unknown", "0.0", "Unknown", "Unknown", "0.0", "Unknown", null);
            }
        }
        return pack;
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String resName) {
        return findResource(resName);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(className);
        if (clazz == null) {
            return findClass(className);
        }
        return clazz;
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String resName) throws IOException {
        return findResources(resName);
    }
}
