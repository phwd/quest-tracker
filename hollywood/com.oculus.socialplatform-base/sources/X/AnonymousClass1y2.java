package X;

/* renamed from: X.1y2  reason: invalid class name */
public final class AnonymousClass1y2 {
    public static final AbstractC12361xL A00;

    static {
        try {
            AbstractC12361xL r0 = (AbstractC12361xL) new AnonymousClass1y1().call();
            if (r0 != null) {
                A00 = r0;
                return;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw C12301xE.A00(th);
        }
    }

    public static AbstractC12361xL A00() {
        AbstractC12361xL r0 = A00;
        if (r0 != null) {
            return r0;
        }
        throw new NullPointerException("scheduler == null");
    }
}
