package com.oculus.horizon.api.media;

import X.AnonymousClass0IR;
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
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        A04.put("web_rtc_handshake_id", this.mCastHandshakeId);
        return A04.build();
    }
}
