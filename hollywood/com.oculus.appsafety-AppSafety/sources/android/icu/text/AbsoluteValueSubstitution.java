package android.icu.text;

/* access modifiers changed from: package-private */
/* compiled from: NFSubstitution */
public class AbsoluteValueSubstitution extends NFSubstitution {
    AbsoluteValueSubstitution(int pos, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
    }

    @Override // android.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return Math.abs(number);
    }

    @Override // android.icu.text.NFSubstitution
    public double transformNumber(double number) {
        return Math.abs(number);
    }

    @Override // android.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return -newRuleValue;
    }

    @Override // android.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return Double.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NFSubstitution
    public char tokenChar() {
        return '>';
    }
}
