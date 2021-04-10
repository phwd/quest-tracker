package X;

import android.annotation.SuppressLint;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.w"})
public final class fF {
    @Nullable
    public static fJ A00;
    public static final fJ A01;
    public static final fJ A02 = new S8();
    public static final AbstractC0205fK<Object> A03;

    static {
        S7 s7 = new S7();
        A01 = s7;
        A03 = new S5(s7);
    }

    public static synchronized fJ A00() {
        fJ fJVar;
        synchronized (fF.class) {
            fJVar = A00;
            if (fJVar == null) {
                throw new IllegalStateException();
            }
        }
        return fJVar;
    }

    public static synchronized AbstractC0205fK<Object> A01() {
        AbstractC0205fK<Object> fKVar;
        synchronized (fF.class) {
            fKVar = A03;
        }
        return fKVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void A02(android.content.Context r16) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: X.fF.A02(android.content.Context):void");
    }
}
