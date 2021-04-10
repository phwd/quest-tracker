package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GroupLaunchSetStateParams {
    public String mGroupLaunchID;
    public String mState;

    public GroupLaunchSetStateParams(String str, String str2) {
        this.mState = str;
        this.mGroupLaunchID = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("group_launch_id", this.mGroupLaunchID);
        A01.put("state", this.mState);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
