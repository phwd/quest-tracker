package com.oculus.horizon.api.social;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
@Nullsafe(Nullsafe.Mode.LOCAL)
public class PeopleNearbyResponse {
    @Nullable
    public SocialUser user;
}
