package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class SharePartyInfoResponse {
    @Nullable
    public CurrentParty current_party;

    public static class CurrentParty {
        public String fb_shareable_ent_id;
    }
}
