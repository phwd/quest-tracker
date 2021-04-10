package com.oculus.assistant.service.startup;

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

public class WakeWordHMDMountReciever extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        C0396Vs.A00(context);
        SharedPreferences sharedPreferences = Z4.A00;
        if ((sharedPreferences.getBoolean("interaction_privacy_acknowledge", false) || sharedPreferences.getBoolean("transcription_privacy_acknowledge", false)) && YN.A04()) {
            String action = intent.getAction();
            if (Version.CURRENT_SDK_VERSION >= 59 && "com.oculus.intent.action.MOUNT_STATE_CHANGED".equals(action)) {
                boolean z = intent.getExtras().getBoolean("state");
                VoiceAssistantManager voiceAssistantManager = new VoiceAssistantManager();
                if (z) {
                    C0139Dd.A09("WakeWordHMDMountReciever", "Got HMD Mounted event");
                    voiceAssistantManager.startAudioCapture();
                    return;
                }
                C0139Dd.A09("WakeWordHMDMountReciever", "Got HMD UnMounted event");
                voiceAssistantManager.stopAudioCapture();
            }
        }
    }
}
