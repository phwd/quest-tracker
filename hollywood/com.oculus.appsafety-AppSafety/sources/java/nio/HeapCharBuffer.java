package java.nio;

/* access modifiers changed from: package-private */
public class HeapCharBuffer extends CharBuffer {
    HeapCharBuffer(int cap, int lim) {
        this(cap, lim, false);
    }

    HeapCharBuffer(int cap, int lim, boolean isReadOnly) {
        super(-1, 0, lim, cap, new char[cap], 0);
        this.isReadOnly = isReadOnly;
    }

    HeapCharBuffer(char[] buf, int off, int len) {
        this(buf, off, len, false);
    }

    HeapCharBuffer(char[] buf, int off, int len, boolean isReadOnly) {
        super(-1, off, off + len, buf.length, buf, 0);
        this.isReadOnly = isReadOnly;
    }

    protected HeapCharBuffer(char[] buf, int mark, int pos, int lim, int cap, int off) {
        this(buf, mark, pos, lim, cap, off, false);
    }

    protected HeapCharBuffer(char[] buf, int mark, int pos, int lim, int cap, int off, boolean isReadOnly) {
        super(mark, pos, lim, cap, buf, off);
        this.isReadOnly = isReadOnly;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer slice() {
        return new HeapCharBuffer(this.hb, -1, 0, remaining(), remaining(), position() + this.offset, this.isReadOnly);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer duplicate() {
        return new HeapCharBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, this.isReadOnly);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer asReadOnlyBuffer() {
        return new HeapCharBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, true);
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return this.offset + i;
    }

    @Override // java.nio.CharBuffer
    public char get() {
        return this.hb[ix(nextGetIndex())];
    }

    @Override // java.nio.CharBuffer
    public char get(int i) {
        return this.hb[ix(checkIndex(i))];
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public char getUnchecked(int i) {
        return this.hb[ix(i)];
    }

    @Override // java.nio.CharBuffer
    public CharBuffer get(char[] dst, int offset, int length) {
        checkBounds(offset, length, dst.length);
        if (length <= remaining()) {
            System.arraycopy((Object) this.hb, ix(position()), (Object) dst, offset, length);
            position(position() + length);
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.CharBuffer, java.nio.Buffer
    public boolean isDirect() {
        return false;
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(char x) {
        if (!this.isReadOnly) {
            this.hb[ix(nextPutIndex())] = x;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(int i, char x) {
        if (!this.isReadOnly) {
            this.hb[ix(checkIndex(i))] = x;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(char[] src, int offset, int length) {
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

    @Override // java.nio.CharBuffer
    public CharBuffer put(CharBuffer src) {
        if (src == this) {
            throw new IllegalArgumentException();
        } else if (!this.isReadOnly) {
            if (src instanceof HeapCharBuffer) {
                HeapCharBuffer sb = (HeapCharBuffer) src;
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

    @Override // java.nio.CharBuffer
    public CharBuffer compact() {
        if (!this.isReadOnly) {
            System.arraycopy((Object) this.hb, ix(position()), (Object) this.hb, ix(0), remaining());
            position(remaining());
            limit(capacity());
            discardMark();
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public String toString(int start, int end) {
        try {
            return new String(this.hb, this.offset + start, end - start);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // java.lang.CharSequence, java.nio.CharBuffer, java.nio.CharBuffer
    public CharBuffer subSequence(int start, int end) {
        if (start < 0 || end > length() || start > end) {
            throw new IndexOutOfBoundsException();
        }
        int pos = position();
        return new HeapCharBuffer(this.hb, -1, pos + start, pos + end, capacity(), this.offset, this.isReadOnly);
    }

    @Override // java.nio.CharBuffer
    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
}
