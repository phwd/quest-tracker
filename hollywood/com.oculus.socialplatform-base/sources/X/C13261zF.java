package X;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zF  reason: invalid class name and case insensitive filesystem */
public final class C13261zF<T> extends AbstractC13251zE<T> implements AbstractC12721yD<T> {
    public static final C13281zH[] A05 = new C13281zH[0];
    public static final C13281zH[] A06 = new C13281zH[0];
    public T A00;
    public Throwable A01;
    public final AbstractC12761yH<? extends T> A02;
    public final AtomicInteger A03 = new AtomicInteger();
    public final AtomicReference<C13281zH<T>[]> A04 = new AtomicReference<>(A05);

    @Override // X.AbstractC12721yD
    public final void A8A(AbstractC12271xB r1) {
    }

    public final void A07(C13281zH<T> r9) {
        AtomicReference<C13281zH<T>[]> atomicReference;
        C13281zH<T>[] r6;
        C13281zH<T>[] r1;
        do {
            atomicReference = this.A04;
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
                    r1 = A05;
                } else {
                    r1 = new C13281zH[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(r6, r1));
    }

    @Override // X.AbstractC12721yD
    public final void onError(Throwable th) {
        this.A01 = th;
        C13281zH<T>[] andSet = this.A04.getAndSet(A06);
        for (C13281zH<T> r1 : andSet) {
            if (!r1.get()) {
                r1.downstream.onError(th);
            }
        }
    }

    @Override // X.AbstractC12721yD
    public final void onSuccess(T t) {
        this.A00 = t;
        C13281zH<T>[] andSet = this.A04.getAndSet(A06);
        for (C13281zH<T> r1 : andSet) {
            if (!r1.get()) {
                r1.downstream.onSuccess(t);
            }
        }
    }

    public C13261zF(AbstractC12761yH<? extends T> r3) {
        this.A02 = r3;
    }
}
