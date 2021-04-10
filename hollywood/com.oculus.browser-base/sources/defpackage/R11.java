package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.WebContents;

/* renamed from: R11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R11 extends AbstractC4592ra0 implements View.OnClickListener {
    public final StatusView F;
    public final C5698y21 G;
    public final UH0 H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public Q31 f8804J;
    public AbstractC4422qa0 K;
    public boolean L;

    public R11(boolean z, StatusView statusView, Qq1 qq1, D00 d00, Q31 q31, AbstractC4422qa0 qa0) {
        this.I = z;
        this.F = statusView;
        this.f8804J = q31;
        this.K = qa0;
        UH0 uh0 = new UH0(A21.n);
        this.H = uh0;
        ZH0.a(uh0, statusView, new G21());
        C5698y21 y21 = new C5698y21(uh0, statusView.getResources(), statusView.getContext(), qq1, z, new Q11(this), d00, qa0);
        this.G = y21;
        Resources resources = statusView.getResources();
        y21.S = (resources.getDimensionPixelSize(R.dimen.f20460_resource_name_obfuscated_RES_2131165665) * 2) + resources.getDimensionPixelSize(R.dimen.f20430_resource_name_obfuscated_RES_2131165662) + resources.getDimensionPixelSize(R.dimen.f20470_resource_name_obfuscated_RES_2131165666);
        y21.T = resources.getDimensionPixelSize(R.dimen.f20500_resource_name_obfuscated_RES_2131165669) + resources.getDimensionPixelSize(R.dimen.f20510_resource_name_obfuscated_RES_2131165670);
        y21.U = resources.getDimensionPixelSize(R.dimen.f20480_resource_name_obfuscated_RES_2131165667);
        statusView.b0 = this.K;
        r();
        t();
        this.K.k(this);
    }

    @Override // defpackage.AbstractC4592ra0
    public void m() {
        r();
        t();
    }

    public void onClick(View view) {
        if (!this.L && this.K.r() && this.K.d().l() != null && !this.K.g()) {
            Tab d = this.K.d();
            WebContents l = d.l();
            Activity b = AbstractC5112ud1.b(d);
            PageInfoController.l(b, l, null, 2, new C4985ts(b, l, this.f8804J, new C1742as0(d)), new C0411Gs());
        }
    }

    public int p() {
        StatusView statusView = this.F;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) statusView.getLayoutParams();
        int marginStart = marginLayoutParams.getMarginStart();
        return marginLayoutParams.getMarginEnd() + statusView.getMeasuredWidth() + marginStart;
    }

    public void q(boolean z) {
        this.G.F.j(A21.d, z);
    }

    public final void r() {
        C5698y21 y21 = this.G;
        y21.W = this.K.o(this.I);
        y21.e();
        C5698y21 y212 = this.G;
        y212.X = this.K.m();
        y212.e();
        C5698y21 y213 = this.G;
        y213.Y = this.K.n();
        y213.e();
    }

    public final void t() {
        C5698y21 y21 = this.G;
        int p = this.K.p();
        if (y21.V != p) {
            y21.V = p;
            y21.f();
            y21.e();
        }
        C5698y21 y212 = this.G;
        boolean t = this.K.t();
        if (y212.L != t) {
            y212.L = t;
            y212.f();
            y212.d();
        }
        C5698y21 y213 = this.G;
        boolean v = this.K.v();
        if (y213.f11657J != v) {
            y213.f11657J = v;
            y213.f();
            y213.d();
        }
        C5698y21 y214 = this.G;
        boolean u = this.K.u();
        if (y214.K != u) {
            y214.K = u;
            y214.f();
            y214.d();
        }
    }
}
