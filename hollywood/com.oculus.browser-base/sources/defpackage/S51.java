package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: S51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S51 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ T51 f8877a;

    public S51(T51 t51) {
        this.f8877a = t51;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void B(Tab tab, String str) {
        if (str != null) {
            this.f8877a.G = str;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void G(Tab tab, LoadUrlParams loadUrlParams, int i) {
        if ((loadUrlParams.c & 33554432) == 33554432) {
            this.f8877a.G = null;
        }
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }
}
