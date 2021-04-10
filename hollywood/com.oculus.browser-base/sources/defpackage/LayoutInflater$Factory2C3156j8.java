package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.ContentFrameLayout;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: j8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LayoutInflater$Factory2C3156j8 extends Q7 implements AbstractC4275pi0, LayoutInflater.Factory2 {
    public static final BW0 I = new BW0();

    /* renamed from: J  reason: collision with root package name */
    public static final int[] f10190J = {16842836};
    public static final boolean K = (!"robolectric".equals(Build.FINGERPRINT));
    public static final boolean L = true;
    public AbstractC2302e8 A0;
    public AbstractC2302e8 B0;
    public boolean C0;
    public int D0;
    public final Runnable E0 = new R7(this);
    public boolean F0;
    public Rect G0;
    public Rect H0;
    public U8 I0;
    public final Object M;
    public final Context N;
    public Window O;
    public Window$CallbackC1790b8 P;
    public final M7 Q;
    public AbstractC1772b2 R;
    public MenuInflater S;
    public CharSequence T;
    public CD U;
    public Y7 V;
    public C2986i8 W;
    public AbstractC5696y2 X;
    public ActionBarContextView Y;
    public PopupWindow Z;
    public Runnable a0;
    public Zu1 b0 = null;
    public boolean c0 = true;
    public boolean d0;
    public ViewGroup e0;
    public TextView f0;
    public View g0;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public boolean k0;
    public boolean l0;
    public boolean m0;
    public boolean n0;
    public boolean o0;
    public C2815h8[] p0;
    public C2815h8 q0;
    public boolean r0;
    public boolean s0;
    public boolean t0;
    public boolean u0;
    public boolean v0;
    public int w0 = -100;
    public int x0;
    public boolean y0;
    public boolean z0;

    public LayoutInflater$Factory2C3156j8(Context context, Window window, M7 m7, Object obj) {
        I7 i7;
        this.N = context;
        this.Q = m7;
        this.M = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (!(context instanceof I7)) {
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        }
                        context = ((ContextWrapper) context).getBaseContext();
                    } else {
                        i7 = (I7) context;
                        break;
                    }
                } else {
                    break;
                }
            }
            i7 = null;
            if (i7 != null) {
                this.w0 = ((LayoutInflater$Factory2C3156j8) i7.b0()).w0;
            }
        }
        if (this.w0 == -100) {
            BW0 bw0 = I;
            Integer num = (Integer) bw0.getOrDefault(this.M.getClass().getName(), null);
            if (num != null) {
                this.w0 = num.intValue();
                bw0.remove(this.M.getClass().getName());
            }
        }
        if (window != null) {
            r(window);
        }
        C3840n8.e();
    }

    public final void A() {
        if (this.O == null) {
            Object obj = this.M;
            if (obj instanceof Activity) {
                r(((Activity) obj).getWindow());
            }
        }
        if (this.O == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public C2815h8 B(Menu menu) {
        C2815h8[] h8VarArr = this.p0;
        int length = h8VarArr != null ? h8VarArr.length : 0;
        for (int i = 0; i < length; i++) {
            C2815h8 h8Var = h8VarArr[i];
            if (h8Var != null && h8Var.h == menu) {
                return h8Var;
            }
        }
        return null;
    }

    public final AbstractC2302e8 C(Context context) {
        if (this.A0 == null) {
            if (Do1.f7912a == null) {
                Context applicationContext = context.getApplicationContext();
                Do1.f7912a = new Do1(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.A0 = new C2473f8(this, Do1.f7912a);
        }
        return this.A0;
    }

    public C2815h8 D(int i) {
        C2815h8[] h8VarArr = this.p0;
        if (h8VarArr == null || h8VarArr.length <= i) {
            C2815h8[] h8VarArr2 = new C2815h8[(i + 1)];
            if (h8VarArr != null) {
                System.arraycopy(h8VarArr, 0, h8VarArr2, 0, h8VarArr.length);
            }
            this.p0 = h8VarArr2;
            h8VarArr = h8VarArr2;
        }
        C2815h8 h8Var = h8VarArr[i];
        if (h8Var != null) {
            return h8Var;
        }
        C2815h8 h8Var2 = new C2815h8(i);
        h8VarArr[i] = h8Var2;
        return h8Var2;
    }

    public final Window.Callback E() {
        return this.O.getCallback();
    }

    public final void F() {
        z();
        if (this.j0 && this.R == null) {
            Object obj = this.M;
            if (obj instanceof Activity) {
                this.R = new Ty1((Activity) this.M, this.k0);
            } else if (obj instanceof Dialog) {
                this.R = new Ty1((Dialog) this.M);
            }
            AbstractC1772b2 b2Var = this.R;
            if (b2Var != null) {
                b2Var.c(this.F0);
            }
        }
    }

    public final void G(int i) {
        this.D0 = (1 << i) | this.D0;
        if (!this.C0) {
            View decorView = this.O.getDecorView();
            Runnable runnable = this.E0;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            decorView.postOnAnimation(runnable);
            this.C0 = true;
        }
    }

    public int H(Context context, int i) {
        if (i == -100) {
            return -1;
        }
        if (i != -1) {
            if (i != 0) {
                if (!(i == 1 || i == 2)) {
                    if (i == 3) {
                        if (this.B0 == null) {
                            this.B0 = new C1961c8(this, context);
                        }
                        return this.B0.c();
                    }
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
            } else if (((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() == 0) {
                return -1;
            } else {
                return C(context).c();
            }
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0157, code lost:
        if (r15 != null) goto L_0x0159;
     */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x015e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void I(defpackage.C2815h8 r14, android.view.KeyEvent r15) {
        /*
        // Method dump skipped, instructions count: 473
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflater$Factory2C3156j8.I(h8, android.view.KeyEvent):void");
    }

    public final boolean J(C2815h8 h8Var, int i, KeyEvent keyEvent, int i2) {
        C4616ri0 ri0;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((h8Var.k || K(h8Var, keyEvent)) && (ri0 = h8Var.h) != null) {
            z = ri0.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == 0 && this.U == null) {
            u(h8Var, true);
        }
        return z;
    }

    public final boolean K(C2815h8 h8Var, KeyEvent keyEvent) {
        CD cd;
        CD cd2;
        CD cd3;
        Resources.Theme theme;
        CD cd4;
        if (this.v0) {
            return false;
        }
        if (h8Var.k) {
            return true;
        }
        C2815h8 h8Var2 = this.q0;
        if (!(h8Var2 == null || h8Var2 == h8Var)) {
            u(h8Var2, false);
        }
        Window.Callback E = E();
        if (E != null) {
            h8Var.g = E.onCreatePanelView(h8Var.f10049a);
        }
        int i = h8Var.f10049a;
        boolean z = i == 0 || i == 108;
        if (z && (cd4 = this.U) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) cd4;
            actionBarOverlayLayout.m();
            actionBarOverlayLayout.K.m = true;
        }
        if (h8Var.g == null) {
            C4616ri0 ri0 = h8Var.h;
            if (ri0 == null || h8Var.p) {
                if (ri0 == null) {
                    Context context = this.N;
                    int i2 = h8Var.f10049a;
                    if ((i2 == 0 || i2 == 108) && this.U != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme2 = context.getTheme();
                        theme2.resolveAttribute(R.attr.f1390_resource_name_obfuscated_RES_2130968585, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            theme = context.getResources().newTheme();
                            theme.setTo(theme2);
                            theme.applyStyle(typedValue.resourceId, true);
                            theme.resolveAttribute(R.attr.f1400_resource_name_obfuscated_RES_2130968586, typedValue, true);
                        } else {
                            theme2.resolveAttribute(R.attr.f1400_resource_name_obfuscated_RES_2130968586, typedValue, true);
                            theme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (theme == null) {
                                theme = context.getResources().newTheme();
                                theme.setTo(theme2);
                            }
                            theme.applyStyle(typedValue.resourceId, true);
                        }
                        if (theme != null) {
                            C3812mz mzVar = new C3812mz(context, 0);
                            mzVar.getTheme().setTo(theme);
                            context = mzVar;
                        }
                    }
                    C4616ri0 ri02 = new C4616ri0(context);
                    ri02.f = this;
                    h8Var.a(ri02);
                    if (h8Var.h == null) {
                        return false;
                    }
                }
                if (z && (cd3 = this.U) != null) {
                    if (this.V == null) {
                        this.V = new Y7(this);
                    }
                    ((ActionBarOverlayLayout) cd3).p(h8Var.h, this.V);
                }
                h8Var.h.y();
                if (!E.onCreatePanelMenu(h8Var.f10049a, h8Var.h)) {
                    h8Var.a(null);
                    if (z && (cd2 = this.U) != null) {
                        ((ActionBarOverlayLayout) cd2).p(null, this.V);
                    }
                    return false;
                }
                h8Var.p = false;
            }
            h8Var.h.y();
            Bundle bundle = h8Var.q;
            if (bundle != null) {
                h8Var.h.u(bundle);
                h8Var.q = null;
            }
            if (!E.onPreparePanel(0, h8Var.g, h8Var.h)) {
                if (z && (cd = this.U) != null) {
                    ((ActionBarOverlayLayout) cd).p(null, this.V);
                }
                h8Var.h.x();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            h8Var.n = z2;
            h8Var.h.setQwertyMode(z2);
            h8Var.h.x();
        }
        h8Var.k = true;
        h8Var.l = false;
        this.q0 = h8Var;
        return true;
    }

    public final boolean L() {
        ViewGroup viewGroup;
        if (this.d0 && (viewGroup = this.e0) != null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (viewGroup.isLaidOut()) {
                return true;
            }
        }
        return false;
    }

    public final void M() {
        if (this.d0) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final int N(C3985nz1 nz1, Rect rect) {
        boolean z;
        int i;
        int i2;
        boolean z2;
        int i3;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i4;
        int d = nz1.d();
        ActionBarContextView actionBarContextView = this.Y;
        int i5 = 8;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.Y.getLayoutParams();
            boolean z3 = true;
            if (this.Y.isShown()) {
                if (this.G0 == null) {
                    this.G0 = new Rect();
                    this.H0 = new Rect();
                }
                Rect rect2 = this.G0;
                Rect rect3 = this.H0;
                rect2.set(nz1.b(), nz1.d(), nz1.c(), nz1.a());
                ViewGroup viewGroup = this.e0;
                Method method = AbstractC4826sv1.f11309a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect2, rect3);
                    } catch (Exception unused) {
                    }
                }
                int i6 = rect2.top;
                int i7 = rect2.left;
                int i8 = rect2.right;
                C3985nz1 g = AbstractC1920bu1.g(this.e0);
                if (g == null) {
                    i = 0;
                } else {
                    i = g.b();
                }
                if (g == null) {
                    i2 = 0;
                } else {
                    i2 = g.c();
                }
                if (marginLayoutParams2.topMargin == i6 && marginLayoutParams2.leftMargin == i7 && marginLayoutParams2.rightMargin == i8) {
                    z2 = false;
                } else {
                    marginLayoutParams2.topMargin = i6;
                    marginLayoutParams2.leftMargin = i7;
                    marginLayoutParams2.rightMargin = i8;
                    z2 = true;
                }
                if (i6 <= 0 || this.g0 != null) {
                    View view = this.g0;
                    if (!(view == null || ((marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams()).height == (i4 = marginLayoutParams2.topMargin) && marginLayoutParams.leftMargin == i && marginLayoutParams.rightMargin == i2))) {
                        marginLayoutParams.height = i4;
                        marginLayoutParams.leftMargin = i;
                        marginLayoutParams.rightMargin = i2;
                        this.g0.setLayoutParams(marginLayoutParams);
                    }
                } else {
                    View view2 = new View(this.N);
                    this.g0 = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams2.topMargin, 51);
                    layoutParams.leftMargin = i;
                    layoutParams.rightMargin = i2;
                    this.e0.addView(this.g0, -1, layoutParams);
                }
                View view3 = this.g0;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    View view4 = this.g0;
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    if ((view4.getWindowSystemUiVisibility() & 8192) == 0) {
                        z3 = false;
                    }
                    if (z3) {
                        Context context = this.N;
                        Object obj = K2.f8337a;
                        i3 = context.getColor(R.color.f9640_resource_name_obfuscated_RES_2131099654);
                    } else {
                        Context context2 = this.N;
                        Object obj2 = K2.f8337a;
                        i3 = context2.getColor(R.color.f9630_resource_name_obfuscated_RES_2131099653);
                    }
                    view4.setBackgroundColor(i3);
                }
                if (!this.l0 && z) {
                    d = 0;
                }
                z3 = z2;
            } else if (marginLayoutParams2.topMargin != 0) {
                marginLayoutParams2.topMargin = 0;
                z = false;
            } else {
                z3 = false;
                z = false;
            }
            if (z3) {
                this.Y.setLayoutParams(marginLayoutParams2);
            }
        }
        View view5 = this.g0;
        if (view5 != null) {
            if (z) {
                i5 = 0;
            }
            view5.setVisibility(i5);
        }
        return d;
    }

    @Override // defpackage.AbstractC4275pi0
    public boolean a(C4616ri0 ri0, MenuItem menuItem) {
        C2815h8 B;
        Window.Callback E = E();
        if (E == null || this.v0 || (B = B(ri0.k())) == null) {
            return false;
        }
        return E.onMenuItemSelected(B.f10049a, menuItem);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        if (r6 == false) goto L_0x00ba;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042  */
    @Override // defpackage.AbstractC4275pi0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(defpackage.C4616ri0 r6) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflater$Factory2C3156j8.b(ri0):void");
    }

    @Override // defpackage.Q7
    public void c(View view, ViewGroup.LayoutParams layoutParams) {
        z();
        ((ViewGroup) this.e0.findViewById(16908290)).addView(view, layoutParams);
        this.P.F.onContentChanged();
    }

    @Override // defpackage.Q7
    public boolean d() {
        return q(true);
    }

    @Override // defpackage.Q7
    public void e() {
        LayoutInflater from = LayoutInflater.from(this.N);
        if (from.getFactory() == null) {
            from.setFactory2(this);
        } else if (!(from.getFactory2() instanceof LayoutInflater$Factory2C3156j8)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // defpackage.Q7
    public void f() {
        F();
        AbstractC1772b2 b2Var = this.R;
        G(0);
    }

    @Override // defpackage.Q7
    public void g(Bundle bundle) {
        this.s0 = true;
        q(false);
        A();
        Object obj = this.M;
        if (obj instanceof Activity) {
            String str = null;
            try {
                Activity activity = (Activity) obj;
                try {
                    str = AbstractC0522Im0.c(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                AbstractC1772b2 b2Var = this.R;
                if (b2Var == null) {
                    this.F0 = true;
                } else {
                    b2Var.c(true);
                }
            }
        }
        synchronized (Q7.H) {
            Q7.j(this);
            Q7.G.add(new WeakReference(this));
        }
        this.t0 = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.Q7
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h() {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflater$Factory2C3156j8.h():void");
    }

    @Override // defpackage.Q7
    public void i() {
        this.u0 = false;
        F();
        AbstractC1772b2 b2Var = this.R;
        if (b2Var != null) {
            Ty1 ty1 = (Ty1) b2Var;
            ty1.x = false;
            C1923bv1 bv1 = ty1.w;
            if (bv1 != null) {
                bv1.a();
            }
        }
    }

    @Override // defpackage.Q7
    public boolean k(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.n0 && i == 108) {
            return false;
        }
        if (this.j0 && i == 1) {
            this.j0 = false;
        }
        if (i == 1) {
            M();
            this.n0 = true;
            return true;
        } else if (i == 2) {
            M();
            this.h0 = true;
            return true;
        } else if (i == 5) {
            M();
            this.i0 = true;
            return true;
        } else if (i == 10) {
            M();
            this.l0 = true;
            return true;
        } else if (i == 108) {
            M();
            this.j0 = true;
            return true;
        } else if (i != 109) {
            return this.O.requestFeature(i);
        } else {
            M();
            this.k0 = true;
            return true;
        }
    }

    @Override // defpackage.Q7
    public void l(int i) {
        z();
        ViewGroup viewGroup = (ViewGroup) this.e0.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.N).inflate(i, viewGroup);
        this.P.F.onContentChanged();
    }

    @Override // defpackage.Q7
    public void m(View view) {
        z();
        ViewGroup viewGroup = (ViewGroup) this.e0.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.P.F.onContentChanged();
    }

    @Override // defpackage.Q7
    public void n(View view, ViewGroup.LayoutParams layoutParams) {
        z();
        ViewGroup viewGroup = (ViewGroup) this.e0.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.P.F.onContentChanged();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:81:0x01d9 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0110, code lost:
        if (r9.equals("ImageButton") == false) goto L_0x013f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View onCreateView(android.view.View r8, java.lang.String r9, android.content.Context r10, android.util.AttributeSet r11) {
        /*
        // Method dump skipped, instructions count: 698
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflater$Factory2C3156j8.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    @Override // defpackage.Q7
    public final void p(CharSequence charSequence) {
        this.T = charSequence;
        CD cd = this.U;
        if (cd != null) {
            ((ActionBarOverlayLayout) cd).r(charSequence);
            return;
        }
        AbstractC1772b2 b2Var = this.R;
        if (b2Var != null) {
            Hl1 hl1 = ((Ty1) b2Var).g;
            if (!hl1.h) {
                hl1.i = charSequence;
                if ((hl1.b & 8) != 0) {
                    hl1.f8179a.I(charSequence);
                    return;
                }
                return;
            }
            return;
        }
        TextView textView = this.f0;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b1 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean q(boolean r11) {
        /*
        // Method dump skipped, instructions count: 444
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflater$Factory2C3156j8.q(boolean):boolean");
    }

    public final void r(Window window) {
        if (this.O == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof Window$CallbackC1790b8)) {
                Window$CallbackC1790b8 b8Var = new Window$CallbackC1790b8(this, callback);
                this.P = b8Var;
                window.setCallback(b8Var);
                C0514Ii1 p = C0514Ii1.p(this.N, null, f10190J);
                Drawable h = p.h(0);
                if (h != null) {
                    window.setBackgroundDrawable(h);
                }
                p.b.recycle();
                this.O = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    public void s(int i, C2815h8 h8Var, Menu menu) {
        if (menu == null && h8Var != null) {
            menu = h8Var.h;
        }
        if ((h8Var == null || h8Var.m) && !this.v0) {
            this.P.F.onPanelClosed(i, menu);
        }
    }

    public void t(C4616ri0 ri0) {
        C4676s2 s2Var;
        if (!this.o0) {
            this.o0 = true;
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.U;
            actionBarOverlayLayout.m();
            ActionMenuView actionMenuView = actionBarOverlayLayout.K.f8179a.F;
            if (!(actionMenuView == null || (s2Var = actionMenuView.b0) == null)) {
                s2Var.b();
            }
            Window.Callback E = E();
            if (E != null && !this.v0) {
                E.onPanelClosed(108, ri0);
            }
            this.o0 = false;
        }
    }

    public void u(C2815h8 h8Var, boolean z) {
        ViewGroup viewGroup;
        CD cd;
        if (!z || h8Var.f10049a != 0 || (cd = this.U) == null || !((ActionBarOverlayLayout) cd).l()) {
            WindowManager windowManager = (WindowManager) this.N.getSystemService("window");
            if (!(windowManager == null || !h8Var.m || (viewGroup = h8Var.e) == null)) {
                windowManager.removeView(viewGroup);
                if (z) {
                    s(h8Var.f10049a, h8Var, null);
                }
            }
            h8Var.k = false;
            h8Var.l = false;
            h8Var.m = false;
            h8Var.f = null;
            h8Var.o = true;
            if (this.q0 == h8Var) {
                this.q0 = null;
                return;
            }
            return;
        }
        t(h8Var.h);
    }

    public final Configuration v(Context context, int i, Configuration configuration) {
        int i2 = i != 1 ? i != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & -49);
        return configuration2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0148, code lost:
        if (r7 == false) goto L_0x014c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean w(android.view.KeyEvent r7) {
        /*
        // Method dump skipped, instructions count: 336
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflater$Factory2C3156j8.w(android.view.KeyEvent):boolean");
    }

    public void x(int i) {
        C2815h8 D = D(i);
        if (D.h != null) {
            Bundle bundle = new Bundle();
            D.h.v(bundle);
            if (bundle.size() > 0) {
                D.q = bundle;
            }
            D.h.y();
            D.h.clear();
        }
        D.p = true;
        D.o = true;
        if ((i == 108 || i == 0) && this.U != null) {
            C2815h8 D2 = D(0);
            D2.k = false;
            K(D2, null);
        }
    }

    public void y() {
        Zu1 zu1 = this.b0;
        if (zu1 != null) {
            zu1.b();
        }
    }

    public final void z() {
        ViewGroup viewGroup;
        CharSequence charSequence;
        Context context;
        if (!this.d0) {
            TypedArray obtainStyledAttributes = this.N.obtainStyledAttributes(FJ0.o);
            if (obtainStyledAttributes.hasValue(115)) {
                if (obtainStyledAttributes.getBoolean(124, false)) {
                    k(1);
                } else if (obtainStyledAttributes.getBoolean(115, false)) {
                    k(108);
                }
                if (obtainStyledAttributes.getBoolean(116, false)) {
                    k(109);
                }
                if (obtainStyledAttributes.getBoolean(117, false)) {
                    k(10);
                }
                this.m0 = obtainStyledAttributes.getBoolean(0, false);
                obtainStyledAttributes.recycle();
                A();
                this.O.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.N);
                if (this.n0) {
                    viewGroup = this.l0 ? (ViewGroup) from.inflate(R.layout.f36490_resource_name_obfuscated_RES_2131623958, (ViewGroup) null) : (ViewGroup) from.inflate(R.layout.f36480_resource_name_obfuscated_RES_2131623957, (ViewGroup) null);
                } else if (this.m0) {
                    viewGroup = (ViewGroup) from.inflate(R.layout.f36390_resource_name_obfuscated_RES_2131623948, (ViewGroup) null);
                    this.k0 = false;
                    this.j0 = false;
                } else if (this.j0) {
                    TypedValue typedValue = new TypedValue();
                    this.N.getTheme().resolveAttribute(R.attr.f1390_resource_name_obfuscated_RES_2130968585, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        context = new C3812mz(this.N, typedValue.resourceId);
                    } else {
                        context = this.N;
                    }
                    viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f36500_resource_name_obfuscated_RES_2131623959, (ViewGroup) null);
                    CD cd = (CD) viewGroup.findViewById(R.id.decor_content_parent);
                    this.U = cd;
                    Window.Callback E = E();
                    ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) cd;
                    actionBarOverlayLayout.m();
                    actionBarOverlayLayout.K.l = E;
                    if (this.k0) {
                        ((ActionBarOverlayLayout) this.U).k(109);
                    }
                    if (this.h0) {
                        ((ActionBarOverlayLayout) this.U).k(2);
                    }
                    if (this.i0) {
                        ((ActionBarOverlayLayout) this.U).k(5);
                    }
                } else {
                    viewGroup = null;
                }
                if (viewGroup != null) {
                    AbstractC1920bu1.o(viewGroup, new S7(this));
                    if (this.U == null) {
                        this.f0 = (TextView) viewGroup.findViewById(R.id.title);
                    }
                    Method method = AbstractC4826sv1.f11309a;
                    try {
                        Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                        if (!method2.isAccessible()) {
                            method2.setAccessible(true);
                        }
                        method2.invoke(viewGroup, new Object[0]);
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    }
                    ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                    ViewGroup viewGroup2 = (ViewGroup) this.O.findViewById(16908290);
                    if (viewGroup2 != null) {
                        while (viewGroup2.getChildCount() > 0) {
                            View childAt = viewGroup2.getChildAt(0);
                            viewGroup2.removeViewAt(0);
                            contentFrameLayout.addView(childAt);
                        }
                        viewGroup2.setId(-1);
                        contentFrameLayout.setId(16908290);
                        if (viewGroup2 instanceof FrameLayout) {
                            ((FrameLayout) viewGroup2).setForeground(null);
                        }
                    }
                    this.O.setContentView(viewGroup);
                    contentFrameLayout.M = new U7(this);
                    this.e0 = viewGroup;
                    Object obj = this.M;
                    if (obj instanceof Activity) {
                        charSequence = ((Activity) obj).getTitle();
                    } else {
                        charSequence = this.T;
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        CD cd2 = this.U;
                        if (cd2 != null) {
                            ((ActionBarOverlayLayout) cd2).r(charSequence);
                        } else {
                            AbstractC1772b2 b2Var = this.R;
                            if (b2Var != null) {
                                Hl1 hl1 = ((Ty1) b2Var).g;
                                if (!hl1.h) {
                                    hl1.i = charSequence;
                                    if ((hl1.b & 8) != 0) {
                                        hl1.f8179a.I(charSequence);
                                    }
                                }
                            } else {
                                TextView textView = this.f0;
                                if (textView != null) {
                                    textView.setText(charSequence);
                                }
                            }
                        }
                    }
                    ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.e0.findViewById(16908290);
                    View decorView = this.O.getDecorView();
                    contentFrameLayout2.L.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    if (contentFrameLayout2.isLaidOut()) {
                        contentFrameLayout2.requestLayout();
                    }
                    TypedArray obtainStyledAttributes2 = this.N.obtainStyledAttributes(FJ0.o);
                    if (contentFrameLayout2.F == null) {
                        contentFrameLayout2.F = new TypedValue();
                    }
                    obtainStyledAttributes2.getValue(122, contentFrameLayout2.F);
                    if (contentFrameLayout2.G == null) {
                        contentFrameLayout2.G = new TypedValue();
                    }
                    obtainStyledAttributes2.getValue(123, contentFrameLayout2.G);
                    if (obtainStyledAttributes2.hasValue(120)) {
                        if (contentFrameLayout2.H == null) {
                            contentFrameLayout2.H = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(120, contentFrameLayout2.H);
                    }
                    if (obtainStyledAttributes2.hasValue(121)) {
                        if (contentFrameLayout2.I == null) {
                            contentFrameLayout2.I = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(121, contentFrameLayout2.I);
                    }
                    if (obtainStyledAttributes2.hasValue(118)) {
                        if (contentFrameLayout2.f9461J == null) {
                            contentFrameLayout2.f9461J = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(118, contentFrameLayout2.f9461J);
                    }
                    if (obtainStyledAttributes2.hasValue(119)) {
                        if (contentFrameLayout2.K == null) {
                            contentFrameLayout2.K = new TypedValue();
                        }
                        obtainStyledAttributes2.getValue(119, contentFrameLayout2.K);
                    }
                    obtainStyledAttributes2.recycle();
                    contentFrameLayout2.requestLayout();
                    this.d0 = true;
                    C2815h8 D = D(0);
                    if (!this.v0 && D.h == null) {
                        G(108);
                        return;
                    }
                    return;
                }
                StringBuilder i = AbstractC2531fV.i("AppCompat does not support the current theme features: { windowActionBar: ");
                i.append(this.j0);
                i.append(", windowActionBarOverlay: ");
                i.append(this.k0);
                i.append(", android:windowIsFloating: ");
                i.append(this.m0);
                i.append(", windowActionModeOverlay: ");
                i.append(this.l0);
                i.append(", windowNoTitle: ");
                i.append(this.n0);
                i.append(" }");
                throw new IllegalArgumentException(i.toString());
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
