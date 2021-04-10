package com.oculus.statscollector;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.android.os.AtomsProto;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.statscollector.StatsdManager;

public class FetchStatsTask extends AsyncTask<JobParameters, Void, JobParameters> {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = FetchStatsTask.class.getSimpleName();
    private Context mContext;
    StatsdManager.IEventHandler mEventHandler;
    private final JobService mJobService;
    private final UnifiedTelemetryLogger mTelemetry;

    public FetchStatsTask(JobService jobService) {
        this(jobService, jobService);
    }

    public FetchStatsTask(Context context) {
        this(context, null);
    }

    private FetchStatsTask(Context context, JobService jobService) {
        this.mEventHandler = new StatsdManager.IEventHandler() {
            /* class com.oculus.statscollector.FetchStatsTask.AnonymousClass1 */

            @Override // com.oculus.statscollector.StatsdManager.IEventHandler
            public void handleEvent(long timestamp, AtomsProto.Atom atom) {
                if (atom.hasDiskIo()) {
                    AtomsProto.DiskIo diskIoAtom = atom.getDiskIo();
                    AnalyticsEvent event = new AnalyticsEvent("oculus_mobile_disk_io_by_uid");
                    event.setExtra("timestamp", Long.valueOf(timestamp)).setExtra("uid", Integer.valueOf(diskIoAtom.getUid())).setExtra("fg_bytes_read", Long.valueOf(diskIoAtom.getFgBytesRead())).setExtra("fg_bytes_written", Long.valueOf(diskIoAtom.getFgBytesWrite())).setExtra("bg_bytes_read", Long.valueOf(diskIoAtom.getBgBytesRead())).setExtra("bg_bytes_written", Long.valueOf(diskIoAtom.getBgBytesWrite()));
                    if (FetchStatsTask.DEBUG) {
                        String str = FetchStatsTask.TAG;
                        Log.d(str, "Reporting DiskIo event for UID: " + diskIoAtom.getUid());
                    }
                    FetchStatsTask.this.mTelemetry.reportEvent(event, false);
                } else if (atom.hasLmkKillOccurred()) {
                    AtomsProto.LmkKillOccurred lmkAtom = atom.getLmkKillOccurred();
                    AnalyticsEvent event2 = new AnalyticsEvent("oculus_mobile_lmk_kill_events");
                    event2.setExtra("timestamp", Long.valueOf(timestamp)).setExtra("process_name", lmkAtom.getProcessName()).setExtra("oom_adj_score", Integer.valueOf(lmkAtom.getOomAdjScore())).setExtra("min_oom_score", Integer.valueOf(lmkAtom.getMinOomScore())).setExtra("rss", Long.valueOf(lmkAtom.getRssInBytes())).setExtra("uid", Integer.valueOf(lmkAtom.getUid())).setExtra("minfree", Integer.valueOf(lmkAtom.getMinfree())).setExtra("other_free", Long.valueOf(lmkAtom.getOtherFree())).setExtra("other_file", Long.valueOf(lmkAtom.getOtherFile())).setExtra("pss", Long.valueOf(lmkAtom.getPssInBytes())).setExtra("sys_meminfo", lmkAtom.getSysMeminfo());
                    if (FetchStatsTask.DEBUG) {
                        String str2 = FetchStatsTask.TAG;
                        Log.d(str2, "Reporting LmkKillOccurred event for process: " + lmkAtom.getProcessName());
                    }
                    FetchStatsTask.this.mTelemetry.reportEvent(event2, false);
                } else if (atom.hasLowStorageStateChanged()) {
                    AtomsProto.LowStorageStateChanged lowStorageAtom = atom.getLowStorageStateChanged();
                    AnalyticsEvent event3 = new AnalyticsEvent("oculus_mobile_low_storage_events");
                    event3.setExtra("timestamp", Long.valueOf(timestamp)).setExtra("volume", lowStorageAtom.getVolumeDescription());
                    if (FetchStatsTask.DEBUG) {
                        String str3 = FetchStatsTask.TAG;
                        Log.d(str3, "Reporting LowStorageStateChanged event for volume: " + lowStorageAtom.getVolumeDescription());
                    }
                    FetchStatsTask.this.mTelemetry.reportEvent(event3, false);
                } else if (atom.hasThermalThrottlingSeverityStateChanged()) {
                    AtomsProto.ThermalThrottlingSeverityStateChanged thermalAtom = atom.getThermalThrottlingSeverityStateChanged();
                    AnalyticsEvent event4 = new AnalyticsEvent("oculus_mobile_thermal_throttling_events");
                    event4.setExtra("timestamp", Long.valueOf(timestamp)).setExtra("sensor_name", thermalAtom.getSensorName()).setExtra("severity", thermalAtom.getSeverity().toString()).setExtra("temperature", Integer.valueOf(thermalAtom.getTemperatureDeciCelsius()));
                    if (FetchStatsTask.DEBUG) {
                        String str4 = FetchStatsTask.TAG;
                        Log.d(str4, "Reporting ThermalThrottlingSeverityStageChanged for sensor: " + thermalAtom.getSensorName() + ": " + thermalAtom.getTemperatureDeciCelsius());
                    }
                    FetchStatsTask.this.mTelemetry.reportEvent(event4, false);
                } else if (atom.hasWallClockTimeShifted()) {
                    AtomsProto.WallClockTimeShifted wallClockAtom = atom.getWallClockTimeShifted();
                    AnalyticsEvent event5 = new AnalyticsEvent("oculus_mobile_wall_clock_events");
                    event5.setExtra("wall_clock_timestamp", Long.valueOf(wallClockAtom.getWallClockTimestampMillis()));
                    if (FetchStatsTask.DEBUG) {
                        String str5 = FetchStatsTask.TAG;
                        Log.d(str5, "Reporting WallClockTimeShifted: " + wallClockAtom.getWallClockTimestampMillis());
                    }
                    FetchStatsTask.this.mTelemetry.reportEvent(event5, false);
                } else if (atom.hasWifiEnabledStateChanged()) {
                    AtomsProto.WifiEnabledStateChanged wifiEnabledAtom = atom.getWifiEnabledStateChanged();
                    AnalyticsEvent event6 = new AnalyticsEvent("oculus_mobile_wifi_enabled_events");
                    event6.setExtra("timestamp", Long.valueOf(timestamp)).setExtra("state", wifiEnabledAtom.getState());
                    if (FetchStatsTask.DEBUG) {
                        String str6 = FetchStatsTask.TAG;
                        Log.d(str6, "Reporting WifiEnabledStateChange: " + wifiEnabledAtom.getState());
                    }
                    FetchStatsTask.this.mTelemetry.reportEvent(event6, false);
                }
            }
        };
        this.mContext = context;
        this.mJobService = jobService;
        this.mTelemetry = UnifiedTelemetryLogger.getInstance(context);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public JobParameters doInBackground(JobParameters... params) {
        if (DEBUG) {
            Log.d(TAG, "Fetching reports from statsd");
        }
        StatsdManager.getReports(this.mContext, this.mEventHandler);
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
}
