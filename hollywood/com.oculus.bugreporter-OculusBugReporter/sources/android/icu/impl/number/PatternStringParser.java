package android.icu.impl.number;

import android.icu.impl.coll.Collation;
import android.icu.impl.number.Padder;

public class PatternStringParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int IGNORE_ROUNDING_ALWAYS = 2;
    public static final int IGNORE_ROUNDING_IF_CURRENCY = 1;
    public static final int IGNORE_ROUNDING_NEVER = 0;

    public static class ParsedSubpatternInfo {
        public boolean exponentHasPlusSign = false;
        public int exponentZeros = 0;
        public int fractionHashSigns = 0;
        public int fractionNumerals = 0;
        public int fractionTotal = 0;
        public long groupingSizes = 281474976645120L;
        public boolean hasCurrencySign = false;
        public boolean hasDecimal = false;
        public boolean hasMinusSign = false;
        public boolean hasPerMilleSign = false;
        public boolean hasPercentSign = false;
        public boolean hasPlusSign = false;
        public int integerAtSigns = 0;
        public int integerLeadingHashSigns = 0;
        public int integerNumerals = 0;
        public int integerTotal = 0;
        public int integerTrailingHashSigns = 0;
        public long paddingEndpoints = 0;
        public Padder.PadPosition paddingLocation = null;
        public long prefixEndpoints = 0;
        public DecimalQuantity_DualStorageBCD rounding = null;
        public long suffixEndpoints = 0;
        public int widthExceptAffixes = 0;
    }

    public static ParsedPatternInfo parseToPatternInfo(String patternString) {
        ParserState state = new ParserState(patternString);
        ParsedPatternInfo result = new ParsedPatternInfo(patternString);
        consumePattern(state, result);
        return result;
    }

    public static DecimalFormatProperties parseToProperties(String pattern, int ignoreRounding) {
        DecimalFormatProperties properties = new DecimalFormatProperties();
        parseToExistingPropertiesImpl(pattern, properties, ignoreRounding);
        return properties;
    }

    public static DecimalFormatProperties parseToProperties(String pattern) {
        return parseToProperties(pattern, 0);
    }

    public static void parseToExistingProperties(String pattern, DecimalFormatProperties properties, int ignoreRounding) {
        parseToExistingPropertiesImpl(pattern, properties, ignoreRounding);
    }

    public static void parseToExistingProperties(String pattern, DecimalFormatProperties properties) {
        parseToExistingProperties(pattern, properties, 0);
    }

    public static class ParsedPatternInfo implements AffixPatternProvider {
        public ParsedSubpatternInfo negative;
        public String pattern;
        public ParsedSubpatternInfo positive;

        private ParsedPatternInfo(String pattern2) {
            this.pattern = pattern2;
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public char charAt(int flags, int index) {
            long endpoints = getEndpoints(flags);
            int left = (int) (-1 & endpoints);
            int right = (int) (endpoints >>> 32);
            if (index >= 0 && index < right - left) {
                return this.pattern.charAt(left + index);
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public int length(int flags) {
            return getLengthFromEndpoints(getEndpoints(flags));
        }

        public static int getLengthFromEndpoints(long endpoints) {
            return ((int) (endpoints >>> 32)) - ((int) (-1 & endpoints));
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public String getString(int flags) {
            long endpoints = getEndpoints(flags);
            int left = (int) (-1 & endpoints);
            int right = (int) (endpoints >>> 32);
            if (left == right) {
                return "";
            }
            return this.pattern.substring(left, right);
        }

        private long getEndpoints(int flags) {
            boolean padding = true;
            boolean prefix = (flags & 256) != 0;
            boolean isNegative = (flags & 512) != 0;
            if ((flags & 1024) == 0) {
                padding = false;
            }
            if (isNegative && padding) {
                return this.negative.paddingEndpoints;
            }
            if (padding) {
                return this.positive.paddingEndpoints;
            }
            if (prefix && isNegative) {
                return this.negative.prefixEndpoints;
            }
            if (prefix) {
                return this.positive.prefixEndpoints;
            }
            if (isNegative) {
                return this.negative.suffixEndpoints;
            }
            return this.positive.suffixEndpoints;
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public boolean positiveHasPlusSign() {
            return this.positive.hasPlusSign;
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public boolean hasNegativeSubpattern() {
            return this.negative != null;
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public boolean negativeHasMinusSign() {
            return this.negative.hasMinusSign;
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public boolean hasCurrencySign() {
            ParsedSubpatternInfo parsedSubpatternInfo;
            return this.positive.hasCurrencySign || ((parsedSubpatternInfo = this.negative) != null && parsedSubpatternInfo.hasCurrencySign);
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public boolean containsSymbolType(int type) {
            return AffixUtils.containsType(this.pattern, type);
        }

        @Override // android.icu.impl.number.AffixPatternProvider
        public boolean hasBody() {
            return this.positive.integerTotal > 0;
        }
    }

    /* access modifiers changed from: private */
    public static class ParserState {
        int offset = 0;
        final String pattern;

        ParserState(String pattern2) {
            this.pattern = pattern2;
        }

        /* access modifiers changed from: package-private */
        public int peek() {
            if (this.offset == this.pattern.length()) {
                return -1;
            }
            return this.pattern.codePointAt(this.offset);
        }

        /* access modifiers changed from: package-private */
        public int next() {
            int codePoint = peek();
            this.offset += Character.charCount(codePoint);
            return codePoint;
        }

        /* access modifiers changed from: package-private */
        public IllegalArgumentException toParseException(String message) {
            return new IllegalArgumentException("Malformed pattern for ICU DecimalFormat: \"" + this.pattern + "\": " + message + " at position " + this.offset);
        }
    }

    private static void consumePattern(ParserState state, ParsedPatternInfo result) {
        result.positive = new ParsedSubpatternInfo();
        consumeSubpattern(state, result.positive);
        if (state.peek() == 59) {
            state.next();
            if (state.peek() != -1) {
                result.negative = new ParsedSubpatternInfo();
                consumeSubpattern(state, result.negative);
            }
        }
        if (state.peek() != -1) {
            throw state.toParseException("Found unquoted special character");
        }
    }

    private static void consumeSubpattern(ParserState state, ParsedSubpatternInfo result) {
        consumePadding(state, result, Padder.PadPosition.BEFORE_PREFIX);
        result.prefixEndpoints = consumeAffix(state, result);
        consumePadding(state, result, Padder.PadPosition.AFTER_PREFIX);
        consumeFormat(state, result);
        consumeExponent(state, result);
        consumePadding(state, result, Padder.PadPosition.BEFORE_SUFFIX);
        result.suffixEndpoints = consumeAffix(state, result);
        consumePadding(state, result, Padder.PadPosition.AFTER_SUFFIX);
    }

    private static void consumePadding(ParserState state, ParsedSubpatternInfo result, Padder.PadPosition paddingLocation) {
        if (state.peek() == 42) {
            if (result.paddingLocation == null) {
                result.paddingLocation = paddingLocation;
                state.next();
                result.paddingEndpoints |= (long) state.offset;
                consumeLiteral(state);
                result.paddingEndpoints |= ((long) state.offset) << 32;
                return;
            }
            throw state.toParseException("Cannot have multiple pad specifiers");
        }
    }

    private static long consumeAffix(ParserState state, ParsedSubpatternInfo result) {
        long endpoints = (long) state.offset;
        while (true) {
            int peek = state.peek();
            if (!(peek == -1 || peek == 35)) {
                if (peek == 37) {
                    result.hasPercentSign = true;
                } else if (!(peek == 59 || peek == 64)) {
                    if (peek == 164) {
                        result.hasCurrencySign = true;
                    } else if (peek != 8240) {
                        switch (peek) {
                            case 42:
                            case 44:
                            case 46:
                                break;
                            case 43:
                                result.hasPlusSign = true;
                                continue;
                            case 45:
                                result.hasMinusSign = true;
                                continue;
                            default:
                                switch (peek) {
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        break;
                                    default:
                                        continue;
                                }
                        }
                    } else {
                        result.hasPerMilleSign = true;
                    }
                }
                consumeLiteral(state);
            }
        }
        return endpoints | (((long) state.offset) << 32);
    }

    private static void consumeLiteral(ParserState state) {
        if (state.peek() == -1) {
            throw state.toParseException("Expected unquoted literal but found EOL");
        } else if (state.peek() == 39) {
            state.next();
            while (state.peek() != 39) {
                if (state.peek() != -1) {
                    state.next();
                } else {
                    throw state.toParseException("Expected quoted literal but found EOL");
                }
            }
            state.next();
        } else {
            state.next();
        }
    }

    private static void consumeFormat(ParserState state, ParsedSubpatternInfo result) {
        consumeIntegerFormat(state, result);
        if (state.peek() == 46) {
            state.next();
            result.hasDecimal = true;
            result.widthExceptAffixes++;
            consumeFractionFormat(state, result);
        }
    }

    private static void consumeIntegerFormat(ParserState state, ParsedSubpatternInfo result) {
        while (true) {
            int peek = state.peek();
            if (peek != 35) {
                if (peek == 44) {
                    result.widthExceptAffixes++;
                    result.groupingSizes <<= 16;
                } else if (peek != 64) {
                    switch (peek) {
                        default:
                            short grouping1 = (short) ((int) (result.groupingSizes & 65535));
                            short grouping2 = (short) ((int) ((result.groupingSizes >>> 16) & 65535));
                            short grouping3 = (short) ((int) (65535 & (result.groupingSizes >>> 32)));
                            if (grouping1 == 0 && grouping2 != -1) {
                                throw state.toParseException("Trailing grouping separator is invalid");
                            } else if (grouping2 == 0 && grouping3 != -1) {
                                throw state.toParseException("Grouping width of zero is invalid");
                            } else {
                                return;
                            }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                            if (result.integerAtSigns <= 0) {
                                result.widthExceptAffixes++;
                                result.groupingSizes++;
                                result.integerNumerals++;
                                result.integerTotal++;
                                if (state.peek() != 48 && result.rounding == null) {
                                    result.rounding = new DecimalQuantity_DualStorageBCD();
                                }
                                if (result.rounding != null) {
                                    result.rounding.appendDigit((byte) (state.peek() - 48), 0, true);
                                    break;
                                } else {
                                    continue;
                                }
                            } else {
                                throw state.toParseException("Cannot mix @ and 0");
                            }
                    }
                } else if (result.integerNumerals > 0) {
                    throw state.toParseException("Cannot mix 0 and @");
                } else if (result.integerTrailingHashSigns <= 0) {
                    result.widthExceptAffixes++;
                    result.groupingSizes++;
                    result.integerAtSigns++;
                    result.integerTotal++;
                } else {
                    throw state.toParseException("Cannot nest # inside of a run of @");
                }
            } else if (result.integerNumerals <= 0) {
                result.widthExceptAffixes++;
                result.groupingSizes++;
                if (result.integerAtSigns > 0) {
                    result.integerTrailingHashSigns++;
                } else {
                    result.integerLeadingHashSigns++;
                }
                result.integerTotal++;
            } else {
                throw state.toParseException("# cannot follow 0 before decimal point");
            }
            state.next();
        }
    }

    private static void consumeFractionFormat(ParserState state, ParsedSubpatternInfo result) {
        int zeroCounter = 0;
        while (true) {
            int peek = state.peek();
            if (peek != 35) {
                switch (peek) {
                    default:
                        return;
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        if (result.fractionHashSigns <= 0) {
                            result.widthExceptAffixes++;
                            result.fractionNumerals++;
                            result.fractionTotal++;
                            if (state.peek() != 48) {
                                if (result.rounding == null) {
                                    result.rounding = new DecimalQuantity_DualStorageBCD();
                                }
                                result.rounding.appendDigit((byte) (state.peek() - 48), zeroCounter, false);
                                zeroCounter = 0;
                                break;
                            } else {
                                zeroCounter++;
                                continue;
                            }
                        } else {
                            throw state.toParseException("0 cannot follow # after decimal point");
                        }
                }
            } else {
                result.widthExceptAffixes++;
                result.fractionHashSigns++;
                result.fractionTotal++;
                zeroCounter++;
            }
            state.next();
        }
    }

    private static void consumeExponent(ParserState state, ParsedSubpatternInfo result) {
        if (state.peek() == 69) {
            if ((result.groupingSizes & Collation.MAX_PRIMARY) == Collation.MAX_PRIMARY) {
                state.next();
                result.widthExceptAffixes++;
                if (state.peek() == 43) {
                    state.next();
                    result.exponentHasPlusSign = true;
                    result.widthExceptAffixes++;
                }
                while (state.peek() == 48) {
                    state.next();
                    result.exponentZeros++;
                    result.widthExceptAffixes++;
                }
                return;
            }
            throw state.toParseException("Cannot have grouping separator in scientific notation");
        }
    }

    private static void parseToExistingPropertiesImpl(String pattern, DecimalFormatProperties properties, int ignoreRounding) {
        if (pattern == null || pattern.length() == 0) {
            properties.clear();
        } else {
            patternInfoToProperties(properties, parseToPatternInfo(pattern), ignoreRounding);
        }
    }

    private static void patternInfoToProperties(DecimalFormatProperties properties, ParsedPatternInfo patternInfo, int _ignoreRounding) {
        boolean ignoreRounding;
        int minFrac;
        int minInt;
        ParsedSubpatternInfo positive = patternInfo.positive;
        if (_ignoreRounding == 0) {
            ignoreRounding = false;
        } else if (_ignoreRounding == 1) {
            ignoreRounding = positive.hasCurrencySign;
        } else {
            ignoreRounding = true;
        }
        short grouping1 = (short) ((int) (positive.groupingSizes & 65535));
        short grouping2 = (short) ((int) ((positive.groupingSizes >>> 16) & 65535));
        short grouping3 = (short) ((int) (65535 & (positive.groupingSizes >>> 32)));
        if (grouping2 != -1) {
            properties.setGroupingSize(grouping1);
            properties.setGroupingUsed(true);
        } else {
            properties.setGroupingSize(-1);
            properties.setGroupingUsed(false);
        }
        if (grouping3 != -1) {
            properties.setSecondaryGroupingSize(grouping2);
        } else {
            properties.setSecondaryGroupingSize(-1);
        }
        if (positive.integerTotal == 0 && positive.fractionTotal > 0) {
            minInt = 0;
            minFrac = Math.max(1, positive.fractionNumerals);
        } else if (positive.integerNumerals == 0 && positive.fractionNumerals == 0) {
            minInt = 1;
            minFrac = 0;
        } else {
            minInt = positive.integerNumerals;
            minFrac = positive.fractionNumerals;
        }
        if (positive.integerAtSigns > 0) {
            properties.setMinimumFractionDigits(-1);
            properties.setMaximumFractionDigits(-1);
            properties.setRoundingIncrement(null);
            properties.setMinimumSignificantDigits(positive.integerAtSigns);
            properties.setMaximumSignificantDigits(positive.integerAtSigns + positive.integerTrailingHashSigns);
        } else if (positive.rounding != null) {
            if (!ignoreRounding) {
                properties.setMinimumFractionDigits(minFrac);
                properties.setMaximumFractionDigits(positive.fractionTotal);
                properties.setRoundingIncrement(positive.rounding.toBigDecimal().setScale(positive.fractionNumerals));
            } else {
                properties.setMinimumFractionDigits(-1);
                properties.setMaximumFractionDigits(-1);
                properties.setRoundingIncrement(null);
            }
            properties.setMinimumSignificantDigits(-1);
            properties.setMaximumSignificantDigits(-1);
        } else {
            if (!ignoreRounding) {
                properties.setMinimumFractionDigits(minFrac);
                properties.setMaximumFractionDigits(positive.fractionTotal);
                properties.setRoundingIncrement(null);
            } else {
                properties.setMinimumFractionDigits(-1);
                properties.setMaximumFractionDigits(-1);
                properties.setRoundingIncrement(null);
            }
            properties.setMinimumSignificantDigits(-1);
            properties.setMaximumSignificantDigits(-1);
        }
        if (!positive.hasDecimal || positive.fractionTotal != 0) {
            properties.setDecimalSeparatorAlwaysShown(false);
        } else {
            properties.setDecimalSeparatorAlwaysShown(true);
        }
        if (positive.exponentZeros > 0) {
            properties.setExponentSignAlwaysShown(positive.exponentHasPlusSign);
            properties.setMinimumExponentDigits(positive.exponentZeros);
            if (positive.integerAtSigns == 0) {
                properties.setMinimumIntegerDigits(positive.integerNumerals);
                properties.setMaximumIntegerDigits(positive.integerTotal);
            } else {
                properties.setMinimumIntegerDigits(1);
                properties.setMaximumIntegerDigits(-1);
            }
        } else {
            properties.setExponentSignAlwaysShown(false);
            properties.setMinimumExponentDigits(-1);
            properties.setMinimumIntegerDigits(minInt);
            properties.setMaximumIntegerDigits(-1);
        }
        String posPrefix = patternInfo.getString(256);
        String posSuffix = patternInfo.getString(0);
        if (positive.paddingLocation != null) {
            properties.setFormatWidth(positive.widthExceptAffixes + AffixUtils.estimateLength(posPrefix) + AffixUtils.estimateLength(posSuffix));
            String rawPaddingString = patternInfo.getString(1024);
            if (rawPaddingString.length() == 1) {
                properties.setPadString(rawPaddingString);
            } else if (rawPaddingString.length() != 2) {
                properties.setPadString(rawPaddingString.substring(1, rawPaddingString.length() - 1));
            } else if (rawPaddingString.charAt(0) == '\'') {
                properties.setPadString("'");
            } else {
                properties.setPadString(rawPaddingString);
            }
            properties.setPadPosition(positive.paddingLocation);
        } else {
            properties.setFormatWidth(-1);
            properties.setPadString(null);
            properties.setPadPosition(null);
        }
        properties.setPositivePrefixPattern(posPrefix);
        properties.setPositiveSuffixPattern(posSuffix);
        if (patternInfo.negative != null) {
            properties.setNegativePrefixPattern(patternInfo.getString(768));
            properties.setNegativeSuffixPattern(patternInfo.getString(512));
        } else {
            properties.setNegativePrefixPattern(null);
            properties.setNegativeSuffixPattern(null);
        }
        if (positive.hasPercentSign) {
            properties.setMagnitudeMultiplier(2);
        } else if (positive.hasPerMilleSign) {
            properties.setMagnitudeMultiplier(3);
        } else {
            properties.setMagnitudeMultiplier(0);
        }
    }
}
