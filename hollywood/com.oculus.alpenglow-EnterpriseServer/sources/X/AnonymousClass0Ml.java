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
import com.oculus.alpenglow.R;

@VisibleForTesting
/* renamed from: X.0Ml  reason: invalid class name */
public class AnonymousClass0Ml extends C04080dy implements AnonymousClass04S {
    public ListAdapter A00;
    public int A01;
    public CharSequence A02;
    public final Rect A03 = new Rect();
    public final /* synthetic */ C04110e4 A04;

    @Override // X.AnonymousClass04S
    public final CharSequence A3e() {
        return this.A02;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Ml(C04110e4 r3, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle, 0);
        this.A04 = r3;
        this.A07 = r3;
        this.A0E = true;
        this.A0A.setFocusable(true);
        this.A08 = new AnonymousClass04N(this, r3);
    }

    public final void A02() {
        C04110e4 r4;
        Rect rect;
        int i;
        Drawable A33 = A33();
        int i2 = 0;
        if (A33 != null) {
            r4 = this.A04;
            rect = r4.A04;
            A33.getPadding(rect);
            if (r4.getLayoutDirection() == 1) {
                i2 = rect.right;
            } else {
                i2 = -rect.left;
            }
        } else {
            r4 = this.A04;
            rect = r4.A04;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = r4.getPaddingLeft();
        int paddingRight = r4.getPaddingRight();
        int width = r4.getWidth();
        int i3 = r4.A00;
        if (i3 == -2) {
            int A002 = r4.A00((SpinnerAdapter) this.A00, A33());
            int i4 = (r4.getContext().getResources().getDisplayMetrics().widthPixels - rect.left) - rect.right;
            if (A002 > i4) {
                A002 = i4;
            }
            i3 = Math.max(A002, (width - paddingLeft) - paddingRight);
        } else if (i3 == -1) {
            i3 = (width - paddingLeft) - paddingRight;
        }
        A01(i3);
        if (r4.getLayoutDirection() == 1) {
            i = i2 + (((width - paddingRight) - super.A04) - this.A01);
        } else {
            i = i2 + paddingLeft + this.A01;
        }
        A7u(i);
    }

    @Override // X.C04080dy, X.AnonymousClass04S
    public final void A7j(ListAdapter listAdapter) {
        super.A7j(listAdapter);
        this.A00 = listAdapter;
    }

    @Override // X.AnonymousClass04S
    public final void A8Q(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        boolean A5a = A5a();
        A02();
        this.A0A.setInputMethodMode(2);
        A8P();
        ListView A3u = A3u();
        A3u.setChoiceMode(1);
        A3u.setTextDirection(i);
        A3u.setTextAlignment(i2);
        C04110e4 r3 = this.A04;
        int selectedItemPosition = r3.getSelectedItemPosition();
        C003004g r1 = this.A0B;
        if (A5a() && r1 != null) {
            r1.A08 = false;
            r1.setSelection(selectedItemPosition);
            if (r1.getChoiceMode() != 0) {
                r1.setItemChecked(selectedItemPosition, true);
            }
        }
        if (!A5a && (viewTreeObserver = r3.getViewTreeObserver()) != null) {
            AnonymousClass04O r0 = new AnonymousClass04O(this);
            viewTreeObserver.addOnGlobalLayoutListener(r0);
            this.A0A.setOnDismissListener(new AnonymousClass04P(this, r0));
        }
    }

    @Override // X.AnonymousClass04S
    public final void A7v(int i) {
        this.A01 = i;
    }

    @Override // X.AnonymousClass04S
    public final void A84(CharSequence charSequence) {
        this.A02 = charSequence;
    }
}
