package defpackage;

/* renamed from: MD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MD0 implements Runnable {
    public final C2151dE0 F;

    public MD0(C2151dE0 de0) {
        this.F = de0;
    }

    public void run() {
        Runnable runnable = this.F.f9761a;
        if (runnable != null) {
            runnable.run();
        }
        if (!AbstractC4542rE0.a("PaintPreview.Player.Scrolled")) {
            AbstractC3535lK0.a("PaintPreview.Player.Scrolled");
            AbstractC4542rE0.f11192a.put("PaintPreview.Player.Scrolled", Long.valueOf(System.currentTimeMillis()));
        }
    }
}
