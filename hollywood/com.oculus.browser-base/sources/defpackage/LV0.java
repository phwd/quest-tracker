package defpackage;

import org.chromium.chrome.browser.signin.SigninFragmentBase;

/* renamed from: LV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LV0 implements Runnable {
    public final SigninFragmentBase F;

    public LV0(SigninFragmentBase signinFragmentBase) {
        this.F = signinFragmentBase;
    }

    public void run() {
        SigninFragmentBase signinFragmentBase = this.F;
        signinFragmentBase.B0.Q.setVisibility(0);
        signinFragmentBase.B0.S.setVisibility(8);
        signinFragmentBase.B0.F.c(null);
    }
}
