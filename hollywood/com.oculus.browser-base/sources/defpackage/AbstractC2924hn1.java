package defpackage;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: hn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2924hn1 implements Cloneable {
    public static final int[] F = {2, 1, 3, 4};
    public static final C1899bn1 G = new C1899bn1();
    public static ThreadLocal H = new ThreadLocal();
    public String I = getClass().getName();

    /* renamed from: J  reason: collision with root package name */
    public long f10101J = -1;
    public long K = -1;
    public TimeInterpolator L = null;
    public ArrayList M = new ArrayList();
    public ArrayList N = new ArrayList();
    public C4802sn1 O = new C4802sn1();
    public C4802sn1 P = new C4802sn1();
    public C4291pn1 Q = null;
    public int[] R = F;
    public ArrayList S;
    public ArrayList T;
    public ArrayList U = new ArrayList();
    public int V = 0;
    public boolean W = false;
    public boolean X = false;
    public ArrayList Y = null;
    public ArrayList Z = new ArrayList();
    public AbstractC2582fn1 a0;
    public C1899bn1 b0 = G;

    public static void c(C4802sn1 sn1, View view, C4632rn1 rn1) {
        sn1.f11301a.put(view, rn1);
        int id = view.getId();
        if (id >= 0) {
            if (sn1.b.indexOfKey(id) >= 0) {
                sn1.b.put(id, null);
            } else {
                sn1.b.put(id, view);
            }
        }
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        String transitionName = view.getTransitionName();
        if (transitionName != null) {
            if (sn1.d.e(transitionName) >= 0) {
                sn1.d.put(transitionName, null);
            } else {
                sn1.d.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                C4083ob0 ob0 = sn1.c;
                if (ob0.G) {
                    ob0.d();
                }
                if (AbstractC0179Cy.b(ob0.H, ob0.f10560J, itemIdAtPosition) >= 0) {
                    View view2 = (View) sn1.c.e(itemIdAtPosition);
                    if (view2 != null) {
                        view2.setHasTransientState(false);
                        sn1.c.i(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                view.setHasTransientState(true);
                sn1.c.i(itemIdAtPosition, view);
            }
        }
    }

    public static C4931ta p() {
        C4931ta taVar = (C4931ta) H.get();
        if (taVar != null) {
            return taVar;
        }
        C4931ta taVar2 = new C4931ta();
        H.set(taVar2);
        return taVar2;
    }

    public static boolean u(C4632rn1 rn1, C4632rn1 rn12, String str) {
        Object obj = rn1.f11223a.get(str);
        Object obj2 = rn12.f11223a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public AbstractC2924hn1 A(long j) {
        this.K = j;
        return this;
    }

    public void B(AbstractC2582fn1 fn1) {
        this.a0 = fn1;
    }

    public AbstractC2924hn1 C(TimeInterpolator timeInterpolator) {
        this.L = timeInterpolator;
        return this;
    }

    public void D(C1899bn1 bn1) {
        if (bn1 == null) {
            this.b0 = G;
        } else {
            this.b0 = bn1;
        }
    }

    public void E(AbstractC3778mn1 mn1) {
    }

    public AbstractC2924hn1 F(long j) {
        this.f10101J = j;
        return this;
    }

    public void G() {
        if (this.V == 0) {
            ArrayList arrayList = this.Y;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.Y.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((AbstractC2753gn1) arrayList2.get(i)).a(this);
                }
            }
            this.X = false;
        }
        this.V++;
    }

    public String H(String str) {
        StringBuilder i = AbstractC2531fV.i(str);
        i.append(getClass().getSimpleName());
        i.append("@");
        i.append(Integer.toHexString(hashCode()));
        i.append(": ");
        String sb = i.toString();
        if (this.K != -1) {
            StringBuilder j = AbstractC2531fV.j(sb, "dur(");
            j.append(this.K);
            j.append(") ");
            sb = j.toString();
        }
        if (this.f10101J != -1) {
            StringBuilder j2 = AbstractC2531fV.j(sb, "dly(");
            j2.append(this.f10101J);
            j2.append(") ");
            sb = j2.toString();
        }
        if (this.L != null) {
            StringBuilder j3 = AbstractC2531fV.j(sb, "interp(");
            j3.append(this.L);
            j3.append(") ");
            sb = j3.toString();
        }
        if (this.M.size() <= 0 && this.N.size() <= 0) {
            return sb;
        }
        String f = AbstractC2531fV.f(sb, "tgts(");
        if (this.M.size() > 0) {
            for (int i2 = 0; i2 < this.M.size(); i2++) {
                if (i2 > 0) {
                    f = AbstractC2531fV.f(f, ", ");
                }
                StringBuilder i3 = AbstractC2531fV.i(f);
                i3.append(this.M.get(i2));
                f = i3.toString();
            }
        }
        if (this.N.size() > 0) {
            for (int i4 = 0; i4 < this.N.size(); i4++) {
                if (i4 > 0) {
                    f = AbstractC2531fV.f(f, ", ");
                }
                StringBuilder i5 = AbstractC2531fV.i(f);
                i5.append(this.N.get(i4));
                f = i5.toString();
            }
        }
        return AbstractC2531fV.f(f, ")");
    }

    public AbstractC2924hn1 a(AbstractC2753gn1 gn1) {
        if (this.Y == null) {
            this.Y = new ArrayList();
        }
        this.Y.add(gn1);
        return this;
    }

    public AbstractC2924hn1 b(View view) {
        this.N.add(view);
        return this;
    }

    public void d() {
        for (int size = this.U.size() - 1; size >= 0; size--) {
            ((Animator) this.U.get(size)).cancel();
        }
        ArrayList arrayList = this.Y;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.Y.clone();
            int size2 = arrayList2.size();
            for (int i = 0; i < size2; i++) {
                ((AbstractC2753gn1) arrayList2.get(i)).e(this);
            }
        }
    }

    public abstract void e(C4632rn1 rn1);

    public final void f(View view, boolean z) {
        if (view != null) {
            view.getId();
            if (view.getParent() instanceof ViewGroup) {
                C4632rn1 rn1 = new C4632rn1(view);
                if (z) {
                    h(rn1);
                } else {
                    e(rn1);
                }
                rn1.c.add(this);
                g(rn1);
                if (z) {
                    c(this.O, view, rn1);
                } else {
                    c(this.P, view, rn1);
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    f(viewGroup.getChildAt(i), z);
                }
            }
        }
    }

    public void g(C4632rn1 rn1) {
    }

    public abstract void h(C4632rn1 rn1);

    public void i(ViewGroup viewGroup, boolean z) {
        j(z);
        if (this.M.size() > 0 || this.N.size() > 0) {
            for (int i = 0; i < this.M.size(); i++) {
                View findViewById = viewGroup.findViewById(((Integer) this.M.get(i)).intValue());
                if (findViewById != null) {
                    C4632rn1 rn1 = new C4632rn1(findViewById);
                    if (z) {
                        h(rn1);
                    } else {
                        e(rn1);
                    }
                    rn1.c.add(this);
                    g(rn1);
                    if (z) {
                        c(this.O, findViewById, rn1);
                    } else {
                        c(this.P, findViewById, rn1);
                    }
                }
            }
            for (int i2 = 0; i2 < this.N.size(); i2++) {
                View view = (View) this.N.get(i2);
                C4632rn1 rn12 = new C4632rn1(view);
                if (z) {
                    h(rn12);
                } else {
                    e(rn12);
                }
                rn12.c.add(this);
                g(rn12);
                if (z) {
                    c(this.O, view, rn12);
                } else {
                    c(this.P, view, rn12);
                }
            }
            return;
        }
        f(viewGroup, z);
    }

    public void j(boolean z) {
        if (z) {
            this.O.f11301a.clear();
            this.O.b.clear();
            this.O.c.a();
            return;
        }
        this.P.f11301a.clear();
        this.P.b.clear();
        this.P.c.a();
    }

    /* renamed from: k */
    public AbstractC2924hn1 clone() {
        try {
            AbstractC2924hn1 hn1 = (AbstractC2924hn1) super.clone();
            hn1.Z = new ArrayList();
            hn1.O = new C4802sn1();
            hn1.P = new C4802sn1();
            hn1.S = null;
            hn1.T = null;
            return hn1;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator l(ViewGroup viewGroup, C4632rn1 rn1, C4632rn1 rn12) {
        return null;
    }

    public void m(ViewGroup viewGroup, C4802sn1 sn1, C4802sn1 sn12, ArrayList arrayList, ArrayList arrayList2) {
        int i;
        Animator l;
        Animator animator;
        C4632rn1 rn1;
        View view;
        C4632rn1 rn12;
        Animator animator2;
        C4931ta p = p();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            C4632rn1 rn13 = (C4632rn1) arrayList.get(i2);
            C4632rn1 rn14 = (C4632rn1) arrayList2.get(i2);
            if (rn13 != null && !rn13.c.contains(this)) {
                rn13 = null;
            }
            if (rn14 != null && !rn14.c.contains(this)) {
                rn14 = null;
            }
            if (!(rn13 == null && rn14 == null)) {
                if ((rn13 == null || rn14 == null || s(rn13, rn14)) && (l = l(viewGroup, rn13, rn14)) != null) {
                    if (rn14 != null) {
                        View view2 = rn14.b;
                        String[] q = q();
                        if (q != null && q.length > 0) {
                            rn12 = new C4632rn1(view2);
                            C4632rn1 rn15 = (C4632rn1) sn12.f11301a.get(view2);
                            if (rn15 != null) {
                                int i3 = 0;
                                while (i3 < q.length) {
                                    rn12.f11223a.put(q[i3], rn15.f11223a.get(q[i3]));
                                    i3++;
                                    l = l;
                                    size = size;
                                    rn15 = rn15;
                                }
                            }
                            i = size;
                            int i4 = p.L;
                            int i5 = 0;
                            while (true) {
                                if (i5 >= i4) {
                                    animator2 = l;
                                    break;
                                }
                                C2411en1 en1 = (C2411en1) p.get((Animator) p.h(i5));
                                if (en1.c != null && en1.f9879a == view2 && en1.b.equals(this.I) && en1.c.equals(rn12)) {
                                    animator2 = null;
                                    break;
                                }
                                i5++;
                            }
                        } else {
                            i = size;
                            animator2 = l;
                            rn12 = null;
                        }
                        view = view2;
                        animator = animator2;
                        rn1 = rn12;
                    } else {
                        i = size;
                        view = rn13.b;
                        animator = l;
                        rn1 = null;
                    }
                    if (animator != null) {
                        String str = this.I;
                        AbstractC5846yv1 yv1 = AbstractC4315pv1.f11100a;
                        p.put(animator, new C2411en1(view, str, this, new C1935bz1(viewGroup), rn1));
                        this.Z.add(animator);
                    }
                    i2++;
                    size = i;
                }
            }
            i = size;
            i2++;
            size = i;
        }
        if (sparseIntArray.size() != 0) {
            for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                Animator animator3 = (Animator) this.Z.get(sparseIntArray.keyAt(i6));
                animator3.setStartDelay(animator3.getStartDelay() + (((long) sparseIntArray.valueAt(i6)) - Long.MAX_VALUE));
            }
        }
    }

    public void n() {
        int i = this.V - 1;
        this.V = i;
        if (i == 0) {
            ArrayList arrayList = this.Y;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.Y.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((AbstractC2753gn1) arrayList2.get(i2)).c(this);
                }
            }
            for (int i3 = 0; i3 < this.O.c.k(); i3++) {
                View view = (View) this.O.c.l(i3);
                if (view != null) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    view.setHasTransientState(false);
                }
            }
            for (int i4 = 0; i4 < this.P.c.k(); i4++) {
                View view2 = (View) this.P.c.l(i4);
                if (view2 != null) {
                    AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                    view2.setHasTransientState(false);
                }
            }
            this.X = true;
        }
    }

    public C4632rn1 o(View view, boolean z) {
        C4291pn1 pn1 = this.Q;
        if (pn1 != null) {
            return pn1.o(view, z);
        }
        ArrayList arrayList = z ? this.S : this.T;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            C4632rn1 rn1 = (C4632rn1) arrayList.get(i2);
            if (rn1 == null) {
                return null;
            }
            if (rn1.b == view) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i < 0) {
            return null;
        }
        return (C4632rn1) (z ? this.T : this.S).get(i);
    }

    public String[] q() {
        return null;
    }

    public C4632rn1 r(View view, boolean z) {
        C4291pn1 pn1 = this.Q;
        if (pn1 != null) {
            return pn1.r(view, z);
        }
        return (C4632rn1) (z ? this.O : this.P).f11301a.getOrDefault(view, null);
    }

    public boolean s(C4632rn1 rn1, C4632rn1 rn12) {
        if (rn1 == null || rn12 == null) {
            return false;
        }
        String[] q = q();
        if (q != null) {
            for (String str : q) {
                if (!u(rn1, rn12, str)) {
                }
            }
            return false;
        }
        for (String str2 : rn1.f11223a.keySet()) {
            if (u(rn1, rn12, str2)) {
            }
        }
        return false;
        return true;
    }

    public boolean t(View view) {
        int id = view.getId();
        if ((this.M.size() != 0 || this.N.size() != 0) && !this.M.contains(Integer.valueOf(id)) && !this.N.contains(view)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return H("");
    }

    public void v(View view) {
        if (!this.X) {
            C4931ta p = p();
            int i = p.L;
            AbstractC5846yv1 yv1 = AbstractC4315pv1.f11100a;
            C1935bz1 bz1 = new C1935bz1(view);
            for (int i2 = i - 1; i2 >= 0; i2--) {
                C2411en1 en1 = (C2411en1) p.k(i2);
                if (en1.f9879a != null && bz1.equals(en1.d)) {
                    ((Animator) p.h(i2)).pause();
                }
            }
            ArrayList arrayList = this.Y;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.Y.clone();
                int size = arrayList2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    ((AbstractC2753gn1) arrayList2.get(i3)).d(this);
                }
            }
            this.W = true;
        }
    }

    public AbstractC2924hn1 w(AbstractC2753gn1 gn1) {
        ArrayList arrayList = this.Y;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(gn1);
        if (this.Y.size() == 0) {
            this.Y = null;
        }
        return this;
    }

    public AbstractC2924hn1 x(View view) {
        this.N.remove(view);
        return this;
    }

    public void y(View view) {
        if (this.W) {
            if (!this.X) {
                C4931ta p = p();
                int i = p.L;
                AbstractC5846yv1 yv1 = AbstractC4315pv1.f11100a;
                C1935bz1 bz1 = new C1935bz1(view);
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    C2411en1 en1 = (C2411en1) p.k(i2);
                    if (en1.f9879a != null && bz1.equals(en1.d)) {
                        ((Animator) p.h(i2)).resume();
                    }
                }
                ArrayList arrayList = this.Y;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.Y.clone();
                    int size = arrayList2.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ((AbstractC2753gn1) arrayList2.get(i3)).b(this);
                    }
                }
            }
            this.W = false;
        }
    }

    public void z() {
        G();
        C4931ta p = p();
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (p.containsKey(animator)) {
                G();
                if (animator != null) {
                    animator.addListener(new C2070cn1(this, p));
                    long j = this.K;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.f10101J;
                    if (j2 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.L;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new C2241dn1(this));
                    animator.start();
                }
            }
        }
        this.Z.clear();
        n();
    }
}
