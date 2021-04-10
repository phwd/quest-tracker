package defpackage;

import android.util.Log;

/* renamed from: dJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2167dJ1 implements Runnable {
    public final FI1 F;
    public final C5898zC1 G;

    public RunnableC2167dJ1(FI1 fi1, C5898zC1 zc1) {
        this.F = fi1;
        this.G = zc1;
    }

    public final void run() {
        FI1 fi1 = this.F;
        int i = this.G.f11733a;
        synchronized (fi1) {
            C5898zC1 zc1 = (C5898zC1) fi1.e.get(i);
            if (zc1 != null) {
                StringBuilder sb = new StringBuilder(31);
                sb.append("Timing out request: ");
                sb.append(i);
                Log.w("MessengerIpcClient", sb.toString());
                fi1.e.remove(i);
                zc1.a(new C5218vC1(3, "Timed out waiting for response"));
                fi1.c();
            }
        }
    }
}
