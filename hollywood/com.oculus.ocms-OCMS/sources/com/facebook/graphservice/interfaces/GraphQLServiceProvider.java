package com.facebook.graphservice.interfaces;

import com.facebook.graphql.query.interfaces.IGraphQLRequest;

public interface GraphQLServiceProvider {
    <T extends IGraphQLRequest> boolean canGetGraphQLServiceForRequest(T t);

    <T extends IGraphQLRequest> GraphQLService getGraphQLServiceForRequest(T t);
}
