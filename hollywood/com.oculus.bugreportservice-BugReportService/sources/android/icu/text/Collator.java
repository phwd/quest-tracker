package android.icu.text;

import android.icu.impl.ICUDebug;
import android.icu.lang.UCharacter;
import android.icu.util.Freezable;
import android.icu.util.ICUException;
import android.icu.util.ULocale;
import java.util.Comparator;
import java.util.Locale;
import java.util.MissingResourceException;

public abstract class Collator implements Comparator, Freezable, Cloneable {
    private static final boolean DEBUG = ICUDebug.enabled("collator");
    private static final String[] KEYWORDS = {"collation"};
    private static ServiceShim shim;

    public abstract int compare(String str, String str2);

    public int hashCode() {
        return 0;
    }

    public boolean isFrozen() {
        return false;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass());
    }

    private void checkNotFrozen() {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen Collator");
        }
    }

    public void setStrength(int i) {
        checkNotFrozen();
    }

    public void setDecomposition(int i) {
        checkNotFrozen();
    }

    public void setReorderCodes(int... iArr) {
        throw new UnsupportedOperationException("Needs to be implemented by the subclass.");
    }

    public Object clone() {
        return super.clone();
    }

    /* access modifiers changed from: package-private */
    public static abstract class ServiceShim {
        /* access modifiers changed from: package-private */
        public abstract Collator getInstance(ULocale uLocale);

        ServiceShim() {
        }
    }

    private static ServiceShim getShim() {
        if (shim == null) {
            try {
                shim = (ServiceShim) Class.forName("android.icu.text.CollatorServiceShim").newInstance();
            } catch (MissingResourceException e) {
                throw e;
            } catch (Exception e2) {
                if (DEBUG) {
                    e2.printStackTrace();
                }
                throw new ICUException(e2);
            }
        }
        return shim;
    }

    /* access modifiers changed from: private */
    public static final class ASCII {
        static boolean equalIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
            int length = charSequence.length();
            if (length != charSequence2.length()) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                char charAt = charSequence.charAt(i);
                char charAt2 = charSequence2.charAt(i);
                if (charAt != charAt2) {
                    if ('A' > charAt || charAt > 'Z') {
                        if ('A' <= charAt2 && charAt2 <= 'Z' && charAt2 + ' ' == charAt) {
                        }
                    } else if (charAt + ' ' == charAt2) {
                    }
                    return false;
                }
            }
            return true;
        }
    }

    private static final boolean getYesOrNo(String str, String str2) {
        if (ASCII.equalIgnoreCase(str2, "yes")) {
            return true;
        }
        if (ASCII.equalIgnoreCase(str2, "no")) {
            return false;
        }
        throw new IllegalArgumentException("illegal locale keyword=value: " + str + "=" + str2);
    }

    private static final int getIntValue(String str, String str2, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (ASCII.equalIgnoreCase(str2, strArr[i])) {
                return i;
            }
        }
        throw new IllegalArgumentException("illegal locale keyword=value: " + str + "=" + str2);
    }

    private static final int getReorderCode(String str, String str2) {
        return getIntValue(str, str2, "space", "punct", "symbol", "currency", "digit") + 4096;
    }

    private static void setAttributesFromKeywords(ULocale uLocale, Collator collator, RuleBasedCollator ruleBasedCollator) {
        int i;
        if (uLocale.getKeywordValue("colHiraganaQuaternary") != null) {
            throw new UnsupportedOperationException("locale keyword kh/colHiraganaQuaternary");
        } else if (uLocale.getKeywordValue("variableTop") == null) {
            String keywordValue = uLocale.getKeywordValue("colStrength");
            if (keywordValue != null) {
                int intValue = getIntValue("colStrength", keywordValue, "primary", "secondary", "tertiary", "quaternary", "identical");
                if (intValue > 3) {
                    intValue = 15;
                }
                collator.setStrength(intValue);
            }
            String keywordValue2 = uLocale.getKeywordValue("colBackwards");
            if (keywordValue2 != null) {
                if (ruleBasedCollator != null) {
                    ruleBasedCollator.setFrenchCollation(getYesOrNo("colBackwards", keywordValue2));
                } else {
                    throw new UnsupportedOperationException("locale keyword kb/colBackwards only settable for RuleBasedCollator");
                }
            }
            String keywordValue3 = uLocale.getKeywordValue("colCaseLevel");
            if (keywordValue3 != null) {
                if (ruleBasedCollator != null) {
                    ruleBasedCollator.setCaseLevel(getYesOrNo("colCaseLevel", keywordValue3));
                } else {
                    throw new UnsupportedOperationException("locale keyword kb/colBackwards only settable for RuleBasedCollator");
                }
            }
            String keywordValue4 = uLocale.getKeywordValue("colCaseFirst");
            boolean z = true;
            if (keywordValue4 != null) {
                if (ruleBasedCollator != null) {
                    int intValue2 = getIntValue("colCaseFirst", keywordValue4, "no", "lower", "upper");
                    if (intValue2 == 0) {
                        ruleBasedCollator.setLowerCaseFirst(false);
                        ruleBasedCollator.setUpperCaseFirst(false);
                    } else if (intValue2 == 1) {
                        ruleBasedCollator.setLowerCaseFirst(true);
                    } else {
                        ruleBasedCollator.setUpperCaseFirst(true);
                    }
                } else {
                    throw new UnsupportedOperationException("locale keyword kf/colCaseFirst only settable for RuleBasedCollator");
                }
            }
            String keywordValue5 = uLocale.getKeywordValue("colAlternate");
            if (keywordValue5 != null) {
                if (ruleBasedCollator != null) {
                    if (getIntValue("colAlternate", keywordValue5, "non-ignorable", "shifted") == 0) {
                        z = false;
                    }
                    ruleBasedCollator.setAlternateHandlingShifted(z);
                } else {
                    throw new UnsupportedOperationException("locale keyword ka/colAlternate only settable for RuleBasedCollator");
                }
            }
            String keywordValue6 = uLocale.getKeywordValue("colNormalization");
            if (keywordValue6 != null) {
                collator.setDecomposition(getYesOrNo("colNormalization", keywordValue6) ? 17 : 16);
            }
            String keywordValue7 = uLocale.getKeywordValue("colNumeric");
            if (keywordValue7 != null) {
                if (ruleBasedCollator != null) {
                    ruleBasedCollator.setNumericCollation(getYesOrNo("colNumeric", keywordValue7));
                } else {
                    throw new UnsupportedOperationException("locale keyword kn/colNumeric only settable for RuleBasedCollator");
                }
            }
            String keywordValue8 = uLocale.getKeywordValue("colReorder");
            if (keywordValue8 != null) {
                int[] iArr = new int[190];
                int i2 = 0;
                int i3 = 0;
                while (i2 != iArr.length) {
                    int i4 = i3;
                    while (i4 < keywordValue8.length() && keywordValue8.charAt(i4) != '-') {
                        i4++;
                    }
                    String substring = keywordValue8.substring(i3, i4);
                    if (substring.length() == 4) {
                        i = UCharacter.getPropertyValueEnum(4106, substring);
                    } else {
                        i = getReorderCode("colReorder", substring);
                    }
                    int i5 = i2 + 1;
                    iArr[i2] = i;
                    if (i4 != keywordValue8.length()) {
                        i3 = i4 + 1;
                        i2 = i5;
                    } else if (i5 != 0) {
                        int[] iArr2 = new int[i5];
                        System.arraycopy(iArr, 0, iArr2, 0, i5);
                        collator.setReorderCodes(iArr2);
                    } else {
                        throw new IllegalArgumentException("no script codes for colReorder locale keyword");
                    }
                }
                throw new IllegalArgumentException("too many script codes for colReorder locale keyword: " + keywordValue8);
            }
            String keywordValue9 = uLocale.getKeywordValue("kv");
            if (keywordValue9 != null) {
                collator.setMaxVariable(getReorderCode("kv", keywordValue9));
            }
        } else {
            throw new UnsupportedOperationException("locale keyword vt/variableTop");
        }
    }

    public static final Collator getInstance(ULocale uLocale) {
        if (uLocale == null) {
            uLocale = ULocale.getDefault();
        }
        Collator instance = getShim().getInstance(uLocale);
        if (!uLocale.getName().equals(uLocale.getBaseName())) {
            setAttributesFromKeywords(uLocale, instance, instance instanceof RuleBasedCollator ? (RuleBasedCollator) instance : null);
        }
        return instance;
    }

    public static final Collator getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return doCompare((CharSequence) obj, (CharSequence) obj2);
    }

    /* access modifiers changed from: protected */
    public int doCompare(CharSequence charSequence, CharSequence charSequence2) {
        return compare(charSequence.toString(), charSequence2.toString());
    }

    public Collator setMaxVariable(int i) {
        throw new UnsupportedOperationException("Needs to be implemented by the subclass.");
    }

    protected Collator() {
    }
}
