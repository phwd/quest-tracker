package defpackage;

import org.chromium.base.Callback;

/* renamed from: Yk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1493Yk0 implements Runnable {
    public final C1554Zk0 F;
    public final Callback G;
    public final int H;

    public RunnableC1493Yk0(C1554Zk0 zk0, Callback callback, int i) {
        this.F = zk0;
        this.G = callback;
        this.H = i;
    }

    public void run() {
        C1554Zk0 zk0 = this.F;
        this.G.onResult((byte[]) zk0.f9365a.get(C1554Zk0.e(this.H)));
    }
}
