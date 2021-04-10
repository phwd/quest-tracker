package defpackage;

import J.N;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.graphics.Rect;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.TabGridThumbnailView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: b91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1795b91 implements AbstractC4371qE {
    public final I91 F;
    public final TabListRecyclerView G;
    public final JW0 H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final Rect f9518J = new Rect();
    public final Context K;
    public final K91 L;
    public final int M;
    public final AbstractC0124Ca1 N;
    public boolean O;
    public ViewTreeObserver.OnGlobalLayoutListener P;

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0140, code lost:
        if (r17 == 3) goto L_0x0144;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1795b91(int r17, android.content.Context r18, defpackage.AbstractC0124Ca1 r19, defpackage.H91 r20, defpackage.C0618Kc1 r21, boolean r22, defpackage.AbstractC5209v91 r23, defpackage.T61 r24, int r25, defpackage.C5956zb1 r26, defpackage.AbstractC3577ld1 r27, android.view.ViewGroup r28, boolean r29, java.lang.String r30) {
        /*
        // Method dump skipped, instructions count: 364
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1795b91.<init>(int, android.content.Context, Ca1, H91, Kc1, boolean, v91, T61, int, zb1, ld1, android.view.ViewGroup, boolean, java.lang.String):void");
    }

    public static void o(View view) {
        ((TabGridThumbnailView) view.findViewById(R.id.tab_thumbnail)).P = AbstractC4089od0.b((float) AbstractC4772sd1.c.c(), 0.5f, 2.0f);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        I91 i91 = this.F;
        AbstractC2648g90 g90 = i91.D;
        if (g90 != null) {
            i91.g.F.c(g90);
        }
        TabModel i = ((AbstractC0246Ea1) i91.i).i();
        if (i != null) {
            for (int i2 = 0; i2 < i.getCount(); i2++) {
                i.getTabAt(i2).I(i91.B);
            }
        }
        AbstractC5783ya1 ya1 = i91.C;
        if (ya1 != null) {
            ((AbstractC0246Ea1) i91.i).c.h(ya1);
        }
        if (i91.G != null) {
            ((I71) ((AbstractC0246Ea1) i91.i).c.g(false)).g.c(i91.G);
            ((I71) ((AbstractC0246Ea1) i91.i).c.g(true)).g.c(i91.G);
        }
        ComponentCallbacks componentCallbacks = i91.t;
        if (componentCallbacks != null) {
            i91.f.unregisterComponentCallbacks(componentCallbacks);
        }
        C5039u91 u91 = i91.F;
        if (u91 != null) {
            ((AbstractC0246Ea1) u91.f11393a).c.h(u91.b);
            ((I71) ((AbstractC0246Ea1) u91.f11393a).c.g(false)).g.c(u91.c);
            ((I71) ((AbstractC0246Ea1) u91.f11393a).c.g(true)).g.c(u91.c);
        }
        if (i91.E != null) {
            AbstractC0444Hf1.a().b.c(i91.E);
        }
        if (this.P != null) {
            this.G.getViewTreeObserver().removeOnGlobalLayoutListener(this.P);
        }
        this.G.V = null;
    }

    public void f(int i, int i2, UH0 uh0) {
        K91 k91 = this.F.g;
        k91.G.add(i, new C4765sb0(i2, uh0));
        k91.o(i, 1);
    }

    public void g(int i, UH0 uh0) {
        I91 i91 = this.F;
        int size = this.L.size();
        K91 k91 = i91.g;
        k91.G.add(size, new C4765sb0(i, uh0));
        k91.o(size, 1);
    }

    public Rect h() {
        Rect rect = new Rect();
        this.G.getGlobalVisibleRect(rect);
        return rect;
    }

    public int i() {
        if (!AbstractC2793h01.b()) {
            return 0;
        }
        Rect h = h();
        Rect rect = new Rect();
        ((ChromeActivity) this.K).I0.getGlobalVisibleRect(rect);
        h.offset(0, (-rect.top) - ((int) this.K.getResources().getDimension(R.dimen.f26330_resource_name_obfuscated_RES_2131166252)));
        return h.top;
    }

    public void j(IJ ij) {
        float f;
        if (!this.O) {
            this.O = true;
            Profile b = ((AbstractC0246Ea1) this.N).i().b();
            I91 i91 = this.F;
            i91.p.e(b);
            ((AbstractC0246Ea1) i91.i).c.a(i91.C);
            int i = 0;
            if (((AbstractC0246Ea1) i91.i).c.d() instanceof I71) {
                i91.G = new C4869t91(i91);
                ((I71) ((AbstractC0246Ea1) i91.i).c.g(false)).g.b(i91.G);
                ((I71) ((AbstractC0246Ea1) i91.i).c.g(true)).g.b(i91.G);
            }
            if (AbstractC4772sd1.f()) {
                i91.F = new C5039u91(i91, i91.i);
            }
            if (AbstractC4772sd1.d.c()) {
                i91.x = i91.h();
                i91.E = new C2478f91(i91);
                AbstractC0444Hf1.a().b.b(i91.E);
            }
            if (ij != null) {
                TabListRecyclerView tabListRecyclerView = this.G;
                Objects.requireNonNull(tabListRecyclerView);
                M91 m91 = new M91(tabListRecyclerView, tabListRecyclerView);
                tabListRecyclerView.q1 = m91;
                try {
                    f = Float.valueOf(N.MMltG$kc("TabToGTSAnimation", "downsampling-scale")).floatValue();
                } catch (NumberFormatException unused) {
                    f = 0.5f;
                }
                if (m91.f10109J != f) {
                    m91.f(null);
                }
                m91.f10109J = f;
                tabListRecyclerView.p1 = ij;
            }
            int i2 = this.I;
            if ((i2 == 0 || i2 == 3) && this.M != 0) {
                I91 i912 = this.F;
                float dimension = this.K.getResources().getDimension(R.dimen.f25300_resource_name_obfuscated_RES_2131166149);
                float dimension2 = this.K.getResources().getDimension(R.dimen.f25480_resource_name_obfuscated_RES_2131166167);
                float dimension3 = this.K.getResources().getDimension(R.dimen.f16870_resource_name_obfuscated_RES_2131165306);
                C4863t71 t71 = i912.u;
                t71.i = dimension;
                t71.j = dimension2;
                t71.k = dimension3;
                t71.t = b;
                if (true ^ (C3919nd1.k() && t71.l)) {
                    i = 51;
                }
                t71.n = i;
                new C5533x40(i912.u).j(this.G);
            }
        }
    }

    public void k(int i, AbstractC5105ub0 ub0, YH0 yh0) {
        this.H.K.put(i, new Pair(ub0, yh0));
    }

    public void l(int i, int i2) {
        int i3;
        I91 i91 = this.F;
        Objects.requireNonNull(i91);
        if (i != 3 && i != 6) {
            if (i == 5) {
                K91 k91 = i91.g;
                i3 = k91.size() - 1;
                while (true) {
                    if (i3 < 0) {
                        break;
                    } else if (((C4765sb0) k91.get(i3)).b.f(J91.f8274a) == 2) {
                        break;
                    } else {
                        i3--;
                    }
                }
            }
            i3 = -1;
        } else if (i2 == 4) {
            while (i91.g.z() != -1) {
                i91.g.s(i91.g.z());
            }
            return;
        } else {
            i3 = i91.g.A(i2);
        }
        if (i3 != -1) {
            i91.g.s(i3);
        }
    }

    public boolean m(List list) {
        return n(C4384qI0.d(list), false, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r1 == 0) goto L_0x008a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean n(java.util.List r10, boolean r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 266
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1795b91.n(java.util.List, boolean, boolean):boolean");
    }

    public boolean p() {
        Rect rect;
        TabListRecyclerView tabListRecyclerView = this.G;
        I91 i91 = this.F;
        int i = i91.v;
        if (i == -1) {
            i = ((AbstractC0246Ea1) i91.i).k();
        }
        int x = i91.g.x(i);
        I91 i912 = this.F;
        if (i912.v == -1) {
            ((AbstractC0246Ea1) i912.i).k();
        }
        IW0 iw0 = (IW0) tabListRecyclerView.H(x);
        if (iw0 == null || x == -1) {
            rect = null;
        } else {
            View d = ((ViewLookupCachingFrameLayout) iw0.G).d(R.id.tab_thumbnail);
            Rect rect2 = new Rect();
            rect = new Rect();
            tabListRecyclerView.getGlobalVisibleRect(rect2);
            d.getGlobalVisibleRect(rect);
            rect.offset(-rect2.left, -rect2.top);
        }
        if (rect == null) {
            return false;
        }
        rect.offset(0, i());
        this.f9518J.set(rect);
        return true;
    }
}
