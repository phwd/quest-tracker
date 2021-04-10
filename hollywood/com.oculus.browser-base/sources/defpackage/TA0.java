package defpackage;

import J.N;
import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.payments.ui.PaymentRequestHeader;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* renamed from: TA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TA0 implements HF, View.OnClickListener, AbstractView$OnClickListenerC5891zA0, AbstractC0850Ny0 {
    public static final /* synthetic */ int F = 0;
    public final PA0 G = new PA0(new HA0(this));
    public final Context H;
    public final NA0 I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f8943J;
    public final C5894zB0 K;
    public final Profile L;
    public final IF M;
    public final View$OnClickListenerC3876nK N;
    public final View$OnClickListenerC3876nK O;
    public final ViewGroup P;
    public final Callback Q = new IA0(this);
    public final TU0 R;
    public final int S;
    public FadingEdgeScrollView T;
    public LinearLayout U;
    public TextView V;
    public ViewGroup W;
    public Button X;
    public Button Y;
    public View Z;
    public View a0;
    public C5211vA0 b0;
    public C5721yA0 c0;
    public C5721yA0 d0;
    public C5721yA0 e0;
    public C5721yA0 f0;
    public List g0;
    public BA0 h0;
    public boolean i0;
    public boolean j0;
    public boolean k0;
    public boolean l0;
    public boolean m0;
    public boolean n0;
    public boolean o0;
    public C5084uR0 p0;
    public C5084uR0 q0;
    public C5084uR0 r0;
    public C5084uR0 s0;
    public Animator t0;
    public C5763yR u0;

    public TA0(Activity activity, NA0 na0, boolean z, boolean z2, String str, String str2, int i, TU0 tu0, C5894zB0 zb0, Profile profile) {
        this.H = activity;
        this.I = na0;
        this.f8943J = z2;
        this.S = activity.getResources().getDimensionPixelSize(R.dimen.f24020_resource_name_obfuscated_RES_2131166021);
        this.L = profile;
        this.R = tu0;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.f40480_resource_name_obfuscated_RES_2131624357, (ViewGroup) null);
        this.P = viewGroup;
        this.a0 = viewGroup.findViewById(R.id.payment_request_spinny);
        this.m0 = true;
        ((TextView) viewGroup.findViewById(R.id.message)).setText(R.string.f58660_resource_name_obfuscated_RES_2131953183);
        PaymentRequestHeader paymentRequestHeader = (PaymentRequestHeader) viewGroup.findViewById(R.id.header);
        ((TextView) paymentRequestHeader.findViewById(R.id.page_title)).setText(str);
        TextView textView = (TextView) paymentRequestHeader.findViewById(R.id.hostname);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        boolean z3 = !AbstractC1270Uv.g(paymentRequestHeader.F);
        C3956nq nqVar = new C3956nq(profile);
        AbstractC0229Ds0.a(spannableStringBuilder, paymentRequestHeader.G.getResources(), nqVar, i, false, z3, true);
        nqVar.a();
        textView.setText(spannableStringBuilder);
        if (str2.startsWith("https://")) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(C0636Ki1.b(paymentRequestHeader.G, R.drawable.f34340_resource_name_obfuscated_RES_2131231474, R.color.f11190_resource_name_obfuscated_RES_2131099809), (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setPaddingRelative(0, 0, 0, 0);
        }
        View findViewById = viewGroup.findViewById(R.id.close_button);
        this.Z = findViewById;
        findViewById.setOnClickListener(this);
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.bottom_bar);
        this.W = viewGroup2;
        Button button = (Button) viewGroup2.findViewById(R.id.button_primary);
        this.Y = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.W.findViewById(R.id.button_secondary);
        this.X = button2;
        button2.setOnClickListener(this);
        this.g0 = new ArrayList();
        this.T = (FadingEdgeScrollView) viewGroup.findViewById(R.id.option_container);
        this.U = (LinearLayout) viewGroup.findViewById(R.id.payment_container_layout);
        this.V = (TextView) viewGroup.findViewById(R.id.retry_error);
        this.b0 = new C5211vA0(activity, activity.getString(R.string.f58720_resource_name_obfuscated_RES_2131953189), this, activity.getString(R.string.f58920_resource_name_obfuscated_RES_2131953209));
        this.c0 = new C5721yA0(activity, activity.getString(tu0.f8960a), this);
        this.d0 = new C5721yA0(activity, activity.getString(tu0.b), this);
        this.e0 = new C5721yA0(activity, activity.getString(R.string.f58520_resource_name_obfuscated_RES_2131953169), this);
        C5721yA0 ya0 = new C5721yA0(activity, activity.getString(R.string.f58670_resource_name_obfuscated_RES_2131953184), this);
        this.f0 = ya0;
        this.c0.j0 = false;
        C5721yA0 ya02 = this.d0;
        ya02.k0 = true;
        ya02.a0 = false;
        ya0.a0 = z;
        boolean M1X7xdZV = N.M1X7xdZV("WebPaymentsMethodSectionOrderV2");
        this.U.addView(this.b0, new LinearLayout.LayoutParams(-1, -2));
        if (M1X7xdZV) {
            this.g0.add(new AA0(this.U, -1));
            this.U.addView(this.f0, new LinearLayout.LayoutParams(-1, -2));
        }
        AA0 aa0 = new AA0(this.U, -1);
        this.g0.add(aa0);
        this.U.addView(this.c0, new LinearLayout.LayoutParams(-1, -2));
        AB0 ab0 = (AB0) na0;
        if (!ab0.q()) {
            this.c0.setVisibility(8);
            aa0.setVisibility(8);
        }
        if (!M1X7xdZV) {
            this.g0.add(new AA0(this.U, -1));
            this.U.addView(this.f0, new LinearLayout.LayoutParams(-1, -2));
        }
        AA0 aa02 = new AA0(this.U, -1);
        this.g0.add(aa02);
        this.U.addView(this.e0, new LinearLayout.LayoutParams(-1, -2));
        if (!ab0.p()) {
            this.e0.setVisibility(8);
            aa02.setVisibility(8);
        }
        viewGroup.addOnLayoutChangeListener(new QA0(this, null));
        this.Y.setEnabled(false);
        View$OnClickListenerC3876nK nKVar = new View$OnClickListenerC3876nK(activity, null, profile);
        this.N = nKVar;
        IF.b(nKVar.getWindow());
        View$OnClickListenerC3876nK nKVar2 = new View$OnClickListenerC3876nK(activity, null, profile);
        this.O = nKVar2;
        IF.b(nKVar2.getWindow());
        WindowManager.LayoutParams attributes = nKVar2.getWindow().getAttributes();
        attributes.flags |= 8192;
        nKVar2.getWindow().setAttributes(attributes);
        this.M = new IF(activity, this);
        this.K = zb0;
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
        IF r02 = this.M;
        r02.f8211a.getWindow().setAttributes(r02.f8211a.getWindow().getAttributes());
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
    }

    public final void f(boolean z) {
        if (this.m0 != z) {
            this.m0 = z;
            if (z) {
                this.T.setVisibility(8);
                this.W.setVisibility(8);
                this.Z.setVisibility(8);
                this.a0.setVisibility(0);
                ((FrameLayout.LayoutParams) this.P.getLayoutParams()).height = -2;
                this.P.requestLayout();
                return;
            }
            this.T.setVisibility(0);
            this.W.setVisibility(0);
            this.Z.setVisibility(0);
            this.a0.setVisibility(8);
            if (this.i0) {
                ((FrameLayout.LayoutParams) this.P.getLayoutParams()).height = -1;
                this.P.requestLayout();
            }
        }
    }

    public final void g(boolean z) {
        this.o0 = true;
        IF r1 = this.M;
        Objects.requireNonNull(r1);
        if (z) {
            new FF(r1, true);
            return;
        }
        r1.f8211a.dismiss();
        r1.a();
    }

    public final void h(BA0 ba0) {
        String str;
        CoreAccountInfo b;
        if (!this.i0) {
            this.P.getLayoutParams().height = -1;
            this.P.addOnLayoutChangeListener(new SA0(this, true));
            this.T.b(2, 1);
            this.g0.add(new AA0(this.U, -1));
            LinearLayout linearLayout = this.U;
            if (!this.f8943J) {
                str = this.H.getString(R.string.f58460_resource_name_obfuscated_RES_2131953163);
            } else {
                IdentityManager c = C5949zZ.a().c(this.L);
                String str2 = null;
                if (!(c == null || (b = c.b(1)) == null)) {
                    str2 = b.getEmail();
                }
                if (str2 != null) {
                    str = this.H.getString(R.string.f58470_resource_name_obfuscated_RES_2131953164, str2);
                } else {
                    str = this.H.getString(R.string.f58480_resource_name_obfuscated_RES_2131953165);
                }
            }
            SpannableString a2 = FY0.a(str, new EY0("BEGIN_LINK", "END_LINK", new C4467qp0(this.H.getResources(), new GA0(this))));
            TextViewWithClickableSpans textViewWithClickableSpans = new TextViewWithClickableSpans(this.H);
            textViewWithClickableSpans.setText(a2);
            textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
            textViewWithClickableSpans.setTextAppearance(textViewWithClickableSpans.getContext(), R.style.f72010_resource_name_obfuscated_RES_2132017774);
            int dimensionPixelSize = this.H.getResources().getDimensionPixelSize(R.dimen.f19000_resource_name_obfuscated_RES_2131165519);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            textViewWithClickableSpans.setPaddingRelative(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            linearLayout.addView(textViewWithClickableSpans);
            for (int i = 0; i < this.g0.size(); i++) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ((AA0) this.g0.get(i)).getLayoutParams();
                layoutParams.setMarginStart(0);
                layoutParams.setMarginEnd(0);
            }
            this.U.requestLayout();
            this.X.setText(this.H.getString(R.string.f48470_resource_name_obfuscated_RES_2131952164));
            p();
            this.i0 = true;
        }
        this.h0 = ba0;
        if (ba0 == this.b0) {
            AB0 ab0 = (AB0) this.I;
            ab0.d.post(new RunnableC0884Ol(new KA0(this), ab0.x));
        } else if (ba0 == this.c0) {
            ((AB0) this.I).j(1, new LA0(this, 1));
        } else if (ba0 == this.d0) {
            ((AB0) this.I).j(2, new LA0(this, 2));
        } else if (ba0 == this.e0) {
            ((AB0) this.I).j(3, new LA0(this, 3));
        } else if (ba0 == this.f0) {
            ((AB0) this.I).j(4, new LA0(this, 4));
        } else {
            q();
        }
    }

    public final boolean i() {
        return !this.M.e && this.t0 == null && this.u0 == null && !this.j0 && !this.n0 && !this.o0;
    }

    public boolean j() {
        return i() && this.p0 != null && !this.l0;
    }

    public void k(BA0 ba0) {
        int i = 3;
        if (ba0 == this.c0) {
            i = ((AB0) this.I).l(1, this.Q);
        } else if (ba0 == this.e0) {
            i = ((AB0) this.I).l(3, null);
        } else if (ba0 == this.f0) {
            i = ((AB0) this.I).l(4, null);
        }
        r(ba0, i);
    }

    public void l(String str) {
        TextView textView = this.V;
        if (textView != null) {
            textView.setText(str);
            if (TextUtils.isEmpty(str)) {
                this.V.setVisibility(8);
                return;
            }
            if (this.i0) {
                int dimensionPixelSize = this.H.getResources().getDimensionPixelSize(R.dimen.f19000_resource_name_obfuscated_RES_2131165519);
                TextView textView2 = this.V;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                textView2.setPaddingRelative(0, dimensionPixelSize, 0, dimensionPixelSize);
            } else {
                TextView textView3 = this.V;
                AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                textView3.setPaddingRelative(0, 0, 0, 0);
            }
            this.V.setVisibility(0);
        }
    }

    public void m(UU0 uu0) {
        if (uu0 == null || uu0.f9030a == null) {
            this.b0.setVisibility(8);
            return;
        }
        this.b0.setVisibility(0);
        C5211vA0 va0 = this.b0;
        Context context = va0.W.getContext();
        C5375w80 w80 = uu0.f9030a;
        CharSequence h = va0.h(w80.b, w80.c, true);
        if (va0.T.getText() != null && !TextUtils.equals(va0.T.getText(), h) && va0.T.getVisibility() == 0) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(va0.a0.getAlpha(), 1.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setInterpolator(G30.e);
            alphaAnimation.setFillAfter(true);
            va0.a0.startAnimation(alphaAnimation);
            va0.d0.removeCallbacks(va0.c0);
            va0.d0.postDelayed(va0.c0, 5000);
        }
        va0.f(uu0.f9030a.f11521a, h);
        va0.W.removeAllViews();
        va0.b0.clear();
        if (uu0.a() != null) {
            int width = (((View) va0.W.getParent()).getWidth() * 2) / 3;
            int size = uu0.a().size();
            GridLayout gridLayout = va0.W;
            gridLayout.c0.s(size);
            gridLayout.m();
            gridLayout.requestLayout();
            for (int i = 0; i < size; i++) {
                C5375w80 w802 = (C5375w80) uu0.a().get(i);
                TextView textView = new TextView(context);
                boolean z = w802.d;
                int i2 = R.style.f71710_resource_name_obfuscated_RES_2132017744;
                textView.setTextAppearance(textView.getContext(), z ? R.style.f71710_resource_name_obfuscated_RES_2132017744 : R.style.f71700_resource_name_obfuscated_RES_2132017743);
                textView.setText(w802.f11521a);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setMaxLines(2);
                if (width > 0) {
                    textView.setMaxWidth(width);
                }
                TextView textView2 = new TextView(context);
                if (!w802.d) {
                    i2 = R.style.f71700_resource_name_obfuscated_RES_2132017743;
                }
                textView2.setTextAppearance(textView2.getContext(), i2);
                textView2.setText(va0.h(w802.b, w802.c, false));
                va0.b0.add(textView2);
                BW bw = GridLayout.S;
                IW iw = new IW(GridLayout.r(i, 1, bw), GridLayout.r(0, 1, bw));
                IW iw2 = new IW(GridLayout.r(i, 1, bw), GridLayout.r(1, 1, bw));
                iw2.setMarginStart(context.getResources().getDimensionPixelSize(R.dimen.f23990_resource_name_obfuscated_RES_2131166018));
                va0.W.addView(textView, iw);
                va0.W.addView(textView2, iw2);
            }
        }
    }

    public final void n() {
        PaymentApp paymentApp;
        C5084uR0 ur0;
        C5084uR0 ur02;
        C5084uR0 ur03;
        C5084uR0 ur04;
        boolean z = false;
        boolean z2 = !((AB0) this.I).p() || !((ur04 = this.s0) == null || ur04.d() == null);
        boolean z3 = !((AB0) this.I).q() || !((ur03 = this.q0) == null || ur03.d() == null);
        boolean z4 = !((AB0) this.I).q() || !((ur02 = this.r0) == null || ur02.d() == null);
        Button button = this.Y;
        if (z2 && z3 && z4 && (ur0 = this.p0) != null && ur0.d() != null && !this.l0 && !this.n0 && !this.o0) {
            z = true;
        }
        button.setEnabled(z);
        C5084uR0 ur05 = this.p0;
        if (ur05 == null) {
            paymentApp = null;
        } else {
            paymentApp = (PaymentApp) ur05.d();
        }
        this.Y.setText((paymentApp == null || (paymentApp instanceof C1870be)) ? R.string.f58730_resource_name_obfuscated_RES_2131953190 : R.string.f58530_resource_name_obfuscated_RES_2131953170);
        this.G.a();
    }

    public void o(int i, C5084uR0 ur0) {
        if (i == 1) {
            this.q0 = ur0;
            this.c0.k(ur0);
        } else if (i == 2) {
            this.r0 = ur0;
            this.d0.k(ur0);
            if (((AB0) this.I).q() && !this.r0.f() && this.U.indexOfChild(this.d0) == -1) {
                int indexOfChild = this.U.indexOfChild(this.c0);
                AA0 aa0 = new AA0(this.U, indexOfChild + 1);
                this.g0.add(aa0);
                if (this.i0) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) aa0.getLayoutParams();
                    layoutParams.setMarginStart(0);
                    layoutParams.setMarginEnd(0);
                }
                this.U.addView(this.d0, indexOfChild + 2, new LinearLayout.LayoutParams(-1, -2));
                this.U.requestLayout();
            }
        } else if (i == 3) {
            this.s0 = ur0;
            this.e0.k(ur0);
        } else if (i == 4) {
            this.p0 = ur0;
            this.f0.k(ur0);
        }
        this.n0 = false;
        p();
        n();
    }

    public void onClick(View view) {
        C1997cK cKVar;
        C1997cK cKVar2;
        if (i()) {
            if (view == this.Z) {
                g(true);
            } else if (j()) {
                if (!(view instanceof BA0) || ((BA0) view).b() == 0) {
                    C5211vA0 va0 = this.b0;
                    if (view == va0) {
                        h(va0);
                    } else {
                        C5721yA0 ya0 = this.c0;
                        if (view == ya0) {
                            h(ya0);
                        } else {
                            C5721yA0 ya02 = this.d0;
                            if (view == ya02) {
                                h(ya02);
                            } else {
                                C5721yA0 ya03 = this.e0;
                                if (view == ya03) {
                                    h(ya03);
                                } else {
                                    C5721yA0 ya04 = this.f0;
                                    if (view == ya04) {
                                        h(ya04);
                                    } else if (view == this.Y) {
                                        this.j0 = true;
                                        NA0 na0 = this.I;
                                        C5084uR0 ur0 = this.q0;
                                        if (ur0 == null) {
                                            cKVar = null;
                                        } else {
                                            cKVar = ur0.d();
                                        }
                                        C5084uR0 ur02 = this.r0;
                                        if (ur02 == null) {
                                            cKVar2 = null;
                                        } else {
                                            cKVar2 = ur02.d();
                                        }
                                        if (((C0289Es) ((AB0) na0).m).f(cKVar, cKVar2, (PaymentApp) this.p0.d())) {
                                            f(true);
                                        } else {
                                            C5894zB0 zb0 = this.K;
                                            zb0.b = false;
                                            zb0.b();
                                        }
                                    } else if (view == this.X) {
                                        if (this.i0) {
                                            g(true);
                                        } else {
                                            h(va0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    l(null);
                    n();
                }
            }
        }
    }

    public final void p() {
        boolean z = !this.l0;
        for (int i = 0; i < this.U.getChildCount(); i++) {
            View childAt = this.U.getChildAt(i);
            if (childAt instanceof BA0) {
                BA0 ba0 = (BA0) childAt;
                ba0.H.setEnabled(z);
                if (ba0.b() != 0) {
                    z = false;
                }
            }
        }
    }

    public final void q() {
        this.u0 = new C5763yR(this.U, this.h0, new MA0(this));
        C5211vA0 va0 = this.b0;
        boolean z = true;
        va0.d(this.h0 == va0 ? 5 : 4);
        if (((AB0) this.I).q()) {
            C5721yA0 ya0 = this.c0;
            ya0.i(this.h0 == ya0);
            C5721yA0 ya02 = this.d0;
            ya02.i(this.h0 == ya02);
        }
        if (((AB0) this.I).p()) {
            C5721yA0 ya03 = this.e0;
            ya03.i(this.h0 == ya03);
        }
        C5721yA0 ya04 = this.f0;
        if (this.h0 != ya04) {
            z = false;
        }
        ya04.i(z);
        p();
    }

    public void r(BA0 ba0, int i) {
        boolean z = false;
        boolean z2 = i == 1;
        this.l0 = z2;
        if (i == 2) {
            z = true;
        }
        this.n0 = z;
        if (z2) {
            this.h0 = ba0;
            q();
            ba0.d(6);
        } else {
            h(null);
        }
        n();
    }
}
