package com.facebook.stetho.websocket;

class MaskingHelper {
    MaskingHelper() {
    }

    public static void unmask(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 > 0) {
                bArr2[i] = (byte) (bArr[i3 % bArr.length] ^ bArr2[i]);
                i++;
                i2 = i4;
                i3++;
            } else {
                return;
            }
        }
    }
}
