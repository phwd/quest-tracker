package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyCancelInviteParams {
    public String mPartyID;
    public String mUserID;

    public PartyCancelInviteParams(String str, String str2) {
        this.mUserID = str;
        this.mPartyID = str2;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("party_id", this.mPartyID);
        A01.put(OculusContent.FriendList.USER_TO_UNINVITE_VALUE, this.mUserID);
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
