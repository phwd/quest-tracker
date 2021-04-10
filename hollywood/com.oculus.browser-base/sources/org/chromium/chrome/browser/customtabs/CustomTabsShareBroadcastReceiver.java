package org.chromium.chrome.browser.customtabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CustomTabsShareBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        AbstractC3535lK0.a("MobileTopToolbarShareButton");
        String dataString = intent.getDataString();
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("text/plain");
        intent2.putExtra("android.intent.extra.TEXT", dataString);
        Intent createChooser = Intent.createChooser(intent2, null);
        createChooser.setFlags(268435456);
        context.startActivity(createChooser);
    }
}
