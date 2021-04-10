package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: U61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U61 extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9006a;
    public final UH0 b;
    public final AbstractC0124Ca1 c;
    public final AbstractC0612Ka1 d;
    public final AbstractC5783ya1 e;
    public final AbstractC3226ja1 f;
    public final I61 g;
    public final AbstractC3748md1 h;
    public final C0861Oc1 i;
    public final T61 j;
    public final AbstractC0956Pq0 k;
    public final Runnable l;
    public final String m;
    public C5039u91 n;
    public AbstractC0249Eb1 o;
    public AbstractC3322k60 p;
    public int q = -1;
    public boolean r;
    public String s;
    public Callback t;

    public U61(Context context, I61 i61, UH0 uh0, AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1, AbstractC3748md1 md1, C0861Oc1 oc1, AbstractC0956Pq0 pq0, View$OnClickListenerC5098uY0 uy0, String str) {
        this.f9006a = context;
        this.b = uh0;
        this.c = ca1;
        this.f = ja1;
        this.g = i61;
        this.h = md1;
        this.i = oc1;
        this.j = new T61(this);
        this.k = pq0;
        this.m = str;
        this.e = new P61(this, uy0);
        Q61 q61 = new Q61(this);
        this.d = q61;
        ((AbstractC0246Ea1) ca1).c(q61);
        this.l = new J61(this);
        uh0.j(AbstractC5033u71.g, false);
    }

    public static void e(U61 u61) {
        AbstractC3748md1 md1;
        if (u61.b.h(AbstractC5033u71.g) && (md1 = u61.h) != null) {
            ((C1349Wc1) md1).t(((AbstractC0246Ea1) u61.c).c.d(), false, false);
        }
    }

    public static int g(Tab tab) {
        return C5383wB.q(tab).R;
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        int intValue = ((Integer) obj).intValue();
        TabModel m2 = ((AbstractC0246Ea1) this.c).m(intValue);
        if (m2 != null) {
            m2.s(intValue);
        }
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
        int intValue = ((Integer) obj).intValue();
        TabModel m2 = ((AbstractC0246Ea1) this.c).m(intValue);
        if (m2 != null) {
            m2.v(intValue);
        }
    }

    public final List f(int i2) {
        return ((AbstractC0246Ea1) this.c).c.d().N(i2);
    }

    public void h(boolean z) {
        int i2;
        if (this.b.h(AbstractC5033u71.g)) {
            if (!z) {
                this.b.m(AbstractC5033u71.i, null);
            } else {
                C0861Oc1 oc1 = this.i;
                if (!(oc1 == null || (i2 = this.q) == -1)) {
                    this.b.m(AbstractC5033u71.i, oc1.a(i2));
                }
            }
            AbstractC0249Eb1 eb1 = this.o;
            if (eb1 != null) {
                ((C0676Lb1) eb1).b();
            }
            i();
            if (AbstractC4772sd1.d()) {
                this.b.j(AbstractC5033u71.u, false);
            }
            this.g.e(null);
        }
    }

    public final void i() {
        if (f(this.q).size() < 2) {
            this.s = null;
        }
        if (this.s != null) {
            Tab o2 = ((AbstractC0246Ea1) this.c).o(this.q);
            if (this.s.length() == 0) {
                this.n.a(g(o2));
                int size = f(this.q).size();
                String quantityString = this.f9006a.getResources().getQuantityString(R.plurals.f42660_resource_name_obfuscated_RES_2131820558, size, Integer.valueOf(size));
                if (AbstractC4772sd1.d()) {
                    this.b.m(AbstractC5033u71.w, this.f9006a.getResources().getQuantityString(R.plurals.f42520_resource_name_obfuscated_RES_2131820544, size, Integer.valueOf(size)));
                }
                this.b.m(AbstractC5033u71.c, quantityString);
                this.n.c(o2, quantityString);
                return;
            }
            this.n.b(g(o2), this.s);
            this.n.c(o2, this.s);
            if (AbstractC4772sd1.d()) {
                int size2 = f(this.q).size();
                this.b.m(AbstractC5033u71.w, this.f9006a.getResources().getQuantityString(R.plurals.f42530_resource_name_obfuscated_RES_2131820545, size2, this.s, Integer.valueOf(size2)));
            }
            this.b.m(AbstractC5033u71.c, this.s);
            AbstractC3535lK0.a("TabGridDialog.TabGroupNamedInDialog");
            this.s = null;
        }
    }

    public final void j() {
        List f2 = f(this.q);
        int size = f2.size();
        if (size == 0) {
            h(true);
            return;
        }
        if (this.n != null) {
            Tab o2 = ((AbstractC0246Ea1) this.c).o(this.q);
            C5039u91 u91 = this.n;
            int g2 = g(o2);
            Objects.requireNonNull(u91);
            String c2 = AbstractC3842n81.c(g2);
            if (c2 != null && f2.size() > 1) {
                if (AbstractC4772sd1.d()) {
                    this.b.m(AbstractC5033u71.w, this.f9006a.getResources().getQuantityString(R.plurals.f42530_resource_name_obfuscated_RES_2131820545, f2.size(), c2, Integer.valueOf(f2.size())));
                }
                this.b.m(AbstractC5033u71.c, c2);
                return;
            }
        }
        if (AbstractC4772sd1.d()) {
            this.b.m(AbstractC5033u71.w, this.f9006a.getResources().getQuantityString(R.plurals.f42520_resource_name_obfuscated_RES_2131820544, f2.size(), Integer.valueOf(f2.size())));
        }
        this.b.m(AbstractC5033u71.c, this.f9006a.getResources().getQuantityString(R.plurals.f42660_resource_name_obfuscated_RES_2131820558, size, Integer.valueOf(size)));
    }
}
