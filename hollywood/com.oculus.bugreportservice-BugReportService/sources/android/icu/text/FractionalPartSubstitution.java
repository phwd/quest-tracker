package android.icu.text;

import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import java.text.ParsePosition;

/* access modifiers changed from: package-private */
/* compiled from: NFSubstitution */
public class FractionalPartSubstitution extends NFSubstitution {
    private final boolean byDigits;
    private final boolean useSpaces;

    @Override // android.icu.text.NFSubstitution
    public double calcUpperBound(double d) {
        return 0.0d;
    }

    @Override // android.icu.text.NFSubstitution
    public double composeRuleValue(double d, double d2) {
        return d + d2;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NFSubstitution
    public char tokenChar() {
        return '>';
    }

    @Override // android.icu.text.NFSubstitution
    public long transformNumber(long j) {
        return 0;
    }

    FractionalPartSubstitution(int i, NFRuleSet nFRuleSet, String str) {
        super(i, nFRuleSet, str);
        NFRuleSet nFRuleSet2;
        if (str.equals(">>") || str.equals(">>>") || nFRuleSet == (nFRuleSet2 = this.ruleSet)) {
            this.byDigits = true;
            this.useSpaces = !str.equals(">>>");
            return;
        }
        this.byDigits = false;
        this.useSpaces = true;
        nFRuleSet2.makeIntoFractionRuleSet();
    }

    @Override // android.icu.text.NFSubstitution
    public void doSubstitution(double d, StringBuilder sb, int i, int i2) {
        if (!this.byDigits) {
            super.doSubstitution(d, sb, i, i2);
            return;
        }
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD = new DecimalQuantity_DualStorageBCD(d);
        decimalQuantity_DualStorageBCD.roundToInfinity();
        boolean z = false;
        for (int lowerDisplayMagnitude = decimalQuantity_DualStorageBCD.getLowerDisplayMagnitude(); lowerDisplayMagnitude < 0; lowerDisplayMagnitude++) {
            if (!z || !this.useSpaces) {
                z = true;
            } else {
                sb.insert(this.pos + i, ' ');
            }
            this.ruleSet.format((long) decimalQuantity_DualStorageBCD.getDigit(lowerDisplayMagnitude), sb, i + this.pos, i2);
        }
    }

    @Override // android.icu.text.NFSubstitution
    public double transformNumber(double d) {
        return d - Math.floor(d);
    }

    @Override // android.icu.text.NFSubstitution
    public Number doParse(String str, ParsePosition parsePosition, double d, double d2, boolean z, int i) {
        Number parse;
        if (!this.byDigits) {
            return super.doParse(str, parsePosition, d, 0.0d, z, i);
        }
        ParsePosition parsePosition2 = new ParsePosition(1);
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD = new DecimalQuantity_DualStorageBCD();
        String str2 = str;
        int i2 = 0;
        while (str2.length() > 0 && parsePosition2.getIndex() != 0) {
            parsePosition2.setIndex(0);
            int intValue = this.ruleSet.parse(str2, parsePosition2, 10.0d, i).intValue();
            if (z && parsePosition2.getIndex() == 0 && (parse = this.ruleSet.owner.getDecimalFormat().parse(str2, parsePosition2)) != null) {
                intValue = parse.intValue();
            }
            if (parsePosition2.getIndex() != 0) {
                decimalQuantity_DualStorageBCD.appendDigit((byte) intValue, 0, true);
                i2++;
                parsePosition.setIndex(parsePosition.getIndex() + parsePosition2.getIndex());
                str2 = str2.substring(parsePosition2.getIndex());
                while (str2.length() > 0 && str2.charAt(0) == ' ') {
                    str2 = str2.substring(1);
                    parsePosition.setIndex(parsePosition.getIndex() + 1);
                }
            }
        }
        decimalQuantity_DualStorageBCD.adjustMagnitude(-i2);
        return new Double(composeRuleValue(decimalQuantity_DualStorageBCD.toDouble(), d));
    }
}
