package X;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0ee  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04310ee implements AbstractC000603b {
    public int A00 = R.layout.abc_action_menu_item_layout;
    public Context A01;
    public LayoutInflater A02;
    public C04280eZ A03;
    public AbstractC000503a A04;
    public AbstractC000803d A05;
    public int A06 = R.layout.abc_action_menu_layout;
    public Context A07;
    public LayoutInflater A08;

    public abstract void A02(C04250eW v, AbstractC000703c v2);

    public boolean A03(int i, C04250eW r3) {
        return true;
    }

    @Override // X.AbstractC000603b
    public final boolean A1k(C04280eZ r2, C04250eW r3) {
        return false;
    }

    @Override // X.AbstractC000603b
    public final boolean A2V(C04280eZ r2, C04250eW r3) {
        return false;
    }

    @Override // X.AbstractC000603b
    public boolean A2p() {
        return false;
    }

    public View A00(C04250eW r4, View view, ViewGroup viewGroup) {
        if (!(view instanceof AbstractC000703c)) {
            view = this.A02.inflate(this.A00, viewGroup, false);
        }
        AbstractC000703c r5 = (AbstractC000703c) view;
        A02(r4, r5);
        return (View) r5;
    }

    public AbstractC000803d A01(ViewGroup viewGroup) {
        if (this.A05 == null) {
            AbstractC000803d r1 = (AbstractC000803d) this.A02.inflate(this.A06, viewGroup, false);
            this.A05 = r1;
            r1.A5F(this.A03);
            A8k(true);
        }
        return this.A05;
    }

    @Override // X.AbstractC000603b
    public void A5E(Context context, C04280eZ r3) {
        this.A01 = context;
        this.A08 = LayoutInflater.from(context);
        this.A03 = r3;
    }

    @Override // X.AbstractC000603b
    public void A5x(C04280eZ r2, boolean z) {
        AbstractC000503a r0 = this.A04;
        if (r0 != null) {
            r0.A5x(r2, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [X.0eZ] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AbstractC000603b
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean A6e(X.SubMenuC01890Mu r2) {
        /*
            r1 = this;
            X.03a r0 = r1.A04
            if (r0 == 0) goto L_0x000d
            if (r2 != 0) goto L_0x0008
            X.0eZ r2 = r1.A03
        L_0x0008:
            boolean r0 = r0.A6L(r2)
            return r0
        L_0x000d:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC04310ee.A6e(X.0Mu):boolean");
    }

    @Override // X.AbstractC000603b
    public void A8k(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.A05;
        if (viewGroup != null) {
            C04280eZ r0 = this.A03;
            int i = 0;
            if (r0 != null) {
                r0.A07();
                ArrayList<C04250eW> A062 = this.A03.A06();
                int size = A062.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    C04250eW r3 = A062.get(i3);
                    if (A03(i2, r3)) {
                        View childAt = viewGroup.getChildAt(i2);
                        C04250eW r02 = null;
                        if (childAt instanceof AbstractC000703c) {
                            r02 = ((AbstractC000703c) childAt).getItemData();
                        }
                        View A002 = A00(r3, childAt, viewGroup);
                        if (r3 != r02) {
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
                if (!A04(viewGroup, i)) {
                    i++;
                }
            }
        }
    }

    public AbstractC04310ee(Context context) {
        this.A07 = context;
        this.A02 = LayoutInflater.from(context);
    }

    public boolean A04(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // X.AbstractC000603b
    public final void A7m(AbstractC000503a r1) {
        this.A04 = r1;
    }
}
