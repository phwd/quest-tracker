package defpackage;

/* renamed from: yx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5850yx extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0177Cx f11712a;

    public C5850yx(C0177Cx cx) {
        this.f11712a = cx;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0177Cx cx = this.f11712a;
        cx.h = (Boolean) obj;
        if (cx.f7848a == 2) {
            Runnable runnable = cx.i;
            if (runnable != null) {
                cx.f.removeCallbacks(runnable);
                cx.i = null;
            }
            cx.b();
        }
    }
}
