package com.facebook.assistant.clientplatform.clientstate;

public interface AssistantClientStateListener {
    boolean onAssistantResponse(String str);

    boolean onError(AssistantErrorType assistantErrorType, String str);

    boolean onInteractionCompleted(boolean z);

    boolean onInteractionPropertiesChanged(float f);

    boolean onInteractionStateChanged(AssistantInteractionState assistantInteractionState);

    boolean onTTSResponseReady();

    boolean onTranscriptionChanged(String str, boolean z);
}
