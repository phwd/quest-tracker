package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FBDisconnectMutationResponse {
    public String client_mutation_id;
}
