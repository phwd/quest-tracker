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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BugReportCleanupJobService extends JobService {
    private static final Uri AUTHORITY = Uri.parse("content://com.oculus.bugreportservice.provider");
    private static final Uri DELETE = Uri.parse(AUTHORITY + "/report/id/");
    private static final long PERIOD_MS = TimeUnit.DAYS.toMillis(1);
    private static final Uri REQUEST_INCOMPLETE = Uri.parse(AUTHORITY + "/report/incomplete");
    private CleanIncompleteReportsTask mCleanupTask;

    private class CleanIncompleteReportsTask extends AsyncTask {
        BugFileUtils mBugFileUtils;
        JobParameters mParams;

        public CleanIncompleteReportsTask(Context context, JobParameters jobParameters) {
            this.mParams = jobParameters;
            this.mBugFileUtils = new BugFileUtils(context);
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            Cursor query = BugReportCleanupJobService.this.getContentResolver().query(BugReportCleanupJobService.REQUEST_INCOMPLETE, null, null, null, null);
            if (query == null) {
                BugReportCleanupJobService.this.jobFinished(this.mParams, false);
            }
            Calendar instance = Calendar.getInstance(Locale.US);
            instance.add(5, -1);
            Date time = instance.getTime();
            while (query.moveToNext() && !isCancelled()) {
                try {
                    String string = query.getString(query.getColumnIndex("reportId"));
                    Metadata readFromFile = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(string, "metadata.json")));
                    if (new Date(readFromFile == null ? 0 : readFromFile.timestamp).before(time)) {
                        if (BugReportCleanupJobService.this.getContentResolver().delete(BugReportCleanupJobService.DELETE.buildUpon().appendPath(string).build(), null, null) <= 0) {
                            Log.e("BugReportCleanupService", "Failed to delete expired report " + string);
                        }
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            query.close();
            BugReportCleanupJobService.this.jobFinished(this.mParams, false);
            return null;
        }
    }

    public static void scheduleCleanupJob(Context context) {
        JobInfo build = new JobInfo.Builder(2, new ComponentName(context, BugReportCleanupJobService.class)).setRequiresDeviceIdle(true).setPeriodic(PERIOD_MS).setPersisted(true).build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
            if (build.getId() == jobInfo.getId()) {
                return;
            }
        }
        jobScheduler.schedule(build);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        this.mCleanupTask = new CleanIncompleteReportsTask(this, jobParameters);
        this.mCleanupTask.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        this.mCleanupTask.cancel(true);
        return true;
    }
}
