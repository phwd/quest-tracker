package com.oculus.bugreporter;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BugReportUploaderJobService extends JobService {
    private static final Uri AUTHORITY = Uri.parse("content://com.oculus.bugreporter.provider");
    private static final Uri DELETE = Uri.parse(((Object) AUTHORITY) + "/report/id/");
    private static final Uri REQUEST_ALL = Uri.parse(((Object) AUTHORITY) + "/report/ready_to_upload");
    private static final String TAG = "BugReportUploaderService";
    private UploadAvailableReports uploaderTask;

    private class UploadAvailableReports extends AsyncTask<Void, Integer, Integer> {
        Context mContext;
        JobParameters mParams;
        int totalReports = 0;
        int uploaded = 0;

        public UploadAvailableReports(Context context, JobParameters params) {
            this.mContext = context;
            this.mParams = params;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public Integer doInBackground(Void... p) {
            Cursor cursor = BugReportUploaderJobService.this.getContentResolver().query(BugReportUploaderJobService.REQUEST_ALL, null, null, null, null);
            boolean z = false;
            if (cursor == null) {
                Log.e(BugReportUploaderJobService.TAG, "Spurious notification. Nothing to upload");
                BugReportUploaderJobService.this.jobFinished(this.mParams, false);
            }
            try {
                FlytrapUploader uploader = new FlytrapUploader(this.mContext);
                while (cursor.moveToNext() && !isCancelled()) {
                    this.totalReports++;
                    int columns = cursor.getColumnCount();
                    BugReport report = new BugReport();
                    String id = cursor.getString(cursor.getColumnIndex(BugReportProvider.REPORTID));
                    report.id = id;
                    report.sessionId = id;
                    Log.d(BugReportUploaderJobService.TAG, "Uploading report " + id);
                    for (int i = 0; i < columns; i++) {
                        String col = cursor.getColumnName(i);
                        int type = cursor.getType(i);
                        if (type == 1) {
                            BugReportUploaderJobService.fillBugReport(report, col, Integer.toString(cursor.getInt(i)));
                        } else if (type != 3) {
                            Log.e(BugReportUploaderJobService.TAG, "Unsupported Data Type");
                        } else {
                            BugReportUploaderJobService.fillBugReport(report, col, cursor.getString(i));
                        }
                    }
                    if (uploader.upload(report)) {
                        if (BugReportUploaderJobService.this.getContentResolver().delete(BugReportUploaderJobService.DELETE.buildUpon().appendPath(id).build(), null, null) <= 0) {
                            Log.e(BugReportUploaderJobService.TAG, "Failed to delete report " + id);
                        }
                        this.uploaded++;
                    }
                }
                cursor.close();
                BugReportUploaderJobService bugReportUploaderJobService = BugReportUploaderJobService.this;
                JobParameters jobParameters = this.mParams;
                if (this.totalReports != this.uploaded) {
                    z = true;
                }
                bugReportUploaderJobService.jobFinished(jobParameters, z);
                return Integer.valueOf(this.uploaded);
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
    }

    public static void scheduleUploadJob(Context context) {
        ComponentName uploaderComponent = new ComponentName(context, BugReportUploaderJobService.class);
        JobInfo uploaderJobInfo = new JobInfo.Builder(1, uploaderComponent).addTriggerContentUri(new JobInfo.TriggerContentUri(Uri.parse("content://com.oculus.bugreporter.provider/report/ready_to_upload"), 0)).setRequiredNetworkType(1).build();
        JobScheduler js = (JobScheduler) context.getSystemService(JobScheduler.class);
        for (JobInfo i : js.getAllPendingJobs()) {
            if (uploaderJobInfo.getId() == i.getId()) {
                return;
            }
        }
        js.schedule(uploaderJobInfo);
    }

    /* access modifiers changed from: private */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static void fillBugReport(BugReport report, String column, String value) {
        char c;
        switch (column.hashCode()) {
            case -1724546052:
                if (column.equals(BugReportProvider.DESCRIPTION)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1304177323:
                if (column.equals(BugReportProvider.DESCRIPTION_RECORDING)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -427040401:
                if (column.equals(BugReportProvider.REPORTID)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -416447130:
                if (column.equals(BugReportProvider.SCREENSHOT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3327407:
                if (column.equals(BugReportProvider.LOGS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 93028124:
                if (column.equals(BugReportProvider.APPID)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 607796817:
                if (column.equals(BugReportProvider.SESSIONID)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 730789300:
                if (column.equals(BugReportProvider.EXTRAMEDIA)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1296531129:
                if (column.equals(BugReportProvider.CATEGORYID)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1514689710:
                if (column.equals(BugReportProvider.PASTAUDIODATA)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                report.description = value;
                return;
            case 1:
                report.appId = value;
                return;
            case 2:
                report.categoryId = value;
                return;
            case 3:
                report.sessionId = value;
                return;
            case 4:
                report.screenshot = new File(value);
                return;
            case 5:
                report.pastAudioData = new File(value);
                return;
            case 6:
                report.logs = new File(value);
                return;
            case 7:
                report.descriptionRecording = new File(value);
                return;
            case '\b':
                List<File> extraMedia = new ArrayList<>();
                if (value != null && !value.isEmpty()) {
                    for (String fileName : value.split(",")) {
                        extraMedia.add(new File(fileName.trim()));
                    }
                }
                report.extraMedia = extraMedia;
                return;
            case '\t':
                return;
            default:
                Log.e(TAG, "Unable to assign column name " + column);
                return;
        }
    }

    public boolean onStartJob(JobParameters params) {
        this.uploaderTask = new UploadAvailableReports(this, params);
        this.uploaderTask.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters params) {
        this.uploaderTask.cancel(true);
        return false;
    }
}
