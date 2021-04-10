package libcore.io;

public abstract class BufferIterator {
    public abstract byte readByte();

    public abstract void readByteArray(byte[] bArr, int i, int i2);

    public abstract int readInt();

    public abstract void readIntArray(int[] iArr, int i, int i2);

    public abstract void skip(int i);
}
