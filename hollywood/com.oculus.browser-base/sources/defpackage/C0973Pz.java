package defpackage;

import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: Pz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0973Pz extends AbstractC1099Sa1 {
    public final /* synthetic */ ContextualSearchManager I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0973Pz(ContextualSearchManager contextualSearchManager, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = contextualSearchManager;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        this.I.i(0);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void m(Tab tab, boolean z) {
        if (z) {
            this.I.i(0);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        if (C3372kO0.W(tab)) {
            this.I.i(0);
        }
    }
}
