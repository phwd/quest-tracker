package defpackage;

import J.N;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: rc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC4598rc0 extends WK implements AbstractC2124d50, View.OnLayoutChangeListener {
    public UH0 F;
    public WindowAndroid G;
    public Q31 H;
    public final C1078Rq0 I;

    /* renamed from: J  reason: collision with root package name */
    public final C0007Ac0 f11207J = new C0007Ac0();
    public final HashSet K = new HashSet();
    public C2294e50 L;
    public C3474l0 M;
    public ChromeActivity N;
    public AbstractC0855Oa1 O;
    public BJ P;
    public AbstractC4448qj Q;
    public final AbstractC1404Xa1 R = new C3915nc0(this);
    public final TT S = new C4086oc0(this);
    public final AbstractC0576Jj T = new C4257pc0(this);

    public View$OnLayoutChangeListenerC4598rc0() {
        QH0 qh0 = AbstractC4938tc0.f11352a;
        SH0 sh0 = AbstractC4938tc0.c;
        QH0 qh02 = AbstractC4938tc0.b;
        QH0 qh03 = AbstractC4938tc0.d;
        Map c = UH0.c(new KH0[]{qh0, sh0, qh02, qh03});
        GH0 gh0 = new GH0(null);
        gh0.f8081a = false;
        HashMap hashMap = (HashMap) c;
        hashMap.put(qh0, gh0);
        JH0 jh0 = new JH0(null);
        jh0.f8282a = 4;
        hashMap.put(sh0, jh0);
        GH0 gh02 = new GH0(null);
        gh02.f8081a = true;
        hashMap.put(qh02, gh02);
        GH0 gh03 = new GH0(null);
        gh03.f8081a = false;
        hashMap.put(qh03, gh03);
        this.F = new UH0(c, null);
        C1078Rq0 rq0 = new C1078Rq0();
        this.I = rq0;
        rq0.m(0);
    }

    public final void V(int i) {
        boolean z = true;
        if (i != 1) {
            int i2 = 0;
            int dimensionPixelSize = (i & 1) != 0 ? this.N.getResources().getDimensionPixelSize(R.dimen.f20280_resource_name_obfuscated_RES_2131165647) : 0;
            if ((i & 2) == 0) {
                z = false;
            }
            if (z) {
                dimensionPixelSize += this.M.a();
                i2 = 0 + this.M.a();
            }
            this.L.f9829a.F.l(I50.d, i2);
            this.I.m(Integer.valueOf(dimensionPixelSize));
        }
    }

    public final ViewGroup W() {
        Tab tab;
        ChromeActivity chromeActivity = this.N;
        if (chromeActivity == null || (tab = chromeActivity.W0.H) == null) {
            return null;
        }
        return tab.u();
    }

    public final C0653Kr X() {
        return (C0653Kr) this.G.u0();
    }

    public final boolean Y() {
        int i = this.G.I.h;
        return i == 0 || i == 2;
    }

    public final boolean Z() {
        WebContents R0;
        ChromeActivity chromeActivity = this.N;
        if (chromeActivity == null || (R0 = chromeActivity.R0()) == null) {
            return false;
        }
        if ((((float) ((Integer) this.I.H).intValue()) / this.G.I.e) + ((float) R0.getHeight()) < 128.0f || R0.getWidth() < 180) {
            return false;
        }
        return true;
    }

    public final void a0() {
        View rootView;
        int i;
        ViewGroup W = W();
        if (W != null && (rootView = W.getRootView()) != null) {
            C3474l0 l0Var = this.M;
            W10 w10 = (W10) this.H.get();
            int dimensionPixelSize = this.N.getResources().getDimensionPixelSize(R.dimen.f20280_resource_name_obfuscated_RES_2131165647) * 3;
            if (w10 != null) {
                i = w10.G.bottom;
            } else {
                i = X().b(rootView);
            }
            l0Var.b(Math.max(dimensionPixelSize, i));
            X().c(rootView);
        }
    }

    public final boolean b0(int i) {
        return this.F.f(AbstractC4938tc0.c) == i;
    }

    public boolean c0() {
        return this.G != null;
    }

    public final boolean d0(View view) {
        return view != null && X().e(this.N, view);
    }

    public void e0() {
        if (b0(3) || b0(1)) {
            this.F.l(AbstractC4938tc0.c, 11);
        } else if (b0(11)) {
            this.F.l(AbstractC4938tc0.c, 13);
        }
    }

    public void f0() {
        if (c0()) {
            this.L.a();
            this.F.l(AbstractC4938tc0.c, 4);
        }
    }

    public final void g0() {
        if (c0()) {
            C5958zc0 a2 = this.f11207J.a(this.N.R0());
            C4112ol olVar = a2.d;
            if (olVar != null) {
                olVar.b(olVar.I);
            }
            for (int i : C5958zc0.f11755a) {
                C4112ol olVar2 = a2.b(i).f11691a;
                if (olVar2 != null) {
                    olVar2.b(olVar2.I);
                }
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 : C5958zc0.f11755a) {
                AbstractC4329q0 q0Var = a2.b(i2).b;
                if (q0Var != null) {
                    arrayList.add(q0Var.f11106a);
                }
            }
            C3148j50[] j50Arr = (C3148j50[]) arrayList.toArray(new C3148j50[0]);
            C3816n0 n0Var = this.M.f10320a;
            UH0 uh0 = n0Var.F;
            OH0 oh0 = AbstractC4158p0.f11043a;
            ((C1794b90) uh0.g(oh0)).u(j50Arr);
            UH0 uh02 = n0Var.F;
            uh02.l(AbstractC4158p0.b, ((C1794b90) uh02.g(oh0)).size() - 1);
            ((C1794b90) this.L.b.b.F.g(S50.f8876a)).u(j50Arr);
        }
    }

    public final void h0(int i) {
        if ((i & 8) != 0) {
            ViewGroup W = W();
            if (W != null) {
                X().i(W);
            }
        } else if (i == 1) {
            a0();
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        WebContents R0;
        if (c0()) {
            C4686s50 s50 = this.L.f9829a;
            Objects.requireNonNull(s50);
            boolean z = false;
            if (!(!N.M09VlOh_("AutofillKeyboardAccessory") ? ((C1794b90) s50.F.g(I50.f8198a)).size() > 0 : ((C1794b90) s50.F.g(I50.f8198a)).size() > 1) && !((R50) s50.H).g()) {
                z = true;
            }
            if (!z) {
                int i9 = 4;
                if (!Z()) {
                    this.F.l(AbstractC4938tc0.c, 4);
                    return;
                }
                boolean Y = Y();
                UH0 uh0 = this.F;
                QH0 qh0 = AbstractC4938tc0.b;
                if (Y != uh0.h(qh0)) {
                    this.F.j(qh0, Y());
                    return;
                }
                if ((b0(11) || b0(3)) && (R0 = this.N.R0()) != null) {
                    float f = this.G.I.e;
                    int round = (Math.round(((float) R0.getHeight()) * f) + ((Integer) this.I.H).intValue()) - Math.round(f * 128.0f);
                    if (this.M.a() > round) {
                        this.M.b(round);
                        V(this.F.f(AbstractC4938tc0.c));
                    }
                }
                if (!d0(view)) {
                    if (b0(1)) {
                        this.F.l(AbstractC4938tc0.c, 3);
                    }
                    if (b0(5)) {
                        this.F.l(AbstractC4938tc0.c, 4);
                    }
                    if (b0(4)) {
                        this.L.a();
                    }
                    h0(this.F.f(AbstractC4938tc0.c));
                } else if (!b0(1)) {
                    UH0 uh02 = this.F;
                    SH0 sh0 = AbstractC4938tc0.c;
                    if (uh02.h(AbstractC4938tc0.f11352a)) {
                        i9 = 5;
                    }
                    uh02.l(sh0, i9);
                }
            }
        }
    }
}
