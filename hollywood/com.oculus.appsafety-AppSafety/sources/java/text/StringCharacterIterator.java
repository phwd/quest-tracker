package java.text;

public final class StringCharacterIterator implements CharacterIterator {
    private int begin;
    private int end;
    private int pos;
    private String text;

    public StringCharacterIterator(String text2) {
        this(text2, 0);
    }

    public StringCharacterIterator(String text2, int pos2) {
        this(text2, 0, text2.length(), pos2);
    }

    public StringCharacterIterator(String text2, int begin2, int end2, int pos2) {
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

    public void setText(String text2) {
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
        if (!(obj instanceof StringCharacterIterator)) {
            return false;
        }
        StringCharacterIterator that = (StringCharacterIterator) obj;
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
            return (StringCharacterIterator) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
