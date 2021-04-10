package com.oculus.bugreportservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.BugReport;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import oculus.internal.FileUtils;

public class BugReportServiceUtil {
    private Context mContext;

    public BugReportServiceUtil(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public String createBugReport(boolean z) {
        String bugId = getBugId();
        if (!"0".equals(bugId) && z) {
            Intent intent = new Intent(this.mContext, BugReportLogService.class);
            intent.putExtra("bug_id", bugId);
            this.mContext.startService(intent);
            Log.d("BugReportService", "Start collecting logs early (before user decides to attach logs) for report " + bugId);
        }
        return bugId;
    }

    /* access modifiers changed from: protected */
    public boolean submitBugReport(BugReport bugReport) {
        scheduleCleanupJob();
        BugFileUtils bugFileUtils = new BugFileUtils(this.mContext);
        Extradata extradata = new Extradata();
        extradata.extraMedia = bugReport.getExtraMedia() != null ? bugReport.getExtraMedia() : "";
        extradata.appId = bugReport.getAppId();
        extradata.categoryId = bugReport.getCategoryId();
        extradata.source = bugReport.getEntrySource() != null ? bugReport.getEntrySource() : "unknown";
        extradata.appId = bugReport.getAppId();
        extradata.shouldUploadLogs = bugReport.getAttachLogs();
        extradata.shouldUploadScreenshot = bugReport.getScreenshot() != null;
        Extradata.writeToFile(new File(bugFileUtils.reportFilename(bugReport.getId(), "extradata.json")), extradata);
        if (!addScreenShot(bugReport) || !addDescription(bugReport, bugFileUtils)) {
            return false;
        }
        if (bugReport.getAttachLogs() && !bugReport.getEarlyCollectLogs()) {
            Intent intent = new Intent(this.mContext, BugReportLogService.class);
            intent.putExtra("bug_id", bugReport.getId());
            this.mContext.startService(intent);
            Log.d("BugReportService", "Start collecting logs late (after user selects to attach logs) for report " + bugReport.getId());
        }
        addPastAudioData(bugReport);
        reportTelemetryEvent(bugReport, "submitted");
        Log.d("BugReportService", "Submitted report " + bugReport.getId());
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean cancelBugReport(BugReport bugReport) {
        if (deleteBugReport(bugReport) <= 0) {
            Log.e("BugReportService", "Failed to delete report " + bugReport.getId());
            return false;
        }
        reportTelemetryEvent(bugReport, "submission_canceled");
        Log.d("BugReportService", "Cancelled report " + bugReport.getId());
        return true;
    }

    /* access modifiers changed from: protected */
    public String getBugId() {
        Uri insert = this.mContext.getContentResolver().insert(Uri.parse("content://com.oculus.bugreportservice.provider/report"), new ContentValues());
        if (insert != null) {
            return insert.getLastPathSegment();
        }
        Log.e("BugReportService", "Failed to create bug report");
        return "0";
    }

    /* access modifiers changed from: protected */
    public void addPastAudioData(BugReport bugReport) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pastAudioData", (Boolean) true);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.update(Uri.parse("content://com.oculus.bugreportservice.provider/report/id/" + bugReport.getId()), contentValues, "", new String[0]);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0082, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0083, code lost:
        if (r2 != null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0089, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008a, code lost:
        r6.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008d, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean addScreenShot(com.oculus.os.BugReport r7) {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.bugreportservice.BugReportServiceUtil.addScreenShot(com.oculus.os.BugReport):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean addDescription(BugReport bugReport, BugFileUtils bugFileUtils) {
        Throwable th;
        IOException e;
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(bugFileUtils.reportFilename(bugReport.getId(), "description.txt")));
            try {
                fileOutputStream2.write((String.format("Description: %s", bugReport.getDescription()) + "\n" + String.format("Source App: %s", bugReport.getSourceApp()) + "\n" + String.format("Entry source: %s", bugReport.getEntrySource()) + "\n").getBytes());
                FileUtils.closeQuietly(fileOutputStream2);
                ContentValues contentValues = new ContentValues();
                contentValues.put("description", (Boolean) true);
                ContentResolver contentResolver = this.mContext.getContentResolver();
                contentResolver.update(Uri.parse("content://com.oculus.bugreportservice.provider/report/id/" + bugReport.getId()), contentValues, null, null);
                return true;
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    Log.e("BugReportService", "Couldn't save description file.", e);
                    FileUtils.closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                FileUtils.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e("BugReportService", "Couldn't save description file.", e);
            FileUtils.closeQuietly(fileOutputStream);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public int deleteBugReport(BugReport bugReport) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return contentResolver.delete(Uri.parse("content://com.oculus.bugreportservice.provider/report/id/" + bugReport.getId()), null, null);
    }

    /* access modifiers changed from: protected */
    public void scheduleCleanupJob() {
        BugReportCleanupJobService.scheduleCleanupJob(this.mContext);
    }

    /* access modifiers changed from: protected */
    public void reportTelemetryEvent(BugReport bugReport, String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_bugreport");
        analyticsEvent.setExtra("bug_id", bugReport.getId());
        analyticsEvent.setExtra("state", str);
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(analyticsEvent, false);
    }
}
