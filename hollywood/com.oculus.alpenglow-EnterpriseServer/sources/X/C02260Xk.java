package X;

/* renamed from: X.0Xk  reason: invalid class name and case insensitive filesystem */
public final class C02260Xk extends AbstractC01530Hx {
    @Override // X.AbstractC01530Hx
    public final boolean A02(AnonymousClass0BX<?> r2, AnonymousClass0HF r3, AnonymousClass0HF r4) {
        boolean z;
        synchronized (r2) {
            if (r2.listeners == r3) {
                r2.listeners = r4;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @Override // X.AbstractC01530Hx
    public final boolean A03(AnonymousClass0BX<?> r2, AnonymousClass0H4 r3, AnonymousClass0H4 r4) {
        boolean z;
        synchronized (r2) {
            if (r2.waiters == r3) {
                r2.waiters = r4;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @Override // X.AbstractC01530Hx
    public final boolean A04(AnonymousClass0BX<?> r2, Object obj, Object obj2) {
        boolean z;
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

    @Override // X.AbstractC01530Hx
    public final void A00(AnonymousClass0H4 r1, AnonymousClass0H4 r2) {
        r1.next = r2;
    }

    @Override // X.AbstractC01530Hx
    public final void A01(AnonymousClass0H4 r1, Thread thread) {
        r1.thread = thread;
    }
}
