package java.nio;

public abstract class IntBuffer extends Buffer implements Comparable {
    final int[] hb;
    boolean isReadOnly;
    final int offset;

    private static boolean equals(int i, int i2) {
        return i == i2;
    }

    public abstract int get();

    public abstract int get(int i);

    IntBuffer(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        super(i, i2, i3, i4, 2);
        this.hb = iArr;
        this.offset = i5;
    }

    IntBuffer(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0);
    }

    public IntBuffer get(int[] iArr, int i, int i2) {
        Buffer.checkBounds(i, i2, iArr.length);
        if (i2 <= remaining()) {
            int i3 = i2 + i;
            while (i < i3) {
                iArr[i] = get();
                i++;
            }
            return this;
        }
        throw new BufferUnderflowException();
    }

    public IntBuffer get(int[] iArr) {
        get(iArr, 0, iArr.length);
        return this;
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
        if (!(obj instanceof IntBuffer)) {
            return false;
        }
        IntBuffer intBuffer = (IntBuffer) obj;
        if (remaining() != intBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = intBuffer.limit() - 1;
        while (limit >= position) {
            if (!equals(get(limit), intBuffer.get(limit2))) {
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    public int compareTo(IntBuffer intBuffer) {
        int position = position() + Math.min(remaining(), intBuffer.remaining());
        int position2 = position();
        int position3 = intBuffer.position();
        while (position2 < position) {
            int compare = compare(get(position2), intBuffer.get(position3));
            if (compare != 0) {
                return compare;
            }
            position2++;
            position3++;
        }
        return remaining() - intBuffer.remaining();
    }

    private static int compare(int i, int i2) {
        return Integer.compare(i, i2);
    }
}
