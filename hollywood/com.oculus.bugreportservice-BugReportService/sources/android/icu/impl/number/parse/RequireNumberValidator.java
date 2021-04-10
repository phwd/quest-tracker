package android.icu.impl.number.parse;

public class RequireNumberValidator extends ValidationMatcher {
    public String toString() {
        return "<RequireNumber>";
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
        if (!parsedNumber.seenNumber()) {
            parsedNumber.flags |= 256;
        }
    }
}
