package X;

import java.io.IOException;

/* renamed from: X.0Lu  reason: invalid class name */
public abstract class AnonymousClass0Lu implements AbstractC07640v7 {
    public final AbstractC07640v7 delegate;

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // X.AbstractC07640v7, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // X.AbstractC07640v7
    public C07620v5 timeout() {
        return this.delegate.timeout();
    }

    @Override // X.AbstractC07640v7
    public void write(AnonymousClass0B3 r2, long j) throws IOException {
        this.delegate.write(r2, j);
    }

    public AnonymousClass0Lu(AbstractC07640v7 r3) {
        if (r3 != null) {
            this.delegate = r3;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final AbstractC07640v7 delegate() {
        return this.delegate;
    }

    public String toString() {
        return AnonymousClass006.A08(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
