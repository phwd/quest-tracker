package android.icu.impl.number.parse;

import android.icu.number.Scale;

public class MultiplierParseHandler extends ValidationMatcher {
    private final Scale multiplier;

    public MultiplierParseHandler(Scale multiplier2) {
        this.multiplier = multiplier2;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (result.quantity != null) {
            this.multiplier.applyReciprocalTo(result.quantity);
        }
    }

    public String toString() {
        return "<MultiplierHandler " + ((Object) this.multiplier) + ">";
    }
}
