package com.oculus.horizon.api.media;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class CastNotifResponse {
    @SerializedName("client_mutation_id")
    public String mClientMutationId;
}
