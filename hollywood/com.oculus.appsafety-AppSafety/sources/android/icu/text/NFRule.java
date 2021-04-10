package android.icu.text;

import android.icu.impl.PatternProps;
import android.icu.impl.PatternTokenizer;
import android.icu.impl.number.Padder;
import android.icu.text.PluralRules;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Objects;

/* access modifiers changed from: package-private */
public final class NFRule {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int IMPROPER_FRACTION_RULE = -2;
    static final int INFINITY_RULE = -5;
    static final int MASTER_RULE = -4;
    static final int NAN_RULE = -6;
    static final int NEGATIVE_NUMBER_RULE = -1;
    static final int PROPER_FRACTION_RULE = -3;
    private static final String[] RULE_PREFIXES = {"<<", "<%", "<#", "<0", ">>", ">%", ">#", ">0", "=%", "=#", "=0"};
    static final Long ZERO = 0L;
    private long baseValue;
    private char decimalPoint = 0;
    private short exponent = 0;
    private final RuleBasedNumberFormat formatter;
    private int radix = 10;
    private PluralFormat rulePatternFormat;
    private String ruleText;
    private NFSubstitution sub1;
    private NFSubstitution sub2;

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        if (r8 != -4) goto L_0x00c7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void makeRules(java.lang.String r19, android.icu.text.NFRuleSet r20, android.icu.text.NFRule r21, android.icu.text.RuleBasedNumberFormat r22, java.util.List<android.icu.text.NFRule> r23) {
        /*
        // Method dump skipped, instructions count: 280
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.NFRule.makeRules(java.lang.String, android.icu.text.NFRuleSet, android.icu.text.NFRule, android.icu.text.RuleBasedNumberFormat, java.util.List):void");
    }

    public NFRule(RuleBasedNumberFormat formatter2, String ruleText2) {
        String str = null;
        this.ruleText = null;
        this.rulePatternFormat = null;
        this.sub1 = null;
        this.sub2 = null;
        this.formatter = formatter2;
        this.ruleText = ruleText2 != null ? parseRuleDescriptor(ruleText2) : str;
    }

    private String parseRuleDescriptor(String description) {
        short s;
        String description2 = description;
        int p = description2.indexOf(":");
        if (p != -1) {
            String descriptor = description2.substring(0, p);
            while (true) {
                p++;
                if (p >= description.length() || !PatternProps.isWhiteSpace(description2.charAt(p))) {
                    description2 = description2.substring(p);
                    int descriptorLength = descriptor.length();
                    char firstChar = descriptor.charAt(0);
                    char lastChar = descriptor.charAt(descriptorLength - 1);
                    char c = '0';
                }
            }
            description2 = description2.substring(p);
            int descriptorLength2 = descriptor.length();
            char firstChar2 = descriptor.charAt(0);
            char lastChar2 = descriptor.charAt(descriptorLength2 - 1);
            char c2 = '0';
            if (firstChar2 >= '0') {
                char c3 = '9';
                if (firstChar2 <= '9' && lastChar2 != 'x') {
                    long tempValue = 0;
                    char c4 = 0;
                    int p2 = 0;
                    while (p2 < descriptorLength2) {
                        c4 = descriptor.charAt(p2);
                        if (c4 < '0' || c4 > '9') {
                            if (c4 == '/' || c4 == '>') {
                                break;
                            } else if (!(PatternProps.isWhiteSpace(c4) || c4 == ',' || c4 == '.')) {
                                throw new IllegalArgumentException("Illegal character " + c4 + " in rule descriptor");
                            }
                        } else {
                            tempValue = (10 * tempValue) + ((long) (c4 - '0'));
                        }
                        p2++;
                    }
                    setBaseValue(tempValue);
                    if (c4 == '/') {
                        long tempValue2 = 0;
                        p2++;
                        while (p2 < descriptorLength2) {
                            c4 = descriptor.charAt(p2);
                            if (c4 >= c2 && c4 <= c3) {
                                tempValue2 = (tempValue2 * 10) + ((long) (c4 - '0'));
                            } else if (c4 == '>') {
                                break;
                            } else if (!PatternProps.isWhiteSpace(c4)) {
                                if (c4 != ',') {
                                    if (c4 != '.') {
                                        throw new IllegalArgumentException("Illegal character " + c4 + " in rule descriptor");
                                    }
                                }
                            }
                            p2++;
                            c2 = '0';
                            c3 = '9';
                        }
                        this.radix = (int) tempValue2;
                        if (this.radix != 0) {
                            this.exponent = expectedExponent();
                        } else {
                            throw new IllegalArgumentException("Rule can't have radix of 0");
                        }
                    }
                    if (c4 == '>') {
                        while (p2 < descriptorLength2) {
                            if (descriptor.charAt(p2) != '>' || (s = this.exponent) <= 0) {
                                throw new IllegalArgumentException("Illegal character in rule descriptor");
                            }
                            this.exponent = (short) (s - 1);
                            p2++;
                        }
                    }
                }
            }
            if (descriptor.equals("-x")) {
                setBaseValue(-1);
            } else if (descriptorLength2 == 3) {
                if (firstChar2 == '0' && lastChar2 == 'x') {
                    setBaseValue(-3);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (firstChar2 == 'x' && lastChar2 == 'x') {
                    setBaseValue(-2);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (firstChar2 == 'x' && lastChar2 == '0') {
                    setBaseValue(-4);
                    this.decimalPoint = descriptor.charAt(1);
                } else if (descriptor.equals("NaN")) {
                    setBaseValue(-6);
                } else if (descriptor.equals("Inf")) {
                    setBaseValue(-5);
                }
            }
        }
        if (description2.length() <= 0 || description2.charAt(0) != '\'') {
            return description2;
        }
        return description2.substring(1);
    }

    private void extractSubstitutions(NFRuleSet owner, String ruleText2, NFRule predecessor) {
        PluralRules.PluralType pluralType;
        this.ruleText = ruleText2;
        this.sub1 = extractSubstitution(owner, predecessor);
        if (this.sub1 == null) {
            this.sub2 = null;
        } else {
            this.sub2 = extractSubstitution(owner, predecessor);
        }
        String ruleText3 = this.ruleText;
        int pluralRuleStart = ruleText3.indexOf("$(");
        int pluralRuleEnd = pluralRuleStart >= 0 ? ruleText3.indexOf(")$", pluralRuleStart) : -1;
        if (pluralRuleEnd >= 0) {
            int endType = ruleText3.indexOf(44, pluralRuleStart);
            if (endType >= 0) {
                String type = this.ruleText.substring(pluralRuleStart + 2, endType);
                if ("cardinal".equals(type)) {
                    pluralType = PluralRules.PluralType.CARDINAL;
                } else if ("ordinal".equals(type)) {
                    pluralType = PluralRules.PluralType.ORDINAL;
                } else {
                    throw new IllegalArgumentException(type + " is an unknown type");
                }
                this.rulePatternFormat = this.formatter.createPluralFormat(pluralType, ruleText3.substring(endType + 1, pluralRuleEnd));
                return;
            }
            throw new IllegalArgumentException("Rule \"" + ruleText3 + "\" does not have a defined type");
        }
    }

    private NFSubstitution extractSubstitution(NFRuleSet owner, NFRule predecessor) {
        int subEnd;
        int subStart = indexOfAnyRulePrefix(this.ruleText);
        if (subStart == -1) {
            return null;
        }
        if (this.ruleText.startsWith(">>>", subStart)) {
            subEnd = subStart + 2;
        } else {
            char c = this.ruleText.charAt(subStart);
            int subEnd2 = this.ruleText.indexOf(c, subStart + 1);
            if (c != '<' || subEnd2 == -1 || subEnd2 >= this.ruleText.length() - 1 || this.ruleText.charAt(subEnd2 + 1) != c) {
                subEnd = subEnd2;
            } else {
                subEnd = subEnd2 + 1;
            }
        }
        if (subEnd == -1) {
            return null;
        }
        NFSubstitution result = NFSubstitution.makeSubstitution(subStart, this, predecessor, owner, this.formatter, this.ruleText.substring(subStart, subEnd + 1));
        this.ruleText = this.ruleText.substring(0, subStart) + this.ruleText.substring(subEnd + 1);
        return result;
    }

    /* access modifiers changed from: package-private */
    public final void setBaseValue(long newBaseValue) {
        this.baseValue = newBaseValue;
        this.radix = 10;
        if (this.baseValue >= 1) {
            this.exponent = expectedExponent();
            NFSubstitution nFSubstitution = this.sub1;
            if (nFSubstitution != null) {
                nFSubstitution.setDivisor(this.radix, this.exponent);
            }
            NFSubstitution nFSubstitution2 = this.sub2;
            if (nFSubstitution2 != null) {
                nFSubstitution2.setDivisor(this.radix, this.exponent);
                return;
            }
            return;
        }
        this.exponent = 0;
    }

    private short expectedExponent() {
        if (this.radix == 0) {
            return 0;
        }
        long j = this.baseValue;
        if (j < 1) {
            return 0;
        }
        short tempResult = (short) ((int) (Math.log((double) j) / Math.log((double) this.radix)));
        if (power((long) this.radix, (short) (tempResult + 1)) <= this.baseValue) {
            return (short) (tempResult + 1);
        }
        return tempResult;
    }

    private static int indexOfAnyRulePrefix(String ruleText2) {
        int result = -1;
        if (ruleText2.length() > 0) {
            for (String string : RULE_PREFIXES) {
                int pos = ruleText2.indexOf(string);
                if (pos != -1 && (result == -1 || pos < result)) {
                    result = pos;
                }
            }
        }
        return result;
    }

    public boolean equals(Object that) {
        if (!(that instanceof NFRule)) {
            return false;
        }
        NFRule that2 = (NFRule) that;
        if (this.baseValue != that2.baseValue || this.radix != that2.radix || this.exponent != that2.exponent || !this.ruleText.equals(that2.ruleText) || !Objects.equals(this.sub1, that2.sub1) || !Objects.equals(this.sub2, that2.sub2)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 42;
    }

    public String toString() {
        NFSubstitution nFSubstitution;
        StringBuilder result = new StringBuilder();
        long j = this.baseValue;
        if (j == -1) {
            result.append("-x: ");
        } else if (j == -2) {
            result.append('x');
            char c = this.decimalPoint;
            if (c == 0) {
                c = '.';
            }
            result.append(c);
            result.append("x: ");
        } else if (j == -3) {
            result.append('0');
            char c2 = this.decimalPoint;
            if (c2 == 0) {
                c2 = '.';
            }
            result.append(c2);
            result.append("x: ");
        } else if (j == -4) {
            result.append('x');
            char c3 = this.decimalPoint;
            if (c3 == 0) {
                c3 = '.';
            }
            result.append(c3);
            result.append("0: ");
        } else if (j == -5) {
            result.append("Inf: ");
        } else if (j == -6) {
            result.append("NaN: ");
        } else {
            result.append(String.valueOf(j));
            if (this.radix != 10) {
                result.append('/');
                result.append(this.radix);
            }
            int numCarets = expectedExponent() - this.exponent;
            for (int i = 0; i < numCarets; i++) {
                result.append('>');
            }
            result.append(PluralRules.KEYWORD_RULE_SEPARATOR);
        }
        if (this.ruleText.startsWith(Padder.FALLBACK_PADDING_STRING) && ((nFSubstitution = this.sub1) == null || nFSubstitution.getPos() != 0)) {
            result.append(PatternTokenizer.SINGLE_QUOTE);
        }
        StringBuilder ruleTextCopy = new StringBuilder(this.ruleText);
        NFSubstitution nFSubstitution2 = this.sub2;
        if (nFSubstitution2 != null) {
            ruleTextCopy.insert(nFSubstitution2.getPos(), this.sub2.toString());
        }
        NFSubstitution nFSubstitution3 = this.sub1;
        if (nFSubstitution3 != null) {
            ruleTextCopy.insert(nFSubstitution3.getPos(), this.sub1.toString());
        }
        result.append(ruleTextCopy.toString());
        result.append(';');
        return result.toString();
    }

    public final char getDecimalPoint() {
        return this.decimalPoint;
    }

    public final long getBaseValue() {
        return this.baseValue;
    }

    public long getDivisor() {
        return power((long) this.radix, this.exponent);
    }

    public void doFormat(long number, StringBuilder toInsertInto, int pos, int recursionCount) {
        int lengthOffset;
        int pluralRuleStart;
        int pluralRuleStart2 = this.ruleText.length();
        int i = 0;
        if (this.rulePatternFormat == null) {
            toInsertInto.insert(pos, this.ruleText);
            pluralRuleStart = pluralRuleStart2;
            lengthOffset = 0;
        } else {
            int pluralRuleStart3 = this.ruleText.indexOf("$(");
            int pluralRuleEnd = this.ruleText.indexOf(")$", pluralRuleStart3);
            int initialLength = toInsertInto.length();
            if (pluralRuleEnd < this.ruleText.length() - 1) {
                toInsertInto.insert(pos, this.ruleText.substring(pluralRuleEnd + 2));
            }
            toInsertInto.insert(pos, this.rulePatternFormat.format((double) (number / power((long) this.radix, this.exponent))));
            if (pluralRuleStart3 > 0) {
                toInsertInto.insert(pos, this.ruleText.substring(0, pluralRuleStart3));
            }
            pluralRuleStart = pluralRuleStart3;
            lengthOffset = this.ruleText.length() - (toInsertInto.length() - initialLength);
        }
        NFSubstitution nFSubstitution = this.sub2;
        if (nFSubstitution != null) {
            nFSubstitution.doSubstitution(number, toInsertInto, pos - (nFSubstitution.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
        NFSubstitution nFSubstitution2 = this.sub1;
        if (nFSubstitution2 != null) {
            if (nFSubstitution2.getPos() > pluralRuleStart) {
                i = lengthOffset;
            }
            nFSubstitution2.doSubstitution(number, toInsertInto, pos - i, recursionCount);
        }
    }

    public void doFormat(double number, StringBuilder toInsertInto, int pos, int recursionCount) {
        int lengthOffset;
        int pluralRuleStart;
        double pluralVal;
        int pluralRuleStart2 = this.ruleText.length();
        int i = 0;
        if (this.rulePatternFormat == null) {
            toInsertInto.insert(pos, this.ruleText);
            pluralRuleStart = pluralRuleStart2;
            lengthOffset = 0;
        } else {
            int pluralRuleStart3 = this.ruleText.indexOf("$(");
            int pluralRuleEnd = this.ruleText.indexOf(")$", pluralRuleStart3);
            int initialLength = toInsertInto.length();
            if (pluralRuleEnd < this.ruleText.length() - 1) {
                toInsertInto.insert(pos, this.ruleText.substring(pluralRuleEnd + 2));
            }
            if (0.0d > number || number >= 1.0d) {
                pluralVal = number / ((double) power((long) this.radix, this.exponent));
            } else {
                pluralVal = (double) Math.round(((double) power((long) this.radix, this.exponent)) * number);
            }
            toInsertInto.insert(pos, this.rulePatternFormat.format((double) ((long) pluralVal)));
            if (pluralRuleStart3 > 0) {
                toInsertInto.insert(pos, this.ruleText.substring(0, pluralRuleStart3));
            }
            pluralRuleStart = pluralRuleStart3;
            lengthOffset = this.ruleText.length() - (toInsertInto.length() - initialLength);
        }
        NFSubstitution nFSubstitution = this.sub2;
        if (nFSubstitution != null) {
            nFSubstitution.doSubstitution(number, toInsertInto, pos - (nFSubstitution.getPos() > pluralRuleStart ? lengthOffset : 0), recursionCount);
        }
        NFSubstitution nFSubstitution2 = this.sub1;
        if (nFSubstitution2 != null) {
            if (nFSubstitution2.getPos() > pluralRuleStart) {
                i = lengthOffset;
            }
            nFSubstitution2.doSubstitution(number, toInsertInto, pos - i, recursionCount);
        }
    }

    static long power(long base, short exponent2) {
        if (exponent2 < 0) {
            throw new IllegalArgumentException("Exponent can not be negative");
        } else if (base >= 0) {
            long result = 1;
            while (exponent2 > 0) {
                if ((exponent2 & 1) == 1) {
                    result *= base;
                }
                base *= base;
                exponent2 = (short) (exponent2 >> 1);
            }
            return result;
        } else {
            throw new IllegalArgumentException("Base can not be negative");
        }
    }

    public boolean shouldRollBack(long number) {
        NFSubstitution nFSubstitution;
        NFSubstitution nFSubstitution2 = this.sub1;
        if ((nFSubstitution2 == null || !nFSubstitution2.isModulusSubstitution()) && ((nFSubstitution = this.sub2) == null || !nFSubstitution.isModulusSubstitution())) {
            return false;
        }
        long divisor = power((long) this.radix, this.exponent);
        if (number % divisor != 0 || this.baseValue % divisor == 0) {
            return false;
        }
        return true;
    }

    public Number doParse(String text, ParsePosition parsePosition, boolean isFractionRule, double upperBound, int nonNumericalExecutedRuleMask) {
        int i;
        int start;
        double result;
        int highWaterMark;
        int i2 = 0;
        ParsePosition pp = new ParsePosition(0);
        NFSubstitution nFSubstitution = this.sub1;
        int sub1Pos = nFSubstitution != null ? nFSubstitution.getPos() : this.ruleText.length();
        NFSubstitution nFSubstitution2 = this.sub2;
        int sub2Pos = nFSubstitution2 != null ? nFSubstitution2.getPos() : this.ruleText.length();
        String workText = stripPrefix(text, this.ruleText.substring(0, sub1Pos), pp);
        int prefixLength = text.length() - workText.length();
        if (pp.getIndex() == 0 && sub1Pos != 0) {
            return ZERO;
        }
        long j = this.baseValue;
        if (j == -5) {
            parsePosition.setIndex(pp.getIndex());
            return Double.valueOf(Double.POSITIVE_INFINITY);
        } else if (j == -6) {
            parsePosition.setIndex(pp.getIndex());
            return Double.valueOf(Double.NaN);
        } else {
            double tempBaseValue = (double) Math.max(0L, j);
            double result2 = 0.0d;
            int start2 = 0;
            int highWaterMark2 = 0;
            while (true) {
                pp.setIndex(i2);
                double partialResult = matchToDelimiter(workText, start2, tempBaseValue, this.ruleText.substring(sub1Pos, sub2Pos), this.rulePatternFormat, pp, this.sub1, upperBound, nonNumericalExecutedRuleMask).doubleValue();
                if (pp.getIndex() != 0 || this.sub1 == null) {
                    int start3 = pp.getIndex();
                    String workText2 = workText.substring(pp.getIndex());
                    ParsePosition pp2 = new ParsePosition(0);
                    i = 0;
                    double partialResult2 = matchToDelimiter(workText2, 0, partialResult, this.ruleText.substring(sub2Pos), this.rulePatternFormat, pp2, this.sub2, upperBound, nonNumericalExecutedRuleMask).doubleValue();
                    if (pp2.getIndex() != 0 || this.sub2 == null) {
                        highWaterMark = highWaterMark2;
                        if (prefixLength + pp.getIndex() + pp2.getIndex() > highWaterMark) {
                            highWaterMark2 = prefixLength + pp.getIndex() + pp2.getIndex();
                            result2 = partialResult2;
                            start = start3;
                        }
                    } else {
                        highWaterMark = highWaterMark2;
                    }
                    highWaterMark2 = highWaterMark;
                    start = start3;
                } else {
                    start = start2;
                    highWaterMark2 = highWaterMark2;
                    i = 0;
                }
                if (sub1Pos == sub2Pos || pp.getIndex() <= 0 || pp.getIndex() >= workText.length() || pp.getIndex() == start) {
                    parsePosition.setIndex(highWaterMark2);
                } else {
                    start2 = start;
                    sub1Pos = sub1Pos;
                    sub2Pos = sub2Pos;
                    workText = workText;
                    i2 = i;
                    tempBaseValue = tempBaseValue;
                }
            }
            parsePosition.setIndex(highWaterMark2);
            if (!isFractionRule || highWaterMark2 <= 0 || this.sub1 != null) {
                result = result2;
            } else {
                result = 1.0d / result2;
            }
            if (result == ((double) ((long) result))) {
                return Long.valueOf((long) result);
            }
            return new Double(result);
        }
    }

    private String stripPrefix(String text, String prefix, ParsePosition pp) {
        int pfl;
        if (prefix.length() == 0 || (pfl = prefixLength(text, prefix)) == 0) {
            return text;
        }
        pp.setIndex(pp.getIndex() + pfl);
        return text.substring(pfl);
    }

    private Number matchToDelimiter(String text, int startPos, double baseVal, String delimiter, PluralFormat pluralFormatDelimiter, ParsePosition pp, NFSubstitution sub, double upperBound, int nonNumericalExecutedRuleMask) {
        if (!allIgnorable(delimiter)) {
            ParsePosition tempPP = new ParsePosition(0);
            int[] temp = findText(text, delimiter, pluralFormatDelimiter, startPos);
            int dPos = temp[0];
            int dLen = temp[1];
            while (dPos >= 0) {
                String subText = text.substring(0, dPos);
                if (subText.length() > 0) {
                    Number tempResult = sub.doParse(subText, tempPP, baseVal, upperBound, this.formatter.lenientParseEnabled(), nonNumericalExecutedRuleMask);
                    if (tempPP.getIndex() == dPos) {
                        pp.setIndex(dPos + dLen);
                        return tempResult;
                    }
                }
                tempPP.setIndex(0);
                int[] temp2 = findText(text, delimiter, pluralFormatDelimiter, dPos + dLen);
                dPos = temp2[0];
                dLen = temp2[1];
            }
            pp.setIndex(0);
            return ZERO;
        } else if (sub == null) {
            return Double.valueOf(baseVal);
        } else {
            ParsePosition tempPP2 = new ParsePosition(0);
            Number result = ZERO;
            Number tempResult2 = sub.doParse(text, tempPP2, baseVal, upperBound, this.formatter.lenientParseEnabled(), nonNumericalExecutedRuleMask);
            if (tempPP2.getIndex() == 0) {
                return result;
            }
            pp.setIndex(tempPP2.getIndex());
            return tempResult2 != null ? tempResult2 : result;
        }
    }

    private int prefixLength(String str, String prefix) {
        if (prefix.length() == 0) {
            return 0;
        }
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (scanner != null) {
            return scanner.prefixLength(str, prefix);
        }
        if (str.startsWith(prefix)) {
            return prefix.length();
        }
        return 0;
    }

    private int[] findText(String str, String key, PluralFormat pluralFormatKey, int startingAt) {
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (pluralFormatKey != null) {
            FieldPosition position = new FieldPosition(0);
            position.setBeginIndex(startingAt);
            pluralFormatKey.parseType(str, scanner, position);
            int start = position.getBeginIndex();
            if (start >= 0) {
                int pluralRuleStart = this.ruleText.indexOf("$(");
                int matchLen = position.getEndIndex() - start;
                String prefix = this.ruleText.substring(0, pluralRuleStart);
                String suffix = this.ruleText.substring(this.ruleText.indexOf(")$", pluralRuleStart) + 2);
                if (str.regionMatches(start - prefix.length(), prefix, 0, prefix.length()) && str.regionMatches(start + matchLen, suffix, 0, suffix.length())) {
                    return new int[]{start - prefix.length(), prefix.length() + matchLen + suffix.length()};
                }
            }
            return new int[]{-1, 0};
        } else if (scanner != null) {
            return scanner.findText(str, key, startingAt);
        } else {
            return new int[]{str.indexOf(key, startingAt), key.length()};
        }
    }

    private boolean allIgnorable(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        RbnfLenientScanner scanner = this.formatter.getLenientScanner();
        if (scanner == null || !scanner.allIgnorable(str)) {
            return false;
        }
        return true;
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        NFSubstitution nFSubstitution = this.sub1;
        if (nFSubstitution != null) {
            nFSubstitution.setDecimalFormatSymbols(newSymbols);
        }
        NFSubstitution nFSubstitution2 = this.sub2;
        if (nFSubstitution2 != null) {
            nFSubstitution2.setDecimalFormatSymbols(newSymbols);
        }
    }
}
