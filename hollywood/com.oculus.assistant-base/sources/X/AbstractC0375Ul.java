package X;

/* renamed from: X.Ul  reason: case insensitive filesystem */
public abstract class AbstractC0375Ul {
    public void A00(Us us, Us us2) {
        if (!(this instanceof C1188up)) {
            ((C1187uo) this).A02.lazySet(us, us2);
        } else {
            us.next = us2;
        }
    }

    public void A01(Us us, Thread thread) {
        if (!(this instanceof C1188up)) {
            ((C1187uo) this).A03.lazySet(us, thread);
        } else {
            us.thread = thread;
        }
    }

    public boolean A02(SH sh, C0379Up up, C0379Up up2) {
        boolean z;
        if (!(this instanceof C1188up)) {
            return ((C1187uo) this).A00.compareAndSet(sh, up, up2);
        }
        synchronized (sh) {
            if (sh.listeners == up) {
                sh.listeners = up2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean A03(SH sh, Us us, Us us2) {
        boolean z;
        if (!(this instanceof C1188up)) {
            return ((C1187uo) this).A04.compareAndSet(sh, us, us2);
        }
        synchronized (sh) {
            if (sh.waiters == us) {
                sh.waiters = us2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean A04(SH sh, Object obj, Object obj2) {
        boolean z;
        if (!(this instanceof C1188up)) {
            return ((C1187uo) this).A01.compareAndSet(sh, obj, obj2);
        }
        synchronized (sh) {
            if (sh.value == obj) {
                sh.value = obj2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
