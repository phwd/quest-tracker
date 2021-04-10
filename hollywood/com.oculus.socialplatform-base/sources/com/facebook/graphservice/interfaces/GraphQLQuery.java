package com.facebook.graphservice.interfaces;

import X.AnonymousClass0lD;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GraphQLQuery {
    public final HybridData mHybridData;

    public native int cacheTtlSeconds();

    public native int freshCacheTtlSeconds();

    public native boolean hasAnalyticsHints();

    public native boolean isLiveQuery();

    public native String queryName();

    public native boolean terminateAfterFreshResponse();

    static {
        AnonymousClass0lD.A01("graphservice-jni");
    }

    @DoNotStrip
    public GraphQLQuery(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
