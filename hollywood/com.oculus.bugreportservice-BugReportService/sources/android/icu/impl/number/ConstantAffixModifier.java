package android.icu.impl.number;

import android.icu.text.NumberFormat;

public class ConstantAffixModifier implements Modifier {
    public static final ConstantAffixModifier EMPTY = new ConstantAffixModifier();
    private final NumberFormat.Field field = null;
    private final String prefix = "";
    private final boolean strong = false;
    private final String suffix = "";

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
        return numberStringBuilder.insert(i2, this.suffix, this.field) + numberStringBuilder.insert(i, this.prefix, this.field);
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        String str = this.prefix;
        int codePointCount = str.codePointCount(0, str.length());
        String str2 = this.suffix;
        return codePointCount + str2.codePointCount(0, str2.length());
    }

    public String toString() {
        return String.format("<ConstantAffixModifier prefix:'%s' suffix:'%s'>", this.prefix, this.suffix);
    }
}
