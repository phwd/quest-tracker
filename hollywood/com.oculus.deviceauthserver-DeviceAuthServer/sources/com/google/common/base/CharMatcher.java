package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.BitSet;
import javax.annotation.CheckReturnValue;

@Beta
@GwtCompatible(emulated = true)
public abstract class CharMatcher implements Predicate<Character> {
    public static final CharMatcher ANY = new FastMatcher("CharMatcher.ANY") {
        /* class com.google.common.base.CharMatcher.AnonymousClass7 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence) {
            return sequence.length() == 0 ? -1 : 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence, int start) {
            int length = sequence.length();
            Preconditions.checkPositionIndex(start, length);
            if (start == length) {
                return -1;
            }
            return start;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence sequence) {
            return sequence.length() - 1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence sequence) {
            return sequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, char replacement) {
            char[] array = new char[sequence.length()];
            Arrays.fill(array, replacement);
            return new String(array);
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, CharSequence replacement) {
            StringBuilder retval = new StringBuilder(sequence.length() * replacement.length());
            for (int i = 0; i < sequence.length(); i++) {
                retval.append(replacement);
            }
            return retval.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence sequence, char replacement) {
            return sequence.length() == 0 ? "" : String.valueOf(replacement);
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence sequence) {
            return sequence.length();
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher other) {
            return (CharMatcher) Preconditions.checkNotNull(other);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher other) {
            Preconditions.checkNotNull(other);
            return this;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return NONE;
        }
    };
    public static final CharMatcher ASCII = inRange(0, Ascii.MAX, "CharMatcher.ASCII");
    public static final CharMatcher BREAKING_WHITESPACE = new CharMatcher() {
        /* class com.google.common.base.CharMatcher.AnonymousClass1 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            if (!(c == ' ' || c == 133 || c == 5760)) {
                if (c == 8199) {
                    return false;
                }
                if (!(c == 8287 || c == 12288 || c == 8232 || c == 8233)) {
                    switch (c) {
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                            break;
                        default:
                            return c >= 8192 && c <= 8202;
                    }
                }
            }
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.BREAKING_WHITESPACE";
        }
    };
    public static final CharMatcher DIGIT = new RangesMatcher("CharMatcher.DIGIT", ZEROES.toCharArray(), NINES.toCharArray());
    private static final int DISTINCT_CHARS = 65536;
    public static final CharMatcher INVISIBLE = new RangesMatcher("CharMatcher.INVISIBLE", "\u0000­؀؜۝܏ ᠎   ⁦⁧⁨⁩⁪　?﻿￹￺".toCharArray(), "  ­؄؜۝܏ ᠎‏ ⁤⁦⁧⁨⁩⁯　﻿￹￻".toCharArray());
    public static final CharMatcher JAVA_DIGIT = new CharMatcher("CharMatcher.JAVA_DIGIT") {
        /* class com.google.common.base.CharMatcher.AnonymousClass2 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isDigit(c);
        }
    };
    public static final CharMatcher JAVA_ISO_CONTROL = inRange(0, 31).or(inRange(Ascii.MAX, 159)).withToString("CharMatcher.JAVA_ISO_CONTROL");
    public static final CharMatcher JAVA_LETTER = new CharMatcher("CharMatcher.JAVA_LETTER") {
        /* class com.google.common.base.CharMatcher.AnonymousClass3 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLetter(c);
        }
    };
    public static final CharMatcher JAVA_LETTER_OR_DIGIT = new CharMatcher("CharMatcher.JAVA_LETTER_OR_DIGIT") {
        /* class com.google.common.base.CharMatcher.AnonymousClass4 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLetterOrDigit(c);
        }
    };
    public static final CharMatcher JAVA_LOWER_CASE = new CharMatcher("CharMatcher.JAVA_LOWER_CASE") {
        /* class com.google.common.base.CharMatcher.AnonymousClass6 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLowerCase(c);
        }
    };
    public static final CharMatcher JAVA_UPPER_CASE = new CharMatcher("CharMatcher.JAVA_UPPER_CASE") {
        /* class com.google.common.base.CharMatcher.AnonymousClass5 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isUpperCase(c);
        }
    };
    private static final String NINES;
    public static final CharMatcher NONE = new FastMatcher("CharMatcher.NONE") {
        /* class com.google.common.base.CharMatcher.AnonymousClass8 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return false;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence, int start) {
            Preconditions.checkPositionIndex(start, sequence.length());
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence sequence) {
            return sequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, char replacement) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, CharSequence replacement) {
            Preconditions.checkNotNull(replacement);
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence sequence, char replacement) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimLeadingFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimTrailingFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return 0;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher other) {
            Preconditions.checkNotNull(other);
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher other) {
            return (CharMatcher) Preconditions.checkNotNull(other);
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return ANY;
        }
    };
    public static final CharMatcher SINGLE_WIDTH = new RangesMatcher("CharMatcher.SINGLE_WIDTH", "\u0000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
    public static final CharMatcher WHITESPACE = new FastMatcher("WHITESPACE") {
        /* class com.google.common.base.CharMatcher.AnonymousClass15 */

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return CharMatcher.WHITESPACE_TABLE.charAt((CharMatcher.WHITESPACE_MULTIPLIER * c) >>> WHITESPACE_SHIFT) == c;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible("java.util.BitSet")
        public void setBits(BitSet table) {
            for (int i = 0; i < CharMatcher.WHITESPACE_TABLE.length(); i++) {
                table.set(CharMatcher.WHITESPACE_TABLE.charAt(i));
            }
        }
    };
    static final int WHITESPACE_MULTIPLIER = 1682554634;
    static final int WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(WHITESPACE_TABLE.length() - 1);
    static final String WHITESPACE_TABLE = " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　";
    private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";
    final String description;

