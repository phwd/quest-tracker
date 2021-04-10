package com.oculus.util.version;

import android.content.pm.PackageInfo;
import org.apache.commons.cli.HelpFormatter;

public class VersionUtils {
    public static boolean meetsMinimumVersion(PackageInfo packageInfo, String str) {
        int i;
        int i2;
        String[] split = packageInfo.versionName.split(HelpFormatter.DEFAULT_OPT_PREFIX)[0].split("\\.");
        String[] split2 = str.split("\\.");
        int length = split.length;
        int length2 = split2.length;
        int max = Math.max(length, length2);
        for (int i3 = 0; i3 < max; i3++) {
            if (i3 < length) {
                try {
                    i = Integer.parseInt(split[i3]);
                } catch (NumberFormatException unused) {
                    return false;
                }
            } else {
                i = 0;
            }
            if (i3 < length2) {
                i2 = Integer.parseInt(split2[i3]);
            } else {
                i2 = 0;
            }
            if (i < i2) {
                return false;
            }
            if (i > i2) {
                break;
            }
        }
        return true;
    }
}
