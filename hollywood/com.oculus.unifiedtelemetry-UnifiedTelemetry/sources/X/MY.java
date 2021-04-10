package X;

import com.facebook.acra.LogCatCollector;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class MY extends Writer {
    public final OutputStream A00;
    public final String A01 = LogCatCollector.UTF_8_ENCODING;
    public final boolean A02 = true;

    private boolean A00(char c) throws IOException {
        if (c <= 127) {
            this.A00.write((byte) c);
            return true;
        } else if (!this.A02) {
            this.A00.write(new String(new char[]{c}).getBytes(this.A01));
            return false;
        } else {
            StringBuilder sb = new StringBuilder("Non-ASCII character detected: ");
            sb.append(c);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Deprecated
    public MY(OutputStream outputStream) {
        super(outputStream);
        this.A00 = outputStream;
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        this.A00.flush();
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        A00((char) i);
    }

    @Override // java.io.Writer
    public final void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    @Override // java.io.Writer
    public final void write(String str, int i, int i2) throws IOException {
        if ((i | i2) < 0 || i > str.length() - i2) {
            StringBuilder sb = new StringBuilder("length=");
            sb.append(str.length());
            sb.append("; offset=");
            sb.append(i);
            sb.append("; count=");
            sb.append(i2);
            throw new StringIndexOutOfBoundsException(sb.toString());
        }
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i + 1;
            if (A00(str.charAt(i)) || i2 - i3 <= 0) {
                i3++;
                i = i4;
            } else {
                this.A00.write(str.substring(i4).getBytes(this.A01));
                return;
            }
        }
    }

    @Override // java.io.Writer
    public final void write(char[] cArr) throws IOException {
        for (char c : cArr) {
            A00(c);
        }
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        if ((i | i2) < 0 || i > cArr.length - i2) {
            StringBuilder sb = new StringBuilder("length=");
            sb.append(cArr.length);
            sb.append("; offset=");
            sb.append(i);
            sb.append("; count=");
            sb.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i + 1;
            if (A00(cArr[i]) || i2 - i3 <= 0) {
                i3++;
                i = i4;
            } else {
                this.A00.write(new String(cArr, i4, i2).getBytes(this.A01));
                return;
            }
        }
    }
}
