package java.io;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import sun.nio.cs.StreamDecoder;

public class InputStreamReader extends Reader {
    private final StreamDecoder sd;

    public InputStreamReader(InputStream in) {
        super(in);
        try {
            this.sd = StreamDecoder.forInputStreamReader(in, this, (String) null);
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public InputStreamReader(InputStream in, String charsetName) throws UnsupportedEncodingException {
        super(in);
        if (charsetName != null) {
            this.sd = StreamDecoder.forInputStreamReader(in, this, charsetName);
            return;
        }
        throw new NullPointerException("charsetName");
    }

    public InputStreamReader(InputStream in, Charset cs) {
        super(in);
        if (cs != null) {
            this.sd = StreamDecoder.forInputStreamReader(in, this, cs);
            return;
        }
        throw new NullPointerException("charset");
    }

    public InputStreamReader(InputStream in, CharsetDecoder dec) {
        super(in);
        if (dec != null) {
            this.sd = StreamDecoder.forInputStreamReader(in, this, dec);
            return;
        }
        throw new NullPointerException("charset decoder");
    }

    public String getEncoding() {
        return this.sd.getEncoding();
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        return this.sd.read();
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int offset, int length) throws IOException {
        return this.sd.read(cbuf, offset, length);
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return this.sd.ready();
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sd.close();
    }
}
