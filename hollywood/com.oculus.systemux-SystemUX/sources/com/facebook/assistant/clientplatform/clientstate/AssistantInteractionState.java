package com.facebook.assistant.clientplatform.clientstate;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum AssistantInteractionState {
    INACTIVE,
    LISTENING,
    PROCESSING,
    FINISHED_PROCESSING,
    UNKNOWN
}
