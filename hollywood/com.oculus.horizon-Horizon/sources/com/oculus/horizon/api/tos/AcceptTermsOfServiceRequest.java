package com.oculus.horizon.api.tos;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class AcceptTermsOfServiceRequest extends ApiRequest<AcceptTermsOfServiceResponse> {
    public TosType type = TosType.TOS;

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", UUID.randomUUID().toString());
        A01.put("tos_type", this.type.toString());
        return A01.build();
    }
}
