package org.chromium.chrome.browser.offlinepages;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CctOfflinePageModelObserver {
    public static void onPageChanged(String str, boolean z, String str2) {
        C0593Jr0 jr0 = new C0593Jr0(str);
        if (!jr0.c()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_new", z);
            bundle.putString("url", str2);
            String str3 = jr0.f8316a;
            Objects.requireNonNull(AppHooks.get());
            if (!Collections.emptyList().contains(str3)) {
                StringBuilder i = AbstractC2531fV.i("Non-allowlisted app: ");
                i.append(jr0.f8316a);
                AbstractC1220Ua0.f("CctModelObserver", i.toString(), new Object[0]);
                return;
            }
            Context applicationContext = ContextUtils.getApplicationContext();
            if (!Arrays.equals(jr0.b, C0593Jr0.b(applicationContext, jr0.f8316a))) {
                AbstractC1220Ua0.f("CctModelObserver", "Signature hashes are different", new Object[0]);
                return;
            }
            Intent intent = new Intent();
            intent.setAction("org.chromium.chrome.browser.offlinepages.OFFLINE_PAGES_CHANGED");
            intent.setPackage(jr0.f8316a);
            PendingIntent broadcast = PendingIntent.getBroadcast(applicationContext, 0, new Intent(), 134217728);
            broadcast.cancel();
            intent.putExtra("org.chromium.chrome.extra.CHROME_NAME_PENDING_INTENT", broadcast);
            intent.putExtra("org.chromium.chrome.extra.OFFLINE_PAGE_INFO", bundle);
            applicationContext.sendBroadcast(intent);
        }
    }
}
