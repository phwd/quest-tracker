package com.oculus.statscollectorn;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.util.Pair;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchBLEStatsTask extends AsyncTask<JobParameters, Void, JobParameters> {
    private static final boolean DEBUG = false;
    private static final String TAG = FetchBLEStatsTask.class.getSimpleName();
    private Context mContext;
    private final JobService mJobService;

    /* access modifiers changed from: private */
    public enum ReaderState {
        UNKNOWN,
        BONDED_DEVICES,
        CONNECTION_EVENTS,
        PROFILE
    }

    public FetchBLEStatsTask(JobService jobService) {
        this.mJobService = jobService;
        this.mContext = jobService;
    }

    public FetchBLEStatsTask(Context context) {
        this.mJobService = null;
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public JobParameters doInBackground(JobParameters... params) {
        HashMap<String, Date> knownConnectionTimes;
        String disconnectedReason;
        try {
            HashMap<String, String> knownBondedDevices = new HashMap<>();
            HashMap<String, Date> knownConnectionTimes2 = new HashMap<>();
            HashMap<String, Pair<Date, String>> knownDisconnectionTimes = new HashMap<>();
            Process proc = Runtime.getRuntime().exec("dumpsys bluetooth_manager");
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            ReaderState readerState = ReaderState.UNKNOWN;
            Pattern bondedDevicesPattern = Pattern.compile("^ *(..:..:..:..:..:..) *(.*)");
            Pattern connectionEventsDisconnectedPattern = Pattern.compile("^ +([0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9][0-9][0-9]) +DISCONNECTED +(..:..:..:..:..:..) reason=(.*)");
            Pattern connectionEventsConnectedPattern = Pattern.compile("^ +([0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9][0-9][0-9]) +CONNECTED +(..:..:..:..:..:..)");
            SimpleDateFormat connectionEventTimeFormater = new SimpleDateFormat("MM-dd HH:mm:ss");
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                } else if (line.contains("Bonded devices:")) {
                    readerState = ReaderState.BONDED_DEVICES;
                } else if (line.contains("Connection Events:")) {
                    readerState = ReaderState.CONNECTION_EVENTS;
                } else if (line.contains("Profile:")) {
                    readerState = ReaderState.PROFILE;
                } else if (readerState == ReaderState.BONDED_DEVICES) {
                    Matcher m = bondedDevicesPattern.matcher(line);
                    if (m.matches() && !knownBondedDevices.containsKey(m.group(1).toLowerCase())) {
                        knownBondedDevices.put(m.group(1).toLowerCase(), m.group(2));
                    }
                } else if (readerState == ReaderState.CONNECTION_EVENTS) {
                    Matcher mDisconnect = connectionEventsDisconnectedPattern.matcher(line);
                    if (mDisconnect.matches()) {
                        knownDisconnectionTimes.put(knownBondedDevices.getOrDefault(mDisconnect.group(2).toLowerCase(), "non-bonded-device"), new Pair<>(connectionEventTimeFormater.parse(mDisconnect.group(1)), mDisconnect.group(3)));
                    } else {
                        Matcher mConnect = connectionEventsConnectedPattern.matcher(line);
                        if (mConnect.matches()) {
                            knownConnectionTimes2.put(knownBondedDevices.getOrDefault(mConnect.group(2).toLowerCase(), "non-bonded-device"), connectionEventTimeFormater.parse(mConnect.group(1)));
                        }
                    }
                }
            }
            UnifiedTelemetryLogger mTelemetryLogger = UnifiedTelemetryLogger.getInstance(this.mContext);
            for (String deviceKey : knownBondedDevices.keySet()) {
                String deviceName = knownBondedDevices.get(deviceKey);
                Date connected = knownConnectionTimes2.get(deviceName);
                Pair<Date, String> disconnectedPair = knownDisconnectionTimes.get(deviceName);
                Date disconnected = disconnectedPair != null ? (Date) disconnectedPair.first : null;
                if (disconnectedPair != null) {
                    knownConnectionTimes = knownConnectionTimes2;
                    disconnectedReason = (String) disconnectedPair.second;
                } else {
                    knownConnectionTimes = knownConnectionTimes2;
                    disconnectedReason = null;
                }
                AnalyticsEvent event = new AnalyticsEvent("oculus_mobile_ble_telemetry_events");
                event.setExtra("ble_device_name", deviceName).setExtra("has_connected", Boolean.valueOf(connected != null ? true : DEBUG)).setExtra("has_disconnected", Boolean.valueOf(disconnected != null ? true : DEBUG));
                if (!(connected == null || disconnected == null || !disconnected.after(connected))) {
                    event.setExtra("connection_duration_ms", Long.valueOf(disconnected.getTime() - connected.getTime()));
                }
                if (disconnectedReason != null) {
                    event.setExtra("disconnect_reason", disconnectedReason);
                }
                mTelemetryLogger.reportEvent(event, (boolean) DEBUG);
                knownBondedDevices = knownBondedDevices;
                knownConnectionTimes2 = knownConnectionTimes;
                knownDisconnectionTimes = knownDisconnectionTimes;
                proc = proc;
                br = br;
                readerState = readerState;
            }
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "FetchBLEStatsTask did not succeed because of exception: " + e);
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
}
