package android.icu.text;

import android.icu.impl.PluralRulesLoader;
import android.icu.util.ULocale;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public class PluralRules implements Serializable {
    static final UnicodeSet ALLOWED_ID;
    static final Pattern AND_SEPARATED = Pattern.compile("\\s*and\\s*");
    static final Pattern AT_SEPARATED = Pattern.compile("\\s*\\Q\\E@\\s*");
    static final Pattern COMMA_SEPARATED = Pattern.compile("\\s*,\\s*");
    public static final PluralRules DEFAULT = new PluralRules(new RuleList().addRule(DEFAULT_RULE));
    private static final Rule DEFAULT_RULE = new Rule("other", NO_CONSTRAINT, null, null);
    static final Pattern DOTDOT_SEPARATED = Pattern.compile("\\s*\\Q..\\E\\s*");
    private static final Constraint NO_CONSTRAINT = new Constraint() {
        /* class android.icu.text.PluralRules.AnonymousClass1 */
        private static final long serialVersionUID = 9163464945387899416L;

        @Override // android.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal iFixedDecimal) {
            return true;
        }

        public String toString() {
            return "";
        }
    };
    static final Pattern OR_SEPARATED = Pattern.compile("\\s*or\\s*");
    static final Pattern SEMI_SEPARATED = Pattern.compile("\\s*;\\s*");
    static final Pattern TILDE_SEPARATED = Pattern.compile("\\s*~\\s*");
    private static final long serialVersionUID = 1;
    private final transient Set keywords;
    private final RuleList rules;

    /* access modifiers changed from: private */
    public interface Constraint extends Serializable {
        boolean isFulfilled(IFixedDecimal iFixedDecimal);
    }

    public interface IFixedDecimal {
        double getPluralOperand(Operand operand);

        boolean isInfinite();

        boolean isNaN();
    }

    public enum Operand {
        n,
        i,
        f,
        t,
        v,
        w,
        j
    }

    public enum PluralType {
        CARDINAL,
        ORDINAL
    }

    public enum SampleType {
        INTEGER,
        DECIMAL
    }

    static {
        UnicodeSet unicodeSet = new UnicodeSet("[a-z]");
        unicodeSet.freeze();
        ALLOWED_ID = unicodeSet;
    }

    public static abstract class Factory {
        protected Factory() {
        }

        public static PluralRulesLoader getDefaultFactory() {
            return PluralRulesLoader.loader;
        }
    }

    public static PluralRules parseDescription(String str) {
        String trim = str.trim();
        return trim.length() == 0 ? DEFAULT : new PluralRules(parseRuleChain(trim));
    }

    public static PluralRules createRules(String str) {
        try {
            return parseDescription(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static class FixedDecimal extends Number implements Comparable, IFixedDecimal {
        private static final long serialVersionUID = -4756200506571685661L;
        private final int baseFactor;
        final long decimalDigits;
        final long decimalDigitsWithoutTrailingZeros;
        final boolean hasIntegerValue;
        final long integerValue;
        final boolean isNegative;
        final double source;
        final int visibleDecimalDigitCount;
        final int visibleDecimalDigitCountWithoutTrailingZeros;

        public int getVisibleDecimalDigitCount() {
            return this.visibleDecimalDigitCount;
        }

        public FixedDecimal(double d, int i, long j) {
            boolean z = true;
            this.isNegative = d < 0.0d;
            this.source = this.isNegative ? -d : d;
            this.visibleDecimalDigitCount = i;
            this.decimalDigits = j;
            this.integerValue = d > 1.0E18d ? 1000000000000000000L : (long) d;
            this.hasIntegerValue = this.source != ((double) this.integerValue) ? false : z;
            if (j == 0) {
                this.decimalDigitsWithoutTrailingZeros = 0;
                this.visibleDecimalDigitCountWithoutTrailingZeros = 0;
            } else {
                int i2 = i;
                while (j % 10 == 0) {
                    j /= 10;
                    i2--;
                }
                this.decimalDigitsWithoutTrailingZeros = j;
                this.visibleDecimalDigitCountWithoutTrailingZeros = i2;
            }
            this.baseFactor = (int) Math.pow(10.0d, (double) i);
        }

        public FixedDecimal(double d, int i) {
            this(d, i, (long) getFractionalDigits(d, i));
        }

        private static int getFractionalDigits(double d, int i) {
            if (i == 0) {
                return 0;
            }
            if (d < 0.0d) {
                d = -d;
            }
            int pow = (int) Math.pow(10.0d, (double) i);
            return (int) (Math.round(d * ((double) pow)) % ((long) pow));
        }

        public FixedDecimal(double d) {
            this(d, decimals(d));
        }

        public static int decimals(double d) {
            if (Double.isInfinite(d) || Double.isNaN(d)) {
                return 0;
            }
            if (d < 0.0d) {
                d = -d;
            }
            if (d == Math.floor(d)) {
                return 0;
            }
            if (d < 1.0E9d) {
                long j = ((long) (d * 1000000.0d)) % 1000000;
                int i = 10;
                for (int i2 = 6; i2 > 0; i2--) {
                    if (j % ((long) i) != 0) {
                        return i2;
                    }
                    i *= 10;
                }
                return 0;
            }
            String format = String.format(Locale.ENGLISH, "%1.15e", Double.valueOf(d));
            int lastIndexOf = format.lastIndexOf(101);
            int i3 = lastIndexOf + 1;
            if (format.charAt(i3) == '+') {
                i3++;
            }
            int parseInt = (lastIndexOf - 2) - Integer.parseInt(format.substring(i3));
            if (parseInt < 0) {
                return 0;
            }
            int i4 = lastIndexOf - 1;
            while (parseInt > 0 && format.charAt(i4) == '0') {
                parseInt--;
                i4--;
            }
            return parseInt;
        }

        public FixedDecimal(String str) {
            this(Double.parseDouble(str), getVisibleFractionCount(str));
        }

        private static int getVisibleFractionCount(String str) {
            String trim = str.trim();
            int indexOf = trim.indexOf(46) + 1;
            if (indexOf == 0) {
                return 0;
            }
            return trim.length() - indexOf;
        }

        @Override // android.icu.text.PluralRules.IFixedDecimal
        public double getPluralOperand(Operand operand) {
            switch (AnonymousClass2.$SwitchMap$android$icu$text$PluralRules$Operand[operand.ordinal()]) {
                case 1:
                    return this.source;
                case 2:
                    return (double) this.integerValue;
                case 3:
                    return (double) this.decimalDigits;
                case 4:
                    return (double) this.decimalDigitsWithoutTrailingZeros;
                case 5:
                    return (double) this.visibleDecimalDigitCount;
                case 6:
                    return (double) this.visibleDecimalDigitCountWithoutTrailingZeros;
                default:
                    return this.source;
            }
        }

        public static Operand getOperand(String str) {
            return Operand.valueOf(str);
        }

        public int compareTo(FixedDecimal fixedDecimal) {
            long j = this.integerValue;
            long j2 = fixedDecimal.integerValue;
            if (j != j2) {
                return j < j2 ? -1 : 1;
            }
            double d = this.source;
            double d2 = fixedDecimal.source;
            if (d != d2) {
                return d < d2 ? -1 : 1;
            }
            int i = this.visibleDecimalDigitCount;
            int i2 = fixedDecimal.visibleDecimalDigitCount;
            if (i != i2) {
                return i < i2 ? -1 : 1;
            }
            int i3 = ((this.decimalDigits - fixedDecimal.decimalDigits) > 0 ? 1 : ((this.decimalDigits - fixedDecimal.decimalDigits) == 0 ? 0 : -1));
            if (i3 != 0) {
                return i3 < 0 ? -1 : 1;
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FixedDecimal)) {
                return false;
            }
            FixedDecimal fixedDecimal = (FixedDecimal) obj;
            return this.source == fixedDecimal.source && this.visibleDecimalDigitCount == fixedDecimal.visibleDecimalDigitCount && this.decimalDigits == fixedDecimal.decimalDigits;
        }

        public int hashCode() {
            return (int) (this.decimalDigits + ((long) ((this.visibleDecimalDigitCount + ((int) (this.source * 37.0d))) * 37)));
        }

        public String toString() {
            return String.format("%." + this.visibleDecimalDigitCount + "f", Double.valueOf(this.source));
        }

        @Override // java.lang.Number
        public int intValue() {
            return (int) this.integerValue;
        }

        @Override // java.lang.Number
        public long longValue() {
            return this.integerValue;
        }

        @Override // java.lang.Number
        public double doubleValue() {
            return this.isNegative ? -this.source : this.source;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream objectInputStream) {
            throw new NotSerializableException();
        }

        @Override // android.icu.text.PluralRules.IFixedDecimal
        public boolean isNaN() {
            return Double.isNaN(this.source);
        }

        @Override // android.icu.text.PluralRules.IFixedDecimal
        public boolean isInfinite() {
            return Double.isInfinite(this.source);
        }
    }

    public static class FixedDecimalRange {
        public final FixedDecimal end;
        public final FixedDecimal start;

        public FixedDecimalRange(FixedDecimal fixedDecimal, FixedDecimal fixedDecimal2) {
            if (fixedDecimal.visibleDecimalDigitCount == fixedDecimal2.visibleDecimalDigitCount) {
                this.start = fixedDecimal;
                this.end = fixedDecimal2;
                return;
            }
            throw new IllegalArgumentException("Ranges must have the same number of visible decimals: " + fixedDecimal + "~" + fixedDecimal2);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(this.start);
            if (this.end == this.start) {
                str = "";
            } else {
                str = "~" + this.end;
            }
            sb.append(str);
            return sb.toString();
        }
    }

    public static class FixedDecimalSamples {
        public final boolean bounded;
        public final SampleType sampleType;
        public final Set samples;

        private FixedDecimalSamples(SampleType sampleType2, Set set, boolean z) {
            this.sampleType = sampleType2;
            this.samples = set;
            this.bounded = z;
        }

        static FixedDecimalSamples parse(String str) {
            SampleType sampleType2;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (str.startsWith("integer")) {
                sampleType2 = SampleType.INTEGER;
            } else if (str.startsWith("decimal")) {
                sampleType2 = SampleType.DECIMAL;
            } else {
                throw new IllegalArgumentException("Samples must start with 'integer' or 'decimal'");
            }
            String[] split = PluralRules.COMMA_SEPARATED.split(str.substring(7).trim());
            boolean z = false;
            boolean z2 = true;
            for (String str2 : split) {
                if (str2.equals("…") || str2.equals("...")) {
                    z2 = false;
                    z = true;
                } else if (!z) {
                    String[] split2 = PluralRules.TILDE_SEPARATED.split(str2);
                    int length = split2.length;
                    if (length == 1) {
                        FixedDecimal fixedDecimal = new FixedDecimal(split2[0]);
                        checkDecimal(sampleType2, fixedDecimal);
                        linkedHashSet.add(new FixedDecimalRange(fixedDecimal, fixedDecimal));
                    } else if (length == 2) {
                        FixedDecimal fixedDecimal2 = new FixedDecimal(split2[0]);
                        FixedDecimal fixedDecimal3 = new FixedDecimal(split2[1]);
                        checkDecimal(sampleType2, fixedDecimal2);
                        checkDecimal(sampleType2, fixedDecimal3);
                        linkedHashSet.add(new FixedDecimalRange(fixedDecimal2, fixedDecimal3));
                    } else {
                        throw new IllegalArgumentException("Ill-formed number range: " + str2);
                    }
                } else {
                    throw new IllegalArgumentException("Can only have … at the end of samples: " + str2);
                }
            }
            return new FixedDecimalSamples(sampleType2, Collections.unmodifiableSet(linkedHashSet), z2);
        }

        private static void checkDecimal(SampleType sampleType2, FixedDecimal fixedDecimal) {
            boolean z = true;
            boolean z2 = sampleType2 == SampleType.INTEGER;
            if (fixedDecimal.getVisibleDecimalDigitCount() != 0) {
                z = false;
            }
            if (z2 != z) {
                throw new IllegalArgumentException("Ill-formed number range: " + fixedDecimal);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("@");
            sb.append(this.sampleType.toString().toLowerCase(Locale.ENGLISH));
            boolean z = true;
            for (FixedDecimalRange fixedDecimalRange : this.samples) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(' ');
                sb.append(fixedDecimalRange);
            }
            if (!this.bounded) {
                sb.append(", …");
            }
            return sb.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static class SimpleTokenizer {
        static final UnicodeSet BREAK_AND_IGNORE;
        static final UnicodeSet BREAK_AND_KEEP;

        static {
            UnicodeSet unicodeSet = new UnicodeSet(9, 10, 12, 13, 32, 32);
            unicodeSet.freeze();
            BREAK_AND_IGNORE = unicodeSet;
            UnicodeSet unicodeSet2 = new UnicodeSet(33, 33, 37, 37, 44, 44, 46, 46, 61, 61);
            unicodeSet2.freeze();
            BREAK_AND_KEEP = unicodeSet2;
        }

        static String[] split(String str) {
            ArrayList arrayList = new ArrayList();
            int i = -1;
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (BREAK_AND_IGNORE.contains(charAt)) {
                    if (i >= 0) {
                        arrayList.add(str.substring(i, i2));
                    }
                } else if (BREAK_AND_KEEP.contains(charAt)) {
                    if (i >= 0) {
                        arrayList.add(str.substring(i, i2));
                    }
                    arrayList.add(str.substring(i2, i2 + 1));
                } else {
                    if (i < 0) {
                        i = i2;
                    }
                }
                i = -1;
            }
            if (i >= 0) {
                arrayList.add(str.substring(i));
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:138:0x021b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.icu.text.PluralRules.Constraint parseConstraint(java.lang.String r33) {
        /*
        // Method dump skipped, instructions count: 635
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.PluralRules.parseConstraint(java.lang.String):android.icu.text.PluralRules$Constraint");
    }

    private static ParseException unexpected(String str, String str2) {
        return new ParseException("unexpected token '" + str + "' in '" + str2 + "'", -1);
    }

    private static String nextToken(String[] strArr, int i, String str) {
        if (i < strArr.length) {
            return strArr[i];
        }
        throw new ParseException("missing token at end of '" + str + "'", -1);
    }

    /* access modifiers changed from: private */
    public static Rule parseRule(String str) {
        FixedDecimalSamples fixedDecimalSamples;
        Constraint constraint;
        if (str.length() == 0) {
            return DEFAULT_RULE;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int indexOf = lowerCase.indexOf(58);
        if (indexOf != -1) {
            String trim = lowerCase.substring(0, indexOf).trim();
            if (isValidKeyword(trim)) {
                boolean z = true;
                String trim2 = lowerCase.substring(indexOf + 1).trim();
                String[] split = AT_SEPARATED.split(trim2);
                int length = split.length;
                FixedDecimalSamples fixedDecimalSamples2 = null;
                if (length == 1) {
                    fixedDecimalSamples = null;
                } else if (length == 2) {
                    fixedDecimalSamples = FixedDecimalSamples.parse(split[1]);
                    if (fixedDecimalSamples.sampleType != SampleType.DECIMAL) {
                        fixedDecimalSamples2 = fixedDecimalSamples;
                        fixedDecimalSamples = null;
                    }
                } else if (length == 3) {
                    fixedDecimalSamples2 = FixedDecimalSamples.parse(split[1]);
                    FixedDecimalSamples parse = FixedDecimalSamples.parse(split[2]);
                    if (fixedDecimalSamples2.sampleType == SampleType.INTEGER && parse.sampleType == SampleType.DECIMAL) {
                        fixedDecimalSamples = parse;
                    } else {
                        throw new IllegalArgumentException("Must have @integer then @decimal in " + trim2);
                    }
                } else {
                    throw new IllegalArgumentException("Too many samples in " + trim2);
                }
                boolean equals = trim.equals("other");
                if (split[0].length() != 0) {
                    z = false;
                }
                if (equals == z) {
                    if (equals) {
                        constraint = NO_CONSTRAINT;
                    } else {
                        constraint = parseConstraint(split[0]);
                    }
                    return new Rule(trim, constraint, fixedDecimalSamples2, fixedDecimalSamples);
                }
                throw new IllegalArgumentException("The keyword 'other' must have no constraints, just samples.");
            }
            throw new ParseException("keyword '" + trim + " is not valid", 0);
        }
        throw new ParseException("missing ':' in rule description '" + lowerCase + "'", 0);
    }

    private static RuleList parseRuleChain(String str) {
        String[] split;
        RuleList ruleList = new RuleList();
        if (str.endsWith(";")) {
            str = str.substring(0, str.length() - 1);
        }
        for (String str2 : SEMI_SEPARATED.split(str)) {
            Rule parseRule = parseRule(str2.trim());
            RuleList.access$276(ruleList, (parseRule.integerSamples == null && parseRule.decimalSamples == null) ? 0 : 1);
            ruleList.addRule(parseRule);
        }
        return ruleList.finish();
    }

    /* access modifiers changed from: private */
    public static class RangeConstraint implements Constraint, Serializable {
        private static final long serialVersionUID = 1;
        private final boolean inRange;
        private final boolean integersOnly;
        private final double lowerBound;
        private final int mod;
        private final Operand operand;
        private final long[] range_list;
        private final double upperBound;

        RangeConstraint(int i, boolean z, Operand operand2, boolean z2, double d, double d2, long[] jArr) {
            this.mod = i;
            this.inRange = z;
            this.integersOnly = z2;
            this.lowerBound = d;
            this.upperBound = d2;
            this.range_list = jArr;
            this.operand = operand2;
        }

        @Override // android.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal iFixedDecimal) {
            double pluralOperand = iFixedDecimal.getPluralOperand(this.operand);
            if ((this.integersOnly && pluralOperand - ((double) ((long) pluralOperand)) != 0.0d) || (this.operand == Operand.j && iFixedDecimal.getPluralOperand(Operand.v) != 0.0d)) {
                return !this.inRange;
            }
            int i = this.mod;
            if (i != 0) {
                pluralOperand %= (double) i;
            }
            boolean z = pluralOperand >= this.lowerBound && pluralOperand <= this.upperBound;
            if (z && this.range_list != null) {
                z = false;
                int i2 = 0;
                while (!z) {
                    long[] jArr = this.range_list;
                    if (i2 >= jArr.length) {
                        break;
                    }
                    z = pluralOperand >= ((double) jArr[i2]) && pluralOperand <= ((double) jArr[i2 + 1]);
                    i2 += 2;
                }
            }
            if (this.inRange == z) {
                return true;
            }
            return false;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
            if (r10.inRange != false) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
            if (r10.inRange != false) goto L_0x0044;
         */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x004b  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0065  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String toString() {
            /*
            // Method dump skipped, instructions count: 115
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.text.PluralRules.RangeConstraint.toString():java.lang.String");
        }
    }

    /* renamed from: android.icu.text.PluralRules$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$PluralRules$Operand = new int[Operand.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$PluralRules$SampleType = new int[SampleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005d */
        static {
            /*
            // Method dump skipped, instructions count: 105
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.text.PluralRules.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static void addRange(StringBuilder sb, double d, double d2, boolean z) {
        if (z) {
            sb.append(",");
        }
        if (d == d2) {
            sb.append(format(d));
            return;
        }
        sb.append(format(d) + ".." + format(d2));
    }

    private static String format(double d) {
        long j = (long) d;
        return d == ((double) j) ? String.valueOf(j) : String.valueOf(d);
    }

    private static abstract class BinaryConstraint implements Constraint, Serializable {
        private static final long serialVersionUID = 1;
        protected final Constraint a;
        protected final Constraint b;

        protected BinaryConstraint(Constraint constraint, Constraint constraint2) {
            this.a = constraint;
            this.b = constraint2;
        }
    }

    /* access modifiers changed from: private */
    public static class AndConstraint extends BinaryConstraint {
        private static final long serialVersionUID = 7766999779862263523L;

        AndConstraint(Constraint constraint, Constraint constraint2) {
            super(constraint, constraint2);
        }

        @Override // android.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal iFixedDecimal) {
            return this.a.isFulfilled(iFixedDecimal) && this.b.isFulfilled(iFixedDecimal);
        }

        public String toString() {
            return this.a.toString() + " and " + this.b.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class OrConstraint extends BinaryConstraint {
        private static final long serialVersionUID = 1405488568664762222L;

        OrConstraint(Constraint constraint, Constraint constraint2) {
            super(constraint, constraint2);
        }

        @Override // android.icu.text.PluralRules.Constraint
        public boolean isFulfilled(IFixedDecimal iFixedDecimal) {
            return this.a.isFulfilled(iFixedDecimal) || this.b.isFulfilled(iFixedDecimal);
        }

        public String toString() {
            return this.a.toString() + " or " + this.b.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class Rule implements Serializable {
        private static final long serialVersionUID = 1;
        private final Constraint constraint;
        private final FixedDecimalSamples decimalSamples;
        private final FixedDecimalSamples integerSamples;
        private final String keyword;

        public Rule(String str, Constraint constraint2, FixedDecimalSamples fixedDecimalSamples, FixedDecimalSamples fixedDecimalSamples2) {
            this.keyword = str;
            this.constraint = constraint2;
            this.integerSamples = fixedDecimalSamples;
            this.decimalSamples = fixedDecimalSamples2;
        }

        public String getKeyword() {
            return this.keyword;
        }

        public boolean appliesTo(IFixedDecimal iFixedDecimal) {
            return this.constraint.isFulfilled(iFixedDecimal);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(this.keyword);
            sb.append(": ");
            sb.append(this.constraint.toString());
            String str2 = "";
            if (this.integerSamples == null) {
                str = str2;
            } else {
                str = " " + this.integerSamples.toString();
            }
            sb.append(str);
            if (this.decimalSamples != null) {
                str2 = " " + this.decimalSamples.toString();
            }
            sb.append(str2);
            return sb.toString();
        }

        public int hashCode() {
            return this.constraint.hashCode() ^ this.keyword.hashCode();
        }
    }

    /* access modifiers changed from: private */
    public static class RuleList implements Serializable {
        private static final long serialVersionUID = 1;
        private boolean hasExplicitBoundingInfo;
        private final List rules;

        private RuleList() {
            this.hasExplicitBoundingInfo = false;
            this.rules = new ArrayList();
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [boolean, byte] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static /* synthetic */ boolean access$276(android.icu.text.PluralRules.RuleList r1, int r2) {
            /*
                boolean r0 = r1.hasExplicitBoundingInfo
                r2 = r2 | r0
                byte r2 = (byte) r2
                r1.hasExplicitBoundingInfo = r2
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.text.PluralRules.RuleList.access$276(android.icu.text.PluralRules$RuleList, int):boolean");
        }

        public RuleList addRule(Rule rule) {
            String keyword = rule.getKeyword();
            for (Rule rule2 : this.rules) {
                if (keyword.equals(rule2.getKeyword())) {
                    throw new IllegalArgumentException("Duplicate keyword: " + keyword);
                }
            }
            this.rules.add(rule);
            return this;
        }

        public RuleList finish() {
            Iterator it = this.rules.iterator();
            Rule rule = null;
            while (it.hasNext()) {
                Rule rule2 = (Rule) it.next();
                if ("other".equals(rule2.getKeyword())) {
                    it.remove();
                    rule = rule2;
                }
            }
            if (rule == null) {
                rule = PluralRules.parseRule("other:");
            }
            this.rules.add(rule);
            return this;
        }

        private Rule selectRule(IFixedDecimal iFixedDecimal) {
            for (Rule rule : this.rules) {
                if (rule.appliesTo(iFixedDecimal)) {
                    return rule;
                }
            }
            return null;
        }

        public String select(IFixedDecimal iFixedDecimal) {
            return (iFixedDecimal.isInfinite() || iFixedDecimal.isNaN()) ? "other" : selectRule(iFixedDecimal).getKeyword();
        }

        public Set getKeywords() {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (Rule rule : this.rules) {
                linkedHashSet.add(rule.getKeyword());
            }
            return linkedHashSet;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Rule rule : this.rules) {
                if (sb.length() != 0) {
                    sb.append(";  ");
                }
                sb.append(rule);
            }
            return sb.toString();
        }
    }

    public static PluralRules forLocale(ULocale uLocale) {
        return Factory.getDefaultFactory().forLocale(uLocale, PluralType.CARDINAL);
    }

    public static PluralRules forLocale(ULocale uLocale, PluralType pluralType) {
        return Factory.getDefaultFactory().forLocale(uLocale, pluralType);
    }

    private static boolean isValidKeyword(String str) {
        return ALLOWED_ID.containsAll(str);
    }

    private PluralRules(RuleList ruleList) {
        this.rules = ruleList;
        this.keywords = Collections.unmodifiableSet(ruleList.getKeywords());
    }

    public int hashCode() {
        return this.rules.hashCode();
    }

    public String select(IFixedDecimal iFixedDecimal) {
        return this.rules.select(iFixedDecimal);
    }

    public String toString() {
        return this.rules.toString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof PluralRules) && equals((PluralRules) obj);
    }

    public boolean equals(PluralRules pluralRules) {
        return pluralRules != null && toString().equals(pluralRules.toString());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        throw new NotSerializableException();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new NotSerializableException();
    }

    private Object writeReplace() {
        return new PluralRulesSerialProxy(toString());
    }
}
