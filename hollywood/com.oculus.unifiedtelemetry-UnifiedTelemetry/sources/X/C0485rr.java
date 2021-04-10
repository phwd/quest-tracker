package X;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* renamed from: X.rr  reason: case insensitive filesystem */
public class C0485rr extends AbstractC0507sb {
    public final File A00;
    public final int A01;

    public static long A01(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        A02(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    public static void A02(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new C0517t0("ELF file truncated");
    }

    public static long A00(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        A02(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    @Override // X.AbstractC0507sb
    public final String toString() {
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

    public C0485rr(File file, int i) {
        this.A00 = file;
        this.A01 = i;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02d9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02e5, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass06.A04("Error: Cannot load ", r2), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0328, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0329, code lost:
        r9 = r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:?, code lost:
        throw new X.C0517t0("ELF file does not contain dynamic linking information");
     */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x02d9 A[ExcHandler: IllegalAccessException | IllegalArgumentException | InvocationTargetException (r1v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:169:0x02d8] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0328 A[ExcHandler: IOException | SecurityException | NoSuchAlgorithmException (r0v19 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:185:0x031f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A08(java.lang.String r37, int r38, java.io.File r39, android.os.StrictMode.ThreadPolicy r40) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 845
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0485rr.A08(java.lang.String, int, java.io.File, android.os.StrictMode$ThreadPolicy):int");
    }
}
