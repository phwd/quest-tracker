package defpackage;

import J.N;

/* renamed from: mL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3707mL {
    public static void a(int i) {
        AbstractC3364kK0.g("NewTabPage.Promo.EnhancedProtectionPromo", i, 4);
        if (i != 0) {
            int M37SqSAy = N.M37SqSAy("EnhancedProtectionPromoCard", "MaxEnhancedProtectionPromoImpressions", 22);
            String b = AbstractC0533Is.i.b("EnhancedProtectionPromoCard");
            PU0 pu0 = NU0.f8549a;
            int f = pu0.f(b, 0);
            if (i == 1) {
                pu0.n(b, f + 1);
            } else if (i == 3) {
                AbstractC3535lK0.a("NewTabPage.Promo.EnhancedProtectionPromo.Accepted");
                AbstractC3364kK0.h("NewTabPage.Promo.EnhancedProtectionPromo.ImpressionUntilAction", f, 1, M37SqSAy, M37SqSAy + 1);
            } else if (i == 2) {
                AbstractC3535lK0.a("NewTabPage.Promo.EnhancedProtectionPromo.Dismissed");
                AbstractC3364kK0.h("NewTabPage.Promo.EnhancedProtectionPromo.ImpressionUntilDismissal", f, 1, M37SqSAy, M37SqSAy + 1);
            }
        }
    }
}
