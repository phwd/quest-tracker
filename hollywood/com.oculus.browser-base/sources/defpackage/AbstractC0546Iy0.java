package defpackage;

/* renamed from: Iy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0546Iy0 {
    public static boolean a(C0485Hy0[] hy0Arr, C0485Hy0[] hy0Arr2) {
        if (hy0Arr == null || hy0Arr2 == null || hy0Arr.length != hy0Arr2.length) {
            return false;
        }
        for (int i = 0; i < hy0Arr.length; i++) {
            if (!(hy0Arr[i].f8192a == hy0Arr2[i].f8192a && hy0Arr[i].b.length == hy0Arr2[i].b.length)) {
                return false;
            }
        }
        return true;
    }

    public static float[] b(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int length = fArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008a, code lost:
        if (r13 == 0) goto L_0x008c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0097 A[Catch:{ NumberFormatException -> 0x00bc }, LOOP:3: B:25:0x006c->B:45:0x0097, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00d9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0096 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.C0485Hy0[] c(java.lang.String r17) {
        /*
        // Method dump skipped, instructions count: 276
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0546Iy0.c(java.lang.String):Hy0[]");
    }

    public static C0485Hy0[] d(C0485Hy0[] hy0Arr) {
        if (hy0Arr == null) {
            return null;
        }
        C0485Hy0[] hy0Arr2 = new C0485Hy0[hy0Arr.length];
        for (int i = 0; i < hy0Arr.length; i++) {
            hy0Arr2[i] = new C0485Hy0(hy0Arr[i]);
        }
        return hy0Arr2;
    }
}
