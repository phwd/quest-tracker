package com.oculus.vrshell.panels.telemetry;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.telemetry.SectionTrackerImpl;
import java.util.Arrays;

public class TelemetryManager implements SectionTrackerImpl.SectionListener {
    private static final String TAG = LoggingUtil.tag(TelemetryManager.class);
    private boolean mDebugPrinting = false;
    private final Logger mLogger;
    private final SectionTracker mSectionTracker;

    public TelemetryManager(Logger logger, SectionTracker sectionTracker) {
        this.mLogger = logger;
        this.mSectionTracker = sectionTracker;
    }

    public void logJsonEvent(String str, String str2) {
        if (this.mDebugPrinting) {
            String str3 = TAG;
            Log.d(str3, "logEvent(" + str + ") extras" + str2 + "");
        }
        this.mLogger.rawLogJsonEvent(str, str2);
    }

    public void logEvent(String str, String... strArr) {
        SectionTracker sectionTracker = this.mSectionTracker;
        if (sectionTracker != null) {
            strArr = sectionTracker.addSectionData(str, strArr);
        }
        if (this.mDebugPrinting) {
            String str2 = TAG;
            Log.d(str2, "logEvent(" + str + ") extras" + Arrays.toString(strArr) + "");
        }
        this.mLogger.rawLogEvent(str, strArr);
    }

    public void setDebugPrintingEnabled(boolean z) {
        this.mDebugPrinting = z;
    }

    public SectionTracker getSectionTracker() {
        return this.mSectionTracker;
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTrackerImpl.SectionListener
    public void onSectionEntryCallback() {
        logEvent(Event.SECTION_ENTRY.toString().toLowerCase(), new String[0]);
    }

    @Override // com.oculus.vrshell.panels.telemetry.SectionTrackerImpl.SectionListener
    public void onSectionExitCallback() {
        logEvent(Event.SECTION_EXIT.toString().toLowerCase(), new String[0]);
    }
}
