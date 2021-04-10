package defpackage;

import android.app.Activity;
import android.content.ComponentName;

/* renamed from: jt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3282jt0 implements Runnable {
    public final C3795mt0 F;
    public final Activity G;

    public RunnableC3282jt0(C3795mt0 mt0, Activity activity) {
        this.F = mt0;
        this.G = activity;
    }

    public void run() {
        C3795mt0 mt0 = this.F;
        Activity activity = this.G;
        if (mt0.b.isEmpty() && !mt0.d.isEmpty()) {
            mt0.d.size();
            for (int i = 0; i < mt0.d.size(); i++) {
                activity.getPackageManager().setComponentEnabledSetting((ComponentName) mt0.d.get(i), 2, 1);
            }
            mt0.d.clear();
        }
    }
}
