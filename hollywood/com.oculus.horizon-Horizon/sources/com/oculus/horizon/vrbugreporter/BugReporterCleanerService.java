package com.oculus.horizon.vrbugreporter;

import X.AnonymousClass0NO;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BugReporterCleanerService extends JobService {
    public static final String TAG = "BugReporterCleanerService";
    public final CommandProcessor mCurrProcessor = new CommandProcessor();
    public JobParameters mParams;

    public final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        public CommandProcessor() {
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object[]] */
        @Override // android.os.AsyncTask
        public final Void doInBackground(Void[] voidArr) {
            ArrayList<BugReport> A02 = BugReport.A02(BugReporterCleanerService.this.getApplicationContext());
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.add(6, -7);
            int i = 0;
            while (!isCancelled()) {
                if (i < A02.size()) {
                    BugReport bugReport = A02.get(i);
                    if (bugReport.mDate.before(instance.getTime()) && !bugReport.A08()) {
                        AnonymousClass0NO.A0E(BugReporterCleanerService.TAG, "could not delete bug report %s", bugReport.A05());
                    }
                    i++;
                } else {
                    BugReporterCleanerService bugReporterCleanerService = BugReporterCleanerService.this;
                    bugReporterCleanerService.jobFinished(bugReporterCleanerService.mParams, false);
                    return null;
                }
            }
            return null;
        }
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        this.mParams = jobParameters;
        this.mCurrProcessor.execute(new Void[0]);
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        this.mCurrProcessor.cancel(false);
        return true;
    }
}
