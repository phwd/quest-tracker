package java.security;

import java.security.Provider;
import java.util.Iterator;
import sun.security.jca.GetInstance;
import sun.security.util.Debug;

public class KeyFactory {
    private static final Debug debug = Debug.getInstance("jca", "KeyFactory");
    private final String algorithm;
    private final Object lock = new Object();
    private Provider provider;
    private Iterator serviceIterator;
    private volatile KeyFactorySpi spi;

    private KeyFactory(String str) {
        this.algorithm = str;
        this.serviceIterator = GetInstance.getServices("KeyFactory", str).iterator();
        if (nextSpi(null) == null) {
            throw new NoSuchAlgorithmException(str + " KeyFactory not available");
        }
    }

    public static KeyFactory getInstance(String str) {
        return new KeyFactory(str);
    }

    private KeyFactorySpi nextSpi(KeyFactorySpi keyFactorySpi) {
        synchronized (this.lock) {
            if (keyFactorySpi != null) {
                if (keyFactorySpi != this.spi) {
                    return this.spi;
                }
            }
            if (this.serviceIterator == null) {
                return null;
            }
            while (this.serviceIterator.hasNext()) {
                Provider.Service service = (Provider.Service) this.serviceIterator.next();
                try {
                    Object newInstance = service.newInstance(null);
                    if (newInstance instanceof KeyFactorySpi) {
                        KeyFactorySpi keyFactorySpi2 = (KeyFactorySpi) newInstance;
                        this.provider = service.getProvider();
                        this.spi = keyFactorySpi2;
                        return keyFactorySpi2;
                    }
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            this.serviceIterator = null;
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.security.PublicKey generatePublic(java.security.spec.KeySpec r4) {
        /*
            r3 = this;
            java.util.Iterator r0 = r3.serviceIterator
            if (r0 != 0) goto L_0x000b
            java.security.KeyFactorySpi r3 = r3.spi
            java.security.PublicKey r3 = r3.engineGeneratePublic(r4)
            return r3
        L_0x000b:
            r0 = 0
            java.security.KeyFactorySpi r1 = r3.spi
        L_0x000e:
            java.security.PublicKey r3 = r1.engineGeneratePublic(r4)     // Catch:{ Exception -> 0x0013 }
            return r3
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            java.security.KeyFactorySpi r1 = r3.nextSpi(r1)
            if (r1 != 0) goto L_0x000e
            boolean r3 = r0 instanceof java.lang.RuntimeException
            if (r3 != 0) goto L_0x0030
            boolean r3 = r0 instanceof java.security.spec.InvalidKeySpecException
            if (r3 == 0) goto L_0x0028
            java.security.spec.InvalidKeySpecException r0 = (java.security.spec.InvalidKeySpecException) r0
            throw r0
        L_0x0028:
            java.security.spec.InvalidKeySpecException r3 = new java.security.spec.InvalidKeySpecException
            java.lang.String r4 = "Could not generate public key"
            r3.<init>(r4, r0)
            throw r3
        L_0x0030:
            java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.KeyFactory.generatePublic(java.security.spec.KeySpec):java.security.PublicKey");
    }
}
