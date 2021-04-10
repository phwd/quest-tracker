package com.facebook.graphql.query.interfaces;

import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.Executor;

public interface IGraphQLQueryExecutor {
    <T> void fetch(IGraphQLRequest<T> iGraphQLRequest, FutureCallback<IGraphQLResult<T>> futureCallback);

    <T> void fetch(IGraphQLRequest<T> iGraphQLRequest, FutureCallback<IGraphQLResult<T>> futureCallback, Executor executor);
}
