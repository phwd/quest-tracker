package com.facebook.graphql.query.interfaces;

import com.facebook.graphql.query.interfaces.IMutationRequest;
import com.facebook.infer.annotation.ThreadSafe;

public interface IMutationRequestBuilder<Request extends IMutationRequest<ResponseModel>, ResponseModel> {
    @ThreadSafe
    Request build();
}
