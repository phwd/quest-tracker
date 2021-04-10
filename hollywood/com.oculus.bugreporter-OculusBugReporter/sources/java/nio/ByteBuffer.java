package java.nio;

import java.nio.DirectByteBuffer;
import libcore.io.Memory;

public abstract class ByteBuffer extends Buffer implements Comparable<ByteBuffer> {
    boolean bigEndian;
    final byte[] hb;
    boolean isReadOnly;
    boolean nativeByteOrder;
    final int offset;

    /* access modifiers changed from: package-private */
    public abstract byte _get(int i);

    /* access modifiers changed from: package-private */
    public abstract void _put(int i, byte b);

    public abstract CharBuffer asCharBuffer();

    public abstract DoubleBuffer asDoubleBuffer();

    public abstract FloatBuffer asFloatBuffer();

    public abstract IntBuffer asIntBuffer();

    public abstract LongBuffer asLongBuffer();

    public abstract ByteBuffer asReadOnlyBuffer();

    public abstract ShortBuffer asShortBuffer();

    public abstract ByteBuffer compact();

    public abstract ByteBuffer duplicate();

    public abstract byte get();

    public abstract byte get(int i);

    public abstract char getChar();

    public abstract char getChar(int i);

    /* access modifiers changed from: package-private */
    public abstract char getCharUnchecked(int i);

    public abstract double getDouble();

    public abstract double getDouble(int i);

    /* access modifiers changed from: package-private */
    public abstract double getDoubleUnchecked(int i);

    public abstract float getFloat();

    public abstract float getFloat(int i);

    /* access modifiers changed from: package-private */
    public abstract float getFloatUnchecked(int i);

    public abstract int getInt();

    public abstract int getInt(int i);

    /* access modifiers changed from: package-private */
    public abstract int getIntUnchecked(int i);

    public abstract long getLong();

    public abstract long getLong(int i);

    /* access modifiers changed from: package-private */
    public abstract long getLongUnchecked(int i);

    public abstract short getShort();

    public abstract short getShort(int i);

