package android.icu.impl.number.parse;

public class RequireCurrencyValidator extends ValidationMatcher {
    public String toString() {
        return "<RequireCurrency>";
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
        if (parsedNumber.currencyCode == null) {
            parsedNumber.flags |= 256;
        }
    }
}
