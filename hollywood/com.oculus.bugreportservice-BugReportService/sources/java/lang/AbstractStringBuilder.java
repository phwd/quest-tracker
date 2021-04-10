package java.lang;

import java.util.Arrays;
import sun.misc.FloatingDecimal;

/* access modifiers changed from: package-private */
public abstract class AbstractStringBuilder implements Appendable, CharSequence {
    int count;
    char[] value;

    AbstractStringBuilder() {
    }

    AbstractStringBuilder(int i) {
        this.value = new char[i];
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.count;
    }

    public void ensureCapacity(int i) {
        if (i > 0) {
            ensureCapacityInternal(i);
        }
    }

    private void ensureCapacityInternal(int i) {
        char[] cArr = this.value;
        if (i - cArr.length > 0) {
            this.value = Arrays.copyOf(cArr, newCapacity(i));
        }
    }

    private int newCapacity(int i) {
        int length = (this.value.length << 1) + 2;
        if (length - i < 0) {
            length = i;
        }
        return (length <= 0 || 2147483639 - length < 0) ? hugeCapacity(i) : length;
    }

    private int hugeCapacity(int i) {
        if (Integer.MAX_VALUE - i < 0) {
            throw new OutOfMemoryError();
        } else if (i > 2147483639) {
            return i;
        } else {
            return 2147483639;
        }
    }

