package com.oculus.unifiedtelemetry.unifiedlogging.utils;

import X.Mu;
import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.SettingsObserverCallback;
import javax.annotation.Nullable;

@Dependencies({})
@ApplicationScoped
@Nullsafe(Nullsafe.Mode.LOCAL)
public class SettingsManager {
    public static final String DEFAULT_HW_DEVICE_ID = "0";
    public static final int DEFAULT_MANAGED_DEVICE_MODE = -1;
    public static final String DEFAULT_TELEMETRY_STATE = TelemetryState.ON.getState();
    public static final String TAG = "SettingsManager";
    public static volatile SettingsManager _UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_utils_SettingsManager_ULSEP_INSTANCE;
    public volatile String mHwDeviceId;
    public volatile int mManagedMode;
    @Nullable
    public com.oculus.os.SettingsManager mSettingsManager = new com.oculus.os.SettingsManager();
    public volatile TelemetryState mTelemetryState;

    public class SettingsStateObserver implements SettingsObserverCallback {
        public void onSettingChange(String str) {
            int hashCode = str.hashCode();
            if (hashCode != -1372983560) {
                if (hashCode != -227669258) {
                    if (hashCode == 1566891147 && str.equals("telemetry_state")) {
                        SettingsManager settingsManager = SettingsManager.this;
                        com.oculus.os.SettingsManager settingsManager2 = settingsManager.mSettingsManager;
                        if (settingsManager2 != null) {
                            settingsManager.mTelemetryState = TelemetryState.fromString(settingsManager2.getString("telemetry_state", SettingsManager.DEFAULT_TELEMETRY_STATE), TelemetryState.ON);
                            return;
                        }
                        return;
                    }
                } else if (str.equals("managed_device")) {
                    SettingsManager settingsManager3 = SettingsManager.this;
                    com.oculus.os.SettingsManager settingsManager4 = settingsManager3.mSettingsManager;
                    if (settingsManager4 != null) {
                        settingsManager3.mManagedMode = settingsManager4.getInt("managed_device", -1);
                        return;
                    }
                    return;
                }
            } else if (str.equals("device_ent_id")) {
                SettingsManager settingsManager5 = SettingsManager.this;
                com.oculus.os.SettingsManager settingsManager6 = settingsManager5.mSettingsManager;
                if (settingsManager6 != null) {
                    settingsManager5.mHwDeviceId = settingsManager6.getString("device_ent_id", "0");
                    return;
                }
                return;
            }
            Mu.A05(SettingsManager.TAG, "onSettingChange called with incorrect settingName = %s", str);
        }

        public SettingsStateObserver() {
        }
    }

    public enum TelemetryState {
        OFF("off"),
        ON("on");
        
        public final String mState;

        /* access modifiers changed from: public */
        TelemetryState(String str) {
            this.mState = str;
        }

        public static TelemetryState fromString(String str, TelemetryState telemetryState) {
            TelemetryState[] values = values();
            for (TelemetryState telemetryState2 : values) {
                if (telemetryState2.mState.equalsIgnoreCase(str)) {
                    return telemetryState2;
                }
            }
            return telemetryState;
        }

        public String getState() {
            return this.mState;
        }
    }

    @Inject
    public SettingsManager() {
        SettingsStateObserver settingsStateObserver = new SettingsStateObserver();
        Handler handler = new Handler(Looper.getMainLooper());
        this.mSettingsManager.registerSettingsObserver("telemetry_state", settingsStateObserver, handler);
        this.mSettingsManager.registerSettingsObserver("managed_device", settingsStateObserver, handler);
        this.mSettingsManager.registerSettingsObserver("device_ent_id", settingsStateObserver, handler);
        this.mTelemetryState = TelemetryState.fromString(this.mSettingsManager.getString("telemetry_state", DEFAULT_TELEMETRY_STATE), TelemetryState.ON);
        this.mManagedMode = this.mSettingsManager.getInt("managed_device", -1);
        this.mHwDeviceId = this.mSettingsManager.getString("device_ent_id", "0");
    }
}
