package java.lang;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Arrays;

public final class StringBuffer extends AbstractStringBuilder implements Serializable, CharSequence {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("value", char[].class), new ObjectStreamField("count", Integer.TYPE), new ObjectStreamField("shared", Boolean.TYPE)};
    static final long serialVersionUID = 3388685877147921107L;
    private transient char[] toStringCache;

    public StringBuffer() {
        super(16);
    }

    public StringBuffer(int i) {
        super(i);
    }

    public StringBuffer(String str) {
        super(str.length() + 16);
        append(str);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized int length() {
        return this.count;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void setLength(int i) {
        this.toStringCache = null;
        super.setLength(i);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized char charAt(int i) {
        if (i >= 0) {
            if (i < this.count) {
            }
        }
        throw new StringIndexOutOfBoundsException(i);
        return this.value[i];
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void getChars(int i, int i2, char[] cArr, int i3) {
        super.getChars(i, i2, cArr, i3);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void setCharAt(int i, char c) {
        if (i >= 0) {
            if (i < this.count) {
                this.toStringCache = null;
                this.value[i] = c;
            }
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public synchronized StringBuffer append(Object obj) {
        this.toStringCache = null;
        super.append(String.valueOf(obj));
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(String str) {
        this.toStringCache = null;
        super.append(str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(StringBuffer stringBuffer) {
        this.toStringCache = null;
        super.append(stringBuffer);
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(AbstractStringBuilder abstractStringBuilder) {
        this.toStringCache = null;
        super.append(abstractStringBuilder);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(CharSequence charSequence) {
        this.toStringCache = null;
        super.append(charSequence);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(CharSequence charSequence, int i, int i2) {
        this.toStringCache = null;
        super.append(charSequence, i, i2);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(char[] cArr) {
        this.toStringCache = null;
        super.append(cArr);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(char[] cArr, int i, int i2) {
        this.toStringCache = null;
        super.append(cArr, i, i2);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(char c) {
        this.toStringCache = null;
        super.append(c);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(int i) {
        this.toStringCache = null;
        super.append(i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer appendCodePoint(int i) {
        this.toStringCache = null;
        super.appendCodePoint(i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(long j) {
        this.toStringCache = null;
        super.append(j);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer delete(int i, int i2) {
        this.toStringCache = null;
        super.delete(i, i2);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer replace(int i, int i2, String str) {
        this.toStringCache = null;
        super.replace(i, i2, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized String substring(int i) {
        return substring(i, this.count);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized CharSequence subSequence(int i, int i2) {
        super.substring(i, i2);
        throw null;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized String substring(int i, int i2) {
        super.substring(i, i2);
        throw null;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int i, char[] cArr, int i2, int i3) {
        this.toStringCache = null;
        super.insert(i, cArr, i2, i3);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int i, String str) {
        this.toStringCache = null;
        super.insert(i, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int i, char[] cArr) {
        this.toStringCache = null;
        super.insert(i, cArr);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int i, CharSequence charSequence, int i2, int i3) {
        this.toStringCache = null;
        super.insert(i, charSequence, i2, i3);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int i, char c) {
        this.toStringCache = null;
        super.insert(i, c);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public int indexOf(String str) {
        return super.indexOf(str);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int indexOf(String str, int i) {
        return super.indexOf(str, i);
    }

    @Override // java.lang.CharSequence
    public synchronized String toString() {
        if (this.toStringCache == null) {
            this.toStringCache = Arrays.copyOfRange(this.value, 0, this.count);
        }
        new String(this.toStringCache, 0, this.count);
        throw null;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }
}
