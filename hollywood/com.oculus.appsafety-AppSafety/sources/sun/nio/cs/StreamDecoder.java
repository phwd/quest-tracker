package sun.nio.cs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import sun.nio.ch.ChannelInputStream;

public class StreamDecoder extends Reader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DEFAULT_BYTE_BUFFER_SIZE = 8192;
    private static final int MIN_BYTE_BUFFER_SIZE = 32;
    private static volatile boolean channelsAvailable = true;
    private ByteBuffer bb;
    private ReadableByteChannel ch;
    private Charset cs;
    private CharsetDecoder decoder;
    private boolean haveLeftoverChar;
    private InputStream in;
    private volatile boolean isOpen;
    private char leftoverChar;
    private boolean needsFlush;

    private void ensureOpen() throws IOException {
        if (!this.isOpen) {
            throw new IOException("Stream closed");
        }
    }

    public static StreamDecoder forInputStreamReader(InputStream in2, Object lock, String charsetName) throws UnsupportedEncodingException {
        String csn = charsetName;
        if (csn == null) {
            csn = Charset.defaultCharset().name();
        }
        try {
            if (Charset.isSupported(csn)) {
                return new StreamDecoder(in2, lock, Charset.forName(csn));
            }
        } catch (IllegalCharsetNameException e) {
        }
        throw new UnsupportedEncodingException(csn);
    }

    public static StreamDecoder forInputStreamReader(InputStream in2, Object lock, Charset cs2) {
        return new StreamDecoder(in2, lock, cs2);
    }

    public static StreamDecoder forInputStreamReader(InputStream in2, Object lock, CharsetDecoder dec) {
        return new StreamDecoder(in2, lock, dec);
    }

    public static StreamDecoder forDecoder(ReadableByteChannel ch2, CharsetDecoder dec, int minBufferCap) {
        return new StreamDecoder(ch2, dec, minBufferCap);
    }

    public String getEncoding() {
        if (isOpen()) {
            return encodingName();
        }
        return null;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        return read0();
    }

    private int read0() throws IOException {
        synchronized (this.lock) {
            if (this.haveLeftoverChar) {
                this.haveLeftoverChar = false;
                return this.leftoverChar;
            }
            char[] cb = new char[2];
            int n = read(cb, 0, 2);
            if (n == -1) {
                return -1;
            }
            if (n != 1) {
                if (n != 2) {
                    return -1;
                }
                this.leftoverChar = cb[1];
                this.haveLeftoverChar = true;
            }
            return cb[0];
        }
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int offset, int length) throws IOException {
        int off = offset;
        int len = length;
        synchronized (this.lock) {
            ensureOpen();
            if (off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            } else {
                int n = 0;
                if (this.haveLeftoverChar) {
                    cbuf[off] = this.leftoverChar;
                    off++;
                    len--;
                    this.haveLeftoverChar = false;
                    n = 1;
                    if (len == 0 || !implReady()) {
                        return 1;
                    }
                }
                if (len == 1) {
                    int c = read0();
                    int i = -1;
                    if (c == -1) {
                        if (n != 0) {
                            i = n;
                        }
                        return i;
                    }
                    cbuf[off] = (char) c;
                    return n + 1;
                }
                return implRead(cbuf, off, off + len) + n;
            }
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (this.lock) {
            ensureOpen();
            if (!this.haveLeftoverChar) {
                if (!implReady()) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.isOpen) {
                implClose();
                this.isOpen = false;
            }
        }
    }

    private boolean isOpen() {
        return this.isOpen;
    }

    private static FileChannel getChannel(FileInputStream in2) {
        if (!channelsAvailable) {
            return null;
        }
        try {
            return in2.getChannel();
        } catch (UnsatisfiedLinkError e) {
            channelsAvailable = false;
            return null;
        }
    }

    StreamDecoder(InputStream in2, Object lock, Charset cs2) {
        this(in2, lock, cs2.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE));
    }

    StreamDecoder(InputStream in2, Object lock, CharsetDecoder dec) {
        super(lock);
        this.isOpen = true;
        this.haveLeftoverChar = false;
        this.needsFlush = false;
        this.cs = dec.charset();
        this.decoder = dec;
        if (this.ch == null) {
            this.in = in2;
            this.ch = null;
            this.bb = ByteBuffer.allocate(8192);
        }
        this.bb.flip();
    }

    StreamDecoder(ReadableByteChannel ch2, CharsetDecoder dec, int mbc) {
        this.isOpen = true;
        this.haveLeftoverChar = false;
        this.needsFlush = false;
        this.in = null;
        this.ch = ch2;
        this.decoder = dec;
        this.cs = dec.charset();
        int i = 32;
        if (mbc < 0) {
            i = 8192;
        } else if (mbc >= 32) {
            i = mbc;
        }
        this.bb = ByteBuffer.allocate(i);
        this.bb.flip();
    }

    private int readBytes() throws IOException {
        this.bb.compact();
        try {
            if (this.ch != null) {
                int n = ChannelInputStream.read(this.ch, this.bb, true);
                if (n < 0) {
                    return n;
                }
            } else {
                int lim = this.bb.limit();
                int pos = this.bb.position();
                int n2 = this.in.read(this.bb.array(), this.bb.arrayOffset() + pos, pos <= lim ? lim - pos : 0);
                if (n2 < 0) {
                    this.bb.flip();
                    return n2;
                } else if (n2 != 0) {
                    this.bb.position(pos + n2);
                } else {
                    throw new IOException("Underlying input stream returned zero bytes");
                }
            }
            this.bb.flip();
            return this.bb.remaining();
        } finally {
            this.bb.flip();
        }
    }

    /* access modifiers changed from: package-private */
    public int implRead(char[] cbuf, int off, int end) throws IOException {
        CharBuffer cb = CharBuffer.wrap(cbuf, off, end - off);
        if (cb.position() != 0) {
            cb = cb.slice();
        }
        if (this.needsFlush) {
            CoderResult cr = this.decoder.flush(cb);
            if (cr.isOverflow()) {
                return cb.position();
            }
            if (!cr.isUnderflow()) {
                cr.throwException();
            } else if (cb.position() == 0) {
                return -1;
            } else {
                return cb.position();
            }
        }
        boolean eof = false;
        while (true) {
            CoderResult cr2 = this.decoder.decode(this.bb, cb, eof);
            if (cr2.isUnderflow()) {
                if (eof || !cb.hasRemaining() || (cb.position() > 0 && !inReady())) {
                    break;
                } else if (readBytes() < 0) {
                    eof = true;
                }
            } else if (cr2.isOverflow()) {
                break;
            } else {
                cr2.throwException();
            }
        }
        if (eof) {
            CoderResult cr3 = this.decoder.flush(cb);
            if (cr3.isOverflow()) {
                this.needsFlush = true;
                return cb.position();
            }
            this.decoder.reset();
            if (!cr3.isUnderflow()) {
                cr3.throwException();
            }
        }
        if (cb.position() != 0 || !eof) {
            return cb.position();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public String encodingName() {
        Charset charset = this.cs;
        if (charset instanceof HistoricallyNamedCharset) {
            return ((HistoricallyNamedCharset) charset).historicalName();
        }
        return charset.name();
    }

    private boolean inReady() {
        try {
            return (this.in != null && this.in.available() > 0) || (this.ch instanceof FileChannel);
        } catch (IOException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean implReady() {
        return this.bb.hasRemaining() || inReady();
    }

    /* access modifiers changed from: package-private */
    public void implClose() throws IOException {
        ReadableByteChannel readableByteChannel = this.ch;
        if (readableByteChannel != null) {
            readableByteChannel.close();
        } else {
            this.in.close();
        }
    }
}
