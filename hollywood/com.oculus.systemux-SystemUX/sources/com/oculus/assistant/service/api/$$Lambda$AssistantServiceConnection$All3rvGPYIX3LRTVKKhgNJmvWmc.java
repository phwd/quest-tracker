package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Post;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$All3rvGPYIX3LRTVKKhgNJmvWmc  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$All3rvGPYIX3LRTVKKhgNJmvWmc implements Post {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$All3rvGPYIX3LRTVKKhgNJmvWmc INSTANCE = new $$Lambda$AssistantServiceConnection$All3rvGPYIX3LRTVKKhgNJmvWmc();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$All3rvGPYIX3LRTVKKhgNJmvWmc() {
    }

    @Override // com.facebook.assistant.listeners.Post
    public final void run(Object obj) {
        ((AssistantServiceLifecycleListener) obj).onControlServiceDisconnected();
    }
}
