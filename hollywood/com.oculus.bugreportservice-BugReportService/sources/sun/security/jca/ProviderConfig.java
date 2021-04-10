package sun.security.jca;

import java.io.File;
import java.security.AccessController;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.ProviderException;
import sun.security.util.Debug;
import sun.security.util.PropertyExpander;

/* access modifiers changed from: package-private */
public final class ProviderConfig {
    private static final Class[] CL_STRING = {String.class};
    private static final Debug debug = Debug.getInstance("jca", "ProviderConfig");
    private final String argument;
    private final String className;
    private boolean isLoading;
    private volatile Provider provider;
    private int tries;

    ProviderConfig(String str, String str2) {
        if (str.equals("sun.security.pkcs11.SunPKCS11") && str2.equals("${java.home}/lib/security/sunpkcs11-solaris.cfg")) {
            checkSunPKCS11Solaris();
        }
        this.className = str;
        this.argument = expand(str2);
    }

    ProviderConfig(String str) {
        this(str, "");
    }

    private void checkSunPKCS11Solaris() {
        if (((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.security.jca.ProviderConfig.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Boolean run() {
                if (!new File("/usr/lib/libpkcs11.so").exists()) {
                    return Boolean.FALSE;
                }
                if ("false".equalsIgnoreCase(System.getProperty("sun.security.pkcs11.enable-solaris"))) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
        })) == Boolean.FALSE) {
            this.tries = 30;
        }
    }

    private boolean hasArgument() {
        return this.argument.length() != 0;
    }

    private boolean shouldLoad() {
        return this.tries < 30;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void disableLoad() {
        this.tries = 30;
    }

    /* access modifiers changed from: package-private */
    public boolean isLoaded() {
        return this.provider != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProviderConfig)) {
            return false;
        }
        ProviderConfig providerConfig = (ProviderConfig) obj;
        return this.className.equals(providerConfig.className) && this.argument.equals(providerConfig.argument);
    }

    public int hashCode() {
        return this.className.hashCode() + this.argument.hashCode();
    }

    public String toString() {
        if (!hasArgument()) {
            return this.className;
        }
        return this.className + "('" + this.argument + "')";
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public synchronized Provider getProvider() {
        Provider provider2 = this.provider;
        if (provider2 != null) {
            return provider2;
        }
        if (!shouldLoad()) {
            return null;
        }
        if (this.isLoading) {
            if (debug != null) {
                debug.println("Recursion loading provider: " + this);
                new Exception("Call trace").printStackTrace();
            }
            return null;
        }
        try {
            this.isLoading = true;
            this.tries++;
            Provider doLoadProvider = doLoadProvider();
            this.isLoading = false;
            this.provider = doLoadProvider;
            return doLoadProvider;
        } catch (Throwable th) {
            this.isLoading = false;
            throw th;
        }
    }

    private Provider doLoadProvider() {
        return (Provider) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.security.jca.ProviderConfig.AnonymousClass2 */

            /* JADX DEBUG: Failed to insert an additional move for type inference into block B:3:0x0020 */
            /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: sun.security.jca.ProviderConfig$2 */
            /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: sun.security.jca.ProviderConfig$2 */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r4v7, types: [java.security.Provider] */
            /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0047, code lost:
                if ((r0 instanceof java.lang.reflect.InvocationTargetException) != false) goto L_0x0049;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
                r0 = ((java.lang.reflect.InvocationTargetException) r0).getCause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0053, code lost:
                if (sun.security.jca.ProviderConfig.debug != null) goto L_0x0055;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
                r1 = sun.security.jca.ProviderConfig.debug;
                r1.println("Error loading provider " + r4.this$0);
                r0.printStackTrace();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0074, code lost:
                if ((r0 instanceof java.security.ProviderException) == false) goto L_0x0076;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0078, code lost:
                if ((r0 instanceof java.lang.UnsupportedOperationException) != false) goto L_0x007a;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x007a, code lost:
                r4.this$0.disableLoad();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
                throw ((java.security.ProviderException) r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
                return null;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
                return null;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x0043, code lost:
                return r4.this$0.initProvider(r4.this$0.className, java.lang.ClassLoader.getSystemClassLoader());
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x0044, code lost:
                r0 = e;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0033 */
            @Override // java.security.PrivilegedAction
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.security.Provider run() {
                /*
                // Method dump skipped, instructions count: 132
                */
                throw new UnsupportedOperationException("Method not decompiled: sun.security.jca.ProviderConfig.AnonymousClass2.run():java.security.Provider");
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Provider initProvider(String str, ClassLoader classLoader) {
        Class cls;
        Object obj;
        if (classLoader != null) {
            cls = classLoader.loadClass(str);
        } else {
            cls = Class.forName(str);
        }
        if (!hasArgument()) {
            obj = cls.newInstance();
        } else {
            obj = cls.getConstructor(CL_STRING).newInstance(this.argument);
        }
        if (obj instanceof Provider) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("Loaded provider " + obj);
            }
            return (Provider) obj;
        }
        Debug debug3 = debug;
        if (debug3 != null) {
            debug3.println(str + " is not a provider");
        }
        disableLoad();
        return null;
    }

    private static String expand(final String str) {
        if (!str.contains("${")) {
            return str;
        }
        return (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.security.jca.ProviderConfig.AnonymousClass3 */

            @Override // java.security.PrivilegedAction
            public String run() {
                try {
                    return PropertyExpander.expand(String.this);
                } catch (GeneralSecurityException e) {
                    throw new ProviderException(e);
                }
            }
        });
    }
}
