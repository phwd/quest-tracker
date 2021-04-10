package android.icu.impl.number.parse;

public class RequireCurrencyValidator extends ValidationMatcher {
    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (result.currencyCode == null) {
            result.flags |= 256;
        }
    }

    public String toString() {
        return "<RequireCurrency>";
    }
}
