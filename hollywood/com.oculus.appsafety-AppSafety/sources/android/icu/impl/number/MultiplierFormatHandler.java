package android.icu.impl.number;

import android.icu.number.Scale;

public class MultiplierFormatHandler implements MicroPropsGenerator {
    final Scale multiplier;
    final MicroPropsGenerator parent;

    public MultiplierFormatHandler(Scale multiplier2, MicroPropsGenerator parent2) {
        this.multiplier = multiplier2;
        this.parent = parent2;
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity quantity) {
        MicroProps micros = this.parent.processQuantity(quantity);
        this.multiplier.applyTo(quantity);
        return micros;
    }
}
