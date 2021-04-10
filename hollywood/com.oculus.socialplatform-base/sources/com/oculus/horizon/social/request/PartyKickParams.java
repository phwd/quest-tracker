package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyKickParams {
    public String mPartyID;
    public String[] mUserIDs;

    public PartyKickParams(String str, String[] strArr) {
        this.mPartyID = str;
        this.mUserIDs = strArr;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("party_id", this.mPartyID);
        A04.put("users_to_kick", this.mUserIDs);
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
