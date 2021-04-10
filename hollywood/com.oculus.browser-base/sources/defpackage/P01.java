package defpackage;

import android.content.Context;
import android.os.SystemClock;

/* renamed from: P01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P01 extends C3565lZ0 {
    public final C3818n01 P0;
    public final AbstractC1939c01 Q0;
    public boolean R0;

    public P01(Context context, K70 k70, F70 f70, AbstractC2451f01 f01, AbstractC0956Pq0 pq0) {
        super(context, k70, f70, pq0);
        C3818n01 n01 = (C3818n01) f01;
        this.P0 = n01;
        n01.e(new O01(this));
        this.Q0 = n01.c;
    }

    @Override // defpackage.AbstractC2300e70
    public void A() {
        if (!this.R0) {
            this.R0 = true;
            if (this.K0 == null) {
                this.K0 = new Q91();
            }
            if (this.L0) {
                this.L0 = false;
                P(SystemClock.uptimeMillis(), false);
            }
            this.P0.b();
        }
    }

    @Override // defpackage.AbstractC5780yZ0, defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        this.P0.c();
        ((M01) this.Q0).q(false);
        if (this.R0) {
            super.P(j, z);
        }
    }

    @Override // defpackage.AbstractC5780yZ0, defpackage.AbstractC2300e70
    public void Q(int i, boolean z) {
        ((M01) this.Q0).G.c(false);
        super.Q(i, z);
    }

    @Override // defpackage.AbstractC5780yZ0, defpackage.AbstractC2300e70
    public VL n() {
        if (this.P0.d()) {
            return this.E0;
        }
        return null;
    }

    @Override // defpackage.AbstractC5780yZ0, defpackage.AbstractC2300e70
    public boolean z() {
        if (this.P0.d()) {
            L(SystemClock.currentThreadTimeMillis(), true);
        }
        return ((M01) this.Q0).h();
    }
}
