package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class EntitlementsWithAchievementsResponse {
    public Entitlements active_android_entitlements;

    public static class EntitlementWithAchievements {
        public Item item;
    }

    public static class Entitlements {
        public List<EntitlementWithAchievements> nodes;
    }

    public static class Image {
        public String uri;
    }

    public static class Item {
        public Image cover_square_image;
        public String display_name;
        public String id;
    }
}
