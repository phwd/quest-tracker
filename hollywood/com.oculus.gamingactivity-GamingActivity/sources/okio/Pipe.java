package okio;

import java.io.IOException;
import javax.annotation.Nullable;

public final class Pipe {
    final Buffer buffer = new Buffer();
    @Nullable
    private Sink foldedSink;
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

    public final Source source() {
        return this.source;
    }

    public final Sink sink() {
        return this.sink;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r9.write(r1, r1.size);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r0 == false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        if (1 != 0) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
        r4 = r8.buffer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r8.sourceClosed = true;
        r8.buffer.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0051, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0056, code lost:
        r9.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005b, code lost:
        if (0 == 0) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005f, code lost:
        monitor-enter(r8.buffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r8.sourceClosed = true;
        r8.buffer.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0069, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fold(okio.Sink r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.fold(okio.Sink):void");
    }

    final class PipeSink implements Sink {
        final PushableTimeout timeout = new PushableTimeout();

        PipeSink() {
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 138
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
            */
        @Override // okio.Sink
        public void write(okio.Buffer r13, long r14) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 134
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.PipeSink.write(okio.Buffer, long):void");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            Sink delegate = null;
            synchronized (Pipe.this.buffer) {
                if (Pipe.this.sinkClosed) {
                    throw new IllegalStateException("closed");
                } else if (Pipe.this.foldedSink != null) {
                    delegate = Pipe.this.foldedSink;
                } else if (Pipe.this.sourceClosed && Pipe.this.buffer.size() > 0) {
                    throw new IOException("source is closed");
                }
            }
            if (delegate != null) {
                this.timeout.push(delegate.timeout());
                try {
                    delegate.flush();
                } finally {
                    this.timeout.pop();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
            r8.timeout.push(r0.timeout());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
            r8.timeout.pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            return;
         */
        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r8 = this;
                r0 = 0
                okio.Pipe r1 = okio.Pipe.this
                okio.Buffer r2 = r1.buffer
                monitor-enter(r2)
                okio.Pipe r1 = okio.Pipe.this     // Catch:{ all -> 0x004d }
                boolean r1 = r1.sinkClosed     // Catch:{ all -> 0x004d }
                if (r1 == 0) goto L_0x000e
                monitor-exit(r2)     // Catch:{ all -> 0x004d }
            L_0x000d:
                return
            L_0x000e:
                okio.Pipe r1 = okio.Pipe.this     // Catch:{ all -> 0x004d }
                okio.Sink r1 = okio.Pipe.access$000(r1)     // Catch:{ all -> 0x004d }
                if (r1 == 0) goto L_0x0031
                okio.Pipe r1 = okio.Pipe.this     // Catch:{ all -> 0x004d }
                okio.Sink r0 = okio.Pipe.access$000(r1)     // Catch:{ all -> 0x004d }
            L_0x001c:
                monitor-exit(r2)     // Catch:{ all -> 0x004d }
                if (r0 == 0) goto L_0x000d
                okio.PushableTimeout r1 = r8.timeout
                okio.Timeout r2 = r0.timeout()
                r1.push(r2)
                r0.close()     // Catch:{ all -> 0x005d }
                okio.PushableTimeout r1 = r8.timeout
                r1.pop()
                goto L_0x000d
            L_0x0031:
                okio.Pipe r1 = okio.Pipe.this
                boolean r1 = r1.sourceClosed
                if (r1 == 0) goto L_0x0050
                okio.Pipe r1 = okio.Pipe.this
                okio.Buffer r1 = r1.buffer
                long r4 = r1.size()
                r6 = 0
                int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r1 <= 0) goto L_0x0050
                java.io.IOException r1 = new java.io.IOException
                java.lang.String r3 = "source is closed"
                r1.<init>(r3)
                throw r1
            L_0x004d:
                r1 = move-exception
                monitor-exit(r2)
                throw r1
            L_0x0050:
                okio.Pipe r1 = okio.Pipe.this
                r3 = 1
                r1.sinkClosed = r3
                okio.Pipe r1 = okio.Pipe.this
                okio.Buffer r1 = r1.buffer
                r1.notifyAll()
                goto L_0x001c
            L_0x005d:
                r1 = move-exception
                okio.PushableTimeout r2 = r8.timeout
                r2.pop()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.PipeSink.close():void");
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
