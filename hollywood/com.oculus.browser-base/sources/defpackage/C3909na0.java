package defpackage;

import J.N;
import android.content.Context;
import android.view.ActionMode;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.omnibox.LocationBarPhone;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: na0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3909na0 extends AbstractC3055ia0 implements AbstractC4968tm0, AbstractC0046As0, AbstractC0920Pc {
    public AbstractView$OnClickListenerC5272va0 F;
    public AbstractC3738ma0 G;
    public M2 H;
    public Oq1 I;

    /* renamed from: J  reason: collision with root package name */
    public C0859Oc f10497J;
    public R11 K;
    public Uy1 L;
    public View M;
    public View$OnKeyListenerC0001Aa0 N;
    public View O;
    public final C1570Zs0 P;
    public C1128Sl Q = new C1128Sl();
    public boolean R;

    public C3909na0(View view, View view2, AbstractC0956Pq0 pq0, AbstractC4422qa0 qa0, ActionMode.Callback callback, Uy1 uy1, WindowAndroid windowAndroid, C1595a3 a3Var, Q31 q31, Q31 q312, D00 d00, M2 m2, AbstractC1511Yt0 yt0) {
        C1570Zs0 zs0 = new C1570Zs0();
        this.P = zs0;
        this.F = (AbstractView$OnClickListenerC5272va0) view;
        this.L = uy1;
        this.H = m2;
        m2.a(this);
        this.M = view2;
        this.O = this.F.findViewById(R.id.url_bar);
        C1570Zs0 zs02 = new C1570Zs0();
        View$OnKeyListenerC0001Aa0 aa0 = new View$OnKeyListenerC0001Aa0(this.F, qa0, zs02, pq0, WF0.a(), yt0, LocaleManager.getInstance(), zs0);
        this.N = aa0;
        Oq1 oq1 = new Oq1((UrlBarApi26) this.O, uy1, callback, this.Q.b(new C3396ka0(aa0)), this.N, windowAndroid.u0());
        this.I = oq1;
        this.f10497J = new C0859Oc(this.F, this, this, oq1, m2, q31, a3Var, q312, qa0);
        R11 r11 = new R11(g(), (StatusView) this.F.findViewById(R.id.location_bar_status), this.I, d00, q31, qa0);
        this.K = r11;
        View$OnKeyListenerC0001Aa0 aa02 = this.N;
        Oq1 oq12 = this.I;
        C0859Oc oc = this.f10497J;
        aa02.N = oq12;
        aa02.L = oc;
        aa02.K = r11;
        this.O.setOnKeyListener(aa02);
        Oq1 oq13 = this.I;
        oq13.H.L.add(this.f10497J);
        Oq1 oq14 = this.I;
        oq14.H.F.m(Wq1.i, this.Q.b(new C3567la0(this)));
        this.F.getContext().registerComponentCallbacks(this.N);
        AbstractView$OnClickListenerC5272va0 va0 = this.F;
        va0.M.b(this.f10497J);
        AbstractView$OnClickListenerC5272va0 va02 = this.F;
        va02.M.b(this.I);
        this.F.f(this.f10497J, this.I, this.K, qa0, uy1, windowAndroid, this.N.C(), zs02);
        if (view instanceof LocationBarPhone) {
            this.G = new C4080oa0((LocationBarPhone) view, this.K);
        } else if (view instanceof LocationBarTablet) {
            this.G = new C4251pa0((LocationBarTablet) view);
        }
    }

    @Override // defpackage.AbstractC3225ja0
    public Sv1 C() {
        return this.N.C();
    }

    @Override // defpackage.AbstractC3225ja0
    public void F() {
        this.I.G.selectAll();
    }

    @Override // defpackage.AbstractC3225ja0
    public void H() {
        this.N.F.i();
    }

    @Override // defpackage.AbstractC3225ja0
    public void L() {
        if (this.R) {
            Objects.requireNonNull(this.f10497J);
            N.MjJ0r9e$();
        }
    }

    @Override // defpackage.AbstractC3225ja0
    public AbstractC1834bO Q() {
        return this.N;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.H.b(this);
        this.H = null;
        AbstractC3738ma0 ma0 = this.G;
        if (ma0 != null) {
            ma0.destroy();
            this.G = null;
        }
        this.O.setOnKeyListener(null);
        this.O = null;
        Oq1 oq1 = this.I;
        Tq1 tq1 = oq1.H;
        tq1.L.clear();
        tq1.M.clear();
        tq1.G = new Sq1();
        oq1.H = null;
        oq1.G.removeCallbacks(oq1.K);
        oq1.G.removeCallbacks(oq1.L);
        UrlBarApi26 urlBarApi26 = oq1.G;
        urlBarApi26.d0 = false;
        urlBarApi26.setFocusable(false);
        urlBarApi26.setFocusableInTouchMode(false);
        urlBarApi26.Q = null;
        urlBarApi26.setOnFocusChangeListener(null);
        urlBarApi26.T = null;
        urlBarApi26.R = null;
        urlBarApi26.S = null;
        oq1.G = null;
        this.I = null;
        this.F.getContext().unregisterComponentCallbacks(this.N);
        AbstractView$OnClickListenerC5272va0 va0 = this.F;
        va0.M.c(this.f10497J);
        C0859Oc oc = this.f10497J;
        C4305ps0 ps0 = oc.G;
        MZ mz = ps0.e;
        if (mz != null) {
            mz.b();
        }
        ps0.e = null;
        oc.G = null;
        C2379ed edVar = oc.H;
        if (edVar.P != null) {
            edVar.r(false);
            AutocompleteController autocompleteController = edVar.P;
            long j = autocompleteController.f10726a;
            if (j != 0) {
                N.MunC8VXp(j);
            }
            autocompleteController.f10726a = 0;
        }
        C4044oJ oJVar = edVar.g0;
        MZ mz2 = oJVar.f;
        if (mz2 != null) {
            mz2.b();
            oJVar.f = null;
        }
        X60 x60 = oJVar.g;
        if (x60 != null) {
            x60.a();
            oJVar.g = null;
        }
        Y2 y2 = edVar.f0;
        if (y2 != null) {
            y2.destroy();
        }
        M2 m2 = edVar.d0;
        if (m2 != null) {
            m2.b(edVar);
        }
        oc.H = null;
        this.f10497J = null;
        R11 r11 = this.K;
        r11.K.w(r11);
        r11.K = null;
        this.K = null;
        AbstractView$OnClickListenerC5272va0 va02 = this.F;
        va02.M.clear();
        if (va02.K != null) {
            va02.K = null;
        }
        va02.f11487J = null;
        this.F = null;
        this.Q.a();
        this.Q = null;
        View$OnKeyListenerC0001Aa0 aa0 = this.N;
        C0122Ca ca = aa0.I;
        if (ca != null) {
            ca.f7820J.b.c(ca);
            ca.K.e.c(ca);
            ProfileManager.f10754a.c(ca);
            aa0.I = null;
        }
        aa0.K = null;
        aa0.L = null;
        aa0.N = null;
        aa0.P = null;
        aa0.G = null;
        aa0.H.w(aa0);
        aa0.T.clear();
        TemplateUrlService templateUrlService = (TemplateUrlService) aa0.U.get();
        if (templateUrlService != null) {
            templateUrlService.b.c(aa0);
        }
        this.N = null;
    }

    public boolean f() {
        return this.N.F.U;
    }

    public boolean g() {
        return DeviceFormFactor.a(this.F.getContext());
    }

    public void h(String str) {
        this.I.k(Pq1.c(str), 0, 1);
        this.N.F.t();
    }

    public void i(boolean z) {
        Tq1 tq1 = this.I.H;
        tq1.F.j(Wq1.b, z);
        if (z) {
            tq1.F.j(Wq1.f, tq1.H);
        }
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        this.P.a(AbstractC0444Hf1.a());
        View$OnKeyListenerC0001Aa0 aa0 = this.N;
        aa0.W = true;
        ((TemplateUrlService) aa0.U.get()).j(new RunnableC5612xa0(aa0));
        aa0.M = new C3450ks0();
        Context context = aa0.F.getContext();
        C0122Ca ca = new C0122Ca(context, YM.f9268a, (TemplateUrlService) aa0.U.get(), C5259vU.b(context), aa0, NU0.f8549a);
        aa0.I = ca;
        aa0.f7678J.a(ca);
        aa0.F.h();
        aa0.t((Profile) ((C1078Rq0) aa0.O).H);
        for (Runnable runnable : aa0.T) {
            aa0.F.post(runnable);
        }
        aa0.T.clear();
        C2379ed edVar = this.f10497J.H;
        edVar.O = true;
        edVar.R = N.M09VlOh_("OmniboxAdaptiveSuggestionsCount");
        for (Runnable runnable2 : edVar.K) {
            edVar.L.post(runnable2);
        }
        edVar.K.clear();
        C4044oJ oJVar = edVar.g0;
        Objects.requireNonNull(oJVar);
        oJVar.j = N.M09VlOh_("OmniboxAdaptiveSuggestionsCount");
        Objects.requireNonNull(oJVar.c);
        for (int i = 0; i < oJVar.f10541a.size(); i++) {
            ((B31) oJVar.f10541a.get(i)).i();
        }
        R11 r11 = this.K;
        r11.G.e();
        r11.G.F.m(A21.e, r11);
        this.R = true;
    }

    @Override // defpackage.AbstractC3225ja0
    public View w() {
        AbstractView$OnClickListenerC5272va0 va0 = this.F;
        Objects.requireNonNull(va0);
        return va0;
    }

    @Override // defpackage.AbstractC3225ja0
    public void x() {
        this.N.x();
    }

    @Override // defpackage.AbstractC3225ja0
    public View y() {
        return this.F.O.F.K;
    }
}
