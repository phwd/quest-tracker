package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import java.util.List;
import org.chromium.base.task.PostTask;

/* renamed from: it0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3111it0 implements Runnable {
    public final C3795mt0 F;
    public final List G;
    public final Activity H;
    public final Runnable I;

    public RunnableC3111it0(C3795mt0 mt0, List list, Activity activity, Runnable runnable) {
        this.F = mt0;
        this.G = list;
        this.H = activity;
        this.I = runnable;
    }

    public void run() {
        C3795mt0 mt0 = this.F;
        List list = this.G;
        Activity activity = this.H;
        Runnable runnable = this.I;
        if (!mt0.b.isEmpty()) {
            list.size();
            PackageManager packageManager = activity.getPackageManager();
            for (int i = 0; i < list.size(); i++) {
                ComponentName componentName = new ComponentName(activity, (Class) list.get(i));
                if (packageManager.getComponentEnabledSetting(componentName) != 1) {
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                    mt0.d.add(componentName);
                }
            }
            PostTask.b(Zo1.d, runnable, 0);
        }
    }
}
