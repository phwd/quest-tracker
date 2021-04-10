package com.oculus.horizon.api.push;

import com.facebook.infer.annotation.Nullsafe;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PushToken {
    @SerializedName("token")
    @Nullable
    public final IndividualToken token;

    public static class IndividualToken {
        @SerializedName("id")
        public final long id;
    }
}
