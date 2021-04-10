package android.icu.impl.number;

import android.icu.number.Scale;
import java.math.MathContext;
import java.math.RoundingMode;

public class RoundingUtils {
    public static final MathContext DEFAULT_MATH_CONTEXT_34_DIGITS = MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS[DEFAULT_ROUNDING_MODE.ordinal()];
    public static final MathContext DEFAULT_MATH_CONTEXT_UNLIMITED = MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[DEFAULT_ROUNDING_MODE.ordinal()];
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
    private static final MathContext[] MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS = new MathContext[RoundingMode.values().length];
    private static final MathContext[] MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED = new MathContext[RoundingMode.values().length];
    public static final int MAX_INT_FRAC_SIG = 999;
    public static final int SECTION_LOWER = 1;
    public static final int SECTION_MIDPOINT = 2;
    public static final int SECTION_UPPER = 3;

    static {
        for (int i = 0; i < MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS.length; i++) {
            MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[i] = new MathContext(0, RoundingMode.valueOf(i));
            MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS[i] = new MathContext(34);
        }
    }

    public static boolean getRoundingDirection(boolean isEven, boolean isNegative, int section, int roundingMode, Object reference) {
        switch (roundingMode) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return isNegative;
            case 3:
                return !isNegative;
            case 4:
                if (section == 1) {
                    return true;
                }
                if (section == 2 || section == 3) {
                    return false;
                }
            case 5:
                if (section == 1 || section == 2) {
                    return true;
                }
                if (section == 3) {
                    return false;
                }
                break;
            case 6:
                if (section == 1) {
                    return true;
                }
                if (section == 2) {
                    return isEven;
                }
                if (section == 3) {
                    return false;
                }
                break;
        }
        throw new ArithmeticException("Rounding is required on " + reference.toString());
    }

    public static boolean roundsAtMidpoint(int roundingMode) {
        if (roundingMode == 0 || roundingMode == 1 || roundingMode == 2 || roundingMode == 3) {
            return false;
        }
        return true;
    }

    public static MathContext getMathContextOrUnlimited(DecimalFormatProperties properties) {
        MathContext mathContext = properties.getMathContext();
        if (mathContext != null) {
            return mathContext;
        }
        RoundingMode roundingMode = properties.getRoundingMode();
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_EVEN;
        }
        return MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[roundingMode.ordinal()];
    }

    public static MathContext getMathContextOr34Digits(DecimalFormatProperties properties) {
        MathContext mathContext = properties.getMathContext();
        if (mathContext != null) {
            return mathContext;
        }
        RoundingMode roundingMode = properties.getRoundingMode();
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_EVEN;
        }
        return MATH_CONTEXT_BY_ROUNDING_MODE_34_DIGITS[roundingMode.ordinal()];
    }

    public static MathContext mathContextUnlimited(RoundingMode roundingMode) {
        return MATH_CONTEXT_BY_ROUNDING_MODE_UNLIMITED[roundingMode.ordinal()];
    }

    public static Scale scaleFromProperties(DecimalFormatProperties properties) {
        MathContext mc = getMathContextOr34Digits(properties);
        if (properties.getMagnitudeMultiplier() != 0) {
            return Scale.powerOfTen(properties.getMagnitudeMultiplier()).withMathContext(mc);
        }
        if (properties.getMultiplier() != null) {
            return Scale.byBigDecimal(properties.getMultiplier()).withMathContext(mc);
        }
        return null;
    }
}
