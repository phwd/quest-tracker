package com.oculus.browser;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserRestartService extends Service {
    public static final /* synthetic */ int F = 0;
    public final IBinder G = new BinderC3596lk(this);
    public final Handler H = new Handler();

    public IBinder onBind(Intent intent) {
        return this.G;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int h = U20.h(intent, "org.chromium.chrome.browser.BrowserRestartService.main_pid", -1);
        Log.i("BrowserRestartService", "Killing the browser, pid = " + h);
        Process.killProcess(h);
        if (U20.d(intent, "org.chromium.chrome.browser.BrowserRestartService.restart", false)) {
            Context applicationContext = ContextUtils.getApplicationContext();
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setPackage(applicationContext.getPackageName());
            intent2.setFlags(268435456);
            this.H.postDelayed(new RunnableC3425kk(this, intent2, applicationContext), 300);
        }
        return super.onStartCommand(intent, i, i2);
    }
}
