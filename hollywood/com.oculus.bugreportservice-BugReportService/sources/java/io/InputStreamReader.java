package java.io;

import java.nio.charset.Charset;
import sun.nio.cs.StreamDecoder;

public class InputStreamReader extends Reader {
    private final StreamDecoder sd;

    public InputStreamReader(InputStream inputStream) {
        super(inputStream);
        try {
            this.sd = StreamDecoder.forInputStreamReader(inputStream, this, (String) null);
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public InputStreamReader(InputStream inputStream, String str) {
        super(inputStream);
        if (str != null) {
            this.sd = StreamDecoder.forInputStreamReader(inputStream, this, str);
            return;
        }
        throw new NullPointerException("charsetName");
    }

    public InputStreamReader(InputStream inputStream, Charset charset) {
        super(inputStream);
        if (charset != null) {
            this.sd = StreamDecoder.forInputStreamReader(inputStream, this, charset);
            return;
        }
        throw new NullPointerException("charset");
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) {
        return this.sd.read(cArr, i, i2);
    }

    @Override // java.io.Reader
    public boolean ready() {
        return this.sd.ready();
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() {
        this.sd.close();
    }
}
