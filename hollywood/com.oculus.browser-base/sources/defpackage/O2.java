package defpackage;

import android.app.Application;

/* renamed from: O2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O2 implements Runnable {
    public final /* synthetic */ Application F;
    public final /* synthetic */ Q2 G;

    public O2(Application application, Q2 q2) {
        this.F = application;
        this.G = q2;
    }

    public void run() {
        this.F.unregisterActivityLifecycleCallbacks(this.G);
    }
}
