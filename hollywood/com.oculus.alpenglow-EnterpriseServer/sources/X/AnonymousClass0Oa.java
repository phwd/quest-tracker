package X;

import java.io.IOException;

/* renamed from: X.0Oa  reason: invalid class name */
public abstract class AnonymousClass0Oa implements AnonymousClass0h1 {
    public final AnonymousClass0h1 delegate;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public void close() throws IOException {
        this.delegate.close();
    }

    public final AnonymousClass0h1 delegate() {
        return this.delegate;
    }

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // X.AnonymousClass0h1
    public C04540gz timeout() {
        return this.delegate.timeout();
    }

    @Override // X.AnonymousClass0h1
    public void write(AnonymousClass0HR r2, long j) throws IOException {
        this.delegate.write(r2, j);
    }

    public AnonymousClass0Oa(AnonymousClass0h1 r3) {
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
