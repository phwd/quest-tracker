package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Uh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1241Uh0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1302Vh0 f9043a;

    public C1241Uh0(C1302Vh0 vh0) {
        this.f9043a = vh0;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.f9043a.a();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        C0936Ph0 ph0 = this.f9043a.b;
        if (ph0 != null) {
            ph0.c();
            ph0.g();
            AbstractC6022zx1 zx1 = ph0.c;
            if (zx1 != null) {
                zx1.destroy();
            }
            ph0.c = null;
            X60 x60 = ph0.t;
            if (x60 != null) {
                x60.a();
            }
            ph0.t = null;
        }
        this.f9043a.f9100a.I(this);
        this.f9043a.f9100a = null;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void x(Tab tab, Bitmap bitmap) {
        this.f9043a.b.l(bitmap);
    }
}
