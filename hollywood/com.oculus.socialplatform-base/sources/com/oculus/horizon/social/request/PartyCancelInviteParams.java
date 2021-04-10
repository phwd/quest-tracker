package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyCancelInviteParams {
    public String mPartyID;
    public String mUserID;

    public PartyCancelInviteParams(String str, String str2) {
        this.mUserID = str;
        this.mPartyID = str2;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("party_id", this.mPartyID);
        A04.put("user_to_uninvite", this.mUserID);
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
