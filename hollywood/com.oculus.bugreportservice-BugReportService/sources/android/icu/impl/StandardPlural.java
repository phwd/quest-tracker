package android.icu.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum StandardPlural {
    ZERO("zero"),
    ONE("one"),
    TWO("two"),
    FEW("few"),
    MANY("many"),
    OTHER("other");
    
    public static final int COUNT = VALUES.size();
    public static final int OTHER_INDEX = OTHER.ordinal();
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private final String keyword;

    private StandardPlural(String str) {
        this.keyword = str;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public static final StandardPlural orNullFromString(CharSequence charSequence) {
        int length = charSequence.length();
        if (length != 3) {
            if (length != 4) {
                if (length == 5 && "other".contentEquals(charSequence)) {
                    return OTHER;
                }
                return null;
            } else if ("many".contentEquals(charSequence)) {
                return MANY;
            } else {
                if ("zero".contentEquals(charSequence)) {
                    return ZERO;
                }
                return null;
            }
        } else if ("one".contentEquals(charSequence)) {
            return ONE;
        } else {
            if ("two".contentEquals(charSequence)) {
                return TWO;
            }
            if ("few".contentEquals(charSequence)) {
                return FEW;
            }
            return null;
        }
    }

    public static final StandardPlural orOtherFromString(CharSequence charSequence) {
        StandardPlural orNullFromString = orNullFromString(charSequence);
        return orNullFromString != null ? orNullFromString : OTHER;
    }

    public static final StandardPlural fromString(CharSequence charSequence) {
        StandardPlural orNullFromString = orNullFromString(charSequence);
        if (orNullFromString != null) {
            return orNullFromString;
        }
        throw new IllegalArgumentException(charSequence.toString());
    }
}