    /* access modifiers changed from: package-private */
    public abstract short getShortUnchecked(int i);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, char[] cArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, double[] dArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, float[] fArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, int[] iArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, long[] jArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, short[] sArr, int i2, int i3);

    @Override // java.nio.Buffer
    public abstract boolean isDirect();

    public abstract ByteBuffer put(byte b);

    public abstract ByteBuffer put(int i, byte b);

    public abstract ByteBuffer putChar(char c);

    public abstract ByteBuffer putChar(int i, char c);

    /* access modifiers changed from: package-private */
    public abstract void putCharUnchecked(int i, char c);

    public abstract ByteBuffer putDouble(double d);

    public abstract ByteBuffer putDouble(int i, double d);

    /* access modifiers changed from: package-private */
    public abstract void putDoubleUnchecked(int i, double d);

    public abstract ByteBuffer putFloat(float f);

    public abstract ByteBuffer putFloat(int i, float f);

    /* access modifiers changed from: package-private */
    public abstract void putFloatUnchecked(int i, float f);

    public abstract ByteBuffer putInt(int i);

    public abstract ByteBuffer putInt(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void putIntUnchecked(int i, int i2);

    public abstract ByteBuffer putLong(int i, long j);

    public abstract ByteBuffer putLong(long j);

    /* access modifiers changed from: package-private */
    public abstract void putLongUnchecked(int i, long j);

    public abstract ByteBuffer putShort(int i, short s);

    public abstract ByteBuffer putShort(short s);

    /* access modifiers changed from: package-private */
    public abstract void putShortUnchecked(int i, short s);

    /* access modifiers changed from: package-private */
    public abstract void putUnchecked(int i, char[] cArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void putUnchecked(int i, double[] dArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void putUnchecked(int i, float[] fArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void putUnchecked(int i, int[] iArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void putUnchecked(int i, long[] jArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void putUnchecked(int i, short[] sArr, int i2, int i3);

    public abstract ByteBuffer slice();

    ByteBuffer(int mark, int pos, int lim, int cap, byte[] hb2, int offset2) {
        super(mark, pos, lim, cap, 0);
        boolean z = true;
        this.bigEndian = true;
        this.nativeByteOrder = Bits.byteOrder() != ByteOrder.BIG_ENDIAN ? false : z;
        this.hb = hb2;
        this.offset = offset2;
    }

    ByteBuffer(int mark, int pos, int lim, int cap) {
        this(mark, pos, lim, cap, null, 0);
    }

    public static ByteBuffer allocateDirect(int capacity) {
        return new DirectByteBuffer(capacity, new DirectByteBuffer.MemoryRef(capacity));
    }

    public static ByteBuffer allocate(int capacity) {
        if (capacity >= 0) {
            return new HeapByteBuffer(capacity, capacity);
        }
        throw new IllegalArgumentException();
    }

    public static ByteBuffer wrap(byte[] array, int offset2, int length) {
        try {
            return new HeapByteBuffer(array, offset2, length);
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static ByteBuffer wrap(byte[] array) {
        return wrap(array, 0, array.length);
    }

    public ByteBuffer get(byte[] dst, int offset2, int length) {
        checkBounds(offset2, length, dst.length);
        if (length <= remaining()) {
            int end = offset2 + length;
            for (int i = offset2; i < end; i++) {
                dst[i] = get();
            }
            return this;
        }
        throw new BufferUnderflowException();
    }

    public ByteBuffer get(byte[] dst) {
        return get(dst, 0, dst.length);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ByteBuffer put(ByteBuffer src) {
        int dstOffset;
        byte[] bArr;
        if (src == this) {
            throw new IllegalArgumentException();
        } else if (!isReadOnly()) {
            int n = src.remaining();
            if (n <= remaining()) {
                if (this.hb == null || (bArr = src.hb) == null) {
                    Object srcObject = src.isDirect() ? src : src.hb;
                    int srcOffset = src.position();
                    if (!src.isDirect()) {
                        srcOffset += src.offset;
                    }
                    Object dstObject = isDirect() ? this : this.hb;
                    int dstOffset2 = position();
                    if (!isDirect()) {
                        dstOffset = dstOffset2 + this.offset;
                    } else {
                        dstOffset = dstOffset2;
                    }
                    Memory.memmove(dstObject, dstOffset, srcObject, srcOffset, (long) n);
                } else {
                    System.arraycopy(bArr, src.position() + src.offset, this.hb, position() + this.offset, n);
                }
                src.position(src.limit());
                position(position() + n);
                return this;
            }
            throw new BufferOverflowException();
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    public ByteBuffer put(byte[] src, int offset2, int length) {
        checkBounds(offset2, length, src.length);
        if (length <= remaining()) {
            int end = offset2 + length;
            for (int i = offset2; i < end; i++) {
                put(src[i]);
            }
            return this;
        }
        throw new BufferOverflowException();
    }

    public final ByteBuffer put(byte[] src) {
        return put(src, 0, src.length);
    }

    @Override // java.nio.Buffer
    public final boolean hasArray() {
        return this.hb != null && !this.isReadOnly;
    }

    @Override // java.nio.Buffer
    public final byte[] array() {
        byte[] bArr = this.hb;
        if (bArr == null) {
            throw new UnsupportedOperationException();
        } else if (!this.isReadOnly) {
            return bArr;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    @Override // java.nio.Buffer
    public final int arrayOffset() {
        if (this.hb == null) {
            throw new UnsupportedOperationException();
        } else if (!this.isReadOnly) {
            return this.offset;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    @Override // java.nio.Buffer
    public Buffer position(int newPosition) {
        return super.position(newPosition);
    }

    @Override // java.nio.Buffer
    public Buffer limit(int newLimit) {
        return super.limit(newLimit);
    }

    @Override // java.nio.Buffer
    public Buffer mark() {
        return super.mark();
    }

    @Override // java.nio.Buffer
    public Buffer reset() {
        return super.reset();
    }

    @Override // java.nio.Buffer
    public Buffer clear() {
        return super.clear();
    }

    @Override // java.nio.Buffer
    public Buffer flip() {
        return super.flip();
    }

    @Override // java.nio.Buffer
    public Buffer rewind() {
        return super.rewind();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append("[pos=");
        sb.append(position());
        sb.append(" lim=");
        sb.append(limit());
        sb.append(" cap=");
        sb.append(capacity());
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        int h = 1;
        int p = position();
        for (int i = limit() - 1; i >= p; i--) {
            h = (h * 31) + get(i);
        }
        return h;
    }

    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }
        if (!(ob instanceof ByteBuffer)) {
            return false;
        }
        ByteBuffer that = (ByteBuffer) ob;
        if (remaining() != that.remaining()) {
            return false;
        }
        int p = position();
        int i = limit() - 1;
        int j = that.limit() - 1;
        while (i >= p) {
            if (!equals(get(i), that.get(j))) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }

    private static boolean equals(byte x, byte y) {
        return x == y;
    }

    public int compareTo(ByteBuffer that) {
        int n = position() + Math.min(remaining(), that.remaining());
        int i = position();
        int j = that.position();
        while (i < n) {
            int cmp = compare(get(i), that.get(j));
            if (cmp != 0) {
                return cmp;
            }
            i++;
            j++;
        }
        return remaining() - that.remaining();
    }

    private static int compare(byte x, byte y) {
        return Byte.compare(x, y);
    }

    public final ByteOrder order() {
        return this.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    public final ByteBuffer order(ByteOrder bo) {
        boolean z = true;
        this.bigEndian = bo == ByteOrder.BIG_ENDIAN;
        if (this.bigEndian != (Bits.byteOrder() == ByteOrder.BIG_ENDIAN)) {
            z = false;
        }
        this.nativeByteOrder = z;
        return this;
    }

    public boolean isAccessible() {
        return true;
    }

    public void setAccessible(boolean value) {
        throw new UnsupportedOperationException();
    }
}
