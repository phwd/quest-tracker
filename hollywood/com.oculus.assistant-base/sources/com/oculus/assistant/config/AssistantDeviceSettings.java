package com.oculus.assistant.config;

import X.AnonymousClass08;
import X.BX;
import X.C0139Dd;
import X.C0398Vv;
import X.C0514bB;
import X.C1028qz;
import X.EnumC0242Mp;
import X.NV;
import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.oculus.os.SettingsManager;

@JsonAutoDetect(fieldVisibility = EnumC0242Mp.NONE, getterVisibility = EnumC0242Mp.ANY, setterVisibility = EnumC0242Mp.NONE)
public final class AssistantDeviceSettings {
    public static final AssistantDeviceSettings INSTANCE = new AssistantDeviceSettings();
    public static final String TAG = "AssistantDeviceSettings";
    public static final Application context = BX.A00();
    public static final SettingsManager settingsManager = new SettingsManager(BX.A00());

    public final String toJson() {
        try {
            String A00 = new C1028qz(null).A00(this);
            C0514bB.A01(A00, "objectMapper.writeValueAsString(this)");
            return A00;
        } catch (NV e) {
            C0139Dd.A0L(TAG, "Error serializing", e);
            return "Error serializing";
        }
    }

    public final boolean isDoubleTapEnabled() {
        if (settingsManager.getInt("oculus_button_doublepress_behavior", 0) == 2) {
            return true;
        }
        return false;
    }

    public final boolean isHandTrackingEnabled() {
        return settingsManager.getBoolean("hand_tracking_enabled", false);
    }

    public final boolean isInAppVoiceCommandsEnabled() {
        return settingsManager.getBoolean("in_app_voice_commands_enabled_v2", false);
    }

    public final boolean isKeyboardFederatedLearningEnabled() {
        return settingsManager.getBoolean("keyboard_federated_learning_enabled", false);
    }

    public final boolean isMicMuted() {
        return settingsManager.getBoolean("mic_muted", false);
    }

    public final boolean isSharedMicAvailable() {
        Application application = context;
        Cursor cursor = null;
        try {
            Cursor query = application.getContentResolver().query(Uri.parse(AnonymousClass08.A05("content://com.oculus.horizon.gatekeeper/fetch", "?name=", "oculus_media_shared_mic")), null, null, null, null);
            boolean z = false;
            if (query != null) {
                if (!query.moveToNext()) {
                    query.close();
                } else {
                    if (query.getInt(query.getColumnIndexOrThrow("gk_enabled")) == 1) {
                        z = true;
                    }
                    query.close();
                    if (!z || !application.getPackageManager().hasSystemFeature("oculus.software.mic_sharing")) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final boolean isVoiceStorageEnabled() {
        return settingsManager.getBoolean("voice_interaction_storage_enabled", false);
    }

    public final boolean isWakeWordEnabled() {
        return settingsManager.getBoolean("assistant_wakeword_enabled", false);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0119 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isMicrophoneExclusive() {
        /*
        // Method dump skipped, instructions count: 1942
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.assistant.config.AssistantDeviceSettings.isMicrophoneExclusive():boolean");
    }

    public final boolean isShellBackgrounded() {
        return C0398Vv.A03();
    }
}
