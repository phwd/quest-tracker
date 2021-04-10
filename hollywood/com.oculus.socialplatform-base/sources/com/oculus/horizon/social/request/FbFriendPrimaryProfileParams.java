package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FbFriendPrimaryProfileParams {
    public String mFriendId;

    public FbFriendPrimaryProfileParams(String str) {
        this.mFriendId = str;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("friend_id", this.mFriendId);
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A04.build());
    }
}
