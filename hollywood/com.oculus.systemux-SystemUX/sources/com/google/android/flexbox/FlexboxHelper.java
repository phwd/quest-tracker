package com.google.android.flexbox;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.widget.CompoundButtonCompat;
import com.facebook.imagepipeline.common.BytesRange;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* access modifiers changed from: package-private */
public class FlexboxHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_CAPACITY = 10;
    private static final long MEASURE_SPEC_WIDTH_MASK = 4294967295L;
    private boolean[] mChildrenFrozen;
    private final FlexContainer mFlexContainer;
    @Nullable
    int[] mIndexToFlexLine;
    @Nullable
    long[] mMeasureSpecCache;
    @Nullable
    private long[] mMeasuredSizeCache;

    /* access modifiers changed from: package-private */
    public int extractHigherInt(long j) {
        return (int) (j >> 32);
    }

    /* access modifiers changed from: package-private */
    public int extractLowerInt(long j) {
        return (int) j;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public long makeCombinedLong(int i, int i2) {
        return (((long) i) & MEASURE_SPEC_WIDTH_MASK) | (((long) i2) << 32);
    }

    FlexboxHelper(FlexContainer flexContainer) {
        this.mFlexContainer = flexContainer;
    }

    /* access modifiers changed from: package-private */
    public int[] createReorderedIndices(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        List<Order> createOrders = createOrders(flexItemCount);
        Order order = new Order();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            order.order = 1;
        } else {
            order.order = ((FlexItem) layoutParams).getOrder();
        }
        if (i == -1 || i == flexItemCount) {
            order.index = flexItemCount;
        } else if (i < this.mFlexContainer.getFlexItemCount()) {
            order.index = i;
            while (i < flexItemCount) {
                createOrders.get(i).index++;
                i++;
            }
        } else {
            order.index = flexItemCount;
        }
        createOrders.add(order);
        return sortOrdersIntoReorderedIndices(flexItemCount + 1, createOrders, sparseIntArray);
    }

    /* access modifiers changed from: package-private */
    public int[] createReorderedIndices(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        return sortOrdersIntoReorderedIndices(flexItemCount, createOrders(flexItemCount), sparseIntArray);
    }

    @NonNull
    private List<Order> createOrders(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            Order order = new Order();
            order.order = ((FlexItem) this.mFlexContainer.getFlexItemAt(i2).getLayoutParams()).getOrder();
            order.index = i2;
            arrayList.add(order);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean isOrderChangedFromLastMeasurement(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View flexItemAt = this.mFlexContainer.getFlexItemAt(i);
            if (!(flexItemAt == null || ((FlexItem) flexItemAt.getLayoutParams()).getOrder() == sparseIntArray.get(i))) {
                return true;
            }
        }
        return false;
    }

    private int[] sortOrdersIntoReorderedIndices(int i, List<Order> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (Order order : list) {
            iArr[i2] = order.index;
            sparseIntArray.append(order.index, order.order);
            i2++;
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i, int i2) {
        calculateFlexLines(flexLinesResult, i, i2, BytesRange.TO_END_OF_CONTENT, 0, -1, null);
    }

    /* access modifiers changed from: package-private */
    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, @Nullable List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i, i2, i3, i4, -1, list);
    }

    /* access modifiers changed from: package-private */
    public void calculateHorizontalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i, i2, i3, 0, i4, list);
    }

    /* access modifiers changed from: package-private */
    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i, int i2) {
        calculateFlexLines(flexLinesResult, i2, i, BytesRange.TO_END_OF_CONTENT, 0, -1, null);
    }

    /* access modifiers changed from: package-private */
    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, @Nullable List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i2, i, i3, i4, -1, list);
    }

    /* access modifiers changed from: package-private */
    public void calculateVerticalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i2, i, i3, 0, i4, list);
    }

    /* access modifiers changed from: package-private */
    public void calculateFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, int i5, @Nullable List<FlexLine> list) {
        int i6;
        FlexLinesResult flexLinesResult2;
        int i7;
        int i8;
        List<FlexLine> list2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        View view;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i2;
        int i19 = i5;
        boolean isMainAxisDirectionHorizontal = this.mFlexContainer.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        ArrayList arrayList = list == null ? new ArrayList() : list;
        flexLinesResult.mFlexLines = arrayList;
        boolean z = i19 == -1;
        int paddingStartMain = getPaddingStartMain(isMainAxisDirectionHorizontal);
        int paddingEndMain = getPaddingEndMain(isMainAxisDirectionHorizontal);
        int paddingStartCross = getPaddingStartCross(isMainAxisDirectionHorizontal);
        int paddingEndCross = getPaddingEndCross(isMainAxisDirectionHorizontal);
        FlexLine flexLine = new FlexLine();
        int i20 = i4;
        flexLine.mFirstIndex = i20;
        int i21 = paddingEndMain + paddingStartMain;
        flexLine.mMainSize = i21;
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        boolean z2 = z;
        int i22 = Integer.MIN_VALUE;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        while (true) {
            if (i20 >= flexItemCount) {
                i6 = i24;
                flexLinesResult2 = flexLinesResult;
                break;
            }
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i20);
            if (reorderedFlexItemAt != null) {
                if (reorderedFlexItemAt.getVisibility() != 8) {
                    if (reorderedFlexItemAt instanceof CompoundButton) {
                        evaluateMinimumSizeForCompoundButton((CompoundButton) reorderedFlexItemAt);
                    }
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    if (flexItem.getAlignSelf() == 4) {
                        flexLine.mIndicesAlignSelfStretch.add(Integer.valueOf(i20));
                    }
                    int flexItemSizeMain = getFlexItemSizeMain(flexItem, isMainAxisDirectionHorizontal);
                    if (flexItem.getFlexBasisPercent() != -1.0f && mode == 1073741824) {
                        flexItemSizeMain = Math.round(((float) size) * flexItem.getFlexBasisPercent());
                    }
                    if (isMainAxisDirectionHorizontal) {
                        int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i17, i21 + getFlexItemMarginStartMain(flexItem, true) + getFlexItemMarginEndMain(flexItem, true), flexItemSizeMain);
                        i8 = size;
                        i7 = mode;
                        int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i18, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, true) + getFlexItemMarginEndCross(flexItem, true) + i23, getFlexItemSizeCross(flexItem, true));
                        reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        updateMeasureCache(i20, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                        i13 = childWidthMeasureSpec;
                    } else {
                        i8 = size;
                        i7 = mode;
                        int childWidthMeasureSpec2 = this.mFlexContainer.getChildWidthMeasureSpec(i18, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, false) + getFlexItemMarginEndCross(flexItem, false) + i23, getFlexItemSizeCross(flexItem, false));
                        int childHeightMeasureSpec2 = this.mFlexContainer.getChildHeightMeasureSpec(i17, getFlexItemMarginStartMain(flexItem, false) + i21 + getFlexItemMarginEndMain(flexItem, false), flexItemSizeMain);
                        reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                        updateMeasureCache(i20, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                        i13 = childHeightMeasureSpec2;
                    }
                    this.mFlexContainer.updateViewCache(i20, reorderedFlexItemAt);
                    checkSizeConstraints(reorderedFlexItemAt, i20);
                    i24 = View.combineMeasuredStates(i24, reorderedFlexItemAt.getMeasuredState());
                    int i26 = i23;
                    list2 = arrayList;
                    if (isWrapRequired(reorderedFlexItemAt, i7, i8, flexLine.mMainSize, getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal) + getViewMeasuredSizeMain(reorderedFlexItemAt, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal), flexItem, i20, i25, arrayList.size())) {
                        if (flexLine.getItemCountNotGone() > 0) {
                            addFlexLine(list2, flexLine, i20 > 0 ? i20 - 1 : 0, i26);
                            i16 = flexLine.mCrossSize + i26;
                        } else {
                            i16 = i26;
                        }
                        if (!isMainAxisDirectionHorizontal) {
                            i11 = i2;
                            view = reorderedFlexItemAt;
                            i20 = i20;
                            if (flexItem.getWidth() == -1) {
                                FlexContainer flexContainer = this.mFlexContainer;
                                view.measure(flexContainer.getChildWidthMeasureSpec(i11, flexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i16, flexItem.getWidth()), i13);
                                checkSizeConstraints(view, i20);
                            }
                        } else if (flexItem.getHeight() == -1) {
                            FlexContainer flexContainer2 = this.mFlexContainer;
                            i11 = i2;
                            i20 = i20;
                            view = reorderedFlexItemAt;
                            view.measure(i13, flexContainer2.getChildHeightMeasureSpec(i11, flexContainer2.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i16, flexItem.getHeight()));
                            checkSizeConstraints(view, i20);
                        } else {
                            i11 = i2;
                            view = reorderedFlexItemAt;
                            i20 = i20;
                        }
                        flexLine = new FlexLine();
                        flexLine.mItemCount = 1;
                        i10 = i21;
                        flexLine.mMainSize = i10;
                        flexLine.mFirstIndex = i20;
                        i26 = i16;
                        i14 = Integer.MIN_VALUE;
                        i15 = 0;
                    } else {
                        i11 = i2;
                        view = reorderedFlexItemAt;
                        i20 = i20;
                        flexLine = flexLine;
                        i10 = i21;
                        flexLine.mItemCount++;
                        i15 = i25 + 1;
                        i14 = i22;
                    }
                    flexLine.mAnyItemsHaveFlexGrow |= flexItem.getFlexGrow() != 0.0f;
                    flexLine.mAnyItemsHaveFlexShrink |= flexItem.getFlexShrink() != 0.0f;
                    int[] iArr = this.mIndexToFlexLine;
                    if (iArr != null) {
                        iArr[i20] = list2.size();
                    }
                    flexLine.mMainSize += getViewMeasuredSizeMain(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal);
                    flexLine.mTotalFlexGrow += flexItem.getFlexGrow();
                    flexLine.mTotalFlexShrink += flexItem.getFlexShrink();
                    this.mFlexContainer.onNewFlexItemAdded(view, i20, i15, flexLine);
                    int max = Math.max(i14, getViewMeasuredSizeCross(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartCross(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndCross(flexItem, isMainAxisDirectionHorizontal) + this.mFlexContainer.getDecorationLengthCrossAxis(view));
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, max);
                    if (isMainAxisDirectionHorizontal) {
                        if (this.mFlexContainer.getFlexWrap() != 2) {
                            flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, view.getBaseline() + flexItem.getMarginTop());
                        } else {
                            flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.getMarginBottom());
                        }
                    }
                    i12 = flexItemCount;
                    if (isLastFlexItem(i20, i12, flexLine)) {
                        addFlexLine(list2, flexLine, i20, i26);
                        i26 += flexLine.mCrossSize;
                    }
                    i9 = i5;
                    if (i9 != -1 && list2.size() > 0) {
                        if (list2.get(list2.size() - 1).mLastIndex >= i9 && i20 >= i9 && !z2) {
                            i26 = -flexLine.getCrossSize();
                            z2 = true;
                        }
                    }
                    if (i26 > i3 && z2) {
                        flexLinesResult2 = flexLinesResult;
                        i6 = i24;
                        break;
                    }
                    i25 = i15;
                    i22 = max;
                    i23 = i26;
                    i20++;
                    i17 = i;
                    flexItemCount = i12;
                    i18 = i11;
                    i21 = i10;
                    arrayList = list2;
                    mode = i7;
                    i19 = i9;
                    size = i8;
                } else {
                    flexLine.mGoneItemCount++;
                    flexLine.mItemCount++;
                    if (isLastFlexItem(i20, flexItemCount, flexLine)) {
                        addFlexLine(arrayList, flexLine, i20, i23);
                    }
                }
            } else if (isLastFlexItem(i20, flexItemCount, flexLine)) {
                addFlexLine(arrayList, flexLine, i20, i23);
            }
            i8 = size;
            i7 = mode;
            i11 = i18;
            i9 = i19;
            list2 = arrayList;
            i10 = i21;
            i12 = flexItemCount;
            i20++;
            i17 = i;
            flexItemCount = i12;
            i18 = i11;
            i21 = i10;
            arrayList = list2;
            mode = i7;
            i19 = i9;
            size = i8;
        }
        flexLinesResult2.mChildState = i6;
    }

    private void evaluateMinimumSizeForCompoundButton(CompoundButton compoundButton) {
        int i;
        int i2;
        FlexItem flexItem = (FlexItem) compoundButton.getLayoutParams();
        int minWidth = flexItem.getMinWidth();
        int minHeight = flexItem.getMinHeight();
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
        if (buttonDrawable == null) {
            i = 0;
        } else {
            i = buttonDrawable.getMinimumWidth();
        }
        if (buttonDrawable == null) {
            i2 = 0;
        } else {
            i2 = buttonDrawable.getMinimumHeight();
        }
        if (minWidth == -1) {
            minWidth = i;
        }
        flexItem.setMinWidth(minWidth);
        if (minHeight != -1) {
            i2 = minHeight;
        }
        flexItem.setMinHeight(i2);
    }

    private int getPaddingStartMain(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingStart();
        }
        return this.mFlexContainer.getPaddingTop();
    }

    private int getPaddingEndMain(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingEnd();
        }
        return this.mFlexContainer.getPaddingBottom();
    }

    private int getPaddingStartCross(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingTop();
        }
        return this.mFlexContainer.getPaddingStart();
    }

    private int getPaddingEndCross(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingBottom();
        }
        return this.mFlexContainer.getPaddingEnd();
    }

    private int getViewMeasuredSizeMain(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int getViewMeasuredSizeCross(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int getFlexItemSizeMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getWidth();
        }
        return flexItem.getHeight();
    }

    private int getFlexItemSizeCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    private int getFlexItemMarginStartMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginLeft();
        }
        return flexItem.getMarginTop();
    }

    private int getFlexItemMarginEndMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginRight();
        }
        return flexItem.getMarginBottom();
    }

    private int getFlexItemMarginStartCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginTop();
        }
        return flexItem.getMarginLeft();
    }

    private int getFlexItemMarginEndCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginBottom();
        }
        return flexItem.getMarginRight();
    }

    private boolean isWrapRequired(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6, int i7) {
        if (this.mFlexContainer.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.isWrapBefore()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int maxLine = this.mFlexContainer.getMaxLine();
        if (maxLine != -1 && maxLine <= i7 + 1) {
            return false;
        }
        int decorationLengthMainAxis = this.mFlexContainer.getDecorationLengthMainAxis(view, i5, i6);
        if (decorationLengthMainAxis > 0) {
            i4 += decorationLengthMainAxis;
        }
        if (i2 < i3 + i4) {
            return true;
        }
        return false;
    }

    private boolean isLastFlexItem(int i, int i2, FlexLine flexLine) {
        return i == i2 - 1 && flexLine.getItemCountNotGone() != 0;
    }

    private void addFlexLine(List<FlexLine> list, FlexLine flexLine, int i, int i2) {
        flexLine.mSumCrossSizeBefore = i2;
        this.mFlexContainer.onNewFlexLineAdded(flexLine);
        flexLine.mLastIndex = i;
        list.add(flexLine);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkSizeConstraints(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L_0x001b
            int r1 = r0.getMinWidth()
        L_0x0019:
            r3 = r4
            goto L_0x0027
        L_0x001b:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L_0x0026
            int r1 = r0.getMaxWidth()
            goto L_0x0019
        L_0x0026:
            r3 = 0
        L_0x0027:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L_0x0032
            int r2 = r0.getMinHeight()
            goto L_0x003e
        L_0x0032:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L_0x003d
            int r2 = r0.getMaxHeight()
            goto L_0x003e
        L_0x003d:
            r4 = r3
        L_0x003e:
            if (r4 == 0) goto L_0x0055
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.updateMeasureCache(r8, r1, r0, r7)
            com.google.android.flexbox.FlexContainer r0 = r6.mFlexContainer
            r0.updateViewCache(r8, r7)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxHelper.checkSizeConstraints(android.view.View, int):void");
    }

    /* access modifiers changed from: package-private */
    public void determineMainSize(int i, int i2) {
        determineMainSize(i, i2, 0);
    }

    /* access modifiers changed from: package-private */
    public void determineMainSize(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        ensureChildrenFrozen(this.mFlexContainer.getFlexItemCount());
        if (i3 < this.mFlexContainer.getFlexItemCount()) {
            int flexDirection = this.mFlexContainer.getFlexDirection();
            int flexDirection2 = this.mFlexContainer.getFlexDirection();
            if (flexDirection2 == 0 || flexDirection2 == 1) {
                int mode = View.MeasureSpec.getMode(i);
                i5 = View.MeasureSpec.getSize(i);
                int largestMainSize = this.mFlexContainer.getLargestMainSize();
                if (mode != 1073741824 && largestMainSize <= i5) {
                    i5 = largestMainSize;
                }
                i6 = this.mFlexContainer.getPaddingLeft();
                i4 = this.mFlexContainer.getPaddingRight();
            } else if (flexDirection2 == 2 || flexDirection2 == 3) {
                int mode2 = View.MeasureSpec.getMode(i2);
                i5 = View.MeasureSpec.getSize(i2);
                if (mode2 != 1073741824) {
                    i5 = this.mFlexContainer.getLargestMainSize();
                }
                i6 = this.mFlexContainer.getPaddingTop();
                i4 = this.mFlexContainer.getPaddingBottom();
            } else {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            int i7 = i6 + i4;
            int i8 = 0;
            int[] iArr = this.mIndexToFlexLine;
            if (iArr != null) {
                i8 = iArr[i3];
            }
            List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            for (int i9 = i8; i9 < size; i9++) {
                FlexLine flexLine = flexLinesInternal.get(i9);
                if (flexLine.mMainSize < i5 && flexLine.mAnyItemsHaveFlexGrow) {
                    expandFlexItems(i, i2, flexLine, i5, i7, false);
                } else if (flexLine.mMainSize > i5 && flexLine.mAnyItemsHaveFlexShrink) {
                    shrinkFlexItems(i, i2, flexLine, i5, i7, false);
                }
            }
        }
    }

    private void ensureChildrenFrozen(int i) {
        boolean[] zArr = this.mChildrenFrozen;
        if (zArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mChildrenFrozen = new boolean[i];
        } else if (zArr.length < i) {
            int length = zArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mChildrenFrozen = new boolean[i];
        } else {
            Arrays.fill(zArr, false);
        }
    }

    private void expandFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        double d;
        int i9;
        double d2;
        float f = 0.0f;
        if (flexLine.mTotalFlexGrow > 0.0f && i3 >= flexLine.mMainSize) {
            int i10 = flexLine.mMainSize;
            float f2 = ((float) (i3 - flexLine.mMainSize)) / flexLine.mTotalFlexGrow;
            flexLine.mMainSize = i4 + flexLine.mDividerLengthInMainSize;
            if (!z) {
                flexLine.mCrossSize = Integer.MIN_VALUE;
            }
            int i11 = 0;
            float f3 = 0.0f;
            boolean z2 = false;
            int i12 = 0;
            while (i11 < flexLine.mItemCount) {
                int i13 = flexLine.mFirstIndex + i11;
                View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i13);
                if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                    i5 = i10;
                } else {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = this.mFlexContainer.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr = this.mMeasuredSizeCache;
                        if (jArr != null) {
                            measuredWidth = extractLowerInt(jArr[i13]);
                        }
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr2 = this.mMeasuredSizeCache;
                        if (jArr2 != null) {
                            i5 = i10;
                            measuredHeight = extractHigherInt(jArr2[i13]);
                        } else {
                            i5 = i10;
                        }
                        if (this.mChildrenFrozen[i13] || flexItem.getFlexGrow() <= 0.0f) {
                            i8 = measuredWidth;
                            i7 = measuredHeight;
                        } else {
                            float flexGrow = ((float) measuredWidth) + (flexItem.getFlexGrow() * f2);
                            if (i11 == flexLine.mItemCount - 1) {
                                flexGrow += f3;
                                f3 = 0.0f;
                            }
                            int round = Math.round(flexGrow);
                            if (round > flexItem.getMaxWidth()) {
                                round = flexItem.getMaxWidth();
                                this.mChildrenFrozen[i13] = true;
                                flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                                z2 = true;
                            } else {
                                f3 += flexGrow - ((float) round);
                                double d3 = (double) f3;
                                if (d3 > 1.0d) {
                                    round++;
                                    d = d3 - 1.0d;
                                } else if (d3 < -1.0d) {
                                    round--;
                                    d = d3 + 1.0d;
                                }
                                f3 = (float) d;
                            }
                            int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, Ints.MAX_POWER_OF_TWO);
                            reorderedFlexItemAt.measure(makeMeasureSpec, childHeightMeasureSpecInternal);
                            i8 = reorderedFlexItemAt.getMeasuredWidth();
                            i7 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i13, makeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i13, reorderedFlexItemAt);
                        }
                        i6 = Math.max(i12, i7 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine.mMainSize += i8 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    } else {
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr3 = this.mMeasuredSizeCache;
                        if (jArr3 != null) {
                            measuredHeight2 = extractHigherInt(jArr3[i13]);
                        }
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr4 = this.mMeasuredSizeCache;
                        if (jArr4 != null) {
                            measuredWidth2 = extractLowerInt(jArr4[i13]);
                        }
                        if (this.mChildrenFrozen[i13] || flexItem.getFlexGrow() <= f) {
                            i9 = i10;
                        } else {
                            float flexGrow2 = ((float) measuredHeight2) + (flexItem.getFlexGrow() * f2);
                            if (i11 == flexLine.mItemCount - 1) {
                                flexGrow2 += f3;
                                f3 = f;
                            }
                            int round2 = Math.round(flexGrow2);
                            if (round2 > flexItem.getMaxHeight()) {
                                round2 = flexItem.getMaxHeight();
                                this.mChildrenFrozen[i13] = true;
                                flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                                i9 = i10;
                                z2 = true;
                            } else {
                                f3 += flexGrow2 - ((float) round2);
                                i9 = i10;
                                double d4 = (double) f3;
                                if (d4 > 1.0d) {
                                    round2++;
                                    d2 = d4 - 1.0d;
                                } else if (d4 < -1.0d) {
                                    round2--;
                                    d2 = d4 + 1.0d;
                                }
                                f3 = (float) d2;
                            }
                            int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, Ints.MAX_POWER_OF_TWO);
                            reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec2);
                            measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i13, childWidthMeasureSpecInternal, makeMeasureSpec2, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i13, reorderedFlexItemAt);
                            measuredHeight2 = measuredHeight3;
                        }
                        i6 = Math.max(i12, measuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine.mMainSize += measuredHeight2 + flexItem.getMarginTop() + flexItem.getMarginBottom();
                        i5 = i9;
                    }
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, i6);
                    i12 = i6;
                }
                i11++;
                i10 = i5;
                f = 0.0f;
            }
            if (z2 && i10 != flexLine.mMainSize) {
                expandFlexItems(i, i2, flexLine, i3, i4, true);
            }
        }
    }

    private void shrinkFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8 = flexLine.mMainSize;
        float f = 0.0f;
        if (flexLine.mTotalFlexShrink > 0.0f && i3 <= flexLine.mMainSize) {
            float f2 = ((float) (flexLine.mMainSize - i3)) / flexLine.mTotalFlexShrink;
            flexLine.mMainSize = i4 + flexLine.mDividerLengthInMainSize;
            if (!z) {
                flexLine.mCrossSize = Integer.MIN_VALUE;
            }
            int i9 = 0;
            float f3 = 0.0f;
            boolean z2 = false;
            int i10 = 0;
            while (i9 < flexLine.mItemCount) {
                int i11 = flexLine.mFirstIndex + i9;
                View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i11);
                if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                    i5 = i9;
                } else {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = this.mFlexContainer.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        i5 = i9;
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr = this.mMeasuredSizeCache;
                        if (jArr != null) {
                            measuredWidth = extractLowerInt(jArr[i11]);
                        }
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr2 = this.mMeasuredSizeCache;
                        if (jArr2 != null) {
                            measuredHeight = extractHigherInt(jArr2[i11]);
                        }
                        if (this.mChildrenFrozen[i11] || flexItem.getFlexShrink() <= 0.0f) {
                            i7 = measuredWidth;
                        } else {
                            float flexShrink = ((float) measuredWidth) - (flexItem.getFlexShrink() * f2);
                            if (i5 == flexLine.mItemCount - 1) {
                                flexShrink += f3;
                                f3 = 0.0f;
                            }
                            int round = Math.round(flexShrink);
                            if (round < flexItem.getMinWidth()) {
                                round = flexItem.getMinWidth();
                                this.mChildrenFrozen[i11] = true;
                                flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                                z2 = true;
                            } else {
                                f3 += flexShrink - ((float) round);
                                double d = (double) f3;
                                if (d > 1.0d) {
                                    round++;
                                    f3 -= 1.0f;
                                } else if (d < -1.0d) {
                                    round--;
                                    f3 += 1.0f;
                                }
                            }
                            int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, Ints.MAX_POWER_OF_TWO);
                            reorderedFlexItemAt.measure(makeMeasureSpec, childHeightMeasureSpecInternal);
                            i7 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i11, makeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i11, reorderedFlexItemAt);
                            measuredHeight = measuredHeight2;
                        }
                        i6 = Math.max(i10, measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine.mMainSize += i7 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    } else {
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        long[] jArr3 = this.mMeasuredSizeCache;
                        if (jArr3 != null) {
                            measuredHeight3 = extractHigherInt(jArr3[i11]);
                        }
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        long[] jArr4 = this.mMeasuredSizeCache;
                        if (jArr4 != null) {
                            measuredWidth2 = extractLowerInt(jArr4[i11]);
                        }
                        if (this.mChildrenFrozen[i11] || flexItem.getFlexShrink() <= f) {
                            i5 = i9;
                        } else {
                            float flexShrink2 = ((float) measuredHeight3) - (flexItem.getFlexShrink() * f2);
                            if (i9 == flexLine.mItemCount - 1) {
                                flexShrink2 += f3;
                                f3 = f;
                            }
                            int round2 = Math.round(flexShrink2);
                            if (round2 < flexItem.getMinHeight()) {
                                round2 = flexItem.getMinHeight();
                                this.mChildrenFrozen[i11] = true;
                                flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                                i5 = i9;
                                z2 = true;
                            } else {
                                f3 += flexShrink2 - ((float) round2);
                                i5 = i9;
                                double d2 = (double) f3;
                                if (d2 > 1.0d) {
                                    round2++;
                                    f3 -= 1.0f;
                                } else if (d2 < -1.0d) {
                                    round2--;
                                    f3 += 1.0f;
                                }
                            }
                            int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine.mSumCrossSizeBefore);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, Ints.MAX_POWER_OF_TWO);
                            reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec2);
                            measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                            int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i11, childWidthMeasureSpecInternal, makeMeasureSpec2, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i11, reorderedFlexItemAt);
                            measuredHeight3 = measuredHeight4;
                        }
                        i6 = Math.max(i10, measuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine.mMainSize += measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom();
                    }
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, i6);
                    i10 = i6;
                }
                i9 = i5 + 1;
                f = 0.0f;
            }
            if (z2 && i8 != flexLine.mMainSize) {
                shrinkFlexItems(i, i2, flexLine, i3, i4, true);
            }
        }
    }

    private int getChildWidthMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childWidthMeasureSpec = flexContainer.getChildWidthMeasureSpec(i, flexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i2, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > flexItem.getMaxWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return size < flexItem.getMinWidth() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : childWidthMeasureSpec;
    }

    private int getChildHeightMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childHeightMeasureSpec = flexContainer.getChildHeightMeasureSpec(i, flexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i2, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > flexItem.getMaxHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return size < flexItem.getMinHeight() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : childHeightMeasureSpec;
    }

    /* access modifiers changed from: package-private */
    public void determineCrossSize(int i, int i2, int i3) {
        int i4;
        int i5;
        int flexDirection = this.mFlexContainer.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            i5 = View.MeasureSpec.getMode(i2);
            i4 = View.MeasureSpec.getSize(i2);
        } else if (flexDirection == 2 || flexDirection == 3) {
            int mode = View.MeasureSpec.getMode(i);
            i4 = View.MeasureSpec.getSize(i);
            i5 = mode;
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        if (i5 == 1073741824) {
            int sumOfCrossSize = this.mFlexContainer.getSumOfCrossSize() + i3;
            int i6 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).mCrossSize = i4 - i3;
            } else if (flexLinesInternal.size() >= 2) {
                int alignContent = this.mFlexContainer.getAlignContent();
                if (alignContent == 1) {
                    int i7 = i4 - sumOfCrossSize;
                    FlexLine flexLine = new FlexLine();
                    flexLine.mCrossSize = i7;
                    flexLinesInternal.add(0, flexLine);
                } else if (alignContent == 2) {
                    this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, i4, sumOfCrossSize));
                } else if (alignContent != 3) {
                    if (alignContent != 4) {
                        if (alignContent == 5 && sumOfCrossSize < i4) {
                            float size = ((float) (i4 - sumOfCrossSize)) / ((float) flexLinesInternal.size());
                            int size2 = flexLinesInternal.size();
                            float f = 0.0f;
                            while (i6 < size2) {
                                FlexLine flexLine2 = flexLinesInternal.get(i6);
                                float f2 = ((float) flexLine2.mCrossSize) + size;
                                if (i6 == flexLinesInternal.size() - 1) {
                                    f2 += f;
                                    f = 0.0f;
                                }
                                int round = Math.round(f2);
                                f += f2 - ((float) round);
                                if (f > 1.0f) {
                                    round++;
                                    f -= 1.0f;
                                } else if (f < -1.0f) {
                                    round--;
                                    f += 1.0f;
                                }
                                flexLine2.mCrossSize = round;
                                i6++;
                            }
                        }
                    } else if (sumOfCrossSize >= i4) {
                        this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, i4, sumOfCrossSize));
                    } else {
                        int size3 = (i4 - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                        ArrayList arrayList = new ArrayList();
                        FlexLine flexLine3 = new FlexLine();
                        flexLine3.mCrossSize = size3;
                        for (FlexLine flexLine4 : flexLinesInternal) {
                            arrayList.add(flexLine3);
                            arrayList.add(flexLine4);
                            arrayList.add(flexLine3);
                        }
                        this.mFlexContainer.setFlexLines(arrayList);
                    }
                } else if (sumOfCrossSize < i4) {
                    float size4 = ((float) (i4 - sumOfCrossSize)) / ((float) (flexLinesInternal.size() - 1));
                    ArrayList arrayList2 = new ArrayList();
                    int size5 = flexLinesInternal.size();
                    float f3 = 0.0f;
                    while (i6 < size5) {
                        arrayList2.add(flexLinesInternal.get(i6));
                        if (i6 != flexLinesInternal.size() - 1) {
                            FlexLine flexLine5 = new FlexLine();
                            if (i6 == flexLinesInternal.size() - 2) {
                                flexLine5.mCrossSize = Math.round(f3 + size4);
                                f3 = 0.0f;
                            } else {
                                flexLine5.mCrossSize = Math.round(size4);
                            }
                            f3 += size4 - ((float) flexLine5.mCrossSize);
                            if (f3 > 1.0f) {
                                flexLine5.mCrossSize++;
                                f3 -= 1.0f;
                            } else if (f3 < -1.0f) {
                                flexLine5.mCrossSize--;
                                f3 += 1.0f;
                            }
                            arrayList2.add(flexLine5);
                        }
                        i6++;
                    }
                    this.mFlexContainer.setFlexLines(arrayList2);
                }
            }
        }
    }

    private List<FlexLine> constructFlexLinesForAlignContentCenter(List<FlexLine> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        FlexLine flexLine = new FlexLine();
        flexLine.mCrossSize = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(flexLine);
            }
            arrayList.add(list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void stretchViews() {
        stretchViews(0);
    }

    /* access modifiers changed from: package-private */
    public void stretchViews(int i) {
        View reorderedFlexItemAt;
        if (i < this.mFlexContainer.getFlexItemCount()) {
            int flexDirection = this.mFlexContainer.getFlexDirection();
            if (this.mFlexContainer.getAlignItems() == 4) {
                int[] iArr = this.mIndexToFlexLine;
                List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
                int size = flexLinesInternal.size();
                for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
                    FlexLine flexLine = flexLinesInternal.get(i2);
                    int i3 = flexLine.mItemCount;
                    for (int i4 = 0; i4 < i3; i4++) {
                        int i5 = flexLine.mFirstIndex + i4;
                        if (!(i4 >= this.mFlexContainer.getFlexItemCount() || (reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i5)) == null || reorderedFlexItemAt.getVisibility() == 8)) {
                            FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                            if (flexItem.getAlignSelf() == -1 || flexItem.getAlignSelf() == 4) {
                                if (flexDirection == 0 || flexDirection == 1) {
                                    stretchViewVertically(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                                } else if (flexDirection == 2 || flexDirection == 3) {
                                    stretchViewHorizontally(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                                } else {
                                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                                }
                            }
                        }
                    }
                }
                return;
            }
            for (FlexLine flexLine2 : this.mFlexContainer.getFlexLinesInternal()) {
                Iterator<Integer> it = flexLine2.mIndicesAlignSelfStretch.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Integer next = it.next();
                        View reorderedFlexItemAt2 = this.mFlexContainer.getReorderedFlexItemAt(next.intValue());
                        if (flexDirection == 0 || flexDirection == 1) {
                            stretchViewVertically(reorderedFlexItemAt2, flexLine2.mCrossSize, next.intValue());
                        } else {
                            if (flexDirection != 2) {
                                if (flexDirection != 3) {
                                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                                }
                            }
                            stretchViewHorizontally(reorderedFlexItemAt2, flexLine2.mCrossSize, next.intValue());
                        }
                    }
                }
            }
        }
    }

    private void stretchViewVertically(View view, int i, int i2) {
        int i3;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr != null) {
            i3 = extractLowerInt(jArr[i2]);
        } else {
            i3 = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, Ints.MAX_POWER_OF_TWO);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, Ints.MAX_POWER_OF_TWO);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        updateMeasureCache(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    private void stretchViewHorizontally(View view, int i, int i2) {
        int i3;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr != null) {
            i3 = extractHigherInt(jArr[i2]);
        } else {
            i3 = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, Ints.MAX_POWER_OF_TWO);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, Ints.MAX_POWER_OF_TWO);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        updateMeasureCache(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    /* access modifiers changed from: package-private */
    public void layoutSingleChildHorizontal(View view, FlexLine flexLine, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                    if (this.mFlexContainer.getFlexWrap() != 2) {
                        int i6 = i2 + measuredHeight;
                        view.layout(i, i6, i3, view.getMeasuredHeight() + i6);
                        return;
                    }
                    int i7 = i2 - measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                } else if (alignItems != 3) {
                    if (alignItems != 4) {
                        return;
                    }
                } else if (this.mFlexContainer.getFlexWrap() != 2) {
                    int max = Math.max(flexLine.mMaxBaseline - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                } else {
                    int max2 = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), flexItem.getMarginBottom());
                    view.layout(i, i2 - max2, i3, i4 - max2);
                    return;
                }
            } else if (this.mFlexContainer.getFlexWrap() != 2) {
                int i8 = i2 + i5;
                view.layout(i, (i8 - view.getMeasuredHeight()) - flexItem.getMarginBottom(), i3, i8 - flexItem.getMarginBottom());
                return;
            } else {
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop());
                return;
            }
        }
        if (this.mFlexContainer.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.getMarginTop(), i3, i4 + flexItem.getMarginTop());
        } else {
            view.layout(i, i2 - flexItem.getMarginBottom(), i3, i4 - flexItem.getMarginBottom());
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutSingleChildVertical(View view, FlexLine flexLine, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                    if (!z) {
                        view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                        return;
                    } else {
                        view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                        return;
                    }
                } else if (!(alignItems == 3 || alignItems == 4)) {
                    return;
                }
            } else if (!z) {
                view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i4);
                return;
            } else {
                view.layout((i - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i4);
                return;
            }
        }
        if (!z) {
            view.layout(i + flexItem.getMarginLeft(), i2, i3 + flexItem.getMarginLeft(), i4);
        } else {
            view.layout(i - flexItem.getMarginRight(), i2, i3 - flexItem.getMarginRight(), i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureMeasuredSizeCache(int i) {
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mMeasuredSizeCache = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mMeasuredSizeCache = Arrays.copyOf(this.mMeasuredSizeCache, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureMeasureSpecCache(int i) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mMeasureSpecCache = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mMeasureSpecCache = Arrays.copyOf(this.mMeasureSpecCache, i);
        }
    }

    private void updateMeasureCache(int i, int i2, int i3, View view) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr != null) {
            jArr[i] = makeCombinedLong(i2, i3);
        }
        long[] jArr2 = this.mMeasuredSizeCache;
        if (jArr2 != null) {
            jArr2[i] = makeCombinedLong(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureIndexToFlexLine(int i) {
        int[] iArr = this.mIndexToFlexLine;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.mIndexToFlexLine = new int[i];
        } else if (iArr.length < i) {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mIndexToFlexLine = Arrays.copyOf(this.mIndexToFlexLine, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void clearFlexLines(List<FlexLine> list, int i) {
        int i2 = this.mIndexToFlexLine[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int[] iArr = this.mIndexToFlexLine;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.mMeasureSpecCache;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    /* access modifiers changed from: private */
    public static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        public int compareTo(@NonNull Order order2) {
            int i = this.order;
            int i2 = order2.order;
            if (i != i2) {
                return i - i2;
            }
            return this.index - order2.index;
        }

        @NonNull
        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }
    }

    /* access modifiers changed from: package-private */
    public static class FlexLinesResult {
        int mChildState;
        List<FlexLine> mFlexLines;

        FlexLinesResult() {
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mFlexLines = null;
            this.mChildState = 0;
        }
    }
}
