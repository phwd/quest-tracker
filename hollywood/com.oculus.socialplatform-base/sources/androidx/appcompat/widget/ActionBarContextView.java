package androidx.appcompat.widget;

import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass1rP;
import X.C10901qA;
import X.C11081qa;
import X.C11591sO;
import X.C11781sp;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class ActionBarContextView extends AnonymousClass1rP {
    public View A00;
    public View A01;
    public CharSequence A02;
    public CharSequence A03;
    public boolean A04;
    public int A05;
    public int A06;
    public int A07;
    public LinearLayout A08;
    public TextView A09;
    public TextView A0A;

    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    private void A00() {
        if (this.A08 == null) {
            Context context = getContext();
            LayoutInflater.from(context).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.A08 = linearLayout;
            this.A0A = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.A09 = (TextView) this.A08.findViewById(R.id.action_bar_subtitle);
            int i = this.A07;
            if (i != 0) {
                TextView textView = this.A0A;
                getContext();
                textView.setTextAppearance(context, i);
            }
            int i2 = this.A06;
            if (i2 != 0) {
                TextView textView2 = this.A09;
                getContext();
                textView2.setTextAppearance(context, i2);
            }
        }
        this.A0A.setText(this.A03);
        this.A09.setText(this.A02);
        boolean z = !TextUtils.isEmpty(this.A03);
        boolean z2 = !TextUtils.isEmpty(this.A02);
        TextView textView3 = this.A09;
        int i3 = 0;
        int i4 = 8;
        if (z2) {
            i4 = 0;
        }
        textView3.setVisibility(i4);
        LinearLayout linearLayout2 = this.A08;
        if (!z && !z2) {
            i3 = 8;
        }
        linearLayout2.setVisibility(i3);
        if (this.A08.getParent() == null) {
            addView(this.A08);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A05(X.AbstractC11301rk r5) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContextView.A05(X.1rk):void");
    }

    public CharSequence getSubtitle() {
        return this.A02;
    }

    public CharSequence getTitle() {
        return this.A03;
    }

    public void setCustomView(View view) {
        View view2 = this.A01;
        if (view2 != null) {
            removeView(view2);
        }
        this.A01 = view;
        if (view != null) {
            LinearLayout linearLayout = this.A08;
            if (linearLayout != null) {
                removeView(linearLayout);
                this.A08 = null;
            }
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.A02 = charSequence;
        A00();
    }

    public void setTitle(CharSequence charSequence) {
        this.A03 = charSequence;
        A00();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.A04) {
            requestLayout();
        }
        this.A04 = z;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C11591sO r0 = super.A00;
        if (r0 != null) {
            r0.A03();
            C11781sp r02 = super.A00.A03;
            if (r02 != null) {
                r02.A03();
            }
        }
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.A03);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int paddingRight;
        int i6;
        int i7;
        boolean z2 = true;
        if (getLayoutDirection() == 1) {
            paddingLeft = (i3 - i) - getPaddingRight();
        } else {
            z2 = false;
            paddingLeft = getPaddingLeft();
        }
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.A00;
        if (view == null || view.getVisibility() == 8) {
            i5 = paddingLeft;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.A00.getLayoutParams();
            if (z2) {
                int i8 = marginLayoutParams.rightMargin;
                i6 = marginLayoutParams.leftMargin;
                i7 = paddingLeft - i8;
            } else {
                int i9 = marginLayoutParams.leftMargin;
                i6 = marginLayoutParams.rightMargin;
                i7 = paddingLeft + i9;
            }
            int A012 = i7 + AnonymousClass1rP.A01(this.A00, i7, paddingTop, paddingTop2, z2);
            i5 = A012 + i6;
            if (z2) {
                i5 = A012 - i6;
            }
        }
        LinearLayout linearLayout = this.A08;
        if (!(linearLayout == null || this.A01 != null || linearLayout.getVisibility() == 8)) {
            i5 += AnonymousClass1rP.A01(this.A08, i5, paddingTop, paddingTop2, z2);
        }
        View view2 = this.A01;
        if (view2 != null) {
            AnonymousClass1rP.A01(view2, i5, paddingTop, paddingTop2, z2);
        }
        if (z2) {
            paddingRight = getPaddingLeft();
        } else {
            paddingRight = (i3 - i) - getPaddingRight();
        }
        ActionMenuView actionMenuView = super.A01;
        if (actionMenuView != null) {
            AnonymousClass1rP.A01(actionMenuView, paddingRight, paddingTop, paddingTop2, !z2);
        }
    }

    public final void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(AnonymousClass006.A07(getClass().getSimpleName(), " can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        } else if (View.MeasureSpec.getMode(i2) != 0) {
            int size = View.MeasureSpec.getSize(i);
            int i4 = super.A05;
            if (i4 <= 0) {
                i4 = View.MeasureSpec.getSize(i2);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = i4 - paddingTop;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
            View view = this.A00;
            if (view != null) {
                view.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, Integer.MIN_VALUE), makeMeasureSpec);
                int max = Math.max(0, (paddingLeft - view.getMeasuredWidth()) - 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.A00.getLayoutParams();
                paddingLeft = max - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = super.A01;
            if (actionMenuView != null && actionMenuView.getParent() == this) {
                ActionMenuView actionMenuView2 = super.A01;
                actionMenuView2.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, Integer.MIN_VALUE), makeMeasureSpec);
                paddingLeft = Math.max(0, (paddingLeft - actionMenuView2.getMeasuredWidth()) - 0);
            }
            LinearLayout linearLayout = this.A08;
            if (linearLayout != null && this.A01 == null) {
                if (this.A04) {
                    this.A08.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.A08.getMeasuredWidth();
                    boolean z = false;
                    if (measuredWidth <= paddingLeft) {
                        z = true;
                        paddingLeft -= measuredWidth;
                    }
                    LinearLayout linearLayout2 = this.A08;
                    int i6 = 8;
                    if (z) {
                        i6 = 0;
                    }
                    linearLayout2.setVisibility(i6);
                } else {
                    linearLayout.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, Integer.MIN_VALUE), makeMeasureSpec);
                    paddingLeft = Math.max(0, (paddingLeft - linearLayout.getMeasuredWidth()) - 0);
                }
            }
            View view2 = this.A01;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i7 = layoutParams.width;
                int i8 = Integer.MIN_VALUE;
                if (i7 != -2) {
                    i8 = 1073741824;
                    if (i7 >= 0) {
                        paddingLeft = Math.min(i7, paddingLeft);
                    }
                }
                int i9 = layoutParams.height;
                if (i9 == -2) {
                    i3 = Integer.MIN_VALUE;
                } else if (i9 >= 0) {
                    i5 = Math.min(i9, i5);
                }
                this.A01.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i8), View.MeasureSpec.makeMeasureSpec(i5, i3));
            }
            if (super.A05 <= 0) {
                int childCount = getChildCount();
                int i10 = 0;
                for (int i11 = 0; i11 < childCount; i11++) {
                    int measuredHeight = getChildAt(i11).getMeasuredHeight() + paddingTop;
                    if (measuredHeight > i10) {
                        i10 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i10);
                return;
            }
            setMeasuredDimension(size, i4);
        } else {
            throw new IllegalStateException(AnonymousClass006.A07(getClass().getSimpleName(), " can only be used with android:layout_height=\"wrap_content\""));
        }
    }

    @Override // X.AnonymousClass1rP
    public void setContentHeight(int i) {
        super.A05 = i;
    }

    public ActionBarContextView(@NonNull Context context) {
        this(context, null);
    }

    public ActionBarContextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        C10901qA A002 = C10901qA.A00(context, attributeSet, C11081qa.A03, i, 0);
        setBackground(A002.A02(0));
        TypedArray typedArray = A002.A02;
        this.A07 = typedArray.getResourceId(5, 0);
        this.A06 = typedArray.getResourceId(4, 0);
        super.A05 = typedArray.getLayoutDimension(3, 0);
        this.A05 = typedArray.getResourceId(2, R.layout.abc_action_mode_close_item_material);
        A002.A04();
    }
}
