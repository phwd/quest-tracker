package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.offlinepages.AutoFetchNotifier;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: ic  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3060ic extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final String f10148a;
    public final int b;
    public final long c;
    public final String d;
    public final String e;

    public C3060ic(String str, int i, long j, String str2, String str3) {
        this.f10148a = str;
        this.b = i;
        this.c = j;
        this.d = str2;
        this.e = str3;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Notification notification;
        String str = this.f10148a;
        int i = this.b;
        long j = this.c;
        String str2 = this.d;
        String str3 = this.e;
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent(applicationContext, AutoFetchNotifier.CompleteNotificationReceiver.class);
        intent.putExtra("org.chromium.chrome.browser.offlinepages.URL", str3);
        intent.putExtra("REUSE_TAB_ORIGINAL_URL", str2);
        S20.w(((LoadUrlParams) obj).e, intent);
        intent.putExtra("REUSE_TAB_MATCHING_ID", i);
        intent.putExtra("com.android.browser.application_id", applicationContext.getPackageName());
        intent.putExtra("notification_action", 4);
        intent.putExtra("org.chromium.chrome.browser.tab_launch_type", 2);
        intent.setPackage(applicationContext.getPackageName());
        int i2 = (int) j;
        CB0 a2 = CB0.a(applicationContext, i2, intent, 0);
        Intent intent2 = new Intent(applicationContext, AutoFetchNotifier.CompleteNotificationReceiver.class);
        intent2.putExtra("com.android.browser.application_id", applicationContext.getPackageName());
        intent2.putExtra("notification_action", 3);
        intent2.setPackage(applicationContext.getPackageName());
        C3444kq0 b2 = AbstractC3786mq0.b(true, "downloads", null, new C0832Np0(14, "OfflinePageAutoFetchNotification", i2)).x(true).B(a2).H(str).F(applicationContext.getString(R.string.f56510_resource_name_obfuscated_RES_2131952968)).r("OfflinePageAutoFetchNotification").o(-1).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).w(CB0.a(applicationContext, 0, intent2, 0)).b();
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
        if (b2 == null || (notification = b2.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = b2.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        AbstractC3102iq0.f10166a.b(14, b2.f10306a);
        AbstractC3364kK0.g("OfflinePages.AutoFetch.CompleteNotificationAction", 0, 5);
    }
}
