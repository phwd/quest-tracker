package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Post;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$Fu_MKj9oVznszO7yN2MX4iaA0XE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$Fu_MKj9oVznszO7yN2MX4iaA0XE implements Post {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$Fu_MKj9oVznszO7yN2MX4iaA0XE INSTANCE = new $$Lambda$AssistantServiceConnection$Fu_MKj9oVznszO7yN2MX4iaA0XE();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$Fu_MKj9oVznszO7yN2MX4iaA0XE() {
    }

    @Override // com.facebook.assistant.listeners.Post
    public final void run(Object obj) {
        ((AssistantServiceLifecycleListener) obj).onServiceReady();
    }
}
