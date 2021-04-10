package android.icu.text;

import android.icu.number.FormattedNumber;
import android.icu.number.LocalizedNumberFormatter;
import android.icu.text.MessagePattern;
import android.icu.text.PluralRules;
import android.icu.util.ULocale;
import java.io.ObjectInputStream;
import java.text.FieldPosition;
import java.util.Map;
import java.util.Objects;

public class PluralFormat extends UFormat {
    private static final long serialVersionUID = 1;
    private transient MessagePattern msgPattern;
    private NumberFormat numberFormat = null;
    private transient double offset = 0.0d;
    private Map parsedValues = null;
    private String pattern = null;
    private PluralRules pluralRules = null;
    private transient PluralSelectorAdapter pluralRulesWrapper = new PluralSelectorAdapter();
    private ULocale ulocale = null;

    /* access modifiers changed from: package-private */
    public interface PluralSelector {
        String select(Object obj, double d);
    }

    public PluralFormat() {
        init(null, PluralRules.PluralType.CARDINAL, ULocale.getDefault(ULocale.Category.FORMAT), null);
    }

    PluralFormat(ULocale uLocale, PluralRules.PluralType pluralType, String str, NumberFormat numberFormat2) {
        init(null, pluralType, uLocale, numberFormat2);
        applyPattern(str);
    }

    private void init(PluralRules pluralRules2, PluralRules.PluralType pluralType, ULocale uLocale, NumberFormat numberFormat2) {
        this.ulocale = uLocale;
        if (pluralRules2 == null) {
            pluralRules2 = PluralRules.forLocale(this.ulocale, pluralType);
        }
        this.pluralRules = pluralRules2;
        resetPattern();
        if (numberFormat2 == null) {
            numberFormat2 = NumberFormat.getInstance(this.ulocale);
        }
        this.numberFormat = numberFormat2;
    }

