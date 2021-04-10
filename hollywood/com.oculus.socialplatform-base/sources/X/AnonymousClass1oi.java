package X;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1oi  reason: invalid class name */
public abstract class AnonymousClass1oi implements TextWatcher {
    public static final int MAX_EMOTICON_LENGTH = 8;

    public static int findWhitespaceOrStart(CharSequence charSequence, int i, int i2, boolean z, boolean z2) {
        int i3 = -1;
        if (i <= 0) {
            if (z2) {
                return 0;
            }
            i = 0;
            i3 = 0;
        } else if (z && i < i2) {
            int i4 = i - 1;
            if (Character.isHighSurrogate(charSequence.charAt(i4)) && Character.isLowSurrogate(charSequence.charAt(i))) {
                i = i4;
            }
        }
        while (i < i2) {
            int codePointAt = Character.codePointAt(charSequence, i);
            if (Character.isWhitespace(codePointAt)) {
                if (z2) {
                    return i;
                }
                i3 = i;
            }
            i += Character.charCount(codePointAt);
        }
        return i3;
    }

    public abstract boolean addSmallEmojiSpans(Spannable spannable, int i, int i2);

    public void afterTextChanged(@Nullable Editable editable) {
    }

    public boolean handleTextChanged(Spannable spannable, int i, int i2) {
        int i3;
        int findWhitespaceOrStart;
        if (i2 < 1 || !supportEmoji()) {
            return false;
        }
        boolean addSmallEmojiSpans = addSmallEmojiSpans(spannable, i, i2);
        if (!supportEmoticons() || (findWhitespaceOrStart = findWhitespaceOrStart(spannable, i, (i3 = i + i2), false, true)) == -1) {
            return addSmallEmojiSpans;
        }
        int findWhitespaceOrStart2 = findWhitespaceOrStart(spannable, findWhitespaceOrStart - 8, i, true, false);
        if (findWhitespaceOrStart2 == -1) {
            findWhitespaceOrStart2 = findWhitespaceOrStart;
        }
        if (i2 > 2) {
            findWhitespaceOrStart = findWhitespaceOrStart(spannable, findWhitespaceOrStart, i3, false, false);
        }
        if (findWhitespaceOrStart2 != findWhitespaceOrStart) {
            return addSmallEmojiSpans | replaceEmoticonsWithEmojis(spannable, findWhitespaceOrStart2, findWhitespaceOrStart);
        }
        return addSmallEmojiSpans;
    }

    public abstract boolean replaceEmoticonsWithEmojis(Spannable spannable, int i, int i2);

    public abstract boolean supportEmoji();

    public abstract boolean supportEmoticons();

    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence instanceof Spannable) {
            Spannable spannable = (Spannable) charSequence;
            int i4 = i + i2;
            AbstractC03500mo[] r7 = (AbstractC03500mo[]) spannable.getSpans(i, i4, AbstractC03500mo.class);
            int length = r7.length;
            if (length != 0) {
                int i5 = i;
                int i6 = i4;
                int i7 = 0;
                do {
                    AbstractC03500mo r1 = r7[i7];
                    int spanStart = spannable.getSpanStart(r1);
                    if (spanStart >= 0 && spanStart < i5) {
                        i5 = spanStart;
                    }
                    i6 = Math.max(i6, spannable.getSpanEnd(r1));
                    spannable.removeSpan(r1);
                    i7++;
                } while (i7 < length);
                if (i5 < i) {
                    addSmallEmojiSpans(spannable, i5, i - i5);
                }
                if (i4 < i6) {
                    addSmallEmojiSpans(spannable, i4, i6 - i4);
                }
            }
            if (i2 != 0) {
                for (AbstractC10621os r0 : (AbstractC10621os[]) spannable.getSpans(i, i4, AbstractC10621os.class)) {
                    spannable.removeSpan(r0);
                }
            }
        }
    }

    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence instanceof Spannable) {
            handleTextChanged((Spannable) charSequence, i, i3);
        }
    }
}
