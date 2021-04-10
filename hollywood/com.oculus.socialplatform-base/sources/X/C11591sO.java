package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;

/* renamed from: X.1sO  reason: invalid class name and case insensitive filesystem */
public final class C11591sO extends AnonymousClass1sT implements AnonymousClass06w {
    public int A00;
    public int A01;
    public Drawable A02;
    public C11781sp A03;
    public AnonymousClass1tP A04;
    public RunnableC11831su A05;
    public C11871sz A06;
    public AnonymousClass1t5 A07;
    public boolean A08;
    public boolean A09;
    public boolean A0A;
    public boolean A0B;
    public int A0C;
    public final AnonymousClass1t1 A0D = new AnonymousClass1t1(this);
    public final SparseBooleanArray A0E = new SparseBooleanArray();

    @Override // X.AnonymousClass1sT
    public final AnonymousClass1td A01(ViewGroup viewGroup) {
        AnonymousClass1td r0 = super.A05;
        AnonymousClass1td A012 = super.A01(viewGroup);
        if (r0 != A012) {
            ((ActionMenuView) A012).setPresenter(this);
        }
        return A012;
    }

    public final boolean A03() {
        AnonymousClass1td r0;
        RunnableC11831su r2 = this.A05;
        if (r2 == null || (r0 = super.A05) == null) {
            AnonymousClass1t5 r02 = this.A07;
            if (r02 == null) {
                return false;
            }
            r02.A03();
            return true;
        }
        ((View) r0).removeCallbacks(r2);
        this.A05 = null;
        return true;
    }

    public final boolean A04() {
        AnonymousClass1t5 r0 = this.A07;
        if (r0 == null || !r0.A05()) {
            return false;
        }
        return true;
    }

    public final boolean A05() {
        C11581sN r1;
        if (!this.A0A || A04() || (r1 = super.A03) == null || super.A05 == null || this.A05 != null) {
            return false;
        }
        r1.A07();
        if (r1.A08.isEmpty()) {
            return false;
        }
        RunnableC11831su r12 = new RunnableC11831su(this, new AnonymousClass1t5(this, super.A01, super.A03, this.A06));
        this.A05 = r12;
        ((View) super.A05).post(r12);
        return true;
    }

    public C11591sO(Context context) {
        super(context);
    }

    @Override // X.AnonymousClass1sT
    public final View A00(C11601sP r4, View view, ViewGroup viewGroup) {
        View actionView = r4.getActionView();
        if (actionView == null || r4.A00()) {
            actionView = super.A00(r4, view, viewGroup);
        }
        int i = 0;
        if (r4.isActionViewExpanded()) {
            i = 8;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // X.AnonymousClass1sT
    public final boolean A02(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.A06) {
            return false;
        }
        return super.A02(viewGroup, i);
    }

    @Override // X.AnonymousClass1t6, X.AnonymousClass1sT
    public final void A5e(@NonNull Context context, @Nullable C11581sN r8) {
        super.A5e(context, r8);
        Resources resources = context.getResources();
        C11391ru r1 = new C11391ru(context);
        if (!this.A0B) {
            this.A0A = true;
        }
        this.A0C = r1.A00.getResources().getDisplayMetrics().widthPixels >> 1;
        this.A01 = r1.A00();
        int i = this.A0C;
        if (this.A0A) {
            if (this.A06 == null) {
                C11871sz r2 = new C11871sz(this, super.A07);
                this.A06 = r2;
                if (this.A09) {
                    r2.setImageDrawable(this.A02);
                    this.A02 = null;
                    this.A09 = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.A06.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.A06.getMeasuredWidth();
        } else {
            this.A06 = null;
        }
        this.A00 = i;
        resources.getDisplayMetrics();
    }

    @Override // X.AnonymousClass1t6, X.AnonymousClass1sT
    public final void A6r(C11581sN r2, boolean z) {
        A03();
        C11781sp r0 = this.A03;
        if (r0 != null) {
            r0.A03();
        }
        super.A6r(r2, z);
    }

    @Override // X.AnonymousClass1t6, X.AnonymousClass1sT
    public final boolean A89(SubMenuC11621sV r8) {
        boolean z = false;
        if (r8.hasVisibleItems()) {
            SubMenuC11621sV r0 = r8;
            while (r0.A00 != super.A03) {
                r0 = (SubMenuC11621sV) r0.A00;
            }
            MenuItem item = r0.getItem();
            ViewGroup viewGroup = (ViewGroup) super.A05;
            if (viewGroup != null) {
                int childCount = viewGroup.getChildCount();
                int i = 0;
                while (true) {
                    if (i >= childCount) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i);
                    if (!(childAt instanceof AbstractC11971tg) || ((AbstractC11971tg) childAt).getItemData() != item) {
                        i++;
                    } else if (childAt != null) {
                        r8.getItem().getItemId();
                        int size = r8.size();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= size) {
                                break;
                            }
                            MenuItem item2 = r8.getItem(i2);
                            if (item2.isVisible() && item2.getIcon() != null) {
                                z = true;
                                break;
                            }
                            i2++;
                        }
                        C11781sp r2 = new C11781sp(this, super.A01, r8, childAt);
                        this.A03 = r2;
                        r2.A05 = z;
                        AnonymousClass1sY r02 = r2.A03;
                        if (r02 != null) {
                            r02.A02(z);
                        }
                        if (!r2.A05()) {
                            if (r2.A01 == null) {
                                throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                            }
                            AnonymousClass1sZ.A00(r2, 0, 0, false, false);
                        }
                        super.A89(r8);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // X.AnonymousClass1t6, X.AnonymousClass1sT
    public final void AAw(boolean z) {
        ArrayList<C11601sP> arrayList;
        AnonymousClass1td r1;
        int size;
        super.AAw(z);
        ((View) super.A05).requestLayout();
        C11581sN r0 = super.A03;
        if (r0 != null) {
            r0.A07();
            ArrayList<C11601sP> arrayList2 = r0.A06;
            int size2 = arrayList2.size();
            for (int i = 0; i < size2; i++) {
                AbstractC002906y A4z = arrayList2.get(i).A4z();
                if (A4z != null) {
                    A4z.A00 = this;
                }
            }
        }
        C11581sN r02 = super.A03;
        if (r02 != null) {
            r02.A07();
            arrayList = r02.A08;
        } else {
            arrayList = null;
        }
        if (!this.A0A || arrayList == null || ((size = arrayList.size()) != 1 ? size <= 0 : !(!arrayList.get(0).isActionViewExpanded()))) {
            C11871sz r03 = this.A06;
            if (r03 != null && r03.getParent() == (r1 = super.A05)) {
                ((ViewGroup) r1).removeView(this.A06);
            }
        } else {
            C11871sz r12 = this.A06;
            if (r12 == null) {
                r12 = new C11871sz(this, super.A07);
                this.A06 = r12;
            }
            ViewGroup viewGroup = (ViewGroup) r12.getParent();
            if (viewGroup != super.A05) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.A06);
                }
                C11871sz r2 = this.A06;
                C11841sv r13 = new C11841sv();
                ((AnonymousClass1EM) r13).A01 = 16;
                r13.A04 = true;
                ((ActionMenuView) super.A05).addView(r2, r13);
            }
        }
        ((ActionMenuView) super.A05).A06 = this.A0A;
    }
}
