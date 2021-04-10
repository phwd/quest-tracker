package X;

import java.io.IOException;

/* renamed from: X.0OY  reason: invalid class name */
public abstract class AnonymousClass0OY implements AbstractC04550h0 {
    public final AbstractC04550h0 delegate;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
    public void close() throws IOException {
        this.delegate.close();
    }

    public final AbstractC04550h0 delegate() {
        return this.delegate;
    }

    @Override // X.AbstractC04550h0
    public long read(AnonymousClass0HR r3, long j) throws IOException {
        return this.delegate.read(r3, j);
    }

    @Override // X.AbstractC04550h0
    public C04540gz timeout() {
        return this.delegate.timeout();
    }

    public AnonymousClass0OY(AbstractC04550h0 r3) {
        if (r3 != null) {
            this.delegate = r3;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public String toString() {
        return AnonymousClass006.A08(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
