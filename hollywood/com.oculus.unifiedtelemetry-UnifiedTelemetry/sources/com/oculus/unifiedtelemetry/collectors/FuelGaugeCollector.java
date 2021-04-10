package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AnonymousClass8f;
import X.Mu;
import X.QC;
import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.logging.ExtraKeys;
import com.oculus.os.CalibrationInputStream;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class FuelGaugeCollector implements ICollectorWithScreenEvents {
    public static final String DEFAULT_BATTERY_SERIAL_NUMBER = "unknown";
    public static final String EVENT_NAME_FUEL_GAUGE = "oculus_mobile_power_fuel_gauge";
    public static final long NB_MSEC_BETWEEN_FUEL_GAUGE_EVENT = TimeUnit.MINUTES.toMillis(2);
    @Nullable
    public static final File SRAM_DUMP_FILE;
    public static final String TAG = "FuelGaugeCollector";
    public static volatile FuelGaugeCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_FuelGaugeCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final ImmutableMap<String, File> batteryMetricFiles;
    public final String mBatterySerialNumber;
    public long mLastFuelGaugeRecordRealtime = SystemClock.elapsedRealtime();
    @Nullable
    public final PowerManager mPowerManager;

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3u() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001c, code lost:
        if (r2.equals("pacific") == false) goto L_0x001e;
     */
    static {
        /*
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            r0 = 2
            long r0 = r2.toMillis(r0)
            com.oculus.unifiedtelemetry.collectors.FuelGaugeCollector.NB_MSEC_BETWEEN_FUEL_GAUGE_EVENT = r0
            java.lang.String r2 = android.os.Build.BOARD
            int r1 = r2.hashCode()
            r0 = -807117175(0xffffffffcfe45e89, float:-7.6628014E9)
            if (r1 != r0) goto L_0x001e
            java.lang.String r0 = "pacific"
            boolean r0 = r2.equals(r0)
            r1 = 0
            if (r0 != 0) goto L_0x001f
        L_0x001e:
            r1 = -1
        L_0x001f:
            r2 = 0
            if (r1 != 0) goto L_0x0031
            java.lang.String r0 = "/sys/kernel/debug/fg_memif/sram_dump"
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 != 0) goto L_0x0030
            r1 = r2
        L_0x0030:
            r2 = r1
        L_0x0031:
            com.oculus.unifiedtelemetry.collectors.FuelGaugeCollector.SRAM_DUMP_FILE = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.FuelGaugeCollector.<clinit>():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r0 == false) goto L_0x001a;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File A00(java.lang.String r4) {
        /*
            java.lang.String r2 = android.os.Build.BOARD
            int r1 = r2.hashCode()
            r0 = -807117175(0xffffffffcfe45e89, float:-7.6628014E9)
            r3 = 1
            if (r1 == r0) goto L_0x0021
            r0 = -319084279(0xffffffffecfb2909, float:-2.4290733E27)
            if (r1 != r0) goto L_0x001a
            java.lang.String r0 = "monterey"
            boolean r0 = r2.equals(r0)
            r1 = 1
        L_0x0018:
            if (r0 != 0) goto L_0x001b
        L_0x001a:
            r1 = -1
        L_0x001b:
            r2 = 0
            if (r1 == 0) goto L_0x0029
            if (r1 == r3) goto L_0x0029
            return r2
        L_0x0021:
            java.lang.String r0 = "pacific"
            boolean r0 = r2.equals(r0)
            r1 = 0
            goto L_0x0018
        L_0x0029:
            java.lang.String r0 = "/sys/class/power_supply/bms/"
            java.lang.String r0 = X.AnonymousClass06.A04(r0, r4)
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 != 0) goto L_0x003b
            return r2
        L_0x003b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.FuelGaugeCollector.A00(java.lang.String):java.io.File");
    }

    @Nullable
    public static String A01() {
        try {
            CalibrationInputStream calibrationInputStream = new CalibrationInputStream("BAT_SN");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = calibrationInputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toString();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException | RuntimeException e) {
            Mu.A02(TAG, "could not read battery serial number", e);
            return null;
        }
    }

    @Inject
    public FuelGaugeCollector(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(2, xu);
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        A02(builder, "battery_id", "resistance_id");
        A02(builder, "capacity", "capacity");
        A02(builder, "capacity_raw", "capacity_raw");
        A02(builder, "current_now", "current_now");
        A02(builder, "resistance", "resistance");
        A02(builder, "rslow", "resistance_slow");
        A02(builder, "soc_battery", "charge_battery");
        A02(builder, "soc_cc", "charge_now_raw");
        A02(builder, "soc_cutoff", "charge_cutoff");
        A02(builder, "soc_full", "charge_full");
        A02(builder, "soc_monotonic", "charge_monotonic");
        A02(builder, "soc_system", "charge_system");
        A02(builder, "temperature", "temp");
        A02(builder, "voltage_now", "voltage_now");
        A02(builder, "voltage_ocv", "voltage_ocv");
        A02(builder, "voltage_predicted", "voltage_predicted");
        this.batteryMetricFiles = builder.build();
        this.mPowerManager = (PowerManager) ((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext)).getSystemService("power");
        String A01 = A01();
        this.mBatterySerialNumber = A01 == null ? "unknown" : A01;
    }

    public static void A02(ImmutableMap.Builder<String, File> builder, String str, String str2) {
        File A00 = A00(str2);
        if (A00 != null) {
            builder.put(str, A00);
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollectorWithScreenEvents
    public final void A3v() {
        if (SystemClock.elapsedRealtime() - this.mLastFuelGaugeRecordRealtime >= NB_MSEC_BETWEEN_FUEL_GAUGE_EVENT) {
            Event event = new Event(EVENT_NAME_FUEL_GAUGE);
            BatterySnapshot batterySnapshot = new BatterySnapshot((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext), this.mPowerManager);
            event.A02("battery_health", batterySnapshot.health);
            event.A02("battery_level", batterySnapshot.level);
            event.A02("battery_temperature", batterySnapshot.temperature);
            event.A03("battery_realtime_ms", batterySnapshot.realtimeMs);
            event.A03("battery_uptime_ms", batterySnapshot.uptimeMs);
            event.A06("battery_serial_number", this.mBatterySerialNumber);
            try {
                File A00 = A00("fake_aggregate_cycle_count");
                if (A00 != null) {
                    if (FileOps.A00(A00) < 0) {
                        File A002 = A00("aggregate_cycle_count");
                        if (A002 != null) {
                            event.A02("battery_cycle_count", FileOps.A00(A002));
                        } else {
                            throw new IOException("Get battery metric file failed for aggregate_cycle_count");
                        }
                    }
                    AnonymousClass8f<Map.Entry<String, File>> A0D = this.batteryMetricFiles.entrySet().iterator();
                    while (A0D.hasNext()) {
                        Map.Entry<String, File> next = A0D.next();
                        File value = next.getValue();
                        if (value != null) {
                            try {
                                event.A02(next.getKey(), FileOps.A00(value));
                            } catch (IOException e) {
                                Mu.A05(TAG, "could not read battery metric: %d", value.getName(), e);
                            }
                        }
                    }
                    File file = SRAM_DUMP_FILE;
                    if (file != null) {
                        try {
                            event.A06("sram_dump", FileOps.A01(file));
                        } catch (IOException e2) {
                            Mu.A02(TAG, "could not read fuel gauge sram dump", e2);
                        }
                    }
                    event.A06(ExtraKeys.DEVICE_SERIAL, BuildSerialUtil.A00());
                    ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
                    this.mLastFuelGaugeRecordRealtime = SystemClock.elapsedRealtime();
                    return;
                }
                throw new IOException("Get battery metric file failed for fake_aggregate_cycle_count");
            } catch (IOException e3) {
                Mu.A02(TAG, "could not read battery_cycle_count", e3);
            }
        }
    }
}
