package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.Party;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class PartyCancelInviteResponse {
    public Party party;
}
