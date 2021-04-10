package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class SingleIDParam {
    public final String mId;

    public String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A05("id", this.mId));
    }

    public SingleIDParam(String str) {
        this.mId = str;
    }
}
