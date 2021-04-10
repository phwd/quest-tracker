package org.chromium.chrome.browser.password_manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordCheckupLauncher {
    public static void launchCheckupInAccountWithActivity(String str, Activity activity) {
        Objects.requireNonNull(AppHooks.get());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setPackage(activity.getPackageName());
        activity.startActivity(intent);
    }

    public static void launchCheckupInAccountWithWindowAndroid(String str, WindowAndroid windowAndroid) {
        if (windowAndroid.f11022J.get() != null) {
            launchCheckupInAccountWithActivity(str, (Activity) windowAndroid.s0().get());
        }
    }

    public static void launchLocalCheckup(WindowAndroid windowAndroid) {
        if (windowAndroid.f11022J.get() != null) {
            AbstractC0848Nx0.b(new C2528fT0()).a((Context) windowAndroid.f11022J.get(), 2);
        }
    }
}
