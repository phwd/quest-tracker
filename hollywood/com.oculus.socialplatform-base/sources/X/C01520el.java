package X;

/* renamed from: X.0el  reason: invalid class name and case insensitive filesystem */
public final class C01520el extends AnonymousClass110 {
    @Override // X.AnonymousClass110
    public final boolean A02(AnonymousClass0BR<?> r2, AnonymousClass114 r3, AnonymousClass114 r4) {
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

    @Override // X.AnonymousClass110
    public final boolean A03(AnonymousClass0BR<?> r2, AnonymousClass117 r3, AnonymousClass117 r4) {
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

    @Override // X.AnonymousClass110
    public final boolean A04(AnonymousClass0BR<?> r2, Object obj, Object obj2) {
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

    @Override // X.AnonymousClass110
    public final void A00(AnonymousClass117 r1, AnonymousClass117 r2) {
        r1.next = r2;
    }

    @Override // X.AnonymousClass110
    public final void A01(AnonymousClass117 r1, Thread thread) {
        r1.thread = thread;
    }
}
