package com.oculus.horizon.social.api;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
@Nullsafe(Nullsafe.Mode.LOCAL)
public class SetNuxFlagForUserResponse {
    public boolean nux_value;
}
