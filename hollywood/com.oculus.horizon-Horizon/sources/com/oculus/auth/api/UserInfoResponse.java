package com.oculus.auth.api;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class UserInfoResponse {
    @SerializedName("device_secret")
    public final String deviceSecret;
}
