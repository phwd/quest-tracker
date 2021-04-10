package X;

public final class DC<TResult> {
    public final DB<TResult> A00 = new DB<>();

    public final void A00() {
        if (!this.A00.A07()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public final void A01(Exception exc) {
        DB<TResult> db = this.A00;
        Object obj = db.A05;
        synchronized (obj) {
            if (db.A04) {
                throw new IllegalStateException("Cannot set the error on a completed task.");
            }
            db.A04 = true;
            db.A00 = exc;
            obj.notifyAll();
            DB.A01(db);
        }
    }

    public final void A02(TResult tresult) {
        if (!this.A00.A08(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }
}
