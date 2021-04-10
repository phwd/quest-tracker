package com.android.org.bouncycastle.crypto.paddings;

import com.android.org.bouncycastle.crypto.InvalidCipherTextException;
import com.google.common.primitives.UnsignedBytes;
import java.security.SecureRandom;

public class ISO7816d4Padding implements BlockCipherPadding {
    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom random) throws IllegalArgumentException {
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "ISO7816-4";
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] in, int inOff) {
        int added = in.length - inOff;
        in[inOff] = UnsignedBytes.MAX_POWER_OF_TWO;
        while (true) {
            inOff++;
            if (inOff >= in.length) {
                return added;
            }
            in[inOff] = 0;
        }
    }

    @Override // com.android.org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] in) throws InvalidCipherTextException {
        int count = in.length - 1;
        while (count > 0 && in[count] == 0) {
            count--;
        }
        if (in[count] == Byte.MIN_VALUE) {
            return in.length - count;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
