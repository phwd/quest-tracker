package com.facebook.stetho.websocket;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* access modifiers changed from: package-private */
public class Frame {
    public static final byte OPCODE_BINARY_FRAME = 2;
    public static final byte OPCODE_CONNECTION_CLOSE = 8;
    public static final byte OPCODE_CONNECTION_PING = 9;
    public static final byte OPCODE_CONNECTION_PONG = 10;
    public static final byte OPCODE_TEXT_FRAME = 1;
    public boolean fin;
    public boolean hasMask;
    public byte[] maskingKey;
    public byte opcode;
    public byte[] payloadData;
    public long payloadLen;
    public boolean rsv1;
    public boolean rsv2;
    public boolean rsv3;

    private static byte[] encodeLength(long j) {
        if (j <= 125) {
            return new byte[]{(byte) ((int) j)};
        } else if (j <= 65535) {
            return new byte[]{126, (byte) ((int) ((j >> 8) & 255)), (byte) ((int) (j & 255))};
        } else {
            return new byte[]{Ascii.DEL, (byte) ((int) ((j >> 56) & 255)), (byte) ((int) ((j >> 48) & 255)), (byte) ((int) ((j >> 40) & 255)), (byte) ((int) ((j >> 32) & 255)), (byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) (j & 255))};
        }
    }

    Frame() {
    }

    public void readFrom(BufferedInputStream bufferedInputStream) throws IOException {
        decodeFirstByte(readByteOrThrow(bufferedInputStream));
        byte readByteOrThrow = readByteOrThrow(bufferedInputStream);
        this.hasMask = (readByteOrThrow & UnsignedBytes.MAX_POWER_OF_TWO) != 0;
        this.payloadLen = decodeLength((byte) (readByteOrThrow & Ascii.DEL), bufferedInputStream);
        this.maskingKey = this.hasMask ? decodeMaskingKey(bufferedInputStream) : null;
        long j = this.payloadLen;
        this.payloadData = new byte[((int) j)];
        readBytesOrThrow(bufferedInputStream, this.payloadData, 0, (int) j);
        MaskingHelper.unmask(this.maskingKey, this.payloadData, 0, (int) this.payloadLen);
    }

    public void writeTo(BufferedOutputStream bufferedOutputStream) throws IOException {
        bufferedOutputStream.write(encodeFirstByte());
        byte[] encodeLength = encodeLength(this.payloadLen);
        if (this.hasMask) {
            encodeLength[0] = (byte) (encodeLength[0] | UnsignedBytes.MAX_POWER_OF_TWO);
        }
        bufferedOutputStream.write(encodeLength, 0, encodeLength.length);
        if (!this.hasMask) {
            bufferedOutputStream.write(this.payloadData, 0, (int) this.payloadLen);
            return;
        }
        throw new UnsupportedOperationException("Writing masked data not implemented");
    }

    private void decodeFirstByte(byte b) {
        boolean z = true;
        this.fin = (b & UnsignedBytes.MAX_POWER_OF_TWO) != 0;
        this.rsv1 = (b & SignedBytes.MAX_POWER_OF_TWO) != 0;
        this.rsv2 = (b & 32) != 0;
        if ((b & Ascii.DLE) == 0) {
            z = false;
        }
        this.rsv3 = z;
        this.opcode = (byte) (b & Ascii.SI);
    }

    private byte encodeFirstByte() {
        byte b = this.fin ? (byte) 128 : 0;
        if (this.rsv1) {
            b = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
        }
        if (this.rsv2) {
            b = (byte) (b | 32);
        }
        if (this.rsv3) {
            b = (byte) (b | Ascii.DLE);
        }
        return (byte) (b | (this.opcode & Ascii.SI));
    }

    private long decodeLength(byte b, InputStream inputStream) throws IOException {
        if (b <= 125) {
            return (long) b;
        }
        if (b == 126) {
            return (long) (((readByteOrThrow(inputStream) & 255) << 8) | (readByteOrThrow(inputStream) & 255));
        }
        if (b == Byte.MAX_VALUE) {
            long j = 0;
            for (int i = 0; i < 8; i++) {
                j = (j | ((long) (readByteOrThrow(inputStream) & 255))) << 8;
            }
            return j;
        }
        throw new IOException("Unexpected length byte: " + ((int) b));
    }

    private static byte[] decodeMaskingKey(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4];
        readBytesOrThrow(inputStream, bArr, 0, bArr.length);
        return bArr;
    }

    private static void readBytesOrThrow(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int read = inputStream.read(bArr, i, i2);
            if (read != -1) {
                i2 -= read;
                i += read;
            } else {
                throw new EOFException();
            }
        }
    }

    private static byte readByteOrThrow(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return (byte) read;
        }
        throw new EOFException();
    }
}
