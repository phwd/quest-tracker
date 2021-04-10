package defpackage;

/* renamed from: JQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JQ implements Runnable {
    public final KQ F;

    public JQ(KQ kq) {
        this.F = kq;
    }

    public void run() {
        NQ nq = this.F.F;
        Runnable runnable = nq.b;
        if (runnable != null) {
            runnable.run();
            nq.b = null;
        }
    }
}
