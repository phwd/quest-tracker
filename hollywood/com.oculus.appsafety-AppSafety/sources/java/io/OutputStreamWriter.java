package java.io;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import sun.nio.cs.StreamEncoder;

public class OutputStreamWriter extends Writer {
    private final StreamEncoder se;

    public OutputStreamWriter(OutputStream out, String charsetName) throws UnsupportedEncodingException {
        super(out);
        if (charsetName != null) {
            this.se = StreamEncoder.forOutputStreamWriter(out, this, charsetName);
            return;
        }
        throw new NullPointerException("charsetName");
    }

    public OutputStreamWriter(OutputStream out) {
        super(out);
        try {
            this.se = StreamEncoder.forOutputStreamWriter(out, this, (String) null);
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public OutputStreamWriter(OutputStream out, Charset cs) {
        super(out);
        if (cs != null) {
            this.se = StreamEncoder.forOutputStreamWriter(out, this, cs);
            return;
        }
        throw new NullPointerException("charset");
    }

    public OutputStreamWriter(OutputStream out, CharsetEncoder enc) {
        super(out);
        if (enc != null) {
            this.se = StreamEncoder.forOutputStreamWriter(out, this, enc);
            return;
        }
        throw new NullPointerException("charset encoder");
    }

    public String getEncoding() {
        return this.se.getEncoding();
    }

    /* access modifiers changed from: package-private */
    public void flushBuffer() throws IOException {
        this.se.flushBuffer();
    }

    @Override // java.io.Writer
    public void write(int c) throws IOException {
        this.se.write(c);
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.se.write(cbuf, off, len);
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) throws IOException {
        this.se.write(str, off, len);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.se.flush();
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() throws IOException {
        this.se.close();
    }
}
