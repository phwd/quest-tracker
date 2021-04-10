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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BugReportCleanupJobService extends JobService {
    private static final Uri AUTHORITY = Uri.parse("content://com.oculus.bugreporter.provider");
    private static final int DAYS_TO_EXPIRE = 1;
    private static final Uri DELETE = Uri.parse(((Object) AUTHORITY) + "/report/id/");
    private static final long PERIOD_MS = TimeUnit.DAYS.toMillis(1);
    private static final Uri REQUEST_INCOMPLETE = Uri.parse(((Object) AUTHORITY) + "/report/incomplete");
    private static final String TAG = "BugReportCleanupService";
    private CleanIncompleteReportsTask mCleanupTask;

    private class CleanIncompleteReportsTask extends AsyncTask<Void, Void, Void> {
        BugFileUtils mBugFileUtils;
        JobParameters mParams;

        public CleanIncompleteReportsTask(Context context, JobParameters params) {
            this.mParams = params;
            this.mBugFileUtils = new BugFileUtils(context);
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public Void doInBackground(Void... p) {
            Cursor cursor = BugReportCleanupJobService.this.getContentResolver().query(BugReportCleanupJobService.REQUEST_INCOMPLETE, null, null, null, null);
            if (cursor == null) {
                BugReportCleanupJobService.this.jobFinished(this.mParams, false);
            }
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.add(5, -1);
            Date expiration = cal.getTime();
            while (cursor.moveToNext() && !isCancelled()) {
                try {
                    String id = cursor.getString(cursor.getColumnIndex(BugReportProvider.REPORTID));
                    Metadata metadata = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(id, BugFileUtils.METADATA)));
                    if (new Date(metadata == null ? 0 : metadata.timestamp).before(expiration)) {
                        if (BugReportCleanupJobService.this.getContentResolver().delete(BugReportCleanupJobService.DELETE.buildUpon().appendPath(id).build(), null, null) <= 0) {
                            Log.e(BugReportCleanupJobService.TAG, "Failed to delete expired report " + id);
                        }
                    }
                } catch (Throwable th) {
                    cursor.close();
                    throw th;
                }
            }
            cursor.close();
            BugReportCleanupJobService.this.jobFinished(this.mParams, false);
            return null;
        }
    }

    public static void scheduleCleanupJob(Context context) {
        JobInfo cleanupJobInfo = new JobInfo.Builder(2, new ComponentName(context, BugReportCleanupJobService.class)).setRequiresDeviceIdle(true).setPeriodic(PERIOD_MS).setPersisted(true).build();
        JobScheduler js = (JobScheduler) context.getSystemService(JobScheduler.class);
        for (JobInfo i : js.getAllPendingJobs()) {
            if (cleanupJobInfo.getId() == i.getId()) {
                return;
            }
        }
        js.schedule(cleanupJobInfo);
    }

    public boolean onStartJob(JobParameters params) {
        this.mCleanupTask = new CleanIncompleteReportsTask(this, params);
        this.mCleanupTask.execute(new Void[0]);
        return true;
    }

    public boolean onStopJob(JobParameters params) {
        this.mCleanupTask.cancel(true);
        return true;
    }
}
