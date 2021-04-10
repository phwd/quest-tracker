package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;

@Beta
@GwtCompatible
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final char safeMax;
    private final char safeMin;

    /* access modifiers changed from: protected */
    public abstract char[] escapeUnsafe(char c);

    protected ArrayBasedCharEscaper(Map<Character, String> replacementMap, char safeMin2, char safeMax2) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin2, safeMax2);
    }

    protected ArrayBasedCharEscaper(ArrayBasedEscaperMap escaperMap, char safeMin2, char safeMax2) {
        Preconditions.checkNotNull(escaperMap);
        this.replacements = escaperMap.getReplacementArray();
        this.replacementsLength = this.replacements.length;
        if (safeMax2 < safeMin2) {
            safeMax2 = 0;
            safeMin2 = 65535;
        }
        this.safeMin = safeMin2;
        this.safeMax = safeMax2;
    }

    @Override // com.google.common.escape.Escaper, com.google.common.escape.CharEscaper
    public final String escape(String s) {
        Preconditions.checkNotNull(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c < this.replacementsLength && this.replacements[c] != null) || c > this.safeMax || c < this.safeMin) {
                return escapeSlow(s, i);
            }
        }
        return s;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.escape.CharEscaper
    public final char[] escape(char c) {
        char[] chars;
        if (c < this.replacementsLength && (chars = this.replacements[c]) != null) {
            return chars;
        }
        if (c < this.safeMin || c > this.safeMax) {
            return escapeUnsafe(c);
        }
        return null;
    }
}
