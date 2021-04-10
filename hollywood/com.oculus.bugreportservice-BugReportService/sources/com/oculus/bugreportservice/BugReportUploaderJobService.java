package com.oculus.bugreportservice;

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

public class BugReportUploaderJobService extends JobService {
    private static final Uri AUTHORITY = Uri.parse("content://com.oculus.bugreportservice.provider");
    private static final Uri DELETE = Uri.parse(AUTHORITY + "/report/id/");
    private static final Uri REQUEST_ALL = Uri.parse(AUTHORITY + "/report/ready_to_upload");
    private UploadAvailableReports uploaderTask;

    private class UploadAvailableReports extends AsyncTask {
        Context mContext;
        JobParameters mParams;
        int totalReports = 0;
        int uploaded = 0;

        public UploadAvailableReports(Context context, JobParameters jobParameters) {
            this.mContext = context;
            this.mParams = jobParameters;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public Integer doInBackground(Void... voidArr) {
            Cursor query = BugReportUploaderJobService.this.getContentResolver().query(BugReportUploaderJobService.REQUEST_ALL, null, null, null, null);
            boolean z = false;
            if (query == null) {
                Log.e("BugReportUploaderService", "Spurious notification. Nothing to upload");
                BugReportUploaderJobService.this.jobFinished(this.mParams, false);
            }
            try {
                FlytrapUploader flytrapUploader = new FlytrapUploader(this.mContext);
                while (query.moveToNext() && !isCancelled()) {
                    this.totalReports++;
                    int columnCount = query.getColumnCount();
                    BugReport bugReport = new BugReport();
                    String string = query.getString(query.getColumnIndex("reportId"));
                    bugReport.id = string;
                    bugReport.sessionId = string;
                    Log.d("BugReportUploaderService", "Uploading report " + string);
                    for (int i = 0; i < columnCount; i++) {
                        String columnName = query.getColumnName(i);
                        int type = query.getType(i);
                        if (type == 1) {
                            BugReportUploaderJobService.fillBugReport(bugReport, columnName, Integer.toString(query.getInt(i)));
                        } else if (type != 3) {
                            Log.e("BugReportUploaderService", "Unsupported Data Type");
                        } else {
                            BugReportUploaderJobService.fillBugReport(bugReport, columnName, query.getString(i));
                        }
                    }
                    if (flytrapUploader.upload(bugReport)) {
                        Log.d("BugReportUploaderService", "upload complete! id: " + string);
                        if (BugReportUploaderJobService.this.getContentResolver().delete(BugReportUploaderJobService.DELETE.buildUpon().appendPath(string).build(), null, null) <= 0) {
                            Log.e("BugReportUploaderService", "Failed to delete report " + string);
                        }
                        this.uploaded++;
                    }
                }
                query.close();
                BugReportUploaderJobService bugReportUploaderJobService = BugReportUploaderJobService.this;
                JobParameters jobParameters = this.mParams;
                if (this.totalReports != this.uploaded) {
                    z = true;
                }
                bugReportUploaderJobService.jobFinished(jobParameters, z);
                return Integer.valueOf(this.uploaded);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
    }

    public static void scheduleUploadJob(Context context) {
        JobInfo build = new JobInfo.Builder(1, new ComponentName(context, BugReportUploaderJobService.class)).setRequiredNetworkType(1).build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
            if (build.getId() == jobInfo.getId()) {
                return;
            }
        }
        jobScheduler.schedule(build);
    }

    /* access modifiers changed from: private */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static void fillBugReport(BugReport bugReport, String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1724546052:
                if (str.equals("description")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1304177323:
                if (str.equals("descriptionRecording")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -427040401:
                if (str.equals("reportId")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -416447130:
                if (str.equals("screenshot")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3327407:
                if (str.equals("logs")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 93028124:
                if (str.equals("appId")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 607796817:
                if (str.equals("sessionId")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 730789300:
                if (str.equals("extraMedia")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1296531129:
                if (str.equals("categoryId")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1514689710:
                if (str.equals("pastAudioData")) {
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
                bugReport.description = str2;
                return;
            case 1:
                bugReport.appId = str2;
                return;
            case 2:
                bugReport.categoryId = str2;
                return;
            case 3:
                bugReport.sessionId = str2;
                return;
            case 4:
                bugReport.screenshot = new File(str2);
                return;
            case 5:
                bugReport.pastAudioData = new File(str2);
                return;
            case 6:
                bugReport.logs = new File(str2);
                return;
            case 7:
                bugReport.descriptionRecording = new File(str2);
                return;
            case '\b':
                ArrayList arrayList = new ArrayList();
                if (str2 != null && !str2.isEmpty()) {
                    for (String str3 : str2.split(",")) {
                        arrayList.add(new File(str3.trim()));
                    }
                }
                bugReport.extraMedia = arrayList;
                return;
            case '\t':
                return;
            default:
                Log.e("BugReportUploaderService", "Unable to assign column name " + str);
                return;
        }
    }

    public boolean onStartJob(JobParameters jobParameters) {
        this.uploaderTask = new UploadAvailableReports(this, jobParameters);
        this.uploaderTask.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        this.uploaderTask.cancel(true);
        return false;
    }
}
