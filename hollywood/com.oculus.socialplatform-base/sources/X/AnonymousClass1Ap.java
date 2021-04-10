package X;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* renamed from: X.1Ap  reason: invalid class name */
public class AnonymousClass1Ap extends AnonymousClass1BE {
    public final /* synthetic */ RecyclerView A00;

    public AnonymousClass1Ap(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }

    private final void A00() {
        RecyclerView recyclerView = this.A00;
        if (!recyclerView.mHasFixedSize || !recyclerView.mIsAttached) {
            recyclerView.mAdapterUpdateDuringMeasure = true;
            recyclerView.requestLayout();
            return;
        }
        recyclerView.postOnAnimation(recyclerView.mUpdateChildViewsRunnable);
    }

    @Override // X.AnonymousClass1BE
    public final void onChanged() {
        RecyclerView recyclerView = this.A00;
        recyclerView.assertNotInLayoutOrScroll(null);
        recyclerView.mState.A06 = true;
        recyclerView.processDataSetCompletelyChanged(true);
        if (recyclerView.mAdapterHelper.A04.size() <= 0) {
            recyclerView.requestLayout();
        }
    }

    @Override // X.AnonymousClass1BE
    public final void onItemRangeChanged(int i, int i2, Object obj) {
        RecyclerView recyclerView = this.A00;
        recyclerView.assertNotInLayoutOrScroll(null);
        AnonymousClass1Aq r4 = recyclerView.mAdapterHelper;
        if (i2 >= 1) {
            ArrayList<AnonymousClass1B0> arrayList = r4.A04;
            arrayList.add(r4.A6a(4, i, i2, obj));
            r4.A00 |= 4;
            if (arrayList.size() == 1) {
                A00();
            }
        }
    }

    @Override // X.AnonymousClass1BE
    public final void onItemRangeInserted(int i, int i2) {
        RecyclerView recyclerView = this.A00;
        recyclerView.assertNotInLayoutOrScroll(null);
        AnonymousClass1Aq r3 = recyclerView.mAdapterHelper;
        if (i2 >= 1) {
            ArrayList<AnonymousClass1B0> arrayList = r3.A04;
            arrayList.add(r3.A6a(1, i, i2, null));
            r3.A00 |= 1;
            if (arrayList.size() == 1) {
                A00();
            }
        }
    }

    @Override // X.AnonymousClass1BE
    public final void onItemRangeMoved(int i, int i2, int i3) {
        RecyclerView recyclerView = this.A00;
        recyclerView.assertNotInLayoutOrScroll(null);
        AnonymousClass1Aq r4 = recyclerView.mAdapterHelper;
        if (i == i2) {
            return;
        }
        if (i3 == 1) {
            ArrayList<AnonymousClass1B0> arrayList = r4.A04;
            arrayList.add(r4.A6a(8, i, i2, null));
            r4.A00 |= 8;
            if (arrayList.size() == 1) {
                A00();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    @Override // X.AnonymousClass1BE
    public final void onItemRangeRemoved(int i, int i2) {
        RecyclerView recyclerView = this.A00;
        recyclerView.assertNotInLayoutOrScroll(null);
        AnonymousClass1Aq r4 = recyclerView.mAdapterHelper;
        if (i2 >= 1) {
            ArrayList<AnonymousClass1B0> arrayList = r4.A04;
            arrayList.add(r4.A6a(2, i, i2, null));
            r4.A00 |= 2;
            if (arrayList.size() == 1) {
                A00();
            }
        }
    }

    @Override // X.AnonymousClass1BE
    public final void onStateRestorationPolicyChanged() {
        AnonymousClass1Aj r0;
        RecyclerView recyclerView = this.A00;
        if (recyclerView.mPendingSavedState != null && (r0 = recyclerView.mAdapter) != null && r0.canRestoreState()) {
            recyclerView.requestLayout();
        }
    }
}
