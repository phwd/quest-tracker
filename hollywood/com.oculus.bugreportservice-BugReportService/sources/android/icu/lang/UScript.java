package android.icu.lang;

import android.icu.impl.UCharacterProperty;
import android.icu.util.ULocale;

public final class UScript {
    private static final ScriptUsage[] usageValues = ScriptUsage.values();

    public enum ScriptUsage {
        NOT_ENCODED,
        UNKNOWN,
        EXCLUDED,
        LIMITED_USE,
        ASPIRATIONAL,
        RECOMMENDED
    }

    private static int[] getCodesFromLocale(ULocale uLocale) {
        int codeFromName;
        String language = uLocale.getLanguage();
        if (language.equals("ja")) {
            return new int[]{22, 20, 17};
        }
        if (language.equals("ko")) {
            return new int[]{18, 17};
        }
        String script = uLocale.getScript();
        if (language.equals("zh") && script.equals("Hant")) {
            return new int[]{17, 5};
        }
        if (script.length() == 0 || (codeFromName = getCodeFromName(script)) == -1) {
            return null;
        }
        if (codeFromName == 73 || codeFromName == 74) {
            codeFromName = 17;
        }
        return new int[]{codeFromName};
    }

    private static int[] findCodeFromLocale(ULocale uLocale) {
        int[] codesFromLocale = getCodesFromLocale(uLocale);
        if (codesFromLocale != null) {
            return codesFromLocale;
        }
        return getCodesFromLocale(ULocale.addLikelySubtags(uLocale));
    }

    public static final int[] getCode(String str) {
        boolean z;
        int propertyValueEnumNoThrow;
        if (str.indexOf(95) >= 0 || str.indexOf(45) >= 0) {
            z = false;
        } else {
            int propertyValueEnumNoThrow2 = UCharacter.getPropertyValueEnumNoThrow(4106, str);
            if (propertyValueEnumNoThrow2 != -1) {
                return new int[]{propertyValueEnumNoThrow2};
            }
            z = true;
        }
        int[] findCodeFromLocale = findCodeFromLocale(new ULocale(str));
        if (findCodeFromLocale != null) {
            return findCodeFromLocale;
        }
        if (z || (propertyValueEnumNoThrow = UCharacter.getPropertyValueEnumNoThrow(4106, str)) == -1) {
            return null;
        }
        return new int[]{propertyValueEnumNoThrow};
    }

    public static final int getCodeFromName(String str) {
        int propertyValueEnumNoThrow = UCharacter.getPropertyValueEnumNoThrow(4106, str);
        if (propertyValueEnumNoThrow == -1) {
            return -1;
        }
        return propertyValueEnumNoThrow;
    }

    public static final int getScript(int i) {
        if ((i >= 0) && (i <= 1114111)) {
            int additional = UCharacterProperty.INSTANCE.getAdditional(i, 0) & 12583167;
            if (additional < 4194304) {
                return additional;
            }
            if (additional < 8388608) {
                return 0;
            }
            if (additional < 12582912) {
                return 1;
            }
            return UCharacterProperty.INSTANCE.m_scriptExtensions_[additional & 255];
        }
        throw new IllegalArgumentException(Integer.toString(i));
    }

    public static final boolean hasScript(int i, int i2) {
        int additional = UCharacterProperty.INSTANCE.getAdditional(i, 0) & 12583167;
        if (additional < 4194304) {
            return i2 == additional;
        }
        char[] cArr = UCharacterProperty.INSTANCE.m_scriptExtensions_;
        int i3 = additional & 255;
        char c = i3;
        if (additional >= 12582912) {
            c = cArr[i3 + 1];
        }
        int i4 = c;
        if (i2 > 32767) {
            return false;
        }
        while (i2 > cArr[i4 == 1 ? 1 : 0]) {
            i4 = (i4 == 1 ? 1 : 0) + 1;
        }
        return i2 == (32767 & cArr[i4]);
    }

    public static final String getName(int i) {
        return UCharacter.getPropertyValueName(4106, i, 1);
    }
}
