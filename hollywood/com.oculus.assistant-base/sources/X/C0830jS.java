package X;

import android.os.StrictMode;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import com.facebook.acra.util.HttpRequest;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.jS  reason: case insensitive filesystem */
public final class C0830jS implements CX {
    public final Set A00 = new HashSet();

    public static C0830jS A00() {
        return new C0830jS();
    }

    @Override // X.CX
    public final boolean A4i(String str, int[] iArr, String[] strArr, long[] jArr, float[] fArr) {
        byte[] bArr = new byte[HttpRequest.CHAR_ARRAY_BUFFER_SIZE];
        Set set = this.A00;
        if (!set.contains(str)) {
            StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                FileDescriptor open = Os.open(str, 0, 0);
                try {
                    int read = Os.read(open, bArr, 0, 255);
                    try {
                        Os.close(open);
                    } catch (ErrnoException unused) {
                    }
                    if (read != -2147483647) {
                        if (allowThreadDiskReads != null) {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                        }
                        if (read >= 0) {
                            return A4U(bArr, 0, read, iArr, strArr, jArr, fArr);
                        }
                    }
                } catch (ErrnoException | InterruptedIOException e) {
                    Log.i("NewProcReader", AnonymousClass08.A04("Unable to read process file: ", str), e);
                    try {
                        Os.close(open);
                    } catch (ErrnoException unused2) {
                    }
                } catch (Throwable th) {
                    try {
                        Os.close(open);
                    } catch (ErrnoException unused3) {
                    }
                    throw th;
                }
            } catch (ErrnoException e2) {
                Log.i("NewProcReader", AnonymousClass08.A04("Unable to raw open process file: ", str), e2);
            }
            set.add(str);
            if (allowThreadDiskReads != null) {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
        Log.i("NewProcReader", AnonymousClass08.A04("Unable to open and read process file: ", str));
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:164:0x01b6, code lost:
        if (r15 > 'Z') goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00c1, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0281  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0046 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x02ab A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x01fd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0217 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x009c  */
    @Override // X.CX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A4U(byte[] r36, int r37, int r38, int[] r39, java.lang.String[] r40, long[] r41, float[] r42) {
        /*
        // Method dump skipped, instructions count: 706
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0830jS.A4U(byte[], int, int, int[], java.lang.String[], long[], float[]):boolean");
    }
}
