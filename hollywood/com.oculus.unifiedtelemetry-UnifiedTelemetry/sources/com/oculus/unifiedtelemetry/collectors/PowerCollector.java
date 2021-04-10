package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0385gk;
import X.AbstractC0386gl;
import X.Mu;
import X.QC;
import X.Rc;
import X.Rd;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.unifiedtelemetry.collectors.MC;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import com.oculus.unifiedtelemetry.unifiedtelemetryservice.UnifiedTelemetryService;
import com.oculus.util.constants.JobSchedulerIds;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import org.json.JSONArray;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_collectors_LowBatteryCheckScheduler_ULSEP_BINDING_ID"})
@ApplicationScoped
public class PowerCollector implements ICollectorWithScreenEvents, AbstractC0385gk {
    public static final boolean ALWAYS_REPORT_DISCHARGE_AS_BAD = false;
    public static final int BATTERY_LEVEL_CHARGED_THRESHOLD = 90;
    public static final String CHARGE_ENDED_REASON_DISCONNECTED = "disconnected";
    public static final String CHARGE_ENDED_REASON_SHUTDOWN = "shutdown";
    public static final String COUNT_SYMBOL = "count:";
    public static final String EVENT_NAME_ACTIVE_DISCHARGE = "oculus_mobile_power_active_discharge";
    public static final String EVENT_NAME_CHARGE = "oculus_mobile_power_charge";
    public static final String EVENT_NAME_CHARGE_PERIOD = "oculus_mobile_power_charge_period";
    public static final String EVENT_NAME_INACTIVE_DISCHARGE = "oculus_mobile_power_inactive_discharge";
    public static final int MAX_NBYTES_BATTERY_STATS = 512000;
    public static final long NB_MSEC_CHECK_BAD_DISCHARGE = TimeUnit.MINUTES.toMillis(10);
    public static final File RPM_STATS_FILE = new File("/sys/kernel/debug/rpm_stats");
    public static final String SAVED_STATE_FILENAME = "powercollector.save";
    public static final String SESSION_NAME_CHARGING = "charging";
    public static final String SESSION_NAME_DISCHARGING = "discharging";
    public static final String TAG = "PowerCollector";
    public static final String VLOW_SYMBOL = "RPM Mode:vlow";
    public static final String VMIN_SYMBOL = "RPM Mode:vmin";
    public static final File WAKEUP_SOURCES_FILE = new File("/sys/kernel/debug/wakeup_sources");
    public static volatile PowerCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_PowerCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public boolean mBatteryChargeThresholdReached;
    public IBinder mBatteryStatsIBinder;
    public BroadcastReceiver mBroadcastReceiver;
    public String mKernelwakeupSourcesBeforeSleep;
    @Nullable
    public BatterySnapshot mLastPluggedSnapshot;
    @Nullable
    public BatterySnapshot mLastRelevantSnapshot;
    public final AtomicReference<LowPowerModeStats> mLowPowerStats = new AtomicReference<>();
    public final PowerManager mPowerManager;
    public UnifiedTelemetryLogger mUnifiedTelemetryLogger;
    public int mVlowCountBeforeSleep;
    public int mVminCountBeforeSleep;

