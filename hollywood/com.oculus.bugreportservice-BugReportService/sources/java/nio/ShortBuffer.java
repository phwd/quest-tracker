package java.nio;

public abstract class ShortBuffer extends Buffer implements Comparable {
    final short[] hb;
    boolean isReadOnly;
    final int offset;

    private static boolean equals(short s, short s2) {
        return s == s2;
    }

    public abstract short get();

    public abstract short get(int i);

    ShortBuffer(int i, int i2, int i3, int i4, short[] sArr, int i5) {
        super(i, i2, i3, i4, 1);
        this.hb = sArr;
        this.offset = i5;
    }

    ShortBuffer(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0);
    }

    public ShortBuffer get(short[] sArr, int i, int i2) {
        Buffer.checkBounds(i, i2, sArr.length);
        if (i2 <= remaining()) {
            int i3 = i2 + i;
            while (i < i3) {
                sArr[i] = get();
                i++;
            }
            return this;
        }
        throw new BufferUnderflowException();
    }

    public ShortBuffer get(short[] sArr) {
        get(sArr, 0, sArr.length);
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
        if (!(obj instanceof ShortBuffer)) {
            return false;
        }
        ShortBuffer shortBuffer = (ShortBuffer) obj;
        if (remaining() != shortBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = shortBuffer.limit() - 1;
        while (limit >= position) {
            if (!equals(get(limit), shortBuffer.get(limit2))) {
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    public int compareTo(ShortBuffer shortBuffer) {
        int position = position() + Math.min(remaining(), shortBuffer.remaining());
        int position2 = position();
        int position3 = shortBuffer.position();
        while (position2 < position) {
            int compare = compare(get(position2), shortBuffer.get(position3));
            if (compare != 0) {
                return compare;
            }
            position2++;
            position3++;
        }
        return remaining() - shortBuffer.remaining();
    }

    private static int compare(short s, short s2) {
        return Short.compare(s, s2);
    }
}
