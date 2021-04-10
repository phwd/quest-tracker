package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Post;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$TudZeXuJPBtuOPNzhxA6IutN6bs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$TudZeXuJPBtuOPNzhxA6IutN6bs implements Post {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$TudZeXuJPBtuOPNzhxA6IutN6bs INSTANCE = new $$Lambda$AssistantServiceConnection$TudZeXuJPBtuOPNzhxA6IutN6bs();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$TudZeXuJPBtuOPNzhxA6IutN6bs() {
    }

    @Override // com.facebook.assistant.listeners.Post
    public final void run(Object obj) {
        ((AssistantServiceLifecycleListener) obj).onTranscriptionServiceDisconnected();
    }
}
