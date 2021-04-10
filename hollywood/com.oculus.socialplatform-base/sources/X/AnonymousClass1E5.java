package X;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.flexbox.FlexItem;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1E5  reason: invalid class name */
public final class AnonymousClass1E5 {
    @Nullable
    public int[] A00;
    @Nullable
    public long[] A01;
    public boolean[] A02;
    @Nullable
    public long[] A03;
    public final AnonymousClass1E6 A04;

    /* JADX WARNING: Removed duplicated region for block: B:45:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0F(int r12, int r13, int r14) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1E5.A0F(int, int, int):void");
    }

    private int A00(int i, FlexItem flexItem, int i2) {
        AnonymousClass1E6 r2 = this.A04;
        int A3X = r2.A3X(i, r2.getPaddingTop() + r2.getPaddingBottom() + flexItem.A4K() + flexItem.A4H() + i2, flexItem.A44());
        int size = View.MeasureSpec.getSize(A3X);
        int A4L = flexItem.A4L();
        if (size > A4L || size < (A4L = flexItem.A4Q())) {
            return View.MeasureSpec.makeMeasureSpec(A4L, View.MeasureSpec.getMode(A3X));
        }
        return A3X;
    }

    private int A01(int i, FlexItem flexItem, int i2) {
        AnonymousClass1E6 r2 = this.A04;
        int A3a = r2.A3a(i, r2.getPaddingLeft() + r2.getPaddingRight() + flexItem.A4I() + flexItem.A4J() + i2, flexItem.A5L());
        int size = View.MeasureSpec.getSize(A3a);
        int A4M = flexItem.A4M();
        if (size > A4M || size < (A4M = flexItem.A4R())) {
            return View.MeasureSpec.makeMeasureSpec(A4M, View.MeasureSpec.getMode(A3a));
        }
        return A3a;
    }

    /* JADX WARN: Incorrect args count in method signature: (I)Ljava/util/List<LX/1E8;>; */
    @NonNull
    public static List A02(AnonymousClass1E5 r4, int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            AnonymousClass1E8 r1 = new AnonymousClass1E8();
            r1.A01 = ((FlexItem) r4.A04.A41(i2).getLayoutParams()).getOrder();
            r1.A00 = i2;
            arrayList.add(r1);
        }
        return arrayList;
    }

    private void A03(int i, int i2, int i3, View view) {
        long[] jArr = this.A01;
        if (jArr != null) {
            jArr[i] = (((long) i2) & 4294967295L) | (((long) i3) << 32);
        }
        long[] jArr2 = this.A03;
        if (jArr2 != null) {
            jArr2[i] = (((long) view.getMeasuredWidth()) & 4294967295L) | (((long) view.getMeasuredHeight()) << 32);
        }
    }

    private void A04(int i, int i2, AnonymousClass1E4 r28, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int A4I;
        int A4J;
        double d;
        double d2;
        float f = r28.A00;
        float f2 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        if (f > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z && i3 >= (i5 = r28.A0C)) {
            float f3 = ((float) (i3 - i5)) / f;
            r28.A0C = i4 + r28.A02;
            if (!z) {
                r28.A07 = Integer.MIN_VALUE;
            }
            int i7 = 0;
            boolean z2 = false;
            float f4 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            int i8 = 0;
            while (i7 < r28.A0A) {
                int i9 = r28.A08 + i7;
                AnonymousClass1E6 r9 = this.A04;
                View A4k = r9.A4k(i9);
                if (!(A4k == null || A4k.getVisibility() == 8)) {
                    FlexItem flexItem = (FlexItem) A4k.getLayoutParams();
                    int flexDirection = r9.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        int measuredWidth = A4k.getMeasuredWidth();
                        long[] jArr = this.A03;
                        if (jArr != null) {
                            measuredWidth = (int) jArr[i9];
                        }
                        int measuredHeight = A4k.getMeasuredHeight();
                        long[] jArr2 = this.A03;
                        if (jArr2 != null) {
                            measuredHeight = (int) (jArr2[i9] >> 32);
                        }
                        boolean[] zArr = this.A02;
                        if (!zArr[i9]) {
                            float A40 = flexItem.A40();
                            if (A40 > f2) {
                                float f5 = ((float) measuredWidth) + (A40 * f3);
                                if (i7 == r28.A0A - 1) {
                                    f5 += f4;
                                    f4 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                }
                                int round = Math.round(f5);
                                int A4M = flexItem.A4M();
                                if (round > A4M) {
                                    round = A4M;
                                    zArr[i9] = true;
                                    r28.A00 -= A40;
                                    z2 = true;
                                } else {
                                    f4 += f5 - ((float) round);
                                    double d3 = (double) f4;
                                    if (d3 > 1.0d) {
                                        round++;
                                        d = d3 - 1.0d;
                                    } else if (d3 < -1.0d) {
                                        round--;
                                        d = d3 + 1.0d;
                                    }
                                    f4 = (float) d;
                                }
                                int A002 = A00(i2, flexItem, r28.A0E);
                                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                                A4k.measure(makeMeasureSpec, A002);
                                measuredWidth = A4k.getMeasuredWidth();
                                measuredHeight = A4k.getMeasuredHeight();
                                A03(i9, makeMeasureSpec, A002, A4k);
                                r9.AAx(i9, A4k);
                            }
                        }
                        i8 = Math.max(i8, measuredHeight + flexItem.A4K() + flexItem.A4H() + r9.A3j(A4k));
                        i6 = r28.A0C;
                        A4I = measuredWidth + flexItem.A4I();
                        A4J = flexItem.A4J();
                    } else {
                        int measuredHeight2 = A4k.getMeasuredHeight();
                        long[] jArr3 = this.A03;
                        if (jArr3 != null) {
                            measuredHeight2 = (int) (jArr3[i9] >> 32);
                        }
                        int measuredWidth2 = A4k.getMeasuredWidth();
                        long[] jArr4 = this.A03;
                        if (jArr4 != null) {
                            measuredWidth2 = (int) jArr4[i9];
                        }
                        boolean[] zArr2 = this.A02;
                        if (!zArr2[i9]) {
                            float A402 = flexItem.A40();
                            if (A402 > f2) {
                                float f6 = ((float) measuredHeight2) + (A402 * f3);
                                if (i7 == r28.A0A - 1) {
                                    f6 += f4;
                                    f4 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                }
                                int round2 = Math.round(f6);
                                int A4L = flexItem.A4L();
                                if (round2 > A4L) {
                                    round2 = A4L;
                                    zArr2[i9] = true;
                                    r28.A00 -= A402;
                                    z2 = true;
                                } else {
                                    f4 += f6 - ((float) round2);
                                    double d4 = (double) f4;
                                    if (d4 > 1.0d) {
                                        round2++;
                                        d2 = d4 - 1.0d;
                                    } else if (d4 < -1.0d) {
                                        round2--;
                                        d2 = d4 + 1.0d;
                                    }
                                    f4 = (float) d2;
                                }
                                int A012 = A01(i, flexItem, r28.A0E);
                                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                                A4k.measure(A012, makeMeasureSpec2);
                                measuredWidth2 = A4k.getMeasuredWidth();
                                measuredHeight2 = A4k.getMeasuredHeight();
                                A03(i9, A012, makeMeasureSpec2, A4k);
                                r9.AAx(i9, A4k);
                            }
                        }
                        i8 = Math.max(i8, measuredWidth2 + flexItem.A4I() + flexItem.A4J() + r9.A3j(A4k));
                        i6 = r28.A0C;
                        A4I = measuredHeight2 + flexItem.A4K();
                        A4J = flexItem.A4H();
                    }
                    r28.A0C = i6 + A4I + A4J;
                    r28.A07 = Math.max(r28.A07, i8);
                }
                i7++;
                f2 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            }
            if (z2 && i5 != r28.A0C) {
                A04(i, i2, r28, i3, i4, true);
            }
        }
    }

    private void A05(int i, int i2, AnonymousClass1E4 r29, int i3, int i4, boolean z) {
        int i5;
        int A4I;
        int A4J;
        int i6 = r29.A0C;
        float f = r29.A01;
        float f2 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        if (f > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z && i3 <= i6) {
            float f3 = ((float) (i6 - i3)) / f;
            r29.A0C = i4 + r29.A02;
            if (!z) {
                r29.A07 = Integer.MIN_VALUE;
            }
            int i7 = 0;
            boolean z2 = false;
            float f4 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            int i8 = 0;
            while (i7 < r29.A0A) {
                int i9 = r29.A08 + i7;
                AnonymousClass1E6 r4 = this.A04;
                View A4k = r4.A4k(i9);
                if (!(A4k == null || A4k.getVisibility() == 8)) {
                    FlexItem flexItem = (FlexItem) A4k.getLayoutParams();
                    int flexDirection = r4.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        int measuredWidth = A4k.getMeasuredWidth();
                        long[] jArr = this.A03;
                        if (jArr != null) {
                            measuredWidth = (int) jArr[i9];
                        }
                        int measuredHeight = A4k.getMeasuredHeight();
                        long[] jArr2 = this.A03;
                        if (jArr2 != null) {
                            measuredHeight = (int) (jArr2[i9] >> 32);
                        }
                        boolean[] zArr = this.A02;
                        if (!zArr[i9]) {
                            float A42 = flexItem.A42();
                            if (A42 > f2) {
                                float f5 = ((float) measuredWidth) - (A42 * f3);
                                if (i7 == r29.A0A - 1) {
                                    f5 += f4;
                                    f4 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                }
                                int round = Math.round(f5);
                                int A4R = flexItem.A4R();
                                if (round < A4R) {
                                    round = A4R;
                                    zArr[i9] = true;
                                    r29.A01 -= A42;
                                    z2 = true;
                                } else {
                                    f4 += f5 - ((float) round);
                                    double d = (double) f4;
                                    if (d > 1.0d) {
                                        round++;
                                        f4 -= 1.0f;
                                    } else if (d < -1.0d) {
                                        round--;
                                        f4 += 1.0f;
                                    }
                                }
                                int A002 = A00(i2, flexItem, r29.A0E);
                                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                                A4k.measure(makeMeasureSpec, A002);
                                measuredWidth = A4k.getMeasuredWidth();
                                measuredHeight = A4k.getMeasuredHeight();
                                A03(i9, makeMeasureSpec, A002, A4k);
                                r4.AAx(i9, A4k);
                            }
                        }
                        i8 = Math.max(i8, measuredHeight + flexItem.A4K() + flexItem.A4H() + r4.A3j(A4k));
                        i5 = r29.A0C;
                        A4I = measuredWidth + flexItem.A4I();
                        A4J = flexItem.A4J();
                    } else {
                        int measuredHeight2 = A4k.getMeasuredHeight();
                        long[] jArr3 = this.A03;
                        if (jArr3 != null) {
                            measuredHeight2 = (int) (jArr3[i9] >> 32);
                        }
                        int measuredWidth2 = A4k.getMeasuredWidth();
                        long[] jArr4 = this.A03;
                        if (jArr4 != null) {
                            measuredWidth2 = (int) jArr4[i9];
                        }
                        boolean[] zArr2 = this.A02;
                        if (!zArr2[i9]) {
                            float A422 = flexItem.A42();
                            if (A422 > f2) {
                                float f6 = ((float) measuredHeight2) - (A422 * f3);
                                if (i7 == r29.A0A - 1) {
                                    f6 += f4;
                                    f4 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                }
                                int round2 = Math.round(f6);
                                int A4Q = flexItem.A4Q();
                                if (round2 < A4Q) {
                                    round2 = A4Q;
                                    zArr2[i9] = true;
                                    r29.A01 -= A422;
                                    z2 = true;
                                } else {
                                    f4 += f6 - ((float) round2);
                                    double d2 = (double) f4;
                                    if (d2 > 1.0d) {
                                        round2++;
                                        f4 -= 1.0f;
                                    } else if (d2 < -1.0d) {
                                        round2--;
                                        f4 += 1.0f;
                                    }
                                }
                                int A012 = A01(i, flexItem, r29.A0E);
                                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                                A4k.measure(A012, makeMeasureSpec2);
                                measuredWidth2 = A4k.getMeasuredWidth();
                                measuredHeight2 = A4k.getMeasuredHeight();
                                A03(i9, A012, makeMeasureSpec2, A4k);
                                r4.AAx(i9, A4k);
                            }
                        }
                        i8 = Math.max(i8, measuredWidth2 + flexItem.A4I() + flexItem.A4J() + r4.A3j(A4k));
                        i5 = r29.A0C;
                        A4I = measuredHeight2 + flexItem.A4K();
                        A4J = flexItem.A4H();
                    }
                    r29.A0C = i5 + A4I + A4J;
                    r29.A07 = Math.max(r29.A07, i8);
                }
                i7++;
                f2 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            }
            if (z2 && i6 != r29.A0C) {
                A05(i, i2, r29, i3, i4, true);
            }
        }
    }

    public final void A0A(int i) {
        long[] copyOf;
        long[] jArr = this.A01;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            copyOf = new long[i];
        } else {
            int length = jArr.length;
            if (length < i) {
                int i2 = length << 1;
                if (i2 >= i) {
                    i = i2;
                }
                copyOf = Arrays.copyOf(jArr, i);
            } else {
                return;
            }
        }
        this.A01 = copyOf;
    }

    public final void A0B(int i) {
        long[] copyOf;
        long[] jArr = this.A03;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            copyOf = new long[i];
        } else {
            int length = jArr.length;
            if (length < i) {
                int i2 = length << 1;
                if (i2 >= i) {
                    i = i2;
                }
                copyOf = Arrays.copyOf(jArr, i);
            } else {
                return;
            }
        }
        this.A03 = copyOf;
    }

    public final void A0C(int i) {
        int[] copyOf;
        int[] iArr = this.A00;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            copyOf = new int[i];
        } else {
            int length = iArr.length;
            if (length < i) {
                int i2 = length << 1;
                if (i2 >= i) {
                    i = i2;
                }
                copyOf = Arrays.copyOf(iArr, i);
            } else {
                return;
            }
        }
        this.A00 = copyOf;
    }

    public final void A0D(int i) {
        int i2;
        View A4k;
        int A3N;
        AnonymousClass1E6 r9 = this.A04;
        if (i < r9.getFlexItemCount()) {
            int flexDirection = r9.getFlexDirection();
            if (r9.getAlignItems() == 4) {
                int[] iArr = this.A00;
                if (iArr != null) {
                    i2 = iArr[i];
                } else {
                    i2 = 0;
                }
                List<AnonymousClass1E4> flexLinesInternal = r9.getFlexLinesInternal();
                int size = flexLinesInternal.size();
                while (i2 < size) {
                    AnonymousClass1E4 r5 = flexLinesInternal.get(i2);
                    int i3 = r5.A0A;
                    for (int i4 = 0; i4 < i3; i4++) {
                        int i5 = r5.A08 + i4;
                        if (i4 < r9.getFlexItemCount() && (A4k = r9.A4k(i5)) != null && A4k.getVisibility() != 8 && ((A3N = ((FlexItem) A4k.getLayoutParams()).A3N()) == -1 || A3N == 4)) {
                            if (flexDirection == 0 || flexDirection == 1) {
                                A08(A4k, r5.A07, i5);
                            } else if (flexDirection == 2 || flexDirection == 3) {
                                A07(A4k, r5.A07, i5);
                            } else {
                                throw new IllegalArgumentException(AnonymousClass006.A03("Invalid flex direction: ", flexDirection));
                            }
                        }
                    }
                    i2++;
                }
                return;
            }
            for (AnonymousClass1E4 r52 : r9.getFlexLinesInternal()) {
                Iterator<Integer> it = r52.A05.iterator();
                while (true) {
                    if (it.hasNext()) {
                        int intValue = it.next().intValue();
                        View A4k2 = r9.A4k(intValue);
                        if (flexDirection == 0 || flexDirection == 1) {
                            A08(A4k2, r52.A07, intValue);
                        } else if (flexDirection == 2 || flexDirection == 3) {
                            A07(A4k2, r52.A07, intValue);
                        } else {
                            throw new IllegalArgumentException(AnonymousClass006.A03("Invalid flex direction: ", flexDirection));
                        }
                    }
                }
            }
        }
    }

    public final void A0E(int i, int i2, int i3) {
        int i4;
        int i5;
        ArrayList arrayList;
        int round;
        AnonymousClass1E6 r5 = this.A04;
        int flexDirection = r5.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            i4 = View.MeasureSpec.getMode(i2);
            i5 = View.MeasureSpec.getSize(i2);
        } else if (flexDirection == 2 || flexDirection == 3) {
            i4 = View.MeasureSpec.getMode(i);
            i5 = View.MeasureSpec.getSize(i);
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A03("Invalid flex direction: ", flexDirection));
        }
        List<AnonymousClass1E4> flexLinesInternal = r5.getFlexLinesInternal();
        if (i4 == 1073741824) {
            int sumOfCrossSize = r5.getSumOfCrossSize() + i3;
            int i6 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).A07 = i5 - i3;
            } else if (flexLinesInternal.size() >= 2) {
                int alignContent = r5.getAlignContent();
                if (alignContent != 1) {
                    if (alignContent != 2) {
                        if (alignContent != 3) {
                            if (alignContent != 4) {
                                if (alignContent == 5 && sumOfCrossSize < i5) {
                                    float size = ((float) (i5 - sumOfCrossSize)) / ((float) flexLinesInternal.size());
                                    int size2 = flexLinesInternal.size();
                                    float f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                    while (i6 < size2) {
                                        AnonymousClass1E4 r3 = flexLinesInternal.get(i6);
                                        float f2 = ((float) r3.A07) + size;
                                        if (i6 == flexLinesInternal.size() - 1) {
                                            f2 += f;
                                            f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                        }
                                        int round2 = Math.round(f2);
                                        f += f2 - ((float) round2);
                                        if (f > 1.0f) {
                                            round2++;
                                            f -= 1.0f;
                                        } else if (f < -1.0f) {
                                            round2--;
                                            f += 1.0f;
                                        }
                                        r3.A07 = round2;
                                        i6++;
                                    }
                                    return;
                                }
                                return;
                            } else if (sumOfCrossSize < i5) {
                                arrayList = new ArrayList();
                                AnonymousClass1E4 r2 = new AnonymousClass1E4();
                                r2.A07 = (i5 - sumOfCrossSize) / (flexLinesInternal.size() << 1);
                                for (AnonymousClass1E4 r0 : flexLinesInternal) {
                                    arrayList.add(r2);
                                    arrayList.add(r0);
                                    arrayList.add(r2);
                                }
                            }
                        } else if (sumOfCrossSize < i5) {
                            float size3 = ((float) (i5 - sumOfCrossSize)) / ((float) (flexLinesInternal.size() - 1));
                            arrayList = new ArrayList();
                            int size4 = flexLinesInternal.size();
                            float f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                            while (i6 < size4) {
                                arrayList.add(flexLinesInternal.get(i6));
                                if (i6 != flexLinesInternal.size() - 1) {
                                    AnonymousClass1E4 r22 = new AnonymousClass1E4();
                                    if (i6 == flexLinesInternal.size() - 2) {
                                        round = Math.round(f3 + size3);
                                        r22.A07 = round;
                                        f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                    } else {
                                        round = Math.round(size3);
                                        r22.A07 = round;
                                    }
                                    f3 += size3 - ((float) round);
                                    if (f3 > 1.0f) {
                                        r22.A07 = round + 1;
                                        f3 -= 1.0f;
                                    } else if (f3 < -1.0f) {
                                        r22.A07 = round - 1;
                                        f3 += 1.0f;
                                    }
                                    arrayList.add(r22);
                                }
                                i6++;
                            }
                        } else {
                            return;
                        }
                        r5.setFlexLines(arrayList);
                        return;
                    }
                    arrayList = new ArrayList();
                    AnonymousClass1E4 r32 = new AnonymousClass1E4();
                    r32.A07 = (i5 - sumOfCrossSize) >> 1;
                    int size5 = flexLinesInternal.size();
                    for (int i7 = 0; i7 < size5; i7++) {
                        if (i7 == 0) {
                            arrayList.add(r32);
                        }
                        arrayList.add(flexLinesInternal.get(i7));
                        if (i7 == flexLinesInternal.size() - 1) {
                            arrayList.add(r32);
                        }
                    }
                    r5.setFlexLines(arrayList);
                    return;
                }
                AnonymousClass1E4 r02 = new AnonymousClass1E4();
                r02.A07 = i5 - sumOfCrossSize;
                flexLinesInternal.add(0, r02);
            }
        }
    }

    public final void A0J(List<AnonymousClass1E4> list, int i) {
        int i2 = this.A00[i];
        if (i2 == -1) {
            i2 = 0;
        }
        int size = list.size();
        while (true) {
            size--;
            if (size < i2) {
                break;
            }
            list.remove(size);
        }
        int[] iArr = this.A00;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.A01;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    public AnonymousClass1E5(AnonymousClass1E6 r1) {
        this.A04 = r1;
    }

    private void A06(View view, int i) {
        boolean z;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int A4R = flexItem.A4R();
        if (measuredWidth < A4R || measuredWidth > (A4R = flexItem.A4M())) {
            measuredWidth = A4R;
            z = true;
        } else {
            z = false;
        }
        int A4Q = flexItem.A4Q();
        if (measuredHeight < A4Q || measuredHeight > (A4Q = flexItem.A4L())) {
            measuredHeight = A4Q;
        } else if (!z) {
            return;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        A03(i, makeMeasureSpec, makeMeasureSpec2, view);
        this.A04.AAx(i, view);
    }

    private void A07(View view, int i, int i2) {
        int measuredHeight;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        AnonymousClass1E6 r5 = this.A04;
        int min = Math.min(Math.max(((i - flexItem.A4I()) - flexItem.A4J()) - r5.A3j(view), flexItem.A4R()), flexItem.A4M());
        long[] jArr = this.A03;
        if (jArr != null) {
            measuredHeight = (int) (jArr[i2] >> 32);
        } else {
            measuredHeight = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        A03(i2, makeMeasureSpec2, makeMeasureSpec, view);
        r5.AAx(i2, view);
    }

    private void A08(View view, int i, int i2) {
        int measuredWidth;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        AnonymousClass1E6 r5 = this.A04;
        int min = Math.min(Math.max(((i - flexItem.A4K()) - flexItem.A4H()) - r5.A3j(view), flexItem.A4Q()), flexItem.A4L());
        long[] jArr = this.A03;
        if (jArr != null) {
            measuredWidth = (int) jArr[i2];
        } else {
            measuredWidth = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        A03(i2, makeMeasureSpec, makeMeasureSpec2, view);
        r5.AAx(i2, view);
    }

    public static int[] A09(int i, List<AnonymousClass1E8> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (AnonymousClass1E8 r0 : list) {
            int i3 = r0.A00;
            iArr[i2] = i3;
            sparseIntArray.append(i3, r0.A01);
            i2++;
        }
        return iArr;
    }

    public final void A0G(View view, AnonymousClass1E4 r8, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        AnonymousClass1E6 r4 = this.A04;
        int alignItems = r4.getAlignItems();
        int A3N = flexItem.A3N();
        if (A3N != -1) {
            alignItems = A3N;
        }
        int i10 = r8.A07;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    int measuredHeight = (((i10 - view.getMeasuredHeight()) + flexItem.A4K()) - flexItem.A4H()) / 2;
                    if (r4.getFlexWrap() != 2) {
                        i9 = i2 + measuredHeight;
                    } else {
                        i9 = i2 - measuredHeight;
                    }
                    view.layout(i, i9, i3, view.getMeasuredHeight() + i9);
                    return;
                } else if (alignItems == 3) {
                    int flexWrap = r4.getFlexWrap();
                    int i11 = r8.A0D;
                    if (flexWrap != 2) {
                        i7 = Math.max(i11 - view.getBaseline(), flexItem.A4K());
                        i6 = i2 + i7;
                        i5 = i4 + i7;
                        view.layout(i, i6, i3, i5);
                    }
                    i8 = Math.max((i11 - view.getMeasuredHeight()) + view.getBaseline(), flexItem.A4H());
                    i6 = i2 - i8;
                    i5 = i4 - i8;
                    view.layout(i, i6, i3, i5);
                } else if (alignItems != 4) {
                    return;
                }
            } else if (r4.getFlexWrap() != 2) {
                int i12 = i2 + i10;
                int measuredHeight2 = i12 - view.getMeasuredHeight();
                int A4H = flexItem.A4H();
                view.layout(i, measuredHeight2 - A4H, i3, i12 - A4H);
                return;
            } else {
                i6 = (i2 - i10) + view.getMeasuredHeight() + flexItem.A4K();
                i4 = (i4 - i10) + view.getMeasuredHeight();
                i7 = flexItem.A4K();
                i5 = i4 + i7;
                view.layout(i, i6, i3, i5);
            }
        }
        if (r4.getFlexWrap() != 2) {
            i7 = flexItem.A4K();
            i6 = i2 + i7;
            i5 = i4 + i7;
            view.layout(i, i6, i3, i5);
        }
        i8 = flexItem.A4H();
        i6 = i2 - i8;
        i5 = i4 - i8;
        view.layout(i, i6, i3, i5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        if (r8 == false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0H(android.view.View r6, X.AnonymousClass1E4 r7, boolean r8, int r9, int r10, int r11, int r12) {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1E5.A0H(android.view.View, X.1E4, boolean, int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x026f, code lost:
        if (r23 < (r13 + r18)) goto L_0x0271;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0240  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0I(X.C05881Bo r34, int r35, int r36, int r37, int r38, int r39, @androidx.annotation.Nullable java.util.List<X.AnonymousClass1E4> r40) {
        /*
        // Method dump skipped, instructions count: 873
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1E5.A0I(X.1Bo, int, int, int, int, int, java.util.List):void");
    }
}
