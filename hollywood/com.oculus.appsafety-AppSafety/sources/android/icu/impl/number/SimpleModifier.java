package android.icu.impl.number;

import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.number.Modifier;
import android.icu.impl.number.range.PrefixInfixSuffixLengthHelper;
import android.icu.text.NumberFormat;
import android.icu.util.ICUException;

public class SimpleModifier implements Modifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ARG_NUM_LIMIT = 256;
    private final String compiledPattern;
    private final NumberFormat.Field field;
    private final Modifier.Parameters parameters;
    private final int prefixLength;
    private final boolean strong;
    private final int suffixLength;
    private final int suffixOffset;

    public SimpleModifier(String compiledPattern2, NumberFormat.Field field2, boolean strong2) {
        this(compiledPattern2, field2, strong2, null);
    }

    public SimpleModifier(String compiledPattern2, NumberFormat.Field field2, boolean strong2, Modifier.Parameters parameters2) {
        this.compiledPattern = compiledPattern2;
        this.field = field2;
        this.strong = strong2;
        this.parameters = parameters2;
        if (SimpleFormatterImpl.getArgumentLimit(compiledPattern2) == 0) {
            this.prefixLength = compiledPattern2.charAt(1) - 256;
            this.suffixOffset = -1;
            this.suffixLength = 0;
            return;
        }
        if (compiledPattern2.charAt(1) != 0) {
            this.prefixLength = compiledPattern2.charAt(1) - 256;
            this.suffixOffset = this.prefixLength + 3;
        } else {
            this.prefixLength = 0;
            this.suffixOffset = 2;
        }
        if (this.prefixLength + 3 < compiledPattern2.length()) {
            this.suffixLength = compiledPattern2.charAt(this.suffixOffset) - 256;
        } else {
            this.suffixLength = 0;
        }
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
        return formatAsPrefixSuffix(output, leftIndex, rightIndex, this.field);
    }

    @Override // android.icu.impl.number.Modifier
    public int getPrefixLength() {
        return this.prefixLength;
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        int count = 0;
        int i = this.prefixLength;
        if (i > 0) {
            count = 0 + Character.codePointCount(this.compiledPattern, 2, i + 2);
        }
        int i2 = this.suffixLength;
        if (i2 <= 0) {
            return count;
        }
        String str = this.compiledPattern;
        int i3 = this.suffixOffset;
        return count + Character.codePointCount(str, i3 + 1, i3 + 1 + i2);
    }

    @Override // android.icu.impl.number.Modifier
    public boolean isStrong() {
        return this.strong;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean containsField(NumberFormat.Field field2) {
        return false;
    }

    @Override // android.icu.impl.number.Modifier
    public Modifier.Parameters getParameters() {
        return this.parameters;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean semanticallyEquivalent(Modifier other) {
        if (!(other instanceof SimpleModifier)) {
            return false;
        }
        SimpleModifier _other = (SimpleModifier) other;
        Modifier.Parameters parameters2 = this.parameters;
        if (parameters2 != null && _other.parameters != null && parameters2.obj == _other.parameters.obj) {
            return true;
        }
        if (this.compiledPattern.equals(_other.compiledPattern) && this.field == _other.field && this.strong == _other.strong) {
            return true;
        }
        return false;
    }

    public int formatAsPrefixSuffix(NumberStringBuilder result, int startIndex, int endIndex, NumberFormat.Field field2) {
        if (this.suffixOffset == -1) {
            return result.splice(startIndex, endIndex, this.compiledPattern, 2, this.prefixLength + 2, field2);
        }
        int i = this.prefixLength;
        if (i > 0) {
            result.insert(startIndex, this.compiledPattern, 2, i + 2, field2);
        }
        int i2 = this.suffixLength;
        if (i2 > 0) {
            int i3 = endIndex + this.prefixLength;
            String str = this.compiledPattern;
            int i4 = this.suffixOffset;
            result.insert(i3, str, i4 + 1, i4 + 1 + i2, field2);
        }
        return this.prefixLength + this.suffixLength;
    }

    public static void formatTwoArgPattern(String compiledPattern2, NumberStringBuilder result, int index, PrefixInfixSuffixLengthHelper h, NumberFormat.Field field2) {
        int prefixLength2;
        int infixLength;
        int suffixLength2;
        if (SimpleFormatterImpl.getArgumentLimit(compiledPattern2) == 2) {
            int length = 0;
            int prefixLength3 = compiledPattern2.charAt(1);
            int offset = 1 + 1;
            if (prefixLength3 < 256) {
                prefixLength2 = 0;
            } else {
                prefixLength2 = prefixLength3 - 256;
                result.insert(index + 0, compiledPattern2, offset, offset + prefixLength2, field2);
                length = 0 + prefixLength2;
                offset = offset + prefixLength2 + 1;
            }
            int infixLength2 = compiledPattern2.charAt(offset);
            int offset2 = offset + 1;
            if (infixLength2 < 256) {
                infixLength = 0;
            } else {
                infixLength = infixLength2 - 256;
                result.insert(index + length, compiledPattern2, offset2, offset2 + infixLength, field2);
                length += infixLength;
                offset2 = offset2 + infixLength + 1;
            }
            if (offset2 == compiledPattern2.length()) {
                suffixLength2 = 0;
            } else {
                suffixLength2 = compiledPattern2.charAt(offset2) - 256;
                int offset3 = offset2 + 1;
                result.insert(index + length, compiledPattern2, offset3, offset3 + suffixLength2, field2);
                int length2 = length + suffixLength2;
            }
            h.lengthPrefix = prefixLength2;
            h.lengthInfix = infixLength;
            h.lengthSuffix = suffixLength2;
            return;
        }
        throw new ICUException();
    }
}
