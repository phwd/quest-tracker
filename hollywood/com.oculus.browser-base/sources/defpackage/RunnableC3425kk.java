package defpackage;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.oculus.browser.BrowserRestartService;

/* renamed from: kk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3425kk implements Runnable {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ Context G;
    public final /* synthetic */ BrowserRestartService H;

    public RunnableC3425kk(BrowserRestartService browserRestartService, Intent intent, Context context) {
        this.H = browserRestartService;
        this.F = intent;
        this.G = context;
    }

    public void run() {
        Application application = this.H.getApplication();
        StringBuilder i = AbstractC2531fV.i("Restart intent is sent for ");
        i.append(application.getPackageName());
        Log.i("BrowserRestartService", i.toString());
        this.F.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        Intent intent = this.F;
        StringBuilder i2 = AbstractC2531fV.i("apk://");
        i2.append(application.getPackageName());
        intent.setData(Uri.parse(i2.toString()));
        this.G.startActivity(this.F);
        this.H.stopSelf();
    }
}
