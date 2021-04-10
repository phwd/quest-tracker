package X;

import androidx.core.app.ActivityRecreator$LifecycleCheckCallbacks;

/* renamed from: X.06X  reason: invalid class name */
public class AnonymousClass06X implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.app.ActivityRecreator$1";
    public final /* synthetic */ ActivityRecreator$LifecycleCheckCallbacks A00;
    public final /* synthetic */ Object A01;

    public AnonymousClass06X(ActivityRecreator$LifecycleCheckCallbacks activityRecreator$LifecycleCheckCallbacks, Object obj) {
        this.A00 = activityRecreator$LifecycleCheckCallbacks;
        this.A01 = obj;
    }

    public final void run() {
        this.A00.A00 = this.A01;
    }
}
