package X;

import android.annotation.SuppressLint;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.w"})
/* renamed from: X.0jp  reason: invalid class name and case insensitive filesystem */
public final class C02780jp {
    @Nullable
    public static AbstractC02820jt A00;
    public static final AbstractC02820jt A01;
    public static final AbstractC02820jt A02 = new AnonymousClass0kN();
    public static final AbstractC02830ju<Object> A03;

    static {
        AnonymousClass0kH r1 = new AnonymousClass0kH();
        A01 = r1;
        A03 = new C02760jn(r1);
    }

    public static synchronized AbstractC02820jt A00() {
        AbstractC02820jt r0;
        synchronized (C02780jp.class) {
            r0 = A00;
            if (r0 == null) {
                throw new IllegalStateException();
            }
        }
        return r0;
    }

    public static synchronized AbstractC02830ju<Object> A01() {
        AbstractC02830ju<Object> r0;
        synchronized (C02780jp.class) {
            r0 = A03;
        }
        return r0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void A02(android.content.Context r16) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02780jp.A02(android.content.Context):void");
    }
}
