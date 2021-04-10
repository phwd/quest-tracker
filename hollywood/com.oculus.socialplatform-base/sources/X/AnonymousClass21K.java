package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21K  reason: invalid class name */
public final class AnonymousClass21K<T> implements AbstractC13121yu<T> {
    public final AbstractC139821e<T> A00;
    public final AtomicReference<AnonymousClass21J<T>> A01;

    @Override // X.AbstractC13121yu
    public final void AAa(AnonymousClass1yM<? super T> r8) {
        AtomicReference<AnonymousClass21J<T>> atomicReference;
        AnonymousClass21J<T> r6;
        AnonymousClass21P[] r4;
        AnonymousClass21P[] r1;
        do {
            atomicReference = this.A01;
            r6 = atomicReference.get();
            if (r6 != null) {
                break;
            }
            r6 = new AnonymousClass21J<>(this.A00.A1v());
        } while (!atomicReference.compareAndSet(null, r6));
        AnonymousClass21P<T> r5 = new AnonymousClass21P<>(r6, r8);
        r8.A8A(r5);
        do {
            r4 = r6.observers.get();
            if (r4 == AnonymousClass21J.A01) {
                break;
            }
            int length = r4.length;
            r1 = new AnonymousClass21P[(length + 1)];
            System.arraycopy(r4, 0, r1, 0, length);
            r1[length] = r5;
        } while (!r6.observers.compareAndSet(r4, r1));
        if (r5.cancelled) {
            r6.A00(r5);
        } else {
            r6.buffer.A9F(r5);
        }
    }

    public AnonymousClass21K(AtomicReference<AnonymousClass21J<T>> atomicReference, AbstractC139821e<T> r2) {
        this.A01 = atomicReference;
        this.A00 = r2;
    }
}
