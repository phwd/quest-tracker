package com.oculus.durableiap.net;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class IAPEntitlementsResponse {
    public static final String ACTIVE_STATE_PERMANENT = "PERMANENT";
    public final AppEntitlements entitlements;

    public static class ActiveAndroidEntitlement {
        public final AppItem item;
    }

    public static class ActiveAndroidEntitlements {
        public final List<ActiveAndroidEntitlement> nodes;
    }

    public static class AppEntitlements {
        public final ActiveAndroidEntitlements active_android_entitlements;
    }

    public static class AppItem {
        public final Grouping grouping;
        public final List<IAPEntitlement> iap_entitlements;
    }

    public static class Grouping {
        public final String id;
    }

    public static class IAPEntitlement {
        public final String active_state;
        public final String expiration_time;
        public final String grant_time;
        public final String id;
        public final Item item;
    }

    public static class Item {
        public final String sku;
    }
}
