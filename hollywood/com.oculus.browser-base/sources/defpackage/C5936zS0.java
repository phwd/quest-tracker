package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.send_tab_to_self.SendTabToSelfNotificationReceiver;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.send_tab_to_self.NotificationManager;

/* renamed from: zS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5936zS0 extends PK {
    public final /* synthetic */ Intent F;

    public C5936zS0(SendTabToSelfNotificationReceiver sendTabToSelfNotificationReceiver, Intent intent) {
        this.F = intent;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void u() {
        Intent intent = this.F;
        String action = intent.getAction();
        String n = U20.n(intent, "send_tab_to_self.notification.guid");
        Profile b = Profile.b();
        action.hashCode();
        char c = 65535;
        switch (action.hashCode()) {
            case -138144437:
                if (action.equals("send_tab_to_self.dismiss")) {
                    c = 0;
                    break;
                }
                break;
            case 91990756:
                if (action.equals("send_tab_to_self.tap")) {
                    c = 1;
                    break;
                }
                break;
            case 1171238946:
                if (action.equals("send_tab_to_self.timeout")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                NotificationManager.hideNotification(n);
                N.Mewze4cT(b, n);
                return;
            case 1:
                Uri data = intent.getData();
                Context applicationContext = ContextUtils.getApplicationContext();
                Intent putExtra = new Intent().setAction("android.intent.action.VIEW").setData(data).setClass(applicationContext, Lr.class).addFlags(268435456).putExtra("com.android.browser.application_id", applicationContext.getPackageName()).putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
                S20.a(putExtra);
                applicationContext.startActivity(putExtra);
                NotificationManager.hideNotification(n);
                N.MOtCIw4g(b, n);
                return;
            case 2:
                N.Mewze4cT(b, n);
                return;
            default:
                return;
        }
    }
}
