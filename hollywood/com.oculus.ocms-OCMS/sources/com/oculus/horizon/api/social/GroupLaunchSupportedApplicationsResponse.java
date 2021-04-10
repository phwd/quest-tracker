package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.List;

@SingleEntryMapResponse
public class GroupLaunchSupportedApplicationsResponse implements ValidatableApiResponse {
    public Viewer viewer;

    public static class AppEntitlement {
        public Application item;
    }

    public static class AppEntitlements {
        public List<AppEntitlement> nodes;
    }

    public static class Application {
        public String display_short_description;
        public String id;
        public int max_group_launch_capacity;
    }

    public static class CurrentParty {
        public PartyUsers party_users;
    }

    public static class PartyUser {
        public AppEntitlements app_entitlements;
        public String id;
    }

    public static class PartyUsers {
        public List<PartyUser> nodes;
    }

    public static class User {
        public AppEntitlements app_entitlements;
        public CurrentParty current_party;
    }

    public static class Viewer {
        public User user;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.viewer == null) {
            throw new NullPointerException("GroupLaunchSupportedApplicationsResponse did not have a viewer");
        }
    }
}
