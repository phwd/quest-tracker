package defpackage;

import org.chromium.chrome.browser.toolbar.top.StartSurfaceToolbarView;

/* renamed from: R01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class R01 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final T01 f8802a;
    public final Vr1 b;

    public R01(T01 t01, Vr1 vr1) {
        this.f8802a = t01;
        this.b = vr1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        T01 t01 = this.f8802a;
        Vr1 vr1 = this.b;
        ZY zy = (ZY) obj;
        StartSurfaceToolbarView startSurfaceToolbarView = t01.d;
        if (startSurfaceToolbarView != null) {
            zy.k = startSurfaceToolbarView.I;
            vr1.a(zy.a());
        }
    }
}
