package com.android.org.bouncycastle.crypto.paddings;

import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

public class ZeroBytePadding implements BlockCipherPadding {
    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom random) throws IllegalArgumentException {
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "ZeroByte";
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] in, int inOff) {
        int added = in.length - inOff;
        while (inOff < in.length) {
            in[inOff] = 0;
            inOff++;
        }
        return added;
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] in) throws InvalidCipherTextException {
        int count = in.length;
        while (count > 0 && in[count - 1] == 0) {
            count--;
        }
        return in.length - count;
    }
}
