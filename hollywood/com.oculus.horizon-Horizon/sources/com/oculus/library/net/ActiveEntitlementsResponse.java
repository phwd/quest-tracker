package com.oculus.library.net;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.library.model.GrantReason;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class ActiveEntitlementsResponse {
    public MyEntitlements entitlements;

    public static class ActiveAndroidEntitlement {
        @SerializedName("expiration_time")
        @Nullable
        public Long grantExpiration;
        @SerializedName("grant_reason")
        public GrantReason grantReason;
        @SerializedName("grant_time")
        public long grantTime;
        public Entitlement item;
        public long last_used;
        public String signed_token;
    }

    public static class ActiveEntitlements {
        public List<ActiveAndroidEntitlement> nodes;
    }

    public class MyEntitlements {
        public ActiveEntitlements active_android_entitlements;
        @Nullable
        public ActiveEntitlements entitlements;
        public final /* synthetic */ ActiveEntitlementsResponse this$0;
    }
}
