package X;

import android.graphics.Rect;
import android.view.View;

/* renamed from: X.1Bm  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05861Bm {
    public int A00 = Integer.MIN_VALUE;
    public final Rect A01 = new Rect();
    public final AnonymousClass1Ag A02;

    public static AbstractC05861Bm A00(AnonymousClass1Ag r1, int i) {
        if (i == 0) {
            return new AnonymousClass1Aw(r1);
        }
        if (i == 1) {
            return new AnonymousClass1Ax(r1);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public final int A01() {
        if (!(this instanceof AnonymousClass1Ax)) {
            return this.A02.mWidth;
        }
        return this.A02.mHeight;
    }

    public final int A02() {
        int i;
        int paddingBottom;
        if (!(this instanceof AnonymousClass1Ax)) {
            AnonymousClass1Ag r0 = this.A02;
            i = r0.mWidth;
            paddingBottom = r0.getPaddingRight();
        } else {
            AnonymousClass1Ag r02 = this.A02;
            i = r02.mHeight;
            paddingBottom = r02.getPaddingBottom();
        }
        return i - paddingBottom;
    }

    public final int A03() {
        if (!(this instanceof AnonymousClass1Ax)) {
            return this.A02.getPaddingRight();
        }
        return this.A02.getPaddingBottom();
    }

    public final int A04() {
        if (!(this instanceof AnonymousClass1Ax)) {
            return this.A02.mWidthMode;
        }
        return this.A02.mHeightMode;
    }

    public final int A05() {
        if (!(this instanceof AnonymousClass1Ax)) {
            return this.A02.getPaddingLeft();
        }
        return this.A02.getPaddingTop();
    }

    public final int A06() {
        int paddingTop;
        int paddingBottom;
        if (!(this instanceof AnonymousClass1Ax)) {
            AnonymousClass1Ag r2 = this.A02;
            paddingTop = r2.mWidth - r2.getPaddingLeft();
            paddingBottom = r2.getPaddingRight();
        } else {
            AnonymousClass1Ag r22 = this.A02;
            paddingTop = r22.mHeight - r22.getPaddingTop();
            paddingBottom = r22.getPaddingBottom();
        }
        return paddingTop - paddingBottom;
    }

    public final int A07() {
        if (Integer.MIN_VALUE == this.A00) {
            return 0;
        }
        return A06() - this.A00;
    }

    public final int A08(View view) {
        int decoratedBottom;
        int i;
        if (!(this instanceof AnonymousClass1Ax)) {
            decoratedBottom = this.A02.getDecoratedRight(view);
            i = ((C05831Bi) view.getLayoutParams()).rightMargin;
        } else {
            decoratedBottom = this.A02.getDecoratedBottom(view);
            i = ((C05831Bi) view.getLayoutParams()).bottomMargin;
        }
        return decoratedBottom + i;
    }

    public final int A09(View view) {
        int decoratedMeasuredHeight;
        int i;
        if (!(this instanceof AnonymousClass1Ax)) {
            C05831Bi r2 = (C05831Bi) view.getLayoutParams();
            decoratedMeasuredHeight = this.A02.getDecoratedMeasuredWidth(view) + r2.leftMargin;
            i = r2.rightMargin;
        } else {
            C05831Bi r22 = (C05831Bi) view.getLayoutParams();
            decoratedMeasuredHeight = this.A02.getDecoratedMeasuredHeight(view) + r22.topMargin;
            i = r22.bottomMargin;
        }
        return decoratedMeasuredHeight + i;
    }

    public final int A0A(View view) {
        int decoratedMeasuredWidth;
        int i;
        if (!(this instanceof AnonymousClass1Ax)) {
            C05831Bi r2 = (C05831Bi) view.getLayoutParams();
            decoratedMeasuredWidth = this.A02.getDecoratedMeasuredHeight(view) + r2.topMargin;
            i = r2.bottomMargin;
        } else {
            C05831Bi r22 = (C05831Bi) view.getLayoutParams();
            decoratedMeasuredWidth = this.A02.getDecoratedMeasuredWidth(view) + r22.leftMargin;
            i = r22.rightMargin;
        }
        return decoratedMeasuredWidth + i;
    }

    public final int A0B(View view) {
        int decoratedTop;
        int i;
        if (!(this instanceof AnonymousClass1Ax)) {
            decoratedTop = this.A02.getDecoratedLeft(view);
            i = ((C05831Bi) view.getLayoutParams()).leftMargin;
        } else {
            decoratedTop = this.A02.getDecoratedTop(view);
            i = ((C05831Bi) view.getLayoutParams()).topMargin;
        }
        return decoratedTop - i;
    }

    public final int A0C(View view) {
        if (!(this instanceof AnonymousClass1Ax)) {
            AnonymousClass1Ag r2 = this.A02;
            Rect rect = this.A01;
            r2.getTransformedBoundingBox(view, true, rect);
            return rect.right;
        }
        AnonymousClass1Ag r22 = this.A02;
        Rect rect2 = this.A01;
        r22.getTransformedBoundingBox(view, true, rect2);
        return rect2.bottom;
    }

    public final int A0D(View view) {
        if (!(this instanceof AnonymousClass1Ax)) {
            AnonymousClass1Ag r2 = this.A02;
            Rect rect = this.A01;
            r2.getTransformedBoundingBox(view, true, rect);
            return rect.left;
        }
        AnonymousClass1Ag r22 = this.A02;
        Rect rect2 = this.A01;
        r22.getTransformedBoundingBox(view, true, rect2);
        return rect2.top;
    }

    public final void A0E(int i) {
        if (!(this instanceof AnonymousClass1Ax)) {
            this.A02.offsetChildrenHorizontal(i);
        } else {
            this.A02.offsetChildrenVertical(i);
        }
    }

    public AbstractC05861Bm(AnonymousClass1Ag r2) {
        this.A02 = r2;
    }
}
