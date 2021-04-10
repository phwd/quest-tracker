package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class RosterSizeResponse {
    public final Viewer viewer;

    public static class ApplicationInvites {
        public final int count;
    }

    public static class LobbyRoster {
        public final int count;
    }

    public static class User {
        public final ApplicationInvites application_invites;
        public final LobbyRoster lobby_roster;
    }

    public static class Viewer {
        public final User user;
    }
}
