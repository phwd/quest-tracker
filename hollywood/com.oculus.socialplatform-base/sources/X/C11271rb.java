package X;

/* renamed from: X.1rb  reason: invalid class name and case insensitive filesystem */
public class C11271rb implements AnonymousClass1F2 {
    public final /* synthetic */ AnonymousClass1rJ A00;

    public C11271rb(AnonymousClass1rJ r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1F2
    public final void onDetachedFromWindow() {
        AnonymousClass1rJ r2 = this.A00;
        AnonymousClass1rl r0 = r2.A0K;
        if (r0 != null) {
            r0.A2e();
        }
        if (r2.A09 != null) {
            r2.A08.getDecorView().removeCallbacks(r2.A0N);
            if (r2.A09.isShowing()) {
                try {
                    r2.A09.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            r2.A09 = null;
        }
        C003007j r02 = r2.A0L;
        if (r02 != null) {
            r02.A00();
        }
        C11581sN r03 = r2.A0N(0).A0A;
        if (r03 != null) {
            r03.close();
        }
    }
}
