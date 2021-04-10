package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: va0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractView$OnClickListenerC5272va0 extends FrameLayout implements View.OnClickListener {
    public ImageButton F;
    public ImageButton G;
    public boolean H;
    public UrlBarApi26 I;

    /* renamed from: J  reason: collision with root package name */
    public Oq1 f11487J;
    public C0859Oc K;
    public AbstractC4422qa0 L;
    public final C1322Vq0 M = new C1322Vq0();
    public final List N = new ArrayList();
    public R11 O;
    public WindowAndroid P;
    public String Q = "";
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public boolean W;
    public boolean a0;
    public float b0;
    public LinearLayout c0;
    public Sv1 d0;
    public C3974nw e0;
    public AbstractC1509Ys0 f0;

    public AbstractView$OnClickListenerC5272va0(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(i, (ViewGroup) this, true);
        this.F = (ImageButton) findViewById(R.id.delete_button);
        this.I = (UrlBarApi26) findViewById(R.id.url_bar);
        this.G = (ImageButton) findViewById(R.id.mic_button);
        this.c0 = (LinearLayout) findViewById(R.id.url_action_container);
    }

    public void a() {
        n(false, null, 12);
        m(this.L.i());
    }

    public void b(boolean z, boolean z2) {
        Oq1 oq1 = this.f11487J;
        if (oq1 != null) {
            oq1.i(z && z2, true);
            q(false);
            x();
        }
    }

    public final void c() {
        String h = this.f11487J.h();
        this.f11487J.g();
        this.K.H.o(h);
    }

    public final int d() {
        if (this.T) {
            return AbstractC2934hr.a(getResources(), this.L.a());
        }
        return this.L.c();
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
    }

    public void e(boolean z) {
        if (z) {
            this.W = false;
        }
        Iterator it = this.M.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1740ar1) uq0.next()).e(z);
            } else {
                return;
            }
        }
    }

    public void f(C0859Oc oc, Oq1 oq1, R11 r11, AbstractC4422qa0 qa0, Uy1 uy1, WindowAndroid windowAndroid, Sv1 sv1, AbstractC1509Ys0 ys0) {
        this.K = oc;
        this.f11487J = oq1;
        this.O = r11;
        this.P = windowAndroid;
        this.L = qa0;
        this.d0 = sv1;
        this.f0 = ys0;
        ys0.g(new C4932ta0(this));
        t();
        x();
    }

    public final void g() {
        s();
    }

    public void h() {
        this.S = true;
        u();
        this.F.setOnClickListener(this);
        this.G.setOnClickListener(this);
        for (Runnable runnable : this.N) {
            post(runnable);
        }
        this.N.clear();
        i();
        v();
    }

    public void i() {
        s();
        int i = 1;
        boolean z = !AbstractC1270Uv.g(d());
        this.F.setImageTintList(AbstractC2934hr.c(getContext(), !z));
        if (this.f11487J.H.d(z) && !this.I.hasFocus()) {
            m(this.L.i());
        }
        R11 r11 = this.O;
        C5698y21 y21 = r11.G;
        if (y21.G != z) {
            y21.G = z;
            y21.d();
        }
        r11.r();
        C0859Oc oc = this.K;
        if (oc != null) {
            boolean a2 = this.L.a();
            C2379ed edVar = oc.H;
            if (a2) {
                i = 2;
            } else if (z) {
                i = 0;
            }
            C4215pJ pJVar = edVar.h0;
            if (pJVar.c != i) {
                pJVar.c = i;
                for (int i2 = 0; i2 < pJVar.d.size(); i2++) {
                    ((C2848hJ) pJVar.d.get(i2)).b.l(AbstractC4851t31.f11318a, i);
                }
            }
            edVar.I.j(AbstractC5701y31.d, a2);
        }
    }

    public void j(boolean z) {
        this.T = z;
        t();
        x();
        i();
        if (this.T) {
            if (this.S) {
                AbstractC3535lK0.a("FocusLocation");
            }
            Pq1 h = this.L.h();
            if (h.f != null) {
                this.f11487J.k(h, 0, 0);
            }
            ((InputMethodManager) this.I.getContext().getSystemService("input_method")).viewClicked(this.I);
        } else {
            this.U = false;
            this.V = false;
            this.W = false;
            if (this.L.r()) {
                m(this.L.i());
            }
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (inputMethodManager.isActive(this.I)) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0, null);
            }
        }
        R11 r11 = this.O;
        boolean z2 = this.T;
        C5698y21 y21 = r11.G;
        if (y21.H != z2) {
            y21.H = z2;
            y21.f();
            y21.e();
            if (!y21.H && true != y21.d0) {
                y21.d0 = true;
                y21.e();
            }
        }
        r11.L = z2;
        r11.t();
        if (!this.W) {
            e(this.T);
        }
        if (this.T && this.L.r() && !this.L.a()) {
            if (!this.S || !AbstractC0444Hf1.a().e()) {
                this.N.add(new RunnableC5102ua0(this));
            } else {
                GeolocationHeader.e();
            }
        }
    }

    public void k(boolean z) {
    }

    public void l(int i) {
        C5698y21 y21 = this.O.G;
        int i2 = (i - y21.S) - y21.T;
        boolean z = i >= y21.U;
        if (z) {
            y21.F.l(A21.m, i2);
        }
        if (z != y21.I) {
            y21.I = z;
            y21.f();
        }
    }

    public void m(String str) {
        if (this.I.hasFocus()) {
            if (this.W && !AbstractC5154ur1.g(str)) {
                n(false, null, 12);
            } else {
                return;
            }
        }
        this.Q = str;
        o(this.L.h(), 1, 0);
        if (!this.L.r()) {
        }
    }

    public void n(boolean z, String str, int i) {
        if (z) {
            if (!this.T) {
                AbstractC3364kK0.g("Android.OmniboxFocusReason", i, 14);
            }
            if (i == 2 || i == 3 || i == 7 || i == 6) {
                this.U = true;
            }
            if (i == 13) {
                this.U = true;
                this.V = true;
            }
            boolean z2 = this.T;
            if (!z2 || !this.W) {
                this.I.requestFocus();
            } else {
                e(z2);
            }
        } else {
            this.I.clearFocus();
        }
        if (str != null) {
            this.f11487J.k(Pq1.c(str), 0, 1);
            c();
        }
    }

    public boolean o(Pq1 pq1, int i, int i2) {
        return this.f11487J.k(pq1, i, i2);
    }

    public void onClick(View view) {
        if (view == this.F) {
            this.f11487J.k(Pq1.c, 2, 0);
            c();
            t();
            AbstractC3535lK0.a("MobileOmniboxDeleteUrl");
        } else if (view == this.G) {
            AbstractC3535lK0.a("MobileOmniboxVoiceSearch");
            this.d0.h(0);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setLayoutTransition(null);
        StatusView statusView = (StatusView) findViewById(R.id.location_bar_status);
        statusView.U = this.e0;
        statusView.K.addOnLayoutChangeListener(new B21(statusView));
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        boolean z = false;
        int i5 = 0;
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.getMarginStart() != i5) {
                    layoutParams.setMarginStart(i5);
                    childAt.setLayoutParams(layoutParams);
                }
                if (childAt == this.I) {
                    break;
                }
                int i7 = layoutParams.width;
                if (i7 == -2) {
                    i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE);
                } else if (i7 == -1) {
                    i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
                } else {
                    i3 = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
                }
                int i8 = layoutParams.height;
                if (i8 == -2) {
                    i4 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE);
                } else if (i8 == -1) {
                    i4 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
                } else {
                    i4 = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
                }
                childAt.measure(i3, i4);
                i5 += childAt.getMeasuredWidth();
            }
        }
        ArrayList arrayList = new ArrayList();
        if (this.c0 != null) {
            for (int i9 = 0; i9 < this.c0.getChildCount(); i9++) {
                View childAt2 = this.c0.getChildAt(i9);
                if (childAt2.getVisibility() != 8) {
                    arrayList.add(childAt2);
                }
            }
        }
        Iterator it = arrayList.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ((View) it.next()).getLayoutParams();
            i10 += marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart() + marginLayoutParams.width;
        }
        LinearLayout linearLayout = this.c0;
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.c0.getLayoutParams();
            i10 += marginLayoutParams2.getMarginEnd() + marginLayoutParams2.getMarginStart();
        }
        if (this.O.F.K.getVisibility() == 0) {
            z = true;
        }
        if (z && hasFocus()) {
            i10 += this.O.G.R;
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.I.getLayoutParams();
        if (layoutParams2.getMarginEnd() != i10) {
            layoutParams2.setMarginEnd(i10);
            this.I.setLayoutParams(layoutParams2);
        }
        super.onMeasure(i, i2);
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            u();
        }
    }

    public void p(float f) {
        this.b0 = f;
    }

    public void q(boolean z) {
        if (this.f11487J != null) {
            this.R = z;
            if (!z) {
                t();
                if (this.U && this.T && C0283Ep.h().d()) {
                    String h = this.f11487J.h();
                    this.I.clearFocus();
                    this.I.requestFocus();
                    if (!TextUtils.isEmpty(h)) {
                        this.f11487J.k(Pq1.c(h), 0, 1);
                        c();
                    }
                }
                Iterator it = this.M.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((AbstractC1740ar1) uq0.next()).d(this.T);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public boolean r() {
        Oq1 oq1 = this.f11487J;
        return (oq1 != null && !TextUtils.isEmpty(oq1.g())) && (this.I.hasFocus() || this.R);
    }

    public final void s() {
        C0122Ca ca = (C0122Ca) this.f0.get();
        if (ca != null) {
            this.G.setImageTintList(ca.b(d(), getContext()));
            this.G.setImageDrawable(AbstractC5544x8.a(ca.H, ca.P ? R.drawable.f29800_resource_name_obfuscated_RES_2131231020 : R.drawable.f28490_resource_name_obfuscated_RES_2131230889));
        }
    }

    public void t() {
        this.F.setVisibility(r() ? 0 : 8);
    }

    public void u() {
        Sv1 sv1 = this.d0;
        this.a0 = sv1 != null && sv1.b();
        t();
    }

    public void v() {
        boolean z = true;
        boolean z2 = !r();
        int i = 0;
        if (!this.a0 || !z2 || (!this.I.hasFocus() && !this.R && this.b0 <= 0.0f && !this.H)) {
            z = false;
        }
        ImageButton imageButton = this.G;
        if (!z) {
            i = 8;
        }
        imageButton.setVisibility(i);
    }

    public void w(boolean z, boolean z2, String str) {
        R11 r11 = this.O;
        C5698y21 y21 = r11.G;
        y21.N = true;
        y21.O = z2;
        y21.f0 = str;
        y21.e();
        r11.F.e();
    }

    public void x() {
        this.O.G.F.j(A21.f7651a, this.T);
    }
}
