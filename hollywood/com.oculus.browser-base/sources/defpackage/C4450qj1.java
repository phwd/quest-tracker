package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import com.oculus.browser.R;

/* renamed from: qj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4450qj1 implements AbstractC2057cj0 {
    public C4616ri0 F;
    public C0817Ni0 G;
    public final /* synthetic */ Toolbar H;

    public C4450qj1(Toolbar toolbar) {
        this.H = toolbar;
    }

    @Override // defpackage.AbstractC2057cj0
    public void a(C4616ri0 ri0, boolean z) {
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean c(C4616ri0 ri0, C0817Ni0 ni0) {
        Toolbar toolbar = this.H;
        if (toolbar.M == null) {
            C4353q8 q8Var = new C4353q8(toolbar.getContext(), null, R.attr.f9060_resource_name_obfuscated_RES_2130969352);
            toolbar.M = q8Var;
            q8Var.setImageDrawable(toolbar.K);
            toolbar.M.setContentDescription(toolbar.L);
            C4620rj1 i = toolbar.generateDefaultLayoutParams();
            i.f9313a = (toolbar.S & 112) | 8388611;
            i.b = 2;
            toolbar.M.setLayoutParams(i);
            toolbar.M.setOnClickListener(new View$OnClickListenerC4279pj1(toolbar));
        }
        ViewParent parent = this.H.M.getParent();
        Toolbar toolbar2 = this.H;
        if (parent != toolbar2) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar2.M);
            }
            Toolbar toolbar3 = this.H;
            toolbar3.addView(toolbar3.M);
        }
        this.H.N = ni0.getActionView();
        this.G = ni0;
        ViewParent parent2 = this.H.N.getParent();
        Toolbar toolbar4 = this.H;
        if (parent2 != toolbar4) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar4.N);
            }
            C4620rj1 i2 = this.H.generateDefaultLayoutParams();
            Toolbar toolbar5 = this.H;
            i2.f9313a = 8388611 | (toolbar5.S & 112);
            i2.b = 2;
            toolbar5.N.setLayoutParams(i2);
            Toolbar toolbar6 = this.H;
            toolbar6.addView(toolbar6.N);
        }
        Toolbar toolbar7 = this.H;
        int childCount = toolbar7.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            View childAt = toolbar7.getChildAt(childCount);
            if (!(((C4620rj1) childAt.getLayoutParams()).b == 2 || childAt == toolbar7.F)) {
                toolbar7.removeViewAt(childCount);
                toolbar7.m0.add(childAt);
            }
        }
        this.H.requestLayout();
        ni0.C = true;
        ni0.n.p(false);
        View view = this.H.N;
        if (view instanceof AbstractC0234Dv) {
            ((AbstractC0234Dv) view).b();
        }
        return true;
    }

    @Override // defpackage.AbstractC2057cj0
    public void g(Context context, C4616ri0 ri0) {
        C0817Ni0 ni0;
        C4616ri0 ri02 = this.F;
        if (!(ri02 == null || (ni0 = this.G) == null)) {
            ri02.d(ni0);
        }
        this.F = ri0;
    }

    @Override // defpackage.AbstractC2057cj0
    public void h(boolean z) {
        if (this.G != null) {
            C4616ri0 ri0 = this.F;
            boolean z2 = false;
            if (ri0 != null) {
                int size = ri0.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (this.F.getItem(i) == this.G) {
                        z2 = true;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (!z2) {
                k(this.F, this.G);
            }
        }
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean i(SubMenuC4510r31 r31) {
        return false;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean j() {
        return false;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean k(C4616ri0 ri0, C0817Ni0 ni0) {
        View view = this.H.N;
        if (view instanceof AbstractC0234Dv) {
            ((AbstractC0234Dv) view).f();
        }
        Toolbar toolbar = this.H;
        toolbar.removeView(toolbar.N);
        Toolbar toolbar2 = this.H;
        toolbar2.removeView(toolbar2.M);
        Toolbar toolbar3 = this.H;
        toolbar3.N = null;
        int size = toolbar3.m0.size();
        while (true) {
            size--;
            if (size >= 0) {
                toolbar3.addView((View) toolbar3.m0.get(size));
            } else {
                toolbar3.m0.clear();
                this.G = null;
                this.H.requestLayout();
                ni0.C = false;
                ni0.n.p(false);
                return true;
            }
        }
    }
}
