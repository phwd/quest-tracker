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
    public final CastSessionHandshake mCastSessionHandshake;
    @Nullable
    @SerializedName("client_mutation_id")
    public String mClientMutationId;

    public static class CastSessionHandshake {
        @Nullable
        public final String id;
        @Nullable
        public final String pin;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        CastSessionHandshake castSessionHandshake = this.mCastSessionHandshake;
        if (castSessionHandshake == null) {
            str = "mCastSessionHandshake is null";
        } else if (castSessionHandshake.id == null) {
            str = "mCastSessionHandshake.id is null";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
