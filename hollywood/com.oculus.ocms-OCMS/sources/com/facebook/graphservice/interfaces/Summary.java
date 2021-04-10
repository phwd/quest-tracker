package com.facebook.graphservice.interfaces;

import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.base.MoreObjects;
import com.oculus.autoupdates.database.AutoUpdatesDBContract;

@DoNotStrip
public class Summary {
    public final long additiveParseTimeMs;
    public final int apiErrorCode;
    public final int attempts;
    public final long cacheSyncEnd;
    public final long cacheSyncStart;
    public final long cachedResponseAge;
    public final int code;
    public final String consistencySource;
    public final String debugInfo;
    public final String description;
    public final String[] experimentalUriPrefetch;
    public final String fbRequestId;
    public final long fetchCachedResponseEnd;
    public final long fetchCachedResponseStart;
    public final boolean freshResponse;
    public final boolean isFinal;
    public final boolean isNetworkComplete;
    public final boolean isSilent;
    public final boolean isTransient;
    public final long networkEnd;
    public final long networkStart;
    public final long parseStart;
    public final int parsedDataSize;
    public final String prefetchPredictionID;
    @Nullable
    public final GraphQLQuery query;
    public final boolean rejectedFromAdaptiveFetch;
    public final long requestEnd;
    public final long requestStart;
    public final boolean requiresReauth;
    public final long rtt;
    public final long serverFlushTime;
    public final long serverStartTime;
    @Source
    public final String source;
    public final String summary;
    public final long upstreamLatency;

    public @interface Source {
        public static final String CACHE_DEPRECATED = "cache";
        public static final String CONSISTENCY = "consistency";
        public static final String NETWORK = "network";
        public static final String UNSET = "";
    }

    public static class SummaryBuilder {
        private long additiveParseTimeMs;
        private int apiErrorCode;
        private int attempts;
        private long cacheSyncEnd;
        private long cacheSyncStart;
        private long cachedResponseAge;
        private int code;
        private String consistencySource;
        private String debugInfo;
        private String description;
        private String[] experimentalUriPrefetch;
        private String fbRequestId;
        private long fetchCachedResponseEnd;
        private long fetchCachedResponseStart;
        private boolean freshResponse;
        private boolean isFinal;
        private boolean isNetworkComplete;
        private boolean isSilent;
        private boolean isTransient;
        private long networkEnd;
        private long networkStart;
        private long parseStart;
        private int parsedDataSize;
        private String prefetchPredictionID;
        @Nullable
        private GraphQLQuery query;
        private boolean rejectedFromAdaptiveFetch;
        private long requestEnd;
        private long requestStart;
        private boolean requiresReauth;
        private long rtt;
        private long serverFlushTime;
        private long serverStartTime;
        @Source
        private String source;
        private String summary;
        private long upstreamLatency;

        private SummaryBuilder() {
            this.source = "";
            this.fbRequestId = "";
            this.summary = "";
            this.description = "";
            this.debugInfo = "";
            this.consistencySource = "";
            this.prefetchPredictionID = "";
        }

        public SummaryBuilder setQuery(GraphQLQuery graphQLQuery) {
            this.query = graphQLQuery;
            return this;
        }

        public SummaryBuilder setSource(String str) {
            this.source = str;
            return this;
        }

        public SummaryBuilder setFinal(boolean z) {
            this.isFinal = z;
            return this;
        }

        public SummaryBuilder setNetworkComplete(boolean z) {
            this.isNetworkComplete = z;
            return this;
        }

        public SummaryBuilder setFbRequestId(String str) {
            this.fbRequestId = str;
            return this;
        }

        public SummaryBuilder setAttempts(int i) {
            this.attempts = i;
            return this;
        }

        public SummaryBuilder setRequestStart(long j) {
            this.requestStart = j;
            return this;
        }

        public SummaryBuilder setNetworkStart(long j) {
            this.networkStart = j;
            return this;
        }

        public SummaryBuilder setNetworkEnd(long j) {
            this.networkEnd = j;
            return this;
        }

        public SummaryBuilder setParseStart(long j) {
            this.parseStart = j;
            return this;
        }

        public SummaryBuilder setRequestEnd(long j) {
            this.requestEnd = j;
            return this;
        }

        public SummaryBuilder setParsedDataSize(int i) {
            this.parsedDataSize = i;
            return this;
        }

        public SummaryBuilder setAdditiveParseTimeMs(long j) {
            this.additiveParseTimeMs = j;
            return this;
        }

        public SummaryBuilder setRejectedFromAdaptiveFetch(boolean z) {
            this.rejectedFromAdaptiveFetch = z;
            return this;
        }

