package android.icu.number;

import android.icu.impl.CacheBase;
import android.icu.impl.PatternProps;
import android.icu.impl.SoftCache;
import android.icu.impl.StringSegment;
import android.icu.impl.number.MacroProps;
import android.icu.impl.number.Padder;
import android.icu.impl.number.RoundingUtils;
import android.icu.number.NumberFormatter;
import android.icu.number.Precision;
import android.icu.text.NumberingSystem;
import android.icu.util.BytesTrie;
import android.icu.util.CharsTrie;
import android.icu.util.CharsTrieBuilder;
import android.icu.util.Currency;
import android.icu.util.MeasureUnit;
import android.icu.util.NoUnit;
import android.icu.util.StringTrieBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* access modifiers changed from: package-private */
public class NumberSkeletonImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String SERIALIZED_STEM_TRIE = buildStemTrie();
    static final StemEnum[] STEM_ENUM_VALUES = StemEnum.values();
    private static final CacheBase<String, UnlocalizedNumberFormatter, Void> cache = new SoftCache<String, UnlocalizedNumberFormatter, Void>() {
        /* class android.icu.number.NumberSkeletonImpl.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public UnlocalizedNumberFormatter createInstance(String skeletonString, Void unused) {
            return NumberSkeletonImpl.create(skeletonString);
        }
    };

    /* access modifiers changed from: package-private */
    public enum ParseState {
        STATE_NULL,
        STATE_SCIENTIFIC,
        STATE_FRACTION_PRECISION,
        STATE_INCREMENT_PRECISION,
        STATE_MEASURE_UNIT,
        STATE_PER_MEASURE_UNIT,
        STATE_CURRENCY_UNIT,
        STATE_INTEGER_WIDTH,
        STATE_NUMBERING_SYSTEM,
        STATE_SCALE
    }

    /* access modifiers changed from: package-private */
    public enum StemEnum {
        STEM_COMPACT_SHORT,
        STEM_COMPACT_LONG,
        STEM_SCIENTIFIC,
        STEM_ENGINEERING,
        STEM_NOTATION_SIMPLE,
        STEM_BASE_UNIT,
        STEM_PERCENT,
        STEM_PERMILLE,
        STEM_PRECISION_INTEGER,
        STEM_PRECISION_UNLIMITED,
        STEM_PRECISION_CURRENCY_STANDARD,
        STEM_PRECISION_CURRENCY_CASH,
        STEM_ROUNDING_MODE_CEILING,
        STEM_ROUNDING_MODE_FLOOR,
        STEM_ROUNDING_MODE_DOWN,
        STEM_ROUNDING_MODE_UP,
        STEM_ROUNDING_MODE_HALF_EVEN,
        STEM_ROUNDING_MODE_HALF_DOWN,
        STEM_ROUNDING_MODE_HALF_UP,
        STEM_ROUNDING_MODE_UNNECESSARY,
        STEM_GROUP_OFF,
        STEM_GROUP_MIN2,
        STEM_GROUP_AUTO,
        STEM_GROUP_ON_ALIGNED,
        STEM_GROUP_THOUSANDS,
        STEM_LATIN,
        STEM_UNIT_WIDTH_NARROW,
        STEM_UNIT_WIDTH_SHORT,
        STEM_UNIT_WIDTH_FULL_NAME,
        STEM_UNIT_WIDTH_ISO_CODE,
        STEM_UNIT_WIDTH_HIDDEN,
        STEM_SIGN_AUTO,
        STEM_SIGN_ALWAYS,
        STEM_SIGN_NEVER,
        STEM_SIGN_ACCOUNTING,
        STEM_SIGN_ACCOUNTING_ALWAYS,
        STEM_SIGN_EXCEPT_ZERO,
        STEM_SIGN_ACCOUNTING_EXCEPT_ZERO,
        STEM_DECIMAL_AUTO,
        STEM_DECIMAL_ALWAYS,
        STEM_PRECISION_INCREMENT,
        STEM_MEASURE_UNIT,
        STEM_PER_MEASURE_UNIT,
        STEM_CURRENCY,
        STEM_INTEGER_WIDTH,
        STEM_NUMBERING_SYSTEM,
        STEM_SCALE
    }

    NumberSkeletonImpl() {
    }

    static String buildStemTrie() {
        CharsTrieBuilder b = new CharsTrieBuilder();
        b.add("compact-short", StemEnum.STEM_COMPACT_SHORT.ordinal());
        b.add("compact-long", StemEnum.STEM_COMPACT_LONG.ordinal());
        b.add("scientific", StemEnum.STEM_SCIENTIFIC.ordinal());
        b.add("engineering", StemEnum.STEM_ENGINEERING.ordinal());
        b.add("notation-simple", StemEnum.STEM_NOTATION_SIMPLE.ordinal());
        b.add("base-unit", StemEnum.STEM_BASE_UNIT.ordinal());
        b.add("percent", StemEnum.STEM_PERCENT.ordinal());
        b.add("permille", StemEnum.STEM_PERMILLE.ordinal());
        b.add("precision-integer", StemEnum.STEM_PRECISION_INTEGER.ordinal());
        b.add("precision-unlimited", StemEnum.STEM_PRECISION_UNLIMITED.ordinal());
        b.add("precision-currency-standard", StemEnum.STEM_PRECISION_CURRENCY_STANDARD.ordinal());
        b.add("precision-currency-cash", StemEnum.STEM_PRECISION_CURRENCY_CASH.ordinal());
        b.add("rounding-mode-ceiling", StemEnum.STEM_ROUNDING_MODE_CEILING.ordinal());
        b.add("rounding-mode-floor", StemEnum.STEM_ROUNDING_MODE_FLOOR.ordinal());
        b.add("rounding-mode-down", StemEnum.STEM_ROUNDING_MODE_DOWN.ordinal());
        b.add("rounding-mode-up", StemEnum.STEM_ROUNDING_MODE_UP.ordinal());
        b.add("rounding-mode-half-even", StemEnum.STEM_ROUNDING_MODE_HALF_EVEN.ordinal());
        b.add("rounding-mode-half-down", StemEnum.STEM_ROUNDING_MODE_HALF_DOWN.ordinal());
        b.add("rounding-mode-half-up", StemEnum.STEM_ROUNDING_MODE_HALF_UP.ordinal());
        b.add("rounding-mode-unnecessary", StemEnum.STEM_ROUNDING_MODE_UNNECESSARY.ordinal());
        b.add("group-off", StemEnum.STEM_GROUP_OFF.ordinal());
        b.add("group-min2", StemEnum.STEM_GROUP_MIN2.ordinal());
        b.add("group-auto", StemEnum.STEM_GROUP_AUTO.ordinal());
        b.add("group-on-aligned", StemEnum.STEM_GROUP_ON_ALIGNED.ordinal());
        b.add("group-thousands", StemEnum.STEM_GROUP_THOUSANDS.ordinal());
        b.add("latin", StemEnum.STEM_LATIN.ordinal());
        b.add("unit-width-narrow", StemEnum.STEM_UNIT_WIDTH_NARROW.ordinal());
        b.add("unit-width-short", StemEnum.STEM_UNIT_WIDTH_SHORT.ordinal());
        b.add("unit-width-full-name", StemEnum.STEM_UNIT_WIDTH_FULL_NAME.ordinal());
        b.add("unit-width-iso-code", StemEnum.STEM_UNIT_WIDTH_ISO_CODE.ordinal());
        b.add("unit-width-hidden", StemEnum.STEM_UNIT_WIDTH_HIDDEN.ordinal());
        b.add("sign-auto", StemEnum.STEM_SIGN_AUTO.ordinal());
        b.add("sign-always", StemEnum.STEM_SIGN_ALWAYS.ordinal());
        b.add("sign-never", StemEnum.STEM_SIGN_NEVER.ordinal());
        b.add("sign-accounting", StemEnum.STEM_SIGN_ACCOUNTING.ordinal());
        b.add("sign-accounting-always", StemEnum.STEM_SIGN_ACCOUNTING_ALWAYS.ordinal());
        b.add("sign-except-zero", StemEnum.STEM_SIGN_EXCEPT_ZERO.ordinal());
        b.add("sign-accounting-except-zero", StemEnum.STEM_SIGN_ACCOUNTING_EXCEPT_ZERO.ordinal());
        b.add("decimal-auto", StemEnum.STEM_DECIMAL_AUTO.ordinal());
        b.add("decimal-always", StemEnum.STEM_DECIMAL_ALWAYS.ordinal());
        b.add("precision-increment", StemEnum.STEM_PRECISION_INCREMENT.ordinal());
        b.add("measure-unit", StemEnum.STEM_MEASURE_UNIT.ordinal());
        b.add("per-measure-unit", StemEnum.STEM_PER_MEASURE_UNIT.ordinal());
        b.add("currency", StemEnum.STEM_CURRENCY.ordinal());
        b.add("integer-width", StemEnum.STEM_INTEGER_WIDTH.ordinal());
        b.add("numbering-system", StemEnum.STEM_NUMBERING_SYSTEM.ordinal());
        b.add("scale", StemEnum.STEM_SCALE.ordinal());
        return b.buildCharSequence(StringTrieBuilder.Option.FAST).toString();
    }

    /* access modifiers changed from: package-private */
    public static final class StemToObject {
        StemToObject() {
        }

        /* access modifiers changed from: private */
        public static Notation notation(StemEnum stem) {
            int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[stem.ordinal()];
            if (i == 1) {
                return Notation.compactShort();
            }
            if (i == 2) {
                return Notation.compactLong();
            }
            if (i == 3) {
                return Notation.scientific();
            }
            if (i == 4) {
                return Notation.engineering();
            }
            if (i == 5) {
                return Notation.simple();
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: private */
        public static MeasureUnit unit(StemEnum stem) {
            int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[stem.ordinal()];
            if (i == 6) {
                return NoUnit.BASE;
            }
            if (i == 7) {
                return NoUnit.PERCENT;
            }
            if (i == 8) {
                return NoUnit.PERMILLE;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: private */
        public static Precision precision(StemEnum stem) {
            switch (stem) {
                case STEM_PRECISION_INTEGER:
                    return Precision.integer();
                case STEM_PRECISION_UNLIMITED:
                    return Precision.unlimited();
                case STEM_PRECISION_CURRENCY_STANDARD:
                    return Precision.currency(Currency.CurrencyUsage.STANDARD);
                case STEM_PRECISION_CURRENCY_CASH:
                    return Precision.currency(Currency.CurrencyUsage.CASH);
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public static RoundingMode roundingMode(StemEnum stem) {
            switch (stem) {
                case STEM_ROUNDING_MODE_CEILING:
                    return RoundingMode.CEILING;
                case STEM_ROUNDING_MODE_FLOOR:
                    return RoundingMode.FLOOR;
                case STEM_ROUNDING_MODE_DOWN:
                    return RoundingMode.DOWN;
                case STEM_ROUNDING_MODE_UP:
                    return RoundingMode.UP;
                case STEM_ROUNDING_MODE_HALF_EVEN:
                    return RoundingMode.HALF_EVEN;
                case STEM_ROUNDING_MODE_HALF_DOWN:
                    return RoundingMode.HALF_DOWN;
                case STEM_ROUNDING_MODE_HALF_UP:
                    return RoundingMode.HALF_UP;
                case STEM_ROUNDING_MODE_UNNECESSARY:
                    return RoundingMode.UNNECESSARY;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public static NumberFormatter.GroupingStrategy groupingStrategy(StemEnum stem) {
            switch (stem) {
                case STEM_GROUP_OFF:
                    return NumberFormatter.GroupingStrategy.OFF;
                case STEM_GROUP_MIN2:
                    return NumberFormatter.GroupingStrategy.MIN2;
                case STEM_GROUP_AUTO:
                    return NumberFormatter.GroupingStrategy.AUTO;
                case STEM_GROUP_ON_ALIGNED:
                    return NumberFormatter.GroupingStrategy.ON_ALIGNED;
                case STEM_GROUP_THOUSANDS:
                    return NumberFormatter.GroupingStrategy.THOUSANDS;
                default:
                    return null;
            }
        }

        /* access modifiers changed from: private */
        public static NumberFormatter.UnitWidth unitWidth(StemEnum stem) {
            switch (stem) {
                case STEM_UNIT_WIDTH_NARROW:
                    return NumberFormatter.UnitWidth.NARROW;
                case STEM_UNIT_WIDTH_SHORT:
                    return NumberFormatter.UnitWidth.SHORT;
                case STEM_UNIT_WIDTH_FULL_NAME:
                    return NumberFormatter.UnitWidth.FULL_NAME;
                case STEM_UNIT_WIDTH_ISO_CODE:
                    return NumberFormatter.UnitWidth.ISO_CODE;
                case STEM_UNIT_WIDTH_HIDDEN:
                    return NumberFormatter.UnitWidth.HIDDEN;
                default:
                    return null;
            }
        }

        /* access modifiers changed from: private */
        public static NumberFormatter.SignDisplay signDisplay(StemEnum stem) {
            switch (stem) {
                case STEM_SIGN_AUTO:
                    return NumberFormatter.SignDisplay.AUTO;
                case STEM_SIGN_ALWAYS:
                    return NumberFormatter.SignDisplay.ALWAYS;
                case STEM_SIGN_NEVER:
                    return NumberFormatter.SignDisplay.NEVER;
                case STEM_SIGN_ACCOUNTING:
                    return NumberFormatter.SignDisplay.ACCOUNTING;
                case STEM_SIGN_ACCOUNTING_ALWAYS:
                    return NumberFormatter.SignDisplay.ACCOUNTING_ALWAYS;
                case STEM_SIGN_EXCEPT_ZERO:
                    return NumberFormatter.SignDisplay.EXCEPT_ZERO;
                case STEM_SIGN_ACCOUNTING_EXCEPT_ZERO:
                    return NumberFormatter.SignDisplay.ACCOUNTING_EXCEPT_ZERO;
                default:
                    return null;
            }
        }

        /* access modifiers changed from: private */
        public static NumberFormatter.DecimalSeparatorDisplay decimalSeparatorDisplay(StemEnum stem) {
            int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[stem.ordinal()];
            if (i == 38) {
                return NumberFormatter.DecimalSeparatorDisplay.AUTO;
            }
            if (i != 39) {
                return null;
            }
            return NumberFormatter.DecimalSeparatorDisplay.ALWAYS;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class EnumToStemString {
        EnumToStemString() {
        }

        /* access modifiers changed from: private */
        public static void roundingMode(RoundingMode value, StringBuilder sb) {
            switch (value) {
                case CEILING:
                    sb.append("rounding-mode-ceiling");
                    return;
                case FLOOR:
                    sb.append("rounding-mode-floor");
                    return;
                case DOWN:
                    sb.append("rounding-mode-down");
                    return;
                case UP:
                    sb.append("rounding-mode-up");
                    return;
                case HALF_EVEN:
                    sb.append("rounding-mode-half-even");
                    return;
                case HALF_DOWN:
                    sb.append("rounding-mode-half-down");
                    return;
                case HALF_UP:
                    sb.append("rounding-mode-half-up");
                    return;
                case UNNECESSARY:
                    sb.append("rounding-mode-unnecessary");
                    return;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public static void groupingStrategy(NumberFormatter.GroupingStrategy value, StringBuilder sb) {
            int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[value.ordinal()];
            if (i == 1) {
                sb.append("group-off");
            } else if (i == 2) {
                sb.append("group-min2");
            } else if (i == 3) {
                sb.append("group-auto");
            } else if (i == 4) {
                sb.append("group-on-aligned");
            } else if (i == 5) {
                sb.append("group-thousands");
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public static void unitWidth(NumberFormatter.UnitWidth value, StringBuilder sb) {
            int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberFormatter$UnitWidth[value.ordinal()];
            if (i == 1) {
                sb.append("unit-width-narrow");
            } else if (i == 2) {
                sb.append("unit-width-short");
            } else if (i == 3) {
                sb.append("unit-width-full-name");
            } else if (i == 4) {
                sb.append("unit-width-iso-code");
            } else if (i == 5) {
                sb.append("unit-width-hidden");
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public static void signDisplay(NumberFormatter.SignDisplay value, StringBuilder sb) {
            switch (value) {
                case AUTO:
                    sb.append("sign-auto");
                    return;
                case ALWAYS:
                    sb.append("sign-always");
                    return;
                case NEVER:
                    sb.append("sign-never");
                    return;
                case ACCOUNTING:
                    sb.append("sign-accounting");
                    return;
                case ACCOUNTING_ALWAYS:
                    sb.append("sign-accounting-always");
                    return;
                case EXCEPT_ZERO:
                    sb.append("sign-except-zero");
                    return;
                case ACCOUNTING_EXCEPT_ZERO:
                    sb.append("sign-accounting-except-zero");
                    return;
                default:
                    throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public static void decimalSeparatorDisplay(NumberFormatter.DecimalSeparatorDisplay value, StringBuilder sb) {
            int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberFormatter$DecimalSeparatorDisplay[value.ordinal()];
            if (i == 1) {
                sb.append("decimal-auto");
            } else if (i == 2) {
                sb.append("decimal-always");
            } else {
                throw new AssertionError();
            }
        }
    }

    public static UnlocalizedNumberFormatter getOrCreate(String skeletonString) {
        return cache.getInstance(skeletonString, null);
    }

    public static UnlocalizedNumberFormatter create(String skeletonString) {
        return (UnlocalizedNumberFormatter) NumberFormatter.with().macros(parseSkeleton(skeletonString));
    }

    public static String generate(MacroProps macros) {
        StringBuilder sb = new StringBuilder();
        generateSkeleton(macros, sb);
        return sb.toString();
    }

    private static MacroProps parseSkeleton(String skeletonString) {
        MacroProps macros = new MacroProps();
        StringSegment segment = new StringSegment(skeletonString + Padder.FALLBACK_PADDING_STRING, false);
        CharsTrie stemTrie = new CharsTrie(SERIALIZED_STEM_TRIE, 0);
        ParseState stem = ParseState.STATE_NULL;
        int offset = 0;
        while (offset < segment.length()) {
            int cp = segment.codePointAt(offset);
            boolean isTokenSeparator = PatternProps.isWhiteSpace(cp);
            boolean isOptionSeparator = cp == 47;
            if (isTokenSeparator || isOptionSeparator) {
                if (offset != 0) {
                    segment.setLength(offset);
                    if (stem == ParseState.STATE_NULL) {
                        stem = parseStem(segment, stemTrie, macros);
                        stemTrie.reset();
                    } else {
                        stem = parseOption(stem, segment, macros);
                    }
                    segment.resetLength();
                    segment.adjustOffset(offset);
                    offset = 0;
                } else if (stem != ParseState.STATE_NULL) {
                    segment.setLength(Character.charCount(cp));
                    throw new SkeletonSyntaxException("Unexpected separator character", segment);
                }
                if (!isOptionSeparator || stem != ParseState.STATE_NULL) {
                    if (isTokenSeparator && stem != ParseState.STATE_NULL) {
                        switch (stem) {
                            case STATE_INCREMENT_PRECISION:
                            case STATE_MEASURE_UNIT:
                            case STATE_PER_MEASURE_UNIT:
                            case STATE_CURRENCY_UNIT:
                            case STATE_INTEGER_WIDTH:
                            case STATE_NUMBERING_SYSTEM:
                            case STATE_SCALE:
                                segment.setLength(Character.charCount(cp));
                                throw new SkeletonSyntaxException("Stem requires an option", segment);
                            default:
                                stem = ParseState.STATE_NULL;
                                break;
                        }
                    }
                    segment.adjustOffset(Character.charCount(cp));
                } else {
                    segment.setLength(Character.charCount(cp));
                    throw new SkeletonSyntaxException("Unexpected option separator", segment);
                }
            } else {
                offset += Character.charCount(cp);
                if (stem == ParseState.STATE_NULL) {
                    stemTrie.nextForCodePoint(cp);
                }
            }
        }
        return macros;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.number.NumberSkeletonImpl$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$number$NumberFormatter$DecimalSeparatorDisplay = new int[NumberFormatter.DecimalSeparatorDisplay.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy = new int[NumberFormatter.GroupingStrategy.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$number$NumberFormatter$UnitWidth = new int[NumberFormatter.UnitWidth.values().length];

        static {
            $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState = new int[ParseState.values().length];
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_INCREMENT_PRECISION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_MEASURE_UNIT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_PER_MEASURE_UNIT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_CURRENCY_UNIT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_INTEGER_WIDTH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_NUMBERING_SYSTEM.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_SCALE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_SCIENTIFIC.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[ParseState.STATE_FRACTION_PRECISION.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$DecimalSeparatorDisplay[NumberFormatter.DecimalSeparatorDisplay.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$DecimalSeparatorDisplay[NumberFormatter.DecimalSeparatorDisplay.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            $SwitchMap$android$icu$number$NumberFormatter$SignDisplay = new int[NumberFormatter.SignDisplay.values().length];
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.NEVER.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.ACCOUNTING.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.ACCOUNTING_ALWAYS.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.EXCEPT_ZERO.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$SignDisplay[NumberFormatter.SignDisplay.ACCOUNTING_EXCEPT_ZERO.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$UnitWidth[NumberFormatter.UnitWidth.NARROW.ordinal()] = 1;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$UnitWidth[NumberFormatter.UnitWidth.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$UnitWidth[NumberFormatter.UnitWidth.FULL_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$UnitWidth[NumberFormatter.UnitWidth.ISO_CODE.ordinal()] = 4;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$UnitWidth[NumberFormatter.UnitWidth.HIDDEN.ordinal()] = 5;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.MIN2.ordinal()] = 2;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.ON_ALIGNED.ordinal()] = 4;
            } catch (NoSuchFieldError e27) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.THOUSANDS.ordinal()] = 5;
            } catch (NoSuchFieldError e28) {
            }
            $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 1;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError e32) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 5;
            } catch (NoSuchFieldError e33) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e34) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError e35) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 8;
            } catch (NoSuchFieldError e36) {
            }
            $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum = new int[StemEnum.values().length];
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_COMPACT_SHORT.ordinal()] = 1;
            } catch (NoSuchFieldError e37) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_COMPACT_LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e38) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SCIENTIFIC.ordinal()] = 3;
            } catch (NoSuchFieldError e39) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ENGINEERING.ordinal()] = 4;
            } catch (NoSuchFieldError e40) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_NOTATION_SIMPLE.ordinal()] = 5;
            } catch (NoSuchFieldError e41) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_BASE_UNIT.ordinal()] = 6;
            } catch (NoSuchFieldError e42) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PERCENT.ordinal()] = 7;
            } catch (NoSuchFieldError e43) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PERMILLE.ordinal()] = 8;
            } catch (NoSuchFieldError e44) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PRECISION_INTEGER.ordinal()] = 9;
            } catch (NoSuchFieldError e45) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PRECISION_UNLIMITED.ordinal()] = 10;
            } catch (NoSuchFieldError e46) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PRECISION_CURRENCY_STANDARD.ordinal()] = 11;
            } catch (NoSuchFieldError e47) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PRECISION_CURRENCY_CASH.ordinal()] = 12;
            } catch (NoSuchFieldError e48) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_CEILING.ordinal()] = 13;
            } catch (NoSuchFieldError e49) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_FLOOR.ordinal()] = 14;
            } catch (NoSuchFieldError e50) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_DOWN.ordinal()] = 15;
            } catch (NoSuchFieldError e51) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_UP.ordinal()] = 16;
            } catch (NoSuchFieldError e52) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_HALF_EVEN.ordinal()] = 17;
            } catch (NoSuchFieldError e53) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_HALF_DOWN.ordinal()] = 18;
            } catch (NoSuchFieldError e54) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_HALF_UP.ordinal()] = 19;
            } catch (NoSuchFieldError e55) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_ROUNDING_MODE_UNNECESSARY.ordinal()] = 20;
            } catch (NoSuchFieldError e56) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_GROUP_OFF.ordinal()] = 21;
            } catch (NoSuchFieldError e57) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_GROUP_MIN2.ordinal()] = 22;
            } catch (NoSuchFieldError e58) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_GROUP_AUTO.ordinal()] = 23;
            } catch (NoSuchFieldError e59) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_GROUP_ON_ALIGNED.ordinal()] = 24;
            } catch (NoSuchFieldError e60) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_GROUP_THOUSANDS.ordinal()] = 25;
            } catch (NoSuchFieldError e61) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_UNIT_WIDTH_NARROW.ordinal()] = 26;
            } catch (NoSuchFieldError e62) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_UNIT_WIDTH_SHORT.ordinal()] = 27;
            } catch (NoSuchFieldError e63) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_UNIT_WIDTH_FULL_NAME.ordinal()] = 28;
            } catch (NoSuchFieldError e64) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_UNIT_WIDTH_ISO_CODE.ordinal()] = 29;
            } catch (NoSuchFieldError e65) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_UNIT_WIDTH_HIDDEN.ordinal()] = 30;
            } catch (NoSuchFieldError e66) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_AUTO.ordinal()] = 31;
            } catch (NoSuchFieldError e67) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_ALWAYS.ordinal()] = 32;
            } catch (NoSuchFieldError e68) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_NEVER.ordinal()] = 33;
            } catch (NoSuchFieldError e69) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_ACCOUNTING.ordinal()] = 34;
            } catch (NoSuchFieldError e70) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_ACCOUNTING_ALWAYS.ordinal()] = 35;
            } catch (NoSuchFieldError e71) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_EXCEPT_ZERO.ordinal()] = 36;
            } catch (NoSuchFieldError e72) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SIGN_ACCOUNTING_EXCEPT_ZERO.ordinal()] = 37;
            } catch (NoSuchFieldError e73) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_DECIMAL_AUTO.ordinal()] = 38;
            } catch (NoSuchFieldError e74) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_DECIMAL_ALWAYS.ordinal()] = 39;
            } catch (NoSuchFieldError e75) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_LATIN.ordinal()] = 40;
            } catch (NoSuchFieldError e76) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PRECISION_INCREMENT.ordinal()] = 41;
            } catch (NoSuchFieldError e77) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_MEASURE_UNIT.ordinal()] = 42;
            } catch (NoSuchFieldError e78) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_PER_MEASURE_UNIT.ordinal()] = 43;
            } catch (NoSuchFieldError e79) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_CURRENCY.ordinal()] = 44;
            } catch (NoSuchFieldError e80) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_INTEGER_WIDTH.ordinal()] = 45;
            } catch (NoSuchFieldError e81) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_NUMBERING_SYSTEM.ordinal()] = 46;
            } catch (NoSuchFieldError e82) {
            }
            try {
                $SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[StemEnum.STEM_SCALE.ordinal()] = 47;
            } catch (NoSuchFieldError e83) {
            }
        }
    }

    private static ParseState parseStem(StringSegment segment, CharsTrie stemTrie, MacroProps macros) {
        char charAt = segment.charAt(0);
        if (charAt == '.') {
            checkNull(macros.precision, segment);
            BlueprintHelpers.parseFractionStem(segment, macros);
            return ParseState.STATE_FRACTION_PRECISION;
        } else if (charAt != '@') {
            BytesTrie.Result stemResult = stemTrie.current();
            if (stemResult == BytesTrie.Result.INTERMEDIATE_VALUE || stemResult == BytesTrie.Result.FINAL_VALUE) {
                StemEnum stem = STEM_ENUM_VALUES[stemTrie.getValue()];
                switch (stem) {
                    case STEM_COMPACT_SHORT:
                    case STEM_COMPACT_LONG:
                    case STEM_SCIENTIFIC:
                    case STEM_ENGINEERING:
                    case STEM_NOTATION_SIMPLE:
                        checkNull(macros.notation, segment);
                        macros.notation = StemToObject.notation(stem);
                        int i = AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[stem.ordinal()];
                        if (i == 3 || i == 4) {
                            return ParseState.STATE_SCIENTIFIC;
                        }
                        return ParseState.STATE_NULL;
                    case STEM_BASE_UNIT:
                    case STEM_PERCENT:
                    case STEM_PERMILLE:
                        checkNull(macros.unit, segment);
                        macros.unit = StemToObject.unit(stem);
                        return ParseState.STATE_NULL;
                    case STEM_PRECISION_INTEGER:
                    case STEM_PRECISION_UNLIMITED:
                    case STEM_PRECISION_CURRENCY_STANDARD:
                    case STEM_PRECISION_CURRENCY_CASH:
                        checkNull(macros.precision, segment);
                        macros.precision = StemToObject.precision(stem);
                        if (AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$StemEnum[stem.ordinal()] != 9) {
                            return ParseState.STATE_NULL;
                        }
                        return ParseState.STATE_FRACTION_PRECISION;
                    case STEM_ROUNDING_MODE_CEILING:
                    case STEM_ROUNDING_MODE_FLOOR:
                    case STEM_ROUNDING_MODE_DOWN:
                    case STEM_ROUNDING_MODE_UP:
                    case STEM_ROUNDING_MODE_HALF_EVEN:
                    case STEM_ROUNDING_MODE_HALF_DOWN:
                    case STEM_ROUNDING_MODE_HALF_UP:
                    case STEM_ROUNDING_MODE_UNNECESSARY:
                        checkNull(macros.roundingMode, segment);
                        macros.roundingMode = StemToObject.roundingMode(stem);
                        return ParseState.STATE_NULL;
                    case STEM_GROUP_OFF:
                    case STEM_GROUP_MIN2:
                    case STEM_GROUP_AUTO:
                    case STEM_GROUP_ON_ALIGNED:
                    case STEM_GROUP_THOUSANDS:
                        checkNull(macros.grouping, segment);
                        macros.grouping = StemToObject.groupingStrategy(stem);
                        return ParseState.STATE_NULL;
                    case STEM_UNIT_WIDTH_NARROW:
                    case STEM_UNIT_WIDTH_SHORT:
                    case STEM_UNIT_WIDTH_FULL_NAME:
                    case STEM_UNIT_WIDTH_ISO_CODE:
                    case STEM_UNIT_WIDTH_HIDDEN:
                        checkNull(macros.unitWidth, segment);
                        macros.unitWidth = StemToObject.unitWidth(stem);
                        return ParseState.STATE_NULL;
                    case STEM_SIGN_AUTO:
                    case STEM_SIGN_ALWAYS:
                    case STEM_SIGN_NEVER:
                    case STEM_SIGN_ACCOUNTING:
                    case STEM_SIGN_ACCOUNTING_ALWAYS:
                    case STEM_SIGN_EXCEPT_ZERO:
                    case STEM_SIGN_ACCOUNTING_EXCEPT_ZERO:
                        checkNull(macros.sign, segment);
                        macros.sign = StemToObject.signDisplay(stem);
                        return ParseState.STATE_NULL;
                    case STEM_DECIMAL_AUTO:
                    case STEM_DECIMAL_ALWAYS:
                        checkNull(macros.decimal, segment);
                        macros.decimal = StemToObject.decimalSeparatorDisplay(stem);
                        return ParseState.STATE_NULL;
                    case STEM_LATIN:
                        checkNull(macros.symbols, segment);
                        macros.symbols = NumberingSystem.LATIN;
                        return ParseState.STATE_NULL;
                    case STEM_PRECISION_INCREMENT:
                        checkNull(macros.precision, segment);
                        return ParseState.STATE_INCREMENT_PRECISION;
                    case STEM_MEASURE_UNIT:
                        checkNull(macros.unit, segment);
                        return ParseState.STATE_MEASURE_UNIT;
                    case STEM_PER_MEASURE_UNIT:
                        checkNull(macros.perUnit, segment);
                        return ParseState.STATE_PER_MEASURE_UNIT;
                    case STEM_CURRENCY:
                        checkNull(macros.unit, segment);
                        return ParseState.STATE_CURRENCY_UNIT;
                    case STEM_INTEGER_WIDTH:
                        checkNull(macros.integerWidth, segment);
                        return ParseState.STATE_INTEGER_WIDTH;
                    case STEM_NUMBERING_SYSTEM:
                        checkNull(macros.symbols, segment);
                        return ParseState.STATE_NUMBERING_SYSTEM;
                    case STEM_SCALE:
                        checkNull(macros.scale, segment);
                        return ParseState.STATE_SCALE;
                    default:
                        throw new AssertionError();
                }
            } else {
                throw new SkeletonSyntaxException("Unknown stem", segment);
            }
        } else {
            checkNull(macros.precision, segment);
            BlueprintHelpers.parseDigitsStem(segment, macros);
            return ParseState.STATE_NULL;
        }
    }

    private static ParseState parseOption(ParseState stem, StringSegment segment, MacroProps macros) {
        switch (stem) {
            case STATE_INCREMENT_PRECISION:
                BlueprintHelpers.parseIncrementOption(segment, macros);
                return ParseState.STATE_NULL;
            case STATE_MEASURE_UNIT:
                BlueprintHelpers.parseMeasureUnitOption(segment, macros);
                return ParseState.STATE_NULL;
            case STATE_PER_MEASURE_UNIT:
                BlueprintHelpers.parseMeasurePerUnitOption(segment, macros);
                return ParseState.STATE_NULL;
            case STATE_CURRENCY_UNIT:
                BlueprintHelpers.parseCurrencyOption(segment, macros);
                return ParseState.STATE_NULL;
            case STATE_INTEGER_WIDTH:
                BlueprintHelpers.parseIntegerWidthOption(segment, macros);
                return ParseState.STATE_NULL;
            case STATE_NUMBERING_SYSTEM:
                BlueprintHelpers.parseNumberingSystemOption(segment, macros);
                return ParseState.STATE_NULL;
            case STATE_SCALE:
                BlueprintHelpers.parseScaleOption(segment, macros);
                return ParseState.STATE_NULL;
            default:
                if (AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[stem.ordinal()] == 8) {
                    if (BlueprintHelpers.parseExponentWidthOption(segment, macros)) {
                        return ParseState.STATE_SCIENTIFIC;
                    }
                    if (BlueprintHelpers.parseExponentSignOption(segment, macros)) {
                        return ParseState.STATE_SCIENTIFIC;
                    }
                }
                if (AnonymousClass2.$SwitchMap$android$icu$number$NumberSkeletonImpl$ParseState[stem.ordinal()] == 9 && BlueprintHelpers.parseFracSigOption(segment, macros)) {
                    return ParseState.STATE_NULL;
                }
                throw new SkeletonSyntaxException("Invalid option", segment);
        }
    }

    private static void generateSkeleton(MacroProps macros, StringBuilder sb) {
        if (macros.notation != null && GeneratorHelpers.notation(macros, sb)) {
            sb.append(' ');
        }
        if (macros.unit != null && GeneratorHelpers.unit(macros, sb)) {
            sb.append(' ');
        }
        if (macros.perUnit != null && GeneratorHelpers.perUnit(macros, sb)) {
            sb.append(' ');
        }
        if (macros.precision != null && GeneratorHelpers.precision(macros, sb)) {
            sb.append(' ');
        }
        if (macros.roundingMode != null && GeneratorHelpers.roundingMode(macros, sb)) {
            sb.append(' ');
        }
        if (macros.grouping != null && GeneratorHelpers.grouping(macros, sb)) {
            sb.append(' ');
        }
        if (macros.integerWidth != null && GeneratorHelpers.integerWidth(macros, sb)) {
            sb.append(' ');
        }
        if (macros.symbols != null && GeneratorHelpers.symbols(macros, sb)) {
            sb.append(' ');
        }
        if (macros.unitWidth != null && GeneratorHelpers.unitWidth(macros, sb)) {
            sb.append(' ');
        }
        if (macros.sign != null && GeneratorHelpers.sign(macros, sb)) {
            sb.append(' ');
        }
        if (macros.decimal != null && GeneratorHelpers.decimal(macros, sb)) {
            sb.append(' ');
        }
        if (macros.scale != null && GeneratorHelpers.scale(macros, sb)) {
            sb.append(' ');
        }
        if (macros.padder != null) {
            throw new UnsupportedOperationException("Cannot generate number skeleton with custom padder");
        } else if (macros.affixProvider != null) {
            throw new UnsupportedOperationException("Cannot generate number skeleton with custom affix provider");
        } else if (macros.rules != null) {
            throw new UnsupportedOperationException("Cannot generate number skeleton with custom plural rules");
        } else if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class BlueprintHelpers {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        BlueprintHelpers() {
        }

        /* access modifiers changed from: private */
        public static boolean parseExponentWidthOption(StringSegment segment, MacroProps macros) {
            if (segment.charAt(0) != '+') {
                return false;
            }
            int offset = 1;
            int minExp = 0;
            while (offset < segment.length() && segment.charAt(offset) == 'e') {
                minExp++;
                offset++;
            }
            if (offset < segment.length()) {
                return false;
            }
            macros.notation = ((ScientificNotation) macros.notation).withMinExponentDigits(minExp);
            return true;
        }

        /* access modifiers changed from: private */
        public static void generateExponentWidthOption(int minExponentDigits, StringBuilder sb) {
            sb.append('+');
            NumberSkeletonImpl.appendMultiple(sb, 101, minExponentDigits);
        }

        /* access modifiers changed from: private */
        public static boolean parseExponentSignOption(StringSegment segment, MacroProps macros) {
            NumberFormatter.SignDisplay sign;
            CharsTrie tempStemTrie = new CharsTrie(NumberSkeletonImpl.SERIALIZED_STEM_TRIE, 0);
            BytesTrie.Result result = tempStemTrie.next(segment, 0, segment.length());
            if ((result != BytesTrie.Result.INTERMEDIATE_VALUE && result != BytesTrie.Result.FINAL_VALUE) || (sign = StemToObject.signDisplay(NumberSkeletonImpl.STEM_ENUM_VALUES[tempStemTrie.getValue()])) == null) {
                return false;
            }
            macros.notation = ((ScientificNotation) macros.notation).withExponentSignDisplay(sign);
            return true;
        }

        /* access modifiers changed from: private */
        public static void parseCurrencyOption(StringSegment segment, MacroProps macros) {
            try {
                macros.unit = Currency.getInstance(segment.subSequence(0, segment.length()).toString());
            } catch (IllegalArgumentException e) {
                throw new SkeletonSyntaxException("Invalid currency", segment, e);
            }
        }

        /* access modifiers changed from: private */
        public static void generateCurrencyOption(Currency currency, StringBuilder sb) {
            sb.append(currency.getCurrencyCode());
        }

        /* access modifiers changed from: private */
        public static void parseMeasureUnitOption(StringSegment segment, MacroProps macros) {
            int firstHyphen = 0;
            while (firstHyphen < segment.length() && segment.charAt(firstHyphen) != '-') {
                firstHyphen++;
            }
            if (firstHyphen != segment.length()) {
                String type = segment.subSequence(0, firstHyphen).toString();
                String subType = segment.subSequence(firstHyphen + 1, segment.length()).toString();
                for (MeasureUnit unit : MeasureUnit.getAvailable(type)) {
                    if (subType.equals(unit.getSubtype())) {
                        macros.unit = unit;
                        return;
                    }
                }
                throw new SkeletonSyntaxException("Unknown measure unit", segment);
            }
            throw new SkeletonSyntaxException("Invalid measure unit option", segment);
        }

        /* access modifiers changed from: private */
        public static void generateMeasureUnitOption(MeasureUnit unit, StringBuilder sb) {
            sb.append(unit.getType());
            sb.append("-");
            sb.append(unit.getSubtype());
        }

        /* access modifiers changed from: private */
        public static void parseMeasurePerUnitOption(StringSegment segment, MacroProps macros) {
            MeasureUnit numerator = macros.unit;
            parseMeasureUnitOption(segment, macros);
            macros.perUnit = macros.unit;
            macros.unit = numerator;
        }

        /* access modifiers changed from: private */
        public static void parseFractionStem(StringSegment segment, MacroProps macros) {
            int maxFrac;
            int offset = 1;
            int minFrac = 0;
            while (offset < segment.length() && segment.charAt(offset) == '0') {
                minFrac++;
                offset++;
            }
            if (offset >= segment.length()) {
                maxFrac = minFrac;
            } else if (segment.charAt(offset) == '+') {
                maxFrac = -1;
                offset++;
            } else {
                maxFrac = minFrac;
                while (offset < segment.length() && segment.charAt(offset) == '#') {
                    maxFrac++;
                    offset++;
                }
            }
            if (offset < segment.length()) {
                throw new SkeletonSyntaxException("Invalid fraction stem", segment);
            } else if (maxFrac == -1) {
                macros.precision = Precision.minFraction(minFrac);
            } else {
                macros.precision = Precision.minMaxFraction(minFrac, maxFrac);
            }
        }

        /* access modifiers changed from: private */
        public static void generateFractionStem(int minFrac, int maxFrac, StringBuilder sb) {
            if (minFrac == 0 && maxFrac == 0) {
                sb.append("precision-integer");
                return;
            }
            sb.append('.');
            NumberSkeletonImpl.appendMultiple(sb, 48, minFrac);
            if (maxFrac == -1) {
                sb.append('+');
            } else {
                NumberSkeletonImpl.appendMultiple(sb, 35, maxFrac - minFrac);
            }
        }

        /* access modifiers changed from: private */
        public static void parseDigitsStem(StringSegment segment, MacroProps macros) {
            int maxSig;
            int offset = 0;
            int minSig = 0;
            while (offset < segment.length() && segment.charAt(offset) == '@') {
                minSig++;
                offset++;
            }
            if (offset >= segment.length()) {
                maxSig = minSig;
            } else if (segment.charAt(offset) == '+') {
                maxSig = -1;
                offset++;
            } else {
                maxSig = minSig;
                while (offset < segment.length() && segment.charAt(offset) == '#') {
                    maxSig++;
                    offset++;
                }
            }
            if (offset < segment.length()) {
                throw new SkeletonSyntaxException("Invalid significant digits stem", segment);
            } else if (maxSig == -1) {
                macros.precision = Precision.minSignificantDigits(minSig);
            } else {
                macros.precision = Precision.minMaxSignificantDigits(minSig, maxSig);
            }
        }

        /* access modifiers changed from: private */
        public static void generateDigitsStem(int minSig, int maxSig, StringBuilder sb) {
            NumberSkeletonImpl.appendMultiple(sb, 64, minSig);
            if (maxSig == -1) {
                sb.append('+');
            } else {
                NumberSkeletonImpl.appendMultiple(sb, 35, maxSig - minSig);
            }
        }

        /* access modifiers changed from: private */
        public static boolean parseFracSigOption(StringSegment segment, MacroProps macros) {
            int maxSig;
            if (segment.charAt(0) != '@') {
                return false;
            }
            int offset = 0;
            int minSig = 0;
            while (offset < segment.length() && segment.charAt(offset) == '@') {
                minSig++;
                offset++;
            }
            if (offset < segment.length()) {
                if (segment.charAt(offset) == '+') {
                    maxSig = -1;
                    offset++;
                } else if (minSig <= 1) {
                    maxSig = minSig;
                    while (offset < segment.length() && segment.charAt(offset) == '#') {
                        maxSig++;
                        offset++;
                    }
                } else {
                    throw new SkeletonSyntaxException("Invalid digits option for fraction rounder", segment);
                }
                if (offset >= segment.length()) {
                    FractionPrecision oldRounder = (FractionPrecision) macros.precision;
                    if (maxSig == -1) {
                        macros.precision = oldRounder.withMinDigits(minSig);
                    } else {
                        macros.precision = oldRounder.withMaxDigits(maxSig);
                    }
                    return true;
                }
                throw new SkeletonSyntaxException("Invalid digits option for fraction rounder", segment);
            }
            throw new SkeletonSyntaxException("Invalid digits option for fraction rounder", segment);
        }

        /* access modifiers changed from: private */
        public static void parseIncrementOption(StringSegment segment, MacroProps macros) {
            try {
                macros.precision = Precision.increment(new BigDecimal(segment.subSequence(0, segment.length()).toString()));
            } catch (NumberFormatException e) {
                throw new SkeletonSyntaxException("Invalid rounding increment", segment, e);
            }
        }

        /* access modifiers changed from: private */
        public static void generateIncrementOption(BigDecimal increment, StringBuilder sb) {
            sb.append(increment.toPlainString());
        }

        /* access modifiers changed from: private */
        public static void parseIntegerWidthOption(StringSegment segment, MacroProps macros) {
            int maxInt;
            int offset = 0;
            int minInt = 0;
            if (segment.charAt(0) == '+') {
                maxInt = -1;
                offset = 0 + 1;
            } else {
                maxInt = 0;
            }
            while (offset < segment.length() && segment.charAt(offset) == '#') {
                maxInt++;
                offset++;
            }
            if (offset < segment.length()) {
                while (offset < segment.length() && segment.charAt(offset) == '0') {
                    minInt++;
                    offset++;
                }
            }
            if (maxInt != -1) {
                maxInt += minInt;
            }
            if (offset < segment.length()) {
                throw new SkeletonSyntaxException("Invalid integer width stem", segment);
            } else if (maxInt == -1) {
                macros.integerWidth = IntegerWidth.zeroFillTo(minInt);
            } else {
                macros.integerWidth = IntegerWidth.zeroFillTo(minInt).truncateAt(maxInt);
            }
        }

        /* access modifiers changed from: private */
        public static void generateIntegerWidthOption(int minInt, int maxInt, StringBuilder sb) {
            if (maxInt == -1) {
                sb.append('+');
            } else {
                NumberSkeletonImpl.appendMultiple(sb, 35, maxInt - minInt);
            }
            NumberSkeletonImpl.appendMultiple(sb, 48, minInt);
        }

        /* access modifiers changed from: private */
        public static void parseNumberingSystemOption(StringSegment segment, MacroProps macros) {
            NumberingSystem ns = NumberingSystem.getInstanceByName(segment.subSequence(0, segment.length()).toString());
            if (ns != null) {
                macros.symbols = ns;
                return;
            }
            throw new SkeletonSyntaxException("Unknown numbering system", segment);
        }

        /* access modifiers changed from: private */
        public static void generateNumberingSystemOption(NumberingSystem ns, StringBuilder sb) {
            sb.append(ns.getName());
        }

        /* access modifiers changed from: private */
        public static void parseScaleOption(StringSegment segment, MacroProps macros) {
            try {
                macros.scale = Scale.byBigDecimal(new BigDecimal(segment.subSequence(0, segment.length()).toString()));
            } catch (NumberFormatException e) {
                throw new SkeletonSyntaxException("Invalid scale", segment, e);
            }
        }

        /* access modifiers changed from: private */
        public static void generateScaleOption(Scale scale, StringBuilder sb) {
            BigDecimal bd = scale.arbitrary;
            if (bd == null) {
                bd = BigDecimal.ONE;
            }
            sb.append(bd.scaleByPowerOfTen(scale.magnitude).toPlainString());
        }
    }

    /* access modifiers changed from: package-private */
    public static final class GeneratorHelpers {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        GeneratorHelpers() {
        }

        /* access modifiers changed from: private */
        public static boolean notation(MacroProps macros, StringBuilder sb) {
            if (macros.notation instanceof CompactNotation) {
                if (macros.notation == Notation.compactLong()) {
                    sb.append("compact-long");
                    return true;
                } else if (macros.notation == Notation.compactShort()) {
                    sb.append("compact-short");
                    return true;
                } else {
                    throw new UnsupportedOperationException("Cannot generate number skeleton with custom compact data");
                }
            } else if (!(macros.notation instanceof ScientificNotation)) {
                return false;
            } else {
                ScientificNotation impl = (ScientificNotation) macros.notation;
                if (impl.engineeringInterval == 3) {
                    sb.append("engineering");
                } else {
                    sb.append("scientific");
                }
                if (impl.minExponentDigits > 1) {
                    sb.append('/');
                    BlueprintHelpers.generateExponentWidthOption(impl.minExponentDigits, sb);
                }
                if (impl.exponentSignDisplay != NumberFormatter.SignDisplay.AUTO) {
                    sb.append('/');
                    EnumToStemString.signDisplay(impl.exponentSignDisplay, sb);
                }
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static boolean unit(MacroProps macros, StringBuilder sb) {
            if (macros.unit instanceof Currency) {
                sb.append("currency/");
                BlueprintHelpers.generateCurrencyOption((Currency) macros.unit, sb);
                return true;
            } else if (!(macros.unit instanceof NoUnit)) {
                sb.append("measure-unit/");
                BlueprintHelpers.generateMeasureUnitOption(macros.unit, sb);
                return true;
            } else if (macros.unit == NoUnit.PERCENT) {
                sb.append("percent");
                return true;
            } else if (macros.unit != NoUnit.PERMILLE) {
                return false;
            } else {
                sb.append("permille");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static boolean perUnit(MacroProps macros, StringBuilder sb) {
            if ((macros.perUnit instanceof Currency) || (macros.perUnit instanceof NoUnit)) {
                throw new UnsupportedOperationException("Cannot generate number skeleton with per-unit that is not a standard measure unit");
            }
            sb.append("per-measure-unit/");
            BlueprintHelpers.generateMeasureUnitOption(macros.perUnit, sb);
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean precision(MacroProps macros, StringBuilder sb) {
            if (macros.precision instanceof Precision.InfiniteRounderImpl) {
                sb.append("precision-unlimited");
            } else if (macros.precision instanceof Precision.FractionRounderImpl) {
                Precision.FractionRounderImpl impl = (Precision.FractionRounderImpl) macros.precision;
                BlueprintHelpers.generateFractionStem(impl.minFrac, impl.maxFrac, sb);
            } else if (macros.precision instanceof Precision.SignificantRounderImpl) {
                Precision.SignificantRounderImpl impl2 = (Precision.SignificantRounderImpl) macros.precision;
                BlueprintHelpers.generateDigitsStem(impl2.minSig, impl2.maxSig, sb);
            } else if (macros.precision instanceof Precision.FracSigRounderImpl) {
                Precision.FracSigRounderImpl impl3 = (Precision.FracSigRounderImpl) macros.precision;
                BlueprintHelpers.generateFractionStem(impl3.minFrac, impl3.maxFrac, sb);
                sb.append('/');
                if (impl3.minSig == -1) {
                    BlueprintHelpers.generateDigitsStem(1, impl3.maxSig, sb);
                } else {
                    BlueprintHelpers.generateDigitsStem(impl3.minSig, -1, sb);
                }
            } else if (macros.precision instanceof Precision.IncrementRounderImpl) {
                sb.append("precision-increment/");
                BlueprintHelpers.generateIncrementOption(((Precision.IncrementRounderImpl) macros.precision).increment, sb);
            } else if (((Precision.CurrencyRounderImpl) macros.precision).usage == Currency.CurrencyUsage.STANDARD) {
                sb.append("precision-currency-standard");
            } else {
                sb.append("precision-currency-cash");
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean roundingMode(MacroProps macros, StringBuilder sb) {
            if (macros.roundingMode == RoundingUtils.DEFAULT_ROUNDING_MODE) {
                return false;
            }
            EnumToStemString.roundingMode(macros.roundingMode, sb);
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean grouping(MacroProps macros, StringBuilder sb) {
            if (!(macros.grouping instanceof NumberFormatter.GroupingStrategy)) {
                throw new UnsupportedOperationException("Cannot generate number skeleton with custom Grouper");
            } else if (macros.grouping == NumberFormatter.GroupingStrategy.AUTO) {
                return false;
            } else {
                EnumToStemString.groupingStrategy((NumberFormatter.GroupingStrategy) macros.grouping, sb);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static boolean integerWidth(MacroProps macros, StringBuilder sb) {
            if (macros.integerWidth.equals(IntegerWidth.DEFAULT)) {
                return false;
            }
            sb.append("integer-width/");
            BlueprintHelpers.generateIntegerWidthOption(macros.integerWidth.minInt, macros.integerWidth.maxInt, sb);
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean symbols(MacroProps macros, StringBuilder sb) {
            if (macros.symbols instanceof NumberingSystem) {
                NumberingSystem ns = (NumberingSystem) macros.symbols;
                if (ns.getName().equals("latn")) {
                    sb.append("latin");
                    return true;
                }
                sb.append("numbering-system/");
                BlueprintHelpers.generateNumberingSystemOption(ns, sb);
                return true;
            }
            throw new UnsupportedOperationException("Cannot generate number skeleton with custom DecimalFormatSymbols");
        }

        /* access modifiers changed from: private */
        public static boolean unitWidth(MacroProps macros, StringBuilder sb) {
            if (macros.unitWidth == NumberFormatter.UnitWidth.SHORT) {
                return false;
            }
            EnumToStemString.unitWidth(macros.unitWidth, sb);
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean sign(MacroProps macros, StringBuilder sb) {
            if (macros.sign == NumberFormatter.SignDisplay.AUTO) {
                return false;
            }
            EnumToStemString.signDisplay(macros.sign, sb);
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean decimal(MacroProps macros, StringBuilder sb) {
            if (macros.decimal == NumberFormatter.DecimalSeparatorDisplay.AUTO) {
                return false;
            }
            EnumToStemString.decimalSeparatorDisplay(macros.decimal, sb);
            return true;
        }

        /* access modifiers changed from: private */
        public static boolean scale(MacroProps macros, StringBuilder sb) {
            if (!macros.scale.isValid()) {
                return false;
            }
            sb.append("scale/");
            BlueprintHelpers.generateScaleOption(macros.scale, sb);
            return true;
        }
    }

    private static void checkNull(Object value, CharSequence content) {
        if (value != null) {
            throw new SkeletonSyntaxException("Duplicated setting", content);
        }
    }

    /* access modifiers changed from: private */
    public static void appendMultiple(StringBuilder sb, int cp, int count) {
        for (int i = 0; i < count; i++) {
            sb.appendCodePoint(cp);
        }
    }
}
