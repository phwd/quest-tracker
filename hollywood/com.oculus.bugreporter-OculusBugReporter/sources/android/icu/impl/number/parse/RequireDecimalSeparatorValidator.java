package android.icu.impl.number.parse;

public class RequireDecimalSeparatorValidator extends ValidationMatcher {
    private static final RequireDecimalSeparatorValidator A = new RequireDecimalSeparatorValidator(true);
    private static final RequireDecimalSeparatorValidator B = new RequireDecimalSeparatorValidator(false);
    private final boolean patternHasDecimalSeparator;

    public static RequireDecimalSeparatorValidator getInstance(boolean patternHasDecimalSeparator2) {
        return patternHasDecimalSeparator2 ? A : B;
    }

    private RequireDecimalSeparatorValidator(boolean patternHasDecimalSeparator2) {
        this.patternHasDecimalSeparator = patternHasDecimalSeparator2;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (((result.flags & 32) != 0) != this.patternHasDecimalSeparator) {
            result.flags |= 256;
        }
    }

    public String toString() {
        return "<RequireDecimalSeparator>";
    }
}
