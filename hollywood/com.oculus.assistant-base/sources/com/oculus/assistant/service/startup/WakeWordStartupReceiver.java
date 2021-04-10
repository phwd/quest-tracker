package com.oculus.assistant.service.startup;

import X.C00799i;
import X.C0139Dd;
import X.C0396Vs;
import X.YN;
import X.Z4;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.oculus.os.Version;
import com.oculus.os.VoiceAssistantManager;

public class WakeWordStartupReceiver extends BroadcastReceiver {
    public static void A00() {
        try {
            if (YN.A02()) {
                VoiceAssistantManager voiceAssistantManager = new VoiceAssistantManager();
                voiceAssistantManager.initialize(true);
                SharedPreferences sharedPreferences = Z4.A00;
                if ((sharedPreferences.getBoolean("interaction_privacy_acknowledge", false) || sharedPreferences.getBoolean("transcription_privacy_acknowledge", false)) && YN.A04()) {
                    voiceAssistantManager.startAudioCapture();
                }
                if (YN.A03() && !YN.A02()) {
                    C00799i.A00.logError("WakeWordStartupReceiver - Assistant App GK not in sync with Native GK");
                }
            }
        } catch (Exception e) {
            C0139Dd.A0L("WakeWordStartupReceiver", "Failed initializing VoiceAssistantManager and wake word", e);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        C0396Vs.A00(context);
        String action = intent.getAction();
        if (Version.CURRENT_SDK_VERSION >= 52 && "android.intent.action.BOOT_COMPLETED".equals(action)) {
            C0139Dd.A09("WakeWordStartupReceiver", "Got boot completed action");
            A00();
        }
    }
}
