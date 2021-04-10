package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: y80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5715y80 extends ViewGroup {
    public boolean F = true;
    public int G = -1;
    public int H = 0;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f11666J = 8388659;
    public int K;
    public float L;
    public boolean M;
    public int[] N;
    public int[] O;
    public Drawable P;
    public int Q;
    public int R;
    public int S;
    public int T;

    public AbstractC5715y80(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Drawable drawable;
        int resourceId;
        boolean z = true;
        int[] iArr = FJ0.X;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        AbstractC1920bu1.m(this, context, iArr, attributeSet, obtainStyledAttributes, i, 0);
        int i2 = obtainStyledAttributes.getInt(1, -1);
        if (i2 >= 0 && this.I != i2) {
            this.I = i2;
            requestLayout();
        }
        int i3 = obtainStyledAttributes.getInt(0, -1);
        if (i3 >= 0 && this.f11666J != i3) {
            i3 = (8388615 & i3) == 0 ? i3 | 8388611 : i3;
            this.f11666J = (i3 & 112) == 0 ? i3 | 48 : i3;
            requestLayout();
        }
        boolean z2 = obtainStyledAttributes.getBoolean(2, true);
        if (!z2) {
            this.F = z2;
        }
        this.L = obtainStyledAttributes.getFloat(4, -1.0f);
        this.G = obtainStyledAttributes.getInt(3, -1);
        this.M = obtainStyledAttributes.getBoolean(7, false);
        if (!obtainStyledAttributes.hasValue(5) || (resourceId = obtainStyledAttributes.getResourceId(5, 0)) == 0) {
            drawable = obtainStyledAttributes.getDrawable(5);
        } else {
            drawable = AbstractC5544x8.a(context, resourceId);
        }
        if (drawable != this.P) {
            this.P = drawable;
            if (drawable != null) {
                this.Q = drawable.getIntrinsicWidth();
                this.R = drawable.getIntrinsicHeight();
            } else {
                this.Q = 0;
                this.R = 0;
            }
            setWillNotDraw(drawable != null ? false : z);
            requestLayout();
        }
        this.S = obtainStyledAttributes.getInt(8, 0);
        this.T = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        obtainStyledAttributes.recycle();
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C5545x80;
    }

    public void g(Canvas canvas, int i) {
        this.P.setBounds(getPaddingLeft() + this.T, i, (getWidth() - getPaddingRight()) - this.T, this.R + i);
        this.P.draw(canvas);
    }

    public int getBaseline() {
        int i;
        if (this.G < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.G;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.H;
                if (this.I == 1 && (i = this.f11666J & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.K) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.K;
                    }
                }
                return i3 + ((ViewGroup.MarginLayoutParams) ((C5545x80) childAt.getLayoutParams())).topMargin + baseline;
            } else if (this.G == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public void h(Canvas canvas, int i) {
        this.P.setBounds(i, getPaddingTop() + this.T, this.Q + i, (getHeight() - getPaddingBottom()) - this.T);
        this.P.draw(canvas);
    }

    /* renamed from: i */
    public C5545x80 generateDefaultLayoutParams() {
        int i = this.I;
        if (i == 0) {
            return new C5545x80(-2, -2);
        }
        if (i == 1) {
            return new C5545x80(-1, -2);
        }
        return null;
    }

    /* renamed from: j */
    public C5545x80 generateLayoutParams(AttributeSet attributeSet) {
        return new C5545x80(getContext(), attributeSet);
    }

    /* renamed from: k */
    public C5545x80 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C5545x80(layoutParams);
    }

    public int l() {
        return 0;
    }

    public int m() {
        return 0;
    }

    public int n() {
        return 0;
    }

    public boolean o(int i) {
        if (i == 0) {
            return (this.S & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.S & 4) != 0;
        }
        if ((this.S & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.P != null) {
            int i6 = 0;
            if (this.I == 1) {
                int childCount = getChildCount();
                while (i6 < childCount) {
                    View childAt = getChildAt(i6);
                    if (!(childAt == null || childAt.getVisibility() == 8 || !o(i6))) {
                        g(canvas, (childAt.getTop() - ((ViewGroup.MarginLayoutParams) ((C5545x80) childAt.getLayoutParams())).topMargin) - this.R);
                    }
                    i6++;
                }
                if (o(childCount)) {
                    View childAt2 = getChildAt(childCount - 1);
                    if (childAt2 == null) {
                        i5 = (getHeight() - getPaddingBottom()) - this.R;
                    } else {
                        i5 = childAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((C5545x80) childAt2.getLayoutParams())).bottomMargin;
                    }
                    g(canvas, i5);
                    return;
                }
                return;
            }
            int childCount2 = getChildCount();
            boolean a2 = AbstractC4826sv1.a(this);
            while (i6 < childCount2) {
                View childAt3 = getChildAt(i6);
                if (!(childAt3 == null || childAt3.getVisibility() == 8 || !o(i6))) {
                    C5545x80 x80 = (C5545x80) childAt3.getLayoutParams();
                    if (a2) {
                        i4 = childAt3.getRight() + ((ViewGroup.MarginLayoutParams) x80).rightMargin;
                    } else {
                        i4 = (childAt3.getLeft() - ((ViewGroup.MarginLayoutParams) x80).leftMargin) - this.Q;
                    }
                    h(canvas, i4);
                }
                i6++;
            }
            if (o(childCount2)) {
                View childAt4 = getChildAt(childCount2 - 1);
                if (childAt4 != null) {
                    C5545x80 x802 = (C5545x80) childAt4.getLayoutParams();
                    if (a2) {
                        i3 = childAt4.getLeft() - ((ViewGroup.MarginLayoutParams) x802).leftMargin;
                        i2 = this.Q;
                    } else {
                        i = childAt4.getRight() + ((ViewGroup.MarginLayoutParams) x802).rightMargin;
                        h(canvas, i);
                    }
                } else if (a2) {
                    i = getPaddingLeft();
                    h(canvas, i);
                } else {
                    i3 = getWidth() - getPaddingRight();
                    i2 = this.Q;
                }
                i = i3 - i2;
                h(canvas, i);
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
        // Method dump skipped, instructions count: 494
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC5715y80.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x0312  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x04be  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x04c3  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x04eb  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04f0  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x04f8  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0509  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0520  */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x058f  */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x059a  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x0635  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x06ff  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x071c  */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x08d2  */
    /* JADX WARNING: Removed duplicated region for block: B:428:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r39, int r40) {
        /*
        // Method dump skipped, instructions count: 2323
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC5715y80.onMeasure(int, int):void");
    }

    public void p(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public int q() {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
