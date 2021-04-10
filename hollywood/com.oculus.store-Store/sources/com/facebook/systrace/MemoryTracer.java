package com.facebook.systrace;

import android.os.Debug;
import android.os.SystemClock;

public final class MemoryTracer implements Runnable {
    private static final int SAMPLE_DELAY_MILLIS = 100;
    private int mGlobalAllocCount;
    private int mGlobalAllocSize;
    private int mGlobalClassInitCount;
    private int mGlobalClassLoadCount;
    private long mLastSampleTime;
    private volatile boolean mStopRequested = false;

    public static void register() {
        TraceConfig.addListener(new TraceListener() {
            /* class com.facebook.systrace.MemoryTracer.AnonymousClass1 */
            MemoryTracer mTracer;

            @Override // com.facebook.systrace.TraceListener
            public void onTraceStarted() {
                if (Systrace.isTracing(1024)) {
                    this.mTracer = new MemoryTracer();
                    new Thread(this.mTracer, "MemoryTracer").start();
                }
            }

            @Override // com.facebook.systrace.TraceListener
            public void onTraceStopped() {
                if (this.mTracer != null) {
                    this.mTracer.requestStop();
                    this.mTracer = null;
                }
            }
        });
    }

    public void requestStop() {
        this.mStopRequested = true;
    }

    /* JADX INFO: finally extract failed */
    public void run() {
        Debug.startAllocCounting();
        try {
            this.mGlobalAllocCount = Debug.getGlobalAllocCount();
            this.mGlobalAllocSize = Debug.getGlobalAllocSize();
            this.mGlobalClassLoadCount = Debug.getLoadedClassCount();
            this.mGlobalClassInitCount = Debug.getGlobalClassInitCount();
            this.mLastSampleTime = SystemClock.uptimeMillis();
            while (!this.mStopRequested) {
                Systrace.beginSection(1024, "MemoryTracer");
                try {
                    takeSample();
                    Systrace.endSection(1024);
                    waitForNextSample();
                } catch (Throwable th) {
                    Systrace.endSection(1024);
                    throw th;
                }
            }
        } finally {
            Debug.stopAllocCounting();
        }
    }

    /* access modifiers changed from: package-private */
    public void takeSample() {
        int globalAllocCount = Debug.getGlobalAllocCount();
        int globalAllocSize = Debug.getGlobalAllocSize();
        int globalClassLoadCount = Debug.getLoadedClassCount();
        int globalClassInitCount = Debug.getGlobalClassInitCount();
        long sampleTime = SystemClock.uptimeMillis();
        if (((int) (sampleTime - this.mLastSampleTime)) != 0) {
            Runtime runtime = Runtime.getRuntime();
            long totalHeapSize = runtime.totalMemory();
            long freeHeapSize = runtime.freeMemory();
            Systrace.traceCounter(1024, "Java bytes allocated", globalAllocSize - this.mGlobalAllocSize);
            Systrace.traceCounter(1024, "# Java allocations", globalAllocCount - this.mGlobalAllocCount);
            Systrace.traceCounter(1024, "classinits", globalClassInitCount - this.mGlobalClassInitCount);
            Systrace.traceCounter(1024, "classloads", globalClassLoadCount - this.mGlobalClassLoadCount);
            Systrace.traceCounter(1024, "free Java heap", (int) freeHeapSize);
            Systrace.traceCounter(1024, "used Java heap", (int) (totalHeapSize - freeHeapSize));
            Systrace.traceCounter(1024, "total Java heap", (int) totalHeapSize);
            Systrace.traceCounter(1024, "initialized classes", globalClassInitCount);
            Systrace.traceCounter(1024, "loaded classes", globalClassLoadCount);
            this.mGlobalAllocCount = globalAllocCount;
            this.mGlobalAllocSize = globalAllocSize;
            this.mGlobalClassLoadCount = globalClassLoadCount;
            this.mGlobalClassInitCount = globalClassInitCount;
            this.mLastSampleTime = sampleTime;
        }
    }

    private static void waitForNextSample() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
