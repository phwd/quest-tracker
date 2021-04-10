package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.UnicodeSet;

public class StringSegment implements CharSequence {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int end;
    private boolean foldCase;
    private int start = 0;
    private final String str;

    public StringSegment(String str2, boolean foldCase2) {
        this.str = str2;
        this.end = str2.length();
        this.foldCase = foldCase2;
    }

    public int getOffset() {
        return this.start;
    }

    public void setOffset(int start2) {
        this.start = start2;
    }

    public void adjustOffset(int delta) {
        this.start += delta;
    }

    public void adjustOffsetByCodePoint() {
        this.start += Character.charCount(getCodePoint());
    }

    public void setLength(int length) {
        this.end = this.start + length;
    }

    public void resetLength() {
        this.end = this.str.length();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.end - this.start;
    }

    @Override // java.lang.CharSequence
    public char charAt(int index) {
        return this.str.charAt(this.start + index);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int start2, int end2) {
        String str2 = this.str;
        int i = this.start;
        return str2.subSequence(start2 + i, i + end2);
    }

    public int getCodePoint() {
        char lead = this.str.charAt(this.start);
        if (Character.isHighSurrogate(lead)) {
            int i = this.start;
            if (i + 1 < this.end) {
                char trail = this.str.charAt(i + 1);
                if (Character.isLowSurrogate(trail)) {
                    return Character.toCodePoint(lead, trail);
                }
            }
        }
        return lead;
    }

    public int codePointAt(int index) {
        return this.str.codePointAt(this.start + index);
    }

    public boolean startsWith(int otherCp) {
        return codePointsEqual(getCodePoint(), otherCp, this.foldCase);
    }

    public boolean startsWith(UnicodeSet uniset) {
        int cp = getCodePoint();
        if (cp == -1) {
            return false;
        }
        return uniset.contains(cp);
    }

    public boolean startsWith(CharSequence other) {
        if (other == null || other.length() == 0 || length() == 0) {
            return false;
        }
        return codePointsEqual(Character.codePointAt(this, 0), Character.codePointAt(other, 0), this.foldCase);
    }

    public int getCommonPrefixLength(CharSequence other) {
        return getPrefixLengthInternal(other, this.foldCase);
    }

    public int getCaseSensitivePrefixLength(CharSequence other) {
        return getPrefixLengthInternal(other, false);
    }

    private int getPrefixLengthInternal(CharSequence other, boolean foldCase2) {
        int offset = 0;
        while (offset < Math.min(length(), other.length())) {
            int cp1 = Character.codePointAt(this, offset);
            if (!codePointsEqual(cp1, Character.codePointAt(other, offset), foldCase2)) {
                break;
            }
            offset += Character.charCount(cp1);
        }
        return offset;
    }

    private static final boolean codePointsEqual(int cp1, int cp2, boolean foldCase2) {
        if (cp1 == cp2) {
            return true;
        }
        if (!foldCase2) {
            return false;
        }
        if (UCharacter.foldCase(cp1, true) == UCharacter.foldCase(cp2, true)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object other) {
        if (!(other instanceof CharSequence)) {
            return false;
        }
        return Utility.charSequenceEquals(this, (CharSequence) other);
    }

    public int hashCode() {
        return Utility.charSequenceHashCode(this);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.str.substring(0, this.start) + "[" + this.str.substring(this.start, this.end) + "]" + this.str.substring(this.end);
    }
}
