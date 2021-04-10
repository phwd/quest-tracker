package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PartySetMicrophoneChannelParams {
    public final PartyMicrophoneChannel mChannel;
    public final String mPartyID;

    public PartySetMicrophoneChannelParams(String str, PartyMicrophoneChannel partyMicrophoneChannel) {
        this.mPartyID = str;
        this.mChannel = partyMicrophoneChannel;
    }

    public String toString() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("party_id", this.mPartyID);
        A04.put("microphone_channel_selection", this.mChannel.toString());
        return GraphQLParamsHelper.encodeRankReviewParams(A04.build());
    }
}
