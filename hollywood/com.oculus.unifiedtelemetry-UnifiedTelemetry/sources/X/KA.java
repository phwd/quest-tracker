package X;

import java.io.IOException;

public abstract class KA implements AbstractC0313cc {
    public final AbstractC0313cc delegate;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // X.AbstractC0313cc, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // X.AbstractC0313cc
    public ca timeout() {
        return this.delegate.timeout();
    }

    @Override // X.AbstractC0313cc
    public void write(AnonymousClass98 r2, long j) throws IOException {
        this.delegate.write(r2, j);
    }

    public KA(AbstractC0313cc ccVar) {
        if (ccVar != null) {
            this.delegate = ccVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public String toString() {
        return AnonymousClass06.A06(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }

    public final AbstractC0313cc delegate() {
        return this.delegate;
    }
}
