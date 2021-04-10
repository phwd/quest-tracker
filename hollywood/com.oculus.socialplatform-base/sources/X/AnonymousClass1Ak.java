package X;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

@SuppressLint({"VisibleForTests"})
/* renamed from: X.1Ak  reason: invalid class name */
public class AnonymousClass1Ak implements AnonymousClass1CF {
    public int A00;
    public int A01;
    public int A02;
    public int[] A03;

    public final void A00(RecyclerView recyclerView, boolean z) {
        this.A00 = 0;
        int[] iArr = this.A03;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        AnonymousClass1Ag r3 = recyclerView.mLayout;
        AnonymousClass1Aj r1 = recyclerView.mAdapter;
        if (r1 != null && r3 != null && r3.mItemPrefetchEnabled) {
            if (z) {
                if (recyclerView.mAdapterHelper.A04.size() <= 0) {
                    r3.collectInitialPrefetchPositions(r1.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                r3.collectAdjacentPrefetchPositions(this.A01, this.A02, recyclerView.mState, this);
            }
            int i = this.A00;
            if (i > r3.mPrefetchMaxCountObserved) {
                r3.mPrefetchMaxCountObserved = i;
                r3.mPrefetchMaxObservedInInitialPrefetch = z;
                recyclerView.mRecycler.A05();
            }
        }
    }

    @Override // X.AnonymousClass1CF
    public final void A1J(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        } else if (i2 >= 0) {
            int i3 = this.A00 << 1;
            int[] iArr = this.A03;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.A03 = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[(i3 << 1)];
                this.A03 = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.A03;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.A00++;
        } else {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
    }
}
