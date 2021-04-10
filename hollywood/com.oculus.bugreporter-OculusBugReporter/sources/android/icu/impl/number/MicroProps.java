package android.icu.impl.number;

import android.icu.number.IntegerWidth;
import android.icu.number.NumberFormatter;
import android.icu.number.Precision;
import android.icu.text.DecimalFormatSymbols;

public class MicroProps implements Cloneable, MicroPropsGenerator {
    public NumberFormatter.DecimalSeparatorDisplay decimal;
    private volatile boolean exhausted;
    public Grouper grouping;
    private final boolean immutable;
    public IntegerWidth integerWidth;
    public Modifier modInner;
    public Modifier modMiddle;
    public Modifier modOuter;
    public Padder padding;
    public Precision rounder;
    public NumberFormatter.SignDisplay sign;
    public DecimalFormatSymbols symbols;
    public boolean useCurrency;

    public MicroProps(boolean immutable2) {
        this.immutable = immutable2;
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity quantity) {
        if (this.immutable) {
            return (MicroProps) clone();
        }
        if (!this.exhausted) {
            this.exhausted = true;
            return this;
        }
        throw new AssertionError((Object) "Cannot re-use a mutable MicroProps in the quantity chain");
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
