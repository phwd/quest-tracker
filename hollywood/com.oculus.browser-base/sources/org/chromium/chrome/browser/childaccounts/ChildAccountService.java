package org.chromium.chrome.browser.childaccounts;

import android.app.Activity;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChildAccountService {
    public static void reauthenticateChildAccount(WindowAndroid windowAndroid, String str, long j) {
        Object obj = ThreadUtils.f10596a;
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            PostTask.b(Zo1.f9374a, new RunnableC1132Sn(j), 0);
            return;
        }
        AccountManagerFacadeProvider.getInstance().b(V1.b(str), activity, new C1193Tn(j));
    }
}
