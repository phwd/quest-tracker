package android.icu.text;

/* access modifiers changed from: package-private */
/* compiled from: NFSubstitution */
public class MultiplierSubstitution extends NFSubstitution {
    long divisor;

    MultiplierSubstitution(int pos, NFRule rule, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        this.divisor = rule.getDivisor();
        if (this.divisor == 0) {
            throw new IllegalStateException("Substitution with divisor 0 " + description.substring(0, pos) + " | " + description.substring(pos));
        }
    }

    @Override // android.icu.text.NFSubstitution
    public void setDivisor(int radix, short exponent) {
        this.divisor = NFRule.power((long) radix, exponent);
        if (this.divisor == 0) {
            throw new IllegalStateException("Substitution with divisor 0");
        }
    }

    @Override // android.icu.text.NFSubstitution
    public boolean equals(Object that) {
        return super.equals(that) && this.divisor == ((MultiplierSubstitution) that).divisor;
    }

    @Override // android.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return (long) Math.floor((double) (number / this.divisor));
    }

    @Override // android.icu.text.NFSubstitution
    public double transformNumber(double number) {
        if (this.ruleSet == null) {
            return number / ((double) this.divisor);
        }
        return Math.floor(number / ((double) this.divisor));
    }

    @Override // android.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return ((double) this.divisor) * newRuleValue;
    }

    @Override // android.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return (double) this.divisor;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.NFSubstitution
    public char tokenChar() {
        return '<';
    }
}
