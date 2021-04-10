package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Cn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Cn1 implements Runnable {
    public final Profile F;
    public final String G;
    public final Callback H;

    public Cn1(Profile profile, String str, Callback callback) {
        this.F = profile;
        this.G = str;
        this.H = callback;
    }

    public void run() {
        N.MC5V7t2n(this.F, this.G, this.H);
    }
}
