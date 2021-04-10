package android.icu.lang;

import android.icu.impl.CaseMapImpl;
import android.icu.impl.IllegalIcuArgumentException;
import android.icu.impl.UCaseProps;
import android.icu.impl.UCharacterName;
import android.icu.impl.UCharacterProperty;
import android.icu.impl.UPropertyAliases;
import android.icu.text.BreakIterator;
import android.icu.util.ULocale;
import android.icu.util.VersionInfo;

public final class UCharacter implements UCharacterEnums$ECharacterCategory, UCharacterEnums$ECharacterDirection {
    public static int getIntPropertyMinValue(int i) {
        return 0;
    }

    public static boolean isISOControl(int i) {
        return i >= 0 && i <= 159 && (i <= 31 || i >= 127);
    }

    public static int digit(int i, int i2) {
        if (2 > i2 || i2 > 36) {
            return -1;
        }
        int digit = digit(i);
        int europeanDigit = digit < 0 ? UCharacterProperty.getEuropeanDigit(i) : digit;
        if (europeanDigit < i2) {
            return europeanDigit;
        }
        return -1;
    }

    public static int digit(int i) {
        return UCharacterProperty.INSTANCE.digit(i);
    }

    public static double getUnicodeNumericValue(int i) {
        return UCharacterProperty.INSTANCE.getUnicodeNumericValue(i);
    }

    public static int getType(int i) {
        return UCharacterProperty.INSTANCE.getType(i);
    }

    public static boolean isDigit(int i) {
        return getType(i) == 9;
    }

    public static boolean isLowerCase(int i) {
        return getType(i) == 2;
    }

    public static boolean isUnicodeIdentifierPart(int i) {
        return ((1 << getType(i)) & 4196222) != 0 || isIdentifierIgnorable(i);
    }

    public static boolean isUnicodeIdentifierStart(int i) {
        return ((1 << getType(i)) & 1086) != 0;
    }

    public static boolean isIdentifierIgnorable(int i) {
        return i <= 159 ? isISOControl(i) && (i < 9 || i > 13) && (i < 28 || i > 31) : getType(i) == 16;
    }

    public static int toLowerCase(int i) {
        return UCaseProps.INSTANCE.tolower(i);
    }

    public static String toString(int i) {
        if (i < 0 || i > 1114111) {
            return null;
        }
        if (i < 65536) {
            return String.valueOf((char) i);
        }
        new String(Character.toChars(i));
        throw null;
    }

    public static VersionInfo getUnicodeVersion() {
        return UCharacterProperty.INSTANCE.m_unicodeVersion_;
    }

    public static String getExtendedName(int i) {
        return UCharacterName.INSTANCE.getName(i, 2);
    }

    public static int getCharFromExtendedName(String str) {
        return UCharacterName.INSTANCE.getCharFromName(2, str);
    }

    public static int getPropertyEnum(CharSequence charSequence) {
        int propertyEnum = UPropertyAliases.INSTANCE.getPropertyEnum(charSequence);
        if (propertyEnum != -1) {
            return propertyEnum;
        }
        throw new IllegalIcuArgumentException("Invalid name: " + ((Object) charSequence));
    }

    public static String getPropertyValueName(int i, int i2, int i3) {
        if ((i != 4098 && i != 4112 && i != 4113) || i2 < getIntPropertyMinValue(4098) || i2 > getIntPropertyMaxValue(4098) || i3 < 0 || i3 >= 2) {
            return UPropertyAliases.INSTANCE.getPropertyValueName(i, i2, i3);
        }
        try {
            return UPropertyAliases.INSTANCE.getPropertyValueName(i, i2, i3);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static int getPropertyValueEnum(int i, CharSequence charSequence) {
        int propertyValueEnum = UPropertyAliases.INSTANCE.getPropertyValueEnum(i, charSequence);
        if (propertyValueEnum != -1) {
            return propertyValueEnum;
        }
        throw new IllegalIcuArgumentException("Invalid name: " + ((Object) charSequence));
    }

    public static int getPropertyValueEnumNoThrow(int i, CharSequence charSequence) {
        return UPropertyAliases.INSTANCE.getPropertyValueEnumNoThrow(i, charSequence);
    }

    private static int getCaseLocale(ULocale uLocale) {
        if (uLocale == null) {
            uLocale = ULocale.getDefault();
        }
        return UCaseProps.getCaseLocale(uLocale);
    }

    public static String toUpperCase(ULocale uLocale, String str) {
        return CaseMapImpl.toUpper(getCaseLocale(uLocale), 0, str);
    }

    public static String toLowerCase(ULocale uLocale, String str) {
        return CaseMapImpl.toLower(getCaseLocale(uLocale), 0, str);
    }

    public static String toTitleCase(ULocale uLocale, String str, BreakIterator breakIterator) {
        return toTitleCase(uLocale, str, breakIterator, 0);
    }

    public static String toTitleCase(ULocale uLocale, String str, BreakIterator breakIterator, int i) {
        if (breakIterator == null && uLocale == null) {
            uLocale = ULocale.getDefault();
        }
        BreakIterator titleBreakIterator = CaseMapImpl.getTitleBreakIterator(uLocale, i, breakIterator);
        titleBreakIterator.setText(str);
        return CaseMapImpl.toTitle(getCaseLocale(uLocale), i, titleBreakIterator, str);
    }

    public static int foldCase(int i, boolean z) {
        return foldCase(i, !z ? 1 : 0);
    }

    public static String foldCase(String str, boolean z) {
        return foldCase(str, !z ? 1 : 0);
    }

    public static int foldCase(int i, int i2) {
        return UCaseProps.INSTANCE.fold(i, i2);
    }

    public static final String foldCase(String str, int i) {
        return CaseMapImpl.fold(i, str);
    }

    public static VersionInfo getAge(int i) {
        if (i >= 0 && i <= 1114111) {
            return UCharacterProperty.INSTANCE.getAge(i);
        }
        throw new IllegalArgumentException("Codepoint out of bounds");
    }

    public static boolean hasBinaryProperty(int i, int i2) {
        return UCharacterProperty.INSTANCE.hasBinaryProperty(i, i2);
    }

    public static boolean isUAlphabetic(int i) {
        return hasBinaryProperty(i, 0);
    }

    public static boolean isUWhiteSpace(int i) {
        return hasBinaryProperty(i, 31);
    }

    public static int getIntPropertyValue(int i, int i2) {
        return UCharacterProperty.INSTANCE.getIntPropertyValue(i, i2);
    }

    public static int getIntPropertyMaxValue(int i) {
        return UCharacterProperty.INSTANCE.getIntPropertyMaxValue(i);
    }
}
