package com.oculus.assistant.service.api;

import com.oculus.assistant.service.api.control.OculusAssistantControlService;
import com.oculus.assistant.service.api.transcription.OculusAssistantTranscriptionService;

public interface AssistantServiceLifecycleListener {
    void onAttentionServiceConnected();

    void onAttentionServiceDisconnected();

    void onControlServiceConnected(OculusAssistantControlService oculusAssistantControlService);

    void onControlServiceDisconnected();

    void onServiceDisconnected();

    void onServiceReady();

    void onTranscriptionServiceConnected(OculusAssistantTranscriptionService oculusAssistantTranscriptionService);

    void onTranscriptionServiceDisconnected();
}
