package java.io;

import java.nio.charset.Charset;
import sun.nio.cs.StreamEncoder;

public class OutputStreamWriter extends Writer {
    private final StreamEncoder se;

    public OutputStreamWriter(OutputStream outputStream) {
        super(outputStream);
        try {
            this.se = StreamEncoder.forOutputStreamWriter(outputStream, this, (String) null);
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public OutputStreamWriter(OutputStream outputStream, Charset charset) {
        super(outputStream);
        if (charset != null) {
            this.se = StreamEncoder.forOutputStreamWriter(outputStream, this, charset);
            return;
        }
        throw new NullPointerException("charset");
    }

    /* access modifiers changed from: package-private */
    public void flushBuffer() {
        this.se.flushBuffer();
    }

    @Override // java.io.Writer
    public void write(int i) {
        this.se.write(i);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        this.se.write(cArr, i, i2);
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        this.se.write(str, i, i2);
    }

    @Override // java.io.Writer
    public void flush() {
        this.se.flush();
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
        this.se.close();
    }
}
