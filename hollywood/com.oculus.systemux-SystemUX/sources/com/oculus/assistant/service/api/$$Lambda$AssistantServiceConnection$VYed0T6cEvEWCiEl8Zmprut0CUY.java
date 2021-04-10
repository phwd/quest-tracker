package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Callback;
import com.oculus.assistant.service.api.attention.AssistantErrorType;
import com.oculus.assistant.service.api.attention.OculusAssistantAttentionListener;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$VYed0T6cEvEWCiEl8Zmprut0CUY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$VYed0T6cEvEWCiEl8Zmprut0CUY implements Callback {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$VYed0T6cEvEWCiEl8Zmprut0CUY INSTANCE = new $$Lambda$AssistantServiceConnection$VYed0T6cEvEWCiEl8Zmprut0CUY();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$VYed0T6cEvEWCiEl8Zmprut0CUY() {
    }

    @Override // com.facebook.assistant.listeners.Callback
    public final boolean run(Object obj) {
        return ((OculusAssistantAttentionListener) obj).onError(AssistantErrorType.INCOMPATIBLE_DEVICE);
    }
}
