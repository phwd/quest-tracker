package X;

import android.annotation.SuppressLint;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.w"})
/* renamed from: X.gu  reason: case insensitive filesystem */
public final class C0392gu {
    @Nullable
    public static AbstractC0396gy A00;
    public static final AbstractC0396gy A01;
    public static final AbstractC0396gy A02 = new XO();
    public static final AbstractC0397gz<Object> A03;

    static {
        XN xn = new XN();
        A01 = xn;
        A03 = new XL(xn);
    }

    public static synchronized AbstractC0396gy A00() {
        AbstractC0396gy gyVar;
        synchronized (C0392gu.class) {
            gyVar = A00;
            if (gyVar == null) {
                throw new IllegalStateException();
            }
        }
        return gyVar;
    }

    public static synchronized AbstractC0397gz<Object> A01() {
        AbstractC0397gz<Object> gzVar;
        synchronized (C0392gu.class) {
            gzVar = A03;
        }
        return gzVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void A02(android.content.Context r16) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0392gu.A02(android.content.Context):void");
    }
}
