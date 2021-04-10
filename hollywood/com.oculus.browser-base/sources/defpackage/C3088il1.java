package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: il1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3088il1 extends AbstractC2406em {
    public final /* synthetic */ ToolbarPhone G;

    public C3088il1(ToolbarPhone toolbarPhone) {
        this.G = toolbarPhone;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        ToolbarPhone toolbarPhone = this.G;
        int i = ToolbarPhone.U;
        toolbarPhone.C0();
        toolbarPhone.postInvalidate();
        toolbarPhone.V0.run();
    }
}
