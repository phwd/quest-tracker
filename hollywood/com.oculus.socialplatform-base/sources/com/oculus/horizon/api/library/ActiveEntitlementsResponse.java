package com.oculus.horizon.api.library;

import com.oculus.horizon.api.common.Item;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class ActiveEntitlementsResponse {
    public ActiveEntitlements active_android_entitlements;

    public static class ActiveAndroidEntitlement {
        public String app_viewer_id;
        public Item item;
        public long last_used;
        public String signed_token;
    }

    public static class ActiveEntitlements {
        public List<ActiveAndroidEntitlement> nodes;
    }
}
