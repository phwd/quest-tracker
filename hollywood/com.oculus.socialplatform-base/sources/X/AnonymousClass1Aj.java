package X;

import X.AnonymousClass1Ah;
import android.os.Trace;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* renamed from: X.1Aj  reason: invalid class name */
public abstract class AnonymousClass1Aj<VH extends AnonymousClass1Ah> {
    public boolean mHasStableIds = false;
    public final AnonymousClass1B1 mObservable = new AnonymousClass1B1();
    public AnonymousClass1CI mStateRestorationPolicy = AnonymousClass1CI.ALLOW;

    public int findRelativeAdapterPositionIn(@NonNull AnonymousClass1Aj<? extends AnonymousClass1Ah> r2, @NonNull AnonymousClass1Ah r3, int i) {
        if (r2 == this) {
            return i;
        }
        return -1;
    }

    public abstract int getItemCount();

    public long getItemId(int i) {
        return -1;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
    }

    public abstract void onBindViewHolder(@NonNull VH vh, int i);

    @NonNull
    public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
    }

    public boolean onFailedToRecycleView(@NonNull VH vh) {
        return false;
    }

    public void onViewAttachedToWindow(@NonNull VH vh) {
    }

    public void onViewDetachedFromWindow(@NonNull VH vh) {
    }

    public void onViewRecycled(@NonNull VH vh) {
    }

    public final void bindViewHolder(@NonNull VH vh, int i) {
        boolean z = false;
        if (vh.mBindingAdapter == null) {
            z = true;
            vh.mPosition = i;
            if (this.mHasStableIds) {
                vh.mItemId = getItemId(i);
            }
            vh.setFlags(1, 519);
            Trace.beginSection(RecyclerView.TRACE_BIND_VIEW_TAG);
        }
        vh.mBindingAdapter = this;
        vh.getUnmodifiedPayloads();
        onBindViewHolder(vh, i);
        if (z) {
            vh.clearPayload();
            ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof C05831Bi) {
                ((C05831Bi) layoutParams).A02 = true;
            }
            Trace.endSection();
        }
    }

    public boolean canRestoreState() {
        switch (this.mStateRestorationPolicy.ordinal()) {
            case 1:
                if (getItemCount() > 0) {
                    return true;
                }
                return false;
            case 2:
                return false;
            default:
                return true;
        }
    }

    @NonNull
    public final VH createViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            Trace.beginSection(RecyclerView.TRACE_CREATE_VIEW_TAG);
            VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
            if (onCreateViewHolder.itemView.getParent() == null) {
                onCreateViewHolder.mItemViewType = i;
                return onCreateViewHolder;
            }
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        } finally {
            Trace.endSection();
        }
    }

    @NonNull
    public final AnonymousClass1CI getStateRestorationPolicy() {
        return this.mStateRestorationPolicy;
    }

    public final boolean hasObservers() {
        return this.mObservable.A06();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void notifyDataSetChanged() {
        this.mObservable.A00();
    }

    public final void notifyItemInserted(int i) {
        this.mObservable.A03(i, 1);
    }

    public final void notifyItemMoved(int i, int i2) {
        this.mObservable.A02(i, i2);
    }

    public final void notifyItemRangeInserted(int i, int i2) {
        this.mObservable.A03(i, i2);
    }

    public final void notifyItemRangeRemoved(int i, int i2) {
        this.mObservable.A04(i, i2);
    }

    public final void notifyItemRemoved(int i) {
        this.mObservable.A04(i, 1);
    }

    public void registerAdapterDataObserver(@NonNull AnonymousClass1BE r2) {
        this.mObservable.registerObserver(r2);
    }

    public void setHasStableIds(boolean z) {
        if (!this.mObservable.A06()) {
            this.mHasStableIds = z;
            return;
        }
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }

    public void setStateRestorationPolicy(@NonNull AnonymousClass1CI r2) {
        this.mStateRestorationPolicy = r2;
        this.mObservable.A01();
    }

    public void unregisterAdapterDataObserver(@NonNull AnonymousClass1BE r2) {
        this.mObservable.unregisterObserver(r2);
    }

    public final void notifyItemChanged(int i) {
        this.mObservable.A05(i, 1, null);
    }

    public final void notifyItemChanged(int i, @Nullable Object obj) {
        this.mObservable.A05(i, 1, obj);
    }

    public final void notifyItemRangeChanged(int i, int i2) {
        this.mObservable.A05(i, i2, null);
    }

    public final void notifyItemRangeChanged(int i, int i2, @Nullable Object obj) {
        this.mObservable.A05(i, i2, obj);
    }

    public void onBindViewHolder(@NonNull VH vh, int i, @NonNull List<Object> list) {
        onBindViewHolder(vh, i);
    }
}
