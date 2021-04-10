package com.facebook.mobileconfig.impl;

import com.oculus.common.build.BuildConfig;
import java.io.File;

public class MobileConfigFilesOnDiskUtils {
    private static final String TAG = "MobileConfigFilesOnDiskUtils";

    public static String getSpecToHashFilePath(File file, String str) {
        return getDirectoryPathForSessionFromId(file, str, BuildConfig.PROVIDER_SUFFIX) + "/" + "spec_to_param.txt";
    }

    private static String getDirectoryPathForSessionFromId(File file, String str, String str2) {
        String str3 = file.getAbsolutePath() + "/mobileconfig/";
        if (!str2.isEmpty()) {
            str2 = "_" + str2;
        }
        if (str == null || str.isEmpty() || str.equals("0")) {
            return str3 + "sessionless" + str2 + ".data/";
        }
        return str3 + str + str2 + ".data/";
    }
}
