package java.net;

import java.security.AccessControlContext;

/* access modifiers changed from: package-private */
/* compiled from: URLClassLoader */
public final class FactoryURLClassLoader extends URLClassLoader {
    static {
        ClassLoader.registerAsParallelCapable();
    }

    FactoryURLClassLoader(URL[] urls, ClassLoader parent, AccessControlContext acc) {
        super(urls, parent, acc);
    }

    FactoryURLClassLoader(URL[] urls, AccessControlContext acc) {
        super(urls, acc);
    }

    @Override // java.lang.ClassLoader
    public final Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        int i;
        SecurityManager sm = System.getSecurityManager();
        if (!(sm == null || (i = name.lastIndexOf(46)) == -1)) {
            sm.checkPackageAccess(name.substring(0, i));
        }
        return super.loadClass(name, resolve);
    }
}
