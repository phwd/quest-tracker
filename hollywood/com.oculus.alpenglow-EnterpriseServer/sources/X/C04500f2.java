package X;

/* renamed from: X.0f2  reason: invalid class name and case insensitive filesystem */
public class C04500f2 implements AnonymousClass04Z {
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    public C04500f2(LayoutInflater$Factory2C04430et r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass04Z
    public final void onDetachedFromWindow() {
        LayoutInflater$Factory2C04430et r2 = this.A00;
        AbstractC002504b r0 = r2.A0L;
        if (r0 != null) {
            r0.A29();
        }
        if (r2.A0C != null) {
            r2.A0B.getDecorView().removeCallbacks(r2.A0O);
            if (r2.A0C.isShowing()) {
                try {
                    r2.A0C.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            r2.A0C = null;
        }
        AnonymousClass0B0 r02 = r2.A0M;
        if (r02 != null) {
            r02.A00();
        }
        C04280eZ r03 = r2.A0d(0).A0A;
        if (r03 != null) {
            r03.close();
        }
    }
}
