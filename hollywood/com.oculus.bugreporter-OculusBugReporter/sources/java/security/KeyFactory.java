package java.security;

import java.security.Provider;
import java.util.Iterator;
import sun.security.jca.GetInstance;
import sun.security.jca.Providers;
import sun.security.util.Debug;

public class KeyFactory {
    private static final Debug debug = Debug.getInstance("jca", "KeyFactory");
    private final String algorithm;
    private final Object lock = new Object();
    private Provider provider;
    private Iterator<Provider.Service> serviceIterator;
    private volatile KeyFactorySpi spi;

    protected KeyFactory(KeyFactorySpi keyFacSpi, Provider provider2, String algorithm2) {
        this.spi = keyFacSpi;
        this.provider = provider2;
        this.algorithm = algorithm2;
    }

    private KeyFactory(String algorithm2) throws NoSuchAlgorithmException {
        this.algorithm = algorithm2;
        this.serviceIterator = GetInstance.getServices("KeyFactory", algorithm2).iterator();
        if (nextSpi(null) == null) {
            throw new NoSuchAlgorithmException(algorithm2 + " KeyFactory not available");
        }
    }

    public static KeyFactory getInstance(String algorithm2) throws NoSuchAlgorithmException {
        return new KeyFactory(algorithm2);
    }

    public static KeyFactory getInstance(String algorithm2, String provider2) throws NoSuchAlgorithmException, NoSuchProviderException {
        Providers.checkBouncyCastleDeprecation(provider2, "KeyFactory", algorithm2);
        GetInstance.Instance instance = GetInstance.getInstance("KeyFactory", (Class<?>) KeyFactorySpi.class, algorithm2, provider2);
        return new KeyFactory((KeyFactorySpi) instance.impl, instance.provider, algorithm2);
    }

    public static KeyFactory getInstance(String algorithm2, Provider provider2) throws NoSuchAlgorithmException {
        Providers.checkBouncyCastleDeprecation(provider2, "KeyFactory", algorithm2);
        GetInstance.Instance instance = GetInstance.getInstance("KeyFactory", (Class<?>) KeyFactorySpi.class, algorithm2, provider2);
        return new KeyFactory((KeyFactorySpi) instance.impl, instance.provider, algorithm2);
    }

