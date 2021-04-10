package X;

import java.io.IOException;

public abstract class K5 implements AbstractC0312cb {
    public final AbstractC0312cb delegate;

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // X.AbstractC0312cb
    public long read(AnonymousClass98 r3, long j) throws IOException {
        return this.delegate.read(r3, j);
    }

    @Override // X.AbstractC0312cb
    public ca timeout() {
        return this.delegate.timeout();
    }

    public K5(AbstractC0312cb cbVar) {
        if (cbVar != null) {
            this.delegate = cbVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public String toString() {
        return AnonymousClass06.A06(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }

    public final AbstractC0312cb delegate() {
        return this.delegate;
    }
}
