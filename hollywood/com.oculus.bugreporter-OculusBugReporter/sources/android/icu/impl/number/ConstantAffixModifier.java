package android.icu.impl.number;

import android.icu.impl.number.Modifier;
import android.icu.text.NumberFormat;

public class ConstantAffixModifier implements Modifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final ConstantAffixModifier EMPTY = new ConstantAffixModifier();
    private final NumberFormat.Field field;
    private final String prefix;
    private final boolean strong;
    private final String suffix;

    public ConstantAffixModifier(String prefix2, String suffix2, NumberFormat.Field field2, boolean strong2) {
        String str = "";
        this.prefix = prefix2 == null ? str : prefix2;
        this.suffix = suffix2 != null ? suffix2 : str;
        this.field = field2;
        this.strong = strong2;
    }

    public ConstantAffixModifier() {
        this.prefix = "";
        this.suffix = "";
        this.field = null;
        this.strong = false;
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
        return output.insert(rightIndex, this.suffix, this.field) + output.insert(leftIndex, this.prefix, this.field);
    }

    @Override // android.icu.impl.number.Modifier
    public int getPrefixLength() {
        return this.prefix.length();
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        String str = this.prefix;
        int codePointCount = str.codePointCount(0, str.length());
        String str2 = this.suffix;
        return codePointCount + str2.codePointCount(0, str2.length());
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
        return null;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean semanticallyEquivalent(Modifier other) {
        if (!(other instanceof ConstantAffixModifier)) {
            return false;
        }
        ConstantAffixModifier _other = (ConstantAffixModifier) other;
        if (!this.prefix.equals(_other.prefix) || !this.suffix.equals(_other.suffix) || this.field != _other.field || this.strong != _other.strong) {
            return false;
        }
        return true;
    }

    public String toString() {
        return String.format("<ConstantAffixModifier prefix:'%s' suffix:'%s'>", this.prefix, this.suffix);
    }
}
