package defpackage;

import org.chromium.chrome.browser.signin.SigninFragmentBase;

/* renamed from: FV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class FV0 implements Runnable {
    public final SigninFragmentBase F;
    public final String G;

    public FV0(SigninFragmentBase signinFragmentBase, String str) {
        this.F = signinFragmentBase;
        this.G = str;
    }

    public void run() {
        SigninFragmentBase signinFragmentBase = this.F;
        String str = this.G;
        signinFragmentBase.D0 = true;
        signinFragmentBase.E0 = str;
        signinFragmentBase.y1();
    }
}
