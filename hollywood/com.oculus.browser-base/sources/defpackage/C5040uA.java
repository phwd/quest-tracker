package defpackage;

import org.chromium.chrome.browser.contextualsearch.ContextualSearchTabHelper;

/* renamed from: uA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5040uA implements AbstractC0383Gf1 {
    public final /* synthetic */ ContextualSearchTabHelper F;

    public C5040uA(ContextualSearchTabHelper contextualSearchTabHelper) {
        this.F = contextualSearchTabHelper;
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        boolean e = AbstractC0444Hf1.a().e();
        Boolean bool = this.F.N;
        if (bool == null || e != bool.booleanValue()) {
            this.F.N = Boolean.valueOf(e);
            ContextualSearchTabHelper contextualSearchTabHelper = this.F;
            contextualSearchTabHelper.Y(contextualSearchTabHelper.I);
        }
    }
}
