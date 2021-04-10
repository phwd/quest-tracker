package X;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: X.0Xl  reason: invalid class name and case insensitive filesystem */
public final class C02270Xl extends AbstractC01530Hx {
    public final AtomicReferenceFieldUpdater<AnonymousClass0BX, AnonymousClass0HF> A00;
    public final AtomicReferenceFieldUpdater<AnonymousClass0BX, Object> A01;
    public final AtomicReferenceFieldUpdater<AnonymousClass0H4, AnonymousClass0H4> A02;
    public final AtomicReferenceFieldUpdater<AnonymousClass0H4, Thread> A03;
    public final AtomicReferenceFieldUpdater<AnonymousClass0BX, AnonymousClass0H4> A04;

    @Override // X.AbstractC01530Hx
    public final void A00(AnonymousClass0H4 r2, AnonymousClass0H4 r3) {
        this.A02.lazySet(r2, r3);
    }

    @Override // X.AbstractC01530Hx
    public final void A01(AnonymousClass0H4 r2, Thread thread) {
        this.A03.lazySet(r2, thread);
    }

    @Override // X.AbstractC01530Hx
    public final boolean A02(AnonymousClass0BX<?> r2, AnonymousClass0HF r3, AnonymousClass0HF r4) {
        return this.A00.compareAndSet(r2, r3, r4);
    }

    @Override // X.AbstractC01530Hx
    public final boolean A03(AnonymousClass0BX<?> r2, AnonymousClass0H4 r3, AnonymousClass0H4 r4) {
        return this.A04.compareAndSet(r2, r3, r4);
    }

    @Override // X.AbstractC01530Hx
    public final boolean A04(AnonymousClass0BX<?> r2, Object obj, Object obj2) {
        return this.A01.compareAndSet(r2, obj, obj2);
    }

    public C02270Xl(AtomicReferenceFieldUpdater<AnonymousClass0H4, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<AnonymousClass0H4, AnonymousClass0H4> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AnonymousClass0BX, AnonymousClass0H4> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AnonymousClass0BX, AnonymousClass0HF> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AnonymousClass0BX, Object> atomicReferenceFieldUpdater5) {
        this.A03 = atomicReferenceFieldUpdater;
        this.A02 = atomicReferenceFieldUpdater2;
        this.A04 = atomicReferenceFieldUpdater3;
        this.A00 = atomicReferenceFieldUpdater4;
        this.A01 = atomicReferenceFieldUpdater5;
    }
}
