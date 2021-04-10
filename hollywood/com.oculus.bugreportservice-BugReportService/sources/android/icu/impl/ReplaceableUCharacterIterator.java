package android.icu.impl;

import android.icu.text.Replaceable;
import android.icu.text.ReplaceableString;
import android.icu.text.UCharacterIterator;

public class ReplaceableUCharacterIterator extends UCharacterIterator {
    private int currentIndex;
    private Replaceable replaceable;

    public ReplaceableUCharacterIterator(String str) {
        if (str != null) {
            this.replaceable = new ReplaceableString(str);
            this.currentIndex = 0;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // android.icu.text.UCharacterIterator
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // android.icu.text.UCharacterIterator
    public int getLength() {
        return this.replaceable.length();
    }

    @Override // android.icu.text.UCharacterIterator
    public int getIndex() {
        return this.currentIndex;
    }

    @Override // android.icu.text.UCharacterIterator
    public int next() {
        if (this.currentIndex >= this.replaceable.length()) {
            return -1;
        }
        Replaceable replaceable2 = this.replaceable;
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        return replaceable2.charAt(i);
    }

    @Override // android.icu.text.UCharacterIterator
    public int previous() {
        int i = this.currentIndex;
        if (i <= 0) {
            return -1;
        }
        Replaceable replaceable2 = this.replaceable;
        int i2 = i - 1;
        this.currentIndex = i2;
        return replaceable2.charAt(i2);
    }

    @Override // android.icu.text.UCharacterIterator
    public void setIndex(int i) {
        if (i < 0 || i > this.replaceable.length()) {
            throw new IndexOutOfBoundsException();
        }
        this.currentIndex = i;
    }
}
