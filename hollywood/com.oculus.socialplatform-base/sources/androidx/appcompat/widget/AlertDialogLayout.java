package androidx.appcompat.widget;

import X.AnonymousClass02C;
import X.AnonymousClass1EM;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class AlertDialogLayout extends LinearLayoutCompat {
    @Override // androidx.appcompat.widget.LinearLayoutCompat
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int childCount = getChildCount();
        View view = null;
        ViewGroup viewGroup = null;
        View view2 = null;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == R.id.topPanel) {
                    view = childAt;
                } else if (id == R.id.buttonPanel) {
                    viewGroup = childAt;
                } else if ((id == R.id.contentPanel || id == R.id.customPanel) && view2 == null) {
                    view2 = childAt;
                } else {
                    super.onMeasure(i, i2);
                    return;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (view != null) {
            view.measure(i, 0);
            paddingTop += view.getMeasuredHeight();
            i3 = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            i3 = 0;
        }
        if (viewGroup != null) {
            viewGroup.measure(i, 0);
            i4 = viewGroup.getMinimumHeight();
            if (i4 <= 0) {
                if (viewGroup instanceof ViewGroup) {
                    ViewGroup viewGroup2 = viewGroup;
                    if (viewGroup2.getChildCount() == 1) {
                        View childAt2 = viewGroup2.getChildAt(0);
                        i4 = childAt2.getMinimumHeight();
                        if (i4 <= 0) {
                            if (childAt2 instanceof ViewGroup) {
                                ViewGroup viewGroup3 = (ViewGroup) childAt2;
                                if (viewGroup3.getChildCount() == 1) {
                                    View childAt3 = viewGroup3.getChildAt(0);
                                    i4 = childAt3.getMinimumHeight();
                                    if (i4 <= 0) {
                                        if (childAt3 instanceof ViewGroup) {
                                            ViewGroup viewGroup4 = (ViewGroup) childAt3;
                                            if (viewGroup4.getChildCount() == 1) {
                                                i4 = A00(viewGroup4.getChildAt(0));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                i4 = 0;
            }
            i5 = viewGroup.getMeasuredHeight() - i4;
            paddingTop += i4;
            i3 = View.combineMeasuredStates(i3, viewGroup.getMeasuredState());
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (view2 != null) {
            int i8 = 0;
            if (mode != 0) {
                i8 = View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingTop), mode);
            }
            view2.measure(i, i8);
            i6 = view2.getMeasuredHeight();
            paddingTop += i6;
            i3 = View.combineMeasuredStates(i3, view2.getMeasuredState());
        } else {
            i6 = 0;
        }
        int i9 = size - paddingTop;
        if (viewGroup != null) {
            int i10 = paddingTop - i4;
            int min = Math.min(i9, i5);
            if (min > 0) {
                i9 -= min;
                i4 += min;
            }
            viewGroup.measure(i, View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
            paddingTop = i10 + viewGroup.getMeasuredHeight();
            i3 = View.combineMeasuredStates(i3, viewGroup.getMeasuredState());
        }
        if (view2 != null && i9 > 0) {
            view2.measure(i, View.MeasureSpec.makeMeasureSpec(i6 + i9, mode));
            paddingTop = (paddingTop - i6) + view2.getMeasuredHeight();
            i3 = View.combineMeasuredStates(i3, view2.getMeasuredState());
        }
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt4 = getChildAt(i12);
            if (childAt4.getVisibility() != 8) {
                i11 = Math.max(i11, childAt4.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(i11 + getPaddingLeft() + getPaddingRight(), i, i3), View.resolveSizeAndState(paddingTop, i2, 0));
        if (mode2 != 1073741824) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt5 = getChildAt(i13);
                if (childAt5.getVisibility() != 8) {
                    AnonymousClass1EM r2 = (AnonymousClass1EM) childAt5.getLayoutParams();
                    if (r2.width == -1) {
                        int i14 = r2.height;
                        r2.height = childAt5.getMeasuredHeight();
                        measureChildWithMargins(childAt5, makeMeasureSpec, 0, i2, 0);
                        r2.height = i14;
                    }
                }
            }
        }
    }

    public static int A00(View view) {
        int minimumHeight = view.getMinimumHeight();
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return A00(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingTop;
        int intrinsicHeight;
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int i8 = this.mGravity;
        int i9 = i8 & 112;
        int i10 = i8 & 8388615;
        if (i9 == 16) {
            paddingTop = getPaddingTop() + (((i4 - i2) - measuredHeight) >> 1);
        } else if (i9 != 80) {
            paddingTop = getPaddingTop();
        } else {
            paddingTop = ((getPaddingTop() + i4) - i2) - measuredHeight;
        }
        Drawable drawable = this.mDivider;
        if (drawable == null) {
            intrinsicHeight = 0;
        } else {
            intrinsicHeight = drawable.getIntrinsicHeight();
        }
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight2 = childAt.getMeasuredHeight();
                AnonymousClass1EM r6 = (AnonymousClass1EM) childAt.getLayoutParams();
                int i12 = r6.A01;
                if (i12 < 0) {
                    i12 = i10;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i12, getLayoutDirection()) & 7;
                if (absoluteGravity == 1) {
                    i5 = ((paddingRight2 - measuredWidth) >> 1) + paddingLeft + r6.leftMargin;
                    i6 = i5 - r6.rightMargin;
                } else if (absoluteGravity != 5) {
                    i6 = r6.leftMargin + paddingLeft;
                } else {
                    i5 = paddingRight - measuredWidth;
                    i6 = i5 - r6.rightMargin;
                }
                if (hasDividerBeforeChildAt(i11)) {
                    paddingTop += intrinsicHeight;
                }
                int i13 = paddingTop + r6.topMargin;
                childAt.layout(i6, i13, measuredWidth + i6, measuredHeight2 + i13);
                paddingTop = i13 + measuredHeight2 + r6.bottomMargin;
            }
        }
    }

    public AlertDialogLayout(@Nullable Context context) {
        super(context, null);
    }

    public AlertDialogLayout(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
