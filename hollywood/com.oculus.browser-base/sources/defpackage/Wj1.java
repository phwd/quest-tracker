package defpackage;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: Wj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Wj1 extends FrameLayout implements AbstractC1056Rg1, AbstractC0995Qg1 {
    public Callback F;
    public final C1322Vq0 G = new C1322Vq0();
    public final int[] H = new int[2];
    public final ColorStateList I = AbstractC1300Vg1.b(getContext(), false);

    /* renamed from: J  reason: collision with root package name */
    public Sj1 f9169J;
    public C5476wl1 K;
    public C4456ql1 L = new C4456ql1(getContext(), getResources().getDimensionPixelSize(R.dimen.f26390_resource_name_obfuscated_RES_2131166258), this, false);
    public boolean M;
    public boolean N;
    public long O;
    public boolean P;
    public AbstractC1117Sg1 Q;
    public C5976zi0 R;
    public AbstractView$OnTouchListenerC4526r9 S;
    public Xl1 T;

    public Wj1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        addOnLayoutChangeListener(new Uj1(this));
    }

    public void A() {
    }

    public void B() {
    }

    public void C() {
    }

    public void D(boolean z) {
        this.N = z;
    }

    public void E() {
        Profile profile;
        r();
        C5476wl1 wl1 = this.K;
        if (wl1 != null) {
            AbstractC3535lK0.a("Home");
            if (!((Boolean) wl1.b.get()).booleanValue()) {
                Tab tab = (Tab) wl1.f11566a.get();
                if (tab != null) {
                    String str = (String) wl1.e.get();
                    boolean z = str.startsWith("about:") || str.startsWith("chrome:") || str.startsWith("chrome-native:");
                    AbstractC3100ip1.f10165a.a("Navigation.Home.IsChromeInternal", z);
                    if (!z) {
                        AbstractC3535lK0.a("Navigation.Home.NotChromeInternal");
                    }
                    Tab tab2 = (Tab) wl1.f11566a.get();
                    Profile profile2 = (Profile) wl1.c.get();
                    if (!(tab2 == null || profile2 == null)) {
                        Tm1 a2 = Um1.a((Profile) wl1.c.get());
                        a2.notifyEvent("homepage_button_clicked");
                        if (AbstractC5154ur1.g(str)) {
                            a2.notifyEvent("ntp_homebutton_clicked");
                        }
                    }
                    tab.c(new LoadUrlParams(str, 67108864));
                }
            } else if (N.M09VlOh_("ToolbarIphAndroid") && (profile = (Profile) wl1.c.get()) != null) {
                Um1.a(profile).notifyEvent("homepage_button_clicked");
            }
        }
    }

    public void F(View.OnClickListener onClickListener) {
    }

    public void G(boolean z) {
    }

    public void H(View.OnClickListener onClickListener) {
    }

    public boolean I(boolean z) {
        return false;
    }

    public void J(Runnable runnable) {
    }

    public void K(C3909na0 na0) {
    }

    public void L(View.OnClickListener onClickListener) {
    }

    public void M(View.OnLongClickListener onLongClickListener) {
    }

    public void N(C5880z61 z61) {
    }

    public void O() {
    }

    public void P(boolean z, boolean z2, boolean z3, C5976zi0 zi0) {
    }

    public void Q(boolean z) {
    }

    public boolean R() {
        if (this.N || this.P) {
            return true;
        }
        AbstractView$OnTouchListenerC4526r9 r9Var = this.S;
        if (r9Var != null) {
            C4697s9 s9Var = (C4697s9) r9Var;
            if (s9Var.H || s9Var.F.g()) {
                return true;
            }
        }
        return false;
    }

    public void S(boolean z) {
    }

    public void T(boolean z, boolean z2) {
    }

    public void U() {
    }

    public void V(boolean z) {
    }

    public void W(C0517Ik ik) {
    }

    public void X(boolean z) {
    }

    @Override // defpackage.AbstractC0995Qg1
    public void a(int i, boolean z) {
    }

    @Override // defpackage.AbstractC1056Rg1
    public void c(ColorStateList colorStateList, boolean z) {
    }

    public void d() {
        AbstractC1117Sg1 sg1 = this.Q;
        if (sg1 != null) {
            sg1.K.c(this);
            this.Q.f8908J.c(this);
            this.Q = null;
        }
    }

    public void draw(Canvas canvas) {
        TraceEvent j0 = TraceEvent.j0("ToolbarLayout.draw");
        try {
            super.draw(canvas);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public void e() {
    }

    public View f() {
        Tab d = this.f9169J.d();
        if (d != null) {
            return d.b();
        }
        return null;
    }

    public HomeButton g() {
        return null;
    }

    public abstract AbstractC3225ja0 h();

    public void i(Rect rect) {
        View w = h().w();
        rect.set(w.getPaddingLeft(), w.getPaddingTop(), w.getWidth() - w.getPaddingRight(), w.getHeight() - w.getPaddingBottom());
        AbstractC4656rv1.c(this, h().w(), this.H);
        int[] iArr = this.H;
        rect.offset(iArr[0], iArr[1]);
    }

    public View j() {
        return null;
    }

    public int k() {
        return getResources().getDimensionPixelSize(R.dimen.f25730_resource_name_obfuscated_RES_2131166192);
    }

    public ColorStateList l() {
        AbstractC1117Sg1 sg1 = this.Q;
        return sg1 == null ? this.I : sg1.a();
    }

    public void m(boolean z) {
        this.P = z;
    }

    public void n() {
    }

    public void o(Sj1 sj1, C5476wl1 wl1, C5976zi0 zi0) {
        this.f9169J = sj1;
        this.K = wl1;
        this.R = zi0;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new Tj1(this));
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.O == 0) {
            this.O = SystemClock.elapsedRealtime();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f9169J = new Vj1(this);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int actionMasked;
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent j0 = TraceEvent.j0("ToolbarLayout.onLayout");
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public void onMeasure(int i, int i2) {
        TraceEvent j0 = TraceEvent.j0("ToolbarLayout.onMeasure");
        try {
            super.onMeasure(i, i2);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public boolean p() {
        return this.f9169J.a();
    }

    public boolean q() {
        return true;
    }

    public final void r() {
        if (h() != null && h().Q() != null) {
            ((View$OnKeyListenerC0001Aa0) h().Q()).F.n(false, null, 12);
        }
    }

    public void s(boolean z) {
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        Xl1 xl1 = this.T;
        if (xl1 != null) {
            boolean z = i == 0;
            C2067cm1 cm1 = xl1.I;
            cm1.n = z;
            cm1.b();
        }
    }

    public void t() {
    }

    public void u(boolean z) {
    }

    public void v() {
    }

    public void w() {
        this.M = true;
        if (this.L.getParent() != null) {
            this.L.g();
        }
    }

    public void x() {
    }

    public void y(boolean z) {
    }

    public void z() {
    }
}
