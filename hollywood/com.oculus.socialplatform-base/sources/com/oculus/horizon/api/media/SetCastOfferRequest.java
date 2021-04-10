package com.oculus.horizon.api.media;

import X.AnonymousClass0IR;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class SetCastOfferRequest extends ApiRequest<SetCastOfferResponse> {
    public final boolean mEnableDataChannel;
    public final String mSdpCastOffer;

    public SetCastOfferRequest(String str, boolean z) {
        this.mSdpCastOffer = str;
        this.mEnableDataChannel = z;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", AnonymousClass0IR.A00().toString());
        A04.put("enable_data_channel", String.valueOf(this.mEnableDataChannel));
        A04.put("offer", this.mSdpCastOffer);
        return A04.build();
    }
}
