package java.nio;

import libcore.io.Memory;

/* access modifiers changed from: package-private */
public final class HeapByteBuffer extends ByteBuffer {
    HeapByteBuffer(int cap, int lim) {
        this(cap, lim, false);
    }

    private HeapByteBuffer(int cap, int lim, boolean isReadOnly) {
        super(-1, 0, lim, cap, new byte[cap], 0);
        this.isReadOnly = isReadOnly;
    }

    HeapByteBuffer(byte[] buf, int off, int len) {
        this(buf, off, len, false);
    }

    private HeapByteBuffer(byte[] buf, int off, int len, boolean isReadOnly) {
        super(-1, off, off + len, buf.length, buf, 0);
        this.isReadOnly = isReadOnly;
    }

    private HeapByteBuffer(byte[] buf, int mark, int pos, int lim, int cap, int off, boolean isReadOnly) {
        super(mark, pos, lim, cap, buf, off);
        this.isReadOnly = isReadOnly;
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
        return this.offset + i;
    }

    @Override // java.nio.ByteBuffer
    public byte get() {
        return this.hb[ix(nextGetIndex())];
    }

    @Override // java.nio.ByteBuffer
    public byte get(int i) {
        return this.hb[ix(checkIndex(i))];
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer get(byte[] dst, int offset, int length) {
        checkBounds(offset, length, dst.length);
        if (length <= remaining()) {
            System.arraycopy(this.hb, ix(position()), dst, offset, length);
            position(position() + length);
            return this;
        }
        throw new BufferUnderflowException();
    }

    @Override // java.nio.Buffer, java.nio.ByteBuffer
    public boolean isDirect() {
        return false;
    }

    @Override // java.nio.Buffer
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(byte x) {
        if (!this.isReadOnly) {
            this.hb[ix(nextPutIndex())] = x;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(int i, byte x) {
        if (!this.isReadOnly) {
            this.hb[ix(checkIndex(i))] = x;
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(byte[] src, int offset, int length) {
        if (!this.isReadOnly) {
            checkBounds(offset, length, src.length);
            if (length <= remaining()) {
                System.arraycopy(src, offset, this.hb, ix(position()), length);
                position(position() + length);
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
        return Bits.getChar(this, ix(checkIndex(i, 2)), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public char getCharUnchecked(int i) {
        return Bits.getChar(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int pos, char[] dst, int dstOffset, int length) {
        Memory.unsafeBulkGet(dst, dstOffset, length * 2, this.hb, ix(pos), 2, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putChar(char x) {
        if (!this.isReadOnly) {
            Bits.putChar(this, ix(nextPutIndex(2)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putChar(int i, char x) {
        if (!this.isReadOnly) {
            Bits.putChar(this, ix(checkIndex(i, 2)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putCharUnchecked(int i, char x) {
        Bits.putChar(this, ix(i), x, this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putUnchecked(int pos, char[] src, int srcOffset, int length) {
        Memory.unsafeBulkPut(this.hb, ix(pos), length * 2, src, srcOffset, 2, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public CharBuffer asCharBuffer() {
        int size = remaining() >> 1;
        return new ByteBufferAsCharBuffer(this, -1, 0, size, size, position(), order());
    }

    @Override // java.nio.ByteBuffer
    public short getShort() {
        return Bits.getShort(this, ix(nextGetIndex(2)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public short getShort(int i) {
        return Bits.getShort(this, ix(checkIndex(i, 2)), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public short getShortUnchecked(int i) {
        return Bits.getShort(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int pos, short[] dst, int dstOffset, int length) {
        Memory.unsafeBulkGet(dst, dstOffset, length * 2, this.hb, ix(pos), 2, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putShort(short x) {
        if (!this.isReadOnly) {
            Bits.putShort(this, ix(nextPutIndex(2)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putShort(int i, short x) {
        if (!this.isReadOnly) {
            Bits.putShort(this, ix(checkIndex(i, 2)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putShortUnchecked(int i, short x) {
        Bits.putShort(this, ix(i), x, this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putUnchecked(int pos, short[] src, int srcOffset, int length) {
        Memory.unsafeBulkPut(this.hb, ix(pos), length * 2, src, srcOffset, 2, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ShortBuffer asShortBuffer() {
        int size = remaining() >> 1;
        return new ByteBufferAsShortBuffer(this, -1, 0, size, size, position(), order());
    }

    @Override // java.nio.ByteBuffer
    public int getInt() {
        return Bits.getInt(this, ix(nextGetIndex(4)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public int getInt(int i) {
        return Bits.getInt(this, ix(checkIndex(i, 4)), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public int getIntUnchecked(int i) {
        return Bits.getInt(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int pos, int[] dst, int dstOffset, int length) {
        Memory.unsafeBulkGet(dst, dstOffset, length * 4, this.hb, ix(pos), 4, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putInt(int x) {
        if (!this.isReadOnly) {
            Bits.putInt(this, ix(nextPutIndex(4)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putInt(int i, int x) {
        if (!this.isReadOnly) {
            Bits.putInt(this, ix(checkIndex(i, 4)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putIntUnchecked(int i, int x) {
        Bits.putInt(this, ix(i), x, this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putUnchecked(int pos, int[] src, int srcOffset, int length) {
        Memory.unsafeBulkPut(this.hb, ix(pos), length * 4, src, srcOffset, 4, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public IntBuffer asIntBuffer() {
        int size = remaining() >> 2;
        return new ByteBufferAsIntBuffer(this, -1, 0, size, size, position(), order());
    }

    @Override // java.nio.ByteBuffer
    public long getLong() {
        return Bits.getLong(this, ix(nextGetIndex(8)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public long getLong(int i) {
        return Bits.getLong(this, ix(checkIndex(i, 8)), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public long getLongUnchecked(int i) {
        return Bits.getLong(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int pos, long[] dst, int dstOffset, int length) {
        Memory.unsafeBulkGet(dst, dstOffset, length * 8, this.hb, ix(pos), 8, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putLong(long x) {
        if (!this.isReadOnly) {
            Bits.putLong(this, ix(nextPutIndex(8)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putLong(int i, long x) {
        if (!this.isReadOnly) {
            Bits.putLong(this, ix(checkIndex(i, 8)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putLongUnchecked(int i, long x) {
        Bits.putLong(this, ix(i), x, this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putUnchecked(int pos, long[] src, int srcOffset, int length) {
        Memory.unsafeBulkPut(this.hb, ix(pos), length * 8, src, srcOffset, 8, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public LongBuffer asLongBuffer() {
        int size = remaining() >> 3;
        return new ByteBufferAsLongBuffer(this, -1, 0, size, size, position(), order());
    }

    @Override // java.nio.ByteBuffer
    public float getFloat() {
        return Bits.getFloat(this, ix(nextGetIndex(4)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public float getFloat(int i) {
        return Bits.getFloat(this, ix(checkIndex(i, 4)), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public float getFloatUnchecked(int i) {
        return Bits.getFloat(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int pos, float[] dst, int dstOffset, int length) {
        Memory.unsafeBulkGet(dst, dstOffset, length * 4, this.hb, ix(pos), 4, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putFloat(float x) {
        if (!this.isReadOnly) {
            Bits.putFloat(this, ix(nextPutIndex(4)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putFloat(int i, float x) {
        if (!this.isReadOnly) {
            Bits.putFloat(this, ix(checkIndex(i, 4)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putFloatUnchecked(int i, float x) {
        Bits.putFloat(this, ix(i), x, this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putUnchecked(int pos, float[] src, int srcOffset, int length) {
        Memory.unsafeBulkPut(this.hb, ix(pos), length * 4, src, srcOffset, 4, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public FloatBuffer asFloatBuffer() {
        int size = remaining() >> 2;
        return new ByteBufferAsFloatBuffer(this, -1, 0, size, size, position(), order());
    }

    @Override // java.nio.ByteBuffer
    public double getDouble() {
        return Bits.getDouble(this, ix(nextGetIndex(8)), this.bigEndian);
    }

    @Override // java.nio.ByteBuffer
    public double getDouble(int i) {
        return Bits.getDouble(this, ix(checkIndex(i, 8)), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public double getDoubleUnchecked(int i) {
        return Bits.getDouble(this, ix(i), this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int pos, double[] dst, int dstOffset, int length) {
        Memory.unsafeBulkGet(dst, dstOffset, length * 8, this.hb, ix(pos), 8, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putDouble(double x) {
        if (!this.isReadOnly) {
            Bits.putDouble(this, ix(nextPutIndex(8)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer putDouble(int i, double x) {
        if (!this.isReadOnly) {
            Bits.putDouble(this, ix(checkIndex(i, 8)), x, this.bigEndian);
            return this;
        }
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putDoubleUnchecked(int i, double x) {
        Bits.putDouble(this, ix(i), x, this.bigEndian);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putUnchecked(int pos, double[] src, int srcOffset, int length) {
        Memory.unsafeBulkPut(this.hb, ix(pos), length * 8, src, srcOffset, 8, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public DoubleBuffer asDoubleBuffer() {
        int size = remaining() >> 3;
        return new ByteBufferAsDoubleBuffer(this, -1, 0, size, size, position(), order());
    }
}
