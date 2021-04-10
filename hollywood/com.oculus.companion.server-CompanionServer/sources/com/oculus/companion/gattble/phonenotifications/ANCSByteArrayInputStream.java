package com.oculus.companion.gattble.phonenotifications;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class ANCSByteArrayInputStream extends ByteArrayInputStream {
    public ANCSByteArrayInputStream(byte[] bArr) {
        super(bArr);
    }

    private byte[] readBytes(int i) throws EOFException {
        byte[] bArr = new byte[i];
        if (read(bArr, 0, i) == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public int readInt8() throws EOFException {
        return readInt8(readBytes(1));
    }

    public static int readInt8(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).get();
    }

    public int readInt16() throws EOFException {
        return ByteBuffer.wrap(readBytes(2)).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public int readInt32() throws EOFException {
        return ByteBuffer.wrap(readBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public String readString(int i) throws EOFException {
        return new String(readBytes(i), StandardCharsets.UTF_8);
    }

    public String readString() throws EOFException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read = read();
        if (read >= 0) {
            while (read > 0) {
                byteArrayOutputStream.write(read);
                read = read();
                if (read < 0) {
                    throw new EOFException();
                }
            }
            return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        }
        throw new EOFException();
    }

    public void mark() {
        super.mark(0);
    }
}
