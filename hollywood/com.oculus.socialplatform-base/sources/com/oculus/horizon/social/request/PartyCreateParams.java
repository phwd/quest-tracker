package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
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

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        String str = this.mActivityID;
        if (str != null) {
            A04.put("invite_activity_id", str);
        }
        String str2 = this.mPartyType;
        if (str2 != null) {
            A04.put("party_type", str2);
        }
        String str3 = this.mDestinationID;
        if (str3 != null) {
            A04.put("destination_id", str3);
        }
        String str4 = this.mMessageThreadKey;
        if (str4 != null) {
            A04.put("message_thread_key", str4);
        }
        return GraphQLParamsHelper.encodeMutationParams(A04.build());
    }
}
