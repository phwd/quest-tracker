package android.icu.impl.number;

import android.icu.number.IntegerWidth;
import android.icu.number.Notation;
import android.icu.number.NumberFormatter;
import android.icu.number.Precision;
import android.icu.number.Scale;
import android.icu.text.PluralRules;
import android.icu.util.MeasureUnit;
import android.icu.util.ULocale;
import java.math.RoundingMode;
import java.util.Objects;

public class MacroProps implements Cloneable {
    public AffixPatternProvider affixProvider;
    public NumberFormatter.DecimalSeparatorDisplay decimal;
    public Object grouping;
    public IntegerWidth integerWidth;
    public ULocale loc;
    public Notation notation;
    public Padder padder;
    public MeasureUnit perUnit;
    public Precision precision;
    public RoundingMode roundingMode;
    public PluralRules rules;
    public Scale scale;
    public NumberFormatter.SignDisplay sign;
    public Object symbols;
    public Long threshold;
    public MeasureUnit unit;
    public NumberFormatter.UnitWidth unitWidth;

    public void fallback(MacroProps fallback) {
        if (this.notation == null) {
            this.notation = fallback.notation;
        }
        if (this.unit == null) {
            this.unit = fallback.unit;
        }
        if (this.perUnit == null) {
            this.perUnit = fallback.perUnit;
        }
        if (this.precision == null) {
            this.precision = fallback.precision;
        }
        if (this.roundingMode == null) {
            this.roundingMode = fallback.roundingMode;
        }
        if (this.grouping == null) {
            this.grouping = fallback.grouping;
        }
        if (this.padder == null) {
            this.padder = fallback.padder;
        }
        if (this.integerWidth == null) {
            this.integerWidth = fallback.integerWidth;
        }
        if (this.symbols == null) {
            this.symbols = fallback.symbols;
        }
        if (this.unitWidth == null) {
            this.unitWidth = fallback.unitWidth;
        }
        if (this.sign == null) {
            this.sign = fallback.sign;
        }
        if (this.decimal == null) {
            this.decimal = fallback.decimal;
        }
        if (this.affixProvider == null) {
            this.affixProvider = fallback.affixProvider;
        }
        if (this.scale == null) {
            this.scale = fallback.scale;
        }
        if (this.rules == null) {
            this.rules = fallback.rules;
        }
        if (this.loc == null) {
            this.loc = fallback.loc;
        }
    }

    public int hashCode() {
        return Objects.hash(this.notation, this.unit, this.perUnit, this.precision, this.roundingMode, this.grouping, this.padder, this.integerWidth, this.symbols, this.unitWidth, this.sign, this.decimal, this.affixProvider, this.scale, this.rules, this.loc);
    }

    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }
        if (this == _other) {
            return true;
        }
        if (!(_other instanceof MacroProps)) {
            return false;
        }
        MacroProps other = (MacroProps) _other;
        if (!Objects.equals(this.notation, other.notation) || !Objects.equals(this.unit, other.unit) || !Objects.equals(this.perUnit, other.perUnit) || !Objects.equals(this.precision, other.precision) || !Objects.equals(this.roundingMode, other.roundingMode) || !Objects.equals(this.grouping, other.grouping) || !Objects.equals(this.padder, other.padder) || !Objects.equals(this.integerWidth, other.integerWidth) || !Objects.equals(this.symbols, other.symbols) || !Objects.equals(this.unitWidth, other.unitWidth) || !Objects.equals(this.sign, other.sign) || !Objects.equals(this.decimal, other.decimal) || !Objects.equals(this.affixProvider, other.affixProvider) || !Objects.equals(this.scale, other.scale) || !Objects.equals(this.rules, other.rules) || !Objects.equals(this.loc, other.loc)) {
            return false;
        }
        return true;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
