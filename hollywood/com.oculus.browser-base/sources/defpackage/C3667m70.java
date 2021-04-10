package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: m70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3667m70 extends AbstractC5024u41 {
    public int F;
    public final boolean G;
    public final /* synthetic */ AbstractC3838n70 H;

    public C3667m70(AbstractC3838n70 n70, boolean z) {
        this.H = n70;
        this.G = z;
    }

    @Override // defpackage.AbstractC5194v41
    public void d(int i, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        C5306vl1 vl1 = this.H.s0;
        if (vl1 != null && vl1.x()) {
            motionEvent.getRawX();
            float f5 = this.H.F;
            motionEvent.getRawX();
            AbstractC3838n70 n70 = this.H;
            float f6 = n70.F;
            float f7 = f * f6;
            float f8 = f3 * f6;
            C5306vl1 vl12 = n70.s0;
            SystemClock.uptimeMillis();
            float f9 = vl12.F * 0.5f;
            vl12.a0(AbstractC4089od0.b(f8 * 0.033333335f, -f9, f9) + f7);
        }
    }

    @Override // defpackage.AbstractC5194v41
    public void f(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        int index;
        if (this.H.s0 != null) {
            motionEvent.getRawX();
            float f5 = this.H.F;
            motionEvent.getRawY();
            AbstractC3838n70 n70 = this.H;
            float f6 = n70.F;
            float f7 = f3 * f6;
            float f8 = f4 * f6;
            float f9 = f * f6;
            if (this.F != 0) {
                C5306vl1 vl1 = n70.s0;
                SystemClock.uptimeMillis();
                vl1.a0(f9);
                return;
            }
            double degrees = (Math.toDegrees(Math.atan2((double) (-f8), (double) f7)) + 360.0d) % 360.0d;
            boolean z = false;
            int i = (degrees >= 205.0d || degrees <= 155.0d) ? (degrees < 25.0d || degrees > 335.0d) ? 2 : (degrees >= 295.0d || degrees <= 245.0d) ? 0 : 4 : 1;
            this.F = i;
            if (i != 0) {
                if (this.G && this.H.t0 != null && i == 4) {
                    AbstractC3535lK0.a("MobileToolbarSwipeOpenStackView");
                    this.H.E(true);
                } else if (i == 1 || i == 2) {
                    AbstractC3838n70 n702 = this.H;
                    n702.F(n702.s0, true);
                }
                C5306vl1 vl12 = this.H.s0;
                SystemClock.uptimeMillis();
                int i2 = this.F;
                AbstractC0124Ca1 ca1 = vl12.L;
                if (ca1 != null && vl12.Y == null && i2 != 4) {
                    if (i2 == 2) {
                        z = true;
                    }
                    TabModel i3 = ((AbstractC0246Ea1) ca1).i();
                    if (i3 != null && (index = i3.index()) != -1) {
                        vl12.Z(i2, index, z ^ LocalizationUtils.isLayoutRtl() ? index - 1 : index + 1);
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractC5194v41
    public void h() {
        J70 j70;
        J70 j702;
        C5306vl1 vl1 = this.H.s0;
        if (vl1 != null && vl1.x()) {
            C5306vl1 vl12 = this.H.s0;
            SystemClock.uptimeMillis();
            if (vl12.X != null && vl12.L != null) {
                float min = Math.min(vl12.e0, vl12.F / 3.0f);
                float f = 0.0f;
                J70 j703 = vl12.X;
                vl12.Y = j703;
                float f2 = vl12.c0;
                if (f2 > min && (j702 = vl12.V) != null) {
                    vl12.Y = j702;
                    f = 0.0f + vl12.F;
                } else if (f2 < (-min) && (j70 = vl12.W) != null) {
                    vl12.Y = j70;
                    f = 0.0f - vl12.F;
                }
                if (vl12.Y != j703) {
                    AbstractC3535lK0.a("MobileSideSwipeFinished");
                }
                vl12.Q(vl12.Y.q(), false);
                float f3 = vl12.c0;
                vl12.Y.q();
                vl12.X(f3, f, (long) ((Math.abs(f3 - f) * 500.0f) / vl12.F));
            }
        }
    }

    @Override // defpackage.AbstractC5194v41
    public boolean i(int i) {
        AbstractC3838n70 n70 = this.H;
        ST st = ((CompositorViewHolder) n70.G).U.Z;
        if (n70.S != n70.K || !C5052uE.c().f) {
            return false;
        }
        if (st != null && st.a()) {
            return false;
        }
        if (i == 4) {
            boolean d = C0283Ep.h().d();
            if (this.H.t0 == null || d) {
                return false;
            }
            return true;
        } else if (i == 1 || i == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override // defpackage.AbstractC5194v41
    public void j(int i, MotionEvent motionEvent) {
        this.F = 0;
    }
}
