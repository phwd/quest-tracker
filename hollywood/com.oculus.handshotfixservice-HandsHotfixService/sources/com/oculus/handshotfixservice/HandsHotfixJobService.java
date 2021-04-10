package com.oculus.handshotfixservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.oculus.os.SettingsManager;
import oculus.internal.Gatekeeper;

public class HandsHotfixJobService extends JobService {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String HANDS_HOTFIX_SHARED_PREFS = "hands_hotfix_service_prefs";
    private static final String HAND_SETTINGS_MIGRATION_COMPLETE = "hand_settings_migration_complete";
    private static final int PRIMARY_USER_ID = 0;
    private static final String TAG = "HandsHotfixService";
    private SettingsMigrationTask mTask;

    private class SettingsMigrationTask extends AsyncTask<JobParameters, Void, JobParameters> {
        private Context mContext;
        private final JobService mJobService;

        public SettingsMigrationTask(HandsHotfixJobService handsHotfixJobService, JobService jobService) {
            this(jobService, jobService);
        }

        public SettingsMigrationTask(HandsHotfixJobService handsHotfixJobService, Context context) {
            this(context, null);
        }

        private SettingsMigrationTask(Context context, JobService jobService) {
            this.mContext = context;
            this.mJobService = jobService;
        }

        /* access modifiers changed from: protected */
        @WorkerThread
        public JobParameters doInBackground(JobParameters... params) {
            Gatekeeper gk = new Gatekeeper(38);
            SharedPreferences prefs = getSharedPreferences();
            if (gk.isEnabled() && !prefs.getBoolean(HandsHotfixJobService.HAND_SETTINGS_MIGRATION_COMPLETE, HandsHotfixJobService.DEBUG)) {
                if (HandsHotfixJobService.DEBUG) {
                    Log.d(HandsHotfixJobService.TAG, "Hands hotfix GK passes and migration not complete, performing migration");
                }
                migrateSettings();
                prefs.edit().putBoolean(HandsHotfixJobService.HAND_SETTINGS_MIGRATION_COMPLETE, true).commit();
                if (HandsHotfixJobService.DEBUG) {
                    Log.d(HandsHotfixJobService.TAG, "Hand settings migration complete, disabling hotfix service");
                }
                disableSelf();
            }
            if (params != null) {
                return params[0];
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @MainThread
        public void onPostExecute(JobParameters params) {
            JobService jobService = this.mJobService;
            if (jobService != null) {
                jobService.jobFinished(params, true);
            }
        }

        private void migrateSettings() {
            SettingsManager sm = new SettingsManager(this.mContext);
            boolean handTrackingOptInV20UserVal = sm.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN_V20, HandsHotfixJobService.DEBUG);
            boolean handTrackingOptInVal = sm.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN, HandsHotfixJobService.DEBUG);
            if (handTrackingOptInV20UserVal && !handTrackingOptInVal) {
                if (HandsHotfixJobService.DEBUG) {
                    Log.d(HandsHotfixJobService.TAG, "Detected hand_tracking_opt_in setting was disabled, re-enabling via GK");
                }
                sm.setBoolean(SettingsManager.HAND_TRACKING_OPT_IN, true);
            }
            boolean handTrackingEnabledUserVal = sm.getBoolean(SettingsManager.HAND_TRACKING_ENABLED, HandsHotfixJobService.DEBUG, 0);
            boolean handTrackingEnabledVal = sm.getBoolean(SettingsManager.HAND_TRACKING_ENABLED, HandsHotfixJobService.DEBUG);
            if (handTrackingEnabledUserVal && !handTrackingEnabledVal) {
                if (HandsHotfixJobService.DEBUG) {
                    Log.d(HandsHotfixJobService.TAG, "Detected hand_tracking_enabled setting was disabled, re-enabling via GK");
                }
                sm.setBoolean(SettingsManager.HAND_TRACKING_ENABLED, true);
                sm.setBoolean(SettingsManager.HAND_TRACKING_OPT_IN, true);
            }
            boolean handTrackingUseIotUserVal = sm.getBoolean(SettingsManager.HAND_TRACKING_USE_IOT, HandsHotfixJobService.DEBUG, 0);
            boolean handTrackingUseIotVal = sm.getBoolean(SettingsManager.HAND_TRACKING_USE_IOT, HandsHotfixJobService.DEBUG);
            if (handTrackingUseIotUserVal && !handTrackingUseIotVal) {
                if (HandsHotfixJobService.DEBUG) {
                    Log.d(HandsHotfixJobService.TAG, "Detected hand_tracking_use_iot setting was disabled, re-enabling via GK");
                }
                sm.setBoolean(SettingsManager.HAND_TRACKING_USE_IOT, true);
            }
        }

        private void disableSelf() {
            PackageManager pm = this.mContext.getPackageManager();
            ComponentName bootReceiver = new ComponentName(this.mContext.getPackageName(), BootReceiver.class.getName());
            ComponentName handsHotfixJobService = new ComponentName(this.mContext.getPackageName(), HandsHotfixJobService.class.getName());
            pm.setComponentEnabledSetting(bootReceiver, 2, 1);
            pm.setComponentEnabledSetting(handsHotfixJobService, 2, 1);
        }

        private SharedPreferences getSharedPreferences() {
            return this.mContext.getSharedPreferences(HandsHotfixJobService.HANDS_HOTFIX_SHARED_PREFS, 0);
        }
    }

    @MainThread
    public boolean onStartJob(@NonNull JobParameters params) {
        this.mTask = new SettingsMigrationTask(this, (JobService) this);
        if (DEBUG) {
            Log.d(TAG, "Starting HandsHotfix job");
        }
        this.mTask.execute(params);
        return true;
    }

    @MainThread
    public boolean onStopJob(@NonNull JobParameters params) {
        SettingsMigrationTask settingsMigrationTask = this.mTask;
        if (settingsMigrationTask != null) {
            settingsMigrationTask.cancel(true);
        }
        if (DEBUG) {
            Log.d(TAG, "Stopped HandsHotfix job");
        }
        return true;
    }
}
