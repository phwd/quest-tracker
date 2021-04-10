package java.nio;

import java.nio.DirectByteBuffer;

public abstract class ByteBuffer extends Buffer implements Comparable {
    boolean bigEndian;
    final byte[] hb;
    boolean isReadOnly;
    boolean nativeByteOrder;
    final int offset;

    private static boolean equals(byte b, byte b2) {
        return b == b2;
    }

    /* access modifiers changed from: package-private */
    public abstract byte _get(int i);

    /* access modifiers changed from: package-private */
    public abstract void _put(int i, byte b);

    public abstract CharBuffer asCharBuffer();

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

    public abstract int getInt();

    public abstract int getInt(int i);

    /* access modifiers changed from: package-private */
    public abstract int getIntUnchecked(int i);

    /* access modifiers changed from: package-private */
    public abstract long getLongUnchecked(int i);

    /* access modifiers changed from: package-private */
    public abstract short getShortUnchecked(int i);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, char[] cArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, int[] iArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, long[] jArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void getUnchecked(int i, short[] sArr, int i2, int i3);

    public abstract boolean isDirect();

    public abstract ByteBuffer put(byte b);

    /* access modifiers changed from: package-private */
    public abstract void putCharUnchecked(int i, char c);

    public abstract ByteBuffer slice();

    ByteBuffer(int i, int i2, int i3, int i4, byte[] bArr, int i5) {
        super(i, i2, i3, i4, 0);
        boolean z = true;
        this.bigEndian = true;
        this.nativeByteOrder = Bits.byteOrder() != ByteOrder.BIG_ENDIAN ? false : z;
        this.hb = bArr;
        this.offset = i5;
    }

    ByteBuffer(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0);
    }

    public static ByteBuffer allocateDirect(int i) {
        return new DirectByteBuffer(i, new DirectByteBuffer.MemoryRef(i));
    }

    public static ByteBuffer allocate(int i) {
        if (i >= 0) {
            return new HeapByteBuffer(i, i);
        }
        throw new IllegalArgumentException();
    }

    public static ByteBuffer wrap(byte[] bArr, int i, int i2) {
        try {
            return new HeapByteBuffer(bArr, i, i2);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static ByteBuffer wrap(byte[] bArr) {
        return wrap(bArr, 0, bArr.length);
    }

    public ByteBuffer get(byte[] bArr, int i, int i2) {
        Buffer.checkBounds(i, i2, bArr.length);
        if (i2 <= remaining()) {
            int i3 = i2 + i;
            while (i < i3) {
                bArr[i] = get();
                i++;
            }
            return this;
        }
        throw new BufferUnderflowException();
    }

    public ByteBuffer get(byte[] bArr) {
        return get(bArr, 0, bArr.length);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v12, types: [byte[]] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.nio.ByteBuffer put(java.nio.ByteBuffer r9) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.ByteBuffer.put(java.nio.ByteBuffer):java.nio.ByteBuffer");
    }

    public ByteBuffer put(byte[] bArr, int i, int i2) {
        Buffer.checkBounds(i, i2, bArr.length);
        if (i2 <= remaining()) {
            int i3 = i2 + i;
            while (i < i3) {
                put(bArr[i]);
                i++;
            }
            return this;
        }
        throw new BufferOverflowException();
    }

    public final ByteBuffer put(byte[] bArr) {
        return put(bArr, 0, bArr.length);
    }

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
    public Buffer position(int i) {
        super.position(i);
        return this;
    }

    @Override // java.nio.Buffer
    public Buffer limit(int i) {
        super.limit(i);
        return this;
    }

    @Override // java.nio.Buffer
    public Buffer mark() {
        super.mark();
        return this;
    }

    @Override // java.nio.Buffer
    public Buffer reset() {
        super.reset();
        return this;
    }

    @Override // java.nio.Buffer
    public Buffer clear() {
        super.clear();
        return this;
    }

    @Override // java.nio.Buffer
    public Buffer flip() {
        super.flip();
        return this;
    }

    @Override // java.nio.Buffer
    public Buffer rewind() {
        super.rewind();
        return this;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        stringBuffer.append("[pos=");
        stringBuffer.append(position());
        stringBuffer.append(" lim=");
        stringBuffer.append(limit());
        stringBuffer.append(" cap=");
        stringBuffer.append(capacity());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public int hashCode() {
        int position = position();
        int i = 1;
        for (int limit = limit() - 1; limit >= position; limit--) {
            i = (i * 31) + get(limit);
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ByteBuffer)) {
            return false;
        }
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        if (remaining() != byteBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = byteBuffer.limit() - 1;
        while (limit >= position) {
            if (!equals(get(limit), byteBuffer.get(limit2))) {
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    public int compareTo(ByteBuffer byteBuffer) {
        int position = position() + Math.min(remaining(), byteBuffer.remaining());
        int position2 = position();
        int position3 = byteBuffer.position();
        while (position2 < position) {
            int compare = compare(get(position2), byteBuffer.get(position3));
            if (compare != 0) {
                return compare;
            }
            position2++;
            position3++;
        }
        return remaining() - byteBuffer.remaining();
    }

    private static int compare(byte b, byte b2) {
        return Byte.compare(b, b2);
    }

    public final ByteOrder order() {
        return this.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    public final ByteBuffer order(ByteOrder byteOrder) {
        boolean z = true;
        this.bigEndian = byteOrder == ByteOrder.BIG_ENDIAN;
        if (this.bigEndian != (Bits.byteOrder() == ByteOrder.BIG_ENDIAN)) {
            z = false;
        }
        this.nativeByteOrder = z;
        return this;
    }
}
