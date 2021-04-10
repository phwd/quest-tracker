package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyIDParam {
    public final String mPartyID;

    public final String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A02("party_id", this.mPartyID));
    }

    public PartyIDParam(String str) {
        this.mPartyID = str;
    }
}
