package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1Av  reason: invalid class name */
public class AnonymousClass1Av implements AnonymousClass1BF {
    public final /* synthetic */ RecyclerView A00;

    @Override // X.AnonymousClass1BF
    public final void A8c(AnonymousClass1Ah r10, @NonNull AnonymousClass1BS r11, @NonNull AnonymousClass1BS r12) {
        boolean A0B;
        r10.setIsRecyclable(false);
        RecyclerView recyclerView = this.A00;
        if (recyclerView.mDataSetHasChangedAfterLayout) {
            A0B = recyclerView.mItemAnimator.A09(r10, r10, r11, r12);
        } else {
            AnonymousClass1Az r3 = (AnonymousClass1Az) recyclerView.mItemAnimator;
            int i = r11.A00;
            int i2 = r12.A00;
            if (i == i2 && r11.A01 == r12.A01) {
                AnonymousClass1BQ r0 = r3.A04;
                if (r0 != null) {
                    r0.A6j(r10);
                    return;
                }
                return;
            }
            A0B = r3.A0B(r10, i, r11.A01, i2, r12.A01);
        }
        if (A0B) {
            recyclerView.postAnimationRunner();
        }
    }

    public AnonymousClass1Av(RecyclerView recyclerView) {
        this.A00 = recyclerView;
    }

    @Override // X.AnonymousClass1BF
    public final void A8a(AnonymousClass1Ah r2, AnonymousClass1BS r3, AnonymousClass1BS r4) {
        this.A00.animateAppearance(r2, r3, r4);
    }

    @Override // X.AnonymousClass1BF
    public final void A8b(AnonymousClass1Ah r3, @NonNull AnonymousClass1BS r4, @Nullable AnonymousClass1BS r5) {
        RecyclerView recyclerView = this.A00;
        recyclerView.mRecycler.A08(r3);
        recyclerView.animateDisappearance(r3, r4, r5);
    }

    @Override // X.AnonymousClass1BF
    public final void AAr(AnonymousClass1Ah r4) {
        RecyclerView recyclerView = this.A00;
        recyclerView.mLayout.removeAndRecycleView(r4.itemView, recyclerView.mRecycler);
    }
}
