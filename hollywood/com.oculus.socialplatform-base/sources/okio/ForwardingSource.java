package okio;

import X.AnonymousClass006;
import java.io.IOException;

public abstract class ForwardingSource implements Source {
    public final Source delegate;

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        return this.delegate.read(buffer, j);
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public ForwardingSource(Source source) {
        if (source != null) {
            this.delegate = source;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final Source delegate() {
        return this.delegate;
    }

    public String toString() {
        return AnonymousClass006.A0B(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
