package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class SharePartyInfoResponse {
    @Nullable
    public final CurrentParty current_party;

    public static class CurrentParty {
        public final String fb_shareable_ent_id;
    }
}
