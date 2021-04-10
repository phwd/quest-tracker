package com.oculus.assistant.service.api.attention;

public interface OculusAssistantAttentionListener {
    boolean onAssistantResponse(String str);

    boolean onError(AssistantErrorType assistantErrorType);

    boolean onInteractionCompleted(boolean z);

    boolean onInteractionId(String str);

    boolean onInteractionPropertiesChanged(float f);

    boolean onInteractionStateChanged(AssistantInteractionState assistantInteractionState);

    boolean onTranscriptionChanged(String str, boolean z);
}
