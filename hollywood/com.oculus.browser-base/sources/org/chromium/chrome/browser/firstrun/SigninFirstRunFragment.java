package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninFirstRunFragment extends SigninFragmentBase implements UQ {
    public Bundle U0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void e0(Context context) {
        super.e0(context);
        Bundle D = TQ.a(this).D();
        String string = D.getString("ForceSigninAccountTo");
        if (string == null) {
            this.U0 = SigninFragmentBase.f1(null);
        } else {
            int i = D.getInt("ChildAccountStatus");
            Bundle bundle = new Bundle();
            bundle.putInt("SigninFragmentBase.SigninFlowType", 1);
            bundle.putString("SigninFragmentBase.AccountName", string);
            bundle.putInt("SigninFragmentBase.ChildAccountStatus", i);
            this.U0 = bundle;
        }
        AbstractC3364kK0.d("Signin.AndroidDeviceAccountsNumberWhenEnteringFRE", Math.min(AccountManagerFacadeProvider.getInstance().n().size(), 2));
        AbstractC3535lK0.a("MobileFre.SignInShown");
        AbstractC3535lK0.a("Signin_Signin_FromStartPage");
        AbstractC3364kK0.g("Signin.SigninStartedAccessPoint", 0, 34);
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public int j1() {
        return R.string.f55970_resource_name_obfuscated_RES_2131952914;
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public Bundle k1() {
        return this.U0;
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public void r1(String str, boolean z, boolean z2, Runnable runnable) {
        TQ.a(this).M(str, z, z2);
        TQ.a(this).r();
        ((SV0) runnable).run();
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public void s1() {
        if (l1()) {
            TQ.a(this).E();
            return;
        }
        C4072oW0 ow0 = C4072oW0.f10556a;
        ow0.b.o("ntp.signin_promo_suppression_period_start", System.currentTimeMillis());
        TQ.a(this).f();
        TQ.a(this).r();
    }
}
