package android.icu.lang;

import android.icu.impl.CharacterPropertiesImpl;
import android.icu.text.UnicodeSet;
import android.icu.util.CodePointMap;

public final class CharacterProperties {
    private static final CodePointMap[] maps = new CodePointMap[25];
    private static final UnicodeSet[] sets = new UnicodeSet[65];

    private static UnicodeSet makeSet(int i) {
        UnicodeSet unicodeSet = new UnicodeSet();
        UnicodeSet inclusionsForProperty = CharacterPropertiesImpl.getInclusionsForProperty(i);
        int rangeCount = inclusionsForProperty.getRangeCount();
        int i2 = -1;
        for (int i3 = 0; i3 < rangeCount; i3++) {
            int rangeEnd = inclusionsForProperty.getRangeEnd(i3);
            for (int rangeStart = inclusionsForProperty.getRangeStart(i3); rangeStart <= rangeEnd; rangeStart++) {
                if (UCharacter.hasBinaryProperty(rangeStart, i)) {
                    if (i2 < 0) {
                        i2 = rangeStart;
                    }
                } else if (i2 >= 0) {
                    unicodeSet.add(i2, rangeStart - 1);
                    i2 = -1;
                }
            }
        }
        if (i2 >= 0) {
            unicodeSet.add(i2, 1114111);
        }
        unicodeSet.freeze();
        return unicodeSet;
    }

    public static final UnicodeSet getBinaryPropertySet(int i) {
        UnicodeSet unicodeSet;
        if (i < 0 || 65 <= i) {
            throw new IllegalArgumentException("" + i + " is not a constant for a UProperty binary property");
        }
        synchronized (sets) {
            unicodeSet = sets[i];
            if (unicodeSet == null) {
                UnicodeSet[] unicodeSetArr = sets;
                UnicodeSet makeSet = makeSet(i);
                unicodeSetArr[i] = makeSet;
                unicodeSet = makeSet;
            }
        }
        return unicodeSet;
    }
}
