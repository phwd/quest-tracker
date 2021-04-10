package X;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21E  reason: invalid class name */
public final class AnonymousClass21E<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final AnonymousClass21Q[] A04 = new AnonymousClass21Q[0];
    public static final AnonymousClass21Q[] A05 = new AnonymousClass21Q[0];
    public final AtomicReference<AnonymousClass21E<T>> A00;
    public final AtomicReference<AbstractC12271xB> A01 = new AtomicReference<>();
    public final AtomicBoolean A02;
    public final AtomicReference<AnonymousClass21Q<T>[]> A03 = new AtomicReference<>(A04);

    public final void A00(AnonymousClass21Q<T> r9) {
        AtomicReference<AnonymousClass21Q<T>[]> atomicReference;
        AnonymousClass21Q<T>[] r6;
        AnonymousClass21Q<T>[] r1;
        do {
            atomicReference = this.A03;
            r6 = atomicReference.get();
            int length = r6.length;
            if (length != 0) {
                int i = 0;
                while (!r6[i].equals(r9)) {
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
                    r1 = new AnonymousClass21Q[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(r6, r1));
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        EnumC12511xi.setOnce(this.A01, r2);
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        AtomicReference<AnonymousClass21Q<T>[]> atomicReference = this.A03;
        AnonymousClass21Q<T>[] r1 = A05;
        if (atomicReference.getAndSet(r1) != r1) {
            this.A00.compareAndSet(this, null);
            EnumC12511xi.dispose(this.A01);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.A00.compareAndSet(this, null);
        for (AnonymousClass21Q<T> r0 : this.A03.getAndSet(A05)) {
            r0.child.onComplete();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        this.A00.compareAndSet(this, null);
        AnonymousClass21Q<T>[] andSet = this.A03.getAndSet(A05);
        int length = andSet.length;
        if (length != 0) {
            int i = 0;
            do {
                andSet[i].child.onError(th);
                i++;
            } while (i < length);
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        for (AnonymousClass21Q<T> r0 : this.A03.get()) {
            r0.child.onNext(t);
        }
    }

    public AnonymousClass21E(AtomicReference<AnonymousClass21E<T>> atomicReference) {
        this.A00 = atomicReference;
        this.A02 = new AtomicBoolean();
    }
}
