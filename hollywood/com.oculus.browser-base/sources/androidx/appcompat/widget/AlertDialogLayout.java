package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AlertDialogLayout extends AbstractC5715y80 {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public static int r(View view) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        int minimumHeight = view.getMinimumHeight();
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return r(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009b  */
    @Override // defpackage.AbstractC5715y80
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        /*
        // Method dump skipped, instructions count: 174
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AlertDialogLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // defpackage.AbstractC5715y80
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int childCount = getChildCount();
        View view = null;
        boolean z = false;
        View view2 = null;
        View view3 = null;
        int i7 = 0;
        while (true) {
            if (i7 < childCount) {
                View childAt = getChildAt(i7);
                if (childAt.getVisibility() != 8) {
                    int id = childAt.getId();
                    if (id == R.id.topPanel) {
                        view = childAt;
                    } else if (id == R.id.buttonPanel) {
                        view2 = childAt;
                    } else if ((id == R.id.contentPanel || id == R.id.customPanel) && view3 == null) {
                        view3 = childAt;
                    }
                }
                i7++;
            } else {
                int mode = View.MeasureSpec.getMode(i2);
                int size = View.MeasureSpec.getSize(i2);
                int mode2 = View.MeasureSpec.getMode(i);
                int paddingBottom = getPaddingBottom() + getPaddingTop();
                if (view != null) {
                    view.measure(i, 0);
                    paddingBottom += view.getMeasuredHeight();
                    i3 = View.combineMeasuredStates(0, view.getMeasuredState());
                } else {
                    i3 = 0;
                }
                if (view2 != null) {
                    view2.measure(i, 0);
                    i5 = r(view2);
                    i4 = view2.getMeasuredHeight() - i5;
                    paddingBottom += i5;
                    i3 = View.combineMeasuredStates(i3, view2.getMeasuredState());
                } else {
                    i5 = 0;
                    i4 = 0;
                }
                if (view3 != null) {
                    view3.measure(i, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode));
                    i6 = view3.getMeasuredHeight();
                    paddingBottom += i6;
                    i3 = View.combineMeasuredStates(i3, view3.getMeasuredState());
                } else {
                    i6 = 0;
                }
                int i8 = size - paddingBottom;
                if (view2 != null) {
                    int i9 = paddingBottom - i5;
                    int min = Math.min(i8, i4);
                    if (min > 0) {
                        i8 -= min;
                        i5 += min;
                    }
                    view2.measure(i, View.MeasureSpec.makeMeasureSpec(i5, 1073741824));
                    paddingBottom = i9 + view2.getMeasuredHeight();
                    i3 = View.combineMeasuredStates(i3, view2.getMeasuredState());
                }
                if (view3 != null && i8 > 0) {
                    view3.measure(i, View.MeasureSpec.makeMeasureSpec(i6 + i8, mode));
                    paddingBottom = (paddingBottom - i6) + view3.getMeasuredHeight();
                    i3 = View.combineMeasuredStates(i3, view3.getMeasuredState());
                }
                int i10 = 0;
                for (int i11 = 0; i11 < childCount; i11++) {
                    View childAt2 = getChildAt(i11);
                    if (childAt2.getVisibility() != 8) {
                        i10 = Math.max(i10, childAt2.getMeasuredWidth());
                    }
                }
                setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + i10, i, i3), View.resolveSizeAndState(paddingBottom, i2, 0));
                if (mode2 != 1073741824) {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
                    for (int i12 = 0; i12 < childCount; i12++) {
                        View childAt3 = getChildAt(i12);
                        if (childAt3.getVisibility() != 8) {
                            C5545x80 x80 = (C5545x80) childAt3.getLayoutParams();
                            if (((ViewGroup.MarginLayoutParams) x80).width == -1) {
                                int i13 = ((ViewGroup.MarginLayoutParams) x80).height;
                                ((ViewGroup.MarginLayoutParams) x80).height = childAt3.getMeasuredHeight();
                                measureChildWithMargins(childAt3, makeMeasureSpec, 0, i2, 0);
                                ((ViewGroup.MarginLayoutParams) x80).height = i13;
                            }
                        }
                    }
                }
                z = true;
            }
        }
        if (!z) {
            super.onMeasure(i, i2);
        }
    }
}
