package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.text.PluralRules;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.FieldPosition;

public interface DecimalQuantity extends PluralRules.IFixedDecimal {
    void adjustMagnitude(int i);

    DecimalQuantity createCopy();

    byte getDigit(int i);

    int getLowerDisplayMagnitude();

    int getMagnitude();

    StandardPlural getStandardPlural(PluralRules pluralRules);

    int getUpperDisplayMagnitude();

    @Override // android.icu.text.PluralRules.IFixedDecimal
    boolean isInfinite();

    @Override // android.icu.text.PluralRules.IFixedDecimal
    boolean isNaN();

    boolean isZero();

    void multiplyBy(BigDecimal bigDecimal);

    void populateUFieldPosition(FieldPosition fieldPosition);

    void roundToIncrement(BigDecimal bigDecimal, MathContext mathContext);

    void roundToInfinity();

    void roundToMagnitude(int i, MathContext mathContext);

    void setFractionLength(int i, int i2);

    void setIntegerLength(int i, int i2);

    int signum();

    BigDecimal toBigDecimal();
}
