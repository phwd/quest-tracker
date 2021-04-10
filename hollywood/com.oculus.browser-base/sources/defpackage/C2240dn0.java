package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.chrome.browser.gesturenav.NavigationSheetView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.url.GURL;

/* renamed from: dn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2240dn0 extends AbstractC4277pj implements AbstractC1497Ym0 {
    public final View F;
    public final LayoutInflater G;
    public final Q31 H;
    public final C2923hn0 I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC0576Jj f9807J = new C2069cn0(this);
    public final Handler K = new Handler();
    public final Runnable L;
    public final float M;
    public final C4935tb0 N;
    public final C4284pl0 O;
    public final int P;
    public final int Q;
    public final View R;
    public C5455we1 S;
    public NavigationSheetView T;
    public boolean U;
    public boolean V;
    public boolean W;
    public boolean X;

    public C2240dn0(View view, Context context, Q31 q31, Profile profile) {
        C4935tb0 tb0 = new C4935tb0();
        this.N = tb0;
        C4284pl0 pl0 = new C4284pl0(tb0);
        this.O = pl0;
        this.R = view;
        this.H = q31;
        LayoutInflater from = LayoutInflater.from(context);
        this.G = from;
        this.F = from.inflate(R.layout.f39820_resource_name_obfuscated_RES_2131624291, (ViewGroup) null);
        this.I = new C2923hn0(context, tb0, profile, new C1558Zm0(this));
        pl0.a(0, new L70(R.layout.f39800_resource_name_obfuscated_RES_2131624289), new C1718an0());
        this.L = new RunnableC1898bn0(this);
        this.M = Math.min(context.getResources().getDisplayMetrics().density * 224.0f, (float) (view.getWidth() / 2));
        this.P = context.getResources().getDimensionPixelSize(R.dimen.f22840_resource_name_obfuscated_RES_2131165903);
        this.Q = context.getResources().getDimensionPixelSize(R.dimen.f22880_resource_name_obfuscated_RES_2131165907) + context.getResources().getDimensionPixelSize(R.dimen.f22870_resource_name_obfuscated_RES_2131165906) + context.getResources().getDimensionPixelSize(R.dimen.f22890_resource_name_obfuscated_RES_2131165908);
    }

    public static int z(Context context, int i) {
        return context.getResources().getDimensionPixelSize(i);
    }

    public final int A() {
        int i;
        BottomSheet bottomSheet = ((C5638xj) ((AbstractC4448qj) this.H.get())).F;
        if (bottomSheet == null) {
            i = -1;
        } else {
            i = bottomSheet.V;
        }
        return i != -1 ? i : ((C5638xj) ((AbstractC4448qj) this.H.get())).o();
    }

    public final boolean B() {
        if (this.H.get() != null && A() == 1) {
            return true;
        }
        return false;
    }

    public final boolean C(boolean z) {
        NavigationSheetView navigationSheetView = (NavigationSheetView) this.G.inflate(R.layout.f39810_resource_name_obfuscated_RES_2131624290, (ViewGroup) null);
        this.T = navigationSheetView;
        ((ListView) navigationSheetView.findViewById(R.id.navigation_entries)).setAdapter((ListAdapter) this.O);
        C5455we1 we1 = this.S;
        C0948Pm0 z2 = we1.f11557a.l().f().z(this.U, 8);
        z2.f8712a.add(new NavigationEntry(-1, new GURL("chrome://history/"), GURL.emptyGURL(), GURL.emptyGURL(), GURL.emptyGURL(), we1.c, null, 0, 0));
        C2923hn0 hn0 = this.I;
        hn0.j = z2;
        HashSet hashSet = new HashSet();
        for (int i = 0; i < hn0.j.b(); i++) {
            UH0 uh0 = new UH0(Arrays.asList(AbstractC2752gn0.d));
            NavigationEntry a2 = hn0.j.a(i);
            TH0 th0 = AbstractC2752gn0.b;
            String str = a2.f;
            if (AbstractC5154ur1.h(a2.b)) {
                str = hn0.h;
            }
            if (TextUtils.isEmpty(str)) {
                str = a2.d.h();
            }
            if (TextUtils.isEmpty(str)) {
                str = a2.b.h();
            }
            uh0.m(th0, str);
            uh0.m(AbstractC2752gn0.c, new View$OnClickListenerC2410en0(hn0, i, a2));
            hn0.e.q(new C4765sb0(0, uh0));
            if (a2.g == null) {
                String h = a2.b.h();
                if (!hashSet.contains(h)) {
                    C2581fn0 fn0 = new C2581fn0(hn0, h);
                    if (!h.equals("chrome://history/")) {
                        hn0.b.c(hn0.i, h, hn0.d, fn0);
                        hashSet.add(h);
                    } else {
                        ((C4765sb0) hn0.e.get(i)).b.m(AbstractC2752gn0.f10021a, hn0.f);
                    }
                }
            }
        }
        if (!((C5638xj) ((AbstractC4448qj) this.H.get())).u(this, true)) {
            w(false);
            this.T = null;
            return false;
        }
        ((C5638xj) ((AbstractC4448qj) this.H.get())).j(this.f9807J);
        this.W = true;
        if (z && z2.b() <= 3) {
            x();
        }
        return true;
    }

    public final void D(float f, long j) {
        if (isHidden() && Math.abs(f) > 2.0f) {
            this.K.removeCallbacks(this.L);
            this.K.postDelayed(this.L, j);
        }
    }

    @Override // defpackage.AbstractC1497Ym0
    public void a() {
        if (this.H.get() != null) {
            this.K.removeCallbacks(this.L);
            if (this.W) {
                AbstractC3364kK0.g("GestureNavigation.Sheet.Peeked", this.U ? 1 : 0, 2);
            }
            if (B()) {
                x();
            }
        }
    }

    @Override // defpackage.AbstractC1497Ym0
    public void b(float f, float f2, boolean z) {
        if (this.H.get() == null || this.V) {
            return;
        }
        if (f2 > this.M) {
            D(f, 50);
        } else if (z) {
            D(f, 400);
        } else if (B()) {
            w(true);
        } else {
            this.K.removeCallbacks(this.L);
        }
    }

    @Override // defpackage.AbstractC1497Ym0
    public void c(boolean z, boolean z2) {
        if (this.H.get() != null) {
            this.U = z;
            this.V = z2;
            this.W = false;
            this.X = false;
        }
    }

    @Override // defpackage.AbstractC1497Ym0
    public boolean d(boolean z, boolean z2) {
        c(z, false);
        this.X = true;
        boolean C = C(false);
        if (C) {
            AbstractC3535lK0.a("BackMenu_Popup");
        }
        return C;
    }

    @Override // defpackage.AbstractC1497Ym0
    public boolean e() {
        if (this.H.get() == null) {
            return false;
        }
        int A = A();
        if (A == 2 || A == 3) {
            return true;
        }
        return false;
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.T;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return y((float) this.R.getHeight());
    }

    @Override // defpackage.AbstractC4277pj
    public float i() {
        if (this.X) {
            return -2.0f;
        }
        return y((float) ((this.P / 2) + (this.R.getHeight() / 2)));
    }

    @Override // defpackage.AbstractC1497Ym0
    public boolean isHidden() {
        if (this.H.get() == null || A() == 0) {
            return true;
        }
        return false;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        if (this.H.get() == null || this.X || ((C5638xj) ((AbstractC4448qj) this.H.get())).q()) {
            return -2;
        }
        return z(this.R.getContext(), R.dimen.f22900_resource_name_obfuscated_RES_2131165909);
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 1;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f56890_resource_name_obfuscated_RES_2131953006;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f56900_resource_name_obfuscated_RES_2131953007;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f56910_resource_name_obfuscated_RES_2131953008;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f56920_resource_name_obfuscated_RES_2131953009;
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return this.F;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        NavigationSheetView navigationSheetView = this.T;
        View childAt = navigationSheetView.F.getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        return -(childAt.getTop() - navigationSheetView.F.getPaddingTop());
    }

    @Override // defpackage.AbstractC4277pj
    public boolean t() {
        return true;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }

    public void w(boolean z) {
        AbstractC4448qj qjVar = (AbstractC4448qj) this.H.get();
        if (qjVar != null) {
            C5638xj xjVar = (C5638xj) qjVar;
            xjVar.p(this, z, 0);
            xjVar.r(this.f9807J);
            this.I.e.clear();
        }
    }

    public final void x() {
        ((C5638xj) ((AbstractC4448qj) this.H.get())).m();
        AbstractC3364kK0.g("GestureNavigation.Sheet.Viewed", this.U ? 1 : 0, 2);
    }

    public final float y(float f) {
        return Math.min(f, (float) ((this.O.getCount() * this.P) + this.Q)) / ((float) this.R.getHeight());
    }
}
