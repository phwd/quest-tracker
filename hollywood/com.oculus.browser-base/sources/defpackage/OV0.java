package defpackage;

import android.view.View;
import android.widget.TextView;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.signin.SigninFragmentBase;

/* renamed from: OV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OV0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SigninFragmentBase f8627a;

    public OV0(SigninFragmentBase signinFragmentBase) {
        this.f8627a = signinFragmentBase;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SigninFragmentBase signinFragmentBase = this.f8627a;
        View view = (View) obj;
        if (signinFragmentBase.e1()) {
            signinFragmentBase.N0 = true;
            AbstractC3535lK0.a("Signin_Signin_WithAdvancedSyncSettings");
            UV0 uv0 = new UV0(signinFragmentBase, (TextView) view);
            Executor executor = AbstractC2032cb.f9616a;
            uv0.f();
            ((ExecutorC1463Ya) executor).execute(uv0.e);
            signinFragmentBase.u1(true);
        }
    }
}
