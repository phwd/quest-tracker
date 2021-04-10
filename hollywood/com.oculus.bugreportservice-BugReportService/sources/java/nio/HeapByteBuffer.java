package java.nio;

import libcore.io.Memory;

/* access modifiers changed from: package-private */
public final class HeapByteBuffer extends ByteBuffer {
    @Override // java.nio.ByteBuffer
    public boolean isDirect() {
        return false;
    }

    HeapByteBuffer(int i, int i2) {
        this(i, i2, false);
    }

    private HeapByteBuffer(int i, int i2, boolean z) {
        super(-1, 0, i2, i, new byte[i], 0);
        this.isReadOnly = z;
    }

    HeapByteBuffer(byte[] bArr, int i, int i2) {
        this(bArr, i, i2, false);
    }

    private HeapByteBuffer(byte[] bArr, int i, int i2, boolean z) {
        super(-1, i, i + i2, bArr.length, bArr, 0);
        this.isReadOnly = z;
    }

    private HeapByteBuffer(byte[] bArr, int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, i2, i3, i4, bArr, i5);
        this.isReadOnly = z;
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer slice() {
        return new HeapByteBuffer(this.hb, -1, 0, remaining(), remaining(), position() + this.offset, this.isReadOnly);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer duplicate() {
        return new HeapByteBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, this.isReadOnly);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer asReadOnlyBuffer() {
        return new HeapByteBuffer(this.hb, markValue(), position(), limit(), capacity(), this.offset, true);
    }

    /* access modifiers changed from: protected */
    public int ix(int i) {
        return i + this.offset;
    }

    @Override // java.nio.ByteBuffer
    public byte get() {
        return this.hb[ix(nextGetIndex())];
    }

    @Override // java.nio.ByteBuffer
    public byte get(int i) {
        byte[] bArr = this.hb;
        checkIndex(i);
        return bArr[ix(i)];
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer get(byte[] bArr, int i, int i2) {
        Buffer.checkBounds(i, i2, bArr.length);
        if (i2 <= remaining()) {
            System.arraycopy(this.hb, ix(position()), bArr, i, i2);
            position(position() + i2);
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(byte b) {
        if (!this.isReadOnly) {
            this.hb[ix(nextPutIndex())] = b;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(byte[] bArr, int i, int i2) {
        if (!this.isReadOnly) {
            Buffer.checkBounds(i, i2, bArr.length);
            if (i2 <= remaining()) {
                System.arraycopy(bArr, i, this.hb, ix(position()), i2);
                position(position() + i2);
                return this;
            }
            throw new BufferOverflowException();
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer compact() {
        if (!this.isReadOnly) {
            System.arraycopy(this.hb, ix(position()), this.hb, ix(0), remaining());
            position(remaining());
            limit(capacity());
            discardMark();
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public byte _get(int i) {
        return this.hb[i];
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void _put(int i, byte b) {
        if (!this.isReadOnly) {
            this.hb[i] = b;
            return;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public char getChar() {
        return Bits.getChar(this, ix(nextGetIndex(2)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public char getChar(int i) {
        checkIndex(i, 2);
        return Bits.getChar(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public char getCharUnchecked(int i) {
        return Bits.getChar(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int i, char[] cArr, int i2, int i3) {
        Memory.unsafeBulkGet(cArr, i2, i3 * 2, this.hb, ix(i), 2, !this.nativeByteOrder);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putCharUnchecked(int i, char c) {
        Bits.putChar(this, ix(i), c, this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public CharBuffer asCharBuffer() {
        int remaining = remaining() >> 1;
        return new ByteBufferAsCharBuffer(this, -1, 0, remaining, remaining, position(), order());
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public short getShortUnchecked(int i) {
        return Bits.getShort(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int i, short[] sArr, int i2, int i3) {
        Memory.unsafeBulkGet(sArr, i2, i3 * 2, this.hb, ix(i), 2, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ShortBuffer asShortBuffer() {
        int remaining = remaining() >> 1;
        return new ByteBufferAsShortBuffer(this, -1, 0, remaining, remaining, position(), order());
    }

    @Override // java.nio.ByteBuffer
    public int getInt() {
        return Bits.getInt(this, ix(nextGetIndex(4)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public int getInt(int i) {
        checkIndex(i, 4);
        return Bits.getInt(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public int getIntUnchecked(int i) {
        return Bits.getInt(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int i, int[] iArr, int i2, int i3) {
        Memory.unsafeBulkGet(iArr, i2, i3 * 4, this.hb, ix(i), 4, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public IntBuffer asIntBuffer() {
        int remaining = remaining() >> 2;
        return new ByteBufferAsIntBuffer(this, -1, 0, remaining, remaining, position(), order());
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public long getLongUnchecked(int i) {
        return Bits.getLong(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int i, long[] jArr, int i2, int i3) {
        Memory.unsafeBulkGet(jArr, i2, i3 * 8, this.hb, ix(i), 8, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public LongBuffer asLongBuffer() {
        int remaining = remaining() >> 3;
        return new ByteBufferAsLongBuffer(this, -1, 0, remaining, remaining, position(), order());
    }
}
