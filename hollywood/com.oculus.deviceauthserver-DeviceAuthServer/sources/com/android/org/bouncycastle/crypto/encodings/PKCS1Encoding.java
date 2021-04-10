package com.android.org.bouncycastle.crypto.encodings;

import com.android.org.bouncycastle.crypto.AsymmetricBlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.CryptoServicesRegistrar;
import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.android.org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import com.android.org.bouncycastle.crypto.params.ParametersWithRandom;
import com.android.org.bouncycastle.util.Arrays;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;

public class PKCS1Encoding implements AsymmetricBlockCipher {
    private static final int HEADER_LENGTH = 10;
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "com.android.org.bouncycastle.pkcs1.not_strict";
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "com.android.org.bouncycastle.pkcs1.strict";
    private byte[] blockBuffer;
    private AsymmetricBlockCipher engine;
    private byte[] fallback = null;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private int pLen = -1;
    private SecureRandom random;
    private boolean useStrictLength;

    public PKCS1Encoding(AsymmetricBlockCipher cipher) {
        this.engine = cipher;
        this.useStrictLength = useStrict();
    }

    public PKCS1Encoding(AsymmetricBlockCipher cipher, int pLen2) {
        this.engine = cipher;
        this.useStrictLength = useStrict();
        this.pLen = pLen2;
    }

    public PKCS1Encoding(AsymmetricBlockCipher cipher, byte[] fallback2) {
        this.engine = cipher;
        this.useStrictLength = useStrict();
        this.fallback = fallback2;
        this.pLen = fallback2.length;
    }

    private boolean useStrict() {
        String strict = (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class com.android.org.bouncycastle.crypto.encodings.PKCS1Encoding.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        String notStrict = (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class com.android.org.bouncycastle.crypto.encodings.PKCS1Encoding.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(PKCS1Encoding.NOT_STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        if (notStrict != null) {
            return true ^ notStrict.equals("true");
        }
        if (strict == null || strict.equals("true")) {
            return true;
        }
        return false;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean forEncryption2, CipherParameters param) {
        AsymmetricKeyParameter kParam;
        if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.random = rParam.getRandom();
            kParam = (AsymmetricKeyParameter) rParam.getParameters();
        } else {
            kParam = (AsymmetricKeyParameter) param;
            if (!kParam.isPrivate() && forEncryption2) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
        }
        this.engine.init(forEncryption2, param);
        this.forPrivateKey = kParam.isPrivate();
        this.forEncryption = forEncryption2;
        this.blockBuffer = new byte[this.engine.getOutputBlockSize()];
        if (this.pLen > 0 && this.fallback == null && this.random == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int baseBlockSize = this.engine.getInputBlockSize();
        if (this.forEncryption) {
            return baseBlockSize - 10;
        }
        return baseBlockSize;
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int baseBlockSize = this.engine.getOutputBlockSize();
        if (this.forEncryption) {
            return baseBlockSize;
        }
        return baseBlockSize - 10;
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return encodeBlock(in, inOff, inLen);
        }
        return decodeBlock(in, inOff, inLen);
    }

    private byte[] encodeBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (inLen <= getInputBlockSize()) {
            byte[] block = new byte[this.engine.getInputBlockSize()];
            if (this.forPrivateKey) {
                block[0] = 1;
                for (int i = 1; i != (block.length - inLen) - 1; i++) {
                    block[i] = -1;
                }
            } else {
                this.random.nextBytes(block);
                block[0] = 2;
                for (int i2 = 1; i2 != (block.length - inLen) - 1; i2++) {
                    while (block[i2] == 0) {
                        block[i2] = (byte) this.random.nextInt();
                    }
                }
            }
            block[(block.length - inLen) - 1] = 0;
            System.arraycopy(in, inOff, block, block.length - inLen, inLen);
            return this.engine.processBlock(block, 0, block.length);
        }
        throw new IllegalArgumentException("input data too large");
    }

    private static int checkPkcs1Encoding(byte[] encoded, int pLen2) {
        int correct = 0 | (encoded[0] ^ 2);
        int plen = encoded.length - (pLen2 + 1);
        for (int i = 1; i < plen; i++) {
            byte b = encoded[i];
            int tmp = b | (b >> 1);
            int tmp2 = tmp | (tmp >> 2);
            correct |= ((tmp2 | (tmp2 >> 4)) & 1) - 1;
        }
        int correct2 = correct | encoded[encoded.length - (pLen2 + 1)];
        int correct3 = correct2 | (correct2 >> 1);
        int correct4 = correct3 | (correct3 >> 2);
        return ~(((correct4 | (correct4 >> 4)) & 1) - 1);
    }

    private byte[] decodeBlockOrRandom(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] random2;
        if (this.forPrivateKey) {
            byte[] block = this.engine.processBlock(in, inOff, inLen);
            if (this.fallback == null) {
                random2 = new byte[this.pLen];
                this.random.nextBytes(random2);
            } else {
                random2 = this.fallback;
            }
            byte[] data = this.useStrictLength & (block.length != this.engine.getOutputBlockSize()) ? this.blockBuffer : block;
            int correct = checkPkcs1Encoding(data, this.pLen);
            byte[] result = new byte[this.pLen];
            int i = 0;
            while (true) {
                int i2 = this.pLen;
                if (i < i2) {
                    result[i] = (byte) ((data[(data.length - i2) + i] & (~correct)) | (random2[i] & correct));
                    i++;
                } else {
                    Arrays.fill(data, (byte) 0);
                    return result;
                }
            }
        } else {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
    }

    private byte[] decodeBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] data;
        boolean badType;
        if (this.pLen != -1) {
            return decodeBlockOrRandom(in, inOff, inLen);
        }
        byte[] block = this.engine.processBlock(in, inOff, inLen);
        boolean z = true;
        boolean incorrectLength = this.useStrictLength & (block.length != this.engine.getOutputBlockSize());
        if (block.length < getOutputBlockSize()) {
            data = this.blockBuffer;
        } else {
            data = block;
        }
        byte type = data[0];
        if (this.forPrivateKey) {
            badType = type != 2;
        } else {
            badType = type != 1;
        }
        int start = findStart(type, data) + 1;
        if (start >= 10) {
            z = false;
        }
        if (z || badType) {
            Arrays.fill(data, (byte) 0);
            throw new InvalidCipherTextException("block incorrect");
        } else if (!incorrectLength) {
            byte[] result = new byte[(data.length - start)];
            System.arraycopy(data, start, result, 0, result.length);
            return result;
        } else {
            Arrays.fill(data, (byte) 0);
            throw new InvalidCipherTextException("block incorrect size");
        }
    }

    private int findStart(byte type, byte[] block) throws InvalidCipherTextException {
        int start = -1;
        boolean padErr = false;
        for (int i = 1; i != block.length; i++) {
            byte pad = block[i];
            boolean z = false;
            if ((pad == 0) && (start < 0)) {
                start = i;
            }
            boolean z2 = (type == 1) & (start < 0);
            if (pad != -1) {
                z = true;
            }
            padErr |= z2 & z;
        }
        if (padErr) {
            return -1;
        }
        return start;
    }
}
