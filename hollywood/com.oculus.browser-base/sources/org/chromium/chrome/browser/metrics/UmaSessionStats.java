package org.chromium.chrome.browser.metrics;

import J.N;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UmaSessionStats {

    /* renamed from: a  reason: collision with root package name */
    public static long f10696a;
    public AbstractC0124Ca1 b;
    public AbstractC1099Sa1 c;
    public final Context d;
    public ComponentCallbacks e;
    public boolean f;

    public UmaSessionStats(Context context) {
        this.d = context;
    }

    public static void a(boolean z) {
        WF0 a2 = WF0.a();
        a2.c.m("metrics_reporting", z);
        a2.c();
        if (!z) {
            C2463f41 a3 = C2463f41.a();
            ContextUtils.getApplicationContext();
            Objects.requireNonNull(a3);
        }
        N.Mh1r7OJ$(z);
        e();
    }

    public static boolean b() {
        return ((BrowserStartupControllerImpl) AbstractC4280pk.a()).f();
    }

    public static void e() {
        WF0 a2 = WF0.a();
        a2.c();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) a2.b.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = true;
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected()) || (!a2.b() && !AbstractC1575Zv.e().g("force-dump-upload"))) {
            z = false;
        }
        N.Mq3Hvtdc(z);
    }

    public static boolean hasVisibleActivity() {
        return ApplicationStatus.hasVisibleActivities();
    }

    public void c() {
        if (this.b != null) {
            this.d.unregisterComponentCallbacks(this.e);
            this.c.destroy();
            this.b = null;
        }
        N.MAKAEiev(f10696a, this);
    }

    public void d(AbstractC0124Ca1 ca1) {
        if (f10696a == 0) {
            f10696a = N.MU4tSmY3();
        }
        this.b = ca1;
        if (ca1 != null) {
            ComponentCallbacksC3442kp1 kp1 = new ComponentCallbacksC3442kp1(this);
            this.e = kp1;
            this.d.registerComponentCallbacks(kp1);
            boolean z = true;
            if (this.d.getResources().getConfiguration().keyboard == 1) {
                z = false;
            }
            this.f = z;
            this.c = new C3613lp1(this, this.b);
        }
        N.M950EFso(f10696a, this);
        WF0 a2 = WF0.a();
        a2.c.m("in_metrics_sample", N.MGJFzlge());
        a2.c();
        e();
        Object obj = HD.f8145a;
        try {
            FD fd = new FD();
            Executor executor = AbstractC2032cb.f9616a;
            fd.f();
            ((ExecutorC1463Ya) executor).execute(fd.e);
        } catch (RejectedExecutionException unused) {
        }
    }
}
