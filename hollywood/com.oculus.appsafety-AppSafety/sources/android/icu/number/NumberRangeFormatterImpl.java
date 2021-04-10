package android.icu.number;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.PatternProps;
import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.UResource;
import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MacroProps;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.Modifier;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.impl.number.SimpleModifier;
import android.icu.impl.number.range.PrefixInfixSuffixLengthHelper;
import android.icu.impl.number.range.RangeMacroProps;
import android.icu.impl.number.range.StandardPluralRanges;
import android.icu.number.NumberRangeFormatter;
import android.icu.text.NumberFormat;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;

class NumberRangeFormatterImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    SimpleModifier fApproximatelyModifier;
    final NumberRangeFormatter.RangeCollapse fCollapse;
    final NumberRangeFormatter.RangeIdentityFallback fIdentityFallback;
    final StandardPluralRanges fPluralRanges;
    String fRangePattern;
    final boolean fSameFormatters;
    final NumberFormatterImpl formatterImpl1;
    final NumberFormatterImpl formatterImpl2;

    /* access modifiers changed from: package-private */
    public int identity2d(NumberRangeFormatter.RangeIdentityFallback a, NumberRangeFormatter.RangeIdentityResult b) {
        return a.ordinal() | (b.ordinal() << 4);
    }

    /* access modifiers changed from: private */
    public static final class NumberRangeDataSink extends UResource.Sink {
        String approximatelyPattern;
        String rangePattern;
        StringBuilder sb;

        NumberRangeDataSink(StringBuilder sb2) {
            this.sb = sb2;
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table miscTable = value.getTable();
            for (int i = 0; miscTable.getKeyAndValue(i, key, value); i++) {
                if (key.contentEquals("range") && this.rangePattern == null) {
                    this.rangePattern = SimpleFormatterImpl.compileToStringMinMaxArguments(value.getString(), this.sb, 2, 2);
                }
                if (key.contentEquals("approximately") && this.approximatelyPattern == null) {
                    this.approximatelyPattern = SimpleFormatterImpl.compileToStringMinMaxArguments(value.getString(), this.sb, 1, 1);
                }
            }
        }
    }

    private static void getNumberRangeData(ULocale locale, String nsName, NumberRangeFormatterImpl out) {
        StringBuilder sb = new StringBuilder();
        NumberRangeDataSink sink = new NumberRangeDataSink(sb);
        sb.append("NumberElements/");
        sb.append(nsName);
        sb.append("/miscPatterns");
        ((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale)).getAllItemsWithFallback(sb.toString(), sink);
        if (sink.rangePattern == null) {
            sink.rangePattern = SimpleFormatterImpl.compileToStringMinMaxArguments("{0}–{1}", sb, 2, 2);
        }
        if (sink.approximatelyPattern == null) {
            sink.approximatelyPattern = SimpleFormatterImpl.compileToStringMinMaxArguments("~{0}", sb, 1, 1);
        }
        out.fRangePattern = sink.rangePattern;
        out.fApproximatelyModifier = new SimpleModifier(sink.approximatelyPattern, null, false);
    }

    public NumberRangeFormatterImpl(RangeMacroProps macros) {
        MacroProps macroProps;
        MacroProps macroProps2;
        NumberRangeFormatter.RangeIdentityFallback rangeIdentityFallback;
        if (macros.formatter1 != null) {
            macroProps = macros.formatter1.resolve();
        } else {
            macroProps = NumberFormatter.withLocale(macros.loc).resolve();
        }
        this.formatterImpl1 = new NumberFormatterImpl(macroProps);
        if (macros.formatter2 != null) {
            macroProps2 = macros.formatter2.resolve();
        } else {
            macroProps2 = NumberFormatter.withLocale(macros.loc).resolve();
        }
        this.formatterImpl2 = new NumberFormatterImpl(macroProps2);
        this.fSameFormatters = macros.sameFormatters != 0;
        this.fCollapse = macros.collapse != null ? macros.collapse : NumberRangeFormatter.RangeCollapse.AUTO;
        if (macros.identityFallback != null) {
            rangeIdentityFallback = macros.identityFallback;
        } else {
            rangeIdentityFallback = NumberRangeFormatter.RangeIdentityFallback.APPROXIMATELY;
        }
        this.fIdentityFallback = rangeIdentityFallback;
        getNumberRangeData(macros.loc, "latn", this);
        this.fPluralRanges = new StandardPluralRanges(macros.loc);
    }

    public FormattedNumberRange format(DecimalQuantity quantity1, DecimalQuantity quantity2, boolean equalBeforeRounding) {
        MicroProps micros2;
        NumberRangeFormatter.RangeIdentityResult identityResult;
        NumberStringBuilder string = new NumberStringBuilder();
        MicroProps micros1 = this.formatterImpl1.preProcess(quantity1);
        if (this.fSameFormatters) {
            micros2 = this.formatterImpl1.preProcess(quantity2);
        } else {
            micros2 = this.formatterImpl2.preProcess(quantity2);
        }
        if (!micros1.modInner.semanticallyEquivalent(micros2.modInner) || !micros1.modMiddle.semanticallyEquivalent(micros2.modMiddle) || !micros1.modOuter.semanticallyEquivalent(micros2.modOuter)) {
            formatRange(quantity1, quantity2, string, micros1, micros2);
            return new FormattedNumberRange(string, quantity1, quantity2, NumberRangeFormatter.RangeIdentityResult.NOT_EQUAL);
        }
        if (equalBeforeRounding) {
            identityResult = NumberRangeFormatter.RangeIdentityResult.EQUAL_BEFORE_ROUNDING;
        } else if (quantity1.equals(quantity2)) {
            identityResult = NumberRangeFormatter.RangeIdentityResult.EQUAL_AFTER_ROUNDING;
        } else {
            identityResult = NumberRangeFormatter.RangeIdentityResult.NOT_EQUAL;
        }
        int identity2d = identity2d(this.fIdentityFallback, identityResult);
        if (!(identity2d == 0 || identity2d == 1)) {
            if (identity2d != 2) {
                if (identity2d != 3) {
                    switch (identity2d) {
                        case 16:
                            break;
                        case 17:
                        case 18:
                            break;
                        default:
                            switch (identity2d) {
                            }
                        case 19:
                            formatRange(quantity1, quantity2, string, micros1, micros2);
                            break;
                    }
                    return new FormattedNumberRange(string, quantity1, quantity2, identityResult);
                }
                formatRange(quantity1, quantity2, string, micros1, micros2);
                return new FormattedNumberRange(string, quantity1, quantity2, identityResult);
            }
            formatApproximately(quantity1, quantity2, string, micros1, micros2);
            return new FormattedNumberRange(string, quantity1, quantity2, identityResult);
        }
        formatSingleValue(quantity1, quantity2, string, micros1, micros2);
        return new FormattedNumberRange(string, quantity1, quantity2, identityResult);
    }

    private void formatSingleValue(DecimalQuantity quantity1, DecimalQuantity quantity2, NumberStringBuilder string, MicroProps micros1, MicroProps micros2) {
        if (this.fSameFormatters) {
            NumberFormatterImpl.writeAffixes(micros1, string, 0, NumberFormatterImpl.writeNumber(micros1, quantity1, string, 0));
        } else {
            formatRange(quantity1, quantity2, string, micros1, micros2);
        }
    }

    private void formatApproximately(DecimalQuantity quantity1, DecimalQuantity quantity2, NumberStringBuilder string, MicroProps micros1, MicroProps micros2) {
        if (this.fSameFormatters) {
            int length = NumberFormatterImpl.writeNumber(micros1, quantity1, string, 0);
            int length2 = length + micros1.modInner.apply(string, 0, length);
            int length3 = length2 + micros1.modMiddle.apply(string, 0, length2);
            micros1.modOuter.apply(string, 0, length3 + this.fApproximatelyModifier.apply(string, 0, length3));
            return;
        }
        formatRange(quantity1, quantity2, string, micros1, micros2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.number.NumberRangeFormatterImpl$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$number$NumberRangeFormatter$RangeCollapse = new int[NumberRangeFormatter.RangeCollapse.values().length];

        static {
            try {
                $SwitchMap$android$icu$number$NumberRangeFormatter$RangeCollapse[NumberRangeFormatter.RangeCollapse.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$number$NumberRangeFormatter$RangeCollapse[NumberRangeFormatter.RangeCollapse.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$number$NumberRangeFormatter$RangeCollapse[NumberRangeFormatter.RangeCollapse.UNIT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Multiple debug info for r7v2 android.icu.impl.number.Modifier: [D('mm' android.icu.impl.number.Modifier), D('collapseInner' boolean)] */
    private void formatRange(DecimalQuantity quantity1, DecimalQuantity quantity2, NumberStringBuilder string, MicroProps micros1, MicroProps micros2) {
        boolean collapseInner;
        boolean collapseMiddle;
        boolean collapseOuter;
        int i = AnonymousClass1.$SwitchMap$android$icu$number$NumberRangeFormatter$RangeCollapse[this.fCollapse.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            collapseOuter = micros1.modOuter.semanticallyEquivalent(micros2.modOuter);
            if (!collapseOuter) {
                collapseMiddle = false;
                collapseInner = false;
            } else {
                collapseMiddle = micros1.modMiddle.semanticallyEquivalent(micros2.modMiddle);
                if (!collapseMiddle) {
                    collapseInner = false;
                } else {
                    Modifier mm = micros1.modMiddle;
                    if (this.fCollapse == NumberRangeFormatter.RangeCollapse.UNIT) {
                        if (!mm.containsField(NumberFormat.Field.CURRENCY) && !mm.containsField(NumberFormat.Field.PERCENT)) {
                            collapseMiddle = false;
                        }
                    } else if (this.fCollapse == NumberRangeFormatter.RangeCollapse.AUTO && mm.getCodePointCount() <= 1) {
                        collapseMiddle = false;
                    }
                    if (!collapseMiddle || this.fCollapse != NumberRangeFormatter.RangeCollapse.ALL) {
                        collapseInner = false;
                    } else {
                        collapseInner = micros1.modInner.semanticallyEquivalent(micros2.modInner);
                    }
                }
            }
        } else {
            collapseOuter = false;
            collapseMiddle = false;
            collapseInner = false;
        }
        PrefixInfixSuffixLengthHelper h = new PrefixInfixSuffixLengthHelper();
        boolean repeatOuter = false;
        SimpleModifier.formatTwoArgPattern(this.fRangePattern, string, 0, h, null);
        boolean repeatInner = !collapseInner && micros1.modInner.getCodePointCount() > 0;
        boolean repeatMiddle = !collapseMiddle && micros1.modMiddle.getCodePointCount() > 0;
        if (!collapseOuter && micros1.modOuter.getCodePointCount() > 0) {
            repeatOuter = true;
        }
        if (repeatInner || repeatMiddle || repeatOuter) {
            if (!PatternProps.isWhiteSpace(string.charAt(h.index1()))) {
                h.lengthInfix += string.insertCodePoint(h.index1(), 32, null);
            }
            if (!PatternProps.isWhiteSpace(string.charAt(h.index2() - 1))) {
                h.lengthInfix += string.insertCodePoint(h.index2(), 32, null);
            }
        }
        h.length1 += NumberFormatterImpl.writeNumber(micros1, quantity1, string, h.index0());
        h.length2 += NumberFormatterImpl.writeNumber(micros2, quantity2, string, h.index2());
        if (collapseInner) {
            h.lengthInfix += resolveModifierPlurals(micros1.modInner, micros2.modInner).apply(string, h.index0(), h.index3());
        } else {
            h.length1 += micros1.modInner.apply(string, h.index0(), h.index1());
            h.length2 += micros2.modInner.apply(string, h.index2(), h.index3());
        }
        if (collapseMiddle) {
            h.lengthInfix += resolveModifierPlurals(micros1.modMiddle, micros2.modMiddle).apply(string, h.index0(), h.index3());
        } else {
            h.length1 += micros1.modMiddle.apply(string, h.index0(), h.index1());
            h.length2 += micros2.modMiddle.apply(string, h.index2(), h.index3());
        }
        if (collapseOuter) {
            h.lengthInfix += resolveModifierPlurals(micros1.modOuter, micros2.modOuter).apply(string, h.index0(), h.index3());
            return;
        }
        h.length1 += micros1.modOuter.apply(string, h.index0(), h.index1());
        h.length2 += micros2.modOuter.apply(string, h.index2(), h.index3());
    }

    /* access modifiers changed from: package-private */
    public Modifier resolveModifierPlurals(Modifier first, Modifier second) {
        Modifier.Parameters secondParameters;
        Modifier.Parameters firstParameters = first.getParameters();
        if (firstParameters == null || (secondParameters = second.getParameters()) == null) {
            return first;
        }
        return firstParameters.obj.getModifier(firstParameters.signum, this.fPluralRanges.resolve(firstParameters.plural, secondParameters.plural));
    }
}
