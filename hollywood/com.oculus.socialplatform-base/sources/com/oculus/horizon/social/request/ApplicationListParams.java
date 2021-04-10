package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ApplicationListParams {
    public static final String PARAM_APPLICATION_IDS = "application_ids";
    public final String[] mAppIds;

    public String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A05("application_ids", this.mAppIds));
    }

    public ApplicationListParams(String[] strArr) {
        this.mAppIds = strArr;
    }
}
