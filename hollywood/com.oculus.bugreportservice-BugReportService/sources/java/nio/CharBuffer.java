package java.nio;

public abstract class CharBuffer extends Buffer implements Comparable, Appendable, CharSequence, Readable {
    final char[] hb;
    boolean isReadOnly;
    final int offset;

    private static boolean equals(char c, char c2) {
        return c == c2;
    }

    public abstract CharBuffer duplicate();

    public abstract char get();

    public abstract char get(int i);

    public abstract boolean isDirect();

    public abstract CharBuffer put(char c);

    public abstract CharBuffer slice();

    @Override // java.lang.CharSequence
    public abstract CharBuffer subSequence(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract String toString(int i, int i2);

    CharBuffer(int i, int i2, int i3, int i4, char[] cArr, int i5) {
        super(i, i2, i3, i4, 1);
        this.hb = cArr;
        this.offset = i5;
    }

    CharBuffer(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0);
    }

    public static CharBuffer allocate(int i) {
        if (i >= 0) {
            return new HeapCharBuffer(i, i);
        }
        throw new IllegalArgumentException();
    }

    public static CharBuffer wrap(char[] cArr, int i, int i2) {
        try {
            return new HeapCharBuffer(cArr, i, i2);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static CharBuffer wrap(char[] cArr) {
        return wrap(cArr, 0, cArr.length);
    }

    public static CharBuffer wrap(CharSequence charSequence, int i, int i2) {
        try {
            return new StringCharBuffer(charSequence, i, i2);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static CharBuffer wrap(CharSequence charSequence) {
        return wrap(charSequence, 0, charSequence.length());
    }

    public CharBuffer get(char[] cArr, int i, int i2) {
        Buffer.checkBounds(i, i2, cArr.length);
        if (i2 <= remaining()) {
            int i3 = i2 + i;
            while (i < i3) {
                cArr[i] = get();
                i++;
            }
            return this;
        }
        throw new BufferUnderflowException();
    }

    public CharBuffer get(char[] cArr) {
        return get(cArr, 0, cArr.length);
    }

    public CharBuffer put(CharBuffer charBuffer) {
        if (charBuffer == this) {
            throw new IllegalArgumentException();
        } else if (!isReadOnly()) {
            int remaining = charBuffer.remaining();
            if (remaining <= remaining()) {
                for (int i = 0; i < remaining; i++) {
                    put(charBuffer.get());
                }
                return this;
            }
            throw new BufferOverflowException();
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    public CharBuffer put(String str, int i, int i2) {
        int i3 = i2 - i;
        Buffer.checkBounds(i, i3, str.length());
        if (i == i2) {
            return this;
        }
        if (isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (i3 <= remaining()) {
            while (i < i2) {
                put(str.charAt(i));
                i++;
            }
            return this;
        } else {
            throw new BufferOverflowException();
        }
    }

    public final CharBuffer put(String str) {
        put(str, 0, str.length());
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
        if (!(obj instanceof CharBuffer)) {
            return false;
        }
        CharBuffer charBuffer = (CharBuffer) obj;
        if (remaining() != charBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = charBuffer.limit() - 1;
        while (limit >= position) {
            if (!equals(get(limit), charBuffer.get(limit2))) {
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    public int compareTo(CharBuffer charBuffer) {
        int position = position() + Math.min(remaining(), charBuffer.remaining());
        int position2 = position();
        int position3 = charBuffer.position();
        while (position2 < position) {
            int compare = compare(get(position2), charBuffer.get(position3));
            if (compare != 0) {
                return compare;
            }
            position2++;
            position3++;
        }
        return remaining() - charBuffer.remaining();
    }

    private static int compare(char c, char c2) {
        return Character.compare(c, c2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return toString(position(), limit());
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return remaining();
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        int position = position();
        checkIndex(i, 1);
        return get(position + i);
    }

    @Override // java.lang.Appendable
    public CharBuffer append(CharSequence charSequence) {
        if (charSequence == null) {
            put("null");
            return this;
        }
        put(charSequence.toString());
        return this;
    }

    @Override // java.lang.Appendable
    public CharBuffer append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        put(charSequence.subSequence(i, i2).toString());
        return this;
    }

    @Override // java.lang.Appendable
    public CharBuffer append(char c) {
        return put(c);
    }
}
