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

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("friend_id", this.mFriendId);
        return GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build());
    }
}