    public void setLength(int i) {
        if (i >= 0) {
            ensureCapacityInternal(i);
            int i2 = this.count;
            if (i2 < i) {
                Arrays.fill(this.value, i2, i, (char) 0);
            }
            this.count = i;
            return;
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        if (i >= 0 && i < this.count) {
            return this.value[i];
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public int codePointAt(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.count)) {
            return Character.codePointAtImpl(this.value, i, i2);
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public int codePointBefore(int i) {
        int i2 = i - 1;
        if (i2 >= 0 && i2 < this.count) {
            return Character.codePointBeforeImpl(this.value, i, 0);
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public int offsetByCodePoints(int i, int i2) {
        int i3;
        if (i >= 0 && i <= (i3 = this.count)) {
            return Character.offsetByCodePointsImpl(this.value, 0, i3, i, i2);
        }
        throw new IndexOutOfBoundsException();
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(i);
        } else if (i2 < 0 || i2 > this.count) {
            throw new StringIndexOutOfBoundsException(i2);
        } else if (i <= i2) {
            System.arraycopy((Object) this.value, i, (Object) cArr, i3, i2 - i);
        } else {
            throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
        }
    }

    public void setCharAt(int i, char c) {
        if (i < 0 || i >= this.count) {
            throw new StringIndexOutOfBoundsException(i);
        }
        this.value[i] = c;
    }

    public AbstractStringBuilder append(String str) {
        if (str == null) {
            appendNull();
            return this;
        }
        int length = str.length();
        ensureCapacityInternal(this.count + length);
        str.getChars(0, length, this.value, this.count);
        this.count += length;
        return this;
    }

    public AbstractStringBuilder append(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            appendNull();
            return this;
        }
        int length = stringBuffer.length();
        ensureCapacityInternal(this.count + length);
        stringBuffer.getChars(0, length, this.value, this.count);
        this.count += length;
        return this;
    }

    /* access modifiers changed from: package-private */
    public AbstractStringBuilder append(AbstractStringBuilder abstractStringBuilder) {
        if (abstractStringBuilder == null) {
            appendNull();
            return this;
        }
        int length = abstractStringBuilder.length();
        ensureCapacityInternal(this.count + length);
        abstractStringBuilder.getChars(0, length, this.value, this.count);
        this.count += length;
        return this;
    }

    @Override // java.lang.Appendable
    public AbstractStringBuilder append(CharSequence charSequence) {
        if (charSequence == null) {
            appendNull();
            return this;
        } else if (charSequence instanceof String) {
            return append((String) charSequence);
        } else {
            if (charSequence instanceof AbstractStringBuilder) {
                return append((AbstractStringBuilder) charSequence);
            }
            return append(charSequence, 0, charSequence.length());
        }
    }

    private AbstractStringBuilder appendNull() {
        int i = this.count;
        ensureCapacityInternal(i + 4);
        char[] cArr = this.value;
        int i2 = i + 1;
        cArr[i] = 'n';
        int i3 = i2 + 1;
        cArr[i2] = 'u';
        int i4 = i3 + 1;
        cArr[i3] = 'l';
        cArr[i4] = 'l';
        this.count = i4 + 1;
        return this;
    }

    @Override // java.lang.Appendable
    public AbstractStringBuilder append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (i < 0 || i > i2 || i2 > charSequence.length()) {
            throw new IndexOutOfBoundsException("start " + i + ", end " + i2 + ", s.length() " + charSequence.length());
        }
        int i3 = i2 - i;
        ensureCapacityInternal(this.count + i3);
        int i4 = this.count;
        while (i < i2) {
            this.value[i4] = charSequence.charAt(i);
            i++;
            i4++;
        }
        this.count += i3;
        return this;
    }

    public AbstractStringBuilder append(char[] cArr) {
        int length = cArr.length;
        ensureCapacityInternal(this.count + length);
        System.arraycopy((Object) cArr, 0, (Object) this.value, this.count, length);
        this.count += length;
        return this;
    }

    public AbstractStringBuilder append(char[] cArr, int i, int i2) {
        if (i2 > 0) {
            ensureCapacityInternal(this.count + i2);
        }
        System.arraycopy((Object) cArr, i, (Object) this.value, this.count, i2);
        this.count += i2;
        return this;
    }

    public AbstractStringBuilder append(boolean z) {
        if (z) {
            ensureCapacityInternal(this.count + 4);
            char[] cArr = this.value;
            int i = this.count;
            this.count = i + 1;
            cArr[i] = 't';
            int i2 = this.count;
            this.count = i2 + 1;
            cArr[i2] = 'r';
            int i3 = this.count;
            this.count = i3 + 1;
            cArr[i3] = 'u';
            int i4 = this.count;
            this.count = i4 + 1;
            cArr[i4] = 'e';
        } else {
            ensureCapacityInternal(this.count + 5);
            char[] cArr2 = this.value;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr2[i5] = 'f';
            int i6 = this.count;
            this.count = i6 + 1;
            cArr2[i6] = 'a';
            int i7 = this.count;
            this.count = i7 + 1;
            cArr2[i7] = 'l';
            int i8 = this.count;
            this.count = i8 + 1;
            cArr2[i8] = 's';
            int i9 = this.count;
            this.count = i9 + 1;
            cArr2[i9] = 'e';
        }
        return this;
    }

    @Override // java.lang.Appendable
    public AbstractStringBuilder append(char c) {
        ensureCapacityInternal(this.count + 1);
        char[] cArr = this.value;
        int i = this.count;
        this.count = i + 1;
        cArr[i] = c;
        return this;
    }

    public AbstractStringBuilder append(int i) {
        int i2;
        if (i == Integer.MIN_VALUE) {
            append("-2147483648");
            return this;
        }
        if (i < 0) {
            i2 = Integer.stringSize(-i) + 1;
        } else {
            i2 = Integer.stringSize(i);
        }
        int i3 = this.count + i2;
        ensureCapacityInternal(i3);
        Integer.getChars(i, i3, this.value);
        this.count = i3;
        return this;
    }

    public AbstractStringBuilder append(long j) {
        int i;
        if (j == Long.MIN_VALUE) {
            append("-9223372036854775808");
            return this;
        }
        if (j < 0) {
            i = Long.stringSize(-j) + 1;
        } else {
            i = Long.stringSize(j);
        }
        int i2 = this.count + i;
        ensureCapacityInternal(i2);
        Long.getChars(j, i2, this.value);
        this.count = i2;
        return this;
    }

    public AbstractStringBuilder append(float f) {
        FloatingDecimal.appendTo(f, (Appendable) this);
        return this;
    }

    public AbstractStringBuilder append(double d) {
        FloatingDecimal.appendTo(d, this);
        return this;
    }

    public AbstractStringBuilder delete(int i, int i2) {
        if (i >= 0) {
            int i3 = this.count;
            if (i2 > i3) {
                i2 = i3;
            }
            if (i <= i2) {
                int i4 = i2 - i;
                if (i4 > 0) {
                    char[] cArr = this.value;
                    System.arraycopy((Object) cArr, i + i4, (Object) cArr, i, this.count - i2);
                    this.count -= i4;
                }
                return this;
            }
            throw new StringIndexOutOfBoundsException();
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public AbstractStringBuilder appendCodePoint(int i) {
        int i2 = this.count;
        if (Character.isBmpCodePoint(i)) {
            int i3 = i2 + 1;
            ensureCapacityInternal(i3);
            this.value[i2] = (char) i;
            this.count = i3;
        } else if (Character.isValidCodePoint(i)) {
            int i4 = i2 + 2;
            ensureCapacityInternal(i4);
            Character.toSurrogates(i, this.value, i2);
            this.count = i4;
        } else {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public AbstractStringBuilder deleteCharAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.count)) {
            throw new StringIndexOutOfBoundsException(i);
        }
        char[] cArr = this.value;
        System.arraycopy((Object) cArr, i + 1, (Object) cArr, i, (i2 - i) - 1);
        this.count--;
        return this;
    }

    public AbstractStringBuilder replace(int i, int i2, String str) {
        if (i >= 0) {
            int i3 = this.count;
            if (i > i3) {
                throw new StringIndexOutOfBoundsException("start > length()");
            } else if (i <= i2) {
                if (i2 > i3) {
                    i2 = i3;
                }
                int length = str.length();
                int i4 = (this.count + length) - (i2 - i);
                ensureCapacityInternal(i4);
                char[] cArr = this.value;
                System.arraycopy((Object) cArr, i2, (Object) cArr, length + i, this.count - i2);
                str.getChars(this.value, i);
                this.count = i4;
                return this;
            } else {
                throw new StringIndexOutOfBoundsException("start > end");
            }
        } else {
            throw new StringIndexOutOfBoundsException(i);
        }
    }

    public String substring(int i) {
        return substring(i, this.count);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return substring(i, i2);
    }

    public String substring(int i, int i2) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(i);
        } else if (i2 > this.count) {
            throw new StringIndexOutOfBoundsException(i2);
        } else if (i > i2) {
            throw new StringIndexOutOfBoundsException(i2 - i);
        } else {
            new String(this.value, i, i2 - i);
            throw null;
        }
    }

    public AbstractStringBuilder insert(int i, char[] cArr, int i2, int i3) {
        if (i < 0 || i > length()) {
            throw new StringIndexOutOfBoundsException(i);
        } else if (i2 < 0 || i3 < 0 || i2 > cArr.length - i3) {
            throw new StringIndexOutOfBoundsException("offset " + i2 + ", len " + i3 + ", str.length " + cArr.length);
        } else {
            ensureCapacityInternal(this.count + i3);
            char[] cArr2 = this.value;
            System.arraycopy((Object) cArr2, i, (Object) cArr2, i + i3, this.count - i);
            System.arraycopy((Object) cArr, i2, (Object) this.value, i, i3);
            this.count += i3;
            return this;
        }
    }

    public AbstractStringBuilder insert(int i, String str) {
        if (i < 0 || i > length()) {
            throw new StringIndexOutOfBoundsException(i);
        }
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        ensureCapacityInternal(this.count + length);
        char[] cArr = this.value;
        System.arraycopy((Object) cArr, i, (Object) cArr, i + length, this.count - i);
        str.getChars(this.value, i);
        this.count += length;
        return this;
    }

    public AbstractStringBuilder insert(int i, char[] cArr) {
        if (i < 0 || i > length()) {
            throw new StringIndexOutOfBoundsException(i);
        }
        int length = cArr.length;
        ensureCapacityInternal(this.count + length);
        char[] cArr2 = this.value;
        System.arraycopy((Object) cArr2, i, (Object) cArr2, i + length, this.count - i);
        System.arraycopy((Object) cArr, 0, (Object) this.value, i, length);
        this.count += length;
        return this;
    }

    public AbstractStringBuilder insert(int i, CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (charSequence instanceof String) {
            return insert(i, (String) charSequence);
        }
        return insert(i, charSequence, 0, charSequence.length());
    }

    public AbstractStringBuilder insert(int i, CharSequence charSequence, int i2, int i3) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (i < 0 || i > length()) {
            throw new IndexOutOfBoundsException("dstOffset " + i);
        } else if (i2 < 0 || i3 < 0 || i2 > i3 || i3 > charSequence.length()) {
            throw new IndexOutOfBoundsException("start " + i2 + ", end " + i3 + ", s.length() " + charSequence.length());
        } else {
            int i4 = i3 - i2;
            ensureCapacityInternal(this.count + i4);
            char[] cArr = this.value;
            System.arraycopy((Object) cArr, i, (Object) cArr, i + i4, this.count - i);
            while (i2 < i3) {
                this.value[i] = charSequence.charAt(i2);
                i2++;
                i++;
            }
            this.count += i4;
            return this;
        }
    }

