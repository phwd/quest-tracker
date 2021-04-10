package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Yv  reason: case insensitive filesystem */
public final class C0271Yv extends Fz<Object> {
    public static Fz A00;

    public static synchronized Fz A01() {
        Fz fz;
        synchronized (C0271Yv.class) {
            fz = A00;
            if (fz == null) {
                fz = new C0271Yv();
                A00 = fz;
            }
        }
        return fz;
    }

    @Override // X.Fz
    public final Fz<Object>.BatchLock A03(Object obj) {
        return new C0272Yw(this, obj);
    }
}
