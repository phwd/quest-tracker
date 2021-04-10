package defpackage;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: I71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I71 extends AbstractC3568la1 {
    public static SharedPreferences f;
    public C1322Vq0 g = new C1322Vq0();
    public Map h = new HashMap();
    public Map i = new HashMap();
    public int j = -1;
    public int k;
    public Tab l;
    public boolean m = true;
    public boolean n;

    public I71(TabModel tabModel) {
        super(tabModel);
    }

    public static int V(Tab tab) {
        return C5383wB.q(tab).R;
    }

    public static void e0(Tab tab, int i2) {
        C5383wB.q(tab).u(i2);
    }

    @Override // defpackage.AbstractC5783ya1, defpackage.AbstractC3568la1
    public void B(Tab tab, int i2, int i3) {
        boolean z;
        int i4;
        boolean z2;
        boolean z3 = true;
        if (this.d || this.e) {
            if (!this.i.containsKey(Integer.valueOf(C5383wB.q(tab).R))) {
                z = false;
            } else {
                z = !((H71) this.i.get(Integer.valueOf(C5383wB.q(tab).R))).b(tab.getId());
            }
            boolean z4 = !this.i.containsKey(Integer.valueOf(C5383wB.q(tab).R));
            if (z || z4) {
                Iterator it = this.i.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i4 = -1;
                        break;
                    }
                    Integer num = (Integer) it.next();
                    if (((H71) this.i.get(num)).b(tab.getId())) {
                        i4 = num.intValue();
                        break;
                    }
                }
            } else {
                i4 = C5383wB.q(tab).R;
            }
            H71 h71 = (H71) this.i.get(Integer.valueOf(i4));
            if (!z4) {
                if (!z) {
                    Q();
                    int min = Math.min(i3, i2);
                    int max = Math.max(i3, i2);
                    while (true) {
                        if (min > max) {
                            z2 = true;
                            break;
                        } else if (V(this.b.getTabAt(min)) != C5383wB.q(tab).R) {
                            z2 = false;
                            break;
                        } else {
                            min++;
                        }
                    }
                    if (!z2) {
                        int d = (i2 - ((H71) this.i.get(Integer.valueOf(C5383wB.q(tab).R))).d()) + 1;
                        if (d >= 0) {
                            int i5 = i2;
                            while (true) {
                                if (i5 < d) {
                                    break;
                                } else if (V(this.b.getTabAt(i5)) != C5383wB.q(tab).R) {
                                    break;
                                } else {
                                    i5--;
                                }
                            }
                        }
                        z3 = false;
                        if (z3) {
                            Iterator it2 = this.g.iterator();
                            while (true) {
                                C1261Uq0 uq0 = (C1261Uq0) it2;
                                if (!uq0.hasNext()) {
                                    break;
                                }
                                ((RK) uq0.next()).c(tab, i3, i2);
                            }
                        } else {
                            return;
                        }
                    } else {
                        Iterator it3 = this.g.iterator();
                        while (true) {
                            C1261Uq0 uq02 = (C1261Uq0) it3;
                            if (!uq02.hasNext()) {
                                break;
                            }
                            ((RK) uq02.next()).e(tab, i3, i2);
                        }
                    }
                } else {
                    c0();
                    if (h71 == null || h71.d() == 1) {
                        H71 h712 = (H71) this.i.get(Integer.valueOf(C5383wB.q(tab).R));
                        Iterator it4 = this.g.iterator();
                        while (true) {
                            C1261Uq0 uq03 = (C1261Uq0) it4;
                            if (!uq03.hasNext()) {
                                break;
                            }
                            ((RK) uq03.next()).b(tab, h712.b);
                        }
                    } else {
                        return;
                    }
                }
            } else {
                c0();
                int intValue = ((Integer) this.h.get(Integer.valueOf(i4))).intValue();
                Iterator it5 = this.g.iterator();
                while (true) {
                    C1261Uq0 uq04 = (C1261Uq0) it5;
                    if (!uq04.hasNext()) {
                        break;
                    }
                    ((RK) uq04.next()).d(tab, intValue);
                }
            }
            super.B(tab, i2, i3);
        }
    }

    @Override // defpackage.AbstractC3568la1
    public void L(Tab tab) {
        Tab d;
        if (tab.a() == a()) {
            if ((this.d || this.e) && !this.n && (d = AbstractC1160Ta1.d(this.b, C5383wB.q(tab).Q)) != null) {
                C5383wB.q(tab).u(C5383wB.q(d).R);
            }
            int i2 = C5383wB.q(tab).R;
            if (this.i.containsKey(Integer.valueOf(i2))) {
                if (((H71) this.i.get(Integer.valueOf(i2))).d() == 1) {
                    this.k++;
                    if (this.m && tab.F() == 5) {
                        AbstractC3535lK0.a("TabGroup.Created.OpenInNewTab");
                    }
                }
                ((H71) this.i.get(Integer.valueOf(i2))).a(tab.getId());
            } else {
                H71 h71 = new H71(this, C5383wB.q(tab).R);
                h71.a(tab.getId());
                this.i.put(Integer.valueOf(i2), h71);
                this.h.put(Integer.valueOf(i2), Integer.valueOf(this.h.size()));
            }
            Tab tab2 = this.l;
            if (tab2 != null) {
                this.l = null;
                R(tab2);
                return;
            }
            return;
        }
        throw new IllegalStateException("Attempting to open tab in the wrong model");
    }

    @Override // defpackage.AbstractC3568la1
    public void M(Tab tab) {
        int i2;
        int i3 = C5383wB.q(tab).R;
        if (tab.a() != a() || this.i.get(Integer.valueOf(i3)) == null || !((H71) this.i.get(Integer.valueOf(i3))).b(tab.getId())) {
            throw new IllegalStateException("Attempting to close tab in the wrong model");
        }
        H71 h71 = (H71) this.i.get(Integer.valueOf(i3));
        int id = tab.getId();
        if (h71.b == id) {
            if (h71.f8138a.size() == 1 || !h71.f8138a.contains(Integer.valueOf(id))) {
                i2 = -1;
            } else {
                List c = h71.c();
                int indexOf = c.indexOf(Integer.valueOf(id));
                i2 = indexOf == 0 ? ((Integer) c.get(indexOf + 1)).intValue() : ((Integer) c.get(indexOf - 1)).intValue();
            }
            if (i2 != -1) {
                h71.b = i2;
            }
        }
        h71.f8138a.remove(Integer.valueOf(id));
        if (h71.d() == 1) {
            this.k--;
        }
        if (h71.d() == 0) {
            int intValue = ((Integer) this.h.get(Integer.valueOf(i3))).intValue();
            for (Integer num : this.h.keySet()) {
                int intValue2 = ((Integer) this.h.get(num)).intValue();
                if (intValue2 > intValue) {
                    this.h.put(num, Integer.valueOf(intValue2 - 1));
                }
            }
            this.h.remove(Integer.valueOf(i3));
            this.i.remove(Integer.valueOf(i3));
            ((ExecutorC1463Ya) AbstractC2032cb.f9616a).execute(new G71(this, i3));
        }
    }

    @Override // defpackage.AbstractC3568la1
    public List N(int i2) {
        Tab d = AbstractC1160Ta1.d(this.b, i2);
        if (d == null) {
            return super.N(i2);
        }
        H71 h71 = (H71) this.i.get(Integer.valueOf(C5383wB.q(d).R));
        if (h71 == null) {
            return super.N(-1);
        }
        return T(h71.c());
    }

    @Override // defpackage.AbstractC3568la1
    public boolean P(Tab tab) {
        H71 h71 = (H71) this.i.get(Integer.valueOf(C5383wB.q(tab).R));
        if (h71 == null || h71.d() <= 1) {
            return false;
        }
        return true;
    }

    @Override // defpackage.AbstractC3568la1
    public void Q() {
        b0(-1);
        TabModel tabModel = this.b;
        if (tabModel.index() == -1) {
            this.j = -1;
        } else {
            R(tabModel.getTabAt(tabModel.index()));
        }
    }

    @Override // defpackage.AbstractC3568la1
    public void R(Tab tab) {
        int i2 = C5383wB.q(tab).R;
        if (this.i.get(Integer.valueOf(i2)) == null) {
            this.l = tab;
            return;
        }
        ((H71) this.i.get(Integer.valueOf(i2))).b = tab.getId();
        this.j = ((Integer) this.h.get(Integer.valueOf(i2))).intValue();
    }

    @Override // defpackage.AbstractC3568la1
    public boolean S() {
        return this.l == null;
    }

    public final List T(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(AbstractC1160Ta1.d(this.b, ((Integer) it.next()).intValue()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List U(int i2) {
        if (i2 == -1) {
            return super.N(i2);
        }
        H71 h71 = (H71) this.i.get(Integer.valueOf(i2));
        if (h71 == null) {
            return super.N(-1);
        }
        return T(h71.c());
    }

    public final SharedPreferences W() {
        if (f == null) {
            f = ContextUtils.getApplicationContext().getSharedPreferences("tab_group_pref", 0);
        }
        return f;
    }

    public final int X(Tab tab) {
        List c = ((H71) this.i.get(Integer.valueOf(C5383wB.q(tab).R))).c();
        return AbstractC1160Ta1.e(this.b, ((Integer) c.get(c.size() - 1)).intValue()) + 1;
    }

    public void Y(List list, Tab tab, boolean z, boolean z2) {
        int i2 = C5383wB.q(tab).R;
        int X = X(tab);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            Tab tab2 = (Tab) list.get(i3);
            boolean z3 = true;
            if (!z || i3 == list.size() - 1) {
                Iterator it = this.g.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (!uq0.hasNext()) {
                        break;
                    }
                    ((RK) uq0.next()).f(tab2, i2);
                }
            }
            int e = AbstractC1160Ta1.e(this.b, tab2.getId());
            arrayList.add(Integer.valueOf(e));
            if (tab2.getId() != tab.getId()) {
                if (e >= X) {
                    z3 = false;
                }
                C5383wB.q(tab2).u(i2);
                TabModel tabModel = this.b;
                int id = tab2.getId();
                int i4 = z3 ? X : X + 1;
                tabModel.m(id, X);
                X = i4;
            }
        }
        if (z2) {
            Iterator it2 = this.g.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (uq02.hasNext()) {
                    ((RK) uq02.next()).a(list, arrayList, z);
                } else {
                    return;
                }
            }
        }
    }

    public void Z(int i2) {
        TabModel tabModel = this.b;
        Tab d = AbstractC1160Ta1.d(tabModel, i2);
        int i3 = tabModel.i(d);
        H71 h71 = (H71) this.i.get(Integer.valueOf(V(d)));
        int i4 = tabModel.i(AbstractC1160Ta1.d(tabModel, ((Integer) h71.c().get(h71.c().size() - 1)).intValue()));
        int intValue = ((Integer) this.h.get(Integer.valueOf(V(d)))).intValue();
        if (h71.d() == 1) {
            Iterator it = this.g.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((RK) uq0.next()).d(d, intValue);
                } else {
                    return;
                }
            }
        } else {
            int V = V(d);
            if (d.getId() == C5383wB.q(d).R) {
                if (i3 != 0) {
                    int i5 = i3 - 1;
                    if (V(tabModel.getTabAt(i5)) == C5383wB.q(d).R) {
                        V = tabModel.getTabAt(i5).getId();
                    }
                }
                if (i3 != tabModel.getCount() - 1) {
                    int i6 = i3 + 1;
                    if (V(tabModel.getTabAt(i6)) == C5383wB.q(d).R) {
                        V = tabModel.getTabAt(i6).getId();
                    }
                }
            }
            Iterator it2 = this.g.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (!uq02.hasNext()) {
                    break;
                }
                ((RK) uq02.next()).g(d, V);
            }
            if (d.getId() == C5383wB.q(d).R) {
                for (Integer num : h71.c()) {
                    e0(AbstractC1160Ta1.d(tabModel, num.intValue()), V);
                }
                c0();
            }
            C5383wB.q(d).u(d.getId());
            if (i3 == i4) {
                c0();
                Iterator it3 = this.g.iterator();
                while (true) {
                    C1261Uq0 uq03 = (C1261Uq0) it3;
                    if (uq03.hasNext()) {
                        ((RK) uq03.next()).d(d, intValue);
                    } else {
                        return;
                    }
                }
            } else {
                tabModel.m(d.getId(), i4 + 1);
            }
        }
    }

    @Override // defpackage.N81
    public boolean a() {
        return this.b.a();
    }

    public void a0(Tab tab) {
        int i2 = C5383wB.q(tab).R;
        boolean z = true;
        if (this.i.get(Integer.valueOf(i2)) == null || ((H71) this.i.get(Integer.valueOf(i2))).d() <= 1) {
            z = false;
        }
        if (z) {
            ((ExecutorC1463Ya) AbstractC2032cb.f9616a).execute(new F71(this, i2));
        }
    }

    public final void b0(int i2) {
        boolean z = i2 == -1;
        if (z) {
            this.h.clear();
        }
        TabModel tabModel = this.b;
        for (int i3 = 0; i3 < tabModel.getCount(); i3++) {
            Tab tabAt = tabModel.getTabAt(i3);
            if (z) {
                i2 = V(tabAt);
                if (!this.h.containsKey(Integer.valueOf(i2))) {
                    this.h.put(Integer.valueOf(i2), Integer.valueOf(this.h.size()));
                }
            }
            H71 h71 = (H71) this.i.get(Integer.valueOf(i2));
            int id = tabAt.getId();
            if (h71.f8138a.contains(Integer.valueOf(id))) {
                h71.f8138a.remove(Integer.valueOf(id));
                h71.f8138a.add(Integer.valueOf(id));
            }
        }
    }

    public void c0() {
        this.m = false;
        this.n = true;
        HashMap hashMap = new HashMap();
        for (Integer num : this.i.keySet()) {
            int intValue = num.intValue();
            hashMap.put(Integer.valueOf(intValue), Integer.valueOf(((H71) this.i.get(Integer.valueOf(intValue))).b));
        }
        d0();
        TabModel tabModel = this.b;
        for (int i2 = 0; i2 < tabModel.getCount(); i2++) {
            L(tabModel.getTabAt(i2));
        }
        for (Integer num2 : this.i.keySet()) {
            int intValue2 = num2.intValue();
            if (hashMap.containsKey(Integer.valueOf(intValue2))) {
                int intValue3 = ((Integer) hashMap.get(Integer.valueOf(intValue2))).intValue();
                if (((H71) this.i.get(Integer.valueOf(intValue2))).b(intValue3)) {
                    ((H71) this.i.get(Integer.valueOf(intValue2))).b = intValue3;
                }
            }
        }
        TabModel tabModel2 = this.b;
        if (tabModel2.index() == -1) {
            this.j = -1;
        } else {
            R(tabModel2.getTabAt(tabModel2.index()));
        }
        this.m = true;
        this.n = false;
    }

    public void d0() {
        this.h.clear();
        this.i.clear();
        this.k = 0;
    }

    @Override // defpackage.N81
    public int getCount() {
        return this.i.size();
    }

    @Override // defpackage.N81
    public Tab getTabAt(int i2) {
        int i3;
        if (i2 < 0 || i2 >= getCount()) {
            return null;
        }
        Iterator it = this.h.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            Integer num = (Integer) it.next();
            if (((Integer) this.h.get(num)).intValue() == i2) {
                i3 = num.intValue();
                break;
            }
        }
        if (i3 == -1) {
            return null;
        }
        return AbstractC1160Ta1.d(this.b, ((H71) this.i.get(Integer.valueOf(i3))).b);
    }

    @Override // defpackage.N81
    public int i(Tab tab) {
        if (tab == null || tab.a() != a() || this.b.i(tab) == -1) {
            return -1;
        }
        int i2 = C5383wB.q(tab).R;
        if (!this.h.containsKey(Integer.valueOf(i2))) {
            return -1;
        }
        return ((Integer) this.h.get(Integer.valueOf(i2))).intValue();
    }

    @Override // defpackage.N81
    public int index() {
        return this.j;
    }

    @Override // defpackage.N81
    public boolean t(int i2) {
        return this.b.t(i2);
    }

    @Override // defpackage.AbstractC3568la1
    public void u(Tab tab) {
        M(tab);
    }
}
