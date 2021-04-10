package androidx.recyclerview.widget;

import X.AnonymousClass006;
import X.AnonymousClass07z;
import X.AnonymousClass1Af;
import X.AnonymousClass1Ag;
import X.AnonymousClass1As;
import X.AnonymousClass1C5;
import X.AnonymousClass1C6;
import X.AnonymousClass1CE;
import X.AnonymousClass1CF;
import X.C05831Bi;
import X.C05901Bt;
import X.C05911Bu;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class GridLayoutManager extends LinearLayoutManager {
    public int A00 = -1;
    public AnonymousClass1C6 A01 = new AnonymousClass1CE();
    public boolean A02 = false;
    public int[] A03;
    public View[] A04;
    public final Rect A05 = new Rect();
    public final SparseIntArray A06 = new SparseIntArray();
    public final SparseIntArray A07 = new SparseIntArray();

    private int A00(AnonymousClass1Af r7, AnonymousClass1As r8, int i) {
        int i2;
        int i3;
        int i4;
        if (r8.A0A) {
            int A032 = r7.A03(i);
            if (A032 != -1) {
                i3 = this.A00;
                i4 = 0;
                i2 = 0;
                int i5 = 0;
                while (true) {
                    i4++;
                    if (i5 >= A032) {
                        break;
                    }
                    if (i4 == i3) {
                        i2++;
                        i4 = 0;
                    } else if (i4 > i3) {
                        i2++;
                        i4 = 1;
                    }
                    i5++;
                }
            } else {
                Log.w("GridLayoutManager", AnonymousClass006.A03("Cannot find span size for pre layout position. ", i));
                return 0;
            }
        } else {
            i3 = this.A00;
            i4 = 0;
            i2 = 0;
            int i6 = 0;
            while (true) {
                i4++;
                if (i6 >= i) {
                    break;
                }
                if (i4 == i3) {
                    i2++;
                    i4 = 0;
                } else if (i4 > i3) {
                    i2++;
                    i4 = 1;
                }
                i6++;
            }
        }
        if (i4 > i3) {
            return i2 + 1;
        }
        return i2;
    }

    private int A01(AnonymousClass1Af r4, AnonymousClass1As r5, int i) {
        if (!r5.A0A) {
            return this.A01.A00(i, this.A00);
        }
        int i2 = this.A06.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int A032 = r4.A03(i);
        if (A032 != -1) {
            return this.A01.A00(A032, this.A00);
        }
        Log.w("GridLayoutManager", AnonymousClass006.A03("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:", i));
        return 0;
    }

    private int A02(AnonymousClass1Af r3, AnonymousClass1As r4, int i) {
        if (r4.A0A) {
            int i2 = this.A07.get(i, -1);
            if (i2 != -1) {
                return i2;
            }
            if (r3.A03(i) == -1) {
                Log.w("GridLayoutManager", AnonymousClass006.A03("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:", i));
            }
        }
        return 1;
    }

    private void A03() {
        View[] viewArr = this.A04;
        if (viewArr == null || viewArr.length != this.A00) {
            this.A04 = new View[this.A00];
        }
    }

    private void A04() {
        int paddingBottom;
        int paddingTop;
        if (this.mOrientation == 1) {
            paddingBottom = this.mWidth - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            paddingBottom = this.mHeight - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        A05(paddingBottom - paddingTop);
    }

    private void A05(int i) {
        int i2;
        int length;
        int[] iArr = this.A03;
        int i3 = this.A00;
        if (!(iArr != null && (length = iArr.length) == i3 + 1 && iArr[length - 1] == i)) {
            iArr = new int[(i3 + 1)];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.A03 = iArr;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void collectPrefetchPositionsForLayoutState(AnonymousClass1As r6, C05911Bu r7, AnonymousClass1CF r8) {
        int i;
        int i2 = this.A00;
        for (int i3 = 0; i3 < this.A00 && (i = r7.A09) >= 0 && i < r6.A00() && i2 > 0; i3++) {
            r8.A1J(i, Math.max(0, r7.A06));
            i2--;
            r7.A09 += r7.A0A;
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final C05831Bi generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new AnonymousClass1C5(-2, -1);
        }
        return new AnonymousClass1C5(-1, -2);
    }

    @Override // X.AnonymousClass1Ag
    public final int getColumnCountForAccessibility(AnonymousClass1Af r3, AnonymousClass1As r4) {
        if (this.mOrientation == 1) {
            return this.A00;
        }
        int A002 = r4.A00();
        if (A002 < 1) {
            return 0;
        }
        return A00(r3, r4, A002 - 1) + 1;
    }

    @Override // X.AnonymousClass1Ag
    public final int getRowCountForAccessibility(AnonymousClass1Af r3, AnonymousClass1As r4) {
        if (this.mOrientation == 0) {
            return this.A00;
        }
        int A002 = r4.A00();
        if (A002 < 1) {
            return 0;
        }
        return A00(r3, r4, A002 - 1) + 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r19v0, resolved type: androidx.recyclerview.widget.GridLayoutManager */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cc  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void layoutChunk(X.AnonymousClass1Af r20, X.AnonymousClass1As r21, X.C05911Bu r22, X.AnonymousClass1CC r23) {
        /*
        // Method dump skipped, instructions count: 624
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.layoutChunk(X.1Af, X.1As, X.1Bu, X.1CC):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fc, code lost:
        if (r22 != null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fe, code lost:
        return r18;
     */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View onFocusSearchFailed(android.view.View r29, int r30, X.AnonymousClass1Af r31, X.AnonymousClass1As r32) {
        /*
        // Method dump skipped, instructions count: 265
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, X.1Af, X.1As):android.view.View");
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        this.A01.A01.clear();
        this.A01.A00.clear();
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsChanged(RecyclerView recyclerView) {
        this.A01.A01.clear();
        this.A01.A00.clear();
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        this.A01.A01.clear();
        this.A01.A00.clear();
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        this.A01.A01.clear();
        this.A01.A00.clear();
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.A01.A01.clear();
        this.A01.A00.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final void onLayoutChildren(AnonymousClass1Af r7, AnonymousClass1As r8) {
        if (r8.A0A) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                AnonymousClass1C5 r3 = (AnonymousClass1C5) getChildAt(i).getLayoutParams();
                int layoutPosition = ((C05831Bi) r3).A01.getLayoutPosition();
                this.A07.put(layoutPosition, r3.A01);
                this.A06.put(layoutPosition, r3.A00);
            }
        }
        super.onLayoutChildren(r7, r8);
        this.A07.clear();
        this.A06.clear();
    }

    @Override // X.AnonymousClass1Ag
    public final void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        if (this.A03 == null) {
            super.setMeasuredDimension(rect, i, i2);
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = AnonymousClass1Ag.chooseSize(i2, rect.height() + paddingTop, this.mRecyclerView.getMinimumHeight());
            int[] iArr = this.A03;
            chooseSize = AnonymousClass1Ag.chooseSize(i, iArr[iArr.length - 1] + paddingLeft, this.mRecyclerView.getMinimumWidth());
        } else {
            chooseSize = AnonymousClass1Ag.chooseSize(i, rect.width() + paddingLeft, this.mRecyclerView.getMinimumWidth());
            int[] iArr2 = this.A03;
            chooseSize2 = AnonymousClass1Ag.chooseSize(i2, iArr2[iArr2.length - 1] + paddingTop, this.mRecyclerView.getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void setStackFromEnd(boolean z) {
        if (!z) {
            super.setStackFromEnd(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState != null || this.A02) {
            return false;
        }
        return true;
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3 = AnonymousClass1Ag.getProperties(context, attributeSet, i, i2).A01;
        if (i3 != this.A00) {
            this.A02 = true;
            if (i3 >= 1) {
                this.A00 = i3;
                this.A01.A01.clear();
                requestLayout();
                return;
            }
            throw new IllegalArgumentException(AnonymousClass006.A03("Span count should be at least 1. Provided ", i3));
        }
    }

    private void A06(View view, int i, boolean z) {
        int i2;
        int i3;
        int childMeasureSpec;
        int childMeasureSpec2;
        boolean shouldMeasureChild;
        AnonymousClass1C5 r5 = (AnonymousClass1C5) view.getLayoutParams();
        Rect rect = r5.A03;
        int i4 = rect.top + rect.bottom + r5.topMargin + r5.bottomMargin;
        int i5 = rect.left + rect.right + r5.leftMargin + r5.rightMargin;
        int i6 = r5.A00;
        int i7 = r5.A01;
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.A03;
            i2 = iArr[i7 + i6];
            i3 = iArr[i6];
        } else {
            int[] iArr2 = this.A03;
            int i8 = this.A00 - i6;
            i2 = iArr2[i8];
            i3 = iArr2[i8 - i7];
        }
        int i9 = i2 - i3;
        if (this.mOrientation == 1) {
            childMeasureSpec2 = AnonymousClass1Ag.getChildMeasureSpec(i9, i, i5, r5.width, false);
            childMeasureSpec = AnonymousClass1Ag.getChildMeasureSpec(this.mOrientationHelper.A06(), this.mHeightMode, i4, r5.height, true);
        } else {
            childMeasureSpec = AnonymousClass1Ag.getChildMeasureSpec(i9, i, i4, r5.height, false);
            childMeasureSpec2 = AnonymousClass1Ag.getChildMeasureSpec(this.mOrientationHelper.A06(), this.mWidthMode, i5, r5.width, true);
        }
        C05831Bi r1 = (C05831Bi) view.getLayoutParams();
        if (z) {
            shouldMeasureChild = shouldReMeasureChild(view, childMeasureSpec2, childMeasureSpec, r1);
        } else {
            shouldMeasureChild = shouldMeasureChild(view, childMeasureSpec2, childMeasureSpec, r1);
        }
        if (shouldMeasureChild) {
            view.measure(childMeasureSpec2, childMeasureSpec);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final int computeHorizontalScrollOffset(AnonymousClass1As r2) {
        return super.computeHorizontalScrollOffset(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final int computeHorizontalScrollRange(AnonymousClass1As r2) {
        return super.computeHorizontalScrollRange(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final int computeVerticalScrollOffset(AnonymousClass1As r2) {
        return super.computeVerticalScrollOffset(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final int computeVerticalScrollRange(AnonymousClass1As r2) {
        return super.computeVerticalScrollRange(r2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View findReferenceChild(AnonymousClass1Af r11, AnonymousClass1As r12, boolean z, boolean z2) {
        int i;
        int childCount = getChildCount();
        int i2 = -1;
        int i3 = 1;
        if (z2) {
            i = getChildCount() - 1;
            i3 = -1;
        } else {
            i2 = childCount;
            i = 0;
        }
        int A002 = r12.A00();
        ensureLayoutState();
        int A052 = this.mOrientationHelper.A05();
        int A022 = this.mOrientationHelper.A02();
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < A002 && A01(r11, r12, position) == 0) {
                if (((C05831Bi) childAt.getLayoutParams()).A01.isRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.A0B(childAt) < A022 && this.mOrientationHelper.A08(childAt) >= A052) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i3;
        }
        if (view == null) {
            return view2;
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void onAnchorReady(AnonymousClass1Af r6, AnonymousClass1As r7, C05901Bt r8, int i) {
        super.onAnchorReady(r6, r7, r8, i);
        A04();
        if (r7.A00() > 0 && !r7.A0A) {
            boolean z = false;
            if (i == 1) {
                z = true;
            }
            int A012 = A01(r6, r7, r8.A02);
            if (z) {
                while (A012 > 0) {
                    int i2 = r8.A02;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    r8.A02 = i3;
                    A012 = A01(r6, r7, i3);
                }
            } else {
                int A002 = r7.A00() - 1;
                int i4 = r8.A02;
                while (i4 < A002) {
                    int i5 = i4 + 1;
                    int A013 = A01(r6, r7, i5);
                    if (A013 <= A012) {
                        break;
                    }
                    i4 = i5;
                    A012 = A013;
                }
                r8.A02 = i4;
            }
        }
        A03();
    }

    @Override // X.AnonymousClass1Ag
    public final void onInitializeAccessibilityNodeInfoForItem(AnonymousClass1Af r11, AnonymousClass1As r12, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfo.CollectionItemInfo obtain;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof AnonymousClass1C5)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        AnonymousClass1C5 r1 = (AnonymousClass1C5) layoutParams;
        int A002 = A00(r11, r12, ((C05831Bi) r1).A01.getLayoutPosition());
        int i = this.mOrientation;
        int i2 = r1.A00;
        if (i == 0) {
            obtain = AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, r1.A01, A002, 1, false, false);
        } else {
            obtain = AccessibilityNodeInfo.CollectionItemInfo.obtain(A002, 1, i2, r1.A01, false, false);
        }
        accessibilityNodeInfoCompat.A00.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) new AnonymousClass07z(obtain).A00);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final void onLayoutCompleted(AnonymousClass1As r2) {
        super.onLayoutCompleted(r2);
        this.A02 = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final int scrollHorizontallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        A04();
        A03();
        return super.scrollHorizontallyBy(i, r3, r4);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
    public final int scrollVerticallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        A04();
        A03();
        return super.scrollVerticallyBy(i, r3, r4);
    }

    @Override // X.AnonymousClass1Ag
    public final boolean checkLayoutParams(C05831Bi r2) {
        return r2 instanceof AnonymousClass1C5;
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new AnonymousClass1C5(context, attributeSet);
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new AnonymousClass1C5((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new AnonymousClass1C5(layoutParams);
    }
}
