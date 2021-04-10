package java.io;

public class FilterOutputStream extends OutputStream {
    protected OutputStream out;

    @Override // java.io.OutputStream
    public void flush() {
        throw null;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        throw null;
    }

    public FilterOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0015, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (r0 != null) goto L_0x0010;
     */
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r2 = this;
            java.io.OutputStream r0 = r2.out
            r2.flush()     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x000a
            r0.close()
        L_0x000a:
            return
        L_0x000b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x000d }
        L_0x000d:
            r1 = move-exception
            if (r0 == 0) goto L_0x0018
            r0.close()     // Catch:{ all -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0018:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.FilterOutputStream.close():void");
    }
}
