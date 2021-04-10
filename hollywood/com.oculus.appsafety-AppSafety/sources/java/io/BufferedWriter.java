package java.io;

import java.security.AccessController;
import sun.security.action.GetPropertyAction;

public class BufferedWriter extends Writer {
    private static int defaultCharBufferSize = 8192;
    private char[] cb;
    private String lineSeparator;
    private int nChars;
    private int nextChar;
    private Writer out;

    public BufferedWriter(Writer out2) {
        this(out2, defaultCharBufferSize);
    }

    public BufferedWriter(Writer out2, int sz) {
        super(out2);
        if (sz > 0) {
            this.out = out2;
            this.cb = new char[sz];
            this.nChars = sz;
            this.nextChar = 0;
            this.lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    private void ensureOpen() throws IOException {
        if (this.out == null) {
            throw new IOException("Stream closed");
        }
    }

    /* access modifiers changed from: package-private */
    public void flushBuffer() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.nextChar != 0) {
                this.out.write(this.cb, 0, this.nextChar);
                this.nextChar = 0;
            }
        }
    }

    @Override // java.io.Writer
    public void write(int c) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.nextChar >= this.nChars) {
                flushBuffer();
            }
            char[] cArr = this.cb;
            int i = this.nextChar;
            this.nextChar = i + 1;
            cArr[i] = (char) c;
        }
    }

    private int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            } else if (len != 0) {
                if (len >= this.nChars) {
                    flushBuffer();
                    this.out.write(cbuf, off, len);
                    return;
                }
                int b = off;
                int t = off + len;
                while (b < t) {
                    int d = min(this.nChars - this.nextChar, t - b);
                    System.arraycopy((Object) cbuf, b, (Object) this.cb, this.nextChar, d);
                    b += d;
                    this.nextChar += d;
                    if (this.nextChar >= this.nChars) {
                        flushBuffer();
                    }
                }
            }
        }
    }

    @Override // java.io.Writer
    public void write(String s, int off, int len) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            int b = off;
            int t = off + len;
            while (b < t) {
                int d = min(this.nChars - this.nextChar, t - b);
                s.getChars(b, b + d, this.cb, this.nextChar);
                b += d;
                this.nextChar += d;
                if (this.nextChar >= this.nChars) {
                    flushBuffer();
                }
            }
        }
    }

    public void newLine() throws IOException {
        write(this.lineSeparator);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        synchronized (this.lock) {
            flushBuffer();
            this.out.flush();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x001e, code lost:
        if (r2 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0024, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0025, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0028, code lost:
        throw r4;
     */
    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r6 = this;
            java.lang.Object r0 = r6.lock
            monitor-enter(r0)
            java.io.Writer r1 = r6.out     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x0009:
            r1 = 0
            java.io.Writer r2 = r6.out     // Catch:{ all -> 0x0029 }
            r6.flushBuffer()     // Catch:{ all -> 0x001b }
            if (r2 == 0) goto L_0x0014
            r2.close()
        L_0x0014:
            r6.out = r1
            r6.cb = r1
            monitor-exit(r0)
            return
        L_0x001b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001d }
        L_0x001d:
            r4 = move-exception
            if (r2 == 0) goto L_0x0028
            r2.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0028:
            throw r4
        L_0x0029:
            r2 = move-exception
            r6.out = r1
            r6.cb = r1
            throw r2
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.BufferedWriter.close():void");
    }
}
