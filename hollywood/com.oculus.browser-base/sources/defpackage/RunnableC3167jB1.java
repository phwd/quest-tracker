package defpackage;

import com.google.android.gms.common.ConnectionResult;

/* renamed from: jB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC3167jB1 implements Runnable {
    public final /* synthetic */ ConnectionResult F;
    public final /* synthetic */ ZV G;

    public RunnableC3167jB1(ZV zv, ConnectionResult connectionResult) {
        this.G = zv;
        this.F = connectionResult;
    }

    public final void run() {
        this.G.e0(this.F);
    }
}
