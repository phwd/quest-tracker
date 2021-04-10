package com.oculus.companion.gattble.phonenotifications;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class ANCSByteArrayOutputStream extends ByteArrayOutputStream {
    public void writeByte(int i) {
        write(i);
    }

    public void writeInt16(short s) throws IOException {
        write(ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(s).array());
    }

    public void writeInt32(int i) throws IOException {
        write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array());
    }

    public void writeString(String str) throws IOException {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        write(ByteBuffer.allocate(bytes.length + 1).order(ByteOrder.LITTLE_ENDIAN).put(bytes).put((byte) 0).array());
    }
}
