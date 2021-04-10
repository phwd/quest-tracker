package com.oculus.vralertservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.vralertservice.AlertApplication;

public class AlertReceiver extends BroadcastReceiver {
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        if (r2.equals("com.oculus.intent.action.fan_malfunction") != false) goto L_0x004a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r9, android.content.Intent r10) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vralertservice.AlertReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    private void handleFanMalfunction(Context context, Intent intent) {
        if (intent.getBooleanExtra("EXTRA_MALFUNCTIONING", false)) {
            UnifiedTelemetryLogger.getInstance(context.getApplicationContext()).reportEvent(new AnalyticsEvent("oculus_mobile_fan_failure"), false);
            if (AlertApplication.moveTo(AlertApplication.AlertLevel.FAN_MALFUNCTION) && intent.getLongExtra("EXTRA_WARN_COUNT", 1) == 1) {
                context.sendBroadcastAsUser(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"), UserHandle.SYSTEM);
                startActivity(context, FanMalfunctionActivity.class);
            }
        }
    }

    private void handleBatteryOverheat(Context context, Intent intent) {
        if (AlertApplication.moveTo(AlertApplication.AlertLevel.BATTERY_TEMPERATURE)) {
            startActivity(context, BatteryOverheatActivity.class);
        }
    }

    private void displayDialogIfNeeded(Context context, Intent intent) {
        Intent registerReceiver;
        boolean z = context.getResources().getBoolean(R.bool.config_checkFanMalfunction);
        boolean z2 = false;
        if (z && (registerReceiver = context.registerReceiver(null, new IntentFilter("com.oculus.intent.action.fan_malfunction"))) != null && registerReceiver.getBooleanExtra("EXTRA_MALFUNCTIONING", false)) {
            z2 = true;
        }
        if (!z || z2) {
            if (AlertApplication.moveTo(AlertApplication.AlertLevel.SOC_TEMPERATURE)) {
                startActivity(context, ThermalOverheatActivity.class);
            }
        } else if (AlertApplication.moveTo(AlertApplication.AlertLevel.EXTERNAL_TEMPERATURE)) {
            startActivity(context, ExternalTempOverheatActivity.class);
        }
    }

    private void startActivity(Context context, Class cls) {
        context.startActivityAsUser(new Intent(context, cls).addFlags(1073741824), UserHandle.SYSTEM);
    }
}
