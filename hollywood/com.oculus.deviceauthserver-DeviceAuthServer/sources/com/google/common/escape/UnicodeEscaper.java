package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Beta
@GwtCompatible
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    /* access modifiers changed from: protected */
    public abstract char[] escape(int i);

    protected UnicodeEscaper() {
    }

    /* access modifiers changed from: protected */
    public int nextEscapeIndex(CharSequence csq, int start, int end) {
        int index = start;
        while (index < end) {
            int cp = codePointAt(csq, index, end);
            if (cp < 0 || escape(cp) != null) {
                break;
            }
            index += Character.isSupplementaryCodePoint(cp) ? 2 : 1;
        }
        return index;
    }

    @Override // com.google.common.escape.Escaper
    public String escape(String string) {
        Preconditions.checkNotNull(string);
        int end = string.length();
        int index = nextEscapeIndex(string, 0, end);
        return index == end ? string : escapeSlow(string, index);
    }

    /* JADX INFO: Multiple debug info for r5v0 int: [D('charsSkipped' int), D('cp' int)] */
    /* access modifiers changed from: protected */
    public final String escapeSlow(String s, int index) {
        int end = s.length();
        char[] dest = Platform.charBufferFromThreadLocal();
        int destIndex = 0;
        int unescapedChunkStart = 0;
        while (index < end) {
            int cp = codePointAt(s, index, end);
            if (cp >= 0) {
                char[] escaped = escape(cp);
                int nextIndex = (Character.isSupplementaryCodePoint(cp) ? 2 : 1) + index;
                if (escaped != null) {
                    int charsSkipped = index - unescapedChunkStart;
                    int sizeNeeded = destIndex + charsSkipped + escaped.length;
                    if (dest.length < sizeNeeded) {
                        dest = growBuffer(dest, destIndex, (end - index) + sizeNeeded + 32);
                    }
                    if (charsSkipped > 0) {
                        s.getChars(unescapedChunkStart, index, dest, destIndex);
                        destIndex += charsSkipped;
                    }
                    if (escaped.length > 0) {
                        System.arraycopy(escaped, 0, dest, destIndex, escaped.length);
                        destIndex += escaped.length;
                    }
                    unescapedChunkStart = nextIndex;
                }
                index = nextEscapeIndex(s, nextIndex, end);
            } else {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
        }
        int cp2 = end - unescapedChunkStart;
        if (cp2 > 0) {
            int endIndex = destIndex + cp2;
            if (dest.length < endIndex) {
                dest = growBuffer(dest, destIndex, endIndex);
            }
            s.getChars(unescapedChunkStart, end, dest, destIndex);
            destIndex = endIndex;
        }
        return new String(dest, 0, destIndex);
    }

    protected static int codePointAt(CharSequence seq, int index, int end) {
        Preconditions.checkNotNull(seq);
        if (index < end) {
            int index2 = index + 1;
            char c1 = seq.charAt(index);
            if (c1 < 55296 || c1 > 57343) {
                return c1;
            }
            if (c1 > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(c1);
                sb.append("' with value ");
                sb.append((int) c1);
                sb.append(" at index ");
                sb.append(index2 - 1);
                sb.append(" in '");
                sb.append((Object) seq);
                sb.append("'");
                throw new IllegalArgumentException(sb.toString());
            } else if (index2 == end) {
                return -c1;
            } else {
                char c2 = seq.charAt(index2);
                if (Character.isLowSurrogate(c2)) {
                    return Character.toCodePoint(c1, c2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + c2 + "' with value " + ((int) c2) + " at index " + index2 + " in '" + ((Object) seq) + "'");
            }
        } else {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
    }

    private static char[] growBuffer(char[] dest, int index, int size) {
        char[] copy = new char[size];
        if (index > 0) {
            System.arraycopy(dest, 0, copy, 0, index);
        }
        return copy;
    }
}
