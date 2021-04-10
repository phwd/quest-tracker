package android.icu.impl;

import java.text.CharacterIterator;

public class CSCharacterIterator implements CharacterIterator {
    private int index;
    private CharSequence seq;

    public CSCharacterIterator(CharSequence text) {
        if (text != null) {
            this.seq = text;
            this.index = 0;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.text.CharacterIterator
    public char first() {
        this.index = 0;
        return current();
    }

    @Override // java.text.CharacterIterator
    public char last() {
        this.index = this.seq.length();
        return previous();
    }

    @Override // java.text.CharacterIterator
    public char current() {
        if (this.index == this.seq.length()) {
            return 65535;
        }
        return this.seq.charAt(this.index);
    }

    @Override // java.text.CharacterIterator
    public char next() {
        if (this.index < this.seq.length()) {
            this.index++;
        }
        return current();
    }

    @Override // java.text.CharacterIterator
    public char previous() {
        int i = this.index;
        if (i == 0) {
            return 65535;
        }
        this.index = i - 1;
        return current();
    }

    @Override // java.text.CharacterIterator
    public char setIndex(int position) {
        if (position < 0 || position > this.seq.length()) {
            throw new IllegalArgumentException();
        }
        this.index = position;
        return current();
    }

    @Override // java.text.CharacterIterator
    public int getBeginIndex() {
        return 0;
    }

    @Override // java.text.CharacterIterator
    public int getEndIndex() {
        return this.seq.length();
    }

    @Override // java.text.CharacterIterator
    public int getIndex() {
        return this.index;
    }

    @Override // java.text.CharacterIterator
    public Object clone() {
        CSCharacterIterator copy = new CSCharacterIterator(this.seq);
        copy.setIndex(this.index);
        return copy;
    }
}
