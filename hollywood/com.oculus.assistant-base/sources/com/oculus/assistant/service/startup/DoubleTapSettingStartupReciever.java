package com.oculus.assistant.service.startup;

import X.BX;
import X.C0139Dd;
import X.C0396Vs;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.oculus.os.SettingsManager;

public class DoubleTapSettingStartupReciever extends BroadcastReceiver {
    public static void A00() {
        SharedPreferences sharedPreferences = BX.A00().getSharedPreferences("AssistantServiceConfig", 0);
        if (!sharedPreferences.getBoolean("OCULUS_DOUBLE_TAP_SETTING_CHANGED", false)) {
            new SettingsManager(BX.A00()).setInt("oculus_button_doublepress_behavior", 2);
            sharedPreferences.edit().putBoolean("OCULUS_DOUBLE_TAP_SETTING_CHANGED", true).apply();
        }
    }

    public final void onReceive(Context context, Intent intent) {
        C0396Vs.A00(context);
        if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(intent.getAction())) {
            C0139Dd.A09("DoubleTapSettingStartupReciever", "Got Power disconnected action");
            A00();
        }
    }
}
