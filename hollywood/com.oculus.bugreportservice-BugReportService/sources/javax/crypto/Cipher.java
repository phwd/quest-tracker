package javax.crypto;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.ProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import sun.security.jca.Providers;

public class Cipher {
    private boolean initialized = false;
    private int opmode = 0;
    private Provider provider;
    private CipherSpi spi;
    private final SpiAndProviderUpdater spiAndProviderUpdater;
    private final String[] tokenizedTransformation;
    private final String transformation;

    /* access modifiers changed from: package-private */
    public enum InitType {
        KEY,
        ALGORITHM_PARAMS,
        ALGORITHM_PARAM_SPEC
    }

    /* access modifiers changed from: package-private */
    public enum NeedToSet {
        NONE,
        MODE,
        PADDING,
        BOTH
    }

    private Cipher(CipherSpi cipherSpi, Provider provider2, String str, String[] strArr) {
        this.spi = cipherSpi;
        this.provider = provider2;
        this.transformation = str;
        this.tokenizedTransformation = strArr;
        this.spiAndProviderUpdater = new SpiAndProviderUpdater(provider2, cipherSpi);
    }

    private static String[] tokenizeTransformation(String str) {
        if (str == null || str.isEmpty()) {
            throw new NoSuchAlgorithmException("No transformation given");
        }
        String[] strArr = new String[3];
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/");
        int i = 0;
        while (stringTokenizer.hasMoreTokens() && i < 3) {
            try {
                strArr[i] = stringTokenizer.nextToken().trim();
                i++;
            } catch (NoSuchElementException unused) {
                throw new NoSuchAlgorithmException("Invalid transformation format:" + str);
            }
        }
        if (i == 0 || i == 2 || stringTokenizer.hasMoreTokens()) {
            throw new NoSuchAlgorithmException("Invalid transformation format:" + str);
        } else if (strArr[0] != null && strArr[0].length() != 0) {
            return strArr;
        } else {
            throw new NoSuchAlgorithmException("Invalid transformation:algorithm not specified-" + str);
        }
    }

    public static final Cipher getInstance(String str, Provider provider2) {
        if (provider2 != null) {
            return createCipher(str, provider2);
        }
        throw new IllegalArgumentException("Missing provider");
    }

