package org.chromium.chrome.browser.password_manager;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.HashSet;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordManagerLauncher {
    public static boolean a() {
        ProfileSyncService b;
        if (C5949zZ.a().c(Profile.b()).c() && (b = ProfileSyncService.b()) != null && ((HashSet) b.c()).contains(4) && !b.o()) {
            return true;
        }
        return false;
    }

    public static void b(Activity activity, int i) {
        if (a() && N.M09VlOh_("PasswordScriptsFetching")) {
            N.MVksKGki();
        }
        AbstractC3364kK0.g("PasswordManager.ManagePasswordsReferrer", i, 9);
        Bundle bundle = new Bundle();
        bundle.putInt("manage-passwords-referrer", i);
        Intent intent = new Intent();
        intent.setClass(activity, XS0.class);
        if (!(activity instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", "org.chromium.chrome.browser.password_manager.settings.PasswordSettings");
        intent.putExtra("show_fragment_args", bundle);
        activity.startActivity(intent);
    }

    public static void showPasswordSettings(WebContents webContents, int i) {
        WindowAndroid I = webContents.I();
        if (I != null) {
            b((Activity) I.s0().get(), i);
        }
    }
}
