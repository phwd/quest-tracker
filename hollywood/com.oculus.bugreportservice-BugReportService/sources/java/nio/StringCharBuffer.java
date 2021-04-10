package java.nio;

/* access modifiers changed from: package-private */
public class StringCharBuffer extends CharBuffer {
    CharSequence str;

    @Override // java.nio.CharBuffer
    public boolean isDirect() {
        return false;
    }

    @Override // java.nio.Buffer
    public final boolean isReadOnly() {
        return true;
    }

    StringCharBuffer(CharSequence charSequence, int i, int i2) {
        super(-1, i, i2, charSequence.length());
        int length = charSequence.length();
        if (i < 0 || i > length || i2 < i || i2 > length) {
            throw new IndexOutOfBoundsException();
        }
        this.str = charSequence;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer slice() {
        return new StringCharBuffer(this.str, -1, 0, remaining(), remaining(), this.offset + position());
    }

    private StringCharBuffer(CharSequence charSequence, int i, int i2, int i3, int i4, int i5) {
        super(i, i2, i3, i4, null, i5);
        this.str = charSequence;
    }

    @Override // java.nio.CharBuffer
    public CharBuffer duplicate() {
        return new StringCharBuffer(this.str, markValue(), position(), limit(), capacity(), this.offset);
    }

    @Override // java.nio.CharBuffer
    public final char get() {
        return this.str.charAt(nextGetIndex() + this.offset);
    }

    @Override // java.nio.CharBuffer
    public final char get(int i) {
        CharSequence charSequence = this.str;
        checkIndex(i);
        return charSequence.charAt(i + this.offset);
    }

    @Override // java.nio.CharBuffer
    public final CharBuffer put(char c) {
        throw new ReadOnlyBufferException();
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.CharBuffer
    public final String toString(int i, int i2) {
        String charSequence = this.str.toString();
        int i3 = this.offset;
        return charSequence.substring(i + i3, i2 + i3);
    }

    @Override // java.lang.CharSequence, java.nio.CharBuffer, java.nio.CharBuffer
    public final CharBuffer subSequence(int i, int i2) {
        try {
            int position = position();
            CharSequence charSequence = this.str;
            checkIndex(i, position);
            checkIndex(i2, position);
            return new StringCharBuffer(charSequence, -1, position + i, position + i2, capacity(), this.offset);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException();
        }
    }
}
