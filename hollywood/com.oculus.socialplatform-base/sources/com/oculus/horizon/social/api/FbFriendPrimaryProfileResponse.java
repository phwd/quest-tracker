package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class FbFriendPrimaryProfileResponse {
    public final FbFriendPrimaryProfile fb_friend_primary_profile;
    public final Viewer viewer;

    public static class FbFriendPrimaryProfile {
        public final String bio;
        public final boolean is_currently_active;
        public final long last_active_time;
        public final String name;
        public final OcUser oc_user;
        public final String profile_picture_uri;
    }

    public static class Node {
        public final String id;
    }

    public static class Nodes {
        @Nullable
        public final ArrayList<Node> nodes;
    }

    public static class OcUser {
        public final Party current_party;
        public final String friend_status;
        public final String id;
        public final Presence most_recent_presence;
        public final String presence_status;
    }

    public static class Party {
        public final String id;
        public final String join_policy;
    }

    public static class Presence {
        public final boolean is_current;
        public final String presence;
        public final long vr_last_active_time;
    }

    public static class Viewer {
        public final ViewerUser user;
        public final String viewer_fb_id;
    }

    public static class ViewerParty {
        public final String id;
        @Nullable
        public final Nodes invited_users;
    }

    public static class ViewerUser {
        public final ViewerParty current_party;
        public final boolean fb_presence_sharing;
        @Nullable
        public final Nodes invited_parties;
    }
}
