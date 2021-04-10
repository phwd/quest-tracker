package com.oculus.video.audio;

import java.lang.Exception;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class OutputBufferConsumer<E extends Exception> {
    private static final int ASYNC_CONSUMPTION_INTERVAL_MS = 4;
    private final AtomicReference<E> mAsyncConsumerException = new AtomicReference<>();
    private Thread mAsyncConsumerThread;
    private volatile boolean mAsyncConsumptionInProgress;
    private AtomicBoolean mCanConsume = new AtomicBoolean(false);
    private final boolean mConsumeEveryInterval;
    private final boolean mUseAsync;

    /* access modifiers changed from: protected */
    public abstract void consumeSynchronously() throws Exception;

    public OutputBufferConsumer(boolean z, boolean z2) {
        this.mUseAsync = z;
        this.mConsumeEveryInterval = z2;
    }

    public void maybeThrowLastException() throws Exception {
        E e = this.mAsyncConsumerException.get();
        if (e != null) {
            disable();
            throw e;
        }
    }

    public boolean isAsync() {
        return this.mUseAsync;
    }

    public void consume() throws Exception {
        if (!this.mAsyncConsumptionInProgress) {
            consumeSynchronously();
        } else {
            this.mCanConsume.set(true);
        }
    }

    public void enable() {
        disable();
        if (this.mUseAsync) {
            this.mAsyncConsumptionInProgress = true;
            this.mAsyncConsumerThread = new Thread(new Runnable() {
                /* class com.oculus.video.audio.OutputBufferConsumer.AnonymousClass1 */

                public void run() {
                    while (OutputBufferConsumer.this.mAsyncConsumptionInProgress) {
                        try {
                            if (OutputBufferConsumer.this.mCanConsume.get() || OutputBufferConsumer.this.mConsumeEveryInterval) {
                                OutputBufferConsumer.this.consumeSynchronously();
                                OutputBufferConsumer.this.mCanConsume.set(false);
                            }
                            try {
                                Thread.sleep(4);
                            } catch (InterruptedException unused) {
                                return;
                            }
                        } catch (Exception e) {
                            OutputBufferConsumer.this.mAsyncConsumerException.set(e);
                            return;
                        }
                    }
                }
            });
            this.mAsyncConsumerThread.start();
        }
    }

    public void disable() {
        Thread thread = this.mAsyncConsumerThread;
        if (thread != null) {
            try {
                this.mAsyncConsumptionInProgress = false;
                thread.interrupt();
                this.mAsyncConsumerThread.join();
            } catch (InterruptedException unused) {
            }
        }
        this.mAsyncConsumerThread = null;
        this.mAsyncConsumerException.set(null);
    }
}
