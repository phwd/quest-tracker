package com.oculus.vrshell.home.config;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import bolts.Continuation;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.panellib.CrashReporting;
import com.oculus.util.constants.OculusConstants;
import com.oculus.vrshell.home.HomeApplication;
import com.oculus.vrshell.home.HomeEventLogger;

public class ConfigUpdaterJobService extends JobService {
    private static final int JOB_ID_CONFIG_CLEAR = -676753713;
    private static final int JOB_ID_CONFIG_UPDATE = -676753714;
    private static final long REFRESH_INTERVAL_MS = 28800000;
    private static final String TAG = ConfigUpdaterJobService.class.getSimpleName();
    private static final ConfigUpdater mConfigUpdater = new ConfigUpdater();

    public ConfigUpdaterJobService() {
        try {
            System.loadLibrary("home");
        } catch (Exception e) {
            BLog.e(TAG, "ConfigUpdaterJobService() loadLibrary FAIL: " + e.toString(), e);
        }
    }

    public boolean onStartJob(final JobParameters params) {
        CrashReporting.initialize(this, null, "", HomeApplication.FB_HOME_APP_ID, HomeApplication.FB_HOME_APP_NAME);
        NetworkHeaders.init(this, new UserAgentBuilder(), OculusConstants.HOME_SHELL_USER_AGENT_APP_NAME);
        final int jobId = params.getJobId();
        BLog.d(TAG, "onStartJob id=%d", Integer.valueOf(jobId));
        postOnUiThread(new Runnable() {
            /* class com.oculus.vrshell.home.config.ConfigUpdaterJobService.AnonymousClass1 */

            public void run() {
                Task<Void> configTask;
                if (jobId == ConfigUpdaterJobService.JOB_ID_CONFIG_UPDATE) {
                    configTask = ConfigUpdaterJobService.mConfigUpdater.fetchAsync(this);
                } else if (jobId == ConfigUpdaterJobService.JOB_ID_CONFIG_CLEAR) {
                    configTask = ConfigUpdaterJobService.mConfigUpdater.clearAsync(this);
                } else {
                    ConfigUpdaterJobService.this.onFailed(params, new RuntimeException("Invalid job id given to ConfigUpdaterJobService."));
                    return;
                }
                configTask.continueWith(new Continuation<Void, Void>() {
                    /* class com.oculus.vrshell.home.config.ConfigUpdaterJobService.AnonymousClass1.AnonymousClass1 */

                    @Override // bolts.Continuation
                    public Void then(Task<Void> task) throws Exception {
                        if (task.isFaulted()) {
                            ConfigUpdaterJobService.this.onFailed(params, task.getError());
                        } else {
                            ConfigUpdaterJobService.this.onSucceed(params);
                        }
                        if (jobId != ConfigUpdaterJobService.JOB_ID_CONFIG_CLEAR) {
                            return null;
                        }
                        System.exit(1);
                        return null;
                    }
                });
            }
        });
        return true;
    }

    private static void postOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onSucceed(JobParameters params) {
        BLog.d(TAG, "onSucceed()");
        jobFinished(params, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onFailed(JobParameters params, Exception error) {
        BLog.e(TAG, "onFailed()", error);
        jobFinished(params, true);
    }

    public boolean onStopJob(JobParameters params) {
        BLog.d(TAG, "onStopJob()");
        return true;
    }

    public static void schedule(Context context) {
        BLog.i(TAG, "schedule()");
        if (isJobScheduled(context, JOB_ID_CONFIG_UPDATE)) {
            BLog.d(TAG, "schedule(): already scheduled.");
            return;
        }
        HomeEventLogger.reportEvent("oculus_config", "status", "config_updater_schedule_started");
        int scheduleResult = getJobScheduler(context).schedule(new JobInfo.Builder(JOB_ID_CONFIG_UPDATE, new ComponentName(context, ConfigUpdaterJobService.class.getName())).setPeriodic(REFRESH_INTERVAL_MS).setRequiredNetworkType(1).setPersisted(true).setBackoffCriteria(30000, 1).build());
        if (scheduleResult != 1) {
            BLog.e(TAG, "schedule(): failed");
            HomeEventLogger.reportEvent("oculus_config", "status", "config_updater_schedule_failed", "error", "schedule_failed", "error_details", Integer.toString(scheduleResult));
            return;
        }
        BLog.d(TAG, "schedule(): success.");
        HomeEventLogger.reportEvent("oculus_config", "status", "config_updater_schedule_completed");
    }

    public static void unschedule(Context context) {
        BLog.i(TAG, "unschedule()");
        getJobScheduler(context).cancel(JOB_ID_CONFIG_UPDATE);
        if (isJobScheduled(context, JOB_ID_CONFIG_CLEAR)) {
            BLog.d(TAG, "unschedule(): already unscheduled.");
            return;
        }
        HomeEventLogger.reportEvent("oculus_config", "status", "config_updater_unschedule_started");
        int scheduleResult = getJobScheduler(context).schedule(new JobInfo.Builder(JOB_ID_CONFIG_CLEAR, new ComponentName(context, ConfigUpdaterJobService.class.getName())).setOverrideDeadline(0).setBackoffCriteria(30000, 1).build());
        if (scheduleResult != 1) {
            BLog.e(TAG, "unschedule(): failed");
            HomeEventLogger.reportEvent("oculus_config", "status", "config_updater_unschedule_failed", "error", "clear_schedule_failed", "error_details", Integer.toString(scheduleResult));
            return;
        }
        HomeEventLogger.reportEvent("oculus_config", "status", "config_updater_unschedule_completed");
        BLog.d(TAG, "unschedule(): success.");
    }

    private static boolean isJobScheduled(Context context, int jobId) {
        for (JobInfo jobInfo : getJobScheduler(context).getAllPendingJobs()) {
            if (jobInfo.getId() == jobId) {
                return true;
            }
        }
        return false;
    }

    private static JobScheduler getJobScheduler(Context context) {
        return (JobScheduler) context.getSystemService("jobscheduler");
    }
}
