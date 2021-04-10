package android.icu.text;

/* access modifiers changed from: package-private */
/* compiled from: NFSubstitution */
public class SameValueSubstitution extends NFSubstitution {
    SameValueSubstitution(int pos, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        if (description.equals("==")) {
            throw new IllegalArgumentException("== is not a legal token");
        }
    }

    @Override // android.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return number;
    }

    @Override // android.icu.text.NFSubstitution
    public double transformNumber(double number) {
        return number;
    }

    @Override // android.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return newRuleValue;
    }

    @Override // android.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return oldUpperBound;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NFSubstitution
    public char tokenChar() {
        return '=';
    }
}
