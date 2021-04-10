package defpackage;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import java.lang.ref.WeakReference;
import org.chromium.base.ContextUtils;
import org.chromium.base.JavaExceptionReporter;

/* renamed from: hE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2833hE extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f10057a = new Handler();
    public final Runnable b = new RunnableC2662gE(this);
    public final WeakReference c;
    public Intent d;
    public boolean e;

    public C2833hE(Activity activity) {
        this.c = new WeakReference(activity);
    }

    public void a(Intent intent) {
        this.f10057a.removeCallbacks(this.b);
        this.f10057a.postDelayed(this.b, 10000);
        this.d = intent;
        boolean z = intent != null;
        if (z != this.e) {
            this.e = z;
            Context applicationContext = ContextUtils.getApplicationContext();
            if (z) {
                applicationContext.registerReceiver(this, new IntentFilter("android.intent.action.USER_PRESENT"));
            } else {
                applicationContext.unregisterReceiver(this);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction()) && this.d != null) {
            Activity activity = (Activity) this.c.get();
            if (activity != null) {
                try {
                    activity.startActivity(this.d);
                } catch (ActivityNotFoundException e2) {
                    int i = JavaExceptionReporter.f10588a;
                    N.MLlibBXh(false, e2);
                }
            }
            a(null);
        }
    }
}
