package org.chromium.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OptimizedFrameLayout extends FrameLayout {
    public List F = new ArrayList();

    public OptimizedFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        int childCount = getChildCount();
        boolean z = (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) ? false : true;
        this.F.clear();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i9 < childCount) {
            View childAt = getChildAt(i9);
            if (getMeasureAllChildren() || childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i6 = childCount;
                int childMeasureSpec = FrameLayout.getChildMeasureSpec(i7, paddingLeft + paddingRight + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width);
                int childMeasureSpec2 = FrameLayout.getChildMeasureSpec(i8, paddingTop + paddingBottom + layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height);
                childAt.measure(childMeasureSpec, childMeasureSpec2);
                i10 = Math.max(i10, childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
                i11 = Math.max(i11, childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
                i12 = FrameLayout.combineMeasuredStates(i12, childAt.getMeasuredState());
                if (z && (layoutParams.width == -1 || layoutParams.height == -1)) {
                    this.F.add(new C2428et0(this, childAt, childMeasureSpec, childMeasureSpec2));
                }
            } else {
                i6 = childCount;
            }
            i9++;
            i7 = i;
            i8 = i2;
            childCount = i6;
        }
        int i13 = paddingLeft + paddingRight;
        int i14 = paddingTop + paddingBottom;
        int max = Math.max(i11 + i14, getSuggestedMinimumHeight());
        int max2 = Math.max(i10 + i13, getSuggestedMinimumWidth());
        Drawable foreground = getForeground();
        if (foreground != null) {
            max = Math.max(max, foreground.getMinimumHeight());
            max2 = Math.max(max2, foreground.getMinimumWidth());
        }
        setMeasuredDimension(FrameLayout.resolveSizeAndState(max2, i, i12), FrameLayout.resolveSizeAndState(max, i2, i12 << 16));
        int size = this.F.size();
        if (size > 1) {
            int i15 = 0;
            while (i15 < size) {
                C2428et0 et0 = (C2428et0) this.F.get(i15);
                View view = et0.f9888a;
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int i16 = marginLayoutParams.width;
                if (i16 == -1) {
                    i4 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (((getMeasuredWidth() - paddingLeft) - paddingRight) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin), 1073741824);
                    i3 = i13;
                } else {
                    i3 = i13;
                    i4 = FrameLayout.getChildMeasureSpec(i, marginLayoutParams.leftMargin + i13 + marginLayoutParams.rightMargin, i16);
                }
                int i17 = marginLayoutParams.height;
                if (i17 == -1) {
                    i5 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (((getMeasuredHeight() - paddingTop) - paddingBottom) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin), 1073741824);
                } else {
                    i5 = FrameLayout.getChildMeasureSpec(i2, marginLayoutParams.topMargin + i14 + marginLayoutParams.bottomMargin, i17);
                }
                if (et0.b != i4 || et0.c != i5) {
                    view.measure(i4, i5);
                }
                i15++;
                i13 = i3;
            }
        }
        this.F.clear();
    }
}
