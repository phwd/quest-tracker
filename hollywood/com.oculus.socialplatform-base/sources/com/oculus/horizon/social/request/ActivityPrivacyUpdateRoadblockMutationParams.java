package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ActivityPrivacyUpdateRoadblockMutationParams {
    public static final String PARAM_FALSE = "false";
    public static final String PARAM_TRUE = "true";
    public static final String PARAM_VALUE = "value";
    public boolean value;

    public String toString() {
        String str;
        if (this.value) {
            str = "true";
        } else {
            str = "false";
        }
        return GraphQLParamsHelper.encodeMutationParams(ImmutableMap.A05("value", str));
    }

    public ActivityPrivacyUpdateRoadblockMutationParams(boolean z) {
        this.value = z;
    }
}
