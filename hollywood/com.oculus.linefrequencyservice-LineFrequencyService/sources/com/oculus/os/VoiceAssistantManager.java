package com.oculus.os;

import oculus.internal.VoiceAssistantService;

public class VoiceAssistantManager {
    private static final String TAG = VoiceAssistantManager.class.getSimpleName();

    public byte[] getMicData() {
        return VoiceAssistantService.getInstance().getMicData();
    }

    public void initialize(boolean useWakeWord) {
        VoiceAssistantService.getInstance().initialize(useWakeWord);
    }

    public void resetWakeWordRecognition() {
        VoiceAssistantService.getInstance().resetWakeWordRecognition();
    }

    public void stopWakeWordRecognition() {
        VoiceAssistantService.getInstance().stopWakeWordRecognition();
    }

    public void stopAudioCapture() {
        VoiceAssistantService.getInstance().stopAudioCapture();
    }

    public void startAudioCapture() {
        VoiceAssistantService.getInstance().startAudioCapture();
    }
}
