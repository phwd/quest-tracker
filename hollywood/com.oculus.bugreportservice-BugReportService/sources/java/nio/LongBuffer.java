package java.nio;

public abstract class LongBuffer extends Buffer implements Comparable {
    final long[] hb;
    boolean isReadOnly;
    final int offset;

    private static boolean equals(long j, long j2) {
        return j == j2;
    }

    public abstract long get();

    public abstract long get(int i);

    LongBuffer(int i, int i2, int i3, int i4, long[] jArr, int i5) {
        super(i, i2, i3, i4, 3);
        this.hb = jArr;
        this.offset = i5;
    }

    LongBuffer(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0);
    }

    public LongBuffer get(long[] jArr, int i, int i2) {
        Buffer.checkBounds(i, i2, jArr.length);
        if (i2 <= remaining()) {
            int i3 = i2 + i;
            while (i < i3) {
                jArr[i] = get();
                i++;
            }
            return this;
        }
        throw new BufferUnderflowException();
    }

    public LongBuffer get(long[] jArr) {
        get(jArr, 0, jArr.length);
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
            i = (i * 31) + ((int) get(limit));
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongBuffer)) {
            return false;
        }
        LongBuffer longBuffer = (LongBuffer) obj;
        if (remaining() != longBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = longBuffer.limit() - 1;
        while (limit >= position) {
            if (!equals(get(limit), longBuffer.get(limit2))) {
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    public int compareTo(LongBuffer longBuffer) {
        int position = position() + Math.min(remaining(), longBuffer.remaining());
        int position2 = position();
        int position3 = longBuffer.position();
        while (position2 < position) {
            int compare = compare(get(position2), longBuffer.get(position3));
            if (compare != 0) {
                return compare;
            }
            position2++;
            position3++;
        }
        return remaining() - longBuffer.remaining();
    }

    private static int compare(long j, long j2) {
        return Long.compare(j, j2);
    }
}
