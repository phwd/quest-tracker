package X;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public final class UL extends AS {
    public final AtomicReferenceFieldUpdater<AbstractC00136k, C0048Al> A00;
    public final AtomicReferenceFieldUpdater<AbstractC00136k, Object> A01;
    public final AtomicReferenceFieldUpdater<B0, B0> A02;
    public final AtomicReferenceFieldUpdater<B0, Thread> A03;
    public final AtomicReferenceFieldUpdater<AbstractC00136k, B0> A04;

    @Override // X.AS
    public final void A00(B0 b0, B0 b02) {
        this.A02.lazySet(b0, b02);
    }

    @Override // X.AS
    public final void A01(B0 b0, Thread thread) {
        this.A03.lazySet(b0, thread);
    }

    @Override // X.AS
    public final boolean A02(AbstractC00136k<?> r2, C0048Al al, C0048Al al2) {
        return this.A00.compareAndSet(r2, al, al2);
    }

    @Override // X.AS
    public final boolean A03(AbstractC00136k<?> r2, B0 b0, B0 b02) {
        return this.A04.compareAndSet(r2, b0, b02);
    }

    @Override // X.AS
    public final boolean A04(AbstractC00136k<?> r2, Object obj, Object obj2) {
        return this.A01.compareAndSet(r2, obj, obj2);
    }

    public UL(AtomicReferenceFieldUpdater<B0, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<B0, B0> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractC00136k, B0> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractC00136k, C0048Al> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractC00136k, Object> atomicReferenceFieldUpdater5) {
        this.A03 = atomicReferenceFieldUpdater;
        this.A02 = atomicReferenceFieldUpdater2;
        this.A04 = atomicReferenceFieldUpdater3;
        this.A00 = atomicReferenceFieldUpdater4;
        this.A01 = atomicReferenceFieldUpdater5;
    }
}
