package android.icu.impl;

import android.icu.text.PluralRules;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum StandardPlural {
    ZERO(PluralRules.KEYWORD_ZERO),
    ONE(PluralRules.KEYWORD_ONE),
    TWO(PluralRules.KEYWORD_TWO),
    FEW(PluralRules.KEYWORD_FEW),
    MANY(PluralRules.KEYWORD_MANY),
    OTHER(PluralRules.KEYWORD_OTHER);
    
    public static final int COUNT = VALUES.size();
    public static final int OTHER_INDEX = OTHER.ordinal();
    public static final List<StandardPlural> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private final String keyword;

    private StandardPlural(String kw) {
        this.keyword = kw;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public static final StandardPlural orNullFromString(CharSequence keyword2) {
        int length = keyword2.length();
        if (length != 3) {
            if (length != 4) {
                if (length == 5 && PluralRules.KEYWORD_OTHER.contentEquals(keyword2)) {
                    return OTHER;
                }
                return null;
            } else if (PluralRules.KEYWORD_MANY.contentEquals(keyword2)) {
                return MANY;
            } else {
                if (PluralRules.KEYWORD_ZERO.contentEquals(keyword2)) {
                    return ZERO;
                }
                return null;
            }
        } else if (PluralRules.KEYWORD_ONE.contentEquals(keyword2)) {
            return ONE;
        } else {
            if (PluralRules.KEYWORD_TWO.contentEquals(keyword2)) {
                return TWO;
            }
            if (PluralRules.KEYWORD_FEW.contentEquals(keyword2)) {
                return FEW;
            }
            return null;
        }
    }

    public static final StandardPlural orOtherFromString(CharSequence keyword2) {
        StandardPlural p = orNullFromString(keyword2);
        return p != null ? p : OTHER;
    }

    public static final StandardPlural fromString(CharSequence keyword2) {
        StandardPlural p = orNullFromString(keyword2);
        if (p != null) {
            return p;
        }
        throw new IllegalArgumentException(keyword2.toString());
    }

    public static final int indexOrNegativeFromString(CharSequence keyword2) {
        StandardPlural p = orNullFromString(keyword2);
        if (p != null) {
            return p.ordinal();
        }
        return -1;
    }

    public static final int indexOrOtherIndexFromString(CharSequence keyword2) {
        StandardPlural p = orNullFromString(keyword2);
        return p != null ? p.ordinal() : OTHER.ordinal();
    }

    public static final int indexFromString(CharSequence keyword2) {
        StandardPlural p = orNullFromString(keyword2);
        if (p != null) {
            return p.ordinal();
        }
        throw new IllegalArgumentException(keyword2.toString());
    }
}
