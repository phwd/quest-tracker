package android.icu.impl.coll;

import android.icu.text.UCharacterIterator;

public class IterCollationIterator extends CollationIterator {
    protected UCharacterIterator iter;

    public IterCollationIterator(CollationData d, boolean numeric, UCharacterIterator ui) {
        super(d, numeric);
        this.iter = ui;
    }

    @Override // android.icu.impl.coll.CollationIterator
    public void resetToOffset(int newOffset) {
        reset();
        this.iter.setIndex(newOffset);
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int getOffset() {
        return this.iter.getIndex();
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int nextCodePoint() {
        return this.iter.nextCodePoint();
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int previousCodePoint() {
        return this.iter.previousCodePoint();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public long handleNextCE32() {
        int c = this.iter.next();
        if (c < 0) {
            return -4294967104L;
        }
        return makeCodePointAndCE32Pair(c, this.trie.getFromU16SingleLead((char) c));
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public char handleGetTrailSurrogate() {
        int trail = this.iter.next();
        if (!isTrailSurrogate(trail) && trail >= 0) {
            this.iter.previous();
        }
        return (char) trail;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public void forwardNumCodePoints(int num) {
        this.iter.moveCodePointIndex(num);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public void backwardNumCodePoints(int num) {
        this.iter.moveCodePointIndex(-num);
    }
}
