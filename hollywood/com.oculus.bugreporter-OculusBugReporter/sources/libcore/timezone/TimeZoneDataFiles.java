package libcore.timezone;

import java.util.ArrayList;
import java.util.List;

public final class TimeZoneDataFiles {
    private static final String ANDROID_DATA_ENV = "ANDROID_DATA";
    private static final String ANDROID_ROOT_ENV = "ANDROID_ROOT";
    private static final String ANDROID_RUNTIME_ROOT_ENV = "ANDROID_RUNTIME_ROOT";
    private static final String ANDROID_TZDATA_ROOT_ENV = "ANDROID_TZDATA_ROOT";

    private TimeZoneDataFiles() {
    }

    public static String[] getTimeZoneFilePaths(String fileName) {
        return new String[]{getDataTimeZoneFile(fileName), getTimeZoneModuleFile("tz/" + fileName), getRuntimeModuleFile("tz/" + fileName), getSystemTimeZoneFile(fileName)};
    }

    public static String getDataTimeZoneRootDir() {
        return System.getenv(ANDROID_DATA_ENV) + "/misc/zoneinfo/";
    }

    public static String getDataTimeZoneFile(String fileName) {
        return getDataTimeZoneRootDir() + "current/" + fileName;
    }

    public static String getTimeZoneModuleFile(String fileName) {
        return System.getenv(ANDROID_TZDATA_ROOT_ENV) + "/etc/" + fileName;
    }

    public static String getRuntimeModuleTzVersionFile() {
        return getRuntimeModuleFile("tz/tz_version");
    }

    public static String getRuntimeModuleFile(String fileName) {
        return System.getenv(ANDROID_RUNTIME_ROOT_ENV) + "/etc/" + fileName;
    }

    public static String getSystemTimeZoneFile(String fileName) {
        return getEnvironmentPath(ANDROID_ROOT_ENV, "/usr/share/zoneinfo/" + fileName);
    }

    public static String getSystemIcuFile(String fileName) {
        return getEnvironmentPath(ANDROID_ROOT_ENV, "/usr/icu/" + fileName);
    }

    public static String generateIcuDataPath() {
        List<String> paths = new ArrayList<>(3);
        String dataIcuDataPath = getEnvironmentPath(ANDROID_DATA_ENV, "/misc/zoneinfo/current/icu/");
        if (dataIcuDataPath != null) {
            paths.add(dataIcuDataPath);
        }
        String timeZoneModuleIcuDataPath = getTimeZoneModuleFile("icu/");
        if (timeZoneModuleIcuDataPath != null) {
            paths.add(timeZoneModuleIcuDataPath);
        }
        String runtimeModuleIcuDataPath = getRuntimeModuleFile("icu/");
        if (runtimeModuleIcuDataPath != null) {
            paths.add(runtimeModuleIcuDataPath);
        }
        return String.join(":", paths);
    }

    private static String getEnvironmentPath(String environmentVariable, String path) {
        String variable = System.getenv(environmentVariable);
        if (variable == null) {
            return null;
        }
        return variable + path;
    }
}
