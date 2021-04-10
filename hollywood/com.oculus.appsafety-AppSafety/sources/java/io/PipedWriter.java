package java.io;

public class PipedWriter extends Writer {
    private boolean closed = false;
    private PipedReader sink;

    public PipedWriter(PipedReader snk) throws IOException {
        connect(snk);
    }

    public PipedWriter() {
    }

    public synchronized void connect(PipedReader snk) throws IOException {
        if (snk == null) {
            throw new NullPointerException();
        } else if (this.sink != null || snk.connected) {
            throw new IOException("Already connected");
        } else if (snk.closedByReader || this.closed) {
            throw new IOException("Pipe closed");
        } else {
            this.sink = snk;
            snk.in = -1;
            snk.out = 0;
            snk.connected = true;
        }
    }

    @Override // java.io.Writer
    public void write(int c) throws IOException {
        PipedReader pipedReader = this.sink;
        if (pipedReader != null) {
            pipedReader.receive(c);
            return;
        }
        throw new IOException("Pipe not connected");
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        PipedReader pipedReader = this.sink;
        if (pipedReader == null) {
            throw new IOException("Pipe not connected");
        } else if ((off | len | (off + len) | (cbuf.length - (off + len))) >= 0) {
            pipedReader.receive(cbuf, off, len);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
        r1 = th;
     */
    @Override // java.io.Writer, java.io.Flushable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void flush() throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.io.PipedReader r0 = r2.sink     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0026
            java.io.PipedReader r0 = r2.sink     // Catch:{ all -> 0x0028 }
            boolean r0 = r0.closedByReader     // Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x001e
            boolean r0 = r2.closed     // Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x001e
            java.io.PipedReader r0 = r2.sink     // Catch:{ all -> 0x0028 }
            monitor-enter(r0)     // Catch:{ all -> 0x0028 }
            java.io.PipedReader r1 = r2.sink     // Catch:{ all -> 0x0019 }
            r1.notifyAll()     // Catch:{ all -> 0x0019 }
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            goto L_0x0026
        L_0x0019:
            r1 = move-exception
        L_0x001a:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r1
        L_0x001c:
            r1 = move-exception
            goto L_0x001a
        L_0x001e:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Pipe closed"
            r0.<init>(r1)
            throw r0
        L_0x0026:
            monitor-exit(r2)
            return
        L_0x0028:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.PipedWriter.flush():void");
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        PipedReader pipedReader = this.sink;
        if (pipedReader != null) {
            pipedReader.receivedLast();
        }
    }
}
