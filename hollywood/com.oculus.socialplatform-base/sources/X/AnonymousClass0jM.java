package X;

import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: X.0jM  reason: invalid class name */
public class AnonymousClass0jM extends AnonymousClass0l1 {
    public final File A00;
    public final int A01;

    public static long A02(AbstractC03030kr r1, ByteBuffer byteBuffer, long j) throws IOException {
        A03(r1, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    public static void A03(AbstractC03030kr r3, ByteBuffer byteBuffer, int i, long j) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0) {
            r3.A8R(j);
            int read = r3.read(byteBuffer);
            if (read == -1) {
                break;
            }
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new C03050kt("ELF file truncated");
    }

    public static long A01(AbstractC03030kr r1, ByteBuffer byteBuffer, long j) throws IOException {
        A03(r1, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    @Override // X.AnonymousClass0l1
    public int A05(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return A07(str, i, this.A00, threadPolicy);
    }

    @Override // X.AnonymousClass0l1
    @Nullable
    public final String A06(String str) throws IOException {
        File file = new File(this.A00, str);
        if (file.exists()) {
            return file.getCanonicalPath();
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0269, code lost:
        throw new X.C03050kt("ELF file does not contain dynamic linking information");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02c5, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02d1, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass006.A07("Error: Cannot load ", r2), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0314, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0315, code lost:
        r12 = r0.toString();
     */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x02c5 A[ExcHandler: IllegalAccessException | IllegalArgumentException | InvocationTargetException (r1v6 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:169:0x02c4] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0314 A[ExcHandler: IOException | SecurityException | NoSuchAlgorithmException (r0v28 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:185:0x030b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A07(java.lang.String r36, int r37, java.io.File r38, android.os.StrictMode.ThreadPolicy r39) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 858
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0jM.A07(java.lang.String, int, java.io.File, android.os.StrictMode$ThreadPolicy):int");
    }

    @Override // X.AnonymousClass0l1
    public String toString() {
        String str;
        try {
            str = String.valueOf(this.A00.getCanonicalPath());
        } catch (IOException unused) {
            str = this.A00.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[root = ");
        sb.append(str);
        sb.append(" flags = ");
        sb.append(this.A01);
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass0jM(File file, int i) {
        this.A00 = file;
        this.A01 = i;
    }
}