        public SummaryBuilder setFetchCachedResponseStart(long j) {
            this.fetchCachedResponseStart = j;
            return this;
        }

        public SummaryBuilder setFetchCachedResponseEnd(long j) {
            this.fetchCachedResponseEnd = j;
            return this;
        }

        public SummaryBuilder setCachedResponseAge(long j) {
            this.cachedResponseAge = j;
            return this;
        }

        public SummaryBuilder setFreshResponse(boolean z) {
            this.freshResponse = z;
            return this;
        }

        public SummaryBuilder setCacheSyncStart(long j) {
            this.cacheSyncStart = j;
            return this;
        }

        public SummaryBuilder setCacheSyncEnd(long j) {
            this.cacheSyncEnd = j;
            return this;
        }

        public SummaryBuilder setCode(int i) {
            this.code = i;
            return this;
        }

        public SummaryBuilder setApiErrorCode(int i) {
            this.apiErrorCode = i;
            return this;
        }

        public SummaryBuilder setSummary(String str) {
            this.summary = str;
            return this;
        }

        public SummaryBuilder setDescription(String str) {
            this.description = str;
            return this;
        }

        public SummaryBuilder setDebugInfo(String str) {
            this.debugInfo = str;
            return this;
        }

        public SummaryBuilder setSilent(boolean z) {
            this.isSilent = z;
            return this;
        }

        public SummaryBuilder setTransient(boolean z) {
            this.isTransient = z;
            return this;
        }

        public SummaryBuilder setRequiresReauth(boolean z) {
            this.requiresReauth = z;
            return this;
        }

        public SummaryBuilder setConsistencySource(String str) {
            this.consistencySource = str;
            return this;
        }

        public SummaryBuilder setServerStartTime(long j) {
            this.serverStartTime = j;
            return this;
        }

        public SummaryBuilder setServerFlushTime(long j) {
            this.serverFlushTime = j;
            return this;
        }

        public SummaryBuilder setPrefetchPredictionID(String str) {
            this.prefetchPredictionID = str;
            return this;
        }

        public SummaryBuilder setRtt(long j) {
            this.rtt = j;
            return this;
        }

        public SummaryBuilder setUpstreamLatency(long j) {
            this.upstreamLatency = j;
            return this;
        }

        public SummaryBuilder setExperimentalUriPrefetch(String[] strArr) {
            this.experimentalUriPrefetch = strArr;
            return this;
        }

        public Summary build() {
            return new Summary(this.source, this.isFinal, this.isNetworkComplete, this.fbRequestId, this.attempts, this.requestStart, this.networkStart, this.networkEnd, this.parseStart, this.requestEnd, this.parsedDataSize, this.additiveParseTimeMs, this.fetchCachedResponseStart, this.fetchCachedResponseEnd, this.cachedResponseAge, this.freshResponse, this.code, this.apiErrorCode, this.summary, this.description, this.debugInfo, this.isSilent, this.isTransient, this.requiresReauth, this.consistencySource, this.serverStartTime, this.serverFlushTime, this.cacheSyncStart, this.cacheSyncEnd, this.rejectedFromAdaptiveFetch, this.prefetchPredictionID, this.rtt, this.upstreamLatency, this.experimentalUriPrefetch, this.query);
        }
    }

    public static SummaryBuilder Builder() {
        return new SummaryBuilder();
    }

    public static SummaryBuilder BuilderFromSummary(Summary summary2) {
        SummaryBuilder summaryBuilder = new SummaryBuilder();
        summaryBuilder.setSource(summary2.source).setFinal(summary2.isFinal).setNetworkComplete(summary2.isNetworkComplete).setFbRequestId(summary2.fbRequestId).setAttempts(summary2.attempts).setRequestStart(summary2.requestStart).setRequestEnd(summary2.requestEnd).setNetworkStart(summary2.networkStart).setNetworkEnd(summary2.networkEnd).setParseStart(summary2.parseStart).setParsedDataSize(summary2.parsedDataSize).setAdditiveParseTimeMs(summary2.additiveParseTimeMs).setRejectedFromAdaptiveFetch(summary2.rejectedFromAdaptiveFetch).setFetchCachedResponseStart(summary2.fetchCachedResponseStart).setFetchCachedResponseEnd(summary2.fetchCachedResponseEnd).setCachedResponseAge(summary2.cachedResponseAge).setFreshResponse(summary2.freshResponse).setCacheSyncStart(summary2.cacheSyncStart).setCacheSyncEnd(summary2.cacheSyncEnd).setCode(summary2.code).setApiErrorCode(summary2.apiErrorCode).setSummary(summary2.summary).setDescription(summary2.description).setDebugInfo(summary2.debugInfo).setSilent(summary2.isSilent).setTransient(summary2.isTransient).setRequiresReauth(summary2.requiresReauth).setConsistencySource(summary2.consistencySource).setServerStartTime(summary2.serverStartTime).setServerFlushTime(summary2.serverFlushTime).setPrefetchPredictionID(summary2.prefetchPredictionID).setRtt(summary2.rtt).setUpstreamLatency(summary2.upstreamLatency).setExperimentalUriPrefetch(summary2.experimentalUriPrefetch).setQuery(summary2.query);
        return summaryBuilder;
    }

