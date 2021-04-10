package defpackage;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: pn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4291pn1 extends AbstractC2924hn1 {
    public ArrayList c0 = new ArrayList();
    public boolean d0 = true;
    public int e0;
    public boolean f0 = false;
    public int g0 = 0;

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 A(long j) {
        ArrayList arrayList;
        this.K = j;
        if (j >= 0 && (arrayList = this.c0) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((AbstractC2924hn1) this.c0.get(i)).A(j);
            }
        }
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public void B(AbstractC2582fn1 fn1) {
        this.a0 = fn1;
        this.g0 |= 8;
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC2924hn1) this.c0.get(i)).B(fn1);
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 C(TimeInterpolator timeInterpolator) {
        this.g0 |= 1;
        ArrayList arrayList = this.c0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((AbstractC2924hn1) this.c0.get(i)).C(timeInterpolator);
            }
        }
        this.L = timeInterpolator;
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public void D(C1899bn1 bn1) {
        if (bn1 == null) {
            this.b0 = AbstractC2924hn1.G;
        } else {
            this.b0 = bn1;
        }
        this.g0 |= 4;
        if (this.c0 != null) {
            for (int i = 0; i < this.c0.size(); i++) {
                ((AbstractC2924hn1) this.c0.get(i)).D(bn1);
            }
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void E(AbstractC3778mn1 mn1) {
        this.g0 |= 2;
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC2924hn1) this.c0.get(i)).E(mn1);
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 F(long j) {
        this.f10101J = j;
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public String H(String str) {
        String H = super.H(str);
        for (int i = 0; i < this.c0.size(); i++) {
            StringBuilder j = AbstractC2531fV.j(H, "\n");
            j.append(((AbstractC2924hn1) this.c0.get(i)).H(str + "  "));
            H = j.toString();
        }
        return H;
    }

    public C4291pn1 I(AbstractC2924hn1 hn1) {
        this.c0.add(hn1);
        hn1.Q = this;
        long j = this.K;
        if (j >= 0) {
            hn1.A(j);
        }
        if ((this.g0 & 1) != 0) {
            hn1.C(this.L);
        }
        if ((this.g0 & 2) != 0) {
            hn1.E(null);
        }
        if ((this.g0 & 4) != 0) {
            hn1.D(this.b0);
        }
        if ((this.g0 & 8) != 0) {
            hn1.B(this.a0);
        }
        return this;
    }

    public AbstractC2924hn1 J(int i) {
        if (i < 0 || i >= this.c0.size()) {
            return null;
        }
        return (AbstractC2924hn1) this.c0.get(i);
    }

    public C4291pn1 K(int i) {
        if (i == 0) {
            this.d0 = true;
        } else if (i == 1) {
            this.d0 = false;
        } else {
            throw new AndroidRuntimeException(AbstractC2531fV.w("Invalid parameter for TransitionSet ordering: ", i));
        }
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 a(AbstractC2753gn1 gn1) {
        super.a(gn1);
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 b(View view) {
        for (int i = 0; i < this.c0.size(); i++) {
            ((AbstractC2924hn1) this.c0.get(i)).b(view);
        }
        this.N.add(view);
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public void d() {
        super.d();
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC2924hn1) this.c0.get(i)).d();
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void e(C4632rn1 rn1) {
        if (t(rn1.b)) {
            Iterator it = this.c0.iterator();
            while (it.hasNext()) {
                AbstractC2924hn1 hn1 = (AbstractC2924hn1) it.next();
                if (hn1.t(rn1.b)) {
                    hn1.e(rn1);
                    rn1.c.add(hn1);
                }
            }
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void g(C4632rn1 rn1) {
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC2924hn1) this.c0.get(i)).g(rn1);
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void h(C4632rn1 rn1) {
        if (t(rn1.b)) {
            Iterator it = this.c0.iterator();
            while (it.hasNext()) {
                AbstractC2924hn1 hn1 = (AbstractC2924hn1) it.next();
                if (hn1.t(rn1.b)) {
                    hn1.h(rn1);
                    rn1.c.add(hn1);
                }
            }
        }
    }

    @Override // defpackage.AbstractC2924hn1
    /* renamed from: k */
    public AbstractC2924hn1 clone() {
        C4291pn1 pn1 = (C4291pn1) super.clone();
        pn1.c0 = new ArrayList();
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            AbstractC2924hn1 k = ((AbstractC2924hn1) this.c0.get(i)).clone();
            pn1.c0.add(k);
            k.Q = pn1;
        }
        return pn1;
    }

    @Override // defpackage.AbstractC2924hn1
    public void m(ViewGroup viewGroup, C4802sn1 sn1, C4802sn1 sn12, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.f10101J;
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            AbstractC2924hn1 hn1 = (AbstractC2924hn1) this.c0.get(i);
            if (j > 0 && (this.d0 || i == 0)) {
                long j2 = hn1.f10101J;
                if (j2 > 0) {
                    hn1.F(j2 + j);
                } else {
                    hn1.F(j);
                }
            }
            hn1.m(viewGroup, sn1, sn12, arrayList, arrayList2);
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void v(View view) {
        super.v(view);
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC2924hn1) this.c0.get(i)).v(view);
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 w(AbstractC2753gn1 gn1) {
        super.w(gn1);
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public AbstractC2924hn1 x(View view) {
        for (int i = 0; i < this.c0.size(); i++) {
            ((AbstractC2924hn1) this.c0.get(i)).x(view);
        }
        this.N.remove(view);
        return this;
    }

    @Override // defpackage.AbstractC2924hn1
    public void y(View view) {
        super.y(view);
        int size = this.c0.size();
        for (int i = 0; i < size; i++) {
            ((AbstractC2924hn1) this.c0.get(i)).y(view);
        }
    }

    @Override // defpackage.AbstractC2924hn1
    public void z() {
        if (this.c0.isEmpty()) {
            G();
            n();
            return;
        }
        C4120on1 on1 = new C4120on1(this);
        Iterator it = this.c0.iterator();
        while (it.hasNext()) {
            ((AbstractC2924hn1) it.next()).a(on1);
        }
        this.e0 = this.c0.size();
        if (!this.d0) {
            for (int i = 1; i < this.c0.size(); i++) {
                ((AbstractC2924hn1) this.c0.get(i - 1)).a(new C3949nn1(this, (AbstractC2924hn1) this.c0.get(i)));
            }
            AbstractC2924hn1 hn1 = (AbstractC2924hn1) this.c0.get(0);
            if (hn1 != null) {
                hn1.z();
                return;
            }
            return;
        }
        Iterator it2 = this.c0.iterator();
        while (it2.hasNext()) {
            ((AbstractC2924hn1) it2.next()).z();
        }
    }
}
