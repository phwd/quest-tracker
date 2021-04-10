package java.security;

import java.io.ByteArrayOutputStream;
import java.security.Provider;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.security.jca.GetInstance;
import sun.security.jca.Providers;
import sun.security.jca.ServiceId;

public abstract class Signature extends SignatureSpi {
    private static final List rsaIds = Arrays.asList(new ServiceId("Signature", "NONEwithRSA"), new ServiceId("Cipher", "RSA/ECB/PKCS1Padding"), new ServiceId("Cipher", "RSA/ECB"), new ServiceId("Cipher", "RSA//PKCS1Padding"), new ServiceId("Cipher", "RSA"));
    private static final Map signatureInfo = new ConcurrentHashMap();
    private String algorithm;
    Provider provider;
    protected int state = 0;

    protected Signature(String str) {
        this.algorithm = str;
    }

    static {
        Boolean bool = Boolean.TRUE;
        signatureInfo.put("sun.security.provider.DSA$RawDSA", bool);
        signatureInfo.put("sun.security.provider.DSA$SHA1withDSA", bool);
        signatureInfo.put("sun.security.rsa.RSASignature$MD2withRSA", bool);
        signatureInfo.put("sun.security.rsa.RSASignature$MD5withRSA", bool);
        signatureInfo.put("sun.security.rsa.RSASignature$SHA1withRSA", bool);
        signatureInfo.put("sun.security.rsa.RSASignature$SHA256withRSA", bool);
        signatureInfo.put("sun.security.rsa.RSASignature$SHA384withRSA", bool);
        signatureInfo.put("sun.security.rsa.RSASignature$SHA512withRSA", bool);
        signatureInfo.put("com.sun.net.ssl.internal.ssl.RSASignature", bool);
        signatureInfo.put("sun.security.pkcs11.P11Signature", bool);
    }

    public static Signature getInstance(String str) {
        List list;
        if (str.equalsIgnoreCase("NONEwithRSA")) {
            list = GetInstance.getServices(rsaIds);
        } else {
            list = GetInstance.getServices("Signature", str);
        }
        Iterator it = list.iterator();
        if (it.hasNext()) {
            do {
                Provider.Service service = (Provider.Service) it.next();
                if (isSpi(service)) {
                    return new Delegate(str);
                }
                try {
                    return getInstance(GetInstance.getInstance(service, SignatureSpi.class), str);
                } catch (NoSuchAlgorithmException e) {
                    if (!it.hasNext()) {
                        throw e;
                    }
                }
            } while (!it.hasNext());
            throw e;
        }
        throw new NoSuchAlgorithmException(str + " Signature not available");
    }

    private static Signature getInstance(GetInstance.Instance instance, String str) {
        Signature signature;
        Object obj = instance.impl;
        if (obj instanceof Signature) {
            signature = (Signature) obj;
            signature.algorithm = str;
        } else {
            signature = new Delegate((SignatureSpi) obj, str);
        }
        signature.provider = instance.provider;
        return signature;
    }

    /* access modifiers changed from: private */
    public static boolean isSpi(Provider.Service service) {
        boolean z = true;
        if (service.getType().equals("Cipher")) {
            return true;
        }
        String className = service.getClassName();
        Boolean bool = (Boolean) signatureInfo.get(className);
        if (bool == null) {
            try {
                Object newInstance = service.newInstance(null);
                if (!(newInstance instanceof SignatureSpi) || (newInstance instanceof Signature)) {
                    z = false;
                }
                bool = Boolean.valueOf(z);
                signatureInfo.put(className, bool);
            } catch (Exception unused) {
                return false;
            }
        }
        return bool.booleanValue();
    }

