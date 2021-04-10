package X;

import com.facebook.acra.util.HttpRequestMultipart;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Strings;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@Beta
@GwtIncompatible
/* renamed from: X.9K  reason: invalid class name */
public final class AnonymousClass9K {
    public static final OutputStream A00 = new AnonymousClass9J();

    public static byte[] A00(InputStream inputStream, long j) throws IOException {
        boolean z = false;
        if (j >= 0) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat("expectedSize (%s) must be non-negative", Long.valueOf(j)));
        } else if (j <= 2147483639) {
            int i = (int) j;
            byte[] bArr = new byte[i];
            int i2 = i;
            while (i2 > 0) {
                int i3 = i - i2;
                int read = inputStream.read(bArr, i3, i2);
                if (read == -1) {
                    return Arrays.copyOf(bArr, i3);
                }
                i2 -= read;
            }
            int read2 = inputStream.read();
            if (read2 == -1) {
                return bArr;
            }
            ArrayDeque arrayDeque = new ArrayDeque(22);
            arrayDeque.add(bArr);
            arrayDeque.add(new byte[]{(byte) read2});
            return A01(inputStream, arrayDeque, i + 1);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            sb.append(" bytes is too large to fit in a byte array");
            throw new OutOfMemoryError(sb.toString());
        }
    }

    public static byte[] A01(InputStream inputStream, Deque<byte[]> deque, int i) throws IOException {
        byte[] bArr;
        int i2 = HttpRequestMultipart.STREAM_BLOCK_SIZE;
        while (true) {
            if (i < 2147483639) {
                byte[] bArr2 = new byte[Math.min(i2, 2147483639 - i)];
                deque.add(bArr2);
                int i3 = 0;
                while (i3 < bArr2.length) {
                    int read = inputStream.read(bArr2, i3, bArr2.length - i3);
                    if (read == -1) {
                        bArr = new byte[i];
                        int i4 = i;
                        while (i4 > 0) {
                            byte[] removeFirst = deque.removeFirst();
                            int min = Math.min(i4, removeFirst.length);
                            System.arraycopy(removeFirst, 0, bArr, i - i4, min);
                            i4 -= min;
                        }
                    } else {
                        i3 += read;
                        i += read;
                    }
                }
                i2 = AL.A00(((long) i2) * ((long) 2));
            } else if (inputStream.read() == -1) {
                bArr = new byte[2147483639];
                int i5 = 2147483639;
                do {
                    byte[] removeFirst2 = deque.removeFirst();
                    int min2 = Math.min(i5, removeFirst2.length);
                    System.arraycopy(removeFirst2, 0, bArr, 2147483639 - i5, min2);
                    i5 -= min2;
                } while (i5 > 0);
            } else {
                throw new OutOfMemoryError("input is too large to fit in a byte array");
            }
        }
        return bArr;
    }
}
