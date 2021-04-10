package defpackage;

import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;

/* renamed from: n0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3816n0 implements AbstractC1641aI0 {
    public final UH0 F;
    public final MK0 G = new C3645m0(this);

    public C3816n0(UH0 uh0) {
        this.F = uh0;
        uh0.f9530a.b(this);
    }

    public C3148j50 a() {
        UH0 uh0 = this.F;
        SH0 sh0 = AbstractC4158p0.b;
        if (uh0.f(sh0) == -1) {
            return null;
        }
        return (C3148j50) ((C1794b90) this.F.g(AbstractC4158p0.f11043a)).get(this.F.f(sh0));
    }

    @Override // defpackage.AbstractC1641aI0
    public void b(AbstractC1821bI0 bi0, Object obj) {
        KH0 kh0 = (KH0) obj;
        QH0 qh0 = AbstractC4158p0.c;
        if (kh0 == qh0) {
            if (!(!this.F.h(qh0) || a() == null || a().g == null)) {
                C4670s0 a2 = a().g.a();
                int i = a2.G;
                C5010u0 u0Var = a2.F;
                int i2 = 0;
                for (int i3 = 0; i3 < u0Var.size(); i3++) {
                    if (((C4840t0) u0Var.get(i3)).b == 2 || ((C4840t0) u0Var.get(i3)).b == 3 || ((C4840t0) u0Var.get(i3)).b == 4) {
                        for (UserInfoField userInfoField : ((C3319k50) ((C4840t0) u0Var.get(i3)).f11315a).b) {
                            if (userInfoField.isSelectable()) {
                                i2++;
                            }
                        }
                    }
                }
                AbstractC3364kK0.c(AbstractC4768sc0.a("KeyboardAccessory.AccessorySheetSuggestionCount", i), i2);
                if (i != 0) {
                    AbstractC3364kK0.c(AbstractC4768sc0.a("KeyboardAccessory.AccessorySheetSuggestionCount", 0), i2);
                }
                AbstractC3364kK0.g("KeyboardAccessory.AccessoryActionImpression", a2.I, 8);
                for (int i4 = 0; i4 < a2.F.size(); i4++) {
                    C4840t0 t0Var = (C4840t0) a2.F.get(i4);
                    if (t0Var.b == 8) {
                        C2978i50 i50 = (C2978i50) t0Var.f11315a;
                        AbstractC3364kK0.g("KeyboardAccessory.AccessoryToggleImpression", i50.d == 6 ? !i50.b ? 1 : 0 : 2, 2);
                    }
                }
            }
        } else if (kh0 != AbstractC4158p0.b && kh0 != AbstractC4158p0.d && kh0 != AbstractC4158p0.e) {
            TH0 th0 = AbstractC4158p0.f;
        }
    }
}
