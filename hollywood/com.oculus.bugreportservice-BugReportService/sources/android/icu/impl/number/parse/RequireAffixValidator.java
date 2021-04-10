package android.icu.impl.number.parse;

public class RequireAffixValidator extends ValidationMatcher {
    public String toString() {
        return "<RequireAffix>";
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
        if (parsedNumber.prefix == null || parsedNumber.suffix == null) {
            parsedNumber.flags |= 256;
        }
    }
}
