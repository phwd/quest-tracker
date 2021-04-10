package sun.nio.cs;

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

public class StreamDecoder extends Reader {
    private ByteBuffer bb;
    private ReadableByteChannel ch;
    private Charset cs;
    private CharsetDecoder decoder;
    private boolean haveLeftoverChar;
    private InputStream in;
    private volatile boolean isOpen;
    private char leftoverChar;
    private boolean needsFlush;

    private void ensureOpen() {
        if (!this.isOpen) {
            throw new IOException("Stream closed");
        }
    }

    public static StreamDecoder forInputStreamReader(InputStream inputStream, Object obj, String str) {
        if (str == null) {
            str = Charset.defaultCharset().name();
        }
        try {
            if (Charset.isSupported(str)) {
                return new StreamDecoder(inputStream, obj, Charset.forName(str));
            }
        } catch (IllegalCharsetNameException unused) {
        }
        throw new UnsupportedEncodingException(str);
    }

    public static StreamDecoder forInputStreamReader(InputStream inputStream, Object obj, Charset charset) {
        return new StreamDecoder(inputStream, obj, charset);
    }

    private int read0() {
        synchronized (this.lock) {
            if (this.haveLeftoverChar) {
                this.haveLeftoverChar = false;
                return this.leftoverChar;
            }
            char[] cArr = new char[2];
            int read = read(cArr, 0, 2);
            if (read == -1) {
                return -1;
            }
            if (read != 1) {
                if (read != 2) {
                    return -1;
                }
                this.leftoverChar = cArr[1];
                this.haveLeftoverChar = true;
            }
            return cArr[0];
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) {
        int i3;
        synchronized (this.lock) {
            ensureOpen();
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            }
            int i4 = 0;
            if (i2 == 0) {
                return 0;
            }
            if (this.haveLeftoverChar) {
                cArr[i] = this.leftoverChar;
                i++;
                i2--;
                this.haveLeftoverChar = false;
                if (i2 != 0) {
                    if (implReady()) {
                        i4 = 1;
                    }
                }
                return 1;
            }
            if (i2 != 1) {
                return i4 + implRead(cArr, i, i2 + i);
            }
            int read0 = read0();
            int i5 = -1;
            if (read0 == -1) {
                if (i4 != 0) {
                    i5 = i4;
                }
                return i5;
            }
            cArr[i] = (char) read0;
            return i4 + 1;
        }
    }

    @Override // java.io.Reader
    public boolean ready() {
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
    public void close() {
        synchronized (this.lock) {
            if (this.isOpen) {
                implClose();
                this.isOpen = false;
            }
        }
    }

    StreamDecoder(InputStream inputStream, Object obj, Charset charset) {
        this(inputStream, obj, charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE));
    }

    StreamDecoder(InputStream inputStream, Object obj, CharsetDecoder charsetDecoder) {
        super(obj);
        this.isOpen = true;
        this.haveLeftoverChar = false;
        this.needsFlush = false;
        this.cs = charsetDecoder.charset();
        this.decoder = charsetDecoder;
        if (this.ch == null) {
            this.in = inputStream;
            this.ch = null;
            this.bb = ByteBuffer.allocate(8192);
        }
        this.bb.flip();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r0 < 0) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int readBytes() {
        /*
            r5 = this;
            java.nio.ByteBuffer r0 = r5.bb
            r0.compact()
            java.nio.channels.ReadableByteChannel r0 = r5.ch     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x001a
            java.nio.channels.ReadableByteChannel r0 = r5.ch     // Catch:{ all -> 0x005d }
            java.nio.ByteBuffer r1 = r5.bb     // Catch:{ all -> 0x005d }
            r2 = 1
            int r0 = sun.nio.ch.ChannelInputStream.read(r0, r1, r2)     // Catch:{ all -> 0x005d }
            if (r0 >= 0) goto L_0x0049
        L_0x0014:
            java.nio.ByteBuffer r5 = r5.bb
            r5.flip()
            return r0
        L_0x001a:
            java.nio.ByteBuffer r0 = r5.bb
            int r0 = r0.limit()
            java.nio.ByteBuffer r1 = r5.bb
            int r1 = r1.position()
            if (r1 > r0) goto L_0x002a
            int r0 = r0 - r1
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            java.io.InputStream r2 = r5.in
            java.nio.ByteBuffer r3 = r5.bb
            byte[] r3 = r3.array()
            java.nio.ByteBuffer r4 = r5.bb
            int r4 = r4.arrayOffset()
            int r4 = r4 + r1
            int r0 = r2.read(r3, r4, r0)
            if (r0 >= 0) goto L_0x0041
            goto L_0x0014
        L_0x0041:
            if (r0 == 0) goto L_0x0055
            java.nio.ByteBuffer r2 = r5.bb
            int r1 = r1 + r0
            r2.position(r1)
        L_0x0049:
            java.nio.ByteBuffer r0 = r5.bb
            r0.flip()
            java.nio.ByteBuffer r5 = r5.bb
            int r5 = r5.remaining()
            return r5
        L_0x0055:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Underlying input stream returned zero bytes"
            r0.<init>(r1)
            throw r0
        L_0x005d:
            r0 = move-exception
            java.nio.ByteBuffer r5 = r5.bb
            r5.flip()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.cs.StreamDecoder.readBytes():int");
    }

    /* access modifiers changed from: package-private */
    public int implRead(char[] cArr, int i, int i2) {
        CharBuffer wrap = CharBuffer.wrap(cArr, i, i2 - i);
        if (wrap.position() != 0) {
            wrap = wrap.slice();
        }
        if (this.needsFlush) {
            CoderResult flush = this.decoder.flush(wrap);
            if (flush.isOverflow()) {
                return wrap.position();
            }
            if (!flush.isUnderflow()) {
                flush.throwException();
            } else if (wrap.position() == 0) {
                return -1;
            } else {
                return wrap.position();
            }
        }
        boolean z = false;
        while (true) {
            CoderResult decode = this.decoder.decode(this.bb, wrap, z);
            if (decode.isUnderflow()) {
                if (z || !wrap.hasRemaining() || (wrap.position() > 0 && !inReady())) {
                    break;
                } else if (readBytes() < 0) {
                    z = true;
                }
            } else if (decode.isOverflow()) {
                break;
            } else {
                decode.throwException();
            }
        }
        if (z) {
            CoderResult flush2 = this.decoder.flush(wrap);
            if (flush2.isOverflow()) {
                this.needsFlush = true;
                return wrap.position();
            }
            this.decoder.reset();
            if (!flush2.isUnderflow()) {
                flush2.throwException();
            }
        }
        if (wrap.position() != 0 || !z) {
            return wrap.position();
        }
        return -1;
    }

    private boolean inReady() {
        try {
            return (this.in != null && this.in.available() > 0) || (this.ch instanceof FileChannel);
        } catch (IOException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean implReady() {
        return this.bb.hasRemaining() || inReady();
    }

    /* access modifiers changed from: package-private */
    public void implClose() {
        ReadableByteChannel readableByteChannel = this.ch;
        if (readableByteChannel != null) {
            readableByteChannel.close();
        } else {
            this.in.close();
        }
    }
}
