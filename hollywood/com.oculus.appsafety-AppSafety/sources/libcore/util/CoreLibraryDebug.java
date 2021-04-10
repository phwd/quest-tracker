package libcore.util;

import android.icu.util.TimeZone;
import java.io.File;
import java.io.IOException;
import libcore.icu.ICU;
import libcore.timezone.TimeZoneDataFiles;
import libcore.timezone.TzDataSetVersion;
import libcore.timezone.ZoneInfoDB;

public class CoreLibraryDebug {
    private static final String CORE_LIBRARY_TIMEZONE_DEBUG_PREFIX = "core_library.timezone.";

    private CoreLibraryDebug() {
    }

    public static DebugInfo getDebugInfo() {
        DebugInfo debugInfo = new DebugInfo();
        populateTimeZoneFilesInfo(debugInfo);
        populateTimeZoneLibraryReportedVersion(debugInfo);
        return debugInfo;
    }

    private static void populateTimeZoneFilesInfo(DebugInfo debugInfo) {
        addTzDataSetVersionDebugInfo(TimeZoneDataFiles.getTimeZoneModuleFile("tz/tz_version"), "core_library.timezone.source." + "tzdata_module_", debugInfo);
        addTzDataSetVersionDebugInfo(TimeZoneDataFiles.getRuntimeModuleFile("tz/tz_version"), "core_library.timezone.source." + "runtime_module_", debugInfo);
        addTzDataSetVersionDebugInfo(TimeZoneDataFiles.getSystemTimeZoneFile(TzDataSetVersion.DEFAULT_FILE_NAME), "core_library.timezone.source." + "system_", debugInfo);
    }

    private static void addTzDataSetVersionDebugInfo(String tzDataSetVersionFileName, String debugKeyPrefix, DebugInfo debugInfo) {
        File file = new File(tzDataSetVersionFileName);
        String statusKey = debugKeyPrefix + "status";
        if (file.exists()) {
            try {
                TzDataSetVersion tzDataSetVersion = TzDataSetVersion.readFromFile(file);
                DebugInfo addStringEntry = debugInfo.addStringEntry(statusKey, "OK");
                addStringEntry.addStringEntry(debugKeyPrefix + "formatVersion", tzDataSetVersion.formatMajorVersion + "." + tzDataSetVersion.formatMinorVersion).addStringEntry(debugKeyPrefix + "rulesVersion", tzDataSetVersion.rulesVersion).addStringEntry(debugKeyPrefix + "revision", tzDataSetVersion.revision);
            } catch (IOException | TzDataSetVersion.TzDataSetException e) {
                debugInfo.addStringEntry(statusKey, "ERROR");
                debugInfo.addStringEntry(debugKeyPrefix + "exception_class", e.getClass().getName());
                debugInfo.addStringEntry(debugKeyPrefix + "exception_msg", e.getMessage());
                System.logE("Error reading " + ((Object) file), e);
            }
        } else {
            debugInfo.addStringEntry(statusKey, "NOT_FOUND");
        }
    }

    private static void populateTimeZoneLibraryReportedVersion(DebugInfo debugInfo) {
        debugInfo.addStringEntry("core_library.timezone.lib." + "icu4j.tzdb_version", TimeZone.getTZDataVersion());
        debugInfo.addStringEntry("core_library.timezone.lib." + "libcore.tzdb_version", ZoneInfoDB.getInstance().getVersion());
        debugInfo.addStringEntry("core_library.timezone.lib." + "icu4c.tzdb_version", ICU.getTZDataVersion());
    }
}
