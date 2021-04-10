package defpackage;

import com.oculus.browser.VrShellDelegate;

/* renamed from: sw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4829sw1 implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ VrShellDelegate G;

    public RunnableC4829sw1(VrShellDelegate vrShellDelegate, boolean z) {
        this.G = vrShellDelegate;
        this.F = z;
    }

    public void run() {
        VrShellDelegate vrShellDelegate = this.G;
        if (vrShellDelegate.H != null) {
            vrShellDelegate.f(this.F);
        }
    }
}