    public static int A00(String str) {
        try {
            String A01 = FileOps.A01(RPM_STATS_FILE);
            int indexOf = A01.indexOf(COUNT_SYMBOL, A01.indexOf(str) + str.length());
            int i = indexOf + 6;
            int indexOf2 = A01.indexOf("\n", i);
            if (indexOf >= 0) {
                return Integer.parseInt(A01.substring(i, indexOf2));
            }
            Mu.A01(TAG, "unexpected contents in rpm_stats: count not found");
            return -1;
        } catch (NumberFormatException e) {
            Mu.A03(TAG, "could not parse vmin_count as Int", e);
            return -1;
        } catch (IOException e2) {
            Mu.A03(TAG, "could not read RPM stats", e2);
            return -1;
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0096  */
    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A3u() {
        /*
        // Method dump skipped, instructions count: 325
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.PowerCollector.A3u():void");
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3v() {
        PowerManager powerManager = this.mPowerManager;
        boolean z = this.mLastRelevantSnapshot.isCharging;
        BatterySnapshot batterySnapshot = new BatterySnapshot((Context) AbstractC0096Hu.A03(2, 3, this._UL_mInjectionContext), powerManager);
        batterySnapshot.isSleeping = false;
        batterySnapshot.isCharging = z;
        BatterySnapshot batterySnapshot2 = this.mLastRelevantSnapshot;
        if (!batterySnapshot2.isCharging) {
            Event A01 = A01(batterySnapshot2, batterySnapshot);
            ((LoggingHandler) AbstractC0096Hu.A03(1, 114, this._UL_mInjectionContext)).A07(A01.mName, A01.mContent);
        }
        this.mLastRelevantSnapshot = batterySnapshot;
        if (A04()) {
            LowBatteryCheckScheduler lowBatteryCheckScheduler = (LowBatteryCheckScheduler) AbstractC0096Hu.A03(3, 128, this._UL_mInjectionContext);
            JobScheduler A00 = LowBatteryCheckScheduler.A00(lowBatteryCheckScheduler);
            if (A00 == null) {
                Mu.A00(LowBatteryCheckScheduler.TAG, "Can't get job scheduler when trying to cancel jobs");
                return;
            }
            ((Context) AbstractC0096Hu.A03(0, 3, lowBatteryCheckScheduler._UL_mInjectionContext)).getPackageName();
            A00.cancel(JobSchedulerIds.LOW_BATTERY_CHECKER);
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:29|30|31|32|(1:34)|35|36|(1:38)|(2:43|44)) */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010e, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0111, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x011e, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00de */
    /* JADX WARNING: Missing exception handler attribute for start block: B:58:0x010a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:68:0x011a */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e2 A[Catch:{ IOException -> 0x00e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010e A[Catch:{ IOException -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x011e A[Catch:{ IOException -> 0x0121 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.oculus.unifiedtelemetry.collectors.Event A01(com.oculus.unifiedtelemetry.collectors.BatterySnapshot r14, com.oculus.unifiedtelemetry.collectors.BatterySnapshot r15) {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.PowerCollector.A01(com.oculus.unifiedtelemetry.collectors.BatterySnapshot, com.oculus.unifiedtelemetry.collectors.BatterySnapshot):com.oculus.unifiedtelemetry.collectors.Event");
    }

    public static Event A02(String str, BatterySnapshot batterySnapshot, BatterySnapshot batterySnapshot2) {
        Event event = new Event(str);
        if (batterySnapshot != null) {
            event.A02("last_battery_health", batterySnapshot.health);
            event.A02("last_battery_level", batterySnapshot.level);
            event.A02("last_battery_voltage", batterySnapshot.voltage);
            event.A02("last_battery_temperature", batterySnapshot.temperature);
            event.A03("last_battery_realtime_ms", batterySnapshot.realtimeMs);
            event.A03("last_battery_uptime_ms", batterySnapshot.uptimeMs);
            event.mContent.putBoolean("last_battery_power_save", batterySnapshot.isPowerSave);
        }
        event.A02("battery_health", batterySnapshot2.health);
        event.A02("battery_level", batterySnapshot2.level);
        event.A02("battery_voltage", batterySnapshot2.voltage);
        event.A02("battery_temperature", batterySnapshot2.temperature);
        event.A03("battery_realtime_ms", batterySnapshot2.realtimeMs);
        event.A03("battery_uptime_ms", batterySnapshot2.uptimeMs);
        event.mContent.putBoolean("battery_power_save", batterySnapshot2.isPowerSave);
        return event;
    }

    public static String A03() {
        StringBuilder sb = null;
        try {
            String[] split = FileOps.A01(WAKEUP_SOURCES_FILE).split("\n");
            for (String str : split) {
                if (sb == null) {
                    sb = new StringBuilder();
                } else {
                    String[] split2 = str.split("\\s+");
                    try {
                        int parseInt = Integer.parseInt(split2[1]);
                        int parseInt2 = Integer.parseInt(split2[2]);
                        int parseInt3 = Integer.parseInt(split2[6]);
                        if (parseInt > 0 && parseInt2 > 0 && parseInt3 > 0) {
                            sb.append(Arrays.toString(split2));
                            sb.append("\n");
                        }
                    } catch (NumberFormatException unused) {
                        Mu.A01(TAG, "cannot parse wakeup entry");
                    }
                }
            }
        } catch (IOException e) {
            Mu.A03(TAG, "could not read kernel wakeup sources", e);
        }
        if (sb == null) {
            return "";
        }
        return sb.toString();
    }

    private boolean A04() {
        Rd A00 = Rd.A00(new Rd());
        A00.A00 = true;
        Rd A002 = Rd.A00(A00);
        A002.A02 = true;
        return ((Rc) AbstractC0096Hu.A03(0, 115, this._UL_mInjectionContext)).A2M(MC.oculus_notifications.low_battery_event_v2_enabled, A002);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c2, code lost:
        if (r3.exists() == false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c9, code lost:
        if (r3.exists() != false) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00cb, code lost:
        r3.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d4, code lost:
        if (r9.mPowerManager.isPowerSaveMode() == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d6, code lost:
        r0 = r9.mLowPowerStats.getAndSet(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00df, code lost:
        if (r0 == null) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e3, code lost:
        if (r0.mStartSnapshot == null) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e6, code lost:
        r3 = new com.oculus.unifiedtelemetry.collectors.LowPowerModeStats();
        r3.mStartSnapshot = new com.oculus.unifiedtelemetry.collectors.BatterySnapshot((android.content.Context) X.AbstractC0096Hu.A03(2, 3, r9._UL_mInjectionContext), r9.mPowerManager);
        r9.mLowPowerStats.compareAndSet(null, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0102, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onStart() {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.PowerCollector.onStart():void");
    }

    @Inject
    public PowerCollector(AbstractC0247Xu xu) {
        String str;
        QC qc = new QC(4, xu);
        this._UL_mInjectionContext = qc;
        this.mPowerManager = (PowerManager) ((Context) AbstractC0096Hu.A03(2, 3, qc)).getSystemService("power");
        try {
            this.mBatteryStatsIBinder = (IBinder) Class.forName(UnifiedTelemetryService.SERVICE_MANAGER_CLASS).getMethod("getService", String.class).invoke(null, "batterystats");
        } catch (ReflectiveOperationException e) {
            Mu.A02(TAG, "Service manager call to get service failed", e);
        }
        this.mLastRelevantSnapshot = new BatterySnapshot((Context) AbstractC0096Hu.A03(2, 3, this._UL_mInjectionContext), this.mPowerManager);
        UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance((Context) AbstractC0096Hu.A03(2, 3, this._UL_mInjectionContext));
        this.mUnifiedTelemetryLogger = instance;
        this.mBatteryChargeThresholdReached = false;
        BatterySnapshot batterySnapshot = this.mLastRelevantSnapshot;
        if (batterySnapshot.isCharging) {
            this.mLastPluggedSnapshot = batterySnapshot;
            str = SESSION_NAME_CHARGING;
        } else {
            str = SESSION_NAME_DISCHARGING;
        }
        instance.startSession(str);
    }

    @Override // X.AbstractC0385gk
    public final void A3q(Context context, Intent intent, AbstractC0386gl glVar) {
        BatterySnapshot batterySnapshot;
        Event A02;
        Event A022;
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -1886648615:
                    if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        PowerManager powerManager = this.mPowerManager;
                        boolean z = this.mLastRelevantSnapshot.isSleeping;
                        batterySnapshot = new BatterySnapshot(context, powerManager);
                        batterySnapshot.isSleeping = z;
                        batterySnapshot.isCharging = false;
                        this.mKernelwakeupSourcesBeforeSleep = A03();
                        this.mVlowCountBeforeSleep = A00(VLOW_SYMBOL);
                        this.mVminCountBeforeSleep = A00(VMIN_SYMBOL);
                        this.mUnifiedTelemetryLogger.stopSession(SESSION_NAME_CHARGING);
                        this.mUnifiedTelemetryLogger.startSession(SESSION_NAME_DISCHARGING);
                        BatterySnapshot batterySnapshot2 = this.mLastPluggedSnapshot;
                        if (batterySnapshot2 != null) {
                            Event A023 = A02(EVENT_NAME_CHARGE_PERIOD, batterySnapshot2, batterySnapshot);
                            A023.A03("charge_time_ms", batterySnapshot.realtimeMs - batterySnapshot2.realtimeMs);
                            A023.A06("charge_ended_reason", CHARGE_ENDED_REASON_DISCONNECTED);
                            ((LoggingHandler) AbstractC0096Hu.A03(1, 114, this._UL_mInjectionContext)).A07(A023.mName, A023.mContent);
                        }
                        this.mLastPluggedSnapshot = null;
                        this.mBatteryChargeThresholdReached = false;
                        break;
                    } else {
                        return;
                    }
                case -1538406691:
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        PowerManager powerManager2 = this.mPowerManager;
                        BatterySnapshot batterySnapshot3 = this.mLastRelevantSnapshot;
                        boolean z2 = batterySnapshot3.isSleeping;
                        boolean z3 = batterySnapshot3.isCharging;
                        batterySnapshot = new BatterySnapshot(context, powerManager2);
                        batterySnapshot.isSleeping = z2;
                        batterySnapshot.isCharging = z3;
                        BatterySnapshot batterySnapshot4 = this.mLastPluggedSnapshot;
                        if (batterySnapshot4 != null) {
                            boolean z4 = this.mBatteryChargeThresholdReached;
                            if (z3) {
                                if (!z4 && batterySnapshot.level > 90 && batterySnapshot4.level < 90) {
                                    Event A024 = A02(EVENT_NAME_CHARGE, batterySnapshot4, batterySnapshot);
                                    ((LoggingHandler) AbstractC0096Hu.A03(1, 114, this._UL_mInjectionContext)).A07(A024.mName, A024.mContent);
                                    this.mBatteryChargeThresholdReached = true;
                                    break;
                                } else {
                                    return;
                                }
                            } else if (z4 && batterySnapshot.level < 90) {
                                this.mBatteryChargeThresholdReached = false;
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                    break;
                case 1019184907:
                    if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                        PowerManager powerManager3 = this.mPowerManager;
                        boolean z5 = this.mLastRelevantSnapshot.isSleeping;
                        batterySnapshot = new BatterySnapshot(context, powerManager3);
                        batterySnapshot.isSleeping = z5;
                        batterySnapshot.isCharging = true;
                        BatterySnapshot batterySnapshot5 = this.mLastRelevantSnapshot;
                        if (batterySnapshot5.isSleeping) {
                            A022 = A01(batterySnapshot5, batterySnapshot);
                        } else {
                            A022 = A02(EVENT_NAME_ACTIVE_DISCHARGE, batterySnapshot5, batterySnapshot);
                        }
                        ((LoggingHandler) AbstractC0096Hu.A03(1, 114, this._UL_mInjectionContext)).A07(A022.mName, A022.mContent);
                        if (batterySnapshot.isCharging) {
                            this.mUnifiedTelemetryLogger.stopSession(SESSION_NAME_DISCHARGING);
                            this.mUnifiedTelemetryLogger.startSession(SESSION_NAME_CHARGING);
                            this.mLastPluggedSnapshot = batterySnapshot;
                            this.mBatteryChargeThresholdReached = false;
                            break;
                        }
                    } else {
                        return;
                    }
                    break;
                case 1779291251:
                    if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                        PowerManager powerManager4 = this.mPowerManager;
                        BatterySnapshot batterySnapshot6 = this.mLastRelevantSnapshot;
                        boolean z6 = batterySnapshot6.isSleeping;
                        boolean z7 = batterySnapshot6.isCharging;
                        BatterySnapshot batterySnapshot7 = new BatterySnapshot(context, powerManager4);
                        batterySnapshot7.isSleeping = z6;
                        batterySnapshot7.isCharging = z7;
                        if (this.mPowerManager.isPowerSaveMode()) {
                            LowPowerModeStats lowPowerModeStats = new LowPowerModeStats();
                            lowPowerModeStats.mStartSnapshot = batterySnapshot7;
                            this.mLowPowerStats.set(lowPowerModeStats);
                            return;
                        }
                        LowPowerModeStats andSet = this.mLowPowerStats.getAndSet(null);
                        if (!(andSet == null || andSet.mStartSnapshot == null)) {
                            andSet.mEndSnapshot = batterySnapshot7;
                            Event A01 = andSet.A01();
                            if (A01 != null) {
                                ((LoggingHandler) AbstractC0096Hu.A03(1, 114, this._UL_mInjectionContext)).A07(A01.mName, A01.mContent);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 1947666138:
                    if (action.equals("android.intent.action.ACTION_SHUTDOWN")) {
                        PowerManager powerManager5 = this.mPowerManager;
                        BatterySnapshot batterySnapshot8 = this.mLastRelevantSnapshot;
                        boolean z8 = batterySnapshot8.isSleeping;
                        boolean z9 = batterySnapshot8.isCharging;
                        batterySnapshot = new BatterySnapshot(context, powerManager5);
                        batterySnapshot.isSleeping = z8;
                        batterySnapshot.isCharging = z9;
                        ArrayList<Event> arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        BatterySnapshot batterySnapshot9 = this.mLastRelevantSnapshot;
                        if (!batterySnapshot9.isCharging) {
                            if (batterySnapshot9.isSleeping) {
                                A02 = A01(batterySnapshot9, batterySnapshot);
                            } else {
                                A02 = A02(EVENT_NAME_ACTIVE_DISCHARGE, batterySnapshot9, batterySnapshot);
                            }
                            arrayList.add(A02);
                            arrayList2.add(A02.mName);
                        }
                        BatterySnapshot batterySnapshot10 = this.mLastPluggedSnapshot;
                        if (batterySnapshot10 != null) {
                            Event A025 = A02(EVENT_NAME_CHARGE_PERIOD, batterySnapshot10, batterySnapshot);
                            A025.A03("charge_time_ms", batterySnapshot.realtimeMs - batterySnapshot10.realtimeMs);
                            A025.A06("charge_ended_reason", CHARGE_ENDED_REASON_SHUTDOWN);
                            arrayList.add(A025);
                            arrayList2.add(A025.mName);
                        }
                        LowPowerModeStats andSet2 = this.mLowPowerStats.getAndSet(null);
                        if (!(andSet2 == null || andSet2.mStartSnapshot == null)) {
                            andSet2.mEndSnapshot = batterySnapshot;
                            Event A012 = andSet2.A01();
                            if (A012 != null) {
                                arrayList.add(A012);
                                arrayList2.add(A012.mName);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            ArrayList arrayList3 = new ArrayList();
                            for (Event event : arrayList) {
                                arrayList3.add(event.A01());
                            }
                            try {
                                FileOps.A02(new File(((Context) AbstractC0096Hu.A03(2, 3, this._UL_mInjectionContext)).getFilesDir(), SAVED_STATE_FILENAME), new JSONArray((Collection) arrayList3).toString());
                                break;
                            } catch (IOException e) {
                                Mu.A05(TAG, "could not persist events: %s", arrayList2.toString(), e);
                                break;
                            }
                        }
                    } else {
                        return;
                    }
                    break;
                default:
                    return;
            }
            this.mLastRelevantSnapshot = batterySnapshot;
        }
    }
}
