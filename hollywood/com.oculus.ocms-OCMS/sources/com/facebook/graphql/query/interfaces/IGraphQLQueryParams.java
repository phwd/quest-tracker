package com.facebook.graphql.query.interfaces;

import java.util.Map;

public interface IGraphQLQueryParams {
    Map<String, Object> getParamsCopy();
}
