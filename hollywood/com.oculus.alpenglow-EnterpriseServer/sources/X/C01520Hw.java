package X;

/* renamed from: X.0Hw  reason: invalid class name and case insensitive filesystem */
public final class C01520Hw<TResult> {
    public final AnonymousClass0Hv<TResult> A00 = new AnonymousClass0Hv<>();

    public final void A00() {
        if (!this.A00.A06()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public final void A01(Exception exc) {
        AnonymousClass0Hv<TResult> r2 = this.A00;
        Object obj = r2.A05;
        synchronized (obj) {
            if (r2.A04) {
                throw new IllegalStateException("Cannot set the error on a completed task.");
            }
            r2.A04 = true;
            r2.A00 = exc;
            obj.notifyAll();
            AnonymousClass0Hv.A01(r2);
        }
    }

    public final void A02(TResult tresult) {
        if (!this.A00.A07(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }
}
