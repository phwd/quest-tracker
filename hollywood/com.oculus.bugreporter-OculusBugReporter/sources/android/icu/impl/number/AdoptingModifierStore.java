package android.icu.impl.number;

import android.icu.impl.StandardPlural;

public class AdoptingModifierStore implements ModifierStore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    boolean frozen;
    final Modifier[] mods;
    private final Modifier negative;
    private final Modifier positive;
    private final Modifier zero;

    public AdoptingModifierStore(Modifier positive2, Modifier zero2, Modifier negative2) {
        this.positive = positive2;
        this.zero = zero2;
        this.negative = negative2;
        this.mods = null;
        this.frozen = true;
    }

    public AdoptingModifierStore() {
        this.positive = null;
        this.zero = null;
        this.negative = null;
        this.mods = new Modifier[(StandardPlural.COUNT * 3)];
        this.frozen = false;
    }

    public void setModifier(int signum, StandardPlural plural, Modifier mod) {
        this.mods[getModIndex(signum, plural)] = mod;
    }

    public void freeze() {
        this.frozen = true;
    }

    public Modifier getModifierWithoutPlural(int signum) {
        if (signum == 0) {
            return this.zero;
        }
        return signum < 0 ? this.negative : this.positive;
    }

    @Override // android.icu.impl.number.ModifierStore
    public Modifier getModifier(int signum, StandardPlural plural) {
        return this.mods[getModIndex(signum, plural)];
    }

    private static int getModIndex(int signum, StandardPlural plural) {
        return (plural.ordinal() * 3) + signum + 1;
    }
}
