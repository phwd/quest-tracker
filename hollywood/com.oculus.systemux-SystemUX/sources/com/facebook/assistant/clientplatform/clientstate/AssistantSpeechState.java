package com.facebook.assistant.clientplatform.clientstate;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum AssistantSpeechState {
    RESPONDING,
    SPEECH_PREPARED,
    SPEECH_STARTING,
    SPEECH_ENDING,
    UNKNOWN
}
