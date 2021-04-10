package android.icu.impl;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class ClassLoaderUtil {
    private static volatile ClassLoader BOOTSTRAP_CLASSLOADER;

    /* access modifiers changed from: private */
    public static class BootstrapClassLoader extends ClassLoader {
        BootstrapClassLoader() {
            super(Object.class.getClassLoader());
        }
    }

    private static ClassLoader getBootstrapClassLoader() {
        ClassLoader classLoader;
        if (BOOTSTRAP_CLASSLOADER == null) {
            synchronized (ClassLoaderUtil.class) {
                if (BOOTSTRAP_CLASSLOADER == null) {
                    if (System.getSecurityManager() != null) {
                        classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
                            /* class android.icu.impl.ClassLoaderUtil.AnonymousClass1 */

                            @Override // java.security.PrivilegedAction
                            public BootstrapClassLoader run() {
                                return new BootstrapClassLoader();
                            }
                        });
                    } else {
                        classLoader = new BootstrapClassLoader();
                    }
                    BOOTSTRAP_CLASSLOADER = classLoader;
                }
            }
        }
        return BOOTSTRAP_CLASSLOADER;
    }

    public static ClassLoader getClassLoader(Class cls) {
        ClassLoader classLoader = cls.getClassLoader();
        return classLoader == null ? getClassLoader() : classLoader;
    }

    public static ClassLoader getClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        return systemClassLoader == null ? getBootstrapClassLoader() : systemClassLoader;
    }
}
