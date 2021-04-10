package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.chrome.browser.signin.SigninUtils;

/* renamed from: EV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class EV0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SigninFragmentBase f7964a;

    public EV0(SigninFragmentBase signinFragmentBase) {
        this.f7964a = signinFragmentBase;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SigninFragmentBase signinFragmentBase = this.f7964a;
        Intent intent = (Intent) obj;
        if (intent != null) {
            signinFragmentBase.d1(intent, 1);
        } else {
            SigninUtils.a(signinFragmentBase.u());
        }
    }
}
