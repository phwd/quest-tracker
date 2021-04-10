package defpackage;

import org.chromium.chrome.browser.browserservices.verification.OriginVerifier;

/* renamed from: vt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5329vt0 implements Runnable {
    public final C4649rt0 F;
    public final boolean G;
    public final Boolean H;
    public final /* synthetic */ OriginVerifier I;

    public RunnableC5329vt0(OriginVerifier originVerifier, C4649rt0 rt0, boolean z, Boolean bool) {
        this.I = originVerifier;
        this.F = rt0;
        this.G = z;
        this.H = bool;
    }

    public void run() {
        OriginVerifier originVerifier = this.I;
        C4649rt0 rt0 = this.F;
        boolean z = this.G;
        Boolean bool = this.H;
        char[] cArr = OriginVerifier.f10625a;
        originVerifier.d(rt0, z, bool);
    }
}
