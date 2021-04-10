package java.lang;

import java.io.IOException;
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

    public StringBuffer(int capacity) {
        super(capacity);
    }

    public StringBuffer(String str) {
        super(str.length() + 16);
        append(str);
    }

    public StringBuffer(CharSequence seq) {
        this(seq.length() + 16);
        append(seq);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized int length() {
        return this.count;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int capacity() {
        return this.value.length;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void ensureCapacity(int minimumCapacity) {
        super.ensureCapacity(minimumCapacity);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void trimToSize() {
        super.trimToSize();
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void setLength(int newLength) {
        this.toStringCache = null;
        super.setLength(newLength);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized char charAt(int index) {
        if (index >= 0) {
            if (index < this.count) {
            }
        }
        throw new StringIndexOutOfBoundsException(index);
        return this.value[index];
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int codePointAt(int index) {
        return super.codePointAt(index);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int codePointBefore(int index) {
        return super.codePointBefore(index);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int codePointCount(int beginIndex, int endIndex) {
        return super.codePointCount(beginIndex, endIndex);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int offsetByCodePoints(int index, int codePointOffset) {
        return super.offsetByCodePoints(index, codePointOffset);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        super.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized void setCharAt(int index, char ch) {
        if (index >= 0) {
            if (index < this.count) {
                this.toStringCache = null;
                this.value[index] = ch;
            }
        }
        throw new StringIndexOutOfBoundsException(index);
    }

    @Override // java.lang.AbstractStringBuilder
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
    public synchronized StringBuffer append(StringBuffer sb) {
        this.toStringCache = null;
        super.append(sb);
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(AbstractStringBuilder asb) {
        this.toStringCache = null;
        super.append(asb);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(CharSequence s) {
        this.toStringCache = null;
        super.append(s);
        return this;
    }

    @Override // java.lang.Appendable, java.lang.AbstractStringBuilder, java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(CharSequence s, int start, int end) {
        this.toStringCache = null;
        super.append(s, start, end);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(char[] str) {
        this.toStringCache = null;
        super.append(str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(char[] str, int offset, int len) {
        this.toStringCache = null;
        super.append(str, offset, len);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(boolean b) {
        this.toStringCache = null;
        super.append(b);
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
    public synchronized StringBuffer appendCodePoint(int codePoint) {
        this.toStringCache = null;
        super.appendCodePoint(codePoint);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(long lng) {
        this.toStringCache = null;
        super.append(lng);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(float f) {
        this.toStringCache = null;
        super.append(f);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer append(double d) {
        this.toStringCache = null;
        super.append(d);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer delete(int start, int end) {
        this.toStringCache = null;
        super.delete(start, end);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer deleteCharAt(int index) {
        this.toStringCache = null;
        super.deleteCharAt(index);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer replace(int start, int end, String str) {
        this.toStringCache = null;
        super.replace(start, end, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized String substring(int start) {
        return substring(start, this.count);
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized CharSequence subSequence(int start, int end) {
        return super.substring(start, end);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized String substring(int start, int end) {
        return super.substring(start, end);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int index, char[] str, int offset, int len) {
        this.toStringCache = null;
        super.insert(index, str, offset, len);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int offset, Object obj) {
        this.toStringCache = null;
        super.insert(offset, String.valueOf(obj));
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int offset, String str) {
        this.toStringCache = null;
        super.insert(offset, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int offset, char[] str) {
        this.toStringCache = null;
        super.insert(offset, str);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuffer insert(int dstOffset, CharSequence s) {
        super.insert(dstOffset, s);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int dstOffset, CharSequence s, int start, int end) {
        this.toStringCache = null;
        super.insert(dstOffset, s, start, end);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuffer insert(int offset, boolean b) {
        super.insert(offset, b);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer insert(int offset, char c) {
        this.toStringCache = null;
        super.insert(offset, c);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuffer insert(int offset, int i) {
        super.insert(offset, i);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuffer insert(int offset, long l) {
        super.insert(offset, l);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuffer insert(int offset, float f) {
        super.insert(offset, f);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public StringBuffer insert(int offset, double d) {
        super.insert(offset, d);
        return this;
    }

    @Override // java.lang.AbstractStringBuilder
    public int indexOf(String str) {
        return super.indexOf(str);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int indexOf(String str, int fromIndex) {
        return super.indexOf(str, fromIndex);
    }

    @Override // java.lang.AbstractStringBuilder
    public int lastIndexOf(String str) {
        return lastIndexOf(str, this.count);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized int lastIndexOf(String str, int fromIndex) {
        return super.lastIndexOf(str, fromIndex);
    }

    @Override // java.lang.AbstractStringBuilder
    public synchronized StringBuffer reverse() {
        this.toStringCache = null;
        super.reverse();
        return this;
    }

    @Override // java.lang.CharSequence, java.lang.AbstractStringBuilder
    public synchronized String toString() {
        if (this.toStringCache == null) {
            this.toStringCache = Arrays.copyOfRange(this.value, 0, this.count);
        }
        return new String(this.toStringCache, 0, this.count);
    }

    private synchronized void writeObject(ObjectOutputStream s) throws IOException {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("value", this.value);
        fields.put("count", this.count);
        fields.put("shared", false);
        s.writeFields();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = s.readFields();
        this.value = (char[]) fields.get("value", (Object) null);
        this.count = fields.get("count", 0);
    }
}
