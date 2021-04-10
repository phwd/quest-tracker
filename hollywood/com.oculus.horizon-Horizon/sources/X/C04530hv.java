package X;

import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.annotation.Nullable;

/* renamed from: X.0hv  reason: invalid class name and case insensitive filesystem */
public class C04530hv extends AbstractC03170cL {
    public final File A00;
    public final int A01;

    public static long A02(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        A03(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    public static void A03(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
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
        throw new C03120cD("ELF file truncated");
    }

    public static long A01(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        A03(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    @Override // X.AbstractC03170cL
    public int A05(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return A07(str, i, this.A00, threadPolicy);
    }

    @Override // X.AbstractC03170cL
    @Nullable
    public final String A06(String str) throws IOException {
        File file = new File(this.A00, str);
        if (file.exists()) {
            return file.getCanonicalPath();
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02e0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02ec, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass006.A05("Error: Cannot load ", r2), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x032f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0330, code lost:
        r12 = r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:?, code lost:
        throw new X.C03120cD("ELF file does not contain dynamic linking information");
     */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x02e0 A[ExcHandler: IllegalAccessException | IllegalArgumentException | InvocationTargetException (r1v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:169:0x02df] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x032f A[ExcHandler: IOException | SecurityException | NoSuchAlgorithmException (r0v18 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:185:0x0326] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A07(java.lang.String r37, int r38, java.io.File r39, android.os.StrictMode.ThreadPolicy r40) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 852
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04530hv.A07(java.lang.String, int, java.io.File, android.os.StrictMode$ThreadPolicy):int");
    }

    @Override // X.AbstractC03170cL
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

    public C04530hv(File file, int i) {
        this.A00 = file;
        this.A01 = i;
    }
}
