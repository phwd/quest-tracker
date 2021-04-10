package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

public class ThreadHandoffProducerQueueImpl implements ThreadHandoffProducerQueue {
    private final Executor mExecutor;
    private boolean mQueueing = false;
    private final Deque<Runnable> mRunnableList;

    public ThreadHandoffProducerQueueImpl(Executor executor) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mRunnableList = new ArrayDeque();
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized void addToQueueOrExecute(Runnable runnable) {
        if (this.mQueueing) {
            this.mRunnableList.add(runnable);
        } else {
            this.mExecutor.execute(runnable);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized void startQueueing() {
        this.mQueueing = true;
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized void stopQueuing() {
        this.mQueueing = false;
        execInQueue();
    }

    private void execInQueue() {
        while (!this.mRunnableList.isEmpty()) {
            this.mExecutor.execute(this.mRunnableList.pop());
        }
        this.mRunnableList.clear();
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized void remove(Runnable runnable) {
        this.mRunnableList.remove(runnable);
    }

    @Override // com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue
    public synchronized boolean isQueueing() {
        return this.mQueueing;
    }
}
