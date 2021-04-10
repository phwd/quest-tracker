package defpackage;

import android.view.ViewGroup;
import org.chromium.chrome.browser.tasks.tab_management.PriceCardView;
import org.chromium.chrome.browser.tasks.tab_management.PriceWelcomeMessageCardView;

/* renamed from: Rc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1044Rc1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        KH0 kh0 = (KH0) obj3;
        PriceWelcomeMessageCardView priceWelcomeMessageCardView = (PriceWelcomeMessageCardView) ((ViewGroup) obj2);
        TH0 th0 = AbstractC0516Ij0.b;
        if (th0 == kh0) {
            priceWelcomeMessageCardView.f10780J.setText((String) uh0.g(th0));
            priceWelcomeMessageCardView.f10780J.setOnClickListener(new DF0(uh0));
            return;
        }
        TH0 th02 = AbstractC0516Ij0.n;
        if (th02 == kh0) {
            priceWelcomeMessageCardView.H.setText((String) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC0516Ij0.c;
        if (th03 == kh0) {
            priceWelcomeMessageCardView.I.setText((String) uh0.g(th03));
            return;
        }
        TH0 th04 = AbstractC0516Ij0.j;
        if (th04 == kh0) {
            priceWelcomeMessageCardView.K.setContentDescription((String) uh0.g(th04));
            priceWelcomeMessageCardView.K.setOnClickListener(new EF0(uh0));
            return;
        }
        TH0 th05 = AbstractC0516Ij0.o;
        if (th05 == kh0) {
            C2020cV0 cv0 = (C2020cV0) uh0.g(th05);
            if (cv0 != null) {
                PriceCardView priceCardView = priceWelcomeMessageCardView.G;
                String str = cv0.f9609a;
                String str2 = cv0.b;
                priceCardView.F.setText(str);
                priceCardView.G.setText(str2);
                return;
            }
            return;
        }
        RH0 rh0 = J91.b;
        if (rh0 == kh0) {
            priceWelcomeMessageCardView.setAlpha(uh0.e(rh0));
        }
    }
}
