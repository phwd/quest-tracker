package com.android.okhttp;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Dispatcher {
    private final Deque executedCalls = new ArrayDeque();
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque readyCalls = new ArrayDeque();
    private final Deque runningCalls = new ArrayDeque();

    /* access modifiers changed from: package-private */
    public synchronized void executed(Call call) {
        this.executedCalls.add(call);
    }

    /* access modifiers changed from: package-private */
    public synchronized void finished(Call call) {
        if (!this.executedCalls.remove(call)) {
            throw new AssertionError((Object) "Call wasn't in-flight!");
        }
    }
}
