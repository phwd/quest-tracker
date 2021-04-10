package defpackage;

/* renamed from: vX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC5266vX0 implements Runnable {
    public int F;
    public final Runnable G;

    public RunnableC5266vX0(int i, Runnable runnable) {
        this.F = i;
        this.G = runnable;
    }

    public void run() {
        int i = this.F;
        if (i != 0) {
            int i2 = i - 1;
            this.F = i2;
            if (i2 == 0) {
                this.G.run();
            }
        }
    }
}
