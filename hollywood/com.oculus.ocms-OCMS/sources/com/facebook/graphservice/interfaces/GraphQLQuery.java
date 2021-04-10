package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GraphQLQuery {
    private final HybridData mHybridData;

    public native int cacheTtlSeconds();

    public native int freshCacheTtlSeconds();

    public native boolean hasAnalyticsHints();

    public native boolean isLiveQuery();

    public native String queryName();

    public native boolean terminateAfterFreshResponse();

    static {
        NativeLoader.loadLibrary("graphservice-jni");
    }

    @DoNotStrip
    private GraphQLQuery(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
