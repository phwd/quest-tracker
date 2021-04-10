package X;

public final class FZ<TResult> {
    public final FY<TResult> A00 = new FY<>();

    public final void A00(Exception exc) {
        FY<TResult> fy = this.A00;
        Object obj = fy.A04;
        synchronized (obj) {
            if (fy.A02) {
                throw new IllegalStateException("Cannot set the error on a completed task.");
            }
            fy.A02 = true;
            fy.A00 = exc;
            obj.notifyAll();
            FY.A00(fy);
        }
    }

    public final void A01(TResult tresult) {
        if (!this.A00.A04(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }
}
