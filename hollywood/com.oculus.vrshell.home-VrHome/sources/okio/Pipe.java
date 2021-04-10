package okio;

import java.io.IOException;

public final class Pipe {
    final Buffer buffer = new Buffer();
    final long maxBufferSize;
    private final Sink sink = new PipeSink();
    boolean sinkClosed;
    private final Source source = new PipeSource();
    boolean sourceClosed;

    public Pipe(long maxBufferSize2) {
        if (maxBufferSize2 < 1) {
            throw new IllegalArgumentException("maxBufferSize < 1: " + maxBufferSize2);
        }
        this.maxBufferSize = maxBufferSize2;
    }

    public Source source() {
        return this.source;
    }

    public Sink sink() {
        return this.sink;
    }

    final class PipeSink implements Sink {
        final Timeout timeout = new Timeout();

        PipeSink() {
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 126
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
            */
        @Override // okio.Sink
        public void write(okio.Buffer r13, long r14) throws java.io.IOException {
            /*
                r12 = this;
                r10 = 0
                okio.Pipe r4 = okio.Pipe.this
                okio.Buffer r5 = r4.buffer
                monitor-enter(r5)
                okio.Pipe r4 = okio.Pipe.this     // Catch:{ all -> 0x0015 }
                boolean r4 = r4.sinkClosed     // Catch:{ all -> 0x0015 }
                if (r4 == 0) goto L_0x0033
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0015 }
                java.lang.String r6 = "closed"
                r4.<init>(r6)     // Catch:{ all -> 0x0015 }
                throw r4     // Catch:{ all -> 0x0015 }
            L_0x0015:
                r4 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0015 }
                throw r4
            L_0x0018:
                okio.Pipe r4 = okio.Pipe.this
                long r6 = r4.maxBufferSize
                okio.Pipe r4 = okio.Pipe.this
                okio.Buffer r4 = r4.buffer
                long r8 = r4.size()
                long r0 = r6 - r8
                int r4 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
                if (r4 != 0) goto L_0x0045
                okio.Timeout r4 = r12.timeout
                okio.Pipe r6 = okio.Pipe.this
                okio.Buffer r6 = r6.buffer
                r4.waitUntilNotified(r6)
            L_0x0033:
                int r4 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
                if (r4 <= 0) goto L_0x0059
                okio.Pipe r4 = okio.Pipe.this
                boolean r4 = r4.sourceClosed
                if (r4 == 0) goto L_0x0018
                java.io.IOException r4 = new java.io.IOException
                java.lang.String r6 = "source is closed"
                r4.<init>(r6)
                throw r4
            L_0x0045:
                long r2 = java.lang.Math.min(r0, r14)
                okio.Pipe r4 = okio.Pipe.this
                okio.Buffer r4 = r4.buffer
                r4.write(r13, r2)
                long r14 = r14 - r2
                okio.Pipe r4 = okio.Pipe.this
                okio.Buffer r4 = r4.buffer
                r4.notifyAll()
                goto L_0x0033
            L_0x0059:
                monitor-exit(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.PipeSink.write(okio.Buffer, long):void");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Pipe.this.buffer) {
                if (Pipe.this.sinkClosed) {
                    throw new IllegalStateException("closed");
                } else if (Pipe.this.sourceClosed && Pipe.this.buffer.size() > 0) {
                    throw new IOException("source is closed");
                }
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sinkClosed) {
                    if (!Pipe.this.sourceClosed || Pipe.this.buffer.size() <= 0) {
                        Pipe.this.sinkClosed = true;
                        Pipe.this.buffer.notifyAll();
                        return;
                    }
                    throw new IOException("source is closed");
                }
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }
    }

    final class PipeSource implements Source {
        final Timeout timeout = new Timeout();

        PipeSource() {
        }

        @Override // okio.Source
        public long read(Buffer sink, long byteCount) throws IOException {
            long read;
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sourceClosed) {
                    while (true) {
                        if (Pipe.this.buffer.size() != 0) {
                            read = Pipe.this.buffer.read(sink, byteCount);
                            Pipe.this.buffer.notifyAll();
                            break;
                        } else if (Pipe.this.sinkClosed) {
                            read = -1;
                            break;
                        } else {
                            this.timeout.waitUntilNotified(Pipe.this.buffer);
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
            return read;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe.this.sourceClosed = true;
                Pipe.this.buffer.notifyAll();
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }
}
