package sun.misc;

import java.io.EOFException;
import java.io.InputStream;
import java.util.Arrays;

public class IOUtils {
    public static byte[] readFully(InputStream inputStream, int i, boolean z) {
        int i2;
        int i3 = 0;
        byte[] bArr = new byte[0];
        if (i == -1) {
            i = Integer.MAX_VALUE;
        }
        while (i3 < i) {
            if (i3 >= bArr.length) {
                i2 = Math.min(i - i3, bArr.length + 1024);
                int i4 = i3 + i2;
                if (bArr.length < i4) {
                    bArr = Arrays.copyOf(bArr, i4);
                }
            } else {
                i2 = bArr.length - i3;
            }
            int read = inputStream.read(bArr, i3, i2);
            if (read >= 0) {
                i3 += read;
            } else if (!z || i == Integer.MAX_VALUE) {
                return bArr.length != i3 ? Arrays.copyOf(bArr, i3) : bArr;
            } else {
                throw new EOFException("Detect premature EOF");
            }
        }
        return bArr;
    }
}
