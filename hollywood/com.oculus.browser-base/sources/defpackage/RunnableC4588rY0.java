package defpackage;

/* renamed from: rY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4588rY0 implements Runnable {
    public final /* synthetic */ View$OnClickListenerC5098uY0 F;

    public RunnableC4588rY0(View$OnClickListenerC5098uY0 uy0) {
        this.F = uy0;
    }

    public void run() {
        C4418qY0 qy0 = this.F.I;
        if (!qy0.a().b()) {
            qy0.c(false);
            while (true) {
                C4076oY0 a2 = qy0.a();
                if (a2 == null || !a2.a()) {
                    break;
                }
                qy0.c(false);
            }
        }
        this.F.m();
    }
}
