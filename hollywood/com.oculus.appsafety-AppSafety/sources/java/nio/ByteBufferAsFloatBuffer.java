package java.nio;

import libcore.io.Memory;

class ByteBufferAsFloatBuffer extends FloatBuffer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final ByteBuffer bb;
    protected final int offset;
    private final ByteOrder order;

    ByteBufferAsFloatBuffer(ByteBuffer bb2, int mark, int pos, int lim, int cap, int off, ByteOrder order2) {
        super(mark, pos, lim, cap);
        this.bb = bb2.duplicate();
        this.isReadOnly = bb2.isReadOnly;
        if (bb2 instanceof DirectByteBuffer) {
            this.address = bb2.address + ((long) off);
        }
        this.bb.order(order2);
        this.order = order2;
        this.offset = off;
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer slice() {
        int pos = position();
        int lim = limit();
        int rem = pos <= lim ? lim - pos : 0;
        return new ByteBufferAsFloatBuffer(this.bb, -1, 0, rem, rem, (pos << 2) + this.offset, this.order);
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer duplicate() {
        return new ByteBufferAsFloatBuffer(this.bb, markValue(), position(), limit(), capacity(), this.offset, this.order);
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer asReadOnlyBuffer() {
        return new ByteBufferAsFloatBuffer(this.bb.asReadOnlyBuffer(), markValue(), position(), limit(), capacity(), this.offset, this.order);
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return (i << 2) + this.offset;
    }

    @Override // java.nio.FloatBuffer
    public float get() {
        return get(nextGetIndex());
    }

    @Override // java.nio.FloatBuffer
    public float get(int i) {
        return this.bb.getFloatUnchecked(ix(checkIndex(i)));
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer get(float[] dst, int offset2, int length) {
        checkBounds(offset2, length, dst.length);
        if (length <= remaining()) {
            this.bb.getUnchecked(ix(this.position), dst, offset2, length);
            this.position += length;
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer put(float x) {
        put(nextPutIndex(), x);
        return this;
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer put(int i, float x) {
        if (!this.isReadOnly) {
            this.bb.putFloatUnchecked(ix(checkIndex(i)), x);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer put(float[] src, int offset2, int length) {
        checkBounds(offset2, length, src.length);
        if (length <= remaining()) {
            this.bb.putUnchecked(ix(this.position), src, offset2, length);
            this.position += length;
            return this;
        }
        throw new BufferOverflowException();
    }

    @Override // java.nio.FloatBuffer
    public FloatBuffer compact() {
        if (!this.isReadOnly) {
            int pos = position();
            int lim = limit();
            int rem = pos <= lim ? lim - pos : 0;
            ByteBuffer byteBuffer = this.bb;
            if (!(byteBuffer instanceof DirectByteBuffer)) {
                System.arraycopy(byteBuffer.array(), ix(pos), this.bb.array(), ix(0), rem << 2);
            } else {
                Memory.memmove(this, ix(0), this, ix(pos), (long) (rem << 2));
            }
            position(rem);
            limit(capacity());
            discardMark();
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.FloatBuffer, java.nio.Buffer
    public boolean isDirect() {
        return this.bb.isDirect();
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.FloatBuffer
    public ByteOrder order() {
        return this.order;
    }
}
