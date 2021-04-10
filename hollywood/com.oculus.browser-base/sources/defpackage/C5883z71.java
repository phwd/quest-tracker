package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.PriceCardView;

/* renamed from: z71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5883z71 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PriceCardView f11724a;

    public C5883z71(PriceCardView priceCardView) {
        this.f11724a = priceCardView;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PriceCardView priceCardView = this.f11724a;
        C2361eV0 ev0 = (C2361eV0) obj;
        if (ev0 == null || ev0.s() == null) {
            priceCardView.setVisibility(8);
            return;
        }
        String str = ev0.s().f9609a;
        String str2 = ev0.s().b;
        priceCardView.F.setText(str);
        priceCardView.G.setText(str2);
        priceCardView.setVisibility(0);
    }
}