    public static Signature getInstance(String str, String str2) {
        if (!str.equalsIgnoreCase("NONEwithRSA")) {
            Providers.checkBouncyCastleDeprecation(str2, "Signature", str);
            return getInstance(GetInstance.getInstance("Signature", SignatureSpi.class, str, str2), str);
        } else if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("missing provider");
        } else {
            Provider provider2 = Security.getProvider(str2);
            if (provider2 != null) {
                return getInstanceRSA(provider2);
            }
            throw new NoSuchProviderException("no such provider: " + str2);
        }
    }

    private static Signature getInstanceRSA(Provider provider2) {
        Provider.Service service = provider2.getService("Signature", "NONEwithRSA");
        if (service != null) {
            return getInstance(GetInstance.getInstance(service, SignatureSpi.class), "NONEwithRSA");
        }
        try {
            return new Delegate(new CipherAdapter(Cipher.getInstance("RSA/ECB/PKCS1Padding", provider2)), "NONEwithRSA");
        } catch (GeneralSecurityException e) {
            throw new NoSuchAlgorithmException("no such algorithm: NONEwithRSA for provider " + provider2.getName(), e);
        }
    }

    public final void initVerify(PublicKey publicKey) {
        engineInitVerify(publicKey);
        this.state = 3;
    }

    public final boolean verify(byte[] bArr) {
        if (this.state == 3) {
            return engineVerify(bArr);
        }
        throw new SignatureException("object not initialized for verification");
    }

    public final void update(byte[] bArr, int i, int i2) {
        int i3 = this.state;
        if (i3 != 2 && i3 != 3) {
            throw new SignatureException("object not initialized for signature or verification");
        } else if (bArr == null) {
            throw new IllegalArgumentException("data is null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("off or len is less than 0");
        } else if (bArr.length - i >= i2) {
            engineUpdate(bArr, i, i2);
        } else {
            throw new IllegalArgumentException("data too small for specified offset and length");
        }
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public String toString() {
        int i = this.state;
        String str = i != 0 ? i != 2 ? i != 3 ? "" : "<initialized for verifying>" : "<initialized for signing>" : "<not initialized>";
        return "Signature object: " + getAlgorithm() + str;
    }

    @Override // java.security.SignatureSpi
    public Object clone() {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }

    /* access modifiers changed from: private */
    public static class Delegate extends Signature {
        private final Object lock;
        private SignatureSpi sigSpi;

        Delegate(SignatureSpi signatureSpi, String str) {
            super(str);
            this.sigSpi = signatureSpi;
            this.lock = null;
        }

        Delegate(String str) {
            super(str);
            this.lock = new Object();
        }

        @Override // java.security.SignatureSpi, java.security.Signature
        public Object clone() {
            chooseFirstProvider();
            SignatureSpi signatureSpi = this.sigSpi;
            if (signatureSpi instanceof Cloneable) {
                Delegate delegate = new Delegate((SignatureSpi) signatureSpi.clone(), ((Signature) this).algorithm);
                delegate.provider = this.provider;
                return delegate;
            }
            throw new CloneNotSupportedException();
        }

        private static SignatureSpi newInstance(Provider.Service service) {
            if (service.getType().equals("Cipher")) {
                try {
                    return new CipherAdapter(Cipher.getInstance("RSA/ECB/PKCS1Padding", service.getProvider()));
                } catch (NoSuchPaddingException e) {
                    throw new NoSuchAlgorithmException(e);
                }
            } else {
                Object newInstance = service.newInstance(null);
                if (newInstance instanceof SignatureSpi) {
                    return (SignatureSpi) newInstance;
                }
                throw new NoSuchAlgorithmException("Not a SignatureSpi: " + newInstance.getClass().getName());
            }
        }

        /* access modifiers changed from: package-private */
        public void chooseFirstProvider() {
            List<Provider.Service> list;
            if (this.sigSpi == null) {
                synchronized (this.lock) {
                    if (this.sigSpi == null) {
                        NoSuchAlgorithmException e = null;
                        if (((Signature) this).algorithm.equalsIgnoreCase("NONEwithRSA")) {
                            list = GetInstance.getServices(Signature.rsaIds);
                        } else {
                            list = GetInstance.getServices("Signature", ((Signature) this).algorithm);
                        }
                        for (Provider.Service service : list) {
                            if (Signature.isSpi(service)) {
                                try {
                                    this.sigSpi = newInstance(service);
                                    this.provider = service.getProvider();
                                    return;
                                } catch (NoSuchAlgorithmException e2) {
                                    e = e2;
                                }
                            }
                        }
                        ProviderException providerException = new ProviderException("Could not construct SignatureSpi instance");
                        if (e != null) {
                            providerException.initCause(e);
                        }
                        throw providerException;
                    }
                }
            }
        }

        private void chooseProvider(int i, Key key, SecureRandom secureRandom) {
            List<Provider.Service> list;
            synchronized (this.lock) {
                if (this.sigSpi == null || key != null) {
                    InvalidKeyException invalidKeyException = null;
                    if (((Signature) this).algorithm.equalsIgnoreCase("NONEwithRSA")) {
                        list = GetInstance.getServices(Signature.rsaIds);
                    } else {
                        list = GetInstance.getServices("Signature", ((Signature) this).algorithm);
                    }
                    for (Provider.Service service : list) {
                        if (service.supportsParameter(key)) {
                            if (!Signature.isSpi(service)) {
                                continue;
                            } else {
                                try {
                                    SignatureSpi newInstance = newInstance(service);
                                    init(newInstance, i, key, secureRandom);
                                    this.provider = service.getProvider();
                                    this.sigSpi = newInstance;
                                    return;
                                } catch (Exception e) {
                                    if (invalidKeyException == null) {
                                        invalidKeyException = e;
                                    }
                                    if (invalidKeyException instanceof InvalidKeyException) {
                                        throw invalidKeyException;
                                    }
                                }
                            }
                        }
                    }
                    if (invalidKeyException instanceof InvalidKeyException) {
                        throw invalidKeyException;
                    } else if (!(invalidKeyException instanceof RuntimeException)) {
                        String name = key != null ? key.getClass().getName() : "(null)";
                        throw new InvalidKeyException("No installed provider supports this key: " + name, invalidKeyException);
                    } else {
                        throw ((RuntimeException) invalidKeyException);
                    }
                } else {
                    init(this.sigSpi, i, key, secureRandom);
                }
            }
        }

        private void init(SignatureSpi signatureSpi, int i, Key key, SecureRandom secureRandom) {
            if (i == 1) {
                signatureSpi.engineInitVerify((PublicKey) key);
            } else if (i == 2) {
                signatureSpi.engineInitSign((PrivateKey) key);
            } else if (i == 3) {
                signatureSpi.engineInitSign((PrivateKey) key, secureRandom);
            } else {
                throw new AssertionError((Object) ("Internal error: " + i));
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineInitVerify(PublicKey publicKey) {
            if (this.sigSpi == null || !(this.lock == null || publicKey == null)) {
                chooseProvider(1, publicKey, null);
            } else {
                this.sigSpi.engineInitVerify(publicKey);
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineInitSign(PrivateKey privateKey) {
            if (this.sigSpi == null || !(this.lock == null || privateKey == null)) {
                chooseProvider(2, privateKey, null);
            } else {
                this.sigSpi.engineInitSign(privateKey);
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) {
            if (this.sigSpi == null || !(this.lock == null || privateKey == null)) {
                chooseProvider(3, privateKey, secureRandom);
            } else {
                this.sigSpi.engineInitSign(privateKey, secureRandom);
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineUpdate(byte[] bArr, int i, int i2) {
            chooseFirstProvider();
            this.sigSpi.engineUpdate(bArr, i, i2);
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public boolean engineVerify(byte[] bArr) {
            chooseFirstProvider();
            return this.sigSpi.engineVerify(bArr);
        }
    }

    /* access modifiers changed from: private */
    public static class CipherAdapter extends SignatureSpi {
        private final Cipher cipher;
        private ByteArrayOutputStream data;

        CipherAdapter(Cipher cipher2) {
            this.cipher = cipher2;
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineInitVerify(PublicKey publicKey) {
            this.cipher.init(2, publicKey);
            ByteArrayOutputStream byteArrayOutputStream = this.data;
            if (byteArrayOutputStream == null) {
                this.data = new ByteArrayOutputStream(128);
            } else {
                byteArrayOutputStream.reset();
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineInitSign(PrivateKey privateKey) {
            this.cipher.init(1, privateKey);
            this.data = null;
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) {
            this.cipher.init(1, privateKey, secureRandom);
            this.data = null;
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public void engineUpdate(byte[] bArr, int i, int i2) {
            ByteArrayOutputStream byteArrayOutputStream = this.data;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.write(bArr, i, i2);
                return;
            }
            byte[] update = this.cipher.update(bArr, i, i2);
            if (update != null && update.length != 0) {
                throw new SignatureException("Cipher unexpectedly returned data");
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.security.SignatureSpi
        public boolean engineVerify(byte[] bArr) {
            try {
                byte[] doFinal = this.cipher.doFinal(bArr);
                byte[] byteArray = this.data.toByteArray();
                this.data.reset();
                return MessageDigest.isEqual(doFinal, byteArray);
            } catch (BadPaddingException unused) {
                return false;
            } catch (IllegalBlockSizeException e) {
                throw new SignatureException("doFinal() failed", e);
            }
        }
    }
}