    private void resetPattern() {
        this.pattern = null;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern != null) {
            messagePattern.clear();
        }
        this.offset = 0.0d;
    }

    public void applyPattern(String str) {
        this.pattern = str;
        if (this.msgPattern == null) {
            this.msgPattern = new MessagePattern();
        }
        try {
            this.msgPattern.parsePluralStyle(str);
            this.offset = this.msgPattern.getPluralOffset(0);
        } catch (RuntimeException e) {
            resetPattern();
            throw e;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0091, code lost:
        if (r15.partSubstringMatches(r4, r7) != false) goto L_0x0093;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int findSubMessage(android.icu.text.MessagePattern r15, int r16, android.icu.text.PluralFormat.PluralSelector r17, java.lang.Object r18, double r19) {
        /*
        // Method dump skipped, instructions count: 162
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.PluralFormat.findSubMessage(android.icu.text.MessagePattern, int, android.icu.text.PluralFormat$PluralSelector, java.lang.Object, double):int");
    }

    /* access modifiers changed from: private */
    public final class PluralSelectorAdapter implements PluralSelector {
        private PluralSelectorAdapter() {
        }

        @Override // android.icu.text.PluralFormat.PluralSelector
        public String select(Object obj, double d) {
            return PluralFormat.this.pluralRules.select((PluralRules.IFixedDecimal) obj);
        }
    }

    public final String format(double d) {
        return format(Double.valueOf(d), d);
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Number) {
            Number number = (Number) obj;
            stringBuffer.append(format(number, number.doubleValue()));
            return stringBuffer;
        }
        throw new IllegalArgumentException("'" + obj + "' is not a Number");
    }

    private String format(Number number, double d) {
        String str;
        int index;
        FormattedNumber formattedNumber;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern == null || messagePattern.countParts() == 0) {
            return this.numberFormat.format(number);
        }
        double d2 = this.offset;
        double d3 = d - d2;
        NumberFormat numberFormat2 = this.numberFormat;
        StringBuilder sb = null;
        if (numberFormat2 instanceof DecimalFormat) {
            LocalizedNumberFormatter numberFormatter = ((DecimalFormat) numberFormat2).toNumberFormatter();
            if (this.offset == 0.0d) {
                formattedNumber = numberFormatter.format(number);
            } else {
                formattedNumber = numberFormatter.format(d3);
            }
            formattedNumber.toString();
            throw null;
        }
        if (d2 == 0.0d) {
            str = numberFormat2.format(number);
        } else {
            str = numberFormat2.format(d3);
        }
        int findSubMessage = findSubMessage(this.msgPattern, 0, this.pluralRulesWrapper, new PluralRules.FixedDecimal(d3), d);
        int limit = this.msgPattern.getPart(findSubMessage).getLimit();
        while (true) {
            findSubMessage++;
            MessagePattern.Part part = this.msgPattern.getPart(findSubMessage);
            MessagePattern.Part.Type type = part.getType();
            index = part.getIndex();
            if (type == MessagePattern.Part.Type.MSG_LIMIT) {
                break;
            } else if (type == MessagePattern.Part.Type.REPLACE_NUMBER || (type == MessagePattern.Part.Type.SKIP_SYNTAX && this.msgPattern.jdkAposMode())) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append((CharSequence) this.pattern, limit, index);
                if (type == MessagePattern.Part.Type.REPLACE_NUMBER) {
                    sb.append(str);
                }
                limit = part.getLimit();
            } else if (type == MessagePattern.Part.Type.ARG_START) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append((CharSequence) this.pattern, limit, index);
                findSubMessage = this.msgPattern.getLimitPartIndex(findSubMessage);
                limit = this.msgPattern.getPart(findSubMessage).getLimit();
                MessagePattern.appendReducedApostrophes(this.pattern, index, limit, sb);
            }
        }
        if (sb == null) {
            return this.pattern.substring(limit, index);
        }
        sb.append((CharSequence) this.pattern, limit, index);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public String parseType(String str, RbnfLenientScanner rbnfLenientScanner, FieldPosition fieldPosition) {
        int i;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern == null || messagePattern.countParts() == 0) {
            fieldPosition.setBeginIndex(-1);
            fieldPosition.setEndIndex(-1);
            return null;
        }
        int countParts = this.msgPattern.countParts();
        int beginIndex = fieldPosition.getBeginIndex();
        char c = 0;
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        int i2 = 0;
        String str2 = null;
        int i3 = -1;
        String str3 = null;
        while (i2 < countParts) {
            int i4 = i2 + 1;
            if (this.msgPattern.getPart(i2).getType() != MessagePattern.Part.Type.ARG_SELECTOR) {
                i2 = i4;
            } else {
                int i5 = i4 + 1;
                MessagePattern.Part part = this.msgPattern.getPart(i4);
                if (part.getType() != MessagePattern.Part.Type.MSG_START) {
                    i2 = i5;
                } else {
                    int i6 = i5 + 1;
                    MessagePattern.Part part2 = this.msgPattern.getPart(i5);
                    if (part2.getType() != MessagePattern.Part.Type.MSG_LIMIT) {
                        i2 = i6;
                    } else {
                        String substring = this.pattern.substring(part.getLimit(), part2.getIndex());
                        if (rbnfLenientScanner != null) {
                            i = rbnfLenientScanner.findText(str, substring, beginIndex)[c];
                        } else {
                            i = str.indexOf(substring, beginIndex);
                        }
                        if (i >= 0 && i >= i3 && (str3 == null || substring.length() > str3.length())) {
                            str3 = substring;
                            i3 = i;
                            str2 = this.pattern.substring(part.getLimit(), part2.getIndex());
                        }
                        i2 = i6;
                        c = 0;
                    }
                }
            }
        }
        if (str2 != null) {
            fieldPosition.setBeginIndex(i3);
            fieldPosition.setEndIndex(i3 + str3.length());
            return str2;
        }
        fieldPosition.setBeginIndex(-1);
        fieldPosition.setEndIndex(-1);
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PluralFormat.class != obj.getClass()) {
            return false;
        }
        PluralFormat pluralFormat = (PluralFormat) obj;
        return Objects.equals(this.ulocale, pluralFormat.ulocale) && Objects.equals(this.pluralRules, pluralFormat.pluralRules) && Objects.equals(this.msgPattern, pluralFormat.msgPattern) && Objects.equals(this.numberFormat, pluralFormat.numberFormat);
    }

    public int hashCode() {
        return this.parsedValues.hashCode() ^ this.pluralRules.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("locale=" + this.ulocale);
        sb.append(", rules='" + this.pluralRules + "'");
        sb.append(", pattern='" + this.pattern + "'");
        sb.append(", format='" + this.numberFormat + "'");
        return sb.toString();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
