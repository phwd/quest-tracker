package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.oculus.browser.BrowserRestartService;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: Nr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0835Nr implements S9, Z9 {
    public static C0835Nr F;
    public final Handler G = new Handler(Looper.getMainLooper());
    public final Runnable H = new RunnableC0774Mr(this);
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f8580J;
    public int K;

    public final void a() {
        Object obj = ThreadUtils.f10596a;
        if (!this.I) {
            this.I = true;
            this.G.removeCallbacks(this.H);
            Context applicationContext = ContextUtils.getApplicationContext();
            boolean z = this.f8580J;
            int i = BrowserRestartService.F;
            Intent intent = new Intent();
            intent.setClassName(applicationContext.getPackageName(), BrowserRestartService.class.getName());
            intent.setFlags(268435456);
            intent.putExtra("org.chromium.chrome.browser.BrowserRestartService.main_pid", Process.myPid());
            intent.putExtra("org.chromium.chrome.browser.BrowserRestartService.restart", z);
            applicationContext.startService(intent);
        }
    }

    @Override // defpackage.S9
    public void b(boolean z) {
        this.f8580J = z;
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            ApplicationStatus.g(this, activity);
            this.K++;
            activity.finish();
        }
        this.G.postDelayed(this.H, 1000);
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 6) {
            int i2 = this.K - 1;
            this.K = i2;
            if (i2 == 0) {
                a();
            }
        }
    }
}
