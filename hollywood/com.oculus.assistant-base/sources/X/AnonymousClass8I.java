package X;

/* renamed from: X.8I  reason: invalid class name */
public final class AnonymousClass8I implements AutoCloseable {
    public final AnonymousClass8J A00;

    @Override // java.lang.AutoCloseable
    public final void close() {
        AnonymousClass8J r1 = this.A00;
        synchronized (r1) {
            r1.A04 = false;
            AnonymousClass8J.A00(r1);
        }
    }

    public AnonymousClass8I(AnonymousClass8J r2) {
        this.A00 = r2;
        synchronized (r2) {
            r2.A04 = true;
        }
    }
}
