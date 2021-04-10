package com.oculus.horizon.api.push;

import com.facebook.infer.annotation.Nullsafe;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PushTokenResponse {
    @SerializedName("add_push_token")
    @Nullable
    public PushToken pushToken;
}
