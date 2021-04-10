package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: wd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5452wd1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f11555a;
    public final /* synthetic */ C5622xd1 b;

    public C5452wd1(C5622xd1 xd1, Callback callback) {
        this.b = xd1;
        this.f11555a = callback;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.b.b();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        this.b.b();
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.b.h = tab.i().O;
            ((C1078Rq0) this.b.h).l(this.f11555a);
            return;
        }
        AbstractC0956Pq0 pq0 = this.b.h;
        ((C1078Rq0) pq0).I.c(this.f11555a);
        C5622xd1 xd1 = this.b;
        xd1.h = null;
        xd1.b();
    }
}
