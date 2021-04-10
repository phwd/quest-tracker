package X;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* renamed from: X.0dt  reason: invalid class name and case insensitive filesystem */
public class C04050dt implements AbstractC000603b {
    public C04280eZ A00;
    public C04250eW A01;
    public final /* synthetic */ Toolbar A02;

    @Override // X.AbstractC000603b
    public final boolean A2p() {
        return false;
    }

    @Override // X.AbstractC000603b
    public final void A5x(C04280eZ r1, boolean z) {
    }

    @Override // X.AbstractC000603b
    public final boolean A6e(SubMenuC01890Mu r2) {
        return false;
    }

    @Override // X.AbstractC000603b
    public final void A7m(AbstractC000503a r1) {
    }

    public C04050dt(Toolbar toolbar) {
        this.A02 = toolbar;
    }

    @Override // X.AbstractC000603b
    public final boolean A1k(C04280eZ r6, C04250eW r7) {
        Toolbar toolbar = this.A02;
        View view = toolbar.A01;
        if (view instanceof AnonymousClass03E) {
            ((AnonymousClass03E) view).onActionViewCollapsed();
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

    @Override // X.AbstractC000603b
    public final boolean A2V(C04280eZ r6, C04250eW r7) {
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
            C04040ds r2 = new C04040ds();
            ((AnonymousClass02Q) r2).A00 = 8388611 | (toolbar.A00 & 112);
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
            if (!(((C04040ds) childAt.getLayoutParams()).A00 == 2 || childAt == toolbar.A09)) {
                toolbar.removeViewAt(childCount);
                toolbar.A0b.add(childAt);
            }
        }
        toolbar.requestLayout();
        r7.A0G = true;
        r7.A0B.A0G(false);
        View view = toolbar.A01;
        if (view instanceof AnonymousClass03E) {
            ((AnonymousClass03E) view).onActionViewExpanded();
        }
        return true;
    }

    @Override // X.AbstractC000603b
    public final void A5E(Context context, C04280eZ r4) {
        C04250eW r0;
        C04280eZ r1 = this.A00;
        if (!(r1 == null || (r0 = this.A01) == null)) {
            r1.A0M(r0);
        }
        this.A00 = r4;
    }

    @Override // X.AbstractC000603b
    public final void A8k(boolean z) {
        C04250eW r4 = this.A01;
        if (r4 != null) {
            C04280eZ r3 = this.A00;
            if (r3 != null) {
                int size = r3.size();
                for (int i = 0; i < size; i++) {
                    if (r3.getItem(i) == r4) {
                        return;
                    }
                }
            }
            A1k(r3, r4);
        }
    }
}
