package X;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import com.oculus.alpenglow.R;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0ej  reason: invalid class name and case insensitive filesystem */
public final class C04340ej extends AnonymousClass02W implements AbstractC001803p {
    public static final Interpolator A0R = new AccelerateInterpolator();
    public static final Interpolator A0S = new DecelerateInterpolator();
    public Context A00;
    public C04350ek A01;
    public ActionBarContextView A02;
    public boolean A03;
    public int A04 = 0;
    public View A05;
    public AnonymousClass03C A06;
    public AnonymousClass03D A07;
    public AnonymousClass03K A08;
    public ActionBarContainer A09;
    public ActionBarOverlayLayout A0A;
    public AbstractC002604c A0B;
    public boolean A0C = true;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public Activity A0H;
    public Context A0I;
    public ArrayList<ActionBar.OnMenuVisibilityListener> A0J = new ArrayList<>();
    public ArrayList<WindowDecorActionBar.TabImpl> A0K = new ArrayList<>();
    public boolean A0L;
    public boolean A0M;
    public boolean A0N = true;
    public final AnonymousClass0B1 A0O = new AnonymousClass0N1(this);
    public final AnonymousClass0B1 A0P = new AnonymousClass0N0(this);
    public final AnonymousClass0B2 A0Q = new C04360el(this);

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r2 == false) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(boolean r6) {
        /*
            r5 = this;
            r5.A0L = r6
            r1 = 0
            if (r6 != 0) goto L_0x0033
            X.04c r0 = r5.A0B
            r0.A7t(r1)
            androidx.appcompat.widget.ActionBarContainer r0 = r5.A09
            r0.setTabContainer(r1)
        L_0x000f:
            X.04c r4 = r5.A0B
            int r1 = r4.A49()
            r0 = 2
            r3 = 1
            r2 = 0
            if (r1 != r0) goto L_0x001b
            r2 = 1
        L_0x001b:
            boolean r0 = r5.A0L
            if (r0 != 0) goto L_0x0022
            r0 = 1
            if (r2 != 0) goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            r4.A7o(r0)
            androidx.appcompat.widget.ActionBarOverlayLayout r1 = r5.A0A
            boolean r0 = r5.A0L
            if (r0 != 0) goto L_0x0031
            if (r2 == 0) goto L_0x0031
        L_0x002e:
            r1.A04 = r3
            return
        L_0x0031:
            r3 = 0
            goto L_0x002e
        L_0x0033:
            androidx.appcompat.widget.ActionBarContainer r0 = r5.A09
            r0.setTabContainer(r1)
            X.04c r0 = r5.A0B
            r0.A7t(r1)
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04340ej.A01(boolean):void");
    }

    private void A02(boolean z) {
        View view;
        View view2;
        View view3;
        boolean z2 = this.A03;
        if (this.A0G || !z2) {
            if (!this.A0N) {
                this.A0N = true;
                AnonymousClass03K r0 = this.A08;
                if (r0 != null) {
                    r0.A00();
                }
                this.A09.setVisibility(0);
                if (this.A04 != 0 || (!this.A0F && !z)) {
                    this.A09.setAlpha(1.0f);
                    this.A09.setTranslationY(0.0f);
                    if (this.A0C && (view = this.A05) != null) {
                        view.setTranslationY(0.0f);
                    }
                    this.A0P.A5q(null);
                } else {
                    this.A09.setTranslationY(0.0f);
                    float f = (float) (-this.A09.getHeight());
                    if (z) {
                        int[] iArr = {0, 0};
                        this.A09.getLocationInWindow(iArr);
                        f -= (float) iArr[1];
                    }
                    this.A09.setTranslationY(f);
                    AnonymousClass03K r4 = new AnonymousClass03K();
                    AnonymousClass0B0 A002 = AnonymousClass0Aw.A00(this.A09);
                    A002.A02(0.0f);
                    AnonymousClass0B2 r2 = this.A0Q;
                    View view4 = A002.A00.get();
                    if (view4 != null) {
                        AnonymousClass0Az r1 = null;
                        if (r2 != null) {
                            r1 = new AnonymousClass0Az(A002, r2, view4);
                        }
                        view4.animate().setUpdateListener(r1);
                    }
                    boolean z3 = r4.A03;
                    if (!z3) {
                        r4.A04.add(A002);
                    }
                    if (this.A0C && (view2 = this.A05) != null) {
                        view2.setTranslationY(f);
                        AnonymousClass0B0 A003 = AnonymousClass0Aw.A00(this.A05);
                        A003.A02(0.0f);
                        z3 = r4.A03;
                        if (!z3) {
                            r4.A04.add(A003);
                        }
                    }
                    Interpolator interpolator = A0S;
                    if (!z3) {
                        r4.A01 = interpolator;
                        if (!z3) {
                            r4.A00 = 250;
                        }
                    }
                    AnonymousClass0B1 r02 = this.A0P;
                    if (!z3) {
                        r4.A02 = r02;
                    }
                    this.A08 = r4;
                    r4.A01();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = this.A0A;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.requestApplyInsets();
                }
            }
        } else if (this.A0N) {
            this.A0N = false;
            AnonymousClass03K r03 = this.A08;
            if (r03 != null) {
                r03.A00();
            }
            if (this.A04 != 0 || (!this.A0F && !z)) {
                this.A0O.A5q(null);
                return;
            }
            this.A09.setAlpha(1.0f);
            this.A09.setTransitioning(true);
            AnonymousClass03K r3 = new AnonymousClass03K();
            float f2 = (float) (-this.A09.getHeight());
            if (z) {
                int[] iArr2 = {0, 0};
                this.A09.getLocationInWindow(iArr2);
                f2 -= (float) iArr2[1];
            }
            AnonymousClass0B0 A004 = AnonymousClass0Aw.A00(this.A09);
            A004.A02(f2);
            AnonymousClass0B2 r22 = this.A0Q;
            View view5 = A004.A00.get();
            if (view5 != null) {
                AnonymousClass0Az r12 = null;
                if (r22 != null) {
                    r12 = new AnonymousClass0Az(A004, r22, view5);
                }
                view5.animate().setUpdateListener(r12);
            }
            boolean z4 = r3.A03;
            if (!z4) {
                r3.A04.add(A004);
            }
            if (this.A0C && (view3 = this.A05) != null) {
                AnonymousClass0B0 A005 = AnonymousClass0Aw.A00(view3);
                A005.A02(f2);
                z4 = r3.A03;
                if (!z4) {
                    r3.A04.add(A005);
                }
            }
            Interpolator interpolator2 = A0R;
            if (!z4) {
                r3.A01 = interpolator2;
                if (!z4) {
                    r3.A00 = 250;
                }
            }
            AnonymousClass0B1 r04 = this.A0O;
            if (!z4) {
                r3.A02 = r04;
            }
            this.A08 = r3;
            r3.A01();
        }
    }

    @Override // X.AnonymousClass02W
    public final int A08() {
        return this.A0B.A3S();
    }

    @Override // X.AnonymousClass02W
    public final Context A09() {
        Context context = this.A0I;
        if (context == null) {
            TypedValue typedValue = new TypedValue();
            this.A00.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                context = new ContextThemeWrapper(this.A00, i);
            } else {
                context = this.A00;
            }
            this.A0I = context;
        }
        return context;
    }

    @Override // X.AnonymousClass02W
    public final AnonymousClass03D A0A(AnonymousClass03C r4) {
        C04350ek r0 = this.A01;
        if (r0 != null) {
            r0.A05();
        }
        this.A0A.setHideOnContentScrollEnabled(false);
        ActionBarContextView actionBarContextView = this.A02;
        actionBarContextView.removeAllViews();
        actionBarContextView.A01 = null;
        ((AbstractC001203i) actionBarContextView).A01 = null;
        C04350ek r2 = new C04350ek(this, this.A02.getContext(), r4);
        C04280eZ r1 = r2.A02;
        r1.A09();
        try {
            if (!r2.A00.A62(r2, r1)) {
                return null;
            }
            this.A01 = r2;
            r2.A06();
            this.A02.A05(r2);
            A0I(true);
            this.A02.sendAccessibilityEvent(32);
            return r2;
        } finally {
            r1.A08();
        }
    }

    @Override // X.AnonymousClass02W
    public final void A0B(Configuration configuration) {
        A01(new AnonymousClass03B(this.A00).A00.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs));
    }

    @Override // X.AnonymousClass02W
    public final void A0C(CharSequence charSequence) {
        this.A0B.setWindowTitle(charSequence);
    }

    @Override // X.AnonymousClass02W
    public final void A0D(boolean z) {
        if (z != this.A0M) {
            this.A0M = z;
            ArrayList<ActionBar.OnMenuVisibilityListener> arrayList = this.A0J;
            if (0 < arrayList.size()) {
                arrayList.get(0);
                throw null;
            }
        }
    }

    @Override // X.AnonymousClass02W
    public final void A0E(boolean z) {
        if (!this.A0D) {
            int i = 0;
            if (z) {
                i = 4;
            }
            AbstractC002604c r2 = this.A0B;
            int A3S = r2.A3S();
            this.A0D = true;
            r2.A7s((i & 4) | (-5 & A3S));
        }
    }

    @Override // X.AnonymousClass02W
    public final void A0F(boolean z) {
        AnonymousClass03K r0;
        this.A0F = z;
        if (!z && (r0 = this.A08) != null) {
            r0.A00();
        }
    }

    @Override // X.AnonymousClass02W
    public final boolean A0G() {
        AbstractC002604c r1 = this.A0B;
        if (r1 == null || !r1.A50()) {
            return false;
        }
        r1.A1j();
        return true;
    }

    @Override // X.AnonymousClass02W
    public final boolean A0H(int i, KeyEvent keyEvent) {
        Menu A002;
        int i2;
        C04350ek r0 = this.A01;
        if (r0 == null || (A002 = r0.A00()) == null) {
            return false;
        }
        if (keyEvent != null) {
            i2 = keyEvent.getDeviceId();
        } else {
            i2 = -1;
        }
        int keyboardType = KeyCharacterMap.load(i2).getKeyboardType();
        boolean z = true;
        if (keyboardType == 1) {
            z = false;
        }
        A002.setQwertyMode(z);
        return A002.performShortcut(i, keyEvent, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0I(boolean r9) {
        /*
        // Method dump skipped, instructions count: 141
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04340ej.A0I(boolean):void");
    }

    @Override // X.AbstractC001803p
    public final void A53() {
        if (!this.A03) {
            this.A03 = true;
            A02(true);
        }
    }

    @Override // X.AbstractC001803p
    public final void A61() {
        AnonymousClass03K r0 = this.A08;
        if (r0 != null) {
            r0.A00();
            this.A08 = null;
        }
    }

    @Override // X.AbstractC001803p
    public final void A8R() {
        if (this.A03) {
            this.A03 = false;
            A02(true);
        }
    }

    private void A00(View view) {
        String str;
        String str2;
        String str3;
        AbstractC002604c wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        this.A0A = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        View findViewById = view.findViewById(R.id.action_bar);
        if (findViewById instanceof AbstractC002604c) {
            wrapper = (AbstractC002604c) findViewById;
        } else if (findViewById instanceof Toolbar) {
            wrapper = ((Toolbar) findViewById).getWrapper();
        } else {
            str2 = "Can't make a decor toolbar out of ";
            if (findViewById != null) {
                str3 = findViewById.getClass().getSimpleName();
            } else {
                str3 = "null";
            }
            str = AnonymousClass006.A05(str2, str3);
            throw new IllegalStateException(str);
        }
        this.A0B = wrapper;
        this.A02 = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        this.A09 = actionBarContainer;
        AbstractC002604c r1 = this.A0B;
        if (r1 == null || this.A02 == null || actionBarContainer == null) {
            str2 = getClass().getSimpleName();
            str3 = " can only be used with a compatible window decor layout";
            str = AnonymousClass006.A05(str2, str3);
            throw new IllegalStateException(str);
        }
        Context A3F = r1.A3F();
        this.A00 = A3F;
        if ((r1.A3S() & 4) != 0) {
            this.A0D = true;
        }
        AnonymousClass03B r12 = new AnonymousClass03B(A3F);
        r12.A00.getApplicationInfo();
        A01(r12.A00.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs));
        TypedArray obtainStyledAttributes = this.A00.obtainStyledAttributes(null, AnonymousClass18N.A00, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.A0A;
            if (actionBarOverlayLayout2.A05) {
                this.A0E = true;
                actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
            } else {
                str = "Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll";
                throw new IllegalStateException(str);
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            this.A09.setElevation((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    @Override // X.AbstractC001803p
    public final void A2J(boolean z) {
        this.A0C = z;
    }

    @Override // X.AbstractC001803p
    public final void A6j(int i) {
        this.A04 = i;
    }

    public C04340ej(Activity activity, boolean z) {
        this.A0H = activity;
        View decorView = activity.getWindow().getDecorView();
        A00(decorView);
        if (!z) {
            this.A05 = decorView.findViewById(16908290);
        }
    }

    public C04340ej(Dialog dialog) {
        A00(dialog.getWindow().getDecorView());
    }
}
