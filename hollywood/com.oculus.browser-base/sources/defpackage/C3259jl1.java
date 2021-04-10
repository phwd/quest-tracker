package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: jl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3259jl1 extends AbstractC2406em {
    public final /* synthetic */ ToolbarPhone G;

    public C3259jl1(ToolbarPhone toolbarPhone) {
        this.G = toolbarPhone;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        ToolbarPhone toolbarPhone = this.G;
        toolbarPhone.j0 = null;
        toolbarPhone.C0();
        toolbarPhone.postInvalidate();
        toolbarPhone.V0.run();
    }

    @Override // defpackage.AbstractC2406em
    public void c(Animator animator) {
        ToolbarPhone toolbarPhone = this.G;
        int i = ToolbarPhone.U;
        toolbarPhone.C0();
    }
}
