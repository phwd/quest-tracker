package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.UnicodeSet;

public final class CharacterPropertiesImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int NUM_INCLUSIONS = 40;
    private static final UnicodeSet[] inclusions = new UnicodeSet[40];

    public static synchronized void clear() {
        synchronized (CharacterPropertiesImpl.class) {
            for (int i = 0; i < inclusions.length; i++) {
                inclusions[i] = null;
            }
        }
    }

    private static UnicodeSet getInclusionsForSource(int src) {
        if (inclusions[src] == null) {
            UnicodeSet incl = new UnicodeSet();
            switch (src) {
                case 1:
                    UCharacterProperty.INSTANCE.addPropertyStarts(incl);
                    break;
                case 2:
                    UCharacterProperty.INSTANCE.upropsvec_addPropertyStarts(incl);
                    break;
                case 3:
                default:
                    throw new IllegalStateException("getInclusions(unknown src " + src + ")");
                case 4:
                    UCaseProps.INSTANCE.addPropertyStarts(incl);
                    break;
                case 5:
                    UBiDiProps.INSTANCE.addPropertyStarts(incl);
                    break;
                case 6:
                    UCharacterProperty.INSTANCE.addPropertyStarts(incl);
                    UCharacterProperty.INSTANCE.upropsvec_addPropertyStarts(incl);
                    break;
                case 7:
                    Norm2AllModes.getNFCInstance().impl.addPropertyStarts(incl);
                    UCaseProps.INSTANCE.addPropertyStarts(incl);
                    break;
                case 8:
                    Norm2AllModes.getNFCInstance().impl.addPropertyStarts(incl);
                    break;
                case 9:
                    Norm2AllModes.getNFKCInstance().impl.addPropertyStarts(incl);
                    break;
                case 10:
                    Norm2AllModes.getNFKC_CFInstance().impl.addPropertyStarts(incl);
                    break;
                case 11:
                    Norm2AllModes.getNFCInstance().impl.addCanonIterPropertyStarts(incl);
                    break;
                case 12:
                case 13:
                case 14:
                    UCharacterProperty.INSTANCE.ulayout_addPropertyStarts(src, incl);
                    break;
            }
            inclusions[src] = incl.compact();
        }
        return inclusions[src];
    }

    private static UnicodeSet getIntPropInclusions(int prop) {
        int inclIndex = (prop + 15) - 4096;
        UnicodeSet[] unicodeSetArr = inclusions;
        if (unicodeSetArr[inclIndex] != null) {
            return unicodeSetArr[inclIndex];
        }
        UnicodeSet incl = getInclusionsForSource(UCharacterProperty.INSTANCE.getSource(prop));
        UnicodeSet intPropIncl = new UnicodeSet(0, 0);
        int numRanges = incl.getRangeCount();
        int prevValue = 0;
        for (int i = 0; i < numRanges; i++) {
            int rangeEnd = incl.getRangeEnd(i);
            for (int c = incl.getRangeStart(i); c <= rangeEnd; c++) {
                int value = UCharacter.getIntPropertyValue(c, prop);
                if (value != prevValue) {
                    intPropIncl.add(c);
                    prevValue = value;
                }
            }
        }
        UnicodeSet[] unicodeSetArr2 = inclusions;
        UnicodeSet compact = intPropIncl.compact();
        unicodeSetArr2[inclIndex] = compact;
        return compact;
    }

    public static synchronized UnicodeSet getInclusionsForProperty(int prop) {
        synchronized (CharacterPropertiesImpl.class) {
            if (4096 > prop || prop >= 4121) {
                return getInclusionsForSource(UCharacterProperty.INSTANCE.getSource(prop));
            }
            return getIntPropInclusions(prop);
        }
    }
}
