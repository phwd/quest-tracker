package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartySetTypeParams {
    public String mPartyID;
    public String mPartyType;

    public PartySetTypeParams(String str, String str2) {
        this.mPartyID = str;
        this.mPartyType = str2;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("party_id", this.mPartyID);
        A04.put("party_type", this.mPartyType);
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
