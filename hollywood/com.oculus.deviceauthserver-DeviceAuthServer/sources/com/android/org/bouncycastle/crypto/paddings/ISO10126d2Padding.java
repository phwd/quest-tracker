package com.android.org.bouncycastle.crypto.paddings;

import com.android.org.bouncycastle.crypto.CryptoServicesRegistrar;
import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.google.common.primitives.UnsignedBytes;
import java.security.SecureRandom;

public class ISO10126d2Padding implements BlockCipherPadding {
    SecureRandom random;

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom random2) throws IllegalArgumentException {
        if (random2 != null) {
            this.random = random2;
        } else {
            this.random = CryptoServicesRegistrar.getSecureRandom();
        }
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "ISO10126-2";
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] in, int inOff) {
        byte code = (byte) (in.length - inOff);
        while (inOff < in.length - 1) {
            in[inOff] = (byte) this.random.nextInt();
            inOff++;
        }
        in[inOff] = code;
        return code;
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] in) throws InvalidCipherTextException {
        int count = in[in.length - 1] & UnsignedBytes.MAX_VALUE;
        if (count <= in.length) {
            return count;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
