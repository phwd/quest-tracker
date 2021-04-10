package com.oculus.statscollector;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.StatsManager;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.internal.os.StatsdConfigProto;
import com.android.os.AtomsProto;
import com.android.os.StatsLog;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StatsdManager {
    private static final String[] ALLOWED_LOG_SOURCES = {"AID_BLUETOOTH", "AID_GRAPHICS", "AID_INCIDENTD", "AID_LMKD", "AID_MEDIA", "AID_NETWORK_STACK", "AID_RADIO", "AID_ROOT", "AID_STATSD", "AID_SYSTEM", "com.android.systemui"};
    private static final long CONFIG_ID = 57475;
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = StatsdManager.class.getSimpleName();

    public interface IEventHandler {
        void handleEvent(long j, AtomsProto.Atom atom);
    }

    public static class ReceiverIntentService extends IntentService {
        public ReceiverIntentService() {
            super("ReceiverIntentService");
        }

        /* access modifiers changed from: protected */
        public void onHandleIntent(Intent intent) {
            if (StatsdManager.DEBUG) {
                Log.d(StatsdManager.TAG, "In-memory stats approaching limits, fetching reports");
            }
            new FetchStatsTask(this).execute(new JobParameters[0]);
        }
    }

    public static void configureStatsd(Context context) {
        StatsManager statsManager = (StatsManager) context.getSystemService("stats");
        if (statsManager != null) {
            StatsdConfigProto.StatsdConfig config = createConfig();
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "Creating configuration: " + config.toString());
            }
            try {
                statsManager.addConfig((long) CONFIG_ID, config.toByteArray());
                try {
                    statsManager.setFetchReportsOperation(PendingIntent.getService(context, 0, new Intent(context, ReceiverIntentService.class), 134217728), (long) CONFIG_ID);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to setFetchReportsOperation, data loss possible!", e);
                }
            } catch (StatsManager.StatsUnavailableException | IllegalArgumentException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            throw new RuntimeException("Failed to get stats service");
        }
    }

    private static StatsdConfigProto.StatsdConfig createConfig() {
        ArrayList<String> allowedSources = new ArrayList<>();
        Collections.addAll(allowedSources, ALLOWED_LOG_SOURCES);
        StatsdConfigProto.StatsdConfig.Builder builder = StatsdConfigProto.StatsdConfig.newBuilder();
        builder.addAllAllowedLogSource(allowedSources);
        try {
            addLmkKillOccurredAtoms(builder);
            addLowStorageStateChangedAtoms(builder);
            addThermalThrottlingAtoms(builder);
            addWallClockTimeShiftedAtoms(builder);
        } catch (Exception e) {
            Log.e(TAG, "Failed to add stats configuration", e);
        }
        return (StatsdConfigProto.StatsdConfig) builder.build();
    }

    private static void addLmkKillOccurredAtoms(StatsdConfigProto.StatsdConfig.Builder conf) throws Exception {
        addAtomEvent(conf, 51);
    }

    private static void addLowStorageStateChangedAtoms(StatsdConfigProto.StatsdConfig.Builder conf) throws Exception {
        addAtomEvent(conf, 130, createFvm(2).setEqInt(2));
    }

    private static void addThermalThrottlingAtoms(StatsdConfigProto.StatsdConfig.Builder conf) throws Exception {
        addAtomEvent(conf, (int) AtomsProto.Atom.THERMAL_THROTTLING_SEVERITY_STATE_CHANGED_FIELD_NUMBER, createFvm(4).setEqInt(1));
        addAtomEvent(conf, (int) AtomsProto.Atom.THERMAL_THROTTLING_SEVERITY_STATE_CHANGED_FIELD_NUMBER, createFvm(4).setEqInt(2));
        addAtomEvent(conf, (int) AtomsProto.Atom.THERMAL_THROTTLING_SEVERITY_STATE_CHANGED_FIELD_NUMBER, createFvm(4).setEqInt(3));
        addAtomEvent(conf, (int) AtomsProto.Atom.THERMAL_THROTTLING_SEVERITY_STATE_CHANGED_FIELD_NUMBER, createFvm(4).setEqInt(4));
        addAtomEvent(conf, (int) AtomsProto.Atom.THERMAL_THROTTLING_SEVERITY_STATE_CHANGED_FIELD_NUMBER, createFvm(4).setEqInt(5));
        addAtomEvent(conf, (int) AtomsProto.Atom.THERMAL_THROTTLING_SEVERITY_STATE_CHANGED_FIELD_NUMBER, createFvm(4).setEqInt(6));
    }

    private static void addWallClockTimeShiftedAtoms(StatsdConfigProto.StatsdConfig.Builder conf) throws Exception {
        addAtomEvent(conf, 45);
    }

    private static void addAtomEvent(StatsdConfigProto.StatsdConfig.Builder conf, int atomId) throws Exception {
        addAtomEvent(conf, atomId, (List<StatsdConfigProto.FieldValueMatcher.Builder>) null);
    }

    private static void addAtomEvent(StatsdConfigProto.StatsdConfig.Builder conf, int atomId, StatsdConfigProto.FieldValueMatcher.Builder fvm) throws Exception {
        addAtomEvent(conf, atomId, Arrays.asList(fvm));
    }

    private static void addAtomEvent(StatsdConfigProto.StatsdConfig.Builder conf, int atomId, List<StatsdConfigProto.FieldValueMatcher.Builder> fvms) throws Exception {
        String atomName = "Atom" + System.nanoTime();
        String eventName = "Event" + System.nanoTime();
        StatsdConfigProto.SimpleAtomMatcher.Builder sam = StatsdConfigProto.SimpleAtomMatcher.newBuilder().setAtomId(atomId);
        if (fvms != null) {
            for (StatsdConfigProto.FieldValueMatcher.Builder fvm : fvms) {
                sam.addFieldValueMatcher(fvm);
            }
        }
        conf.addAtomMatcher(StatsdConfigProto.AtomMatcher.newBuilder().setId((long) atomName.hashCode()).setSimpleAtomMatcher(sam));
        conf.addEventMetric(StatsdConfigProto.EventMetric.newBuilder().setId((long) eventName.hashCode()).setWhat((long) atomName.hashCode()));
    }

    private static StatsdConfigProto.FieldValueMatcher.Builder createFvm(int field) {
        return StatsdConfigProto.FieldValueMatcher.newBuilder().setField(field);
    }

    public static void getReports(Context context, IEventHandler eventHandler) {
        StatsManager statsManager = (StatsManager) context.getSystemService("stats");
        if (statsManager == null) {
            Log.e(TAG, "Failed to get stats service, unable to get reports!");
            return;
        }
        try {
            if (DEBUG) {
                Log.d(TAG, "Fetching reports from statsd");
            }
            parseReports(statsManager.getReports((long) CONFIG_ID), eventHandler);
        } catch (StatsManager.StatsUnavailableException e) {
            Log.e(TAG, "Failed to get report data", e);
        }
    }

    private static void parseReports(byte[] data, IEventHandler eventHandler) {
        try {
            List<StatsLog.ConfigMetricsReport> configMetrics = StatsLog.ConfigMetricsReportList.parseFrom(data).getReportsList();
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "Found " + configMetrics.size() + " metrics reports");
            }
            for (StatsLog.ConfigMetricsReport configMetric : configMetrics) {
                for (StatsLog.EventMetricData eventMetric : (List) configMetric.getMetricsList().stream().flatMap($$Lambda$StatsdManager$omM_B0D4YUbz9Ce0i6d23q9t_w.INSTANCE).sorted(Comparator.comparing($$Lambda$Qfy95b5MKizLt_vOMGNiB6IQtfg.INSTANCE)).collect(Collectors.toList())) {
                    AtomsProto.Atom atom = eventMetric.getAtom();
                    long timestamp = eventMetric.getElapsedTimestampNanos();
                    if (DEBUG) {
                        String str2 = TAG;
                        Log.d(str2, "Atom at " + timestamp + "ns: " + atom.toString());
                    }
                    eventHandler.handleEvent(timestamp, atom);
                }
            }
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "Invalid protocol buffer data encountered parsing report!", e);
        }
    }
}