    public abstract boolean matches(char c);

    static {
        StringBuilder builder = new StringBuilder(ZEROES.length());
        for (int i = 0; i < ZEROES.length(); i++) {
            builder.append((char) (ZEROES.charAt(i) + '\t'));
        }
        NINES = builder.toString();
    }

    private static class RangesMatcher extends CharMatcher {
        private final char[] rangeEnds;
        private final char[] rangeStarts;

        RangesMatcher(String description, char[] rangeStarts2, char[] rangeEnds2) {
            super(description);
            this.rangeStarts = rangeStarts2;
            this.rangeEnds = rangeEnds2;
            Preconditions.checkArgument(rangeStarts2.length == rangeEnds2.length);
            for (int i = 0; i < rangeStarts2.length; i++) {
                Preconditions.checkArgument(rangeStarts2[i] <= rangeEnds2[i]);
                if (i + 1 < rangeStarts2.length) {
                    Preconditions.checkArgument(rangeEnds2[i] < rangeStarts2[i + 1]);
                }
            }
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            int index = Arrays.binarySearch(this.rangeStarts, c);
            if (index >= 0) {
                return true;
            }
            int index2 = (~index) - 1;
            if (index2 < 0 || c > this.rangeEnds[index2]) {
                return false;
            }
            return true;
        }
    }

