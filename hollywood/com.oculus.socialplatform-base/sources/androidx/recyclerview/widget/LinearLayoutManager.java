package androidx.recyclerview.widget;

import X.AbstractC05861Bm;
import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass1Af;
import X.AnonymousClass1Ag;
import X.AnonymousClass1Ah;
import X.AnonymousClass1An;
import X.AnonymousClass1Ao;
import X.AnonymousClass1As;
import X.AnonymousClass1BL;
import X.AnonymousClass1BR;
import X.AnonymousClass1C7;
import X.AnonymousClass1CC;
import X.AnonymousClass1CF;
import X.AnonymousClass2fg;
import X.C05831Bi;
import X.C05901Bt;
import X.C05911Bu;
import X.C05921By;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.List;

public class LinearLayoutManager extends AnonymousClass1Ag implements AnonymousClass1BR {
    public static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final float MAX_SCROLL_FACTOR = 0.33333334f;
    public static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    public final C05901Bt mAnchorInfo;
    public int mInitialPrefetchItemCount;
    public boolean mLastStackFromEnd;
    public final AnonymousClass1CC mLayoutChunkResult;
    public C05911Bu mLayoutState;
    public int mOrientation;
    public AbstractC05861Bm mOrientationHelper;
    public SavedState mPendingSavedState;
    public int mPendingScrollPosition;
    public int mPendingScrollPositionOffset;
    public boolean mRecycleChildrenOnDetach;
    public int[] mReusableIntPair;
    public boolean mReverseLayout;
    public boolean mShouldReverseLayout;
    public boolean mSmoothScrollbarEnabled;
    public boolean mStackFromEnd;

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1C7();
        public int A00;
        public boolean A01;
        public int A02;

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.A02);
            parcel.writeInt(this.A00);
            parcel.writeInt(this.A01 ? 1 : 0);
        }

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.A02 = parcel.readInt();
            this.A00 = parcel.readInt();
            this.A01 = parcel.readInt() != 1 ? false : true;
        }

        public SavedState(SavedState savedState) {
            this.A02 = savedState.A02;
            this.A00 = savedState.A00;
            this.A01 = savedState.A01;
        }
    }

    private int computeScrollExtent(AnonymousClass1As r8) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return C05921By.A00(r8, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(AnonymousClass1As r9) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return C05921By.A02(r9, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(AnonymousClass1As r8) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return C05921By.A01(r8, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private void logChildren() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            getPosition(childAt);
            this.mOrientationHelper.A0B(childAt);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0033 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0046 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int convertFocusDirectionToLayoutDirection(int r5) {
        /*
            r4 = this;
            r3 = -1
            r2 = 1
            if (r5 == r2) goto L_0x0037
            r0 = 2
            if (r5 == r0) goto L_0x0024
            r0 = 17
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r5 == r0) goto L_0x0042
            r0 = 33
            if (r5 == r0) goto L_0x001f
            r0 = 66
            if (r5 == r0) goto L_0x002f
            r0 = 130(0x82, float:1.82E-43)
            if (r5 == r0) goto L_0x001a
            return r1
        L_0x001a:
            int r0 = r4.mOrientation
            if (r0 != r2) goto L_0x0034
            return r2
        L_0x001f:
            int r0 = r4.mOrientation
            if (r0 != r2) goto L_0x0047
            return r3
        L_0x0024:
            int r0 = r4.mOrientation
            if (r0 == r2) goto L_0x0033
            boolean r0 = r4.isLayoutRTL()
            if (r0 == 0) goto L_0x0033
            return r3
        L_0x002f:
            int r0 = r4.mOrientation
            if (r0 != 0) goto L_0x0034
        L_0x0033:
            return r2
        L_0x0034:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            return r2
        L_0x0037:
            int r0 = r4.mOrientation
            if (r0 == r2) goto L_0x0046
            boolean r0 = r4.isLayoutRTL()
            if (r0 == 0) goto L_0x0046
            return r2
        L_0x0042:
            int r0 = r4.mOrientation
            if (r0 != 0) goto L_0x0047
        L_0x0046:
            return r3
        L_0x0047:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.convertFocusDirectionToLayoutDirection(int):int");
    }

    @Override // X.AnonymousClass1Ag
    public C05831Bi generateDefaultLayoutParams() {
        return new C05831Bi(-2, -2);
    }

    @Override // X.AnonymousClass1Ag
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public void onAnchorReady(AnonymousClass1Af r1, AnonymousClass1As r2, C05901Bt r3, int i) {
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z != this.mReverseLayout) {
            this.mReverseLayout = z;
            requestLayout();
        }
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd != z) {
            this.mStackFromEnd = z;
            requestLayout();
        }
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
        if (this.mShouldReverseLayout) {
            return findFirstPartiallyOrCompletelyInvisibleChild();
        }
        return findLastPartiallyOrCompletelyInvisibleChild();
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
        if (this.mShouldReverseLayout) {
            return findLastPartiallyOrCompletelyInvisibleChild();
        }
        return findFirstPartiallyOrCompletelyInvisibleChild();
    }

    private int fixLayoutEndGap(int i, AnonymousClass1Af r5, AnonymousClass1As r6, boolean z) {
        int A02;
        int A022 = this.mOrientationHelper.A02() - i;
        if (A022 <= 0) {
            return 0;
        }
        int i2 = -scrollBy(-A022, r5, r6);
        int i3 = i + i2;
        if (!z || (A02 = this.mOrientationHelper.A02() - i3) <= 0) {
            return i2;
        }
        this.mOrientationHelper.A0E(A02);
        return A02 + i2;
    }

    private int fixLayoutStartGap(int i, AnonymousClass1Af r5, AnonymousClass1As r6, boolean z) {
        int A05;
        int A052 = i - this.mOrientationHelper.A05();
        if (A052 <= 0) {
            return 0;
        }
        int i2 = -scrollBy(A052, r5, r6);
        int i3 = i + i2;
        if (!z || (A05 = i3 - this.mOrientationHelper.A05()) <= 0) {
            return i2;
        }
        this.mOrientationHelper.A0E(-A05);
        return i2 - A05;
    }

    private View getChildClosestToEnd() {
        int childCount;
        if (this.mShouldReverseLayout) {
            childCount = 0;
        } else {
            childCount = getChildCount() - 1;
        }
        return getChildAt(childCount);
    }

    private View getChildClosestToStart() {
        int i;
        if (this.mShouldReverseLayout) {
            i = getChildCount() - 1;
        } else {
            i = 0;
        }
        return getChildAt(i);
    }

    private void layoutForPredictiveAnimations(AnonymousClass1Af r11, AnonymousClass1As r12, int i, int i2) {
        if (r12.A0C && getChildCount() != 0 && !r12.A0A && supportsPredictiveItemAnimations()) {
            List<AnonymousClass1Ah> list = r11.A07;
            int size = list.size();
            int position = getPosition(getChildAt(0));
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                AnonymousClass1Ah r9 = list.get(i5);
                if (!r9.isRemoved()) {
                    boolean z = false;
                    if (r9.getLayoutPosition() < position) {
                        z = true;
                    }
                    if (z != this.mShouldReverseLayout) {
                        i3 += this.mOrientationHelper.A09(r9.itemView);
                    } else {
                        i4 += this.mOrientationHelper.A09(r9.itemView);
                    }
                }
            }
            this.mLayoutState.A0B = list;
            if (i3 > 0) {
                updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), i);
                C05911Bu r1 = this.mLayoutState;
                r1.A01 = i3;
                r1.A00 = 0;
                C05911Bu.A00(r1, null);
                fill(r11, this.mLayoutState, r12, false);
            }
            if (i4 > 0) {
                updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), i2);
                C05911Bu r13 = this.mLayoutState;
                r13.A01 = i4;
                r13.A00 = 0;
                C05911Bu.A00(r13, null);
                fill(r11, this.mLayoutState, r12, false);
            }
            this.mLayoutState.A0B = null;
        }
    }

    private void recycleByLayoutState(AnonymousClass1Af r5, C05911Bu r6) {
        if (r6.A08 && !r6.A07) {
            int i = r6.A06;
            int i2 = r6.A04;
            if (r6.A03 == -1) {
                recycleViewsFromEnd(r5, i, i2);
            } else {
                recycleViewsFromStart(r5, i, i2);
            }
        }
    }

    private void recycleChildren(AnonymousClass1Af r1, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 > i) {
            while (true) {
                i2--;
                if (i2 >= i) {
                    removeAndRecycleViewAt(i2, r1);
                } else {
                    return;
                }
            }
        } else {
            while (i > i2) {
                removeAndRecycleViewAt(i, r1);
                i--;
            }
        }
    }

    private void recycleViewsFromStart(AnonymousClass1Af r6, int i, int i2) {
        int i3;
        int i4;
        if (i >= 0) {
            int i5 = i - i2;
            int childCount = getChildCount();
            if (this.mShouldReverseLayout) {
                i3 = childCount - 1;
                i4 = i3;
                while (i4 >= 0) {
                    View childAt = getChildAt(i4);
                    if (this.mOrientationHelper.A08(childAt) <= i5 && this.mOrientationHelper.A0C(childAt) <= i5) {
                        i4--;
                    }
                }
                return;
            }
            i3 = 0;
            i4 = 0;
            while (i4 < childCount) {
                View childAt2 = getChildAt(i4);
                if (this.mOrientationHelper.A08(childAt2) <= i5 && this.mOrientationHelper.A0C(childAt2) <= i5) {
                    i4++;
                }
            }
            return;
            recycleChildren(r6, i3, i4);
        }
    }

    private void resolveShouldLayoutReverse() {
        boolean z;
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            z = this.mReverseLayout;
        } else {
            z = !this.mReverseLayout;
        }
        this.mShouldReverseLayout = z;
    }

    private boolean updateAnchorFromPendingData(AnonymousClass1As r7, C05901Bt r8) {
        int i;
        int i2;
        int i3;
        int i4;
        int A02;
        int i5;
        int A05;
        boolean z = false;
        if (!r7.A0A && (i = this.mPendingScrollPosition) != -1) {
            if (i < 0 || i >= r7.A00()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else {
                r8.A02 = i;
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.A02 < 0) {
                    if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                        View findViewByPosition = findViewByPosition(i);
                        if (findViewByPosition != null) {
                            if (this.mOrientationHelper.A09(findViewByPosition) <= this.mOrientationHelper.A06()) {
                                if (this.mOrientationHelper.A0B(findViewByPosition) - this.mOrientationHelper.A05() < 0) {
                                    r8.A01 = this.mOrientationHelper.A05();
                                    r8.A04 = false;
                                    return true;
                                } else if (this.mOrientationHelper.A02() - this.mOrientationHelper.A08(findViewByPosition) < 0) {
                                    r8.A01 = this.mOrientationHelper.A02();
                                    r8.A04 = true;
                                    return true;
                                } else if (r8.A04) {
                                    i2 = this.mOrientationHelper.A08(findViewByPosition);
                                    i3 = this.mOrientationHelper.A07();
                                } else {
                                    i4 = this.mOrientationHelper.A0B(findViewByPosition);
                                    r8.A01 = i4;
                                    return true;
                                }
                            }
                        } else if (getChildCount() > 0) {
                            boolean z2 = false;
                            if (this.mPendingScrollPosition < getPosition(getChildAt(0))) {
                                z2 = true;
                            }
                            if (z2 == this.mShouldReverseLayout) {
                                z = true;
                            }
                            r8.A04 = z;
                        }
                        if (r8.A04) {
                            A05 = r8.A03.A02();
                        } else {
                            A05 = r8.A03.A05();
                        }
                        r8.A01 = A05;
                        return true;
                    }
                    boolean z3 = this.mShouldReverseLayout;
                    r8.A04 = z3;
                    if (z3) {
                        A02 = this.mOrientationHelper.A02();
                        i5 = this.mPendingScrollPositionOffset;
                    } else {
                        i2 = this.mOrientationHelper.A05();
                        i3 = this.mPendingScrollPositionOffset;
                    }
                    i4 = i2 + i3;
                    r8.A01 = i4;
                    return true;
                }
                boolean z4 = savedState.A01;
                r8.A04 = z4;
                if (z4) {
                    A02 = this.mOrientationHelper.A02();
                    i5 = this.mPendingSavedState.A00;
                } else {
                    i2 = this.mOrientationHelper.A05();
                    i3 = this.mPendingSavedState.A00;
                    i4 = i2 + i3;
                    r8.A01 = i4;
                    return true;
                }
                i4 = A02 - i5;
                r8.A01 = i4;
                return true;
            }
        }
        return false;
    }

    private void updateLayoutState(int i, int i2, boolean z, AnonymousClass1As r10) {
        int A05;
        C05911Bu r1 = this.mLayoutState;
        r1.A07 = resolveIsInfinite();
        r1.A03 = i;
        int[] iArr = this.mReusableIntPair;
        boolean z2 = false;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(r10, iArr);
        int[] iArr2 = this.mReusableIntPair;
        int max = Math.max(0, iArr2[0]);
        int max2 = Math.max(0, iArr2[1]);
        if (i == 1) {
            z2 = true;
        }
        C05911Bu r2 = this.mLayoutState;
        int i3 = max;
        if (z2) {
            i3 = max2;
        }
        r2.A01 = i3;
        if (!z2) {
            max = max2;
        }
        r2.A04 = max;
        int i4 = -1;
        if (z2) {
            r2.A01 = i3 + this.mOrientationHelper.A03();
            View childClosestToEnd = getChildClosestToEnd();
            C05911Bu r3 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                i4 = 1;
            }
            r3.A0A = i4;
            int position = getPosition(childClosestToEnd);
            C05911Bu r12 = this.mLayoutState;
            r3.A09 = position + r12.A0A;
            r12.A05 = this.mOrientationHelper.A08(childClosestToEnd);
            A05 = this.mOrientationHelper.A08(childClosestToEnd) - this.mOrientationHelper.A02();
        } else {
            View childClosestToStart = getChildClosestToStart();
            this.mLayoutState.A01 += this.mOrientationHelper.A05();
            C05911Bu r32 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                i4 = 1;
            }
            r32.A0A = i4;
            int position2 = getPosition(childClosestToStart);
            C05911Bu r13 = this.mLayoutState;
            r32.A09 = position2 + r13.A0A;
            r13.A05 = this.mOrientationHelper.A0B(childClosestToStart);
            A05 = (-this.mOrientationHelper.A0B(childClosestToStart)) + this.mOrientationHelper.A05();
        }
        C05911Bu r0 = this.mLayoutState;
        r0.A00 = i2;
        if (z) {
            r0.A00 = i2 - A05;
        }
        r0.A06 = A05;
    }

    @Override // X.AnonymousClass1Ag
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // X.AnonymousClass1Ag
    public boolean canScrollHorizontally() {
        if (this.mOrientation == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public boolean canScrollVertically() {
        if (this.mOrientation != 1) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1Ag
    public void collectAdjacentPrefetchPositions(int i, int i2, AnonymousClass1As r6, AnonymousClass1CF r7) {
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            ensureLayoutState();
            int i3 = -1;
            if (i > 0) {
                i3 = 1;
            }
            updateLayoutState(i3, Math.abs(i), true, r6);
            collectPrefetchPositionsForLayoutState(r6, this.mLayoutState, r7);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (r0 != false) goto L_0x000e;
     */
    @Override // X.AnonymousClass1Ag
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void collectInitialPrefetchPositions(int r6, X.AnonymousClass1CF r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r0 = r5.mPendingSavedState
            r4 = -1
            r3 = 0
            if (r0 == 0) goto L_0x001e
            int r2 = r0.A02
            if (r2 < 0) goto L_0x001e
            boolean r0 = r0.A01
        L_0x000c:
            if (r0 == 0) goto L_0x002d
        L_0x000e:
            r1 = 0
        L_0x000f:
            int r0 = r5.mInitialPrefetchItemCount
            if (r1 >= r0) goto L_0x002f
            if (r2 < 0) goto L_0x002f
            if (r2 >= r6) goto L_0x002f
            r7.A1J(r2, r3)
            int r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x000f
        L_0x001e:
            r5.resolveShouldLayoutReverse()
            boolean r0 = r5.mShouldReverseLayout
            int r2 = r5.mPendingScrollPosition
            if (r2 != r4) goto L_0x000c
            r2 = 0
            if (r0 == 0) goto L_0x002d
            int r2 = r6 + -1
            goto L_0x000e
        L_0x002d:
            r4 = 1
            goto L_0x000e
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.collectInitialPrefetchPositions(int, X.1CF):void");
    }

    public void collectPrefetchPositionsForLayoutState(AnonymousClass1As r4, C05911Bu r5, AnonymousClass1CF r6) {
        int i = r5.A09;
        if (i >= 0 && i < r4.A00()) {
            r6.A1J(i, Math.max(0, r5.A06));
        }
    }

    public C05911Bu createLayoutState() {
        return new C05911Bu();
    }

    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new C05911Bu();
        }
    }

    public int fill(AnonymousClass1Af r8, C05911Bu r9, AnonymousClass1As r10, boolean z) {
        int i;
        int i2 = r9.A00;
        int i3 = r9.A06;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                r9.A06 = i3 + i2;
            }
            recycleByLayoutState(r8, r9);
        }
        int i4 = r9.A00 + r9.A01;
        AnonymousClass1CC r2 = this.mLayoutChunkResult;
        while (true) {
            if ((!r9.A07 && i4 <= 0) || (i = r9.A09) < 0 || i >= r10.A00()) {
                break;
            }
            r2.A00 = 0;
            r2.A01 = false;
            r2.A03 = false;
            r2.A02 = false;
            layoutChunk(r8, r10, r9, r2);
            if (r2.A01) {
                break;
            }
            int i5 = r9.A05;
            int i6 = r2.A00;
            r9.A05 = i5 + (r9.A03 * i6);
            if (!r2.A03 || r9.A0B != null || !r10.A0A) {
                r9.A00 -= i6;
                i4 -= i6;
            }
            int i7 = r9.A06;
            if (i7 != Integer.MIN_VALUE) {
                int i8 = i7 + i6;
                r9.A06 = i8;
                int i9 = r9.A00;
                if (i9 < 0) {
                    r9.A06 = i8 + i9;
                }
                recycleByLayoutState(r8, r9);
            }
            if (z && r2.A02) {
                break;
            }
        }
        return i2 - r9.A00;
    }

    public View findFirstVisibleChildClosestToEnd(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(0, getChildCount(), z, z2);
        }
        return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
    }

    public View findFirstVisibleChildClosestToStart(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
        }
        return findOneVisibleChild(0, getChildCount(), z, z2);
    }

    @Deprecated
    public int getExtraLayoutSpace(AnonymousClass1As r3) {
        if (r3.A09 != -1) {
            return this.mOrientationHelper.A06();
        }
        return 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    public boolean isLayoutRTL() {
        if (this.mRecyclerView.getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void layoutChunk(AnonymousClass1Af r16, AnonymousClass1As r17, C05911Bu r18, AnonymousClass1CC r19) {
        int paddingTop;
        int A0A;
        int i;
        int i2;
        View A01 = r18.A01(r16);
        if (A01 == null) {
            r19.A01 = true;
            return;
        }
        C05831Bi r3 = (C05831Bi) A01.getLayoutParams();
        if (r18.A0B == null) {
            boolean z = this.mShouldReverseLayout;
            boolean z2 = false;
            if (r18.A03 == -1) {
                z2 = true;
            }
            if (z == z2) {
                addView(A01, -1);
            } else {
                addView(A01, 0);
            }
        } else {
            boolean z3 = this.mShouldReverseLayout;
            boolean z4 = false;
            if (r18.A03 == -1) {
                z4 = true;
            }
            if (z3 == z4) {
                addDisappearingView(A01, -1);
            } else {
                addDisappearingView(A01, 0);
            }
        }
        measureChildWithMargins(A01, 0, 0);
        r19.A00 = this.mOrientationHelper.A09(A01);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                i2 = this.mWidth - getPaddingRight();
                i = i2 - this.mOrientationHelper.A0A(A01);
            } else {
                i = getPaddingLeft();
                i2 = this.mOrientationHelper.A0A(A01) + i;
            }
            if (r18.A03 == -1) {
                A0A = r18.A05;
                paddingTop = A0A - r19.A00;
            } else {
                paddingTop = r18.A05;
                A0A = paddingTop + r19.A00;
            }
        } else {
            paddingTop = getPaddingTop();
            A0A = this.mOrientationHelper.A0A(A01) + paddingTop;
            if (r18.A03 == -1) {
                i2 = r18.A05;
                i = i2 - r19.A00;
            } else {
                i = r18.A05;
                i2 = i + r19.A00;
            }
        }
        layoutDecoratedWithMargins(A01, i, paddingTop, i2, A0A);
        AnonymousClass1Ah r1 = r3.A01;
        if (r1.isRemoved() || r1.isUpdated()) {
            r19.A03 = true;
        }
        r19.A02 = A01.hasFocusable();
    }

    @Override // X.AnonymousClass1Ag
    public void onLayoutChildren(AnonymousClass1Af r9, AnonymousClass1As r10) {
        int i;
        int i2;
        int i3;
        int i4;
        int fixLayoutEndGap;
        int i5;
        View findViewByPosition;
        int A0B;
        int i6;
        int i7;
        SavedState savedState = this.mPendingSavedState;
        int i8 = -1;
        if (!(savedState == null && this.mPendingScrollPosition == -1) && r10.A00() == 0) {
            removeAndRecycleAllViews(r9);
            return;
        }
        if (savedState != null && (i7 = savedState.A02) >= 0) {
            this.mPendingScrollPosition = i7;
        }
        ensureLayoutState();
        this.mLayoutState.A08 = false;
        resolveShouldLayoutReverse();
        View focusedChild = getFocusedChild();
        C05901Bt r6 = this.mAnchorInfo;
        if (!r6.A00 || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            r6.A00();
            r6.A04 = this.mShouldReverseLayout ^ this.mStackFromEnd;
            updateAnchorInfoForLayout(r9, r10, r6);
            this.mAnchorInfo.A00 = true;
        } else if (focusedChild != null && (this.mOrientationHelper.A0B(focusedChild) >= this.mOrientationHelper.A02() || this.mOrientationHelper.A08(focusedChild) <= this.mOrientationHelper.A05())) {
            this.mAnchorInfo.A02(focusedChild, getPosition(focusedChild));
        }
        C05911Bu r3 = this.mLayoutState;
        int i9 = -1;
        if (r3.A02 >= 0) {
            i9 = 1;
        }
        r3.A03 = i9;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(r10, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.A05();
        int max2 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.A03();
        if (!(!r10.A0A || (i5 = this.mPendingScrollPosition) == -1 || this.mPendingScrollPositionOffset == Integer.MIN_VALUE || (findViewByPosition = findViewByPosition(i5)) == null)) {
            if (this.mShouldReverseLayout) {
                i6 = this.mOrientationHelper.A02() - this.mOrientationHelper.A08(findViewByPosition);
                A0B = this.mPendingScrollPositionOffset;
            } else {
                A0B = this.mOrientationHelper.A0B(findViewByPosition) - this.mOrientationHelper.A05();
                i6 = this.mPendingScrollPositionOffset;
            }
            int i10 = i6 - A0B;
            if (i10 > 0) {
                max += i10;
            } else {
                max2 -= i10;
            }
        }
        C05901Bt r1 = this.mAnchorInfo;
        if (!r1.A04 ? !this.mShouldReverseLayout : this.mShouldReverseLayout) {
            i8 = 1;
        }
        onAnchorReady(r9, r10, r1, i8);
        detachAndScrapAttachedViews(r9);
        C05911Bu r12 = this.mLayoutState;
        r12.A07 = resolveIsInfinite();
        r12.A04 = 0;
        C05901Bt r13 = this.mAnchorInfo;
        if (r13.A04) {
            updateLayoutStateToFillStart(r13);
            C05911Bu r0 = this.mLayoutState;
            r0.A01 = max;
            fill(r9, r0, r10, false);
            C05911Bu r02 = this.mLayoutState;
            i2 = r02.A05;
            int i11 = r02.A09;
            int i12 = r02.A00;
            if (i12 > 0) {
                max2 += i12;
            }
            updateLayoutStateToFillEnd(this.mAnchorInfo);
            C05911Bu r5 = this.mLayoutState;
            r5.A01 = max2;
            r5.A09 += r5.A0A;
            fill(r9, r5, r10, false);
            C05911Bu r03 = this.mLayoutState;
            i = r03.A05;
            int i13 = r03.A00;
            if (i13 > 0) {
                updateLayoutStateToFillStart(i11, i2);
                C05911Bu r04 = this.mLayoutState;
                r04.A01 = i13;
                fill(r9, r04, r10, false);
                i2 = this.mLayoutState.A05;
            }
        } else {
            updateLayoutStateToFillEnd(r13);
            C05911Bu r05 = this.mLayoutState;
            r05.A01 = max2;
            fill(r9, r05, r10, false);
            C05911Bu r06 = this.mLayoutState;
            i = r06.A05;
            int i14 = r06.A09;
            int i15 = r06.A00;
            if (i15 > 0) {
                max += i15;
            }
            updateLayoutStateToFillStart(this.mAnchorInfo);
            C05911Bu r32 = this.mLayoutState;
            r32.A01 = max;
            r32.A09 += r32.A0A;
            fill(r9, r32, r10, false);
            C05911Bu r07 = this.mLayoutState;
            i2 = r07.A05;
            int i16 = r07.A00;
            if (i16 > 0) {
                updateLayoutStateToFillEnd(i14, i);
                C05911Bu r08 = this.mLayoutState;
                r08.A01 = i16;
                fill(r9, r08, r10, false);
                i = this.mLayoutState.A05;
            }
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int fixLayoutEndGap2 = fixLayoutEndGap(i, r9, r10, true);
                i3 = i2 + fixLayoutEndGap2;
                i4 = i + fixLayoutEndGap2;
                fixLayoutEndGap = fixLayoutStartGap(i3, r9, r10, false);
            } else {
                int fixLayoutStartGap = fixLayoutStartGap(i2, r9, r10, true);
                i3 = i2 + fixLayoutStartGap;
                i4 = i + fixLayoutStartGap;
                fixLayoutEndGap = fixLayoutEndGap(i4, r9, r10, false);
            }
            i2 = i3 + fixLayoutEndGap;
            i = i4 + fixLayoutEndGap;
        }
        layoutForPredictiveAnimations(r9, r10, i2, i);
        if (!r10.A0A) {
            AbstractC05861Bm r14 = this.mOrientationHelper;
            r14.A00 = r14.A06();
        } else {
            this.mAnchorInfo.A00();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override // X.AnonymousClass1Ag
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.A02 = -1;
            }
            requestLayout();
        }
    }

    @Override // X.AnonymousClass1Ag
    public Parcelable onSaveInstanceState() {
        int i;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState2.A01 = z;
            if (z) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState2.A00 = this.mOrientationHelper.A02() - this.mOrientationHelper.A08(childClosestToEnd);
                i = getPosition(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                savedState2.A02 = getPosition(childClosestToStart);
                savedState2.A00 = this.mOrientationHelper.A0B(childClosestToStart) - this.mOrientationHelper.A05();
                return savedState2;
            }
        } else {
            i = -1;
        }
        savedState2.A02 = i;
        return savedState2;
    }

    public void prepareForDrop(@NonNull View view, @NonNull View view2, int i, int i2) {
        int i3;
        int i4;
        int i5;
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        char c = 65535;
        if (position < position2) {
            c = 1;
        }
        if (this.mShouldReverseLayout) {
            if (c == 1) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.A02() - (this.mOrientationHelper.A0B(view2) + this.mOrientationHelper.A09(view)));
                return;
            } else {
                i4 = this.mOrientationHelper.A02();
                i5 = this.mOrientationHelper.A08(view2);
            }
        } else if (c == 65535) {
            i3 = this.mOrientationHelper.A0B(view2);
            scrollToPositionWithOffset(position2, i3);
        } else {
            i4 = this.mOrientationHelper.A08(view2);
            i5 = this.mOrientationHelper.A09(view);
        }
        i3 = i4 - i5;
        scrollToPositionWithOffset(position2, i3);
    }

    public boolean resolveIsInfinite() {
        AbstractC05861Bm r1 = this.mOrientationHelper;
        if (r1.A04() == 0 && r1.A01() == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public int scrollHorizontallyBy(int i, AnonymousClass1Af r4, AnonymousClass1As r5) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, r4, r5);
    }

    @Override // X.AnonymousClass1Ag
    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.A02 = -1;
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.A02 = -1;
        }
        requestLayout();
    }

    @Override // X.AnonymousClass1Ag
    public int scrollVerticallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, r3, r4);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll(null);
            if (i != this.mOrientation || this.mOrientationHelper == null) {
                AbstractC05861Bm A00 = AbstractC05861Bm.A00(this, i);
                this.mOrientationHelper = A00;
                this.mAnchorInfo.A03 = A00;
                this.mOrientation = i;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A03("invalid orientation:", i));
    }

    @Override // X.AnonymousClass1Ag
    public boolean shouldMeasureTwice() {
        if (this.mHeightMode == 1073741824 || this.mWidthMode == 1073741824 || !hasFlexibleChildInBothOrientations()) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1Ag
    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd) {
            return true;
        }
        return false;
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private void recycleViewsFromEnd(AnonymousClass1Af r7, int i, int i2) {
        int i3;
        int i4;
        int childCount = getChildCount();
        if (i >= 0) {
            int A01 = (this.mOrientationHelper.A01() - i) + i2;
            if (this.mShouldReverseLayout) {
                i3 = 0;
                i4 = 0;
                while (i4 < childCount) {
                    View childAt = getChildAt(i4);
                    if (this.mOrientationHelper.A0B(childAt) >= A01 && this.mOrientationHelper.A0D(childAt) >= A01) {
                        i4++;
                    }
                }
                return;
            }
            i3 = childCount - 1;
            i4 = i3;
            while (i4 >= 0) {
                View childAt2 = getChildAt(i4);
                if (this.mOrientationHelper.A0B(childAt2) >= A01 && this.mOrientationHelper.A0D(childAt2) >= A01) {
                    i4--;
                }
            }
            return;
            recycleChildren(r7, i3, i4);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        if (r4 >= r2) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateAnchorFromChildren(X.AnonymousClass1Af r8, X.AnonymousClass1As r9, X.C05901Bt r10) {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.updateAnchorFromChildren(X.1Af, X.1As, X.1Bt):boolean");
    }

    private void updateAnchorInfoForLayout(AnonymousClass1Af r2, AnonymousClass1As r3, C05901Bt r4) {
        int A05;
        int i;
        if (!updateAnchorFromPendingData(r3, r4) && !updateAnchorFromChildren(r2, r3, r4)) {
            if (r4.A04) {
                A05 = r4.A03.A02();
            } else {
                A05 = r4.A03.A05();
            }
            r4.A01 = A05;
            if (this.mStackFromEnd) {
                i = r3.A00() - 1;
            } else {
                i = 0;
            }
            r4.A02 = i;
        }
    }

    public void calculateExtraLayoutSpace(@NonNull AnonymousClass1As r6, @NonNull int[] iArr) {
        int extraLayoutSpace = getExtraLayoutSpace(r6);
        int i = 0;
        if (this.mLayoutState.A03 != -1) {
            i = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        iArr[0] = extraLayoutSpace;
        iArr[1] = i;
    }

    @Override // X.AnonymousClass1Ag
    public int computeHorizontalScrollExtent(AnonymousClass1As r2) {
        return computeScrollExtent(r2);
    }

    @Override // X.AnonymousClass1Ag
    public int computeHorizontalScrollOffset(AnonymousClass1As r2) {
        return computeScrollOffset(r2);
    }

    @Override // X.AnonymousClass1Ag
    public int computeHorizontalScrollRange(AnonymousClass1As r2) {
        return computeScrollRange(r2);
    }

    @Override // X.AnonymousClass1BR
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        boolean z = false;
        int i2 = 1;
        if (i < getPosition(getChildAt(0))) {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            i2 = -1;
        }
        float f = (float) i2;
        if (this.mOrientation == 0) {
            return new PointF(f, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        }
        return new PointF(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, f);
    }

    @Override // X.AnonymousClass1Ag
    public int computeVerticalScrollExtent(AnonymousClass1As r2) {
        return computeScrollExtent(r2);
    }

    @Override // X.AnonymousClass1Ag
    public int computeVerticalScrollOffset(AnonymousClass1As r2) {
        return computeScrollOffset(r2);
    }

    @Override // X.AnonymousClass1Ag
    public int computeVerticalScrollRange(AnonymousClass1As r2) {
        return computeScrollRange(r2);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if (findOneVisibleChild != null) {
            return getPosition(findOneVisibleChild);
        }
        return -1;
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (findOneVisibleChild != null) {
            return getPosition(findOneVisibleChild);
        }
        return -1;
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        AnonymousClass2fg r0;
        ensureLayoutState();
        if (i2 <= i && i2 >= i) {
            return getChildAt(i);
        }
        int i3 = 4161;
        int i4 = 4097;
        if (this.mOrientationHelper.A0B(getChildAt(i)) < this.mOrientationHelper.A05()) {
            i3 = 16644;
            i4 = 16388;
        }
        if (this.mOrientation == 0) {
            r0 = this.mHorizontalBoundCheck;
        } else {
            r0 = this.mVerticalBoundCheck;
        }
        return r0.A00(i, i2, i3, i4);
    }

    public View findOneVisibleChild(int i, int i2, boolean z, boolean z2) {
        AnonymousClass2fg r0;
        ensureLayoutState();
        int i3 = 320;
        int i4 = 320;
        if (z) {
            i4 = 24579;
        }
        if (!z2) {
            i3 = 0;
        }
        if (this.mOrientation == 0) {
            r0 = this.mHorizontalBoundCheck;
        } else {
            r0 = this.mVerticalBoundCheck;
        }
        return r0.A00(i, i2, i4, i3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        if (r3 >= r6) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005a, code lost:
        if (r2 <= r5) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View findReferenceChild(X.AnonymousClass1Af r15, X.AnonymousClass1As r16, boolean r17, boolean r18) {
        /*
        // Method dump skipped, instructions count: 122
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.findReferenceChild(X.1Af, X.1As, boolean, boolean):android.view.View");
    }

    @Override // X.AnonymousClass1Ag
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    @Override // X.AnonymousClass1Ag
    public void onDetachedFromWindow(RecyclerView recyclerView, AnonymousClass1Af r3) {
        super.onDetachedFromWindow(recyclerView, r3);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(r3);
            r3.A05.clear();
            AnonymousClass1Af.A01(r3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e A[RETURN] */
    @Override // X.AnonymousClass1Ag
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onFocusSearchFailed(android.view.View r6, int r7, X.AnonymousClass1Af r8, X.AnonymousClass1As r9) {
        /*
            r5 = this;
            r5.resolveShouldLayoutReverse()
            int r0 = r5.getChildCount()
            r4 = 0
            if (r0 == 0) goto L_0x004f
            int r3 = r5.convertFocusDirectionToLayoutDirection(r7)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r2) goto L_0x004f
            r5.ensureLayoutState()
            r1 = 1051372203(0x3eaaaaab, float:0.33333334)
            X.1Bm r0 = r5.mOrientationHelper
            int r0 = r0.A06()
            float r0 = (float) r0
            float r0 = r0 * r1
            int r1 = (int) r0
            r0 = 0
            r5.updateLayoutState(r3, r1, r0, r9)
            X.1Bu r1 = r5.mLayoutState
            r1.A06 = r2
            r1.A08 = r0
            r0 = 1
            r5.fill(r8, r1, r9, r0)
            r0 = -1
            if (r3 != r0) goto L_0x0043
            android.view.View r2 = r5.findPartiallyOrCompletelyInvisibleChildClosestToStart()
        L_0x0036:
            android.view.View r1 = r5.getChildClosestToStart()
        L_0x003a:
            boolean r0 = r1.hasFocusable()
            if (r0 == 0) goto L_0x004e
            if (r2 == 0) goto L_0x004f
            return r1
        L_0x0043:
            android.view.View r2 = r5.findPartiallyOrCompletelyInvisibleChildClosestToEnd()
            if (r3 == r0) goto L_0x0036
            android.view.View r1 = r5.getChildClosestToEnd()
            goto L_0x003a
        L_0x004e:
            return r2
        L_0x004f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.onFocusSearchFailed(android.view.View, int, X.1Af, X.1As):android.view.View");
    }

    @Override // X.AnonymousClass1Ag
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // X.AnonymousClass1Ag
    public void onLayoutCompleted(AnonymousClass1As r2) {
        super.onLayoutCompleted(r2);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.A00();
    }

    public int scrollBy(int i, AnonymousClass1Af r7, AnonymousClass1As r8) {
        if (!(getChildCount() == 0 || i == 0)) {
            ensureLayoutState();
            this.mLayoutState.A08 = true;
            int i2 = -1;
            if (i > 0) {
                i2 = 1;
            }
            int abs = Math.abs(i);
            updateLayoutState(i2, abs, true, r8);
            C05911Bu r0 = this.mLayoutState;
            int fill = r0.A06 + fill(r7, r0, r8, false);
            if (fill >= 0) {
                if (abs > fill) {
                    i = i2 * fill;
                }
                this.mOrientationHelper.A0E(-i);
                this.mLayoutState.A02 = i;
                return i;
            }
        }
        return 0;
    }

    @Override // X.AnonymousClass1Ag
    public void smoothScrollToPosition(RecyclerView recyclerView, AnonymousClass1As r4, int i) {
        AnonymousClass1Ao r0 = new AnonymousClass1Ao(recyclerView.getContext());
        ((AnonymousClass1An) r0).A00 = i;
        startSmoothScroll(r0);
    }

    public void validateChildOrder() {
        getChildCount();
        if (getChildCount() >= 1) {
            boolean z = false;
            int position = getPosition(getChildAt(0));
            int A0B = this.mOrientationHelper.A0B(getChildAt(0));
            int i = 1;
            if (this.mShouldReverseLayout) {
                while (i < getChildCount()) {
                    View childAt = getChildAt(i);
                    int position2 = getPosition(childAt);
                    int A0B2 = this.mOrientationHelper.A0B(childAt);
                    if (position2 < position) {
                        logChildren();
                        if (A0B2 < A0B) {
                            z = true;
                        }
                        throw new RuntimeException(AnonymousClass006.A0E("detected invalid position. loc invalid? ", z));
                    } else if (A0B2 <= A0B) {
                        i++;
                    } else {
                        logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
                return;
            }
            while (i < getChildCount()) {
                View childAt2 = getChildAt(i);
                int position3 = getPosition(childAt2);
                int A0B3 = this.mOrientationHelper.A0B(childAt2);
                if (position3 < position) {
                    logChildren();
                    if (A0B3 < A0B) {
                        z = true;
                    }
                    throw new RuntimeException(AnonymousClass006.A0E("detected invalid position. loc invalid? ", z));
                } else if (A0B3 >= A0B) {
                    i++;
                } else {
                    logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }
        }
    }

    public void setInitialPrefetchItemCount(int i) {
        this.mInitialPrefetchItemCount = i;
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new C05901Bt();
        this.mLayoutChunkResult = new AnonymousClass1CC();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i);
        setReverseLayout(z);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new C05901Bt();
        this.mLayoutChunkResult = new AnonymousClass1CC();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        AnonymousClass1BL properties = AnonymousClass1Ag.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.A00);
        setReverseLayout(properties.A02);
        setStackFromEnd(properties.A03);
    }

    private void updateLayoutStateToFillEnd(int i, int i2) {
        this.mLayoutState.A00 = this.mOrientationHelper.A02() - i2;
        C05911Bu r3 = this.mLayoutState;
        int i3 = 1;
        if (this.mShouldReverseLayout) {
            i3 = -1;
        }
        r3.A0A = i3;
        r3.A09 = i;
        r3.A03 = 1;
        r3.A05 = i2;
        r3.A06 = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillEnd(C05901Bt r3) {
        updateLayoutStateToFillEnd(r3.A02, r3.A01);
    }

    private void updateLayoutStateToFillStart(int i, int i2) {
        this.mLayoutState.A00 = i2 - this.mOrientationHelper.A05();
        C05911Bu r3 = this.mLayoutState;
        r3.A09 = i;
        int i3 = -1;
        if (this.mShouldReverseLayout) {
            i3 = 1;
        }
        r3.A0A = i3;
        r3.A03 = -1;
        r3.A05 = i2;
        r3.A06 = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(C05901Bt r3) {
        updateLayoutStateToFillStart(r3.A02, r3.A01);
    }
}
