package com.facebook.graphql.query.interfaces;

import java.util.List;
import java.util.Map;

public interface IGraphQLRequest<T> {
    Map<String, String> getAdaptiveFetchClientParams();

    Map<String, String> getAdditionalHttpHeaders();

    List<String> getAnalyticTags();

    String getCallName();

    String getClientTraceId();

    boolean getEnableExperimentalGraphStoreCache();

    boolean getEnableOfflineCaching();

    long getFreshCacheAgeMs();

    boolean getMarkHttpRequestAsReplaySafe();

    long getMaxToleratedCacheAgeMs();

    int getNetworkTimeoutSeconds();

    boolean getOnlyCacheInitialNetworkResponse();

    boolean getParseOnClientExecutor();

    GraphQLQueryString<T> getQuery();

    IGraphQLQueryParams getQueryParams();

    boolean getTerminateAfterFreshResponse();

    Class<?> getTreeModelType();

    boolean isMutation();

    boolean isWriteToCacheDisabled();

    boolean shouldSendCacheAgeForAdaptiveFetch();
}
