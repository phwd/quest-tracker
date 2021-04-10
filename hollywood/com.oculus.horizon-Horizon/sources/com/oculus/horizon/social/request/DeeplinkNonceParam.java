package com.oculus.horizon.social.request;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.provider.OculusContent;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class DeeplinkNonceParam {
    public final String mDeeplinkTargetId;
    public final String mLinkNonce;

    public final String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A03(OculusContent.FriendList.DEEP_LINK_TARGET_ID_VALUE, this.mDeeplinkTargetId, OculusContent.FriendList.LINK_NONCE_VALUE, this.mLinkNonce));
    }

    public DeeplinkNonceParam(String str, String str2) {
        this.mDeeplinkTargetId = str;
        this.mLinkNonce = str2;
    }
}
