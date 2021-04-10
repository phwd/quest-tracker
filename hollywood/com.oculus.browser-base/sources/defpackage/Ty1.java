package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Ty1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ty1 extends AbstractC1772b2 implements AbstractC2797h2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Interpolator f8999a = new AccelerateInterpolator();
    public static final Interpolator b = new DecelerateInterpolator();
    public final AbstractC2094cv1 A = new Qy1(this);
    public final Ry1 B = new Ry1(this);
    public Context c;
    public Context d;
    public ActionBarOverlayLayout e;
    public ActionBarContainer f;
    public Hl1 g;
    public ActionBarContextView h;
    public View i;
    public boolean j;
    public Sy1 k;
    public AbstractC5696y2 l;
    public AbstractC5526x2 m;
    public boolean n;
    public ArrayList o = new ArrayList();
    public boolean p;
    public int q = 0;
    public boolean r = true;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v = true;
    public C1923bv1 w;
    public boolean x;
    public boolean y;
    public final AbstractC2094cv1 z = new Py1(this);

    public Ty1(Activity activity, boolean z2) {
        new ArrayList();
        View decorView = activity.getWindow().getDecorView();
        e(decorView);
        if (!z2) {
            this.i = decorView.findViewById(16908290);
        }
    }

    @Override // defpackage.AbstractC1772b2
    public void a(boolean z2) {
        if (z2 != this.n) {
            this.n = z2;
            int size = this.o.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((AbstractC1592a2) this.o.get(i2)).a(z2);
            }
        }
    }

    @Override // defpackage.AbstractC1772b2
    public Context b() {
        if (this.d == null) {
            TypedValue typedValue = new TypedValue();
            this.c.getTheme().resolveAttribute(R.attr.f1400_resource_name_obfuscated_RES_2130968586, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.d = new ContextThemeWrapper(this.c, i2);
            } else {
                this.d = this.c;
            }
        }
        return this.d;
    }

    @Override // defpackage.AbstractC1772b2
    public void c(boolean z2) {
        if (!this.j) {
            int i2 = z2 ? 4 : 0;
            Hl1 hl1 = this.g;
            int i3 = hl1.b;
            this.j = true;
            hl1.a((i2 & 4) | (i3 & -5));
        }
    }

    public void d(boolean z2) {
        Zu1 zu1;
        Zu1 zu12;
        if (z2) {
            if (!this.u) {
                this.u = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.e;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.q();
                }
                g(false);
            }
        } else if (this.u) {
            this.u = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.e;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.q();
            }
            g(false);
        }
        ActionBarContainer actionBarContainer = this.f;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (actionBarContainer.isLaidOut()) {
            if (z2) {
                zu1 = this.g.d(4, 100);
                zu12 = this.h.i(0, 200);
            } else {
                zu12 = this.g.d(0, 200);
                zu1 = this.h.i(8, 100);
            }
            C1923bv1 bv1 = new C1923bv1();
            bv1.f9573a.add(zu1);
            View view = (View) zu1.f9382a.get();
            long duration = view != null ? view.animate().getDuration() : 0;
            View view2 = (View) zu12.f9382a.get();
            if (view2 != null) {
                view2.animate().setStartDelay(duration);
            }
            bv1.f9573a.add(zu12);
            bv1.b();
        } else if (z2) {
            this.g.f8179a.setVisibility(4);
            this.h.setVisibility(0);
        } else {
            this.g.f8179a.setVisibility(0);
            this.h.setVisibility(8);
        }
    }

    public final void e(View view) {
        Hl1 hl1;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        this.e = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.h0 = this;
            if (actionBarOverlayLayout.getWindowToken() != null) {
                ((Ty1) actionBarOverlayLayout.h0).q = actionBarOverlayLayout.H;
                int i2 = actionBarOverlayLayout.S;
                if (i2 != 0) {
                    actionBarOverlayLayout.onWindowSystemUiVisibilityChanged(i2);
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    actionBarOverlayLayout.requestApplyInsets();
                }
            }
        }
        View findViewById = view.findViewById(R.id.action_bar);
        if (findViewById instanceof Hl1) {
            hl1 = (Hl1) findViewById;
        } else if (findViewById instanceof Toolbar) {
            Toolbar toolbar = (Toolbar) findViewById;
            if (toolbar.q0 == null) {
                toolbar.q0 = new Hl1(toolbar, true);
            }
            hl1 = toolbar.q0;
        } else {
            StringBuilder i3 = AbstractC2531fV.i("Can't make a decor toolbar out of ");
            i3.append(findViewById != null ? findViewById.getClass().getSimpleName() : "null");
            throw new IllegalStateException(i3.toString());
        }
        this.g = hl1;
        this.h = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        this.f = actionBarContainer;
        Hl1 hl12 = this.g;
        if (hl12 == null || this.h == null || actionBarContainer == null) {
            throw new IllegalStateException(Ty1.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        Context context = hl12.f8179a.getContext();
        this.c = context;
        if ((this.g.b & 4) != 0) {
            this.j = true;
        }
        int i4 = context.getApplicationInfo().targetSdkVersion;
        Objects.requireNonNull(this.g);
        f(context.getResources().getBoolean(R.bool.f9490_resource_name_obfuscated_RES_2131034112));
        TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(null, FJ0.f8010a, R.attr.f1350_resource_name_obfuscated_RES_2130968581, 0);
        if (obtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.e;
            if (actionBarOverlayLayout2.N) {
                this.y = true;
                actionBarOverlayLayout2.o(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.f;
            AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
            actionBarContainer2.setElevation((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    public final void f(boolean z2) {
        this.p = z2;
        if (!z2) {
            this.g.b(null);
            ActionBarContainer actionBarContainer = this.f;
            View view = actionBarContainer.G;
            if (view != null) {
                actionBarContainer.removeView(view);
            }
            actionBarContainer.G = null;
        } else {
            ActionBarContainer actionBarContainer2 = this.f;
            View view2 = actionBarContainer2.G;
            if (view2 != null) {
                actionBarContainer2.removeView(view2);
            }
            actionBarContainer2.G = null;
            this.g.b(null);
        }
        Hl1 hl1 = this.g;
        Objects.requireNonNull(hl1);
        boolean z3 = this.p;
        Toolbar toolbar = hl1.f8179a;
        toolbar.t0 = false;
        toolbar.requestLayout();
        ActionBarOverlayLayout actionBarOverlayLayout = this.e;
        boolean z4 = this.p;
        actionBarOverlayLayout.O = false;
    }

    public final void g(boolean z2) {
        View view;
        View view2;
        View view3;
        if (this.u || (!this.s && !this.t)) {
            if (!this.v) {
                this.v = true;
                C1923bv1 bv1 = this.w;
                if (bv1 != null) {
                    bv1.a();
                }
                this.f.setVisibility(0);
                if (this.q != 0 || (!this.x && !z2)) {
                    this.f.setAlpha(1.0f);
                    this.f.setTranslationY(0.0f);
                    if (this.r && (view2 = this.i) != null) {
                        view2.setTranslationY(0.0f);
                    }
                    this.A.b(null);
                } else {
                    this.f.setTranslationY(0.0f);
                    float f2 = (float) (-this.f.getHeight());
                    if (z2) {
                        int[] iArr = {0, 0};
                        this.f.getLocationInWindow(iArr);
                        f2 -= (float) iArr[1];
                    }
                    this.f.setTranslationY(f2);
                    C1923bv1 bv12 = new C1923bv1();
                    Zu1 a2 = AbstractC1920bu1.a(this.f);
                    a2.g(0.0f);
                    a2.f(this.B);
                    if (!bv12.e) {
                        bv12.f9573a.add(a2);
                    }
                    if (this.r && (view3 = this.i) != null) {
                        view3.setTranslationY(f2);
                        Zu1 a3 = AbstractC1920bu1.a(this.i);
                        a3.g(0.0f);
                        if (!bv12.e) {
                            bv12.f9573a.add(a3);
                        }
                    }
                    Interpolator interpolator = b;
                    boolean z3 = bv12.e;
                    if (!z3) {
                        bv12.c = interpolator;
                    }
                    if (!z3) {
                        bv12.b = 250;
                    }
                    AbstractC2094cv1 cv1 = this.A;
                    if (!z3) {
                        bv12.d = cv1;
                    }
                    this.w = bv12;
                    bv12.b();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = this.e;
                if (actionBarOverlayLayout != null) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    actionBarOverlayLayout.requestApplyInsets();
                }
            }
        } else if (this.v) {
            this.v = false;
            C1923bv1 bv13 = this.w;
            if (bv13 != null) {
                bv13.a();
            }
            if (this.q != 0 || (!this.x && !z2)) {
                this.z.b(null);
                return;
            }
            this.f.setAlpha(1.0f);
            ActionBarContainer actionBarContainer = this.f;
            actionBarContainer.F = true;
            actionBarContainer.setDescendantFocusability(393216);
            C1923bv1 bv14 = new C1923bv1();
            float f3 = (float) (-this.f.getHeight());
            if (z2) {
                int[] iArr2 = {0, 0};
                this.f.getLocationInWindow(iArr2);
                f3 -= (float) iArr2[1];
            }
            Zu1 a4 = AbstractC1920bu1.a(this.f);
            a4.g(f3);
            a4.f(this.B);
            if (!bv14.e) {
                bv14.f9573a.add(a4);
            }
            if (this.r && (view = this.i) != null) {
                Zu1 a5 = AbstractC1920bu1.a(view);
                a5.g(f3);
                if (!bv14.e) {
                    bv14.f9573a.add(a5);
                }
            }
            Interpolator interpolator2 = f8999a;
            boolean z4 = bv14.e;
            if (!z4) {
                bv14.c = interpolator2;
            }
            if (!z4) {
                bv14.b = 250;
            }
            AbstractC2094cv1 cv12 = this.z;
            if (!z4) {
                bv14.d = cv12;
            }
            this.w = bv14;
            bv14.b();
        }
    }

    public Ty1(Dialog dialog) {
        new ArrayList();
        e(dialog.getWindow().getDecorView());
    }
}
