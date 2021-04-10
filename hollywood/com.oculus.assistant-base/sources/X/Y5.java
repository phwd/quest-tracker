package X;

import com.oculus.os.Version;
import com.oculus.os.VoiceAssistantManager;

public final class Y5 {
    public VoiceAssistantManager A00;

    public final synchronized byte[] A03() {
        byte[] micData;
        int length;
        micData = this.A00.getMicData();
        if (micData == null || (length = micData.length) == 0) {
            micData = null;
        } else {
            for (int i = 0; i < length; i += 2) {
                byte b = micData[i];
                int i2 = i + 1;
                micData[i] = micData[i2];
                micData[i2] = b;
            }
        }
        return micData;
    }

    public final void A00() {
        if (Version.CURRENT_SDK_VERSION >= 52) {
            this.A00.stopAudioCapture();
        }
    }

    public final void A01() {
        if (Version.CURRENT_SDK_VERSION >= 52) {
            this.A00.resetWakeWordRecognition();
            this.A00.stopAudioCapture();
            this.A00.startAudioCapture();
        }
    }

    public final void A02() {
        if (Version.CURRENT_SDK_VERSION >= 52) {
            this.A00.startAudioCapture();
        }
    }

    public Y5() {
        this.A00 = new VoiceAssistantManager();
    }

    public Y5(VoiceAssistantManager voiceAssistantManager) {
        this.A00 = voiceAssistantManager;
    }
}
