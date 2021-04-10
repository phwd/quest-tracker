package com.facebook.common.util;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import androidx.annotation.Nullable;

public class SpannableDrawableUtil {
    public static SpannableStringBuilder appendDrawable(SpannableStringBuilder spannableStringBuilder, @Nullable Drawable drawable) {
        appendDrawable(spannableStringBuilder, drawable, 1);
        return spannableStringBuilder;
    }

    public static SpannableStringBuilder appendDrawable(SpannableStringBuilder spannableStringBuilder, @Nullable Drawable drawable, int i) {
        insertDrawable(spannableStringBuilder, spannableStringBuilder.length(), drawable, i);
        return spannableStringBuilder;
    }

    public static void insertDrawable(SpannableStringBuilder spannableStringBuilder, int i, @Nullable Drawable drawable) {
        insertDrawable(spannableStringBuilder, i, drawable, 1);
    }

    public static void insertDrawable(SpannableStringBuilder spannableStringBuilder, int i, @Nullable Drawable drawable, int i2) {
        if (drawable != null) {
            spannableStringBuilder.insert(i, ".").insert(i, " ");
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannableStringBuilder.setSpan(new ImageSpan(drawable, i2), i + 1, i + 2, 17);
        }
    }
}
