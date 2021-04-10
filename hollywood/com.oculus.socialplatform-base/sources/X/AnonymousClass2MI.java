package X;

import X.AnonymousClass1Ah;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* renamed from: X.2MI  reason: invalid class name */
public abstract class AnonymousClass2MI<T, VH extends AnonymousClass1Ah> extends AnonymousClass1Aj<VH> {
    public final AnonymousClass2MJ<T> mDiffer;
    public final AnonymousClass2NO<T> mListener = new AnonymousClass2NK(this);

    public void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2) {
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mDiffer.A03;
    }

    public T getItem(int i) {
        return this.mDiffer.A03.get(i);
    }

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        return this.mDiffer.A03.size();
    }

    public AnonymousClass2MI(@NonNull AnonymousClass2NM<T> r6) {
        AnonymousClass1BN r4 = new AnonymousClass1BN(this);
        AnonymousClass2N3 r3 = new AnonymousClass2N3(r6);
        Executor executor = r3.A00;
        if (executor == null) {
            synchronized (AnonymousClass2N3.A03) {
                if (AnonymousClass2N3.A02 == null) {
                    AnonymousClass2N3.A02 = Executors.newFixedThreadPool(2);
                }
            }
            executor = AnonymousClass2N3.A02;
            r3.A00 = executor;
        }
        AnonymousClass2MJ<T> r0 = new AnonymousClass2MJ<>(r4, new AnonymousClass2NB(executor, r3.A01));
        this.mDiffer = r0;
        r0.A06.add(this.mListener);
    }

    public void submitList(@Nullable List<T> list) {
        this.mDiffer.A00(list, null);
    }

    public void submitList(@Nullable List<T> list, @Nullable Runnable runnable) {
        this.mDiffer.A00(list, runnable);
    }
}
