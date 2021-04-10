package com.oculus.companion.server;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.SettingsManager;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.HashMap;

public class Telemetry {
    private static HashMap<Integer, Long> mStartTimes = new HashMap<>();
    private static UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public void destroy() {
    }

    public Telemetry(Context context) {
        mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
    }

    public void recordHidBondEvent(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice != null) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_hid_bond_events");
            analyticsEvent.setExtra("deviceName", bluetoothDevice.getName()).setExtra("bondSuccessful", Boolean.valueOf(z)).setExtra("deviceType", Integer.valueOf(bluetoothDevice.getType()));
            mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
        }
    }

    public void recordBluetoothScanEvent(int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_bluetooth_scan");
        analyticsEvent.setExtra("devicesFound", Integer.valueOf(i));
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public void recordBluetoothPairingRequest(int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_bluetooth_pairing");
        analyticsEvent.setExtra("pairingVariant", Integer.valueOf(i));
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public void registerStartTime(Protocol$Request protocol$Request) {
        mStartTimes.put(Integer.valueOf(protocol$Request.getSeq()), Long.valueOf(System.currentTimeMillis()));
    }

    public void logBLEInvoke(Protocol$Request protocol$Request, Protocol$ResponseCode protocol$ResponseCode, Protocol$ErrorCode protocol$ErrorCode) {
        String str;
        String str2;
        Long remove;
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_companion_server_ble_invoke");
        if (protocol$Request == null) {
            str = "error";
        } else {
            str = protocol$Request.getMethod().toString();
        }
        AnalyticsEvent extra = analyticsEvent.setExtra("invoke_method", str).setExtra("response_code", protocol$ResponseCode.toString());
        if (protocol$ErrorCode == null) {
            str2 = "";
        } else {
            str2 = protocol$ErrorCode.toString();
        }
        extra.setExtra("error_code", str2).setExtra("response_success", Boolean.valueOf(protocol$ResponseCode == Protocol$ResponseCode.SUCCESS));
        if (!(protocol$Request == null || protocol$Request.getSeq() == 0 || (remove = mStartTimes.remove(Integer.valueOf(protocol$Request.getSeq()))) == null)) {
            analyticsEvent.setExtra("response_time_ms", Long.valueOf(System.currentTimeMillis() - remove.longValue()));
        }
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public void logIntentInvoke(String str, long j, String str2, String str3) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_companion_server_intent_invoke");
        analyticsEvent.setExtra("invoke_method", str).setExtra("response_success", Boolean.valueOf(str2 == "")).setExtra("response_time_ms", Long.valueOf(j)).setExtra("exception", str2).setExtra("exception_stack_trace", str3);
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public void recordEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, String str8, String str9, Protocol$ErrorCode protocol$ErrorCode) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_" + str);
        analyticsEvent.setExtra("message", str2).setExtra("secureConnection", str3).setExtra("isAuthenticated", str4).setExtra("isLockedToUser", str5).setExtra("invalidDevice", str6).setExtra("isPinLocked", str7).setExtra("connected", Integer.valueOf(i)).setExtra("state", str8);
        if (str9 != null) {
            analyticsEvent.setExtra("requestName", str9);
        }
        if (protocol$ErrorCode != null) {
            analyticsEvent.setExtra("errorCode", protocol$ErrorCode.toString());
        }
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public void recordCrashReportsEnabledEvent(Context context) {
        boolean z = new SettingsManager().getBoolean("crash_reports_enabled", false);
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_crash_reports_enabled");
        analyticsEvent.setExtra("crash_reports_enabled", Boolean.valueOf(z));
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }
}
