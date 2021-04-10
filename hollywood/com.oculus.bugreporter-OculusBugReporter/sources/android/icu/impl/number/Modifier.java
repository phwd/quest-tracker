package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.text.NumberFormat;

public interface Modifier {

    public static class Parameters {
        public ModifierStore obj;
        public StandardPlural plural;
        public int signum;
    }

    int apply(NumberStringBuilder numberStringBuilder, int i, int i2);

    boolean containsField(NumberFormat.Field field);

    int getCodePointCount();

    Parameters getParameters();

    int getPrefixLength();

    boolean isStrong();

    boolean semanticallyEquivalent(Modifier modifier);
}
