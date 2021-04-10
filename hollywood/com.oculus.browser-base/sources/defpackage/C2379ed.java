package defpackage;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: ed  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2379ed implements AbstractC4597rc, AbstractC1768b01, AbstractC5496ws0, AbstractC5531x31 {
    public final Context F;
    public final AbstractC0920Pc G;
    public final Qq1 H;
    public final UH0 I;

    /* renamed from: J  reason: collision with root package name */
    public final C4935tb0 f9866J;
    public final List K = new ArrayList();
    public final Handler L;
    public AutocompleteResult M;
    public AbstractC4422qa0 N;
    public boolean O;
    public AutocompleteController P;
    public long Q;
    public boolean R;
    public int S;
    public int T;
    public long U = -1;
    public boolean V;
    public String W;
    public Runnable X;
    public RunnableC2038cd Y;
    public boolean Z;
    public boolean a0;
    public long b0;
    public boolean c0 = true;
    public M2 d0;
    public Q31 e0;
    public Y2 f0;
    public final C4044oJ g0;
    public final C4215pJ h0;

    public C2379ed(Context context, AbstractC0920Pc pc, Qq1 qq1, AutocompleteController autocompleteController, UH0 uh0, Handler handler, M2 m2, Q31 q31, C1595a3 a3Var, Q31 q312, AbstractC4422qa0 qa0) {
        this.F = context;
        this.G = pc;
        this.H = qq1;
        this.I = uh0;
        this.d0 = m2;
        m2.a(this);
        this.e0 = q31;
        this.P = autocompleteController;
        autocompleteController.c = this;
        this.L = handler;
        this.N = qa0;
        C4935tb0 tb0 = (C4935tb0) uh0.g(AbstractC5701y31.c);
        this.f9866J = tb0;
        this.M = new AutocompleteResult(null, null);
        C4044oJ oJVar = new C4044oJ(this.P);
        this.g0 = oJVar;
        oJVar.e = q312;
        this.h0 = new C4215pJ(tb0);
        if (a3Var != null) {
            oJVar.d = a3Var;
            this.f0 = new C1867bd(this, a3Var);
        }
    }

    @Override // defpackage.AbstractC1768b01
    public void d() {
    }

    @Override // defpackage.AbstractC1768b01
    public void e() {
        this.h0.b();
    }

    public void f() {
        Runnable runnable = this.X;
        if (runnable != null) {
            if (!this.K.remove(runnable)) {
                this.L.removeCallbacks(this.X);
            }
            this.X = null;
        }
    }

    public final void g(String str, long j) {
        AutocompleteMatch autocompleteMatch;
        boolean z = false;
        if (j() <= 0 || !str.trim().equals(this.W.trim())) {
            AutocompleteController autocompleteController = this.P;
            boolean f = ((C3909na0) this.G).f();
            long j2 = autocompleteController.f10726a;
            autocompleteMatch = j2 != 0 ? (AutocompleteMatch) N.MDxZMia5(j2, autocompleteController, str, f) : null;
            if (autocompleteMatch == null) {
                return;
            }
        } else {
            autocompleteMatch = i(0);
            z = true;
        }
        l(0, autocompleteMatch, autocompleteMatch.j, j, z);
    }

    public final long h() {
        if (this.U > 0) {
            return SystemClock.elapsedRealtime() - this.U;
        }
        return -1;
    }

    public AutocompleteMatch i(int i) {
        return (AutocompleteMatch) this.M.f10862a.get(i);
    }

    public int j() {
        return this.M.f10862a.size();
    }

    public final void k() {
        if (this.P != null && this.O) {
            r(true);
            C4215pJ pJVar = this.h0;
            pJVar.d.clear();
            pJVar.f11061a.clear();
            pJVar.e.clear();
            this.M = new AutocompleteResult(null, null);
            t();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006b, code lost:
        if (r4 == null) goto L_0x006d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l(int r16, org.chromium.components.omnibox.AutocompleteMatch r17, org.chromium.url.GURL r18, long r19, boolean r21) {
        /*
        // Method dump skipped, instructions count: 217
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2379ed.l(int, org.chromium.components.omnibox.AutocompleteMatch, org.chromium.url.GURL, long, boolean):void");
    }

    public void m(AutocompleteMatch autocompleteMatch, int i, GURL gurl) {
        if (!this.Z || this.O) {
            l(i, autocompleteMatch, gurl, this.b0, true);
        } else {
            this.Y = new RunnableC2038cd(this, autocompleteMatch, i, gurl);
        }
    }

    public void n(AutocompleteResult autocompleteResult, String str) {
        boolean z;
        boolean z2;
        int i;
        B31 b31;
        if (!(this.a0 || this.S == 0)) {
            List list = autocompleteResult.f10862a;
            RunnableC2038cd cdVar = this.Y;
            if (cdVar != null) {
                int size = list.size();
                RunnableC2038cd cdVar2 = this.Y;
                int i2 = cdVar2.G;
                cdVar.H = size > i2 && cdVar2.F.equals(list.get(i2));
                this.Y.run();
                this.Y = null;
            }
            this.W = AbstractC2531fV.f(((Oq1) this.H).h(), str);
            if (!this.M.equals(autocompleteResult)) {
                this.M = autocompleteResult;
                C4044oJ oJVar = this.g0;
                Objects.requireNonNull(oJVar.c);
                for (int i3 = 0; i3 < oJVar.f10541a.size(); i3++) {
                    ((B31) oJVar.f10541a.get(i3)).c();
                }
                List list2 = autocompleteResult.f10862a;
                int size2 = list2.size();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int i4 = 0; i4 < size2; i4++) {
                    AutocompleteMatch autocompleteMatch = (AutocompleteMatch) list2.get(i4);
                    int i5 = 0;
                    while (true) {
                        if (i5 >= oJVar.f10541a.size()) {
                            b31 = null;
                            break;
                        }
                        b31 = (B31) oJVar.f10541a.get(i5);
                        if (b31.f(autocompleteMatch, i4)) {
                            break;
                        }
                        i5++;
                    }
                    arrayList2.add(new Pair(autocompleteMatch, b31));
                }
                int i6 = -1;
                if (oJVar.j) {
                    if (oJVar.i == -1) {
                        i = Math.min(arrayList2.size(), 5);
                    } else {
                        i = 0;
                        int i7 = 0;
                        int i8 = -1;
                        while (i < arrayList2.size() && i7 < oJVar.i) {
                            Pair pair = (Pair) arrayList2.get(i);
                            int i9 = ((AutocompleteMatch) pair.first).r;
                            if (i8 != i9) {
                                i7 += oJVar.c.c;
                                i8 = i9;
                            }
                            i7 += ((B31) pair.second).h();
                            i++;
                        }
                    }
                    oJVar.k = i < arrayList2.size();
                    int i10 = 0;
                    while (i10 < arrayList2.size() && ((AutocompleteMatch) ((Pair) arrayList2.get(i10)).first).r == -1) {
                        i10++;
                    }
                    int i11 = 1;
                    while (i11 < arrayList2.size() && ((r9 = ((AutocompleteMatch) ((Pair) arrayList2.get(i11)).first).f10861a) == 28 || r9 == 26 || r9 == 19 || r9 == 27)) {
                        i11++;
                    }
                    if (i10 > i11) {
                        int max = Math.max(Math.min(i, i10), i11);
                        C3873nJ nJVar = new C3873nJ();
                        if (i11 < max) {
                            Collections.sort(arrayList2.subList(i11, max), nJVar);
                            N.MYNhWiA7(oJVar.b.f10726a, i11, max);
                        }
                        if (max < i10) {
                            Collections.sort(arrayList2.subList(max, i10), nJVar);
                            N.MYNhWiA7(oJVar.b.f10726a, max, i10);
                        }
                    }
                }
                for (int i12 = 0; i12 < size2; i12++) {
                    Pair pair2 = (Pair) arrayList2.get(i12);
                    AutocompleteMatch autocompleteMatch2 = (AutocompleteMatch) pair2.first;
                    B31 b312 = (B31) pair2.second;
                    int i13 = autocompleteMatch2.r;
                    if (i6 != i13) {
                        C2550fd fdVar = (C2550fd) autocompleteResult.b.get(i13);
                        if (fdVar != null) {
                            Objects.requireNonNull(oJVar.c);
                            UH0 uh0 = new UH0(AbstractC2194dX.e);
                            YW yw = oJVar.c;
                            String str2 = fdVar.f9934a;
                            Objects.requireNonNull(yw);
                            uh0.m(AbstractC2194dX.c, str2);
                            uh0.j(AbstractC2194dX.b, false);
                            uh0.m(AbstractC2194dX.f9787a, new XW(yw, uh0, i13));
                            arrayList.add(new C2848hJ(oJVar.c, uh0, i13));
                        }
                        i6 = i13;
                    }
                    UH0 g = b312.g();
                    b312.a(autocompleteMatch2, g, i12);
                    arrayList.add(new C2848hJ(b312, g, i6));
                }
                C4215pJ pJVar = this.h0;
                SparseArray sparseArray = autocompleteResult.b;
                pJVar.d = arrayList;
                pJVar.e.clear();
                for (int i14 = 0; i14 < sparseArray.size(); i14++) {
                    pJVar.e.put(sparseArray.keyAt(i14), Boolean.valueOf(((C2550fd) sparseArray.valueAt(i14)).b));
                }
                ArrayList arrayList3 = new ArrayList();
                for (int i15 = 0; i15 < pJVar.d.size(); i15++) {
                    C2848hJ hJVar = (C2848hJ) pJVar.d.get(i15);
                    UH0 uh02 = hJVar.b;
                    uh02.l(AbstractC4851t31.b, pJVar.b);
                    uh02.l(AbstractC4851t31.f11318a, pJVar.c);
                    if (!((Boolean) pJVar.e.get(hJVar.d, Boolean.FALSE)).booleanValue() || pJVar.a(hJVar, hJVar.d)) {
                        arrayList3.add(hJVar);
                    }
                }
                pJVar.f11061a.t(arrayList3);
                if (TextUtils.isEmpty(((Oq1) this.H).h()) || list.isEmpty()) {
                    z = false;
                    z2 = true;
                } else {
                    z = false;
                    z2 = ((AutocompleteMatch) list.get(0)).c;
                }
                View$OnKeyListenerC0001Aa0 aa0 = ((C3909na0) this.G).N;
                C5698y21 y21 = aa0.K.G;
                if (z2 != y21.d0) {
                    y21.d0 = z2;
                    y21.e();
                }
                String h = aa0.N.h();
                if (aa0.N.m()) {
                    Tq1 tq1 = aa0.N.H;
                    if (tq1.H) {
                        tq1.F.m(Wq1.c, new Uq1(h, str));
                    }
                }
                AbstractView$OnClickListenerC5272va0 va0 = aa0.F;
                if (va0.W && va0.T) {
                    va0.e(true);
                }
                if (aa0.W && !AbstractC1575Zv.e().g("disable-instant")) {
                    Objects.requireNonNull(aa0.P);
                    if (C5052uE.c().e) {
                        z = N.Mknz7Q$j();
                    }
                    if (z && aa0.H.r()) {
                        C3450ks0 ks0 = aa0.M;
                        N.M5tjuSum(ks0.f10309a, ks0, h, aa0.F.Q, aa0.L.H.P.b, (Profile) ((C1078Rq0) aa0.O).H, aa0.H.d());
                    }
                }
                t();
            }
        }
    }

    public void o(String str) {
        AbstractC1220Ua0.f("Autocomplete", "onTextChangedForAutocomplete", new Object[0]);
        if (!this.a0) {
            this.c0 = true;
            f();
            if (this.T == 0 && this.O) {
                AutocompleteController autocompleteController = this.P;
                long j = autocompleteController.f10726a;
                if (j != 0) {
                    N.MHXditHc(j, autocompleteController);
                }
                this.U = SystemClock.elapsedRealtime();
                this.T = 1;
            }
            r(false);
            if (TextUtils.isEmpty(str)) {
                AbstractC1220Ua0.f("Autocomplete", "onTextChangedForAutocomplete: url is empty", new Object[0]);
                k();
                q();
            } else if (this.N.r() || this.N.g()) {
                boolean z = !((Oq1) this.H).m();
                RunnableC1530Zc zc = new RunnableC1530Zc(this, this.N.b(), this.N.i(), this.N.l(((C3909na0) this.G).f()), str, ((Oq1) this.H).f() == ((Oq1) this.H).G.getSelectionEnd() ? ((Oq1) this.H).f() : -1, z, ((C3909na0) this.G).N.F.V || this.T == 2);
                this.X = zc;
                if (this.O) {
                    this.L.postDelayed(zc, 30);
                } else {
                    this.K.add(zc);
                }
            } else {
                AbstractC1220Ua0.f("Autocomplete", "onTextChangedForAutocomplete: no tab", new Object[0]);
            }
            ((C3909na0) this.G).N.F.t();
        }
    }

    public final void p(int i, int i2, AutocompleteMatch autocompleteMatch) {
        String i3 = this.N.i();
        int l = this.N.l(((C3909na0) this.G).f());
        long h = h();
        int length = ((Oq1) this.H).g().length() - ((Oq1) this.H).h().length();
        WebContents l2 = this.N.r() ? this.N.d().l() : null;
        AutocompleteController autocompleteController = this.P;
        int hashCode = autocompleteMatch.hashCode();
        int i4 = autocompleteMatch.f10861a;
        Objects.requireNonNull(autocompleteController);
        if (i4 != 20) {
            N.MqRSHXK7(autocompleteController.f10726a, autocompleteController, i, i2, hashCode, i3, l, h, length, l2);
        }
    }

    public final void q() {
        this.T = 0;
        this.U = -1;
        if (this.O && ((C3909na0) this.G).N.F.T) {
            if (this.N.r() || this.N.g()) {
                int l = this.N.l(((C3909na0) this.G).f());
                AutocompleteController autocompleteController = this.P;
                Profile b = this.N.b();
                String g = ((Oq1) this.H).g();
                String i = this.N.i();
                String q = this.N.q();
                Objects.requireNonNull(autocompleteController);
                if (b != null && !TextUtils.isEmpty(i)) {
                    if (!b.g() && !AbstractC5154ur1.g(i) && N.M09VlOh_("OmniboxSpareRenderer")) {
                        PostTask.b(Zo1.b, new RunnableC4427qc(b), (long) N.M37SqSAy("OmniboxSpareRenderer", "omnibox_spare_renderer_delay_ms", 0));
                    }
                    long MHKRbGMP = N.MHKRbGMP(autocompleteController, b);
                    autocompleteController.f10726a = MHKRbGMP;
                    if (MHKRbGMP != 0) {
                        if (autocompleteController.e) {
                            autocompleteController.g = true;
                        }
                        N.MmFptZoy(MHKRbGMP, autocompleteController, g, i, l, q);
                    }
                }
            }
        }
    }

    public final void r(boolean z) {
        AutocompleteController autocompleteController = this.P;
        if (autocompleteController != null) {
            autocompleteController.b(z);
        }
        f();
    }

    public final void t() {
        boolean z = this.S == 2 && j() > 0;
        UH0 uh0 = this.I;
        QH0 qh0 = AbstractC5701y31.f11659a;
        boolean h = uh0.h(qh0);
        this.I.j(qh0, z);
        if (z && !h) {
            this.c0 = true;
        }
    }
}
