package X;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import androidx.annotation.VisibleForTesting;
import com.oculus.socialplatform.R;

@VisibleForTesting
/* renamed from: X.1sg  reason: invalid class name and case insensitive filesystem */
public class C11701sg extends C11691sf implements AbstractC11811ss {
    public ListAdapter A00;
    public int A01;
    public CharSequence A02;
    public final Rect A03 = new Rect();
    public final /* synthetic */ AnonymousClass1sR A04;

    @Override // X.AbstractC11811ss
    public final CharSequence A46() {
        return this.A02;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11701sg(AnonymousClass1sR r3, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle, 0);
        this.A04 = r3;
        this.A07 = r3;
        this.A0E = true;
        this.A0A.setFocusable(true);
        this.A08 = new C11791sq(this, r3);
    }

    public final void A01() {
        AnonymousClass1sR r4;
        Rect rect;
        int i;
        Drawable A3Q = A3Q();
        int i2 = 0;
        if (A3Q != null) {
            r4 = this.A04;
            rect = r4.A05;
            A3Q.getPadding(rect);
            if (r4.getLayoutDirection() == 1) {
                i2 = rect.right;
            } else {
                i2 = -rect.left;
            }
        } else {
            r4 = this.A04;
            rect = r4.A05;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = r4.getPaddingLeft();
        int paddingRight = r4.getPaddingRight();
        int width = r4.getWidth();
        int i3 = r4.A00;
        if (i3 == -2) {
            int A002 = r4.A00((SpinnerAdapter) this.A00, A3Q());
            int i4 = (r4.getContext().getResources().getDisplayMetrics().widthPixels - rect.left) - rect.right;
            if (A002 > i4) {
                A002 = i4;
            }
            i3 = Math.max(A002, (width - paddingLeft) - paddingRight);
        } else if (i3 == -1) {
            i3 = (width - paddingLeft) - paddingRight;
        }
        A00(i3);
        if (r4.getLayoutDirection() == 1) {
            i = i2 + (((width - paddingRight) - super.A04) - this.A01);
        } else {
            i = i2 + paddingLeft + this.A01;
        }
        A9u(i);
    }

    @Override // X.C11691sf, X.AbstractC11811ss
    public final void A9e(ListAdapter listAdapter) {
        super.A9e(listAdapter);
        this.A00 = listAdapter;
    }

    @Override // X.AbstractC11811ss
    public final void AAP(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        boolean A6B = A6B();
        A01();
        this.A0A.setInputMethodMode(2);
        AAO();
        ListView A4G = A4G();
        A4G.setChoiceMode(1);
        A4G.setTextDirection(i);
        A4G.setTextAlignment(i2);
        AnonymousClass1sR r3 = this.A04;
        int selectedItemPosition = r3.getSelectedItemPosition();
        AnonymousClass1F5 r1 = this.A0B;
        if (A6B() && r1 != null) {
            r1.A08 = false;
            r1.setSelection(selectedItemPosition);
            if (r1.getChoiceMode() != 0) {
                r1.setItemChecked(selectedItemPosition, true);
            }
        }
        if (!A6B && (viewTreeObserver = r3.getViewTreeObserver()) != null) {
            AnonymousClass1t8 r0 = new AnonymousClass1t8(this);
            viewTreeObserver.addOnGlobalLayoutListener(r0);
            this.A0A.setOnDismissListener(new AnonymousClass1tG(this, r0));
        }
    }

    @Override // X.AbstractC11811ss
    public final void A9v(int i) {
        this.A01 = i;
    }

    @Override // X.AbstractC11811ss
    public final void AA6(CharSequence charSequence) {
        this.A02 = charSequence;
    }
}
