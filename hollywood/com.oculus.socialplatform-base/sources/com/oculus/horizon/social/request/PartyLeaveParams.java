package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyLeaveParams {
    public String mPartyID;

    public PartyLeaveParams(String str) {
        this.mPartyID = str;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("party_id", this.mPartyID);
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
