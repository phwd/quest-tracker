package X;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* renamed from: X.1sS  reason: invalid class name */
public class AnonymousClass1sS implements AnonymousClass1t6 {
    public C11581sN A00;
    public C11601sP A01;
    public final /* synthetic */ Toolbar A02;

    @Override // X.AnonymousClass1t6
    public final boolean A3F() {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final void A6r(C11581sN r1, boolean z) {
    }

    @Override // X.AnonymousClass1t6
    public final boolean A89(SubMenuC11621sV r2) {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final void A9h(AbstractC11941tc r1) {
    }

    public AnonymousClass1sS(Toolbar toolbar) {
        this.A02 = toolbar;
    }

    @Override // X.AnonymousClass1t6
    public final boolean A2B(C11581sN r6, C11601sP r7) {
        Toolbar toolbar = this.A02;
        View view = toolbar.A01;
        if (view instanceof AbstractC12011tl) {
            ((AbstractC12011tl) view).onActionViewCollapsed();
        }
        toolbar.removeView(toolbar.A01);
        toolbar.removeView(toolbar.A02);
        toolbar.A01 = null;
        ArrayList<View> arrayList = toolbar.A0b;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                toolbar.addView(arrayList.get(size));
            } else {
                arrayList.clear();
                this.A01 = null;
                toolbar.requestLayout();
                r7.A0G = false;
                r7.A0B.A0G(false);
                return true;
            }
        }
    }

    @Override // X.AnonymousClass1t6
    public final boolean A2v(C11581sN r6, C11601sP r7) {
        Toolbar toolbar = this.A02;
        toolbar.A0E();
        ViewParent parent = toolbar.A02.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.A02);
            }
            toolbar.addView(toolbar.A02);
        }
        View actionView = r7.getActionView();
        toolbar.A01 = actionView;
        this.A01 = r7;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.A01);
            }
            AnonymousClass1sG r2 = new AnonymousClass1sG();
            ((C05941Dt) r2).A00 = 8388611 | (toolbar.A00 & 112);
            r2.A00 = 2;
            toolbar.A01.setLayoutParams(r2);
            toolbar.addView(toolbar.A01);
        }
        int childCount = toolbar.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            View childAt = toolbar.getChildAt(childCount);
            if (!(((AnonymousClass1sG) childAt.getLayoutParams()).A00 == 2 || childAt == toolbar.A09)) {
                toolbar.removeViewAt(childCount);
                toolbar.A0b.add(childAt);
            }
        }
        toolbar.requestLayout();
        r7.A0G = true;
        r7.A0B.A0G(false);
        View view = toolbar.A01;
        if (view instanceof AbstractC12011tl) {
            ((AbstractC12011tl) view).onActionViewExpanded();
        }
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void A5e(Context context, C11581sN r4) {
        C11601sP r0;
        C11581sN r1 = this.A00;
        if (!(r1 == null || (r0 = this.A01) == null)) {
            r1.A0M(r0);
        }
        this.A00 = r4;
    }

    @Override // X.AnonymousClass1t6
    public final void AAw(boolean z) {
        C11601sP r4 = this.A01;
        if (r4 != null) {
            C11581sN r3 = this.A00;
            if (r3 != null) {
                int size = r3.size();
                for (int i = 0; i < size; i++) {
                    if (r3.getItem(i) == r4) {
                        return;
                    }
                }
            }
            A2B(r3, r4);
        }
    }
}
