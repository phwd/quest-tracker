package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.findinpage.FindToolbarTablet;

/* renamed from: FQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FQ extends AbstractC2406em {
    public final /* synthetic */ FindToolbarTablet G;

    public FQ(FindToolbarTablet findToolbarTablet) {
        this.G = findToolbarTablet;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        this.G.g0 = null;
    }

    @Override // defpackage.AbstractC2406em
    public void c(Animator animator) {
        this.G.setVisibility(0);
        this.G.postInvalidateOnAnimation();
        FQ.super.g();
    }
}
