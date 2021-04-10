package defpackage;

import android.view.View;
import android.widget.TextView;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.signin.SigninFragmentBase;

/* renamed from: MV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MV0 implements View.OnClickListener {
    public final SigninFragmentBase F;

    public MV0(SigninFragmentBase signinFragmentBase) {
        this.F = signinFragmentBase;
    }

    public void onClick(View view) {
        SigninFragmentBase signinFragmentBase = this.F;
        if (signinFragmentBase.e1()) {
            signinFragmentBase.N0 = true;
            signinFragmentBase.P0 = false;
            AbstractC3535lK0.a("Signin_Signin_WithDefaultSyncSettings");
            UV0 uv0 = new UV0(signinFragmentBase, (TextView) view);
            Executor executor = AbstractC2032cb.f9616a;
            uv0.f();
            ((ExecutorC1463Ya) executor).execute(uv0.e);
            signinFragmentBase.u1(false);
        }
    }
}
