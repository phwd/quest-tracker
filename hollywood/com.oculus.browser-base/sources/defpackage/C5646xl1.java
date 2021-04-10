package defpackage;

import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: xl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5646xl1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1377Wn0 f11631a;

    public C5646xl1(AbstractC1377Wn0 wn0) {
        this.f11631a = wn0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC1377Wn0 wn0 = this.f11631a;
        int i = ToolbarTablet.U;
        float max = Math.max(1.0f - (((Float) obj).floatValue() * 2.5f), 0.0f);
        wn0.j(max);
        wn0.a(max);
    }
}
