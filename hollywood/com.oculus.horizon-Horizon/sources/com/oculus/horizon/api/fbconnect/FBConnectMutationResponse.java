package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FBConnectMutationResponse {
    public String access_token;
    public String facebook_id;
}
