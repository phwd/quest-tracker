package android.icu.impl.number;

import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.number.Modifier;
import android.icu.text.NumberFormat;

public class SimpleModifier implements Modifier {
    private final String compiledPattern;
    private final NumberFormat.Field field;
    private final Modifier.Parameters parameters;
    private final int prefixLength;
    private final boolean strong;
    private final int suffixLength;
    private final int suffixOffset;

    public SimpleModifier(String str, NumberFormat.Field field2, boolean z, Modifier.Parameters parameters2) {
        this.compiledPattern = str;
        this.field = field2;
        this.strong = z;
        this.parameters = parameters2;
        if (SimpleFormatterImpl.getArgumentLimit(str) == 0) {
            this.prefixLength = str.charAt(1) - 256;
            this.suffixOffset = -1;
            this.suffixLength = 0;
            return;
        }
        if (str.charAt(1) != 0) {
            this.prefixLength = str.charAt(1) - 256;
            this.suffixOffset = this.prefixLength + 3;
        } else {
            this.prefixLength = 0;
            this.suffixOffset = 2;
        }
        if (this.prefixLength + 3 < str.length()) {
            this.suffixLength = str.charAt(this.suffixOffset) - 256;
        } else {
            this.suffixLength = 0;
        }
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
        return formatAsPrefixSuffix(numberStringBuilder, i, i2, this.field);
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        int i = this.prefixLength;
        int i2 = 0;
        if (i > 0) {
            i2 = 0 + Character.codePointCount(this.compiledPattern, 2, i + 2);
        }
        int i3 = this.suffixLength;
        if (i3 <= 0) {
            return i2;
        }
        String str = this.compiledPattern;
        int i4 = this.suffixOffset;
        return i2 + Character.codePointCount(str, i4 + 1, i4 + 1 + i3);
    }

    public int formatAsPrefixSuffix(NumberStringBuilder numberStringBuilder, int i, int i2, NumberFormat.Field field2) {
        if (this.suffixOffset == -1) {
            return numberStringBuilder.splice(i, i2, this.compiledPattern, 2, this.prefixLength + 2, field2);
        }
        int i3 = this.prefixLength;
        if (i3 > 0) {
            numberStringBuilder.insert(i, this.compiledPattern, 2, i3 + 2, field2);
        }
        int i4 = this.suffixLength;
        if (i4 > 0) {
            int i5 = i2 + this.prefixLength;
            String str = this.compiledPattern;
            int i6 = this.suffixOffset;
            numberStringBuilder.insert(i5, str, i6 + 1, i6 + 1 + i4, field2);
        }
        return this.prefixLength + this.suffixLength;
    }
}
