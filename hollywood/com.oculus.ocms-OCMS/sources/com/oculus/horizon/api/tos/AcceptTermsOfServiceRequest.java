package com.oculus.horizon.api.tos;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class AcceptTermsOfServiceRequest extends ApiRequest<AcceptTermsOfServiceResponse> {
    public TosType type = TosType.TOS;

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("client_mutation_id", UUID.randomUUID().toString()).put("tos_type", this.type.toString()).build();
    }
}
