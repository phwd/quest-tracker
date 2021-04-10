package defpackage;

import J.N;
import android.view.View;
import android.view.ViewStub;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;

/* renamed from: pd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4261pd1 {

    /* renamed from: a  reason: collision with root package name */
    public final ViewStub f11077a;
    public View.OnClickListener b;
    public View.OnClickListener c;
    public C5880z61 d;
    public AbstractC0124Ca1 e;
    public D00 f;
    public C5976zi0 g;
    public boolean h;
    public TabSwitcherModeTTPhone i;
    public P00 j;

    public C4261pd1(ViewStub viewStub, C5976zi0 zi0) {
        this.f11077a = viewStub;
        this.g = zi0;
    }

    public final boolean a() {
        return AbstractC4772sd1.b() && AbstractC4226pO.a() && N.M$3vpOHw() && N.MMltG$kc("TabGridLayoutAndroid", "tab_grid_layout_android_new_tab").equals("NewTabVariation");
    }

    public final void b() {
        if (this.e != null && this.i != null && a() && this.j == null) {
            C4090od1 od1 = new C4090od1(this);
            this.j = od1;
            ((AbstractC0246Ea1) this.e).g.b(od1);
        }
    }

    public final void c() {
        if (this.e != null && this.i != null && a()) {
            boolean z = true;
            if (((AbstractC0246Ea1) this.e).l(true).getCount() == 0) {
                z = false;
            }
            this.i.e(z);
        }
    }
}
