package X;

public abstract class U8 {
    public void A00(X9 x9, X9 x92) {
        if (!(this instanceof MM)) {
            ((MN) this).A02.lazySet(x9, x92);
        } else {
            x9.next = x92;
        }
    }

    public void A01(X9 x9, Thread thread) {
        if (!(this instanceof MM)) {
            ((MN) this).A03.lazySet(x9, thread);
        } else {
            x9.thread = thread;
        }
    }

    public boolean A02(AnonymousClass6f<?> r2, X6 x6, X6 x62) {
        boolean z;
        if (!(this instanceof MM)) {
            return ((MN) this).A00.compareAndSet(r2, x6, x62);
        }
        synchronized (r2) {
            if (r2.listeners == x6) {
                r2.listeners = x62;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean A03(AnonymousClass6f<?> r2, X9 x9, X9 x92) {
        boolean z;
        if (!(this instanceof MM)) {
            return ((MN) this).A04.compareAndSet(r2, x9, x92);
        }
        synchronized (r2) {
            if (r2.waiters == x9) {
                r2.waiters = x92;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean A04(AnonymousClass6f<?> r2, Object obj, Object obj2) {
        boolean z;
        if (!(this instanceof MM)) {
            return ((MN) this).A01.compareAndSet(r2, obj, obj2);
        }
        synchronized (r2) {
            if (r2.value == obj) {
                r2.value = obj2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
