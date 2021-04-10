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
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;

/* renamed from: X.0Mm  reason: invalid class name */
public final class AnonymousClass0Mm extends AbstractC04310ee implements AnonymousClass0AC {
    public AnonymousClass0Mo A00;
    public int A01;
    public Drawable A02;
    public C01880Ms A03;
    public RunnableC002003r A04;
    public C01850Mp A05;
    public boolean A06;
    public boolean A07;
    public boolean A08;
    public boolean A09;
    public int A0A;
    public int A0B;
    public AnonymousClass0eN A0C;
    public final AnonymousClass0eL A0D = new AnonymousClass0eL(this);
    public final SparseBooleanArray A0E = new SparseBooleanArray();

    @Override // X.AbstractC04310ee
    public final void A02(C04250eW r2, AbstractC000703c r3) {
        r3.A5G(r2, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) r3;
        actionMenuItemView.A02 = (ActionMenuView) super.A05;
        AnonymousClass0eN r0 = this.A0C;
        if (r0 == null) {
            r0 = new AnonymousClass0eN(this);
            this.A0C = r0;
        }
        actionMenuItemView.A01 = r0;
    }

    @Override // X.AbstractC04310ee
    public final AbstractC000803d A01(ViewGroup viewGroup) {
        AbstractC000803d r0 = super.A05;
        AbstractC000803d A012 = super.A01(viewGroup);
        if (r0 != A012) {
            ((ActionMenuView) A012).setPresenter(this);
        }
        return A012;
    }

    @Override // X.AbstractC04310ee
    public final boolean A03(int i, C04250eW r5) {
        if ((r5.A02 & 32) == 32) {
            return true;
        }
        return false;
    }

    public final boolean A05() {
        AbstractC000803d r0;
        RunnableC002003r r2 = this.A04;
        if (r2 == null || (r0 = super.A05) == null) {
            AnonymousClass0Mo r02 = this.A00;
            if (r02 == null) {
                return false;
            }
            r02.A03();
            return true;
        }
        ((View) r0).removeCallbacks(r2);
        this.A04 = null;
        return true;
    }

    public final boolean A06() {
        AnonymousClass0Mo r0 = this.A00;
        if (r0 == null || !r0.A05()) {
            return false;
        }
        return true;
    }

    public final boolean A07() {
        C04280eZ r1;
        if (!this.A08 || A06() || (r1 = super.A03) == null || super.A05 == null || this.A04 != null) {
            return false;
        }
        r1.A07();
        if (r1.A08.isEmpty()) {
            return false;
        }
        RunnableC002003r r12 = new RunnableC002003r(this, new AnonymousClass0Mo(this, super.A01, super.A03, this.A05));
        this.A04 = r12;
        ((View) super.A05).post(r12);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a7, code lost:
        if (r8 <= 0) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00fa, code lost:
        if (r15 != false) goto L_0x00cb;
     */
    @Override // X.AbstractC000603b, X.AbstractC04310ee
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A2p() {
        /*
        // Method dump skipped, instructions count: 260
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Mm.A2p():boolean");
    }

    public AnonymousClass0Mm(Context context) {
        super(context);
    }

    @Override // X.AbstractC04310ee
    public final View A00(C04250eW r4, View view, ViewGroup viewGroup) {
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

    @Override // X.AbstractC04310ee
    public final boolean A04(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.A05) {
            return false;
        }
        return super.A04(viewGroup, i);
    }

    @Override // X.AbstractC000603b, X.AbstractC04310ee
    public final void A5E(@NonNull Context context, @Nullable C04280eZ r8) {
        super.A5E(context, r8);
        Resources resources = context.getResources();
        AnonymousClass03B r1 = new AnonymousClass03B(context);
        if (!this.A09) {
            this.A08 = true;
        }
        this.A0B = r1.A00.getResources().getDisplayMetrics().widthPixels >> 1;
        this.A01 = r1.A00();
        int i = this.A0B;
        if (this.A08) {
            if (this.A05 == null) {
                C01850Mp r2 = new C01850Mp(this, super.A07);
                this.A05 = r2;
                if (this.A07) {
                    r2.setImageDrawable(this.A02);
                    this.A02 = null;
                    this.A07 = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.A05.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.A05.getMeasuredWidth();
        } else {
            this.A05 = null;
        }
        this.A0A = i;
        resources.getDisplayMetrics();
    }

    @Override // X.AbstractC000603b, X.AbstractC04310ee
    public final void A5x(C04280eZ r2, boolean z) {
        A05();
        C01880Ms r0 = this.A03;
        if (r0 != null) {
            r0.A03();
        }
        super.A5x(r2, z);
    }

    @Override // X.AbstractC000603b, X.AbstractC04310ee
    public final boolean A6e(SubMenuC01890Mu r8) {
        boolean z = false;
        if (r8.hasVisibleItems()) {
            SubMenuC01890Mu r0 = r8;
            while (r0.A00 != super.A03) {
                r0 = (SubMenuC01890Mu) r0.A00;
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
                    if (!(childAt instanceof AbstractC000703c) || ((AbstractC000703c) childAt).getItemData() != item) {
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
                        C01880Ms r2 = new C01880Ms(this, super.A01, r8, childAt);
                        this.A03 = r2;
                        r2.A05 = z;
                        AbstractC04220eS r02 = r2.A03;
                        if (r02 != null) {
                            r02.A08(z);
                        }
                        if (!r2.A05()) {
                            if (r2.A01 == null) {
                                throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                            }
                            C04210eR.A00(r2, 0, 0, false, false);
                        }
                        super.A6e(r8);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // X.AbstractC000603b, X.AbstractC04310ee
    public final void A8k(boolean z) {
        ArrayList<C04250eW> arrayList;
        AbstractC000803d r1;
        int size;
        super.A8k(z);
        ((View) super.A05).requestLayout();
        C04280eZ r0 = super.A03;
        if (r0 != null) {
            r0.A07();
            ArrayList<C04250eW> arrayList2 = r0.A06;
            int size2 = arrayList2.size();
            for (int i = 0; i < size2; i++) {
                AnonymousClass0AE A4b = arrayList2.get(i).A4b();
                if (A4b != null) {
                    A4b.A00 = this;
                }
            }
        }
        C04280eZ r02 = super.A03;
        if (r02 != null) {
            r02.A07();
            arrayList = r02.A08;
        } else {
            arrayList = null;
        }
        if (!this.A08 || arrayList == null || ((size = arrayList.size()) != 1 ? size <= 0 : !(!arrayList.get(0).isActionViewExpanded()))) {
            C01850Mp r03 = this.A05;
            if (r03 != null && r03.getParent() == (r1 = super.A05)) {
                ((ViewGroup) r1).removeView(this.A05);
            }
        } else {
            C01850Mp r12 = this.A05;
            if (r12 == null) {
                r12 = new C01850Mp(this, super.A07);
                this.A05 = r12;
            }
            ViewGroup viewGroup = (ViewGroup) r12.getParent();
            if (viewGroup != super.A05) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.A05);
                }
                C01850Mp r2 = this.A05;
                C04200eJ r13 = new C04200eJ();
                ((C003604n) r13).A01 = 16;
                r13.A04 = true;
                ((ActionMenuView) super.A05).addView(r2, r13);
            }
        }
        ((ActionMenuView) super.A05).A06 = this.A08;
    }
}
