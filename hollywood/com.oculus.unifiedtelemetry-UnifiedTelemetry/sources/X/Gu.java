package X;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class Gu extends Writer {
    public int A00;
    @Nullable
    public Writer A01;
    @Nullable
    public char[] A02;

    private Writer A00() throws IOException {
        Writer writer = this.A01;
        if (writer != null) {
            return writer;
        }
        throw new IOException("BufferedWriter is closed");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        if (r1 != null) goto L_0x002a;
     */
    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() throws java.io.IOException {
        /*
            r6 = this;
            java.io.Writer r0 = r6.A01
            if (r0 == 0) goto L_0x002c
            java.io.Writer r5 = r6.A00()
            r4 = 0
            java.io.Writer r3 = r6.A00()     // Catch:{ all -> 0x001b }
            int r2 = r6.A00     // Catch:{ all -> 0x001b }
            r1 = 0
            if (r2 <= 0) goto L_0x0017
            char[] r0 = r6.A02     // Catch:{ all -> 0x001b }
            r3.write(r0, r1, r2)     // Catch:{ all -> 0x001b }
        L_0x0017:
            r6.A00 = r1     // Catch:{ all -> 0x001b }
            r1 = r4
            goto L_0x001c
        L_0x001b:
            r1 = move-exception
        L_0x001c:
            r6.A02 = r4
            r5.close()     // Catch:{ all -> 0x0022 }
            goto L_0x002a
        L_0x0022:
            r0 = move-exception
            if (r1 != 0) goto L_0x002a
        L_0x0025:
            r6.A01 = r4
            if (r0 == 0) goto L_0x002c
            throw r0
        L_0x002a:
            r0 = r1
            goto L_0x0025
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Gu.close():void");
    }

    public Gu(Writer writer, char[] cArr) {
        super(writer);
        this.A01 = writer;
        this.A02 = cArr;
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        Writer A002 = A00();
        Writer A003 = A00();
        int i = this.A00;
        if (i > 0) {
            A003.write(this.A02, 0, i);
        }
        this.A00 = 0;
        A002.flush();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        Writer A002 = A00();
        if (cArr != null) {
            C0084Ge.A01(cArr.length, i, i2);
            char[] cArr2 = this.A02;
            int i3 = this.A00;
            if (i3 != 0 || i2 < cArr2.length) {
                int length = cArr2.length - i3;
                if (i2 < length) {
                    length = i2;
                }
                if (length > 0) {
                    System.arraycopy(cArr, i, cArr2, i3, length);
                    i3 = this.A00 + length;
                    this.A00 = i3;
                }
                char[] cArr3 = this.A02;
                int length2 = cArr3.length;
                if (i3 == length2) {
                    A002.write(cArr3, 0, length2);
                    this.A00 = 0;
                    if (i2 > length) {
                        i += length;
                        i2 -= length;
                        char[] cArr4 = this.A02;
                        if (i2 < cArr4.length) {
                            System.arraycopy(cArr, i, cArr4, 0, i2);
                            this.A00 += i2;
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            A002.write(cArr, i, i2);
            return;
        }
        throw new NullPointerException("buffer == null");
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        Writer A002 = A00();
        char[] cArr = this.A02;
        int i2 = this.A00;
        int length = cArr.length;
        if (i2 >= length) {
            A002.write(cArr, 0, length);
            this.A00 = 0;
            i2 = 0;
        }
        this.A00 = i2 + 1;
        cArr[i2] = (char) i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        if (r2 > 0) goto L_0x0029;
     */
    @Override // java.io.Writer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void write(java.lang.String r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            java.io.Writer r5 = r7.A00()
            if (r10 <= 0) goto L_0x0023
            if (r9 < 0) goto L_0x005e
            int r0 = r8.length()
            int r0 = r0 - r10
            if (r9 > r0) goto L_0x005e
            char[] r6 = r7.A02
            int r0 = r7.A00
            r4 = 0
            if (r0 != 0) goto L_0x0024
            int r1 = r6.length
            if (r10 < r1) goto L_0x0024
            char[] r1 = new char[r10]
            int r0 = r9 + r10
            r8.getChars(r9, r0, r1, r4)
            r5.write(r1, r4, r10)
        L_0x0023:
            return
        L_0x0024:
            int r2 = r6.length
            int r2 = r2 - r0
            if (r10 >= r2) goto L_0x0050
            r2 = r10
        L_0x0029:
            int r1 = r9 + r2
            r8.getChars(r9, r1, r6, r0)
            int r0 = r7.A00
            int r0 = r0 + r2
            r7.A00 = r0
        L_0x0033:
            int r3 = r6.length
            if (r0 != r3) goto L_0x0023
            char[] r1 = r7.A02
            int r0 = r1.length
            r5.write(r1, r4, r0)
            r7.A00 = r4
            if (r10 <= r2) goto L_0x0023
            int r9 = r9 + r2
            int r2 = r10 - r2
            if (r2 < r3) goto L_0x0053
            char[] r1 = new char[r10]
            int r0 = r9 + r2
            r8.getChars(r9, r0, r1, r4)
            r5.write(r1, r4, r2)
            return
        L_0x0050:
            if (r2 <= 0) goto L_0x0033
            goto L_0x0029
        L_0x0053:
            int r0 = r9 + r2
            r8.getChars(r9, r0, r6, r4)
            int r0 = r7.A00
            int r0 = r0 + r2
            r7.A00 = r0
            return
        L_0x005e:
            java.lang.StringIndexOutOfBoundsException r0 = X.C0084Ge.A00(r8, r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Gu.write(java.lang.String, int, int):void");
    }
}
