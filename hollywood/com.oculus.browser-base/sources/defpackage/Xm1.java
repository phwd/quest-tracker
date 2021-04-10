package defpackage;

import android.util.Base64;

/* renamed from: Xm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Xm1 implements Runnable {
    public final C4300pq1 F;

    public Xm1(C4300pq1 pq1) {
        this.F = pq1;
    }

    public void run() {
        AbstractC2425es0.e().edit().putString("UpdateProtos_Tracking", Base64.encodeToString(this.F.c(), 0)).apply();
    }
}
