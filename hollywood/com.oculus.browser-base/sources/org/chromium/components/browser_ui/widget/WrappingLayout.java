package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WrappingLayout extends ViewGroup {
    public int F;
    public int G;
    public ArrayList H = new ArrayList();

    public WrappingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, FJ0.U0, 0, 0);
        this.F = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.G = obtainStyledAttributes.getDimensionPixelSize(1, 0);
    }

    public final int a(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode == 1073741824) {
            return size;
        }
        if (mode == Integer.MIN_VALUE) {
            i = Math.min(i, size);
        }
        return Math.max(i, i2);
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        boolean z2 = true;
        boolean z3 = getLayoutDirection() == 1;
        int i5 = 0;
        int i6 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int marginStart = marginLayoutParams.getMarginStart() + paddingStart;
                int i7 = marginLayoutParams.topMargin + paddingTop;
                boolean z4 = paddingStart == getPaddingStart() ? z2 : false;
                int marginEnd = marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart() + measuredWidth;
                int i8 = marginLayoutParams.topMargin + measuredHeight + marginLayoutParams.bottomMargin;
                if (z4 || paddingStart + marginEnd <= getMeasuredWidth()) {
                    i6 = Math.max(i6, i8);
                } else {
                    marginStart = marginLayoutParams.getMarginStart() + getPaddingStart();
                    i7 += i6 + this.G;
                    paddingTop = i7 - marginLayoutParams.topMargin;
                    i6 = i8;
                }
                int i9 = marginStart + measuredWidth;
                int marginEnd2 = marginLayoutParams.getMarginEnd() + i9 + this.F;
                if (z3) {
                    i9 = getMeasuredWidth() - marginStart;
                    marginStart = i9 - measuredWidth;
                }
                childAt.layout(marginStart, i7, i9, measuredHeight + i7);
                paddingStart = marginEnd2;
            }
            i5++;
            z2 = true;
        }
    }

    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 0) {
            measureChildren(i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
                return;
            }
            int size = View.MeasureSpec.getSize(i) - (getPaddingRight() + getPaddingLeft());
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if (getChildAt(i3).getVisibility() != 8) {
                    this.H.add(Integer.valueOf(i3));
                }
            }
            int size2 = this.H.size();
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i4 < size2) {
                View childAt = getChildAt(((Integer) this.H.get(i4)).intValue());
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int marginEnd = marginLayoutParams.getMarginEnd() + childAt.getMeasuredWidth() + marginLayoutParams.getMarginStart();
                int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                if (i7 + marginEnd <= size) {
                    if (i7 != 0) {
                        i7 += this.F;
                    }
                    i7 += marginEnd;
                    i8 = Math.max(i8, measuredHeight);
                } else {
                    if (i8 != 0) {
                        i6 += i8 + this.G;
                    }
                    i8 = measuredHeight;
                    i7 = marginEnd;
                }
                i5 = Math.max(i5, i7);
                i4++;
                if (i4 == size2) {
                    i6 += i8;
                }
            }
            setMeasuredDimension(ViewGroup.resolveSizeAndState(a(getPaddingRight() + getPaddingLeft() + i5, getSuggestedMinimumWidth(), i), i, 0), ViewGroup.resolveSizeAndState(a(getPaddingBottom() + getPaddingTop() + i6, getSuggestedMinimumHeight(), i2), i2, 0));
            this.H.clear();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }
}