    public final Provider getProvider() {
        Provider provider2;
        synchronized (this.lock) {
            this.serviceIterator = null;
            provider2 = this.provider;
        }
        return provider2;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    private KeyFactorySpi nextSpi(KeyFactorySpi oldSpi) {
        synchronized (this.lock) {
            if (oldSpi != null) {
                if (oldSpi != this.spi) {
                    return this.spi;
                }
            }
            if (this.serviceIterator == null) {
                return null;
            }
            while (this.serviceIterator.hasNext()) {
                Provider.Service s = this.serviceIterator.next();
                try {
                    Object obj = s.newInstance(null);
                    if (obj instanceof KeyFactorySpi) {
                        KeyFactorySpi spi2 = (KeyFactorySpi) obj;
                        this.provider = s.getProvider();
                        this.spi = spi2;
                        return spi2;
                    }
                } catch (NoSuchAlgorithmException e) {
                }
            }
            this.serviceIterator = null;
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.security.PublicKey generatePublic(java.security.spec.KeySpec r5) throws java.security.spec.InvalidKeySpecException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            java.security.KeyFactorySpi r0 = r4.spi
            java.security.PublicKey r0 = r0.engineGeneratePublic(r5)
            return r0
        L_0x000b:
            r0 = 0
            java.security.KeyFactorySpi r1 = r4.spi
        L_0x000e:
            java.security.PublicKey r2 = r1.engineGeneratePublic(r5)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            java.security.KeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x0035
            boolean r2 = r0 instanceof java.lang.RuntimeException
            if (r2 != 0) goto L_0x0031
            boolean r2 = r0 instanceof java.security.spec.InvalidKeySpecException
            if (r2 == 0) goto L_0x0029
            r2 = r0
            java.security.spec.InvalidKeySpecException r2 = (java.security.spec.InvalidKeySpecException) r2
            throw r2
        L_0x0029:
            java.security.spec.InvalidKeySpecException r2 = new java.security.spec.InvalidKeySpecException
            java.lang.String r3 = "Could not generate public key"
            r2.<init>(r3, r0)
            throw r2
        L_0x0031:
            r2 = r0
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            throw r2
        L_0x0035:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.KeyFactory.generatePublic(java.security.spec.KeySpec):java.security.PublicKey");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.security.PrivateKey generatePrivate(java.security.spec.KeySpec r5) throws java.security.spec.InvalidKeySpecException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            java.security.KeyFactorySpi r0 = r4.spi
            java.security.PrivateKey r0 = r0.engineGeneratePrivate(r5)
            return r0
        L_0x000b:
            r0 = 0
            java.security.KeyFactorySpi r1 = r4.spi
        L_0x000e:
            java.security.PrivateKey r2 = r1.engineGeneratePrivate(r5)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            java.security.KeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x0035
            boolean r2 = r0 instanceof java.lang.RuntimeException
            if (r2 != 0) goto L_0x0031
            boolean r2 = r0 instanceof java.security.spec.InvalidKeySpecException
            if (r2 == 0) goto L_0x0029
            r2 = r0
            java.security.spec.InvalidKeySpecException r2 = (java.security.spec.InvalidKeySpecException) r2
            throw r2
        L_0x0029:
            java.security.spec.InvalidKeySpecException r2 = new java.security.spec.InvalidKeySpecException
            java.lang.String r3 = "Could not generate private key"
            r2.<init>(r3, r0)
            throw r2
        L_0x0031:
            r2 = r0
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            throw r2
        L_0x0035:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.KeyFactory.generatePrivate(java.security.spec.KeySpec):java.security.PrivateKey");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends java.security.spec.KeySpec> T getKeySpec(java.security.Key r5, java.lang.Class<T> r6) throws java.security.spec.InvalidKeySpecException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            java.security.KeyFactorySpi r0 = r4.spi
            java.security.spec.KeySpec r0 = r0.engineGetKeySpec(r5, r6)
            return r0
        L_0x000b:
            r0 = 0
            java.security.KeyFactorySpi r1 = r4.spi
        L_0x000e:
            java.security.spec.KeySpec r2 = r1.engineGetKeySpec(r5, r6)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            java.security.KeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x0035
            boolean r2 = r0 instanceof java.lang.RuntimeException
            if (r2 != 0) goto L_0x0031
            boolean r2 = r0 instanceof java.security.spec.InvalidKeySpecException
            if (r2 == 0) goto L_0x0029
            r2 = r0
            java.security.spec.InvalidKeySpecException r2 = (java.security.spec.InvalidKeySpecException) r2
            throw r2
        L_0x0029:
            java.security.spec.InvalidKeySpecException r2 = new java.security.spec.InvalidKeySpecException
            java.lang.String r3 = "Could not get key spec"
            r2.<init>(r3, r0)
            throw r2
        L_0x0031:
            r2 = r0
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            throw r2
        L_0x0035:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.KeyFactory.getKeySpec(java.security.Key, java.lang.Class):java.security.spec.KeySpec");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.security.Key translateKey(java.security.Key r5) throws java.security.InvalidKeyException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            java.security.KeyFactorySpi r0 = r4.spi
            java.security.Key r0 = r0.engineTranslateKey(r5)
            return r0
        L_0x000b:
            r0 = 0
            java.security.KeyFactorySpi r1 = r4.spi
        L_0x000e:
            java.security.Key r2 = r1.engineTranslateKey(r5)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            java.security.KeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x0035
            boolean r2 = r0 instanceof java.lang.RuntimeException
            if (r2 != 0) goto L_0x0031
            boolean r2 = r0 instanceof java.security.InvalidKeyException
            if (r2 == 0) goto L_0x0029
            r2 = r0
            java.security.InvalidKeyException r2 = (java.security.InvalidKeyException) r2
            throw r2
        L_0x0029:
            java.security.InvalidKeyException r2 = new java.security.InvalidKeyException
            java.lang.String r3 = "Could not translate key"
            r2.<init>(r3, r0)
            throw r2
        L_0x0031:
            r2 = r0
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            throw r2
        L_0x0035:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: java.security.KeyFactory.translateKey(java.security.Key):java.security.Key");
    }
}
