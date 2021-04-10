package defpackage;

import android.util.Log;
import android.view.View;
import java.util.List;
import java.util.Objects;

/* renamed from: rg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC4610rg0 implements View.OnClickListener {
    public final /* synthetic */ C4780sg0 F;

    public View$OnClickListenerC4610rg0(C4780sg0 sg0) {
        this.F = sg0;
    }

    public void onClick(View view) {
        C4780sg0 sg0 = this.F;
        boolean z = true;
        boolean z2 = !sg0.z(sg0.Z);
        boolean f = this.F.Z.f();
        int i = 0;
        if (z2) {
            C4780sg0 sg02 = this.F;
            C3246jh0 jh0 = sg02.m0.R.I;
            C2392eh0 eh0 = sg02.Z;
            Objects.requireNonNull(jh0);
            C3246jh0.b();
            C1543Zg0 zg0 = C3246jh0.f10229a;
            if (zg0.s instanceof AbstractC0202Dg0) {
                C2222dh0 b = zg0.r.b(eh0);
                if (zg0.r.c().contains(eh0) || b == null || !b.a()) {
                    Log.w("MediaRouter", "Ignoring attemp to add a non-groupable route to dynamic group : " + eh0);
                } else {
                    ((AbstractC0202Dg0) zg0.s).m(eh0.b);
                }
            } else {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
        } else {
            C4780sg0 sg03 = this.F;
            C3246jh0 jh02 = sg03.m0.R.I;
            C2392eh0 eh02 = sg03.Z;
            Objects.requireNonNull(jh02);
            C3246jh0.b();
            C1543Zg0 zg02 = C3246jh0.f10229a;
            if (zg02.s instanceof AbstractC0202Dg0) {
                C2222dh0 b2 = zg02.r.b(eh02);
                if (zg02.r.c().contains(eh02) && b2 != null) {
                    C0141Cg0 cg0 = b2.f9800a;
                    if (cg0 == null || cg0.c) {
                        if (zg02.r.c().size() <= 1) {
                            Log.w("MediaRouter", "Ignoring attempt to remove the last member route.");
                        } else {
                            ((AbstractC0202Dg0) zg02.s).n(eh02.b);
                        }
                    }
                }
                Log.w("MediaRouter", "Ignoring attempt to remove a non-unselectable member route : " + eh02);
            } else {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
        }
        this.F.A(z2, !f);
        if (f) {
            List c = this.F.m0.R.L.c();
            for (C2392eh0 eh03 : this.F.Z.c()) {
                if (c.contains(eh03) != z2) {
                    AbstractC3072ig0 ig0 = (AbstractC3072ig0) this.F.m0.R.Y.get(eh03.c);
                    if (ig0 instanceof C4780sg0) {
                        ((C4780sg0) ig0).A(z2, true);
                    }
                }
            }
        }
        C4780sg0 sg04 = this.F;
        C4950tg0 tg0 = sg04.m0;
        C2392eh0 eh04 = sg04.Z;
        List c2 = tg0.R.L.c();
        int max = Math.max(1, c2.size());
        int i2 = -1;
        if (eh04.f()) {
            for (C2392eh0 eh05 : eh04.c()) {
                if (c2.contains(eh05) != z2) {
                    max += z2 ? 1 : -1;
                }
            }
        } else {
            if (z2) {
                i2 = 1;
            }
            max += i2;
        }
        boolean u = tg0.u();
        if (max < 2) {
            z = false;
        }
        if (u != z) {
            XK0 H = tg0.R.V.H(0);
            if (H instanceof C4098og0) {
                C4098og0 og0 = (C4098og0) H;
                View view2 = og0.G;
                if (z) {
                    i = og0.e0;
                }
                tg0.s(view2, i);
            }
        }
    }
}
