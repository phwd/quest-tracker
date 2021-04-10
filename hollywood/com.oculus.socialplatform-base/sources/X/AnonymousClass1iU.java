package X;

import android.util.SparseArray;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.1iU  reason: invalid class name */
public abstract class AnonymousClass1iU<T> {
    public final AnonymousClass1iW<T> A00 = new AnonymousClass1iW<>();
    public final Set<T> A01 = new HashSet();

    public void A02(T t) {
        boolean add;
        synchronized (this) {
            add = this.A01.add(t);
        }
        if (add) {
            AnonymousClass1iW<T> r4 = this.A00;
            int A012 = C01110Pz.A01(t);
            synchronized (r4) {
                SparseArray<AnonymousClass1iX<T>> sparseArray = r4.A02;
                AnonymousClass1iX<I> r1 = (AnonymousClass1iX<I>) sparseArray.get(A012);
                if (r1 == null) {
                    r1 = new AnonymousClass1iX<>(A012, new LinkedList());
                    sparseArray.put(A012, r1);
                }
                r1.A03.addLast(t);
                if (r4.A00 != r1) {
                    AnonymousClass1iW.A00(r4, r1);
                    AnonymousClass1iX r0 = (AnonymousClass1iX<T>) r4.A00;
                    if (r0 == null) {
                        r4.A00 = (AnonymousClass1iX<T>) r1;
                        r4.A01 = (AnonymousClass1iX<T>) r1;
                    } else {
                        r1.A01 = r0;
                        r0.A02 = r1;
                        r4.A00 = (AnonymousClass1iX<T>) r1;
                    }
                }
            }
        }
    }

    @Nullable
    public T A01(int i) {
        I i2;
        AnonymousClass1iW<T> r3 = this.A00;
        synchronized (r3) {
            AnonymousClass1iX r1 = (AnonymousClass1iX<T>) r3.A02.get(i);
            if (r1 == null) {
                i2 = (T) null;
            } else {
                i2 = r1.A03.pollFirst();
                if (r3.A00 != r1) {
                    AnonymousClass1iW.A00(r3, r1);
                    AnonymousClass1iX r0 = (AnonymousClass1iX<T>) r3.A00;
                    if (r0 == null) {
                        r3.A00 = r1;
                        r3.A01 = r1;
                    } else {
                        r1.A01 = r0;
                        r0.A02 = r1;
                        r3.A00 = r1;
                    }
                }
            }
        }
        if (i2 == null) {
            return (T) i2;
        }
        synchronized (this) {
            this.A01.remove(i2);
        }
        return (T) i2;
    }
}
