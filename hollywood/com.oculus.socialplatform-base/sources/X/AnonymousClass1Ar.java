package X;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1Ar  reason: invalid class name */
public class AnonymousClass1Ar implements AnonymousClass1BA {
    public final /* synthetic */ RecyclerView A00;

    public AnonymousClass1Ar(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }

    private final void A00(AnonymousClass1B0 r6) {
        int i = r6.A00;
        if (i == 1) {
            RecyclerView recyclerView = this.A00;
            recyclerView.mLayout.onItemsAdded(recyclerView, r6.A02, r6.A01);
        } else if (i == 2) {
            RecyclerView recyclerView2 = this.A00;
            recyclerView2.mLayout.onItemsRemoved(recyclerView2, r6.A02, r6.A01);
        } else if (i == 4) {
            RecyclerView recyclerView3 = this.A00;
            recyclerView3.mLayout.onItemsUpdated(recyclerView3, r6.A02, r6.A01, r6.A03);
        } else if (i == 8) {
            RecyclerView recyclerView4 = this.A00;
            recyclerView4.mLayout.onItemsMoved(recyclerView4, r6.A02, r6.A01, 1);
        }
    }

    @Override // X.AnonymousClass1BA
    public final AnonymousClass1Ah A3D(int i) {
        RecyclerView recyclerView = this.A00;
        AnonymousClass1Ah findViewHolderForPosition = recyclerView.findViewHolderForPosition(i, true);
        if (findViewHolderForPosition != null) {
            AnonymousClass1Am r0 = recyclerView.mChildHelper;
            if (!r0.A02.contains(findViewHolderForPosition.itemView)) {
                return findViewHolderForPosition;
            }
        }
        return null;
    }

    @Override // X.AnonymousClass1BA
    public final void A6M(int i, int i2, Object obj) {
        RecyclerView recyclerView = this.A00;
        recyclerView.viewRangeUpdate(i, i2, obj);
        recyclerView.mItemsChanged = true;
    }

    @Override // X.AnonymousClass1BA
    public final void A6c(int i, int i2) {
        RecyclerView recyclerView = this.A00;
        recyclerView.offsetPositionRecordsForInsert(i, i2);
        recyclerView.mItemsAddedOrRemoved = true;
    }

    @Override // X.AnonymousClass1BA
    public final void A6d(int i, int i2) {
        RecyclerView recyclerView = this.A00;
        recyclerView.offsetPositionRecordsForMove(i, i2);
        recyclerView.mItemsAddedOrRemoved = true;
    }

    @Override // X.AnonymousClass1BA
    public final void A6e(int i, int i2) {
        RecyclerView recyclerView = this.A00;
        recyclerView.offsetPositionRecordsForRemove(i, i2, true);
        recyclerView.mItemsAddedOrRemoved = true;
        recyclerView.mState.A00 += i2;
    }

    @Override // X.AnonymousClass1BA
    public final void A6f(int i, int i2) {
        RecyclerView recyclerView = this.A00;
        recyclerView.offsetPositionRecordsForRemove(i, i2, false);
        recyclerView.mItemsAddedOrRemoved = true;
    }

    @Override // X.AnonymousClass1BA
    public final void A71(AnonymousClass1B0 r1) {
        A00(r1);
    }

    @Override // X.AnonymousClass1BA
    public final void A72(AnonymousClass1B0 r1) {
        A00(r1);
    }
}
