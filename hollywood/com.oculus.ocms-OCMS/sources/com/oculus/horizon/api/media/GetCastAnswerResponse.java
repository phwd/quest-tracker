package com.oculus.horizon.api.media;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.api.common.WebRTCHandshake;
import com.oculus.http.core.base.ValidatableApiResponse;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class GetCastAnswerResponse implements ValidatableApiResponse {
    @Nullable
    public WebRTCHandshake cast_session_handshake;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.cast_session_handshake == null) {
            throw new NullPointerException("cast_session_handshake null");
        }
    }
}
