package X;

/* renamed from: X.0DD  reason: invalid class name */
public final class AnonymousClass0DD<TResult> {
    public final AnonymousClass0DC<TResult> A00 = new AnonymousClass0DC<>();

    public final void A00() {
        if (!this.A00.A0L()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public final void A02(TResult tresult) {
        if (!this.A00.A0N(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public final boolean A03(Exception exc) {
        AnonymousClass0DC<TResult> r2 = this.A00;
        Object obj = r2.A05;
        synchronized (obj) {
            if (r2.A01) {
                return false;
            }
            r2.A01 = true;
            r2.A00 = exc;
            obj.notifyAll();
            AnonymousClass0DC.A08(r2);
            return true;
        }
    }

    public final void A01(Exception exc) {
        if (!A03(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
