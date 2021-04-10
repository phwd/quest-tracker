package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21M  reason: invalid class name */
public abstract class AnonymousClass21M<T> extends AtomicReference<C139721d> implements AnonymousClass21R<T> {
    public static final long serialVersionUID = 2346567790059478686L;
    public int size;
    public C139721d tail;

    @Override // X.AnonymousClass21R
    public final void A6Y(T t) {
        C139721d r1 = new C139721d(t);
        this.tail.set(r1);
        this.tail = r1;
        this.size++;
        AnonymousClass21W r2 = (AnonymousClass21W) this;
        if (r2.size > r2.limit) {
            r2.size--;
            r2.set(((AtomicReference) r2.get()).get());
        }
    }

    public AnonymousClass21M() {
        C139721d r0 = new C139721d(null);
        this.tail = r0;
        set(r0);
    }

    @Override // X.AnonymousClass21R
    public final void A2C() {
        C139721d r1 = new C139721d(EnumC139220y.complete());
        this.tail.set(r1);
        this.tail = r1;
        this.size++;
        C139721d r2 = (C139721d) get();
        if (r2.value != null) {
            C139721d r12 = new C139721d(null);
            r12.lazySet(r2.get());
            set(r12);
        }
    }

    @Override // X.AnonymousClass21R
    public final void A2t(Throwable th) {
        C139721d r1 = new C139721d(EnumC139220y.error(th));
        this.tail.set(r1);
        this.tail = r1;
        this.size++;
        C139721d r2 = (C139721d) get();
        if (r2.value != null) {
            C139721d r12 = new C139721d(null);
            r12.lazySet(r2.get());
            set(r12);
        }
    }

    @Override // X.AnonymousClass21R
    public final void A9F(AnonymousClass21P<T> r5) {
        if (r5.getAndIncrement() == 0) {
            int i = 1;
            do {
                C139721d r1 = (AtomicReference) r5.index;
                if (r1 == null) {
                    r1 = (AtomicReference) get();
                    r5.index = r1;
                }
                while (!r5.cancelled) {
                    C139721d r2 = (C139721d) r1.get();
                    if (r2 == null) {
                        r5.index = r1;
                        i = r5.addAndGet(-i);
                    } else if (EnumC139220y.accept(r2.value, r5.child)) {
                        r5.index = null;
                        return;
                    } else {
                        r1 = r2;
                    }
                }
                return;
            } while (i != 0);
        }
    }
}
