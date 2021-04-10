package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class SingleUserParams {
    public final String userId;

    public String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A05("user_id", this.userId));
    }

    public SingleUserParams(String str) {
        this.userId = str;
    }
}
