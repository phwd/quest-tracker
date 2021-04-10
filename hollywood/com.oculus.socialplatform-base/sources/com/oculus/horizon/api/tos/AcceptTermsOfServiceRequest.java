package com.oculus.horizon.api.tos;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class AcceptTermsOfServiceRequest extends ApiRequest<AcceptTermsOfServiceResponse> {
    public TosType type = TosType.TOS;

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", UUID.randomUUID().toString());
        A04.put("tos_type", this.type.toString());
        return A04.build();
    }
}
