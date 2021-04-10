package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

@GwtIncompatible
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    ReaderInputStream(Reader reader2, Charset charset, int bufferSize) {
        this(reader2, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), bufferSize);
    }

    ReaderInputStream(Reader reader2, CharsetEncoder encoder2, int bufferSize) {
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader2);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(encoder2);
        Preconditions.checkArgument(bufferSize > 0, "bufferSize must be positive: %s", bufferSize);
        encoder2.reset();
        this.charBuffer = CharBuffer.allocate(bufferSize);
        this.charBuffer.flip();
        this.byteBuffer = ByteBuffer.allocate(bufferSize);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        this.reader.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    @Override // java.io.InputStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r10, int r11, int r12) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.ReaderInputStream.read(byte[], int, int):int");
    }

    private static CharBuffer grow(CharBuffer buf) {
        CharBuffer bigger = CharBuffer.wrap(Arrays.copyOf(buf.array(), buf.capacity() * 2));
        bigger.position(buf.position());
        bigger.limit(buf.limit());
        return bigger;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                this.charBuffer.compact().flip();
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int limit = this.charBuffer.limit();
        int numChars = this.reader.read(this.charBuffer.array(), limit, availableCapacity(this.charBuffer));
        if (numChars == -1) {
            this.endOfInput = true;
        } else {
            this.charBuffer.limit(limit + numChars);
        }
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private void startDraining(boolean overflow) {
        this.byteBuffer.flip();
        if (!overflow || this.byteBuffer.remaining() != 0) {
            this.draining = true;
        } else {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        }
    }

    private int drain(byte[] b, int off, int len) {
        int remaining = Math.min(len, this.byteBuffer.remaining());
        this.byteBuffer.get(b, off, remaining);
        return remaining;
    }
}
