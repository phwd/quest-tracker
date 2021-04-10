package com.oculus.antipiracy;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class OculusDeveloperQueryResponse {
    @SerializedName("viewer")
    public final OculusDeveloperStatus mOculusDeveloperStatus;

    public static class OculusDeveloperStatus {
        public final boolean is_oculus_developer_blocked;
    }
}
