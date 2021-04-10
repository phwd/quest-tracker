package sun.security.util;

import java.util.Comparator;

public class ByteArrayLexOrder implements Comparator {
    public final int compare(byte[] bArr, byte[] bArr2) {
        int i = 0;
        while (i < bArr.length && i < bArr2.length) {
            int i2 = (bArr[i] & 255) - (bArr2[i] & 255);
            if (i2 != 0) {
                return i2;
            }
            i++;
        }
        return bArr.length - bArr2.length;
    }
}
