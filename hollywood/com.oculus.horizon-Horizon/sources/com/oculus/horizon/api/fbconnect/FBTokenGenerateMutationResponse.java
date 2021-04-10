package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FBTokenGenerateMutationResponse {
    public final String access_token;
    public final long fb_app_id;
    public final long fb_user_id;
}
