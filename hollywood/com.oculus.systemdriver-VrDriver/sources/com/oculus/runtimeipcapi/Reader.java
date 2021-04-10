package com.oculus.runtimeipcapi;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

public class Reader {
    protected int length;
    private byte[] readBuffer;
    protected ByteArrayInputStream stream;

    public Reader(byte[] buf) {
        this.stream = null;
        this.length = 0;
        this.readBuffer = new byte[8];
        this.stream = new ByteArrayInputStream(buf);
        this.length = buf.length;
    }

    public Reader(byte[] buf, int offset, int inLength) {
        this.stream = null;
        this.length = 0;
        this.readBuffer = new byte[8];
        this.stream = new ByteArrayInputStream(buf, offset, inLength);
        this.length = inLength;
    }

    public final void readFully(byte[] b, int off, int len) throws IOException {
        if (len >= 0) {
            int n = 0;
            while (n < len) {
                int count = this.stream.read(b, off + n, len - n);
                if (count >= 0) {
                    n += count;
                } else {
                    throw new EOFException();
                }
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final int read(byte[] b) throws IOException {
        return this.stream.read(b, 0, b.length);
    }

    public final int readShort() throws IOException {
        readFully(this.readBuffer, 0, 2);
        byte[] bArr = this.readBuffer;
        return ((bArr[1] & 255) << 8) + ((bArr[0] & 255) << 0);
    }

    public final int readInt() throws IOException {
        readFully(this.readBuffer, 0, 4);
        byte[] bArr = this.readBuffer;
        return ((bArr[3] & 255) << 24) + ((bArr[2] & 255) << 16) + ((bArr[1] & 255) << 8) + ((bArr[0] & 255) << 0);
    }

    public final long readLong() throws IOException {
        readFully(this.readBuffer, 0, 8);
        byte[] bArr = this.readBuffer;
        return (((long) bArr[7]) << 56) + (((long) (bArr[6] & 255)) << 48) + (((long) (bArr[5] & 255)) << 40) + (((long) (bArr[4] & 255)) << 32) + (((long) (bArr[3] & 255)) << 24) + ((long) ((bArr[2] & 255) << 16)) + ((long) ((bArr[1] & 255) << 8)) + ((long) ((bArr[0] & 255) << 0));
    }

    public final boolean readBoolean() throws IOException {
        int ch = this.stream.read();
        if (ch >= 0) {
            return ch != 0;
        }
        throw new EOFException();
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public final int available() {
        return this.stream.available();
    }

    public final int getReadPos() {
        return this.length - available();
    }

    public final int getLength() {
        return this.length;
    }
}
