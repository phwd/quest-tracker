package X;

import com.squareup.okhttp.internal.framed.Http2;
import javax.net.ssl.SSLException;

/* renamed from: X.16I  reason: invalid class name */
public abstract class AnonymousClass16I {
    public final synchronized void A01(byte b, byte[] bArr, int i, int i2) throws AnonymousClass11f {
        if (bArr == null) {
            throw new AnonymousClass11f((byte) 80, new SSLException("Data cannot be null"));
        } else if (AnonymousClass10b.A00.contains(Byte.valueOf(b))) {
            while (i2 > 16384) {
                A00(b, bArr, i, Http2.INITIAL_MAX_FRAME_SIZE);
                i += Http2.INITIAL_MAX_FRAME_SIZE;
                i2 -= 16384;
            }
            if (i2 > 0) {
                A00(b, bArr, i, i2);
            }
        } else {
            throw new AnonymousClass11f((byte) 80, new SSLException("Invalid content type"));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x010b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0116, code lost:
        throw new X.AnonymousClass11f(r3, new javax.net.ssl.SSLException(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0117, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0122, code lost:
        throw new X.AnonymousClass11f((byte) 80, new javax.net.ssl.SSLException(r1), true);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:8:0x0089, B:22:0x00e0] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x010b A[ExcHandler: IOException (r1v0 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r3 
      PHI: (r3v0 byte) = (r3v1 byte), (r3v4 byte) binds: [B:22:0x00e0, B:8:0x0089] A[DONT_GENERATE, DONT_INLINE], Splitter:B:8:0x0089] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A00(byte r15, byte[] r16, int r17, int r18) throws X.AnonymousClass11f {
        /*
        // Method dump skipped, instructions count: 310
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass16I.A00(byte, byte[], int, int):void");
    }
}
