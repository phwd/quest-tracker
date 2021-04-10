package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class UserBlockedResponse {
    public BlockedUser blocked_user;
}
