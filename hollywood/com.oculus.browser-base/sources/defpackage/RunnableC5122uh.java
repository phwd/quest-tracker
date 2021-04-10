package defpackage;

/* renamed from: uh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5122uh implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ ComponentCallbacks2C5632xh G;

    public RunnableC5122uh(ComponentCallbacks2C5632xh xhVar, int i) {
        this.G = xhVar;
        this.F = i;
    }

    public void run() {
        AbstractC1220Ua0.d("BindingManager", "onTrimMemory: level=%d, size=%d", Integer.valueOf(this.F), Integer.valueOf(this.G.F.size()));
        if (!this.G.F.isEmpty()) {
            int i = this.F;
            if (i <= 5) {
                ComponentCallbacks2C5632xh.a(this.G, 0.25f);
            } else if (i <= 10) {
                ComponentCallbacks2C5632xh.a(this.G, 0.5f);
            } else if (i != 20) {
                ComponentCallbacks2C5632xh.b(this.G);
            }
        }
    }
}
