package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.TigonErrorException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface GraphQLService {

    @DoNotStrip
    public interface DataCallbacks {
        @DoNotStrip
        void onError(TigonErrorException tigonErrorException, @Nullable Summary summary);

        @DoNotStrip
        void onUpdate(Tree tree, @Nullable Summary summary);
    }

    public @interface GraphQLRequestPurpose {
        public static final int FETCH = 2;
        public static final int NONE = 0;
        public static final int PREFETCH = 1;
        public static final int REFRESH = 3;
    }

    @DoNotStrip
    public interface OperationCallbacks {
        @DoNotStrip
        void onError(TigonErrorException tigonErrorException);

        @DoNotStrip
        void onSuccess();
    }

    @DoNotStrip
    @ThreadSafe
    public interface Token {
        @DoNotStrip
        void cancel();
    }

    void appendEdgeForKey(String str, Tree tree);

    void deleteEdgeForKey(String str, String str2);

    @Nullable
    Token experimentalResetForKey(String str, boolean z, OperationCallbacks operationCallbacks, Executor executor);

    Token handleQuery(GraphQLQuery graphQLQuery, DataCallbacks dataCallbacks, Executor executor);

    @Nullable
    Token loadNextPageForKey(String str, int i, int i2, boolean z, Map<String, Object> map, String[] strArr, OperationCallbacks operationCallbacks, Executor executor);

    @Nullable
    Token loadNextPageForKey(String str, int i, int i2, boolean z, Map<String, Object> map, String[] strArr, OperationCallbacks operationCallbacks, Executor executor, String str2);

    @Nullable
    Token loadPreviousPageForKey(String str, int i, Map<String, Object> map, String[] strArr, OperationCallbacks operationCallbacks, Executor executor);

    @Nullable
    Token loadPreviousPageForKey(String str, int i, Map<String, Object> map, String[] strArr, OperationCallbacks operationCallbacks, Executor executor, String str2);

    void prependEdgeForKey(String str, Tree tree);

    public static class ConfigHints {
        @DoNotStrip
        @Nullable
        public Map<String, String> adaptiveFetchClientParams;
        @DoNotStrip
        @Nullable
        public Map<String, String> additionalHttpHeaders;
        @DoNotStrip
        public String[] analyticTags;
        @DoNotStrip
        public int cacheTtlSeconds;
        @DoNotStrip
        public String clientTraceId;
        @DoNotStrip
        public boolean discardRequestIfNotLoggedIn;
        @DoNotStrip
        public boolean enableExperimentalGraphStoreCache;
        @DoNotStrip
        public boolean enableOfflineCaching;
        @DoNotStrip
        public boolean ensureCacheWrite;
        @DoNotStrip
        public int freshCacheTtlSeconds;
        @DoNotStrip
        public String friendlyNameOverride;
        @DoNotStrip
        public String locale;
        @DoNotStrip
        public boolean markHttpRequestReplaySafe;
        @DoNotStrip
        public int networkTimeoutSeconds;
        @DoNotStrip
        public boolean onlyCacheInitialNetworkResponse;
        @DoNotStrip
        public boolean parseOnClientExecutor;
        @GraphQLRequestPurpose
        @DoNotStrip
        public int requestPurpose;
        @DoNotStrip
        public boolean sendCacheAgeForAdaptiveFetch;
        @DoNotStrip
        public boolean terminateAfterFreshResponse;
        @DoNotStrip
        public String tigonQPLTraceId;

        public ConfigHints() {
            this.cacheTtlSeconds = Integer.MAX_VALUE;
            this.freshCacheTtlSeconds = 0;
            this.additionalHttpHeaders = null;
            this.networkTimeoutSeconds = -1;
            this.terminateAfterFreshResponse = false;
            this.friendlyNameOverride = "";
            this.parseOnClientExecutor = false;
            this.locale = "";
            this.analyticTags = new String[0];
            this.requestPurpose = 0;
            this.ensureCacheWrite = false;
            this.onlyCacheInitialNetworkResponse = false;
            this.enableExperimentalGraphStoreCache = false;
            this.enableOfflineCaching = false;
            this.markHttpRequestReplaySafe = false;
            this.sendCacheAgeForAdaptiveFetch = false;
            this.adaptiveFetchClientParams = null;
            this.tigonQPLTraceId = "";
            this.clientTraceId = "";
            this.discardRequestIfNotLoggedIn = false;
        }

        public ConfigHints(ConfigHints configHints) {
            this.cacheTtlSeconds = Integer.MAX_VALUE;
            this.freshCacheTtlSeconds = 0;
            this.additionalHttpHeaders = null;
            this.networkTimeoutSeconds = -1;
            this.terminateAfterFreshResponse = false;
            this.friendlyNameOverride = "";
            this.parseOnClientExecutor = false;
            this.locale = "";
            this.analyticTags = new String[0];
            this.requestPurpose = 0;
            this.ensureCacheWrite = false;
            this.onlyCacheInitialNetworkResponse = false;
            this.enableExperimentalGraphStoreCache = false;
            this.enableOfflineCaching = false;
            this.markHttpRequestReplaySafe = false;
            this.sendCacheAgeForAdaptiveFetch = false;
            this.adaptiveFetchClientParams = null;
            this.tigonQPLTraceId = "";
            this.clientTraceId = "";
            this.discardRequestIfNotLoggedIn = false;
            this.cacheTtlSeconds = configHints.cacheTtlSeconds;
            this.freshCacheTtlSeconds = configHints.freshCacheTtlSeconds;
            this.additionalHttpHeaders = configHints.additionalHttpHeaders;
            this.networkTimeoutSeconds = configHints.networkTimeoutSeconds;
            this.terminateAfterFreshResponse = configHints.terminateAfterFreshResponse;
            this.friendlyNameOverride = configHints.friendlyNameOverride;
            this.parseOnClientExecutor = configHints.parseOnClientExecutor;
            this.locale = configHints.locale;
            this.analyticTags = configHints.analyticTags;
            this.requestPurpose = configHints.requestPurpose;
            this.ensureCacheWrite = configHints.ensureCacheWrite;
            this.onlyCacheInitialNetworkResponse = configHints.onlyCacheInitialNetworkResponse;
            this.enableExperimentalGraphStoreCache = configHints.enableExperimentalGraphStoreCache;
            this.enableOfflineCaching = configHints.enableOfflineCaching;
            this.markHttpRequestReplaySafe = configHints.markHttpRequestReplaySafe;
            this.sendCacheAgeForAdaptiveFetch = configHints.sendCacheAgeForAdaptiveFetch;
            this.adaptiveFetchClientParams = configHints.adaptiveFetchClientParams;
            this.tigonQPLTraceId = configHints.tigonQPLTraceId;
            this.clientTraceId = configHints.clientTraceId;
        }
    }
}
