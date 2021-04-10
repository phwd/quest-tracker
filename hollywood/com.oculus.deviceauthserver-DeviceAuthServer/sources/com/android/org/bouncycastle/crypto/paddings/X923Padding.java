package com.android.org.bouncycastle.crypto.paddings;

import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.google.common.primitives.UnsignedBytes;
import java.security.SecureRandom;

public class X923Padding implements BlockCipherPadding {
    SecureRandom random = null;

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom random2) throws IllegalArgumentException {
        this.random = random2;
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "X9.23";
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] in, int inOff) {
        byte code = (byte) (in.length - inOff);
        while (inOff < in.length - 1) {
            SecureRandom secureRandom = this.random;
            if (secureRandom == null) {
                in[inOff] = 0;
            } else {
                in[inOff] = (byte) secureRandom.nextInt();
            }
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
