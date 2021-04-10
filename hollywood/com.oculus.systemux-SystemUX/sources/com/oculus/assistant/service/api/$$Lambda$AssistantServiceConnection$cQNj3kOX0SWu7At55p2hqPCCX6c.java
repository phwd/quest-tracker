package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Post;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$cQNj3kOX0SWu7At55p2hqPCCX6c  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$cQNj3kOX0SWu7At55p2hqPCCX6c implements Post {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$cQNj3kOX0SWu7At55p2hqPCCX6c INSTANCE = new $$Lambda$AssistantServiceConnection$cQNj3kOX0SWu7At55p2hqPCCX6c();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$cQNj3kOX0SWu7At55p2hqPCCX6c() {
    }

    @Override // com.facebook.assistant.listeners.Post
    public final void run(Object obj) {
        ((AssistantServiceLifecycleListener) obj).onAttentionServiceConnected();
    }
}
