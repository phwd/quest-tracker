package defpackage;

import org.chromium.chrome.browser.signin.SigninFragmentBase;

/* renamed from: RV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RV0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final TV0 f8837a;
    public final boolean b;

    public RV0(TV0 tv0, boolean z) {
        this.f8837a = tv0;
        this.b = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        TV0 tv0 = this.f8837a;
        boolean z = this.b;
        Void r6 = (Void) obj;
        SigninFragmentBase signinFragmentBase = tv0.b;
        signinFragmentBase.r1(signinFragmentBase.F0, signinFragmentBase.G0, z, new SV0(tv0));
    }
}
