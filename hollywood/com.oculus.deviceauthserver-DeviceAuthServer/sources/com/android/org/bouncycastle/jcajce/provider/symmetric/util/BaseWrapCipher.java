package com.android.org.bouncycastle.jcajce.provider.symmetric.util;

import com.android.org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.android.org.bouncycastle.crypto.Wrapper;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.android.org.bouncycastle.crypto.params.ParametersWithIV;
import com.android.org.bouncycastle.crypto.params.ParametersWithRandom;
import com.android.org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import com.android.org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import com.android.org.bouncycastle.jcajce.util.JcaJceHelper;
import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.android.org.bouncycastle.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class BaseWrapCipher extends CipherSpi implements PBE {
    private Class[] availableSpecs;
    protected AlgorithmParameters engineParams;
    private boolean forWrapping;
    private final JcaJceHelper helper;
    private byte[] iv;
    private int ivSize;
    protected int pbeHash;
    protected int pbeIvSize;
    protected int pbeKeySize;
    protected int pbeType;
    protected Wrapper wrapEngine;
    private ErasableOutputStream wrapStream;

    protected BaseWrapCipher() {
        this.availableSpecs = new Class[]{PBEParameterSpec.class, IvParameterSpec.class};
        this.pbeType = 2;
        this.pbeHash = 1;
        this.engineParams = null;
        this.wrapEngine = null;
        this.wrapStream = null;
        this.helper = new DefaultJcaJceHelper();
    }

    protected BaseWrapCipher(Wrapper wrapEngine2) {
        this(wrapEngine2, 0);
    }

    protected BaseWrapCipher(Wrapper wrapEngine2, int ivSize2) {
        this.availableSpecs = new Class[]{PBEParameterSpec.class, IvParameterSpec.class};
        this.pbeType = 2;
        this.pbeHash = 1;
        this.engineParams = null;
        this.wrapEngine = null;
        this.wrapStream = null;
        this.helper = new DefaultJcaJceHelper();
        this.wrapEngine = wrapEngine2;
        this.ivSize = ivSize2;
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        return Arrays.clone(this.iv);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int inputLen) {
        return -1;
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null && this.iv != null) {
            String name = this.wrapEngine.getAlgorithmName();
            if (name.indexOf(47) >= 0) {
                name = name.substring(0, name.indexOf(47));
            }
            try {
                this.engineParams = createParametersInstance(name);
                this.engineParams.init(new IvParameterSpec(this.iv));
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParams;
    }

    /* access modifiers changed from: protected */
    public final AlgorithmParameters createParametersInstance(String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        return this.helper.createAlgorithmParameters(algorithm);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String mode) throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("can't support mode " + mode);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Padding " + padding + " unknown.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters param;
        if (key instanceof BCPBEKey) {
            BCPBEKey k = (BCPBEKey) key;
            if (params instanceof PBEParameterSpec) {
                param = PBE.Util.makePBEParameters(k, params, this.wrapEngine.getAlgorithmName());
            } else if (k.getParam() != null) {
                param = k.getParam();
            } else {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
        } else {
            param = new KeyParameter(key.getEncoded());
        }
        if (params instanceof IvParameterSpec) {
            this.iv = ((IvParameterSpec) params).getIV();
            param = new ParametersWithIV(param, this.iv);
        }
        if ((param instanceof KeyParameter) && this.ivSize != 0 && (opmode == 3 || opmode == 1)) {
            this.iv = new byte[this.ivSize];
            random.nextBytes(this.iv);
            param = new ParametersWithIV(param, this.iv);
        }
        if (random != null) {
            param = new ParametersWithRandom(param, random);
        }
        if (opmode == 1) {
            this.wrapEngine.init(true, param);
            this.wrapStream = new ErasableOutputStream();
            this.forWrapping = true;
        } else if (opmode == 2) {
            this.wrapEngine.init(false, param);
            this.wrapStream = new ErasableOutputStream();
            this.forWrapping = false;
        } else if (opmode == 3) {
            this.wrapEngine.init(true, param);
            this.wrapStream = null;
            this.forWrapping = true;
        } else if (opmode == 4) {
            try {
                this.wrapEngine.init(false, param);
                this.wrapStream = null;
                this.forWrapping = false;
            } catch (Exception e) {
                throw new InvalidKeyOrParametersException(e.getMessage(), e);
            }
        } else {
            throw new InvalidParameterException("Unknown mode parameter passed to init.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec paramSpec = null;
        if (params != null) {
            int i = 0;
            while (true) {
                Class[] clsArr = this.availableSpecs;
                if (i == clsArr.length) {
                    break;
                }
                try {
                    paramSpec = params.getParameterSpec(clsArr[i]);
                    break;
                } catch (Exception e) {
                    i++;
                }
            }
            if (paramSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + params.toString());
            }
        }
        this.engineParams = params;
        engineInit(opmode, key, paramSpec, random);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
        try {
            engineInit(opmode, key, (AlgorithmParameterSpec) null, random);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyOrParametersException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        ErasableOutputStream erasableOutputStream = this.wrapStream;
        if (erasableOutputStream != null) {
            erasableOutputStream.write(input, inputOffset, inputLen);
            return null;
        }
        throw new IllegalStateException("not supported in a wrapping mode");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
        ErasableOutputStream erasableOutputStream = this.wrapStream;
        if (erasableOutputStream != null) {
            erasableOutputStream.write(input, inputOffset, inputLen);
            return 0;
        }
        throw new IllegalStateException("not supported in a wrapping mode");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
        ErasableOutputStream erasableOutputStream = this.wrapStream;
        if (erasableOutputStream != null) {
            erasableOutputStream.write(input, inputOffset, inputLen);
            try {
                if (this.forWrapping) {
                    try {
                        return this.wrapEngine.wrap(this.wrapStream.getBuf(), 0, this.wrapStream.size());
                    } catch (Exception e) {
                        throw new IllegalBlockSizeException(e.getMessage());
                    }
                } else {
                    try {
                        byte[] unwrap = this.wrapEngine.unwrap(this.wrapStream.getBuf(), 0, this.wrapStream.size());
                        this.wrapStream.erase();
                        return unwrap;
                    } catch (InvalidCipherTextException e2) {
                        throw new BadPaddingException(e2.getMessage());
                    }
                }
            } finally {
                this.wrapStream.erase();
            }
        } else {
            throw new IllegalStateException("not supported in a wrapping mode");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        byte[] enc;
        ErasableOutputStream erasableOutputStream = this.wrapStream;
        if (erasableOutputStream != null) {
            erasableOutputStream.write(input, inputOffset, inputLen);
            try {
                if (this.forWrapping) {
                    try {
                        enc = this.wrapEngine.wrap(this.wrapStream.getBuf(), 0, this.wrapStream.size());
                    } catch (Exception e) {
                        throw new IllegalBlockSizeException(e.getMessage());
                    }
                } else {
                    try {
                        enc = this.wrapEngine.unwrap(this.wrapStream.getBuf(), 0, this.wrapStream.size());
                    } catch (InvalidCipherTextException e2) {
                        throw new BadPaddingException(e2.getMessage());
                    }
                }
                if (enc.length + outputOffset <= output.length) {
                    System.arraycopy(enc, 0, output, outputOffset, enc.length);
                    return enc.length;
                }
                throw new ShortBufferException("output buffer too short for input.");
            } finally {
                this.wrapStream.erase();
            }
        } else {
            throw new IllegalStateException("not supported in a wrapping mode");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        byte[] encoded = key.getEncoded();
        if (encoded != null) {
            try {
                if (this.wrapEngine == null) {
                    return engineDoFinal(encoded, 0, encoded.length);
                }
                return this.wrapEngine.wrap(encoded, 0, encoded.length);
            } catch (BadPaddingException e) {
                throw new IllegalBlockSizeException(e.getMessage());
            }
        } else {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] encoded;
        try {
            if (this.wrapEngine == null) {
                encoded = engineDoFinal(wrappedKey, 0, wrappedKey.length);
            } else {
                encoded = this.wrapEngine.unwrap(wrappedKey, 0, wrappedKey.length);
            }
            if (wrappedKeyType == 3) {
                return new SecretKeySpec(encoded, wrappedKeyAlgorithm);
            }
            if (!wrappedKeyAlgorithm.equals("") || wrappedKeyType != 2) {
                try {
                    KeyFactory kf = this.helper.createKeyFactory(wrappedKeyAlgorithm);
                    if (wrappedKeyType == 1) {
                        return kf.generatePublic(new X509EncodedKeySpec(encoded));
                    }
                    if (wrappedKeyType == 2) {
                        return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                    }
                    throw new InvalidKeyException("Unknown key type " + wrappedKeyType);
                } catch (NoSuchProviderException e) {
                    throw new InvalidKeyException("Unknown key type " + e.getMessage());
                } catch (InvalidKeySpecException e2) {
                    throw new InvalidKeyException("Unknown key type " + e2.getMessage());
                }
            } else {
                try {
                    PrivateKeyInfo in = PrivateKeyInfo.getInstance(encoded);
                    PrivateKey privKey = BouncyCastleProvider.getPrivateKey(in);
                    if (privKey != null) {
                        return privKey;
                    }
                    throw new InvalidKeyException("algorithm " + in.getPrivateKeyAlgorithm().getAlgorithm() + " not supported");
                } catch (Exception e3) {
                    throw new InvalidKeyException("Invalid key encoding.");
                }
            }
        } catch (InvalidCipherTextException e4) {
            throw new InvalidKeyException(e4.getMessage());
        } catch (BadPaddingException e5) {
            throw new InvalidKeyException(e5.getMessage());
        } catch (IllegalBlockSizeException e22) {
            throw new InvalidKeyException(e22.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public static final class ErasableOutputStream extends ByteArrayOutputStream {
        public byte[] getBuf() {
            return this.buf;
        }

        public void erase() {
            Arrays.fill(this.buf, (byte) 0);
            reset();
        }
    }

    /* access modifiers changed from: protected */
    public static class InvalidKeyOrParametersException extends InvalidKeyException {
        private final Throwable cause;

        InvalidKeyOrParametersException(String msg, Throwable cause2) {
            super(msg);
            this.cause = cause2;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}
