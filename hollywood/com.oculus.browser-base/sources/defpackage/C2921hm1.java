package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: hm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2921hm1 extends AbstractC1117Sg1 {
    public final AB L;
    public final Q31 M;
    public final AbstractC1487Yi N;
    public final C4566rN0 O;
    public boolean P;

    public C2921hm1(Context context, AbstractC0956Pq0 pq0, Q31 q31, AbstractC1487Yi yi, C4566rN0 rn0) {
        super(context);
        this.L = new AB(pq0, new C2750gm1(this));
        ((C1078Rq0) pq0).l(new C2579fm1(this));
        this.M = q31;
        this.N = yi;
        this.O = rn0;
    }

    public int e(Tab tab, int i) {
        int intValue;
        boolean h = h(tab);
        if (!(h && i != 0 && AbstractC1270Uv.f(i))) {
            i = AbstractC2934hr.a(tab.getContext().getResources(), tab.a());
            if (h && (intValue = ((Integer) this.M.get()).intValue()) != 0) {
                i = intValue;
            }
        }
        return -16777216 | i;
    }

    public int f(Tab tab) {
        AbstractC5818ym0 Q = tab.Q();
        int e = e(tab, tab.m());
        if (Q != null) {
            return 0;
        }
        return e;
    }

    public float g(Tab tab) {
        float f = AbstractC1270Uv.h(e(tab, tab.m())) ? 1.0f : 0.2f;
        if (tab.Q() != null) {
            return 1.0f;
        }
        return f;
    }

    public final boolean h(Tab tab) {
        if (tab.P() && !this.N.a() && !AbstractC1270Uv.e(tab.getContext()) && !tab.isNativePage() && !tab.a()) {
            Objects.requireNonNull(this.O);
            if (!AbstractC5566xF0.a(tab)) {
                return true;
            }
        }
        return false;
    }

    public final boolean i(Tab tab, int i) {
        boolean h = h(tab);
        return !(h && i != 0 && AbstractC1270Uv.f(i)) && (!h || ((Integer) this.M.get()).intValue() == 0);
    }
}
