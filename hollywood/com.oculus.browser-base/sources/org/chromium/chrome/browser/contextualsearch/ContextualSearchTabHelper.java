package org.chromium.chrome.browser.contextualsearch;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import java.util.Objects;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextualSearchTabHelper extends WK implements AbstractC5481wn0 {
    public final Tab F;
    public final float G;
    public AbstractC0383Gf1 H;
    public WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public ContextualSearchManager f10643J;
    public AbstractC5601xV K;
    public C2868hS0 L;
    public long M;
    public Boolean N;

    public ContextualSearchTabHelper(Tab tab) {
        this.F = tab;
        tab.A(this);
        if (NetworkChangeNotifier.b()) {
            NetworkChangeNotifier.a(this);
        }
        float f = 1.0f;
        Context context = tab.getContext();
        this.G = context != null ? 1.0f / context.getResources().getDisplayMetrics().density : f;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        Z(tab);
        ContextualSearchManager V = V(tab);
        if (V != null) {
            V.P.f();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        Z(tab);
    }

    public final ContextualSearchManager V(Tab tab) {
        Activity activity = (Activity) tab.i().s0().get();
        if (activity instanceof ChromeActivity) {
            return ((ChromeActivity) activity).M0;
        }
        return null;
    }

    public final boolean W(ContextualSearchManager contextualSearchManager) {
        if (AbstractC5686xz.c(1)) {
            return true;
        }
        Objects.requireNonNull((ContextualSearchManager) contextualSearchManager.Q);
        return NetworkChangeNotifier.c();
    }

    public final void X(WebContents webContents) {
        if (webContents != null && this.K != null) {
            GestureListenerManagerImpl.s0(webContents).u0(this.K);
            this.K = null;
            if (this.L != null) {
                SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(webContents);
                C2868hS0 hs0 = this.L;
                if (hs0.f10071a) {
                    hs0.b = ((C2697gS0) hs0.b).f10000a;
                } else {
                    hs0.b = null;
                }
                r.F(hs0.b);
            }
            ContextualSearchManager V = V(this.F);
            if (V != null && !W(V)) {
                V.i(0);
            }
        }
    }

    public final void Y(WebContents webContents) {
        if (webContents != null) {
            X(webContents);
            ContextualSearchManager V = V(this.F);
            boolean z = false;
            if (V != null && !webContents.a() && YQ.a() && !ContextualSearchManager.j() && AbstractC0444Hf1.a().e() && !LocaleManager.getInstance().b() && !SysUtils.isLowEndDevice() && !this.F.p() && W(V)) {
                z = true;
            }
            if (z) {
                ContextualSearchManager V2 = V(this.F);
                if (this.K == null && V2 != null) {
                    C4700sA sAVar = V2.P;
                    Objects.requireNonNull(sAVar);
                    this.K = new C4529rA(sAVar, null);
                    GestureListenerManagerImpl.s0(webContents).r0(this.K);
                    SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(webContents);
                    C2868hS0 hs0 = this.L;
                    C1339Vz vz = V2.M;
                    if (hs0.f10071a) {
                        hs0.b = new C2697gS0(hs0.b, vz, null);
                    } else {
                        hs0.b = vz;
                    }
                    r.F(hs0.b);
                    N.MGn2PSB6(this.M, this, webContents, this.G);
                }
            }
        }
    }

    public final void Z(Tab tab) {
        WebContents l = tab.l();
        if (l != this.I || this.f10643J != V(tab)) {
            this.I = l;
            this.f10643J = V(tab);
            WebContents webContents = this.I;
            if (webContents != null && this.L == null) {
                this.L = new C2868hS0(webContents);
            }
            Y(this.I);
        }
    }

    @Override // defpackage.AbstractC5481wn0
    public void a(int i) {
        Y(this.I);
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            Z(tab);
            return;
        }
        X(this.I);
        this.f10643J = null;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        if (this.M == 0 && tab.l() != null) {
            this.M = N.MjIbQ3pN(this, Profile.a(tab.l()));
        }
        if (this.H == null) {
            this.H = new C5040uA(this);
            TemplateUrlService a2 = AbstractC0444Hf1.a();
            a2.b.b(this.H);
        }
        Z(tab);
    }

    public void onContextualSearchPrefChanged() {
        Y(this.I);
        ContextualSearchManager V = V(this.F);
        if (V != null) {
            boolean z = !ContextualSearchManager.j() && !ContextualSearchManager.k();
            C1796bA bAVar = V.T;
            if (bAVar != null && bAVar.O()) {
                C3675mA r0 = bAVar.r0();
                if (r0.U && r0.Q.O()) {
                    if (z) {
                        boolean z2 = r0.V;
                        r0.V = false;
                        C1616aA aAVar = r0.R;
                        if (z2) {
                            aAVar.f9414a.Y().e(true);
                            aAVar.f9414a.n0(15);
                        }
                        C0059Az az = ((ContextualSearchManager) aAVar.f9414a.I0).N;
                        Profile b = Profile.b();
                        Objects.requireNonNull(az);
                        Um1.a(b).notifyEvent("contextual_search_enabled_opt_in");
                        az.l = true;
                    } else {
                        r0.R.f9414a.z(16, true);
                    }
                    r0.p();
                    C5677xw c = C5677xw.c(r0.Q.D(), 1.0f, 0.0f, 218, null);
                    c.I.add(new C2650gA(r0));
                    c.H.b(new C3333kA(r0));
                    c.start();
                }
            }
        }
    }

    public void onShowUnhandledTapUIIfNeeded(int i, int i2, int i3, int i4) {
        int i5;
        if (this.K != null && V(this.F) != null) {
            C4700sA sAVar = V(this.F).P;
            sAVar.h = false;
            if (sAVar.g == 2 || sAVar.u) {
                sAVar.i = null;
                ContextualSearchManager contextualSearchManager = (ContextualSearchManager) sAVar.b;
                if (!contextualSearchManager.n()) {
                    contextualSearchManager.i(7);
                    return;
                }
                return;
            }
            if (sAVar.p != 0) {
                sAVar.r = (int) ((System.nanoTime() - sAVar.p) / 1000000);
            }
            boolean z = true;
            sAVar.h = true;
            sAVar.g = 1;
            sAVar.k = (float) i;
            sAVar.l = (float) i2;
            sAVar.m = i3;
            sAVar.n = i4;
            ContextualSearchManager contextualSearchManager2 = (ContextualSearchManager) sAVar.b;
            if (!contextualSearchManager2.n()) {
                if (!contextualSearchManager2.R.l() && contextualSearchManager2.R.a()) {
                    int[] iArr = new int[2];
                    contextualSearchManager2.V.getLocationInWindow(iArr);
                    C0059Az az = contextualSearchManager2.N;
                    Profile b = Profile.b();
                    Point point = new Point(i + iArr[0], i2 + ((int) contextualSearchManager2.G.M0().c()) + iArr[1]);
                    CtrSuppression ctrSuppression = new CtrSuppression();
                    if (!N.McXNZl2s(ctrSuppression.f10644a, ctrSuppression)) {
                        i5 = 0;
                    } else {
                        i5 = (int) (N.M36jqK_X(ctrSuppression.f10644a, ctrSuppression) * 100.0f);
                    }
                    if (i5 <= 0) {
                        z = false;
                    }
                    C0729Lz lz = new C0729Lz(contextualSearchManager2);
                    az.j = point;
                    az.i = z;
                    az.k = lz;
                    az.c("IPH_ContextualSearchTappedButShouldLongpress", b, false);
                }
                contextualSearchManager2.S.a(6);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void p(Tab tab) {
        ContextualSearchManager V = V(tab);
        if (V != null) {
            ((ContextualSearchManager) V.P.b).h();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        long j = this.M;
        if (j != 0) {
            N.M4Z1OGVX(j, this);
            this.M = 0;
        }
        if (this.H != null) {
            TemplateUrlService a2 = AbstractC0444Hf1.a();
            a2.b.c(this.H);
        }
        if (NetworkChangeNotifier.b()) {
            NetworkChangeNotifier.j(this);
        }
        X(this.I);
        this.I = null;
        this.f10643J = null;
        this.L = null;
        this.K = null;
    }
}
