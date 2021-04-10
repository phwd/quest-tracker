package android.icu.impl;

import android.icu.text.UTF16;
import android.icu.text.UnicodeSet;
import android.icu.util.OutputInt;
import java.util.ArrayList;

public class UnicodeSetStringSpan {
    public static final int ALL = 127;
    static final short ALL_CP_CONTAINED = 255;
    public static final int BACK = 16;
    public static final int BACK_UTF16_CONTAINED = 18;
    public static final int BACK_UTF16_NOT_CONTAINED = 17;
    public static final int CONTAINED = 2;
    public static final int FWD = 32;
    public static final int FWD_UTF16_CONTAINED = 34;
    public static final int FWD_UTF16_NOT_CONTAINED = 33;
    static final short LONG_SPAN = 254;
    public static final int NOT_CONTAINED = 1;
    public static final int WITH_COUNT = 64;
    private boolean all;
    private final int maxLength16;
    private OffsetList offsets;
    private boolean someRelevant;
    private short[] spanLengths;
    private UnicodeSet spanNotSet;
    private UnicodeSet spanSet;
    private ArrayList<String> strings;

    public UnicodeSetStringSpan(UnicodeSet set, ArrayList<String> setStrings, int which) {
        int allocSize;
        int spanBackLengthsOffset;
        this.spanSet = new UnicodeSet(0, 1114111);
        this.strings = setStrings;
        this.all = which == 127;
        this.spanSet.retainAll(set);
        if ((which & 1) != 0) {
            this.spanNotSet = this.spanSet;
        }
        this.offsets = new OffsetList();
        int stringsLength = this.strings.size();
        int maxLength162 = 0;
        this.someRelevant = false;
        for (int i = 0; i < stringsLength; i++) {
            String string = this.strings.get(i);
            int length16 = string.length();
            if (this.spanSet.span(string, UnicodeSet.SpanCondition.CONTAINED) < length16) {
                this.someRelevant = true;
            }
            if (length16 > maxLength162) {
                maxLength162 = length16;
            }
        }
        this.maxLength16 = maxLength162;
        if (this.someRelevant || (which & 64) != 0) {
            if (this.all) {
                this.spanSet.freeze();
            }
            if (this.all) {
                allocSize = stringsLength * 2;
            } else {
                allocSize = stringsLength;
            }
            this.spanLengths = new short[allocSize];
            if (this.all) {
                spanBackLengthsOffset = stringsLength;
            } else {
                spanBackLengthsOffset = 0;
            }
            for (int i2 = 0; i2 < stringsLength; i2++) {
                String string2 = this.strings.get(i2);
                int length162 = string2.length();
                int spanLength = this.spanSet.span(string2, UnicodeSet.SpanCondition.CONTAINED);
                if (spanLength < length162) {
                    if ((which & 2) != 0) {
                        if ((which & 32) != 0) {
                            this.spanLengths[i2] = makeSpanLengthByte(spanLength);
                        }
                        if ((which & 16) != 0) {
                            this.spanLengths[spanBackLengthsOffset + i2] = makeSpanLengthByte(length162 - this.spanSet.spanBack(string2, length162, UnicodeSet.SpanCondition.CONTAINED));
                        }
                    } else {
                        short[] sArr = this.spanLengths;
                        sArr[spanBackLengthsOffset + i2] = 0;
                        sArr[i2] = 0;
                    }
                    if ((which & 1) != 0) {
                        if ((which & 32) != 0) {
                            addToSpanNotSet(string2.codePointAt(0));
                        }
                        if ((which & 16) != 0) {
                            addToSpanNotSet(string2.codePointBefore(length162));
                        }
                    }
                } else if (this.all) {
                    short[] sArr2 = this.spanLengths;
                    sArr2[spanBackLengthsOffset + i2] = ALL_CP_CONTAINED;
                    sArr2[i2] = ALL_CP_CONTAINED;
                } else {
                    this.spanLengths[i2] = ALL_CP_CONTAINED;
                }
            }
            if (this.all) {
                this.spanNotSet.freeze();
            }
        }
    }

