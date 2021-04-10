package com.oculus.assistant.service.api;

import com.facebook.assistant.listeners.Post;

/* renamed from: com.oculus.assistant.service.api.-$$Lambda$AssistantServiceConnection$66p6DWEmQjVgTWnOXbUCKgt4pdQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AssistantServiceConnection$66p6DWEmQjVgTWnOXbUCKgt4pdQ implements Post {
    public static final /* synthetic */ $$Lambda$AssistantServiceConnection$66p6DWEmQjVgTWnOXbUCKgt4pdQ INSTANCE = new $$Lambda$AssistantServiceConnection$66p6DWEmQjVgTWnOXbUCKgt4pdQ();

    private /* synthetic */ $$Lambda$AssistantServiceConnection$66p6DWEmQjVgTWnOXbUCKgt4pdQ() {
    }

    @Override // com.facebook.assistant.listeners.Post
    public final void run(Object obj) {
        ((AssistantServiceLifecycleListener) obj).onServiceDisconnected();
    }
}
