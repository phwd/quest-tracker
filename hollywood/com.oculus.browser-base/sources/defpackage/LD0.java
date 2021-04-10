package defpackage;

/* renamed from: LD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LD0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2151dE0 f8413a;

    public LD0(C2151dE0 de0) {
        this.f8413a = de0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2151dE0 de0 = this.f8413a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Runnable runnable = de0.f9761a;
        if (runnable != null) {
            runnable.run();
        }
        if (booleanValue && !AbstractC4542rE0.a("PaintPreview.Player.Zoomed")) {
            AbstractC3535lK0.a("PaintPreview.Player.Zoomed");
            AbstractC4542rE0.f11192a.put("PaintPreview.Player.Zoomed", Long.valueOf(System.currentTimeMillis()));
        }
    }
}
