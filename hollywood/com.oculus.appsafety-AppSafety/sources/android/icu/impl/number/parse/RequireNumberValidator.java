package android.icu.impl.number.parse;

public class RequireNumberValidator extends ValidationMatcher {
    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (!result.seenNumber()) {
            result.flags |= 256;
        }
    }

    public String toString() {
        return "<RequireNumber>";
    }
}
