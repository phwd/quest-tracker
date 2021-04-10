package javax.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import sun.security.jca.GetInstance;
import sun.security.jca.Providers;

public class KeyGenerator {
    private static final int I_NONE = 1;
    private static final int I_PARAMS = 3;
    private static final int I_RANDOM = 2;
    private static final int I_SIZE = 4;
    private final String algorithm;
    private int initKeySize;
    private AlgorithmParameterSpec initParams;
    private SecureRandom initRandom;
    private int initType;
    private final Object lock = new Object();
    private Provider provider;
    private Iterator<Provider.Service> serviceIterator;
    private volatile KeyGeneratorSpi spi;

    protected KeyGenerator(KeyGeneratorSpi keyGenSpi, Provider provider2, String algorithm2) {
        this.spi = keyGenSpi;
        this.provider = provider2;
        this.algorithm = algorithm2;
    }

    private KeyGenerator(String algorithm2) throws NoSuchAlgorithmException {
        this.algorithm = algorithm2;
        this.serviceIterator = GetInstance.getServices("KeyGenerator", algorithm2).iterator();
        this.initType = 1;
        if (nextSpi(null, false) == null) {
            throw new NoSuchAlgorithmException(algorithm2 + " KeyGenerator not available");
        }
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public static final KeyGenerator getInstance(String algorithm2) throws NoSuchAlgorithmException {
        return new KeyGenerator(algorithm2);
    }

    public static final KeyGenerator getInstance(String algorithm2, String provider2) throws NoSuchAlgorithmException, NoSuchProviderException {
        Providers.checkBouncyCastleDeprecation(provider2, "KeyGenerator", algorithm2);
        GetInstance.Instance instance = JceSecurity.getInstance("KeyGenerator", KeyGeneratorSpi.class, algorithm2, provider2);
        return new KeyGenerator((KeyGeneratorSpi) instance.impl, instance.provider, algorithm2);
    }

    public static final KeyGenerator getInstance(String algorithm2, Provider provider2) throws NoSuchAlgorithmException {
        Providers.checkBouncyCastleDeprecation(provider2, "KeyGenerator", algorithm2);
        GetInstance.Instance instance = JceSecurity.getInstance("KeyGenerator", KeyGeneratorSpi.class, algorithm2, provider2);
        return new KeyGenerator((KeyGeneratorSpi) instance.impl, instance.provider, algorithm2);
    }

    public final Provider getProvider() {
        Provider provider2;
        synchronized (this.lock) {
            disableFailover();
            provider2 = this.provider;
        }
        return provider2;
    }

    private KeyGeneratorSpi nextSpi(KeyGeneratorSpi oldSpi, boolean reinit) {
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
                        Object inst = s.newInstance(null);
                        if (inst instanceof KeyGeneratorSpi) {
                            KeyGeneratorSpi spi2 = (KeyGeneratorSpi) inst;
                            if (reinit) {
                                if (this.initType == 4) {
                                    spi2.engineInit(this.initKeySize, this.initRandom);
                                } else if (this.initType == 3) {
                                    spi2.engineInit(this.initParams, this.initRandom);
                                } else if (this.initType == 2) {
                                    spi2.engineInit(this.initRandom);
                                } else if (this.initType != 1) {
                                    throw new AssertionError((Object) ("KeyGenerator initType: " + this.initType));
                                }
                            }
                            this.provider = s.getProvider();
                            this.spi = spi2;
                            return spi2;
                        }
                    } catch (Exception e) {
                    }
                }
            }
            disableFailover();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void disableFailover() {
        this.serviceIterator = null;
        this.initType = 0;
        this.initParams = null;
        this.initRandom = null;
    }

    public final void init(SecureRandom random) {
        if (this.serviceIterator == null) {
            this.spi.engineInit(random);
            return;
        }
        RuntimeException failure = null;
        KeyGeneratorSpi mySpi = this.spi;
        do {
            try {
                mySpi.engineInit(random);
                this.initType = 2;
                this.initKeySize = 0;
                this.initParams = null;
                this.initRandom = random;
                return;
            } catch (RuntimeException e) {
                if (failure == null) {
                    failure = e;
                }
                mySpi = nextSpi(mySpi, false);
                if (mySpi == null) {
                    throw failure;
                }
            }
        } while (mySpi == null);
        throw failure;
    }

    public final void init(AlgorithmParameterSpec params) throws InvalidAlgorithmParameterException {
        init(params, JceSecurity.RANDOM);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init(java.security.spec.AlgorithmParameterSpec r5, java.security.SecureRandom r6) throws java.security.InvalidAlgorithmParameterException {
        /*
            r4 = this;
            java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
            if (r0 != 0) goto L_0x000a
            javax.crypto.KeyGeneratorSpi r0 = r4.spi
            r0.engineInit(r5, r6)
            return
        L_0x000a:
            r0 = 0
            javax.crypto.KeyGeneratorSpi r1 = r4.spi
        L_0x000d:
            r2 = 0
            r1.engineInit(r5, r6)     // Catch:{ Exception -> 0x001b }
            r3 = 3
            r4.initType = r3     // Catch:{ Exception -> 0x001b }
            r4.initKeySize = r2     // Catch:{ Exception -> 0x001b }
            r4.initParams = r5     // Catch:{ Exception -> 0x001b }
            r4.initRandom = r6     // Catch:{ Exception -> 0x001b }
            return
        L_0x001b:
            r3 = move-exception
            if (r0 != 0) goto L_0x001f
            r0 = r3
        L_0x001f:
            javax.crypto.KeyGeneratorSpi r1 = r4.nextSpi(r1, r2)
            if (r1 != 0) goto L_0x003d
            boolean r2 = r0 instanceof java.security.InvalidAlgorithmParameterException
            if (r2 != 0) goto L_0x0039
            boolean r2 = r0 instanceof java.lang.RuntimeException
            if (r2 == 0) goto L_0x0031
            r2 = r0
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            throw r2
        L_0x0031:
            java.security.InvalidAlgorithmParameterException r2 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r3 = "init() failed"
            r2.<init>(r3, r0)
            throw r2
        L_0x0039:
            r2 = r0
            java.security.InvalidAlgorithmParameterException r2 = (java.security.InvalidAlgorithmParameterException) r2
            throw r2
        L_0x003d:
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.crypto.KeyGenerator.init(java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
    }

    public final void init(int keysize) {
        init(keysize, JceSecurity.RANDOM);
    }

    public final void init(int keysize, SecureRandom random) {
        if (this.serviceIterator == null) {
            this.spi.engineInit(keysize, random);
            return;
        }
        RuntimeException failure = null;
        KeyGeneratorSpi mySpi = this.spi;
        do {
            try {
                mySpi.engineInit(keysize, random);
                this.initType = 4;
                this.initKeySize = keysize;
                this.initParams = null;
                this.initRandom = random;
                return;
            } catch (RuntimeException e) {
                if (failure == null) {
                    failure = e;
                }
                mySpi = nextSpi(mySpi, false);
                if (mySpi == null) {
                    throw failure;
                }
            }
        } while (mySpi == null);
        throw failure;
    }

    public final SecretKey generateKey() {
        if (this.serviceIterator == null) {
            return this.spi.engineGenerateKey();
        }
        RuntimeException failure = null;
        KeyGeneratorSpi mySpi = this.spi;
        do {
            try {
                return mySpi.engineGenerateKey();
            } catch (RuntimeException e) {
                if (failure == null) {
                    failure = e;
                }
                mySpi = nextSpi(mySpi, true);
                if (mySpi == null) {
                    throw failure;
                }
            }
        } while (mySpi == null);
        throw failure;
    }
}
