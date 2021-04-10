package defpackage;

import android.util.Pair;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: t91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4869t91 extends RK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I91 f11326a;

    public C4869t91(I91 i91) {
        this.f11326a = i91;
    }

    @Override // defpackage.RK
    public void a(List list, List list2, boolean z) {
    }

    @Override // defpackage.RK
    public void b(Tab tab, int i) {
        I91 i91 = this.f11326a;
        if (i91.s) {
            K91 k91 = i91.g;
            TabModel i2 = ((AbstractC0246Ea1) i91.i).i();
            List g = this.f11326a.g(tab.getId());
            Objects.requireNonNull(k91);
            int i3 = -1;
            int i4 = -1;
            for (int i5 = i2.i((Tab) g.get(g.size() - 1)); i5 >= 0; i5--) {
                Tab tabAt = i2.getTabAt(i5);
                if (!g.contains(tabAt)) {
                    break;
                }
                int x = k91.x(tabAt.getId());
                if (x != -1 && i4 == -1) {
                    i4 = x;
                } else if (x != -1 && i3 == -1) {
                    i3 = x;
                }
            }
            Pair pair = new Pair(Integer.valueOf(i3), Integer.valueOf(i4));
            int intValue = ((Integer) pair.second).intValue();
            int intValue2 = ((Integer) pair.first).intValue();
            if (this.f11326a.i(intValue) && this.f11326a.i(intValue2)) {
                this.f11326a.g.s(intValue);
                if (this.f11326a.g(tab.getId()).size() == 2) {
                    AbstractC3535lK0.a("TabGroup.Created.DropToMerge");
                } else {
                    AbstractC3535lK0.a("TabGrid.Drag.DropToMerge");
                }
                if (intValue <= intValue2) {
                    intValue2 = this.f11326a.g.w(intValue2);
                }
                Tab tabAt2 = ((AbstractC0246Ea1) this.f11326a.i).c.d().getTabAt(this.f11326a.g.v(intValue2));
                this.f11326a.o(intValue2, C4384qI0.a(tabAt2), ((AbstractC0246Ea1) this.f11326a.i).j() == tabAt2, true, false);
            }
        }
    }

    @Override // defpackage.RK
    public void c(Tab tab, int i, int i2) {
        I91 i91 = this.f11326a;
        if (i91.s && i2 != i) {
            I71 i71 = (I71) ((AbstractC0246Ea1) i91.i).c.d();
            List g = this.f11326a.g(tab.getId());
            Tab a2 = AbstractC3842n81.a(this.f11326a.i, tab);
            TabModel i3 = ((AbstractC0246Ea1) this.f11326a.i).i();
            int x = this.f11326a.g.x(a2.getId());
            int i4 = -1;
            if (x == -1) {
                this.f11326a.g.D(a2, this.f11326a.g.y(i71.i(i3.getTabAt(i))));
                x = this.f11326a.g.x(a2.getId());
            }
            if (this.f11326a.i(x)) {
                Tab tabAt = i3.getTabAt(i2 > i ? i2 - g.size() : i2 + 1);
                Tab a3 = AbstractC3842n81.a(this.f11326a.i, tabAt);
                int x2 = this.f11326a.g.x(a3.getId());
                if (x2 == -1) {
                    K91 k91 = this.f11326a.g;
                    int i5 = i71.i(tabAt);
                    if (i2 > i) {
                        i4 = 1;
                    }
                    this.f11326a.g.D(a3, k91.y(i5 + i4));
                    x2 = this.f11326a.g.x(a3.getId());
                }
                if (this.f11326a.i(x2)) {
                    this.f11326a.g.r(x, x2);
                }
            }
        }
    }

    @Override // defpackage.RK
    public void d(Tab tab, int i) {
        int i2;
        I71 i71 = (I71) ((AbstractC0246Ea1) this.f11326a.i).c.d();
        boolean z = i71.getTabAt(i).getId() == tab.getId();
        I91 i91 = this.f11326a;
        if (!i91.s) {
            int x = i91.g.x(tab.getId());
            if (this.f11326a.i(x)) {
                this.f11326a.g.s(x);
                T61 t61 = this.f11326a.n;
                if (t61 != null) {
                    if (z) {
                        i2 = -1;
                    } else {
                        i2 = i71.getTabAt(i).getId();
                    }
                    U61 u61 = t61.f8940a;
                    u61.q = i2;
                    u61.j();
                }
            }
        } else if (!z) {
            this.f11326a.b(C4384qI0.a(tab), this.f11326a.g.y(AbstractC1160Ta1.e(((AbstractC0246Ea1) this.f11326a.i).c.d(), tab.getId())), ((AbstractC0246Ea1) i91.i).j().getId() == tab.getId());
            boolean z2 = ((AbstractC0246Ea1) this.f11326a.i).k() == i71.getTabAt(i).getId();
            I91 i912 = this.f11326a;
            i912.o(i912.g.y(i), C4384qI0.a(i71.getTabAt(i)), z2, true, false);
        }
    }

    @Override // defpackage.RK
    public void e(Tab tab, int i, int i2) {
        if (i2 != i) {
            int x = this.f11326a.g.x(tab.getId());
            TabModel i3 = ((AbstractC0246Ea1) this.f11326a.i).i();
            if (this.f11326a.i(x)) {
                int x2 = this.f11326a.g.x(i3.getTabAt(i2 > i ? i2 - 1 : i2 + 1).getId());
                if (this.f11326a.i(x2)) {
                    this.f11326a.g.r(x, x2);
                }
            }
        }
    }
}
