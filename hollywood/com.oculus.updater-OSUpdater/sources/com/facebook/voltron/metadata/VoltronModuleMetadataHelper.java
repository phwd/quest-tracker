package com.facebook.voltron.metadata;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.common.build.BuildConfig;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class VoltronModuleMetadataHelper {
    private static final String TAG = "VoltronModuleMetadataHelper";

    @DoNotStrip
    @Nullable
    public static int getModuleRangeIndexForRedexClassName(String str, int[] iArr) {
        return getModuleRangeIndexForRedexClassName(getBase62ClassName(str), iArr, 0, (iArr.length / 2) - 1);
    }

    @DoNotStrip
    private static int getBase62ClassName(String str) {
        int i;
        int i2 = 0;
        for (int lastIndexOf = str.lastIndexOf(46) + 1; lastIndexOf < str.length(); lastIndexOf++) {
            i2 *= 62;
            char charAt = str.charAt(lastIndexOf);
            if (charAt >= '0' && charAt <= '9') {
                i = charAt - '0';
            } else if (charAt < 'A' || charAt > 'Z') {
                if (charAt >= 'a' && charAt <= 'z') {
                    i = (charAt - 'a') + 10 + 26;
                }
            } else {
                i = (charAt - 'A') + 10;
            }
            i2 += i;
        }
        return i2;
    }

    @DoNotStrip
    @Nullable
    private static int getModuleRangeIndexForRedexClassName(int i, int[] iArr, int i2, int i3) {
        if (i2 > i3) {
            return -1;
        }
        int i4 = (i2 + i3) / 2;
        int i5 = i4 * 2;
        int i6 = i5 + 1;
        if (iArr[i5] == -1 || iArr[i6] == -1) {
            BLog.w(TAG, "Invalid range in module range index");
            return -1;
        } else if (i > iArr[i6]) {
            return getModuleRangeIndexForRedexClassName(i, iArr, i4 + 1, i3);
        } else {
            if (i < iArr[i5]) {
                return getModuleRangeIndexForRedexClassName(i, iArr, i2, i4 - 1);
            }
            if (i < iArr[i5] || i > iArr[i6]) {
                return -1;
            }
            return i4;
        }
    }

    @DoNotStrip
    public static String getPackageNameForClass(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Class name is empty");
        } else if (str.startsWith("X.")) {
            return "X";
        } else {
            if (!Character.isLowerCase(str.codePointAt(0))) {
                return BuildConfig.PROVIDER_SUFFIX;
            }
            int length = str.length() - 1;
            int indexOf = str.indexOf(46);
            while (indexOf > 0 && indexOf < length) {
                int i = indexOf + 1;
                if (!Character.isLowerCase(str.codePointAt(i))) {
                    return str.substring(0, indexOf);
                }
                indexOf = str.indexOf(46, i);
            }
            return BuildConfig.PROVIDER_SUFFIX;
        }
    }

    @DoNotStrip
    public static String getShortNameForClass(String str, String str2) {
        return str2.isEmpty() ? str : str.substring(str2.length() + 1);
    }
}
