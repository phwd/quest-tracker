package com.android.org.bouncycastle.crypto.paddings;

import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.google.common.primitives.UnsignedBytes;
import java.security.SecureRandom;

public class PKCS7Padding implements BlockCipherPadding {
    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom random) throws IllegalArgumentException {
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "PKCS7";
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] in, int inOff) {
        byte code = (byte) (in.length - inOff);
        while (inOff < in.length) {
            in[inOff] = code;
            inOff++;
        }
        return code;
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] in) throws InvalidCipherTextException {
        int count = in[in.length - 1] & UnsignedBytes.MAX_VALUE;
        byte countAsbyte = (byte) count;
        boolean failed = (count > in.length) | (count == 0);
        for (int i = 0; i < in.length; i++) {
            failed |= (in.length - i <= count) & (in[i] != countAsbyte);
        }
        if (!failed) {
            return count;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
