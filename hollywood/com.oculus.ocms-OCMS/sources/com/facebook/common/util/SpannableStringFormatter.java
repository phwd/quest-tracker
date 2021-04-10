package com.facebook.common.util;

import android.content.res.Resources;
import android.text.SpannableString;
import java.util.HashMap;
import java.util.Map;

public class SpannableStringFormatter {
    public static SpannableString formatSpannedString(Resources resources, int i, SpannableStringSubstitution... spannableStringSubstitutionArr) {
        HashMap hashMap = new HashMap();
        Object[] objArr = new Object[spannableStringSubstitutionArr.length];
        for (int i2 = 0; i2 < spannableStringSubstitutionArr.length; i2++) {
            SpannableStringSubstitution spannableStringSubstitution = spannableStringSubstitutionArr[i2];
            if (spannableStringSubstitution.span == null) {
                objArr[i2] = getSubstitutionText(resources, spannableStringSubstitution);
            } else {
                String numberedPlaceholder = getNumberedPlaceholder(i2);
                objArr[i2] = numberedPlaceholder;
                hashMap.put(numberedPlaceholder, spannableStringSubstitution);
            }
        }
        String string = resources.getString(i, objArr);
        StyledStringBuilder styledStringBuilder = new StyledStringBuilder(resources);
        styledStringBuilder.append(string);
        for (Map.Entry entry : hashMap.entrySet()) {
            SpannableStringSubstitution spannableStringSubstitution2 = (SpannableStringSubstitution) entry.getValue();
            styledStringBuilder.replaceToken((String) entry.getKey(), getSubstitutionText(resources, spannableStringSubstitution2).toString(), spannableStringSubstitution2.span, spannableStringSubstitution2.spanFlags);
        }
        return styledStringBuilder.toSpannableString();
    }

    private static String getNumberedPlaceholder(int i) {
        return "[[placeholder_" + i + "]]";
    }

    private static Object getSubstitutionText(Resources resources, SpannableStringSubstitution spannableStringSubstitution) {
        if (spannableStringSubstitution.substitutionText != null) {
            return spannableStringSubstitution.substitutionText;
        }
        return resources.getString(spannableStringSubstitution.substitutionTextResId);
    }
}
