package com.google.common.escape;

import com.facebook.imagepipeline.common.BytesRange;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

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

    protected ArrayBasedUnicodeEscaper(Map<Character, String> map, int i, int i2, @NullableDecl String str) {
        this(ArrayBasedEscaperMap.create(map), i, i2, str);
    }

    protected ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, int i, int i2, @NullableDecl String str) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        this.replacements = arrayBasedEscaperMap.getReplacementArray();
        this.replacementsLength = this.replacements.length;
        if (i2 < i) {
            i2 = -1;
            i = BytesRange.TO_END_OF_CONTENT;
        }
        this.safeMin = i;
        this.safeMax = i2;
        if (i >= 55296) {
            this.safeMinChar = 65535;
            this.safeMaxChar = 0;
            return;
        }
        this.safeMinChar = (char) i;
        this.safeMaxChar = (char) Math.min(i2, 55295);
    }

    @Override // com.google.common.escape.Escaper, com.google.common.escape.UnicodeEscaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < this.replacementsLength && this.replacements[charAt] != null) || charAt > this.safeMaxChar || charAt < this.safeMinChar) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.escape.UnicodeEscaper
    public final char[] escape(int i) {
        char[] cArr;
        if (i < this.replacementsLength && (cArr = this.replacements[i]) != null) {
            return cArr;
        }
        if (i < this.safeMin || i > this.safeMax) {
            return escapeUnsafe(i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.escape.UnicodeEscaper
    public final int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if ((charAt < this.replacementsLength && this.replacements[charAt] != null) || charAt > this.safeMaxChar || charAt < this.safeMinChar) {
                break;
            }
            i++;
        }
        return i;
    }
}