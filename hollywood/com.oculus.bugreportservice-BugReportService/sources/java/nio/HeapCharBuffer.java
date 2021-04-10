package java.nio;

/* access modifiers changed from: package-private */
public class HeapCharBuffer extends CharBuffer {
    @Override // java.nio.CharBuffer
    public boolean isDirect() {
        return false;
    }

    HeapCharBuffer(int i, int i2) {
        this(i, i2, false);
    }

    HeapCharBuffer(int i, int i2, boolean z) {
        super(-1, 0, i2, i, new char[i], 0);
        this.isReadOnly = z;
    }

    HeapCharBuffer(char[] cArr, int i, int i2) {
        this(cArr, i, i2, false);
    }

    HeapCharBuffer(char[] cArr, int i, int i2, boolean z) {
        super(-1, i, i + i2, cArr.length, cArr, 0);
        this.isReadOnly = z;
    }

    protected HeapCharBuffer(char[] cArr, int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, i2, i3, i4, cArr, i5);
        this.isReadOnly = z;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer slice() {
        return new HeapCharBuffer(this.hb, -1, 0, remaining(), remaining(), position() + this.offset, this.isReadOnly);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer duplicate() {
        return new HeapCharBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, this.isReadOnly);
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return i + this.offset;
    }

    @Override // java.nio.CharBuffer
    public char get() {
        return this.hb[ix(nextGetIndex())];
    }

    @Override // java.nio.CharBuffer
    public char get(int i) {
        char[] cArr = this.hb;
        checkIndex(i);
        return cArr[ix(i)];
    }

    @Override // java.nio.CharBuffer
    public CharBuffer get(char[] cArr, int i, int i2) {
        Buffer.checkBounds(i, i2, cArr.length);
        if (i2 <= remaining()) {
            System.arraycopy((Object) this.hb, ix(position()), (Object) cArr, i, i2);
            position(position() + i2);
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(char c) {
        if (!this.isReadOnly) {
            this.hb[ix(nextPutIndex())] = c;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public CharBuffer put(CharBuffer charBuffer) {
        if (charBuffer == this) {
            throw new IllegalArgumentException();
        } else if (!this.isReadOnly) {
            if (charBuffer instanceof HeapCharBuffer) {
                HeapCharBuffer heapCharBuffer = (HeapCharBuffer) charBuffer;
                int remaining = heapCharBuffer.remaining();
                if (remaining <= remaining()) {
                    System.arraycopy((Object) heapCharBuffer.hb, heapCharBuffer.ix(heapCharBuffer.position()), (Object) this.hb, ix(position()), remaining);
                    heapCharBuffer.position(heapCharBuffer.position() + remaining);
                    position(position() + remaining);
                } else {
                    throw new BufferOverflowException();
                }
            } else if (charBuffer.isDirect()) {
                int remaining2 = charBuffer.remaining();
                if (remaining2 <= remaining()) {
                    charBuffer.get(this.hb, ix(position()), remaining2);
                    position(position() + remaining2);
                } else {
                    throw new BufferOverflowException();
                }
            } else {
                super.put(charBuffer);
            }
            return this;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public String toString(int i, int i2) {
        try {
            new String(this.hb, this.offset + i, i2 - i);
            throw null;
        } catch (StringIndexOutOfBoundsException unused) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // java.lang.CharSequence, java.nio.CharBuffer, java.nio.CharBuffer
    public CharBuffer subSequence(int i, int i2) {
        if (i < 0 || i2 > length() || i > i2) {
            throw new IndexOutOfBoundsException();
        }
        int position = position();
        return new HeapCharBuffer(this.hb, -1, position + i, position + i2, capacity(), this.offset, this.isReadOnly);
    }
}
