package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FBTokenGenerateMutationResponse {
    public String access_token;
    public long fb_app_id;
    public long fb_user_id;
}
