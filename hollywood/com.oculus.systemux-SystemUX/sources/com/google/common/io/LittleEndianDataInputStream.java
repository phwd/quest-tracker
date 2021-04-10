package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Beta
@GwtIncompatible
public final class LittleEndianDataInputStream extends FilterInputStream implements DataInput {
    public LittleEndianDataInputStream(InputStream inputStream) {
        super((InputStream) Preconditions.checkNotNull(inputStream));
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public String readLine() {
        throw new UnsupportedOperationException("readLine is not supported");
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) throws IOException {
        ByteStreams.readFully(this, bArr);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        ByteStreams.readFully(this, bArr, i, i2);
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) throws IOException {
        return (int) this.in.skip((long) i);
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public int readUnsignedByte() throws IOException {
        int read = this.in.read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public int readUnsignedShort() throws IOException {
        return Ints.fromBytes((byte) 0, (byte) 0, readAndCheckByte(), readAndCheckByte());
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public int readInt() throws IOException {
        byte readAndCheckByte = readAndCheckByte();
        byte readAndCheckByte2 = readAndCheckByte();
        return Ints.fromBytes(readAndCheckByte(), readAndCheckByte(), readAndCheckByte2, readAndCheckByte);
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public long readLong() throws IOException {
        byte readAndCheckByte = readAndCheckByte();
        byte readAndCheckByte2 = readAndCheckByte();
        byte readAndCheckByte3 = readAndCheckByte();
        byte readAndCheckByte4 = readAndCheckByte();
        byte readAndCheckByte5 = readAndCheckByte();
        byte readAndCheckByte6 = readAndCheckByte();
        return Longs.fromBytes(readAndCheckByte(), readAndCheckByte(), readAndCheckByte6, readAndCheckByte5, readAndCheckByte4, readAndCheckByte3, readAndCheckByte2, readAndCheckByte);
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public String readUTF() throws IOException {
        return new DataInputStream(this.in).readUTF();
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public short readShort() throws IOException {
        return (short) readUnsignedShort();
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public char readChar() throws IOException {
        return (char) readUnsignedShort();
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public byte readByte() throws IOException {
        return (byte) readUnsignedByte();
    }

    @Override // java.io.DataInput
    @CanIgnoreReturnValue
    public boolean readBoolean() throws IOException {
        return readUnsignedByte() != 0;
    }

    private byte readAndCheckByte() throws IOException, EOFException {
        int read = this.in.read();
        if (-1 != read) {
            return (byte) read;
        }
        throw new EOFException();
    }
}
