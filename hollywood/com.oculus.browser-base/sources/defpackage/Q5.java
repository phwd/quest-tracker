package defpackage;

/* renamed from: Q5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q5 implements Runnable {
    public final Z5 F;
    public final String G;

    public Q5(Z5 z5, String str) {
        this.F = z5;
        this.G = str;
    }

    public void run() {
        Z5 z5 = this.F;
        ((EA0) z5.t).z(this.G);
        z5.t = null;
    }
}
