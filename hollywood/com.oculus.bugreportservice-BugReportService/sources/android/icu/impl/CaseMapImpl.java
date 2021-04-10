package android.icu.impl;

import android.icu.impl.UCaseProps;
import android.icu.text.BreakIterator;
import android.icu.text.Edits;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.ULocale;
import java.io.IOException;
import java.text.CharacterIterator;

public final class CaseMapImpl {
    private static final Trie2_16 CASE_TRIE = UCaseProps.getTrie();

    public static final class StringContextIterator implements UCaseProps.ContextIterator {
        protected int cpLimit;
        protected int cpStart;
        protected int dir;
        protected int index;
        protected int limit;
        protected CharSequence s;

        public StringContextIterator(CharSequence charSequence) {
            this.s = charSequence;
            this.limit = charSequence.length();
            this.index = 0;
            this.cpLimit = 0;
            this.cpStart = 0;
            this.dir = 0;
        }

        public StringContextIterator(CharSequence charSequence, int i, int i2) {
            this.s = charSequence;
            this.index = 0;
            this.limit = charSequence.length();
            this.cpStart = i;
            this.cpLimit = i2;
            this.dir = 0;
        }

        public void setLimit(int i) {
            if (i < 0 || i > this.s.length()) {
                this.limit = this.s.length();
            } else {
                this.limit = i;
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
            int codePointAt = Character.codePointAt(this.s, i);
            this.cpLimit += Character.charCount(codePointAt);
            return codePointAt;
        }

        public void setCPStartAndLimit(int i, int i2) {
            this.cpStart = i;
            this.cpLimit = i2;
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
        public void reset(int i) {
            if (i > 0) {
                this.dir = 1;
                this.index = this.cpLimit;
            } else if (i < 0) {
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
                int codePointAt = Character.codePointAt(this.s, this.index);
                this.index += Character.charCount(codePointAt);
                return codePointAt;
            } else if (this.dir >= 0 || (i = this.index) <= 0) {
                return -1;
            } else {
                int codePointBefore = Character.codePointBefore(this.s, i);
                this.index -= Character.charCount(codePointBefore);
                return codePointBefore;
            }
        }
    }

    private static boolean isLNS(int i) {
        int type = UCharacterProperty.INSTANCE.getType(i);
        if (((1 << type) & 251792942) != 0) {
            return true;
        }
        if (type != 4 || UCaseProps.INSTANCE.getType(i) == 0) {
            return false;
        }
        return true;
    }

    public static BreakIterator getTitleBreakIterator(ULocale uLocale, int i, BreakIterator breakIterator) {
        int i2 = i & 224;
        if (i2 != 0 && breakIterator != null) {
            throw new IllegalArgumentException("titlecasing iterator option together with an explicit iterator");
        } else if (breakIterator != null) {
            return breakIterator;
        } else {
            if (i2 == 0) {
                return BreakIterator.getWordInstance(uLocale);
            }
            if (i2 == 32) {
                return new WholeStringBreakIterator();
            }
            if (i2 == 64) {
                return BreakIterator.getSentenceInstance(uLocale);
            }
            throw new IllegalArgumentException("unknown titlecasing iterator option");
        }
    }

    private static final class WholeStringBreakIterator extends BreakIterator {
        private int length;

        @Override // android.icu.text.BreakIterator
        public int first() {
            return 0;
        }

        private WholeStringBreakIterator() {
        }

        private static void notImplemented() {
            throw new UnsupportedOperationException("should not occur");
        }

        @Override // android.icu.text.BreakIterator
        public int next() {
            return this.length;
        }

        @Override // android.icu.text.BreakIterator
        public CharacterIterator getText() {
            notImplemented();
            throw null;
        }

        @Override // android.icu.text.BreakIterator
        public void setText(CharacterIterator characterIterator) {
            this.length = characterIterator.getEndIndex();
        }

        @Override // android.icu.text.BreakIterator
        public void setText(String str) {
            this.length = str.length();
        }
    }

    private static int appendCodePoint(Appendable appendable, int i) {
        if (i <= 65535) {
            appendable.append((char) i);
            return 1;
        }
        appendable.append((char) ((i >> 10) + 55232));
        appendable.append((char) ((i & 1023) + 56320));
        return 2;
    }

    /* access modifiers changed from: private */
    public static void appendResult(int i, Appendable appendable, int i2, int i3, Edits edits) {
        if (i < 0) {
            if (edits != null) {
                edits.addUnchanged(i2);
            }
            if ((i3 & 16384) == 0) {
                appendCodePoint(appendable, ~i);
            }
        } else if (i > 31) {
            int appendCodePoint = appendCodePoint(appendable, i);
            if (edits != null) {
                edits.addReplace(i2, appendCodePoint);
            }
        } else if (edits != null) {
            edits.addReplace(i2, i);
        }
    }

    private static final void appendUnchanged(CharSequence charSequence, int i, int i2, Appendable appendable, int i3, Edits edits) {
        if (i2 > 0) {
            if (edits != null) {
                edits.addUnchanged(i2);
            }
            if ((i3 & 16384) == 0) {
                appendable.append(charSequence, i, i2 + i);
            }
        }
    }

    private static String applyEdits(CharSequence charSequence, StringBuilder sb, Edits edits) {
        if (!edits.hasChanges()) {
            return charSequence.toString();
        }
        StringBuilder sb2 = new StringBuilder(charSequence.length() + edits.lengthDelta());
        Edits.Iterator coarseIterator = edits.getCoarseIterator();
        while (coarseIterator.next()) {
            if (coarseIterator.hasChange()) {
                int replacementIndex = coarseIterator.replacementIndex();
                sb2.append((CharSequence) sb, replacementIndex, coarseIterator.newLength() + replacementIndex);
            } else {
                int sourceIndex = coarseIterator.sourceIndex();
                sb2.append(charSequence, sourceIndex, coarseIterator.oldLength() + sourceIndex);
            }
        }
        return sb2.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c5, code lost:
        if (r3 == 0) goto L_0x00e7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void internalToLower(int r18, int r19, java.lang.CharSequence r20, int r21, int r22, android.icu.impl.CaseMapImpl.StringContextIterator r23, java.lang.Appendable r24, android.icu.text.Edits r25) {
        /*
        // Method dump skipped, instructions count: 235
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.internalToLower(int, int, java.lang.CharSequence, int, int, android.icu.impl.CaseMapImpl$StringContextIterator, java.lang.Appendable, android.icu.text.Edits):void");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x00b3 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v15, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0019 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void internalToUpper(int r16, int r17, java.lang.CharSequence r18, java.lang.Appendable r19, android.icu.text.Edits r20) {
        /*
        // Method dump skipped, instructions count: 206
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.internalToUpper(int, int, java.lang.CharSequence, java.lang.Appendable, android.icu.text.Edits):void");
    }

    public static String toLower(int i, int i2, CharSequence charSequence) {
        if (charSequence.length() > 100 || (i2 & 16384) != 0) {
            StringBuilder sb = new StringBuilder(charSequence.length());
            toLower(i, i2, charSequence, sb, null);
            return sb.toString();
        } else if (charSequence.length() == 0) {
            return charSequence.toString();
        } else {
            Edits edits = new Edits();
            StringBuilder sb2 = new StringBuilder();
            toLower(i, i2 | 16384, charSequence, sb2, edits);
            return applyEdits(charSequence, sb2, edits);
        }
    }

    public static Appendable toLower(int i, int i2, CharSequence charSequence, Appendable appendable, Edits edits) {
        if (edits != null) {
            try {
                edits.reset();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        internalToLower(i, i2, charSequence, 0, charSequence.length(), null, appendable, edits);
        return appendable;
    }

    public static String toUpper(int i, int i2, CharSequence charSequence) {
        if (charSequence.length() > 100 || (i2 & 16384) != 0) {
            StringBuilder sb = new StringBuilder(charSequence.length());
            toUpper(i, i2, charSequence, sb, null);
            return sb.toString();
        } else if (charSequence.length() == 0) {
            return charSequence.toString();
        } else {
            Edits edits = new Edits();
            StringBuilder sb2 = new StringBuilder();
            toUpper(i, i2 | 16384, charSequence, sb2, edits);
            return applyEdits(charSequence, sb2, edits);
        }
    }

    public static Appendable toUpper(int i, int i2, CharSequence charSequence, Appendable appendable, Edits edits) {
        if (edits != null) {
            try {
                edits.reset();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        if (i == 4) {
            GreekUpper.toUpper(i2, charSequence, appendable, edits);
            return appendable;
        }
        internalToUpper(i, i2, charSequence, appendable, edits);
        return appendable;
    }

    public static String toTitle(int i, int i2, BreakIterator breakIterator, CharSequence charSequence) {
        if (charSequence.length() > 100 || (i2 & 16384) != 0) {
            StringBuilder sb = new StringBuilder(charSequence.length());
            toTitle(i, i2, breakIterator, charSequence, sb, null);
            return sb.toString();
        } else if (charSequence.length() == 0) {
            return charSequence.toString();
        } else {
            Edits edits = new Edits();
            int i3 = i2 | 16384;
            StringBuilder sb2 = new StringBuilder();
            toTitle(i, i3, breakIterator, charSequence, sb2, edits);
            return applyEdits(charSequence, sb2, edits);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039 A[Catch:{ IOException -> 0x0112 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Appendable toTitle(int r19, int r20, android.icu.text.BreakIterator r21, java.lang.CharSequence r22, java.lang.Appendable r23, android.icu.text.Edits r24) {
        /*
        // Method dump skipped, instructions count: 281
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.toTitle(int, int, android.icu.text.BreakIterator, java.lang.CharSequence, java.lang.Appendable, android.icu.text.Edits):java.lang.Appendable");
    }

    public static String fold(int i, CharSequence charSequence) {
        if (charSequence.length() > 100 || (i & 16384) != 0) {
            StringBuilder sb = new StringBuilder(charSequence.length());
            fold(i, charSequence, sb, null);
            return sb.toString();
        } else if (charSequence.length() == 0) {
            return charSequence.toString();
        } else {
            Edits edits = new Edits();
            StringBuilder sb2 = new StringBuilder();
            fold(i | 16384, charSequence, sb2, edits);
            return applyEdits(charSequence, sb2, edits);
        }
    }

    public static Appendable fold(int i, CharSequence charSequence, Appendable appendable, Edits edits) {
        if (edits != null) {
            try {
                edits.reset();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
        internalToLower(-1, i, charSequence, 0, charSequence.length(), null, appendable, edits);
        return appendable;
    }

    /* access modifiers changed from: private */
    public static final class GreekUpper {
        private static final char[] data0370 = {880, 880, 882, 882, 0, 0, 886, 886, 0, 0, 890, 1021, 1022, 1023, 0, 895, 0, 0, 0, 0, 0, 0, 21393, 0, 21397, 21399, 21401, 0, 21407, 0, 21413, 21417, 54169, 5009, 914, 915, 916, 5013, 918, 5015, 920, 5017, 922, 923, 924, 925, 926, 5023, 928, 929, 0, 931, 932, 5029, 934, 935, 936, 5033, 37785, 37797, 21393, 21397, 21399, 21401, 54181, 5009, 914, 915, 916, 5013, 918, 5015, 920, 5017, 922, 923, 924, 925, 926, 5023, 928, 929, 931, 931, 932, 5029, 934, 935, 936, 5033, 37785, 37797, 21407, 21413, 21417, 975, 914, 920, 978, 17362, 33746, 934, 928, 975, 984, 984, 986, 986, 988, 988, 990, 990, 992, 992, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 922, 929, 1017, 895, 1012, 5013, 0, 1015, 1015, 1017, 1018, 1018, 1020, 1021, 1022, 1023};
        private static final char[] data1F00 = {5009, 5009, 21393, 21393, 21393, 21393, 21393, 21393, 5009, 5009, 21393, 21393, 21393, 21393, 21393, 21393, 5013, 5013, 21397, 21397, 21397, 21397, 0, 0, 5013, 5013, 21397, 21397, 21397, 21397, 0, 0, 5015, 5015, 21399, 21399, 21399, 21399, 21399, 21399, 5015, 5015, 21399, 21399, 21399, 21399, 21399, 21399, 5017, 5017, 21401, 21401, 21401, 21401, 21401, 21401, 5017, 5017, 21401, 21401, 21401, 21401, 21401, 21401, 5023, 5023, 21407, 21407, 21407, 21407, 0, 0, 5023, 5023, 21407, 21407, 21407, 21407, 0, 0, 5029, 5029, 21413, 21413, 21413, 21413, 21413, 21413, 0, 5029, 0, 21413, 0, 21413, 0, 21413, 5033, 5033, 21417, 21417, 21417, 21417, 21417, 21417, 5033, 5033, 21417, 21417, 21417, 21417, 21417, 21417, 21393, 21393, 21397, 21397, 21399, 21399, 21401, 21401, 21407, 21407, 21413, 21413, 21417, 21417, 0, 0, 13201, 13201, 29585, 29585, 29585, 29585, 29585, 29585, 13201, 13201, 29585, 29585, 29585, 29585, 29585, 29585, 13207, 13207, 29591, 29591, 29591, 29591, 29591, 29591, 13207, 13207, 29591, 29591, 29591, 29591, 29591, 29591, 13225, 13225, 29609, 29609, 29609, 29609, 29609, 29609, 13225, 13225, 29609, 29609, 29609, 29609, 29609, 29609, 5009, 5009, 29585, 13201, 29585, 0, 21393, 29585, 5009, 5009, 21393, 21393, 13201, 0, 5017, 0, 0, 0, 29591, 13207, 29591, 0, 21399, 29591, 21397, 21397, 21399, 21399, 13207, 0, 0, 0, 5017, 5017, 54169, 54169, 0, 0, 21401, 54169, 5017, 5017, 21401, 21401, 0, 0, 0, 0, 5029, 5029, 54181, 54181, 929, 929, 21413, 54181, 5029, 5029, 21413, 21413, 929, 0, 0, 0, 0, 0, 29609, 13225, 29609, 0, 21417, 29609, 21407, 21407, 21417, 21417, 13225, 0, 0, 0};

        private static final int getDiacriticData(int i) {
            if (i == 774) {
                return 131072;
            }
            if (i == 776) {
                return 65536;
            }
            if (i == 785) {
                return 16384;
            }
            if (i == 787 || i == 788) {
                return 131072;
            }
            switch (i) {
                case 768:
                case 769:
                case 770:
                case 771:
                    return 16384;
                case 772:
                    return 131072;
                default:
                    switch (i) {
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

        private static final int getLetterData(int i) {
            if (i >= 880 && 8486 >= i && (1023 >= i || i >= 7936)) {
                if (i <= 1023) {
                    return data0370[i - 880];
                }
                if (i <= 8191) {
                    return data1F00[i - 7936];
                }
                if (i == 8486) {
                    return 5033;
                }
            }
            return 0;
        }

        private static boolean isFollowedByCasedLetter(CharSequence charSequence, int i) {
            while (true) {
                if (i >= charSequence.length()) {
                    break;
                }
                int codePointAt = Character.codePointAt(charSequence, i);
                int typeOrIgnorable = UCaseProps.INSTANCE.getTypeOrIgnorable(codePointAt);
                if ((typeOrIgnorable & 4) != 0) {
                    i += Character.charCount(codePointAt);
                } else if (typeOrIgnorable != 0) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:106:0x012f A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x00c4  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x00d5  */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x00ea  */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x00ec  */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x00f0  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x00f8  */
        /* JADX WARNING: Removed duplicated region for block: B:94:0x0106  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Appendable toUpper(int r18, java.lang.CharSequence r19, java.lang.Appendable r20, android.icu.text.Edits r21) {
            /*
            // Method dump skipped, instructions count: 309
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.CaseMapImpl.GreekUpper.toUpper(int, java.lang.CharSequence, java.lang.Appendable, android.icu.text.Edits):java.lang.Appendable");
        }
    }
}
