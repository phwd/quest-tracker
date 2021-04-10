package org.chromium.chrome.browser.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninUtils {
    public static boolean a(Activity activity) {
        return U20.q(activity, new Intent("android.settings.SYNC_SETTINGS"));
    }

    public static void openAccountManagementScreen(WindowAndroid windowAndroid, int i, String str) {
        Object obj = ThreadUtils.f10596a;
        int i2 = AccountManagementFragment.G0;
        Bundle bundle = new Bundle();
        bundle.putInt("ShowGAIAServiceType", i);
        Context applicationContext = ContextUtils.getApplicationContext();
        String name = AccountManagementFragment.class.getName();
        Intent l = AbstractC2531fV.l(applicationContext, XS0.class);
        if (!(applicationContext instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", bundle);
        U20.q(applicationContext, l);
    }

    public static void openAccountPickerBottomSheet(WindowAndroid windowAndroid, String str) {
        Object obj = ThreadUtils.f10596a;
        if (!C5949zZ.a().d(Profile.b()).i()) {
            AbstractC3901nW0.a(7);
        } else if (AccountManagerFacadeProvider.getInstance().n().isEmpty()) {
            AbstractC3901nW0.a(0);
        } else {
            AbstractC4448qj qjVar = (AbstractC4448qj) AbstractC5978zj.f11762a.e(windowAndroid.U);
            if (qjVar != null) {
                ChromeActivity chromeActivity = (ChromeActivity) windowAndroid.s0().get();
                new C2965i1(chromeActivity, qjVar, new D1(windowAndroid, chromeActivity.K0(), new Sx1(), str), ((AbstractC0246Ea1) chromeActivity.P()).l(false), chromeActivity.S(true), C2535fX.a());
            }
        }
    }
}
