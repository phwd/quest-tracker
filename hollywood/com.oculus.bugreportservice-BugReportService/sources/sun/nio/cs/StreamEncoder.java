package sun.nio.cs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;

public class StreamEncoder extends Writer {
    private ByteBuffer bb;
    private WritableByteChannel ch;
    private Charset cs;
    private CharsetEncoder encoder;
    private boolean haveLeftoverChar;
    private volatile boolean isOpen;
    private CharBuffer lcb;
    private char leftoverChar;
    private final OutputStream out;

    private void ensureOpen() {
        if (!this.isOpen) {
            throw new IOException("Stream closed");
        }
    }

    public static StreamEncoder forOutputStreamWriter(OutputStream outputStream, Object obj, String str) {
        if (str == null) {
            str = Charset.defaultCharset().name();
        }
        try {
            if (Charset.isSupported(str)) {
                return new StreamEncoder(outputStream, obj, Charset.forName(str));
            }
        } catch (IllegalCharsetNameException unused) {
        }
        throw new UnsupportedEncodingException(str);
    }

    public static StreamEncoder forOutputStreamWriter(OutputStream outputStream, Object obj, Charset charset) {
        return new StreamEncoder(outputStream, obj, charset);
    }

    public void flushBuffer() {
        synchronized (this.lock) {
            if (isOpen()) {
                implFlushBuffer();
            } else {
                throw new IOException("Stream closed");
            }
        }
    }

    @Override // java.io.Writer
    public void write(int i) {
        write(new char[]{(char) i}, 0, 1);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        synchronized (this.lock) {
            ensureOpen();
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                implWrite(cArr, i, i2);
            }
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        if (i2 >= 0) {
            char[] cArr = new char[i2];
            str.getChars(i, i + i2, cArr, 0);
            write(cArr, 0, i2);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.io.Writer
    public void flush() {
        synchronized (this.lock) {
            ensureOpen();
            implFlush();
        }
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
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

    private StreamEncoder(OutputStream outputStream, Object obj, Charset charset) {
        this(outputStream, obj, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE));
    }

    private StreamEncoder(OutputStream outputStream, Object obj, CharsetEncoder charsetEncoder) {
        super(obj);
        this.isOpen = true;
        this.haveLeftoverChar = false;
        this.lcb = null;
        this.out = outputStream;
        this.ch = null;
        this.cs = charsetEncoder.charset();
        this.encoder = charsetEncoder;
        if (this.ch == null) {
            this.bb = ByteBuffer.allocate(8192);
        }
    }

    private void writeBytes() {
        this.bb.flip();
        int limit = this.bb.limit();
        int position = this.bb.position();
        int i = position <= limit ? limit - position : 0;
        if (i > 0) {
            WritableByteChannel writableByteChannel = this.ch;
            if (writableByteChannel != null) {
                writableByteChannel.write(this.bb);
            } else {
                this.out.write(this.bb.array(), this.bb.arrayOffset() + position, i);
            }
        }
        this.bb.clear();
    }

    private void flushLeftoverChar(CharBuffer charBuffer, boolean z) {
        if (this.haveLeftoverChar || z) {
            CharBuffer charBuffer2 = this.lcb;
            if (charBuffer2 == null) {
                this.lcb = CharBuffer.allocate(2);
            } else {
                charBuffer2.clear();
            }
            if (this.haveLeftoverChar) {
                this.lcb.put(this.leftoverChar);
            }
            if (charBuffer != null && charBuffer.hasRemaining()) {
                this.lcb.put(charBuffer.get());
            }
            this.lcb.flip();
            while (true) {
                if (!this.lcb.hasRemaining() && !z) {
                    break;
                }
                CoderResult encode = this.encoder.encode(this.lcb, this.bb, z);
                if (encode.isUnderflow()) {
                    if (this.lcb.hasRemaining()) {
                        this.leftoverChar = this.lcb.get();
                        if (charBuffer != null && charBuffer.hasRemaining()) {
                            flushLeftoverChar(charBuffer, z);
                            return;
                        }
                        return;
                    }
                } else if (encode.isOverflow()) {
                    writeBytes();
                } else {
                    encode.throwException();
                }
            }
            this.haveLeftoverChar = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void implWrite(char[] cArr, int i, int i2) {
        CharBuffer wrap = CharBuffer.wrap(cArr, i, i2);
        if (this.haveLeftoverChar) {
            flushLeftoverChar(wrap, false);
        }
        while (wrap.hasRemaining()) {
            CoderResult encode = this.encoder.encode(wrap, this.bb, false);
            if (encode.isUnderflow()) {
                if (wrap.remaining() == 1) {
                    this.haveLeftoverChar = true;
                    this.leftoverChar = wrap.get();
                    return;
                }
                return;
            } else if (encode.isOverflow()) {
                writeBytes();
            } else {
                encode.throwException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void implFlushBuffer() {
        if (this.bb.position() > 0) {
            writeBytes();
        }
    }

    /* access modifiers changed from: package-private */
    public void implFlush() {
        implFlushBuffer();
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    /* access modifiers changed from: package-private */
    public void implClose() {
        flushLeftoverChar(null, true);
        while (true) {
            try {
                CoderResult flush = this.encoder.flush(this.bb);
                if (flush.isUnderflow()) {
                    break;
                } else if (flush.isOverflow()) {
                    writeBytes();
                } else {
                    flush.throwException();
                }
            } catch (IOException e) {
                this.encoder.reset();
                throw e;
            }
        }
        if (this.bb.position() > 0) {
            writeBytes();
        }
        if (this.ch != null) {
            this.ch.close();
        } else {
            this.out.close();
        }
    }
}
