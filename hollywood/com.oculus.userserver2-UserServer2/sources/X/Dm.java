package X;

import java.io.IOException;

public abstract class Dm implements WF {
    public final WF delegate;

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // X.WF
    public long read(AnonymousClass8k r3, long j) throws IOException {
        return this.delegate.read(r3, j);
    }

    @Override // X.WF
    public WE timeout() {
        return this.delegate.timeout();
    }

    public Dm(WF wf) {
        if (wf != null) {
            this.delegate = wf;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final WF delegate() {
        return this.delegate;
    }

    public String toString() {
        return AnonymousClass06.A05(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
