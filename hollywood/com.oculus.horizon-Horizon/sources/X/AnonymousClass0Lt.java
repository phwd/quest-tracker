package X;

import java.io.IOException;

/* renamed from: X.0Lt  reason: invalid class name */
public abstract class AnonymousClass0Lt implements AbstractC07630v6 {
    public final AbstractC07630v6 delegate;

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // X.AbstractC07630v6
    public long read(AnonymousClass0B3 r3, long j) throws IOException {
        return this.delegate.read(r3, j);
    }

    @Override // X.AbstractC07630v6
    public C07620v5 timeout() {
        return this.delegate.timeout();
    }

    public AnonymousClass0Lt(AbstractC07630v6 r3) {
        if (r3 != null) {
            this.delegate = r3;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final AbstractC07630v6 delegate() {
        return this.delegate;
    }

    public String toString() {
        return AnonymousClass006.A08(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
