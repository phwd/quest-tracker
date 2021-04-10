package com.oculus.horizon.api.fbconnect;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBProxyInstallMutationParams {
    private final String facebookAccessToken;
    private final String facebookId;
    private final String friendPolicy;

    public FBProxyInstallMutationParams(String str, String str2, FBFriendPolicy fBFriendPolicy) {
        this.facebookId = str;
        this.facebookAccessToken = str2;
        this.friendPolicy = fBFriendPolicy.toString();
    }

    public String toString() {
        return GraphQLParamsHelper.encodeMutationParams(ImmutableMap.of("facebook_id", this.facebookId, "facebook_access_token", this.facebookAccessToken, "friend_policy", this.friendPolicy));
    }
}
