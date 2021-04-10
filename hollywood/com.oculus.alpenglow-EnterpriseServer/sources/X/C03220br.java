package X;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* renamed from: X.0br  reason: invalid class name and case insensitive filesystem */
public final class C03220br implements AnonymousClass0L8 {
    @Nullable
    public static volatile Method A00;
    @Nullable
    public static volatile Method A01;
    public static volatile boolean A02;
    public static volatile boolean A03;
    @Nullable
    public static volatile Method A04;
    public static volatile boolean A05;

    @Nullable
    public static C03220br A00() {
        if (Build.VERSION.SDK_INT > 25) {
            return null;
        }
        if (!A02) {
            Class cls = Integer.TYPE;
            A00 = A02("parseProcLine", byte[].class, cls, cls, int[].class, String[].class, long[].class, float[].class);
            A02 = true;
        }
        if (A00 == null || A01() == null) {
            return null;
        }
        if (!A03) {
            A01 = A02("readProcLines", String.class, String[].class, long[].class);
            A03 = true;
        }
        if (A01 != null) {
            return new C03220br();
        }
        return null;
    }

    @Nullable
    public static Method A01() {
        if (!A05) {
            A04 = A02("readProcFile", String.class, int[].class, String[].class, long[].class, float[].class);
            A05 = true;
        }
        return A04;
    }

    @Nullable
    public static Method A02(String str, Class<?>... clsArr) {
        try {
            return Process.class.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            if (!Log.isLoggable("OldProcReader", 5)) {
                return null;
            }
            Log.w("OldProcReader", AnonymousClass006.A05("Warning! Could not get access to JNI method - ", str), e);
            return null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:3:0x000f */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass0L8
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A7F(java.lang.String r7, int[] r8, @javax.annotation.Nullable java.lang.String[] r9, @javax.annotation.Nullable long[] r10, @javax.annotation.Nullable float[] r11) {
        /*
            r6 = this;
            java.lang.reflect.Method r3 = A01()
            r5 = 0
            java.lang.Object[] r2 = new java.lang.Object[]{r7, r8, r9, r10, r11}
            java.lang.String r4 = ")"
            if (r3 == 0) goto L_0x0041
            r1 = 0
            r0 = 6
            java.lang.Object r0 = r3.invoke(r1, r2)     // Catch:{ IllegalAccessException -> 0x0014, InvocationTargetException -> 0x002b }
            goto L_0x0043
        L_0x0014:
            r3 = move-exception
            java.lang.String r2 = "OldProcReader"
            boolean r0 = android.util.Log.isLoggable(r2, r0)
            if (r0 == 0) goto L_0x0041
            java.lang.String r1 = "Error (IllegalAccessException - "
            java.lang.String r0 = r3.getLocalizedMessage()
            java.lang.String r0 = X.AnonymousClass006.A07(r1, r0, r4)
            android.util.Log.e(r2, r0, r3)
            goto L_0x0041
        L_0x002b:
            r3 = move-exception
            java.lang.String r2 = "OldProcReader"
            boolean r0 = android.util.Log.isLoggable(r2, r0)
            if (r0 == 0) goto L_0x0041
            java.lang.String r1 = "Error (InvocationTargetException - "
            java.lang.String r0 = r3.getLocalizedMessage()
            java.lang.String r0 = X.AnonymousClass006.A07(r1, r0, r4)
            android.util.Log.e(r2, r0, r3)
        L_0x0041:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0043:
            if (r0 != 0) goto L_0x0046
            return r5
        L_0x0046:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03220br.A7F(java.lang.String, int[], java.lang.String[], long[], float[]):boolean");
    }
}
