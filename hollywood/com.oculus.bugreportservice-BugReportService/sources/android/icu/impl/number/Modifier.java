package android.icu.impl.number;

import android.icu.impl.StandardPlural;

public interface Modifier {

    public static class Parameters {
        public ModifierStore obj;
        public StandardPlural plural;
        public int signum;
    }

    int apply(NumberStringBuilder numberStringBuilder, int i, int i2);

    int getCodePointCount();
}
