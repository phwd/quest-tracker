package java.io;

public class FilterOutputStream extends OutputStream {
    protected OutputStream out;

    public FilterOutputStream(OutputStream out2) {
        this.out = out2;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        if ((off | len | (b.length - (len + off)) | (off + len)) >= 0) {
            for (int i = 0; i < len; i++) {
                write(b[off + i]);
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0015, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (r0 != null) goto L_0x0010;
     */
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            java.io.OutputStream r0 = r4.out
            r4.flush()     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x000a
            r0.close()
        L_0x000a:
            return
        L_0x000b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000d }
        L_0x000d:
            r2 = move-exception
            if (r0 == 0) goto L_0x0018
            r0.close()     // Catch:{ all -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0018:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.FilterOutputStream.close():void");
    }
}
