package defpackage;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import java.lang.reflect.Proxy;
import org.chromium.base.ApplicationStatus;

/* renamed from: U9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U9 implements Application.ActivityLifecycleCallbacks {
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ApplicationStatus.a(activity, 1);
        Window.Callback callback = activity.getWindow().getCallback();
        activity.getWindow().setCallback((Window.Callback) Proxy.newProxyInstance(Window.Callback.class.getClassLoader(), new Class[]{Window.Callback.class}, new C2029ca(activity, callback)));
    }

    public void onActivityDestroyed(Activity activity) {
        ApplicationStatus.a(activity, 6);
    }

    public void onActivityPaused(Activity activity) {
        ApplicationStatus.a(activity, 4);
    }

    public void onActivityResumed(Activity activity) {
        ApplicationStatus.a(activity, 3);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        ApplicationStatus.a(activity, 2);
    }

    public void onActivityStopped(Activity activity) {
        ApplicationStatus.a(activity, 5);
    }
}
