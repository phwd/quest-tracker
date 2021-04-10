package defpackage;

import android.net.Network;

/* renamed from: En0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0280En0 implements Runnable {
    public final /* synthetic */ Network F;
    public final /* synthetic */ C0402Gn0 G;

    public RunnableC0280En0(C0402Gn0 gn0, Network network) {
        this.G = gn0;
        this.F = network;
    }

    public void run() {
        this.G.b.e.k(this.F.getNetworkHandle());
    }
}
