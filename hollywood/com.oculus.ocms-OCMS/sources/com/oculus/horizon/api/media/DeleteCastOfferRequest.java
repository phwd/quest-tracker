package com.oculus.horizon.api.media;

import com.facebook.common.identifiers.SafeUUIDGenerator;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeleteCastOfferRequest extends ApiRequest<DeleteCastOfferResponse> {
    public final String mCastHandshakeId;

    public DeleteCastOfferRequest(String str) {
        this.mCastHandshakeId = str;
    }

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("client_mutation_id", SafeUUIDGenerator.randomUUID().toString()).put("web_rtc_handshake_id", this.mCastHandshakeId).build();
    }
}
