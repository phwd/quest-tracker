package android.icu.text;

import android.icu.impl.ICUDebug;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.PatternProps;
import android.icu.lang.UCharacter;
import android.icu.math.BigDecimal;
import android.icu.text.DisplayContext;
import android.icu.text.PluralRules;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceBundleIterator;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class RuleBasedNumberFormat extends NumberFormat {
    private static final boolean DEBUG = ICUDebug.enabled("rbnf");
    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(Long.MAX_VALUE);
    private static final BigDecimal MIN_VALUE = BigDecimal.valueOf(Long.MIN_VALUE);
    private static final String[] locnames = {"SpelloutLocalizations", "OrdinalLocalizations", "DurationLocalizations", "NumberingSystemLocalizations"};
    private static final String[] rulenames = {"SpelloutRules", "OrdinalRules", "DurationRules", "NumberingSystemRules"};
    static final long serialVersionUID = -7664252765575395068L;
    private transient BreakIterator capitalizationBrkIter;
    private boolean capitalizationForListOrMenu;
    private boolean capitalizationForStandAlone;
    private boolean capitalizationInfoIsSet;
    private transient DecimalFormat decimalFormat = null;
    private transient DecimalFormatSymbols decimalFormatSymbols = null;
    private transient NFRule defaultInfinityRule = null;
    private transient NFRule defaultNaNRule = null;
    private transient NFRuleSet defaultRuleSet = null;
    private boolean lenientParse;
    private transient String lenientParseRules;
    private ULocale locale = null;
    private transient boolean lookedForScanner;
    private transient String postProcessRules;
    private transient RBNFPostProcessor postProcessor;
    private String[] publicRuleSetNames;
    private int roundingMode = 7;
    private Map ruleSetDisplayNames;
    private transient NFRuleSet[] ruleSets = null;
    private transient Map ruleSetsMap = null;
    private transient RbnfLenientScannerProvider scannerProvider = null;

    public RuleBasedNumberFormat(ULocale uLocale, int i) {
        String[][] strArr = null;
        this.lenientParse = DEBUG;
        this.capitalizationInfoIsSet = DEBUG;
        this.capitalizationForListOrMenu = DEBUG;
        this.capitalizationForStandAlone = DEBUG;
        this.capitalizationBrkIter = null;
        this.locale = uLocale;
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/rbnf", uLocale);
        ULocale uLocale2 = iCUResourceBundle.getULocale();
        setLocale(uLocale2, uLocale2);
        StringBuilder sb = new StringBuilder();
        try {
            UResourceBundleIterator iterator = iCUResourceBundle.getWithFallback("RBNFRules/" + rulenames[i - 1]).getIterator();
            while (iterator.hasNext()) {
                sb.append(iterator.nextString());
            }
        } catch (MissingResourceException unused) {
        }
        ICUResourceBundle findTopLevel = iCUResourceBundle.findTopLevel(locnames[i - 1]);
        if (findTopLevel != null) {
            strArr = new String[findTopLevel.getSize()][];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = findTopLevel.get(i2).getStringArray();
            }
        }
        init(sb.toString(), strArr);
    }

    @Override // java.text.Format, android.icu.text.NumberFormat
    public Object clone() {
        return super.clone();
    }

    @Override // android.icu.text.NumberFormat
    public boolean equals(Object obj) {
        if (!(obj instanceof RuleBasedNumberFormat)) {
            return DEBUG;
        }
        RuleBasedNumberFormat ruleBasedNumberFormat = (RuleBasedNumberFormat) obj;
        if (!this.locale.equals(ruleBasedNumberFormat.locale) || this.lenientParse != ruleBasedNumberFormat.lenientParse || this.ruleSets.length != ruleBasedNumberFormat.ruleSets.length) {
            return DEBUG;
        }
        int i = 0;
        while (true) {
            NFRuleSet[] nFRuleSetArr = this.ruleSets;
            if (i >= nFRuleSetArr.length) {
                return true;
            }
            if (!nFRuleSetArr[i].equals(ruleBasedNumberFormat.ruleSets[i])) {
                return DEBUG;
            }
            i++;
        }
    }

    @Override // android.icu.text.NumberFormat
    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (NFRuleSet nFRuleSet : this.ruleSets) {
            sb.append(nFRuleSet.toString());
        }
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeUTF(toString());
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readUTF();
        throw null;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (stringBuffer.length() == 0) {
            stringBuffer.append(adjustForContext(format(d, this.defaultRuleSet)));
        } else {
            stringBuffer.append(format(d, this.defaultRuleSet));
        }
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (stringBuffer.length() == 0) {
            stringBuffer.append(adjustForContext(format(j, this.defaultRuleSet)));
        } else {
            stringBuffer.append(format(j, this.defaultRuleSet));
        }
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(new BigDecimal(bigInteger), stringBuffer, fieldPosition);
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(java.math.BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(new BigDecimal(bigDecimal), stringBuffer, fieldPosition);
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (MIN_VALUE.compareTo(bigDecimal) > 0 || MAX_VALUE.compareTo(bigDecimal) < 0) {
            return getDecimalFormat().format(bigDecimal, stringBuffer, fieldPosition);
        }
        if (bigDecimal.scale() == 0) {
            return format(bigDecimal.longValue(), stringBuffer, fieldPosition);
        }
        return format(bigDecimal.doubleValue(), stringBuffer, fieldPosition);
    }

    @Override // android.icu.text.NumberFormat
    public Number parse(String str, ParsePosition parsePosition) {
        String substring = str.substring(parsePosition.getIndex());
        ParsePosition parsePosition2 = new ParsePosition(0);
        Long l = NFRule.ZERO;
        ParsePosition parsePosition3 = new ParsePosition(parsePosition2.getIndex());
        Long l2 = l;
        for (int length = this.ruleSets.length - 1; length >= 0; length--) {
            if (this.ruleSets[length].isPublic() && this.ruleSets[length].isParseable()) {
                Number parse = this.ruleSets[length].parse(substring, parsePosition2, Double.MAX_VALUE, 0);
                if (parsePosition2.getIndex() > parsePosition3.getIndex()) {
                    parsePosition3.setIndex(parsePosition2.getIndex());
                    l2 = parse;
                }
                if (parsePosition3.getIndex() == substring.length()) {
                    break;
                }
                parsePosition2.setIndex(0);
            }
        }
        parsePosition.setIndex(parsePosition.getIndex() + parsePosition3.getIndex());
        return l2;
    }

    public boolean lenientParseEnabled() {
        return this.lenientParse;
    }

    public void setLenientScannerProvider(RbnfLenientScannerProvider rbnfLenientScannerProvider) {
        this.scannerProvider = rbnfLenientScannerProvider;
    }

    public RbnfLenientScannerProvider getLenientScannerProvider() {
        if (this.scannerProvider == null && this.lenientParse && !this.lookedForScanner) {
            try {
                this.lookedForScanner = true;
                setLenientScannerProvider((RbnfLenientScannerProvider) Class.forName("android.icu.impl.text.RbnfScannerProviderImpl").newInstance());
            } catch (Exception unused) {
            }
        }
        return this.scannerProvider;
    }

    public void setDefaultRuleSet(String str) {
        String name;
        if (str == null) {
            String[] strArr = this.publicRuleSetNames;
            if (strArr.length > 0) {
                this.defaultRuleSet = findRuleSet(strArr[0]);
                return;
            }
            this.defaultRuleSet = null;
            int length = this.ruleSets.length;
            do {
                length--;
                if (length >= 0) {
                    name = this.ruleSets[length].getName();
                    if (name.equals("%spellout-numbering") || name.equals("%digits-ordinal")) {
                        this.defaultRuleSet = this.ruleSets[length];
                    }
                } else {
                    int length2 = this.ruleSets.length;
                    do {
                        length2--;
                        if (length2 < 0) {
                            return;
                        }
                    } while (!this.ruleSets[length2].isPublic());
                    this.defaultRuleSet = this.ruleSets[length2];
                    return;
                }
            } while (!name.equals("%duration"));
            this.defaultRuleSet = this.ruleSets[length];
        } else if (!str.startsWith("%%")) {
            this.defaultRuleSet = findRuleSet(str);
        } else {
            throw new IllegalArgumentException("cannot use private rule set: " + str);
        }
    }

    public int getRoundingMode() {
        return this.roundingMode;
    }

    /* access modifiers changed from: package-private */
    public NFRuleSet getDefaultRuleSet() {
        return this.defaultRuleSet;
    }

    /* access modifiers changed from: package-private */
    public RbnfLenientScanner getLenientScanner() {
        RbnfLenientScannerProvider lenientScannerProvider;
        if (!this.lenientParse || (lenientScannerProvider = getLenientScannerProvider()) == null) {
            return null;
        }
        return lenientScannerProvider.get(this.locale, this.lenientParseRules);
    }

    /* access modifiers changed from: package-private */
    public DecimalFormatSymbols getDecimalFormatSymbols() {
        if (this.decimalFormatSymbols == null) {
            this.decimalFormatSymbols = new DecimalFormatSymbols(this.locale);
        }
        return this.decimalFormatSymbols;
    }

    /* access modifiers changed from: package-private */
    public DecimalFormat getDecimalFormat() {
        if (this.decimalFormat == null) {
            this.decimalFormat = new DecimalFormat(NumberFormat.getPattern(this.locale, 0), getDecimalFormatSymbols());
        }
        return this.decimalFormat;
    }

    /* access modifiers changed from: package-private */
    public PluralFormat createPluralFormat(PluralRules.PluralType pluralType, String str) {
        return new PluralFormat(this.locale, pluralType, str, getDecimalFormat());
    }

    /* access modifiers changed from: package-private */
    public NFRule getDefaultInfinityRule() {
        if (this.defaultInfinityRule == null) {
            this.defaultInfinityRule = new NFRule(this, "Inf: " + getDecimalFormatSymbols().getInfinity());
        }
        return this.defaultInfinityRule;
    }

    /* access modifiers changed from: package-private */
    public NFRule getDefaultNaNRule() {
        if (this.defaultNaNRule == null) {
            this.defaultNaNRule = new NFRule(this, "NaN: " + getDecimalFormatSymbols().getNaN());
        }
        return this.defaultNaNRule;
    }

    private String extractSpecial(StringBuilder sb, String str) {
        int indexOf = sb.indexOf(str);
        if (indexOf == -1 || (indexOf != 0 && sb.charAt(indexOf - 1) != ';')) {
            return null;
        }
        int indexOf2 = sb.indexOf(";%", indexOf);
        if (indexOf2 == -1) {
            indexOf2 = sb.length() - 1;
        }
        int length = str.length() + indexOf;
        while (length < indexOf2 && PatternProps.isWhiteSpace(sb.charAt(length))) {
            length++;
        }
        String substring = sb.substring(length, indexOf2);
        sb.delete(indexOf, indexOf2 + 1);
        return substring;
    }

    private void init(String str, String[][] strArr) {
        NFRuleSet[] nFRuleSetArr;
        NFRuleSet[] nFRuleSetArr2;
        initLocalizations(strArr);
        StringBuilder stripWhitespace = stripWhitespace(str);
        this.lenientParseRules = extractSpecial(stripWhitespace, "%%lenient-parse:");
        this.postProcessRules = extractSpecial(stripWhitespace, "%%post-process:");
        int i = 0;
        int i2 = 1;
        while (true) {
            int indexOf = stripWhitespace.indexOf(";%", i);
            if (indexOf == -1) {
                break;
            }
            i2++;
            i = indexOf + 2;
        }
        this.ruleSets = new NFRuleSet[i2];
        this.ruleSetsMap = new HashMap((i2 * 2) + 1);
        this.defaultRuleSet = null;
        String[] strArr2 = new String[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            nFRuleSetArr = this.ruleSets;
            if (i3 >= nFRuleSetArr.length) {
                break;
            }
            int indexOf2 = stripWhitespace.indexOf(";%", i4);
            if (indexOf2 < 0) {
                indexOf2 = stripWhitespace.length() - 1;
            }
            int i6 = indexOf2 + 1;
            strArr2[i3] = stripWhitespace.substring(i4, i6);
            NFRuleSet nFRuleSet = new NFRuleSet(this, strArr2, i3);
            this.ruleSets[i3] = nFRuleSet;
            String name = nFRuleSet.getName();
            this.ruleSetsMap.put(name, nFRuleSet);
            if (!name.startsWith("%%")) {
                i5++;
                if ((this.defaultRuleSet == null && name.equals("%spellout-numbering")) || name.equals("%digits-ordinal") || name.equals("%duration")) {
                    this.defaultRuleSet = nFRuleSet;
                }
            }
            i3++;
            i4 = i6;
        }
        if (this.defaultRuleSet == null) {
            int length = nFRuleSetArr.length - 1;
            while (true) {
                if (length < 0) {
                    break;
                } else if (!this.ruleSets[length].getName().startsWith("%%")) {
                    this.defaultRuleSet = this.ruleSets[length];
                    break;
                } else {
                    length--;
                }
            }
        }
        if (this.defaultRuleSet == null) {
            NFRuleSet[] nFRuleSetArr3 = this.ruleSets;
            this.defaultRuleSet = nFRuleSetArr3[nFRuleSetArr3.length - 1];
        }
        int i7 = 0;
        while (true) {
            nFRuleSetArr2 = this.ruleSets;
            if (i7 >= nFRuleSetArr2.length) {
                break;
            }
            nFRuleSetArr2[i7].parseRules(strArr2[i7]);
            i7++;
        }
        String[] strArr3 = new String[i5];
        int i8 = 0;
        for (int length2 = nFRuleSetArr2.length - 1; length2 >= 0; length2--) {
            if (!this.ruleSets[length2].getName().startsWith("%%")) {
                strArr3[i8] = this.ruleSets[length2].getName();
                i8++;
            }
        }
        if (this.publicRuleSetNames != null) {
            int i9 = 0;
            while (true) {
                String[] strArr4 = this.publicRuleSetNames;
                if (i9 < strArr4.length) {
                    String str2 = strArr4[i9];
                    for (String str3 : strArr3) {
                        if (str2.equals(str3)) {
                            i9++;
                        }
                    }
                    throw new IllegalArgumentException("did not find public rule set: " + str2);
                }
                this.defaultRuleSet = findRuleSet(strArr4[0]);
                return;
            }
        }
        this.publicRuleSetNames = strArr3;
    }

    private void initLocalizations(String[][] strArr) {
        if (strArr != null) {
            this.publicRuleSetNames = (String[]) strArr[0].clone();
            HashMap hashMap = new HashMap();
            for (int i = 1; i < strArr.length; i++) {
                String[] strArr2 = strArr[i];
                String str = strArr2[0];
                String[] strArr3 = new String[(strArr2.length - 1)];
                if (strArr3.length == this.publicRuleSetNames.length) {
                    System.arraycopy(strArr2, 1, strArr3, 0, strArr3.length);
                    hashMap.put(str, strArr3);
                } else {
                    throw new IllegalArgumentException("public name length: " + this.publicRuleSetNames.length + " != localized names[" + i + "] length: " + strArr3.length);
                }
            }
            if (!hashMap.isEmpty()) {
                this.ruleSetDisplayNames = hashMap;
            }
        }
    }

    private StringBuilder stripWhitespace(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            while (i < length && PatternProps.isWhiteSpace(str.charAt(i))) {
                i++;
            }
            if (i >= length || str.charAt(i) != ';') {
                int indexOf = str.indexOf(59, i);
                if (indexOf != -1) {
                    if (indexOf >= length) {
                        break;
                    }
                    int i2 = indexOf + 1;
                    sb.append(str.substring(i, i2));
                    i = i2;
                } else {
                    sb.append(str.substring(i));
                    break;
                }
            } else {
                i++;
            }
        }
        return sb;
    }

    private String format(double d, NFRuleSet nFRuleSet) {
        StringBuilder sb = new StringBuilder();
        if (getRoundingMode() != 7 && !Double.isNaN(d) && !Double.isInfinite(d)) {
            d = new BigDecimal(Double.toString(d)).setScale(getMaximumFractionDigits(), this.roundingMode).doubleValue();
        }
        nFRuleSet.format(d, sb, 0, 0);
        postProcess(sb, nFRuleSet);
        return sb.toString();
    }

    private String format(long j, NFRuleSet nFRuleSet) {
        StringBuilder sb = new StringBuilder();
        if (j == Long.MIN_VALUE) {
            sb.append(getDecimalFormat().format(Long.MIN_VALUE));
        } else {
            nFRuleSet.format(j, sb, 0, 0);
        }
        postProcess(sb, nFRuleSet);
        return sb.toString();
    }

    private void postProcess(StringBuilder sb, NFRuleSet nFRuleSet) {
        String str = this.postProcessRules;
        if (str != null) {
            if (this.postProcessor == null) {
                int indexOf = str.indexOf(";");
                if (indexOf == -1) {
                    indexOf = this.postProcessRules.length();
                }
                String trim = this.postProcessRules.substring(0, indexOf).trim();
                try {
                    this.postProcessor = (RBNFPostProcessor) Class.forName(trim).newInstance();
                    this.postProcessor.init(this, this.postProcessRules);
                } catch (Exception e) {
                    if (DEBUG) {
                        PrintStream printStream = System.out;
                        printStream.println("could not locate " + trim + ", error " + e.getClass().getName() + ", " + e.getMessage());
                    }
                    this.postProcessor = null;
                    this.postProcessRules = null;
                    return;
                }
            }
            this.postProcessor.process(sb, nFRuleSet);
        }
    }

    private String adjustForContext(String str) {
        DisplayContext context = getContext(DisplayContext.Type.CAPITALIZATION);
        if (context == DisplayContext.CAPITALIZATION_NONE || str == null || str.length() <= 0 || !UCharacter.isLowerCase(str.codePointAt(0)) || (context != DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE && ((context != DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU || !this.capitalizationForListOrMenu) && (context != DisplayContext.CAPITALIZATION_FOR_STANDALONE || !this.capitalizationForStandAlone)))) {
            return str;
        }
        if (this.capitalizationBrkIter == null) {
            this.capitalizationBrkIter = BreakIterator.getSentenceInstance(this.locale);
        }
        return UCharacter.toTitleCase(this.locale, str, this.capitalizationBrkIter, 768);
    }

    /* access modifiers changed from: package-private */
    public NFRuleSet findRuleSet(String str) {
        NFRuleSet nFRuleSet = (NFRuleSet) this.ruleSetsMap.get(str);
        if (nFRuleSet != null) {
            return nFRuleSet;
        }
        throw new IllegalArgumentException("No rule set named " + str);
    }
}
