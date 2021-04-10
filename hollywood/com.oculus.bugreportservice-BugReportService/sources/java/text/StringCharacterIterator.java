package java.text;

public final class StringCharacterIterator implements CharacterIterator {
    private int begin;
    private int end;
    private int pos;
    private String text;

    public StringCharacterIterator(String str) {
        this(str, 0);
    }

    public StringCharacterIterator(String str, int i) {
        this(str, 0, str.length(), i);
    }

    public StringCharacterIterator(String str, int i, int i2, int i3) {
        if (str != null) {
            this.text = str;
            if (i < 0 || i > i2 || i2 > str.length()) {
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
        if (!(obj instanceof StringCharacterIterator)) {
            return false;
        }
        StringCharacterIterator stringCharacterIterator = (StringCharacterIterator) obj;
        return hashCode() == stringCharacterIterator.hashCode() && this.text.equals(stringCharacterIterator.text) && this.pos == stringCharacterIterator.pos && this.begin == stringCharacterIterator.begin && this.end == stringCharacterIterator.end;
    }

    public int hashCode() {
        return this.end ^ ((this.text.hashCode() ^ this.pos) ^ this.begin);
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
