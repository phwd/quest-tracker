package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* renamed from: X.2MJ  reason: invalid class name */
public final class AnonymousClass2MJ<T> {
    public static final Executor A07 = new AnonymousClass2NF();
    public int A00;
    public Executor A01;
    @Nullable
    public List<T> A02;
    @NonNull
    public List<T> A03 = Collections.emptyList();
    public final AnonymousClass2NB<T> A04;
    public final AnonymousClass2OR A05;
    public final List<AnonymousClass2NO<T>> A06 = new CopyOnWriteArrayList();

    public final void A00(@Nullable List<T> list, @Nullable Runnable runnable) {
        int i = this.A00 + 1;
        this.A00 = i;
        List<T> list2 = this.A02;
        if (list != list2) {
            if (list == null) {
                int size = list2.size();
                this.A02 = null;
                this.A03 = Collections.emptyList();
                this.A05.A7k(0, size);
            } else if (list2 == null) {
                this.A02 = list;
                this.A03 = Collections.unmodifiableList(list);
                this.A05.A7A(0, list.size());
            } else {
                this.A04.A01.execute(new AnonymousClass2N9(this, list2, list, i, runnable));
                return;
            }
            Iterator<AnonymousClass2NO<T>> it = this.A06.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public AnonymousClass2MJ(@NonNull AnonymousClass2OR r2, @NonNull AnonymousClass2NB<T> r3) {
        this.A05 = r2;
        this.A04 = r3;
        this.A01 = A07;
    }
}
