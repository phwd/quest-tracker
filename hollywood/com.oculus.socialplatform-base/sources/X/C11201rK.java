package X;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
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
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1rK  reason: invalid class name and case insensitive filesystem */
public final class C11201rK extends AbstractC11251rX implements AnonymousClass1Es {
    public static final Interpolator A0R = new AccelerateInterpolator();
    public static final Interpolator A0S = new DecelerateInterpolator();
    public int A00 = 0;
    public Context A01;
    public Context A02;
    public View A03;
    public AnonymousClass1rN A04;
    public AbstractC11461s1 A05;
    public AbstractC11301rk A06;
    public AnonymousClass1rT A07;
    public ActionBarContainer A08;
    public ActionBarContextView A09;
    public ActionBarOverlayLayout A0A;
    public AbstractC06001Eq A0B;
    public ArrayList<ActionBar.OnMenuVisibilityListener> A0C = new ArrayList<>();
    public boolean A0D = true;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;
    public boolean A0H;
    public boolean A0I;
    public boolean A0J;
    public Activity A0K;
    public ArrayList<WindowDecorActionBar.TabImpl> A0L = new ArrayList<>();
    public boolean A0M;
    public boolean A0N = true;
    public final AbstractC003107k A0O = new AnonymousClass1rd(this);
    public final AbstractC003107k A0P = new C11471s2(this);
    public final AbstractC003207l A0Q = new C11491s4(this);

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r2 == false) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(X.C11201rK r5, boolean r6) {
        /*
            r5.A0M = r6
            r1 = 0
            if (r6 != 0) goto L_0x0033
            X.1Eq r0 = r5.A0B
            r0.A9s(r1)
            androidx.appcompat.widget.ActionBarContainer r0 = r5.A08
            r0.setTabContainer(r1)
        L_0x000f:
            X.1Eq r4 = r5.A0B
            int r1 = r4.A4Y()
            r0 = 2
            r3 = 1
            r2 = 0
            if (r1 != r0) goto L_0x001b
            r2 = 1
        L_0x001b:
            boolean r0 = r5.A0M
            if (r0 != 0) goto L_0x0022
            r0 = 1
            if (r2 != 0) goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            r4.A9l(r0)
            androidx.appcompat.widget.ActionBarOverlayLayout r1 = r5.A0A
            boolean r0 = r5.A0M
            if (r0 != 0) goto L_0x0031
            if (r2 == 0) goto L_0x0031
        L_0x002e:
            r1.A04 = r3
            return
        L_0x0031:
            r3 = 0
            goto L_0x002e
        L_0x0033:
            androidx.appcompat.widget.ActionBarContainer r0 = r5.A08
            r0.setTabContainer(r1)
            X.1Eq r0 = r5.A0B
            r0.A9s(r1)
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11201rK.A01(X.1rK, boolean):void");
    }

    private void A02(boolean z) {
        View view;
        View view2;
        View view3;
        boolean z2 = this.A0F;
        if (this.A0J || !z2) {
            if (!this.A0N) {
                this.A0N = true;
                AnonymousClass1rT r0 = this.A07;
                if (r0 != null) {
                    r0.A00();
                }
                this.A08.setVisibility(0);
                if (this.A00 != 0 || (!this.A0I && !z)) {
                    this.A08.setAlpha(1.0f);
                    this.A08.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    if (this.A0D && (view = this.A03) != null) {
                        view.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    }
                    this.A0P.A6i(null);
                } else {
                    this.A08.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    float f = (float) (-this.A08.getHeight());
                    if (z) {
                        int[] iArr = {0, 0};
                        this.A08.getLocationInWindow(iArr);
                        f -= (float) iArr[1];
                    }
                    this.A08.setTranslationY(f);
                    AnonymousClass1rT r3 = new AnonymousClass1rT();
                    C003007j A022 = AnonymousClass07f.A02(this.A08);
                    A022.A02(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    AbstractC003207l r4 = this.A0Q;
                    View view4 = A022.A00.get();
                    if (view4 != null) {
                        AnonymousClass07i r1 = null;
                        if (r4 != null) {
                            r1 = new AnonymousClass07i(A022, r4, view4);
                        }
                        view4.animate().setUpdateListener(r1);
                    }
                    if (!r3.A03) {
                        r3.A04.add(A022);
                    }
                    if (this.A0D && (view2 = this.A03) != null) {
                        view2.setTranslationY(f);
                        C003007j A023 = AnonymousClass07f.A02(this.A03);
                        A023.A02(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                        if (!r3.A03) {
                            r3.A04.add(A023);
                        }
                    }
                    Interpolator interpolator = A0S;
                    boolean z3 = r3.A03;
                    if (!z3) {
                        r3.A01 = interpolator;
                        if (!z3) {
                            r3.A00 = 250;
                        }
                    }
                    AbstractC003107k r02 = this.A0P;
                    if (!z3) {
                        r3.A02 = r02;
                    }
                    this.A07 = r3;
                    r3.A01();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = this.A0A;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.requestApplyInsets();
                }
            }
        } else if (this.A0N) {
            this.A0N = false;
            AnonymousClass1rT r03 = this.A07;
            if (r03 != null) {
                r03.A00();
            }
            if (this.A00 != 0 || (!this.A0I && !z)) {
                this.A0O.A6i(null);
                return;
            }
            this.A08.setAlpha(1.0f);
            this.A08.setTransitioning(true);
            AnonymousClass1rT r32 = new AnonymousClass1rT();
            float f2 = (float) (-this.A08.getHeight());
            if (z) {
                int[] iArr2 = {0, 0};
                this.A08.getLocationInWindow(iArr2);
                f2 -= (float) iArr2[1];
            }
            C003007j A024 = AnonymousClass07f.A02(this.A08);
            A024.A02(f2);
            AbstractC003207l r2 = this.A0Q;
            View view5 = A024.A00.get();
            if (view5 != null) {
                AnonymousClass07i r12 = null;
                if (r2 != null) {
                    r12 = new AnonymousClass07i(A024, r2, view5);
                }
                view5.animate().setUpdateListener(r12);
            }
            if (!r32.A03) {
                r32.A04.add(A024);
            }
            if (this.A0D && (view3 = this.A03) != null) {
                C003007j A025 = AnonymousClass07f.A02(view3);
                A025.A02(f2);
                if (!r32.A03) {
                    r32.A04.add(A025);
                }
            }
            Interpolator interpolator2 = A0R;
            boolean z4 = r32.A03;
            if (!z4) {
                r32.A01 = interpolator2;
                if (!z4) {
                    r32.A00 = 250;
                }
            }
            AbstractC003107k r04 = this.A0O;
            if (!z4) {
                r32.A02 = r04;
            }
            this.A07 = r32;
            r32.A01();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(boolean r9) {
        /*
        // Method dump skipped, instructions count: 141
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11201rK.A06(boolean):void");
    }

    @Override // X.AnonymousClass1Es
    public final void A5U() {
        if (!this.A0F) {
            this.A0F = true;
            A02(true);
        }
    }

    @Override // X.AnonymousClass1Es
    public final void A6t() {
        AnonymousClass1rT r0 = this.A07;
        if (r0 != null) {
            r0.A00();
            this.A07 = null;
        }
    }

    @Override // X.AnonymousClass1Es
    public final void AAQ() {
        if (this.A0F) {
            this.A0F = false;
            A02(true);
        }
    }

    private void A00(View view) {
        String str;
        AbstractC06001Eq wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        this.A0A = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        View findViewById = view.findViewById(R.id.action_bar);
        if (findViewById instanceof AbstractC06001Eq) {
            wrapper = (AbstractC06001Eq) findViewById;
        } else if (findViewById instanceof Toolbar) {
            wrapper = ((Toolbar) findViewById).getWrapper();
        } else {
            if (findViewById != null) {
                str = findViewById.getClass().getSimpleName();
            } else {
                str = "null";
            }
            throw new IllegalStateException(AnonymousClass006.A07("Can't make a decor toolbar out of ", str));
        }
        this.A0B = wrapper;
        this.A09 = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        this.A08 = actionBarContainer;
        AbstractC06001Eq r1 = this.A0B;
        if (r1 == null || this.A09 == null || actionBarContainer == null) {
            throw new IllegalStateException(AnonymousClass006.A07(getClass().getSimpleName(), " can only be used with a compatible window decor layout"));
        }
        Context A3d = r1.A3d();
        this.A01 = A3d;
        if ((r1.A3o() & 4) != 0) {
            this.A0E = true;
        }
        C11391ru r12 = new C11391ru(A3d);
        r12.A00.getApplicationInfo();
        A01(this, r12.A00.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs));
        TypedArray obtainStyledAttributes = this.A01.obtainStyledAttributes(null, C11081qa.A00, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.A0A;
            if (actionBarOverlayLayout2.A05) {
                this.A0G = true;
                actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            this.A08.setElevation((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    @Override // X.AnonymousClass1Es
    public final void A2l(boolean z) {
        this.A0D = z;
    }

    @Override // X.AnonymousClass1Es
    public final void A8I(int i) {
        this.A00 = i;
    }

    public C11201rK(Activity activity, boolean z) {
        this.A0K = activity;
        View decorView = activity.getWindow().getDecorView();
        A00(decorView);
        if (!z) {
            this.A03 = decorView.findViewById(16908290);
        }
    }

    public C11201rK(Dialog dialog) {
        A00(dialog.getWindow().getDecorView());
    }
}