    public UnicodeSetStringSpan(UnicodeSetStringSpan otherStringSpan, ArrayList<String> newParentSetStrings) {
        this.spanSet = otherStringSpan.spanSet;
        this.strings = newParentSetStrings;
        this.maxLength16 = otherStringSpan.maxLength16;
        this.someRelevant = otherStringSpan.someRelevant;
        this.all = true;
        if (Utility.sameObjects(otherStringSpan.spanNotSet, otherStringSpan.spanSet)) {
            this.spanNotSet = this.spanSet;
        } else {
            this.spanNotSet = (UnicodeSet) otherStringSpan.spanNotSet.clone();
        }
        this.offsets = new OffsetList();
        this.spanLengths = (short[]) otherStringSpan.spanLengths.clone();
    }

    public boolean needsStringSpanUTF16() {
        return this.someRelevant;
    }

    public boolean contains(int c) {
        return this.spanSet.contains(c);
    }

    private void addToSpanNotSet(int c) {
        if (Utility.sameObjects(this.spanNotSet, null) || Utility.sameObjects(this.spanNotSet, this.spanSet)) {
            if (!this.spanSet.contains(c)) {
                this.spanNotSet = this.spanSet.cloneAsThawed();
            } else {
                return;
            }
        }
        this.spanNotSet.add(c);
    }

