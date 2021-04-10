package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import java.util.List;
import javax.annotation.Nullable;

@Beta
@GwtCompatible
public final class InternetDomainName {
    private static final CharMatcher DASH_MATCHER = CharMatcher.anyOf("-_");
    private static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".。．｡");
    private static final Joiner DOT_JOINER = Joiner.on('.');
    private static final String DOT_REGEX = "\\.";
    private static final Splitter DOT_SPLITTER = Splitter.on('.');
    private static final int MAX_DOMAIN_PART_LENGTH = 63;
    private static final int MAX_LENGTH = 253;
    private static final int MAX_PARTS = 127;
    private static final int NO_PUBLIC_SUFFIX_FOUND = -1;
    private static final CharMatcher PART_CHAR_MATCHER = CharMatcher.JAVA_LETTER_OR_DIGIT.or(DASH_MATCHER);
    private final String name;
    private final ImmutableList<String> parts;
    private final int publicSuffixIndex;

    InternetDomainName(String name2) {
        String name3 = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom((CharSequence) name2, '.'));
        name3 = name3.endsWith(".") ? name3.substring(0, name3.length() - 1) : name3;
        Preconditions.checkArgument(name3.length() <= MAX_LENGTH, "Domain name too long: '%s':", name3);
        this.name = name3;
        this.parts = ImmutableList.copyOf(DOT_SPLITTER.split(name3));
        Preconditions.checkArgument(this.parts.size() <= MAX_PARTS, "Domain has too many parts: '%s'", name3);
        Preconditions.checkArgument(validateSyntax(this.parts), "Not a valid domain name: '%s'", name3);
        this.publicSuffixIndex = findPublicSuffix();
    }

    private int findPublicSuffix() {
        int partsSize = this.parts.size();
        for (int i = 0; i < partsSize; i++) {
            String ancestorName = DOT_JOINER.join(this.parts.subList(i, partsSize));
            if (PublicSuffixPatterns.EXACT.containsKey(ancestorName)) {
                return i;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(ancestorName)) {
                return i + 1;
            }
            if (matchesWildcardPublicSuffix(ancestorName)) {
                return i;
            }
        }
        return -1;
    }

    public static InternetDomainName from(String domain) {
        return new InternetDomainName((String) Preconditions.checkNotNull(domain));
    }

    private static boolean validateSyntax(List<String> parts2) {
        int lastIndex = parts2.size() - 1;
        if (!validatePart(parts2.get(lastIndex), true)) {
            return false;
        }
        for (int i = 0; i < lastIndex; i++) {
            if (!validatePart(parts2.get(i), false)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validatePart(String part, boolean isFinalPart) {
        if (part.length() < 1 || part.length() > MAX_DOMAIN_PART_LENGTH) {
            return false;
        }
        if (PART_CHAR_MATCHER.matchesAllOf(CharMatcher.ASCII.retainFrom(part)) && !DASH_MATCHER.matches(part.charAt(0)) && !DASH_MATCHER.matches(part.charAt(part.length() - 1))) {
            return !isFinalPart || !CharMatcher.DIGIT.matches(part.charAt(0));
        }
        return false;
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    public boolean isPublicSuffix() {
        return this.publicSuffixIndex == 0;
    }

    public boolean hasPublicSuffix() {
        return this.publicSuffixIndex != -1;
    }

    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return ancestor(this.publicSuffixIndex);
        }
        return null;
    }

    public boolean isUnderPublicSuffix() {
        return this.publicSuffixIndex > 0;
    }

    public boolean isTopPrivateDomain() {
        return this.publicSuffixIndex == 1;
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", this.name);
        return ancestor(this.publicSuffixIndex - 1);
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", this.name);
        return ancestor(1);
    }

    private InternetDomainName ancestor(int levels) {
        Joiner joiner = DOT_JOINER;
        ImmutableList<String> immutableList = this.parts;
        return from(joiner.join(immutableList.subList(levels, immutableList.size())));
    }

    public InternetDomainName child(String leftParts) {
        return from(((String) Preconditions.checkNotNull(leftParts)) + "." + this.name);
    }

    public static boolean isValid(String name2) {
        try {
            from(name2);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean matchesWildcardPublicSuffix(String domain) {
        String[] pieces = domain.split(DOT_REGEX, 2);
        return pieces.length == 2 && PublicSuffixPatterns.UNDER.containsKey(pieces[1]);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof InternetDomainName) {
            return this.name.equals(((InternetDomainName) object).name);
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}
