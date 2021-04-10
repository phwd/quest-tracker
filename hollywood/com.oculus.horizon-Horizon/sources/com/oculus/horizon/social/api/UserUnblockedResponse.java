package com.oculus.horizon.social.api;

import android.text.TextUtils;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class UserUnblockedResponse implements ValidatableApiResponse {
    public final String previously_blocked_user_id;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (TextUtils.isEmpty(this.previously_blocked_user_id)) {
            throw new NullPointerException("UserBlockedResponse had no previously_blocked_user_id");
        }
    }
}
