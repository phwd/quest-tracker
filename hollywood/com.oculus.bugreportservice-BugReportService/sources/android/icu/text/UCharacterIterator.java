package android.icu.text;

import android.icu.impl.CharacterIteratorWrapper;
import android.icu.impl.ReplaceableUCharacterIterator;
import java.text.CharacterIterator;

public abstract class UCharacterIterator implements Cloneable, UForwardCharacterIterator {
    public abstract int getIndex();

    public abstract int getLength();

    public abstract int next();

    public abstract int previous();

    public abstract void setIndex(int i);

    protected UCharacterIterator() {
    }

    public static final UCharacterIterator getInstance(String str) {
        return new ReplaceableUCharacterIterator(str);
    }

    public static final UCharacterIterator getInstance(CharacterIterator characterIterator) {
        return new CharacterIteratorWrapper(characterIterator);
    }

    public int nextCodePoint() {
        int next = next();
        char c = (char) next;
        if (UTF16.isLeadSurrogate(c)) {
            int next2 = next();
            char c2 = (char) next2;
            if (UTF16.isTrailSurrogate(c2)) {
                return Character.toCodePoint(c, c2);
            }
            if (next2 != -1) {
                previous();
            }
        }
        return next;
    }

    public int previousCodePoint() {
        int previous = previous();
        char c = (char) previous;
        if (UTF16.isTrailSurrogate(c)) {
            int previous2 = previous();
            char c2 = (char) previous2;
            if (UTF16.isLeadSurrogate(c2)) {
                return Character.toCodePoint(c2, c);
            }
            if (previous2 != -1) {
                next();
            }
        }
        return previous;
    }

    public int moveCodePointIndex(int i) {
        if (i > 0) {
            while (i > 0 && nextCodePoint() != -1) {
                i--;
            }
        } else {
            while (i < 0 && previousCodePoint() != -1) {
                i++;
            }
        }
        if (i == 0) {
            return getIndex();
        }
        throw new IndexOutOfBoundsException();
    }

    public Object clone() {
        return super.clone();
    }
}
