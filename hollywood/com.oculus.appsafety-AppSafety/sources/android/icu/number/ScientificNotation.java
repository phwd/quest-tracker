package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.MicroPropsGenerator;
import android.icu.impl.number.Modifier;
import android.icu.impl.number.MultiplierProducer;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.impl.number.RoundingUtils;
import android.icu.number.NumberFormatter;
import android.icu.number.Precision;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.NumberFormat;

public class ScientificNotation extends Notation implements Cloneable {
    int engineeringInterval;
    NumberFormatter.SignDisplay exponentSignDisplay;
    int minExponentDigits;
    boolean requireMinInt;

    ScientificNotation(int engineeringInterval2, boolean requireMinInt2, int minExponentDigits2, NumberFormatter.SignDisplay exponentSignDisplay2) {
        this.engineeringInterval = engineeringInterval2;
        this.requireMinInt = requireMinInt2;
        this.minExponentDigits = minExponentDigits2;
        this.exponentSignDisplay = exponentSignDisplay2;
    }

    public ScientificNotation withMinExponentDigits(int minExponentDigits2) {
        if (minExponentDigits2 < 1 || minExponentDigits2 > 999) {
            throw new IllegalArgumentException("Integer digits must be between 1 and 999 (inclusive)");
        }
        ScientificNotation other = (ScientificNotation) clone();
        other.minExponentDigits = minExponentDigits2;
        return other;
    }

    public ScientificNotation withExponentSignDisplay(NumberFormatter.SignDisplay exponentSignDisplay2) {
        ScientificNotation other = (ScientificNotation) clone();
        other.exponentSignDisplay = exponentSignDisplay2;
        return other;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public MicroPropsGenerator withLocaleData(DecimalFormatSymbols symbols, boolean build, MicroPropsGenerator parent) {
        return new ScientificHandler(symbols, build, parent);
    }

    /* access modifiers changed from: private */
    public static class ScientificHandler implements MicroPropsGenerator, MultiplierProducer, Modifier {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        int exponent;
        final ScientificNotation notation;
        final MicroPropsGenerator parent;
        final ScientificModifier[] precomputedMods;
        final DecimalFormatSymbols symbols;

        private ScientificHandler(ScientificNotation notation2, DecimalFormatSymbols symbols2, boolean safe, MicroPropsGenerator parent2) {
            this.notation = notation2;
            this.symbols = symbols2;
            this.parent = parent2;
            if (safe) {
                this.precomputedMods = new ScientificModifier[25];
                for (int i = -12; i <= 12; i++) {
                    this.precomputedMods[i + 12] = new ScientificModifier(i, this);
                }
                return;
            }
            this.precomputedMods = null;
        }

        @Override // android.icu.impl.number.MicroPropsGenerator
        public MicroProps processQuantity(DecimalQuantity quantity) {
            int exponent2;
            MicroProps micros = this.parent.processQuantity(quantity);
            if (!quantity.isZero()) {
                exponent2 = -micros.rounder.chooseMultiplierAndApply(quantity, this);
            } else if (!this.notation.requireMinInt || !(micros.rounder instanceof Precision.SignificantRounderImpl)) {
                micros.rounder.apply(quantity);
                exponent2 = 0;
            } else {
                ((Precision.SignificantRounderImpl) micros.rounder).apply(quantity, this.notation.engineeringInterval);
                exponent2 = 0;
            }
            ScientificModifier[] scientificModifierArr = this.precomputedMods;
            if (scientificModifierArr != null && exponent2 >= -12 && exponent2 <= 12) {
                micros.modInner = scientificModifierArr[exponent2 + 12];
            } else if (this.precomputedMods != null) {
                micros.modInner = new ScientificModifier(exponent2, this);
            } else {
                this.exponent = exponent2;
                micros.modInner = this;
            }
            micros.rounder = Precision.constructPassThrough();
            return micros;
        }

        @Override // android.icu.impl.number.MultiplierProducer
        public int getMultiplier(int magnitude) {
            int digitsShown;
            int interval = this.notation.engineeringInterval;
            if (this.notation.requireMinInt) {
                digitsShown = interval;
            } else if (interval <= 1) {
                digitsShown = 1;
            } else {
                digitsShown = (((magnitude % interval) + interval) % interval) + 1;
            }
            return (digitsShown - magnitude) - 1;
        }

        @Override // android.icu.impl.number.Modifier
        public int getPrefixLength() {
            return 0;
        }

        @Override // android.icu.impl.number.Modifier
        public int getCodePointCount() {
            return RoundingUtils.MAX_INT_FRAC_SIG;
        }

        @Override // android.icu.impl.number.Modifier
        public boolean isStrong() {
            return true;
        }

        @Override // android.icu.impl.number.Modifier
        public boolean containsField(NumberFormat.Field field) {
            return false;
        }

        @Override // android.icu.impl.number.Modifier
        public Modifier.Parameters getParameters() {
            return null;
        }

        @Override // android.icu.impl.number.Modifier
        public boolean semanticallyEquivalent(Modifier other) {
            return false;
        }

        @Override // android.icu.impl.number.Modifier
        public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
            return doApply(this.exponent, output, rightIndex);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private int doApply(int exponent2, NumberStringBuilder output, int rightIndex) {
            int i = rightIndex + output.insert(rightIndex, this.symbols.getExponentSeparator(), NumberFormat.Field.EXPONENT_SYMBOL);
            if (exponent2 < 0 && this.notation.exponentSignDisplay != NumberFormatter.SignDisplay.NEVER) {
                i += output.insert(i, this.symbols.getMinusSignString(), NumberFormat.Field.EXPONENT_SIGN);
            } else if (exponent2 >= 0 && this.notation.exponentSignDisplay == NumberFormatter.SignDisplay.ALWAYS) {
                i += output.insert(i, this.symbols.getPlusSignString(), NumberFormat.Field.EXPONENT_SIGN);
            }
            int disp = Math.abs(exponent2);
            int j = 0;
            while (true) {
                if (j >= this.notation.minExponentDigits && disp <= 0) {
                    return i - rightIndex;
                }
                int i2 = i - j;
                i += output.insert(i2, this.symbols.getDigitStringsLocal()[disp % 10], NumberFormat.Field.EXPONENT);
                j++;
                disp /= 10;
            }
        }
    }

    private static class ScientificModifier implements Modifier {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final int exponent;
        final ScientificHandler handler;

        ScientificModifier(int exponent2, ScientificHandler handler2) {
            this.exponent = exponent2;
            this.handler = handler2;
        }

        @Override // android.icu.impl.number.Modifier
        public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
            return this.handler.doApply(this.exponent, output, rightIndex);
        }

        @Override // android.icu.impl.number.Modifier
        public int getPrefixLength() {
            return 0;
        }

        @Override // android.icu.impl.number.Modifier
        public int getCodePointCount() {
            return RoundingUtils.MAX_INT_FRAC_SIG;
        }

        @Override // android.icu.impl.number.Modifier
        public boolean isStrong() {
            return true;
        }

        @Override // android.icu.impl.number.Modifier
        public boolean containsField(NumberFormat.Field field) {
            return false;
        }

        @Override // android.icu.impl.number.Modifier
        public Modifier.Parameters getParameters() {
            return null;
        }

        @Override // android.icu.impl.number.Modifier
        public boolean semanticallyEquivalent(Modifier other) {
            if ((other instanceof ScientificModifier) && this.exponent == ((ScientificModifier) other).exponent) {
                return true;
            }
            return false;
        }
    }
}
