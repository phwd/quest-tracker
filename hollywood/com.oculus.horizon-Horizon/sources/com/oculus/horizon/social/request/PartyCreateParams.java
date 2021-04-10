package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyCreateParams {
    @Nullable
    public final String mActivityID;
    @Nullable
    public final String mDestinationID;
    @Nullable
    public final String mMessageThreadKey;
    @Nullable
    public final String mPartyType;

    public PartyCreateParams(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.mActivityID = str;
        this.mPartyType = str2;
        this.mDestinationID = str3;
        this.mMessageThreadKey = str4;
    }

    public final String toString() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        String str = this.mActivityID;
        if (str != null) {
            A01.put(OculusContent.FriendList.INVITE_ACTIVITY_ID_VALUE, str);
        }
        String str2 = this.mPartyType;
        if (str2 != null) {
            A01.put("party_type", str2);
        }
        String str3 = this.mDestinationID;
        if (str3 != null) {
            A01.put("destination_id", str3);
        }
        String str4 = this.mMessageThreadKey;
        if (str4 != null) {
            A01.put(OculusContent.FriendList.MESSAGE_THREAD_KEY_PARAM, str4);
        }
        return GraphQLParamsHelper.encodeMutationParams(A01.build());
    }
}
