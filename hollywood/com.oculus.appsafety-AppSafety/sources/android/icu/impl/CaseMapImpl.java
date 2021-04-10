package android.icu.impl;

import android.icu.impl.UCaseProps;
import android.icu.text.BreakIterator;
import android.icu.text.Edits;
import android.icu.text.UTF16;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.ULocale;
import java.io.IOException;
import java.text.CharacterIterator;
import java.util.Locale;

public final class CaseMapImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Trie2_16 CASE_TRIE = UCaseProps.getTrie();
    private static final int LNS = 251792942;
    public static final int OMIT_UNCHANGED_TEXT = 16384;
    private static final int TITLECASE_ADJUSTMENT_MASK = 1536;
    public static final int TITLECASE_ADJUST_TO_CASED = 1024;
    private static final int TITLECASE_ITERATOR_MASK = 224;
    public static final int TITLECASE_SENTENCES = 64;
    public static final int TITLECASE_WHOLE_STRING = 32;

    public static final class StringContextIterator implements UCaseProps.ContextIterator {
        protected int cpLimit;
        protected int cpStart;
        protected int dir;
        protected int index;
        protected int limit;
        protected CharSequence s;

        public StringContextIterator(CharSequence src) {
            this.s = src;
            this.limit = src.length();
            this.index = 0;
            this.cpLimit = 0;
            this.cpStart = 0;
            this.dir = 0;
        }

        public StringContextIterator(CharSequence src, int cpStart2, int cpLimit2) {
            this.s = src;
            this.index = 0;
            this.limit = src.length();
            this.cpStart = cpStart2;
            this.cpLimit = cpLimit2;
            this.dir = 0;
        }

        public void setLimit(int lim) {
            if (lim < 0 || lim > this.s.length()) {
                this.limit = this.s.length();
            } else {
                this.limit = lim;
            }
        }

        public void moveToLimit() {
            int i = this.limit;
            this.cpLimit = i;
            this.cpStart = i;
        }

        public int nextCaseMapCP() {
            int i = this.cpLimit;
            this.cpStart = i;
            if (i >= this.limit) {
                return -1;
            }
            int c = Character.codePointAt(this.s, i);
            this.cpLimit += Character.charCount(c);
            return c;
        }

        public void setCPStartAndLimit(int s2, int l) {
            this.cpStart = s2;
            this.cpLimit = l;
            this.dir = 0;
        }

        public int getCPStart() {
            return this.cpStart;
        }

        public int getCPLimit() {
            return this.cpLimit;
        }

        public int getCPLength() {
            return this.cpLimit - this.cpStart;
        }

        @Override // android.icu.impl.UCaseProps.ContextIterator
        public void reset(int direction) {
            if (direction > 0) {
                this.dir = 1;
                this.index = this.cpLimit;
            } else if (direction < 0) {
                this.dir = -1;
                this.index = this.cpStart;
            } else {
                this.dir = 0;
                this.index = 0;
            }
        }

        @Override // android.icu.impl.UCaseProps.ContextIterator
        public int next() {
            int i;
            if (this.dir > 0 && this.index < this.s.length()) {
                int c = Character.codePointAt(this.s, this.index);
                this.index += Character.charCount(c);
                return c;
            } else if (this.dir >= 0 || (i = this.index) <= 0) {
                return -1;
            } else {
                int c2 = Character.codePointBefore(this.s, i);
                this.index -= Character.charCount(c2);
                return c2;
            }
        }
    }

    public static int addTitleAdjustmentOption(int options, int newOption) {
        int adjOptions = options & TITLECASE_ADJUSTMENT_MASK;
        if (adjOptions == 0 || adjOptions == newOption) {
            return options | newOption;
        }
        throw new IllegalArgumentException("multiple titlecasing index adjustment options");
    }

    private static boolean isLNS(int c) {
        int gc = UCharacterProperty.INSTANCE.getType(c);
        if (((1 << gc) & LNS) != 0) {
            return true;
        }
        if (gc != 4 || UCaseProps.INSTANCE.getType(c) == 0) {
            return false;
        }
        return true;
    }

    public static int addTitleIteratorOption(int options, int newOption) {
        int iterOptions = options & 224;
        if (iterOptions == 0 || iterOptions == newOption) {
            return options | newOption;
        }
        throw new IllegalArgumentException("multiple titlecasing iterator options");
    }

    public static BreakIterator getTitleBreakIterator(Locale locale, int options, BreakIterator iter) {
        int options2 = options & 224;
        if (options2 != 0 && iter != null) {
            throw new IllegalArgumentException("titlecasing iterator option together with an explicit iterator");
        } else if (iter != null) {
            return iter;
        } else {
            if (options2 == 0) {
                return BreakIterator.getWordInstance(locale);
            }
            if (options2 == 32) {
                return new WholeStringBreakIterator();
            }
            if (options2 == 64) {
                return BreakIterator.getSentenceInstance(locale);
            }
            throw new IllegalArgumentException("unknown titlecasing iterator option");
        }
    }

    public static BreakIterator getTitleBreakIterator(ULocale locale, int options, BreakIterator iter) {
        int options2 = options & 224;
        if (options2 != 0 && iter != null) {
            throw new IllegalArgumentException("titlecasing iterator option together with an explicit iterator");
        } else if (iter != null) {
            return iter;
        } else {
            if (options2 == 0) {
                return BreakIterator.getWordInstance(locale);
            }
            if (options2 == 32) {
                return new WholeStringBreakIterator();
            }
            if (options2 == 64) {
                return BreakIterator.getSentenceInstance(locale);
            }
            throw new IllegalArgumentException("unknown titlecasing iterator option");
        }
    }

    private static final class WholeStringBreakIterator extends BreakIterator {
        private int length;

        private WholeStringBreakIterator() {
        }

        private static void notImplemented() {
            throw new UnsupportedOperationException("should not occur");
        }

        @Override // android.icu.text.BreakIterator
        public int first() {
            return 0;
        }

        @Override // android.icu.text.BreakIterator
        public int last() {
            notImplemented();
            return 0;
        }

        @Override // android.icu.text.BreakIterator
        public int next(int n) {
            notImplemented();
            return 0;
        }

        @Override // android.icu.text.BreakIterator
        public int next() {
            return this.length;
        }

        @Override // android.icu.text.BreakIterator
        public int previous() {
            notImplemented();
            return 0;
        }

        @Override // android.icu.text.BreakIterator
        public int following(int offset) {
            notImplemented();
            return 0;
        }

        @Override // android.icu.text.BreakIterator
        public int current() {
            notImplemented();
            return 0;
        }

        @Override // android.icu.text.BreakIterator
        public CharacterIterator getText() {
            notImplemented();
            return null;
        }

        @Override // android.icu.text.BreakIterator
        public void setText(CharacterIterator newText) {
            this.length = newText.getEndIndex();
        }

        @Override // android.icu.text.BreakIterator
        public void setText(CharSequence newText) {
            this.length = newText.length();
        }

        @Override // android.icu.text.BreakIterator
        public void setText(String newText) {
            this.length = newText.length();
        }
    }

    private static int appendCodePoint(Appendable a, int c) throws IOException {
        if (c <= 65535) {
            a.append((char) c);
            return 1;
        }
        a.append((char) ((c >> 10) + 55232));
        a.append((char) ((c & 1023) + UTF16.TRAIL_SURROGATE_MIN_VALUE));
        return 2;
    }

    /* access modifiers changed from: private */
    public static void appendResult(int result, Appendable dest, int cpLength, int options, Edits edits) throws IOException {
        if (result < 0) {
            if (edits != null) {
                edits.addUnchanged(cpLength);
            }
            if ((options & 16384) == 0) {
                appendCodePoint(dest, ~result);
            }
        } else if (result > 31) {
            int length = appendCodePoint(dest, result);
            if (edits != null) {
                edits.addReplace(cpLength, length);
            }
        } else if (edits != null) {
            edits.addReplace(cpLength, result);
        }
    }

    private static final void appendUnchanged(CharSequence src, int start, int length, Appendable dest, int options, Edits edits) throws IOException {
        if (length > 0) {
            if (edits != null) {
                edits.addUnchanged(length);
            }
            if ((options & 16384) == 0) {
                dest.append(src, start, start + length);
            }
        }
    }

    private static String applyEdits(CharSequence src, StringBuilder replacementChars, Edits edits) {
        if (!edits.hasChanges()) {
            return src.toString();
        }
        StringBuilder result = new StringBuilder(src.length() + edits.lengthDelta());
        Edits.Iterator ei = edits.getCoarseIterator();
        while (ei.next()) {
            if (ei.hasChange()) {
                int i = ei.replacementIndex();
                result.append((CharSequence) replacementChars, i, ei.newLength() + i);
            } else {
                int i2 = ei.sourceIndex();
                result.append(src, i2, ei.oldLength() + i2);
            }
        }
        return result.toString();
    }

    /* JADX INFO: Multiple debug info for r14v1 'srcIndex'  int: [D('cpStart' int), D('srcIndex' int)] */
    /* JADX INFO: Multiple debug info for r1v5 int: [D('props' int), D('srcIndex' int)] */
    /* JADX INFO: Multiple debug info for r1v16 byte: [D('d' byte), D('delta' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void internalToLower(int r19, int r20, java.lang.CharSequence r21, int r22, int r23, android.icu.impl.CaseMapImpl.StringContextIterator r24, java.lang.Appendable r25, android.icu.text.Edits r26) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 285
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.internalToLower(int, int, java.lang.CharSequence, int, int, android.icu.impl.CaseMapImpl$StringContextIterator, java.lang.Appendable, android.icu.text.Edits):void");
    }

    /* JADX INFO: Multiple debug info for r12v1 'srcIndex'  int: [D('cpStart' int), D('srcIndex' int)] */
    /* JADX INFO: Multiple debug info for r1v6 int: [D('props' int), D('srcIndex' int)] */
    /* JADX INFO: Multiple debug info for r1v14 byte: [D('d' byte), D('delta' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void internalToUpper(int r17, int r18, java.lang.CharSequence r19, java.lang.Appendable r20, android.icu.text.Edits r21) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 236
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.internalToUpper(int, int, java.lang.CharSequence, java.lang.Appendable, android.icu.text.Edits):void");
    }

    public static String toLower(int caseLocale, int options, CharSequence src) {
        if (src.length() > 100 || (options & 16384) != 0) {
            return ((StringBuilder) toLower(caseLocale, options, src, new StringBuilder(src.length()), null)).toString();
        }
        if (src.length() == 0) {
            return src.toString();
        }
        Edits edits = new Edits();
        return applyEdits(src, (StringBuilder) toLower(caseLocale, options | 16384, src, new StringBuilder(), edits), edits);
    }

    public static <A extends Appendable> A toLower(int caseLocale, int options, CharSequence src, A dest, Edits edits) {
        if (edits != null) {
            try {
                edits.reset();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        internalToLower(caseLocale, options, src, 0, src.length(), null, dest, edits);
        return dest;
    }

    public static String toUpper(int caseLocale, int options, CharSequence src) {
        if (src.length() > 100 || (options & 16384) != 0) {
            return ((StringBuilder) toUpper(caseLocale, options, src, new StringBuilder(src.length()), null)).toString();
        }
        if (src.length() == 0) {
            return src.toString();
        }
        Edits edits = new Edits();
        return applyEdits(src, (StringBuilder) toUpper(caseLocale, options | 16384, src, new StringBuilder(), edits), edits);
    }

    public static <A extends Appendable> A toUpper(int caseLocale, int options, CharSequence src, A dest, Edits edits) {
        if (edits != null) {
            try {
                edits.reset();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        if (caseLocale == 4) {
            return (A) GreekUpper.toUpper(options, src, dest, edits);
        }
        internalToUpper(caseLocale, options, src, dest, edits);
        return dest;
    }

    public static String toTitle(int caseLocale, int options, BreakIterator iter, CharSequence src) {
        if (src.length() > 100 || (options & 16384) != 0) {
            return ((StringBuilder) toTitle(caseLocale, options, iter, src, new StringBuilder(src.length()), null)).toString();
        }
        if (src.length() == 0) {
            return src.toString();
        }
        Edits edits = new Edits();
        return applyEdits(src, (StringBuilder) toTitle(caseLocale, options | 16384, iter, src, new StringBuilder(), edits), edits);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b A[Catch:{ IOException -> 0x015e }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011b A[Catch:{ IOException -> 0x015e }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0155  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <A extends java.lang.Appendable> A toTitle(int r21, int r22, android.icu.text.BreakIterator r23, java.lang.CharSequence r24, A r25, android.icu.text.Edits r26) {
        /*
        // Method dump skipped, instructions count: 357
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.toTitle(int, int, android.icu.text.BreakIterator, java.lang.CharSequence, java.lang.Appendable, android.icu.text.Edits):java.lang.Appendable");
    }

    public static String fold(int options, CharSequence src) {
        if (src.length() > 100 || (options & 16384) != 0) {
            return ((StringBuilder) fold(options, src, new StringBuilder(src.length()), null)).toString();
        }
        if (src.length() == 0) {
            return src.toString();
        }
        Edits edits = new Edits();
        return applyEdits(src, (StringBuilder) fold(options | 16384, src, new StringBuilder(), edits), edits);
    }

    public static <A extends Appendable> A fold(int options, CharSequence src, A dest, Edits edits) {
        if (edits != null) {
            try {
                edits.reset();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        internalToLower(-1, options, src, 0, src.length(), null, dest, edits);
        return dest;
    }

    /* access modifiers changed from: private */
    public static final class GreekUpper {
        private static final int AFTER_CASED = 1;
        private static final int AFTER_VOWEL_WITH_ACCENT = 2;
        private static final int HAS_ACCENT = 16384;
        private static final int HAS_COMBINING_DIALYTIKA = 65536;
        private static final int HAS_DIALYTIKA = 32768;
        private static final int HAS_EITHER_DIALYTIKA = 98304;
        private static final int HAS_OTHER_GREEK_DIACRITIC = 131072;
        private static final int HAS_VOWEL = 4096;
        private static final int HAS_VOWEL_AND_ACCENT = 20480;
        private static final int HAS_VOWEL_AND_ACCENT_AND_DIALYTIKA = 53248;
        private static final int HAS_YPOGEGRAMMENI = 8192;
        private static final int UPPER_MASK = 1023;
        private static final char[] data0370 = {880, 880, 882, 882, 0, 0, 886, 886, 0, 0, 890, 1021, 1022, 1023, 0, 895, 0, 0, 0, 0, 0, 0, 21393, 0, 21397, 21399, 21401, 0, 21407, 0, 21413, 21417, 54169, 5009, 914, 915, 916, 5013, 918, 5015, 920, 5017, 922, 923, 924, 925, 926, 5023, 928, 929, 0, 931, 932, 5029, 934, 935, 936, data2126, 37785, 37797, 21393, 21397, 21399, 21401, 54181, 5009, 914, 915, 916, 5013, 918, 5015, 920, 5017, 922, 923, 924, 925, 926, 5023, 928, 929, 931, 931, 932, 5029, 934, 935, 936, data2126, 37785, 37797, 21407, 21413, 21417, 975, 914, 920, 978, 17362, 33746, 934, 928, 975, 984, 984, 986, 986, 988, 988, 990, 990, 992, 992, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 922, 929, 1017, 895, 1012, 5013, 0, 1015, 1015, 1017, 1018, 1018, 1020, 1021, 1022, 1023};
        private static final char[] data1F00 = {5009, 5009, 21393, 21393, 21393, 21393, 21393, 21393, 5009, 5009, 21393, 21393, 21393, 21393, 21393, 21393, 5013, 5013, 21397, 21397, 21397, 21397, 0, 0, 5013, 5013, 21397, 21397, 21397, 21397, 0, 0, 5015, 5015, 21399, 21399, 21399, 21399, 21399, 21399, 5015, 5015, 21399, 21399, 21399, 21399, 21399, 21399, 5017, 5017, 21401, 21401, 21401, 21401, 21401, 21401, 5017, 5017, 21401, 21401, 21401, 21401, 21401, 21401, 5023, 5023, 21407, 21407, 21407, 21407, 0, 0, 5023, 5023, 21407, 21407, 21407, 21407, 0, 0, 5029, 5029, 21413, 21413, 21413, 21413, 21413, 21413, 0, 5029, 0, 21413, 0, 21413, 0, 21413, data2126, data2126, 21417, 21417, 21417, 21417, 21417, 21417, data2126, data2126, 21417, 21417, 21417, 21417, 21417, 21417, 21393, 21393, 21397, 21397, 21399, 21399, 21401, 21401, 21407, 21407, 21413, 21413, 21417, 21417, 0, 0, 13201, 13201, 29585, 29585, 29585, 29585, 29585, 29585, 13201, 13201, 29585, 29585, 29585, 29585, 29585, 29585, 13207, 13207, 29591, 29591, 29591, 29591, 29591, 29591, 13207, 13207, 29591, 29591, 29591, 29591, 29591, 29591, 13225, 13225, 29609, 29609, 29609, 29609, 29609, 29609, 13225, 13225, 29609, 29609, 29609, 29609, 29609, 29609, 5009, 5009, 29585, 13201, 29585, 0, 21393, 29585, 5009, 5009, 21393, 21393, 13201, 0, 5017, 0, 0, 0, 29591, 13207, 29591, 0, 21399, 29591, 21397, 21397, 21399, 21399, 13207, 0, 0, 0, 5017, 5017, 54169, 54169, 0, 0, 21401, 54169, 5017, 5017, 21401, 21401, 0, 0, 0, 0, 5029, 5029, 54181, 54181, 929, 929, 21413, 54181, 5029, 5029, 21413, 21413, 929, 0, 0, 0, 0, 0, 29609, 13225, 29609, 0, 21417, 29609, 21407, 21407, 21417, 21417, 13225, 0, 0, 0};
        private static final char data2126 = 5033;

        private GreekUpper() {
        }

        private static final int getLetterData(int c) {
            if (c < 880 || 8486 < c || (1023 < c && c < 7936)) {
                return 0;
            }
            if (c <= 1023) {
                return data0370[c - 880];
            }
            if (c <= 8191) {
                return data1F00[c - 7936];
            }
            if (c == 8486) {
                return 5033;
            }
            return 0;
        }

        private static final int getDiacriticData(int c) {
            if (c == 774) {
                return 131072;
            }
            if (c == 776) {
                return 65536;
            }
            if (c == 785) {
                return 16384;
            }
            if (c == 787 || c == 788) {
                return 131072;
            }
            switch (c) {
                case 768:
                case 769:
                case 770:
                case 771:
                    return 16384;
                case 772:
                    return 131072;
                default:
                    switch (c) {
                        case 834:
                            return 16384;
                        case 835:
                            return 131072;
                        case 836:
                            return 81920;
                        case 837:
                            return 8192;
                        default:
                            return 0;
                    }
            }
        }

        private static boolean isFollowedByCasedLetter(CharSequence s, int i) {
            while (i < s.length()) {
                int c = Character.codePointAt(s, i);
                int type = UCaseProps.INSTANCE.getTypeOrIgnorable(c);
                if ((type & 4) != 0) {
                    i += Character.charCount(c);
                } else if (type != 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static <A extends Appendable> A toUpper(int options, CharSequence src, A dest, Edits edits) throws IOException {
            int nextState;
            boolean change;
            boolean state;
            int diacriticData;
            CharSequence charSequence = src;
            int state2 = 0;
            int i = 0;
            while (i < src.length()) {
                int c = Character.codePointAt(charSequence, i);
                int nextIndex = Character.charCount(c) + i;
                int nextState2 = 0;
                int type = UCaseProps.INSTANCE.getTypeOrIgnorable(c);
                if ((type & 4) != 0) {
                    nextState2 = 0 | (state2 & 1);
                } else if (type != 0) {
                    nextState2 = 0 | 1;
                }
                int data = getLetterData(c);
                if (data > 0) {
                    int upper = data & 1023;
                    if (!((data & 4096) == 0 || (state2 & 2) == 0 || (upper != 921 && upper != 933))) {
                        data |= 32768;
                    }
                    int numYpogegrammeni = 0;
                    if ((data & 8192) != 0) {
                        numYpogegrammeni = 1;
                    }
                    while (nextIndex < src.length() && (diacriticData = getDiacriticData(charSequence.charAt(nextIndex))) != 0) {
                        data |= diacriticData;
                        if ((diacriticData & 8192) != 0) {
                            numYpogegrammeni++;
                        }
                        nextIndex++;
                    }
                    if ((HAS_VOWEL_AND_ACCENT_AND_DIALYTIKA & data) == HAS_VOWEL_AND_ACCENT) {
                        nextState2 |= 2;
                    }
                    boolean addTonos = false;
                    if (upper == 919 && (data & 16384) != 0 && numYpogegrammeni == 0 && (state2 & 1) == 0 && !isFollowedByCasedLetter(charSequence, nextIndex)) {
                        if (i == nextIndex) {
                            upper = 905;
                        } else {
                            addTonos = true;
                        }
                    } else if ((32768 & data) != 0) {
                        if (upper == 921) {
                            upper = 938;
                            data &= -98305;
                        } else if (upper == 933) {
                            upper = 939;
                            data &= -98305;
                        }
                    }
                    if (edits == null && (options & 16384) == 0) {
                        change = true;
                        nextState = nextState2;
                    } else {
                        boolean z = false;
                        boolean change2 = charSequence.charAt(i) != upper || numYpogegrammeni > 0;
                        int i2 = i + 1;
                        if ((data & HAS_EITHER_DIALYTIKA) != 0) {
                            if (i2 < nextIndex) {
                                nextState = nextState2;
                                if (charSequence.charAt(i2) == 776) {
                                    state = false;
                                    change2 |= state;
                                    i2++;
                                }
                            } else {
                                nextState = nextState2;
                            }
                            state = true;
                            change2 |= state;
                            i2++;
                        } else {
                            nextState = nextState2;
                        }
                        if (addTonos) {
                            change2 |= i2 >= nextIndex || charSequence.charAt(i2) != 769;
                            i2++;
                        }
                        int oldLength = nextIndex - i;
                        int newLength = (i2 - i) + numYpogegrammeni;
                        change = change2 | (oldLength != newLength);
                        if (!change) {
                            if (edits != null) {
                                edits.addUnchanged(oldLength);
                            }
                            if ((options & 16384) == 0) {
                                z = true;
                            }
                            change = z;
                        } else if (edits != null) {
                            edits.addReplace(oldLength, newLength);
                        }
                    }
                    if (change) {
                        dest.append((char) upper);
                        if ((data & HAS_EITHER_DIALYTIKA) != 0) {
                            dest.append(776);
                        }
                        if (addTonos) {
                            dest.append(769);
                        }
                        while (numYpogegrammeni > 0) {
                            dest.append(921);
                            numYpogegrammeni--;
                        }
                    }
                } else {
                    CaseMapImpl.appendResult(UCaseProps.INSTANCE.toFullUpper(c, null, dest, 4), dest, nextIndex - i, options, edits);
                    nextState = nextState2;
                }
                i = nextIndex;
                state2 = nextState;
                charSequence = src;
            }
            return dest;
        }
    }
}
