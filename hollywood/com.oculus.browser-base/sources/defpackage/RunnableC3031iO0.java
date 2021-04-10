package defpackage;

import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: iO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3031iO0 implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ C3372kO0 G;

    public RunnableC3031iO0(C3372kO0 ko0, boolean z) {
        this.G = ko0;
        this.F = z;
    }

    public void run() {
        if (this.F) {
            this.G.F.W().o1(this.G.F.s(), "MobileSadTabFeedback", Profile.a(this.G.F.L));
            return;
        }
        this.G.F.q();
    }
}
