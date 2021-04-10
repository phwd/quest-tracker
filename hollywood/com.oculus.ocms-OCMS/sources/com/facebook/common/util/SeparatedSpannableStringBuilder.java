package com.facebook.common.util;

import android.text.SpannableStringBuilder;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SeparatedSpannableStringBuilder extends SpannableStringBuilder {
    private static final String BULLET_SEPARATOR_STRING = "  •  ";
    private CharSequence mSeparator;

    public SeparatedSpannableStringBuilder(CharSequence charSequence) {
        this.mSeparator = charSequence;
    }

    public SeparatedSpannableStringBuilder() {
        this(BULLET_SEPARATOR_STRING);
    }

    public SeparatedSpannableStringBuilder bulletedAppend(CharSequence charSequence) {
        return bulletedAppend(charSequence, null, 0);
    }

    public SeparatedSpannableStringBuilder bulletedAppend(CharSequence charSequence, @Nullable Object obj, int i) {
        if (length() > 0) {
            append(this.mSeparator);
        }
        int length = length();
        append(charSequence);
        if (obj != null) {
            setSpan(obj, length, charSequence.length() + length, i);
        }
        return this;
    }
}
