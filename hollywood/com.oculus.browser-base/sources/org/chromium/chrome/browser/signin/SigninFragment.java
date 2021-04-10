package org.chromium.chrome.browser.signin;

import android.accounts.Account;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninFragment extends SigninFragmentBase {
    public static final /* synthetic */ int U0 = 0;
    public int V0;
    public int W0;

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase, defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        String str;
        super.h0(bundle);
        this.V0 = this.K.getInt("SigninFragment.AccessPoint", -1);
        this.W0 = this.K.getInt("SigninFragment.PersonalizedPromoAction", 0);
        AbstractC3364kK0.g("Signin.SigninStartedAccessPoint", this.V0, 34);
        int i = this.W0;
        if (i != 0) {
            if (i == 1) {
                str = "Signin.SigninStartedAccessPoint.WithDefault";
            } else if (i == 2) {
                str = "Signin.SigninStartedAccessPoint.NotDefault";
            } else if (i == 3) {
                str = "Signin.SigninStartedAccessPoint.NewAccountNoExistingAccount";
            }
            AbstractC3364kK0.g(str, this.V0, 34);
        }
        int i2 = this.V0;
        if (i2 == 3) {
            AbstractC3535lK0.a("Signin_Signin_FromSettings");
        } else if (i2 == 9) {
            AbstractC3535lK0.a("Signin_Signin_FromBookmarkManager");
        } else if (i2 == 15) {
            AbstractC3535lK0.a("Signin_Signin_FromSigninPromo");
        } else if (i2 == 16) {
            AbstractC3535lK0.a("Signin_Signin_FromRecentTabs");
        } else if (i2 == 19) {
            AbstractC3535lK0.a("Signin_Signin_FromAutofillDropdown");
        } else if (i2 == 20) {
            AbstractC3535lK0.a("Signin_Signin_FromNTPContentSuggestions");
        }
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public int j1() {
        return this.V0 == 15 ? R.string.f55970_resource_name_obfuscated_RES_2131952914 : R.string.f48470_resource_name_obfuscated_RES_2131952164;
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public Bundle k1() {
        return this.K;
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public void r1(String str, boolean z, boolean z2, Runnable runnable) {
        Account c = V1.c(AccountManagerFacadeProvider.getInstance().n(), str);
        if (c == null) {
            ((SV0) runnable).run();
        } else {
            C5949zZ.a().d(Profile.b()).t(this.V0, c, new CV0(this, z2, runnable));
        }
    }

    @Override // org.chromium.chrome.browser.signin.SigninFragmentBase
    public void s1() {
        u().finish();
    }
}
