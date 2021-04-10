package defpackage;

/* renamed from: oh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4102oh1 implements Runnable {
    public final /* synthetic */ C5464wh1 F;

    public RunnableC4102oh1(C5464wh1 wh1) {
        this.F = wh1;
    }

    public void run() {
        C5464wh1 wh1 = this.F;
        wh1.c();
        while (true) {
            C0325Fg1 fg1 = (C0325Fg1) wh1.i.poll();
            if (fg1 != null) {
                if (!(fg1 instanceof C3931nh1)) {
                    wh1.i(fg1);
                }
            } else {
                return;
            }
        }
    }
}
