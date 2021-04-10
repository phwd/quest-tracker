package java.nio;

import libcore.io.Memory;

class ByteBufferAsCharBuffer extends CharBuffer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final ByteBuffer bb;
    protected final int offset;
    private final ByteOrder order;

    ByteBufferAsCharBuffer(ByteBuffer bb2, int mark, int pos, int lim, int cap, int off, ByteOrder order2) {
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

    @Override // java.nio.CharBuffer
    public CharBuffer slice() {
        int pos = position();
        int lim = limit();
        int rem = pos <= lim ? lim - pos : 0;
        return new ByteBufferAsCharBuffer(this.bb, -1, 0, rem, rem, (pos << 1) + this.offset, this.order);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer duplicate() {
        return new ByteBufferAsCharBuffer(this.bb, markValue(), position(), limit(), capacity(), this.offset, this.order);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer asReadOnlyBuffer() {
        return new ByteBufferAsCharBuffer(this.bb.asReadOnlyBuffer(), markValue(), position(), limit(), capacity(), this.offset, this.order);
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return (i << 1) + this.offset;
    }

    @Override // java.nio.CharBuffer
    public char get() {
        return get(nextGetIndex());
    }

    @Override // java.nio.CharBuffer
    public char get(int i) {
        return this.bb.getCharUnchecked(ix(checkIndex(i)));
    }

    @Override // java.nio.CharBuffer
    public CharBuffer get(char[] dst, int offset2, int length) {
        checkBounds(offset2, length, dst.length);
        if (length <= remaining()) {
            this.bb.getUnchecked(ix(this.position), dst, offset2, length);
            this.position += length;
            return this;
        }
        throw new BufferUnderflowException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public char getUnchecked(int i) {
        return this.bb.getCharUnchecked(ix(i));
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(char x) {
        put(nextPutIndex(), x);
        return this;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(int i, char x) {
        if (!this.isReadOnly) {
            this.bb.putCharUnchecked(ix(checkIndex(i)), x);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(char[] src, int offset2, int length) {
        checkBounds(offset2, length, src.length);
        if (length <= remaining()) {
            this.bb.putUnchecked(ix(this.position), src, offset2, length);
            this.position += length;
            return this;
        }
        throw new BufferOverflowException();
    }

    @Override // java.nio.CharBuffer
    public CharBuffer compact() {
        if (!this.isReadOnly) {
            int pos = position();
            int lim = limit();
            int rem = pos <= lim ? lim - pos : 0;
            ByteBuffer byteBuffer = this.bb;
            if (!(byteBuffer instanceof DirectByteBuffer)) {
                System.arraycopy(byteBuffer.array(), ix(pos), this.bb.array(), ix(0), rem << 1);
            } else {
                Memory.memmove(this, ix(0), this, ix(pos), (long) (rem << 1));
            }
            position(rem);
            limit(capacity());
            discardMark();
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer, java.nio.Buffer
    public boolean isDirect() {
        return this.bb.isDirect();
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.CharBuffer
    public String toString(int start, int end) {
        if (end > limit() || start > end) {
            throw new IndexOutOfBoundsException();
        }
        try {
            char[] ca = new char[(end - start)];
            CharBuffer cb = CharBuffer.wrap(ca);
            CharBuffer db = duplicate();
            db.position(start);
            db.limit(end);
            cb.put(db);
            return new String(ca);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // java.lang.CharSequence, java.nio.CharBuffer, java.nio.CharBuffer
    public CharBuffer subSequence(int start, int end) {
        int pos = position();
        int lim = limit();
        int pos2 = pos <= lim ? pos : lim;
        int len = lim - pos2;
        if (start >= 0 && end <= len && start <= end) {
            return new ByteBufferAsCharBuffer(this.bb, -1, pos2 + start, pos2 + end, capacity(), this.offset, this.order);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.nio.CharBuffer
    public ByteOrder order() {
        return this.order;
    }
}
