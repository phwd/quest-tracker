package com.facebook.graphql.query.interfaces;

import com.facebook.graphql.query.interfaces.IGraphQLRequest;
import com.facebook.infer.annotation.ThreadSafe;

public interface IGraphQLRequestBuilder<Request extends IGraphQLRequest<ResponseModel>, ResponseModel> {
    @ThreadSafe
    Request build();
}
