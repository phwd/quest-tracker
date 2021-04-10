package defpackage;

/* renamed from: ek0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2401ek0 implements Runnable {
    public final C3084ik0 F;
    public final Runnable G;

    public RunnableC2401ek0(C3084ik0 ik0, Runnable runnable) {
        this.F = ik0;
        this.G = runnable;
    }

    public void run() {
        C3084ik0 ik0 = this.F;
        Runnable runnable = this.G;
        ik0.d.a();
        ik0.c = null;
        runnable.run();
    }
}
