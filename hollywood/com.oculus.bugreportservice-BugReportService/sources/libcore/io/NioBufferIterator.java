package libcore.io;

public final class NioBufferIterator extends BufferIterator {
    private final long address;
    private final MemoryMappedFile file;
    private final int length;
    private int position;
    private final boolean swap;

    NioBufferIterator(MemoryMappedFile memoryMappedFile, long j, int i, boolean z) {
        memoryMappedFile.checkNotClosed();
        this.file = memoryMappedFile;
        this.address = j;
        if (i < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (Long.compareUnsigned(j, -1 - ((long) i)) <= 0) {
            this.length = i;
            this.swap = z;
        } else {
            throw new IllegalArgumentException("length " + i + " would overflow 64-bit address space");
        }
    }

    @Override // libcore.io.BufferIterator
    public void skip(int i) {
        this.position += i;
    }

    @Override // libcore.io.BufferIterator
    public void readByteArray(byte[] bArr, int i, int i2) {
        checkDstBounds(i, bArr.length, i2);
        this.file.checkNotClosed();
        checkReadBounds(this.position, this.length, i2);
        Memory.peekByteArray(this.address + ((long) this.position), bArr, i, i2);
        this.position += i2;
    }

    @Override // libcore.io.BufferIterator
    public byte readByte() {
        this.file.checkNotClosed();
        checkReadBounds(this.position, this.length, 1);
        byte peekByte = Memory.peekByte(this.address + ((long) this.position));
        this.position++;
        return peekByte;
    }

    @Override // libcore.io.BufferIterator
    public int readInt() {
        this.file.checkNotClosed();
        checkReadBounds(this.position, this.length, 4);
        int peekInt = Memory.peekInt(this.address + ((long) this.position), this.swap);
        this.position += 4;
        return peekInt;
    }

    @Override // libcore.io.BufferIterator
    public void readIntArray(int[] iArr, int i, int i2) {
        checkDstBounds(i, iArr.length, i2);
        this.file.checkNotClosed();
        int i3 = i2 * 4;
        checkReadBounds(this.position, this.length, i3);
        Memory.peekIntArray(this.address + ((long) this.position), iArr, i, i2, this.swap);
        this.position += i3;
    }

    private static void checkReadBounds(int i, int i2, int i3) {
        if (i < 0 || i3 < 0) {
            throw new IndexOutOfBoundsException("Invalid read args: position=" + i + ", byteCount=" + i3);
        }
        int i4 = i + i3;
        if (i4 < 0 || i4 > i2) {
            throw new IndexOutOfBoundsException("Read outside range: position=" + i + ", byteCount=" + i3 + ", length=" + i2);
        }
    }

    private static void checkDstBounds(int i, int i2, int i3) {
        if (i < 0 || i3 < 0) {
            throw new IndexOutOfBoundsException("Invalid dst args: offset=" + i2 + ", count=" + i3);
        }
        int i4 = i + i3;
        if (i4 < 0 || i4 > i2) {
            throw new IndexOutOfBoundsException("Write outside range: dst.length=" + i2 + ", offset=" + i + ", count=" + i3);
        }
    }
}
