package defpackage;

import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: FN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FN0 extends OK {
    public boolean F;
    public final /* synthetic */ GN0 G;

    public FN0(GN0 gn0) {
        this.G = gn0;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void i(int i) {
        if (i == 0) {
            this.F = false;
            ContextualSearchManager contextualSearchManager = (ContextualSearchManager) this.G.m0.get();
            if (contextualSearchManager != null) {
                contextualSearchManager.q0 = false;
            }
        } else if ((i == 1 || i == 2 || i == 3) && !this.F) {
            this.F = true;
            ContextualSearchManager contextualSearchManager2 = (ContextualSearchManager) this.G.m0.get();
            if (contextualSearchManager2 != null) {
                contextualSearchManager2.q0 = true;
                contextualSearchManager2.i(1);
            }
        }
    }
}
