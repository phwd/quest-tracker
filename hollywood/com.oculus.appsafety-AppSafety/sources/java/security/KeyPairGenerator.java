package java.security;

import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import sun.security.jca.GetInstance;
import sun.security.jca.JCAUtil;
import sun.security.jca.Providers;

public abstract class KeyPairGenerator extends KeyPairGeneratorSpi {
    private final String algorithm;
    Provider provider;

    protected KeyPairGenerator(String algorithm2) {
        this.algorithm = algorithm2;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    private static KeyPairGenerator getInstance(GetInstance.Instance instance, String algorithm2) {
        KeyPairGenerator kpg;
        if (instance.impl instanceof KeyPairGenerator) {
            kpg = (KeyPairGenerator) instance.impl;
        } else {
            kpg = new Delegate((KeyPairGeneratorSpi) instance.impl, algorithm2);
        }
        kpg.provider = instance.provider;
        return kpg;
    }

    public static KeyPairGenerator getInstance(String algorithm2) throws NoSuchAlgorithmException {
        Iterator<Provider.Service> t = GetInstance.getServices("KeyPairGenerator", algorithm2).iterator();
        if (t.hasNext()) {
            NoSuchAlgorithmException failure = null;
            do {
                try {
                    GetInstance.Instance instance = GetInstance.getInstance(t.next(), KeyPairGeneratorSpi.class);
                    if (instance.impl instanceof KeyPairGenerator) {
                        return getInstance(instance, algorithm2);
                    }
                    return new Delegate(instance, t, algorithm2);
                } catch (NoSuchAlgorithmException e) {
                    if (failure == null) {
                        failure = e;
                    }
                    if (!t.hasNext()) {
                        throw failure;
                    }
                }
            } while (!t.hasNext());
            throw failure;
        }
        throw new NoSuchAlgorithmException(algorithm2 + " KeyPairGenerator not available");
    }

    public static KeyPairGenerator getInstance(String algorithm2, String provider2) throws NoSuchAlgorithmException, NoSuchProviderException {
        Providers.checkBouncyCastleDeprecation(provider2, "KeyPairGenerator", algorithm2);
        return getInstance(GetInstance.getInstance("KeyPairGenerator", (Class<?>) KeyPairGeneratorSpi.class, algorithm2, provider2), algorithm2);
    }

    public static KeyPairGenerator getInstance(String algorithm2, Provider provider2) throws NoSuchAlgorithmException {
        Providers.checkBouncyCastleDeprecation(provider2, "KeyPairGenerator", algorithm2);
        return getInstance(GetInstance.getInstance("KeyPairGenerator", (Class<?>) KeyPairGeneratorSpi.class, algorithm2, provider2), algorithm2);
    }

    public final Provider getProvider() {
        disableFailover();
        return this.provider;
    }

    /* access modifiers changed from: package-private */
    public void disableFailover() {
    }

    public void initialize(int keysize) {
        initialize(keysize, JCAUtil.getSecureRandom());
    }

    @Override // java.security.KeyPairGeneratorSpi
    public void initialize(int keysize, SecureRandom random) {
    }

    public void initialize(AlgorithmParameterSpec params) throws InvalidAlgorithmParameterException {
        initialize(params, JCAUtil.getSecureRandom());
    }

    @Override // java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec params, SecureRandom random) throws InvalidAlgorithmParameterException {
    }

    public final KeyPair genKeyPair() {
        return generateKeyPair();
    }

    @Override // java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        return null;
    }

    /* access modifiers changed from: private */
    public static final class Delegate extends KeyPairGenerator {
        private static final int I_NONE = 1;
        private static final int I_PARAMS = 3;
        private static final int I_SIZE = 2;
        private int initKeySize;
        private AlgorithmParameterSpec initParams;
        private SecureRandom initRandom;
        private int initType;
        private final Object lock = new Object();
        private Iterator<Provider.Service> serviceIterator;
        private volatile KeyPairGeneratorSpi spi;

        Delegate(KeyPairGeneratorSpi spi2, String algorithm) {
            super(algorithm);
            this.spi = spi2;
        }

        Delegate(GetInstance.Instance instance, Iterator<Provider.Service> serviceIterator2, String algorithm) {
            super(algorithm);
            this.spi = (KeyPairGeneratorSpi) instance.impl;
            this.provider = instance.provider;
            this.serviceIterator = serviceIterator2;
            this.initType = 1;
        }

        private KeyPairGeneratorSpi nextSpi(KeyPairGeneratorSpi oldSpi, boolean reinit) {
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
                        Object inst = s.newInstance(null);
                        if (inst instanceof KeyPairGeneratorSpi) {
                            if (!(inst instanceof KeyPairGenerator)) {
                                KeyPairGeneratorSpi spi2 = (KeyPairGeneratorSpi) inst;
                                if (reinit) {
                                    if (this.initType == 2) {
                                        spi2.initialize(this.initKeySize, this.initRandom);
                                    } else if (this.initType == 3) {
                                        spi2.initialize(this.initParams, this.initRandom);
                                    } else if (this.initType != 1) {
                                        throw new AssertionError((Object) ("KeyPairGenerator initType: " + this.initType));
                                    }
                                }
                                this.provider = s.getProvider();
                                this.spi = spi2;
                                return spi2;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                disableFailover();
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // java.security.KeyPairGenerator
        public void disableFailover() {
            this.serviceIterator = null;
            this.initType = 0;
            this.initParams = null;
            this.initRandom = null;
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(int keysize, SecureRandom random) {
            if (this.serviceIterator == null) {
                this.spi.initialize(keysize, random);
                return;
            }
            RuntimeException failure = null;
            KeyPairGeneratorSpi mySpi = this.spi;
            do {
                try {
                    mySpi.initialize(keysize, random);
                    this.initType = 2;
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

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x002d  */
        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initialize(java.security.spec.AlgorithmParameterSpec r5, java.security.SecureRandom r6) throws java.security.InvalidAlgorithmParameterException {
            /*
                r4 = this;
                java.util.Iterator<java.security.Provider$Service> r0 = r4.serviceIterator
                if (r0 != 0) goto L_0x000a
                java.security.KeyPairGeneratorSpi r0 = r4.spi
                r0.initialize(r5, r6)
                return
            L_0x000a:
                r0 = 0
                java.security.KeyPairGeneratorSpi r1 = r4.spi
            L_0x000d:
                r2 = 0
                r1.initialize(r5, r6)     // Catch:{ Exception -> 0x001b }
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
                java.security.KeyPairGeneratorSpi r1 = r4.nextSpi(r1, r2)
                if (r1 != 0) goto L_0x0031
                boolean r2 = r0 instanceof java.lang.RuntimeException
                if (r2 == 0) goto L_0x002d
                r2 = r0
                java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
                throw r2
            L_0x002d:
                r2 = r0
                java.security.InvalidAlgorithmParameterException r2 = (java.security.InvalidAlgorithmParameterException) r2
                throw r2
            L_0x0031:
                goto L_0x000d
            */
            throw new UnsupportedOperationException("Method not decompiled: java.security.KeyPairGenerator.Delegate.initialize(java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public KeyPair generateKeyPair() {
            if (this.serviceIterator == null) {
                return this.spi.generateKeyPair();
            }
            RuntimeException failure = null;
            KeyPairGeneratorSpi mySpi = this.spi;
            do {
                try {
                    return mySpi.generateKeyPair();
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
}
