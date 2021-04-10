package defpackage;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: t71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4863t71 extends AbstractC5363w40 {
    public final K91 d;
    public final AbstractC0124Ca1 e;
    public final D91 f;
    public final String g;
    public final T61 h;
    public float i;
    public float j;
    public float k;
    public boolean l;
    public boolean m;
    public int n;
    public int o = -1;
    public int p = -1;
    public int q = -1;
    public int r = 0;
    public RecyclerView s;
    public Profile t;

    public C4863t71(K91 k91, AbstractC0124Ca1 ca1, D91 d91, T61 t61, String str, boolean z) {
        super(0, 0);
        this.d = k91;
        this.e = ca1;
        this.f = d91;
        this.g = str;
        this.l = z;
        this.h = t61;
    }

    @Override // defpackage.AbstractC4853t40
    public boolean a(RecyclerView recyclerView, XK0 xk0, XK0 xk02) {
        int i2 = xk02.L;
        return (i2 == 3 || i2 == 5 || i2 == 6) ? false : true;
    }

    @Override // defpackage.AbstractC4853t40
    public void b(RecyclerView recyclerView, XK0 xk0) {
        super.b(recyclerView, xk0);
        int i2 = this.r;
        this.r = 0;
        if (i2 == 2 && recyclerView.T.b() == 0 && recyclerView.getChildCount() != 0) {
            recyclerView.U.I0(xk0.G);
        }
    }

    @Override // defpackage.AbstractC4853t40
    public int f(RecyclerView recyclerView, XK0 xk0) {
        int i2 = xk0.L;
        int i3 = 0;
        int i4 = (i2 == 3 || i2 == 5 || i2 == 6) ? 0 : this.n;
        if (i2 != 5) {
            i3 = 48;
        }
        this.s = recyclerView;
        return AbstractC4853t40.j(i4, i3);
    }

    @Override // defpackage.AbstractC4853t40
    public float g(XK0 xk0) {
        return this.i / ((float) this.s.getWidth());
    }

    @Override // defpackage.AbstractC4853t40
    public void k(Canvas canvas, RecyclerView recyclerView, XK0 xk0, float f2, float f3, int i2, boolean z) {
        int i3;
        super.k(canvas, recyclerView, xk0, f2, f3, i2, z);
        int i4 = 1;
        boolean z2 = true;
        if (i2 == 1) {
            float max = Math.max(0.2f, 1.0f - ((Math.abs(f2) * 0.8f) / this.i));
            IW0 iw0 = (IW0) xk0;
            UH0 uh0 = iw0.b0;
            NH0 nh0 = J91.f8274a;
            if (uh0.f(nh0) == 0) {
                i3 = this.d.x(iw0.b0.f(AbstractC5106ub1.f11420a));
            } else {
                i3 = iw0.b0.f(nh0) == 1 ? this.d.A(iw0.b0.f(AbstractC0516Ij0.f8246a)) : -1;
            }
            if (i3 != -1) {
                ((C4765sb0) this.d.get(i3)).b.k(J91.b, max);
                if (Math.abs(f2) < this.i) {
                    z2 = false;
                }
                if (z2 && !this.m) {
                    xk0.G.performHapticFeedback(0);
                }
                this.m = z2;
                return;
            }
            return;
        }
        this.r = i2;
        if (i2 != 2 || !this.l) {
            if (i2 == 2 && this.h != null) {
                Object[] objArr = ((float) xk0.G.getBottom()) + f3 > ((float) recyclerView.getBottom()) - this.k ? 1 : null;
                if (this.o != -1) {
                    this.q = objArr != null ? xk0.f() : -1;
                    T61 t61 = this.h;
                    if (objArr != null) {
                        i4 = 2;
                    } else if (this.o != -1) {
                        i4 = 0;
                    }
                    t61.f8940a.b.l(AbstractC5033u71.j, i4);
                }
            }
        } else if (AbstractC4772sd1.g()) {
            int i5 = this.p;
            View view = xk0.G;
            float f4 = this.j;
            int i6 = TabListRecyclerView.k1;
            int i7 = 0;
            while (true) {
                if (i7 >= recyclerView.T.b()) {
                    i7 = -1;
                    break;
                }
                XK0 H = recyclerView.H(i7);
                if (H != null) {
                    View view2 = H.G;
                    if (view2.getLeft() != view.getLeft() || view2.getTop() != view.getTop()) {
                        if (((Math.abs(((float) view2.getLeft()) - (((float) view.getLeft()) + f2)) >= f4 || Math.abs(((float) view2.getTop()) - (((float) view.getTop()) + f3)) >= f4) ? null : 1) != null) {
                            break;
                        }
                    }
                }
                i7++;
            }
            this.p = i7;
            XK0 H2 = this.s.H(i7);
            if (!(H2 instanceof IW0) || o(H2)) {
                this.d.B(this.p, true);
                if (i5 != this.p) {
                    this.d.B(i5, false);
                    return;
                }
                return;
            }
            this.p = -1;
        }
    }

    @Override // defpackage.AbstractC4853t40
    public boolean l(RecyclerView recyclerView, XK0 xk0, XK0 xk02) {
        int i2;
        int i3;
        int i4;
        this.o = xk02.f();
        int i5 = this.p;
        int i6 = 0;
        if (i5 != -1) {
            this.d.B(i5, false);
            this.p = -1;
        }
        UH0 uh0 = ((IW0) xk0).b0;
        SH0 sh0 = AbstractC5106ub1.f11420a;
        int f2 = uh0.f(sh0);
        int f3 = ((IW0) xk02).b0.f(sh0);
        int f4 = xk02.f() - xk0.f();
        AbstractC3568la1 d2 = ((AbstractC0246Ea1) this.e).c.d();
        TabModel i7 = ((AbstractC0246Ea1) this.e).i();
        if (d2 instanceof UK) {
            K91 k91 = this.d;
            int x = k91.x(f2);
            if (f4 > 0) {
                f4++;
            }
            i7.m(f2, k91.v(x + f4));
        } else if (!this.l) {
            int i8 = i7.i(((AbstractC0246Ea1) this.e).o(f3));
            if (f4 > 0) {
                i8++;
            }
            i7.m(f2, i8);
        } else {
            List N = ((AbstractC0246Ea1) this.e).c.d().N(f3);
            if (f4 >= 0) {
                i2 = i7.i((Tab) N.get(N.size() - 1)) + 1;
            } else {
                i2 = i7.i((Tab) N.get(0));
            }
            I71 i71 = (I71) d2;
            List<Tab> N2 = i71.N(f2);
            TabModel tabModel = i71.b;
            int c = AbstractC4089od0.c(i2, 0, tabModel.getCount());
            int e2 = AbstractC1160Ta1.e(tabModel, ((Tab) N2.get(0)).getId());
            if (!(e2 == -1 || e2 == c)) {
                for (Tab tab : N2) {
                    if (tabModel.i(tab) != -1) {
                        int id = tab.getId();
                        if (c >= e2) {
                            i3 = i6;
                            i4 = c;
                        } else {
                            i3 = i6 + 1;
                            i4 = i6 + c;
                        }
                        tabModel.m(id, i4);
                        i6 = i3;
                    }
                }
            }
        }
        StringBuilder i9 = AbstractC2531fV.i("TabGrid.Drag.Reordered.");
        i9.append(this.g);
        AbstractC3535lK0.a(i9.toString());
        return true;
    }

    @Override // defpackage.AbstractC4853t40
    public void m(XK0 xk0, int i2) {
        if (i2 == 2) {
            int f2 = xk0.f();
            this.o = f2;
            this.d.C(f2, true);
            AbstractC3535lK0.a("TabGrid.Drag.Start." + this.g);
        } else if (i2 == 0) {
            this.m = false;
            if (!AbstractC4772sd1.g()) {
                this.p = -1;
            }
            XK0 H = this.s.H(this.p);
            boolean z = !(H instanceof IW0) || o(H);
            if (this.p == -1 || !this.l) {
                this.d.C(this.o, false);
            } else {
                XK0 H2 = this.s.H(this.o);
                if (H2 != null && !this.s.T() && z) {
                    View view = H2.G;
                    int v = this.d.v(this.o);
                    int v2 = this.d.v(this.p);
                    I71 i71 = (I71) ((AbstractC0246Ea1) this.e).c.d();
                    int id = i71.getTabAt(v).getId();
                    int id2 = i71.getTabAt(v2).getId();
                    AbstractC1160Ta1.d(i71.b, id);
                    Tab d2 = AbstractC1160Ta1.d(i71.b, id2);
                    int V = I71.V(d2);
                    List N = i71.N(id);
                    if (!(AbstractC1160Ta1.e(i71.b, ((Tab) N.get(0)).getId()) != i71.X(d2))) {
                        Iterator it = i71.g.iterator();
                        while (true) {
                            C1261Uq0 uq0 = (C1261Uq0) it;
                            if (!uq0.hasNext()) {
                                break;
                            }
                            ((RK) uq0.next()).f((Tab) N.get(N.size() - 1), V);
                        }
                        for (int i3 = 0; i3 < N.size(); i3++) {
                            I71.e0((Tab) N.get(i3), V);
                        }
                        i71.c0();
                        H71 h71 = (H71) i71.i.get(Integer.valueOf(I71.V((Tab) N.get(N.size() - 1))));
                        Iterator it2 = i71.g.iterator();
                        while (true) {
                            C1261Uq0 uq02 = (C1261Uq0) it2;
                            if (!uq02.hasNext()) {
                                break;
                            }
                            ((RK) uq02.next()).b((Tab) N.get(N.size() - 1), h71.b);
                        }
                    } else {
                        i71.Y(N, d2, true, false);
                    }
                    Um1.a(this.t).notifyEvent("tab_drag_and_drop_to_group");
                    this.s.U.I0(view);
                }
            }
            int i4 = this.p;
            if (i4 != -1 && z) {
                K91 k91 = this.d;
                if (this.o <= i4) {
                    i4 = k91.w(i4);
                }
                k91.B(i4, false);
            }
            if (this.q != -1) {
                I71 i712 = (I71) ((AbstractC0246Ea1) this.e).c.d();
                XK0 H3 = this.s.H(this.q);
                if (H3 != null && !this.s.T()) {
                    View view2 = H3.G;
                    i712.Z(((C4765sb0) this.d.get(this.q)).b.f(AbstractC5106ub1.f11420a));
                    if (this.s.T.b() != 0) {
                        this.s.U.I0(view2);
                    }
                    StringBuilder i5 = AbstractC2531fV.i("TabGrid.Drag.RemoveFromGroup.");
                    i5.append(this.g);
                    AbstractC3535lK0.a(i5.toString());
                }
            }
            this.p = -1;
            this.o = -1;
            this.q = -1;
            T61 t61 = this.h;
            if (t61 != null) {
                t61.f8940a.b.l(AbstractC5033u71.j, 1);
            }
        }
    }

    @Override // defpackage.AbstractC4853t40
    public void n(XK0 xk0, int i2) {
        IW0 iw0 = (IW0) xk0;
        UH0 uh0 = iw0.b0;
        NH0 nh0 = J91.f8274a;
        if (uh0.f(nh0) == 0) {
            this.f.a(iw0.b0.f(AbstractC5106ub1.f11420a));
            AbstractC3535lK0.a("MobileStackViewSwipeCloseTab." + this.g);
        } else if (iw0.b0.f(nh0) == 1) {
            xk0.G.findViewById(R.id.close_button).performClick();
        }
    }

    public final boolean o(XK0 xk0) {
        return (xk0 instanceof IW0) && ((IW0) xk0).b0.f(J91.f8274a) == 0;
    }
}
