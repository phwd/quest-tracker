package android.icu.impl.number.parse;

import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import android.icu.number.Scale;

public class MultiplierParseHandler extends ValidationMatcher {
    private final Scale multiplier;

    public MultiplierParseHandler(Scale scale) {
        this.multiplier = scale;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD = parsedNumber.quantity;
        if (decimalQuantity_DualStorageBCD != null) {
            this.multiplier.applyReciprocalTo(decimalQuantity_DualStorageBCD);
        }
    }

    public String toString() {
        return "<MultiplierHandler " + this.multiplier + ">";
    }
}
