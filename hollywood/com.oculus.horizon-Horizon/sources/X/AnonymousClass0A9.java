package X;

import com.facebook.FacebookSdk;
import java.io.Writer;

/* renamed from: X.0A9  reason: invalid class name */
public final class AnonymousClass0A9 extends Writer {
    public StringBuilder A00 = new StringBuilder((int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                A00();
            } else {
                this.A00.append(c);
            }
        }
    }

    private void A00() {
        StringBuilder sb = this.A00;
        if (sb.length() > 0) {
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
        A00();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        A00();
    }
}
