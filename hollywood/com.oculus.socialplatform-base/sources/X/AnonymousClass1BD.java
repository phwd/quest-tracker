package X;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1BD  reason: invalid class name */
public class AnonymousClass1BD implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.RecyclerView$1";
    public final /* synthetic */ RecyclerView A00;

    public AnonymousClass1BD(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }

    public final void run() {
        RecyclerView recyclerView = this.A00;
        if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
            if (!recyclerView.mIsAttached) {
                recyclerView.requestLayout();
            } else if (recyclerView.mLayoutSuppressed) {
                recyclerView.mLayoutWasDefered = true;
            } else {
                recyclerView.consumePendingUpdateOperations();
            }
        }
    }
}
