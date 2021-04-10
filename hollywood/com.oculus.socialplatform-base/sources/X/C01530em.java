package X;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: X.0em  reason: invalid class name and case insensitive filesystem */
public final class C01530em extends AnonymousClass110 {
    public final AtomicReferenceFieldUpdater<AnonymousClass0BR, AnonymousClass114> A00;
    public final AtomicReferenceFieldUpdater<AnonymousClass0BR, Object> A01;
    public final AtomicReferenceFieldUpdater<AnonymousClass117, AnonymousClass117> A02;
    public final AtomicReferenceFieldUpdater<AnonymousClass117, Thread> A03;
    public final AtomicReferenceFieldUpdater<AnonymousClass0BR, AnonymousClass117> A04;

    @Override // X.AnonymousClass110
    public final void A00(AnonymousClass117 r2, AnonymousClass117 r3) {
        this.A02.lazySet(r2, r3);
    }

    @Override // X.AnonymousClass110
    public final void A01(AnonymousClass117 r2, Thread thread) {
        this.A03.lazySet(r2, thread);
    }

    @Override // X.AnonymousClass110
    public final boolean A02(AnonymousClass0BR<?> r2, AnonymousClass114 r3, AnonymousClass114 r4) {
        return this.A00.compareAndSet(r2, r3, r4);
    }

    @Override // X.AnonymousClass110
    public final boolean A03(AnonymousClass0BR<?> r2, AnonymousClass117 r3, AnonymousClass117 r4) {
        return this.A04.compareAndSet(r2, r3, r4);
    }

    @Override // X.AnonymousClass110
    public final boolean A04(AnonymousClass0BR<?> r2, Object obj, Object obj2) {
        return this.A01.compareAndSet(r2, obj, obj2);
    }

    public C01530em(AtomicReferenceFieldUpdater<AnonymousClass117, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<AnonymousClass117, AnonymousClass117> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AnonymousClass0BR, AnonymousClass117> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AnonymousClass0BR, AnonymousClass114> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AnonymousClass0BR, Object> atomicReferenceFieldUpdater5) {
        this.A03 = atomicReferenceFieldUpdater;
        this.A02 = atomicReferenceFieldUpdater2;
        this.A04 = atomicReferenceFieldUpdater3;
        this.A00 = atomicReferenceFieldUpdater4;
        this.A01 = atomicReferenceFieldUpdater5;
    }
}
