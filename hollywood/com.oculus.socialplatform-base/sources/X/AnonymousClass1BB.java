package X;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1BB  reason: invalid class name */
public class AnonymousClass1BB implements AnonymousClass1BQ {
    public final /* synthetic */ RecyclerView A00;

    @Override // X.AnonymousClass1BQ
    public final void A6j(AnonymousClass1Ah r4) {
        r4.setIsRecyclable(true);
        if (r4.mShadowedHolder != null && r4.mShadowingHolder == null) {
            r4.mShadowedHolder = null;
        }
        r4.mShadowingHolder = null;
        if (!r4.shouldBeKeptAsChild()) {
            RecyclerView recyclerView = this.A00;
            if (!recyclerView.removeAnimatingView(r4.itemView) && r4.isTmpDetached()) {
                recyclerView.removeDetachedView(r4.itemView, false);
            }
        }
    }

    public AnonymousClass1BB(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }
}
