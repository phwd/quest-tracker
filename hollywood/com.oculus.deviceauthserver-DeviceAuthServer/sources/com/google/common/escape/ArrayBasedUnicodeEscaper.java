package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nullable;

@Beta
@GwtCompatible
public abstract class ArrayBasedUnicodeEscaper extends UnicodeEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final int safeMax;
    private final char safeMaxChar;
    private final int safeMin;
    private final char safeMinChar;

    /* access modifiers changed from: protected */
    public abstract char[] escapeUnsafe(int i);

    protected ArrayBasedUnicodeEscaper(Map<Character, String> replacementMap, int safeMin2, int safeMax2, @Nullable String unsafeReplacement) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin2, safeMax2, unsafeReplacement);
    }

    protected ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap escaperMap, int safeMin2, int safeMax2, @Nullable String unsafeReplacement) {
        Preconditions.checkNotNull(escaperMap);
        this.replacements = escaperMap.getReplacementArray();
        this.replacementsLength = this.replacements.length;
        if (safeMax2 < safeMin2) {
            safeMax2 = -1;
            safeMin2 = Integer.MAX_VALUE;
        }
        this.safeMin = safeMin2;
        this.safeMax = safeMax2;
        if (safeMin2 >= 55296) {
            this.safeMinChar = 65535;
            this.safeMaxChar = 0;
            return;
        }
        this.safeMinChar = (char) safeMin2;
        this.safeMaxChar = (char) Math.min(safeMax2, 55295);
    }

    @Override // com.google.common.escape.Escaper, com.google.common.escape.UnicodeEscaper
    public final String escape(String s) {
        Preconditions.checkNotNull(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c < this.replacementsLength && this.replacements[c] != null) || c > this.safeMaxChar || c < this.safeMinChar) {
                return escapeSlow(s, i);
            }
        }
        return s;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.escape.UnicodeEscaper
    public final int nextEscapeIndex(CharSequence csq, int index, int end) {
        while (index < end) {
            char c = csq.charAt(index);
            if ((c < this.replacementsLength && this.replacements[c] != null) || c > this.safeMaxChar || c < this.safeMinChar) {
                break;
            }
            index++;
        }
        return index;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.escape.UnicodeEscaper
    public final char[] escape(int cp) {
        char[] chars;
        if (cp < this.replacementsLength && (chars = this.replacements[cp]) != null) {
            return chars;
        }
        if (cp < this.safeMin || cp > this.safeMax) {
            return escapeUnsafe(cp);
        }
        return null;
    }
}
