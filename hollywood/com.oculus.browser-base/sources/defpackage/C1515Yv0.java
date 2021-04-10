package defpackage;

import J.N;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Yv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1515Yv0 extends AbstractC1099Sa1 {
    public PaintPreviewTabService I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9304J;

    public C1515Yv0(PaintPreviewTabService paintPreviewTabService, PaintPreviewTabService paintPreviewTabService2, AbstractC0124Ca1 ca1, boolean z, AbstractC1393Wv0 wv0) {
        super(ca1);
        this.I = paintPreviewTabService2;
        this.f9304J = z;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        String g = tab.getUrl().g();
        boolean z = false;
        boolean z2 = g.equals("http") || g.equals("https");
        if (!tab.a() && !tab.isNativePage() && !tab.p() && tab.l() != null && z2) {
            z = true;
        }
        if (!z) {
            return;
        }
        if (i == 1 || this.f9304J) {
            this.I.b(tab, new C1454Xv0(this, tab));
        }
    }

    @Override // defpackage.AbstractC1099Sa1
    public void W(Tab tab) {
        long j = this.I.c;
        if (j != 0) {
            N.MO7GqHLu(j, tab.getId());
        }
    }
}
