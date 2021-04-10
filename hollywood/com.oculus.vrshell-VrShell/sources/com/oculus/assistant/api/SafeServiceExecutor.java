package com.oculus.assistant.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeServiceExecutor {
    private final Object lock = new Object();
    private final AtomicBoolean mIsReady = new AtomicBoolean(false);
    private final List<Runnable> mPendingTasks = new ArrayList();

    public void setReady(boolean z) {
        ArrayList<Runnable> arrayList;
        this.mIsReady.set(z);
        if (z) {
            synchronized (this.lock) {
                arrayList = new ArrayList(this.mPendingTasks);
                this.mPendingTasks.clear();
            }
            for (Runnable runnable : arrayList) {
                runnable.run();
            }
        }
    }

    public void safeExecute(Runnable runnable) {
        if (this.mIsReady.get()) {
            runnable.run();
            return;
        }
        synchronized (this.lock) {
            this.mPendingTasks.add(runnable);
        }
    }

    public boolean isReady() {
        return this.mIsReady.get();
    }
}
