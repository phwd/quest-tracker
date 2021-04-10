package defpackage;

import J.N;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: I91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I91 {

    /* renamed from: a  reason: collision with root package name */
    public static final Comparator f8203a = new C2820h91();
    public static Map b = new HashMap();
    public final D91 A = new C4016o91(this);
    public final AbstractC1404Xa1 B = new C4187p91(this);
    public final AbstractC5783ya1 C;
    public AbstractC2648g90 D;
    public AbstractC0383Gf1 E;
    public C5039u91 F;
    public RK G;
    public View.AccessibilityDelegate H;
    public boolean c;
    public boolean d;
    public final C3674m91 e = new C3674m91(this);
    public final Context f;
    public final K91 g;
    public final int h;
    public final AbstractC0124Ca1 i;
    public final D91 j;
    public final C0618Kc1 k;
    public final C5956zb1 l;
    public final AbstractC5209v91 m;
    public final T61 n;
    public final String o;
    public final C2307e91 p;
    public final AbstractC3577ld1 q;
    public H91 r;
    public boolean s;
    public ComponentCallbacks t;
    public C4863t71 u;
    public int v = -1;
    public int w;
    public int x;
    public GridLayoutManager y;
    public final D91 z = new C3845n91(this);

    public I91(Context context, K91 k91, int i2, AbstractC0124Ca1 ca1, H91 h91, C0618Kc1 kc1, C2307e91 e91, boolean z2, C5956zb1 zb1, AbstractC5209v91 v91, T61 t61, AbstractC3577ld1 ld1, String str, int i3) {
        this.f = context;
        this.i = ca1;
        this.r = h91;
        this.g = k91;
        this.h = i2;
        this.p = e91;
        this.o = str;
        this.k = kc1;
        this.l = zb1;
        this.m = v91;
        this.n = t61;
        this.s = z2;
        this.w = i3;
        this.q = ld1;
        this.C = new C4358q91(this);
        C4528r91 r91 = new C4528r91(this);
        this.j = r91;
        this.u = new C4863t71(k91, ca1, r91, t61, str, z2);
        if (i2 == 0 && i3 != 0 && AbstractC4772sd1.e()) {
            C4699s91 s91 = new C4699s91(this);
            this.D = s91;
            k91.F.b(s91);
        }
    }

    public static void a(I91 i91, Tab tab, boolean z2) {
        int i2;
        boolean z3 = false;
        if (!z2) {
            i2 = i91.g.y(AbstractC1160Ta1.e(((AbstractC0246Ea1) i91.i).c.d(), tab.getId()));
        } else if (i91.g.size() == 0 || (i2 = i91.g(((C4765sb0) i91.g.get(0)).b.f(AbstractC5106ub1.f11420a)).indexOf(tab)) == -1) {
            i2 = -1;
        }
        if (i2 != -1) {
            C4384qI0 a2 = C4384qI0.a(tab);
            if (((AbstractC0246Ea1) i91.i).j() == tab) {
                z3 = true;
            }
            i91.b(a2, i2, z3);
        }
    }

    public static String c(Tab tab) {
        if (!tab.isInitialized()) {
            return "";
        }
        String b2 = AbstractC5154ur1.b(tab.s(), false);
        return b2.isEmpty() ? tab.s() : b2;
    }

    public final void b(C4384qI0 qi0, int i2, boolean z2) {
        D91 d91;
        C5956zb1 zb1;
        boolean z3 = true;
        if (i2 < this.g.size()) {
            int i3 = ((C4765sb0) this.g.G.get(i2)).f11283a;
            UH0 uh0 = ((C4765sb0) this.g.G.get(i2)).b;
            if ((i3 == 1 || i3 == 0 || i3 == 2) && uh0.f(AbstractC5106ub1.f11420a) == qi0.c() && !AbstractC4772sd1.g()) {
                return;
            }
        }
        boolean l2 = qi0.l();
        boolean z4 = this.s && !this.d && l2 && g(qi0.c()).size() > 1;
        if (!l2) {
            d91 = null;
        } else if (this.m == null || g(qi0.c()).size() == 1 || !this.s) {
            d91 = this.z;
        } else {
            d91 = ((C3919nd1) this.m).n(qi0.g());
            if (d91 == null) {
                d91 = this.z;
            }
        }
        int i4 = qi0.m() ? R.drawable.f34850_resource_name_obfuscated_RES_2131231525 : R.drawable.f34840_resource_name_obfuscated_RES_2131231524;
        int i5 = qi0.m() ? R.color.f12260_resource_name_obfuscated_RES_2131099916 : R.color.f12250_resource_name_obfuscated_RES_2131099915;
        HH0 hh0 = new HH0(AbstractC5106ub1.B);
        hh0.c(AbstractC5106ub1.f11420a, qi0.c());
        hh0.e(AbstractC5106ub1.g, f(qi0));
        hh0.e(AbstractC5106ub1.s, l2 ? d(qi0.g()) : null);
        hh0.e(AbstractC5106ub1.d, this.p.a(qi0.m()));
        hh0.b(AbstractC5106ub1.h, z2);
        hh0.e(AbstractC5106ub1.f, z4 ? this.e : null);
        hh0.f(J91.b, 1.0f);
        hh0.c(AbstractC5106ub1.k, 0);
        hh0.e(AbstractC5106ub1.m, (!l2 || (zb1 = this.l) == null) ? null : zb1.f11753a.f);
        hh0.b(AbstractC5106ub1.n, qi0.m());
        hh0.c(AbstractC5106ub1.o, i4);
        hh0.c(AbstractC5106ub1.p, i5);
        hh0.e(AbstractC5106ub1.t, this.H);
        hh0.b(AbstractC5106ub1.A, false);
        hh0.c(J91.f8274a, 0);
        UH0 a2 = hh0.a();
        if (AbstractC4772sd1.d.c() && this.w == 1 && l2) {
            a2.m(AbstractC5106ub1.u, e(qi0.g()));
            a2.m(AbstractC5106ub1.w, new C5379w91(qi0.g(), this.z));
            a2.l(AbstractC5106ub1.x, this.x);
        }
        if (this.w == 0) {
            Context context = this.f;
            int i6 = qi0.m() ? R.color.f11260_resource_name_obfuscated_RES_2131099816 : R.color.f11310_resource_name_obfuscated_RES_2131099821;
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            ColorStateList colorStateList = context.getColorStateList(i6);
            ColorStateList colorStateList2 = this.f.getColorStateList(qi0.m() ? R.color.f11320_resource_name_obfuscated_RES_2131099822 : R.color.f11220_resource_name_obfuscated_RES_2131099812);
            ColorStateList colorStateList3 = this.f.getColorStateList(qi0.m() ? R.color.f13330_resource_name_obfuscated_RES_2131100023 : R.color.f11100_resource_name_obfuscated_RES_2131099800);
            a2.m(AbstractC5106ub1.i, colorStateList);
            a2.m(AbstractC5106ub1.q, colorStateList2);
            a2.m(AbstractC5106ub1.r, colorStateList3);
            a2.m(AbstractC5106ub1.l, this.A);
        } else {
            a2.m(AbstractC5106ub1.b, d91);
            a2.m(AbstractC5106ub1.c, l2 ? this.j : null);
            k(qi0, a2);
            if (AbstractC4772sd1.d()) {
                j(qi0, a2);
            }
        }
        if (i2 >= this.g.size()) {
            this.g.q(new C4765sb0(this.w, a2));
        } else {
            K91 k91 = this.g;
            k91.G.add(i2, new C4765sb0(this.w, a2));
            k91.o(i2, 1);
        }
        l(qi0, null);
        H91 h91 = this.r;
        if (h91 != null && this.c) {
            int c2 = qi0.c();
            if (!z2 || AbstractC4772sd1.j()) {
                z3 = false;
            }
            a2.m(AbstractC5106ub1.e, new G91(h91, c2, z2, z3));
        }
        if (qi0.g() != null) {
            qi0.g().A(this.B);
        }
    }

    public final String d(Tab tab) {
        if (!AbstractC4772sd1.f()) {
            return "";
        }
        if (!this.s) {
            return c(tab);
        }
        List g2 = g(tab.getId());
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < g2.size(); i2++) {
            arrayList.add(c((Tab) g2.get(i2)));
        }
        return TextUtils.join(", ", arrayList);
    }

    public final String e(Tab tab) {
        if (this.s && AbstractC4772sd1.g() && g(tab.getId()).size() > 1) {
            return null;
        }
        return X51.d().getString(X51.b(tab.getId()), null);
    }

    public String f(C4384qI0 qi0) {
        String j2 = qi0.j(this.k);
        if (!this.s || this.F == null || g(qi0.c()).size() <= 1) {
            return j2;
        }
        C5039u91 u91 = this.F;
        int f2 = qi0.f();
        Objects.requireNonNull(u91);
        String c2 = AbstractC3842n81.c(f2);
        return c2 == null ? j2 : c2;
    }

    public final List g(int i2) {
        AbstractC3568la1 d2 = ((AbstractC0246Ea1) this.i).c.d();
        return d2 == null ? new ArrayList() : d2.N(i2);
    }

    public final int h() {
        if (!AbstractC4772sd1.f.c() || !AbstractC0444Hf1.a().e()) {
            return R.drawable.f32580_resource_name_obfuscated_RES_2131231298;
        }
        return R.drawable.f30810_resource_name_obfuscated_RES_2131231121;
    }

    public final boolean i(int i2) {
        return i2 != -1 && i2 < this.g.size();
    }

    public final void j(C4384qI0 qi0, UH0 uh0) {
        int size;
        if (AbstractC4772sd1.d()) {
            if (!this.s || (size = g(qi0.c()).size()) <= 1) {
                uh0.m(AbstractC5106ub1.z, this.f.getString(R.string.f46030_resource_name_obfuscated_RES_2131951920, qi0.i()));
                return;
            }
            String f2 = f(qi0);
            if (f2.equals(qi0.j(this.k))) {
                f2 = "";
            }
            if (f2.isEmpty()) {
                uh0.m(AbstractC5106ub1.z, this.f.getString(R.string.f45310_resource_name_obfuscated_RES_2131951848, String.valueOf(size)));
            } else {
                uh0.m(AbstractC5106ub1.z, this.f.getString(R.string.f45320_resource_name_obfuscated_RES_2131951849, f2, String.valueOf(size)));
            }
        }
    }

    public final void k(C4384qI0 qi0, UH0 uh0) {
        String str;
        if (this.s) {
            int size = g(qi0.c()).size();
            if (size > 1) {
                String f2 = f(qi0);
                if (f2.equals(qi0.j(this.k))) {
                    f2 = "";
                }
                TH0 th0 = AbstractC5106ub1.y;
                if (f2.isEmpty()) {
                    str = this.f.getString(R.string.f45440_resource_name_obfuscated_RES_2131951861, String.valueOf(size));
                } else {
                    str = this.f.getString(R.string.f45450_resource_name_obfuscated_RES_2131951862, f2, String.valueOf(size));
                }
                uh0.m(th0, str);
                return;
            }
            uh0.m(AbstractC5106ub1.y, null);
        }
    }

    public void l(C4384qI0 qi0, Bitmap bitmap) {
        int x2 = this.g.x(qi0.c());
        if (x2 != -1) {
            List g2 = g(qi0.c());
            C2649g91 g91 = new C2649g91(this, qi0);
            if (!this.s || g2.size() <= 1) {
                C2307e91 e91 = this.p;
                if (e91.n) {
                    if (bitmap != null) {
                        qi0.k();
                        qi0.m();
                        ((C4765sb0) this.g.get(x2)).b.m(AbstractC5106ub1.d, e91.f(bitmap, e91.j));
                        return;
                    }
                    e91.b(qi0.k(), qi0.m(), g91);
                }
            } else if (!AbstractC4772sd1.f()) {
                ((C4765sb0) this.g.get(x2)).b.m(AbstractC5106ub1.d, null);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(qi0.k());
                int i2 = 0;
                while (arrayList.size() < 4 && i2 < g2.size()) {
                    if (qi0.c() != ((Tab) g2.get(i2)).getId()) {
                        arrayList.add(((Tab) g2.get(i2)).s());
                    }
                    i2++;
                }
                C2307e91 e912 = this.p;
                boolean m2 = qi0.m();
                C3542lO lOVar = e912.p;
                Profile profile = e912.o;
                int i3 = e912.h;
                C2137d91 d91 = new C2137d91(e912, g91, m2);
                Objects.requireNonNull(lOVar);
                if (arrayList.size() <= 1 || arrayList.size() > 4) {
                    StringBuilder i4 = AbstractC2531fV.i("Only able to compose 2 to 4 favicon, but requested ");
                    i4.append(arrayList.size());
                    throw new IllegalStateException(i4.toString());
                }
                N.MYTn7kvC(lOVar.f10342a, profile, (String[]) arrayList.toArray(new String[0]), i3, d91);
            }
        }
    }

    public void m() {
        if (!CF0.d()) {
            GridLayoutManager gridLayoutManager = this.y;
            int i2 = gridLayoutManager.H;
            OW ow = gridLayoutManager.M;
            int i3 = 0;
            int i4 = 0;
            while (i3 < this.g.size()) {
                i4 += ow.c(i3);
                if (i4 != i2) {
                    if (i4 > i2) {
                        if (((C4765sb0) this.g.get(i3)).f11283a == 6) {
                            break;
                        }
                    } else {
                        continue;
                        i3++;
                    }
                }
                i4 = 0;
                i3++;
            }
            if (i4 > i2) {
                int c2 = i2 - (i4 - ow.c(i3));
                for (int i5 = i3 + 1; i5 < this.g.size(); i5++) {
                    if (ow.c(i5) <= c2) {
                        this.g.r(i5, i3);
                        return;
                    }
                }
            }
        }
    }

    public void n(GridLayoutManager gridLayoutManager, int i2) {
        int i3;
        boolean z2;
        if (i2 != 1) {
            Activity activity = (Activity) this.f;
            if (activity == null) {
                z2 = false;
            } else {
                z2 = activity.isInMultiWindowMode();
            }
            if (!z2) {
                i3 = 3;
                gridLayoutManager.Q1(i3);
                gridLayoutManager.M = new C3332k91(this, gridLayoutManager);
            }
        }
        i3 = 2;
        gridLayoutManager.Q1(i3);
        gridLayoutManager.M = new C3332k91(this, gridLayoutManager);
    }

    public final void o(int i2, C4384qI0 qi0, boolean z2, boolean z3, boolean z4) {
        D91 d91;
        if (i2 >= 0 && i2 < this.g.size()) {
            if (z3) {
                ((C4765sb0) this.g.G.get(i2)).b.l(AbstractC5106ub1.f11420a, qi0.c());
            }
            boolean l2 = qi0.l();
            boolean z5 = true;
            if (!l2) {
                d91 = null;
            } else if (this.m == null || g(qi0.c()).size() == 1 || !this.s) {
                d91 = this.z;
            } else {
                d91 = ((C3919nd1) this.m).n(qi0.g());
                if (d91 == null) {
                    d91 = this.z;
                }
            }
            ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.b, d91);
            ((C4765sb0) this.g.G.get(i2)).b.j(AbstractC5106ub1.h, z2);
            ((C4765sb0) this.g.G.get(i2)).b.j(AbstractC5106ub1.A, false);
            ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.g, f(qi0));
            ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.c, l2 ? this.j : null);
            k(qi0, ((C4765sb0) this.g.G.get(i2)).b);
            if (AbstractC4772sd1.d()) {
                j(qi0, ((C4765sb0) this.g.G.get(i2)).b);
            }
            if (l2) {
                ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.s, d(qi0.g()));
            }
            if (AbstractC4772sd1.d.c() && this.w == 1 && l2) {
                ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.u, e(qi0.g()));
                ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.w, new C5379w91(qi0.g(), this.z));
                ((C4765sb0) this.g.G.get(i2)).b.l(AbstractC5106ub1.x, this.x);
            }
            if (this.h != 0 || !qi0.l() || qi0.m() || !CF0.e()) {
                ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.v, null);
            } else {
                ((C4765sb0) this.g.G.get(i2)).b.m(AbstractC5106ub1.v, new C91(qi0.g(), this.g, this.q));
            }
            l(qi0, null);
            boolean z6 = z2 && !z4;
            if (this.r != null && this.c) {
                UH0 uh0 = ((C4765sb0) this.g.G.get(i2)).b;
                TH0 th0 = AbstractC5106ub1.e;
                if (uh0.g(th0) == null || z6 || z3) {
                    H91 h91 = this.r;
                    int c2 = qi0.c();
                    if (!z6 || AbstractC4772sd1.j()) {
                        z5 = false;
                    }
                    ((C4765sb0) this.g.G.get(i2)).b.m(th0, new G91(h91, c2, z6, z5));
                }
            }
        }
    }
}
