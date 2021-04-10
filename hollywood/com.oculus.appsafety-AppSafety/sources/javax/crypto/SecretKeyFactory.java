package javax.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Iterator;
import sun.security.jca.GetInstance;
import sun.security.jca.Providers;

public class SecretKeyFactory {
    private final String algorithm;
    private final Object lock = new Object();
    private Provider provider;
    private Iterator<Provider.Service> serviceIterator;
    private volatile SecretKeyFactorySpi spi;

    protected SecretKeyFactory(SecretKeyFactorySpi keyFacSpi, Provider provider2, String algorithm2) {
        this.spi = keyFacSpi;
        this.provider = provider2;
        this.algorithm = algorithm2;
    }

    private SecretKeyFactory(String algorithm2) throws NoSuchAlgorithmException {
        this.algorithm = algorithm2;
        this.serviceIterator = GetInstance.getServices("SecretKeyFactory", algorithm2).iterator();
        if (nextSpi(null) == null) {
            throw new NoSuchAlgorithmException(algorithm2 + " SecretKeyFactory not available");
        }
    }

    public static final SecretKeyFactory getInstance(String algorithm2) throws NoSuchAlgorithmException {
        return new SecretKeyFactory(algorithm2);
    }

    public static final SecretKeyFactory getInstance(String algorithm2, String provider2) throws NoSuchAlgorithmException, NoSuchProviderException {
        Providers.checkBouncyCastleDeprecation(provider2, "SecretKeyFactory", algorithm2);
        GetInstance.Instance instance = JceSecurity.getInstance("SecretKeyFactory", SecretKeyFactorySpi.class, algorithm2, provider2);
        return new SecretKeyFactory((SecretKeyFactorySpi) instance.impl, instance.provider, algorithm2);
    }

    public static final SecretKeyFactory getInstance(String algorithm2, Provider provider2) throws NoSuchAlgorithmException {
        Providers.checkBouncyCastleDeprecation(provider2, "SecretKeyFactory", algorithm2);
        GetInstance.Instance instance = JceSecurity.getInstance("SecretKeyFactory", SecretKeyFactorySpi.class, algorithm2, provider2);
        return new SecretKeyFactory((SecretKeyFactorySpi) instance.impl, instance.provider, algorithm2);
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

    private SecretKeyFactorySpi nextSpi(SecretKeyFactorySpi oldSpi) {
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
                if (JceSecurity.canUseProvider(s.getProvider())) {
                    try {
                        Object obj = s.newInstance(null);
                        if (obj instanceof SecretKeyFactorySpi) {
                            SecretKeyFactorySpi spi2 = (SecretKeyFactorySpi) obj;
                            this.provider = s.getProvider();
                            this.spi = spi2;
                            return spi2;
                        }
                    } catch (NoSuchAlgorithmException e) {
                    }
                }
            }
            this.serviceIterator = null;
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final javax.crypto.SecretKey generateSecret(java.security.spec.KeySpec r5) throws java.security.spec.InvalidKeySpecException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            javax.crypto.SecretKeyFactorySpi r0 = r4.spi
            javax.crypto.SecretKey r0 = r0.engineGenerateSecret(r5)
            return r0
        L_0x000b:
            r0 = 0
            javax.crypto.SecretKeyFactorySpi r1 = r4.spi
        L_0x000e:
            javax.crypto.SecretKey r2 = r1.engineGenerateSecret(r5)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            javax.crypto.SecretKeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x002d
            boolean r2 = r0 instanceof java.security.spec.InvalidKeySpecException
            if (r2 == 0) goto L_0x0025
            r2 = r0
            java.security.spec.InvalidKeySpecException r2 = (java.security.spec.InvalidKeySpecException) r2
            throw r2
        L_0x0025:
            java.security.spec.InvalidKeySpecException r2 = new java.security.spec.InvalidKeySpecException
            java.lang.String r3 = "Could not generate secret key"
            r2.<init>(r3, r0)
            throw r2
        L_0x002d:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.crypto.SecretKeyFactory.generateSecret(java.security.spec.KeySpec):javax.crypto.SecretKey");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.security.spec.KeySpec getKeySpec(javax.crypto.SecretKey r5, java.lang.Class<?> r6) throws java.security.spec.InvalidKeySpecException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            javax.crypto.SecretKeyFactorySpi r0 = r4.spi
            java.security.spec.KeySpec r0 = r0.engineGetKeySpec(r5, r6)
            return r0
        L_0x000b:
            r0 = 0
            javax.crypto.SecretKeyFactorySpi r1 = r4.spi
        L_0x000e:
            java.security.spec.KeySpec r2 = r1.engineGetKeySpec(r5, r6)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            javax.crypto.SecretKeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x002d
            boolean r2 = r0 instanceof java.security.spec.InvalidKeySpecException
            if (r2 == 0) goto L_0x0025
            r2 = r0
            java.security.spec.InvalidKeySpecException r2 = (java.security.spec.InvalidKeySpecException) r2
            throw r2
        L_0x0025:
            java.security.spec.InvalidKeySpecException r2 = new java.security.spec.InvalidKeySpecException
            java.lang.String r3 = "Could not get key spec"
            r2.<init>(r3, r0)
            throw r2
        L_0x002d:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.crypto.SecretKeyFactory.getKeySpec(javax.crypto.SecretKey, java.lang.Class):java.security.spec.KeySpec");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final javax.crypto.SecretKey translateKey(javax.crypto.SecretKey r5) throws java.security.InvalidKeyException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000b
            javax.crypto.SecretKeyFactorySpi r0 = r4.spi
            javax.crypto.SecretKey r0 = r0.engineTranslateKey(r5)
            return r0
        L_0x000b:
            r0 = 0
            javax.crypto.SecretKeyFactorySpi r1 = r4.spi
        L_0x000e:
            javax.crypto.SecretKey r2 = r1.engineTranslateKey(r5)     // Catch:{ Exception -> 0x0013 }
            return r2
        L_0x0013:
            r2 = move-exception
            if (r0 != 0) goto L_0x0017
            r0 = r2
        L_0x0017:
            javax.crypto.SecretKeyFactorySpi r1 = r4.nextSpi(r1)
            if (r1 != 0) goto L_0x002d
            boolean r2 = r0 instanceof java.security.InvalidKeyException
            if (r2 == 0) goto L_0x0025
            r2 = r0
            java.security.InvalidKeyException r2 = (java.security.InvalidKeyException) r2
            throw r2
        L_0x0025:
            java.security.InvalidKeyException r2 = new java.security.InvalidKeyException
            java.lang.String r3 = "Could not translate key"
            r2.<init>(r3, r0)
            throw r2
        L_0x002d:
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.crypto.SecretKeyFactory.translateKey(javax.crypto.SecretKey):javax.crypto.SecretKey");
    }
}