    static final Cipher createCipher(String str, Provider provider2) {
        Providers.checkBouncyCastleDeprecation(provider2, "Cipher", str);
        String[] strArr = tokenizeTransformation(str);
        try {
            if (tryCombinations(null, provider2, strArr) != null) {
                return new Cipher(null, provider2, str, strArr);
            }
            if (provider2 == null) {
                throw new NoSuchAlgorithmException("No provider found for " + str);
            }
            throw new NoSuchAlgorithmException("Provider " + provider2.getName() + " does not provide " + str);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new IllegalStateException("Key/Algorithm excepton despite not passing one", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateProviderIfNeeded() {
        try {
            this.spiAndProviderUpdater.updateAndGetSpiAndProvider(null, this.spi, this.provider);
        } catch (Exception e) {
            ProviderException providerException = new ProviderException("Could not construct CipherSpi instance");
            providerException.initCause(e);
            throw providerException;
        }
    }

    private void chooseProvider(InitType initType, int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        try {
            this.spiAndProviderUpdater.updateAndGetSpiAndProvider(new InitParams(initType, i, key, secureRandom, algorithmParameterSpec, algorithmParameters), this.spi, this.provider);
        } catch (Exception e) {
            if (e instanceof InvalidKeyException) {
                throw ((InvalidKeyException) e);
            } else if (e instanceof InvalidAlgorithmParameterException) {
                throw ((InvalidAlgorithmParameterException) e);
            } else if (!(e instanceof RuntimeException)) {
                String name = key != null ? key.getClass().getName() : "(null)";
                throw new InvalidKeyException("No installed provider supports this key: " + name, e);
            } else {
                throw ((RuntimeException) e);
            }
        }
    }

    private static void checkOpmode(int i) {
        if (i < 1 || i > 4) {
            throw new InvalidParameterException("Invalid operation mode");
        }
    }

    public final void init(int i, Key key) {
        init(i, key, JceSecurity.RANDOM);
    }

    public final void init(int i, Key key, SecureRandom secureRandom) {
        this.initialized = false;
        checkOpmode(i);
        try {
            chooseProvider(InitType.KEY, i, key, null, null, secureRandom);
            this.initialized = true;
            this.opmode = i;
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e);
        }
    }

    private void checkCipherState() {
        if (this.initialized) {
            int i = this.opmode;
            if (i != 1 && i != 2) {
                throw new IllegalStateException("Cipher not initialized for encryption/decryption");
            }
            return;
        }
        throw new IllegalStateException("Cipher not initialized");
    }

    public final byte[] update(byte[] bArr, int i, int i2) {
        checkCipherState();
        if (bArr == null || i < 0 || i2 > bArr.length - i || i2 < 0) {
            throw new IllegalArgumentException("Bad arguments");
        }
        updateProviderIfNeeded();
        if (i2 == 0) {
            return null;
        }
        return this.spi.engineUpdate(bArr, i, i2);
    }

    public final byte[] doFinal(byte[] bArr) {
        checkCipherState();
        if (bArr != null) {
            updateProviderIfNeeded();
            return this.spi.engineDoFinal(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("Null input buffer");
    }

    static boolean matchAttribute(Provider.Service service, String str, String str2) {
        String attribute;
        if (str2 == null || (attribute = service.getAttribute(str)) == null) {
            return true;
        }
        return str2.toUpperCase(Locale.US).matches(attribute.toUpperCase(Locale.US));
    }

    /* access modifiers changed from: package-private */
    public static class Transform {
        private final String name;
        private final NeedToSet needToSet;

        public Transform(String str, NeedToSet needToSet2) {
            this.name = str;
            this.needToSet = needToSet2;
        }
    }

    /* access modifiers changed from: package-private */
    public static class InitParams {
        final InitType initType;
        final Key key;
        final int opmode;
        final AlgorithmParameters params;
        final SecureRandom random;
        final AlgorithmParameterSpec spec;

        InitParams(InitType initType2, int i, Key key2, SecureRandom secureRandom, AlgorithmParameterSpec algorithmParameterSpec, AlgorithmParameters algorithmParameters) {
            this.initType = initType2;
            this.opmode = i;
            this.key = key2;
            this.random = secureRandom;
            this.spec = algorithmParameterSpec;
            this.params = algorithmParameters;
        }
    }

    /* access modifiers changed from: package-private */
    public class SpiAndProviderUpdater {
        private final Object initSpiLock = new Object();
        private final Provider specifiedProvider;
        private final CipherSpi specifiedSpi;

        SpiAndProviderUpdater(Provider provider, CipherSpi cipherSpi) {
            this.specifiedProvider = provider;
            this.specifiedSpi = cipherSpi;
        }

        /* access modifiers changed from: package-private */
        public void setCipherSpiImplAndProvider(CipherSpi cipherSpi, Provider provider) {
            Cipher.this.spi = cipherSpi;
            Cipher.this.provider = provider;
        }

        /* access modifiers changed from: package-private */
        public CipherSpiAndProvider updateAndGetSpiAndProvider(InitParams initParams, CipherSpi cipherSpi, Provider provider) {
            CipherSpi cipherSpi2 = this.specifiedSpi;
            if (cipherSpi2 != null) {
                return new CipherSpiAndProvider(cipherSpi2, provider);
            }
            synchronized (this.initSpiLock) {
                if (cipherSpi == null || initParams != null) {
                    CipherSpiAndProvider tryCombinations = Cipher.tryCombinations(initParams, this.specifiedProvider, Cipher.this.tokenizedTransformation);
                    if (tryCombinations != null) {
                        setCipherSpiImplAndProvider(tryCombinations.cipherSpi, tryCombinations.provider);
                        return new CipherSpiAndProvider(tryCombinations.cipherSpi, tryCombinations.provider);
                    }
                    throw new ProviderException("No provider found for " + Arrays.toString(Cipher.this.tokenizedTransformation));
                }
                return new CipherSpiAndProvider(cipherSpi, provider);
            }
        }
    }

    static CipherSpiAndProvider tryCombinations(InitParams initParams, Provider provider2, String[] strArr) {
        Exception exc;
        Key key;
        ArrayList arrayList = new ArrayList();
        if (!(strArr[1] == null || strArr[2] == null)) {
            arrayList.add(new Transform(strArr[0] + "/" + strArr[1] + "/" + strArr[2], NeedToSet.NONE));
        }
        if (strArr[1] != null) {
            arrayList.add(new Transform(strArr[0] + "/" + strArr[1], NeedToSet.PADDING));
        }
        if (strArr[2] != null) {
            arrayList.add(new Transform(strArr[0] + "//" + strArr[2], NeedToSet.MODE));
        }
        arrayList.add(new Transform(strArr[0], NeedToSet.BOTH));
        if (provider2 != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Transform transform = (Transform) it.next();
                Provider.Service service = provider2.getService("Cipher", transform.name);
                if (service != null) {
                    return tryTransformWithProvider(initParams, strArr, transform.needToSet, service);
                }
            }
            exc = null;
        } else {
            Provider[] providers = Security.getProviders();
            exc = null;
            for (Provider provider3 : providers) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    Transform transform2 = (Transform) it2.next();
                    Provider.Service service2 = provider3.getService("Cipher", transform2.name);
                    if (service2 != null && (initParams == null || (key = initParams.key) == null || service2.supportsParameter(key))) {
                        try {
                            CipherSpiAndProvider tryTransformWithProvider = tryTransformWithProvider(initParams, strArr, transform2.needToSet, service2);
                            if (tryTransformWithProvider != null) {
                                return tryTransformWithProvider;
                            }
                        } catch (Exception e) {
                            if (exc == null) {
                                exc = e;
                            }
                        }
                    }
                }
            }
        }
        if (exc instanceof InvalidKeyException) {
            throw ((InvalidKeyException) exc);
        } else if (exc instanceof InvalidAlgorithmParameterException) {
            throw ((InvalidAlgorithmParameterException) exc);
        } else if (exc instanceof RuntimeException) {
            throw exc;
        } else if (exc != null) {
            throw new InvalidKeyException("No provider can be initialized with given key", exc);
        } else if (initParams == null || initParams.key == null) {
            return null;
        } else {
            throw new InvalidKeyException("No provider offers " + Arrays.toString(strArr) + " for " + initParams.key.getAlgorithm() + " key of class " + initParams.key.getClass().getName() + " and export format " + initParams.key.getFormat());
        }
    }

    /* access modifiers changed from: package-private */
    public static class CipherSpiAndProvider {
        CipherSpi cipherSpi;
        Provider provider;

        CipherSpiAndProvider(CipherSpi cipherSpi2, Provider provider2) {
            this.cipherSpi = cipherSpi2;
            this.provider = provider2;
        }
    }

    static CipherSpiAndProvider tryTransformWithProvider(InitParams initParams, String[] strArr, NeedToSet needToSet, Provider.Service service) {
        try {
            if (matchAttribute(service, "SupportedModes", strArr[1])) {
                if (matchAttribute(service, "SupportedPaddings", strArr[2])) {
                    CipherSpiAndProvider cipherSpiAndProvider = new CipherSpiAndProvider((CipherSpi) service.newInstance(null), service.getProvider());
                    if (cipherSpiAndProvider.cipherSpi != null) {
                        if (cipherSpiAndProvider.provider != null) {
                            CipherSpi cipherSpi = cipherSpiAndProvider.cipherSpi;
                            if ((needToSet == NeedToSet.MODE || needToSet == NeedToSet.BOTH) && strArr[1] != null) {
                                cipherSpi.engineSetMode(strArr[1]);
                            }
                            if ((needToSet == NeedToSet.PADDING || needToSet == NeedToSet.BOTH) && strArr[2] != null) {
                                cipherSpi.engineSetPadding(strArr[2]);
                            }
                            if (initParams != null) {
                                int i = AnonymousClass1.$SwitchMap$javax$crypto$Cipher$InitType[initParams.initType.ordinal()];
                                if (i == 1) {
                                    cipherSpi.engineInit(initParams.opmode, initParams.key, initParams.params, initParams.random);
                                } else if (i == 2) {
                                    cipherSpi.engineInit(initParams.opmode, initParams.key, initParams.spec, initParams.random);
                                } else if (i == 3) {
                                    cipherSpi.engineInit(initParams.opmode, initParams.key, initParams.random);
                                } else {
                                    throw new AssertionError((Object) "This should never be reached");
                                }
                            }
                            return new CipherSpiAndProvider(cipherSpi, cipherSpiAndProvider.provider);
                        }
                    }
                }
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: javax.crypto.Cipher$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$crypto$Cipher$InitType = new int[InitType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                javax.crypto.Cipher$InitType[] r0 = javax.crypto.Cipher.InitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                javax.crypto.Cipher.AnonymousClass1.$SwitchMap$javax$crypto$Cipher$InitType = r0
                int[] r0 = javax.crypto.Cipher.AnonymousClass1.$SwitchMap$javax$crypto$Cipher$InitType     // Catch:{ NoSuchFieldError -> 0x0014 }
                javax.crypto.Cipher$InitType r1 = javax.crypto.Cipher.InitType.ALGORITHM_PARAMS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = javax.crypto.Cipher.AnonymousClass1.$SwitchMap$javax$crypto$Cipher$InitType     // Catch:{ NoSuchFieldError -> 0x001f }
                javax.crypto.Cipher$InitType r1 = javax.crypto.Cipher.InitType.ALGORITHM_PARAM_SPEC     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = javax.crypto.Cipher.AnonymousClass1.$SwitchMap$javax$crypto$Cipher$InitType     // Catch:{ NoSuchFieldError -> 0x002a }
                javax.crypto.Cipher$InitType r1 = javax.crypto.Cipher.InitType.KEY     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.crypto.Cipher.AnonymousClass1.<clinit>():void");
        }
    }
}
