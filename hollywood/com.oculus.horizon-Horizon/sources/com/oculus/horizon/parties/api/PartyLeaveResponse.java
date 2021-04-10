package com.oculus.horizon.parties.api;

import com.oculus.horizon.api.common.Party;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class PartyLeaveResponse {
    public Party party;
}
