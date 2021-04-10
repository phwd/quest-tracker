package dalvik.system;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import sun.misc.CompoundEnumeration;

public final class DelegateLastClassLoader extends PathClassLoader {
    private final boolean delegateResourceLoading;

    public DelegateLastClassLoader(String dexPath, ClassLoader parent) {
        this(dexPath, (String) null, parent, true);
    }

    public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent) {
        this(dexPath, librarySearchPath, parent, true);
    }

    public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, boolean delegateResourceLoading2) {
        super(dexPath, librarySearchPath, parent);
        this.delegateResourceLoading = delegateResourceLoading2;
    }

    public DelegateLastClassLoader(String dexPath, String librarySearchPath, ClassLoader parent, ClassLoader[] sharedLibraryLoaders) {
        super(dexPath, librarySearchPath, parent, sharedLibraryLoaders);
        this.delegateResourceLoading = true;
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> cl = findLoadedClass(name);
        if (cl != null) {
            return cl;
        }
        try {
            return Object.class.getClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            try {
                return findClass(name);
            } catch (ClassNotFoundException ex) {
                try {
                    return getParent().loadClass(name);
                } catch (ClassNotFoundException e2) {
                    throw ex;
                }
            }
        }
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String name) {
        ClassLoader cl;
        URL resource = Object.class.getClassLoader().getResource(name);
        if (resource != null) {
            return resource;
        }
        URL resource2 = findResource(name);
        if (resource2 != null) {
            return resource2;
        }
        if (!this.delegateResourceLoading || (cl = getParent()) == null) {
            return null;
        }
        return cl.getResource(name);
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String name) throws IOException {
        Enumeration<URL>[] resources = new Enumeration[3];
        resources[0] = Object.class.getClassLoader().getResources(name);
        resources[1] = findResources(name);
        resources[2] = (getParent() == null || !this.delegateResourceLoading) ? null : getParent().getResources(name);
        return new CompoundEnumeration(resources);
    }
}
