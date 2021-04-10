package X;

import com.oculus.assistant.config.AssistantDeviceSettings;

public final class WB {
    public final HH A00 = new HH(BX.A00().getSharedPreferences("papaya", 0));

    public static boolean A00() {
        if (!W0.A00().A00.getBoolean("enable_federated_learning", false) || !AssistantDeviceSettings.INSTANCE.isKeyboardFederatedLearningEnabled()) {
            return false;
        }
        return true;
    }
}
