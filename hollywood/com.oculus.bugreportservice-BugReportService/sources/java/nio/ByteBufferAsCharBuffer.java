package java.nio;

class ByteBufferAsCharBuffer extends CharBuffer {
    protected final ByteBuffer bb;
    protected final int offset;
    private final ByteOrder order;

    ByteBufferAsCharBuffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, int i5, ByteOrder byteOrder) {
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

    @Override // java.nio.CharBuffer
    public CharBuffer slice() {
        int position = position();
        int limit = limit();
        int i = position <= limit ? limit - position : 0;
        return new ByteBufferAsCharBuffer(this.bb, -1, 0, i, i, (position << 1) + this.offset, this.order);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer duplicate() {
        return new ByteBufferAsCharBuffer(this.bb, markValue(), position(), limit(), capacity(), this.offset, this.order);
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
        ByteBuffer byteBuffer = this.bb;
        checkIndex(i);
        return byteBuffer.getCharUnchecked(ix(i));
    }

    @Override // java.nio.CharBuffer
    public CharBuffer get(char[] cArr, int i, int i2) {
        Buffer.checkBounds(i, i2, cArr.length);
        if (i2 <= remaining()) {
            this.bb.getUnchecked(ix(this.position), cArr, i, i2);
            this.position += i2;
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(char c) {
        put(nextPutIndex(), c);
        return this;
    }

    public CharBuffer put(int i, char c) {
        if (!this.isReadOnly) {
            ByteBuffer byteBuffer = this.bb;
            checkIndex(i);
            byteBuffer.putCharUnchecked(ix(i), c);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public boolean isDirect() {
        return this.bb.isDirect();
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.CharBuffer
    public String toString(int i, int i2) {
        if (i2 > limit() || i > i2) {
            throw new IndexOutOfBoundsException();
        }
        try {
            char[] cArr = new char[(i2 - i)];
            CharBuffer wrap = CharBuffer.wrap(cArr);
            CharBuffer duplicate = duplicate();
            duplicate.position(i);
            duplicate.limit(i2);
            wrap.put(duplicate);
            new String(cArr);
            throw null;
        } catch (StringIndexOutOfBoundsException unused) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // java.lang.CharSequence, java.nio.CharBuffer, java.nio.CharBuffer
    public CharBuffer subSequence(int i, int i2) {
        int position = position();
        int limit = limit();
        if (position > limit) {
            position = limit;
        }
        int i3 = limit - position;
        if (i >= 0 && i2 <= i3 && i <= i2) {
            return new ByteBufferAsCharBuffer(this.bb, -1, position + i, position + i2, capacity(), this.offset, this.order);
        }
        throw new IndexOutOfBoundsException();
    }
}
