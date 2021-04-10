package defpackage;

/* renamed from: DS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DS0 implements Runnable {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ ES0 G;

    public DS0(ES0 es0, Runnable runnable) {
        this.G = es0;
        this.F = runnable;
    }

    public void run() {
        try {
            this.F.run();
        } finally {
            this.G.a();
        }
    }
}
