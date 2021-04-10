package X;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1Au  reason: invalid class name */
public class AnonymousClass1Au implements AnonymousClass1B5 {
    public final /* synthetic */ RecyclerView A00;

    public AnonymousClass1Au(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }

    @Override // X.AnonymousClass1B5
    public final void A1P(View view, int i) {
        RecyclerView recyclerView = this.A00;
        recyclerView.addView(view, i);
        recyclerView.dispatchChildAttached(view);
    }

    @Override // X.AnonymousClass1B5
    public final View A3U(int i) {
        return this.A00.getChildAt(i);
    }

    @Override // X.AnonymousClass1B5
    public final int A3V() {
        return this.A00.getChildCount();
    }

    @Override // X.AnonymousClass1B5
    public final int A5a(View view) {
        return this.A00.indexOfChild(view);
    }

    @Override // X.AnonymousClass1B5
    public final void A9E(int i) {
        RecyclerView recyclerView = this.A00;
        View childAt = recyclerView.getChildAt(i);
        if (childAt != null) {
            recyclerView.dispatchChildDetached(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i);
    }

    @Override // X.AnonymousClass1B5
    public final void A1X(View view, int i, ViewGroup.LayoutParams layoutParams) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else {
                StringBuilder sb = new StringBuilder("Called attach on a child which is not detached: ");
                sb.append(childViewHolderInt);
                sb.append(this.A00.exceptionLabel());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        this.A00.attachViewToParent(view, i, layoutParams);
    }

    @Override // X.AnonymousClass1B5
    public final void A2b(int i) {
        AnonymousClass1Ah childViewHolderInt;
        View A3U = A3U(i);
        if (!(A3U == null || (childViewHolderInt = RecyclerView.getChildViewHolderInt(A3U)) == null)) {
            if (!childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(256);
            } else {
                StringBuilder sb = new StringBuilder("called detach on an already detached child ");
                sb.append(childViewHolderInt);
                sb.append(this.A00.exceptionLabel());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        this.A00.detachViewFromParent((RecyclerView) i);
    }

    @Override // X.AnonymousClass1B5
    public final AnonymousClass1Ah A3Z(View view) {
        return RecyclerView.getChildViewHolderInt(view);
    }

    @Override // X.AnonymousClass1B5
    public final void A73(View view) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onEnteredHiddenState(this.A00);
        }
    }

    @Override // X.AnonymousClass1B5
    public final void A7E(View view) {
        AnonymousClass1Ah childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onLeftHiddenState(this.A00);
        }
    }

    @Override // X.AnonymousClass1B5
    public final void A98() {
        int A3V = A3V();
        for (int i = 0; i < A3V; i++) {
            View A3U = A3U(i);
            this.A00.dispatchChildDetached(A3U);
            A3U.clearAnimation();
        }
        this.A00.removeAllViews();
    }
}
