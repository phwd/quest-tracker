package androidx.appcompat.widget;

import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass07f;
import X.AnonymousClass1EM;
import X.C10901qA;
import X.C11081qa;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    public static final int INDEX_BOTTOM = 2;
    public static final int INDEX_CENTER_VERTICAL = 0;
    public static final int INDEX_FILL = 3;
    public static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    public static final int VERTICAL_GRAVITY_COUNT = 4;
    public boolean mBaselineAligned;
    public int mBaselineAlignedChildIndex;
    public int mBaselineChildTop;
    public Drawable mDivider;
    public int mDividerHeight;
    public int mDividerPadding;
    public int mDividerWidth;
    public int mGravity;
    public int[] mMaxAscent;
    public int[] mMaxDescent;
    public int mOrientation;
    public int mShowDividers;
    public int mTotalLength;
    public boolean mUseLargestChild;
    public float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public @interface OrientationMode {
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                AnonymousClass1EM r2 = (AnonymousClass1EM) childAt.getLayoutParams();
                if (r2.height == -1) {
                    int i4 = r2.width;
                    r2.width = childAt.getMeasuredWidth();
                    measureChildWithMargins(childAt, i2, 0, makeMeasureSpec, 0);
                    r2.width = i4;
                }
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                AnonymousClass1EM r2 = (AnonymousClass1EM) childAt.getLayoutParams();
                if (r2.width == -1) {
                    int i4 = r2.height;
                    r2.height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, makeMeasureSpec, 0, i2, 0);
                    r2.height = i4;
                }
            }
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    public int getLocationOffset(View view) {
        return 0;
    }

    public int getNextLocationOffset(View view) {
        return 0;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY})
    public boolean hasDividerBeforeChildAt(int i) {
        int i2;
        if (i == 0) {
            i2 = this.mShowDividers & 1;
        } else if (i == getChildCount()) {
            i2 = this.mShowDividers & 4;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i3 = i - 1; i3 >= 0; i3--) {
                if (getChildAt(i3).getVisibility() == 8) {
                }
            }
            return false;
        }
        return i2 != 0;
    }

    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0305, code lost:
        if (r3.height != -1) goto L_0x0307;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0326, code lost:
        if (r3.height != -1) goto L_0x0328;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0114, code lost:
        if (r3.height != -1) goto L_0x0116;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0131  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureHorizontal(int r38, int r39) {
        /*
        // Method dump skipped, instructions count: 1077
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    public int measureNullChild(int i) {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:106:0x021b, code lost:
        if (r9.width == -1) goto L_0x021d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0227, code lost:
        if (r9.width != r4) goto L_0x0229;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0105, code lost:
        if (r3.width != -1) goto L_0x0107;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureVertical(int r31, int r32) {
        /*
        // Method dump skipped, instructions count: 709
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, f);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) >> 1;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return i3 + ((AnonymousClass1EM) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        int i7;
        boolean z = true;
        if (getLayoutDirection() != 1) {
            z = false;
        }
        int paddingTop = getPaddingTop();
        int i8 = i4 - i2;
        int paddingBottom = i8 - getPaddingBottom();
        int paddingBottom2 = (i8 - paddingTop) - getPaddingBottom();
        int childCount = getChildCount();
        int i9 = this.mGravity;
        int i10 = i9 & 112;
        boolean z2 = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = Gravity.getAbsoluteGravity(8388615 & i9, getLayoutDirection());
        if (absoluteGravity != 1) {
            paddingLeft = getPaddingLeft();
            if (absoluteGravity == 5) {
                paddingLeft = ((paddingLeft + i3) - i) - this.mTotalLength;
            }
        } else {
            paddingLeft = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
        }
        int i11 = 0;
        int i12 = 1;
        if (z) {
            i11 = childCount - 1;
            i12 = -1;
        }
        int i13 = 0;
        while (i13 < childCount) {
            int i14 = i11 + (i12 * i13);
            View childAt = getChildAt(i14);
            if (childAt == null) {
                paddingLeft += 0;
            } else if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                AnonymousClass1EM r7 = (AnonymousClass1EM) childAt.getLayoutParams();
                if (!z2 || r7.height == -1) {
                    i5 = -1;
                } else {
                    i5 = childAt.getBaseline();
                }
                int i15 = r7.A01;
                if (i15 < 0) {
                    i15 = i10;
                }
                int i16 = i15 & 112;
                if (i16 == 16) {
                    i6 = ((paddingBottom2 - measuredHeight) / 2) + paddingTop + r7.topMargin;
                    i7 = r7.bottomMargin;
                    i6 -= i7;
                } else if (i16 == 48) {
                    i6 = r7.topMargin + paddingTop;
                    if (i5 != -1) {
                        i6 += iArr[1] - i5;
                    }
                } else if (i16 != 80) {
                    i6 = paddingTop;
                } else {
                    i6 = (paddingBottom - measuredHeight) - r7.bottomMargin;
                    if (i5 != -1) {
                        i7 = iArr2[2] - (childAt.getMeasuredHeight() - i5);
                        i6 -= i7;
                    }
                }
                if (hasDividerBeforeChildAt(i14)) {
                    paddingLeft += this.mDividerWidth;
                }
                int i17 = r7.leftMargin + paddingLeft;
                setChildFrame(childAt, i17 + 0, i6, measuredWidth, measuredHeight);
                i13 += 0;
                paddingLeft = i17 + measuredWidth + r7.rightMargin + 0;
            }
            i13++;
        }
    }

    public void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int childCount = getChildCount();
        int i8 = this.mGravity;
        int i9 = i8 & 112;
        int i10 = i8 & 8388615;
        if (i9 == 16) {
            paddingTop = getPaddingTop() + (((i4 - i2) - this.mTotalLength) >> 1);
        } else if (i9 != 80) {
            paddingTop = getPaddingTop();
        } else {
            paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        }
        int i11 = 0;
        while (i11 < childCount) {
            View childAt = getChildAt(i11);
            if (childAt == null) {
                paddingTop += 0;
            } else if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
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
                    paddingTop += this.mDividerHeight;
                }
                int i13 = paddingTop + r6.topMargin;
                setChildFrame(childAt, i6, i13 + 0, measuredWidth, measuredHeight);
                paddingTop = i13 + measuredHeight + r6.bottomMargin + 0;
                i11 += 0;
            }
            i11++;
        }
    }

    public void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException(AnonymousClass006.A04("base aligned child index out of range (0, ", getChildCount(), ")"));
        }
        this.mBaselineAlignedChildIndex = i;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & -113);
            requestLayout();
        }
    }

    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int left;
        int childCount = getChildCount();
        boolean z = true;
        if (getLayoutDirection() != 1) {
            z = false;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (!(childAt == null || childAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i4))) {
                AnonymousClass1EM r0 = (AnonymousClass1EM) childAt.getLayoutParams();
                if (z) {
                    left = childAt.getRight() + r0.rightMargin;
                } else {
                    left = (childAt.getLeft() - r0.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, left);
            }
        }
        if (hasDividerBeforeChildAt(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 != null) {
                AnonymousClass1EM r02 = (AnonymousClass1EM) childAt2.getLayoutParams();
                if (z) {
                    i = childAt2.getLeft();
                    i2 = r02.leftMargin;
                    i3 = (i - i2) - this.mDividerWidth;
                } else {
                    i3 = childAt2.getRight() + r02.rightMargin;
                }
            } else if (z) {
                i3 = getPaddingLeft();
            } else {
                i = getWidth();
                i2 = getPaddingRight();
                i3 = (i - i2) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, i3);
        }
    }

    public void drawDividersVertical(Canvas canvas) {
        int bottom;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (childAt.getTop() - ((AnonymousClass1EM) childAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = childAt2.getBottom() + ((AnonymousClass1EM) childAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof AnonymousClass1EM;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public LinearLayoutCompat(@NonNull Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        int[] iArr = C11081qa.A0C;
        C10901qA A00 = C10901qA.A00(context, attributeSet, iArr, i, 0);
        TypedArray typedArray = A00.A02;
        AnonymousClass07f.A04(this, context, iArr, attributeSet, typedArray, i);
        int i2 = typedArray.getInt(1, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = typedArray.getInt(0, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        if (!typedArray.getBoolean(2, true)) {
            this.mBaselineAligned = false;
        }
        this.mWeightSum = typedArray.getFloat(4, -1.0f);
        this.mBaselineAlignedChildIndex = typedArray.getInt(3, -1);
        this.mUseLargestChild = typedArray.getBoolean(7, false);
        setDividerDrawable(A00.A02(5));
        this.mShowDividers = typedArray.getInt(8, 0);
        this.mDividerPadding = typedArray.getDimensionPixelSize(6, 0);
        A00.A04();
    }

    public AnonymousClass1EM generateDefaultLayoutParams() {
        int i = this.mOrientation;
        int i2 = -2;
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            i2 = -1;
        }
        return new AnonymousClass1EM(i2);
    }

    @Override // android.view.ViewGroup
    public AnonymousClass1EM generateLayoutParams(AttributeSet attributeSet) {
        return new AnonymousClass1EM(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public AnonymousClass1EM generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new AnonymousClass1EM(layoutParams);
    }
}