    public int span(CharSequence s, int start, UnicodeSet.SpanCondition spanCondition) {
        if (spanCondition == UnicodeSet.SpanCondition.NOT_CONTAINED) {
            return spanNot(s, start, null);
        }
        int spanLimit = this.spanSet.span(s, start, UnicodeSet.SpanCondition.CONTAINED);
        if (spanLimit == s.length()) {
            return spanLimit;
        }
        return spanWithStrings(s, start, spanLimit, spanCondition);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:18:0x0050 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x00a4 */
    private synchronized int spanWithStrings(CharSequence s, int start, int spanLimit, UnicodeSet.SpanCondition spanCondition) {
        int initSize;
        int spanLimit2;
        int initSize2;
        UnicodeSet.SpanCondition spanCondition2 = spanCondition;
        synchronized (this) {
            int initSize3 = 0;
            if (spanCondition2 == UnicodeSet.SpanCondition.CONTAINED) {
                initSize3 = this.maxLength16;
            }
            this.offsets.setMaxLength(initSize3);
            int length = s.length();
            int stringsLength = this.strings.size();
            int spanLength = spanLimit - start;
            int rest = length - spanLimit;
            int pos = spanLimit;
            while (true) {
                char c = 254;
                if (spanCondition2 == UnicodeSet.SpanCondition.CONTAINED) {
                    int i = 0;
                    while (i < stringsLength) {
                        short s2 = this.spanLengths[i];
                        if (s2 != 255) {
                            String string = this.strings.get(i);
                            int length16 = string.length();
                            int overlap = s2;
                            if (s2 >= c) {
                                overlap = string.offsetByCodePoints(length16, -1);
                            }
                            if (overlap > spanLength) {
                                overlap = spanLength;
                            }
                            int inc = length16 - (overlap == 1 ? 1 : 0);
                            int overlap2 = overlap;
                            while (true) {
                                if (inc > rest) {
                                    break;
                                }
                                if (!this.offsets.containsOffset(inc) && matches16CPB(s, pos - overlap2, length, string, length16)) {
                                    if (inc == rest) {
                                        return length;
                                    }
                                    this.offsets.addOffset(inc);
                                }
                                if (overlap2 == 0) {
                                    break;
                                }
                                inc++;
                                overlap2 = (overlap2 == 1 ? 1 : 0) - 1;
                            }
                        }
                        i++;
                        c = 254;
                    }
                    initSize = initSize3;
                } else {
                    int maxOverlap = 0;
                    int i2 = 0;
                    int maxOverlap2 = 0;
                    while (i2 < stringsLength) {
                        short s3 = this.spanLengths[i2];
                        String string2 = this.strings.get(i2);
                        int length162 = string2.length();
                        int overlap3 = s3;
                        if (s3 >= 254) {
                            overlap3 = length162;
                        }
                        if (overlap3 > spanLength) {
                            overlap3 = spanLength;
                        }
                        int overlap4 = overlap3 == 1 ? 1 : 0;
                        int overlap5 = overlap3 == 1 ? 1 : 0;
                        int i3 = length162 - overlap4;
                        int overlap6 = overlap3 == 1 ? 1 : 0;
                        int overlap7 = overlap3 == 1 ? 1 : 0;
                        int overlap8 = overlap6;
                        int inc2 = i3;
                        while (true) {
                            if (inc2 > rest) {
                                initSize2 = initSize3;
                                break;
                            } else if (overlap8 < maxOverlap2) {
                                initSize2 = initSize3;
                                break;
                            } else {
                                if (overlap8 > maxOverlap2 || inc2 > maxOverlap) {
                                    initSize2 = initSize3;
                                    if (matches16CPB(s, pos - overlap8, length, string2, length162)) {
                                        maxOverlap2 = overlap8;
                                        maxOverlap = inc2;
                                        break;
                                    }
                                } else {
                                    initSize2 = initSize3;
                                }
                                overlap8--;
                                inc2++;
                                initSize3 = initSize2;
                            }
                        }
                        i2++;
                        initSize3 = initSize2;
                    }
                    initSize = initSize3;
                    if (!(maxOverlap == 0 && maxOverlap2 == 0)) {
                        pos += maxOverlap;
                        rest -= maxOverlap;
                        if (rest == 0) {
                            return length;
                        }
                        spanLength = 0;
                        spanCondition2 = spanCondition;
                        initSize3 = initSize;
                    }
                }
                if (spanLength != 0 || pos == 0) {
                    if (this.offsets.isEmpty()) {
                        return pos;
                    }
                } else if (this.offsets.isEmpty()) {
                    spanLimit2 = this.spanSet.span(s, pos, UnicodeSet.SpanCondition.CONTAINED);
                    spanLength = spanLimit2 - pos;
                    if (spanLength != rest && spanLength != 0) {
                        pos += spanLength;
                        rest -= spanLength;
                        spanCondition2 = spanCondition;
                        initSize3 = initSize;
                    }
                } else {
                    int spanLength2 = spanOne(this.spanSet, s, pos, rest);
                    if (spanLength2 > 0) {
                        if (spanLength2 == rest) {
                            return length;
                        }
                        pos += spanLength2;
                        rest -= spanLength2;
                        this.offsets.shift(spanLength2);
                        spanLength = 0;
                        spanCondition2 = spanCondition;
                        initSize3 = initSize;
                    }
                }
                int minOffset = this.offsets.popMinimum(null);
                pos += minOffset;
                rest -= minOffset;
                spanLength = 0;
                spanCondition2 = spanCondition;
                initSize3 = initSize;
            }
            return spanLimit2;
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    public int spanAndCount(java.lang.CharSequence r12, int r13, android.icu.text.UnicodeSet.SpanCondition r14, android.icu.util.OutputInt r15) {
        /*
            r11 = this;
            android.icu.text.UnicodeSet$SpanCondition r0 = android.icu.text.UnicodeSet.SpanCondition.NOT_CONTAINED
            if (r14 != r0) goto L_0x0009
            int r0 = r11.spanNot(r12, r13, r15)
            return r0
        L_0x0009:
            android.icu.text.UnicodeSet$SpanCondition r0 = android.icu.text.UnicodeSet.SpanCondition.CONTAINED
            if (r14 != r0) goto L_0x0012
            int r0 = r11.spanContainedAndCount(r12, r13, r15)
            return r0
        L_0x0012:
            java.util.ArrayList<java.lang.String> r0 = r11.strings
            int r0 = r0.size()
            int r1 = r12.length()
            r2 = r13
            int r3 = r1 - r13
            r4 = 0
        L_0x0020:
            if (r3 == 0) goto L_0x0054
            android.icu.text.UnicodeSet r5 = r11.spanSet
            int r5 = spanOne(r5, r12, r2, r3)
            if (r5 <= 0) goto L_0x002c
            r6 = r5
            goto L_0x002d
        L_0x002c:
            r6 = 0
        L_0x002d:
            r7 = 0
        L_0x002e:
            if (r7 >= r0) goto L_0x004a
            java.util.ArrayList<java.lang.String> r8 = r11.strings
            java.lang.Object r8 = r8.get(r7)
            java.lang.String r8 = (java.lang.String) r8
            int r9 = r8.length()
            if (r6 >= r9) goto L_0x0047
            if (r9 > r3) goto L_0x0047
            boolean r10 = matches16CPB(r12, r2, r1, r8, r9)
            if (r10 == 0) goto L_0x0047
            r6 = r9
        L_0x0047:
            int r7 = r7 + 1
            goto L_0x002e
        L_0x004a:
            if (r6 != 0) goto L_0x004f
            r15.value = r4
            return r2
        L_0x004f:
            int r4 = r4 + 1
            int r2 = r2 + r6
            int r3 = r3 - r6
            goto L_0x0020
        L_0x0054:
            r15.value = r4
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.UnicodeSetStringSpan.spanAndCount(java.lang.CharSequence, int, android.icu.text.UnicodeSet$SpanCondition, android.icu.util.OutputInt):int");
    }

    private synchronized int spanContainedAndCount(CharSequence s, int start, OutputInt outCount) {
        this.offsets.setMaxLength(this.maxLength16);
        int stringsLength = this.strings.size();
        int length = s.length();
        int pos = start;
        int rest = length - start;
        int count = 0;
        while (rest != 0) {
            int cpLength = spanOne(this.spanSet, s, pos, rest);
            if (cpLength > 0) {
                this.offsets.addOffsetAndCount(cpLength, count + 1);
            }
            for (int i = 0; i < stringsLength; i++) {
                String string = this.strings.get(i);
                int length16 = string.length();
                if (length16 <= rest && !this.offsets.hasCountAtOffset(length16, count + 1) && matches16CPB(s, pos, length, string, length16)) {
                    this.offsets.addOffsetAndCount(length16, count + 1);
                }
            }
            if (this.offsets.isEmpty()) {
                outCount.value = count;
                return pos;
            }
            int minOffset = this.offsets.popMinimum(outCount);
            count = outCount.value;
            pos += minOffset;
            rest -= minOffset;
        }
        outCount.value = count;
        return pos;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:27:0x0065 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x00b5 */
    public synchronized int spanBack(CharSequence s, int length, UnicodeSet.SpanCondition spanCondition) {
        UnicodeSet.SpanCondition spanCondition2 = spanCondition;
        synchronized (this) {
            if (spanCondition2 == UnicodeSet.SpanCondition.NOT_CONTAINED) {
                return spanNotBack(s, length);
            }
            int pos = this.spanSet.spanBack(s, length, UnicodeSet.SpanCondition.CONTAINED);
            int i = 0;
            if (pos == 0) {
                return 0;
            }
            int spanLength = length - pos;
            int initSize = 0;
            if (spanCondition2 == UnicodeSet.SpanCondition.CONTAINED) {
                initSize = this.maxLength16;
            }
            this.offsets.setMaxLength(initSize);
            int stringsLength = this.strings.size();
            int spanBackLengthsOffset = 0;
            if (this.all) {
                spanBackLengthsOffset = stringsLength;
            }
            while (true) {
                char c = 254;
                if (spanCondition2 == UnicodeSet.SpanCondition.CONTAINED) {
                    int i2 = 0;
                    while (i2 < stringsLength) {
                        short s2 = this.spanLengths[spanBackLengthsOffset + i2];
                        if (s2 != 255) {
                            String string = this.strings.get(i2);
                            int length16 = string.length();
                            int overlap = s2;
                            if (s2 >= c) {
                                overlap = length16 - string.offsetByCodePoints(i, 1);
                            }
                            if (overlap > spanLength) {
                                overlap = spanLength;
                            }
                            int dec = length16 - (overlap == 1 ? 1 : 0);
                            int overlap2 = overlap;
                            while (dec <= pos) {
                                if (!this.offsets.containsOffset(dec) && matches16CPB(s, pos - dec, length, string, length16)) {
                                    if (dec == pos) {
                                        return i;
                                    }
                                    this.offsets.addOffset(dec);
                                }
                                if (overlap2 == 0) {
                                    break;
                                }
                                dec++;
                                overlap2 = (overlap2 == 1 ? 1 : 0) - 1;
                            }
                            continue;
                        }
                        i2++;
                        c = 254;
                    }
                } else {
                    int maxOverlap = 0;
                    int maxOverlap2 = 0;
                    for (int i3 = 0; i3 < stringsLength; i3++) {
                        short s3 = this.spanLengths[spanBackLengthsOffset + i3];
                        String string2 = this.strings.get(i3);
                        int length162 = string2.length();
                        int overlap3 = s3;
                        if (s3 >= 254) {
                            overlap3 = length162;
                        }
                        if (overlap3 > spanLength) {
                            overlap3 = spanLength;
                        }
                        int overlap4 = overlap3 == 1 ? 1 : 0;
                        int overlap5 = overlap3 == 1 ? 1 : 0;
                        int i4 = length162 - overlap4;
                        int overlap6 = overlap3 == 1 ? 1 : 0;
                        int overlap7 = overlap3 == 1 ? 1 : 0;
                        int overlap8 = overlap6;
                        int dec2 = i4;
                        while (true) {
                            if (dec2 > pos || overlap8 < maxOverlap2) {
                                break;
                            } else if ((overlap8 > maxOverlap2 || dec2 > maxOverlap) && matches16CPB(s, pos - dec2, length, string2, length162)) {
                                maxOverlap2 = overlap8;
                                maxOverlap = dec2;
                                break;
                            } else {
                                overlap8--;
                                dec2++;
                            }
                        }
                    }
                    if (!(maxOverlap == 0 && maxOverlap2 == 0)) {
                        pos -= maxOverlap;
                        if (pos == 0) {
                            return 0;
                        }
                        spanLength = 0;
                        i = 0;
                        spanCondition2 = spanCondition;
                    }
                }
                if (spanLength != 0 || pos == length) {
                    if (this.offsets.isEmpty()) {
                        return pos;
                    }
                } else if (this.offsets.isEmpty()) {
                    pos = this.spanSet.spanBack(s, pos, UnicodeSet.SpanCondition.CONTAINED);
                    spanLength = pos - pos;
                    if (pos != 0 && spanLength != 0) {
                        spanCondition2 = spanCondition;
                        i = 0;
                    }
                } else {
                    int spanLength2 = spanOneBack(this.spanSet, s, pos);
                    if (spanLength2 > 0) {
                        if (spanLength2 == pos) {
                            return 0;
                        }
                        pos -= spanLength2;
                        this.offsets.shift(spanLength2);
                        spanLength = 0;
                        spanCondition2 = spanCondition;
                        i = 0;
                    }
                }
                pos -= this.offsets.popMinimum(null);
                spanLength = 0;
                spanCondition2 = spanCondition;
                i = 0;
            }
            return pos;
        }
    }

    private int spanNot(CharSequence s, int start, OutputInt outCount) {
        int spanLimit;
        int rest;
        int cpLength;
        String string;
        int length16;
        int length = s.length();
        int pos = start;
        int i = length - start;
        int stringsLength = this.strings.size();
        int count = 0;
        do {
            if (outCount == null) {
                spanLimit = this.spanNotSet.span(s, pos, UnicodeSet.SpanCondition.NOT_CONTAINED);
            } else {
                spanLimit = this.spanNotSet.spanAndCount(s, pos, UnicodeSet.SpanCondition.NOT_CONTAINED, outCount);
                int i2 = outCount.value + count;
                count = i2;
                outCount.value = i2;
            }
            if (spanLimit == length) {
                return length;
            }
            rest = length - spanLimit;
            cpLength = spanOne(this.spanSet, s, spanLimit, rest);
            if (cpLength > 0) {
                return spanLimit;
            }
            for (int i3 = 0; i3 < stringsLength; i3++) {
                if (this.spanLengths[i3] != 255 && (length16 = (string = this.strings.get(i3)).length()) <= rest && matches16CPB(s, spanLimit, length, string, length16)) {
                    return spanLimit;
                }
            }
            pos = spanLimit - cpLength;
            count++;
        } while (rest + cpLength != 0);
        if (outCount != null) {
            outCount.value = count;
        }
        return length;
    }

    private int spanNotBack(CharSequence s, int length) {
        String string;
        int length16;
        int pos = length;
        int stringsLength = this.strings.size();
        do {
            int pos2 = this.spanNotSet.spanBack(s, pos, UnicodeSet.SpanCondition.NOT_CONTAINED);
            if (pos2 == 0) {
                return 0;
            }
            int cpLength = spanOneBack(this.spanSet, s, pos2);
            if (cpLength > 0) {
                return pos2;
            }
            for (int i = 0; i < stringsLength; i++) {
                if (this.spanLengths[i] != 255 && (length16 = (string = this.strings.get(i)).length()) <= pos2 && matches16CPB(s, pos2 - length16, length, string, length16)) {
                    return pos2;
                }
            }
            pos = pos2 + cpLength;
        } while (pos != 0);
        return 0;
    }

    static short makeSpanLengthByte(int spanLength) {
        return spanLength < 254 ? (short) spanLength : LONG_SPAN;
    }

    private static boolean matches16(CharSequence s, int start, String t, int length) {
        int end = start + length;
        while (true) {
            int length2 = length - 1;
            if (length <= 0) {
                return true;
            }
            end--;
            if (s.charAt(end) != t.charAt(length2)) {
                return false;
            }
            length = length2;
        }
    }

    static boolean matches16CPB(CharSequence s, int start, int limit, String t, int tlength) {
        if (!matches16(s, start, t, tlength) || ((start > 0 && Character.isHighSurrogate(s.charAt(start - 1)) && Character.isLowSurrogate(s.charAt(start))) || (start + tlength < limit && Character.isHighSurrogate(s.charAt((start + tlength) - 1)) && Character.isLowSurrogate(s.charAt(start + tlength))))) {
            return false;
        }
        return true;
    }

    static int spanOne(UnicodeSet set, CharSequence s, int start, int length) {
        char c = s.charAt(start);
        if (c >= 55296 && c <= 56319 && length >= 2) {
            char c2 = s.charAt(start + 1);
            if (UTF16.isTrailSurrogate(c2)) {
                if (set.contains(Character.toCodePoint(c, c2))) {
                    return 2;
                }
                return -2;
            }
        }
        return set.contains(c) ? 1 : -1;
    }

    static int spanOneBack(UnicodeSet set, CharSequence s, int length) {
        char c = s.charAt(length - 1);
        if (c >= 56320 && c <= 57343 && length >= 2) {
            char c2 = s.charAt(length - 2);
            if (UTF16.isLeadSurrogate(c2)) {
                if (set.contains(Character.toCodePoint(c2, c))) {
                    return 2;
                }
                return -2;
            }
        }
        return set.contains(c) ? 1 : -1;
    }

    /* access modifiers changed from: private */
    public static final class OffsetList {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int length;
        private int[] list = new int[16];
        private int start;

        public void setMaxLength(int maxLength) {
            if (maxLength > this.list.length) {
                this.list = new int[maxLength];
            }
            clear();
        }

        public void clear() {
            int i = this.list.length;
            while (true) {
                int i2 = i - 1;
                if (i > 0) {
                    this.list[i2] = 0;
                    i = i2;
                } else {
                    this.length = 0;
                    this.start = 0;
                    return;
                }
            }
        }

        public boolean isEmpty() {
            return this.length == 0;
        }

        public void shift(int delta) {
            int i = this.start + delta;
            int[] iArr = this.list;
            if (i >= iArr.length) {
                i -= iArr.length;
            }
            int[] iArr2 = this.list;
            if (iArr2[i] != 0) {
                iArr2[i] = 0;
                this.length--;
            }
            this.start = i;
        }

        public void addOffset(int offset) {
            int i = this.start + offset;
            int[] iArr = this.list;
            if (i >= iArr.length) {
                i -= iArr.length;
            }
            this.list[i] = 1;
            this.length++;
        }

        public void addOffsetAndCount(int offset, int count) {
            int i = this.start + offset;
            int[] iArr = this.list;
            if (i >= iArr.length) {
                i -= iArr.length;
            }
            int[] iArr2 = this.list;
            if (iArr2[i] == 0) {
                iArr2[i] = count;
                this.length++;
            } else if (count < iArr2[i]) {
                iArr2[i] = count;
            }
        }

        public boolean containsOffset(int offset) {
            int i = this.start + offset;
            int[] iArr = this.list;
            if (i >= iArr.length) {
                i -= iArr.length;
            }
            return this.list[i] != 0;
        }

        public boolean hasCountAtOffset(int offset, int count) {
            int i = this.start + offset;
            int[] iArr = this.list;
            if (i >= iArr.length) {
                i -= iArr.length;
            }
            int oldCount = this.list[i];
            return oldCount != 0 && oldCount <= count;
        }

        public int popMinimum(OutputInt outCount) {
            int[] iArr;
            int[] iArr2;
            int count;
            int count2;
            int i = this.start;
            do {
                i++;
                iArr = this.list;
                if (i < iArr.length) {
                    count2 = iArr[i];
                } else {
                    int result = iArr.length - this.start;
                    int i2 = 0;
                    while (true) {
                        iArr2 = this.list;
                        count = iArr2[i2];
                        if (count != 0) {
                            break;
                        }
                        i2++;
                    }
                    iArr2[i2] = 0;
                    this.length--;
                    this.start = i2;
                    if (outCount != null) {
                        outCount.value = count;
                    }
                    return result + i2;
                }
            } while (count2 == 0);
            iArr[i] = 0;
            this.length--;
            int result2 = i - this.start;
            this.start = i;
            if (outCount != null) {
                outCount.value = count2;
            }
            return result2;
        }
    }
}
