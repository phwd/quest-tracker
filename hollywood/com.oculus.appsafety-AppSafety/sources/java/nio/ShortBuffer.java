package java.nio;

public abstract class ShortBuffer extends Buffer implements Comparable<ShortBuffer> {
    final short[] hb;
    boolean isReadOnly;
    final int offset;

    public abstract ShortBuffer asReadOnlyBuffer();

    public abstract ShortBuffer compact();

    public abstract ShortBuffer duplicate();

    public abstract short get();

    public abstract short get(int i);

    @Override // java.nio.Buffer
    public abstract boolean isDirect();

    public abstract ByteOrder order();

    public abstract ShortBuffer put(int i, short s);

    public abstract ShortBuffer put(short s);

    public abstract ShortBuffer slice();

    ShortBuffer(int mark, int pos, int lim, int cap, short[] hb2, int offset2) {
        super(mark, pos, lim, cap, 1);
        this.hb = hb2;
        this.offset = offset2;
    }

    ShortBuffer(int mark, int pos, int lim, int cap) {
        this(mark, pos, lim, cap, null, 0);
    }

    public static ShortBuffer allocate(int capacity) {
        if (capacity >= 0) {
            return new HeapShortBuffer(capacity, capacity);
        }
        throw new IllegalArgumentException();
    }

    public static ShortBuffer wrap(short[] array, int offset2, int length) {
        try {
            return new HeapShortBuffer(array, offset2, length);
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static ShortBuffer wrap(short[] array) {
        return wrap(array, 0, array.length);
    }

    public ShortBuffer get(short[] dst, int offset2, int length) {
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

    public ShortBuffer get(short[] dst) {
        return get(dst, 0, dst.length);
    }

    public ShortBuffer put(ShortBuffer src) {
        if (src == this) {
            throw new IllegalArgumentException();
        } else if (!isReadOnly()) {
            int n = src.remaining();
            if (n <= remaining()) {
                for (int i = 0; i < n; i++) {
                    put(src.get());
                }
                return this;
            }
            throw new BufferOverflowException();
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    public ShortBuffer put(short[] src, int offset2, int length) {
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

    public final ShortBuffer put(short[] src) {
        return put(src, 0, src.length);
    }

    @Override // java.nio.Buffer
    public final boolean hasArray() {
        return this.hb != null && !this.isReadOnly;
    }

    @Override // java.nio.Buffer
    public final short[] array() {
        short[] sArr = this.hb;
        if (sArr == null) {
            throw new UnsupportedOperationException();
        } else if (!this.isReadOnly) {
            return sArr;
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
        if (!(ob instanceof ShortBuffer)) {
            return false;
        }
        ShortBuffer that = (ShortBuffer) ob;
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

    private static boolean equals(short x, short y) {
        return x == y;
    }

    public int compareTo(ShortBuffer that) {
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

    private static int compare(short x, short y) {
        return Short.compare(x, y);
    }
}
