package com.android.org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class ClassUtil {
    public static Class loadClass(Class sourceClass, final String className) {
        try {
            ClassLoader loader = sourceClass.getClassLoader();
            if (loader != null) {
                return loader.loadClass(className);
            }
            return (Class) AccessController.doPrivileged(new PrivilegedAction() {
                /* class com.android.org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        return Class.forName(className);
                    } catch (Exception e) {
                        return null;
                    }
                }
            });
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
