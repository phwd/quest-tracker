package org.chromium.chrome.browser.historyreport;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HistoryReportJniBridge {
    public static DeltaFileEntry[] createDeltaFileEntriesArray(int i) {
        return new DeltaFileEntry[i];
    }

    public static UsageReport[] createUsageReportsArray(int i) {
        return new UsageReport[i];
    }

    public static void setDeltaFileEntry(DeltaFileEntry[] deltaFileEntryArr, int i, long j, String str, String str2, String str3, int i2, String str4, String str5) {
        deltaFileEntryArr[i] = new DeltaFileEntry(j, str, str2, str3, i2, str4, str5);
    }

    public static void setUsageReport(UsageReport[] usageReportArr, int i, String str, String str2, long j, boolean z) {
        usageReportArr[i] = new UsageReport(str, str2, j, z);
    }

    public final void onDataChanged() {
        throw null;
    }

    public final void onDataCleared() {
        throw null;
    }

    public final void startReportingTask() {
        throw null;
    }

    public final void stopReportingTask() {
        throw null;
    }
}
