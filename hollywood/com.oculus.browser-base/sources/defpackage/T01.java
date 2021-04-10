package defpackage;

import J.N;
import android.view.View;
import android.view.ViewStub;
import java.util.Map;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;
import org.chromium.chrome.browser.toolbar.top.StartSurfaceToolbarView;

/* renamed from: T01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T01 {

    /* renamed from: a  reason: collision with root package name */
    public final Y01 f8931a;
    public final ViewStub b;
    public final UH0 c;
    public StartSurfaceToolbarView d;
    public AbstractC0124Ca1 e;
    public H00 f;
    public C0435Hc1 g;
    public TabSwitcherButtonView h;
    public C5880z61 i;
    public AbstractC1117Sg1 j;
    public View.OnClickListener k;
    public View.OnLongClickListener l;
    public C5976zi0 m;
    public C1128Sl n;

    public T01(ViewStub viewStub, Vr1 vr1, AbstractC1509Ys0 ys0, AbstractC0956Pq0 pq0, AbstractC1117Sg1 sg1, C5976zi0 zi0, Q31 q31) {
        C1128Sl sl = new C1128Sl();
        this.n = sl;
        this.b = viewStub;
        ys0.g(sl.b(new Q01(this)));
        Map c2 = UH0.c(Z01.t);
        TH0 th0 = Z01.q;
        C1426Xi xi = AbstractC2793h01.c;
        Boolean valueOf = Boolean.valueOf(!xi.c() && !AbstractC2793h01.d.c());
        LH0 lh0 = new LH0(null);
        lh0.f8415a = valueOf;
        c2.put(th0, lh0);
        QH0 qh0 = Z01.h;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = false;
        c2.put(qh0, gh0);
        QH0 qh02 = Z01.l;
        GH0 gh02 = new GH0(null);
        gh02.f8081a = true;
        c2.put(qh02, gh02);
        QH0 qh03 = Z01.g;
        GH0 gh03 = new GH0(null);
        gh03.f8081a = true;
        c2.put(qh03, gh03);
        UH0 uh0 = new UH0(c2, null);
        this.c = uh0;
        this.f8931a = new Y01(uh0, new R01(this, vr1), xi.c(), AbstractC2793h01.d.c(), AbstractC2793h01.f.c(), zi0, pq0, q31);
        this.j = sg1;
        this.m = zi0;
    }

    public final void a() {
        if (this.f == null && this.e != null && N.M$3vpOHw() && !AbstractC2793h01.f.c()) {
            this.f = new H00(this.d, this.e);
        }
    }
}
