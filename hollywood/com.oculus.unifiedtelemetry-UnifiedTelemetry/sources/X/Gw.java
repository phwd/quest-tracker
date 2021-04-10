package X;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import javax.annotation.Nullable;

public final class Gw extends Writer {
    @Nullable
    public ByteBuffer A00;
    @Nullable
    public CharsetEncoder A01;
    public final OutputStream A02;

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        A02(true);
    }

    private void A00() throws IOException {
        if (this.A01 == null) {
            throw new IOException("OutputStreamWriter is closed");
        }
    }

    private void A01(CharBuffer charBuffer) throws IOException {
        CoderResult encode;
        if (this.A01 != null) {
            while (true) {
                encode = this.A01.encode(charBuffer, this.A00, false);
                if (!encode.isOverflow()) {
                    break;
                }
                A02(false);
            }
            if (encode.isError()) {
                encode.throwException();
            }
        }
    }

    private void A02(boolean z) throws IOException {
        synchronized (this.lock) {
            A00();
            ByteBuffer byteBuffer = this.A00;
            if (byteBuffer != null) {
                int position = byteBuffer.position();
                if (position > 0) {
                    this.A00.flip();
                    this.A02.write(this.A00.array(), this.A00.arrayOffset(), position);
                    this.A00.clear();
                }
                if (z) {
                    this.A02.flush();
                }
            }
        }
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this.lock) {
            if (this.A01 != null) {
                CharBuffer allocate = CharBuffer.allocate(0);
                while (true) {
                    CoderResult encode = this.A01.encode(allocate, this.A00, true);
                    if (!encode.isError()) {
                        if (!encode.isOverflow()) {
                            break;
                        }
                        A02(false);
                    } else {
                        encode.throwException();
                        break;
                    }
                }
                CoderResult flush = this.A01.flush(this.A00);
                while (!flush.isUnderflow()) {
                    if (flush.isOverflow()) {
                        A02(false);
                        flush = this.A01.flush(this.A00);
                    } else {
                        flush.throwException();
                    }
                }
                A02(false);
                this.A02.close();
                this.A01 = null;
                this.A00 = null;
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Gw(OutputStream outputStream, ByteBuffer byteBuffer) {
        super(outputStream);
        Charset defaultCharset = Charset.defaultCharset();
        this.A02 = outputStream;
        this.A00 = byteBuffer;
        CharsetEncoder newEncoder = defaultCharset.newEncoder();
        this.A01 = newEncoder;
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        newEncoder.onMalformedInput(codingErrorAction);
        this.A01.onUnmappableCharacter(codingErrorAction);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        synchronized (this.lock) {
            A00();
            C0084Ge.A01(cArr.length, i, i2);
            A01(CharBuffer.wrap(cArr, i, i2));
        }
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        synchronized (this.lock) {
            A00();
            A01(CharBuffer.wrap(new char[]{(char) i}));
        }
    }

    @Override // java.io.Writer
    public final void write(String str, int i, int i2) throws IOException {
        synchronized (this.lock) {
            if (i2 < 0) {
                throw C0084Ge.A00(str, i, i2);
            } else if (str != null) {
                if ((i | i2) >= 0) {
                    if (i <= str.length() - i2) {
                        A00();
                        A01(CharBuffer.wrap(str, i, i2 + i));
                    }
                }
                throw C0084Ge.A00(str, i, i2);
            } else {
                throw new NullPointerException("str == null");
            }
        }
    }
}
