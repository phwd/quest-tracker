package com.oculus.runtimeipcapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Writer {
    private ByteArrayOutputStream stream = new ByteArrayOutputStream();
    private byte[] writeBuffer = new byte[8];

    public void writeShort(int value) {
        this.stream.write((value >>> 0) & 255);
        this.stream.write((value >>> 8) & 255);
    }

    public void writeInt(int value) {
        this.stream.write((value >>> 0) & 255);
        this.stream.write((value >>> 8) & 255);
        this.stream.write((value >>> 16) & 255);
        this.stream.write((value >>> 24) & 255);
    }

    public void writeLong(long value) {
        byte[] bArr = this.writeBuffer;
        bArr[0] = (byte) ((int) (value >>> 0));
        bArr[1] = (byte) ((int) (value >>> 8));
        bArr[2] = (byte) ((int) (value >>> 16));
        bArr[3] = (byte) ((int) (value >>> 24));
        bArr[4] = (byte) ((int) (value >>> 32));
        bArr[5] = (byte) ((int) (value >>> 40));
        bArr[6] = (byte) ((int) (value >>> 48));
        bArr[7] = (byte) ((int) (value >>> 56));
        this.stream.write(bArr, 0, 8);
    }

    public void writeFloat(float value) {
        writeInt(Float.floatToIntBits(value));
    }

    public void writeDouble(double value) {
        writeLong(Double.doubleToLongBits(value));
    }

    public void writeBoolean(boolean value) {
        this.stream.write(value ? 1 : 0);
    }

    public void write(byte[] bytes) throws IOException {
        this.stream.write(bytes);
    }

    public void flush() throws IOException {
        this.stream.flush();
    }

    public byte[] toByteArray() {
        return this.stream.toByteArray();
    }
}
