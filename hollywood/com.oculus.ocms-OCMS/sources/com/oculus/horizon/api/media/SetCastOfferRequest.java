package com.oculus.horizon.api.media;

import com.facebook.common.identifiers.SafeUUIDGenerator;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class SetCastOfferRequest extends ApiRequest<SetCastOfferResponse> {
    public final String mSdpCastOffer;

    public SetCastOfferRequest(String str) {
        this.mSdpCastOffer = str;
    }

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("client_mutation_id", SafeUUIDGenerator.randomUUID().toString()).put("offer", this.mSdpCastOffer).build();
    }
}
