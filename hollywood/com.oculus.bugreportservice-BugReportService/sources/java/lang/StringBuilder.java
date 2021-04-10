package java.lang;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class StringBuilder extends AbstractStringBuilder implements Serializable, CharSequence {
    static final long serialVersionUID = 4383685877147921099L;

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
        super.substring(i, i2);
        throw null;
    }

    public StringBuilder() {
        super(16);
    }

    public StringBuilder(int i) {
        super(i);
    }

    public StringBuilder(String str) {
        super(str.length() + 16);
        append(str);
    }

    public StringBuilder(CharSequence charSequence) {
        this(charSequence.length() + 16);
        append(charSequence);
    }

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
    public StringBuilder append(StringBuffer stringBuffer) {
        super.append(stringBuffer);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public StringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public StringBuilder append(CharSequence charSequence, int i, int i2) {
        super.append(charSequence, i, i2);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(char[] cArr) {
        super.append(cArr);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(char[] cArr, int i, int i2) {
        super.append(cArr, i, i2);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder append(boolean z) {
        super.append(z);
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
    public StringBuilder append(long j) {
        super.append(j);
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
    public StringBuilder appendCodePoint(int i) {
        super.appendCodePoint(i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder delete(int i, int i2) {
        super.delete(i, i2);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder deleteCharAt(int i) {
        super.deleteCharAt(i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder replace(int i, int i2, String str) {
        super.replace(i, i2, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, char[] cArr, int i2, int i3) {
        super.insert(i, cArr, i2, i3);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, String str) {
        super.insert(i, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, char[] cArr) {
        super.insert(i, cArr);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, CharSequence charSequence) {
        super.insert(i, charSequence);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, CharSequence charSequence, int i2, int i3) {
        super.insert(i, charSequence, i2, i3);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, char c) {
        super.insert(i, c);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder insert(int i, long j) {
        super.insert(i, j);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public int indexOf(String str) {
        return super.indexOf(str);
    }

    @Override // java.lang.AbstractStringBuilder
    public int indexOf(String str, int i) {
        return super.indexOf(str, i);
    }

    @Override // java.lang.AbstractStringBuilder
    public int lastIndexOf(String str, int i) {
        return super.lastIndexOf(str, i);
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuilder reverse() {
        super.reverse();
        return this;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        int i = this.count;
        if (i == 0) {
            return "";
        }
        new String(this.value, 0, i);
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
