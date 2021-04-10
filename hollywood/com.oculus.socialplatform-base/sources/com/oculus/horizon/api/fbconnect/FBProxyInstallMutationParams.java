package com.oculus.horizon.api.fbconnect;

import X.AnonymousClass0th;
import com.google.common.collect.RegularImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FBProxyInstallMutationParams {
    public final String facebookAccessToken;
    public final String facebookId;
    public final String friendPolicy;

    public String toString() {
        String str = this.facebookId;
        String str2 = this.facebookAccessToken;
        String str3 = this.friendPolicy;
        AnonymousClass0th.A01("facebook_id", str);
        AnonymousClass0th.A01("facebook_access_token", str2);
        AnonymousClass0th.A01("friend_policy", str3);
        return GraphQLParamsHelper.encodeMutationParams(RegularImmutableMap.A00(3, new Object[]{"facebook_id", str, "facebook_access_token", str2, "friend_policy", str3}));
    }

    public FBProxyInstallMutationParams(String str, String str2, FBFriendPolicy fBFriendPolicy) {
        this.facebookId = str;
        this.facebookAccessToken = str2;
        this.friendPolicy = fBFriendPolicy.toString();
    }
}
