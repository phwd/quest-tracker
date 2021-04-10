package X;

import androidx.annotation.IntRange;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: X.1hn  reason: invalid class name and case insensitive filesystem */
public class C09361hn {
    public long A00 = 0;
    public final ByteBuffer A01;
    public final RandomAccessFile A02;
    public final byte[] A03;

    public static void A00(@IntRange(from = 0, to = 4) C09361hn r4, int i) throws IOException {
        if (r4.A02.read(r4.A03, 0, i) == i) {
            r4.A00 += (long) i;
            return;
        }
        throw new IOException("read failed");
    }

    public final long A01() throws IOException {
        ByteBuffer byteBuffer = this.A01;
        byteBuffer.position(0);
        A00(this, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    public final void A02(int i) throws IOException {
        while (i > 0) {
            int skipBytes = this.A02.skipBytes(i);
            if (skipBytes >= 1) {
                i -= skipBytes;
                this.A00 += (long) skipBytes;
            } else {
                throw new IOException("Skip didn't move at least 1 byte forward");
            }
        }
    }

    public C09361hn(RandomAccessFile randomAccessFile) {
        this.A02 = randomAccessFile;
        byte[] bArr = new byte[4];
        this.A03 = bArr;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.A01 = wrap;
        wrap.order(ByteOrder.BIG_ENDIAN);
    }
}
