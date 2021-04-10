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

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("party_id", this.mPartyID);
        A01.put("users_to_kick", this.mUserIDs);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
