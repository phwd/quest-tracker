package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21F  reason: invalid class name */
public final class AnonymousClass21F<T> implements AbstractC13121yu<T> {
    public final AtomicReference<AnonymousClass21E<T>> A00;

    @Override // X.AbstractC13121yu
    public final void AAa(AnonymousClass1yM<? super T> r8) {
        AnonymousClass21E<T> r6;
        AnonymousClass21Q<T> r5 = new AnonymousClass21Q<>(r8);
        r8.A8A(r5);
        loop0:
        while (true) {
            AtomicReference<AnonymousClass21E<T>> atomicReference = this.A00;
            r6 = atomicReference.get();
            if (r6 == null || r6.A03.get() == AnonymousClass21E.A05) {
                AnonymousClass21E<T> r1 = new AnonymousClass21E<>(atomicReference);
                if (atomicReference.compareAndSet(r6, r1)) {
                    r6 = r1;
                } else {
                    continue;
                }
            }
            while (true) {
                AtomicReference<AnonymousClass21Q<T>[]> atomicReference2 = r6.A03;
                AnonymousClass21Q<T>[] r3 = atomicReference2.get();
                if (r3 == AnonymousClass21E.A05) {
                    continue;
                    break;
                }
                int length = r3.length;
                AnonymousClass21Q<T>[] r0 = new AnonymousClass21Q[(length + 1)];
                System.arraycopy(r3, 0, r0, 0, length);
                r0[length] = r5;
                if (atomicReference2.compareAndSet(r3, r0)) {
                    break loop0;
                }
            }
        }
        if (!r5.compareAndSet(null, r6)) {
            r6.A00(r5);
        }
    }

    public AnonymousClass21F(AtomicReference<AnonymousClass21E<T>> atomicReference) {
        this.A00 = atomicReference;
    }
}
