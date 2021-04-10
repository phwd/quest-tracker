package X;

import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashSet;
import java.util.Set;

@TargetApi(26)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0rY  reason: invalid class name and case insensitive filesystem */
public final class C04920rY implements AnonymousClass0Jo {
    public final Set<String> A00 = new HashSet();

    public static C04920rY A00() {
        return new C04920rY();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x003a */
    @Override // X.AnonymousClass0Jo
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A8t(java.lang.String r13, int[] r14, @javax.annotation.Nullable java.lang.String[] r15, @javax.annotation.Nullable long[] r16, @javax.annotation.Nullable float[] r17) {
        /*
            r12 = this;
            r0 = 256(0x100, float:3.59E-43)
            byte[] r5 = new byte[r0]
            r4 = r12
            java.util.Set<java.lang.String> r3 = r12.A00
            boolean r0 = r3.contains(r13)
            if (r0 != 0) goto L_0x004e
            android.os.StrictMode$ThreadPolicy r2 = android.os.StrictMode.allowThreadDiskReads()
            r6 = 0
            java.io.FileDescriptor r1 = android.system.Os.open(r13, r6, r6)     // Catch:{ ErrnoException -> 0x003e }
            r0 = 255(0xff, float:3.57E-43)
            int r7 = android.system.Os.read(r1, r5, r6, r0)     // Catch:{ ErrnoException | InterruptedIOException -> 0x003b, all -> 0x0036 }
            android.system.Os.close(r1)     // Catch:{ ErrnoException -> 0x001f }
        L_0x001f:
            r0 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            if (r7 == r0) goto L_0x003e
            if (r2 == 0) goto L_0x0029
            android.os.StrictMode.setThreadPolicy(r2)
        L_0x0029:
            if (r7 < 0) goto L_0x004e
            r8 = r14
            r11 = r17
            r10 = r16
            r9 = r15
            boolean r0 = r4.A8K(r5, r6, r7, r8, r9, r10, r11)
            return r0
        L_0x0036:
            r0 = move-exception
            android.system.Os.close(r1)     // Catch:{ ErrnoException -> 0x003a }
        L_0x003a:
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x003b:
            android.system.Os.close(r1)
        L_0x003e:
            r3.add(r13)
            goto L_0x0049
        L_0x0042:
            r0 = move-exception
            if (r2 == 0) goto L_0x0048
            android.os.StrictMode.setThreadPolicy(r2)
        L_0x0048:
            throw r0
        L_0x0049:
            if (r2 == 0) goto L_0x004e
            android.os.StrictMode.setThreadPolicy(r2)
        L_0x004e:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04920rY.A8t(java.lang.String, int[], java.lang.String[], long[], float[]):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:163:0x01ab, code lost:
        if (r15 > 'Z') goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00b6, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0269  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0277  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x027c  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0046 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0291 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x01eb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x0205 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0091  */
    @Override // X.AnonymousClass0Jo
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A8K(byte[] r36, int r37, int r38, int[] r39, @javax.annotation.Nullable java.lang.String[] r40, @javax.annotation.Nullable long[] r41, @javax.annotation.Nullable float[] r42) {
        /*
        // Method dump skipped, instructions count: 680
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04920rY.A8K(byte[], int, int, int[], java.lang.String[], long[], float[]):boolean");
    }
}
