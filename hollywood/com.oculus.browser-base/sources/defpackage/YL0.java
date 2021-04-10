package defpackage;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: YL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YL0 implements Application.ActivityLifecycleCallbacks {
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityPostCreated(Activity activity, Bundle bundle) {
        ZL0.b(activity, EnumC3157j80.ON_CREATE);
    }

    public void onActivityPostResumed(Activity activity) {
        ZL0.b(activity, EnumC3157j80.ON_RESUME);
    }

    public void onActivityPostStarted(Activity activity) {
        ZL0.b(activity, EnumC3157j80.ON_START);
    }

    public void onActivityPreDestroyed(Activity activity) {
        ZL0.b(activity, EnumC3157j80.ON_DESTROY);
    }

    public void onActivityPrePaused(Activity activity) {
        ZL0.b(activity, EnumC3157j80.ON_PAUSE);
    }

    public void onActivityPreStopped(Activity activity) {
        ZL0.b(activity, EnumC3157j80.ON_STOP);
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
