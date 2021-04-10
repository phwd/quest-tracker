package com.oculus.vrcast.googlecast.net;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* access modifiers changed from: package-private */
public class Util {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    static byte[] intToBytes(int i) {
        return new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
    }

    Util() {
    }

    static int bytesToInt(byte[] bArr) {
        return (bArr[3] & 255) | (bArr[0] << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    static void readExact(InputStream inputStream, byte[] bArr, int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read != -1) {
                i2 += read;
            } else {
                throw new EOFException("readExact() encountered premature EOF");
            }
        }
    }
}
