package android.icu.text;

import android.icu.impl.number.Padder;
import android.icu.lang.UCharacter;
import android.icu.text.Transliterator;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.ULocale;
import java.text.CharacterIterator;

final class BreakTransliterator extends Transliterator {
    static final int LETTER_OR_MARK_MASK = 510;
    private BreakIterator bi;
    private int[] boundaries;
    private int boundaryCount;
    private String insertion;

    public BreakTransliterator(String ID, UnicodeFilter filter, BreakIterator bi2, String insertion2) {
        super(ID, filter);
        this.boundaries = new int[50];
        this.boundaryCount = 0;
        this.bi = bi2;
        this.insertion = insertion2;
    }

    public BreakTransliterator(String ID, UnicodeFilter filter) {
        this(ID, filter, null, Padder.FALLBACK_PADDING_STRING);
    }

    public String getInsertion() {
        return this.insertion;
    }

    public void setInsertion(String insertion2) {
        this.insertion = insertion2;
    }

    public BreakIterator getBreakIterator() {
        if (this.bi == null) {
            this.bi = BreakIterator.getWordInstance(new ULocale("th_TH"));
        }
        return this.bi;
    }

    public void setBreakIterator(BreakIterator bi2) {
        this.bi = bi2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public synchronized void handleTransliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
        this.boundaryCount = 0;
        getBreakIterator();
        this.bi.setText(new ReplaceableCharacterIterator(text, pos.start, pos.limit, pos.start));
        int boundary = this.bi.first();
        while (boundary != -1 && boundary < pos.limit) {
            if (boundary != 0) {
                if (((1 << UCharacter.getType(UTF16.charAt(text, boundary - 1))) & LETTER_OR_MARK_MASK) != 0) {
                    if (((1 << UCharacter.getType(UTF16.charAt(text, boundary))) & LETTER_OR_MARK_MASK) != 0) {
                        if (this.boundaryCount >= this.boundaries.length) {
                            int[] temp = new int[(this.boundaries.length * 2)];
                            System.arraycopy((Object) this.boundaries, 0, (Object) temp, 0, this.boundaries.length);
                            this.boundaries = temp;
                        }
                        int[] temp2 = this.boundaries;
                        int i = this.boundaryCount;
                        this.boundaryCount = i + 1;
                        temp2[i] = boundary;
                    }
                }
            }
            boundary = this.bi.next();
        }
        int delta = 0;
        int lastBoundary = 0;
        if (this.boundaryCount != 0) {
            delta = this.boundaryCount * this.insertion.length();
            lastBoundary = this.boundaries[this.boundaryCount - 1];
            while (this.boundaryCount > 0) {
                int[] iArr = this.boundaries;
                int i2 = this.boundaryCount - 1;
                this.boundaryCount = i2;
                int boundary2 = iArr[i2];
                text.replace(boundary2, boundary2, this.insertion);
            }
        }
        pos.contextLimit += delta;
        pos.limit += delta;
        pos.start = incremental ? lastBoundary + delta : pos.limit;
    }

    static void register() {
        Transliterator.registerInstance(new BreakTransliterator("Any-BreakInternal", null), false);
    }

    static final class ReplaceableCharacterIterator implements CharacterIterator {
        private int begin;
        private int end;
        private int pos;
        private Replaceable text;

        public ReplaceableCharacterIterator(Replaceable text2, int begin2, int end2, int pos2) {
            if (text2 != null) {
                this.text = text2;
                if (begin2 < 0 || begin2 > end2 || end2 > text2.length()) {
                    throw new IllegalArgumentException("Invalid substring range");
                } else if (pos2 < begin2 || pos2 > end2) {
                    throw new IllegalArgumentException("Invalid position");
                } else {
                    this.begin = begin2;
                    this.end = end2;
                    this.pos = pos2;
                }
            } else {
                throw new NullPointerException();
            }
        }

        public void setText(Replaceable text2) {
            if (text2 != null) {
                this.text = text2;
                this.begin = 0;
                this.end = text2.length();
                this.pos = 0;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.text.CharacterIterator
        public char first() {
            this.pos = this.begin;
            return current();
        }

        @Override // java.text.CharacterIterator
        public char last() {
            int i = this.end;
            if (i != this.begin) {
                this.pos = i - 1;
            } else {
                this.pos = i;
            }
            return current();
        }

        @Override // java.text.CharacterIterator
        public char setIndex(int p) {
            if (p < this.begin || p > this.end) {
                throw new IllegalArgumentException("Invalid index");
            }
            this.pos = p;
            return current();
        }

        @Override // java.text.CharacterIterator
        public char current() {
            int i = this.pos;
            if (i < this.begin || i >= this.end) {
                return 65535;
            }
            return this.text.charAt(i);
        }

        @Override // java.text.CharacterIterator
        public char next() {
            int i = this.pos;
            int i2 = this.end;
            if (i < i2 - 1) {
                this.pos = i + 1;
                return this.text.charAt(this.pos);
            }
            this.pos = i2;
            return 65535;
        }

        @Override // java.text.CharacterIterator
        public char previous() {
            int i = this.pos;
            if (i <= this.begin) {
                return 65535;
            }
            this.pos = i - 1;
            return this.text.charAt(this.pos);
        }

        @Override // java.text.CharacterIterator
        public int getBeginIndex() {
            return this.begin;
        }

        @Override // java.text.CharacterIterator
        public int getEndIndex() {
            return this.end;
        }

        @Override // java.text.CharacterIterator
        public int getIndex() {
            return this.pos;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ReplaceableCharacterIterator)) {
                return false;
            }
            ReplaceableCharacterIterator that = (ReplaceableCharacterIterator) obj;
            if (hashCode() == that.hashCode() && this.text.equals(that.text) && this.pos == that.pos && this.begin == that.begin && this.end == that.end) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((this.text.hashCode() ^ this.pos) ^ this.begin) ^ this.end;
        }

        @Override // java.text.CharacterIterator
        public Object clone() {
            try {
                return (ReplaceableCharacterIterator) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException();
            }
        }
    }

    @Override // android.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        if (getFilterAsUnicodeSet(inputFilter).size() != 0) {
            targetSet.addAll(this.insertion);
        }
    }
}
