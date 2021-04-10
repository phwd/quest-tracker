package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Post;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$hbNbulxiT3sdvbU7KGwQC2oQETM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$hbNbulxiT3sdvbU7KGwQC2oQETM implements Post {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$hbNbulxiT3sdvbU7KGwQC2oQETM INSTANCE = new $$Lambda$AssistantServiceConnection$hbNbulxiT3sdvbU7KGwQC2oQETM();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$hbNbulxiT3sdvbU7KGwQC2oQETM() {
    }

    @Override // com.facebook.assistant.listeners.Post
    public final void run(Object obj) {
        ((AssistantServiceLifecycleListener) obj).onAttentionServiceDisconnected();
    }
}
