package X;

public final class UK extends AS {
    @Override // X.AS
    public final boolean A02(AbstractC00136k<?> r2, C0048Al al, C0048Al al2) {
        boolean z;
        synchronized (r2) {
            if (r2.listeners == al) {
                r2.listeners = al2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @Override // X.AS
    public final boolean A03(AbstractC00136k<?> r2, B0 b0, B0 b02) {
        boolean z;
        synchronized (r2) {
            if (r2.waiters == b0) {
                r2.waiters = b02;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @Override // X.AS
    public final boolean A04(AbstractC00136k<?> r2, Object obj, Object obj2) {
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

    @Override // X.AS
    public final void A00(B0 b0, B0 b02) {
        b0.next = b02;
    }

    @Override // X.AS
    public final void A01(B0 b0, Thread thread) {
        b0.thread = thread;
    }
}
