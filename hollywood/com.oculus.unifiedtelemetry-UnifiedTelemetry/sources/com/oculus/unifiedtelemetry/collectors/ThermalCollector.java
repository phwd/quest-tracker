package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0385gk;
import X.AbstractC0386gl;
import X.C0484rn;
import X.Fa;
import X.Mu;
import X.QC;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ThermalCollector implements ICollector {
    public static final int DEFAULT_TEMPERATURE = -1;
    public static final String SAVED_STATE_FILENAME = "thermalcollector.save";
    public static final String TAG = "ThermalCollector";
    public static volatile ThermalCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_ThermalCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final HashMap<String, SampledMetric> mAllSensors = new HashMap<>();
    public final IntentFilter mBatteryIntentFilter;
    public final SampledMetric mBatteryTemperature = new SampledMetric();
    public final BroadcastReceiver mBroadcastReceiver = new Fa("android.intent.action.ACTION_SHUTDOWN", new AbstractC0385gk() {
        /* class com.oculus.unifiedtelemetry.collectors.ThermalCollector.AnonymousClass1 */

        @Override // X.AbstractC0385gk
        public final void A3q(Context context, Intent intent, AbstractC0386gl glVar) {
            if ("android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
                ThermalCollector thermalCollector = ThermalCollector.this;
                if (thermalCollector.mSampleCounter > 0) {
                    Event A00 = ThermalCollector.A00(thermalCollector);
                    String A01 = A00.A01();
                    if (A01 != null) {
                        File file = new File(((Context) AbstractC0096Hu.A03(1, 3, ThermalCollector.this._UL_mInjectionContext)).getFilesDir(), ThermalCollector.SAVED_STATE_FILENAME);
                        try {
                            Mu.A05("ThermalCollector", "Writing thermal event on shutdown %s", A01);
                            FileOps.A02(file, A01);
                        } catch (IOException e) {
                            Mu.A05("ThermalCollector", "could not persist events: %s", A00.mName, e);
                        }
                    } else {
                        Mu.A00("ThermalCollector", "could not persist events: %s, failed to serialize");
                    }
                }
            }
        }
    });
    public final SampledMetric mCpuTemperature = new SampledMetric();
    public final SampledMetric mFanSpeed = new SampledMetric();
    public final SampledMetric mFrontPlateTemperature = new SampledMetric();
    public final SampledMetric mGpuTemperature = new SampledMetric();
    public int mSampleCounter = 0;

    private native int nativeGetCpuTemperature();

    private native int nativeGetFanSpeed();

    private native int nativeGetFrontPlateTemperature();

    private native int nativeGetGpuTemperature();

    private native HashMap nativeRefreshTemperatures();

    static {
        C0484rn.A02("utlthermalcollectorjni.oculus", 0);
    }

    public static final Event A00(ThermalCollector thermalCollector) {
        Event event = new Event("oculus_os_thermal");
        event.A04("fan_speed", thermalCollector.mFanSpeed);
        event.A04("front_plate_temperature", thermalCollector.mFrontPlateTemperature);
        event.A04("cpu_temperature", thermalCollector.mCpuTemperature);
        event.A04("gpu_temperature", thermalCollector.mGpuTemperature);
        event.A04("battery_temperature", thermalCollector.mBatteryTemperature);
        event.A02("nb_samples", thermalCollector.mSampleCounter);
        event.A06(PowerCollector.CHARGE_ENDED_REASON_SHUTDOWN, "false");
        HashMap<String, SampledMetric> hashMap = thermalCollector.mAllSensors;
        int size = hashMap.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        String[] strArr3 = new String[size];
        String[] strArr4 = new String[size];
        String[] strArr5 = new String[size];
        int i = 0;
        for (Map.Entry<String, SampledMetric> entry : hashMap.entrySet()) {
            SampledMetric value = entry.getValue();
            strArr[i] = entry.getKey();
            strArr2[i] = Integer.toString(value.mMax);
            strArr3[i] = Integer.toString(value.mMin);
            strArr4[i] = Integer.toString(value.mTotal);
            strArr5[i] = Integer.toString(value.mTotalSquare);
            i++;
        }
        event.A06("sensors_name", TextUtils.join(",", strArr));
        event.A06("sensors_max", TextUtils.join(",", strArr2));
        event.A06("sensors_min", TextUtils.join(",", strArr3));
        event.A06("sensors_total", TextUtils.join(",", strArr4));
        event.A06("sensors_totalsq", TextUtils.join(",", strArr5));
        return event;
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
        if (this.mSampleCounter > 0) {
            Event A00 = A00(this);
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(A00.mName, A00.mContent);
            this.mFanSpeed.A00();
            this.mFrontPlateTemperature.A00();
            this.mCpuTemperature.A00();
            this.mGpuTemperature.A00();
            this.mBatteryTemperature.A00();
            for (Map.Entry<String, SampledMetric> entry : this.mAllSensors.entrySet()) {
                entry.getValue().A00();
            }
            this.mSampleCounter = 0;
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        ((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext)).registerReceiver(this.mBroadcastReceiver, intentFilter);
        File file = new File(((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext)).getFilesDir(), SAVED_STATE_FILENAME);
        try {
            Event A00 = Event.A00(FileOps.A01(file));
            if (A00 != null) {
                ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(A00.mName, A00.mContent);
            }
        } catch (FileNotFoundException unused) {
            if (!file.exists()) {
                return;
            }
        } catch (IOException e) {
            Mu.A02("ThermalCollector", "could not read saved state", e);
        } catch (Throwable th) {
            if (file.exists()) {
                file.delete();
            }
            throw th;
        }
        if (!file.exists()) {
            return;
        }
        file.delete();
    }

    @Inject
    public ThermalCollector(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(2, xu);
        this.mBatteryIntentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
        HashMap nativeRefreshTemperatures = nativeRefreshTemperatures();
        Intent registerReceiver = ((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext)).registerReceiver(null, this.mBatteryIntentFilter);
        if (registerReceiver != null) {
            this.mBatteryTemperature.A01(registerReceiver.getIntExtra("temperature", -1));
        } else {
            this.mBatteryTemperature.A01(-1);
        }
        this.mFanSpeed.A01(nativeGetFanSpeed());
        this.mFrontPlateTemperature.A01(nativeGetFrontPlateTemperature());
        this.mCpuTemperature.A01(nativeGetCpuTemperature());
        this.mGpuTemperature.A01(nativeGetGpuTemperature());
        if (nativeRefreshTemperatures != null) {
            for (Map.Entry entry : nativeRefreshTemperatures.entrySet()) {
                Object key = entry.getKey();
                if (!this.mAllSensors.containsKey(key)) {
                    this.mAllSensors.put(key, new SampledMetric());
                }
                this.mAllSensors.get(key).A01(((Number) entry.getValue()).intValue());
            }
        }
        this.mSampleCounter++;
    }
}
