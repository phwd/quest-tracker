package android.icu.impl.number;

import android.icu.impl.number.Modifier;
import android.icu.text.NumberFormat;

public class ConstantMultiFieldModifier implements Modifier {
    private final boolean overwrite;
    private final Modifier.Parameters parameters;
    protected final char[] prefixChars;
    protected final NumberFormat.Field[] prefixFields;
    private final boolean strong;
    protected final char[] suffixChars;
    protected final NumberFormat.Field[] suffixFields;

    public ConstantMultiFieldModifier(NumberStringBuilder numberStringBuilder, NumberStringBuilder numberStringBuilder2, boolean z, boolean z2) {
        this(numberStringBuilder, numberStringBuilder2, z, z2, null);
    }

    public ConstantMultiFieldModifier(NumberStringBuilder numberStringBuilder, NumberStringBuilder numberStringBuilder2, boolean z, boolean z2, Modifier.Parameters parameters2) {
        this.prefixChars = numberStringBuilder.toCharArray();
        this.suffixChars = numberStringBuilder2.toCharArray();
        this.prefixFields = numberStringBuilder.toFieldArray();
        this.suffixFields = numberStringBuilder2.toFieldArray();
        this.overwrite = z;
        this.strong = z2;
        this.parameters = parameters2;
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
        int insert = numberStringBuilder.insert(i, this.prefixChars, this.prefixFields);
        if (this.overwrite) {
            insert += numberStringBuilder.splice(i + insert, i2 + insert, "", 0, 0, null);
        }
        return insert + numberStringBuilder.insert(i2 + insert, this.suffixChars, this.suffixFields);
    }

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

    public String toString() {
        NumberStringBuilder numberStringBuilder = new NumberStringBuilder();
        apply(numberStringBuilder, 0, 0);
        int prefixLength = getPrefixLength();
        return String.format("<ConstantMultiFieldModifier prefix:'%s' suffix:'%s'>", numberStringBuilder.subSequence(0, prefixLength), numberStringBuilder.subSequence(prefixLength, numberStringBuilder.length()));
    }
}
