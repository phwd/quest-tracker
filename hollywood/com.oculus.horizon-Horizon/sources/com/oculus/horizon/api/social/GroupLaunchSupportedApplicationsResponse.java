package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.List;

@SingleEntryMapResponse
public class GroupLaunchSupportedApplicationsResponse implements ValidatableApiResponse {
    public final Viewer viewer;

    public static class AppEntitlement {
        public final Application item;
    }

    public static class AppEntitlements {
        public final List<AppEntitlement> nodes;
    }

    public static class Application {
        public final String display_short_description;
        public final String id;
        public final int max_group_launch_capacity;
    }

    public static class CurrentParty {
        public final PartyUsers party_users;
    }

    public static class PartyUser {
        public final AppEntitlements app_entitlements;
        public String id;
    }

    public static class PartyUsers {
        public final List<PartyUser> nodes;
    }

    public static class User {
        public final AppEntitlements app_entitlements;
        public final CurrentParty current_party;
    }

    public static class Viewer {
        public final User user;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.viewer == null) {
            throw new NullPointerException("GroupLaunchSupportedApplicationsResponse did not have a viewer");
        }
    }
}
