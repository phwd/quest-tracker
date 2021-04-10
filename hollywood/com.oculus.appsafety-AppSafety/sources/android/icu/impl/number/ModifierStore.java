package android.icu.impl.number;

import android.icu.impl.StandardPlural;

public interface ModifierStore {
    Modifier getModifier(int i, StandardPlural standardPlural);
}
