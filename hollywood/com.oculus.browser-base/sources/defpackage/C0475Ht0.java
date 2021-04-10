package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: Ht0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0475Ht0 extends AbstractC2406em {
    public final /* synthetic */ AbstractC0536It0 G;

    public C0475Ht0(AbstractC0536It0 it0) {
        this.G = it0;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        C1796bA bAVar = (C1796bA) this.G;
        Integer num = bAVar.m0;
        if (!(num == null || num.intValue() == 0 || !AbstractC4089od0.a(bAVar.U, bAVar.J(bAVar.m0)))) {
            bAVar.T(bAVar.m0.intValue(), bAVar.n0);
        }
        bAVar.m0 = 0;
        bAVar.n0 = 0;
        int i = bAVar.L;
        if (i == 2 || i == 1) {
            bAVar.k0(true);
        } else {
            bAVar.k0(false);
        }
        OverlayPanelContent overlayPanelContent = bAVar.y0;
        if (overlayPanelContent != null) {
            overlayPanelContent.u = (int) ((bAVar.E0 - bAVar.U) / bAVar.F);
        }
        if (bAVar.H0 && bAVar.L == 4) {
            bAVar.H0 = false;
            ((ContextualSearchManager) bAVar.I0).q();
        }
    }
}
