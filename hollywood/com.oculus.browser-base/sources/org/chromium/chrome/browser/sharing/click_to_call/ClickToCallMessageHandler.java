package org.chromium.chrome.browser.sharing.click_to_call;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClickToCallMessageHandler {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class TapReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            ClickToCallMessageHandler.a(U20.n(intent, "ClickToCallMessageHandler.EXTRA_PHONE_NUMBER"));
        }
    }

    public static void a(String str) {
        Intent intent;
        if (!TextUtils.isEmpty(str)) {
            intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        } else {
            intent = new Intent("android.intent.action.DIAL");
        }
        intent.addFlags(268435456);
        try {
            ContextUtils.getApplicationContext().startActivity(intent);
            AbstractC3100ip1.f10165a.a("Sharing.ClickToCallDialerPresent", true);
        } catch (ActivityNotFoundException unused) {
            AbstractC3100ip1.f10165a.a("Sharing.ClickToCallDialerPresent", false);
            Context applicationContext = ContextUtils.getApplicationContext();
            QU0.a(17, "ClickToCall", 12, null, applicationContext.getResources().getString(R.string.f49100_resource_name_obfuscated_RES_2131952227), applicationContext.getResources().getString(R.string.f49090_resource_name_obfuscated_RES_2131952226), R.drawable.f30090_resource_name_obfuscated_RES_2131231049, R.drawable.f29890_resource_name_obfuscated_RES_2131231029, R.color.f12640_resource_name_obfuscated_RES_2131099954);
        }
    }

    public static void handleMessage(String str) {
        int i = Build.VERSION.SDK_INT;
        boolean z = true;
        if (i >= 29 ? false : i < 26 ? true : C5222vE.f(ContextUtils.getApplicationContext())) {
            a(str);
        }
        if (i < 29 && C5222vE.f(ContextUtils.getApplicationContext())) {
            z = false;
        }
        if (z) {
            Context applicationContext = ContextUtils.getApplicationContext();
            QU0.a(17, "ClickToCall", 9, CB0.a(applicationContext, 0, new Intent(applicationContext, TapReceiver.class).putExtra("ClickToCallMessageHandler.EXTRA_PHONE_NUMBER", str), 134217728), str, applicationContext.getResources().getString(R.string.f49110_resource_name_obfuscated_RES_2131952228), R.drawable.f29870_resource_name_obfuscated_RES_2131231027, R.drawable.f29880_resource_name_obfuscated_RES_2131231028, R.color.f11230_resource_name_obfuscated_RES_2131099813);
        }
    }
}
