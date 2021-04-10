package android.icu.impl;

import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.MissingResourceException;

public final class ICUData {
    private static InputStream getStream(final Class cls, final String str, boolean z) {
        InputStream inputStream;
        if (System.getSecurityManager() != null) {
            inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
                /* class android.icu.impl.ICUData.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public InputStream run() {
                    return Class.this.getResourceAsStream(str);
                }
            });
        } else {
            inputStream = cls.getResourceAsStream(str);
        }
        if (inputStream != null || !z) {
            return inputStream;
        }
        throw new MissingResourceException("could not locate data " + str, cls.getPackage().getName(), str);
    }

    static InputStream getStream(final ClassLoader classLoader, final String str, boolean z) {
        InputStream inputStream;
        if (System.getSecurityManager() != null) {
            inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
                /* class android.icu.impl.ICUData.AnonymousClass3 */

                @Override // java.security.PrivilegedAction
                public InputStream run() {
                    return ClassLoader.this.getResourceAsStream(str);
                }
            });
        } else {
            inputStream = classLoader.getResourceAsStream(str);
        }
        if (inputStream != null || !z) {
            return inputStream;
        }
        throw new MissingResourceException("could not locate data", classLoader.toString(), str);
    }

    public static InputStream getStream(ClassLoader classLoader, String str) {
        return getStream(classLoader, str, false);
    }

    public static InputStream getStream(String str) {
        return getStream(ICUData.class, str, false);
    }
}
