package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: nU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3896nU extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("com.google.android.apps.now.account_update_broadcast".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("account_name");
            AbstractC3364kK0.g("Search.GsaAccountChangeNotificationSource", 1, 2);
            C5259vU.b(context.getApplicationContext()).f(stringExtra);
        }
    }
}
