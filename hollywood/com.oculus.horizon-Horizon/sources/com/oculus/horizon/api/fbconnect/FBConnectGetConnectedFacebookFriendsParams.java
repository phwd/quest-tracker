package com.oculus.horizon.api.fbconnect;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBConnectGetConnectedFacebookFriendsParams {
    public final String facebookAccessToken;
    public final int first;

    public String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A03("facebook_access_token", ImmutableMap.A02(GraphQLParamsHelper.SENSITIVE_STRING_VALUE, this.facebookAccessToken), "first", Integer.valueOf(this.first)));
    }

    public FBConnectGetConnectedFacebookFriendsParams(String str, int i) {
        this.first = i;
        this.facebookAccessToken = str;
    }
}
