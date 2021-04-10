package com.oculus.horizon.parties.api;

import javax.annotation.concurrent.Immutable;

@Immutable
public class PartyJoinMutationParams {
    public static final String PARAM_PARTY_ID = "party_id";
    public final String partyId;
}
