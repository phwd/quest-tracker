package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: al1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1713al1 extends AbstractC2406em {
    public final /* synthetic */ ToolbarPhone G;

    public C1713al1(ToolbarPhone toolbarPhone) {
        this.G = toolbarPhone;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        ToolbarPhone toolbarPhone = this.G;
        toolbarPhone.d1 = null;
        toolbarPhone.g0.setAlpha(1.0f);
        toolbarPhone.g0.setTranslationX(0.0f);
        ToolbarPhone toolbarPhone2 = this.G;
        toolbarPhone2.y0 = false;
        toolbarPhone2.e1 = false;
        toolbarPhone2.getViewTreeObserver().addOnGlobalLayoutListener(this.G.i1);
        this.G.requestLayout();
    }

    @Override // defpackage.AbstractC2406em
    public void c(Animator animator) {
        ToolbarPhone toolbarPhone = this.G;
        toolbarPhone.y0 = true;
        toolbarPhone.e1 = true;
        toolbarPhone.g0.setVisibility(0);
    }
}
