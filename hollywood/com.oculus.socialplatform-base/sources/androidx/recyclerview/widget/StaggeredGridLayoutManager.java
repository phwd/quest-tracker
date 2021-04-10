package androidx.recyclerview.widget;

import X.AbstractC05861Bm;
import X.AnonymousClass02C;
import X.AnonymousClass1Af;
import X.AnonymousClass1Ag;
import X.AnonymousClass1Aj;
import X.AnonymousClass1An;
import X.AnonymousClass1Ao;
import X.AnonymousClass1As;
import X.AnonymousClass1BL;
import X.AnonymousClass1BR;
import X.AnonymousClass1Bv;
import X.AnonymousClass1C1;
import X.AnonymousClass1C4;
import X.AnonymousClass1C9;
import X.AnonymousClass1CD;
import X.AnonymousClass1CF;
import X.C05831Bi;
import X.C05891Bs;
import X.C05921By;
import X.C05931Bz;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends AnonymousClass1Ag implements AnonymousClass1BR {
    public int A00;
    public int A01 = -1;
    public int A02 = Integer.MIN_VALUE;
    public int A03;
    public int A04 = -1;
    @NonNull
    public AbstractC05861Bm A05;
    @NonNull
    public AbstractC05861Bm A06;
    public AnonymousClass1Bv A07 = new AnonymousClass1Bv();
    public SavedState A08;
    public BitSet A09;
    public boolean A0A;
    public boolean A0B;
    public boolean A0C = false;
    public boolean A0D = false;
    public C05891Bs[] A0E;
    public int A0F = 2;
    public boolean A0G = true;
    public int[] A0H;
    public final Rect A0I = new Rect();
    @NonNull
    public final C05931Bz A0J;
    public final AnonymousClass1C1 A0K = new AnonymousClass1C1(this);
    public final Runnable A0L = new AnonymousClass1CD(this);

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1C9();
        public int A00;
        public int A01;
        public int A02;
        public int A03;
        public List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> A04;
        public boolean A05;
        public boolean A06;
        public boolean A07;
        public int[] A08;
        public int[] A09;

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.A00);
            parcel.writeInt(this.A03);
            parcel.writeInt(this.A02);
            if (this.A02 > 0) {
                parcel.writeIntArray(this.A09);
            }
            parcel.writeInt(this.A01);
            if (this.A01 > 0) {
                parcel.writeIntArray(this.A08);
            }
            parcel.writeInt(this.A07 ? 1 : 0);
            parcel.writeInt(this.A05 ? 1 : 0);
            parcel.writeInt(this.A06 ? 1 : 0);
            parcel.writeList(this.A04);
        }

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.A00 = parcel.readInt();
            this.A03 = parcel.readInt();
            int readInt = parcel.readInt();
            this.A02 = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.A09 = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.A01 = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.A08 = iArr2;
                parcel.readIntArray(iArr2);
            }
            boolean z = false;
            this.A07 = parcel.readInt() == 1;
            this.A05 = parcel.readInt() == 1;
            this.A06 = parcel.readInt() == 1 ? true : z;
            this.A04 = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.A02 = savedState.A02;
            this.A00 = savedState.A00;
            this.A03 = savedState.A03;
            this.A09 = savedState.A09;
            this.A01 = savedState.A01;
            this.A08 = savedState.A08;
            this.A07 = savedState.A07;
            this.A05 = savedState.A05;
            this.A06 = savedState.A06;
            this.A04 = savedState.A04;
        }
    }

    private int A05(AnonymousClass1As r7) {
        if (getChildCount() == 0) {
            return 0;
        }
        return C05921By.A00(r7, this.A05, A0A(!this.A0G), A09(!this.A0G), this, this.A0G);
    }

    private int A06(AnonymousClass1As r8) {
        if (getChildCount() == 0) {
            return 0;
        }
        return C05921By.A02(r8, this.A05, A0A(!this.A0G), A09(!this.A0G), this, this.A0G, this.A0D);
    }

    private int A07(AnonymousClass1As r7) {
        if (getChildCount() == 0) {
            return 0;
        }
        return C05921By.A01(r7, this.A05, A0A(!this.A0G), A09(!this.A0G), this, this.A0G);
    }

    private final void A0F(int i, AnonymousClass1As r6) {
        int A002;
        int i2;
        if (i > 0) {
            A002 = A01();
            i2 = 1;
        } else {
            A002 = A00();
            i2 = -1;
        }
        C05931Bz r1 = this.A0J;
        r1.A04 = true;
        A0E(A002, r6);
        A0C(i2);
        r1.A06 = A002 + r1.A07;
        r1.A00 = Math.abs(i);
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        A0D(i, i2, 1);
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        A0D(i, i2, 2);
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        A0D(i, i2, 4);
    }

    @Override // X.AnonymousClass1Ag
    public final void onLayoutChildren(AnonymousClass1Af r2, AnonymousClass1As r3) {
        A0J(r2, r3, true);
    }

    public static int A02(int i, int i2, int i3) {
        int mode;
        if ((i2 != 0 || i3 != 0) && ((mode = View.MeasureSpec.getMode(i)) == Integer.MIN_VALUE || mode == 1073741824)) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode);
        }
        return i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r27v0, resolved type: androidx.recyclerview.widget.StaggeredGridLayoutManager */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        if (r7 >= r30.A00()) goto L_0x005b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x026c  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int A04(X.AnonymousClass1Af r28, X.C05931Bz r29, X.AnonymousClass1As r30) {
        /*
        // Method dump skipped, instructions count: 719
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.A04(X.1Af, X.1Bz, X.1As):int");
    }

    private final View A09(boolean z) {
        int A052 = this.A05.A05();
        int A022 = this.A05.A02();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int A0B2 = this.A05.A0B(childAt);
            int A082 = this.A05.A08(childAt);
            if (A082 > A052 && A0B2 < A022) {
                if (A082 <= A022 || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    private final View A0A(boolean z) {
        int A052 = this.A05.A05();
        int A022 = this.A05.A02();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int A0B2 = this.A05.A0B(childAt);
            if (this.A05.A08(childAt) > A052 && A0B2 < A022) {
                if (A0B2 >= A052 || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    private void A0B() {
        boolean z;
        if (this.A00 == 1 || !A0L()) {
            z = this.A0C;
        } else {
            z = !this.A0C;
        }
        this.A0D = z;
    }

    private void A0C(int i) {
        C05931Bz r4 = this.A0J;
        r4.A08 = i;
        boolean z = this.A0D;
        int i2 = 1;
        boolean z2 = false;
        if (i == -1) {
            z2 = true;
        }
        if (z != z2) {
            i2 = -1;
        }
        r4.A07 = i2;
    }

    private void A0D(int i, int i2, int i3) {
        int A002;
        int i4;
        int i5;
        int A012;
        int[] iArr;
        int length;
        if (this.A0D) {
            A002 = A01();
        } else {
            A002 = A00();
        }
        if (i3 == 8) {
            i4 = i2 + 1;
            if (i >= i2) {
                i4 = i + 1;
                i5 = i2;
            }
            i5 = i;
        } else {
            i4 = i + i2;
            i5 = i;
        }
        AnonymousClass1Bv r8 = this.A07;
        int[] iArr2 = r8.A01;
        if (iArr2 != null && i5 < iArr2.length) {
            List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> list = r8.A00;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = r8.A00.get(size);
                    if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.A02 == i5) {
                        r8.A00.remove(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem);
                        break;
                    }
                }
                int size2 = r8.A00.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size2) {
                        break;
                    } else if (r8.A00.get(i6).A02 < i5) {
                        i6++;
                    } else if (i6 != -1) {
                        r8.A00.remove(i6);
                        int i7 = r8.A00.get(i6).A02;
                        if (i7 != -1) {
                            iArr = r8.A01;
                            length = Math.min(i7 + 1, iArr.length);
                        }
                    }
                }
            }
            iArr = r8.A01;
            length = iArr.length;
            Arrays.fill(iArr, i5, length, -1);
        }
        if (i3 == 1) {
            this.A07.A02(i, i2);
        } else if (i3 == 2) {
            this.A07.A03(i, i2);
        } else if (i3 == 8) {
            this.A07.A03(i, 1);
            this.A07.A02(i2, 1);
        }
        if (i4 > A002) {
            if (this.A0D) {
                A012 = A00();
            } else {
                A012 = A01();
            }
            if (i5 <= A012) {
                requestLayout();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0E(int r8, X.AnonymousClass1As r9) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.A0E(int, X.1As):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000f, code lost:
        if (r11.A08 == -1) goto L_0x0011;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0G(X.AnonymousClass1Af r10, X.C05931Bz r11) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.A0G(X.1Af, X.1Bz):void");
    }

    private void A0H(AnonymousClass1Af r5, AnonymousClass1As r6, boolean z) {
        int A022;
        int A052 = this.A0E[0].A05(Integer.MIN_VALUE);
        for (int i = 1; i < this.A04; i++) {
            int A053 = this.A0E[i].A05(Integer.MIN_VALUE);
            if (A053 > A052) {
                A052 = A053;
            }
        }
        if (A052 != Integer.MIN_VALUE && (A022 = this.A05.A02() - A052) > 0) {
            int i2 = A022 - (-A03(-A022, r5, r6));
            if (z && i2 > 0) {
                this.A05.A0E(i2);
            }
        }
    }

    private void A0K(C05891Bs r5, int i, int i2) {
        int i3 = r5.A02;
        if (i == -1) {
            int i4 = r5.A01;
            if (i4 == Integer.MIN_VALUE) {
                C05891Bs.A02(r5);
                i4 = r5.A01;
            }
            if (i4 + i3 > i2) {
                return;
            }
        } else {
            int i5 = r5.A00;
            if (i5 == Integer.MIN_VALUE) {
                C05891Bs.A01(r5);
                i5 = r5.A00;
            }
            if (i5 - i3 < i2) {
                return;
            }
        }
        this.A09.set(r5.A04, false);
    }

    private final boolean A0L() {
        if (this.mRecyclerView.getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }

    private boolean A0M(int i) {
        if (this.A00 == 0) {
            boolean z = false;
            if (i == -1) {
                z = true;
            }
            if (z != this.A0D) {
                return true;
            }
            return false;
        }
        boolean z2 = false;
        if (i == -1) {
            z2 = true;
        }
        boolean z3 = false;
        if (z2 == this.A0D) {
            z3 = true;
        }
        if (z3 == A0L()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public final void assertNotInLayoutOrScroll(String str) {
        if (this.A08 == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // X.AnonymousClass1Ag
    public final boolean canScrollHorizontally() {
        if (this.A00 == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public final boolean canScrollVertically() {
        if (this.A00 != 1) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1Ag
    @RestrictTo({AnonymousClass02C.LIBRARY})
    public final void collectAdjacentPrefetchPositions(int i, int i2, AnonymousClass1As r9, AnonymousClass1CF r10) {
        int A052;
        int i3;
        if (this.A00 != 0) {
            i = i2;
        }
        if (!(getChildCount() == 0 || i == 0)) {
            A0F(i, r9);
            int[] iArr = this.A0H;
            if (iArr == null || iArr.length < this.A04) {
                this.A0H = new int[this.A04];
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.A04; i5++) {
                C05931Bz r5 = this.A0J;
                if (r5.A07 == -1) {
                    A052 = r5.A02;
                    i3 = this.A0E[i5].A06(A052);
                } else {
                    A052 = this.A0E[i5].A05(r5.A01);
                    i3 = r5.A01;
                }
                int i6 = A052 - i3;
                if (i6 >= 0) {
                    this.A0H[i4] = i6;
                    i4++;
                }
            }
            Arrays.sort(this.A0H, 0, i4);
            for (int i7 = 0; i7 < i4; i7++) {
                C05931Bz r2 = this.A0J;
                int i8 = r2.A06;
                if (i8 >= 0 && i8 < r9.A00()) {
                    r10.A1J(i8, this.A0H[i7]);
                    r2.A06 += r2.A07;
                } else {
                    return;
                }
            }
        }
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateDefaultLayoutParams() {
        if (this.A00 == 0) {
            return new AnonymousClass1C4(-2, -1);
        }
        return new AnonymousClass1C4(-1, -2);
    }

    @Override // X.AnonymousClass1Ag
    public final boolean isAutoMeasureEnabled() {
        if (this.A0F != 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1Ag
    public final void onAdapterChanged(@Nullable AnonymousClass1Aj r3, @Nullable AnonymousClass1Aj r4) {
        this.A07.A01();
        for (int i = 0; i < this.A04; i++) {
            this.A0E[i].A08();
        }
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsChanged(RecyclerView recyclerView) {
        this.A07.A01();
        requestLayout();
    }

    @Override // X.AnonymousClass1Ag
    public final void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        A0D(i, i2, 8);
    }

    @Override // X.AnonymousClass1Ag
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.A08 = savedState;
            if (this.A01 != -1) {
                savedState.A09 = null;
                savedState.A02 = 0;
                savedState.A00 = -1;
                savedState.A03 = -1;
                savedState.A09 = null;
                savedState.A02 = 0;
                savedState.A01 = 0;
                savedState.A08 = null;
                savedState.A04 = null;
            }
            requestLayout();
        }
    }

    @Override // X.AnonymousClass1Ag
    public final Parcelable onSaveInstanceState() {
        int A002;
        View A0A2;
        int position;
        int A062;
        int A052;
        int[] iArr;
        SavedState savedState = this.A08;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.A07 = this.A0C;
        savedState2.A05 = this.A0A;
        savedState2.A06 = this.A0B;
        AnonymousClass1Bv r1 = this.A07;
        if (r1 == null || (iArr = r1.A01) == null) {
            savedState2.A01 = 0;
        } else {
            savedState2.A08 = iArr;
            savedState2.A01 = iArr.length;
            savedState2.A04 = r1.A00;
        }
        if (getChildCount() > 0) {
            if (this.A0A) {
                A002 = A01();
            } else {
                A002 = A00();
            }
            savedState2.A00 = A002;
            if (this.A0D) {
                A0A2 = A09(true);
            } else {
                A0A2 = A0A(true);
            }
            if (A0A2 == null) {
                position = -1;
            } else {
                position = getPosition(A0A2);
            }
            savedState2.A03 = position;
            int i = this.A04;
            savedState2.A02 = i;
            savedState2.A09 = new int[i];
            for (int i2 = 0; i2 < this.A04; i2++) {
                if (this.A0A) {
                    A062 = this.A0E[i2].A05(Integer.MIN_VALUE);
                    if (A062 != Integer.MIN_VALUE) {
                        A052 = this.A05.A02();
                    } else {
                        savedState2.A09[i2] = A062;
                    }
                } else {
                    A062 = this.A0E[i2].A06(Integer.MIN_VALUE);
                    if (A062 != Integer.MIN_VALUE) {
                        A052 = this.A05.A05();
                    } else {
                        savedState2.A09[i2] = A062;
                    }
                }
                A062 -= A052;
                savedState2.A09[i2] = A062;
            }
        } else {
            savedState2.A00 = -1;
            savedState2.A03 = -1;
            savedState2.A02 = 0;
        }
        return savedState2;
    }

    @Override // X.AnonymousClass1Ag
    public final void onScrollStateChanged(int i) {
        if (i == 0) {
            A0N();
        }
    }

    @Override // X.AnonymousClass1Ag
    public final void scrollToPosition(int i) {
        SavedState savedState = this.A08;
        if (!(savedState == null || savedState.A00 == i)) {
            savedState.A09 = null;
            savedState.A02 = 0;
            savedState.A00 = -1;
            savedState.A03 = -1;
        }
        this.A01 = i;
        this.A02 = Integer.MIN_VALUE;
        requestLayout();
    }

    @Override // X.AnonymousClass1Ag
    public final boolean supportsPredictiveItemAnimations() {
        if (this.A08 == null) {
            return true;
        }
        return false;
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        AnonymousClass1BL properties = AnonymousClass1Ag.getProperties(context, attributeSet, i, i2);
        int i3 = properties.A00;
        if (i3 == 0 || i3 == 1) {
            assertNotInLayoutOrScroll(null);
            if (i3 != this.A00) {
                this.A00 = i3;
                AbstractC05861Bm r1 = this.A05;
                this.A05 = this.A06;
                this.A06 = r1;
                requestLayout();
            }
            int i4 = properties.A01;
            assertNotInLayoutOrScroll(null);
            if (i4 != this.A04) {
                this.A07.A01();
                requestLayout();
                this.A04 = i4;
                this.A09 = new BitSet(i4);
                C05891Bs[] r2 = new C05891Bs[i4];
                this.A0E = r2;
                for (int i5 = 0; i5 < i4; i5++) {
                    r2[i5] = new C05891Bs(this, i5);
                }
                requestLayout();
            }
            boolean z = properties.A02;
            assertNotInLayoutOrScroll(null);
            SavedState savedState = this.A08;
            if (!(savedState == null || savedState.A07 == z)) {
                savedState.A07 = z;
            }
            this.A0C = z;
            requestLayout();
            this.A0J = new C05931Bz();
            this.A05 = AbstractC05861Bm.A00(this, this.A00);
            this.A06 = AbstractC05861Bm.A00(this, 1 - this.A00);
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    private final int A00() {
        if (getChildCount() != 0) {
            return getPosition(getChildAt(0));
        }
        return 0;
    }

    private final int A01() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    private final int A03(int i, AnonymousClass1Af r6, AnonymousClass1As r7) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        A0F(i, r7);
        C05931Bz r2 = this.A0J;
        int A042 = A04(r6, r2, r7);
        if (r2.A00 >= A042) {
            i = A042;
            if (i < 0) {
                i = -A042;
            }
        }
        this.A05.A0E(-i);
        this.A0A = this.A0D;
        r2.A00 = 0;
        A0G(r6, r2);
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001b, code lost:
        if (A0L() == false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.view.View A08() {
        /*
        // Method dump skipped, instructions count: 214
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.A08():android.view.View");
    }

    private void A0I(AnonymousClass1Af r5, AnonymousClass1As r6, boolean z) {
        int A052;
        int A062 = this.A0E[0].A06(Integer.MAX_VALUE);
        for (int i = 1; i < this.A04; i++) {
            int A063 = this.A0E[i].A06(Integer.MAX_VALUE);
            if (A063 < A062) {
                A062 = A063;
            }
        }
        if (A062 != Integer.MAX_VALUE && (A052 = A062 - this.A05.A05()) > 0) {
            int A032 = A052 - A03(A052, r5, r6);
            if (z && A032 > 0) {
                this.A05.A0E(-A032);
            }
        }
    }

    public final boolean A0N() {
        int A002;
        if (!(getChildCount() == 0 || this.A0F == 0 || !this.mIsAttachedToWindow)) {
            if (this.A0D) {
                A002 = A01();
                A00();
            } else {
                A002 = A00();
                A01();
            }
            if (A002 == 0 && A08() != null) {
                this.A07.A01();
                this.mRequestedSimpleAnimations = true;
                requestLayout();
                return true;
            }
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

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r1 != r4.A0D) goto L_0x000c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r4.A0D != false) goto L_0x000b;
     */
    @Override // X.AnonymousClass1BR
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.PointF computeScrollVectorForPosition(int r5) {
        /*
            r4 = this;
            int r0 = r4.getChildCount()
            r3 = -1
            if (r0 != 0) goto L_0x001c
            boolean r0 = r4.A0D
            if (r0 == 0) goto L_0x000c
        L_0x000b:
            r3 = 1
        L_0x000c:
            android.graphics.PointF r2 = new android.graphics.PointF
            r2.<init>()
            int r0 = r4.A00
            r1 = 0
            if (r0 != 0) goto L_0x0029
            float r0 = (float) r3
            r2.x = r0
            r2.y = r1
            return r2
        L_0x001c:
            int r0 = r4.A00()
            r1 = 0
            if (r5 >= r0) goto L_0x0024
            r1 = 1
        L_0x0024:
            boolean r0 = r4.A0D
            if (r1 == r0) goto L_0x000b
            goto L_0x000c
        L_0x0029:
            r2.x = r1
            float r0 = (float) r3
            r2.y = r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.computeScrollVectorForPosition(int):android.graphics.PointF");
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
    public final void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.A04; i2++) {
            C05891Bs r2 = this.A0E[i2];
            int i3 = r2.A01;
            if (i3 != Integer.MIN_VALUE) {
                r2.A01 = i3 + i;
            }
            int i4 = r2.A00;
            if (i4 != Integer.MIN_VALUE) {
                r2.A00 = i4 + i;
            }
        }
    }

    @Override // X.AnonymousClass1Ag
    public final void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.A04; i2++) {
            C05891Bs r2 = this.A0E[i2];
            int i3 = r2.A01;
            if (i3 != Integer.MIN_VALUE) {
                r2.A01 = i3 + i;
            }
            int i4 = r2.A00;
            if (i4 != Integer.MIN_VALUE) {
                r2.A00 = i4 + i;
            }
        }
    }

    @Override // X.AnonymousClass1Ag
    public final void onDetachedFromWindow(RecyclerView recyclerView, AnonymousClass1Af r4) {
        super.onDetachedFromWindow(recyclerView, r4);
        removeCallbacks(this.A0L);
        for (int i = 0; i < this.A04; i++) {
            this.A0E[i].A08();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0075, code lost:
        if (r10.A00 == 1) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        if (A0L() != false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008b, code lost:
        if (A0L() != false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0090, code lost:
        if (r10.A00 == 0) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x010c A[LOOP:3: B:84:0x010c->B:93:0x0123, LOOP_START, PHI: r4 
      PHI: (r4v1 int) = (r4v0 int), (r4v2 int) binds: [B:70:0x00e6, B:93:0x0123] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // X.AnonymousClass1Ag
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View onFocusSearchFailed(android.view.View r11, int r12, X.AnonymousClass1Af r13, X.AnonymousClass1As r14) {
        /*
        // Method dump skipped, instructions count: 300
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onFocusSearchFailed(android.view.View, int, X.1Af, X.1As):android.view.View");
    }

    @Override // X.AnonymousClass1Ag
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View A0A2 = A0A(false);
            View A092 = A09(false);
            if (A0A2 != null && A092 != null) {
                int position = getPosition(A0A2);
                int position2 = getPosition(A092);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                    return;
                }
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // X.AnonymousClass1Ag
    public final void onLayoutCompleted(AnonymousClass1As r2) {
        super.onLayoutCompleted(r2);
        this.A01 = -1;
        this.A02 = Integer.MIN_VALUE;
        this.A08 = null;
        this.A0K.A00();
    }

    @Override // X.AnonymousClass1Ag
    public final int scrollHorizontallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        return A03(i, r3, r4);
    }

    @Override // X.AnonymousClass1Ag
    public final int scrollVerticallyBy(int i, AnonymousClass1Af r3, AnonymousClass1As r4) {
        return A03(i, r3, r4);
    }

    @Override // X.AnonymousClass1Ag
    public final void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.A00 == 1) {
            chooseSize2 = AnonymousClass1Ag.chooseSize(i2, rect.height() + paddingTop, this.mRecyclerView.getMinimumHeight());
            chooseSize = AnonymousClass1Ag.chooseSize(i, (this.A03 * this.A04) + paddingLeft, this.mRecyclerView.getMinimumWidth());
        } else {
            chooseSize = AnonymousClass1Ag.chooseSize(i, rect.width() + paddingLeft, this.mRecyclerView.getMinimumWidth());
            chooseSize2 = AnonymousClass1Ag.chooseSize(i2, (this.A03 * this.A04) + paddingTop, this.mRecyclerView.getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    @Override // X.AnonymousClass1Ag
    public final void smoothScrollToPosition(RecyclerView recyclerView, AnonymousClass1As r4, int i) {
        AnonymousClass1Ao r0 = new AnonymousClass1Ao(recyclerView.getContext());
        ((AnonymousClass1An) r0).A00 = i;
        startSmoothScroll(r0);
    }

    @Override // X.AnonymousClass1Ag
    public final boolean checkLayoutParams(C05831Bi r2) {
        return r2 instanceof AnonymousClass1C4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01c0, code lost:
        if (r10.A0D != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01e4, code lost:
        if (r1 != r10.A0D) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        if (r1 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0388, code lost:
        if (A0N() != false) goto L_0x038a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x023c  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x02e1  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0320  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x035f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0J(X.AnonymousClass1Af r11, X.AnonymousClass1As r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 1002
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.A0J(X.1Af, X.1As, boolean):void");
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new AnonymousClass1C4(context, attributeSet);
    }

    @Override // X.AnonymousClass1Ag
    public final C05831Bi generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new AnonymousClass1C4((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new AnonymousClass1C4(layoutParams);
    }
}
