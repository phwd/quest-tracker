package android.icu.impl;

import android.icu.text.UCharacterIterator;
import java.text.CharacterIterator;

public class CharacterIteratorWrapper extends UCharacterIterator {
    private CharacterIterator iterator;

    public CharacterIteratorWrapper(CharacterIterator iter) {
        if (iter != null) {
            this.iterator = iter;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // android.icu.text.UCharacterIterator
    public int current() {
        int c = this.iterator.current();
        if (c == 65535) {
            return -1;
        }
        return c;
    }

    @Override // android.icu.text.UCharacterIterator
    public int getLength() {
        return this.iterator.getEndIndex() - this.iterator.getBeginIndex();
    }

    @Override // android.icu.text.UCharacterIterator
    public int getIndex() {
        return this.iterator.getIndex();
    }

    @Override // android.icu.text.UCharacterIterator, android.icu.text.UForwardCharacterIterator
    public int next() {
        int i = this.iterator.current();
        this.iterator.next();
        if (i == 65535) {
            return -1;
        }
        return i;
    }

    @Override // android.icu.text.UCharacterIterator
    public int previous() {
        int i = this.iterator.previous();
        if (i == 65535) {
            return -1;
        }
        return i;
    }

    @Override // android.icu.text.UCharacterIterator
    public void setIndex(int index) {
        try {
            this.iterator.setIndex(index);
        } catch (IllegalArgumentException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // android.icu.text.UCharacterIterator
    public void setToLimit() {
        CharacterIterator characterIterator = this.iterator;
        characterIterator.setIndex(characterIterator.getEndIndex());
    }

    @Override // android.icu.text.UCharacterIterator
    public int getText(char[] fillIn, int offset) {
        int length = this.iterator.getEndIndex() - this.iterator.getBeginIndex();
        int currentIndex = this.iterator.getIndex();
        if (offset < 0 || offset + length > fillIn.length) {
            throw new IndexOutOfBoundsException(Integer.toString(length));
        }
        char ch = this.iterator.first();
        while (ch != 65535) {
            fillIn[offset] = ch;
            ch = this.iterator.next();
            offset++;
        }
        this.iterator.setIndex(currentIndex);
        return length;
    }

    @Override // android.icu.text.UCharacterIterator
    public Object clone() {
        try {
            CharacterIteratorWrapper result = (CharacterIteratorWrapper) super.clone();
            result.iterator = (CharacterIterator) this.iterator.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override // android.icu.text.UCharacterIterator
    public int moveIndex(int delta) {
        int length = this.iterator.getEndIndex() - this.iterator.getBeginIndex();
        int idx = this.iterator.getIndex() + delta;
        if (idx < 0) {
            idx = 0;
        } else if (idx > length) {
            idx = length;
        }
        return this.iterator.setIndex(idx);
    }

    @Override // android.icu.text.UCharacterIterator
    public CharacterIterator getCharacterIterator() {
        return (CharacterIterator) this.iterator.clone();
    }
}
