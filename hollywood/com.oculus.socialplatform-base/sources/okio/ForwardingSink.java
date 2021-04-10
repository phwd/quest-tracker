package okio;

import X.AnonymousClass006;
import java.io.IOException;

public abstract class ForwardingSink implements Sink {
    public final Sink delegate;

    @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        this.delegate.write(buffer, j);
    }

    public ForwardingSink(Sink sink) {
        if (sink != null) {
            this.delegate = sink;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final Sink delegate() {
        return this.delegate;
    }

    public String toString() {
        return AnonymousClass006.A0B(getClass().getSimpleName(), "(", this.delegate.toString(), ")");
    }
}
