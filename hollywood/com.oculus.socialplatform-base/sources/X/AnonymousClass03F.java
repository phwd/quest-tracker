package X;

import androidx.core.app.ActivityRecreator$LifecycleCheckCallbacks;

/* renamed from: X.03F  reason: invalid class name */
public class AnonymousClass03F implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.app.ActivityRecreator$1";
    public final /* synthetic */ ActivityRecreator$LifecycleCheckCallbacks A00;
    public final /* synthetic */ Object A01;

    public AnonymousClass03F(ActivityRecreator$LifecycleCheckCallbacks activityRecreator$LifecycleCheckCallbacks, Object obj) {
        this.A00 = activityRecreator$LifecycleCheckCallbacks;
        this.A01 = obj;
    }

    public final void run() {
        this.A00.A00 = this.A01;
    }
}
