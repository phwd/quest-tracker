package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: kl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3430kl1 extends AbstractC2406em {
    public final /* synthetic */ boolean G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ ToolbarPhone I;

    public C3430kl1(ToolbarPhone toolbarPhone, boolean z, boolean z2) {
        this.I = toolbarPhone;
        this.G = z;
        this.H = z2;
    }

    @Override // defpackage.AbstractC2406em
    public void a(Animator animator) {
        if (!this.G) {
            this.I.y0 = false;
        }
        this.I.u0 = false;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        if (!this.G) {
            ToolbarPhone toolbarPhone = this.I;
            toolbarPhone.y0 = false;
            toolbarPhone.z0 = false;
            toolbarPhone.requestLayout();
        }
        ToolbarPhone toolbarPhone2 = this.I;
        C4080oa0 oa0 = (C4080oa0) toolbarPhone2.W.G;
        boolean z = this.G;
        boolean z2 = this.H;
        boolean f = toolbarPhone2.f9169J.f();
        oa0.F.b(z, z2);
        if (f) {
            R11 r11 = oa0.G;
            r11.r();
            r11.t();
        }
        this.I.u0 = false;
    }

    @Override // defpackage.AbstractC2406em
    public void c(Animator animator) {
        if (!this.G) {
            this.I.y0 = true;
            return;
        }
        ToolbarPhone toolbarPhone = this.I;
        toolbarPhone.z0 = true;
        toolbarPhone.requestLayout();
    }
}
