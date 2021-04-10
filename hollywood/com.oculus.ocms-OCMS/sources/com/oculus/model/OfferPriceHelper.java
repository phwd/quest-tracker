package com.oculus.model;

import android.content.Context;
import android.content.ContextWrapper;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import com.google.common.base.Strings;

public class OfferPriceHelper extends ContextWrapper {
    private final String mFree;
    private final int mStrikeFormat;
    private final int mStrikeWithPromoBenefitFormat;

    public OfferPriceHelper(Context context, int i, int i2, int i3) {
        super(context);
        this.mFree = context.getString(i);
        this.mStrikeFormat = i2;
        this.mStrikeWithPromoBenefitFormat = i3;
    }

    public CharSequence getPriceText(boolean z, String str, String str2, String str3) {
        SpannableString spannableString;
        if (z) {
            str = this.mFree;
        }
        if (str2 == null) {
            return str;
        }
        if (Strings.isNullOrEmpty(str3)) {
            spannableString = new SpannableString(getBaseContext().getString(this.mStrikeFormat, str2, str));
        } else {
            spannableString = new SpannableString(getBaseContext().getString(this.mStrikeWithPromoBenefitFormat, str2, str3, str));
            spannableString.setSpan(new StyleSpan(1), str2.length() + 2, str2.length() + 2 + str3.length(), 33);
        }
        spannableString.setSpan(new StrikethroughSpan(), 0, str2.length(), 33);
        return spannableString;
    }
}
