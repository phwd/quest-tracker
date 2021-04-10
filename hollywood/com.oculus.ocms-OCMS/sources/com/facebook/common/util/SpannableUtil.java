package com.facebook.common.util;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SpannableUtil {
    public static void setSpans(SpannableStringBuilder spannableStringBuilder, Object... objArr) {
        setSpans(0, spannableStringBuilder.length(), spannableStringBuilder, objArr);
    }

    public static void setSpans(int i, int i2, SpannableStringBuilder spannableStringBuilder, Object... objArr) {
        for (Object obj : objArr) {
            spannableStringBuilder.setSpan(obj, i, i2, 33);
        }
    }

    public static void setSpans(SpannableString spannableString, Object... objArr) {
        setSpans(0, spannableString.length(), spannableString, objArr);
    }

    public static void setSpans(int i, int i2, SpannableString spannableString, Object... objArr) {
        for (Object obj : objArr) {
            spannableString.setSpan(obj, i, i2, 33);
        }
    }
}