    public AbstractStringBuilder insert(int i, char c) {
        ensureCapacityInternal(this.count + 1);
        char[] cArr = this.value;
        System.arraycopy((Object) cArr, i, (Object) cArr, i + 1, this.count - i);
        this.value[i] = c;
        this.count++;
        return this;
    }

    public AbstractStringBuilder insert(int i, long j) {
        return insert(i, String.valueOf(j));
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public int indexOf(String str, int i) {
        return String.indexOf(this.value, 0, this.count, str, i);
    }

    public int lastIndexOf(String str, int i) {
        return String.lastIndexOf(this.value, 0, this.count, str, i);
    }

    public AbstractStringBuilder reverse() {
        int i = this.count - 1;
        boolean z = false;
        for (int i2 = (i - 1) >> 1; i2 >= 0; i2--) {
            int i3 = i - i2;
            char[] cArr = this.value;
            char c = cArr[i2];
            char c2 = cArr[i3];
            cArr[i2] = c2;
            cArr[i3] = c;
            if (Character.isSurrogate(c) || Character.isSurrogate(c2)) {
                z = true;
            }
        }
        if (z) {
            reverseAllValidSurrogatePairs();
        }
        return this;
    }

    private void reverseAllValidSurrogatePairs() {
        int i = 0;
        while (i < this.count - 1) {
            char c = this.value[i];
            if (Character.isLowSurrogate(c)) {
                int i2 = i + 1;
                char c2 = this.value[i2];
                if (Character.isHighSurrogate(c2)) {
                    char[] cArr = this.value;
                    cArr[i] = c2;
                    cArr[i2] = c;
                    i = i2;
                }
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public final char[] getValue() {
        return this.value;
    }
}
