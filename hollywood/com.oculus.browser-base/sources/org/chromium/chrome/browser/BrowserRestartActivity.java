package org.chromium.chrome.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserRestartActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Process.killProcess(U20.h(intent, "org.chromium.chrome.browser.BrowserRestartActivity.main_pid", -1));
        if (U20.d(intent, "org.chromium.chrome.browser.BrowserRestartActivity.restart", false)) {
            Context applicationContext = ContextUtils.getApplicationContext();
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setPackage(applicationContext.getPackageName());
            intent2.setFlags(268435456);
            applicationContext.startActivity(intent2);
        }
        finish();
        Process.killProcess(Process.myPid());
    }
}
