package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import com.oculus.browser.R;
import java.util.ArrayList;

/* renamed from: s2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4676s2 implements AbstractC2057cj0 {
    public Context F;
    public Context G;
    public C4616ri0 H;
    public LayoutInflater I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC1886bj0 f11246J;
    public int K = R.layout.f36300_resource_name_obfuscated_RES_2131623939;
    public int L = R.layout.f36290_resource_name_obfuscated_RES_2131623938;
    public AbstractC2398ej0 M;
    public C4164p2 N;
    public Drawable O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public int S;
    public int T;
    public int U;
    public boolean V;
    public int W;
    public final SparseBooleanArray X = new SparseBooleanArray();
    public C4335q2 Y;
    public C3480l2 Z;
    public RunnableC3822n2 a0;
    public C3651m2 b0;
    public final C4505r2 c0 = new C4505r2(this);

    public C4676s2(Context context) {
        this.F = context;
        this.I = LayoutInflater.from(context);
    }

    @Override // defpackage.AbstractC2057cj0
    public void a(C4616ri0 ri0, boolean z) {
        b();
        AbstractC1886bj0 bj0 = this.f11246J;
        if (bj0 != null) {
            bj0.a(ri0, z);
        }
    }

    public boolean b() {
        return f() | l();
    }

    @Override // defpackage.AbstractC2057cj0
    public /* bridge */ /* synthetic */ boolean c(C4616ri0 ri0, C0817Ni0 ni0) {
        return false;
    }

    public View d(C0817Ni0 ni0, View view, ViewGroup viewGroup) {
        AbstractC2228dj0 dj0;
        View actionView = ni0.getActionView();
        int i = 0;
        if (actionView == null || ni0.f()) {
            if (view instanceof AbstractC2228dj0) {
                dj0 = (AbstractC2228dj0) view;
            } else {
                dj0 = (AbstractC2228dj0) this.I.inflate(this.L, viewGroup, false);
            }
            dj0.e(ni0, 0);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) dj0;
            actionMenuItemView.L = (ActionMenuView) this.M;
            if (this.b0 == null) {
                this.b0 = new C3651m2(this);
            }
            actionMenuItemView.N = this.b0;
            actionView = (View) dj0;
        }
        if (ni0.C) {
            i = 8;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.k(layoutParams));
        }
        return actionView;
    }

    @Override // defpackage.AbstractC2057cj0
    public void e(AbstractC1886bj0 bj0) {
        this.f11246J = bj0;
    }

    public boolean f() {
        AbstractC2398ej0 ej0;
        RunnableC3822n2 n2Var = this.a0;
        if (n2Var == null || (ej0 = this.M) == null) {
            C4335q2 q2Var = this.Y;
            if (q2Var == null) {
                return false;
            }
            if (q2Var.b()) {
                q2Var.j.dismiss();
            }
            return true;
        }
        ((View) ej0).removeCallbacks(n2Var);
        this.a0 = null;
        return true;
    }

    @Override // defpackage.AbstractC2057cj0
    public void g(Context context, C4616ri0 ri0) {
        this.G = context;
        LayoutInflater.from(context);
        this.H = ri0;
        Resources resources = context.getResources();
        if (!this.R) {
            this.Q = true;
        }
        int i = 2;
        this.S = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600 || ((i2 > 960 && i3 > 720) || (i2 > 720 && i3 > 960))) {
            i = 5;
        } else if (i2 >= 500 || ((i2 > 640 && i3 > 480) || (i2 > 480 && i3 > 640))) {
            i = 4;
        } else if (i2 >= 360) {
            i = 3;
        }
        this.U = i;
        int i4 = this.S;
        if (this.Q) {
            if (this.N == null) {
                C4164p2 p2Var = new C4164p2(this, this.F);
                this.N = p2Var;
                if (this.P) {
                    p2Var.setImageDrawable(this.O);
                    this.O = null;
                    this.P = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.N.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i4 -= this.N.getMeasuredWidth();
        } else {
            this.N = null;
        }
        this.T = i4;
        this.W = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // defpackage.AbstractC2057cj0
    public void h(boolean z) {
        AbstractC2398ej0 ej0;
        int i;
        boolean z2;
        ViewGroup viewGroup = (ViewGroup) this.M;
        ArrayList arrayList = null;
        boolean z3 = false;
        if (viewGroup != null) {
            C4616ri0 ri0 = this.H;
            if (ri0 != null) {
                ri0.i();
                ArrayList l = this.H.l();
                int size = l.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    C0817Ni0 ni0 = (C0817Ni0) l.get(i2);
                    if (ni0.g()) {
                        View childAt = viewGroup.getChildAt(i);
                        C0817Ni0 d = childAt instanceof AbstractC2228dj0 ? ((AbstractC2228dj0) childAt).d() : null;
                        View d2 = d(ni0, childAt, viewGroup);
                        if (ni0 != d) {
                            d2.setPressed(false);
                            d2.jumpDrawablesToCurrentState();
                        }
                        if (d2 != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) d2.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(d2);
                            }
                            ((ViewGroup) this.M).addView(d2, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.N) {
                    z2 = false;
                } else {
                    viewGroup.removeViewAt(i);
                    z2 = true;
                }
                if (!z2) {
                    i++;
                }
            }
        }
        ((View) this.M).requestLayout();
        C4616ri0 ri02 = this.H;
        if (ri02 != null) {
            ri02.i();
            ArrayList arrayList2 = ri02.j;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                H2 h2 = ((C0817Ni0) arrayList2.get(i3)).A;
            }
        }
        C4616ri0 ri03 = this.H;
        if (ri03 != null) {
            ri03.i();
            arrayList = ri03.k;
        }
        if (this.Q && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z3 = !((C0817Ni0) arrayList.get(0)).C;
            } else if (size3 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.N == null) {
                this.N = new C4164p2(this, this.F);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.N.getParent();
            if (viewGroup3 != this.M) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.N);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.M;
                C4164p2 p2Var = this.N;
                C5186v2 r = actionMenuView.i();
                r.c = true;
                actionMenuView.addView(p2Var, r);
            }
        } else {
            C4164p2 p2Var2 = this.N;
            if (p2Var2 != null && p2Var2.getParent() == (ej0 = this.M)) {
                ((ViewGroup) ej0).removeView(this.N);
            }
        }
        ((ActionMenuView) this.M).a0 = this.Q;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean i(SubMenuC4510r31 r31) {
        boolean z = false;
        if (!r31.hasVisibleItems()) {
            return false;
        }
        SubMenuC4510r31 r312 = r31;
        while (true) {
            C4616ri0 ri0 = r312.z;
            if (ri0 == this.H) {
                break;
            }
            r312 = (SubMenuC4510r31) ri0;
        }
        C0817Ni0 ni0 = r312.A;
        ViewGroup viewGroup = (ViewGroup) this.M;
        View view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof AbstractC2228dj0) && ((AbstractC2228dj0) childAt).d() == ni0) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        int i2 = r31.A.f8568a;
        int size = r31.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            MenuItem item = r31.getItem(i3);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i3++;
        }
        C3480l2 l2Var = new C3480l2(this, this.G, r31, view);
        this.Z = l2Var;
        l2Var.h = z;
        AbstractC1366Wi0 wi0 = l2Var.j;
        if (wi0 != null) {
            wi0.o(z);
        }
        if (this.Z.f()) {
            AbstractC1886bj0 bj0 = this.f11246J;
            if (bj0 != null) {
                bj0.b(r31);
            }
            return true;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean j() {
        int i;
        ArrayList arrayList;
        int i2;
        boolean z;
        C4616ri0 ri0 = this.H;
        if (ri0 != null) {
            arrayList = ri0.l();
            i = arrayList.size();
        } else {
            i = 0;
            arrayList = null;
        }
        int i3 = this.U;
        int i4 = this.T;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.M;
        int i5 = 0;
        boolean z2 = false;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i2 = 2;
            z = true;
            if (i5 >= i) {
                break;
            }
            C0817Ni0 ni0 = (C0817Ni0) arrayList.get(i5);
            int i8 = ni0.y;
            if ((i8 & 2) == 2) {
                i7++;
            } else if ((i8 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (this.V && ni0.C) {
                i3 = 0;
            }
            i5++;
        }
        if (this.Q && (z2 || i6 + i7 > i3)) {
            i3--;
        }
        int i9 = i3 - i7;
        SparseBooleanArray sparseBooleanArray = this.X;
        sparseBooleanArray.clear();
        int i10 = 0;
        int i11 = 0;
        while (i10 < i) {
            C0817Ni0 ni02 = (C0817Ni0) arrayList.get(i10);
            int i12 = ni02.y;
            if ((i12 & 2) == i2 ? z : false) {
                View d = d(ni02, null, viewGroup);
                d.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = d.getMeasuredWidth();
                i4 -= measuredWidth;
                if (i11 == 0) {
                    i11 = measuredWidth;
                }
                int i13 = ni02.b;
                if (i13 != 0) {
                    sparseBooleanArray.put(i13, z);
                }
                ni02.j(z);
            } else if ((i12 & 1) == z ? z : false) {
                int i14 = ni02.b;
                boolean z3 = sparseBooleanArray.get(i14);
                boolean z4 = ((i9 > 0 || z3) && i4 > 0) ? z : false;
                if (z4) {
                    View d2 = d(ni02, null, viewGroup);
                    d2.measure(makeMeasureSpec, makeMeasureSpec);
                    int measuredWidth2 = d2.getMeasuredWidth();
                    i4 -= measuredWidth2;
                    if (i11 == 0) {
                        i11 = measuredWidth2;
                    }
                    z4 &= i4 + i11 > 0;
                }
                if (z4 && i14 != 0) {
                    sparseBooleanArray.put(i14, true);
                } else if (z3) {
                    sparseBooleanArray.put(i14, false);
                    for (int i15 = 0; i15 < i10; i15++) {
                        C0817Ni0 ni03 = (C0817Ni0) arrayList.get(i15);
                        if (ni03.b == i14) {
                            if (ni03.g()) {
                                i9++;
                            }
                            ni03.j(false);
                        }
                    }
                }
                if (z4) {
                    i9--;
                }
                ni02.j(z4);
            } else {
                ni02.j(false);
                i10++;
                i2 = 2;
                z = true;
            }
            i10++;
            i2 = 2;
            z = true;
        }
        return z;
    }

    @Override // defpackage.AbstractC2057cj0
    public /* bridge */ /* synthetic */ boolean k(C4616ri0 ri0, C0817Ni0 ni0) {
        return false;
    }

    public boolean l() {
        C3480l2 l2Var = this.Z;
        if (l2Var == null) {
            return false;
        }
        if (!l2Var.b()) {
            return true;
        }
        l2Var.j.dismiss();
        return true;
    }

    public boolean m() {
        C4335q2 q2Var = this.Y;
        return q2Var != null && q2Var.b();
    }

    public boolean n() {
        C4616ri0 ri0;
        if (!this.Q || m() || (ri0 = this.H) == null || this.M == null || this.a0 != null) {
            return false;
        }
        ri0.i();
        if (ri0.k.isEmpty()) {
            return false;
        }
        RunnableC3822n2 n2Var = new RunnableC3822n2(this, new C4335q2(this, this.G, this.H, this.N, true));
        this.a0 = n2Var;
        ((View) this.M).post(n2Var);
        AbstractC1886bj0 bj0 = this.f11246J;
        if (bj0 == null) {
            return true;
        }
        bj0.b(null);
        return true;
    }
}
