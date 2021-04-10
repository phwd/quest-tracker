package android.icu.impl.number;

import android.icu.text.NumberFormat;

public class Padder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String FALLBACK_PADDING_STRING = " ";
    public static final Padder NONE = new Padder(null, -1, null);
    String paddingString;
    PadPosition position;
    int targetWidth;

    public enum PadPosition {
        BEFORE_PREFIX,
        AFTER_PREFIX,
        BEFORE_SUFFIX,
        AFTER_SUFFIX;

        public static PadPosition fromOld(int old) {
            if (old == 0) {
                return BEFORE_PREFIX;
            }
            if (old == 1) {
                return AFTER_PREFIX;
            }
            if (old == 2) {
                return BEFORE_SUFFIX;
            }
            if (old == 3) {
                return AFTER_SUFFIX;
            }
            throw new IllegalArgumentException("Don't know how to map " + old);
        }

        public int toOld() {
            int i = AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition[ordinal()];
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 3) {
                return 2;
            }
            if (i != 4) {
                return -1;
            }
            return 3;
        }
    }

    /* renamed from: android.icu.impl.number.Padder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$number$Padder$PadPosition = new int[PadPosition.values().length];

        static {
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[PadPosition.BEFORE_PREFIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[PadPosition.AFTER_PREFIX.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[PadPosition.BEFORE_SUFFIX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[PadPosition.AFTER_SUFFIX.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public Padder(String paddingString2, int targetWidth2, PadPosition position2) {
        this.paddingString = paddingString2 == null ? FALLBACK_PADDING_STRING : paddingString2;
        this.targetWidth = targetWidth2;
        this.position = position2 == null ? PadPosition.BEFORE_PREFIX : position2;
    }

    public static Padder none() {
        return NONE;
    }

    public static Padder codePoints(int cp, int targetWidth2, PadPosition position2) {
        if (targetWidth2 >= 0) {
            return new Padder(String.valueOf(Character.toChars(cp)), targetWidth2, position2);
        }
        throw new IllegalArgumentException("Padding width must not be negative");
    }

    public static Padder forProperties(DecimalFormatProperties properties) {
        return new Padder(properties.getPadString(), properties.getFormatWidth(), properties.getPadPosition());
    }

    public boolean isValid() {
        return this.targetWidth > 0;
    }

    public int padAndApply(Modifier mod1, Modifier mod2, NumberStringBuilder string, int leftIndex, int rightIndex) {
        int requiredPadding = (this.targetWidth - (mod1.getCodePointCount() + mod2.getCodePointCount())) - string.codePointCount();
        int length = 0;
        if (requiredPadding <= 0) {
            int length2 = 0 + mod1.apply(string, leftIndex, rightIndex);
            return length2 + mod2.apply(string, leftIndex, rightIndex + length2);
        }
        if (this.position == PadPosition.AFTER_PREFIX) {
            length = 0 + addPaddingHelper(this.paddingString, requiredPadding, string, leftIndex);
        } else if (this.position == PadPosition.BEFORE_SUFFIX) {
            length = 0 + addPaddingHelper(this.paddingString, requiredPadding, string, rightIndex + 0);
        }
        int length3 = length + mod1.apply(string, leftIndex, rightIndex + length);
        int length4 = length3 + mod2.apply(string, leftIndex, rightIndex + length3);
        if (this.position == PadPosition.BEFORE_PREFIX) {
            return length4 + addPaddingHelper(this.paddingString, requiredPadding, string, leftIndex);
        }
        if (this.position == PadPosition.AFTER_SUFFIX) {
            return length4 + addPaddingHelper(this.paddingString, requiredPadding, string, rightIndex + length4);
        }
        return length4;
    }

    private static int addPaddingHelper(String paddingString2, int requiredPadding, NumberStringBuilder string, int index) {
        for (int i = 0; i < requiredPadding; i++) {
            string.insert(index, paddingString2, (NumberFormat.Field) null);
        }
        return paddingString2.length() * requiredPadding;
    }
}
