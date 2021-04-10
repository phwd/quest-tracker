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

    private int min(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public BufferedWriter(Writer writer) {
        this(writer, defaultCharBufferSize);
    }

    public BufferedWriter(Writer writer, int i) {
        super(writer);
        if (i > 0) {
            this.out = writer;
            this.cb = new char[i];
            this.nChars = i;
            this.nextChar = 0;
            this.lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    private void ensureOpen() {
        if (this.out == null) {
            throw new IOException("Stream closed");
        }
    }

    /* access modifiers changed from: package-private */
    public void flushBuffer() {
        synchronized (this.lock) {
            ensureOpen();
            if (this.nextChar != 0) {
                this.out.write(this.cb, 0, this.nextChar);
                this.nextChar = 0;
            }
        }
    }

    @Override // java.io.Writer
    public void write(int i) {
        synchronized (this.lock) {
            ensureOpen();
            if (this.nextChar >= this.nChars) {
                flushBuffer();
            }
            char[] cArr = this.cb;
            int i2 = this.nextChar;
            this.nextChar = i2 + 1;
            cArr[i2] = (char) i;
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        synchronized (this.lock) {
            ensureOpen();
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                if (i2 >= this.nChars) {
                    flushBuffer();
                    this.out.write(cArr, i, i2);
                    return;
                }
                while (i < i3) {
                    int min = min(this.nChars - this.nextChar, i3 - i);
                    System.arraycopy((Object) cArr, i, (Object) this.cb, this.nextChar, min);
                    i += min;
                    this.nextChar += min;
                    if (this.nextChar >= this.nChars) {
                        flushBuffer();
                    }
                }
            }
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        synchronized (this.lock) {
            ensureOpen();
            int i3 = i2 + i;
            while (i < i3) {
                int min = min(this.nChars - this.nextChar, i3 - i);
                int i4 = i + min;
                str.getChars(i, i4, this.cb, this.nextChar);
                this.nextChar += min;
                if (this.nextChar >= this.nChars) {
                    flushBuffer();
                }
                i = i4;
            }
        }
    }

    public void newLine() {
        write(this.lineSeparator);
    }

    @Override // java.io.Writer
    public void flush() {
        synchronized (this.lock) {
            flushBuffer();
            this.out.flush();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x001d, code lost:
        if (r2 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0023, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0024, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0027, code lost:
        throw r4;
     */
    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            java.io.Writer r1 = r5.out     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0009:
            r1 = 0
            java.io.Writer r2 = r5.out     // Catch:{ all -> 0x0028 }
            r5.flushBuffer()     // Catch:{ all -> 0x001a }
            if (r2 == 0) goto L_0x0014
            r2.close()
        L_0x0014:
            r5.out = r1
            r5.cb = r1
            monitor-exit(r0)
            return
        L_0x001a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001c }
        L_0x001c:
            r4 = move-exception
            if (r2 == 0) goto L_0x0027
            r2.close()     // Catch:{ all -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0027:
            throw r4
        L_0x0028:
            r2 = move-exception
            r5.out = r1
            r5.cb = r1
            throw r2
        L_0x002e:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.BufferedWriter.close():void");
    }
}
