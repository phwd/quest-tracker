package java.lang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class StringBuilder extends AbstractStringBuilder implements Serializable, CharSequence {
    static final long serialVersionUID = 4383685877147921099L;

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ int capacity() {
        return super.capacity();
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ char charAt(int i) {
        return super.charAt(i);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ int codePointAt(int i) {
        return super.codePointAt(i);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ int codePointBefore(int i) {
        return super.codePointBefore(i);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ int codePointCount(int i, int i2) {
        return super.codePointCount(i, i2);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ void ensureCapacity(int i) {
        super.ensureCapacity(i);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ void getChars(int i, int i2, char[] cArr, int i3) {
        super.getChars(i, i2, cArr, i3);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ int length() {
        return super.length();
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ int offsetByCodePoints(int i, int i2) {
        return super.offsetByCodePoints(i, i2);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ void setCharAt(int i, char c) {
        super.setCharAt(i, c);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ void setLength(int i) {
        super.setLength(i);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ CharSequence subSequence(int i, int i2) {
        return super.subSequence(i, i2);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ String substring(int i) {
        return super.substring(i);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ String substring(int i, int i2) {
        return super.substring(i, i2);
    }

    @Override // java.lang.AbstractStringBuilder
    public /* bridge */ /* synthetic */ void trimToSize() {
        super.trimToSize();
    }

    public StringBuilder() {
        super(16);
    }

    public StringBuilder(int capacity) {
        super(capacity);
    }

    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }

    public StringBuilder(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(Object obj) {
        append(String.valueOf(obj));
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(String str) {
        super.append(str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(StringBuffer sb) {
        super.append(sb);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public StringBuilder append(CharSequence s) {
        super.append(s);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public StringBuilder append(CharSequence s, int start, int end) {
        super.append(s, start, end);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(char[] str) {
        super.append(str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(char[] str, int offset, int len) {
        super.append(str, offset, len);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(boolean b) {
        super.append(b);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public StringBuilder append(char c) {
        super.append(c);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(int i) {
        super.append(i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(long lng) {
        super.append(lng);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(float f) {
        super.append(f);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(double d) {
        super.append(d);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder appendCodePoint(int codePoint) {
        super.appendCodePoint(codePoint);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder delete(int start, int end) {
        super.delete(start, end);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder deleteCharAt(int index) {
        super.deleteCharAt(index);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder replace(int start, int end, String str) {
        super.replace(start, end, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int index, char[] str, int offset, int len) {
        super.insert(index, str, offset, len);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, Object obj) {
        super.insert(offset, obj);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, char[] str) {
        super.insert(offset, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int dstOffset, CharSequence s) {
        super.insert(dstOffset, s);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, boolean b) {
        super.insert(offset, b);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, char c) {
        super.insert(offset, c);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, int i) {
        super.insert(offset, i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, long l) {
        super.insert(offset, l);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, float f) {
        super.insert(offset, f);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int offset, double d) {
        super.insert(offset, d);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public int indexOf(String str) {
        return super.indexOf(str);
    }

    @Override // java.lang.AbstractStringBuilder
    public int indexOf(String str, int fromIndex) {
        return super.indexOf(str, fromIndex);
    }

    @Override // java.lang.AbstractStringBuilder
    public int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    @Override // java.lang.AbstractStringBuilder
    public int lastIndexOf(String str, int fromIndex) {
        return super.lastIndexOf(str, fromIndex);
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder reverse() {
        super.reverse();
        return this;
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public String toString() {
        if (this.count == 0) {
            return "";
        }
        return new String(this.value, 0, this.count);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(this.count);
        s.writeObject(this.value);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.count = s.readInt();
        this.value = (char[]) s.readObject();
    }
}
