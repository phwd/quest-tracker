package android.icu.impl.locale;

import android.icu.impl.ValidIdentifiers;
import android.icu.impl.locale.KeyTypeData;
import android.icu.text.DateFormat;
import android.icu.util.IllformedLocaleException;
import android.icu.util.Output;
import android.icu.util.ULocale;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class LocaleValidityChecker {
    static final Set<ValidIdentifiers.Datasubtype> REGULAR_ONLY = EnumSet.of(ValidIdentifiers.Datasubtype.regular);
    static final Set<String> REORDERING_EXCLUDE = new HashSet(Arrays.asList("zinh", "zyyy"));
    static final Set<String> REORDERING_INCLUDE = new HashSet(Arrays.asList("space", "punct", "symbol", "currency", "digit", "others", DateFormat.SPECIFIC_TZ));
    static Pattern SEPARATOR = Pattern.compile("[-_]");
    private static final Pattern VALID_X = Pattern.compile("[a-zA-Z0-9]{2,8}(-[a-zA-Z0-9]{2,8})*");
    private final boolean allowsDeprecated;
    private final Set<ValidIdentifiers.Datasubtype> datasubtypes;

    public static class Where {
        public String codeFailure;
        public ValidIdentifiers.Datatype fieldFailure;

        public boolean set(ValidIdentifiers.Datatype datatype, String code) {
            this.fieldFailure = datatype;
            this.codeFailure = code;
            return false;
        }

        public String toString() {
            if (this.fieldFailure == null) {
                return "OK";
            }
            return "{" + ((Object) this.fieldFailure) + ", " + this.codeFailure + "}";
        }
    }

    public LocaleValidityChecker(Set<ValidIdentifiers.Datasubtype> datasubtypes2) {
        this.datasubtypes = EnumSet.copyOf(datasubtypes2);
        this.allowsDeprecated = datasubtypes2.contains(ValidIdentifiers.Datasubtype.deprecated);
    }

    public LocaleValidityChecker(ValidIdentifiers.Datasubtype... datasubtypes2) {
        this.datasubtypes = EnumSet.copyOf(Arrays.asList(datasubtypes2));
        this.allowsDeprecated = this.datasubtypes.contains(ValidIdentifiers.Datasubtype.deprecated);
    }

    public Set<ValidIdentifiers.Datasubtype> getDatasubtypes() {
        return EnumSet.copyOf(this.datasubtypes);
    }

    public boolean isValid(ULocale locale, Where where) {
        where.set(null, null);
        String language = locale.getLanguage();
        String script = locale.getScript();
        String region = locale.getCountry();
        String variantString = locale.getVariant();
        Set<Character> extensionKeys = locale.getExtensionKeys();
        if (!isValid(ValidIdentifiers.Datatype.language, language, where)) {
            if (!language.equals("x")) {
                return false;
            }
            where.set(null, null);
            return true;
        } else if (!isValid(ValidIdentifiers.Datatype.script, script, where) || !isValid(ValidIdentifiers.Datatype.region, region, where)) {
            return false;
        } else {
            if (!variantString.isEmpty()) {
                for (String variant : SEPARATOR.split(variantString)) {
                    if (!isValid(ValidIdentifiers.Datatype.variant, variant, where)) {
                        return false;
                    }
                }
            }
            for (Character c : extensionKeys) {
                try {
                    ValidIdentifiers.Datatype datatype = ValidIdentifiers.Datatype.valueOf(((Object) c) + "");
                    int i = AnonymousClass1.$SwitchMap$android$icu$impl$ValidIdentifiers$Datatype[datatype.ordinal()];
                    if (i == 1) {
                        return true;
                    }
                    if (i == 2 || i == 3) {
                        if (!isValidU(locale, datatype, locale.getExtension(c.charValue()), where)) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return where.set(ValidIdentifiers.Datatype.illegal, ((Object) c) + "");
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public enum SpecialCase {
        normal,
        anything,
        reorder,
        codepoints,
        subdivision,
        rgKey;

        static SpecialCase get(String key) {
            if (key.equals("kr")) {
                return reorder;
            }
            if (key.equals("vt")) {
                return codepoints;
            }
            if (key.equals("sd")) {
                return subdivision;
            }
            if (key.equals("rg")) {
                return rgKey;
            }
            if (key.equals("x0")) {
                return anything;
            }
            return normal;
        }
    }

    private boolean isValidU(ULocale locale, ValidIdentifiers.Datatype datatype, String extensionString, Where where) {
        KeyTypeData.ValueType valueType;
        SpecialCase specialCase;
        KeyTypeData.ValueType valueType2 = null;
        SpecialCase specialCase2 = null;
        StringBuilder prefix = new StringBuilder();
        Set<String> seen = new HashSet<>();
        StringBuilder tBuffer = datatype == ValidIdentifiers.Datatype.t ? new StringBuilder() : null;
        String[] split = SEPARATOR.split(extensionString);
        int length = split.length;
        int typeCount = 0;
        String key = "";
        int i = 0;
        while (i < length) {
            String subtag = split[i];
            if (subtag.length() == 2 && (tBuffer == null || subtag.charAt(1) <= '9')) {
                if (tBuffer != null) {
                    if (tBuffer.length() != 0 && !isValidLocale(tBuffer.toString(), where)) {
                        return false;
                    }
                    tBuffer = null;
                }
                key = KeyTypeData.toBcpKey(subtag);
                if (key == null) {
                    return where.set(datatype, subtag);
                }
                if (!this.allowsDeprecated && KeyTypeData.isDeprecated(key)) {
                    return where.set(datatype, key);
                }
                KeyTypeData.ValueType valueType3 = KeyTypeData.getValueType(key);
                specialCase2 = SpecialCase.get(key);
                valueType = valueType3;
                typeCount = 0;
            } else if (tBuffer != null) {
                if (tBuffer.length() != 0) {
                    tBuffer.append('-');
                }
                tBuffer.append(subtag);
                valueType = valueType2;
            } else {
                typeCount++;
                int i2 = AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$ValueType[valueType2.ordinal()];
                valueType = valueType2;
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3 && typeCount == 1) {
                            seen.clear();
                        }
                    } else if (typeCount == 1) {
                        prefix.setLength(0);
                        prefix.append(subtag);
                    } else {
                        prefix.append('-');
                        prefix.append(subtag);
                        subtag = prefix.toString();
                    }
                } else if (typeCount > 1) {
                    return where.set(datatype, key + "-" + subtag);
                }
                int i3 = AnonymousClass1.$SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase[specialCase2.ordinal()];
                if (i3 == 1) {
                    specialCase = specialCase2;
                } else if (i3 != 2) {
                    specialCase = specialCase2;
                    if (i3 == 3) {
                        if (!seen.add(subtag.equals(DateFormat.SPECIFIC_TZ) ? "others" : subtag) || !isScriptReorder(subtag)) {
                            return where.set(datatype, key + "-" + subtag);
                        }
                    } else if (i3 != 4) {
                        if (i3 != 5) {
                            if (KeyTypeData.toBcpType(key, subtag, new Output<>(), new Output<>()) == null) {
                                return where.set(datatype, key + "-" + subtag);
                            } else if (!this.allowsDeprecated && KeyTypeData.isDeprecated(key, subtag)) {
                                return where.set(datatype, key + "-" + subtag);
                            }
                        } else if (subtag.length() < 6 || !subtag.endsWith(DateFormat.SPECIFIC_TZ)) {
                            return where.set(datatype, subtag);
                        } else {
                            if (!isValid(ValidIdentifiers.Datatype.region, subtag.substring(0, subtag.length() - 4), where)) {
                                return false;
                            }
                        }
                    } else if (!isSubdivision(locale, subtag)) {
                        return where.set(datatype, key + "-" + subtag);
                    }
                } else {
                    specialCase = specialCase2;
                    try {
                        if (Integer.parseInt(subtag, 16) > 1114111) {
                            return where.set(datatype, key + "-" + subtag);
                        }
                    } catch (NumberFormatException e) {
                        return where.set(datatype, key + "-" + subtag);
                    }
                }
                specialCase2 = specialCase;
            }
            i++;
            split = split;
            valueType2 = valueType;
        }
        if (tBuffer == null || tBuffer.length() == 0 || isValidLocale(tBuffer.toString(), where)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.locale.LocaleValidityChecker$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$ValidIdentifiers$Datatype = new int[ValidIdentifiers.Datatype.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$locale$KeyTypeData$ValueType = new int[KeyTypeData.ValueType.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase = new int[SpecialCase.values().length];

        static {
            try {
                $SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase[SpecialCase.anything.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase[SpecialCase.codepoints.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase[SpecialCase.reorder.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase[SpecialCase.subdivision.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$LocaleValidityChecker$SpecialCase[SpecialCase.rgKey.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$KeyTypeData$ValueType[KeyTypeData.ValueType.single.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$KeyTypeData$ValueType[KeyTypeData.ValueType.incremental.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$KeyTypeData$ValueType[KeyTypeData.ValueType.multiple.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$icu$impl$ValidIdentifiers$Datatype[ValidIdentifiers.Datatype.x.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$icu$impl$ValidIdentifiers$Datatype[ValidIdentifiers.Datatype.t.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$icu$impl$ValidIdentifiers$Datatype[ValidIdentifiers.Datatype.u.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    private boolean isSubdivision(ULocale locale, String subtag) {
        int i = 3;
        if (subtag.length() < 3) {
            return false;
        }
        if (subtag.charAt(0) > '9') {
            i = 2;
        }
        String region = subtag.substring(0, i);
        if (ValidIdentifiers.isValid(ValidIdentifiers.Datatype.subdivision, this.datasubtypes, region, subtag.substring(region.length())) == null) {
            return false;
        }
        String localeRegion = locale.getCountry();
        if (localeRegion.isEmpty()) {
            localeRegion = ULocale.addLikelySubtags(locale).getCountry();
        }
        if (!region.equalsIgnoreCase(localeRegion)) {
            return false;
        }
        return true;
    }

    private boolean isScriptReorder(String subtag) {
        String subtag2 = AsciiUtil.toLowerString(subtag);
        if (REORDERING_INCLUDE.contains(subtag2)) {
            return true;
        }
        if (REORDERING_EXCLUDE.contains(subtag2)) {
            return false;
        }
        if (ValidIdentifiers.isValid(ValidIdentifiers.Datatype.script, REGULAR_ONLY, subtag2) != null) {
            return true;
        }
        return false;
    }

    private boolean isValidLocale(String extensionString, Where where) {
        try {
            return isValid(new ULocale.Builder().setLanguageTag(extensionString).build(), where);
        } catch (IllformedLocaleException e) {
            return where.set(ValidIdentifiers.Datatype.t, SEPARATOR.split(extensionString.substring(e.getErrorIndex()))[0]);
        } catch (Exception e2) {
            return where.set(ValidIdentifiers.Datatype.t, e2.getMessage());
        }
    }

    private boolean isValid(ValidIdentifiers.Datatype datatype, String code, Where where) {
        if (code.isEmpty()) {
            return true;
        }
        if ((datatype == ValidIdentifiers.Datatype.variant && "posix".equalsIgnoreCase(code)) || ValidIdentifiers.isValid(datatype, this.datasubtypes, code) != null) {
            return true;
        }
        if (where == null) {
            return false;
        }
        return where.set(datatype, code);
    }
}
