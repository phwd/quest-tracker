package X;

import java.io.IOException;

public abstract class Dn implements WG {
    public final WG delegate;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // X.WG, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // X.WG
    public WE timeout() {
        return this.delegate.timeout();
    }

    @Override // X.WG
    public void write(AnonymousClass8k r2, long j) throws IOException {
        this.delegate.write(r2, j);
    }

    public Dn(WG wg) {
        if (wg != null) {
            this.delegate = wg;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final WG delegate() {
        return this.delegate;
    }

    public String toString() {
        return AnonymousClass06.A05(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
