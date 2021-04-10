package com.facebook.proxygen;

import java.util.Random;

public class TraceEventContext {
    public static final Random RAND = new Random();
    public TraceEventObserver[] mObservers;
    public int mParentID;
    public final SamplePolicy mSamplePolicy;

    public void informAllObservers(TraceEvent[] traceEventArr) {
        for (TraceEventObserver traceEventObserver : this.mObservers) {
            traceEventObserver.traceEventAvailable(traceEventArr);
        }
    }

    public boolean needPublishEvent() {
        return this.mSamplePolicy.isSampled();
    }

    public int getParentID() {
        return this.mParentID;
    }

    public TraceEventContext(SamplePolicy samplePolicy) {
        this(new TraceEventObserver[0], samplePolicy);
    }

    public TraceEventContext(TraceEventObserver[] traceEventObserverArr) {
        this(traceEventObserverArr, new SamplePolicy() {
            /* class com.facebook.proxygen.TraceEventContext.AnonymousClass1 */

            @Override // com.facebook.proxygen.SamplePolicy
            public boolean isSampled() {
                return true;
            }
        });
    }

    public TraceEventContext(TraceEventObserver[] traceEventObserverArr, SamplePolicy samplePolicy) {
        this.mParentID = RAND.nextInt(Integer.MAX_VALUE);
        this.mObservers = traceEventObserverArr;
        this.mSamplePolicy = samplePolicy;
    }
}