    private static String showCharacter(char c) {
        char[] tmp = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            tmp[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(tmp);
    }

    public static CharMatcher is(final char match) {
        return new FastMatcher("CharMatcher.is('" + showCharacter(match) + "')") {
            /* class com.google.common.base.CharMatcher.AnonymousClass9 */

            @Override // com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return c == match;
            }

            @Override // com.google.common.base.CharMatcher
            public String replaceFrom(CharSequence sequence, char replacement) {
                return sequence.toString().replace(match, replacement);
            }

            @Override // com.google.common.base.CharMatcher
            public CharMatcher and(CharMatcher other) {
                return other.matches(match) ? this : NONE;
            }

            @Override // com.google.common.base.CharMatcher
            public CharMatcher or(CharMatcher other) {
                return other.matches(match) ? other : super.or(other);
            }

            @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
            public CharMatcher negate() {
                return isNot(match);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.CharMatcher
            @GwtIncompatible("java.util.BitSet")
            public void setBits(BitSet table) {
                table.set(match);
            }
        };
    }

    public static CharMatcher isNot(final char match) {
        return new FastMatcher("CharMatcher.isNot('" + showCharacter(match) + "')") {
            /* class com.google.common.base.CharMatcher.AnonymousClass10 */

            @Override // com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return c != match;
            }

            @Override // com.google.common.base.CharMatcher
            public CharMatcher and(CharMatcher other) {
                return other.matches(match) ? super.and(other) : other;
            }

            @Override // com.google.common.base.CharMatcher
            public CharMatcher or(CharMatcher other) {
                return other.matches(match) ? ANY : this;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.CharMatcher
            @GwtIncompatible("java.util.BitSet")
            public void setBits(BitSet table) {
                table.set(0, match);
                table.set(match + 1, CharMatcher.DISTINCT_CHARS);
            }

            @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
            public CharMatcher negate() {
                return is(match);
            }
        };
    }

    public static CharMatcher anyOf(CharSequence sequence) {
        int length = sequence.length();
        if (length == 0) {
            return NONE;
        }
        if (length == 1) {
            return is(sequence.charAt(0));
        }
        if (length == 2) {
            return isEither(sequence.charAt(0), sequence.charAt(1));
        }
        final char[] chars = sequence.toString().toCharArray();
        Arrays.sort(chars);
        StringBuilder description2 = new StringBuilder("CharMatcher.anyOf(\"");
        for (char c : chars) {
            description2.append(showCharacter(c));
        }
        description2.append("\")");
        return new CharMatcher(description2.toString()) {
            /* class com.google.common.base.CharMatcher.AnonymousClass11 */

            @Override // com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return Arrays.binarySearch(chars, c) >= 0;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.CharMatcher
            @GwtIncompatible("java.util.BitSet")
            public void setBits(BitSet table) {
                for (char c : chars) {
                    table.set(c);
                }
            }
        };
    }

    private static CharMatcher isEither(final char match1, final char match2) {
        return new FastMatcher("CharMatcher.anyOf(\"" + showCharacter(match1) + showCharacter(match2) + "\")") {
            /* class com.google.common.base.CharMatcher.AnonymousClass12 */

            @Override // com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return c == match1 || c == match2;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.CharMatcher
            @GwtIncompatible("java.util.BitSet")
            public void setBits(BitSet table) {
                table.set(match1);
                table.set(match2);
            }
        };
    }

    public static CharMatcher noneOf(CharSequence sequence) {
        return anyOf(sequence).negate();
    }

    public static CharMatcher inRange(char startInclusive, char endInclusive) {
        Preconditions.checkArgument(endInclusive >= startInclusive);
        return inRange(startInclusive, endInclusive, "CharMatcher.inRange('" + showCharacter(startInclusive) + "', '" + showCharacter(endInclusive) + "')");
    }

    static CharMatcher inRange(final char startInclusive, final char endInclusive, String description2) {
        return new FastMatcher(description2) {
            /* class com.google.common.base.CharMatcher.AnonymousClass13 */

            @Override // com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return startInclusive <= c && c <= endInclusive;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.base.CharMatcher
            @GwtIncompatible("java.util.BitSet")
            public void setBits(BitSet table) {
                table.set(startInclusive, endInclusive + 1);
            }
        };
    }

    public static CharMatcher forPredicate(final Predicate<? super Character> predicate) {
        Preconditions.checkNotNull(predicate);
        if (predicate instanceof CharMatcher) {
            return (CharMatcher) predicate;
        }
        return new CharMatcher("CharMatcher.forPredicate(" + predicate + ")") {
            /* class com.google.common.base.CharMatcher.AnonymousClass14 */

            @Override // com.google.common.base.CharMatcher
            public boolean matches(char c) {
                return predicate.apply(Character.valueOf(c));
            }

            @Override // com.google.common.base.CharMatcher
            public boolean apply(Character character) {
                return predicate.apply(Preconditions.checkNotNull(character));
            }
        };
    }

    CharMatcher(String description2) {
        this.description = description2;
    }

    protected CharMatcher() {
        this.description = super.toString();
    }

    public CharMatcher negate() {
        return new NegatedMatcher(this);
    }

    /* access modifiers changed from: private */
    public static class NegatedMatcher extends CharMatcher {
        final CharMatcher original;

        NegatedMatcher(String toString, CharMatcher original2) {
            super(toString);
            this.original = original2;
        }

        NegatedMatcher(CharMatcher original2) {
            this(original2 + ".negate()", original2);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return !this.original.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence sequence) {
            return this.original.matchesNoneOf(sequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence sequence) {
            return this.original.matchesAllOf(sequence);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence sequence) {
            return sequence.length() - this.original.countIn(sequence);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible("java.util.BitSet")
        public void setBits(BitSet table) {
            BitSet tmp = new BitSet();
            this.original.setBits(tmp);
            tmp.flip(0, CharMatcher.DISTINCT_CHARS);
            table.or(tmp);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.original;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        public CharMatcher withToString(String description) {
            return new NegatedMatcher(description, this.original);
        }
    }

    public CharMatcher and(CharMatcher other) {
        return new And(this, (CharMatcher) Preconditions.checkNotNull(other));
    }

    /* access modifiers changed from: private */
    public static class And extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        And(CharMatcher a, CharMatcher b) {
            this(a, b, "CharMatcher.and(" + a + ", " + b + ")");
        }

        And(CharMatcher a, CharMatcher b, String description) {
            super(description);
            this.first = (CharMatcher) Preconditions.checkNotNull(a);
            this.second = (CharMatcher) Preconditions.checkNotNull(b);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) && this.second.matches(c);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible("java.util.BitSet")
        public void setBits(BitSet table) {
            BitSet tmp1 = new BitSet();
            this.first.setBits(tmp1);
            BitSet tmp2 = new BitSet();
            this.second.setBits(tmp2);
            tmp1.and(tmp2);
            table.or(tmp1);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        public CharMatcher withToString(String description) {
            return new And(this.first, this.second, description);
        }
    }

    public CharMatcher or(CharMatcher other) {
        return new Or(this, (CharMatcher) Preconditions.checkNotNull(other));
    }

    /* access modifiers changed from: private */
    public static class Or extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        Or(CharMatcher a, CharMatcher b, String description) {
            super(description);
            this.first = (CharMatcher) Preconditions.checkNotNull(a);
            this.second = (CharMatcher) Preconditions.checkNotNull(b);
        }

        Or(CharMatcher a, CharMatcher b) {
            this(a, b, "CharMatcher.or(" + a + ", " + b + ")");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible("java.util.BitSet")
        public void setBits(BitSet table) {
            this.first.setBits(table);
            this.second.setBits(table);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) || this.second.matches(c);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        public CharMatcher withToString(String description) {
            return new Or(this.first, this.second, description);
        }
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    /* access modifiers changed from: package-private */
    public CharMatcher withToString(String description2) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible("java.util.BitSet")
    public CharMatcher precomputedInternal() {
        String negatedDescription;
        BitSet table = new BitSet();
        setBits(table);
        int totalCharacters = table.cardinality();
        if (totalCharacters * 2 <= DISTINCT_CHARS) {
            return precomputedPositive(totalCharacters, table, this.description);
        }
        table.flip(0, DISTINCT_CHARS);
        int negatedCharacters = DISTINCT_CHARS - totalCharacters;
        if (this.description.endsWith(".negate()")) {
            String str = this.description;
            negatedDescription = str.substring(0, str.length() - ".negate()".length());
        } else {
            negatedDescription = this.description + ".negate()";
        }
        return new NegatedFastMatcher(toString(), precomputedPositive(negatedCharacters, table, negatedDescription));
    }

    static abstract class FastMatcher extends CharMatcher {
        FastMatcher() {
        }

        FastMatcher(String description) {
            super(description);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }
    }

    static final class NegatedFastMatcher extends NegatedMatcher {
        NegatedFastMatcher(CharMatcher original) {
            super(original);
        }

        NegatedFastMatcher(String toString, CharMatcher original) {
            super(toString, original);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher, com.google.common.base.CharMatcher.NegatedMatcher
        public CharMatcher withToString(String description) {
            return new NegatedFastMatcher(description, this.original);
        }
    }

    @GwtIncompatible("java.util.BitSet")
    private static CharMatcher precomputedPositive(int totalCharacters, BitSet table, String description2) {
        if (totalCharacters == 0) {
            return NONE;
        }
        if (totalCharacters == 1) {
            return is((char) table.nextSetBit(0));
        }
        if (totalCharacters == 2) {
            char c1 = (char) table.nextSetBit(0);
            return isEither(c1, (char) table.nextSetBit(c1 + 1));
        } else if (isSmall(totalCharacters, table.length())) {
            return SmallCharMatcher.from(table, description2);
        } else {
            return new BitSetMatcher(table, description2);
        }
    }

    @GwtIncompatible("SmallCharMatcher")
    private static boolean isSmall(int totalCharacters, int tableLength) {
        return totalCharacters <= 1023 && tableLength > (totalCharacters * 4) * 16;
    }

    /* access modifiers changed from: private */
    @GwtIncompatible("java.util.BitSet")
    public static class BitSetMatcher extends FastMatcher {
        private final BitSet table;

        private BitSetMatcher(BitSet table2, String description) {
            super(description);
            this.table = table2.length() + 64 < table2.size() ? (BitSet) table2.clone() : table2;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.table.get(c);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.CharMatcher
        public void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible("java.util.BitSet")
    public void setBits(BitSet table) {
        for (int c = 65535; c >= 0; c--) {
            if (matches((char) c)) {
                table.set(c);
            }
        }
    }

    public boolean matchesAnyOf(CharSequence sequence) {
        return !matchesNoneOf(sequence);
    }

    public boolean matchesAllOf(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (!matches(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence sequence) {
        return indexIn(sequence) == -1;
    }

    public int indexIn(CharSequence sequence) {
        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int indexIn(CharSequence sequence, int start) {
        int length = sequence.length();
        Preconditions.checkPositionIndex(start, length);
        for (int i = start; i < length; i++) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexIn(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int countIn(CharSequence sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (matches(sequence.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    @CheckReturnValue
    public String removeFrom(CharSequence sequence) {
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        int spread = 1;
        while (true) {
            while (true) {
                pos++;
                if (pos == chars.length) {
                    return new String(chars, 0, pos - spread);
                }
                if (matches(chars[pos])) {
                    break;
                }
                chars[pos - spread] = chars[pos];
            }
            spread++;
        }
    }

    @CheckReturnValue
    public String retainFrom(CharSequence sequence) {
        return negate().removeFrom(sequence);
    }

    @CheckReturnValue
    public String replaceFrom(CharSequence sequence, char replacement) {
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        chars[pos] = replacement;
        for (int i = pos + 1; i < chars.length; i++) {
            if (matches(chars[i])) {
                chars[i] = replacement;
            }
        }
        return new String(chars);
    }

    @CheckReturnValue
    public String replaceFrom(CharSequence sequence, CharSequence replacement) {
        int replacementLen = replacement.length();
        if (replacementLen == 0) {
            return removeFrom(sequence);
        }
        if (replacementLen == 1) {
            return replaceFrom(sequence, replacement.charAt(0));
        }
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        int len = string.length();
        StringBuilder buf = new StringBuilder(((len * 3) / 2) + 16);
        int oldpos = 0;
        do {
            buf.append((CharSequence) string, oldpos, pos);
            buf.append(replacement);
            oldpos = pos + 1;
            pos = indexIn(string, oldpos);
        } while (pos != -1);
        buf.append((CharSequence) string, oldpos, len);
        return buf.toString();
    }

    @CheckReturnValue
    public String trimFrom(CharSequence sequence) {
        int len = sequence.length();
        int first = 0;
        while (first < len && matches(sequence.charAt(first))) {
            first++;
        }
        int last = len - 1;
        while (last > first && matches(sequence.charAt(last))) {
            last--;
        }
        return sequence.subSequence(first, last + 1).toString();
    }

    @CheckReturnValue
    public String trimLeadingFrom(CharSequence sequence) {
        int len = sequence.length();
        for (int first = 0; first < len; first++) {
            if (!matches(sequence.charAt(first))) {
                return sequence.subSequence(first, len).toString();
            }
        }
        return "";
    }

    @CheckReturnValue
    public String trimTrailingFrom(CharSequence sequence) {
        for (int last = sequence.length() - 1; last >= 0; last--) {
            if (!matches(sequence.charAt(last))) {
                return sequence.subSequence(0, last + 1).toString();
            }
        }
        return "";
    }

    @CheckReturnValue
    public String collapseFrom(CharSequence sequence, char replacement) {
        int len = sequence.length();
        int i = 0;
        while (i < len) {
            char c = sequence.charAt(i);
            if (matches(c)) {
                if (c != replacement || (i != len - 1 && matches(sequence.charAt(i + 1)))) {
                    StringBuilder sb = new StringBuilder(len);
                    sb.append(sequence.subSequence(0, i));
                    return finishCollapseFrom(sequence, i + 1, len, replacement, sb.append(replacement), true);
                }
                i++;
            }
            i++;
        }
        return sequence.toString();
    }

    @CheckReturnValue
    public String trimAndCollapseFrom(CharSequence sequence, char replacement) {
        int len = sequence.length();
        int first = 0;
        while (first < len && matches(sequence.charAt(first))) {
            first++;
        }
        int last = len - 1;
        while (last > first && matches(sequence.charAt(last))) {
            last--;
        }
        if (first == 0 && last == len - 1) {
            return collapseFrom(sequence, replacement);
        }
        return finishCollapseFrom(sequence, first, last + 1, replacement, new StringBuilder((last + 1) - first), false);
    }

    private String finishCollapseFrom(CharSequence sequence, int start, int end, char replacement, StringBuilder builder, boolean inMatchingGroup) {
        for (int i = start; i < end; i++) {
            char c = sequence.charAt(i);
            if (!matches(c)) {
                builder.append(c);
                inMatchingGroup = false;
            } else if (!inMatchingGroup) {
                builder.append(replacement);
                inMatchingGroup = true;
            }
        }
        return builder.toString();
    }

    @Deprecated
    public boolean apply(Character character) {
        return matches(character.charValue());
    }

    public String toString() {
        return this.description;
    }
}
