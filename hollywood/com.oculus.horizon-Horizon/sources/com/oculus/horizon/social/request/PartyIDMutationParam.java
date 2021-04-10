package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyIDMutationParam {
    public String mPartyID;

    public PartyIDMutationParam(String str) {
        this.mPartyID = str;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("party_id", this.mPartyID);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
