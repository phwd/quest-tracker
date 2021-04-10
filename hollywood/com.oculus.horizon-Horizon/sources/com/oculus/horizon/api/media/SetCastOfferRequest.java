package com.oculus.horizon.api.media;

import X.AnonymousClass0KD;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class SetCastOfferRequest extends ApiRequest<SetCastOfferResponse> {
    public final String mSdpCastOffer;

    public SetCastOfferRequest(String str) {
        this.mSdpCastOffer = str;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", AnonymousClass0KD.A00().toString());
        A01.put("offer", this.mSdpCastOffer);
        return A01.build();
    }
}
