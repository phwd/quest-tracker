package com.google.android.flexbox;

import X.AbstractC05861Bm;
import X.AnonymousClass1Af;
import X.AnonymousClass1Ag;
import X.AnonymousClass1Aj;
import X.AnonymousClass1An;
import X.AnonymousClass1Ao;
import X.AnonymousClass1As;
import X.AnonymousClass1Aw;
import X.AnonymousClass1Ax;
import X.AnonymousClass1BR;
import X.AnonymousClass1CA;
import X.AnonymousClass1CB;
import X.AnonymousClass1E4;
import X.AnonymousClass1E5;
import X.AnonymousClass1E6;
import X.C05831Bi;
import X.C05851Bl;
import X.C05871Bn;
import X.C05881Bo;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayoutManager extends AnonymousClass1Ag implements AnonymousClass1E6, AnonymousClass1BR {
    public static final Rect A0O = new Rect();
    public int A00;
    public int A01 = -1;
    public int A02;
    public int A03;
    public int A04 = Integer.MIN_VALUE;
    public int A05 = Integer.MIN_VALUE;
    public int A06 = -1;
    public int A07 = Integer.MIN_VALUE;
    public AbstractC05861Bm A08;
    public AbstractC05861Bm A09;
    public C05881Bo A0A = new C05881Bo();
    public C05851Bl A0B = new C05851Bl(this);
    public C05871Bn A0C;
    public SavedState A0D;
    public List<AnonymousClass1E4> A0E = new ArrayList();
    public boolean A0F;
    public boolean A0G;
    public int A0H = -1;
    public SparseArray<View> A0I = new SparseArray<>();
    public View A0J;
    public AnonymousClass1Af A0K;
    public AnonymousClass1As A0L;
    public final Context A0M;
    public final AnonymousClass1E5 A0N = new AnonymousClass1E5(this);

    public static class LayoutParams extends C05831Bi implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new AnonymousClass1CA();
        public float A00 = -1.0f;
        public float A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        public float A02 = 1.0f;
        public int A03 = -1;
        public int A04 = 16777215;
        public int A05 = 16777215;
        public int A06;
        public int A07;
        public boolean A08;

        public final int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getOrder() {
            return 1;
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
            return this.A08;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.A01);
            parcel.writeFloat(this.A02);
            parcel.writeInt(this.A03);
            parcel.writeFloat(this.A00);
            parcel.writeInt(this.A07);
            parcel.writeInt(this.A06);
            parcel.writeInt(this.A05);
            parcel.writeInt(this.A04);
            parcel.writeByte(this.A08 ? (byte) 1 : 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        public LayoutParams() {
            super(-2, -2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.A01 = parcel.readFloat();
            this.A02 = parcel.readFloat();
            this.A03 = parcel.readInt();
            this.A00 = parcel.readFloat();
            this.A07 = parcel.readInt();
            this.A06 = parcel.readInt();
            this.A05 = parcel.readInt();
            this.A04 = parcel.readInt();
            this.A08 = parcel.readByte() != 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1CB();
        public int A00;
        public int A01;

        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("SavedState{mAnchorPosition=");
            sb.append(this.A01);
            sb.append(", mAnchorOffset=");
            sb.append(this.A00);
            sb.append('}');
            return sb.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.A01);
            parcel.writeInt(this.A00);
        }

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.A01 = parcel.readInt();
            this.A00 = parcel.readInt();
        }

        public SavedState(SavedState savedState) {
            this.A01 = savedState.A01;
            this.A00 = savedState.A00;
        }
    }

    private View A08(int i) {
        int i2;
        View A0B2 = A0B(0, getChildCount(), i);
        if (A0B2 == null || (i2 = this.A0N.A00[getPosition(A0B2)]) == -1) {
            return null;
        }
        return A0C(A0B2, this.A0E.get(i2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005d, code lost:
        if (r2 >= r6) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0057, code lost:
        if (r3 >= r8) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View A0A(int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.A0A(int, int):android.view.View");
    }

    @Override // X.AnonymousClass1E6
    public final void A7R(AnonymousClass1E4 r1) {
    }

    @Override // X.AnonymousClass1E6
    public final int getAlignContent() {
        return 5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        if (r26.A0G == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01d0, code lost:
        if (r27 > 0) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01d4, code lost:
        r6 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (r27 < 0) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int A01(int r27, X.AnonymousClass1Af r28, X.AnonymousClass1As r29) {
        /*
        // Method dump skipped, instructions count: 472
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.A01(int, X.1Af, X.1As):int");
    }

    private int A04(AnonymousClass1Af r34, AnonymousClass1As r35, C05871Bn r36) {
        int i;
        int i2;
        int i3;
        int measuredHeight;
        int round;
        int measuredWidth;
        int i4 = r36.A07;
        if (i4 != Integer.MIN_VALUE) {
            int i5 = r36.A00;
            if (i5 < 0) {
                r36.A07 = i4 + i5;
            }
            A0I(r34, r36);
        }
        int i6 = r36.A00;
        int i7 = i6;
        int i8 = 0;
        boolean A65 = A65();
        while (true) {
            if (i7 <= 0 && !this.A0C.A08) {
                break;
            }
            List<AnonymousClass1E4> list = this.A0E;
            int i9 = r36.A06;
            if (i9 < 0 || i9 >= r35.A00() || (i = r36.A01) < 0 || i >= list.size()) {
                break;
            }
            AnonymousClass1E4 r9 = this.A0E.get(r36.A01);
            r36.A06 = r9.A08;
            if (A65()) {
                int paddingLeft = getPaddingLeft();
                int paddingRight = getPaddingRight();
                int i10 = this.mWidth;
                int i11 = r36.A05;
                if (r36.A04 == -1) {
                    i11 -= r9.A07;
                }
                int i12 = r36.A06;
                int i13 = 1;
                float f = (float) this.A0B.A02;
                float f2 = ((float) paddingLeft) - f;
                float f3 = ((float) (i10 - paddingRight)) - f;
                float max = Math.max((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                int i14 = 0;
                int i15 = r9.A0A;
                int i16 = i12;
                while (i16 < i12 + i15) {
                    View A41 = A41(i16);
                    if (A41 != null) {
                        if (r36.A04 == i13) {
                            calculateItemDecorationsForChild(A41, A0O);
                            addView(A41, -1);
                        } else {
                            calculateItemDecorationsForChild(A41, A0O);
                            addView(A41, i14);
                            i14++;
                        }
                        AnonymousClass1E5 r11 = this.A0N;
                        long j = r11.A01[i16];
                        int i17 = (int) j;
                        int i18 = (int) (j >> 32);
                        LayoutParams layoutParams = (LayoutParams) A41.getLayoutParams();
                        if (A0M(A41, i17, i18, layoutParams)) {
                            A41.measure(i17, i18);
                        }
                        float leftDecorationWidth = f2 + ((float) (layoutParams.leftMargin + getLeftDecorationWidth(A41)));
                        float rightDecorationWidth = f3 - ((float) (layoutParams.rightMargin + getRightDecorationWidth(A41)));
                        int topDecorationHeight = i11 + getTopDecorationHeight(A41);
                        if (this.A0G) {
                            measuredWidth = Math.round(rightDecorationWidth);
                            round = measuredWidth - A41.getMeasuredWidth();
                        } else {
                            round = Math.round(leftDecorationWidth);
                            measuredWidth = round + A41.getMeasuredWidth();
                        }
                        r11.A0G(A41, r9, round, topDecorationHeight, measuredWidth, topDecorationHeight + A41.getMeasuredHeight());
                        f2 = leftDecorationWidth + ((float) (A41.getMeasuredWidth() + layoutParams.rightMargin + getRightDecorationWidth(A41))) + max;
                        f3 = rightDecorationWidth - (((float) ((A41.getMeasuredWidth() + layoutParams.leftMargin) + getLeftDecorationWidth(A41))) + max);
                    }
                    i16++;
                    i13 = 1;
                }
            } else {
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int i19 = this.mHeight;
                int i20 = r36.A05;
                int i21 = i20;
                if (r36.A04 == -1) {
                    int i22 = r9.A07;
                    i20 -= i22;
                    i21 = i20 + i22;
                }
                int i23 = r36.A06;
                float f4 = (float) this.A0B.A02;
                float f5 = ((float) paddingTop) - f4;
                float f6 = ((float) (i19 - paddingBottom)) - f4;
                float max2 = Math.max((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                int i24 = 0;
                int i25 = r9.A0A;
                for (int i26 = i23; i26 < i23 + i25; i26++) {
                    View A412 = A41(i26);
                    if (A412 != null) {
                        AnonymousClass1E5 r12 = this.A0N;
                        long j2 = r12.A01[i26];
                        int i27 = (int) j2;
                        int i28 = (int) (j2 >> 32);
                        LayoutParams layoutParams2 = (LayoutParams) A412.getLayoutParams();
                        if (A0M(A412, i27, i28, layoutParams2)) {
                            A412.measure(i27, i28);
                        }
                        float topDecorationHeight2 = f5 + ((float) (layoutParams2.topMargin + getTopDecorationHeight(A412)));
                        float bottomDecorationHeight = f6 - ((float) (layoutParams2.rightMargin + getBottomDecorationHeight(A412)));
                        if (r36.A04 == 1) {
                            calculateItemDecorationsForChild(A412, A0O);
                            addView(A412, -1);
                        } else {
                            calculateItemDecorationsForChild(A412, A0O);
                            addView(A412, i24);
                            i24++;
                        }
                        int leftDecorationWidth2 = i20 + getLeftDecorationWidth(A412);
                        int rightDecorationWidth2 = i21 - getRightDecorationWidth(A412);
                        boolean z = this.A0G;
                        if (z) {
                            if (this.A0F) {
                                leftDecorationWidth2 = rightDecorationWidth2 - A412.getMeasuredWidth();
                                measuredHeight = Math.round(bottomDecorationHeight);
                                i3 = measuredHeight - A412.getMeasuredHeight();
                            } else {
                                leftDecorationWidth2 = rightDecorationWidth2 - A412.getMeasuredWidth();
                                i3 = Math.round(topDecorationHeight2);
                                measuredHeight = i3 + A412.getMeasuredHeight();
                            }
                        } else if (this.A0F) {
                            measuredHeight = Math.round(bottomDecorationHeight);
                            i3 = measuredHeight - A412.getMeasuredHeight();
                            rightDecorationWidth2 = leftDecorationWidth2 + A412.getMeasuredWidth();
                        } else {
                            i3 = Math.round(topDecorationHeight2);
                            rightDecorationWidth2 = leftDecorationWidth2 + A412.getMeasuredWidth();
                            measuredHeight = i3 + A412.getMeasuredHeight();
                        }
                        r12.A0H(A412, r9, z, leftDecorationWidth2, i3, rightDecorationWidth2, measuredHeight);
                        f5 = topDecorationHeight2 + ((float) (A412.getMeasuredHeight() + layoutParams2.topMargin + getBottomDecorationHeight(A412))) + max2;
                        f6 = bottomDecorationHeight - (((float) ((A412.getMeasuredHeight() + layoutParams2.bottomMargin) + getTopDecorationHeight(A412))) + max2);
                    }
                }
            }
            r36.A01 += this.A0C.A04;
            int i29 = r9.A07;
            i8 += i29;
            if (A65 || !this.A0G) {
                i2 = r36.A05 + (r36.A04 * i29);
            } else {
                i2 = r36.A05 - (r36.A04 * i29);
            }
            r36.A05 = i2;
            i7 -= i29;
        }
        int i30 = r36.A00 - i8;
        r36.A00 = i30;
        int i31 = r36.A07;
        if (i31 != Integer.MIN_VALUE) {
            int i32 = i31 + i8;
            r36.A07 = i32;
            if (i30 < 0) {
                r36.A07 = i32 + i30;
            }
            A0I(r34, r36);
        }
        return i6 - r36.A00;
    }

    private void A0E() {
        if (this.A08 != null) {
            return;
        }
        if (!A65() ? this.A03 != 0 : this.A03 == 0) {
            this.A08 = new AnonymousClass1Aw(this);
            this.A09 = new AnonymousClass1Ax(this);
            return;
        }
        this.A08 = new AnonymousClass1Ax(this);
        this.A09 = new AnonymousClass1Aw(this);
    }

    private final void A0H(int i) {
        if (this.A02 != i) {
            removeAllViews();
            this.A02 = i;
            this.A08 = null;
            this.A09 = null;
            this.A0E.clear();
            C05851Bl r1 = this.A0B;
            C05851Bl.A01(r1);
            r1.A02 = 0;
            requestLayout();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0071, code lost:
        r2 = r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0I(X.AnonymousClass1Af r11, X.C05871Bn r12) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.A0I(X.1Af, X.1Bn):void");
    }

    private void A0J(C05851Bl r5, boolean z, boolean z2) {
        C05871Bn r2;
        int A022;
        int i;
        int i2;
        if (z2) {
            A0F();
        } else {
            this.A0C.A08 = false;
        }
        if (A65() || !this.A0G) {
            r2 = this.A0C;
            A022 = this.A08.A02();
            i = r5.A00;
        } else {
            r2 = this.A0C;
            A022 = r5.A00;
            i = getPaddingRight();
        }
        r2.A00 = A022 - i;
        C05871Bn r1 = this.A0C;
        r1.A06 = r5.A03;
        r1.A02 = 1;
        r1.A04 = 1;
        r1.A05 = r5.A00;
        r1.A07 = Integer.MIN_VALUE;
        r1.A01 = r5.A01;
        if (z && this.A0E.size() > 1 && (i2 = r5.A01) >= 0 && i2 < this.A0E.size() - 1) {
            C05871Bn r22 = this.A0C;
            r22.A01++;
            r22.A06 += this.A0E.get(r5.A01).A0A;
        }
    }

    private void A0K(C05851Bl r5, boolean z, boolean z2) {
        C05871Bn r2;
        int i;
        int i2;
        if (z2) {
            A0F();
        } else {
            this.A0C.A08 = false;
        }
        if (A65() || !this.A0G) {
            r2 = this.A0C;
            i = r5.A00;
        } else {
            r2 = this.A0C;
            i = this.A0J.getWidth() - r5.A00;
        }
        r2.A00 = i - this.A08.A05();
        C05871Bn r1 = this.A0C;
        r1.A06 = r5.A03;
        r1.A02 = 1;
        r1.A04 = -1;
        r1.A05 = r5.A00;
        r1.A07 = Integer.MIN_VALUE;
        int i3 = r5.A01;
        r1.A01 = i3;
        if (z && i3 > 0 && this.A0E.size() > (i2 = r5.A01)) {
            C05871Bn r22 = this.A0C;
            r22.A01--;
            r22.A06 -= this.A0E.get(i2).A0A;
        }
    }

    @Override // X.AnonymousClass1E6
    public final int A3X(int i, int i2, int i3) {
        return AnonymousClass1Ag.getChildMeasureSpec(this.mHeight, this.mHeightMode, i2, i3, canScrollVertically());
    }

    @Override // X.AnonymousClass1E6
    public final int A3a(int i, int i2, int i3) {
        return AnonymousClass1Ag.getChildMeasureSpec(this.mWidth, this.mWidthMode, i2, i3, canScrollHorizontally());
    }

    @Override // X.AnonymousClass1E6
    public final View A41(int i) {
        View view = this.A0I.get(i);
        if (view == null) {
            return this.A0K.A04(i, RecyclerView.FOREVER_NS).itemView;
        }
        return view;
    }

    @Override // X.AnonymousClass1E6
    public final boolean A65() {
        int i = this.A02;
        if (i == 0 || i == 1) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1E6
    public final void A7Q(View view, int i, int i2, AnonymousClass1E4 r6) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        calculateItemDecorationsForChild(view, A0O);
        if (A65()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        int i3 = topDecorationHeight + bottomDecorationHeight;
        r6.A0C += i3;
        r6.A02 += i3;
    }

    @Override // X.AnonymousClass1E6
    public final void AAx(int i, View view) {
        this.A0I.put(i, view);
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // X.AnonymousClass1E6
    public final int getAlignItems() {
        return this.A00;
    }

    @Override // X.AnonymousClass1E6
    public final int getFlexDirection() {
        return this.A02;
    }

    @Override // X.AnonymousClass1E6
    public final int getFlexItemCount() {
        return this.A0L.A00();
    }

    @Override // X.AnonymousClass1E6
    public final List<AnonymousClass1E4> getFlexLinesInternal() {
        return this.A0E;
    }

    @Override // X.AnonymousClass1E6
    public final int getFlexWrap() {
        return this.A03;
    }

    @Override // X.AnonymousClass1E6
    public final int getLargestMainSize() {
        if (this.A0E.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.A0E.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, this.A0E.get(i2).A0C);
        }
        return i;
    }

    @Override // X.AnonymousClass1E6
    public final int getMaxLine() {
        return this.A0H;
    }

    @Override // X.AnonymousClass1E6
    public final int getSumOfCrossSize() {
        int size = this.A0E.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.A0E.get(i2).A07;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03d9, code lost:
        if (r9 != 1) goto L_0x03de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x03dc, code lost:
        if (r9 == 1) goto L_0x03de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03de, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x03df, code lost:
        r22.A0G = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03e3, code lost:
        if (r22.A03 != 2) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x03e5, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x03e8, code lost:
        r0 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0333  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0345  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x037b  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0385  */
    /* JADX WARNING: Removed duplicated region for block: B:197:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01f1  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0248  */
    @Override // X.AnonymousClass1Ag
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayoutChildren(X.AnonymousClass1Af r23, X.AnonymousClass1As r24) {
        /*
        // Method dump skipped, instructions count: 1011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.onLayoutChildren(X.1Af, X.1As):void");
    }

    @Override // X.AnonymousClass1Ag
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.A0D = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // X.AnonymousClass1Ag
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = this.A0D;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            savedState2.A01 = getPosition(childAt);
            savedState2.A00 = this.A08.A0B(childAt) - this.A08.A05();
            return savedState2;
        }
        savedState2.A01 = -1;
        return savedState2;
    }

    @Override // X.AnonymousClass1Ag
    public final void scrollToPosition(int i) {
        this.A06 = i;
        this.A07 = Integer.MIN_VALUE;
        SavedState savedState = this.A0D;
        if (savedState != null) {
            savedState.A01 = -1;
        }
        requestLayout();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FlexboxLayoutManager(android.content.Context r5, android.util.AttributeSet r6, int r7, int r8) {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.<init>(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    private int A00(int i) {
        int height;
        int i2;
        int i3;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        A0E();
        boolean A65 = A65();
        View view = this.A0J;
        if (A65) {
            height = view.getWidth();
            i2 = this.mWidth;
        } else {
            height = view.getHeight();
            i2 = this.mHeight;
        }
        if (this.mRecyclerView.getLayoutDirection() == 1) {
            int abs = Math.abs(i);
            if (i < 0) {
                i3 = Math.min((i2 + this.A0B.A02) - height, abs);
            } else {
                i3 = this.A0B.A02;
                if (i3 + i <= 0) {
                    return i;
                }
            }
        } else if (i > 0) {
            return Math.min((i2 - this.A0B.A02) - height, i);
        } else {
            i3 = this.A0B.A02;
            if (i3 + i >= 0) {
                return i;
            }
        }
        return -i3;
    }

    private int A02(int i, AnonymousClass1Af r5, AnonymousClass1As r6, boolean z) {
        int i2;
        int A022;
        if (A65() || !this.A0G) {
            int A023 = this.A08.A02() - i;
            if (A023 <= 0) {
                return 0;
            }
            i2 = -A01(-A023, r5, r6);
        } else {
            int A052 = i - this.A08.A05();
            if (A052 <= 0) {
                return 0;
            }
            i2 = A01(A052, r5, r6);
        }
        int i3 = i + i2;
        if (!z || (A022 = this.A08.A02() - i3) <= 0) {
            return i2;
        }
        this.A08.A0E(A022);
        return A022 + i2;
    }

    private int A03(int i, AnonymousClass1Af r5, AnonymousClass1As r6, boolean z) {
        int i2;
        int A052;
        if (A65() || !this.A0G) {
            int A053 = i - this.A08.A05();
            if (A053 > 0) {
                i2 = -A01(A053, r5, r6);
            }
            return 0;
        }
        int A022 = this.A08.A02() - i;
        if (A022 > 0) {
            i2 = A01(-A022, r5, r6);
        }
        return 0;
        int i3 = i + i2;
        if (!z || (A052 = i3 - this.A08.A05()) <= 0) {
            return i2;
        }
        this.A08.A0E(-A052);
        return i2 - A052;
    }

    private int A05(AnonymousClass1As r5) {
        if (getChildCount() != 0) {
            int A002 = r5.A00();
            A0E();
            View A082 = A08(A002);
            View A092 = A09(A002);
            if (!(r5.A00() == 0 || A082 == null || A092 == null)) {
                return Math.min(this.A08.A06(), this.A08.A08(A092) - this.A08.A0B(A082));
            }
        }
        return 0;
    }

    private int A06(AnonymousClass1As r8) {
        if (getChildCount() != 0) {
            int A002 = r8.A00();
            View A082 = A08(A002);
            View A092 = A09(A002);
            if (!(r8.A00() == 0 || A082 == null || A092 == null)) {
                int position = getPosition(A082);
                int position2 = getPosition(A092);
                int abs = Math.abs(this.A08.A08(A092) - this.A08.A0B(A082));
                int[] iArr = this.A0N.A00;
                int i = iArr[position];
                if (!(i == 0 || i == -1)) {
                    return Math.round((((float) i) * (((float) abs) / ((float) ((iArr[position2] - i) + 1)))) + ((float) (this.A08.A05() - this.A08.A0B(A082))));
                }
            }
        }
        return 0;
    }

    private int A07(AnonymousClass1As r6) {
        int position;
        if (getChildCount() != 0) {
            int A002 = r6.A00();
            View A082 = A08(A002);
            View A092 = A09(A002);
            if (!(r6.A00() == 0 || A082 == null || A092 == null)) {
                View A0A2 = A0A(0, getChildCount());
                if (A0A2 == null) {
                    position = -1;
                } else {
                    position = getPosition(A0A2);
                }
                int i = -1;
                View A0A3 = A0A(getChildCount() - 1, -1);
                if (A0A3 != null) {
                    i = getPosition(A0A3);
                }
                return (int) ((((float) Math.abs(this.A08.A08(A092) - this.A08.A0B(A082))) / ((float) ((i - position) + 1))) * ((float) r6.A00()));
            }
        }
        return 0;
    }

    private View A09(int i) {
        View A0B2 = A0B(getChildCount() - 1, -1, i);
        if (A0B2 == null) {
            return null;
        }
        return A0D(A0B2, this.A0E.get(this.A0N.A00[getPosition(A0B2)]));
    }

    private View A0B(int i, int i2, int i3) {
        A0E();
        if (this.A0C == null) {
            this.A0C = new C05871Bn();
        }
        int A052 = this.A08.A05();
        int A022 = this.A08.A02();
        int i4 = -1;
        if (i2 > i) {
            i4 = 1;
        }
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((C05831Bi) childAt.getLayoutParams()).A01.isRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.A08.A0B(childAt) >= A052 && this.A08.A08(childAt) <= A022) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        if (view == null) {
            return view2;
        }
        return view;
    }

    private View A0C(View view, AnonymousClass1E4 r8) {
        boolean A65 = A65();
        int i = r8.A0A;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.A0G || A65) {
                    if (this.A08.A0B(view) <= this.A08.A0B(childAt)) {
                    }
                } else if (this.A08.A08(view) >= this.A08.A08(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    private View A0D(View view, AnonymousClass1E4 r8) {
        boolean A65 = A65();
        int childCount = (getChildCount() - r8.A0A) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.A0G || A65) {
                    if (this.A08.A08(view) >= this.A08.A08(childAt)) {
                    }
                } else if (this.A08.A0B(view) <= this.A08.A0B(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r3 == Integer.MIN_VALUE) goto L_0x0011;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0F() {
        /*
            r4 = this;
            boolean r0 = r4.A65()
            if (r0 == 0) goto L_0x0015
            int r3 = r4.mHeightMode
        L_0x0008:
            X.1Bn r2 = r4.A0C
            if (r3 == 0) goto L_0x0011
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = 0
            if (r3 != r1) goto L_0x0012
        L_0x0011:
            r0 = 1
        L_0x0012:
            r2.A08 = r0
            return
        L_0x0015:
            int r3 = r4.mWidthMode
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.A0F():void");
    }

    private void A0G(int i) {
        int A0B2;
        int i2 = -1;
        View A0A2 = A0A(getChildCount() - 1, -1);
        if (A0A2 != null) {
            i2 = getPosition(A0A2);
        }
        if (i < i2) {
            int childCount = getChildCount();
            AnonymousClass1E5 r0 = this.A0N;
            r0.A0A(childCount);
            r0.A0B(childCount);
            r0.A0C(childCount);
            if (i < r0.A00.length) {
                this.A01 = i;
                View childAt = getChildAt(0);
                if (childAt != null) {
                    this.A06 = getPosition(childAt);
                    if (A65() || !this.A0G) {
                        A0B2 = this.A08.A0B(childAt) - this.A08.A05();
                    } else {
                        A0B2 = this.A08.A08(childAt) + this.A08.A03();
                    }
                    this.A07 = A0B2;
                }
            }
        }
    }

    public static boolean A0L(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 <= 0 || i == i3) {
            if (mode != Integer.MIN_VALUE) {
                if (mode == 0) {
                    return true;
                }
                if (mode == 1073741824 && size == i) {
                    return true;
                }
                return false;
            } else if (size >= i) {
                return true;
            }
        }
        return false;
    }

    private boolean A0M(View view, int i, int i2, C05831Bi r6) {
        if (view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !A0L(view.getWidth(), i, r6.width) || !A0L(view.getHeight(), i2, r6.height)) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1E6
    public final int A3j(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (A65()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    @Override // X.AnonymousClass1E6
    public final int A3k(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (A65()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    @Override // X.AnonymousClass1E6
    public final View A4k(int i) {
        return A41(i);
    }

    @Override // X.AnonymousClass1Ag
    public final boolean canScrollHorizontally() {
        if (!A65() || this.mWidth > this.A0J.getWidth()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public final boolean canScrollVertically() {
        if (A65() || this.mHeight > this.A0J.getHeight()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public final int computeHorizontalScrollExtent(AnonymousClass1As r2) {
        return A05(r2);
    }

    @Override // X.AnonymousClass1Ag
    public final int computeHorizontalScrollOffset(AnonymousClass1As r2) {
        return A06(r2);
    }

    @Override // X.AnonymousClass1Ag
    public final int computeHorizontalScrollRange(AnonymousClass1As r2) {
        return A07(r2);
    }

    @Override // X.AnonymousClass1BR
    public final PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = 1;
        if (i < getPosition(getChildAt(0))) {
            i2 = -1;
        }
        float f = (float) i2;
        if (A65()) {
            return new PointF(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, f);
        }
        return new PointF(f, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
    }

    @Override // X.AnonymousClass1Ag
    public final int computeVerticalScrollExtent(AnonymousClass1As r2) {
        return A05(r2);
    }

    @Override // X.AnonymousClass1Ag
    public final int computeVerticalScrollOffset(AnonymousClass1As r2) {
        return A06(r2);
    }

    @Override // X.AnonymousClass1Ag
    public final int computeVerticalScrollRange(AnonymousClass1As r2) {
        return A07(r2);
    }

    @Override // X.AnonymousClass1Ag
    public final void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.A0J = (View) recyclerView.getParent();
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsAdded(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        A0G(i);
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsMoved(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        A0G(Math.min(i, i2));
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsRemoved(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        A0G(i);
    }

    @Override // X.AnonymousClass1Ag
    public final void onLayoutCompleted(AnonymousClass1As r3) {
        super.onLayoutCompleted(r3);
        this.A0D = null;
        this.A06 = -1;
        this.A07 = Integer.MIN_VALUE;
        this.A01 = -1;
        C05851Bl.A01(this.A0B);
        this.A0I.clear();
    }

    @Override // X.AnonymousClass1Ag
    public final int scrollHorizontallyBy(int i, AnonymousClass1Af r5, AnonymousClass1As r6) {
        if (!A65()) {
            int A012 = A01(i, r5, r6);
            this.A0I.clear();
            return A012;
        }
        int A002 = A00(i);
        this.A0B.A02 += A002;
        this.A09.A0E(-A002);
        return A002;
    }

    @Override // X.AnonymousClass1Ag
    public final int scrollVerticallyBy(int i, AnonymousClass1Af r5, AnonymousClass1As r6) {
        if (A65()) {
            int A012 = A01(i, r5, r6);
            this.A0I.clear();
            return A012;
        }
        int A002 = A00(i);
        this.A0B.A02 += A002;
        this.A09.A0E(-A002);
        return A002;
    }

    @Override // X.AnonymousClass1Ag
    public final void smoothScrollToPosition(RecyclerView recyclerView, AnonymousClass1As r4, int i) {
        AnonymousClass1Ao r0 = new AnonymousClass1Ao(recyclerView.getContext());
        ((AnonymousClass1An) r0).A00 = i;
        startSmoothScroll(r0);
    }

    @Override // X.AnonymousClass1Ag
    public final boolean checkLayoutParams(C05831Bi r2) {
        return r2 instanceof LayoutParams;
    }

    @Override // X.AnonymousClass1E6
    public final void setFlexLines(List<AnonymousClass1E4> list) {
        this.A0E = list;
    }

    @Override // X.AnonymousClass1Ag
    public final void onAdapterChanged(AnonymousClass1Aj r1, AnonymousClass1Aj r2) {
        removeAllViews();
    }

    @Override // X.AnonymousClass1Ag
    public final void onDetachedFromWindow(RecyclerView recyclerView, AnonymousClass1Af r2) {
        super.onDetachedFromWindow(recyclerView, r2);
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        A0G(i);
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        A0G(i);
    }
}
