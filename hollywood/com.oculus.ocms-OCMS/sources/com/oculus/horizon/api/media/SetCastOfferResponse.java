package com.oculus.horizon.api.media;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
@Nullsafe(Nullsafe.Mode.LOCAL)
public class SetCastOfferResponse implements ValidatableApiResponse {
    @Nullable
    @SerializedName("cast_session_handshake")
    public CastSessionHandshake mCastSessionHandshake;
    @Nullable
    @SerializedName("client_mutation_id")
    public String mClientMutationId;

    public static class CastSessionHandshake {
        @Nullable
        public String id;
        @Nullable
        public String pin;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        CastSessionHandshake castSessionHandshake = this.mCastSessionHandshake;
        if (castSessionHandshake == null) {
            throw new NullPointerException("mCastSessionHandshake is null");
        } else if (castSessionHandshake.id == null) {
            throw new NullPointerException("mCastSessionHandshake.id is null");
        }
    }
}
