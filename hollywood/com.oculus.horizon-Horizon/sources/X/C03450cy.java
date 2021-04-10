package X;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: X.0cy  reason: invalid class name and case insensitive filesystem */
public final class C03450cy extends AbstractC08240wE {
    public final AtomicReferenceFieldUpdater<AnonymousClass06Z, C08280wI> A00;
    public final AtomicReferenceFieldUpdater<AnonymousClass06Z, Object> A01;
    public final AtomicReferenceFieldUpdater<C08310wL, C08310wL> A02;
    public final AtomicReferenceFieldUpdater<C08310wL, Thread> A03;
    public final AtomicReferenceFieldUpdater<AnonymousClass06Z, C08310wL> A04;

    @Override // X.AbstractC08240wE
    public final void A00(C08310wL r2, C08310wL r3) {
        this.A02.lazySet(r2, r3);
    }

    @Override // X.AbstractC08240wE
    public final void A01(C08310wL r2, Thread thread) {
        this.A03.lazySet(r2, thread);
    }

    @Override // X.AbstractC08240wE
    public final boolean A02(AnonymousClass06Z<?> r2, C08280wI r3, C08280wI r4) {
        return this.A00.compareAndSet(r2, r3, r4);
    }

    @Override // X.AbstractC08240wE
    public final boolean A03(AnonymousClass06Z<?> r2, C08310wL r3, C08310wL r4) {
        return this.A04.compareAndSet(r2, r3, r4);
    }

    @Override // X.AbstractC08240wE
    public final boolean A04(AnonymousClass06Z<?> r2, Object obj, Object obj2) {
        return this.A01.compareAndSet(r2, obj, obj2);
    }

    public C03450cy(AtomicReferenceFieldUpdater<C08310wL, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<C08310wL, C08310wL> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AnonymousClass06Z, C08310wL> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AnonymousClass06Z, C08280wI> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AnonymousClass06Z, Object> atomicReferenceFieldUpdater5) {
        this.A03 = atomicReferenceFieldUpdater;
        this.A02 = atomicReferenceFieldUpdater2;
        this.A04 = atomicReferenceFieldUpdater3;
        this.A00 = atomicReferenceFieldUpdater4;
        this.A01 = atomicReferenceFieldUpdater5;
    }
}
