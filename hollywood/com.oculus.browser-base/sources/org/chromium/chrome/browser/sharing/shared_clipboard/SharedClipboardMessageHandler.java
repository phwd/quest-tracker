package org.chromium.chrome.browser.sharing.shared_clipboard;

import J.N;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.sharing.SharingServiceProxy;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SharedClipboardMessageHandler {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class TapReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class TryAgainReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Notification notification;
            ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("SharedClipboard", 11);
            String n = U20.n(intent, "SharedClipboard.EXTRA_DEVICE_GUID");
            String n2 = U20.n(intent, "SharedClipboard.EXTRA_DEVICE_CLIENT_NAME");
            String n3 = U20.n(intent, "android.intent.extra.TEXT");
            if (!TextUtils.isEmpty(n) && !TextUtils.isEmpty(n2) && !TextUtils.isEmpty(n3)) {
                Context applicationContext = ContextUtils.getApplicationContext();
                C3444kq0 b = AbstractC3786mq0.b(true, "sharing", null, new C0832Np0(18, "SharedClipboard", 11)).H(applicationContext.getResources().getString(R.string.f61780_resource_name_obfuscated_RES_2131953495, n2)).r("SharedClipboard").D(applicationContext.getResources().getColor(R.color.f11230_resource_name_obfuscated_RES_2131099813)).o(1).A(R.drawable.f29870_resource_name_obfuscated_RES_2131231027).L(0, 0, true).u(true).G(-1).b();
                NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
                if (b == null || (notification = b.f10306a) == null) {
                    AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
                } else {
                    C0832Np0 np0 = b.b;
                    notificationManager.notify(np0.b, np0.c, notification);
                }
                AbstractC3102iq0.f10166a.b(18, b.f10306a);
                C1321Vq.b().e();
                SharingServiceProxy a2 = SharingServiceProxy.a();
                IU0 iu0 = new IU0(n, n3, n2);
                Objects.requireNonNull(a2);
                long j = SharingServiceProxy.b;
                if (j == 0) {
                    iu0.run();
                } else {
                    N.MBEvP57R(j, iu0);
                }
            }
        }
    }

    public static void showNotification(String str) {
        String str2;
        Context applicationContext = ContextUtils.getApplicationContext();
        CB0 a2 = CB0.a(applicationContext, 0, new Intent(applicationContext, TapReceiver.class), 134217728);
        Resources resources = applicationContext.getResources();
        if (TextUtils.isEmpty(str)) {
            str2 = resources.getString(R.string.f61560_resource_name_obfuscated_RES_2131953473);
        } else {
            str2 = resources.getString(R.string.f61550_resource_name_obfuscated_RES_2131953472, str);
        }
        QU0.a(18, "SharedClipboard", 10, a2, str2, resources.getString(R.string.f61540_resource_name_obfuscated_RES_2131953471), R.drawable.f29870_resource_name_obfuscated_RES_2131231027, R.drawable.f34950_resource_name_obfuscated_RES_2131231535, R.color.f11230_resource_name_obfuscated_RES_2131099813);
    }
}
