package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.MicroPropsGenerator;
import android.icu.impl.number.Modifier;
import android.icu.impl.number.MultiplierProducer;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.number.NumberFormatter;
import android.icu.number.Precision;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.NumberFormat;

public class ScientificNotation extends Notation implements Cloneable {
    int engineeringInterval;
    NumberFormatter.SignDisplay exponentSignDisplay;
    int minExponentDigits;
    boolean requireMinInt;

    ScientificNotation(int i, boolean z, int i2, NumberFormatter.SignDisplay signDisplay) {
        this.engineeringInterval = i;
        this.requireMinInt = z;
        this.minExponentDigits = i2;
        this.exponentSignDisplay = signDisplay;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public MicroPropsGenerator withLocaleData(DecimalFormatSymbols decimalFormatSymbols, boolean z, MicroPropsGenerator microPropsGenerator) {
        return new ScientificHandler(decimalFormatSymbols, z, microPropsGenerator);
    }

    /* access modifiers changed from: private */
    public static class ScientificHandler implements MicroPropsGenerator, MultiplierProducer, Modifier {
        int exponent;
        final ScientificNotation notation;
        final MicroPropsGenerator parent;
        final ScientificModifier[] precomputedMods;
        final DecimalFormatSymbols symbols;

        @Override // android.icu.impl.number.Modifier
        public int getCodePointCount() {
            return 999;
        }

        private ScientificHandler(ScientificNotation scientificNotation, DecimalFormatSymbols decimalFormatSymbols, boolean z, MicroPropsGenerator microPropsGenerator) {
            this.notation = scientificNotation;
            this.symbols = decimalFormatSymbols;
            this.parent = microPropsGenerator;
            if (z) {
                this.precomputedMods = new ScientificModifier[25];
                for (int i = -12; i <= 12; i++) {
                    this.precomputedMods[i + 12] = new ScientificModifier(i, this);
                }
                return;
            }
            this.precomputedMods = null;
        }

        @Override // android.icu.impl.number.MicroPropsGenerator
        public MicroProps processQuantity(DecimalQuantity decimalQuantity) {
            MicroProps processQuantity = this.parent.processQuantity(decimalQuantity);
            int i = 0;
            if (decimalQuantity.isZero()) {
                ScientificNotation scientificNotation = this.notation;
                if (scientificNotation.requireMinInt) {
                    Precision precision = processQuantity.rounder;
                    if (precision instanceof Precision.SignificantRounderImpl) {
                        ((Precision.SignificantRounderImpl) precision).apply(decimalQuantity, scientificNotation.engineeringInterval);
                    }
                }
                processQuantity.rounder.apply(decimalQuantity);
            } else {
                i = -processQuantity.rounder.chooseMultiplierAndApply(decimalQuantity, this);
            }
            ScientificModifier[] scientificModifierArr = this.precomputedMods;
            if (scientificModifierArr != null && i >= -12 && i <= 12) {
                processQuantity.modInner = scientificModifierArr[i + 12];
            } else if (this.precomputedMods != null) {
                processQuantity.modInner = new ScientificModifier(i, this);
            } else {
                this.exponent = i;
                processQuantity.modInner = this;
            }
            processQuantity.rounder = Precision.constructPassThrough();
            return processQuantity;
        }

        @Override // android.icu.impl.number.MultiplierProducer
        public int getMultiplier(int i) {
            ScientificNotation scientificNotation = this.notation;
            int i2 = scientificNotation.engineeringInterval;
            if (!scientificNotation.requireMinInt) {
                if (i2 <= 1) {
                    i2 = 1;
                } else {
                    i2 = (((i % i2) + i2) % i2) + 1;
                }
            }
            return (i2 - i) - 1;
        }

        @Override // android.icu.impl.number.Modifier
        public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
            return doApply(this.exponent, numberStringBuilder, i2);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private int doApply(int i, NumberStringBuilder numberStringBuilder, int i2) {
            int abs;
            int i3;
            int insert;
            int insert2 = numberStringBuilder.insert(i2, this.symbols.getExponentSeparator(), NumberFormat.Field.EXPONENT_SYMBOL) + i2;
            if (i >= 0 || this.notation.exponentSignDisplay == NumberFormatter.SignDisplay.NEVER) {
                if (i >= 0 && this.notation.exponentSignDisplay == NumberFormatter.SignDisplay.ALWAYS) {
                    insert = numberStringBuilder.insert(insert2, this.symbols.getPlusSignString(), NumberFormat.Field.EXPONENT_SIGN);
                }
                abs = Math.abs(i);
                i3 = 0;
                while (true) {
                    if (i3 < this.notation.minExponentDigits && abs <= 0) {
                        return insert2 - i2;
                    }
                    insert2 += numberStringBuilder.insert(insert2 - i3, this.symbols.getDigitStringsLocal()[abs % 10], NumberFormat.Field.EXPONENT);
                    i3++;
                    abs /= 10;
                }
            } else {
                insert = numberStringBuilder.insert(insert2, this.symbols.getMinusSignString(), NumberFormat.Field.EXPONENT_SIGN);
            }
            insert2 += insert;
            abs = Math.abs(i);
            i3 = 0;
            while (true) {
                if (i3 < this.notation.minExponentDigits) {
                }
                insert2 += numberStringBuilder.insert(insert2 - i3, this.symbols.getDigitStringsLocal()[abs % 10], NumberFormat.Field.EXPONENT);
                i3++;
                abs /= 10;
            }
        }
    }

    private static class ScientificModifier implements Modifier {
        final int exponent;
        final ScientificHandler handler;

        @Override // android.icu.impl.number.Modifier
        public int getCodePointCount() {
            return 999;
        }

        ScientificModifier(int i, ScientificHandler scientificHandler) {
            this.exponent = i;
            this.handler = scientificHandler;
        }

        @Override // android.icu.impl.number.Modifier
        public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
            return this.handler.doApply(this.exponent, numberStringBuilder, i2);
        }
    }
}
