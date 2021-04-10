package X;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yB  reason: invalid class name and case insensitive filesystem */
public final class C12701yB<T> extends AbstractC13251zE<T> implements AbstractC12721yD<T> {
    public static final C12711yC[] A04 = new C12711yC[0];
    public static final C12711yC[] A05 = new C12711yC[0];
    public T A00;
    public Throwable A01;
    public final AtomicBoolean A02 = new AtomicBoolean();
    public final AtomicReference<C12711yC<T>[]> A03 = new AtomicReference<>(A04);

    public final void A07(@NonNull C12711yC<T> r9) {
        AtomicReference<C12711yC<T>[]> atomicReference;
        C12711yC<T>[] r6;
        C12711yC<T>[] r1;
        do {
            atomicReference = this.A03;
            r6 = atomicReference.get();
            int length = r6.length;
            if (length != 0) {
                int i = 0;
                while (r6[i] != r9) {
                    i++;
                    if (i >= length) {
                        return;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    r1 = A04;
                } else {
                    r1 = new C12711yC[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(r6, r1));
    }

    @Override // X.AbstractC12721yD
    public final void A8A(@NonNull AbstractC12271xB r3) {
        if (this.A03.get() == A05) {
            r3.dispose();
        }
    }

    @Override // X.AbstractC12721yD
    public final void onError(@NonNull Throwable th) {
        AnonymousClass219.A01(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.A02.compareAndSet(false, true)) {
            this.A01 = th;
            for (C12711yC<T> r0 : this.A03.getAndSet(A05)) {
                r0.downstream.onError(th);
            }
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(@NonNull T t) {
        AnonymousClass219.A01(t, "onSuccess called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.A02.compareAndSet(false, true)) {
            this.A00 = t;
            for (C12711yC<T> r0 : this.A03.getAndSet(A05)) {
                r0.downstream.onSuccess(t);
            }
        }
    }
}
