package X;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ui.emoji.model.BasicEmoji;
import com.facebook.ui.emoji.model.Emoji;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1oj  reason: invalid class name */
public abstract class AnonymousClass1oj {
    public static final int PROCESS_FULL_STRING = -1;

    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isWithinEmojiRange(int r3) {
        /*
            r1 = 169(0xa9, float:2.37E-43)
            r2 = 0
            if (r3 < r1) goto L_0x0010
            r0 = 8252(0x203c, float:1.1564E-41)
            if (r3 >= r0) goto L_0x0011
            if (r3 == r1) goto L_0x000f
            r0 = 174(0xae, float:2.44E-43)
            if (r3 != r0) goto L_0x0010
        L_0x000f:
            r2 = 1
        L_0x0010:
            return r2
        L_0x0011:
            r0 = 126980(0x1f004, float:1.77937E-40)
            r1 = 12953(0x3299, float:1.8151E-41)
            if (r3 < r0) goto L_0x0022
            r0 = 983040(0xf0000, float:1.377532E-39)
            r1 = 983042(0xf0002, float:1.377535E-39)
            if (r3 >= r0) goto L_0x0022
            r1 = 129791(0x1faff, float:1.81876E-40)
        L_0x0022:
            if (r3 > r1) goto L_0x0010
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1oj.isWithinEmojiRange(int):boolean");
    }

    public abstract void addDebugSpan(Spannable spannable, int i, int i2);

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (parseAndAddEmojiSpans(r11, r6, r13, r8, r12) == false) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean addEmojiSpansInternal(android.text.Spannable r11, int r12, int r13, int r14, boolean r15) {
        /*
            r10 = this;
            r5 = r11
            int r6 = r11.length()
            r0 = -1
            r7 = r13
            int r8 = r14 + r13
            if (r14 != r0) goto L_0x000c
            r8 = r6
        L_0x000c:
            r4 = r10
            boolean r0 = r10.isInDebugMode()
            if (r0 == 0) goto L_0x001e
            r10.addDebugSpan(r11, r13, r8)
            java.lang.String r1 = "Redex: Unreachable code after no-return invoke"
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        L_0x001e:
            boolean r0 = hasEmojiQuick(r11, r13, r8)
            r3 = 1
            r2 = 0
            r9 = r12
            if (r0 == 0) goto L_0x002e
            boolean r0 = r4.parseAndAddEmojiSpans(r5, r6, r7, r8, r9)
            r1 = 1
            if (r0 != 0) goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            if (r15 == 0) goto L_0x0037
            boolean r0 = r10.replaceEmoticonsWithEmojis(r11, r2, r8, r12)
            if (r0 != 0) goto L_0x003a
        L_0x0037:
            if (r1 != 0) goto L_0x003a
            r3 = 0
        L_0x003a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1oj.addEmojiSpansInternal(android.text.Spannable, int, int, int, boolean):boolean");
    }

    public abstract void complainLoudly(String str, Object... objArr);

    @Nullable
    public abstract Object createFallbackSpan(CharSequence charSequence, int i, int i2, int i3);

    @Nullable
    public abstract Object createTypefaceEmojiSpan(int i);

    @Nullable
    public abstract Object createTypefaceEmoticonSpan(Emoji emoji, int i);

    public int findEmojiSpanStopForSpans(Spannable spannable, int i, int i2) {
        int i3 = i;
        AbstractC10571om r1 = AnonymousClass1oq.A00;
        int i4 = -1;
        while (true) {
            i3 = r1.A00(r1.A01, 0, r1.A00, spannable, i3, i2);
            if (i3 < 0) {
                return i4;
            }
            i4 = i3;
        }
    }

    @Nullable
    public abstract String getEmojiFromEmoticon(String str);

    public abstract boolean isInDebugMode();

    public boolean isTextOnlyEmojiAndWhitespace(CharSequence charSequence, int i) {
        if (!(charSequence == null || charSequence.length() == 0)) {
            int length = charSequence.length();
            int i2 = 0;
            boolean z = true;
            int i3 = 0;
            while (i2 < length) {
                int codePointAt = Character.codePointAt(charSequence, i2);
                if (Character.isWhitespace(codePointAt)) {
                    i2 += Character.charCount(codePointAt);
                    z = true;
                } else {
                    AbstractC10571om r5 = AnonymousClass1oq.A00;
                    int A00 = r5.A00(r5.A01, 0, r5.A00, charSequence, i2, length);
                    if (i2 >= A00) {
                        if (z) {
                            AbstractC10571om r52 = C10611or.A00;
                            A00 = r52.A00(r52.A01, 0, r52.A00, charSequence, i2, length);
                            if (i2 >= A00 || getEmojiFromEmoticon(Emoji.A00(charSequence, i2, A00)) == null) {
                                return false;
                            }
                        }
                    }
                    i3++;
                    if (i3 <= i) {
                        i2 = A00;
                        z = false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Nullable
    @VisibleForTesting
    public Spannable maybeConvertAndAddEmoticons(CharSequence charSequence, int i, int i2, int i3, boolean z) {
        int i4;
        int i5 = i;
        Spannable spannable = null;
        while (true) {
            boolean z2 = true;
            while (i5 < i2) {
                int codePointAt = Character.codePointAt(charSequence, i5);
                if (Character.isWhitespace(codePointAt)) {
                    i5 += Character.charCount(codePointAt);
                } else if (!z2) {
                    i5 += Character.charCount(codePointAt);
                } else {
                    AbstractC10571om r6 = C10611or.A00;
                    int A00 = r6.A00(r6.A01, 0, r6.A00, charSequence, i5, i2);
                    if (A00 <= i5) {
                        i5 += Character.charCount(codePointAt);
                    } else {
                        if (A00 < i2) {
                            i4 = Character.codePointAt(charSequence, A00);
                        } else {
                            i4 = 32;
                        }
                        if (!Character.isWhitespace(i4)) {
                            i5 = Character.charCount(i4) + A00;
                        } else {
                            Object spanForEmoticonMatch = getSpanForEmoticonMatch(Emoji.A00(charSequence, i5, A00), i3);
                            if (spanForEmoticonMatch != null) {
                                if (spannable == null) {
                                    if (z || !(charSequence instanceof Spannable)) {
                                        spannable = new SpannableString(charSequence);
                                        if (spannable.length() < i2) {
                                            complainLoudly("Background modification: %d -> %d (%d)", Integer.valueOf(i2), Integer.valueOf(spannable.length()), Integer.valueOf(charSequence.length()));
                                            return null;
                                        }
                                    } else {
                                        spannable = (Spannable) charSequence;
                                    }
                                }
                                spannable.setSpan(spanForEmoticonMatch, i5, A00, 33);
                            }
                            i5 = Character.charCount(i4) + A00;
                        }
                    }
                    z2 = false;
                }
            }
            return spannable;
        }
    }

    public boolean replaceEmoticonsWithEmojis(Spannable spannable, int i, int i2, int i3) {
        if (maybeConvertAndAddEmoticons(spannable, i, i2, i3, false) != null) {
            return true;
        }
        return false;
    }

    public abstract boolean useBitmapFallback();

    @VisibleForTesting
    public static boolean hasEmojiQuick(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int codePointAt = Character.codePointAt(charSequence, i);
            if (isWithinEmojiRange(codePointAt)) {
                return true;
            }
            i += Character.charCount(codePointAt);
        }
        return false;
    }

    public int findEmojiStopForSpans(Spannable spannable, int i, int i2) {
        AbstractC10571om r0 = AnonymousClass1oq.A00;
        return r0.A00(r0.A01, 0, r0.A00, spannable, i, i2);
    }

    @Nullable
    private Object getSpanForEmoticonMatch(String str, int i) {
        String emojiFromEmoticon = getEmojiFromEmoticon(str);
        if (emojiFromEmoticon == null) {
            return null;
        }
        if (useBitmapFallback()) {
            return createFallbackSpan(emojiFromEmoticon, 0, emojiFromEmoticon.length(), i);
        }
        return createTypefaceEmoticonSpan(new BasicEmoji(emojiFromEmoticon), i);
    }

    public boolean parseAndAddEmojiSpans(Spannable spannable, int i, int i2, int i3, int i4) {
        int findEmojiSpanStopForSpans;
        Object createTypefaceEmojiSpan;
        boolean useBitmapFallback = useBitmapFallback();
        boolean z = false;
        while (i2 < i3) {
            if (useBitmapFallback) {
                findEmojiSpanStopForSpans = findEmojiStopForSpans(spannable, i2, i3);
            } else {
                findEmojiSpanStopForSpans = findEmojiSpanStopForSpans(spannable, i2, i3);
            }
            if (findEmojiSpanStopForSpans == -1) {
                i2++;
            } else {
                if (useBitmapFallback) {
                    createTypefaceEmojiSpan = createFallbackSpan(spannable, i2, findEmojiSpanStopForSpans, i4);
                } else {
                    createTypefaceEmojiSpan = createTypefaceEmojiSpan(i4);
                }
                if (createTypefaceEmojiSpan != null && findEmojiSpanStopForSpans <= i) {
                    spannable.setSpan(createTypefaceEmojiSpan, i2, findEmojiSpanStopForSpans, 33);
                    z = true;
                }
                i2 = findEmojiSpanStopForSpans;
            }
        }
        return z;
    }

    public boolean addEmojiSpans(Spannable spannable, int i) {
        return addEmojiSpansInternal(spannable, i, 0, -1, true);
    }

    public boolean addEmojiSpans(Spannable spannable, int i, int i2, int i3, boolean z) {
        return addEmojiSpansInternal(spannable, i, i2, i3, z);
    }

    public boolean addEmojiSpans(Spannable spannable, int i, boolean z) {
        return addEmojiSpansInternal(spannable, i, 0, -1, z);
    }

    public CharSequence maybeConvertAndAddEmoji(CharSequence charSequence, int i) {
        return maybeConvertAndAddEmoji(charSequence, i, true);
    }

    public CharSequence maybeConvertAndAddEmoji(CharSequence charSequence, int i, boolean z) {
        Spannable maybeConvertAndAddEmoticons;
        if ((charSequence instanceof Spannable) && !(charSequence instanceof Editable)) {
            addEmojiSpans((Spannable) charSequence, i, 0, -1, z);
        } else if (isInDebugMode()) {
            SpannableString spannableString = new SpannableString(charSequence);
            addEmojiSpans(spannableString, i, 0, -1, z);
            return spannableString;
        } else {
            int length = charSequence.length();
            if (hasEmojiQuick(charSequence, 0, length)) {
                SpannableString spannableString2 = new SpannableString(charSequence);
                if (spannableString2.length() != length) {
                    complainLoudly("Background modification: %d -> %d", Integer.valueOf(length), Integer.valueOf(spannableString2.length()));
                    length = spannableString2.length();
                }
                parseAndAddEmojiSpans(spannableString2, length, 0, length, i);
                if (z) {
                    replaceEmoticonsWithEmojis(spannableString2, 0, length, i);
                }
                return spannableString2;
            } else if (!z || (maybeConvertAndAddEmoticons = maybeConvertAndAddEmoticons(charSequence, 0, length, i, true)) == null) {
                return charSequence;
            } else {
                return maybeConvertAndAddEmoticons;
            }
        }
        return charSequence;
    }
}
