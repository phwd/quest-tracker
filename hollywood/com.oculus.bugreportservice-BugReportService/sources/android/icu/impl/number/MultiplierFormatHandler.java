package android.icu.impl.number;

import android.icu.number.Scale;

public class MultiplierFormatHandler implements MicroPropsGenerator {
    final Scale multiplier;
    final MicroPropsGenerator parent;

    public MultiplierFormatHandler(Scale scale, MicroPropsGenerator microPropsGenerator) {
        this.multiplier = scale;
        this.parent = microPropsGenerator;
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity decimalQuantity) {
        MicroProps processQuantity = this.parent.processQuantity(decimalQuantity);
        this.multiplier.applyTo(decimalQuantity);
        return processQuantity;
    }
}
