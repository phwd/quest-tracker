package defpackage;

import android.view.View;
import android.view.ViewGroup;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.page_info.PageInfoRowView;

/* renamed from: zv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6015zv0 implements AbstractC0174Cv0, HX0 {
    public AbstractC5675xv0 F;
    public PageInfoRowView G;
    public AbstractC1922bv0 H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public String f11780J;
    public SingleWebsiteSettings K;

    public C6015zv0(AbstractC5675xv0 xv0, PageInfoRowView pageInfoRowView, AbstractC1922bv0 bv0, String str) {
        this.F = xv0;
        this.G = pageInfoRowView;
        this.H = bv0;
        this.f11780J = str;
    }

    @Override // defpackage.AbstractC0174Cv0
    public String b() {
        return this.I;
    }

    @Override // defpackage.AbstractC0174Cv0
    public View c(ViewGroup viewGroup) {
        SingleWebsiteSettings singleWebsiteSettings = (SingleWebsiteSettings) AbstractComponentCallbacksC3550lS.T(this.G.getContext(), SingleWebsiteSettings.class.getName(), SingleWebsiteSettings.k1(this.f11780J));
        this.K = singleWebsiteSettings;
        AbstractC1922bv0 bv0 = this.H;
        C4985ts tsVar = (C4985ts) bv0;
        singleWebsiteSettings.G0 = new C2427et(tsVar.k, tsVar.l);
        singleWebsiteSettings.J0 = true;
        singleWebsiteSettings.K0 = this;
        C0317Fe fe = new C0317Fe(bv0.a());
        fe.b(this.K, null);
        fe.h();
        return this.K.Q0();
    }

    @Override // defpackage.AbstractC0174Cv0
    public void d() {
        KS a2 = this.H.a();
        SingleWebsiteSettings singleWebsiteSettings = this.K;
        this.K = null;
        if (a2 != null && !a2.U()) {
            C0317Fe fe = new C0317Fe(a2);
            fe.p(singleWebsiteSettings);
            fe.h();
        }
    }
}
