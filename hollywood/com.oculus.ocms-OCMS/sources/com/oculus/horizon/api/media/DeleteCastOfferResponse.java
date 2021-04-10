package com.oculus.horizon.api.media;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeleteCastOfferResponse {
    @Nullable
    @SerializedName("client_mutation_id")
    public String mClientMutationId;
}
