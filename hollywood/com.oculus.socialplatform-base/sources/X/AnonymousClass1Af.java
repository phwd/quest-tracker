package X;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: X.1Af  reason: invalid class name */
public final class AnonymousClass1Af {
    public int A00;
    public int A01;
    public AnonymousClass1BH A02;
    public AbstractC05821Bc A03;
    public ArrayList<AnonymousClass1Ah> A04 = null;
    public final ArrayList<AnonymousClass1Ah> A05;
    public final ArrayList<AnonymousClass1Ah> A06 = new ArrayList<>();
    public final List<AnonymousClass1Ah> A07;
    public final /* synthetic */ RecyclerView A08;

    public AnonymousClass1Af(RecyclerView recyclerView) {
        this.A08 = recyclerView;
        ArrayList<AnonymousClass1Ah> arrayList = new ArrayList<>();
        this.A05 = arrayList;
        this.A07 = Collections.unmodifiableList(arrayList);
        this.A01 = 2;
        this.A00 = 2;
    }

    public static final void A01(AnonymousClass1Af r2) {
        ArrayList<AnonymousClass1Ah> arrayList = r2.A06;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            A02(r2, size);
        }
        arrayList.clear();
        AnonymousClass1Ak r22 = r2.A08.mPrefetchRegistry;
        int[] iArr = r22.A03;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        r22.A00 = 0;
    }

    public static final void A02(AnonymousClass1Af r3, int i) {
        ArrayList<AnonymousClass1Ah> arrayList = r3.A06;
        r3.A0A(arrayList.get(i), true);
        arrayList.remove(i);
    }

    public final int A03(int i) {
        if (i >= 0) {
            RecyclerView recyclerView = this.A08;
            AnonymousClass1As r1 = recyclerView.mState;
            if (i < r1.A00()) {
                if (!r1.A0A) {
                    return i;
                }
                return AnonymousClass1Aq.A01(recyclerView.mAdapterHelper, i, 0);
            }
        }
        StringBuilder sb = new StringBuilder("invalid position ");
        sb.append(i);
        sb.append(". State item count is ");
        RecyclerView recyclerView2 = this.A08;
        sb.append(recyclerView2.mState.A00());
        sb.append(recyclerView2.exceptionLabel());
        throw new IndexOutOfBoundsException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01c0, code lost:
        if (r5.mState.A0A == false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x022c, code lost:
        r8.mPosition = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x02a8, code lost:
        if (r8.mItemId != r9.getItemId(r8.mPosition)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00dd, code lost:
        if ((r13 + r0) >= r19) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x012b, code lost:
        if (r1 != null) goto L_0x012d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x013d  */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass1Ah A04(int r18, long r19) {
        /*
        // Method dump skipped, instructions count: 1195
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Af.A04(int, long):X.1Ah");
    }

    public final void A05() {
        int i;
        AnonymousClass1Ag r0 = this.A08.mLayout;
        if (r0 != null) {
            i = r0.mPrefetchMaxCountObserved;
        } else {
            i = 0;
        }
        this.A00 = this.A01 + i;
        ArrayList<AnonymousClass1Ah> arrayList = this.A06;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0 && arrayList.size() > this.A00) {
                A02(this, size);
            } else {
                return;
            }
        }
    }

    public final void A08(AnonymousClass1Ah r2) {
        ArrayList<AnonymousClass1Ah> arrayList;
        if (r2.mInChangeScrap) {
            arrayList = this.A04;
        } else {
            arrayList = this.A05;
        }
        arrayList.remove(r2);
        r2.mScrapContainer = null;
        r2.mInChangeScrap = false;
        r2.clearReturnedFromScrapFlag();
    }

    private void A00(ViewGroup viewGroup, boolean z) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                A00((ViewGroup) childAt, true);
            }
        }
        if (!z) {
            return;
        }
        if (viewGroup.getVisibility() == 4) {
            viewGroup.setVisibility(0);
            viewGroup.setVisibility(4);
            return;
        }
        int visibility = viewGroup.getVisibility();
        viewGroup.setVisibility(4);
        viewGroup.setVisibility(visibility);
    }

    public final void A06(View view) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.hasAnyOfTheFlags(12) || !childViewHolderInt.isUpdated() || this.A08.canReuseUpdatedViewHolder(childViewHolderInt)) {
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved()) {
                RecyclerView recyclerView = this.A08;
                if (!recyclerView.mAdapter.mHasStableIds) {
                    throw new IllegalArgumentException(AnonymousClass006.A07("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.", recyclerView.exceptionLabel()));
                }
            }
            childViewHolderInt.setScrapContainer(this, false);
            this.A05.add(childViewHolderInt);
            return;
        }
        ArrayList<AnonymousClass1Ah> arrayList = this.A04;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.A04 = arrayList;
        }
        childViewHolderInt.setScrapContainer(this, true);
        arrayList.add(childViewHolderInt);
    }

    public final void A07(@NonNull View view) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isTmpDetached()) {
            this.A08.removeDetachedView(view, false);
        }
        if (childViewHolderInt.isScrap()) {
            childViewHolderInt.unScrap();
        } else if (childViewHolderInt.wasReturnedFromScrap()) {
            childViewHolderInt.clearReturnedFromScrapFlag();
        }
        A09(childViewHolderInt);
        RecyclerView recyclerView = this.A08;
        if (recyclerView.mItemAnimator != null && !childViewHolderInt.isRecyclable()) {
            recyclerView.mItemAnimator.A07(childViewHolderInt);
        }
    }

    public final void A09(AnonymousClass1Ah r13) {
        boolean z;
        boolean z2 = false;
        if (r13.isScrap() || r13.itemView.getParent() != null) {
            StringBuilder sb = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
            sb.append(r13.isScrap());
            sb.append(" isAttached:");
            if (r13.itemView.getParent() != null) {
                z2 = true;
            }
            sb.append(z2);
            sb.append(this.A08.exceptionLabel());
            throw new IllegalArgumentException(sb.toString());
        } else if (r13.isTmpDetached()) {
            StringBuilder sb2 = new StringBuilder("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
            sb2.append(r13);
            sb2.append(this.A08.exceptionLabel());
            throw new IllegalArgumentException(sb2.toString());
        } else if (!r13.shouldIgnore()) {
            boolean doesTransientStatePreventRecycling = r13.doesTransientStatePreventRecycling();
            RecyclerView recyclerView = this.A08;
            if (r13.isRecyclable()) {
                int i = this.A00;
                if (i <= 0 || r13.hasAnyOfTheFlags(526)) {
                    z = false;
                    A0A(r13, true);
                    z2 = true;
                } else {
                    ArrayList<AnonymousClass1Ah> arrayList = this.A06;
                    int size = arrayList.size();
                    if (size >= i) {
                        if (size > 0) {
                            A02(this, 0);
                            size--;
                        }
                        arrayList.add(size, r13);
                        z = true;
                    }
                    if (size > 0) {
                        AnonymousClass1Ak r5 = recyclerView.mPrefetchRegistry;
                        int i2 = r13.mPosition;
                        int[] iArr = r5.A03;
                        if (iArr != null) {
                            int i3 = r5.A00 << 1;
                            int i4 = 0;
                            while (true) {
                                if (i4 < i3) {
                                    if (iArr[i4] == i2) {
                                        break;
                                    }
                                    i4 += 2;
                                } else {
                                    break;
                                }
                            }
                        }
                        loop1:
                        while (true) {
                            size--;
                            if (size < 0) {
                                break;
                            }
                            int i5 = arrayList.get(size).mPosition;
                            int[] iArr2 = r5.A03;
                            if (iArr2 == null) {
                                break;
                            }
                            int i6 = r5.A00 << 1;
                            int i7 = 0;
                            while (true) {
                                if (i7 >= i6) {
                                    break loop1;
                                } else if (iArr2[i7] != i5) {
                                    i7 += 2;
                                }
                            }
                        }
                        size++;
                    }
                    arrayList.add(size, r13);
                    z = true;
                }
            } else {
                z = false;
            }
            recyclerView.mViewInfoStore.A02(r13);
            if (!z && !z2 && doesTransientStatePreventRecycling) {
                r13.mBindingAdapter = null;
                r13.mOwnerRecyclerView = null;
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.", this.A08.exceptionLabel()));
        }
    }

    public final void A0A(@NonNull AnonymousClass1Ah r5, boolean z) {
        AnonymousClass06v r0;
        RecyclerView.clearNestedRecyclerViewIfNotNested(r5);
        View view = r5.itemView;
        RecyclerView recyclerView = this.A08;
        AnonymousClass1B3 r02 = recyclerView.mAccessibilityDelegate;
        if (r02 != null) {
            AnonymousClass1B4 r03 = r02.A01;
            if (r03 != null) {
                r0 = r03.A00.remove(view);
            } else {
                r0 = null;
            }
            AnonymousClass07f.A07(view, r0);
        }
        if (z) {
            if (0 < recyclerView.mRecyclerListeners.size()) {
                recyclerView.mRecyclerListeners.get(0);
                throw new NullPointerException("onViewRecycled");
            }
            AnonymousClass1Aj r04 = recyclerView.mAdapter;
            if (r04 != null) {
                r04.onViewRecycled(r5);
            }
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.A02(r5);
            }
        }
        r5.mBindingAdapter = null;
        r5.mOwnerRecyclerView = null;
        AnonymousClass1BH r3 = this.A02;
        if (r3 == null) {
            r3 = new AnonymousClass1BH();
            this.A02 = r3;
        }
        int i = r5.mItemViewType;
        ArrayList<AnonymousClass1Ah> arrayList = AnonymousClass1BH.A00(r3, i).A02;
        r3.A01.get(i);
        if (5 > arrayList.size()) {
            r5.resetInternal();
            arrayList.add(r5);
        }
    }
}
