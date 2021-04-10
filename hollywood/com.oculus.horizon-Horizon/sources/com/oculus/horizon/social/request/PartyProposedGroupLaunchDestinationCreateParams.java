package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyProposedGroupLaunchDestinationCreateParams {
    public String mDestinationID;
    public String mPartyID;

    public PartyProposedGroupLaunchDestinationCreateParams(String str, String str2) {
        this.mDestinationID = str;
        this.mPartyID = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("party_id", this.mPartyID);
        A01.put("destination_id", this.mDestinationID);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
