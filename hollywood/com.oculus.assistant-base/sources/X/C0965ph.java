package X;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* renamed from: X.ph  reason: case insensitive filesystem */
public class C0965ph extends KK {
    public final File A00;
    public final int A01;

    public static long A02(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        A03(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    public static void A03(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) {
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
        throw new KG("ELF file truncated");
    }

    public static long A01(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        A03(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    @Override // X.KK
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

    public C0965ph(File file, int i) {
        this.A00 = file;
        this.A01 = i;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x026b, code lost:
        throw new X.KG("ELF file does not contain dynamic linking information");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0315, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0321, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass08.A04("Error: Cannot load ", r2), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0364, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0365, code lost:
        r18 = r0.toString();
     */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0315 A[ExcHandler: IllegalAccessException | IllegalArgumentException | InvocationTargetException (r1v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:178:0x0314] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0364 A[ExcHandler: IOException | SecurityException | NoSuchAlgorithmException (r0v21 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:194:0x035b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A07(java.lang.String r39, int r40, java.io.File r41, android.os.StrictMode.ThreadPolicy r42) {
        /*
        // Method dump skipped, instructions count: 912
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0965ph.A07(java.lang.String, int, java.io.File, android.os.StrictMode$ThreadPolicy):int");
    }
}
