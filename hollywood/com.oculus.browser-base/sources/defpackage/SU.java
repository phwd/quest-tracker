package defpackage;

import com.oculus.browser.Gatekeeper;

/* renamed from: SU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SU implements Runnable {
    public final /* synthetic */ Gatekeeper F;

    public SU(Gatekeeper gatekeeper) {
        this.F = gatekeeper;
    }

    public void run() {
        Gatekeeper gatekeeper = this.F;
        gatekeeper.M = false;
        gatekeeper.k();
    }
}
