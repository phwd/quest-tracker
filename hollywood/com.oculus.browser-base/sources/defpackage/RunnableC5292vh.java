package defpackage;

/* renamed from: vh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5292vh implements Runnable {
    public final /* synthetic */ ComponentCallbacks2C5632xh F;

    public RunnableC5292vh(ComponentCallbacks2C5632xh xhVar) {
        this.F = xhVar;
    }

    public void run() {
        AbstractC1220Ua0.d("BindingManager", "onLowMemory: evict %d bindings", Integer.valueOf(this.F.F.size()));
        ComponentCallbacks2C5632xh.b(this.F);
    }
}
