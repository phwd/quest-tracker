package android.icu.impl;

import android.icu.text.UCharacterIterator;

public final class UCharArrayIterator extends UCharacterIterator {
    private final int limit;
    private int pos;
    private final int start;
    private final char[] text;

    public UCharArrayIterator(char[] text2, int start2, int limit2) {
        if (start2 < 0 || limit2 > text2.length || start2 > limit2) {
            throw new IllegalArgumentException("start: " + start2 + " or limit: " + limit2 + " out of range [0, " + text2.length + ")");
        }
        this.text = text2;
        this.start = start2;
        this.limit = limit2;
        this.pos = start2;
    }

    @Override // android.icu.text.UCharacterIterator
    public int current() {
        int i = this.pos;
        if (i < this.limit) {
            return this.text[i];
        }
        return -1;
    }

    @Override // android.icu.text.UCharacterIterator
    public int getLength() {
        return this.limit - this.start;
    }

    @Override // android.icu.text.UCharacterIterator
    public int getIndex() {
        return this.pos - this.start;
    }

    @Override // android.icu.text.UCharacterIterator, android.icu.text.UForwardCharacterIterator
    public int next() {
        int i = this.pos;
        if (i >= this.limit) {
            return -1;
        }
        char[] cArr = this.text;
        this.pos = i + 1;
        return cArr[i];
    }

    @Override // android.icu.text.UCharacterIterator
    public int previous() {
        int i = this.pos;
        if (i <= this.start) {
            return -1;
        }
        char[] cArr = this.text;
        int i2 = i - 1;
        this.pos = i2;
        return cArr[i2];
    }

    @Override // android.icu.text.UCharacterIterator
    public void setIndex(int index) {
        if (index >= 0) {
            int i = this.limit;
            int i2 = this.start;
            if (index <= i - i2) {
                this.pos = i2 + index;
                return;
            }
        }
        throw new IndexOutOfBoundsException("index: " + index + " out of range [0, " + (this.limit - this.start) + ")");
    }

    @Override // android.icu.text.UCharacterIterator
    public int getText(char[] fillIn, int offset) {
        int i = this.limit;
        int i2 = this.start;
        int len = i - i2;
        System.arraycopy((Object) this.text, i2, (Object) fillIn, offset, len);
        return len;
    }

    @Override // android.icu.text.UCharacterIterator
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
