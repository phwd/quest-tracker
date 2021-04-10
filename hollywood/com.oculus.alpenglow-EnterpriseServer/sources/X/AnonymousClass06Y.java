package X;

import android.app.Application;
import androidx.core.app.ActivityRecreator$LifecycleCheckCallbacks;

/* renamed from: X.06Y  reason: invalid class name */
public class AnonymousClass06Y implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.app.ActivityRecreator$2";
    public final /* synthetic */ Application A00;
    public final /* synthetic */ ActivityRecreator$LifecycleCheckCallbacks A01;

    public AnonymousClass06Y(Application application, ActivityRecreator$LifecycleCheckCallbacks activityRecreator$LifecycleCheckCallbacks) {
        this.A00 = application;
        this.A01 = activityRecreator$LifecycleCheckCallbacks;
    }

    public final void run() {
        this.A00.unregisterActivityLifecycleCallbacks(this.A01);
    }
}
