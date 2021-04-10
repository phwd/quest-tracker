package com.facebook.graphql.query.interfaces;

import javax.annotation.Nullable;

public interface IGraphQLResult<T> {
    @Nullable
    T getResult();
}
