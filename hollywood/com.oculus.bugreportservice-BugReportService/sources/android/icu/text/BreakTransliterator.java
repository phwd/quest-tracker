package android.icu.text;

import android.icu.lang.UCharacter;
import android.icu.text.Transliterator;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.ULocale;
import java.text.CharacterIterator;

final class BreakTransliterator extends Transliterator {
    private BreakIterator bi;
    private int[] boundaries;
    private int boundaryCount;
    private String insertion;

    public BreakTransliterator(String str, UnicodeFilter unicodeFilter, BreakIterator breakIterator, String str2) {
        super(str, unicodeFilter);
        this.boundaries = new int[50];
        this.boundaryCount = 0;
        this.bi = breakIterator;
        this.insertion = str2;
    }

    public BreakTransliterator(String str, UnicodeFilter unicodeFilter) {
        this(str, unicodeFilter, null, " ");
    }

    public BreakIterator getBreakIterator() {
        if (this.bi == null) {
            this.bi = BreakIterator.getWordInstance(new ULocale("th_TH"));
        }
        return this.bi;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public synchronized void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        int i;
        int i2;
        int i3 = 0;
        this.boundaryCount = 0;
        getBreakIterator();
        this.bi.setText(new ReplaceableCharacterIterator(replaceable, position.start, position.limit, position.start));
        int first = this.bi.first();
        while (first != -1 && first < position.limit) {
            if (first != 0) {
                if (((1 << UCharacter.getType(UTF16.charAt(replaceable, first - 1))) & 510) != 0) {
                    if (((1 << UCharacter.getType(UTF16.charAt(replaceable, first))) & 510) != 0) {
                        if (this.boundaryCount >= this.boundaries.length) {
                            int[] iArr = new int[(this.boundaries.length * 2)];
                            System.arraycopy(this.boundaries, 0, iArr, 0, this.boundaries.length);
                            this.boundaries = iArr;
                        }
                        int[] iArr2 = this.boundaries;
                        int i4 = this.boundaryCount;
                        this.boundaryCount = i4 + 1;
                        iArr2[i4] = first;
                    }
                }
            }
            first = this.bi.next();
        }
        if (this.boundaryCount != 0) {
            i3 = this.boundaryCount * this.insertion.length();
            i = this.boundaries[this.boundaryCount - 1];
            while (this.boundaryCount > 0) {
                int[] iArr3 = this.boundaries;
                int i5 = this.boundaryCount - 1;
                this.boundaryCount = i5;
                int i6 = iArr3[i5];
                replaceable.replace(i6, i6, this.insertion);
            }
        } else {
            i = 0;
        }
        position.contextLimit += i3;
        position.limit += i3;
        if (z) {
            i2 = i + i3;
        } else {
            i2 = position.limit;
        }
        position.start = i2;
    }

    static void register() {
        Transliterator.registerInstance(new BreakTransliterator("Any-BreakInternal", null), false);
    }

    static final class ReplaceableCharacterIterator implements CharacterIterator {
        private int begin;
        private int end;
        private int pos;
        private Replaceable text;

        public ReplaceableCharacterIterator(Replaceable replaceable, int i, int i2, int i3) {
            if (replaceable != null) {
                this.text = replaceable;
                if (i < 0 || i > i2 || i2 > replaceable.length()) {
                    throw new IllegalArgumentException("Invalid substring range");
                } else if (i3 < i || i3 > i2) {
                    throw new IllegalArgumentException("Invalid position");
                } else {
                    this.begin = i;
                    this.end = i2;
                    this.pos = i3;
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.text.CharacterIterator
        public char first() {
            this.pos = this.begin;
            return current();
        }

        @Override // java.text.CharacterIterator
        public char setIndex(int i) {
            if (i < this.begin || i > this.end) {
                throw new IllegalArgumentException("Invalid index");
            }
            this.pos = i;
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
            ReplaceableCharacterIterator replaceableCharacterIterator = (ReplaceableCharacterIterator) obj;
            return hashCode() == replaceableCharacterIterator.hashCode() && this.text.equals(replaceableCharacterIterator.text) && this.pos == replaceableCharacterIterator.pos && this.begin == replaceableCharacterIterator.begin && this.end == replaceableCharacterIterator.end;
        }

        public int hashCode() {
            return this.end ^ ((this.text.hashCode() ^ this.pos) ^ this.begin);
        }

        @Override // java.text.CharacterIterator
        public Object clone() {
            try {
                return (ReplaceableCharacterIterator) super.clone();
            } catch (CloneNotSupportedException unused) {
                throw new ICUCloneNotSupportedException();
            }
        }
    }
}
