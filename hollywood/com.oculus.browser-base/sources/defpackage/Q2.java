package defpackage;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/* renamed from: Q2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Q2 implements Application.ActivityLifecycleCallbacks {
    public Object F;
    public Activity G;
    public final int H;
    public boolean I = false;

    /* renamed from: J  reason: collision with root package name */
    public boolean f8733J = false;
    public boolean K = false;

    public Q2(Activity activity) {
        this.G = activity;
        this.H = activity.hashCode();
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.G == activity) {
            this.G = null;
            this.f8733J = true;
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.f8733J && !this.K && !this.I) {
            Object obj = this.F;
            int i = this.H;
            boolean z = false;
            try {
                Object obj2 = R2.c.get(activity);
                if (obj2 == obj) {
                    if (activity.hashCode() == i) {
                        R2.g.postAtFrontOfQueue(new P2(R2.b.get(activity), obj2));
                        z = true;
                    }
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while fetching field values", th);
            }
            if (z) {
                this.K = true;
                this.F = null;
            }
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (this.G == activity) {
            this.I = true;
        }
    }

    public void onActivityStopped(Activity activity) {
    }
}
