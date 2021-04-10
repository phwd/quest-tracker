package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: Yk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yk1 extends AbstractC2406em {
    public final /* synthetic */ ToolbarPhone G;

    public Yk1(ToolbarPhone toolbarPhone) {
        this.G = toolbarPhone;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        ToolbarPhone toolbarPhone = this.G;
        toolbarPhone.T0 = false;
        toolbarPhone.D0();
    }
}