    @DoNotStrip
    private Summary(@Source String str, boolean z, boolean z2, String str2, int i, long j, long j2, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9, boolean z3, int i3, int i4, String str3, String str4, String str5, boolean z4, boolean z5, boolean z6, String str6, long j10, long j11, long j12, long j13, boolean z7, String str7, long j14, long j15, String[] strArr) {
        this(str, z, z2, str2, i, j, j2, j3, j4, j5, i2, j6, j7, j8, j9, z3, i3, i4, str3, str4, str5, z4, z5, z6, str6, j10, j11, j12, j13, z7, str7, j14, j15, strArr, null);
    }

    @DoNotStrip
    private Summary(@Source String str, boolean z, boolean z2, String str2, int i, long j, long j2, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9, boolean z3, int i3, int i4, String str3, String str4, String str5, boolean z4, boolean z5, boolean z6, String str6, long j10, long j11, long j12, long j13, boolean z7, String str7, long j14, long j15, String[] strArr, @Nullable GraphQLQuery graphQLQuery) {
        this.source = str;
        this.isFinal = z;
        this.isNetworkComplete = z2;
        this.fbRequestId = str2;
        this.attempts = i;
        this.requestStart = j;
        this.networkStart = j2;
        this.networkEnd = j3;
        this.parseStart = j4;
        this.requestEnd = j5;
        this.parsedDataSize = i2;
        this.additiveParseTimeMs = j6;
        this.fetchCachedResponseStart = j7;
        this.fetchCachedResponseEnd = j8;
        this.cachedResponseAge = j9;
        this.freshResponse = z3;
        this.code = i3;
        this.apiErrorCode = i4;
        this.summary = str3;
        this.description = str4;
        this.debugInfo = str5;
        this.isSilent = z4;
        this.isTransient = z5;
        this.requiresReauth = z6;
        this.consistencySource = str6;
        this.serverStartTime = j10;
        this.serverFlushTime = j11;
        this.cacheSyncStart = j12;
        this.cacheSyncEnd = j13;
        this.rejectedFromAdaptiveFetch = z7;
        this.prefetchPredictionID = str7;
        this.rtt = j14;
        this.upstreamLatency = j15;
        this.experimentalUriPrefetch = strArr;
        this.query = graphQLQuery;
    }

    public String getQueryName() {
        GraphQLQuery graphQLQuery = this.query;
        return graphQLQuery != null ? graphQLQuery.queryName() : "";
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add("source", this.source).add("isFinal", this.isFinal).add("isNetworkComplete", this.isNetworkComplete).add(AutoUpdatesDBContract.AutoUpdatesDBTable.COLS.ATTEMPTS, this.attempts).add("fbRequestId", this.fbRequestId).add("requestStart", this.requestStart).add("networkStart", this.networkStart).add("networkEnd", this.networkEnd).add("parseStart", this.parseStart).add("requestEnd", this.requestEnd).add("parsedDataSize", this.parsedDataSize).add("additiveParseTimeMs", this.additiveParseTimeMs).add("fetchCachedResponseStart", this.fetchCachedResponseStart).add("fetchCachedResponseEnd", this.fetchCachedResponseEnd).add("cachedResponseAge", this.cachedResponseAge).add("freshResponse", this.freshResponse).add("consistencySource", this.consistencySource).add("serverStartTime", this.serverStartTime).add("serverFlushTime", this.serverFlushTime).add("rejectedFromAdaptiveFetch", this.rejectedFromAdaptiveFetch).add("prefetchPredictionID", this.prefetchPredictionID).add("rtt", this.rtt).add("upstreamLatency", this.upstreamLatency);
        Object obj = this.experimentalUriPrefetch;
        if (obj == null) {
            obj = new int[0];
        }
        return add.add("experimentalUriPrefetch", obj).toString();
    }
}
