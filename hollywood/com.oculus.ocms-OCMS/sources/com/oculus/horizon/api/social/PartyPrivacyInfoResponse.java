package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyPrivacyInfoResponse implements ValidatableApiResponse {
    @Nullable
    public Party node;

    public static class Party {
        public boolean has_active_link_sharing;
        public String id;
        public String party_type;
        public String url_for_deeplink_target_web_page;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.node == null) {
            throw new NullPointerException("PartyPrivacyInfoResponse did not have a node");
        }
    }
}
