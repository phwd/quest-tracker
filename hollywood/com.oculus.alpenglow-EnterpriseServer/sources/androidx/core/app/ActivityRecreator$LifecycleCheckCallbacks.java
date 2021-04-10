package androidx.core.app;

import X.AnonymousClass06Z;
import X.C006406b;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;

public final class ActivityRecreator$LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
    public Object A00;
    public Activity A01;
    public boolean A02 = false;
    public boolean A03 = false;
    public boolean A04 = false;
    public final int A05;

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if (this.A01 == activity) {
            this.A01 = null;
            this.A02 = true;
        }
    }

    public final void onActivityPaused(Activity activity) {
        if (this.A02 && !this.A04 && !this.A03) {
            Object obj = this.A00;
            int i = this.A05;
            try {
                Object obj2 = C006406b.A02.get(activity);
                if (obj2 == obj && activity.hashCode() == i) {
                    C006406b.A00.postAtFrontOfQueue(new AnonymousClass06Z(C006406b.A01.get(activity), obj2));
                    this.A04 = true;
                    this.A00 = null;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while fetching field values", th);
            }
        }
    }

    public final void onActivityStarted(Activity activity) {
        if (this.A01 == activity) {
            this.A03 = true;
        }
    }

    public ActivityRecreator$LifecycleCheckCallbacks(@NonNull Activity activity) {
        this.A01 = activity;
        this.A05 = activity.hashCode();
    }
}
