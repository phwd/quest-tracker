package X;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public final class MN extends U8 {
    public final AtomicReferenceFieldUpdater<AnonymousClass6f, X6> A00;
    public final AtomicReferenceFieldUpdater<AnonymousClass6f, Object> A01;
    public final AtomicReferenceFieldUpdater<X9, X9> A02;
    public final AtomicReferenceFieldUpdater<X9, Thread> A03;
    public final AtomicReferenceFieldUpdater<AnonymousClass6f, X9> A04;

    public MN(AtomicReferenceFieldUpdater<X9, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<X9, X9> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AnonymousClass6f, X9> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AnonymousClass6f, X6> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AnonymousClass6f, Object> atomicReferenceFieldUpdater5) {
        this.A03 = atomicReferenceFieldUpdater;
        this.A02 = atomicReferenceFieldUpdater2;
        this.A04 = atomicReferenceFieldUpdater3;
        this.A00 = atomicReferenceFieldUpdater4;
        this.A01 = atomicReferenceFieldUpdater5;
    }
}
