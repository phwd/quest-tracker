package okio;

import X.AnonymousClass006;
import java.io.IOException;

public final class Pipe {
    public final Buffer buffer = new Buffer();
    public final long maxBufferSize;
    public final Sink sink = new PipeSink();
    public boolean sinkClosed;
    public final Source source = new PipeSource();
    public boolean sourceClosed;

    public final class PipeSink implements Sink {
        public final Timeout timeout = new Timeout();

        public PipeSink() {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe pipe = Pipe.this;
                if (!pipe.sinkClosed) {
                    if (!pipe.sourceClosed || pipe.buffer.size <= 0) {
                        pipe.sinkClosed = true;
                        pipe.buffer.notifyAll();
                    } else {
                        throw new IOException("source is closed");
                    }
                }
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe pipe = Pipe.this;
                if (pipe.sinkClosed) {
                    throw new IllegalStateException("closed");
                } else if (pipe.sourceClosed && pipe.buffer.size > 0) {
                    throw new IOException("source is closed");
                }
            }
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            synchronized (Pipe.this.buffer) {
                if (Pipe.this.sinkClosed) {
                    throw new IllegalStateException("closed");
                }
                while (j > 0) {
                    Pipe pipe = Pipe.this;
                    if (!pipe.sourceClosed) {
                        long j2 = pipe.maxBufferSize;
                        Buffer buffer2 = pipe.buffer;
                        long j3 = j2 - buffer2.size;
                        if (j3 == 0) {
                            this.timeout.waitUntilNotified(buffer2);
                        } else {
                            long min = Math.min(j3, j);
                            buffer2.write(buffer, min);
                            j -= min;
                            Pipe.this.buffer.notifyAll();
                        }
                    } else {
                        throw new IOException("source is closed");
                    }
                }
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public final class PipeSource implements Source {
        public final Timeout timeout = new Timeout();

        public PipeSource() {
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe pipe = Pipe.this;
                pipe.sourceClosed = true;
                pipe.buffer.notifyAll();
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            long read;
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sourceClosed) {
                    while (true) {
                        Pipe pipe = Pipe.this;
                        Buffer buffer2 = pipe.buffer;
                        if (buffer2.size != 0) {
                            read = buffer2.read(buffer, j);
                            Pipe.this.buffer.notifyAll();
                            break;
                        } else if (pipe.sinkClosed) {
                            read = -1;
                            break;
                        } else {
                            this.timeout.waitUntilNotified(buffer2);
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
            return read;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public Pipe(long j) {
        if (j >= 1) {
            this.maxBufferSize = j;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("maxBufferSize < 1: ", j));
    }

    public Sink sink() {
        return this.sink;
    }

    public Source source() {
        return this.source;
    }
}
