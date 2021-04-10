package defpackage;

/* renamed from: RI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RI1 implements Runnable {
    public final FI1 F;

    public RI1(FI1 fi1) {
        this.F = fi1;
    }

    public final void run() {
        FI1 fi1 = this.F;
        synchronized (fi1) {
            if (fi1.f8008a == 1) {
                fi1.a(1, "Timed out while binding");
            }
        }
    }
}
