package defpackage;

import android.util.Log;

/* renamed from: jH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC3185jH1 implements Runnable {
    public final /* synthetic */ EG1 F;
    public final /* synthetic */ XG1 G;

    public RunnableC3185jH1(XG1 xg1, EG1 eg1) {
        this.G = xg1;
        this.F = eg1;
    }

    public final void run() {
        Log.isLoggable("EnhancedIntentService", 3);
        this.G.f9199a.handleIntent(this.F.f7950a);
        this.F.a();
    }
}
