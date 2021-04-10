package defpackage;

/* renamed from: wh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5462wh implements Runnable {
    public final /* synthetic */ ComponentCallbacks2C5632xh F;

    public RunnableC5462wh(ComponentCallbacks2C5632xh xhVar) {
        this.F = xhVar;
    }

    public void run() {
        AbstractC1220Ua0.d("BindingManager", "Release moderate connections: %d", Integer.valueOf(this.F.F.size()));
        if (C2474f80.f9900a.f()) {
            AbstractC3364kK0.d("Android.ModerateBindingCount", this.F.F.size());
        }
        ComponentCallbacks2C5632xh.b(this.F);
    }
}
