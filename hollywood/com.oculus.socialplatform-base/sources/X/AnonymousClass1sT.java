package X;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import com.oculus.socialplatform.R;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sT  reason: invalid class name */
public abstract class AnonymousClass1sT implements AnonymousClass1t6 {
    public int A00 = R.layout.abc_action_menu_item_layout;
    public Context A01;
    public LayoutInflater A02;
    public C11581sN A03;
    public AbstractC11941tc A04;
    public AnonymousClass1td A05;
    public int A06 = R.layout.abc_action_menu_layout;
    public Context A07;
    public LayoutInflater A08;

    @Override // X.AnonymousClass1t6
    public final boolean A2B(C11581sN r2, C11601sP r3) {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final boolean A2v(C11581sN r2, C11601sP r3) {
        return false;
    }

    public View A00(C11601sP r4, View view, ViewGroup viewGroup) {
        if (!(view instanceof AbstractC11971tg)) {
            view = this.A02.inflate(this.A00, viewGroup, false);
        }
        AbstractC11971tg r5 = (AbstractC11971tg) view;
        C11591sO r2 = (C11591sO) this;
        r5.A5i(r4, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) r5;
        actionMenuItemView.A01 = (ActionMenuView) ((AnonymousClass1sT) r2).A05;
        AnonymousClass1tP r0 = r2.A04;
        if (r0 == null) {
            r0 = new AnonymousClass1tP(r2);
            r2.A04 = r0;
        }
        actionMenuItemView.A00 = r0;
        return (View) r5;
    }

    public AnonymousClass1td A01(ViewGroup viewGroup) {
        if (this.A05 == null) {
            AnonymousClass1td r1 = (AnonymousClass1td) this.A02.inflate(this.A06, viewGroup, false);
            this.A05 = r1;
            r1.A5h(this.A03);
            AAw(true);
        }
        return this.A05;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b0, code lost:
        if (r8 <= 0) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0103, code lost:
        if (r15 != false) goto L_0x00d4;
     */
    @Override // X.AnonymousClass1t6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A3F() {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1sT.A3F():boolean");
    }

    @Override // X.AnonymousClass1t6
    public void A5e(Context context, C11581sN r3) {
        this.A01 = context;
        this.A08 = LayoutInflater.from(context);
        this.A03 = r3;
    }

    @Override // X.AnonymousClass1t6
    public void A6r(C11581sN r2, boolean z) {
        AbstractC11941tc r0 = this.A04;
        if (r0 != null) {
            r0.A6r(r2, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [X.1sN] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass1t6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean A89(X.SubMenuC11621sV r2) {
        /*
            r1 = this;
            X.1tc r0 = r1.A04
            if (r0 == 0) goto L_0x000d
            if (r2 != 0) goto L_0x0008
            X.1sN r2 = r1.A03
        L_0x0008:
            boolean r0 = r0.A7T(r2)
            return r0
        L_0x000d:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1sT.A89(X.1sV):boolean");
    }

    @Override // X.AnonymousClass1t6
    public void AAw(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.A05;
        if (viewGroup != null) {
            C11581sN r0 = this.A03;
            int i = 0;
            if (r0 != null) {
                r0.A07();
                ArrayList<C11601sP> A062 = this.A03.A06();
                int size = A062.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    C11601sP r9 = A062.get(i3);
                    if (!(this instanceof C11591sO) || (r9.A02 & 32) == 32) {
                        View childAt = viewGroup.getChildAt(i2);
                        C11601sP r02 = null;
                        if (childAt instanceof AbstractC11971tg) {
                            r02 = ((AbstractC11971tg) childAt).getItemData();
                        }
                        View A002 = A00(r9, childAt, viewGroup);
                        if (r9 != r02) {
                            A002.setPressed(false);
                            A002.jumpDrawablesToCurrentState();
                        }
                        if (A002 != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) A002.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(A002);
                            }
                            ((ViewGroup) this.A05).addView(A002, i2);
                        }
                        i2++;
                    }
                }
                i = i2;
            }
            while (i < viewGroup.getChildCount()) {
                if (!A02(viewGroup, i)) {
                    i++;
                }
            }
        }
    }

    public AnonymousClass1sT(Context context) {
        this.A07 = context;
        this.A02 = LayoutInflater.from(context);
    }

    public boolean A02(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void A9h(AbstractC11941tc r1) {
        this.A04 = r1;
    }
}
