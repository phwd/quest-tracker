package com.oculus.userserver.net;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public final class FetchDeviceUsersResponse {
    @SerializedName("users_on_device")
    @Nullable
    public final List<UserOnDevice> mUsersOnDevice;

    public static final class UserOnDevice {
        @SerializedName("id")
        @Nullable
        public final String mId;
        @SerializedName("user_status")
        @Nullable
        public final String mStatus;
    }
}
