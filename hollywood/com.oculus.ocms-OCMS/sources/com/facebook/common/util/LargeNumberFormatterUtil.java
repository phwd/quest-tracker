package com.facebook.common.util;

import android.content.res.Resources;
import com.facebook.R;
import com.facebook.common.string.StringUtil;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;

public class LargeNumberFormatterUtil {
    private static final Set<Locale> CJK_LOCALES = ImmutableSet.of(Locale.CHINA, Locale.TAIWAN, Locale.JAPAN, Locale.KOREA);
    private static final int DEFAULT_SHORTEN_THRESHOLD = 10000;

    /* access modifiers changed from: package-private */
    public enum LargeNumberShortFormatConfig {
        CJK(4, 8),
        STANDARD(3, 9);
        
        int mMaxMultiplier;
        int mStepSize;

        private LargeNumberShortFormatConfig(int i, int i2) {
            this.mStepSize = i;
            this.mMaxMultiplier = i2;
        }
    }

    public static String formatShortDecimalLargeNumber(Integer num, Resources resources) {
        return formatShortDecimalLargeNumber(num, resources, false);
    }

    public static String formatShortDecimalLargeNumber(Integer num, Resources resources, boolean z) {
        return formatShortDecimalLargeNumber(num, resources, z, null, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String formatShortDecimalLargeNumber(java.lang.Integer r16, android.content.res.Resources r17, boolean r18, @androidx.annotation.Nullable java.lang.Integer r19, boolean r20) {
        /*
        // Method dump skipped, instructions count: 307
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.util.LargeNumberFormatterUtil.formatShortDecimalLargeNumber(java.lang.Integer, android.content.res.Resources, boolean, java.lang.Integer, boolean):java.lang.String");
    }

    protected static int getMultiplier(double d, LargeNumberShortFormatConfig largeNumberShortFormatConfig) {
        int i = 0;
        int i2 = 1;
        while (largeNumberShortFormatConfig.mStepSize + i <= largeNumberShortFormatConfig.mMaxMultiplier) {
            double d2 = (double) i2;
            double pow = Math.pow(10.0d, (double) largeNumberShortFormatConfig.mStepSize);
            Double.isNaN(d2);
            i2 = (int) (d2 * pow);
            if (((double) i2) > d) {
                break;
            }
            i += largeNumberShortFormatConfig.mStepSize;
        }
        return i;
    }

    private static String getShortString(Resources resources, String str, int i, boolean z) {
        if (i == 3) {
            return resources.getString(z ? R.string.shorten_thousands_capitalized : R.string.shorten_thousands, StringUtil.formatStrLocaleSafe("%s", str));
        } else if (i == 4) {
            return resources.getString(R.string.shorten_ten_thousands, StringUtil.formatStrLocaleSafe("%s", str));
        } else if (i == 6) {
            return resources.getString(z ? R.string.shorten_millions_capitalized : R.string.shorten_millions, StringUtil.formatStrLocaleSafe("%s", str));
        } else if (i == 8) {
            return resources.getString(R.string.shorten_hundred_millions, StringUtil.formatStrLocaleSafe("%s", str));
        } else if (i == 9) {
            return resources.getString(z ? R.string.shorten_billions_capitalized : R.string.shorten_billions, StringUtil.formatStrLocaleSafe("%s", str));
        } else {
            throw new RuntimeException("Invalid multiplier: " + i);
        }
    }

    private static LargeNumberShortFormatConfig getShortFormatConfig(Locale locale) {
        if (CJK_LOCALES.contains(locale)) {
            return LargeNumberShortFormatConfig.CJK;
        }
        return LargeNumberShortFormatConfig.STANDARD;
    }
}
