package defpackage;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* renamed from: gl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2746gl0 {

    /* renamed from: a  reason: collision with root package name */
    public final SparseArray f10017a;
    public final SparseArray b = new SparseArray();
    public final Set c = new HashSet();
    public final AbstractC2575fl0 d;
    public AbstractC2575fl0 e;
    public int f;
    public boolean g;
    public final C1322Vq0 h = new C1322Vq0();
    public final Map i;

    public C2746gl0(AbstractC2575fl0 fl0, int i2) {
        SparseArray sparseArray = new SparseArray();
        this.f10017a = sparseArray;
        HashMap hashMap = new HashMap();
        this.i = hashMap;
        this.d = fl0;
        sparseArray.put(i2, fl0);
        hashMap.put(0, new C2399ej1(new RunnableC1892bl0(this)));
        hashMap.put(1, new C2399ej1(new RunnableC2063cl0(this)));
    }

    public void a() {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            d(this.b.keyAt(i2), 8);
        }
        if (f()) {
            b(this.e.G, 8);
        }
        this.h.clear();
    }

    public void b(UH0 uh0, int i2) {
        if (uh0 != null) {
            AbstractC2575fl0 fl0 = this.e;
            if (fl0 == null || uh0 != fl0.G) {
                for (int i3 = 0; i3 < this.b.size(); i3++) {
                    List list = (List) this.b.valueAt(i3);
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        if (list.get(i4) == uh0) {
                            ((AbstractC3087il0) ((UH0) list.remove(i4)).g(AbstractC3258jl0.f10235a)).f(uh0, i2);
                            Iterator it = this.h.iterator();
                            while (true) {
                                C1261Uq0 uq0 = (C1261Uq0) it;
                                if (uq0.hasNext()) {
                                    Objects.requireNonNull((AbstractC2404el0) uq0.next());
                                } else {
                                    e();
                                    return;
                                }
                            }
                        }
                    }
                }
            } else if (f() && !this.g) {
                this.g = true;
                ((AbstractC3087il0) uh0.g(AbstractC3258jl0.f10235a)).f(uh0, i2);
                Iterator it2 = this.h.iterator();
                while (true) {
                    C1261Uq0 uq02 = (C1261Uq0) it2;
                    if (uq02.hasNext()) {
                        Objects.requireNonNull((AbstractC2404el0) uq02.next());
                    } else {
                        AbstractC2575fl0.a(this.e, null, null);
                        this.e = null;
                        this.g = false;
                        e();
                        j();
                        return;
                    }
                }
            }
        }
    }

    public void c(int i2, int i3) {
        d(i2, i3);
        if (f() && i2 == this.f) {
            b(this.e.G, i3);
        }
    }

    public final void d(int i2, int i3) {
        List list = (List) this.b.get(i2);
        if (list == null || list.isEmpty()) {
            return;
        }
        while (!list.isEmpty()) {
            UH0 uh0 = (UH0) list.remove(0);
            ((AbstractC3087il0) uh0.g(AbstractC3258jl0.f10235a)).f(uh0, i3);
            Iterator it = this.h.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((AbstractC2404el0) uq0.next());
                }
            }
        }
        e();
    }

    public final void e() {
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= this.b.size()) {
                z = true;
                break;
            } else if (!((List) this.b.valueAt(i2)).isEmpty()) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            Iterator it = this.h.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC2404el0) uq0.next()).b();
                } else {
                    return;
                }
            }
        }
    }

    public boolean f() {
        return this.e != null;
    }

    public void g(int i2, int i3) {
        ((C2399ej1) this.i.get(Integer.valueOf(i2))).c(i3);
    }

    public final void h(int i2) {
        if (!((C2399ej1) this.i.get(Integer.valueOf(i2))).b()) {
            this.c.remove(Integer.valueOf(i2));
            if (!f()) {
                j();
            }
        }
    }

    public void i(UH0 uh0, int i2, boolean z) {
        int i3;
        if (!AbstractC1575Zv.e().g("enable-screenshot-ui-mode")) {
            List list = (List) this.b.get(i2);
            if (list == null) {
                SparseArray sparseArray = this.b;
                ArrayList arrayList = new ArrayList();
                sparseArray.put(i2, arrayList);
                list = arrayList;
            }
            if (this.c.contains(Integer.valueOf(i2)) || (f() && this.f <= i2)) {
                if (z) {
                    i3 = 0;
                } else {
                    i3 = list.size();
                }
                list.add(i3, uh0);
                return;
            }
            if (f()) {
                k();
            }
            this.f = i2;
            AbstractC2575fl0 fl0 = (AbstractC2575fl0) this.f10017a.get(i2, this.d);
            this.e = fl0;
            AbstractC2575fl0.a(fl0, uh0, new C2234dl0(this, uh0));
            Iterator it = this.h.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC2404el0) uq0.next()).a(uh0);
                } else {
                    return;
                }
            }
        }
    }

    public final void j() {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            int keyAt = this.b.keyAt(i2);
            if (!this.c.contains(Integer.valueOf(keyAt))) {
                List list = (List) this.b.valueAt(i2);
                if (!list.isEmpty()) {
                    i((UH0) list.remove(0), keyAt, false);
                    return;
                }
            }
        }
    }

    public final void k() {
        AbstractC2575fl0 fl0 = this.e;
        UH0 uh0 = fl0.G;
        AbstractC2575fl0.a(fl0, null, null);
        this.e = null;
        ((List) this.b.get(this.f)).add(0, uh0);
    }

    public int l(int i2) {
        this.c.add(Integer.valueOf(i2));
        if (f() && i2 == this.f) {
            k();
            j();
        }
        return ((C2399ej1) this.i.get(Integer.valueOf(i2))).a();
    }
}
