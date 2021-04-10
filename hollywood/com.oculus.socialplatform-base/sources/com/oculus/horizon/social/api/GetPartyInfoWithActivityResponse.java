package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GetPartyInfoWithActivityResponse {
    public final String id;
    public final PartyBlockedInvitedUsers party_blocked_invited_users;
    public final PartyBlockedUsers party_blocked_users;
    @Nullable
    public final PartyInviteActivity party_invite_activity;
    public final PartyInvitedUsers party_invited_users;
    public final PartyUsers party_users;

    public static class ActivityImage {
        public final String uri;
    }

    public static class Application {
        public final String id;
        public final String package_name;
    }

    public static class PartyBlockedInvitedUsers {
        public final int count;
    }

    public static class PartyBlockedUsers {
        public final int count;
    }

    public static class PartyInviteActivity {
        public final Application application;
        public final String deeplink;
        public final String id;
        public final ActivityImage image;
        public final String subtitle;
        public final String title;
    }

    public static class PartyInvitedUsers {
        public final int count;
    }

    public static class PartyUsers {
        public final int count;
    }
}
