package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyPrivacyInfoResponse implements ValidatableApiResponse {
    @Nullable
    public final Party node;

    public static class Party {
        public final boolean has_active_link_sharing;
        public final String id;
        public final String party_type;
        public final String url_for_deeplink_target_web_page;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.node == null) {
            throw new NullPointerException("PartyPrivacyInfoResponse did not have a node");
        }
    }
}
