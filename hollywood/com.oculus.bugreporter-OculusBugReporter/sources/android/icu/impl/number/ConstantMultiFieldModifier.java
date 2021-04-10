package android.icu.impl.number;

import android.icu.impl.number.Modifier;
import android.icu.text.NumberFormat;
import java.util.Arrays;

public class ConstantMultiFieldModifier implements Modifier {
    private final boolean overwrite;
    private final Modifier.Parameters parameters;
    protected final char[] prefixChars;
    protected final NumberFormat.Field[] prefixFields;
    private final boolean strong;
    protected final char[] suffixChars;
    protected final NumberFormat.Field[] suffixFields;

    public ConstantMultiFieldModifier(NumberStringBuilder prefix, NumberStringBuilder suffix, boolean overwrite2, boolean strong2) {
        this(prefix, suffix, overwrite2, strong2, null);
    }

    public ConstantMultiFieldModifier(NumberStringBuilder prefix, NumberStringBuilder suffix, boolean overwrite2, boolean strong2, Modifier.Parameters parameters2) {
        this.prefixChars = prefix.toCharArray();
        this.suffixChars = suffix.toCharArray();
        this.prefixFields = prefix.toFieldArray();
        this.suffixFields = suffix.toFieldArray();
        this.overwrite = overwrite2;
        this.strong = strong2;
        this.parameters = parameters2;
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
        int length = output.insert(leftIndex, this.prefixChars, this.prefixFields);
        if (this.overwrite) {
            length += output.splice(leftIndex + length, rightIndex + length, "", 0, 0, null);
        }
        return length + output.insert(rightIndex + length, this.suffixChars, this.suffixFields);
    }

    @Override // android.icu.impl.number.Modifier
    public int getPrefixLength() {
        return this.prefixChars.length;
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        char[] cArr = this.prefixChars;
        int codePointCount = Character.codePointCount(cArr, 0, cArr.length);
        char[] cArr2 = this.suffixChars;
        return codePointCount + Character.codePointCount(cArr2, 0, cArr2.length);
    }

    @Override // android.icu.impl.number.Modifier
    public boolean isStrong() {
        return this.strong;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean containsField(NumberFormat.Field field) {
        int i = 0;
        while (true) {
            NumberFormat.Field[] fieldArr = this.prefixFields;
            if (i >= fieldArr.length) {
                int i2 = 0;
                while (true) {
                    NumberFormat.Field[] fieldArr2 = this.suffixFields;
                    if (i2 >= fieldArr2.length) {
                        return false;
                    }
                    if (fieldArr2[i2] == field) {
                        return true;
                    }
                    i2++;
                }
            } else if (fieldArr[i] == field) {
                return true;
            } else {
                i++;
            }
        }
    }

    @Override // android.icu.impl.number.Modifier
    public Modifier.Parameters getParameters() {
        return this.parameters;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean semanticallyEquivalent(Modifier other) {
        if (!(other instanceof ConstantMultiFieldModifier)) {
            return false;
        }
        ConstantMultiFieldModifier _other = (ConstantMultiFieldModifier) other;
        Modifier.Parameters parameters2 = this.parameters;
        if (parameters2 != null && _other.parameters != null && parameters2.obj == _other.parameters.obj) {
            return true;
        }
        if (!Arrays.equals(this.prefixChars, _other.prefixChars) || !Arrays.equals(this.prefixFields, _other.prefixFields) || !Arrays.equals(this.suffixChars, _other.suffixChars) || !Arrays.equals(this.suffixFields, _other.suffixFields) || this.overwrite != _other.overwrite || this.strong != _other.strong) {
            return false;
        }
        return true;
    }

    public String toString() {
        NumberStringBuilder temp = new NumberStringBuilder();
        apply(temp, 0, 0);
        int prefixLength = getPrefixLength();
        return String.format("<ConstantMultiFieldModifier prefix:'%s' suffix:'%s'>", temp.subSequence(0, prefixLength), temp.subSequence(prefixLength, temp.length()));
    }
}
