package android.icu.impl.number.parse;

import android.icu.impl.StandardPlural;
import android.icu.impl.StringSegment;
import android.icu.impl.number.AffixPatternProvider;
import android.icu.impl.number.AffixUtils;
import android.icu.impl.number.Padder;
import android.icu.impl.number.PatternStringUtils;
import android.icu.number.NumberFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class AffixMatcher implements NumberParseMatcher {
    public static final Comparator<AffixMatcher> COMPARATOR = new Comparator<AffixMatcher>() {
        /* class android.icu.impl.number.parse.AffixMatcher.AnonymousClass1 */

        public int compare(AffixMatcher lhs, AffixMatcher rhs) {
            if (AffixMatcher.length(lhs.prefix) != AffixMatcher.length(rhs.prefix)) {
                return AffixMatcher.length(lhs.prefix) > AffixMatcher.length(rhs.prefix) ? -1 : 1;
            }
            if (AffixMatcher.length(lhs.suffix) != AffixMatcher.length(rhs.suffix)) {
                return AffixMatcher.length(lhs.suffix) > AffixMatcher.length(rhs.suffix) ? -1 : 1;
            }
            if (!lhs.equals(rhs)) {
                return lhs.hashCode() > rhs.hashCode() ? -1 : 1;
            }
            return 0;
        }
    };
    private final int flags;
    private final AffixPatternMatcher prefix;
    private final AffixPatternMatcher suffix;

    private static boolean isInteresting(AffixPatternProvider patternInfo, IgnorablesMatcher ignorables, int parseFlags) {
        String posPrefixString = patternInfo.getString(256);
        String posSuffixString = patternInfo.getString(0);
        String negPrefixString = null;
        String negSuffixString = null;
        if (patternInfo.hasNegativeSubpattern()) {
            negPrefixString = patternInfo.getString(768);
            negSuffixString = patternInfo.getString(512);
        }
        if ((parseFlags & 256) != 0 || !AffixUtils.containsOnlySymbolsAndIgnorables(posPrefixString, ignorables.getSet()) || !AffixUtils.containsOnlySymbolsAndIgnorables(posSuffixString, ignorables.getSet()) || !AffixUtils.containsOnlySymbolsAndIgnorables(negPrefixString, ignorables.getSet()) || !AffixUtils.containsOnlySymbolsAndIgnorables(negSuffixString, ignorables.getSet()) || AffixUtils.containsType(posSuffixString, -2) || AffixUtils.containsType(posSuffixString, -1) || AffixUtils.containsType(negSuffixString, -2) || AffixUtils.containsType(negSuffixString, -1)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Multiple debug info for r9v5 'posPrefix'  android.icu.impl.number.parse.AffixPatternMatcher: [D('posSuffix' android.icu.impl.number.parse.AffixPatternMatcher), D('posPrefix' android.icu.impl.number.parse.AffixPatternMatcher)] */
    public static void createMatchers(AffixPatternProvider patternInfo, NumberParserImpl output, AffixTokenMatcherFactory factory, IgnorablesMatcher ignorables, int parseFlags) {
        NumberFormatter.SignDisplay signDisplay;
        ArrayList<AffixMatcher> matchers;
        AffixPatternMatcher posPrefix;
        if (isInteresting(patternInfo, ignorables, parseFlags)) {
            StringBuilder sb = new StringBuilder();
            ArrayList<AffixMatcher> matchers2 = new ArrayList<>(6);
            boolean includeUnpaired = (parseFlags & 128) != 0;
            if ((parseFlags & 1024) != 0) {
                signDisplay = NumberFormatter.SignDisplay.ALWAYS;
            } else {
                signDisplay = NumberFormatter.SignDisplay.AUTO;
            }
            AffixPatternMatcher posPrefix2 = null;
            AffixPatternMatcher posPrefix3 = null;
            int signum = 1;
            while (signum >= -1) {
                PatternStringUtils.patternInfoToStringBuilder(patternInfo, true, signum, signDisplay, StandardPlural.OTHER, false, sb);
                AffixPatternMatcher prefix2 = AffixPatternMatcher.fromAffixPattern(sb.toString(), factory, parseFlags);
                AffixPatternMatcher posSuffix = posPrefix3;
                PatternStringUtils.patternInfoToStringBuilder(patternInfo, false, signum, signDisplay, StandardPlural.OTHER, false, sb);
                AffixPatternMatcher suffix2 = AffixPatternMatcher.fromAffixPattern(sb.toString(), factory, parseFlags);
                if (signum == 1) {
                    posSuffix = suffix2;
                    posPrefix = prefix2;
                } else {
                    posPrefix = posPrefix2;
                    if (Objects.equals(prefix2, posPrefix) && Objects.equals(suffix2, posSuffix)) {
                        posPrefix2 = posPrefix;
                        matchers = matchers2;
                        posPrefix3 = posSuffix;
                        signum--;
                        matchers2 = matchers;
                    }
                }
                int flags2 = signum == -1 ? 1 : 0;
                matchers = matchers2;
                matchers.add(getInstance(prefix2, suffix2, flags2));
                if (!(!includeUnpaired || prefix2 == null || suffix2 == null)) {
                    if (signum == 1 || !Objects.equals(prefix2, posPrefix)) {
                        matchers.add(getInstance(prefix2, null, flags2));
                    }
                    if (signum == 1 || !Objects.equals(suffix2, posSuffix)) {
                        matchers.add(getInstance(null, suffix2, flags2));
                    }
                }
                posPrefix2 = posPrefix;
                posPrefix3 = posSuffix;
                signum--;
                matchers2 = matchers;
            }
            Collections.sort(matchers2, COMPARATOR);
            output.addMatchers(matchers2);
        }
    }

    private static final AffixMatcher getInstance(AffixPatternMatcher prefix2, AffixPatternMatcher suffix2, int flags2) {
        return new AffixMatcher(prefix2, suffix2, flags2);
    }

    private AffixMatcher(AffixPatternMatcher prefix2, AffixPatternMatcher suffix2, int flags2) {
        this.prefix = prefix2;
        this.suffix = suffix2;
        this.flags = flags2;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        if (!result.seenNumber()) {
            if (result.prefix != null || this.prefix == null) {
                return false;
            }
            int initialOffset = segment.getOffset();
            boolean maybeMore = this.prefix.match(segment, result);
            if (initialOffset != segment.getOffset()) {
                result.prefix = this.prefix.getPattern();
            }
            return maybeMore;
        } else if (result.suffix != null || this.suffix == null || !matched(this.prefix, result.prefix)) {
            return false;
        } else {
            int initialOffset2 = segment.getOffset();
            boolean maybeMore2 = this.suffix.match(segment, result);
            if (initialOffset2 != segment.getOffset()) {
                result.suffix = this.suffix.getPattern();
            }
            return maybeMore2;
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        AffixPatternMatcher affixPatternMatcher;
        AffixPatternMatcher affixPatternMatcher2 = this.prefix;
        return (affixPatternMatcher2 != null && affixPatternMatcher2.smokeTest(segment)) || ((affixPatternMatcher = this.suffix) != null && affixPatternMatcher.smokeTest(segment));
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (matched(this.prefix, result.prefix) && matched(this.suffix, result.suffix)) {
            if (result.prefix == null) {
                result.prefix = "";
            }
            if (result.suffix == null) {
                result.suffix = "";
            }
            result.flags |= this.flags;
            AffixPatternMatcher affixPatternMatcher = this.prefix;
            if (affixPatternMatcher != null) {
                affixPatternMatcher.postProcess(result);
            }
            AffixPatternMatcher affixPatternMatcher2 = this.suffix;
            if (affixPatternMatcher2 != null) {
                affixPatternMatcher2.postProcess(result);
            }
        }
    }

    static boolean matched(AffixPatternMatcher affix, String patternString) {
        return (affix == null && patternString == null) || (affix != null && affix.getPattern().equals(patternString));
    }

    /* access modifiers changed from: private */
    public static int length(AffixPatternMatcher matcher) {
        if (matcher == null) {
            return 0;
        }
        return matcher.getPattern().length();
    }

    public boolean equals(Object _other) {
        if (!(_other instanceof AffixMatcher)) {
            return false;
        }
        AffixMatcher other = (AffixMatcher) _other;
        if (!Objects.equals(this.prefix, other.prefix) || !Objects.equals(this.suffix, other.suffix) || this.flags != other.flags) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Objects.hashCode(this.prefix) ^ Objects.hashCode(this.suffix)) ^ this.flags;
    }

    public String toString() {
        boolean isNegative = true;
        if ((this.flags & 1) == 0) {
            isNegative = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<AffixMatcher");
        sb.append(isNegative ? ":negative " : Padder.FALLBACK_PADDING_STRING);
        sb.append((Object) this.prefix);
        sb.append("#");
        sb.append((Object) this.suffix);
        sb.append(">");
        return sb.toString();
    }
}
