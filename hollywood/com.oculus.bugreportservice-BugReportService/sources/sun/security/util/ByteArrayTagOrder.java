package sun.security.util;

import java.util.Comparator;

public class ByteArrayTagOrder implements Comparator {
    public final int compare(byte[] bArr, byte[] bArr2) {
        return (bArr[0] | 32) - (bArr2[0] | 32);
    }
}
