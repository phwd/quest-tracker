package com.oculus.userserver.net;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public final class AddToDeviceResponse {
    @SerializedName("sid")
    public final String mSyncId;
}
