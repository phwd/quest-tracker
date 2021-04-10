package android.icu.impl;

import android.icu.text.UCharacterIterator;
import java.text.CharacterIterator;

public class CharacterIteratorWrapper extends UCharacterIterator {
    private CharacterIterator iterator;

    public CharacterIteratorWrapper(CharacterIterator characterIterator) {
        if (characterIterator != null) {
            this.iterator = characterIterator;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // android.icu.text.UCharacterIterator
    public int getLength() {
        return this.iterator.getEndIndex() - this.iterator.getBeginIndex();
    }

    @Override // android.icu.text.UCharacterIterator
    public int getIndex() {
        return this.iterator.getIndex();
    }

    @Override // android.icu.text.UCharacterIterator
    public int next() {
        char current = this.iterator.current();
        this.iterator.next();
        if (current == 65535) {
            return -1;
        }
        return current;
    }

    @Override // android.icu.text.UCharacterIterator
    public int previous() {
        char previous = this.iterator.previous();
        if (previous == 65535) {
            return -1;
        }
        return previous;
    }

    @Override // android.icu.text.UCharacterIterator
    public void setIndex(int i) {
        try {
            this.iterator.setIndex(i);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // android.icu.text.UCharacterIterator
    public Object clone() {
        try {
            CharacterIteratorWrapper characterIteratorWrapper = (CharacterIteratorWrapper) super.clone();
            characterIteratorWrapper.iterator = (CharacterIterator) this.iterator.clone();
            return characterIteratorWrapper;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
