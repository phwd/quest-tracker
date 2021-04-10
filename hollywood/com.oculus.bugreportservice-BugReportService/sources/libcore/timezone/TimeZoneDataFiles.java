package libcore.timezone;

import java.util.ArrayList;

public final class TimeZoneDataFiles {
    public static String[] getTimeZoneFilePaths(String str) {
        return new String[]{getDataTimeZoneFile(str), getTimeZoneModuleFile("tz/" + str), getRuntimeModuleFile("tz/" + str), getSystemTimeZoneFile(str)};
    }

    public static String getDataTimeZoneRootDir() {
        return System.getenv("ANDROID_DATA") + "/misc/zoneinfo/";
    }

    public static String getDataTimeZoneFile(String str) {
        return getDataTimeZoneRootDir() + "current/" + str;
    }

    public static String getTimeZoneModuleFile(String str) {
        return System.getenv("ANDROID_TZDATA_ROOT") + "/etc/" + str;
    }

    public static String getRuntimeModuleFile(String str) {
        return System.getenv("ANDROID_RUNTIME_ROOT") + "/etc/" + str;
    }

    public static String getSystemTimeZoneFile(String str) {
        return getEnvironmentPath("ANDROID_ROOT", "/usr/share/zoneinfo/" + str);
    }

    public static String generateIcuDataPath() {
        ArrayList arrayList = new ArrayList(3);
        String environmentPath = getEnvironmentPath("ANDROID_DATA", "/misc/zoneinfo/current/icu/");
        if (environmentPath != null) {
            arrayList.add(environmentPath);
        }
        String timeZoneModuleFile = getTimeZoneModuleFile("icu/");
        if (timeZoneModuleFile != null) {
            arrayList.add(timeZoneModuleFile);
        }
        String runtimeModuleFile = getRuntimeModuleFile("icu/");
        if (runtimeModuleFile != null) {
            arrayList.add(runtimeModuleFile);
        }
        return String.join(":", arrayList);
    }

    private static String getEnvironmentPath(String str, String str2) {
        String str3 = System.getenv(str);
        if (str3 == null) {
            return null;
        }
        return str3 + str2;
    }
}
