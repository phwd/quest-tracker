package java.nio;

/* access modifiers changed from: package-private */
public class HeapDoubleBuffer extends DoubleBuffer {
    HeapDoubleBuffer(int cap, int lim) {
        this(cap, lim, false);
    }

    HeapDoubleBuffer(double[] buf, int off, int len) {
        this(buf, off, len, false);
    }

    protected HeapDoubleBuffer(double[] buf, int mark, int pos, int lim, int cap, int off) {
        this(buf, mark, pos, lim, cap, off, false);
    }

    HeapDoubleBuffer(int cap, int lim, boolean isReadOnly) {
        super(-1, 0, lim, cap, new double[cap], 0);
        this.isReadOnly = isReadOnly;
    }

    HeapDoubleBuffer(double[] buf, int off, int len, boolean isReadOnly) {
        super(-1, off, off + len, buf.length, buf, 0);
        this.isReadOnly = isReadOnly;
    }

    protected HeapDoubleBuffer(double[] buf, int mark, int pos, int lim, int cap, int off, boolean isReadOnly) {
        super(mark, pos, lim, cap, buf, off);
        this.isReadOnly = isReadOnly;
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer slice() {
        return new HeapDoubleBuffer(this.hb, -1, 0, remaining(), remaining(), position() + this.offset, this.isReadOnly);
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer duplicate() {
        return new HeapDoubleBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, this.isReadOnly);
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer asReadOnlyBuffer() {
        return new HeapDoubleBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, true);
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return this.offset + i;
    }

    @Override // java.nio.DoubleBuffer
    public double get() {
        return this.hb[ix(nextGetIndex())];
    }

    @Override // java.nio.DoubleBuffer
    public double get(int i) {
        return this.hb[ix(checkIndex(i))];
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer get(double[] dst, int offset, int length) {
        checkBounds(offset, length, dst.length);
        if (length <= remaining()) {
            System.arraycopy((Object) this.hb, ix(position()), (Object) dst, offset, length);
            position(position() + length);
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.DoubleBuffer, java.nio.Buffer
    public boolean isDirect() {
        return false;
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer put(double x) {
        if (!this.isReadOnly) {
            this.hb[ix(nextPutIndex())] = x;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer put(int i, double x) {
        if (!this.isReadOnly) {
            this.hb[ix(checkIndex(i))] = x;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer put(double[] src, int offset, int length) {
        if (!this.isReadOnly) {
            checkBounds(offset, length, src.length);
            if (length <= remaining()) {
                System.arraycopy((Object) src, offset, (Object) this.hb, ix(position()), length);
                position(position() + length);
                return this;
            }
            throw new BufferOverflowException();
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer put(DoubleBuffer src) {
        if (src == this) {
            throw new IllegalArgumentException();
        } else if (!this.isReadOnly) {
            if (src instanceof HeapDoubleBuffer) {
                HeapDoubleBuffer sb = (HeapDoubleBuffer) src;
                int n = sb.remaining();
                if (n <= remaining()) {
                    System.arraycopy((Object) sb.hb, sb.ix(sb.position()), (Object) this.hb, ix(position()), n);
                    sb.position(sb.position() + n);
                    position(position() + n);
                } else {
                    throw new BufferOverflowException();
                }
            } else if (src.isDirect()) {
                int n2 = src.remaining();
                if (n2 <= remaining()) {
                    src.get(this.hb, ix(position()), n2);
                    position(position() + n2);
                } else {
                    throw new BufferOverflowException();
                }
            } else {
                super.put(src);
            }
            return this;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    @Override // java.nio.DoubleBuffer
    public DoubleBuffer compact() {
        if (!this.isReadOnly) {
            System.arraycopy((Object) this.hb, ix(position()), (Object) this.hb, ix(0), remaining());
            position(remaining());
            limit(capacity());
            discardMark();
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.DoubleBuffer
    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
}
