package java.security;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import sun.security.jca.GetInstance;
import sun.security.jca.Providers;

public final class Security {
    private static final Properties props = new Properties();
    private static final Map spiMap = new ConcurrentHashMap();
    private static final AtomicInteger version = new AtomicInteger();

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        if (r1 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
        if (r1 != null) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0052 A[SYNTHETIC, Splitter:B:28:0x0052] */
    static {
        /*
            java.util.concurrent.atomic.AtomicInteger r0 = new java.util.concurrent.atomic.AtomicInteger
            r0.<init>()
            java.security.Security.version = r0
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.security.Security.props = r0
            r0 = 0
            r1 = 0
            java.lang.Class<java.security.Security> r2 = java.security.Security.class
            java.lang.String r3 = "security.properties"
            java.io.InputStream r2 = r2.getResourceAsStream(r3)     // Catch:{ IOException -> 0x003a }
            if (r2 != 0) goto L_0x0020
            java.lang.String r2 = "Could not find 'security.properties'."
            java.lang.System.logE(r2)     // Catch:{ IOException -> 0x003a }
            goto L_0x002c
        L_0x0020:
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x003a }
            r3.<init>(r2)     // Catch:{ IOException -> 0x003a }
            java.util.Properties r1 = java.security.Security.props     // Catch:{ IOException -> 0x0035, all -> 0x0032 }
            r1.load(r3)     // Catch:{ IOException -> 0x0035, all -> 0x0032 }
            r0 = 1
            r1 = r3
        L_0x002c:
            if (r1 == 0) goto L_0x0043
        L_0x002e:
            r1.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0043
        L_0x0032:
            r0 = move-exception
            r1 = r3
            goto L_0x0050
        L_0x0035:
            r2 = move-exception
            r1 = r3
            goto L_0x003b
        L_0x0038:
            r0 = move-exception
            goto L_0x0050
        L_0x003a:
            r2 = move-exception
        L_0x003b:
            java.lang.String r3 = "Could not load 'security.properties'"
            java.lang.System.logE(r3, r2)     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0043
            goto L_0x002e
        L_0x0043:
            if (r0 != 0) goto L_0x0048
            initializeStatic()
        L_0x0048:
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            java.security.Security.spiMap = r0
            return
        L_0x0050:
            if (r1 == 0) goto L_0x0055
            r1.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0055:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.Security.<clinit>():void");
    }

    private static void initializeStatic() {
        props.put("security.provider.1", "com.android.org.conscrypt.OpenSSLProvider");
        props.put("security.provider.2", "sun.security.provider.CertPathProvider");
        props.put("security.provider.3", "com.android.org.bouncycastle.jce.provider.BouncyCastleProvider");
        props.put("security.provider.4", "com.android.org.conscrypt.JSSEProvider");
    }

    private Security() {
    }

    public static Provider[] getProviders() {
        return Providers.getFullProviderList().toArray();
    }

    public static Provider getProvider(String str) {
        return Providers.getProviderList().getProvider(str);
    }

    private static Class getSpiClass(String str) {
        Class cls = (Class) spiMap.get(str);
        if (cls != null) {
            return cls;
        }
        try {
            Class cls2 = Class.forName("java.security." + str + "Spi");
            spiMap.put(str, cls2);
            return cls2;
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Spi class not found", e);
        }
    }

    static Object[] getImpl(String str, String str2, String str3) {
        if (str3 == null) {
            return GetInstance.getInstance(str2, getSpiClass(str2), str).toArray();
        }
        return GetInstance.getInstance(str2, getSpiClass(str2), str, str3).toArray();
    }

    static Object[] getImpl(String str, String str2, Provider provider) {
        return GetInstance.getInstance(str2, getSpiClass(str2), str, provider).toArray();
    }

    public static String getProperty(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            String property = props.getProperty(str);
            if (property != null) {
                return property.trim();
            }
            return property;
        }
        securityManager.checkPermission(new SecurityPermission("getProperty." + str));
        throw null;
    }

    public static void increaseVersion() {
        version.incrementAndGet();
    }

    public static int getVersion() {
        return version.get();
    }
}
