package defpackage;

/* renamed from: OA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OA0 implements Runnable {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ PA0 G;

    public OA0(PA0 pa0, Runnable runnable) {
        this.G = pa0;
        this.F = runnable;
    }

    public void run() {
        this.F.run();
        this.G.c = false;
    }
}
