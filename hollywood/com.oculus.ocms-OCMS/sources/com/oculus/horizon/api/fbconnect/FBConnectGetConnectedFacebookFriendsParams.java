package com.oculus.horizon.api.fbconnect;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBConnectGetConnectedFacebookFriendsParams {
    private final String facebookAccessToken;
    private final int first;

    public FBConnectGetConnectedFacebookFriendsParams(String str, int i) {
        this.first = i;
        this.facebookAccessToken = str;
    }

    public String toString() {
        return GraphQLParamsHelper.encodeParams(ImmutableMap.of("facebook_access_token", GraphQLParamsHelper.sensitiveString(this.facebookAccessToken), OculusContent.Paging.FIRST, Integer.valueOf(this.first)));
    }
}
