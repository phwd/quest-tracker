package androidx.lifecycle;

import X.AnonymousClass0AO;
import X.AnonymousClass0Ae;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ReportFragment$LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    public void onActivityDestroyed(@NonNull Activity activity) {
    }

    public void onActivityPaused(@NonNull Activity activity) {
    }

    public void onActivityResumed(@NonNull Activity activity) {
    }

    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    public void onActivityStarted(@NonNull Activity activity) {
    }

    public void onActivityStopped(@NonNull Activity activity) {
    }

    public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        AnonymousClass0Ae.A01(activity, AnonymousClass0AO.ON_CREATE);
    }

    public void onActivityPostResumed(@NonNull Activity activity) {
        AnonymousClass0Ae.A01(activity, AnonymousClass0AO.ON_RESUME);
    }

    public void onActivityPostStarted(@NonNull Activity activity) {
        AnonymousClass0Ae.A01(activity, AnonymousClass0AO.ON_START);
    }

    public void onActivityPreDestroyed(@NonNull Activity activity) {
        AnonymousClass0Ae.A01(activity, AnonymousClass0AO.ON_DESTROY);
    }

    public void onActivityPrePaused(@NonNull Activity activity) {
        AnonymousClass0Ae.A01(activity, AnonymousClass0AO.ON_PAUSE);
    }

    public void onActivityPreStopped(@NonNull Activity activity) {
        AnonymousClass0Ae.A01(activity, AnonymousClass0AO.ON_STOP);
    }
}
