package com.oculus.horizon.social.request;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class DeeplinkNonceParam {
    public final String mDeeplinkTargetId;
    public final String mLinkNonce;

    public String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A06("deeplink_target_id", this.mDeeplinkTargetId, "link_nonce", this.mLinkNonce));
    }

    public DeeplinkNonceParam(String str, String str2) {
        this.mDeeplinkTargetId = str;
        this.mLinkNonce = str2;
    }
}
