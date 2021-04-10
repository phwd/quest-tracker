package android.icu.impl.number.parse;

public class RequireAffixValidator extends ValidationMatcher {
    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (result.prefix == null || result.suffix == null) {
            result.flags |= 256;
        }
    }

    public String toString() {
        return "<RequireAffix>";
    }
}
