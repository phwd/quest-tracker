package defpackage;

/* renamed from: UW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class UW0 implements Runnable {
    public final C3081ij0 F;

    public UW0(C3081ij0 ij0) {
        this.F = ij0;
    }

    public void run() {
        C3081ij0 ij0 = this.F;
        Runnable runnable = ij0.b;
        if (runnable != null) {
            ij0.a();
            ij0.b = runnable;
            ij0.c.postDelayed(runnable, ij0.f10158a);
        }
    }
}
