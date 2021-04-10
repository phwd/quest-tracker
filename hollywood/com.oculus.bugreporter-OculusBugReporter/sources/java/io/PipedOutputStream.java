package java.io;

public class PipedOutputStream extends OutputStream {
    private PipedInputStream sink;

    public PipedOutputStream(PipedInputStream snk) throws IOException {
        connect(snk);
    }

    public PipedOutputStream() {
    }

    public synchronized void connect(PipedInputStream snk) throws IOException {
        if (snk == null) {
            throw new NullPointerException();
        } else if (this.sink != null || snk.connected) {
            throw new IOException("Already connected");
        } else {
            this.sink = snk;
            snk.in = -1;
            snk.out = 0;
            snk.connected = true;
        }
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        PipedInputStream pipedInputStream = this.sink;
        if (pipedInputStream != null) {
            pipedInputStream.receive(b);
            return;
        }
        throw new IOException("Pipe not connected");
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        PipedInputStream pipedInputStream = this.sink;
        if (pipedInputStream == null) {
            throw new IOException("Pipe not connected");
        } else if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            pipedInputStream.receive(b, off, len);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0012, code lost:
        r1 = th;
     */
    @Override // java.io.OutputStream, java.io.Flushable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void flush() throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.io.PipedInputStream r0 = r2.sink     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0014
            java.io.PipedInputStream r0 = r2.sink     // Catch:{ all -> 0x0016 }
            monitor-enter(r0)     // Catch:{ all -> 0x0016 }
            java.io.PipedInputStream r1 = r2.sink     // Catch:{ all -> 0x000f }
            r1.notifyAll()     // Catch:{ all -> 0x000f }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            goto L_0x0014
        L_0x000f:
            r1 = move-exception
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x0012 }
            throw r1
        L_0x0012:
            r1 = move-exception
            goto L_0x0010
        L_0x0014:
            monitor-exit(r2)
            return
        L_0x0016:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.PipedOutputStream.flush():void");
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        PipedInputStream pipedInputStream = this.sink;
        if (pipedInputStream != null) {
            pipedInputStream.receivedLast();
        }
    }
}
