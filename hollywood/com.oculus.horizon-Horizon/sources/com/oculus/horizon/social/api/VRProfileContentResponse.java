package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class VRProfileContentResponse {
    public final Viewer viewer;

    public static class AvatarImage {
        public final String uri;
    }

    public static class MostRecentPresence {
        public final boolean is_current;
        public final String presence;
        public final long vr_last_active_time;
    }

    public static class Node {
        public final String id;
    }

    public static class Nodes {
        @Nullable
        public final ArrayList<Node> nodes;
    }

    public static class Party {
        public final String id;
        public final String join_policy;
    }

    public static class ProfilePhoto {
        public final String uri;
    }

    public static class User {
        public final ViewerParty current_party;
        @Nullable
        public final Nodes invited_parties;
    }

    public static class UserOrBlockedUser {
        public final String alias;
        public final AvatarImage avatar_image;
        public final String biography;
        public final Party current_party;
        public final String friend_status;
        public final Boolean is_blocked_by_viewer;
        public final MostRecentPresence most_recent_presence;
        public final String name;
        public final String presence;
        public final String presence_status;
        public final ProfilePhoto profile_photo;
    }

    public static class Viewer {
        public final String fb_linked_status;
        public final User user;
        public final UserOrBlockedUser user_or_blocked_user;
    }

    public static class ViewerParty {
        public final String id;
        @Nullable
        public final Nodes invited_users;
    }
}
