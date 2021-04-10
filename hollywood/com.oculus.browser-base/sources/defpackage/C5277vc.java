package defpackage;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import java.util.Map;
import java.util.Objects;

/* renamed from: vc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5277vc implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        A31 a31 = (A31) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = AbstractC5701y31.f11659a;
        if (qh0.equals(kh0)) {
            boolean h = uh0.h(qh0);
            C5836ys0 ys0 = a31.b;
            Objects.requireNonNull(ys0);
            if (h) {
                a31.f7652a.setVisibility(0);
                if (ys0.getParent() == null) {
                    a31.f7652a.addView(ys0);
                }
                C5836ys0 ys02 = a31.b;
                if (ys02.getVisibility() != 0) {
                    ys02.setVisibility(0);
                    C6006zs0 zs0 = ys02.p1;
                    if (zs0 != null && zs0.L != 0) {
                        zs0.x(-1);
                        return;
                    }
                    return;
                }
                return;
            }
            C5836ys0 ys03 = a31.b;
            if (ys03.getVisibility() == 0) {
                ys03.setVisibility(8);
                ys03.P().a();
            }
            Map map = AbstractC2417ep1.f9884a;
            ViewGroup viewGroup = (ViewGroup) ys0.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(ys0);
            }
            a31.f7652a.setVisibility(4);
            return;
        }
        TH0 th0 = AbstractC5701y31.b;
        if (th0.equals(kh0)) {
            C5836ys0 ys04 = a31.b;
            AbstractC0046As0 as0 = (AbstractC0046As0) uh0.g(th0);
            ys04.q1 = as0;
            ys04.s1 = ((C3909na0) as0).M;
            ys04.u1 = new ViewTreeObserver$OnGlobalLayoutListenerC4986ts0(ys04);
            C3909na0 na0 = (C3909na0) ys04.q1;
            AbstractView$OnClickListenerC5272va0 va0 = na0.g() ? na0.F : null;
            ys04.t1 = va0;
            if (va0 != null) {
                ys04.v1 = new View$OnLayoutChangeListenerC5156us0(ys04);
            } else {
                ys04.v1 = null;
            }
        } else {
            TH0 th02 = AbstractC5701y31.e;
            if (th02.equals(kh0)) {
                a31.b.r1 = (AbstractC5496ws0) uh0.g(th02);
                return;
            }
            TH0 th03 = AbstractC5701y31.c;
            if (th03.equals(kh0)) {
                ((C4935tb0) uh0.g(th03)).F.b(new C5871z31(a31));
                return;
            }
            QH0 qh02 = AbstractC5701y31.d;
            if (qh02.equals(kh0)) {
                C5836ys0 ys05 = a31.b;
                int i = uh0.h(qh02) ? ys05.l1 : ys05.k1;
                if (!ys05.isHardwareAccelerated() && Color.alpha(i) == 255) {
                    i = Color.argb(254, Color.red(i), Color.green(i), Color.blue(i));
                }
                ys05.setBackground(new ColorDrawable(i));
            }
        }
    }
}
