package org.chromium.chrome.browser.signin;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.PersonalizedSigninPromoView;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninPromoUtil {
    public static void a(C5264vW0 vw0, WG0 wg0, PersonalizedSigninPromoView personalizedSigninPromoView, AbstractC5094uW0 uw0) {
        ViewTreeObserver$OnPreDrawListenerC1587a00 a00 = vw0.b;
        C3522lG lGVar = null;
        if (a00 != null) {
            a00.a(null);
            vw0.b = null;
        }
        Context context = personalizedSigninPromoView.getContext();
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        if (instance.d()) {
            List n = instance.n();
            if (n.size() > 0) {
                String str = ((Account) n.get(0)).name;
                wg0.Z(Collections.singletonList(str));
                lGVar = wg0.W(str);
            }
        }
        vw0.e(context, personalizedSigninPromoView, lGVar, uw0);
    }

    public static void b(C5264vW0 vw0, WG0 wg0, PersonalizedSigninPromoView personalizedSigninPromoView, AbstractC5094uW0 uw0) {
        String b = CoreAccountInfo.b(C5949zZ.a().c(Profile.b()).b(0));
        wg0.Z(Collections.singletonList(b));
        C3522lG W = wg0.W(b);
        ViewTreeObserver$OnPreDrawListenerC1587a00 a00 = vw0.b;
        if (a00 != null) {
            a00.a(null);
            vw0.b = null;
        }
        vw0.e(personalizedSigninPromoView.getContext(), personalizedSigninPromoView, W, uw0);
        personalizedSigninPromoView.f10765J.setText(R.string.f62990_resource_name_obfuscated_RES_2131953616);
        personalizedSigninPromoView.K.setVisibility(8);
    }

    public static void openSigninActivityForPromo(WindowAndroid windowAndroid, int i) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity != null) {
            BV0.a().b(activity, i);
        }
    }
}
