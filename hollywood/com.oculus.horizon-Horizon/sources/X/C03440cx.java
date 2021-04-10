package X;

/* renamed from: X.0cx  reason: invalid class name and case insensitive filesystem */
public final class C03440cx extends AbstractC08240wE {
    @Override // X.AbstractC08240wE
    public final boolean A02(AnonymousClass06Z<?> r2, C08280wI r3, C08280wI r4) {
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

    @Override // X.AbstractC08240wE
    public final boolean A03(AnonymousClass06Z<?> r2, C08310wL r3, C08310wL r4) {
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

    @Override // X.AbstractC08240wE
    public final boolean A04(AnonymousClass06Z<?> r2, Object obj, Object obj2) {
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

    @Override // X.AbstractC08240wE
    public final void A00(C08310wL r1, C08310wL r2) {
        r1.next = r2;
    }

    @Override // X.AbstractC08240wE
    public final void A01(C08310wL r1, Thread thread) {
        r1.thread = thread;
    }
}
