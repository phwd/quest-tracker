package java.text;

import android.icu.text.BreakIterator;

class IcuIteratorWrapper extends BreakIterator {
    private BreakIterator wrapped;

    IcuIteratorWrapper(BreakIterator iterator) {
        this.wrapped = iterator;
    }

    @Override // java.text.BreakIterator
    public Object clone() {
        IcuIteratorWrapper result = (IcuIteratorWrapper) super.clone();
        result.wrapped = (BreakIterator) this.wrapped.clone();
        return result;
    }

    public boolean equals(Object that) {
        if (!(that instanceof IcuIteratorWrapper)) {
            return false;
        }
        return this.wrapped.equals(((IcuIteratorWrapper) that).wrapped);
    }

    public String toString() {
        return this.wrapped.toString();
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    @Override // java.text.BreakIterator
    public int first() {
        return this.wrapped.first();
    }

    @Override // java.text.BreakIterator
    public int last() {
        return this.wrapped.last();
    }

    @Override // java.text.BreakIterator
    public int next(int n) {
        return this.wrapped.next(n);
    }

    @Override // java.text.BreakIterator
    public int next() {
        return this.wrapped.next();
    }

    @Override // java.text.BreakIterator
    public int previous() {
        return this.wrapped.previous();
    }

    protected static final void checkOffset(int offset, CharacterIterator text) {
        if (offset < text.getBeginIndex() || offset > text.getEndIndex()) {
            throw new IllegalArgumentException("offset out of bounds");
        }
    }

    @Override // java.text.BreakIterator
    public int following(int offset) {
        checkOffset(offset, getText());
        return this.wrapped.following(offset);
    }

    @Override // java.text.BreakIterator
    public int preceding(int offset) {
        checkOffset(offset, getText());
        return this.wrapped.preceding(offset);
    }

    @Override // java.text.BreakIterator
    public boolean isBoundary(int offset) {
        checkOffset(offset, getText());
        return this.wrapped.isBoundary(offset);
    }

    @Override // java.text.BreakIterator
    public int current() {
        return this.wrapped.current();
    }

    @Override // java.text.BreakIterator
    public CharacterIterator getText() {
        return this.wrapped.getText();
    }

    @Override // java.text.BreakIterator
    public void setText(String newText) {
        this.wrapped.setText(newText);
    }

    @Override // java.text.BreakIterator
    public void setText(CharacterIterator newText) {
        newText.current();
        this.wrapped.setText(newText);
    }
}
