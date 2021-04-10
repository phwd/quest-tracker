package android.icu.lang;

import android.icu.impl.CharacterPropertiesImpl;
import android.icu.text.UnicodeSet;
import android.icu.util.CodePointMap;
import android.icu.util.CodePointTrie;
import android.icu.util.MutableCodePointTrie;

public final class CharacterProperties {
    private static final CodePointMap[] maps = new CodePointMap[25];
    private static final UnicodeSet[] sets = new UnicodeSet[65];

    private CharacterProperties() {
    }

    private static UnicodeSet makeSet(int property) {
        UnicodeSet set = new UnicodeSet();
        UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(property);
        int numRanges = inclusions.getRangeCount();
        int startHasProperty = -1;
        for (int i = 0; i < numRanges; i++) {
            int rangeEnd = inclusions.getRangeEnd(i);
            for (int c = inclusions.getRangeStart(i); c <= rangeEnd; c++) {
                if (UCharacter.hasBinaryProperty(c, property)) {
                    if (startHasProperty < 0) {
                        startHasProperty = c;
                    }
                } else if (startHasProperty >= 0) {
                    set.add(startHasProperty, c - 1);
                    startHasProperty = -1;
                }
            }
        }
        if (startHasProperty >= 0) {
            set.add(startHasProperty, 1114111);
        }
        return set.freeze();
    }

    private static CodePointMap makeMap(int property) {
        CodePointTrie.Type type;
        CodePointTrie.ValueWidth valueWidth;
        int nullValue = property == 4106 ? 103 : 0;
        MutableCodePointTrie mutableTrie = new MutableCodePointTrie(nullValue, nullValue);
        UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(property);
        int numRanges = inclusions.getRangeCount();
        int start = 0;
        int value = nullValue;
        for (int i = 0; i < numRanges; i++) {
            int rangeEnd = inclusions.getRangeEnd(i);
            for (int c = inclusions.getRangeStart(i); c <= rangeEnd; c++) {
                int nextValue = UCharacter.getIntPropertyValue(c, property);
                if (value != nextValue) {
                    if (value != nullValue) {
                        mutableTrie.setRange(start, c - 1, value);
                    }
                    start = c;
                    value = nextValue;
                }
            }
        }
        if (value != 0) {
            mutableTrie.setRange(start, 1114111, value);
        }
        if (property == 4096 || property == 4101) {
            type = CodePointTrie.Type.FAST;
        } else {
            type = CodePointTrie.Type.SMALL;
        }
        int max = UCharacter.getIntPropertyMaxValue(property);
        if (max <= 255) {
            valueWidth = CodePointTrie.ValueWidth.BITS_8;
        } else if (max <= 65535) {
            valueWidth = CodePointTrie.ValueWidth.BITS_16;
        } else {
            valueWidth = CodePointTrie.ValueWidth.BITS_32;
        }
        return mutableTrie.buildImmutable(type, valueWidth);
    }

    public static final UnicodeSet getBinaryPropertySet(int property) {
        UnicodeSet set;
        if (property < 0 || 65 <= property) {
            throw new IllegalArgumentException("" + property + " is not a constant for a UProperty binary property");
        }
        synchronized (sets) {
            set = sets[property];
            if (set == null) {
                UnicodeSet[] unicodeSetArr = sets;
                UnicodeSet makeSet = makeSet(property);
                set = makeSet;
                unicodeSetArr[property] = makeSet;
            }
        }
        return set;
    }

    public static final CodePointMap getIntPropertyMap(int property) {
        CodePointMap map;
        if (property < 4096 || 4121 <= property) {
            throw new IllegalArgumentException("" + property + " is not a constant for a UProperty int property");
        }
        synchronized (maps) {
            map = maps[property - 4096];
            if (map == null) {
                CodePointMap makeMap = makeMap(property);
                map = makeMap;
                maps[property - 4096] = makeMap;
            }
        }
        return map;
    }
}
