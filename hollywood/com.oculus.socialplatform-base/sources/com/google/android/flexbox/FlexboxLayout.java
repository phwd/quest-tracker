package com.google.android.flexbox;

import X.AnonymousClass006;
import X.AnonymousClass1E1;
import X.AnonymousClass1E2;
import X.AnonymousClass1E4;
import X.AnonymousClass1E5;
import X.AnonymousClass1E6;
import X.AnonymousClass1E8;
import X.C05881Bo;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayout extends ViewGroup implements AnonymousClass1E6 {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public int A07;
    @Nullable
    public Drawable A08;
    @Nullable
    public Drawable A09;
    public C05881Bo A0A;
    public AnonymousClass1E5 A0B;
    public List<AnonymousClass1E4> A0C;
    public int A0D;
    public int A0E;
    public SparseIntArray A0F;
    public int[] A0G;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new AnonymousClass1E1();
        public float A00 = -1.0f;
        public float A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        public float A02 = 1.0f;
        public int A03 = -1;
        public int A04 = 16777215;
        public int A05 = 16777215;
        public int A06;
        public int A07;
        public int A08 = 1;
        public boolean A09;

        public final int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A3N() {
            return this.A03;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final float A3z() {
            return this.A00;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final float A40() {
            return this.A01;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final float A42() {
            return this.A02;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A44() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4H() {
            return this.bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4I() {
            return this.leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4J() {
            return this.rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4K() {
            return this.topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4L() {
            return this.A04;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4M() {
            return this.A05;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4Q() {
            return this.A06;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A4R() {
            return this.A07;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int A5L() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final boolean A6E() {
            return this.A09;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getOrder() {
            return this.A08;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.A08);
            parcel.writeFloat(this.A01);
            parcel.writeFloat(this.A02);
            parcel.writeInt(this.A03);
            parcel.writeFloat(this.A00);
            parcel.writeInt(this.A07);
            parcel.writeInt(this.A06);
            parcel.writeInt(this.A05);
            parcel.writeInt(this.A04);
            parcel.writeByte(this.A09 ? (byte) 1 : 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.A08 = layoutParams.A08;
            this.A01 = layoutParams.A01;
            this.A02 = layoutParams.A02;
            this.A03 = layoutParams.A03;
            this.A00 = layoutParams.A00;
            this.A07 = layoutParams.A07;
            this.A06 = layoutParams.A06;
            this.A05 = layoutParams.A05;
            this.A04 = layoutParams.A04;
            this.A09 = layoutParams.A09;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass1E2.A01);
            this.A08 = obtainStyledAttributes.getInt(8, 1);
            this.A01 = obtainStyledAttributes.getFloat(2, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            this.A02 = obtainStyledAttributes.getFloat(3, 1.0f);
            this.A03 = obtainStyledAttributes.getInt(0, -1);
            this.A00 = obtainStyledAttributes.getFraction(1, 1, 1, -1.0f);
            this.A07 = obtainStyledAttributes.getDimensionPixelSize(7, 0);
            this.A06 = obtainStyledAttributes.getDimensionPixelSize(6, 0);
            this.A05 = obtainStyledAttributes.getDimensionPixelSize(5, 16777215);
            this.A04 = obtainStyledAttributes.getDimensionPixelSize(4, 16777215);
            this.A09 = obtainStyledAttributes.getBoolean(9, false);
            obtainStyledAttributes.recycle();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(Parcel parcel) {
            super(0, 0);
            boolean z = false;
            this.A08 = parcel.readInt();
            this.A01 = parcel.readFloat();
            this.A02 = parcel.readFloat();
            this.A03 = parcel.readInt();
            this.A00 = parcel.readFloat();
            this.A07 = parcel.readInt();
            this.A06 = parcel.readInt();
            this.A05 = parcel.readInt();
            this.A04 = parcel.readInt();
            this.A09 = parcel.readByte() != 0 ? true : z;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    private boolean A05(int i) {
        int i2;
        int i3;
        int i4;
        if (i < 0 || i >= this.A0C.size()) {
            return false;
        }
        int i5 = 0;
        while (true) {
            if (i5 < i) {
                AnonymousClass1E4 r0 = this.A0C.get(i5);
                if (r0.A0A - r0.A09 > 0) {
                    if (A65()) {
                        i4 = this.A06;
                    } else {
                        i4 = this.A07;
                    }
                    i3 = i4 & 2;
                } else {
                    i5++;
                }
            } else {
                if (A65()) {
                    i2 = this.A06;
                } else {
                    i2 = this.A07;
                }
                i3 = i2 & 1;
            }
        }
        return i3 != 0;
    }

    private boolean A06(int i) {
        int i2;
        if (i >= 0 && i < this.A0C.size()) {
            int i3 = i + 1;
            while (true) {
                if (i3 < this.A0C.size()) {
                    AnonymousClass1E4 r0 = this.A0C.get(i3);
                    if (r0.A0A - r0.A09 > 0) {
                        break;
                    }
                    i3++;
                } else {
                    if (A65()) {
                        i2 = this.A06;
                    } else {
                        i2 = this.A07;
                    }
                    if ((i2 & 4) != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean A07(int i, int i2) {
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6 = 1;
        while (true) {
            if (i6 <= i2) {
                View A002 = A00(i - i6);
                if (A002 != null && A002.getVisibility() != 8) {
                    z = false;
                    break;
                }
                i6++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            if (A65()) {
                i5 = this.A07;
            } else {
                i5 = this.A06;
            }
            i4 = i5 & 1;
        } else {
            if (A65()) {
                i3 = this.A07;
            } else {
                i3 = this.A06;
            }
            i4 = i3 & 2;
        }
        if (i4 != 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1E6
    public final int A3j(View view) {
        return 0;
    }

    @Override // X.AnonymousClass1E6
    public final void AAx(int i, View view) {
    }

    private final View A00(int i) {
        if (i < 0) {
            return null;
        }
        int[] iArr = this.A0G;
        if (i < iArr.length) {
            return getChildAt(iArr[i]);
        }
        return null;
    }

    private void A01(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.A08;
        if (drawable != null) {
            drawable.setBounds(i, i2, i3 + i, this.A01 + i2);
            this.A08.draw(canvas);
        }
    }

    private void A02(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.A09;
        if (drawable != null) {
            drawable.setBounds(i, i2, this.A02 + i, i3 + i2);
            this.A09.draw(canvas);
        }
    }

    private void A04(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        int i5;
        int i6;
        int i7;
        boolean z3;
        int round;
        int measuredWidth;
        int measuredHeight;
        int i8;
        AnonymousClass1E4 r15;
        int i9;
        int i10;
        int i11;
        AnonymousClass1E5 r152;
        int measuredWidth2;
        int round2;
        int measuredHeight2;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i12 = i4 - i2;
        int i13 = (i3 - i) - paddingRight;
        int size = this.A0C.size();
        for (int i14 = 0; i14 < size; i14++) {
            AnonymousClass1E4 r1 = this.A0C.get(i14);
            if (A05(i14)) {
                int i15 = this.A02;
                paddingLeft += i15;
                i13 -= i15;
            }
            int i16 = this.A05;
            if (i16 == 0) {
                f2 = (float) paddingTop;
                i5 = i12 - paddingBottom;
                f = (float) i5;
                f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            } else if (i16 == 1) {
                int i17 = r1.A0C;
                f2 = (float) ((i12 - i17) + paddingBottom);
                i5 = i17 - paddingTop;
                f = (float) i5;
                f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            } else if (i16 == 2) {
                float f4 = ((float) (i12 - r1.A0C)) / 2.0f;
                f2 = ((float) paddingTop) + f4;
                f = ((float) (i12 - paddingBottom)) - f4;
                f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            } else if (i16 == 3) {
                f2 = (float) paddingTop;
                int i18 = r1.A0A - r1.A09;
                float f5 = 1.0f;
                if (i18 != 1) {
                    f5 = (float) (i18 - 1);
                }
                f3 = ((float) (i12 - r1.A0C)) / f5;
                f = (float) (i12 - paddingBottom);
            } else if (i16 == 4) {
                int i19 = r1.A0A - r1.A09;
                if (i19 != 0) {
                    f3 = ((float) (i12 - r1.A0C)) / ((float) i19);
                } else {
                    f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                }
                float f6 = f3 / 2.0f;
                f2 = ((float) paddingTop) + f6;
                f = ((float) (i12 - paddingBottom)) - f6;
            } else if (i16 == 5) {
                int i20 = r1.A0A - r1.A09;
                if (i20 != 0) {
                    f3 = ((float) (i12 - r1.A0C)) / ((float) (i20 + 1));
                } else {
                    f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                }
                f2 = ((float) paddingTop) + f3;
                f = ((float) (i12 - paddingBottom)) - f3;
            } else {
                throw new IllegalStateException(AnonymousClass006.A03("Invalid justifyContent is set: ", i16));
            }
            float max = Math.max(f3, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            for (int i21 = 0; i21 < r1.A0A; i21++) {
                int i22 = r1.A08 + i21;
                View A002 = A00(i22);
                if (!(A002 == null || A002.getVisibility() == 8)) {
                    LayoutParams layoutParams = (LayoutParams) A002.getLayoutParams();
                    float f7 = f2 + ((float) layoutParams.topMargin);
                    float f8 = f - ((float) layoutParams.bottomMargin);
                    if (A07(i22, i21)) {
                        i6 = this.A01;
                        float f9 = (float) i6;
                        f7 += f9;
                        f8 -= f9;
                    } else {
                        i6 = 0;
                    }
                    if (i21 != r1.A0A - 1 || (this.A06 & 4) <= 0) {
                        i7 = 0;
                    } else {
                        i7 = this.A01;
                    }
                    if (z) {
                        if (z2) {
                            r152 = this.A0B;
                            measuredWidth2 = i13 - A002.getMeasuredWidth();
                            measuredHeight2 = Math.round(f8);
                            round2 = measuredHeight2 - A002.getMeasuredHeight();
                        } else {
                            r152 = this.A0B;
                            measuredWidth2 = i13 - A002.getMeasuredWidth();
                            round2 = Math.round(f7);
                            measuredHeight2 = round2 + A002.getMeasuredHeight();
                        }
                        r152.A0H(A002, r1, true, measuredWidth2, round2, i13, measuredHeight2);
                    } else {
                        AnonymousClass1E5 r8 = this.A0B;
                        if (z2) {
                            z3 = false;
                            measuredHeight = Math.round(f8);
                            round = measuredHeight - A002.getMeasuredHeight();
                            measuredWidth = paddingLeft + A002.getMeasuredWidth();
                        } else {
                            z3 = false;
                            round = Math.round(f7);
                            measuredWidth = paddingLeft + A002.getMeasuredWidth();
                            measuredHeight = round + A002.getMeasuredHeight();
                        }
                        r8.A0H(A002, r1, z3, paddingLeft, round, measuredWidth, measuredHeight);
                    }
                    f2 = f7 + ((float) A002.getMeasuredHeight()) + max + ((float) layoutParams.bottomMargin);
                    f = f8 - ((((float) A002.getMeasuredHeight()) + max) + ((float) layoutParams.topMargin));
                    if (z2) {
                        i8 = 0;
                        r15 = r1;
                        i9 = i7;
                        i10 = 0;
                        i11 = i6;
                    } else {
                        i8 = 0;
                        r15 = r1;
                        i9 = i6;
                        i10 = 0;
                        i11 = i7;
                    }
                    r15.A00(A002, i8, i9, i10, i11);
                }
            }
            int i23 = r1.A07;
            paddingLeft += i23;
            i13 -= i23;
        }
    }

    @Override // X.AnonymousClass1E6
    public final boolean A65() {
        int i = this.A03;
        if (i == 0 || i == 1) {
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        SparseIntArray sparseIntArray = this.A0F;
        if (sparseIntArray == null) {
            sparseIntArray = new SparseIntArray(getChildCount());
            this.A0F = sparseIntArray;
        }
        AnonymousClass1E5 r0 = this.A0B;
        AnonymousClass1E6 r1 = r0.A04;
        int flexItemCount = r1.getFlexItemCount();
        List A022 = AnonymousClass1E5.A02(r0, flexItemCount);
        AnonymousClass1E8 r3 = new AnonymousClass1E8();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            r3.A01 = 1;
        } else {
            r3.A01 = ((FlexItem) layoutParams).getOrder();
        }
        if (i == -1 || i == flexItemCount || i >= r1.getFlexItemCount()) {
            r3.A00 = flexItemCount;
        } else {
            r3.A00 = i;
            for (int i2 = i; i2 < flexItemCount; i2++) {
                ((AnonymousClass1E8) A022.get(i2)).A00++;
            }
        }
        A022.add(r3);
        this.A0G = AnonymousClass1E5.A09(flexItemCount + 1, A022, sparseIntArray);
        super.addView(view, i, layoutParams);
    }

    @Override // X.AnonymousClass1E6
    public int getAlignContent() {
        return this.A0D;
    }

    @Override // X.AnonymousClass1E6
    public int getAlignItems() {
        return this.A00;
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.A08;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.A09;
    }

    @Override // X.AnonymousClass1E6
    public int getFlexDirection() {
        return this.A03;
    }

    public List<AnonymousClass1E4> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.A0C.size());
        for (AnonymousClass1E4 r2 : this.A0C) {
            if (r2.A0A - r2.A09 != 0) {
                arrayList.add(r2);
            }
        }
        return arrayList;
    }

    @Override // X.AnonymousClass1E6
    public List<AnonymousClass1E4> getFlexLinesInternal() {
        return this.A0C;
    }

    @Override // X.AnonymousClass1E6
    public int getFlexWrap() {
        return this.A04;
    }

    public int getJustifyContent() {
        return this.A05;
    }

    @Override // X.AnonymousClass1E6
    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (AnonymousClass1E4 r0 : this.A0C) {
            i = Math.max(i, r0.A0C);
        }
        return i;
    }

    @Override // X.AnonymousClass1E6
    public int getMaxLine() {
        return this.A0E;
    }

    public int getShowDividerHorizontal() {
        return this.A06;
    }

    public int getShowDividerVertical() {
        return this.A07;
    }

    @Override // X.AnonymousClass1E6
    public int getSumOfCrossSize() {
        int i;
        int i2;
        int size = this.A0C.size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            AnonymousClass1E4 r1 = this.A0C.get(i4);
            if (A05(i4)) {
                if (A65()) {
                    i2 = this.A01;
                } else {
                    i2 = this.A02;
                }
                i3 += i2;
            }
            if (A06(i4)) {
                if (A65()) {
                    i = this.A01;
                } else {
                    i = this.A02;
                }
                i3 += i;
            }
            i3 += r1.A07;
        }
        return i3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0031, code lost:
        if (r4 == 1) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0036, code lost:
        if (r4 != 1) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0038, code lost:
        r12 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onDraw(android.graphics.Canvas r14) {
        /*
        // Method dump skipped, instructions count: 262
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onDraw(android.graphics.Canvas):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r5 == 1) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (r5 != 1) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        r16 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r34, int r35, int r36, int r37, int r38) {
        /*
        // Method dump skipped, instructions count: 493
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01b2, code lost:
        if (r9 < r10) goto L_0x01b4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r22, int r23) {
        /*
        // Method dump skipped, instructions count: 511
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onMeasure(int, int):void");
    }

    public void setAlignContent(int i) {
        if (this.A0D != i) {
            this.A0D = i;
            requestLayout();
        }
    }

    public void setAlignItems(int i) {
        if (this.A00 != i) {
            this.A00 = i;
            requestLayout();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        if (r2.A09 != null) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDividerDrawableHorizontal(@androidx.annotation.Nullable android.graphics.drawable.Drawable r3) {
        /*
            r2 = this;
            android.graphics.drawable.Drawable r0 = r2.A08
            if (r3 == r0) goto L_0x001e
            r2.A08 = r3
            if (r3 == 0) goto L_0x001f
            int r0 = r3.getIntrinsicHeight()
        L_0x000c:
            r2.A01 = r0
            android.graphics.drawable.Drawable r0 = r2.A08
            if (r0 != 0) goto L_0x0017
            android.graphics.drawable.Drawable r1 = r2.A09
            r0 = 1
            if (r1 == 0) goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            r2.setWillNotDraw(r0)
            r2.requestLayout()
        L_0x001e:
            return
        L_0x001f:
            r0 = 0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.setDividerDrawableHorizontal(android.graphics.drawable.Drawable):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        if (r2.A09 != null) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDividerDrawableVertical(@androidx.annotation.Nullable android.graphics.drawable.Drawable r3) {
        /*
            r2 = this;
            android.graphics.drawable.Drawable r0 = r2.A09
            if (r3 == r0) goto L_0x001e
            r2.A09 = r3
            if (r3 == 0) goto L_0x001f
            int r0 = r3.getIntrinsicWidth()
        L_0x000c:
            r2.A02 = r0
            android.graphics.drawable.Drawable r0 = r2.A08
            if (r0 != 0) goto L_0x0017
            android.graphics.drawable.Drawable r1 = r2.A09
            r0 = 1
            if (r1 == 0) goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            r2.setWillNotDraw(r0)
            r2.requestLayout()
        L_0x001e:
            return
        L_0x001f:
            r0 = 0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.setDividerDrawableVertical(android.graphics.drawable.Drawable):void");
    }

    public void setFlexDirection(int i) {
        if (this.A03 != i) {
            this.A03 = i;
            requestLayout();
        }
    }

    public void setFlexWrap(int i) {
        if (this.A04 != i) {
            this.A04 = i;
            requestLayout();
        }
    }

    public void setJustifyContent(int i) {
        if (this.A05 != i) {
            this.A05 = i;
            requestLayout();
        }
    }

    public void setMaxLine(int i) {
        if (this.A0E != i) {
            this.A0E = i;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.A06) {
            this.A06 = i;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i) {
        if (i != this.A07) {
            this.A07 = i;
            requestLayout();
        }
    }

    private void A03(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int bottom;
        int top;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.A0C.size();
        for (int i3 = 0; i3 < size; i3++) {
            AnonymousClass1E4 r6 = this.A0C.get(i3);
            for (int i4 = 0; i4 < r6.A0A; i4++) {
                int i5 = r6.A08 + i4;
                View A002 = A00(i5);
                if (!(A002 == null || A002.getVisibility() == 8)) {
                    LayoutParams layoutParams = (LayoutParams) A002.getLayoutParams();
                    if (A07(i5, i4)) {
                        if (z2) {
                            top = A002.getBottom() + layoutParams.bottomMargin;
                        } else {
                            top = (A002.getTop() - layoutParams.topMargin) - this.A01;
                        }
                        A01(canvas, r6.A03, top, r6.A07);
                    }
                    if (i4 == r6.A0A - 1 && (this.A06 & 4) > 0) {
                        if (z2) {
                            bottom = (A002.getTop() - layoutParams.topMargin) - this.A01;
                        } else {
                            bottom = A002.getBottom() + layoutParams.bottomMargin;
                        }
                        A01(canvas, r6.A03, bottom, r6.A07);
                    }
                }
            }
            if (A05(i3)) {
                if (z) {
                    i2 = r6.A04;
                } else {
                    i2 = r6.A03 - this.A02;
                }
                A02(canvas, i2, paddingTop, max);
            }
            if (A06(i3) && (this.A07 & 4) > 0) {
                if (z) {
                    i = r6.A03 - this.A02;
                } else {
                    i = r6.A04;
                }
                A02(canvas, i, paddingTop, max);
            }
        }
    }

    @Override // X.AnonymousClass1E6
    public final int A3X(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    @Override // X.AnonymousClass1E6
    public final int A3a(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    @Override // X.AnonymousClass1E6
    public final int A3k(View view, int i, int i2) {
        int i3;
        int i4 = 0;
        if (A65()) {
            if (A07(i, i2)) {
                i4 = 0 + this.A02;
            }
            if ((this.A07 & 4) <= 0) {
                return i4;
            }
            i3 = this.A02;
        } else {
            if (A07(i, i2)) {
                i4 = 0 + this.A01;
            }
            if ((this.A06 & 4) <= 0) {
                return i4;
            }
            i3 = this.A01;
        }
        return i4 + i3;
    }

    @Override // X.AnonymousClass1E6
    public final View A41(int i) {
        return getChildAt(i);
    }

    @Override // X.AnonymousClass1E6
    public final View A4k(int i) {
        return A00(i);
    }

    @Override // X.AnonymousClass1E6
    public final void A7Q(View view, int i, int i2, AnonymousClass1E4 r6) {
        int i3;
        int i4;
        if (A07(i, i2)) {
            if (A65()) {
                i3 = r6.A0C;
                i4 = this.A02;
            } else {
                i3 = r6.A0C;
                i4 = this.A01;
            }
            r6.A0C = i3 + i4;
            r6.A02 += i4;
        }
    }

    @Override // X.AnonymousClass1E6
    public final void A7R(AnonymousClass1E4 r3) {
        int i;
        int i2;
        if (A65()) {
            if ((this.A07 & 4) > 0) {
                i = r3.A0C;
                i2 = this.A02;
            } else {
                return;
            }
        } else if ((this.A06 & 4) > 0) {
            i = r3.A0C;
            i2 = this.A01;
        } else {
            return;
        }
        r3.A0C = i + i2;
        r3.A02 += i2;
    }

    @Override // X.AnonymousClass1E6
    public int getFlexItemCount() {
        return getChildCount();
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // X.AnonymousClass1E6
    public void setFlexLines(List<AnonymousClass1E4> list) {
        this.A0C = list;
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A0E = -1;
        this.A0B = new AnonymousClass1E5(this);
        this.A0C = new ArrayList();
        this.A0A = new C05881Bo();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass1E2.A00, i, 0);
        this.A03 = obtainStyledAttributes.getInt(5, 0);
        this.A04 = obtainStyledAttributes.getInt(6, 0);
        this.A05 = obtainStyledAttributes.getInt(7, 0);
        this.A00 = obtainStyledAttributes.getInt(1, 4);
        this.A0D = obtainStyledAttributes.getInt(0, 5);
        this.A0E = obtainStyledAttributes.getInt(8, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(3);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(4);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(9, 0);
        if (i2 != 0) {
            this.A07 = i2;
            this.A06 = i2;
        }
        int i3 = obtainStyledAttributes.getInt(11, 0);
        if (i3 != 0) {
            this.A07 = i3;
        }
        int i4 = obtainStyledAttributes.getInt(10, 0);
        if (i4 != 0) {
            this.A06 = i4;
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }
}
