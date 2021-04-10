package java.nio;

/* access modifiers changed from: package-private */
public class StringCharBuffer extends CharBuffer {
    CharSequence str;

    StringCharBuffer(CharSequence s, int start, int end) {
        super(-1, start, end, s.length());
        int n = s.length();
        if (start < 0 || start > n || end < start || end > n) {
            throw new IndexOutOfBoundsException();
        }
        this.str = s;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer slice() {
        return new StringCharBuffer(this.str, -1, 0, remaining(), remaining(), this.offset + position());
    }

    private StringCharBuffer(CharSequence s, int mark, int pos, int limit, int cap, int offset) {
        super(mark, pos, limit, cap, null, offset);
        this.str = s;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer duplicate() {
        return new StringCharBuffer(this.str, markValue(), position(), limit(), capacity(), this.offset);
    }

    @Override // java.nio.CharBuffer
    public CharBuffer asReadOnlyBuffer() {
        return duplicate();
    }

    @Override // java.nio.CharBuffer
    public final char get() {
        return this.str.charAt(nextGetIndex() + this.offset);
    }

    @Override // java.nio.CharBuffer
    public final char get(int index) {
        return this.str.charAt(checkIndex(index) + this.offset);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public char getUnchecked(int index) {
        return this.str.charAt(this.offset + index);
    }

    @Override // java.nio.CharBuffer
    public final CharBuffer put(char c) {
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public final CharBuffer put(int index, char c) {
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.CharBuffer
    public final CharBuffer compact() {
        throw new ReadOnlyBufferException();
    }

    @Override // java.nio.Buffer
    public final boolean isReadOnly() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public final String toString(int start, int end) {
        return this.str.toString().substring(this.offset + start, this.offset + end);
    }

    @Override // java.lang.CharSequence, java.nio.CharBuffer, java.nio.CharBuffer
    public final CharBuffer subSequence(int start, int end) {
        try {
            int pos = position();
            return new StringCharBuffer(this.str, -1, pos + checkIndex(start, pos), pos + checkIndex(end, pos), capacity(), this.offset);
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // java.nio.CharBuffer, java.nio.Buffer
    public boolean isDirect() {
        return false;
    }

    @Override // java.nio.CharBuffer
    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
}
