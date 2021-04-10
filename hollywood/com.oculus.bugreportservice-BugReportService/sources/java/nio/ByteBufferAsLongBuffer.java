package java.nio;

class ByteBufferAsLongBuffer extends LongBuffer {
    protected final ByteBuffer bb;
    protected final int offset;
    private final ByteOrder order;

    ByteBufferAsLongBuffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, int i5, ByteOrder byteOrder) {
        super(i, i2, i3, i4);
        this.bb = byteBuffer.duplicate();
        this.isReadOnly = byteBuffer.isReadOnly;
        if (byteBuffer instanceof DirectByteBuffer) {
            this.address = byteBuffer.address + ((long) i5);
        }
        this.bb.order(byteOrder);
        this.order = byteOrder;
        this.offset = i5;
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return (i << 3) + this.offset;
    }

    @Override // java.nio.LongBuffer
    public long get() {
        return get(nextGetIndex());
    }

    @Override // java.nio.LongBuffer
    public long get(int i) {
        ByteBuffer byteBuffer = this.bb;
        checkIndex(i);
        return byteBuffer.getLongUnchecked(ix(i));
    }

    @Override // java.nio.LongBuffer
    public LongBuffer get(long[] jArr, int i, int i2) {
        Buffer.checkBounds(i, i2, jArr.length);
        if (i2 <= remaining()) {
            this.bb.getUnchecked(ix(this.position), jArr, i, i2);
            this.position += i2;
            return this;
        }
        throw new BufferUnderflowException();
    }
}
