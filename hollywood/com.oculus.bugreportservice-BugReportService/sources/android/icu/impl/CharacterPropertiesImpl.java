package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.UnicodeSet;

public final class CharacterPropertiesImpl {
    private static final UnicodeSet[] inclusions = new UnicodeSet[40];

    private static UnicodeSet getInclusionsForSource(int i) {
        if (inclusions[i] == null) {
            UnicodeSet unicodeSet = new UnicodeSet();
            switch (i) {
                case 1:
                    UCharacterProperty.INSTANCE.addPropertyStarts(unicodeSet);
                    break;
                case 2:
                    UCharacterProperty.INSTANCE.upropsvec_addPropertyStarts(unicodeSet);
                    break;
                case 3:
                default:
                    throw new IllegalStateException("getInclusions(unknown src " + i + ")");
                case 4:
                    UCaseProps.INSTANCE.addPropertyStarts(unicodeSet);
                    break;
                case 5:
                    UBiDiProps.INSTANCE.addPropertyStarts(unicodeSet);
                    break;
                case 6:
                    UCharacterProperty.INSTANCE.addPropertyStarts(unicodeSet);
                    UCharacterProperty.INSTANCE.upropsvec_addPropertyStarts(unicodeSet);
                    break;
                case 7:
                    Norm2AllModes.getNFCInstance().impl.addPropertyStarts(unicodeSet);
                    UCaseProps.INSTANCE.addPropertyStarts(unicodeSet);
                    break;
                case 8:
                    Norm2AllModes.getNFCInstance().impl.addPropertyStarts(unicodeSet);
                    break;
                case 9:
                    Norm2AllModes.getNFKCInstance().impl.addPropertyStarts(unicodeSet);
                    break;
                case 10:
                    Norm2AllModes.getNFKC_CFInstance().impl.addPropertyStarts(unicodeSet);
                    break;
                case 11:
                    Norm2AllModes.getNFCInstance().impl.addCanonIterPropertyStarts(unicodeSet);
                    break;
                case 12:
                case 13:
                case 14:
                    UCharacterProperty.INSTANCE.ulayout_addPropertyStarts(i, unicodeSet);
                    break;
            }
            UnicodeSet[] unicodeSetArr = inclusions;
            unicodeSet.compact();
            unicodeSetArr[i] = unicodeSet;
        }
        return inclusions[i];
    }

    private static UnicodeSet getIntPropInclusions(int i) {
        int i2 = (i + 15) - 4096;
        UnicodeSet[] unicodeSetArr = inclusions;
        if (unicodeSetArr[i2] != null) {
            return unicodeSetArr[i2];
        }
        UnicodeSet inclusionsForSource = getInclusionsForSource(UCharacterProperty.INSTANCE.getSource(i));
        UnicodeSet unicodeSet = new UnicodeSet(0, 0);
        int rangeCount = inclusionsForSource.getRangeCount();
        int i3 = 0;
        for (int i4 = 0; i4 < rangeCount; i4++) {
            int rangeEnd = inclusionsForSource.getRangeEnd(i4);
            for (int rangeStart = inclusionsForSource.getRangeStart(i4); rangeStart <= rangeEnd; rangeStart++) {
                int intPropertyValue = UCharacter.getIntPropertyValue(rangeStart, i);
                if (intPropertyValue != i3) {
                    unicodeSet.add(rangeStart);
                    i3 = intPropertyValue;
                }
            }
        }
        UnicodeSet[] unicodeSetArr2 = inclusions;
        unicodeSet.compact();
        unicodeSetArr2[i2] = unicodeSet;
        return unicodeSet;
    }

    public static synchronized UnicodeSet getInclusionsForProperty(int i) {
        synchronized (CharacterPropertiesImpl.class) {
            if (4096 > i || i >= 4121) {
                return getInclusionsForSource(UCharacterProperty.INSTANCE.getSource(i));
            }
            return getIntPropInclusions(i);
        }
    }
}
